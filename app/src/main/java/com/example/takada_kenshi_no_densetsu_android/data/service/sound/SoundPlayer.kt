package com.example.takada_kenshi_no_densetsu_android.data.service.sound

import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import com.example.takada_kenshi_no_densetsu_android.R
import javax.inject.Inject

interface SoundPlayer {
    fun play(id: Int)
    fun stop()
}

@UnstableApi
class SoundPlayerImpl @Inject constructor(private val exoPlayer: ExoPlayer) : SoundPlayer {

    override fun play(id: Int) {
        if ((id > 80) || (id < 1)) return
        exoPlayer.setMediaItem(mediaList[id - 1])
        exoPlayer.prepare()
        exoPlayer.play()
    }

    override fun stop() {
        exoPlayer.stop()
    }

    private val mediaList: List<MediaItem> = listOf(
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_1)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_2)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_3)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_4)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_5)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_6)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_7)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_8)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_9)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_10)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_11)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_12)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_13)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_14)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_15)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_16)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_17)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_18)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_19)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_20)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_21)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_22)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_23)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_24)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_25)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_26)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_27)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_28)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_29)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_30)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_31)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_32)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_33)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_34)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_35)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_36)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_37)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_38)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_39)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_40)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_41)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_42)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_43)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_44)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_45)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_46)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_47)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_48)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_49)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_50)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_51)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_52)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_53)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_54)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_55)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_56)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_57)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_58)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_59)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_60)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_61)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_62)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_63)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_64)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_65)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_66)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_67)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_68)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_69)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_70)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_71)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_72)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_73)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_74)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_75)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_76)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_77)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_78)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_79)),
        MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.legend_80)),
    )
}