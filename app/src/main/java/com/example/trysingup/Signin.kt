package com.example.trysingup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        var mAuth: FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        val edittextEmail=findViewById<EditText>(R.id.edtEmailSignin)
        val edittextPassword=findViewById<EditText>(R.id.edtPasswordSignin)
        val buttonSignin=findViewById<Button>(R.id.btnSignin)
//        val result=findViewById<TextView>(R.id.loginResult)
        val textGoToSignup=findViewById<TextView>(R.id.txtSignin)
        buttonSignin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(edittextEmail.text.toString(), edittextPassword.text.toString())
                .addOnCompleteListener(this,
                    OnCompleteListener<AuthResult?> { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(this, "Login success.", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,Contents::class.java))
//                            val user = mAuth.currentUser
//                            result.setText("${user.email}")
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show()
//                            Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    })
        }
        textGoToSignup.setOnClickListener {
            startActivity(Intent(this,Signup::class.java))
        }
    }
}