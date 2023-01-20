package com.example.likphoto


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.likphoto.API.RetrofitViewModel
import com.example.likphoto.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ApiViewModel: RetrofitViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    // on start and use api with retrofit and get data from api
    override fun onStart() {
        super.onStart()
        // get data from api
        ApiViewModel = ViewModelProvider(this)[RetrofitViewModel::class.java]
        ApiViewModel.getdata()
        Log.i("MainActivity", "onStart: ${ApiViewModel.getdata()}")
    }
}