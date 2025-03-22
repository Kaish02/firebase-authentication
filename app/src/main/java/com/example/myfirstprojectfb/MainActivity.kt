package com.example.myfirstprojectfb

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.myfirstprojectfb.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binnding: ActivityMainBinding
    private val channelId="Notification"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binnding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binnding.root)
        val email = binnding.editTextEmail
        val password = binnding.editTextPassword
        val login_btn = binnding.btnLogin
        val reg_btn = binnding.BtnReg
        val reg_forget_password = binnding.textViewForgetPassword


        val auth = FirebaseAuth.getInstance()
        login_btn.setOnClickListener {
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnSuccessListener {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

                    //notification ke liye
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                        val notification=NotificationChannel(channelId,"Notification",NotificationManager.IMPORTANCE_HIGH)
                        notification.description="I'm Student "

                        val notificationManager=getSystemService(NotificationManager::class.java)
                        notificationManager.createNotificationChannel(notification)
                    }
                    val notificationBuild=NotificationCompat.Builder(this,channelId)
                    notificationBuild.setSmallIcon(R.drawable.ic_launcher_background)
                    notificationBuild.setContentTitle("Hello Android Authentication")
                    notificationBuild.setContentText("This is a Login SuccessFully")
                    notificationBuild.setPriority(NotificationCompat.PRIORITY_HIGH)

                    val manager=getSystemService(NotificationManager::class.java)
                    manager.notify(1,notificationBuild.build())

                    startActivity(Intent(this,ProfileActivity::class.java))

                }
                .addOnFailureListener {
                    Toast.makeText(this, "Login Fail ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }


        reg_btn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
        reg_forget_password.setOnClickListener {
            startActivity(Intent(this, PasswordResetActivity::class.java))
            finish()
        }


    }
}