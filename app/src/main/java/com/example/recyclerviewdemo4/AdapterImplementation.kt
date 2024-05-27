package com.example.recyclerviewdemo4

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecyclerListener
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterImplementation(private var Posts : ArrayList<Post>,
                            private var Ads : ArrayList<Advertisment>) : RecyclerView.Adapter<ViewHolder>(){
                                interface OnPostClickListner {
                                    fun OnClickPost(position: Int, post: Post) {

                                    }
                                }
    var OnClickPostListners: OnPostClickListner?=null
    val TYPE_VIEW_POST = 1
    val TYPE_VIEW_ADVT = 2



    inner class PostViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        var txtUsername : TextView
        var imagePost : ImageView
        var txtPostTitle : TextView

        init{
            txtUsername = view.findViewById(R.id.txtUsername)
            txtPostTitle = view.findViewById(R.id.txtPostTitle)
            imagePost = view.findViewById(R.id.imgPost)

            imagePost.setOnClickListener{
                if(OnClickPostListners !=null){
                    OnClickPostListners!!.OnClickPost(
                        adapterPosition/2,
                        Posts[adapterPosition/2])
                }
            }
        }

    }
    interface OnAdvertisementClickListner{
        fun OnClickAdvertisement(position: Int, advertisment: Advertisment){
        }
    }
    var onAdvertismentClickListner: OnAdvertisementClickListner?=null

    var advertisment: Advertisment? =null
    inner class AdvertisementViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        var txtAd : TextView
        init{
            txtAd = view.findViewById(R.id.txtAd);

            txtAd.setOnClickListener {
                if(onAdvertismentClickListner !=null){
                    onAdvertismentClickListner!!.OnClickAdvertisement(
                        adapterPosition/2,
                        Ads[adapterPosition/2]
                    )
                }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == TYPE_VIEW_POST) {
            var layoutInflater = LayoutInflater.from(parent.context)
            var postView = layoutInflater.inflate(R.layout.post_view, null)
            return PostViewHolder(postView)
        } else {
            var layoutInflater = LayoutInflater.from(parent.context)
            var advertismentView = layoutInflater.inflate(R.layout.advertisement_view, null)
            return AdvertisementViewHolder(advertismentView)
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is PostViewHolder){
            val post = Posts[position/2]

            holder.txtUsername.text = post.username
            holder.txtPostTitle.text = post.title
            holder.imagePost.setImageResource(post.imageId)
        }
        if(holder  is AdvertisementViewHolder){
            val adv = Ads[position/2]
            holder.txtAd.text = adv.title
        }

    }

    override fun getItemCount(): Int {
       return Posts.size + Ads.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(position%2==0){
            TYPE_VIEW_POST
        }else{
            TYPE_VIEW_ADVT
        }
    }


}