package com.example.trysingup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Contents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents)
        var mAuth: FirebaseAuth
        var firebaseUser:FirebaseUser
        mAuth = FirebaseAuth.getInstance();
        firebaseUser=mAuth.currentUser;
        val showEmail=findViewById<TextView>(R.id.showEmail);
        val buttonSignout=findViewById<Button>(R.id.btnSignout)
        showEmail.setText(firebaseUser.email)

        buttonSignout.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this,MainActivity::class.java))
        }





    }
}