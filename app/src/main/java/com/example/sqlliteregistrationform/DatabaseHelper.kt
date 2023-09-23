package com.example.sqlliteregistrationform

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


//Declaring all the required field that we have to insert in the data base sql lite --- 1st step
val DATABASE_NAME="MyDb"
val TABLE_NAME="USERS"
val COL_ID="USERID"
val COL_USEERNAME="USERNAME"
val COL_USEEREMAIL="USEREMAIL"
val COL_USEERPASSWORD="USERPASSWORD"
val COL_USEERPHONE="USERPHONE"
val COL_USEERDOB="USERDOB"
val COL_USEERGENDER="USERGENDER"

// Declaring a Database Helper class inside the parameter we are passing context and extending the SQLiteOpenHelper
// and inside the SQLiteOpenHelper we are passing four parameter i.e context, database name, version and factory=null--2nd step

class DatabaseHelper(var context: Context):SQLiteOpenHelper(context, "DATABASE_NAME",null,1) {

    override fun onCreate(db:SQLiteDatabase?) {
        db?.execSQL(                 // Creating the USERS table---------- 3rd step
            "CREATE TABLE $TABLE_NAME (" +
                    "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COL_USEERNAME TEXT," +
                    "$COL_USEEREMAIL TEXT," +
                    "$COL_USEERPASSWORD TEXT," +
                    "$COL_USEERPHONE TEXT," +
                    "$COL_USEERDOB TEXT," +
                    "$COL_USEERGENDER TEXT)"
        )

        // Inserting a user into the USERS table Statically
        db?.execSQL(
            "INSERT INTO $TABLE_NAME ($COL_USEERNAME,$COL_USEEREMAIL,$COL_USEERPASSWORD,$COL_USEERPHONE,$COL_USEERDOB,$COL_USEERGENDER) " +
                    "VALUES ('ANIKET','aniket@example.com','password123','1234567890','01-01-2000','Male')"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // A method to insert user data into the USERS table dynamically

    fun insertData(user: UserObject): Boolean {       //------------4th step

        // This is a writeDatabase method to insert data ---------------5th step
        val db = this.writableDatabase

        val cv = ContentValues()     // To insert data we need content values -------------------------6th Step
        cv.put(COL_USEERNAME, user.username)   //  cv.put(COL_NAME, values)
        cv.put(COL_USEEREMAIL, user.useremail)
        cv.put(COL_USEERPASSWORD, user.userpassword)
        cv.put(COL_USEERPHONE, user.userphone)
        cv.put(COL_USEERDOB, user.userdob)
        cv.put(COL_USEERGENDER, user.usergender)


        // All values are inserted into the database
        val result = db.insert(TABLE_NAME, null, cv)
        return result != -1L
        db.close()

    }

    @SuppressLint("RestrictedApi", "Range")
    fun getUser(useremail: String): DataClass? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COL_ID, COL_USEEREMAIL, COL_USEERPASSWORD),
            "$COL_USEEREMAIL=?",
            arrayOf(useremail),
            null, null, null, null
        )
        cursor?.use {
            // ensures that the "use" function will only be called if the "cursor" object is not null

            if (it.moveToFirst()) {
                // If there is a matching row in the cursor, create a DataClass object and return it
                return DataClass(
                    it.getInt(it.getColumnIndex(COL_ID)),
                    it.getString(it.getColumnIndex(COL_USEEREMAIL)),
                    it.getString(it.getColumnIndex(COL_USEERPASSWORD))
                )
            }
        }
        return null
    }
}
