package com.example.trysingup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonToSignup=findViewById<Button>(R.id.btnSingupLink)
        val buttonToSignin=findViewById<Button>(R.id.btnSinginLink)

        buttonToSignup.setOnClickListener {
            val intent=Intent(this,Signup::class.java)
            startActivity(intent)
        }
        buttonToSignin.setOnClickListener {
            val intent=Intent(this,Signin::class.java)
            startActivity(intent)
        }
    }
}