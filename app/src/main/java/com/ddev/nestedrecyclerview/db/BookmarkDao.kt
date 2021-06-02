package com.ddev.nestedrecyclerview.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ddev.nestedrecyclerview.model.Bookmark

@Dao
interface BookmarkDao {
    @Insert
    suspend fun bookmarkInsert(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark_table")
    suspend fun getAllBookmark(): List<Bookmark>

    @Query("SELECT EXISTS (SELECT 1 FROM bookmark_table WHERE id= :id)")
    suspend fun isBookmark(id: Int): Int

    @Delete
    suspend fun bookmarkDelete(bookmark: Bookmark)
}