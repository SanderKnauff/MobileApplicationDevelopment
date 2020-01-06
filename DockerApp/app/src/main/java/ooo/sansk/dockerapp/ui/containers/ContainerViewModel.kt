package ooo.sansk.dockerapp.ui.containers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dockerjava.api.model.Container
import ooo.sansk.dockerapp.network.DockerWebClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContainerViewModel : ViewModel() {

    val error = MutableLiveData<String>()
    val containers = MutableLiveData<List<Container>>()
    val includeInactive = MutableLiveData<Boolean>(false)

    fun refreshContainers() {
        Log.i(this::class.java.name, "Refeshing Containers")
        DockerWebClient.dockerWebService!!.getContainers(includeInactive.value?: false)
            .enqueue(object : Callback<List<Container>> {
                override fun onFailure(call: Call<List<Container>>, t: Throwable) {
                    error.value = t.message
                }

                override fun onResponse(
                    call: Call<List<Container>>,
                    response: Response<List<Container>>
                ) {
                    containers.value = response.body()
                }
            })
    }
}