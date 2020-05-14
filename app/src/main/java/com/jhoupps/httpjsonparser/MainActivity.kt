package com.jhoupps.httpjsonparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import android.widget.Toast
//import com.ericchee.jsonfetcher.model.Email
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var apiManager: APIManager
    private val TAG = "jhoupps" //I can filter logs by this!
    private lateinit var allImageURLs: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiManager = (application as RetroHTTPApp).apiManager

        btnFetchHTTPs.setOnClickListener {
            fetchArtistWithRetroFit()

            Log.i(TAG, "buttontest")
        }
    }

    //The fetching mechanics
    private fun fetchArtistWithRetroFit() {
        Log.i(TAG, "fetch1test")

        //i think it's not entering this loop
        apiManager.getListOfArtist ({ allArtists ->
            val listOfArtists = allArtists.artists //this is already parsed!
            var tempList = mutableListOf<String>()

            listOfArtists.forEach {
                Log.i(TAG, it.toString())
                tempList.add(it.largeImageURL)
            }

            allImageURLs = tempList.toList()
        },
                {
                    Toast.makeText(this, "Error, james wilson! ", Toast.LENGTH_SHORT).show()
                })

        Log.i(TAG, "fetch2test")
    }

}

