package com.example.trysingup

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList


class Signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        var mAuth: FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        val edittextEmail=findViewById<EditText>(R.id.edtEmail)
        val edittextPassword=findViewById<EditText>(R.id.edtPassword)
        val editTextConfirmPassword=findViewById<EditText>(R.id.edtConfirmPassword)
        val buttonSignup=findViewById<Button>(R.id.btnSignup)
        val textResult=findViewById<TextView>(R.id.txtResult)
        buttonSignup.setOnClickListener {

           if ((edittextEmail.text.toString()).isEmpty() || (edittextPassword.text.toString()).isEmpty() || (editTextConfirmPassword.text.toString()).isEmpty()){
            textResult.setText("Please fill ")
           } else if((edittextPassword.text.toString()) !=(editTextConfirmPassword.text.toString())){
               textResult.setText("Password not equal ")

           }else{
               mAuth.createUserWithEmailAndPassword(edittextEmail.text.toString(), edittextPassword.text.toString())
                   .addOnCompleteListener(this,
                       OnCompleteListener<AuthResult?> { task ->
                           if (task.isSuccessful) {
                               // Sign in success, update UI with the signed-in user's information
//                            Toast.makeText(this, "Authentication success.", Toast.LENGTH_SHORT).show()
//                               edittextEmail.setText("")
//                               edittextPassword.setText("")
//                               editTextConfirmPassword.setText("")
//                            val user = mAuth.currentUser
//                            Toast.makeText(this, "$user", Toast.LENGTH_SHORT).show()

                               mAuth.signInWithEmailAndPassword(edittextEmail.text.toString(), edittextPassword.text.toString())
                                   .addOnCompleteListener(this,
                                       OnCompleteListener<AuthResult?> { task ->
                                           if (task.isSuccessful) {
                                               // Sign in success, update UI with the signed-in user's information
                                               Toast.makeText(this, "Login success.", Toast.LENGTH_SHORT).show()
                                               writeDataOnFirestore(UserItem(edittextEmail.text.toString()));
                                               startActivity(Intent(this,Contents::class.java))
//                            val user = mAuth.currentUser
//                            result.setText("${user.email}")
                                           }
                                       })
                           } else {
                               // If sign in fails, display a message to the user.
                               Toast.makeText(this, "Authentication failed. ${edittextEmail.text.toString()}", Toast.LENGTH_SHORT).show()
//                            Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                           }
                       })
           }


        }
    }
//    data class UserInfo(
//        var UserList: ArrayList<UserItem> = arrayListOf()
//    )
    data class UserItem(
        var email: String = "",
        var xtext: String = "Welcome",

    )
    val mFireStore = FirebaseFirestore.getInstance()
    private fun writeDataOnFirestore(UserItem: UserItem){
        val user = HashMap<String, Any>()
        user["email"] = UserItem.email.toString()
        user["xtext"] = UserItem.xtext.toString()
        mFireStore.collection("UserWithSignup").document("${UserItem.email.toString()}")
            .set(user)
            .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e) }
    }


}
//Toast.makeText(this,"success",Toast.LENGTH_SHORT)