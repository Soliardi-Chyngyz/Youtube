package com.chyngyz.youtube.network

import com.chyngyz.youtube.data.model.DetailsItem
import com.chyngyz.youtube.data.model.Info
import com.chyngyz.youtube.data.model.PlayListItem
import com.chyngyz.youtube.data.model.VideoInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("playlists")
    suspend fun getAllVideoSeparate(
        @Query("part") part: String,
        @Query("pageToken") pageToken: String?,
        @Query("key") key: String,
        @Query("channelId") channelId: String
    ): VideoInfo

    @GET("playlists?part=snippet,contentDetails&channelId=UC4X7J9D6VbTIwnFDFNkfQ1A&key=AIzaSyCAdfnIfC8_WM9F4SXrl9Jg66TIw7yV5O0")
    fun getAllVideo(): Call <VideoInfo>

    @GET("playlistItems")
    suspend fun getPLayList(
        @Query("part") part: String,
        @Query("pageToken") pageToken: String?,
        @Query("playlistId") playlistId: String,
        @Query("key") key: String
    ) : PlayListItem
}