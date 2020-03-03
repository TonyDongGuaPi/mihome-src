package com.xiaomi.youpin.youpin_common.widget.video;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataRenderer;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Util;
import com.hannto.printservice.hanntoprintservice.entity.PrinterParmater;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.youpin.common.util.PatternUtils;
import com.xiaomi.youpin.common.util.TitleBarUtil;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.widget.video.receiver.AudioBecomingNoisyReceiver;
import com.xiaomi.youpin.youpin_common.widget.video.receiver.BecomingNoisyListener;
import java.lang.ref.WeakReference;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BaseExoplayerView extends FrameLayout implements AudioManager.OnAudioFocusChangeListener, ExoPlayer.EventListener, MetadataRenderer.Output, BecomingNoisyListener {
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private static final CookieManager DEFAULT_COOKIE_MANAGER = new CookieManager();
    private static final int SHOW_PROGRESS = 1;
    private static final String TAG = "ReactExoplayerView";
    private final AudioBecomingNoisyReceiver audioBecomingNoisyReceiver;
    private final AudioManager audioManager;
    private String audioTrackType;
    private DynamicProxy audioTrackValue;
    private int bufferForPlaybackAfterRebufferMs = 5000;
    private int bufferForPlaybackMs = 2500;
    Context context;
    private boolean disableFocus;
    private ExoPlayerView exoPlayerView;
    private String extension;
    private boolean isBuffering;
    private boolean isFullscreen;
    private boolean isInBackground;
    private boolean isMuted = false;
    private boolean isPaused = true;
    private boolean loadVideoStarted;
    private boolean mAutoStart = false;
    FullScreenHelper mFullScreenHelper;
    private int mLastState = -1;
    MainHandler mPlayerHandler = new MainHandler();
    private float mProgressUpdateInterval = 500.0f;
    private Handler mainHandler;
    private int maxBufferMs = 50000;
    private DataSource.Factory mediaDataSourceFactory;
    private int minBufferMs = 15000;
    private boolean playInBackground = false;
    private SimpleExoPlayer player;
    private boolean playerNeedsSource;
    private float rate = 1.0f;
    private boolean repeat;
    private Map<String, String> requestHeaders;
    private long resumePosition;
    private int resumeWindow;
    private Uri srcUri;
    private String textTrackType;
    private DynamicProxy textTrackValue;
    private MappingTrackSelector trackSelector;
    private boolean useTextureView = false;

    /* access modifiers changed from: protected */
    public void callAudioBecomingNoisy() {
    }

    /* access modifiers changed from: protected */
    public void callAudioFocusChanged(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void callBuffering(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void callEnd() {
    }

    /* access modifiers changed from: protected */
    public void callError(String str, Exception exc) {
    }

    /* access modifiers changed from: protected */
    public void callFullscreenDidDismiss() {
    }

    /* access modifiers changed from: protected */
    public void callFullscreenDidPresent() {
    }

    /* access modifiers changed from: protected */
    public void callFullscreenWillDismiss() {
    }

    /* access modifiers changed from: protected */
    public void callFullscreenWillPresent() {
    }

    /* access modifiers changed from: protected */
    public void callIdle() {
    }

    /* access modifiers changed from: protected */
    public void callLoad(double d, double d2, int i, int i2, List list, List list2) {
    }

    /* access modifiers changed from: protected */
    public void callLoadStart() {
    }

    /* access modifiers changed from: protected */
    public void callMuted(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void callOnDrag(String str, long j) {
    }

    /* access modifiers changed from: protected */
    public void callOnPause(boolean z, long j) {
    }

    /* access modifiers changed from: protected */
    public void callPlaybackRateChange(float f) {
    }

    /* access modifiers changed from: protected */
    public void callProgressChanged(double d, double d2, double d3) {
    }

    /* access modifiers changed from: protected */
    public void callReady() {
    }

    /* access modifiers changed from: protected */
    public void callRemoveLifecycleEventListener() {
    }

    /* access modifiers changed from: protected */
    public void callSeek(long j, long j2) {
    }

    /* access modifiers changed from: protected */
    public void callTimedMetadata(Metadata metadata) {
    }

    /* access modifiers changed from: protected */
    public Activity getCurrentActivity() {
        return null;
    }

    public void onLoadingChanged(boolean z) {
    }

    static {
        DEFAULT_COOKIE_MANAGER.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public boolean isMuted() {
        return this.isMuted;
    }

    public static class MainHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<BaseExoplayerView> f23826a;

        private MainHandler(BaseExoplayerView baseExoplayerView) {
            super(Looper.getMainLooper());
            this.f23826a = new WeakReference<>(baseExoplayerView);
        }

        public void handleMessage(Message message) {
            BaseExoplayerView baseExoplayerView;
            if (this.f23826a != null && (baseExoplayerView = (BaseExoplayerView) this.f23826a.get()) != null) {
                baseExoplayerView.handleProgressMsg(message);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void handleProgressMsg(Message message) {
        int i = message.what;
        if (i != 1) {
            if (i == 110) {
                this.mFullScreenHelper.a();
            }
        } else if (this.player != null) {
            if (this.player.getPlaybackState() == 2) {
                if (this.player.getDuration() >= 0) {
                    long currentPosition = this.player.getCurrentPosition();
                    long bufferedPercentage = (((long) this.player.getBufferedPercentage()) * this.player.getDuration()) / 100;
                    LogUtils.d(TAG, "progress:" + currentPosition);
                    handleUIProgress(currentPosition, bufferedPercentage, this.player.getDuration());
                }
            } else if (this.player.getPlaybackState() == 3) {
                long currentPosition2 = this.player.getCurrentPosition();
                LogUtils.d(TAG, "progress:" + currentPosition2);
                handleUIProgress(currentPosition2, (((long) this.player.getBufferedPercentage()) * this.player.getDuration()) / 100, this.player.getDuration());
                if (this.player.getPlayWhenReady()) {
                    this.mPlayerHandler.sendMessageDelayed(this.mPlayerHandler.obtainMessage(1), (long) Math.round(this.mProgressUpdateInterval));
                }
            } else if (this.player.getPlaybackState() == 4) {
                long currentPosition3 = this.player.getCurrentPosition();
                long bufferedPercentage2 = (((long) this.player.getBufferedPercentage()) * this.player.getDuration()) / 100;
                LogUtils.d(TAG, "progress:" + currentPosition3);
                handleUIProgress(currentPosition3, bufferedPercentage2, this.player.getDuration());
            }
        }
    }

    private void handleUIProgress(long j, long j2, long j3) {
        this.mFullScreenHelper.a(j, j2, j3);
        callProgressChanged((double) j, (double) j2, (double) j3);
    }

    public BaseExoplayerView(Context context2) {
        super(context2);
        this.context = context2;
        this.audioManager = (AudioManager) context2.getSystemService("audio");
        this.audioBecomingNoisyReceiver = new AudioBecomingNoisyReceiver(context2);
        this.mFullScreenHelper = new FullScreenHelper(this);
    }

    public void constructView() {
        createViews();
        initializePlayer();
    }

    private void createViews() {
        clearResumePosition();
        this.mediaDataSourceFactory = buildDataSourceFactory(true);
        this.mainHandler = new Handler();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.exoPlayerView = new ExoPlayerView(getContext());
        this.exoPlayerView.setLayoutParams(layoutParams);
        this.mFullScreenHelper.a(this.exoPlayerView);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        initializePlayer();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (!this.isFullscreen) {
            super.onDetachedFromWindow();
            stopPlayback();
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtils.d(TAG, "onConfigurationChanged" + configuration.orientation);
        if (this.mFullScreenHelper != null) {
            this.mFullScreenHelper.a(true, configuration.orientation);
        }
    }

    public void onHostResume() {
        if (!this.playInBackground || !this.isInBackground) {
            setPlayWhenReady(!this.isPaused || this.mAutoStart);
        }
        this.isInBackground = false;
    }

    public void onHostPause() {
        this.isInBackground = true;
        if (!this.playInBackground) {
            setPlayWhenReady(false);
        }
    }

    public void onHostDestroy() {
        stopPlayback();
    }

    public void cleanUpResources() {
        stopPlayback();
    }

    private void initializePlayer() {
        if (this.player == null) {
            this.trackSelector = new DefaultTrackSelector((TrackSelection.Factory) new AdaptiveTrackSelection.Factory());
            this.player = ExoPlayerFactory.newSimpleInstance(getContext(), (RenderersFactory) new DefaultRenderersFactory(this.context), (TrackSelector) this.trackSelector, (LoadControl) new DefaultLoadControl.Builder().setAllocator(new DefaultAllocator(true, 65536)).createDefaultLoadControl());
            this.player.setRepeatMode(0);
            this.player.addListener(this);
            this.player.addMetadataOutput(this);
            this.exoPlayerView.setPlayer(this.player);
            this.audioBecomingNoisyReceiver.setListener(this);
            setPlayWhenReady(!this.isPaused || this.mAutoStart);
            setMutedModifier(this.isMuted);
            this.playerNeedsSource = true;
            this.player.setPlaybackParameters(new PlaybackParameters(this.rate, 1.0f));
        }
        if (this.playerNeedsSource && this.srcUri != null) {
            ArrayList<MediaSource> buildTextSources = buildTextSources();
            MediaSource buildMediaSource = buildMediaSource(this.srcUri, this.extension);
            if (buildTextSources.size() != 0) {
                buildTextSources.add(0, buildMediaSource);
                buildMediaSource = new MergingMediaSource((MediaSource[]) buildTextSources.toArray(new MediaSource[buildTextSources.size()]));
            }
            boolean z = this.resumeWindow != -1;
            if (z) {
                this.player.seekTo(this.resumeWindow, this.resumePosition);
            }
            this.player.prepare(buildMediaSource, !z, false);
            this.playerNeedsSource = false;
            callLoadStart();
            this.loadVideoStarted = true;
        }
        this.mFullScreenHelper.a((Player) this.player);
    }

    private MediaSource buildMediaSource(Uri uri, String str) {
        String str2;
        if (!TextUtils.isEmpty(str)) {
            str2 = "." + str;
        } else {
            str2 = uri.getLastPathSegment();
        }
        int inferContentType = Util.inferContentType(str2);
        switch (inferContentType) {
            case 0:
                return new DashMediaSource(uri, buildDataSourceFactory(false), (DashChunkSource.Factory) new DefaultDashChunkSource.Factory(this.mediaDataSourceFactory), this.mainHandler, (MediaSourceEventListener) null);
            case 1:
                return new SsMediaSource(uri, buildDataSourceFactory(false), (SsChunkSource.Factory) new DefaultSsChunkSource.Factory(this.mediaDataSourceFactory), this.mainHandler, (MediaSourceEventListener) null);
            case 2:
                return new HlsMediaSource(uri, this.mediaDataSourceFactory, this.mainHandler, (MediaSourceEventListener) null);
            case 3:
                return new ExtractorMediaSource(uri, this.mediaDataSourceFactory, new DefaultExtractorsFactory(), this.mainHandler, (ExtractorMediaSource.EventListener) null);
            default:
                throw new IllegalStateException("Unsupported type: " + inferContentType);
        }
    }

    /* access modifiers changed from: protected */
    public ArrayList<MediaSource> buildTextSources() {
        return new ArrayList<>();
    }

    public MediaSource buildTextSource(String str, Uri uri, String str2, String str3) {
        return new SingleSampleMediaSource(uri, this.mediaDataSourceFactory, Format.createTextSampleFormat(str, str2, -1, str3), C.TIME_UNSET);
    }

    private void releasePlayer() {
        if (this.player != null) {
            updateResumePosition();
            this.player.release();
            this.player.removeMetadataOutput(this);
            this.player = null;
            this.trackSelector = null;
        }
        this.mPlayerHandler.removeMessages(1);
        callRemoveLifecycleEventListener();
        this.audioBecomingNoisyReceiver.removeListener();
    }

    private boolean requestAudioFocus() {
        if (!this.disableFocus && this.audioManager.requestAudioFocus(this, 3, 1) != 1) {
            return false;
        }
        return true;
    }

    private void setPlayWhenReady(boolean z) {
        if (this.player != null) {
            if (!z) {
                this.player.setPlayWhenReady(false);
            } else if (requestAudioFocus()) {
                this.player.setPlayWhenReady(true);
            }
        }
    }

    private void startPlayback() {
        if (this.player != null) {
            switch (this.player.getPlaybackState()) {
                case 1:
                case 4:
                    initializePlayer();
                    if (!this.player.getPlayWhenReady()) {
                        setPlayWhenReady(true);
                        break;
                    }
                    break;
                case 2:
                case 3:
                    if (!this.player.getPlayWhenReady()) {
                        setPlayWhenReady(true);
                        break;
                    }
                    break;
            }
        } else {
            initializePlayer();
        }
        if (!this.disableFocus) {
            setKeepScreenOn(true);
        }
    }

    private void pausePlayback() {
        if (this.player != null && this.player.getPlayWhenReady()) {
            setPlayWhenReady(false);
        }
        setKeepScreenOn(false);
    }

    private void stopPlayback() {
        onStopPlayback();
        releasePlayer();
    }

    private void onStopPlayback() {
        setPausedModifier(true);
        this.mFullScreenHelper.b();
        setKeepScreenOn(false);
        this.audioManager.abandonAudioFocus(this);
    }

    private void updateResumePosition() {
        this.resumeWindow = this.player.getCurrentWindowIndex();
        this.resumePosition = this.player.isCurrentWindowSeekable() ? Math.max(0, this.player.getCurrentPosition()) : C.TIME_UNSET;
    }

    private void clearResumePosition() {
        this.resumeWindow = -1;
        this.resumePosition = C.TIME_UNSET;
    }

    private DataSource.Factory buildDataSourceFactory(boolean z) {
        return DataSourceUtil.a(this.context, z ? BANDWIDTH_METER : null, this.requestHeaders);
    }

    public void onAudioFocusChange(int i) {
        if (i == -1) {
            callAudioFocusChanged(false);
        } else if (i == 1) {
            callAudioFocusChanged(true);
        }
        if (this.player == null) {
            return;
        }
        if (i == -3) {
            this.player.setVolume(0.8f);
        } else if (i == 1) {
            this.player.setVolume(1.0f);
        }
    }

    public void onAudioBecomingNoisy() {
        callAudioBecomingNoisy();
    }

    public void onPlayerStateChanged(boolean z, int i) {
        String str;
        if (this.mLastState == -1 || this.mLastState != i || i == 3) {
            this.mLastState = i;
            String str2 = "onStateChanged: playWhenReady=" + z + ", playbackState=";
            switch (i) {
                case 1:
                    str = str2 + PrinterParmater.i;
                    callIdle();
                    break;
                case 2:
                    str = str2 + "buffering";
                    onBuffering(true);
                    startProgressHandler();
                    break;
                case 3:
                    str = str2 + BindingXConstants.b;
                    startProgressHandler();
                    callReady();
                    onBuffering(false);
                    videoLoaded();
                    break;
                case 4:
                    str = str2 + "ended";
                    callEnd();
                    this.mFullScreenHelper.a(0);
                    onStopPlayback();
                    break;
                default:
                    str = str2 + "unknown";
                    break;
            }
            LogUtils.d(TAG, str);
        }
    }

    private void startProgressHandler() {
        this.mPlayerHandler.sendEmptyMessage(1);
    }

    private void videoLoaded() {
        if (this.loadVideoStarted) {
            this.loadVideoStarted = false;
            setSelectedAudioTrack(this.audioTrackType, this.audioTrackValue);
            setSelectedTextTrack(this.textTrackType, this.textTrackValue);
            Format videoFormat = this.player.getVideoFormat();
            callLoad((double) this.player.getDuration(), (double) this.player.getCurrentPosition(), videoFormat != null ? videoFormat.width : 0, videoFormat != null ? videoFormat.height : 0, getAudioTrackInfo(), getTextTrackInfo());
        }
    }

    private List getAudioTrackInfo() {
        ArrayList arrayList = new ArrayList();
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
        int trackRendererIndex = getTrackRendererIndex(1);
        if (currentMappedTrackInfo == null || trackRendererIndex == -1) {
            return arrayList;
        }
        TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(trackRendererIndex);
        for (int i = 0; i < trackGroups.length; i++) {
            Format format = trackGroups.get(i).getFormat(0);
            HashMap hashMap = new HashMap();
            hashMap.put("index", Integer.valueOf(i));
            hashMap.put("title", format.id != null ? format.id : "");
            hashMap.put("type", format.sampleMimeType);
            hashMap.put("language", format.language != null ? format.language : "");
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private List getTextTrackInfo() {
        ArrayList arrayList = new ArrayList();
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
        int trackRendererIndex = getTrackRendererIndex(3);
        if (currentMappedTrackInfo == null || trackRendererIndex == -1) {
            return arrayList;
        }
        TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(trackRendererIndex);
        for (int i = 0; i < trackGroups.length; i++) {
            Format format = trackGroups.get(i).getFormat(0);
            HashMap hashMap = new HashMap();
            hashMap.put("index", Integer.valueOf(i));
            hashMap.put("title", format.id != null ? format.id : "");
            hashMap.put("type", format.sampleMimeType);
            hashMap.put("language", format.language != null ? format.language : "");
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private void onBuffering(boolean z) {
        if (this.isBuffering != z) {
            this.isBuffering = z;
            if (z) {
                callBuffering(true);
            } else {
                callBuffering(false);
            }
        }
    }

    public void onPositionDiscontinuity(int i) {
        LogUtils.d(TAG, "onPositionDiscontinuity:" + i);
        if (this.playerNeedsSource) {
            updateResumePosition();
        }
        if (i == 0 && this.player.getRepeatMode() == 1) {
            callEnd();
        }
    }

    public void onTimelineChanged(Timeline timeline, Object obj, int i) {
        LogUtils.d(TAG, "onTimelineChanged");
    }

    public void onSeekProcessed() {
        LogUtils.d(TAG, "onSeekProcessed");
    }

    public void onShuffleModeEnabledChanged(boolean z) {
        LogUtils.d(TAG, "onShuffleModeEnabledChanged:" + z);
    }

    public void onRepeatModeChanged(int i) {
        LogUtils.d(TAG, "onRepeatModeChanged:" + i);
    }

    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        LogUtils.d(TAG, "onTracksChanged");
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        LogUtils.d(TAG, "onPlaybackParametersChanged");
        callPlaybackRateChange(playbackParameters.speed);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPlayerError(com.google.android.exoplayer2.ExoPlaybackException r7) {
        /*
            r6 = this;
            java.lang.String r0 = "ReactExoplayerView"
            java.lang.String r1 = "onPlayerError"
            com.xiaomi.youpin.log.LogUtils.w((java.lang.String) r0, (java.lang.String) r1)
            int r0 = r7.type
            r1 = 1
            r2 = 0
            if (r0 != r1) goto L_0x0067
            java.lang.Exception r0 = r7.getRendererException()
            boolean r3 = r0 instanceof com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.DecoderInitializationException
            if (r3 == 0) goto L_0x007a
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r0 = (com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.DecoderInitializationException) r0
            java.lang.String r2 = r0.decoderName
            r3 = 0
            if (r2 != 0) goto L_0x0055
            java.lang.Throwable r2 = r0.getCause()
            boolean r2 = r2 instanceof com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException
            if (r2 == 0) goto L_0x002f
            android.content.res.Resources r0 = r6.getResources()
            int r2 = com.xiaomi.youpin.youpin_common.R.string.error_querying_decoders
            java.lang.String r0 = r0.getString(r2)
            goto L_0x0065
        L_0x002f:
            boolean r2 = r0.secureDecoderRequired
            if (r2 == 0) goto L_0x0044
            android.content.res.Resources r2 = r6.getResources()
            int r4 = com.xiaomi.youpin.youpin_common.R.string.error_no_secure_decoder
            java.lang.Object[] r5 = new java.lang.Object[r1]
            java.lang.String r0 = r0.mimeType
            r5[r3] = r0
            java.lang.String r0 = r2.getString(r4, r5)
            goto L_0x0065
        L_0x0044:
            android.content.res.Resources r2 = r6.getResources()
            int r4 = com.xiaomi.youpin.youpin_common.R.string.error_no_decoder
            java.lang.Object[] r5 = new java.lang.Object[r1]
            java.lang.String r0 = r0.mimeType
            r5[r3] = r0
            java.lang.String r0 = r2.getString(r4, r5)
            goto L_0x0065
        L_0x0055:
            android.content.res.Resources r2 = r6.getResources()
            int r4 = com.xiaomi.youpin.youpin_common.R.string.error_instantiating_decoder
            java.lang.Object[] r5 = new java.lang.Object[r1]
            java.lang.String r0 = r0.decoderName
            r5[r3] = r0
            java.lang.String r0 = r2.getString(r4, r5)
        L_0x0065:
            r2 = r0
            goto L_0x007a
        L_0x0067:
            int r0 = r7.type
            if (r0 != 0) goto L_0x007a
            java.io.IOException r0 = r7.getSourceException()
            android.content.res.Resources r2 = r6.getResources()
            int r3 = com.xiaomi.youpin.youpin_common.R.string.unrecognized_media_format
            java.lang.String r2 = r2.getString(r3)
            goto L_0x007b
        L_0x007a:
            r0 = r7
        L_0x007b:
            if (r2 == 0) goto L_0x0080
            r6.callError(r2, r0)
        L_0x0080:
            r6.playerNeedsSource = r1
            boolean r7 = isBehindLiveWindow(r7)
            if (r7 == 0) goto L_0x008f
            r6.clearResumePosition()
            r6.initializePlayer()
            goto L_0x0092
        L_0x008f:
            r6.updateResumePosition()
        L_0x0092:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.youpin_common.widget.video.BaseExoplayerView.onPlayerError(com.google.android.exoplayer2.ExoPlaybackException):void");
    }

    private static boolean isBehindLiveWindow(ExoPlaybackException exoPlaybackException) {
        if (exoPlaybackException.type != 0) {
            return false;
        }
        for (Throwable sourceException = exoPlaybackException.getSourceException(); sourceException != null; sourceException = sourceException.getCause()) {
            if (sourceException instanceof BehindLiveWindowException) {
                return true;
            }
        }
        return false;
    }

    public int getTrackRendererIndex(int i) {
        int rendererCount = this.player.getRendererCount();
        for (int i2 = 0; i2 < rendererCount; i2++) {
            if (this.player.getRendererType(i2) == i) {
                return i2;
            }
        }
        return -1;
    }

    public void onMetadata(Metadata metadata) {
        LogUtils.d(TAG, "onMetadata:" + metadata);
        callTimedMetadata(metadata);
    }

    public void setSrc(Uri uri, String str, Map<String, String> map) {
        LogUtils.d(TAG, "setSrc:" + uri);
        if (uri != null) {
            boolean z = this.srcUri == null;
            boolean equals = uri.equals(this.srcUri);
            this.srcUri = uri;
            this.extension = str;
            this.requestHeaders = map;
            this.mediaDataSourceFactory = DataSourceUtil.a(this.context, BANDWIDTH_METER, this.requestHeaders);
            if (!z && !equals) {
                reloadSource();
            }
        }
    }

    public void setProgressUpdateInterval(float f) {
        LogUtils.d(TAG, "setProgressUpdateInterval:" + f);
        this.mProgressUpdateInterval = f;
    }

    public void setRawSrc(Uri uri, String str) {
        LogUtils.d(TAG, "setRawSrc");
        if (uri != null) {
            boolean z = this.srcUri == null;
            boolean equals = uri.equals(this.srcUri);
            this.srcUri = uri;
            this.extension = str;
            this.mediaDataSourceFactory = DataSourceUtil.b(this.context);
            if (!z && !equals) {
                reloadSource();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void reloadSource() {
        LogUtils.d(TAG, "reloadSource");
        this.playerNeedsSource = true;
        initializePlayer();
    }

    public void setResizeModeModifier(int i) {
        LogUtils.d(TAG, "setResizeModeModifier:" + i);
        this.exoPlayerView.setResizeMode(i);
    }

    public void setRepeatModifier(boolean z) {
        LogUtils.d(TAG, "setRepeatModifier:" + z);
        if (this.player != null) {
            if (z) {
                this.player.setRepeatMode(1);
            } else {
                this.player.setRepeatMode(0);
            }
        }
        this.repeat = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00ec  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSelectedTrack(int r7, java.lang.String r8, com.xiaomi.youpin.youpin_common.widget.video.DynamicProxy r9) {
        /*
            r6 = this;
            java.lang.String r0 = "ReactExoplayerView"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "setSelectedTrack:"
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r0, (java.lang.String) r1)
            int r7 = r6.getTrackRendererIndex(r7)
            r0 = -1
            if (r7 != r0) goto L_0x001e
            return
        L_0x001e:
            com.google.android.exoplayer2.trackselection.MappingTrackSelector r1 = r6.trackSelector
            com.google.android.exoplayer2.trackselection.MappingTrackSelector$MappedTrackInfo r1 = r1.getCurrentMappedTrackInfo()
            if (r1 != 0) goto L_0x0027
            return
        L_0x0027:
            com.google.android.exoplayer2.source.TrackGroupArray r1 = r1.getTrackGroups(r7)
            boolean r2 = android.text.TextUtils.isEmpty(r8)
            if (r2 == 0) goto L_0x0033
            java.lang.String r8 = "default"
        L_0x0033:
            java.lang.String r2 = "disabled"
            boolean r2 = r8.equals(r2)
            r3 = 0
            if (r2 == 0) goto L_0x0044
            com.google.android.exoplayer2.trackselection.MappingTrackSelector r8 = r6.trackSelector
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector r8 = (com.google.android.exoplayer2.trackselection.DefaultTrackSelector) r8
            r8.setSelectionOverride(r7, r1, r3)
            return
        L_0x0044:
            java.lang.String r2 = "language"
            boolean r2 = r8.equals(r2)
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0070
            r8 = 0
        L_0x004f:
            int r2 = r1.length
            if (r8 >= r2) goto L_0x00e1
            com.google.android.exoplayer2.source.TrackGroup r2 = r1.get(r8)
            com.google.android.exoplayer2.Format r2 = r2.getFormat(r5)
            java.lang.String r3 = r2.language
            if (r3 == 0) goto L_0x006d
            java.lang.String r2 = r2.language
            java.lang.String r3 = r9.asString()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x006d
            goto L_0x00e2
        L_0x006d:
            int r8 = r8 + 1
            goto L_0x004f
        L_0x0070:
            java.lang.String r2 = "title"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x0099
            r8 = 0
        L_0x0079:
            int r2 = r1.length
            if (r8 >= r2) goto L_0x00e1
            com.google.android.exoplayer2.source.TrackGroup r2 = r1.get(r8)
            com.google.android.exoplayer2.Format r2 = r2.getFormat(r5)
            java.lang.String r3 = r2.id
            if (r3 == 0) goto L_0x0096
            java.lang.String r2 = r2.id
            java.lang.String r3 = r9.asString()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0096
            goto L_0x00e2
        L_0x0096:
            int r8 = r8 + 1
            goto L_0x0079
        L_0x0099:
            java.lang.String r2 = "index"
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x00ae
            int r8 = r9.asInt()
            int r2 = r1.length
            if (r8 >= r2) goto L_0x00e1
            int r8 = r9.asInt()
            goto L_0x00e2
        L_0x00ae:
            r8 = 3
            if (r7 != r8) goto L_0x00da
            int r8 = android.os.Build.VERSION.SDK_INT
            r9 = 18
            if (r8 <= r9) goto L_0x00d2
            int r8 = r1.length
            if (r8 <= 0) goto L_0x00d2
            android.content.Context r8 = r6.context
            java.lang.String r9 = "captioning"
            java.lang.Object r8 = r8.getSystemService(r9)
            android.view.accessibility.CaptioningManager r8 = (android.view.accessibility.CaptioningManager) r8
            if (r8 == 0) goto L_0x00e1
            boolean r8 = r8.isEnabled()
            if (r8 == 0) goto L_0x00e1
            int r8 = r6.getTrackIndexForDefaultLocale(r1)
            goto L_0x00e2
        L_0x00d2:
            com.google.android.exoplayer2.trackselection.MappingTrackSelector r8 = r6.trackSelector
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector r8 = (com.google.android.exoplayer2.trackselection.DefaultTrackSelector) r8
            r8.setSelectionOverride(r7, r1, r3)
            return
        L_0x00da:
            if (r7 != r4) goto L_0x00e1
            int r8 = r6.getTrackIndexForDefaultLocale(r1)
            goto L_0x00e2
        L_0x00e1:
            r8 = -1
        L_0x00e2:
            if (r8 != r0) goto L_0x00ec
            com.google.android.exoplayer2.trackselection.MappingTrackSelector r8 = r6.trackSelector
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector r8 = (com.google.android.exoplayer2.trackselection.DefaultTrackSelector) r8
            r8.clearSelectionOverrides(r7)
            return
        L_0x00ec:
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector$SelectionOverride r9 = new com.google.android.exoplayer2.trackselection.DefaultTrackSelector$SelectionOverride
            int[] r0 = new int[r4]
            r0[r5] = r5
            r9.<init>(r8, r0)
            com.google.android.exoplayer2.trackselection.MappingTrackSelector r8 = r6.trackSelector
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector r8 = (com.google.android.exoplayer2.trackselection.DefaultTrackSelector) r8
            r8.setSelectionOverride(r7, r1, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.youpin_common.widget.video.BaseExoplayerView.setSelectedTrack(int, java.lang.String, com.xiaomi.youpin.youpin_common.widget.video.DynamicProxy):void");
    }

    private int getTrackIndexForDefaultLocale(TrackGroupArray trackGroupArray) {
        if (trackGroupArray.length == 0) {
            return -1;
        }
        String language = Locale.getDefault().getLanguage();
        String iSO3Language = Locale.getDefault().getISO3Language();
        for (int i = 0; i < trackGroupArray.length; i++) {
            String str = trackGroupArray.get(i).getFormat(0).language;
            if (str != null && (str.equals(language) || str.equals(iSO3Language))) {
                return i;
            }
        }
        return 0;
    }

    public void setSelectedAudioTrack(String str, DynamicProxy dynamicProxy) {
        LogUtils.d(TAG, "setSelectedAudioTrack:" + str);
        this.audioTrackType = str;
        this.audioTrackValue = dynamicProxy;
        setSelectedTrack(1, this.audioTrackType, this.audioTrackValue);
    }

    public void setSelectedTextTrack(String str, DynamicProxy dynamicProxy) {
        LogUtils.d(TAG, "setSelectedTextTrack:" + str);
        this.textTrackType = str;
        this.textTrackValue = dynamicProxy;
        setSelectedTrack(3, this.textTrackType, this.textTrackValue);
    }

    public void setPausedModifier(boolean z) {
        LogUtils.d(TAG, "setPausedModifier:" + z);
        if (this.isPaused == z) {
            LogUtils.w(TAG, "pause no change");
            return;
        }
        this.isPaused = z;
        this.mFullScreenHelper.b();
        if (this.player != null) {
            if (!z) {
                startPlayback();
            } else {
                pausePlayback();
            }
            callOnPause(this.isPaused, this.player.getCurrentPosition());
        }
    }

    public void setMutedModifier(boolean z) {
        LogUtils.d(TAG, "setMutedModifier:" + z);
        if (this.player != null) {
            this.isMuted = z;
            this.player.setVolume(z ? 0.0f : 1.0f);
            callMuted(z);
        }
    }

    public void setVolumeModifier(float f) {
        LogUtils.d(TAG, "setVolumeModifier:" + f);
        if (this.player != null) {
            this.player.setVolume(f);
            callMuted(f == 0.0f);
        }
    }

    public void seekTo(long j) {
        LogUtils.d(TAG, "seekTo:" + j);
        if (this.player != null) {
            callSeek(this.player.getCurrentPosition(), j);
            this.player.seekTo(j);
        }
    }

    public void setRateModifier(float f) {
        LogUtils.d(TAG, "setRateModifier:" + f);
        this.rate = f;
        if (this.player != null) {
            this.player.setPlaybackParameters(new PlaybackParameters(this.rate, 1.0f));
        }
    }

    public void setPlayInBackground(boolean z) {
        LogUtils.d(TAG, "setPlayInBackground:" + z);
        this.playInBackground = z;
    }

    public void setDisableFocus(boolean z) {
        LogUtils.d(TAG, "setDisableFocus:" + z);
        this.disableFocus = z;
    }

    public void setFullscreen(boolean z) {
        LogUtils.d(TAG, "setFullscreen:" + z);
        if (z != this.isFullscreen) {
            this.isFullscreen = z;
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                Window window = currentActivity.getWindow();
                View decorView = window.getDecorView();
                if (this.isFullscreen) {
                    currentActivity.setRequestedOrientation(4);
                    int i = Util.SDK_INT >= 19 ? 4102 : 6;
                    callFullscreenWillPresent();
                    this.mFullScreenHelper.a(decorView);
                    this.mFullScreenHelper.b();
                    decorView.setSystemUiVisibility(i);
                    callFullscreenDidPresent();
                    return;
                }
                currentActivity.setRequestedOrientation(1);
                callFullscreenWillDismiss();
                this.mFullScreenHelper.b(decorView);
                this.mFullScreenHelper.b();
                decorView.setSystemUiVisibility(0);
                TitleBarUtil.a(window);
                callFullscreenDidDismiss();
            }
        }
    }

    public void setUseTextureView(boolean z) {
        LogUtils.d(TAG, "setUseTextureView:" + z);
        this.exoPlayerView.setUseTextureView(z);
    }

    public void setBufferConfig(int i, int i2, int i3, int i4) {
        LogUtils.d(TAG, "setBufferConfig:" + i);
        this.minBufferMs = i;
        this.maxBufferMs = i2;
        this.bufferForPlaybackMs = i3;
        this.bufferForPlaybackAfterRebufferMs = i4;
        releasePlayer();
        initializePlayer();
    }

    public void setControls(boolean z) {
        LogUtils.d(TAG, "setControls:" + z);
        this.mFullScreenHelper.a(z);
    }

    public void setAutoStart(boolean z) {
        LogUtils.d(TAG, "setAutoStart:" + z);
        this.mAutoStart = z;
        if (this.player != null && this.player.getPlayWhenReady() != z) {
            setPlayWhenReady(z);
        }
    }

    public boolean resetVideoIfNeed() {
        if (this.player == null || this.player.getPlaybackState() != 4) {
            return false;
        }
        seekTo(0);
        return true;
    }

    public void setDrag(boolean z) {
        LogUtils.d(TAG, "setDrag:" + z);
        this.mFullScreenHelper.b(z);
    }

    public void setBackgroundColor(String str) {
        LogUtils.d(TAG, "setBackgroundColor:" + str);
        String trim = str.trim();
        try {
            if (PatternUtils.a(trim)) {
                this.exoPlayerView.setBackgroundColor(Color.parseColor(trim));
                this.exoPlayerView.setShutterBackgroundColor(Color.parseColor(trim));
            } else if (PatternUtils.b(trim)) {
                String[] split = trim.substring(trim.indexOf(Operators.BRACKET_START_STR) + 1, trim.length() - 1).split(",");
                if (split.length == 3) {
                    int rgb = Color.rgb(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                    this.exoPlayerView.setBackgroundColor(rgb);
                    this.exoPlayerView.setShutterBackgroundColor(rgb);
                }
            } else if (PatternUtils.c(trim)) {
                String[] split2 = trim.substring(trim.indexOf(Operators.BRACKET_START_STR) + 1, trim.length() - 1).split(",");
                if (split2.length == 4) {
                    int argb = Color.argb((int) (Float.parseFloat(split2[3]) * 255.0f), Integer.parseInt(split2[0]), Integer.parseInt(split2[1]), Integer.parseInt(split2[2]));
                    this.exoPlayerView.setBackgroundColor(argb);
                    this.exoPlayerView.setShutterBackgroundColor(argb);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
