package ooo.sansk.dockerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.dockerjava.api.command.InspectImageResponse
import com.github.dockerjava.api.model.Container
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ooo.sansk.dockerapp.database.ServerEntryRepository
import ooo.sansk.dockerapp.model.ServerEntry
import ooo.sansk.dockerapp.network.DockerWebClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    val image = MutableLiveData<InspectImageResponse>()
    val error = MutableLiveData<String>()
    val imageId = MutableLiveData<String>()

    fun retrieveImage() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                if (imageId.value.isNullOrBlank()) {
                    return@withContext
                }
                DockerWebClient.dockerWebService!!.getImageDetails(imageId.value!!).enqueue(object :
                    Callback<InspectImageResponse> {
                    override fun onFailure(call: Call<InspectImageResponse>, t: Throwable) {
                        error.value = t.message
                    }

                    override fun onResponse(
                        call: Call<InspectImageResponse>,
                        response: Response<InspectImageResponse>
                    ) {
                        image.value = response.body()
                    }
                })
            }
        }
    }

    fun deleteImage(onFinish: Callback<Void>) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                DockerWebClient.dockerWebService!!.deleteImage(imageId.value!!).enqueue(object :
                    Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        error.value = t.message
                        onFailure(call, t)
                    }

                    override fun onResponse(
                        call: Call<Void>,
                        response: Response<Void>
                    ) {
                        onFinish.onResponse(call, response)
                    }
                })
            }
        }
    }
}