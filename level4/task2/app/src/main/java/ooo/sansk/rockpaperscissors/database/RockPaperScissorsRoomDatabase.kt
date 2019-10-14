package ooo.sansk.rockpaperscissors.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ooo.sansk.rockpaperscissors.converter.DateConverter
import ooo.sansk.rockpaperscissors.converter.GameResultConverter
import ooo.sansk.rockpaperscissors.converter.MoveConverter
import ooo.sansk.rockpaperscissors.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, GameResultConverter::class, MoveConverter::class)
abstract class RockPaperScissorsRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "ROCK_PAPER_SCISSORS_DATABASE"

        @Volatile
        private var rockPaperScissorsRoomDatabaseInstance: RockPaperScissorsRoomDatabase? = null

        fun getDatabase(context: Context): RockPaperScissorsRoomDatabase? {
            if (rockPaperScissorsRoomDatabaseInstance == null) {
                synchronized(RockPaperScissorsRoomDatabase::class.java) {
                    if (rockPaperScissorsRoomDatabaseInstance == null) {
                        rockPaperScissorsRoomDatabaseInstance =
                            Room.databaseBuilder(context.applicationContext,
                                RockPaperScissorsRoomDatabase::class.java,
                                DATABASE_NAME
                            ).build()
                    }
                }
            }
            return rockPaperScissorsRoomDatabaseInstance
        }
    }

}