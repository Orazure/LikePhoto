package com.example.likphoto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
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
    }
}
