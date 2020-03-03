package com.xiaomi.smarthome.fastvideo;

import android.media.MediaPlayer;
import com.xiaomi.smarthome.camera.XmMp4Player;

public abstract class VideoPlayerBase extends VideoFrameDecoder implements XmMp4Player {
    MediaPlayer.OnCompletionListener h = null;
    MediaPlayer.OnErrorListener i = null;
    MediaPlayer.OnPreparedListener j = null;
    XmMp4Player.RenderCallback k = null;
    XmMp4Player.AudioListener l = null;

    public abstract void changeSource(String str);

    public abstract int getCurrentPosition();

    public abstract int getDuration();

    public abstract boolean isPlaying();

    public abstract void openVideoPlayer(String str);

    public abstract void pause();

    public abstract void seekTo(int i2);

    public abstract void setVolume(int i2);

    public VideoPlayerBase(VideoGlSurfaceView videoGlSurfaceView) {
        super(videoGlSurfaceView);
    }

    public void setAudioListener(XmMp4Player.AudioListener audioListener) {
        this.l = audioListener;
    }

    public void setRenderCallback(XmMp4Player.RenderCallback renderCallback) {
        this.k = renderCallback;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.j = onPreparedListener;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.h = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.i = onErrorListener;
    }
}
