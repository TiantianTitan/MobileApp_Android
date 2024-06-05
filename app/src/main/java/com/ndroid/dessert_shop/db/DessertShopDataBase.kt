package com.ndroid.dessert_shop.db

import android.content.ContentValues
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
        // inserer un nouveau utilisateur dans la base de données
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME,user.name)
        values.put(EMAIL,user.email)
        values.put(PASSWORD,user.password)
        // insert into users(nom,email,password) values(user.nom,user.email, user.password)

        val result = db.insert(USERS_TABLE_NAME,null,values).toInt()


        db.close()

        return result != -1
    }

    fun findUser(email: String, password: String): User?{
        var user:User? = null
        val db = this.readableDatabase
        //val selectQuery = "SELECT * FROM $USERS_TABLE_NAME WHERE $EMAIL ='$email' and $PASSWORD = '$password' "
        //db.rawQuery(selectQuery, arrayOf(email,password))
        val selectionArgs = arrayOf(email,password)
        val cursor = db.query(USERS_TABLE_NAME,null,"$EMAIL=? AND $PASSWORD = ?",selectionArgs ,null,null,null)
        if(cursor.moveToFirst()){
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val email = cursor.getString(2)
            val user = User(id,name,email,"")
            return user
        }

        db.close()
        return user
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