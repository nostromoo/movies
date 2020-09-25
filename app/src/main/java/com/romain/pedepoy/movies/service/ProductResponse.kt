package com.romain.pedepoy.movies.service


data class MoviesResponse (
    val product: Product?= null
){
    data class Product (
        val product_name_fr: String? = null,
        val image_url: String? = null)
}