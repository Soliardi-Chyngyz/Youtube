package com.chyngyz.youtube.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.chyngyz.youtube.data.model.PlayListItem
import com.chyngyz.youtube.data.model.VideoInfo
import com.chyngyz.youtube.data.room.AppDatabase
import com.chyngyz.youtube.network.ApiInterface
import com.chyngyz.youtube.utils.Resource
import kotlinx.coroutines.Dispatchers

const val channelId = "UC4X7J9D6VbTIwnFDFNkfQ1A"
const val key = "AIzaSyCAdfnIfC8_WM9F4SXrl9Jg66TIw7yV5O0"
const val part = "snippet,contentDetails"

class Repository(private val db: AppDatabase, private var api: ApiInterface) {

    /* override fun getData(id: String): Call<PlayListItem> {
         val callback = RetrofitService.getInstance()
             ?.getPLayList(Constant.PART, Constant.PAGE_TOKEN, id, Constant.API_KEY)
         return callback!!
     }*/

    /*fun fetchPlaylist() = liveData(Dispatchers.IO) {
        // emit это fun присваивает значение в liveData
        emit(Resource(Status.LOADING, data = null, message = null))
        try {
            emit(Resource.success(data = RetrofitService.getInstance()?.getAllVideo()))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message.toString()))
        }
    }*/

    fun fetchItemPlaylist(pageToke: String?) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            updateDataPLaylistDao(pageToke)
            Log.i("unique", "fetchItemPlaylist: " + db.infoDao().getPlaylistItems()?.size)
            // emit сетит значения в LiveData
            emit(Resource.success(db.infoDao().getPlaylistItems()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

    private suspend fun updateDataPLaylistDao(pageToke: String?) {
        var playlists: MutableList<VideoInfo>? = null
        try {
            playlists = fetchPlaylistsByNetwork(pageToke, mutableListOf())
        } catch (ignore: Exception) {
        }
        if (playlists != null) {
            db.infoDao().deleteAllPlaylists()
            playlists.forEach { db.infoDao().insertPlaylists(it) }
        }
    }

    private suspend fun fetchPlaylistsByNetwork(
        pageToke: String?,
        data: MutableList<VideoInfo>
    ): MutableList<VideoInfo>? {
        try {
            val newData =
                api.getAllVideoSeparate(part, pageToke, key, channelId)
            if (newData != null) {
                newData.also {
                    data.add(it)
                    return if (it.nextPageToken != null) fetchPlaylistsByNetwork(
                        it.nextPageToken,
                        data
                    )
                    else data
                }
            } else return null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun fetchDetailsPlaylistById(pageToken: String?, playlistId: String) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                updateDataDetailPlaylist(pageToken, playlistId)
                emit(Resource.success(data = db.todoDao().getDetailsItemsById(playlistId)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.error(data = null, message = e.message ?: "Error"))
            }
        }

    private suspend fun updateDataDetailPlaylist(
        pageToken: String?,
        playlistId: String
    ) {
        var data: PlayListItem? = null
        try {
            data = fetchDetailPlaylistsByNetwork(pageToken, playlistId, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (data != null) {
            data.playlistApiId = data.items[0].snippet.playlistId.toString()
            db.todoDao().insertPlaylists(data)
        }
    }

    private suspend fun fetchDetailPlaylistsByNetwork(
        pageToken: String?,
        playlistId: String,
        data: PlayListItem?
    ): PlayListItem? {
        val newData = api.getPLayList(part, pageToken, playlistId, key)
        data?.let { newData?.items?.addAll(it.items) }
        return if (newData?.nextPageToken != null) fetchDetailPlaylistsByNetwork(
            newData.nextPageToken,
            playlistId,
            newData
        )
        else newData
    }

}