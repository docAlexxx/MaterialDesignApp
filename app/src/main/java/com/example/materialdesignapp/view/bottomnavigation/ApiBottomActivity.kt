package com.example.materialdesignapp.view.bottomnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesignapp.databinding.ActivityApiBottomBinding

class ApiBottomActivity: AppCompatActivity()  {

    lateinit var binding: ActivityApiBottomBinding

  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}