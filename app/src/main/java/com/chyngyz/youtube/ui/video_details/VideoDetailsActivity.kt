package com.chyngyz.youtube.ui.video_details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chyngyz.youtube.Constant
import com.chyngyz.youtube.R
import com.chyngyz.youtube.core.BaseActivity
import com.chyngyz.youtube.data.model.DetailsItem
import com.chyngyz.youtube.databinding.ActivityVideoDetailsBinding
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_video_details.*
import org.koin.android.ext.android.inject

class VideoDetailsActivity : AppCompatActivity() {

    lateinit var videoId: String
    private var mPlayer: SimpleExoPlayer? = null
    private var currentWindow = 0
    private var playBackPos: Long = 0
    private var playWhenReady = true
    private val hlsUri = "https://www.youtube.com/watch?v=9H7OiP1AQx0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*val model = intent.getSerializableExtra(Constant.YOUTUBE_ID) as DetailsItem
        videoId = (model.snippet.resourceId?.videoId ?: "")
        setData(model)*/
    }

    private fun initPlayer() {
        mPlayer = SimpleExoPlayer.Builder(this).build()
        youtube_view.player = mPlayer
        mPlayer!!.playWhenReady = true
        mPlayer!!.seekTo(playBackPos)
        mPlayer!!.prepare(buildMediaSource(), false, false,)
    }

    private fun setData(model: DetailsItem) {
        video_title.text = model.snippet.title
        video_desc.text = model.snippet.description
    }

    override fun onStart() {
        super.onStart()
        if(Util.SDK_INT >= 24){
            initPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
        if(Util.SDK_INT < 24 || mPlayer == null) {
            initPlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if(Util.SDK_INT < 24)
            releasePlayer()
    }

    private fun releasePlayer() {
        if(mPlayer == null) return
        playWhenReady = mPlayer!!.playWhenReady
        playBackPos = mPlayer!!.currentPosition
        currentWindow = mPlayer!!.currentWindowIndex
        mPlayer!!.release()
        mPlayer = null
    }

    private fun hideSystemUI() {
        youtube_view.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

    }

    private fun buildMediaSource(): MediaSource {
        val userAgent = Util.getUserAgent(this, youtube_view.context.getString(R.string.app_name))

        val dataSourceFactory = DefaultHttpDataSourceFactory(userAgent)
        val hlsMediaSource =  HlsMediaSource.Factory(dataSourceFactory).
        createMediaSource(Uri.parse(hlsUri))

        return  hlsMediaSource

    }
}