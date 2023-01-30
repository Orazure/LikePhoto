package com.example.likphoto

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.likphoto.API.RetrofitViewModel
import com.example.likphoto.API.UserViewModelFactory
import com.example.likphoto.databinding.ActivityMainBinding
import com.example.likphoto.databinding.FavViewBinding
import com.example.likphoto.models.Pictures

class FavActivity : AppCompatActivity()  {

    private lateinit var binding: FavViewBinding
    private lateinit var ApiViewModel: RetrofitViewModel
    private lateinit var adapter: AdapterFavPictures
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ApiViewModel = ViewModelProvider(this, UserViewModelFactory())[RetrofitViewModel::class.java]
        adapter = AdapterFavPictures(listOf(),ApiViewModel)


    }

    // on start and use api with retrofit and get likes pictures from api
    override fun onStart() {
        super.onStart()
        // get data from api
        // bind data to image view
        binding.recyclerViewFav.adapter = adapter
        ApiViewModel.data.observe(this) {
            adapter.setData(it)
            // list of pictures to load in image viewz
        }
        ApiViewModel.getLikedPictures()





    }



}