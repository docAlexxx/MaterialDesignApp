package com.example.materialdesignapp.view.recycler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesignapp.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecyclerBinding
    lateinit var adapter: RecyclerActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = arrayListOf(
            Pair(false, Data("", type = TYPE_HEADER)),
            Pair(false, Data("Earth", type = TYPE_EARTH)),
            Pair(false, Data("Earth", type = TYPE_EARTH)),
            Pair(false, Data("Mars", "", type = TYPE_MARS)),
            Pair(false, Data("Earth", type = TYPE_EARTH)),
            Pair(false, Data("Earth", type = TYPE_EARTH)),
            Pair(false, Data("Earth", type = TYPE_EARTH)),
            Pair(false, Data("Mars", "", type = TYPE_MARS))
        )


        adapter = RecyclerActivityAdapter({
            Toast.makeText(this, it.someText, Toast.LENGTH_SHORT).show()
        }, data)

        binding.recyclerView.adapter = adapter

        binding.recyclerActivityFAB.setOnClickListener {
            adapter.addItem()
            binding.recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
        }
    }
}