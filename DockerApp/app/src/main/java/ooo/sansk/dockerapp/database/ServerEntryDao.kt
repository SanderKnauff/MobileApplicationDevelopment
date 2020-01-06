package ooo.sansk.dockerapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ooo.sansk.dockerapp.model.ServerEntry

@Dao
interface ServerEntryDao {

    @Insert
    suspend fun insertServerEntry(serverEntry: ServerEntry)

    @Query("SELECT * FROM serverEntry")
    fun getAllEntries(): LiveData<List<ServerEntry>>

    @Update
    suspend fun updateServerEntry(serverEntry: ServerEntry)

    @Delete
    suspend fun deleteServerEntry(serverEntry: ServerEntry)
}