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
    private var allImageURLs = listOf<String>()
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiManager = (application as RetroHTTPApp).apiManager

        btnFetchHTTPs.setOnClickListener {
            fetchArtistWithRetroFit()

            Log.i(TAG, "buttontest")
        }
        //TODO- MAKE THIS HIDDEN UNTIL FETCH CLICKED
        btnAdvance.setOnClickListener{
            advanceImage()
        }

        //TODO- MAKE THIS HIDDEN UNTIL FETCH CLICKED
        btnPrev.setOnClickListener{
            prevImage()
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
            advanceImage()

            //val imageView = findViewById<View>(R.id.testOneImage) as ImageView
            //Glide.with(this).load(allImageURLs[0]).into(imageView);
        },
                {
                    Toast.makeText(this, "Error, james wilson! ", Toast.LENGTH_SHORT).show()
                })

        Log.i(TAG, "fetch2test")


    }

    private fun advanceImage() {
        val totalLen = allImageURLs.size
        val imageView = findViewById<View>(R.id.testOneImage) as ImageView

        if (position < totalLen - 1) {
            position += 1
        } else {
            position = 0
        }

        Glide.with(this).load(allImageURLs[position]).into(imageView);

        testOneImage.visibility = View.VISIBLE
        btnPrev.visibility = View.VISIBLE
        btnAdvance.visibility = View.VISIBLE
        tvProgress.visibility = View.VISIBLE
        btnFetchHTTPs.visibility = View.GONE

        //tvProgress.text = "Image $position out of $totalLen"
        tvProgress.text = "Image"

    }

    private fun prevImage() {
        val totalLen = allImageURLs.size
        val imageView = findViewById<View>(R.id.testOneImage) as ImageView

        if (position != 0) {
            position -= 1
        } else {
            position = totalLen - 1
        }

        Glide.with(this).load(allImageURLs[position]).into(imageView);
        tvProgress.text = "Image $position out of $totalLen"

    }

}

