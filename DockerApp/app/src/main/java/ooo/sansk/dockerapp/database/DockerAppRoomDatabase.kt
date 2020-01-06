package ooo.sansk.dockerapp.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ooo.sansk.dockerapp.model.ServerEntry
import java.util.*

@Database(entities = [ServerEntry::class], version = 1, exportSchema = false)
abstract class DockerAppRoomDatabase : RoomDatabase() {

    abstract fun serverEntryDao(): ServerEntryDao

    companion object {
        private const val DATABASE_NAME = "DOCKER_APP_DATABASE"

        @Volatile
        private var INSTANCE: DockerAppRoomDatabase? = null

        fun getDatabase(context: Context): DockerAppRoomDatabase? {
            if (INSTANCE != null) {
                return INSTANCE
            }
            synchronized(DockerAppRoomDatabase::class.java) {
                if (INSTANCE != null) {
                    return INSTANCE
                }
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DockerAppRoomDatabase::class.java,
                    DATABASE_NAME
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        INSTANCE.let {
                            CoroutineScope(Dispatchers.IO).launch {
                                it!!.serverEntryDao().insertServerEntry(
                                    ServerEntry(null, "Server A", "https://a.com")
                                )
                                it.serverEntryDao().insertServerEntry(
                                    ServerEntry(null, "LocalServer", "https://localhost:8080")
                                )
                            }
                        }
                    }
                })
                    .fallbackToDestructiveMigration()
                    .build()
                return INSTANCE
            }

        }

    }

}