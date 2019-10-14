package ooo.sansk.rockpaperscissors

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_game_history.*
import kotlinx.android.synthetic.main.content_game_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ooo.sansk.rockpaperscissors.adapter.GameAdapter
import ooo.sansk.rockpaperscissors.database.GameHistoryRepository
import ooo.sansk.rockpaperscissors.model.Game

class GameHistoryActivity : AppCompatActivity() {

    private lateinit var gameHistoryRepository: GameHistoryRepository
    private var games = mutableListOf<Game>()
    private val gamesAdapter = GameAdapter(games)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_history)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.game_history_title)

        gameHistoryRepository = GameHistoryRepository(this)
        initViews()
    }

    private fun initViews() {
        rvGameHistory.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvGameHistory.adapter = gamesAdapter
        rvGameHistory.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        getGamesFromDatabase()
    }

    private fun getGamesFromDatabase() {
        mainScope.launch {
            val gameList = withContext(Dispatchers.IO) {
                gameHistoryRepository.getAllGames()
            }
            this@GameHistoryActivity.games.clear()
            this@GameHistoryActivity.games.addAll(gameList)
            this@GameHistoryActivity.gamesAdapter.notifyDataSetChanged()
        }
    }

    private fun clearHistory() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameHistoryRepository.deleteAllGames()
            }
            getGamesFromDatabase()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_game_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear_history -> {
                clearHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
