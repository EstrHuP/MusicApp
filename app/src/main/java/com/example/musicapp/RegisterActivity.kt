package com.example.musicapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.w3c.dom.Text

class RegisterActivity : AppCompatActivity() {

    private lateinit var txtName: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtPasswordR: EditText

    private lateinit var progressBar: ProgressBar

    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPasswordR = findViewById(R.id.txtPasswordR)

//        progressBar = findViewById(R.id.progressBar)

        instanciasFirebase()

        dbReference = database.reference.child("usuarios")
    }

    fun onClickNew(view: View){
        crearUsuario()
        //sendEmailVerification()
    }

    private fun crearUsuario(){
        val name:String = txtName.text.toString()
        val email:String = txtEmail.text.toString()
        val password:String = txtPasswordR.text.toString()

        //Comprobar campos vacios
        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){
            //Comprobar email y contraseña
           if (!email.contains("@") || password.length < 6){
                txtEmail.error = "Correo no valido"
                txtPasswordR.error = "Debe tener mas de 6 caracteres"
                //Si toodo esta correcto
            }else{
                //Crear usuario firebase
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this){
                            task ->
                        if (task.isComplete){
                            val usuario:FirebaseUser? = auth.currentUser
                            val usuarioDB = dbReference.child(usuario!!.uid) //Recogemos el uid del usuario
                            usuarioDB.child("Nombre").setValue(name) //Visualizar nombre en BD
                            usuarioDB.child("Correo").setValue(email) //Visualizar correo en BD
                        }
                    }
                Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_LONG).show()
               sendEmailVerification()
            }

        }else{
            Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show()
        }
        Log.d("RegisterActivity", "Nombre: $name")
        Log.d("RegisterActivity", "Correo: $email")
        Log.d("RegisterActivity", "Contraseña: $password")
    }

    private fun instanciasFirebase(){
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
    }

    private fun sendEmailVerification() {
        // Disable button
        btnNew.isEnabled = false
        // Send verification email
        // [START send_email_verification]
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this) { task ->
                // [START_EXCLUDE]
                // Re-enable button
                btnNew.isEnabled = true

                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Verification email sent to ${user.email} ", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("RegisterActivity", "sendEmailVerification", task.exception)
                    Toast.makeText(baseContext, "Failed to send verification email.", Toast.LENGTH_SHORT).show()
                }
                // [END_EXCLUDE]
            }
        // [END send_email_verification]
    }

    //private fun comprobarMail(){
    //    if (!email.contains("@")){
    //        txtEmail.error = "Correo no valido"
            //Toast.makeText(this, "Debe poner un correo válido", Toast.LENGTH_SHORT).show()
     //   }
    //}

    //private fun comprobarPass(){
    //    if (password.length < 6){
    //        txtPasswordR.error = "Debe tener mas de 6 caracteres"
    //        //Toast.makeText(this, "La contraseña debe tener más de 6 caracteres", Toast.LENGTH_SHORT).show()
    //    }
    //}

//    fun comprobarMail(email: CharSequence): Boolean {
//        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
//    }
}



//    fun onClickNewUser(view:View){
//        crearUsuario()
//    }

//    private fun crearUsuario(){
//        val name:String = txtName.text.toString()
//        val email:String = txtEmail.text.toString()
//        val password:String = txtPasswordR.text.toString()
//
//        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
//            progressBar.visibility = View.VISIBLE
//
//            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
//                    task ->
//                if (task.isComplete){
//                    val user:FirebaseUser? = auth.currentUser
//                    verificarEmail(user)
//
//                    val userBD=dbReference.child(user!!.uid) //Nos interesa recoger el id del usuario
//                    //Nos interesa guardar el nick y el email del usuario
//                    userBD.child("Nombre").setValue(name)
//                    userBD.child("Email").setValue(email)
//                    action()
//                }
//            }
//        }else{
//            Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT)
//        }
//    }

    //Cuando se crea un usuario este metodo se va a lanzar cuando este correctamente
    //Manda al usuario a la vista Login
//    private fun action(){
//        startActivity(Intent(this, LoginActivity::class.java))
//    }

//    private fun verificarEmail(user: FirebaseUser?){
//        user?.sendEmailVerification()
//            ?.addOnCompleteListener(this){
//                task ->
//
//                if(task.isComplete){
//                    Toast.makeText(this, "Email enviado", Toast.LENGTH_LONG)
//                }else{
//                    Toast.makeText(this, "Error al enviar email", Toast.LENGTH_LONG)
//                }
//            }
//    }

