package com.example.ryden.libations

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_happy_hour_information.*

class HappyHourInformation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_happy_hour_information)

        var locName: String = intent.getStringExtra("locName")
        var times: String = intent.getStringExtra("times")
        var address: String = intent.getStringExtra("address")
        var description: String = intent.getStringExtra("description")

        val nameView = id_barName.findViewById<TextView>(R.id.id_barName)
        val timeView = id_barTimes.findViewById<TextView>(R.id.id_barTimes)
        val addressView = id_barAddress.findViewById<TextView>(R.id.id_barAddress)
        val descriptionView = id_barDescription.findViewById<TextView>(R.id.id_barDescription)

        nameView.text = locName
        timeView.text = times
        addressView.text = address
        descriptionView.text = description

        id_mapButton.setOnClickListener {
            val location = addressView.text.toString().trim()
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/search/?api=1&query=$location")
            )
            startActivity(intent)
        }

        id_goBack.setOnClickListener {
            finish()
        }
    }
}
