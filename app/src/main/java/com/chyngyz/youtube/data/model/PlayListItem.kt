package com.chyngyz.youtube.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "playList")
data class PlayListItem(
    @NonNull
    @PrimaryKey(autoGenerate = true) var id: Int,
    var playlistApiId: String? = null,
    var items: MutableList<DetailsItem>,
    var nextPageToken: String?
) : Serializable

data class DetailsItem(
    var snippet: DetailsSnippet,
    var isChange: Boolean = false,
    var startTime: Float = 0f
) : Serializable

data class DetailsSnippet(
    var title: String,
    var description: String,
    var publishedAt: String,
    var thumbnails: Thumbnails,
    var playlistId: String? = null,
    var resourceId: ResourceId? = null
) : Serializable

data class ResourceId(
    var videoId: String? = null
) : Serializable
