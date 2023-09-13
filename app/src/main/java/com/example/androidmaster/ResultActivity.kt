package com.example.androidmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val args: String = intent.extras?.getString("ARGS").orEmpty()

        if (args.isNotEmpty()){
            tvResult.text = args
        }

    }
}