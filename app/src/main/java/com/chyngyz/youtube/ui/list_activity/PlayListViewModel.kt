package com.chyngyz.youtube.ui.list_activity

import androidx.lifecycle.MutableLiveData
import com.chyngyz.youtube.core.BaseViewModel
import com.chyngyz.youtube.data.model.PlayListItem
import com.chyngyz.youtube.data.repository.Repository
import com.chyngyz.youtube.utils.Status
import org.koin.java.KoinJavaComponent.inject

class PlayListViewModel(private var repository: Repository) : BaseViewModel() {

    fun setDataResource(playListId: String) {
        repository.fetchDetailsPlaylistById(null, playListId).observeForever{ resource ->
            if(resource.status == Status.SUCCESS)
                resource?.data.let {
                listOfPlaylist.value = resource.data
                }
            when(resource.status){
                Status.LOADING -> isLoading.value = true
                else -> isLoading.value = false
            }
        }
    }


    /*repository?.getData(playListId)?.enqueue(object : Callback<PlayListItem> {
        override fun onResponse(call: Call<PlayListItem>, response: Response<PlayListItem>) {
            if (response.isSuccessful)
                response.body()?.let {
                    playListItems.value = response.body()
                }
        }

        override fun onFailure(call: Call<PlayListItem>, t: Throwable) {
            t.printStackTrace()
        }

    })*/
}
