package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import com.example.assignment2.Adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_show_all.*

class ShowAll : AppCompatActivity() {
//create adapter and inject into this
private var datasource: Source? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all)
        datasource = Source(this)
        datasource!!.open()
        /* back to main */
        val actionbar = supportActionBar
        actionbar!!.title = "Assignment 2"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        /**/
        val values = datasource!!.allUsers
        val adapter = UserAdapter(values, datasource!!)
        AllUsersView.adapter = adapter
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
