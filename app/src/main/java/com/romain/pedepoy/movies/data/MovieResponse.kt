package com.romain.pedepoy.movies.data

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    val page: Page,
    val heros: Heros,
    val details: Details,
    val clips: List<Clip>
) {
    class Page (
        val movie_title: String,
        val release_copy: String
    )

    class Heros (
        @SerializedName("0")
        val locale: Locale
    ){
        class Locale (
            val imageurl: String
        )
    }

    class Details (
        val official_url: String,
        val locale: Locale
    ) {
        class Locale (
            val en: En
        ){
            class En (
                val synopsis: String,
                val castcrew: CastCrew?
            ){
                class CastCrew (
                    val directors: List<People>?,
                    val writers: List<People>?,
                    val actors: List<People>?
                ){
                    class People (
                        val name: String
                    )
                }
            }
        }
    }


    class Clip (
        val versions: Versions
    ) {
        class Versions (
            val enus: Enus
        ) {
            class Enus (
                val sizes: Sizes
            ){
                class Sizes (
                    val sd: Sd
                ){
                    class Sd (
                        val srcAlt: String,
                        val height: String,
                        val width: Int
                    )
                }
            }
        }
    }
}