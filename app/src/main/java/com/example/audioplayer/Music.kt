package com.example.audioplayer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.media.MediaMetadataRetriever
import androidx.appcompat.app.AlertDialog
import com.google.android.material.color.MaterialColors
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

data class Music(val id:String, val title:String, val album:String ,val artist:String, val duration: Long = 0, val path: String,
                 val artUri:String)

class Playlist{
    lateinit var name: String
    lateinit var playlist: ArrayList<Music>
    lateinit var createdBy: String
    lateinit var createdOn: String
}


fun formatDuration(duration: Long):String{
    val minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
    val seconds = (TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS) -
            minutes*TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES))
    return String.format("%02d:%02d", minutes, seconds)
}

fun setSongPosition(increment: Boolean){

        if(increment)
        {
            if(PlayerActivity.musicListPA.size - 1 == PlayerActivity.songPosition)
                PlayerActivity.songPosition = 0
            else ++PlayerActivity.songPosition
        }else{
            if(0 == PlayerActivity.songPosition)
                PlayerActivity.songPosition = PlayerActivity.musicListPA.size-1
            else --PlayerActivity.songPosition
        }

}
fun exitApplication(){
    if(PlayerActivity.musicService != null){
        PlayerActivity.musicService!!.audioManager.abandonAudioFocus(PlayerActivity.musicService)
        PlayerActivity.musicService!!.stopForeground(true)
        PlayerActivity.musicService!!.mediaPlayer!!.release()
        PlayerActivity.musicService = null}
    exitProcess(1)
}






