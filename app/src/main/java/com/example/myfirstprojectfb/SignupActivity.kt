package com.example.myfirstprojectfb

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private val channelId="SigNup_Notification"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        val emailEditTextSignup=findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.edit_Text_email_signup)
        val EditTextPassword=findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.edit_Text_password_signup)
        val btn_signup=findViewById<AppCompatButton>(R.id.btn_signup)
        val textView_loginPage=findViewById<TextView>(R.id.textViewClickLoginPage)

        val auth=FirebaseAuth.getInstance()
        btn_signup.setOnClickListener {
            val email=emailEditTextSignup.text.toString()
            val pass=EditTextPassword.text.toString()

            auth.createUserWithEmailAndPassword(email,pass)
                .addOnSuccessListener {
                    Toast.makeText(this, "Signup Success", Toast.LENGTH_SHORT).show()

                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                        val notification=NotificationChannel(channelId,"Notification",NotificationManager.IMPORTANCE_HIGH)
                        notification.description="I'm Student"

                        val manager=getSystemService(NotificationManager::class.java)
                        manager.createNotificationChannel(notification)
                    }
                    val notificationBuild=NotificationCompat.Builder(this,channelId)
                    notificationBuild.setPriority(NotificationCompat.PRIORITY_HIGH)
                    notificationBuild.setSmallIcon(R.drawable.ic_launcher_background)
                    notificationBuild.setContentTitle("Hello Android Authentication")
                    notificationBuild.setContentText("This is a Account Create SuccessFully")

                    val manager=getSystemService(NotificationManager::class.java)
                    manager.notify(2,notificationBuild.build())

                    startActivity(Intent(this,MainActivity::class.java))

                }
                .addOnFailureListener {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
        }


        textView_loginPage.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}