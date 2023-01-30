package com.example.likphoto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.likphoto.API.RetrofitViewModel
import com.example.likphoto.API.UserViewModelFactory
import com.example.likphoto.databinding.PicturesLayoutBinding

class DetailsPictures : AppCompatActivity() {

    private lateinit var binding: PicturesLayoutBinding
    private lateinit var viewModel: RetrofitViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= PicturesLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this, UserViewModelFactory())[RetrofitViewModel::class.java]
        val url = intent.getStringExtra("url")
        val id = intent.getStringExtra("id")
        val likes= intent.getStringExtra("number_of_likes")
        binding.textView1.text = url
        binding.textView2.text = id
        binding.nbLike.text = likes
        // back to main activity
        binding.buttonReturn.setOnClickListener {
            finish()
        }
        // send to the second activity when the button is clicked for like the picture
        binding.likeBtn.setOnClickListener {
            if (id != null) {
                viewModel.likePicture(id)
            }
        }

        // send to the second activity when the button is clicked for unlike the picture
        binding.unlikeBtn.setOnClickListener {
            if (id != null) {
                viewModel.unlikePicture(id)
            }
        }
    }
}