package com.jhoupps.httpjsonparser

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

//The one and only activity of the app
//This activity interacts with all the various parts of the UI and interfaces with the API manager
//and the RetroHttP app
class MainActivity : AppCompatActivity() {
    private lateinit var apiManager: APIManager
    private val TAG = "jhoupps" //I can filter logs by this!
    var allImageURLs = listOf<String>("f")
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiManager = (application as RetroHTTPApp).apiManager

        btnFetchHTTPs.setOnClickListener {
            fetchArtistWithRetroFit()
        }

        btnAdvance.setOnClickListener{
            advanceImage()
        }

        btnPrev.setOnClickListener{
            prevImage()
        }

        //Todo - I acknowledge that hardcoding max size is in poor style
        //I would fix this, but I ran out of time
        //If I were to bring this app to production, this is the next change I would make
        btnUpvote.setOnClickListener{
            tvFavorites.text = apiManager.getFavoriteCount(position, 47).toString() +" upvotes"
            apiManager.favoriteCount[position] += 1
            tvFavorites.text = apiManager.getFavoriteCount(position, 47).toString() +" upvotes"
        }
    }

    //Fetching the Json and calling the Glide tool
    private fun fetchArtistWithRetroFit() {
        //use the apimanager to get all the artists from the httpApp
        apiManager.getListOfArtist ({ allArtists ->
            val listOfArtists = allArtists.artists //this is already parsed!
            var tempList = mutableListOf<String>()

            listOfArtists.forEach {
                Log.i(TAG, it.toString())
                tempList.add(it.largeImageURL)
            }

            allImageURLs = tempList.toList()
            advanceImage()
        },
                {
                    Toast.makeText(this, "Error: Check your internet connection and try again ", Toast.LENGTH_SHORT).show()
                })
    }

    //When the button is pushed, the image advances
    //This is also called when the images are first loaded
    private fun advanceImage() {
        val totalLen = allImageURLs.size
        val imageView = findViewById<View>(R.id.imMyImageView) as ImageView

        if (position < totalLen - 1) {
            position += 1
        } else {
            position = 0
        }

        Glide.with(this).load(allImageURLs[position]).into(imageView);

        imMyImageView.visibility = View.VISIBLE
        btnPrev.visibility = View.VISIBLE
        btnAdvance.visibility = View.VISIBLE
        tvProgress.visibility = View.VISIBLE
        btnFetchHTTPs.visibility = View.GONE
        tvFavorites.visibility = View.VISIBLE
        btnUpvote.visibility = View.VISIBLE

        tvProgress.text = "Image $position out of $totalLen"
        tvFavorites.text = apiManager.getFavoriteCount(position, 47).toString() +" upvotes"

    }

    //When the button is pushed, the image goes back one
    private fun prevImage() {
        val totalLen = allImageURLs.size
        val imageView = findViewById<View>(R.id.imMyImageView) as ImageView

        if (position != 0) {
            position -= 1
        } else {
            position = totalLen - 1
        }

        Glide.with(this).load(allImageURLs[position]).into(imageView);
        tvProgress.text = "Image $position out of $totalLen"
        tvFavorites.text = apiManager.getFavoriteCount(position, 47).toString() +" upvotes"
    }
}

