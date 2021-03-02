package com.chyngyz.youtube.ui.list_activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chyngyz.youtube.data.model.DetailsItem
import com.chyngyz.youtube.data.model.PlayListItem
import com.chyngyz.youtube.data.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayListViewModel() : ViewModel() {
    private val repository = Repository()
    private val playListItems = MutableLiveData<PlayListItem?>()

    fun getData(): LiveData<PlayListItem?> {
        return playListItems
    }

    fun setDataResource(playListId: String) {
        repository?.getData(playListId)?.enqueue(object : Callback<PlayListItem> {
            override fun onResponse(call: Call<PlayListItem>, response: Response<PlayListItem>) {
                if (response.isSuccessful)
                    response.body()?.let {
                        playListItems.value = response.body()
                    }
            }

            override fun onFailure(call: Call<PlayListItem>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}