package com.example.flixtertopratedaramirez

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val FLIX_EXTRA = "FLIX_EXTRA"
private const val TAG = "tRatedAdapter"

class tRatedAdapter (private val context: Context, private val tRated: List<TopRated>) :
    RecyclerView.Adapter<tRatedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.top_rated_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topR = tRated[position]
        holder.bind(topR)
    }

    override fun getItemCount() = tRated.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val mediaImageView = itemView.findViewById<ImageView>(R.id.tRImage)
        private val titleTextView = itemView.findViewById<TextView>(R.id.tRName)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(rated: TopRated) {
            titleTextView.text = rated.title

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + rated.posterPath)
                .into(mediaImageView)
        }

        override fun onClick(v: View?) {
            val rated = tRated[absoluteAdapterPosition]

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(FLIX_EXTRA, rated)
            context.startActivity(intent)
        }
    }
}