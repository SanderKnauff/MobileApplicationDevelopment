package ooo.sansk.gamebacklog.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "game")
data class Game(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long? = null,
    var title: String,
    var platform: String,
    var releaseDate: Date?
) : Parcelable, Comparable<Game> {
    override fun compareTo(other: Game): Int {
        return releaseDate?.compareTo(other.releaseDate) ?: -1
    }
}
