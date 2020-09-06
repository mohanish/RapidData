package com.rapidata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rapidata.ui.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.root_container, SearchFragment.newInstance())
            .commitAllowingStateLoss()
    }
}