package com.chyngyz.youtube.ui.main_activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import com.chyngyz.youtube.R
import com.chyngyz.youtube.core.BaseActivity
import com.chyngyz.youtube.core.BaseAdapter
import com.chyngyz.youtube.data.model.Info
import com.chyngyz.youtube.network.NetConnection
import com.chyngyz.youtube.ui.list_activity.PlayListActivity
import com.chyngyz.youtube.ui.main_activity.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainListActivity : AppCompatActivity(),
    BaseAdapter.IBaseAdapterClickListener<Info> {

    private lateinit var adapter: MainAdapter
    private val viewModel: MainListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        initAdapter()
        popAdapter()
        network()
    }

    private fun network() {
        val networkConnection = NetConnection(this)
        networkConnection.observe(this, { isConnected ->
            if (isConnected) {
                no_network_img.visibility = View.GONE
                popAdapter()
            } else
                no_network_img.visibility = View.VISIBLE
        })
    }

    private fun popAdapter() {
        viewModel.fetchMainList(null)
        viewModel.getData().observe(this, {
            adapter.data = it
        })
        viewModel.isLoading().observe(this, {
            when (it) {
                true -> main_progress_bar.visibility = View.VISIBLE
                false -> main_progress_bar.visibility = View.GONE
            }
        })
    }

    fun initAdapter() {
        adapter = MainAdapter()
        adapter.listener = this
        main_recycler.adapter = adapter
        main_recycler.itemAnimator = DefaultItemAnimator()
        main_recycler.isNestedScrollingEnabled = true

//        main_recycler.addOnScrollListener()
    }

    override fun onClick(model: Info) {
        val intent = Intent(this, PlayListActivity::class.java).apply {
            putExtra("key", model)
        }
        startActivity(intent)
    }
}