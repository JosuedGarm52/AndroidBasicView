package com.example.mybasicview

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Materia::class], version = 1)
abstract class KardexDataBase : RoomDatabase() {
    abstract fun materiaDAO(): MateriaDAO
} 