package com.example.likphoto


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.likphoto.API.RetrofitViewModel
import com.example.likphoto.API.UserViewModelFactory
import com.example.likphoto.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ApiViewModel: RetrofitViewModel
    private lateinit var adapter: AdapterPictures
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ApiViewModel = ViewModelProvider(this, UserViewModelFactory())[RetrofitViewModel::class.java]
        adapter = AdapterPictures(listOf())

    }

    // on start and use api with retrofit and get data from api
    override fun onStart() {
        super.onStart()
        // get data from api
        // bind data to image view
        binding.recyclerView.adapter = adapter
        ApiViewModel.data.observe(this) {
            adapter.setData(it)
            // list of pictures to load in image viewz
        }
        ApiViewModel.getdata()

    }
}