package com.example.assignment2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.example.assignment2.DBHelper.DBHelper
import com.example.assignment2.model.Users

class Source(context: Context) {
    private var database: SQLiteDatabase? = null
    private val dbHelper: DBHelper
    init{
        dbHelper= DBHelper(context)
    }
    private val columnList =
        arrayOf<String>(DBHelper.COLUMN_ID, DBHelper.COLUMN_NAME, DBHelper.COLUMN_PASSWORD)

    fun addUser(user: Users)
    {
        val db = dbHelper.writableDatabase
        val values= ContentValues()
        values.put(DBHelper.COLUMN_NAME,user.name)
        values.put(DBHelper.COLUMN_PASSWORD,user.password)
        db.insert(DBHelper.TABLE_NAME,null,values)
        db.close()
    }

    fun deleteUser(user: Users){
        val id=user.id
        val db = dbHelper.writableDatabase
        db.delete(DBHelper.TABLE_NAME,DBHelper.COLUMN_ID+"="+id,null)
        db.close()
    }
    @Throws(SQLException::class)
    fun open() {
        database = dbHelper.getWritableDatabase()
    }

    val allUsers: ArrayList<Users>
        get() {
            val userlist = ArrayList<Users>()

            val cursor = database!!.query(
                DBHelper.TABLE_NAME,
                columnList, null, null, null, null, null
            )

            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val user = cursorToUser(cursor)
                userlist.add(user)
                cursor.moveToNext()
            }
            cursor.close()
            return userlist
        }
    /*
    fun checkPass(username:String,password:String): Int{
        val name =DBHelper.COLUMN_NAME
        val pw=DBHelper.COLUMN_PASSWORD
        val user=database!!.query(DBHelper.TABLE_NAME,columnList,"$name ='$username' AND $pw='$password'",null,null,null,null)
        val num=user.count
        user.close()
        return num
    }

     */

    fun checkPass(username: String,password: String): Int{
        val col_username = DBHelper.COLUMN_NAME
        val col_password = DBHelper.COLUMN_PASSWORD
        val user = database!!.query(DBHelper.TABLE_NAME,
            columnList, "$col_username = '$username' AND $col_password='$password'", null, null, null, null)
        val num = user.count
        user.close()
        return num
    }

    private fun cursorToUser(cursor: Cursor): Users {
        val comment = Users()
        comment.id = cursor.getInt(-0)
        comment.name = cursor.getString(1)
        comment.password = cursor.getString(2)
        return comment
    }
    fun existence(name: String): Boolean {
        val columns = arrayOf(DBHelper.COLUMN_ID)
        val db = dbHelper.readableDatabase
        val selection = "${DBHelper.COLUMN_NAME} = ?"
        val selectionArgs = arrayOf(name)
        val cursor = db.query(
            DBHelper.TABLE_NAME, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }
}