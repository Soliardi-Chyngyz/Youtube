package com.chyngyz.youtube.ui.main_activity

import android.app.Application
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.chyngyz.youtube.core.BaseViewModel
import com.chyngyz.youtube.data.model.Info
import com.chyngyz.youtube.data.model.VideoInfo
import com.chyngyz.youtube.data.repository.Repository
import com.chyngyz.youtube.data.room.InfoDao
import com.chyngyz.youtube.network.ApiInterface
import com.chyngyz.youtube.utils.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainListViewModel(private val repository: Repository) : BaseViewModel() {
//    val mutableLiveDataVideoInfo = MutableLiveData<MutableList<Info>>(mutableListOf())

    fun fetchMainList(pageToken: String?) {
        val newData = mutableListOf<Info>()

        repository.fetchItemPlaylist(pageToken).observeForever { result ->
            if (result.status == Status.SUCCESS)
                result.data?.forEach { videoInfo ->
                    videoInfo.items?.let { newData?.addAll(it) }
                }
            /*result.data?.enqueue(object : Callback<VideoInfo> {
                override fun onResponse(call: Call<VideoInfo>, response: Response<VideoInfo>) {
                    if (response.isSuccessful)
                        response.body()?.let {
                            list.value = response.body()!!.items
                            receivedToken.value = response.body()?.nextPageToken!!
                        }
                }

                override fun onFailure(call: Call<VideoInfo>, t: Throwable) {
                    Log.e("error", "onFailure: " + t.message)
                }
            })*/
            when (result.status) {
                Status.LOADING -> isLoading.value = true
                else -> isLoading.value = false
            }
            list.value = newData
        }
    }
}