package com.chyngyz.youtube.ui.list_activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.chyngyz.youtube.R
import com.chyngyz.youtube.core.BaseAdapter
import com.chyngyz.youtube.core.loadImg
import com.chyngyz.youtube.data.model.DetailsItem
import com.chyngyz.youtube.data.model.Info
import com.chyngyz.youtube.ui.list_activity.adapter.ListItemAdapter
import com.chyngyz.youtube.utils.ololo
import com.chyngyz.youtube.utils.showToast
import kotlinx.android.synthetic.main.activity_list2.*
import kotlinx.android.synthetic.main.scroll_layout.*

class PlayListActivity : AppCompatActivity(), BaseAdapter.IBaseAdapterClickListener<DetailsItem> {
    private lateinit var adapter: ListItemAdapter
    private var vModel: PlayListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_list2)

        initAdapter()
        vModel = ViewModelProvider(this).get(PlayListViewModel::class.java)
        getData()

    }

    private fun initAdapter() {
        adapter = ListItemAdapter()
        adapter.listener = this
        list_recyclerview.adapter = adapter
        list_recyclerview.isNestedScrollingEnabled = true
        list_recyclerview.itemAnimator = DefaultItemAnimator()
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        val info: Info? = intent.getSerializableExtra("key") as Info?
        info?.let {
            toolbar_layout.title = info?.snippet?.title
            info?.snippet.thumbnails?.medium?.url?.let { list_img.loadImg(it) }
            val count = info?.contentDetails?.itemCount
            list_count_series.text = "$count video series"
            list_collaps_sub_title.text = info?.snippet?.description
            vModel?.setDataResource(info.id!!)
        }
        vModel?.getData()?.observe(this, {
            list_progress_bar.visibility = View.GONE
            adapter.data = it?.items!!
            adapter.notifyDataSetChanged()
        })
    }

    override fun onClick(model: DetailsItem) {
    }
}