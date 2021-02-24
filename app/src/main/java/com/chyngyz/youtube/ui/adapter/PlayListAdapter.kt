package com.chyngyz.youtube.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chyngyz.youtube.R
import com.chyngyz.youtube.data.model.Info
import com.chyngyz.youtube.data.model.VideoInfo
import kotlinx.android.synthetic.main.main_recycler.view.*
import retrofit2.Callback

class PlayListAdapter(
    private val listener: (pos: Int, info: Info) -> Unit
) : RecyclerView.Adapter<BaseViewHolder>() {
    val list: MutableList<Info> = mutableListOf()

    /*init {
        list.addAll(dslamd)
        notifyDataSetChanged()
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.main_recycler,
            parent,
            false
        );
        return PlaylistViewHolder(view)
    }


    fun addItems(list: MutableList<Info>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position, list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlaylistViewHolder(itemView: View) : BaseViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        override fun onBind(position: Int, info: Info) {
            itemView.apply {
                main_recycler_desc.text = list[position].snippet?.title
                main_recycler_under_desc.text = list[position].contentDetails?.itemCount.toString() + " video serious"
            }
            itemView.setOnClickListener{listener(adapterPosition, info)}
        }

    }
}