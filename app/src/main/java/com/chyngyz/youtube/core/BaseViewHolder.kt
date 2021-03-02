package com.chyngyz.youtube.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chyngyz.youtube.data.model.Info

abstract class BaseViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    abstract fun onBind(position: Int, info: Info)

    companion object {
        val VIEW_TYPE_EMPTY = 0
        val VIEW_TYPE_NORMAL = 1
    }
}