package ooo.sansk.dockerapp.ui.images

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dockerjava.api.model.Container
import com.github.dockerjava.api.model.Image
import ooo.sansk.dockerapp.network.DockerWebClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagesViewModel : ViewModel() {

    val error = MutableLiveData<String>()
    val images = MutableLiveData<List<Image>>()

    fun refreshImages() {
        Log.i(this::class.java.name, "Refeshing Containers")
        DockerWebClient.dockerWebService!!.getImages()
            .enqueue(object : Callback<List<Image>> {
                override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                    error.value = t.message
                }

                override fun onResponse(
                    call: Call<List<Image>>,
                    response: Response<List<Image>>
                ) {
                    images.value = response.body()
                }
            })
    }
}