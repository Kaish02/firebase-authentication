package com.example.myfirstprojectfb

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ProfileActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val auth = FirebaseAuth.getInstance()
        val btn_logOutAuth = findViewById<AppCompatButton>(R.id.btn_logOut)
        val btn_update_password = findViewById<AppCompatButton>(R.id.btn_update_password)
        val edit_text_update_password = findViewById<TextInputEditText>(R.id.edit_Text_password_update)
        val edit_text_update_email = findViewById<TextInputEditText>(R.id.edit_Text_email_update)
        val btn_update_email = findViewById<AppCompatButton>(R.id.btn_update_email)
        val btn_account_delete = findViewById<AppCompatButton>(R.id.btn_account_deleted)

        btn_logOutAuth.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

        btn_update_password.setOnClickListener {
            val user = auth.currentUser
            user?.updatePassword(edit_text_update_password.text.toString())
                ?.addOnSuccessListener {
                    Toast.makeText(this, "Update Password Success", Toast.LENGTH_SHORT).show()
                }
        }


        btn_update_email.setOnClickListener {
            val user =FirebaseAuth.getInstance().currentUser
            val editTextEmail = edit_text_update_email.text.toString()
                user?.updateEmail(editTextEmail)
                ?.addOnSuccessListener {
                    Toast.makeText(this, "Update Email Success", Toast.LENGTH_SHORT).show()
                }
                ?.addOnFailureListener {
                    Toast.makeText(this, "Not Updated: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }

        btn_account_delete.setOnClickListener {
            val user=auth.currentUser
            user?.delete()?.addOnSuccessListener {
                Toast.makeText(this, "Account Delete SuccessFully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,SignupActivity::class.java))
                finish()
            }
        }
    }
}