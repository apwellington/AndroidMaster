package com.example.androidmaster.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.androidmaster.R

class IMCResultActivity : AppCompatActivity() {

    private lateinit var tvImcResult:TextView
    private lateinit var tvRresult:TextView
    private lateinit var tvDescription:TextView
    private lateinit var btnRecalcular:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcresult)
        val result: Double = intent.extras?.getDouble(IMCActivity.IMC_KEY) ?: -1.0
        initComponent()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        this.btnRecalcular.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUI(result: Double) {
        tvImcResult.text = result.toString()
        when(result){
            in 0.00..18.50 -> {
                tvRresult.text = getString(R.string.bajo_peso_title)
                tvDescription.text = getString(R.string.bajo_peso_decription)
                tvRresult.setTextColor(getColor(R.color.peso_bajo))
            }

            in 18.51..24.99 -> {
                tvRresult.text = getString(R.string.normal_peso_title)
                tvDescription.text = getString(R.string.normal_peso_decription)
                tvRresult.setTextColor(getColor(R.color.peso_normal))
            }

            in 25.00..29.99 -> {
                tvRresult.text = getString(R.string.sobrepeso_title)
                tvDescription.text = getString(R.string.sobrepeso_decription)
                tvRresult.setTextColor(getColor(R.color.sobrepeso))
            }

            in 30.00..99.99 -> {
                tvRresult.text =getString(R.string.obesidad_title)
                tvDescription.text =getString(R.string.obesidad_decription)
                tvRresult.setTextColor(getColor(R.color.obeso))
            }
            else -> {
                tvImcResult.text = getString(R.string.error)
                tvRresult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponent() {
        this.tvImcResult = findViewById(R.id.tvImcResult)
        this.tvRresult = findViewById(R.id.tvRresult)
        this.tvDescription = findViewById(R.id.tvDescription)
        this.btnRecalcular = findViewById(R.id.btnReCalcular)
    }


}