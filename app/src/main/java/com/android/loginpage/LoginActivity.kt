package com.android.loginpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.loginpage.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import android.app.Activity
import android.content.pm.ActivityInfo
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.view.View


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvHaveAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // Initialize Firebase Auth
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        auth = Firebase.auth
        emailEditText = findViewById(R.id.et_email)
        passwordEditText = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        tvHaveAccount=findViewById(R.id.tv_havent_account)

        btnLogin.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            signIn(email, password)
        }
        tvHaveAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()}
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                    writeToFirebase(email, password)
                    Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Login failed! Please Check Your Password and Email ", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        // Implement UI updates based on user authentication status
    }

    private fun writeToFirebase(email: String, password: String) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val usersRef: DatabaseReference = database.getReference("users")

        val userRef = usersRef.child(email.replace(".", ","))
        userRef.child("email").setValue(email)
        userRef.child("password").setValue(password)
    }
}
