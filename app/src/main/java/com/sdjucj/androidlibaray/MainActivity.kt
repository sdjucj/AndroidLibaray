package com.sdjucj.androidlibaray

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btTestRecyclerViewAdapter).setOnClickListener {
            startActivity(Intent(this@MainActivity, RecyclerViewAdapterTestActivity::class.java))
        }
    }

}