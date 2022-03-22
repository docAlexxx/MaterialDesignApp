package com.example.materialdesignapp.view.bottomnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.ActivityApiBottomBinding
import com.example.materialdesignapp.view.viewpager.EarthFragment
import com.example.materialdesignapp.view.viewpager.MarsFragment
import com.example.materialdesignapp.view.viewpager.SystemFragment

class ApiBottomActivity : AppCompatActivity() {

    lateinit var binding: ActivityApiBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_earth -> {
                    navigateTo(EarthFragment())
                    true
                }
                R.id.bottom_view_mars -> {
                    navigateTo(MarsFragment())
                    true
                }
                R.id.bottom_view_system -> {
                    navigateTo(SystemFragment())
                    true
                }
                else -> true
            }
        }

    }

    fun navigateTo(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }

}