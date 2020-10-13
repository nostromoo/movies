package com.romain.pedepoy.movies.adapter

import android.view.View

interface OnItemClickListener<T> {
    fun onItemClick(view: View?, data: T)
}