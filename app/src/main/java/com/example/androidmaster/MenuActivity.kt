package com.example.androidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.androidmaster.imc.IMCActivity
import com.example.androidmaster.todo.TodoActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var btnSaludApp:Button
    private lateinit var btnIMCApp:Button
    private lateinit var btnTodo:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        initComponent()
        initListeners()
    }
    private fun initComponent(){
        this.btnSaludApp = findViewById(R.id.btnSaludApp)
        this.btnIMCApp = findViewById(R.id.btnIMCApp)
        this.btnTodo = findViewById(R.id.btnTodo)
    }

    private fun initListeners(){
        btnSaludApp.setOnClickListener { navigateTo("SALUD_APP")}
        btnIMCApp.setOnClickListener { navigateTo("IMC_APP") }
        btnTodo.setOnClickListener { navigateTo("TODO_APP") }
    }

    private fun navigateTo(destination: String) {
        val intent = Intent(this, getClass(destination))
        startActivity(intent)

    }

    private fun getClass(destination: String): Class<*>? {
        return when(destination){
            "SALUD_APP" -> MainActivity::class.java
            "IMC_APP" -> IMCActivity::class.java
            "TODO_APP" -> TodoActivity::class.java
            else -> MainActivity::class.java
        }
    }

}