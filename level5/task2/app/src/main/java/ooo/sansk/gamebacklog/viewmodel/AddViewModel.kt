package ooo.sansk.gamebacklog.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ooo.sansk.gamebacklog.database.GameRepository
import ooo.sansk.gamebacklog.model.Game

class AddViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val game = MutableLiveData<Game?>()
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()

    fun addGame() {
        if (isGameValid()) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(game.value!!)
                }
                success.value = true
            }
        }
    }

    private fun isGameValid(): Boolean {
        return when {
            game.value == null -> {
                error.value = "Game must not be null"
                false
            }
            game.value!!.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            game.value!!.title.isBlank() -> {
                error.value = "Platform must not be empty"
                false
            }
            game.value!!.releaseDate == null -> {
                error.value = "Release date must be valid"
                false
            }
            else -> true
        }
    }

}
