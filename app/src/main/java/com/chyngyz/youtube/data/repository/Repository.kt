package com.chyngyz.youtube.data.repository

import com.chyngyz.youtube.Constant
import com.chyngyz.youtube.core.ReposApiClient
import com.chyngyz.youtube.data.model.PlayListItem
import com.chyngyz.youtube.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback


class Repository: ReposApiClient{

    override fun getData(id: String) : Call<PlayListItem> {
        val callback = RetrofitService.getInstance()?.getPLayList(Constant.PART, Constant.PAGE_TOKEN, id, Constant.KEY)
        return callback!!
    }
}