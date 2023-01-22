package com.yuvesh.chatapplication10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User

class SignUp : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etpassword: EditText
    lateinit var btnSignUp: Button
    lateinit var mAuth:FirebaseAuth
    lateinit var mDbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth= FirebaseAuth.getInstance()
        etName = findViewById(R.id.etname)
        etEmail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)
        btnSignUp = findViewById(R.id.btnSignUp)



        supportActionBar?.hide()
        btnSignUp.setOnClickListener {
            val name = etName.text.toString()
            val email=etEmail.text.toString()
            val password=etpassword.text.toString()

             signUp(name,email,password)
        }
    }

    private fun signUp(name:String,email:String,password:String)
    {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                val intent= Intent(this@SignUp,MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@SignUp, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addUserToDatabase(name:String,email: String,uid:String)
    {
       mDbRef=FirebaseDatabase.getInstance().getReference()
       mDbRef.child("user").child(uid).setValue(user(name, email, uid))
    }

}