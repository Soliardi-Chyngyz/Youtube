package com.chyngyz.youtube.ui.adapter

import android.view.View
import com.chyngyz.youtube.R
import com.chyngyz.youtube.core.BaseAdapter
import com.chyngyz.youtube.data.model.PlayListItem

class ListItemAdapter(playListItem: PlayListItem?) : BaseAdapter<PlayListItem>(R.layout.main_recycler, mutableListOf()) {
    override fun onBind(view: View, model: PlayListItem) {

    }
}