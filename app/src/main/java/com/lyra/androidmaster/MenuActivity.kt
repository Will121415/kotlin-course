package com.lyra.androidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lyra.androidmaster.firstapp.FirstAppActivity
import com.lyra.androidmaster.imccalculator.ImcCalculatorActivity
import com.lyra.androidmaster.todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSalutedApp = findViewById<Button>(R.id.btnSalutedApp)
        val btnIMCalculate = findViewById<Button>(R.id.btnIMCalculate)
        val btnTODO = findViewById<Button>(R.id.btnTODO)

        btnIMCalculate.setOnClickListener { navigateToIMCalculate() }
        btnSalutedApp.setOnClickListener { navigateToSalutedApp() }

        btnTODO.setOnClickListener { navigateToTodoApp() }
    }

    private fun navigateToTodoApp() {
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToIMCalculate() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSalutedApp()
    {
         val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }
}