package ooo.sansk.dockerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ooo.sansk.dockerapp.model.ServerEntry

class LoginActivityViewModel(application: Application): AndroidViewModel(application) {

    val server = MutableLiveData<ServerEntry>()
}