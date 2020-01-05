package ooo.sansk.gamebacklog.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ooo.sansk.gamebacklog.model.Game

@Dao
interface GameDao {

    @Insert
    suspend fun insertGame(game: Game)

    @Query("SELECT * FROM game")
    fun getGames(): LiveData<List<Game>>

    @Update
    suspend fun updateGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM game")
    suspend fun deleteGames()

}

