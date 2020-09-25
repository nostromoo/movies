package com.romain.pedepoy.movies.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    val title: String?,
    val cover: String?
)