package com.example.ryden.libations

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        id_loginButton.setOnClickListener {

            val email = id_email.text.toString().trim()
            val password = id_password.text.toString().trim()


            mAuth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        val i = Intent(this, UserHomeActivity::class.java)
                        startActivity(i)
                    } else {
                        Toast.makeText(this, "Incorrect email/password entered", Toast.LENGTH_LONG).show()
                    }
                }
        }

        id_signUp.setOnClickListener {
            val email = id_email.text.toString().trim()
            val password = id_password.text.toString().trim()

            if(password.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters long",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            mAuth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this){
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Account created!!",
                            Toast.LENGTH_LONG).show()
                    } else {
                        val m = it.exception.toString()
                        Toast.makeText(this, "Account creation Failed!\n$m",
                            Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}
