package com.android.loginpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.android.loginpage.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val namebox=findViewById<TextInputLayout>(R.id.et_fullname)
        val emailbox=findViewById<TextInputLayout>(R.id.et_email)
        val passwordbox=findViewById<TextInputLayout>(R.id.et_fullname)


        binding.btnRegister.setOnClickListener {
            val namebox=findViewById<TextInputLayout>(R.id.et_fullname)
            val emailbox=findViewById<TextInputLayout>(R.id.et_email)
            val passwordbox=findViewById<TextInputLayout>(R.id.et_fullname)

            createAccount(emailbox.editText?.text.toString(),passwordbox.editText?.text.toString())
        }
        binding.tvHaveAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                    startActivity(Intent(this, RegisterActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.

                    updateUI(null)
                }
            }

    }
    fun updateUI(user: FirebaseUser?) {

    }

fun registerbuttonclick(){
    val namebox=findViewById<TextInputLayout>(R.id.et_fullname)
    val emailbox=findViewById<TextInputLayout>(R.id.et_email)
    val passwordbox=findViewById<TextInputLayout>(R.id.et_fullname)

    createAccount(emailbox.editText?.text.toString(),passwordbox.editText?.text.toString())

}


}