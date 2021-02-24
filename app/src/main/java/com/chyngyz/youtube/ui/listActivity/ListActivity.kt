package com.chyngyz.youtube.ui.listActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.chyngyz.youtube.R
import com.chyngyz.youtube.data.model.Info
import com.chyngyz.youtube.ui.adapter.ListItemAdapter
import kotlinx.android.synthetic.main.activity_list2.*

class ListActivity : AppCompatActivity() {
    private lateinit var adapter: ListItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list2)

        val vModel: ListActivViewModel by viewModels()
        getData(vModel)
    }

    private fun getData(vModel: ListActivViewModel) {
        val info: Info? = intent.getSerializableExtra("key") as Info?
        toolbar_layout.title = info?.snippet?.title
        info?.id?.let { it1 ->
            vModel?.setDataResource(info.id!!)
        }
        vModel?.getData()?.observe(this, {
            adapter = ListItemAdapter(it)
            Log.i("IT", "getData: " + it?.items?.size)
        })

    }
}