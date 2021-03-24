package com.chyngyz.youtube.ui.video_details

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chyngyz.youtube.Constant
import com.chyngyz.youtube.R
import com.chyngyz.youtube.data.model.DetailsItem
import com.chyngyz.youtube.data.model.PlayListItem
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_video_details.*
import kotlinx.coroutines.GlobalScope
import java.lang.String

class VideoDetailsActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    lateinit var videoId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_details)
        val model = intent.getSerializableExtra(Constant.YOUTUBE_ID) as DetailsItem
        videoId = (model.snippet.resourceId?.videoId ?: "") as String
        setData(model)
        setupViews()

    }

    private fun setData(model: DetailsItem) {
        video_title.text = model.snippet.title
        video_desc.text = model.snippet.description
    }

    private fun setupViews() {
        youtube_view.initialize(Constant.API_KEY, this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        if (!wasRestored) {
            player?.cueVideo(videoId.toString())
            Toast.makeText(this, "Yulalaaa", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        errorReason: YouTubeInitializationResult
    ) {
        if (errorReason.isUserRecoverableError) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show()
        } else {
            val error = String.format(getString(R.string.player_error), errorReason.toString())
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RECOVERY_REQUEST) {
            youtube_view.initialize(Constant.API_KEY, this)
        }
    }

    companion object {
        private const val RECOVERY_REQUEST = 1
    }
}