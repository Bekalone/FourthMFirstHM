package com.example.fourthmfirsthm.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fourthmfirsthm.model.News

@Database(entities = [News::class], version = 1)
 abstract class AppDatabase : RoomDatabase() {
     abstract fun newsDao(): NewsDao
}