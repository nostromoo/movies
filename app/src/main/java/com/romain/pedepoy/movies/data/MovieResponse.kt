package com.romain.pedepoy.movies.data

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    val page: Page,
    val heros: Heros,
    val clips: List<Clip>
) {
    class Page (
        val movie_title: String
    )

    class Heros (
        @SerializedName("0")
        val locale: Locale
    ){
        class Locale (
            val imageurl: String
        )
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
                        val srcAlt: String
                    )
                }
            }
        }
    }
}