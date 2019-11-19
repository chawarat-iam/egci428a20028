package com.example.assignment2

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.assignment2.DBHelper.DBHelper
import com.example.assignment2.model.Users
import kotlinx.android.synthetic.main.activity_sign_in.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class SignUp : AppCompatActivity() {
    private var datasource: Source? = null
    //var userData  = Users()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        datasource = Source(this)
        datasource!!.open()
        setContentView(R.layout.activity_sign_up)
        //SQLiteDatabase db=this
        /* back to main */
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Assignment 2"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        /**/
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun onClick(view: View) {
        //val db = DBHelper(this)

            if (datasource!!.existence(username!!.text.toString()) == true) {
                Toast.makeText(applicationContext, "Username is invalid", Toast.LENGTH_SHORT).show()
                username.setText("")
            }
            else if (password.length() < 8) { //check for password length
                Toast.makeText(applicationContext, "Password is invalid", Toast.LENGTH_SHORT).show()
                password.setText("")
            }
        else{
                if (datasource!!.existence(username.text.toString()) == false && password.length() >= 8) {
                    val newuser = Users(username.text.toString(), password.text.toString())
                    datasource!!.addUser(newuser)
                    Toast.makeText(applicationContext, "Sign Up success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }



    }
}
