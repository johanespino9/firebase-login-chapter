package com.johanespino.firebaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

  lateinit  var editTextEmail: EditText
  lateinit var editTextPassword: EditText
  lateinit  var buttonLogin: Button
  private lateinit var auth: FirebaseAuth

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    auth = Firebase.auth
    editTextEmail =  findViewById(R.id.et_email)
    editTextPassword = findViewById(R.id.et_password)
    buttonLogin = findViewById(R.id.btn_login)

    buttonLogin.setOnClickListener {
      val email = editTextEmail.text.toString()
      val password = editTextPassword.text.toString()

      if (email.isNotEmpty() && password.isNotEmpty()) {
        login()
      } else {
        Toast.makeText(this, "Complete los campos del usuario", Toast.LENGTH_LONG).show()
      }
    }

  }

  fun login(){
    auth.signInWithEmailAndPassword(editTextEmail.text.toString(),
      editTextPassword.text.toString()).addOnCompleteListener(this) { task ->
      if (task.isSuccessful) {
        // Sign in success, update UI wit h the signed-in user's information
        Log.d("TAG", "signInWithEmail:success")
        Toast.makeText(this, "Usuario: ${editTextEmail.text} ha iniciado sesion correctamente", Toast.LENGTH_LONG).show()
        redirectToUserProfile()
      } else {
        // If sign in fails, display a message to the user.
        Log.w("TAG", "signInWithEmail:failure", task.exception)
        Toast.makeText(baseContext, "Authentication failed.",
          Toast.LENGTH_SHORT).show()
        // ...
      }

      // ...
    }
  }

  fun redirectToUserProfile() {
    val intent = Intent(this, PrincipalActivity::class.java).apply {
      putExtra("email", editTextEmail.text.toString())
    }
    startActivity(intent)
  }



}