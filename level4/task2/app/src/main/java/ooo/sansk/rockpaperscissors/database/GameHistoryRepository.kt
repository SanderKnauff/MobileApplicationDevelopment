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

    suspend fun insertGame(product: Game) {
        gameDao.insertGame(product)
    }

    suspend fun deleteGame(product: Game) {
        gameDao.deleteGame(product)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

}
