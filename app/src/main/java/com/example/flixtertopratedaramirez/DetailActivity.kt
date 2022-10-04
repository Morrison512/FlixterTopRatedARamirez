package com.example.flixtertopratedaramirez

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"
class DetailActivity : AppCompatActivity() {
        private lateinit var mediaImageView: ImageView
        private lateinit var titleTextView: TextView
        private lateinit var knownForTextView: TextView
        private lateinit var detailTextView: TextView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_detail)

            // TODO: Find the views for the screen
            mediaImageView = findViewById(R.id.backDropImage)
            titleTextView = findViewById(R.id.nameTV)
            knownForTextView = findViewById(R.id.dateTv)
            detailTextView = findViewById(R.id.descTV)

            // TODO: Get the extra from the Intent
            val rated = intent.getSerializableExtra(TRATED_EXTRA) as TopRated

            // TODO: Set the title, byline, and abstract information from the article
            titleTextView.text = rated.title
            knownForTextView.text = "Realeased on: ${rated.rDate}"
            detailTextView.text = rated.description

            // TODO: Load the media image
            Glide.with(mediaImageView)
                .load("https://image.tmdb.org/t/p/w500/" + rated.backDrop)
                .into(mediaImageView)
        }
    }