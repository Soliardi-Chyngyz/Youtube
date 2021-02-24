package com.chyngyz.youtube.core

import com.chyngyz.youtube.data.model.PlayListItem
import retrofit2.Call
import retrofit2.Callback

interface ReposApiClient {
    fun getData(id: String) : Call<PlayListItem>
}