package com.example.mybasicview

import android.app.Application

class KardexApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { KardexDataBase.getDatabase(this) }
    val repository by lazy { KardexRepository(database.materiaDAO()) }
}