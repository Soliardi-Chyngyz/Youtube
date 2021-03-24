package com.chyngyz.youtube.data.room

import android.content.ClipData
import androidx.room.*
import com.chyngyz.youtube.data.model.DetailsItem
import com.chyngyz.youtube.data.model.PlayListItem
import com.chyngyz.youtube.data.model.VideoInfo

@Dao
interface InfoDao {
    @Query("select * from videoinfo")
    suspend fun getPlaylistItems(): MutableList<VideoInfo>?

    @Query("DELETE FROM videoinfo")
    suspend fun deleteAllPlaylists()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylists(videoInfo: VideoInfo)

    /*@Query("select * from videoinfo where items like :id")
    fun getDetailsItemsById(id: String): DetailsItem?*/

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll (vararg list: List<VideoInfo>)*/

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePlayList(playListItem: VideoInfo)*/

    /*@Delete
    fun delete(data: VideoInfo)*/

    /*@Update(onConflict = OnConflictStrategy.REPLACE)
    fun update()*/
}