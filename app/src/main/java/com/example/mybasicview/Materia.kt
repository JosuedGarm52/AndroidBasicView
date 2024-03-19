package com.example.mybasicview

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Materia(
    var periodo: String,
    @PrimaryKey @ColumnInfo(name = "claveMateria") var claveMateria: String,
    var materia: String,
    var calificacion: Int
)
