package com.example.androidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtMain = findViewById<EditText>(R.id.edtMain)
        val bnMain = findViewById<Button>(R.id.btnMain)

        bnMain.setOnClickListener{
            val value = edtMain.text.toString()
            if(value.isNotEmpty()) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("ARGS", value)
                startActivity(intent)
            }
        }
    }
}