package com.chyngyz.youtube.core

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImg(url: String){
    Picasso.get()
        .load(url)
        .into(this)
}