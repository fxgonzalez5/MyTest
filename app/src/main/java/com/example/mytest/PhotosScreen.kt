package com.example.mytest

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class PhotosScreen : AppCompatActivity() {
    private val PERMISO_CAMARA: Int = 99
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if (result.resultCode != Activity.RESULT_OK){
            showMessage("No se tomo la foto")
        }
        else {
            val bitmap = result.data?.extras!!.get("data") as Bitmap
            findViewById<ImageView>(R.id.imagePrev).setImageBitmap(bitmap)
            savePhoto(bitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos_screen)

        openCamera()
        cambiarPantalla()
    }

    fun openCamera(){
        val boton1 = findViewById<Button>(R.id.btn12)

        boton1.setOnClickListener(){
            when{
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED -> {
                    takePhoto()
                }
                shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA) -> {
                    showMessage("El permiso fue rechazado previamente, habilitar en los ajustes")
                }
                else -> {
                    requestPermissions(arrayOf(android.Manifest.permission.CAMERA), PERMISO_CAMARA)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISO_CAMARA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    takePhoto()
                }
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startForResult.launch(intent)
    }

    fun showMessage(mensaje: String){
        Toast.makeText(applicationContext, mensaje, Toast.LENGTH_LONG).show()
    }

    fun savePhoto(bitmap: Bitmap){
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "Foto")
            put(MediaStore.Images.Media.DESCRIPTION, "Descripción_de_la_imagen")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }

        val contentResolver = applicationContext.contentResolver
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        try {
            val outputStream = contentResolver.openOutputStream(uri!!)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream!!)
            outputStream?.close()
            Toast.makeText(applicationContext, "Imagen guardada exitosamente", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Error al guardar la imagen", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    fun cambiarPantalla(){
        val boton2 = findViewById<Button>(R.id.btn13)
        val navegar2: Intent = Intent(this, HomeScreen::class.java)

        boton2.setOnClickListener(){
            startActivity(navegar2)
        }
    }
}