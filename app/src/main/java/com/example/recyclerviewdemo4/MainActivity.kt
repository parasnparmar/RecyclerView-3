package com.example.recyclerviewdemo4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var  recyclerView: RecyclerView
    private val posts = ArrayList<Post>()
    private val advertisments = ArrayList<Advertisment>()
    private lateinit var postAdapter: AdapterImplementation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initViews()
        initAdapter()
    }
    private inner class MyOnPostClickListner : AdapterImplementation.OnPostClickListner{
        override fun OnClickPost(position: Int, post: Post) {
            if(isLoggedIn()){
                val intent = Intent(this@MainActivity,PostDetailsActivity::class.java)
                intent.putExtra("post",post)
                startActivity(intent)
                Toast.makeText(this@MainActivity,"Post :${post.title}",Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this@MainActivity,LogIn::class.java)
                startActivity(intent)
            }
        }
    }
    private inner class MyOnAdvertisementListner : AdapterImplementation.OnAdvertisementClickListner{
        override fun OnClickAdvertisement(position: Int, advertisment: Advertisment) {
            super.OnClickAdvertisement(position, advertisment)
            if(isLoggedIn()){
                val intent = Intent(this@MainActivity,AdvertismentDetails::class.java)
                intent.putExtra("advertisement",advertisment)
                startActivity(intent)
                Toast.makeText(this@MainActivity,"Advertisement: ${advertisment.title}",Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this@MainActivity,LogIn::class.java)
                startActivity(intent)
            }
        }
    }
    private fun isLoggedIn() : Boolean{
        return Random.nextBoolean()
    }
    fun initViews(){
        recyclerView = findViewById(R.id.recyclerViewPostAndAdvertisement)
        recyclerView.layoutManager = LinearLayoutManager(
            this,LinearLayoutManager.VERTICAL,false
        )

        
    }
    fun initAdapter(){
        postAdapter = AdapterImplementation(posts,advertisments)
        recyclerView.adapter = postAdapter

        postAdapter.OnClickPostListners = MyOnPostClickListner()
        postAdapter.onAdvertismentClickListner = MyOnAdvertisementListner()
    }
    fun initData(){

        for(i in 1..10){
            posts.add(Post("post $i","postTitle $i",R.drawable.ic_launcher_background))
            advertisments.add(Advertisment("Ad Titel $i","advertisementurl.com$i"))
        }
    }
}