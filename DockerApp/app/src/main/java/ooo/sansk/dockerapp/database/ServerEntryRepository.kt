package ooo.sansk.dockerapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import ooo.sansk.dockerapp.model.ServerEntry

class ServerEntryRepository(context: Context) {

    private val serverEntryDao: ServerEntryDao

    init {
        val database = DockerAppRoomDatabase.getDatabase(context)
        serverEntryDao = database!!.serverEntryDao()
    }

    fun getServers(): LiveData<List<ServerEntry>> {
        return serverEntryDao.getAllEntries()
    }

    suspend fun insertServer(serverEntry: ServerEntry) {
        serverEntryDao.insertServerEntry(serverEntry)
    }

    suspend fun deleteServer(serverEntry: ServerEntry) {
        serverEntryDao.deleteServerEntry(serverEntry)
    }


}