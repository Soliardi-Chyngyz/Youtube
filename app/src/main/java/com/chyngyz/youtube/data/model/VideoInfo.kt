package com.chyngyz.youtube.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity
data class VideoInfo(
    @NotNull
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var nextPageToken: String? = null,
    val items: MutableList<Info>
)

data class Info(
    var id: String? = null,
    var snippet: Snippet,
    var contentDetails: ContentDetails? = null
) : Serializable

data class ContentDetails(
    var itemCount: Int = 0,
    var videoPublishedAt: String? = null,
    var videoId: String? = null
) : Serializable

data class Snippet(
    var publishedAt: String? = null,
    var channelId: String? = null,
    var title: String? = null,
    var description: String? = null,
    var thumbnails: Thumbnails? = null,
    var channelTitle: String? = null
) : Serializable

data class Thumbnails(
    var medium: Medium? = null
) : Serializable

data class Medium(
    var url: String? = null,
    var width: String? = null,
    var height: String? = null
) : Serializable
