package com.example.mybasicview

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MateriaDAO {
    @Query("SELECT * FROM Materia")
    fun getAll(): Flow<List<Materia>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg m : Materia)

    @Update
    fun update(m: Materia)
}