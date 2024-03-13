package com.example.mybasicview

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class KardexSqliteOpenHelper (context: Context)
    : SQLiteOpenHelper(context, "Kardex.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_ENTRIES = ("CREATE TABLE Materia(clave_materia VARCHAR(7) PRIMARY KEY, " +
                "materia VARCHAR(100), " +
                "periodo VARCHAR(4), " +
                "calificacion INTEGER)")
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    fun getAllKardexItems(): List<Materia> {
        val kardexList = mutableListOf<Materia>()
        val db = writableDatabase
        val cursor = db.query(
            "Materia", // Table name
            null, // Select all columns (null)
            null, // No selection criteria
            null, // No selection arguments
            null, // No group by
            null, // No having
            null  // No order by
        )

        try {
            if (cursor.moveToFirst()) {
                do {

                    val periodo = cursor.getString(cursor.getColumnIndexOrThrow("periodo"))
                    val claveMateria = cursor.getString(cursor.getColumnIndexOrThrow("clave_materia"))
                    val materia = cursor.getString(cursor.getColumnIndexOrThrow("materia"))
                    val calificacion = cursor.getInt(cursor.getColumnIndexOrThrow("calificacion"))
                    // Create a Materia object
                    val materiaKardex = Materia(periodo, claveMateria, materia, calificacion)

                    // Add the Materia object to Singleton.kardex (assuming Singleton is valid)
                    Singleton.kardex.add(materiaKardex)

                    kardexList.add(Materia(periodo, claveMateria, materia, calificacion))
                } while (cursor.moveToNext())
            }
        } finally {
            cursor.close()
            db.close() // Close the database connection
        }

        return kardexList
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        val SQL_DELETE_ENTRIES = ""
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    /*
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "FeedReader.db"*/
}
