package com.lyra.androidmaster.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.lyra.androidmaster.R
import com.lyra.androidmaster.imccalculator.const.Const.Companion.IMC_KEY
import java.text.DecimalFormat

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvTitle:TextView
    private lateinit var tvIMC:TextView
    private lateinit var tvDescription:TextView
    private lateinit var btnRecalculate:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)

        val resultIMC = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        initComponents()
        initUI(resultIMC)
        initListeners()


    }


    private fun initComponents() {
        tvIMC = findViewById(R.id.tvIMC)
        tvTitle = findViewById(R.id.tvTitle)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btn_recalculate)
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { onBackPressed() }

    }

    private fun initUI(result: Double) {
        val df = DecimalFormat("#.##");

        tvIMC.text = getString(R.string.resultIMC, df.format(result))

        when(result) {
            in 0.00..18.50 -> {
                tvTitle.text = getString(R.string.result_title_underweight)
                tvTitle.setTextColor(ContextCompat.getColor(this,R.color.imc_underweight))
                tvDescription.text = getString(R.string.result_description_underweight)
            }
            in 18.50..24.90 -> {
                tvTitle.text = getString(R.string.result_title_normal_weight)
                tvTitle.setTextColor(ContextCompat.getColor(this,R.color.imc_normal_weight))
                tvDescription.text = getString(R.string.result_description_normal_weight)
            }
            in 25.00..29.00 -> {
                tvTitle.text = getString(R.string.result_title_overweight)
                tvTitle.setTextColor(ContextCompat.getColor(this,R.color.imc_overweight))
                tvDescription.text = getString(R.string.result_description_overweight)
            }
            in 30.00..34.90 -> {
                tvTitle.text = getString(R.string.result_title_obesity_grade_1)
                tvTitle.setTextColor(ContextCompat.getColor(this,R.color.imc_obesity_grade_1))
                tvDescription.text = getString(R.string.result_description_obesity_grade_1)
            }
            in 35.00..39.00 -> {
                tvTitle.text = getString(R.string.result_title_obesity_grade_2)
                tvTitle.setTextColor(ContextCompat.getColor(this,R.color.imc_obesity_grade_2))
                tvDescription.text = getString(R.string.result_description_obesity_grade_2)
            }
            else -> {
                when {
                    result > 40.00 -> {
                        tvTitle.text = getString(R.string.result_title_obesity_grade_3)
                        tvTitle.setTextColor(ContextCompat.getColor(this,R.color.imc_obesity_grade_3))
                        tvDescription.text = getString(R.string.result_description_obesity_grade_3)
                    }
                }
            }

        }


    }

}
