package com.jhoupps.httpjsonparser
import android.util.Log
import retrofit2.Call
import retrofit2.*
import kotlin.random.Random

//This class holds helper functions for working with the RetroHTTP tool
class APIManager (private val artistService: ArtistService) {
    //this mutable list is to fulfill the "holds and changes state" requirement
     var favoriteCount = mutableListOf<Int>(0)

    //This function gets the favorite count from the stored state
    //It does so by creating a favorite count if it does not yet exist
    //Or simply fetching it and returning it if it already has been created
    fun getFavoriteCount(position: Int, maxSize: Int): Int {
        try {
            return favoriteCount[position]
        }
        catch (e: Exception) {
            for(i in 0 until maxSize) { //exclusive
                var randInt = Random.nextInt(0, 30)
                favoriteCount.add(position, randInt)
            }
        }
        finally {
            return favoriteCount[position]
        }
    }

    //This function interacts with the artistService to get the list of all artists
    //The artist list includes a artist name, as well as a nature image
    //The nature image is later displayed in the core functionality of the app
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
                Log.i("jhoupps", "ERROR: SOME SORT OF CONNECTION ERROR")
            }
        })
    }
}
