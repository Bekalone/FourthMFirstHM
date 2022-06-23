package com.example.fourthmfirsthm.room

import androidx.room.*
import com.example.fourthmfirsthm.model.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM news order by createdAt DESC")
    fun getAll(): List<News>

    @Insert
    fun insert(news: News)

    @Update
    fun update(news: News)

    @Delete(entity = News::class)
    fun delete(news: News)

    @Query("SELECT * FROM news WHERE title LIKE '%'||:search|| '%'")
    fun getSearch(search: String): List<News>


}