package ooo.sansk.dockerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ooo.sansk.dockerapp.database.ServerEntryRepository
import ooo.sansk.dockerapp.model.ServerEntry

class ServerListViewModel(application: Application) : AndroidViewModel(application) {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val serverEntryRepository = ServerEntryRepository(application.applicationContext)

    val servers = serverEntryRepository.getServers()

    fun deleteServerEntry(serverEntry: ServerEntry) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                serverEntryRepository.deleteServer(serverEntry)
            }
        }
    }
}