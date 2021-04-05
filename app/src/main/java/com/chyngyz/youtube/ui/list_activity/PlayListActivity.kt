package com.chyngyz.youtube.ui.list_activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.chyngyz.youtube.Constant
import com.chyngyz.youtube.R
import com.chyngyz.youtube.core.BaseActivity
import com.chyngyz.youtube.core.BaseAdapter
import com.chyngyz.youtube.core.loadImg
import com.chyngyz.youtube.data.model.DetailsItem
import com.chyngyz.youtube.data.model.Info
import com.chyngyz.youtube.data.model.PlayListItem
import com.chyngyz.youtube.databinding.ActivityList2Binding
import com.chyngyz.youtube.databinding.ActivityListBinding
import com.chyngyz.youtube.ui.list_activity.adapter.ListItemAdapter
import com.chyngyz.youtube.ui.main_activity.MainListViewModel
import com.chyngyz.youtube.ui.video_details.VideoDetailsActivity
import com.chyngyz.youtube.utils.gone
import com.chyngyz.youtube.utils.ololo
import com.chyngyz.youtube.utils.showToast
import com.chyngyz.youtube.utils.visible
import kotlinx.android.synthetic.main.activity_list2.*
import kotlinx.android.synthetic.main.scroll_layout.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent

class PlayListActivity : BaseActivity<PlayListViewModel, ActivityList2Binding>() {
    private lateinit var adapter: ListItemAdapter
    override val viewModel: PlayListViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        initAdapter()
        getData()
    }

    private fun initAdapter() {
        adapter = ListItemAdapter()
        adapter.listener = ::onClick
        list_recyclerview.adapter = adapter
        list_recyclerview.isNestedScrollingEnabled = true
        list_recyclerview.itemAnimator = DefaultItemAnimator()
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        val info: Info? = intent.getSerializableExtra("key") as Info?
        info?.let {
            toolbar_layout.title = info.snippet.title
            info.snippet.thumbnails?.medium?.url?.let { list_img.loadImg(it) }
            val count = info.contentDetails?.itemCount
            list_count_series.text = "$count video series"
            list_collaps_sub_title.text = info.snippet.description
            viewModel.setDataResource(info.id!!)
        }
        viewModel.getDataPlayList().observe(this, {
//            list_progress_bar.visibility = View.GONE
            adapter.setAllData(it.items)
        })
        viewModel.isLoading().observe(this, {
            if(it) list_progress_bar.visible()
            else list_progress_bar.gone()
        })
    }

    fun onClick(model: DetailsItem) {
        val intent = Intent(this, VideoDetailsActivity::class.java)
        intent.putExtra(Constant.YOUTUBE_ID, model)
        startActivity(intent)
    }

    override fun progress(it: Boolean) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityList2Binding {
        return ActivityList2Binding.inflate(inflater)
    }

}