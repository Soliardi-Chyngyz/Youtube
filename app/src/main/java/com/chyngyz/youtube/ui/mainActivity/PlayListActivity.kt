package com.chyngyz.youtube.ui.mainActivity

import android.content.Intent
import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.chyngyz.youtube.R
import com.chyngyz.youtube.data.model.Info
import com.chyngyz.youtube.data.model.VideoInfo
import com.chyngyz.youtube.network.RetrofitService
import com.chyngyz.youtube.ui.adapter.MainActivityAdapter
import com.chyngyz.youtube.ui.adapter.PlayListAdapter
import com.chyngyz.youtube.ui.listActivity.ListActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlayListActivity : AppCompatActivity(), com.chyngyz.youtube.core.BaseAdapter.IBaseAdapterClickListener<Info> {

    private lateinit var adapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        popAdapter()

    }

    private fun popAdapter() {
        RetrofitService.getInstance()?.getAllVideo()?.enqueue(object : Callback<VideoInfo> {
            override fun onResponse(
                call: Call<VideoInfo>,
                response: Response<VideoInfo>
            ) {
                if (response.isSuccessful)
                    response.body()?.let {
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
        adapter = MainActivityAdapter()
        adapter.listener = this
        main_recycler.adapter = adapter
        main_recycler.itemAnimator = DefaultItemAnimator()
        main_recycler.isNestedScrollingEnabled = true


    }

    override fun onClick(model: Info) {
        val intent = Intent(this, ListActivity::class.java).apply{
            putExtra("key", model)
        }
        startActivity(intent)
    }


}