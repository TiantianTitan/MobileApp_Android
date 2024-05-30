package com.ndroid.dessert_shop

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val connect = findViewById<Button>(R.id.connect)
        val email = findViewById<EditText>(R.id.mail)
        val password = findViewById<EditText>(R.id.password)
        val error = findViewById<TextView>(R.id.error_password)


        connect.setOnClickListener{
            val textEmail = email.text.toString()
            val textPassword = password.text.toString()
            if(textEmail.trim().isEmpty() ||textPassword.trim().isEmpty() ) {
                error.text = "Vous devez remplir tout les champs!"
                error.visibility = View.VISIBLE
            }
            else {
                val correctEmail = "admin"
                val correctPassword = "root1234"
                if(correctEmail == textEmail && correctPassword == textPassword){
                    Toast.makeText(this,"connect successful",Toast.LENGTH_LONG).show()
                }else{
                    error.text = "Email ou mot de passe est incorrect!"
                    error.visibility = View.VISIBLE
                }
            }
        }



    }
}