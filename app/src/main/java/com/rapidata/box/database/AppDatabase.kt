package com.rapidata.box.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rapidata.box.apimodels.SearchData

@Database(entities = [SearchData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val searchDao: SearchDao
}