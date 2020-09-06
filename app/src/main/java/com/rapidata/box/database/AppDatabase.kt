package com.rapidata.box.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rapidata.box.apimodels.SearchValue
import com.rapidata.box.apimodels.SearchValueConverter

@Database(entities = [SearchValue::class], version = 2, exportSchema = false)
@TypeConverters(SearchValueConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val searchDao: SearchDao
}