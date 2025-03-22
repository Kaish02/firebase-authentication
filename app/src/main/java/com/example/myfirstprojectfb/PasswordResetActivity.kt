package com.example.myfirstprojectfb

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class PasswordResetActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_password_reset)

        val auth=FirebaseAuth.getInstance()
        val emailBy_password_reset=findViewById<TextInputEditText>(R.id.edit_Text_email_password_reset)
        val click_Password_reset=findViewById<AppCompatButton>(R.id.btn_password_reset)
        val click_login_page=findViewById<TextView>(R.id.textViewClickLoginPage)
        click_Password_reset.setOnClickListener {
            auth.sendPasswordResetEmail(emailBy_password_reset.text.toString())
            startActivity(Intent(this,SignupActivity::class.java))
        }
        click_login_page.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}