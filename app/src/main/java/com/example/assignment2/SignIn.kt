package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.assignment2.DBHelper.DBHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.signinbtn

class SignIn : AppCompatActivity() {
    private var datasource: Source? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        datasource = Source(this)
        datasource!!.open()
        /* back to main */
        val actionbar = supportActionBar
        actionbar!!.title = "Assignment 2"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        /**/
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun onClick(view: View) {
        val name = username.text.toString()
        val pass = password.text.toString()
        if (datasource!!.checkPass(name,pass)==1){
            Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,Welcome::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(applicationContext, "Login error", Toast.LENGTH_SHORT).show()
            username.setText("")
            password.setText("")
        }
    }
}
