package com.chyngyz.youtube.core

import android.widget.ImageView
import android.widget.LinearLayout
import com.chyngyz.youtube.R
import com.squareup.picasso.Picasso

fun ImageView.loadImg(url: String){
    Picasso.get()
        .load(url)
        .into(this)
}

