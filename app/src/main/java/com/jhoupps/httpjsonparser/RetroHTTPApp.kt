package com.jhoupps.httpjsonparser
import android.app.Application
import com.jhoupps.httpjsonparser.Artist
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

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

interface ArtistService {
    //Todo

    @GET("codesnippets/master/allartists.json") //this is an annotation, as designated by the @
    fun allArtists(): Call<AllArtists> //this expects an annotation above it
/*
    @GET("/id/538/256/256.jpg")
    fun artist(): Call<Artist>*/
}