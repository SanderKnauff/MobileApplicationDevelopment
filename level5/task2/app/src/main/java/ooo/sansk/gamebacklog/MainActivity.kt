package ooo.sansk.gamebacklog

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ooo.sansk.gamebacklog.adapter.GameAdapter
import ooo.sansk.gamebacklog.viewmodel.MainActivityViewModel
import java.util.*

const val REQUEST_CODE_ADD_GAME = 1

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var gameAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        gameAdapter = GameAdapter()

        mainActivityViewModel.games.observe(this, Observer { games ->
            gameAdapter.games = games
            gameAdapter.notifyDataSetChanged()
        })
    }

    private fun initViews() {
        rvGameList.adapter = gameAdapter
        rvGameList.layoutManager = LinearLayoutManager(this)
        createItemTouchHelper().attachToRecyclerView(rvGameList)

        fab.setOnClickListener {
            startActivityForResult(Intent(this, AddActivity::class.java), REQUEST_CODE_ADD_GAME)
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder,target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = gameAdapter.games[position]
                mainActivityViewModel.deleteGame(gameToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_shopping_list -> {
                mainActivityViewModel.deleteGames()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

