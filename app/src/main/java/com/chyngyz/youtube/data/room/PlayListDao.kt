package com.chyngyz.youtube.data.room

import androidx.room.*
import com.chyngyz.youtube.data.model.DetailsItem
import com.chyngyz.youtube.data.model.PlayListItem

@Dao
interface PlayListDao {
    @Query ("select * from playList")
    suspend fun getPlaylistItems(): MutableList<PlayListItem>?

    @Query("DELETE FROM playList")
    suspend fun deleteAllPlaylists()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylists(playlists: PlayListItem)

    @Query("select * from playList where playlistApiId like :id")
    suspend fun getDetailsItemsById(id: String): PlayListItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll (list: List<PlayListItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlayList(playListItem: PlayListItem)

    @Delete
    suspend fun delete(data: PlayListItem)

    /*@Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update()*/
}