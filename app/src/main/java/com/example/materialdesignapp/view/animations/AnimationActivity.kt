package com.example.materialdesignapp.view.animations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.ActivityAnimationsBinding

class AnimationActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnimationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationViewActivity.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_transition -> {

                    true
                }
                R.id.bottom_slide -> {
                    true
                }
                R.id.bottom_explode -> {

                    true
                }
                else -> true
            }
        }

        binding.bottomNavigationViewActivity.selectedItemId = R.id.bottom_transition

    }

    fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.containerActivity, fragment).commit()
    }
}