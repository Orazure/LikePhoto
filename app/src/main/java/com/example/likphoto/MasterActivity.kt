package com.example.likphoto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.likphoto.databinding.MasterViewXmlBinding

class MasterActivity : AppCompatActivity() {

    private lateinit var binding: MasterViewXmlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MasterViewXmlBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.buttonToListPhotos.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, FavActivity::class.java)
            startActivity(intent)
        }

        val isSelect=intent.getStringExtra("isSelect")
        if(isSelect=="true"){
            // R.id.favActivity set to true
            binding.bottomNavigatinView.selectedItemId=R.id.masterActivity
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



        // put listener on search_by_index button to search by index
        binding.searchByIndex.setOnClickListener {
            val index = binding.searchByIndex.text.toString()
            if (index.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("index", index)
                startActivity(intent)
            }
        }


    }

    private fun setContent(content: String) {
        setTitle(content)
    }
}
