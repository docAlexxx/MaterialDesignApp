package com.example.materialdesignapp.view.bottomnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.ActivityApiBottomBinding
import com.example.materialdesignapp.view.settings.curTheme
import com.example.materialdesignapp.view.viewpager.EarthFragment
import com.example.materialdesignapp.view.viewpager.MarsFragment
import com.example.materialdesignapp.view.viewpager.SystemFragment
import com.google.android.material.badge.BadgeDrawable

class ApiBottomActivity : AppCompatActivity() {

    lateinit var binding: ActivityApiBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(curTheme)
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

        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_system)
        badge.number = 100
        badge.maxCharacterCount = 3
        badge.badgeGravity = BadgeDrawable.TOP_START

        binding.bottomNavigationView.selectedItemId = R.id.bottom_view_system

    }

    fun navigateTo(fragment: Fragment) {
      //  supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out
        ).replace(R.id.container, fragment).commit()
    }


}