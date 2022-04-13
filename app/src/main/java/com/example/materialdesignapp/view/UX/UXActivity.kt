package com.example.materialdesignapp.view.UX

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.ActivityUxBinding
import com.example.materialdesignapp.view.settings.curTheme

class UXActivity : AppCompatActivity() {
    lateinit var binding: ActivityUxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(curTheme)
        binding = ActivityUxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationViewUX.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.fragment_ux_text -> {
                    navigateTo(UXTextFragment())
                    true
                }
                R.id.fragment_ux_buttons -> {
                    navigateTo(UXButtonsFragment())
                    true
                }
                else -> true
            }
        }
        binding.bottomNavigationViewUX.selectedItemId = R.id.fragment_ux_text
    }

    fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.ux_container, fragment).commit()
    }

}