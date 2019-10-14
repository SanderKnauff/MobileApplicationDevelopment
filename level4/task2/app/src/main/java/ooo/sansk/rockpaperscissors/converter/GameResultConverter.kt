package ooo.sansk.rockpaperscissors.converter

import androidx.room.TypeConverter
import ooo.sansk.rockpaperscissors.model.GameResult
import ooo.sansk.rockpaperscissors.model.Move
import java.util.*

class GameResultConverter {

    @TypeConverter
    fun resultToString(result: GameResult): String {
        return result.toString()
    }

    @TypeConverter
    fun longToDate(value: String): GameResult {
        return GameResult.valueOf(value)
    }
}