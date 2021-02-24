package com.chyngyz.youtube

import com.google.api.services.youtube.YouTubeScopes

object Constant {
    const val API_KEY = "AIzaSyCAdfnIfC8_WM9F4SXrl9Jg66TIw7yV5O0"
    const val REQUEST_ACCOUNT_PICKER = 1000
    const val REQUEST_AUTHORIZATION = 1001
    const val REQUEST_GOOGLE_PLAY_SERVICES = 1002
    const val REQUEST_PERMISSION_GET_ACCOUNTS = 1003
    private const val BUTTON_TEXT = "Call YouTube Data API"
    private const val PREF_ACCOUNT_NAME = "accountName"
    private val SCOPES = arrayOf(YouTubeScopes.YOUTUBE_READONLY)

    const val PART = "snippet,contentDetails"
    const val KEY = "AIzaSyCAdfnIfC8_WM9F4SXrl9Jg66TIw7yV5O0"
    const val PAGE_TOKEN = "CAUQAA"
}