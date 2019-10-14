package ooo.sansk.rockpaperscissors.converter

import androidx.room.TypeConverter
import ooo.sansk.rockpaperscissors.model.Move
import java.util.*

class MoveConverter {

    @TypeConverter
    fun moveToString(move: Move): String {
        return move.toString()
    }

    @TypeConverter
    fun longToDate(value: String): Move {
        return Move.valueOf(value)
    }
}