package com.xiaomi.smarthome.framework.plugin.mpk;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.xiaomi.smarthome.camera.exopackage.MJExoPlayer;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.exopackage.MJExoPlayerViewP;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.util.HashMap;
import java.util.Map;

public class MJExoPlayerImpl implements MJExoPlayer {
    public static final String SCHEME_HTTP = "http";
    public static final String SCHEME_HTTPS = "https";
    private static final String TAG = "MJExoPlayerImpl";
    private Context context;
    private MJExoPlayerViewP mjExoPlayerView;
    /* access modifiers changed from: private */
    public MJExoPlayer.MJExoPlayerListener playerListener;
    private SimpleExoPlayer simpleExoPlayer;

    public interface ExoPlayerBridgeListener {
        void onRenderedFirstFrame();

        void onVideoSizeChanged(int i, int i2, int i3, float f);
    }

    public MJExoPlayerImpl(Context context2, ViewGroup viewGroup, AttributeSet attributeSet, int i) {
        this.mjExoPlayerView = new MJExoPlayerViewP(context2, attributeSet, i);
        this.context = context2;
        this.simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context2, (TrackSelector) new DefaultTrackSelector((TrackSelection.Factory) new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())));
        this.mjExoPlayerView.setPlayer(this.simpleExoPlayer);
        if (this.simpleExoPlayer != null) {
            this.simpleExoPlayer.addListener(new Player.EventListener() {
                public void onTimelineChanged(Timeline timeline, Object obj, int i) {
                    MJExoPlayer.MJExoPlayerListener unused = MJExoPlayerImpl.this.playerListener;
                    LogUtil.a(MJExoPlayerImpl.TAG, "onTimelineChanged:" + i);
                }

                public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
                    LogUtil.a(MJExoPlayerImpl.TAG, "onTracksChanged");
                }

                public void onLoadingChanged(boolean z) {
                    if (MJExoPlayerImpl.this.playerListener != null) {
                        MJExoPlayerImpl.this.playerListener.onLoadingChanged(z);
                    }
                    LogUtil.a(MJExoPlayerImpl.TAG, "onLoadingChanged:" + z);
                }

                public void onPlayerStateChanged(boolean z, int i) {
                    if (MJExoPlayerImpl.this.playerListener != null) {
                        MJExoPlayerImpl.this.playerListener.onPlayerStateChanged(z, i);
                    }
                    LogUtil.a(MJExoPlayerImpl.TAG, "onPlayerStateChanged:" + i);
                }

                public void onRepeatModeChanged(int i) {
                    if (MJExoPlayerImpl.this.playerListener != null) {
                        MJExoPlayerImpl.this.playerListener.onRepeatModeChanged(i);
                    }
                    LogUtil.a(MJExoPlayerImpl.TAG, "onRepeatModeChanged:" + i);
                }

                public void onShuffleModeEnabledChanged(boolean z) {
                    if (MJExoPlayerImpl.this.playerListener != null) {
                        MJExoPlayerImpl.this.playerListener.onShuffleModeEnabledChanged(z);
                    }
                    LogUtil.a(MJExoPlayerImpl.TAG, "onShuffleModeEnabledChanged:" + z);
                }

                public void onPlayerError(ExoPlaybackException exoPlaybackException) {
                    LogUtil.a(MJExoPlayerImpl.TAG, "onPlayerError:" + exoPlaybackException);
                    if (MJExoPlayerImpl.this.playerListener != null) {
                        MJExoPlayerImpl.this.playerListener.onPlayerError(exoPlaybackException.type);
                    }
                }

                public void onPositionDiscontinuity(int i) {
                    LogUtil.a(MJExoPlayerImpl.TAG, "onPositionDiscontinuity:" + i);
                    if (MJExoPlayerImpl.this.playerListener != null) {
                        MJExoPlayerImpl.this.playerListener.onPositionDiscontinuity(i);
                    }
                }

                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                    LogUtil.a(MJExoPlayerImpl.TAG, "onPlaybackParametersChanged:" + playbackParameters.speed);
                    if (MJExoPlayerImpl.this.playerListener != null && playbackParameters != null) {
                        MJExoPlayerImpl.this.playerListener.onPlaybackParametersChanged(playbackParameters.speed);
                    }
                }

                public void onSeekProcessed() {
                    LogUtil.a(MJExoPlayerImpl.TAG, "onSeekProcessed");
                    if (MJExoPlayerImpl.this.playerListener != null) {
                        MJExoPlayerImpl.this.playerListener.onSeekProcessed();
                    }
                }
            });
        } else {
            LogUtil.b(TAG, "simpleExoPlayer null");
        }
        if (this.mjExoPlayerView != null && (viewGroup instanceof FrameLayout)) {
            viewGroup.addView(this.mjExoPlayerView, -1, -1);
        }
        if (!(this.mjExoPlayerView == null || this.mjExoPlayerView.getVideoSurfaceView() == null)) {
            this.mjExoPlayerView.getVideoSurfaceView().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MJExoPlayerImpl.this.playerListener != null) {
                        MJExoPlayerImpl.this.playerListener.onVideoSurfaceViewClicked(view);
                    }
                }
            });
            this.mjExoPlayerView.getVideoSurfaceView().setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (MJExoPlayerImpl.this.playerListener == null) {
                        return false;
                    }
                    MJExoPlayerImpl.this.playerListener.onVideoSurfaceViewLongClicked(view);
                    return true;
                }
            });
        }
        if (this.mjExoPlayerView != null) {
            this.mjExoPlayerView.setExoPlayerBridgeListener(new ExoPlayerBridgeListener() {
                public void onVideoSizeChanged(int i, int i2, int i3, float f) {
                    if (MJExoPlayerImpl.this.playerListener != null) {
                        MJExoPlayerImpl.this.playerListener.onVideoSizeChanged(i, i2, i3, f);
                    }
                }

                public void onRenderedFirstFrame() {
                    if (MJExoPlayerImpl.this.playerListener != null) {
                        MJExoPlayerImpl.this.playerListener.onRenderedFirstFrame();
                    }
                }
            });
        }
    }

    private MediaSource buildMediaSource(Context context2, String str, Map<String, String> map) {
        if (context2 != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    Uri parse = Uri.parse(str);
                    if (!TextUtils.isEmpty(parse.getScheme()) && (parse.getScheme().equals("http") || parse.getScheme().equals("https"))) {
                        return buildMediaSourceHTTP(context2, parse, map);
                    }
                    if (parse == null || !parse.getPath().endsWith(".m3u8")) {
                        return buildMediaSourceLocal(context2, parse);
                    }
                    return buildMediaSourceLocalM3U8(parse);
                }
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    private HlsMediaSource buildMediaSourceHTTP(Context context2, Uri uri, Map<String, String> map) {
        DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory(Util.getUserAgent(context2, "SmartHome;Android"), new DefaultBandwidthMeter());
        if (map != null && map.size() > 0) {
            for (Map.Entry next : map.entrySet()) {
                defaultHttpDataSourceFactory.setDefaultRequestProperty("" + ((String) next.getKey()), "" + ((String) next.getValue()));
            }
        }
        return new HlsMediaSource.Factory((DataSource.Factory) defaultHttpDataSourceFactory).createMediaSource(uri);
    }

    private HlsMediaSource buildMediaSourceLocalM3U8(Uri uri) {
        return new HlsMediaSource.Factory((DataSource.Factory) new FileDataSourceFactory()).createMediaSource(uri);
    }

    private MediaSource buildMediaSourceLocal(Context context2, Uri uri) {
        return new ExtractorMediaSource(uri, new DefaultDataSourceFactory(context2, "SmartHome;Android"), new DefaultExtractorsFactory(), (Handler) null, (ExtractorMediaSource.EventListener) null);
    }

    public void setPlayerListener(MJExoPlayer.MJExoPlayerListener mJExoPlayerListener) {
        this.playerListener = mJExoPlayerListener;
    }

    public MJExoPlayer.MJExoPlayerListener getPlayerListener() {
        return this.playerListener;
    }

    public void startPlay(String str) {
        HashMap hashMap = new HashMap();
        MiServiceTokenInfo tokenInfo = CloudVideoNetUtils.getInstance().getTokenInfo();
        if (tokenInfo != null && this.mjExoPlayerView != null && this.simpleExoPlayer != null) {
            hashMap.put("Cookie", "yetAnotherServiceToken=" + tokenInfo.c);
            this.simpleExoPlayer.prepare(buildMediaSource(this.context, str, hashMap));
            this.simpleExoPlayer.setPlayWhenReady(true);
        }
    }

    public void pausePlay() {
        if (this.simpleExoPlayer != null) {
            this.simpleExoPlayer.setPlayWhenReady(false);
        }
    }

    public void stopPlay() {
        if (this.simpleExoPlayer != null) {
            this.simpleExoPlayer.stop();
        }
    }

    public void resumePlay() {
        if (this.simpleExoPlayer != null) {
            this.simpleExoPlayer.setPlayWhenReady(true);
        }
    }

    public void seekTo(long j) {
        if (this.simpleExoPlayer != null) {
            this.simpleExoPlayer.seekTo(j);
        }
    }

    public int getBufferedPercentage() {
        if (this.simpleExoPlayer != null) {
            return this.simpleExoPlayer.getBufferedPercentage();
        }
        return 0;
    }

    public long getBufferedPosition() {
        if (this.simpleExoPlayer != null) {
            return this.simpleExoPlayer.getBufferedPosition();
        }
        return 0;
    }

    public long getCurrentPosition() {
        if (this.simpleExoPlayer != null) {
            return this.simpleExoPlayer.getCurrentPosition();
        }
        return 0;
    }

    public long getDuration() {
        if (this.simpleExoPlayer != null) {
            return this.simpleExoPlayer.getDuration();
        }
        return 0;
    }

    public boolean getPlayWhenReady() {
        if (this.simpleExoPlayer != null) {
            return this.simpleExoPlayer.getPlayWhenReady();
        }
        return false;
    }

    public int getVideoScalingMode() {
        if (this.simpleExoPlayer != null) {
            return this.simpleExoPlayer.getVideoScalingMode();
        }
        return 0;
    }

    public float getVolume() {
        if (this.simpleExoPlayer != null) {
            return this.simpleExoPlayer.getVolume();
        }
        return 0.0f;
    }

    public void setVolume(float f) {
        if (this.simpleExoPlayer != null) {
            this.simpleExoPlayer.setVolume(f);
        }
    }

    public boolean isLoading() {
        if (this.simpleExoPlayer != null) {
            return this.simpleExoPlayer.isLoading();
        }
        return false;
    }

    public void setPlayWhenReady(boolean z) {
        if (this.simpleExoPlayer != null) {
            this.simpleExoPlayer.setPlayWhenReady(z);
        }
    }

    public int getPlaybackState() {
        if (this.simpleExoPlayer != null) {
            return this.simpleExoPlayer.getPlaybackState();
        }
        return 0;
    }

    public float getPlaybackSpeed() {
        if (this.simpleExoPlayer == null || this.simpleExoPlayer.getPlaybackParameters() == null) {
            return 0.0f;
        }
        return this.simpleExoPlayer.getPlaybackParameters().speed;
    }

    public void setPlaybackSpeed(float f) {
        if (this.simpleExoPlayer != null && f > 0.0f) {
            this.simpleExoPlayer.setPlaybackParameters(new PlaybackParameters(f, 1.0f));
        }
    }

    public Bitmap snapshot() {
        if (this.mjExoPlayerView == null || this.mjExoPlayerView.getVideoSurfaceView() == null || !(this.mjExoPlayerView.getVideoSurfaceView() instanceof TextureView)) {
            return null;
        }
        return ((TextureView) this.mjExoPlayerView.getVideoSurfaceView()).getBitmap();
    }
}
