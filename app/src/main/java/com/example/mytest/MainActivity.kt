package com.example.mytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Base_Theme_MyTest)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cambiarPantalla()
    }

    fun cambiarPantalla(){
        val boton1 = findViewById<Button>(R.id.btn2)
        val navegar: Intent = Intent(this, RegisterScreen::class.java)

        boton1.setOnClickListener(){
            startActivity(navegar)
        }

        val boton2 = findViewById<Button>(R.id.btn1)
        val navegar2: Intent = Intent(this, HomeScreen::class.java)

        boton2.setOnClickListener(){
            startActivity(navegar2)
        }
    }
}