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
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ndroid.dessert_shop.data.Gateau
import com.ndroid.dessert_shop.db.DessertShopDataBase
import java.io.ByteArrayOutputStream

class AddGateauActivity : AppCompatActivity() {

    lateinit var btnSave : Button
    lateinit var editTitle : EditText
    lateinit var editDescription: EditText
    lateinit var imageGateau : ImageView
    lateinit var editPrice : EditText

    lateinit var db: DessertShopDataBase
    var bitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_gateau)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = DessertShopDataBase(this)

        btnSave = findViewById<Button>(R.id.btn_gateaux_add)
        editTitle = findViewById<EditText>(R.id.editTItle)
        editDescription = findViewById<EditText>(R.id.editDescription)
        imageGateau = findViewById<ImageView>(R.id.imageAddGateau)
        editPrice = findViewById<EditText>(R.id.editPrice)

        val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){ data ->
            val inputStream = contentResolver.openInputStream(data!!)
            bitmap = BitmapFactory.decodeStream(inputStream)
            imageGateau.setImageBitmap(bitmap)
        }

        imageGateau.setOnClickListener{
            // ouvrir gallery
            galleryLauncher.launch("image/*")
        }


        btnSave.setOnClickListener {
           // récupérer les différents valeurs ...
            val title = editTitle.text.toString()
            val description = editDescription.text.toString()
            val price = editPrice.text.toString()

            if (bitmap == null) {
                Toast.makeText(this, "Veuillez sélectionner une image", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(title.isEmpty() || description.isEmpty() || price.isEmpty()){
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val imagesBlob : ByteArray = getBytes(bitmap!!)


            val gateau = Gateau(title,description,imagesBlob,price,"")

            editTitle.setText("")
            editDescription.setText("")
            editPrice.setText("")
            bitmap = null


            val isAdded = db.addGateau(gateau)

            if (isAdded) {
                Toast.makeText(this, "Gâteau ajouté avec succès", Toast.LENGTH_SHORT).show()
                Intent(this, HomeActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            } else {
                Toast.makeText(this, "Erreur lors de l'ajout du gâteau", Toast.LENGTH_SHORT).show()
            }
        }


        val btn_back = findViewById<Button>(R.id.btn_gateaux_add_back)

        btn_back.setOnClickListener {
            Intent(this,HomeActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }

    } // fin onCreate


    fun getBytes(bitmap: Bitmap): ByteArray{
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream)
        return  stream.toByteArray()
    }



}