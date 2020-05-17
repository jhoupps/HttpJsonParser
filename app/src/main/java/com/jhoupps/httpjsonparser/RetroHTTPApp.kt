package com.jhoupps.httpjsonparser
import android.app.Application
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//This class works with the Retrofit external library
//It extends the application class
//It does the work of fetching JSON data from an external url
class RetroHTTPApp : Application(){
    lateinit var apiManager: APIManager
    private lateinit var artistService: ArtistService

    override fun onCreate() {
        super.onCreate()

        // Init Retrofit + ArtistService
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/echeeUW/")
            .addConverterFactory(GsonConverterFactory.create()) // this will automatically apply Gson conversion :)
            .build()
        artistService = retrofit.create(ArtistService::class.java)

        // Load managers
        apiManager = APIManager(artistService)
    }
}

//This service is used to store the end point for the url called for the JSON by the API manager
interface ArtistService {
    @GET("codesnippets/master/allartists.json") //this is an annotation, as designated by the @
    fun allArtists(): Call<AllArtists> //this expects an annotation above it
}