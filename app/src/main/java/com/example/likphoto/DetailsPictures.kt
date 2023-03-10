package com.example.likphoto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.likphoto.API.RetrofitApi
import com.example.likphoto.API.RetrofitViewModel
import com.example.likphoto.API.UserViewModelFactory
import com.example.likphoto.bdd.DAO.PictureDao
import com.example.likphoto.bdd.DatabasePicture
import com.example.likphoto.bdd.Model.PictureRepo
import com.example.likphoto.databinding.PicturesLayoutBinding

class DetailsPictures : AppCompatActivity() {

    private lateinit var binding: PicturesLayoutBinding
    private lateinit var viewModel: RetrofitViewModel
    private lateinit var dao: PictureDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= PicturesLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val service=RetrofitApi.getService()
        val databasePicture = DatabasePicture.getInstance(this)
        dao = databasePicture.getPictureDao()
        val repo = PictureRepo(service,dao)
        viewModel = ViewModelProvider(this, UserViewModelFactory(repo))[RetrofitViewModel::class.java]
        val desc = intent.getStringExtra("desc")
        val id = intent.getStringExtra("id")
        val likes= intent.getStringExtra("number_of_likes")
        println(desc +"DESC")
        binding.textDesc.text = desc
        binding.nbLike.text = likes
        binding.textView2.text=id
        // back to main activity
        binding.buttonReturn.setOnClickListener {
            finish()
        }
        // send to the second activity when the button is clicked for like the picture
        binding.likeBtn.setOnClickListener {
            if (id != null) {
                viewModel.likePicture(id)
                // update the number of likes
                binding.nbLike.text = (likes?.toInt()?.plus(1)).toString()
            }
        }

        // send to the second activity when the button is clicked for unlike the picture
        binding.unlikeBtn.setOnClickListener {
            if (id != null) {
                viewModel.unlikePicture(id)
                // update the number of likes
                binding.nbLike.text = (likes?.toInt()?.minus(1)).toString()
            }
        }
    }
}