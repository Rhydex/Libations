package com.example.ryden.libations

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_user_home.*
import kotlinx.android.synthetic.main.app_bar_user_home.*

const val REQ_CODE_ADD = 1
const val USER_ADD = "user_add"
const val HAPPY_ADD = "happy_add"

class UserHomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var mAuth: FirebaseAuth? = null
    var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)
        setSupportActionBar(toolbar)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val currentUser = mAuth?.currentUser

        if (currentUser != null) {
            val headerView = nav_view.getHeaderView(0)
            val emailView = headerView.findViewById<TextView>(R.id.id_nav_email)
            emailView.text = currentUser.email
        }

        fab.setOnClickListener {
            val i = Intent(this, AddHappyHour::class.java)
            startActivityForResult(i, REQ_CODE_ADD)

        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null) return

        val userEmail = mAuth?.currentUser?.email
        if (userEmail == null) return


        val userProfile = data.getParcelableExtra<MyUserProfile>(USER_ADD)
        val happyHour = data.getParcelableExtra<MyHappyHour>(HAPPY_ADD)
        val docRef = db?.collection(userEmail)?.document()

        if(userProfile != null) {
            docRef?.set(userProfile)
                ?.addOnSuccessListener {
                    userProfile.id = docRef.id
                    Toast.makeText(
                        this, "Added!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                ?.addOnFailureListener {
                    Toast.makeText(
                        this, "Add Failed!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
        if(happyHour != null) {
            docRef?.set(happyHour)
                ?.addOnSuccessListener {
                    happyHour.id = docRef.id
                    Toast.makeText(
                        this, "Added!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                ?.addOnFailureListener {
                    Toast.makeText(
                        this, "Add Failed!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.user_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_signOut -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_location -> {
                // Handle the camera action
            }
            R.id.nav_manage -> {
                val i = Intent(this, AddUserProfile::class.java)
                startActivityForResult(i, REQ_CODE_ADD)

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
