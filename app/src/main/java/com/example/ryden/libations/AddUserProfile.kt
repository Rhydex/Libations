package com.example.ryden.libations

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_user_profile.*

class AddUserProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user_profile)

        id_saveButton.setOnClickListener {
            val userName = id_userName.text.toString().trim()
            val userBio = id_bio.text.toString().trim()

            if( userName != ""
                && userBio != "") {
                val userProfile = MyUserProfile(userName, userBio)
                val data = Intent()
                data.putExtra(USER_ADD, userProfile)
                setResult(Activity.RESULT_OK, data)

                finish()
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
        id_cancel_profile.setOnClickListener {
            finish()
        }
    }
}
