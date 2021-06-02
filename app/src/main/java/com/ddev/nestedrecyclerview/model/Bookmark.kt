package com.ddev.nestedrecyclerview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_table")
data class Bookmark (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id")
    var id: Int? = 0,
    @ColumnInfo(name= "name")
    var name: String? = null
)