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
import com.example.likphoto.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ApiViewModel: RetrofitViewModel
    private lateinit var adapter: AdapterPictures
    private lateinit var dao: PictureDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val service = RetrofitApi.getService()
        val databasePicture = DatabasePicture.getInstance(this)
        dao = databasePicture.getPictureDao()
        val repo = PictureRepo(service,dao)
        ApiViewModel = ViewModelProvider(this, UserViewModelFactory(repo))[RetrofitViewModel::class.java]
        adapter = AdapterPictures(listOf())


        val isSelect=intent.getStringExtra("isSelect")
        if(isSelect=="true"){
            // R.id.favActivity set to true
            binding.bottomNavigatinView.selectedItemId=R.id.mainActivity
        }
        binding.bottomNavigatinView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mainActivity -> {
                    setContent("Home")
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("isSelect", "true")
                    startActivity(intent)
                    true
                }
                R.id.masterActivity -> {
                    setContent("Notification")
                    val intent = Intent(this, MasterActivity::class.java)
                    intent.putExtra("isSelect", "true")
                    startActivity(intent)
                    true
                }
                R.id.favActivity -> {
                    setContent("Activity")
                    val intent = Intent(this, FavActivity::class.java)
                    // send isSelect to FavActivity
                    intent.putExtra("isSelect", "true")
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

    // on start and use api with retrofit and get data from api
    override fun onStart() {
        super.onStart()
        // get data from api
        // bind data to image view
        // get intent index , if is false get data from api ,
        val index=intent.getStringExtra("index")
        println(index)
        if(index == null) {
            println("null index")
            binding.recyclerView.adapter = adapter
            ApiViewModel.data.observe(this) {
                adapter.setData(it)
            }
            ApiViewModel.getdata()
        }else{
            println("index is not null")
            binding.recyclerView.adapter = adapter
            ApiViewModel.data.observe(this) {
                adapter.setData(it)
            }
            ApiViewModel.searchPhotoAct(index,1)
        }

    }
}