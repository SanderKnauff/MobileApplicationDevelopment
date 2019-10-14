package ooo.sansk.rockpaperscissors.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.Instant
import java.util.*

@Parcelize
@Entity(tableName = "game_table")
data class Game(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long? = null,
                @ColumnInfo(name = "date") var date: Date,
                @ColumnInfo(name = "player_move") var playerMove: Move,
                @ColumnInfo(name = "cpu_move") var cpuMove: Move,
                @ColumnInfo(name = "result") var result: GameResult
): Parcelable