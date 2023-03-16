package com.lyra.androidmaster.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.lyra.androidmaster.R
import com.lyra.androidmaster.imccalculator.const.Const.Companion.IMC_KEY
import java.text.DecimalFormat
import kotlin.math.pow

class ImcCalculatorActivity : AppCompatActivity() {


    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeight:TextView
    private lateinit var tvWeight:TextView
    private lateinit var tvAge:TextView
    private lateinit var btnRemoveWeight: FloatingActionButton
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var btnRemoveAge: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var rsHeight:RangeSlider
    private lateinit var btnCalculator: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calulator)

        initComponent()
        initListener()

    }

    private fun initListener() {

        viewMale.setOnClickListener {
            setGenderColor(viewMale,viewFemale)
        }
        viewFemale.setOnClickListener {
            setGenderColor(viewFemale,viewMale)
        }

        rsHeight.addOnChangeListener { _, value, _ ->
            val height = DecimalFormat("#.##").format(value);
            tvHeight.text = "$height cm"
        }

        btnRemoveWeight.setOnClickListener { changeWeight(-1) }

        btnAddWeight.setOnClickListener { changeWeight(1) }

        btnRemoveAge.setOnClickListener { changeAge(-1) }

        btnAddAge.setOnClickListener { changeAge(1) }

        btnCalculator.setOnClickListener {
            navigateToResult(calculateIMC())
        }


    }

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnRemoveWeight = findViewById(R.id.btnRemoveWeight)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnRemoveAge = findViewById(R.id.btnRemoveAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculator =findViewById(R.id.btnCalculator)
    }

    private fun setGenderColor(isSelected: CardView, isNotSelected: CardView) {

        isSelected.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_component_selected))
        isNotSelected.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_component))
    }

    private fun changeValue(value: Int, tv: TextView, label: String) {
        var newValue:Int = Integer.parseInt(tv.text.toString().substringBefore(" "));
        newValue += value

        tv.text = "$newValue $label"
    }

    private fun changeWeight(value: Int) {
        changeValue(value,tvWeight,"kg");
    }

    private fun changeAge(value: Int) {
        changeValue(value, tvAge, "")
    }

    private fun calculateIMC(): Double {
        var weight: Int = Integer.parseInt(tvWeight.text.toString().substringBefore(" "));
        val height: Double = tvHeight.text.toString().substringBefore(" ").toDouble() / 100

        return weight / (height.pow(2.0))
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }


}