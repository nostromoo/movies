package com.romain.pedepoy.movies.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey
    val title: String,
    val cover: String?,
    val videoUrl: String?,
    val videoHeight: Int,
    val videoWidth: Int,
    val officialUrl: String?,
    val synopsis: String?,
    val releaseDate: String?,
    val rating: String?,
    @TypeConverters(MyTypeConverter::class)
    val directors: List<String>,
    @TypeConverters(MyTypeConverter::class)
    val writers: List<String>,
    @TypeConverters(MyTypeConverter::class)
    val actors: List<String>
){
    fun releaseLabel() =
        " Release date : $releaseDate"

    fun ratingLabel() =
        " Rating :\n ${rating?.toUpperCase()}"

    fun directorsLabel() =
        " Director :\n ${directors.joinToString(", ")}"

    fun writersLabel() =
        " Writer :\n ${writers.joinToString(", ")}"

    fun actorsLabel() =
        " Actors :\n ${actors.joinToString(", ")}"

}