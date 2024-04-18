package com.example.mybasicview

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Materia::class], version = 1)
abstract class KardexDataBase : RoomDatabase() {
    abstract fun materiaDAO(): MateriaDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: KardexDataBase? = null

        fun getDatabase(context: Context): KardexDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            //ponerle un valor a instance y si ya tiene regresarlo
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KardexDataBase::class.java,
                    "materia_kardex_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
                //la ultima linea es lo que regresa
                //si pongo un "" me marcara error porque no retornar string
            }
        }//->
        //se asegura que fuera un unico hilo
    }
} 