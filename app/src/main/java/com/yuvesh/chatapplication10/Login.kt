package com.yuvesh.chatapplication10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.toSpanned
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    lateinit var etEmail: EditText
    lateinit var etpassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnSignUp: Button


    lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth=FirebaseAuth.getInstance()
        etEmail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignUp)


        supportActionBar?.hide()

        btnSignUp.setOnClickListener {
            val intent = Intent(this@Login, SignUp::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email=etEmail.text.toString()
            val password=etpassword.text.toString()

            login(email,password)
        }


    }

    private fun login(email:String,password:String)
    {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
               val intent=Intent(this@Login,MainActivity::class.java)
                finish()
                startActivity(intent)
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }
}