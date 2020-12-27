package com.johanespino.firebaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PrincipalActivity : AppCompatActivity() {

    lateinit var textViewUserEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val value = intent.getStringExtra("email")

        textViewUserEmail = findViewById(R.id.tv_user_email)
        textViewUserEmail.text = value
    }
}