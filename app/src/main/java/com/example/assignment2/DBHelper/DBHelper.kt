package com.example.assignment2.DBHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VER) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {

        val TABLE_NAME="USERS"
        val COLUMN_ID="ID"
        val COLUMN_NAME="USERNAME"
        val COLUMN_PASSWORD="PASSWORD"
        private val DATABASE_VER = 1
        private val DATABASE_NAME ="users.db"
        private val DATABASE_CREATE = ("CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COLUMN_NAME TEXT,$COLUMN_PASSWORD TEXT)")

    }
}