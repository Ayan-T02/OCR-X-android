package com.ayant02.ocrx.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayant02.ocrx.data.dao.OCRTextDao
import com.ayant02.ocrx.data.dao.SessionDao
import com.ayant02.ocrx.data.entity.OCRTextEntity
import com.ayant02.ocrx.data.entity.SessionEntity

@Database(
    entities = [
        SessionEntity::class,
        OCRTextEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sessionDao(): SessionDao

    abstract fun ocrTextDao(): OCRTextDao
}