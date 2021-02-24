package com.chyngyz.youtube.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import com.chyngyz.youtube.R
import com.chyngyz.youtube.core.BaseAdapter
import com.chyngyz.youtube.core.loadImg
import com.chyngyz.youtube.data.model.Info
import com.chyngyz.youtube.data.model.VideoInfo
import kotlinx.android.synthetic.main.main_recycler.view.*

class MainActivityAdapter : BaseAdapter<Info>(R.layout.main_recycler, mutableListOf()) {
    @SuppressLint("SetTextI18n")
    override fun onBind(view: View, model: Info) {
        view.main_recycler_desc.text = model.snippet?.title
        view.main_recycler_under_desc.text = model.contentDetails?.itemCount.toString() + " video series"
        model.snippet?.thumbnails?.medium?.url?.let { view.main_recycler_img.loadImg(it) }
    }

}