package com.lyra.androidmaster.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.lyra.androidmaster.R

class ShowMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_message)


        val tvShowMessage = findViewById<TextView>(R.id.tvShowMessage)
        val name:String = intent.extras?.getString("EXTRA_NAME").orEmpty()

        tvShowMessage.text = "Hola $name"
    }
}