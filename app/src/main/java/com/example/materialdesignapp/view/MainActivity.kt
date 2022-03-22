package com.example.materialdesignapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesignapp.R
import com.example.materialdesignapp.view.main.MainFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(curTheme)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance()).commit()
        }
    }
}