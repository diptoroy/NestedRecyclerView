package com.ddev.nestedrecyclerview.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ddev.nestedrecyclerview.model.Bookmark

@Database(
    entities = [Bookmark::class],
    version = 1
)
abstract class BookmarkDatabase: RoomDatabase(){
    abstract fun getBookmarkDao(): BookmarkDao

    companion object{
        @Volatile
        private var instance : BookmarkDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK){
                instance
                    ?: buildDatabase(context).also{
                        instance = it
                    }
            }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            BookmarkDatabase::class.java,
            "bookmarkDatabase"
        ).build()
    }
}