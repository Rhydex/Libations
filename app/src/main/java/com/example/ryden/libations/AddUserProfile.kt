package com.example.ryden.libations

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_user_profile.*
import org.w3c.dom.Text

class AddUserProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user_profile)

        var userName: String = intent.getStringExtra("userName")
        var userBio: String = intent.getStringExtra("userBio")

        var userNameView = id_userName.findViewById<TextView>(R.id.id_userName)
        var userBioView = id_bio.findViewById<TextView>(R.id.id_bio)

        userNameView.text = userName
        userBioView.text = userBio
        id_saveButton.setOnClickListener {
            val nUserName = id_userName.text.toString().trim()
            val nUserBio = id_bio.text.toString().trim()

            if( nUserName != "TempUserName"
                && nUserBio != "Temp User Bio")
            {
                if(nUserName != userName
                    || nUserBio != userBio)
                {
                    val userProfile = MyUserProfile(nUserName, nUserBio)
                    val data = Intent()
                    data.putExtra(USER_ADD, userProfile)
                    setResult(Activity.RESULT_OK, data)

                    finish()
                } else {
                    Toast.makeText(this, "Please change a field", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
        id_cancel_profile.setOnClickListener {
            finish()
        }
    }
}
