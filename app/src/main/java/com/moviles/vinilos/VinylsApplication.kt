package com.moviles.vinilos

import android.app.Application
import com.moviles.vinilos.database.VinylRoomDatabase

class VinylsApplication: Application()  {
    val database by lazy { VinylRoomDatabase.getDatabase(this) }
}