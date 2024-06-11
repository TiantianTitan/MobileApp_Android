package com.ndroid.dessert_shop.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Im
import com.ndroid.dessert_shop.data.Gateau
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
            CREATE TABLE $USERS_TABLE_NAME(
                $USER_ID integer PRIMARY KEY,
                $NAME varchar(50),
                $EMAIL varchar(100),
                $PASSWORD varchar(20)
            )
        """.trimIndent()

        val createTableGateaux = """
            CREATE TABLE $GATEAUX_TABLE_NAME(
                $GATEAU_ID integer PRIMARY KEY,
                $TITLE varchar(50),
                $DESCRIPTION text,
                $IMAGE blob,
                $PRICE text,
                $DESCRIPTION_COMPLET varchar(50),
                $LIKES integer
            )
        """.trimIndent()


        //db?.execSQL(createTableUser)
        db?.execSQL(createTableGateaux)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // la suppression des anciens table,
        //db?.execSQL("DROP TABLE IF EXISTS $USERS_TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $GATEAUX_TABLE_NAME")

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

    fun addGateau(gateau: Gateau):Boolean{
        val db = writableDatabase
        val values = ContentValues()
        values.put(TITLE,gateau.titre)
        values.put(DESCRIPTION,gateau.description)
        values.put(IMAGE,gateau.image)
        values.put(PRICE,gateau.prix)
        values.put(DESCRIPTION_COMPLET,gateau.description_complet)
        values.put(LIKES,0)

        val result = db.insert(GATEAUX_TABLE_NAME,null,values).toInt()
        db.close()

        return result != -1
    }

    fun findGateaux():ArrayList<Gateau>{
        val gateaux = ArrayList<Gateau>()
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $GATEAUX_TABLE_NAME"

        val cursor = db.rawQuery(selectQuery,null)
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(GATEAU_ID))
                    val title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE))
                    val description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION))
                    val image = cursor.getBlob(cursor.getColumnIndexOrThrow(IMAGE))
                    val price = cursor.getString(cursor.getColumnIndexOrThrow(PRICE))
                    val description_complet = cursor.getString(cursor.getColumnIndexOrThrow(
                        DESCRIPTION_COMPLET))
                    val likes = cursor.getInt(cursor.getColumnIndexOrThrow(LIKES))
                    val gateau = Gateau(id,title,description,image,price,description_complet,likes)
                    gateaux.add(gateau)
                }while(cursor.moveToNext())
            }
        }


        db.close()
        return gateaux
    }

    fun deleteGateaux(id: Int): Boolean{
        val db = writableDatabase

        val rowDeleted = db.delete(GATEAUX_TABLE_NAME,"id=?", arrayOf(id.toString()))

        return  rowDeleted > 0
    }

    fun incrementLikes(gateau: Gateau) {
        val db = this.writableDatabase

        val newLikesCount =  gateau.jaime+1
        val values = ContentValues()
        values.put(LIKES,newLikesCount)

        db.update(GATEAUX_TABLE_NAME,values,"id=?", arrayOf("${gateau.id}"))
        db.close()
    }


    companion object {
        private val DB_NAME = "dessertshop_db"
        private val DB_VERSION = 3
        // users
        private val USERS_TABLE_NAME = "users"
        private val USER_ID = "id"
        private val NAME = "name"
        private val EMAIL = "email"
        private val PASSWORD = "password"

        // gateux
        private val GATEAUX_TABLE_NAME = "gateux"
        private val GATEAU_ID = "id"
        private val TITLE = "title"
        private val DESCRIPTION = "description"
        private val DESCRIPTION_COMPLET = "description_complet"
        private val IMAGE = "image"
        private val PRICE = "price"
        private val LIKES = "jaime"


    }


}