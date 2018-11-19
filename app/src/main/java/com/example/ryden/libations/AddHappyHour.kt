package com.example.ryden.libations

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_happy_hour.*

class AddHappyHour : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_happy_hour)

        id_save_happy.setOnClickListener {
            val locName = id_loc_name.text.toString().trim()
            val times = id_times.text.toString().trim()
            val description = id_description.text.toString().trim()
            val address = id_address.text.toString().trim()

            val happyHour = MyHappyHour(locName, times, address, description)
            val data = Intent()
            data.putExtra(HAPPY_ADD, happyHour)
            setResult(Activity.RESULT_OK, data)

            finish()
        }
    }
}
