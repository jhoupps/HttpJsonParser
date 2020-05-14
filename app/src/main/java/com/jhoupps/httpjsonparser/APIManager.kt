package com.jhoupps.httpjsonparser
import android.app.Application
import android.util.Log
import com.jhoupps.httpjsonparser.Artist
import retrofit2.Call
import retrofit2.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class APIManager (private val artistService: ArtistService) {
/*
    fun getArtist(onArtistReady: (Artist) -> Unit, onError: (() -> Unit)? = null) {
        artistService.artist().enqueue(object : Callback<Artist> {
            override fun onResponse(call: Call<Artist>, response: Response<Artist>) {
                val artist = response.body()
                if (artist != null) {
                    onArtistReady(artist)
                } else {
                    onError?.invoke()
                }
            }

            override fun onFailure(call: Call<Artist>, t: Throwable) {
                onError?.invoke()
            }
        })

    }
*/
    //TODO THIS LATER

    fun getListOfArtist(onArtistReady: (AllArtists) -> Unit, onError: (() -> Unit)? = null) {

        artistService.allArtists().enqueue(object : Callback<AllArtists> {
            override fun onResponse(call: Call<AllArtists>, response: Response<AllArtists>) {
                val allArtists = response.body()
                if (allArtists != null) {
                    onArtistReady(allArtists)
                } else {
                    onError?.invoke()
                }
            }

            override fun onFailure(call: Call<AllArtists>, t: Throwable) {
                onError?.invoke()
                Log.i("jhoupps", "ERROR, WILL ROBINSON")
            }
        })
    }


}
