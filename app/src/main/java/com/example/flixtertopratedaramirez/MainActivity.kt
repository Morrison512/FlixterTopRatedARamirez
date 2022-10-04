package com.example.flixtertopratedaramirez

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

private const val TOP_RATED = "https://api.themoviedb.org/3/movie/top_rated?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US&page=1"
private const val TAG = "MainActivity/"

class MainActivity : AppCompatActivity() {
    lateinit var tRated : MutableList<TopRated>
    lateinit var tRatedRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tRated = mutableListOf()
        tRatedRecyclerView = findViewById(R.id.tRatedRV)
        tRatedRecyclerView.layoutManager = GridLayoutManager(this , 2)

        val client = AsyncHttpClient()
        client.get(TOP_RATED, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch top rated: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched top rated: $json")
                try {
                    val gson = Gson()

                    val resultsJSON: String = json.jsonObject.get("results").toString()
                    val arrayTRatedType = object : TypeToken<List<TopRated>>() {}.type
                    tRated = gson.fromJson(resultsJSON, arrayTRatedType)

                    tRatedRecyclerView.adapter =
                        TRatedAdapter(this@MainActivity, tRated)

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })
    }
}