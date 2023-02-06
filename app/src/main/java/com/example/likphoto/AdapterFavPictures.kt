package com.example.likphoto

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.likphoto.API.RetrofitViewModel
import com.example.likphoto.databinding.FavButtonImageViewBinding
import com.example.likphoto.models.Pictures


class AdapterFavPictures(private var userList: List<Pictures>, private var ApiViewModel: RetrofitViewModel):RecyclerView.Adapter<AdapterFavPictures.MyViewHolder>() {





    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fav_button_image_view, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var data= userList.get(position)
        holder.bind(data)
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


        private val binding : FavButtonImageViewBinding = FavButtonImageViewBinding.bind(itemView)

        @SuppressLint("NotifyDataSetChanged")
        fun bind(pictures: Pictures) {

            binding.imageViewFav.load(pictures.urls.regular)
            // change appareance of fav button if picture is liked

            // if picture is liked
            println("bind${pictures.likedByUser}")
            if (pictures.likedByUser){
                binding.likeHeart.isSelected = true
            }

            binding.likeHeart.setOnClickListener{
                // if is selected change to unselected
                var id = pictures.id
                if (binding.likeHeart.isSelected){
                    binding.likeHeart.isSelected = false
                    // remove id from liked pictures list in view model
                    var listNew=userList.filter { it.id != id }
                    userList= listOf()
                    notifyDataSetChanged()
                    userList=listNew
                    notifyDataSetChanged()
                    ApiViewModel.unlikePicture(id)
                }
            }
            binding.imageViewFav.setOnClickListener{
                val intent= Intent(itemView.context,DetailsPictures::class.java)
                intent.putExtra("url",pictures.urls.regular)
                intent.putExtra("desc",pictures.altDescription)
                // if i like the picture i will send the id of the picture to details activity
                intent.putExtra("id",pictures.id)
                intent.putExtra("is_liked",pictures.likedByUser)
                intent.putExtra("number_of_likes",pictures.likes.toString())
                itemView.context.startActivity(intent)
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(user: List<Pictures>?) {
        userList = user!!
        notifyDataSetChanged()
    }
}
