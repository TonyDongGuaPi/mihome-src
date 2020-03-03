package com.ximalaya.ting.android.player;

import android.content.Context;
import com.ximalaya.ting.android.player.XMediaPlayer;
import com.ximalaya.ting.android.player.XMediaplayerJNI;
import com.ximalaya.ting.android.player.model.HttpConfig;
import java.io.FileDescriptor;
import java.util.Map;

public interface XMediaplayerImpl {
    public static final int n = 0;
    public static final int o = 1;
    public static final int p = 2;
    public static final int q = 3;
    public static final int r = 4;
    public static final int s = 5;
    public static final int t = 6;
    public static final int u = 7;
    public static final int v = 8;
    public static final int w = 9;
    public static final int x = 10;
    public static final int y = 11;
    public static final int z = 12;

    int a();

    void a(int i);

    void a(long j);

    void a(Context context, int i);

    void a(XMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener);

    void a(XMediaPlayer.OnCompletionListener onCompletionListener);

    void a(XMediaPlayer.OnErrorListener onErrorListener);

    void a(XMediaPlayer.OnInfoListener onInfoListener);

    void a(XMediaPlayer.OnPositionChangeListener onPositionChangeListener);

    void a(XMediaPlayer.OnPreparedListener onPreparedListener);

    void a(XMediaPlayer.OnSeekCompleteListener onSeekCompleteListener);

    void a(HttpConfig httpConfig);

    void a(FileDescriptor fileDescriptor, String str);

    void a(Map<String, String> map);

    boolean c();

    void d();

    XMediaplayerJNI.AudioType getAudioType();

    int getCurrentPosition();

    int getDuration();

    boolean isPlaying();

    void pause();

    void prepareAsync();

    void release();

    void reset();

    void seekTo(int i);

    void setDataSource(String str);

    void setOnPlayDataOutputListener(XMediaPlayer.OnPlayDataOutputListener onPlayDataOutputListener);

    void setPreBufferUrl(String str);

    void setSoundTouchAllParams(float f, float f2, float f3);

    void setTempo(float f);

    void setVolume(float f, float f2);

    void start();

    void stop();
}
