package ooo.sansk.dockerapp.network

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.dockerjava.api.command.InspectContainerResponse
import com.github.dockerjava.api.command.InspectImageResponse
import com.github.dockerjava.api.model.Container
import com.github.dockerjava.api.model.Image
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object DockerWebClient {

    var host: String = ""
        set(value) {
            field = value
            rebuildRetrofit()
        }

    var authentication: String = ""
        set(value) {
            field = value
            rebuildRetrofit()
        }

    var dockerWebService: DockerWebService? = null
    private set

    fun rebuildRetrofit() {
        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                .header("Authentication", authentication)
                .build()

            chain.proceed(request)
        }
        val objectMapper = ObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val http = Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .baseUrl(host)
            .client(httpClient.build())
            .build()

        dockerWebService = http.create(DockerWebService::class.java)
    }

    interface DockerWebService {

        @GET("/api/containers")
        fun getContainers(@Query("includeInactiveContainers") includeInactiveContainers: Boolean): Call<List<Container>>

        @GET("/api/containers/{id}")
        fun getContainerDetails(@Path("id") id: String): Call<InspectContainerResponse>

        @GET("/api/images")
        fun getImages(): Call<List<Image>>

        @GET("/api/images/{id}")
        fun getImageDetails(@Path("id") id: String): Call<InspectImageResponse>

        @DELETE("/api/images/{id}")
        fun deleteImage(@Path("id") id: String): Call<Void>
    }
}