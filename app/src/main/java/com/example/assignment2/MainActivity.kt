package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment2.DBHelper.DBHelper
import kotlinx.android.synthetic.main.activity_main.*

//http://www.androidtutorialshub.com/android-kotlin-login-register-with-sqlite-database-tutorial/
class MainActivity : AppCompatActivity() {
    lateinit var database : DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database=DBHelper(this)
        signupbtn.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        signinbtn.setOnClickListener{
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
        showallbtn.setOnClickListener{
            val intent = Intent(this, ShowAll::class.java)
            startActivity(intent)
        }
    }
}
