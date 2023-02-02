package com.example.likphoto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.likphoto.API.RetrofitApi
import com.example.likphoto.API.RetrofitViewModel
import com.example.likphoto.API.UserViewModelFactory
import com.example.likphoto.bdd.DAO.PictureDao
import com.example.likphoto.bdd.DatabasePicture
import com.example.likphoto.bdd.Model.PictureRepo
import com.example.likphoto.databinding.FavViewBinding

class FavActivity : AppCompatActivity()  {

    private lateinit var binding: FavViewBinding
    private lateinit var ApiViewModel: RetrofitViewModel
    private lateinit var adapter: AdapterFavPictures

    private lateinit var dao: PictureDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val service=RetrofitApi.getService()
        val databasePicture = DatabasePicture.getInstance(this)
        dao = databasePicture.getPictureDao()
        val repo = PictureRepo(service,dao)
        ApiViewModel = ViewModelProvider(this, UserViewModelFactory(repo))[RetrofitViewModel::class.java]
        adapter = AdapterFavPictures(listOf(),ApiViewModel)

        val isSelect=intent.getStringExtra("isSelect")
        if(isSelect=="true"){
            // R.id.favActivity set to true
            binding.bottomNavigatinView.selectedItemId=R.id.favActivity
        }
        binding.bottomNavigatinView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mainActivity -> {
                    setContent("Home")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.masterActivity -> {
                    setContent("Notification")
                    val intent = Intent(this, MasterActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.favActivity -> {
                    setContent("Activity")
                    val intent = Intent(this, FavActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

    }

    private fun setContent(content: String) {
        setTitle(content)
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