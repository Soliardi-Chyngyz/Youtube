package com.chyngyz.youtube.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast

fun Context.showToast(text: String){
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.ololo(){
    this.setBackgroundColor(Color.BLUE)
}