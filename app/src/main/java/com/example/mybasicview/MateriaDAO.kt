package com.example.mybasicview

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MateriaDAO {
    @Query("SELECT * FROM Materia")
    fun getAll(): List<Materia>

    //@Query("SELECT * FROM user WHERE uid IN (:userIds)")
    //fun loadAllByIds(userIds: IntArray): List<User>
    /*
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User
    */

    @Insert
    fun insertAll(vararg m : Materia)

    @Update
    fun update(m: Materia)
}