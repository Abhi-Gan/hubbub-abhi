package com.example.app



import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import java.io.IOException

class CommunityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.community)
        val backButton = findViewById<View>(R.id.backButton) as Button
        backButton.setOnClickListener { finish() }

        val postRecyclerView = findViewById<RecyclerView>(R.id.postRecyclerView);
        val posts = createDummyPosts()
//
//        // Set up the RecyclerView with the adapter
        val postAdapter = PostAdapter(posts,this)
//
        postRecyclerView.layoutManager = (LinearLayoutManager(this));
        postRecyclerView.adapter = postAdapter

    }


    fun createDummyPosts(): List<Post> {
        val dummyProfiles = mutableListOf<Post>()

        // Add some dummy profiles to the list
        dummyProfiles.add(Post("All Surveys Go HERE!", "https://www.reddit.com/r/wheelchairs/comments/14ebyvr/all_surveys_go_here/"))
        dummyProfiles.add(Post("Tips to make my wheelchair less painful to use?", "https://i.redd.it/6n0viss0gmdb1.jpg"))
        dummyProfiles.add(Post("Literally me when I first got my chair!", "https://v.redd.it/hv8osf07okdb1"))
        dummyProfiles.add(Post("MASSIVE long shot, but anyone know this wheelchair?", "https://www.reddit.com/gallery/15726iz"))
        dummyProfiles.add(Post("Finding a job", "https://www.reddit.com/r/wheelchairs/comments/156yi0c/finding_a_job/"))

        // Add more profiles as needed
        // follow https://www.techiedelight.com/send-http-get-post-requests-kotlin/
        // or https://square.github.io/okhttp/
//        Log.d("fetch","a")
//        // val responseVal = run("http://127.0.0.1:5000/top_reddit_posts?n=5")
//        Log.d("fetch","b")
        // print(responseVal!!.string())
        return dummyProfiles
    }


    private val client = OkHttpClient()

    private fun run(url_str: String): ResponseBody? {
        val request = Request.Builder()
            .url(url_str)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

//            for ((name, value) in response.headers) {
//                println("$name: $value")
//            }

            return response.body
            // println(response.body!!.string())
        }
    }
}