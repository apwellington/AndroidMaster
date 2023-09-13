package com.example.androidmaster.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.androidmaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class IMCActivity : AppCompatActivity() {

    private var isMaleSelected:Boolean = true
    private var isFemaleSelected:Boolean = false
    private var currentWigth = 60
    private var currentAge = 18
    private var currentHeight = 120



    private lateinit var cvMale:CardView
    private lateinit var cvFemale:CardView
    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:RangeSlider

    private lateinit var btnSubtractWight:FloatingActionButton
    private lateinit var btnAddWight:FloatingActionButton
    private lateinit var btnSubtractAge:FloatingActionButton
    private lateinit var btnAddAge:FloatingActionButton

    private lateinit var btnCalcular:Button

    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcactivity)
        initComponent()
        initListeners()
        intiUI()
    }


    private fun initComponent() {
        this.cvMale = findViewById(R.id.cvMale)
        this.cvFemale = findViewById(R.id.cvFemales)
        this.tvHeight = findViewById(R.id.tvHeight)
        this.rsHeight = findViewById(R.id.rsheight)
        this.btnAddWight = findViewById(R.id.btnAddWeigth)
        this.btnSubtractWight =findViewById(R.id.btnSubtractWeight)
        this.btnAddAge =findViewById(R.id.btnAddAge)
        this.btnSubtractAge = findViewById(R.id.btnSubtractAge)
        this.tvWeight = findViewById(R.id.tvWight)
        this.tvAge = findViewById(R.id.tvAge)
        this.btnCalcular = findViewById(R.id.btnCalcular)
    }


    private fun initListeners() {
        this.cvMale.setOnClickListener{
            changeGender()
            setGenderColor()
        }

        this.cvFemale.setOnClickListener{
            changeGender()
            setGenderColor()
        }

        this.rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }

        this.btnSubtractAge.setOnClickListener {
            currentAge-=1
            setAge()
        }

        this.btnAddAge.setOnClickListener {
            currentAge+=1
            setAge()
        }

        this.btnSubtractWight.setOnClickListener {
            currentWigth-=1
            setWigth()
        }

        this.btnAddWight.setOnClickListener {
            currentWigth+=1
            setWigth()
        }

        this.btnCalcular.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(value:Double){
        val intent = Intent(this, IMCResultActivity::class.java)
        intent.putExtra(IMC_KEY,value)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val heightMeter = currentHeight.toDouble() / 100
        val imc = currentWigth / (heightMeter * heightMeter)
        val df = DecimalFormat("#.##")
        return df.format(imc).toDouble()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWigth() {
        tvWeight.text = currentWigth.toString()
    }

    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor(){
        cvFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
        cvMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
    }

    private fun getBackgroundColor(isSelectedCompoenet:Boolean): Int{
        val colorRef = if(isSelectedCompoenet) {
            R.color.background_selected
        }else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorRef)
    }

    private fun intiUI() {
        setGenderColor()
        setWigth()
        setAge()
    }
}