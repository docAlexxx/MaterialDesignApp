package com.example.materialdesignapp.view.recycler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesignapp.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = arrayListOf(
            Data("",type = TYPE_HEADER),
            Data("Earth",type = TYPE_EARTH),
            Data("Earth",type = TYPE_EARTH),
            Data("Mars", "",type = TYPE_MARS),
            Data("Earth",type = TYPE_EARTH),
            Data("Earth",type = TYPE_EARTH),
            Data("Earth",type = TYPE_EARTH),
            Data("Mars", "",type = TYPE_MARS)
        )

        binding.recyclerView.adapter =RecyclerActivityAdapter({
            Toast.makeText(this,it.someText,Toast.LENGTH_SHORT).show()
        },data)
    }
}