package com.example.musicapp.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.EditText
import com.example.musicapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var view: View
    private lateinit var txtPassword: EditText
    private lateinit var txtEmail: EditText

    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Quitar el toolbar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)
        //Pantalla Completa
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)

        auth = FirebaseAuth.getInstance()

        //comprobarCampos()
    }


//    private fun comprobarCampos(){
//        val email:String = txtEmail.toString()
//        val password:String = txtPassword.toString()
//
//        if (!TextUtils.isEmpty(email) && (!TextUtils.isEmpty(password))){
//            auth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this){
//                    task ->
//                    if(task.isSuccessful){
//                        onClickLogin(view)
//                    }else{
//                        Toast.makeText(this, "Error. Usuario no identificado", Toast.LENGTH_SHORT).show()
//                    }
//            }
//        } else {
//            //Toast.makeText(this, "Rellena todos los campos.", Toast.LENGTH_SHORT)
//        }
//    }

    fun onClickRegister(view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun onClickLogin(view: View){
        startActivity(Intent(this, MainActivity::class.java))
    }
}
