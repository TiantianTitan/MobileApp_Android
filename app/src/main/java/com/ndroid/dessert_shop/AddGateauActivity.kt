package com.ndroid.dessert_shop

import android.content.ClipDescription
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddGateauActivity : AppCompatActivity() {

    lateinit var btnSave : Button
    lateinit var editTitle : EditText
    lateinit var editDescription: EditText
    lateinit var imageGateau : ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_gateau)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnSave = findViewById<Button>(R.id.btn_gateaux_add)
        editTitle = findViewById<EditText>(R.id.editTItle)
        editDescription = findViewById<EditText>(R.id.editDescription)
        imageGateau = findViewById<ImageView>(R.id.imageAddGateau)

        imageGateau.setOnClickListener{
            // ouvrir gallery
            val intentImg = Intent(Intent.ACTION_GET_CONTENT)
            intentImg.type= "image/*"
            startActivityForResult(intentImg,100)
        }


        btnSave.setOnClickListener {
            // TODO: enregistrer dans la base de données la poste avec l'image
        }


        val btn_back = findViewById<Button>(R.id.btn_gateaux_add_back)

        btn_back.setOnClickListener {
            Intent(this,HomeActivity::class.java).also {
                startActivity(it)
            }
        }



    } // fin onCreate


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == RESULT_OK){
            val uri: Uri? = data?.data
            val inputStream = contentResolver.openInputStream(uri!!)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imageGateau.setImageBitmap(bitmap)
        }

    }



}