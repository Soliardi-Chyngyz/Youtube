package com.chyngyz.youtube.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class NewBaseAdapter<T>(
    private val holderLayoutId: Int
) : RecyclerView.Adapter<NewBaseAdapter<T>.NewBaseViewHolder>() {

    private var data: MutableList<T> = mutableListOf()
    var listener: ((model: T) -> Unit)? = null

    fun setAllData(data: MutableList<T>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewBaseViewHolder {
        return NewBaseViewHolder(
            LayoutInflater.from(parent.context).inflate(holderLayoutId, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NewBaseViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    abstract fun onBind(view: View, model: T)

    inner class NewBaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(model: T) {
            onBind(itemView, model)

            itemView.setOnClickListener {
                listener?.let { it(model) }
            }
        }
    }
}