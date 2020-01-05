package ooo.sansk.gamebacklog.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ooo.sansk.gamebacklog.database.GameRepository
import ooo.sansk.gamebacklog.model.Game
import java.util.*

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val games = gameRepository.getGames()

    fun deleteGame(gameToDelete: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteGame(gameToDelete)
            }
        }
    }

    fun deleteGames() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteGames()
            }
        }
    }

}
