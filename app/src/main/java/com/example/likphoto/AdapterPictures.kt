package com.example.likphoto

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.example.likphoto.databinding.ImageViewBinding


import com.example.likphoto.models.Pictures

class AdapterPictures(private var userList : List<Pictures>):RecyclerView.Adapter<AdapterPictures.MyViewHolder>() {


    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_view_, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var data= userList.get(position)
        holder.bind(data)
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {




        private val binding : ImageViewBinding = ImageViewBinding.bind(itemView)



        fun bind(pictures: Pictures) {

            binding.imageView2.load(pictures.urls.regular)
            binding.imageView2.setOnClickListener{
                val intent= Intent(itemView.context,DetailsPictures::class.java)
                intent.putExtra("url",pictures.urls.regular)
                intent.putExtra("id",pictures.id)
                intent.putExtra("number_of_likes",pictures.likes.toString())
                itemView.context.startActivity(intent)
            }
        }


        // put image raw in image view from unsplash



    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(user: List<Pictures>?) {
        userList = user!!
        notifyDataSetChanged()
    }



}









