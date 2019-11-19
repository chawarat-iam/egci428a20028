package com.example.assignment2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.assignment2.R
import com.example.assignment2.Source
import com.example.assignment2.model.Users
import kotlinx.android.synthetic.main.row_list.view.*

class UserAdapter(userlist: ArrayList<Users>, datasource: Source) : BaseAdapter() {
    val source:Source = datasource
    val users = userlist
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        if(convertView == null) {
            val inflator = LayoutInflater.from(parent!!.context)
            view = inflator.inflate(R.layout.row_list, parent, false)
            val viewHolder = ViewHolder(view.idtext, view.nametext)
            view.tag = viewHolder
        }
        else{
            view = convertView
        }
        val viewHolder = view.tag as ViewHolder

        viewHolder.id.text =  "User id:"+(position+1).toString() //change to id
        viewHolder.name.text = "username:"+users.get(position).name

        view.setOnClickListener {
            source.deleteUser(users[position])
            users.remove(users[position])
            notifyDataSetChanged()
        }
        return view
    }

    override fun getCount(): Int {
        return users.size
    }

    override fun getItemId(id: Int): Long {
        return id.toLong()
    }

    override fun getItem(position: Int): Any {
        return ""
    }

    private class ViewHolder (val id: TextView, val name: TextView){

    }

}