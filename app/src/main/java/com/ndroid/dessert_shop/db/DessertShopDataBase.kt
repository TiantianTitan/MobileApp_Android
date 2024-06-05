package com.ndroid.dessert_shop.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ndroid.dessert_shop.data.User

class DessertShopDataBase (mContext : Context ) : SQLiteOpenHelper(
    mContext,
    DB_NAME,
    null,
    DB_VERSION
)
{
    override fun onCreate(db: SQLiteDatabase?) {
        // creation des tables
        val createTableUser = """
            CREATE TABLE users(
                $USER_ID integer PRIMARY KEY,
                $NAME varchar(50),
                $EMAIL varchar(100),
                $PASSWORD varchar(20)
            )
        """.trimIndent()
        db?.execSQL(createTableUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // la suppression des anciens table,
        db?.execSQL("DROP TABLE IF EXISTS $USERS_TABLE_NAME")

        // et la re creation des nouveaux
        onCreate(db)
    }

    fun addUser(user: User) : Boolean {
        return false
    }




    companion object {
        private val DB_NAME = "dessertshop_db"
        private val DB_VERSION = 1
        private val USERS_TABLE_NAME = "users"
        private val USER_ID = "id"
        private val NAME = "name"
        private val EMAIL = "email"
        private val PASSWORD = "password"
    }


}