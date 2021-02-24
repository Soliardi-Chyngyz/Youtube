package com.chyngyz.youtube.ui.listActivity

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chyngyz.youtube.data.model.PlayListItem
import com.chyngyz.youtube.data.repository.Repository
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivViewModel(private val repository: Repository) : ViewModel() {

    private val playListItems = MutableLiveData<PlayListItem?>()

    fun getData(): LiveData<PlayListItem?> {
        return playListItems
    }

    fun setDataResource(playListId: String) {
        repository.getData(playListId).enqueue(object : Callback<PlayListItem> {
            override fun onResponse(call: Call<PlayListItem>, response: Response<PlayListItem>) {
                if (response.isSuccessful)
                    response.body()?.let {
                        playListItems.value = response.body()
                    }
            }

            override fun onFailure(call: Call<PlayListItem>, t: Throwable) {
                t.printStackTrace()
                Log.e("ERROR", "onFailure: " + t.message)
            }

        })
    }
}