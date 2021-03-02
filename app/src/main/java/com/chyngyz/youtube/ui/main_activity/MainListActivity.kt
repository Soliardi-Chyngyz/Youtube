package com.chyngyz.youtube.ui.main_activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.chyngyz.youtube.R
import com.chyngyz.youtube.data.model.Info
import com.chyngyz.youtube.data.model.VideoInfo
import com.chyngyz.youtube.network.NetConnection
import com.chyngyz.youtube.network.RetrofitService
import com.chyngyz.youtube.ui.main_activity.adapter.MainAdapter
import com.chyngyz.youtube.ui.list_activity.PlayListActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainListActivity : AppCompatActivity(),
    com.chyngyz.youtube.core.BaseAdapter.IBaseAdapterClickListener<Info> {

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        setContentView(R.layout.activity_main)


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
        RetrofitService.getInstance()?.getAllVideo()?.enqueue(object : Callback<VideoInfo> {
            override fun onResponse(
                call: Call<VideoInfo>,
                response: Response<VideoInfo>
            ) {
                if (response.isSuccessful)
                    response.body()?.let {
                        main_progress_bar.visibility = View.GONE
                        adapter.data = response.body()!!.items
                        main_recycler.adapter = adapter
                    }
            }

            override fun onFailure(call: Call<VideoInfo>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun initAdapter() {
        adapter = MainAdapter()
        adapter.listener = this
        main_recycler.adapter = adapter
        main_recycler.itemAnimator = DefaultItemAnimator()
        main_recycler.isNestedScrollingEnabled = true
    }

    override fun onClick(model: Info) {
        val intent = Intent(this, PlayListActivity::class.java).apply {
            putExtra("key", model)
        }
        startActivity(intent)
    }
}