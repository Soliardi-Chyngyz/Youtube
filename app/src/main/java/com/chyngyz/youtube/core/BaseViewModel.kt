package com.chyngyz.youtube.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chyngyz.youtube.data.model.DetailsItem
import com.chyngyz.youtube.data.model.Info
import com.chyngyz.youtube.data.model.PlayListItem

open class BaseViewModel : ViewModel() {
    protected val isLoading = MutableLiveData(false)
    protected val toast = MutableLiveData<String>()
    protected val list = MutableLiveData<MutableList<Info>>()
    protected val listOfPlaylist = MutableLiveData<PlayListItem>()
    protected var receivedToken = MutableLiveData<String>()

    fun getToast(): LiveData<String> = toast

    fun getData(): LiveData<MutableList<Info>> = list

    fun getDataPlayList(): LiveData<PlayListItem> = listOfPlaylist

    fun isLoading(): LiveData<Boolean> = isLoading

    fun getReceivedToken(): LiveData<String> = receivedToken

}