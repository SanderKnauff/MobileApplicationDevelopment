package ooo.sansk.rockpaperscissors

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ooo.sansk.rockpaperscissors.adapter.GameAdapter
import ooo.sansk.rockpaperscissors.database.GameHistoryRepository
import ooo.sansk.rockpaperscissors.model.Game
import ooo.sansk.rockpaperscissors.model.GameResult
import ooo.sansk.rockpaperscissors.model.Move
import java.lang.IllegalArgumentException
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var gameHistoryRepository: GameHistoryRepository
    private var products = mutableListOf<Game>()
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar?.title = getString(R.string.main_activity_title)

        gameHistoryRepository = GameHistoryRepository(this)
        initViews()
    }

    private fun initViews() {
        btRock.setOnClickListener { playGame(Move.ROCK) }
        btPaper.setOnClickListener { playGame(Move.PAPER) }
        btScissors.setOnClickListener { playGame(Move.SCISSORS) }
    }

    private fun playGame(playerMove: Move) {
        val cpuMove = Move.values().random()
        val gameResult = getGameResult(playerMove, cpuMove)

        ivChoicePlayer.setImageResource(playerMove.drawableId)
        ivChoiceCPU.setImageResource(cpuMove.drawableId)
        tvResult.setText(gameResult.message)
            mainScope.launch {
                val product = Game(
                    date = Date(),
                    playerMove = playerMove,
                    cpuMove = cpuMove,
                    result = gameResult
                )

                withContext(Dispatchers.IO) {
                    gameHistoryRepository.insertGame(product)
                }
            }
    }

    private fun getGameResult(playerMove: Move, cpuMove: Move): GameResult {
        return when(playerMove) {
            Move.ROCK -> when(cpuMove) {
                Move.ROCK -> GameResult.DRAW
                Move.PAPER -> GameResult.LOSE
                Move.SCISSORS -> GameResult.WIN
            }
            Move.PAPER -> when(cpuMove) {
                Move.ROCK -> GameResult.WIN
                Move.PAPER -> GameResult.DRAW
                Move.SCISSORS -> GameResult.LOSE
            }
            Move.SCISSORS -> when(cpuMove) {
                Move.ROCK -> GameResult.LOSE
                Move.PAPER -> GameResult.WIN
                Move.SCISSORS -> GameResult.DRAW
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open_history -> {
                startActivity(Intent(this, GameHistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
