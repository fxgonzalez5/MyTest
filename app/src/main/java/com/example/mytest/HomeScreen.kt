package com.example.mytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        cambiarPantalla()
    }

    fun cambiarPantalla(){
        val boton = findViewById<Button>(R.id.btn7)
        val navegar: Intent = Intent(this, PhotosScreen::class.java)

        boton.setOnClickListener(){
            startActivity(navegar)
        }

        val boton2 = findViewById<Button>(R.id.btn11)
        val navegar2: Intent = Intent(this, MainActivity::class.java)

        boton2.setOnClickListener(){
            startActivity(navegar2)
        }
    }
}