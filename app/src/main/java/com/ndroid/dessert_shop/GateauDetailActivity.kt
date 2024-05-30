package com.ndroid.dessert_shop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GateauDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gateau_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvTitre = findViewById<TextView>(R.id.tvTitre)
        val imageView = findViewById<ImageView>(R.id.imageGateauDetail)
        val price_detail = findViewById<TextView>(R.id.prix_detail)
        val description_complet = findViewById<TextView>(R.id.Description_complet)

        val retour = findViewById<Button>(R.id.back)


        val titre = intent.getStringExtra("titre")
        val image = intent.getIntExtra("imageNumber",0)
        val price = intent.getStringExtra("prix")
        val descriptionComplet = intent.getStringExtra("description_complet")


        tvTitre.text = titre
        imageView.setImageResource(image)
        price_detail.text = price
        description_complet.text = descriptionComplet

        retour.setOnClickListener{
            val intentToHomeActivity = Intent(this,HomeActivity::class.java)
            //intentToHomeActivity.putExtra("email",textEmail)
            startActivity(intentToHomeActivity)
        }

    }
}