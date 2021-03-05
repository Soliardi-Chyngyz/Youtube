package com.chyngyz.youtube.ui.list_activity.adapter

import android.view.View
import com.chyngyz.youtube.R
import com.chyngyz.youtube.core.BaseAdapter
import com.chyngyz.youtube.core.NewBaseAdapter
import com.chyngyz.youtube.core.loadImg
import com.chyngyz.youtube.data.model.DetailsItem
import com.chyngyz.youtube.data.model.PlayListItem
import kotlinx.android.synthetic.main.list_item.view.*

class ListItemAdapter : NewBaseAdapter<DetailsItem>(R.layout.list_item) {


    override fun onBind(view: View, model: DetailsItem) {
        view.list_recycler_desc.text = model.snippet?.title
        view.list_recycler_under_desc.text = model.snippet?.publishedAt
        model.snippet.thumbnails.medium?.url?.let { view.list_recycler_img.loadImg(it) }
    }
}