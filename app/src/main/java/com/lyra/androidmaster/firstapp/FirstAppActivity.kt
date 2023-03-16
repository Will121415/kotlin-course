package com.lyra.androidmaster.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.lyra.androidmaster.R

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)

        val btnGo = findViewById<AppCompatButton>(R.id.btnGo)
        val etName = findViewById<AppCompatEditText>(R.id.etName)


        btnGo.setOnClickListener {
            val name = etName.text.toString()

            if (name.isNotEmpty())
            {
                val intent = Intent(this, ShowMessageActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                startActivity(intent);
            }

        }
    }
}