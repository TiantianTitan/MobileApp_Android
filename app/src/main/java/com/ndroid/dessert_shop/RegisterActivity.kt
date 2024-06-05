package com.ndroid.dessert_shop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ndroid.dessert_shop.data.User
import com.ndroid.dessert_shop.db.DessertShopDataBase

class RegisterActivity : AppCompatActivity() {

    lateinit var db : DessertShopDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnBack = findViewById<Button>(R.id.register_back)
        btnBack.setOnClickListener {
            Intent(this,MainActivity::class.java).also {
                startActivity(it)
            }
        }

        db = DessertShopDataBase(this)

        val editName = findViewById<EditText>(R.id.editName)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val editConfirmPassword = findViewById<EditText>(R.id.editConfirmPassword)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val tvError = findViewById<TextView>(R.id.tvError)
        val registre_back = findViewById<Button>(R.id.register_back)

        btnSave.setOnClickListener {
            tvError.visibility = View.INVISIBLE
            tvError.text = ""
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val confirmPassword = editConfirmPassword.text.toString()

            // Check if null
            if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                tvError.text = getString(R.string.error_empty_fields)
                tvError.visibility = View.VISIBLE
            }else{
                if(password != confirmPassword) {
                    tvError.text = getString(R.string.password_different)
                    tvError.visibility = View.VISIBLE
                }

                val  user = User(0,name,email,password)
                val isInserted = db.addUser(user)
                if(isInserted){
                    Toast.makeText(this,getString(R.string.success_register),Toast.LENGTH_SHORT).show()
                    Intent(this,HomeActivity::class.java).also{
                        it.putExtra("email",email)
                        startActivity(it)
                    }
                    finish()
                }

            }

        }




    }

}