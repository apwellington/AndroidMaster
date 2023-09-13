package com.example.androidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.androidmaster.imc.IMCActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSaludApp = findViewById<Button>(R.id.btnSaludApp)
        val btnIMCApp = findViewById<Button>(R.id.btnIMCApp)

        btnSaludApp.setOnClickListener {navigateTo("SALUD_APP")}

        btnIMCApp.setOnClickListener { navigateTo("IMC_APP") }


    }

    private fun navigateTo(destination: String) {
        val intent = Intent(this, getClass(destination))
        startActivity(intent)

    }

    private fun getClass(destination: String): Class<*>? {
        return when(destination){
            "SALUD_APP" -> MainActivity::class.java
            "IMC_APP" -> IMCActivity::class.java
            else -> MainActivity::class.java
        }
    }

}