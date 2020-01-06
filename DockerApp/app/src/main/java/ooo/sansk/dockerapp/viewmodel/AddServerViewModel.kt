package ooo.sansk.dockerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ooo.sansk.dockerapp.R
import ooo.sansk.dockerapp.database.ServerEntryRepository
import ooo.sansk.dockerapp.model.ServerEntry

class AddServerViewModel(application: Application): AndroidViewModel(application) {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val serverEntryRepository = ServerEntryRepository(application.applicationContext)

    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()
    val serverName = MutableLiveData<String>()
    val serverUrl = MutableLiveData<String>()

    fun isValid(): Boolean {
        if(serverName.value.isNullOrBlank()) {
            error.value = getApplication<Application>().getString(R.string.error_server_name_null)
            return false
        } else if (serverUrl.value.isNullOrBlank()) {
            error.value = getApplication<Application>().getString(R.string.error_server_url_null)
            return false
        } else if (!serverUrl.value!!.startsWith("http://") && !serverUrl.value!!.startsWith("https://")) {
            error.value = getApplication<Application>().getString(R.string.error_server_name_no_protocol)
            return false
        }
        return true
    }

    fun addServerEntry() {
        if (!isValid()) {
            return
        }
        mainScope.launch {
            withContext(Dispatchers.IO) {
                serverEntryRepository.insertServer(ServerEntry(null, serverName.value!!, serverUrl.value!!))
            }
            success.value = true
        }
    }

}