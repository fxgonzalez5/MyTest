package com.example.mytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegisterScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)
        cambiarPantalla()
    }

    fun cambiarPantalla(){
        val boton1 = findViewById<Button>(R.id.btn5)
        val navegar: Intent = Intent(this, MainActivity::class.java)

        boton1.setOnClickListener(){
            startActivity(navegar)
        }

        val boton2 = findViewById<Button>(R.id.btn4)
        val navegar2: Intent = Intent(this, HomeScreen::class.java)

        boton2.setOnClickListener(){
            startActivity(navegar2)
        }
    }
}