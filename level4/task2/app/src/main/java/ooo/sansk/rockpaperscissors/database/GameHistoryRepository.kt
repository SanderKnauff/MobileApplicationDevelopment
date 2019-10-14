package ooo.sansk.rockpaperscissors.database

import android.content.Context
import ooo.sansk.rockpaperscissors.model.Game

class GameHistoryRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database =
            RockPaperScissorsRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

}
