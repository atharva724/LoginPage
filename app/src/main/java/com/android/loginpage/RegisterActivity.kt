package com.android.loginpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.android.loginpage.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var name: EditText
    private lateinit var btnRegister: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Find views by their IDs
        emailEditText = findViewById(R.id.et_email)
        passwordEditText = findViewById(R.id.et_password)
        name=findViewById(R.id.et_fullname)
        btnRegister= findViewById(R.id.btn_register)

       btnRegister.setOnClickListener {
          createAccount(emailEditText.text.toString(), passwordEditText.text.toString())
        }
//       tvHaveAccount.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }
    }


    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this,"Success",Toast.LENGTH_LONG).show()
                    val user = auth.currentUser
                    updateUI(user)
                    startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.

                    updateUI(null)
                }
            }

    }

    fun updateUI(user: FirebaseUser?) {

    }


}