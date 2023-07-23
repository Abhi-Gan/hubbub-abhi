package com.example.app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException

class ChatActivity : AppCompatActivity() {

    private val url = "http://10.0.2.2:5000/top_reddit_posts?n=5" // Replace with your API endpoint URL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val backButton = findViewById<View>(R.id.backButtonChat) as Button
        backButton.setOnClickListener { finish() }
        fetchData()
    }

    private fun fetchData() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    client.newCall(request).execute()
                }

                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    // Process the response data here and update the UI
                    updateUI(responseData)
                } else {
                    // Handle unsuccessful response (e.g., show an error message)
                    updateUI("Error: ${response.code}")
                }
            } catch (e: IOException) {
                // Handle network-related exceptions (e.g., no internet connection)
                updateUI("Error: ${e.message}")
            }
        }
    }

    private fun updateUI(data: String?) {
        val textView = findViewById<TextView>(R.id.FetchTextView)
        textView.text = data ?: "No data received"
    }
}
