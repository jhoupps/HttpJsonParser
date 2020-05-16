package com.jhoupps.httpjsonparser


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private lateinit var apiManager: APIManager
    private val TAG = "jhoupps" //I can filter logs by this!
    private lateinit var allImageURLs: List<String>
    private var position by Delegates.notNull<Int>()

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

        //use the apimanager to get all the artists from the httpapp
        apiManager.getListOfArtist ({ allArtists ->
            val listOfArtists = allArtists.artists //this is already parsed!
            var tempList = mutableListOf<String>()

            listOfArtists.forEach {
                Log.i(TAG, it.toString())
                tempList.add(it.largeImageURL)
            }

            allImageURLs = tempList.toList()

            //val imageView = findViewById<View>(R.id.testOneImage) as ImageView
            //Glide.with(this).load(allImageURLs[0]).into(imageView);
        },
                {
                    Toast.makeText(this, "Error, james wilson! ", Toast.LENGTH_SHORT).show()
                })

        Log.i(TAG, "fetch2test")


    }

    fun advanceImage(){
        val totalLen = allImageURLs.size
        val imageView = findViewById<View>(R.id.testOneImage) as ImageView

        if (position < totalLen) {
            position += 1
        } else {
            position = 0
        }

        Glide.with(this).load(allImageURLs[position]).into(imageView);

    }

}

