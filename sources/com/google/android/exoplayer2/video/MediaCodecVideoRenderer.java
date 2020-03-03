package com.google.android.exoplayer2.video;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Surface;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.mediacodec.MediaFormatUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.tiqiaa.constant.KeyType;
import java.nio.ByteBuffer;
import java.util.List;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

@TargetApi(16)
public class MediaCodecVideoRenderer extends MediaCodecRenderer {
    private static final float INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR = 1.5f;
    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final int MAX_PENDING_OUTPUT_STREAM_OFFSET_COUNT = 10;
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {1920, 1600, 1440, 1280, 960, KeyType.CONTINUE_UP, 640, 540, 480};
    private static final String TAG = "MediaCodecVideoRenderer";
    private static boolean deviceNeedsSetOutputSurfaceWorkaround;
    private static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    private final long allowedJoiningTimeMs;
    private int buffersInCodecCount;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private int currentHeight;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private final boolean deviceNeedsNoPostProcessWorkaround;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private Surface dummySurface;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    @Nullable
    private VideoFrameMetadataListener frameMetadataListener;
    private final VideoFrameReleaseTimeHelper frameReleaseTimeHelper;
    private long initialPositionUs;
    private long joiningDeadlineMs;
    private long lastInputTimeUs;
    private long lastRenderTimeUs;
    private final int maxDroppedFramesToNotify;
    private long outputStreamOffsetUs;
    private int pendingOutputStreamOffsetCount;
    private final long[] pendingOutputStreamOffsetsUs;
    private final long[] pendingOutputStreamSwitchTimesUs;
    private float pendingPixelWidthHeightRatio;
    private int pendingRotationDegrees;
    private boolean renderedFirstFrame;
    private int reportedHeight;
    private float reportedPixelWidthHeightRatio;
    private int reportedUnappliedRotationDegrees;
    private int reportedWidth;
    private int scalingMode;
    private Surface surface;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;

    private static boolean isBufferLate(long j) {
        return j < -30000;
    }

    private static boolean isBufferVeryLate(long j) {
        return j < -500000;
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector) {
        this(context2, mediaCodecSelector, 0);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j) {
        this(context2, mediaCodecSelector, j, (Handler) null, (VideoRendererEventListener) null, -1);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i) {
        this(context2, mediaCodecSelector, j, (DrmSessionManager<FrameworkMediaCrypto>) null, false, handler, videoRendererEventListener, i);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i) {
        super(2, mediaCodecSelector, drmSessionManager, z, 30.0f);
        this.allowedJoiningTimeMs = j;
        this.maxDroppedFramesToNotify = i;
        this.context = context2.getApplicationContext();
        this.frameReleaseTimeHelper = new VideoFrameReleaseTimeHelper(this.context);
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.deviceNeedsNoPostProcessWorkaround = deviceNeedsNoPostProcessWorkaround();
        this.pendingOutputStreamOffsetsUs = new long[10];
        this.pendingOutputStreamSwitchTimesUs = new long[10];
        this.outputStreamOffsetUs = C.TIME_UNSET;
        this.lastInputTimeUs = C.TIME_UNSET;
        this.joiningDeadlineMs = C.TIME_UNSET;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.scalingMode = 1;
        clearReportedVideoSize();
    }

    /* access modifiers changed from: protected */
    public int supportsFormat(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Format format) throws MediaCodecUtil.DecoderQueryException {
        boolean z;
        int i = 0;
        if (!MimeTypes.isVideo(format.sampleMimeType)) {
            return 0;
        }
        DrmInitData drmInitData = format.drmInitData;
        if (drmInitData != null) {
            z = false;
            for (int i2 = 0; i2 < drmInitData.schemeDataCount; i2++) {
                z |= drmInitData.get(i2).requiresSecureDecryption;
            }
        } else {
            z = false;
        }
        List<MediaCodecInfo> decoderInfos = mediaCodecSelector.getDecoderInfos(format.sampleMimeType, z);
        if (decoderInfos.isEmpty()) {
            if (!z || mediaCodecSelector.getDecoderInfos(format.sampleMimeType, false).isEmpty()) {
                return 1;
            }
            return 2;
        } else if (!supportsFormatDrm(drmSessionManager, drmInitData)) {
            return 2;
        } else {
            MediaCodecInfo mediaCodecInfo = decoderInfos.get(0);
            boolean isFormatSupported = mediaCodecInfo.isFormatSupported(format);
            int i3 = mediaCodecInfo.isSeamlessAdaptationSupported(format) ? 16 : 8;
            if (mediaCodecInfo.tunneling) {
                i = 32;
            }
            return (isFormatSupported ? 4 : 3) | i3 | i;
        }
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z) throws ExoPlaybackException {
        super.onEnabled(z);
        this.tunnelingAudioSessionId = getConfiguration().tunnelingAudioSessionId;
        this.tunneling = this.tunnelingAudioSessionId != 0;
        this.eventDispatcher.enabled(this.decoderCounters);
        this.frameReleaseTimeHelper.enable();
    }

    /* access modifiers changed from: protected */
    public void onStreamChanged(Format[] formatArr, long j) throws ExoPlaybackException {
        if (this.outputStreamOffsetUs == C.TIME_UNSET) {
            this.outputStreamOffsetUs = j;
        } else {
            if (this.pendingOutputStreamOffsetCount == this.pendingOutputStreamOffsetsUs.length) {
                Log.w(TAG, "Too many stream changes, so dropping offset: " + this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1]);
            } else {
                this.pendingOutputStreamOffsetCount++;
            }
            this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1] = j;
            this.pendingOutputStreamSwitchTimesUs[this.pendingOutputStreamOffsetCount - 1] = this.lastInputTimeUs;
        }
        super.onStreamChanged(formatArr, j);
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        super.onPositionReset(j, z);
        clearRenderedFirstFrame();
        this.initialPositionUs = C.TIME_UNSET;
        this.consecutiveDroppedFrameCount = 0;
        this.lastInputTimeUs = C.TIME_UNSET;
        if (this.pendingOutputStreamOffsetCount != 0) {
            this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1];
            this.pendingOutputStreamOffsetCount = 0;
        }
        if (z) {
            setJoiningDeadlineMs();
        } else {
            this.joiningDeadlineMs = C.TIME_UNSET;
        }
    }

    public boolean isReady() {
        if (super.isReady() && (this.renderedFirstFrame || ((this.dummySurface != null && this.surface == this.dummySurface) || getCodec() == null || this.tunneling))) {
            this.joiningDeadlineMs = C.TIME_UNSET;
            return true;
        } else if (this.joiningDeadlineMs == C.TIME_UNSET) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.joiningDeadlineMs) {
                return true;
            }
            this.joiningDeadlineMs = C.TIME_UNSET;
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
        this.joiningDeadlineMs = C.TIME_UNSET;
        maybeNotifyDroppedFrames();
        super.onStopped();
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.outputStreamOffsetUs = C.TIME_UNSET;
        this.lastInputTimeUs = C.TIME_UNSET;
        this.pendingOutputStreamOffsetCount = 0;
        clearReportedVideoSize();
        clearRenderedFirstFrame();
        this.frameReleaseTimeHelper.disable();
        this.tunnelingOnFrameRenderedListener = null;
        this.tunneling = false;
        try {
            super.onDisabled();
        } finally {
            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    public void handleMessage(int i, @Nullable Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setSurface((Surface) obj);
        } else if (i == 4) {
            this.scalingMode = ((Integer) obj).intValue();
            MediaCodec codec = getCodec();
            if (codec != null) {
                codec.setVideoScalingMode(this.scalingMode);
            }
        } else if (i == 6) {
            this.frameMetadataListener = (VideoFrameMetadataListener) obj;
        } else {
            super.handleMessage(i, obj);
        }
    }

    private void setSurface(Surface surface2) throws ExoPlaybackException {
        if (surface2 == null) {
            if (this.dummySurface != null) {
                surface2 = this.dummySurface;
            } else {
                MediaCodecInfo codecInfo = getCodecInfo();
                if (codecInfo != null && shouldUseDummySurface(codecInfo)) {
                    this.dummySurface = DummySurface.newInstanceV17(this.context, codecInfo.secure);
                    surface2 = this.dummySurface;
                }
            }
        }
        if (this.surface != surface2) {
            this.surface = surface2;
            int state = getState();
            if (state == 1 || state == 2) {
                MediaCodec codec = getCodec();
                if (Util.SDK_INT < 23 || codec == null || surface2 == null || this.codecNeedsSetOutputSurfaceWorkaround) {
                    releaseCodec();
                    maybeInitCodec();
                } else {
                    setOutputSurfaceV23(codec, surface2);
                }
            }
            if (surface2 == null || surface2 == this.dummySurface) {
                clearReportedVideoSize();
                clearRenderedFirstFrame();
                return;
            }
            maybeRenotifyVideoSizeChanged();
            clearRenderedFirstFrame();
            if (state == 2) {
                setJoiningDeadlineMs();
            }
        } else if (surface2 != null && surface2 != this.dummySurface) {
            maybeRenotifyVideoSizeChanged();
            maybeRenotifyRenderedFirstFrame();
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return this.surface != null || shouldUseDummySurface(mediaCodecInfo);
    }

    /* access modifiers changed from: protected */
    public boolean getCodecNeedsEosPropagation() {
        return this.tunneling;
    }

    /* access modifiers changed from: protected */
    public void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto, float f) throws MediaCodecUtil.DecoderQueryException {
        this.codecMaxValues = getCodecMaxValues(mediaCodecInfo, format, getStreamFormats());
        MediaFormat mediaFormat = getMediaFormat(format, this.codecMaxValues, f, this.deviceNeedsNoPostProcessWorkaround, this.tunnelingAudioSessionId);
        if (this.surface == null) {
            Assertions.checkState(shouldUseDummySurface(mediaCodecInfo));
            if (this.dummySurface == null) {
                this.dummySurface = DummySurface.newInstanceV17(this.context, mediaCodecInfo.secure);
            }
            this.surface = this.dummySurface;
        }
        mediaCodec.configure(mediaFormat, this.surface, mediaCrypto, 0);
        if (Util.SDK_INT >= 23 && this.tunneling) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(mediaCodec);
        }
    }

    /* access modifiers changed from: protected */
    public int canKeepCodec(MediaCodec mediaCodec, MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        if (!mediaCodecInfo.isSeamlessAdaptationSupported(format, format2, true) || format2.width > this.codecMaxValues.width || format2.height > this.codecMaxValues.height || getMaxInputSize(mediaCodecInfo, format2) > this.codecMaxValues.inputSize) {
            return 0;
        }
        if (format.initializationDataEquals(format2)) {
            return 1;
        }
        return 3;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void releaseCodec() {
        try {
            super.releaseCodec();
        } finally {
            this.buffersInCodecCount = 0;
            if (this.dummySurface != null) {
                if (this.surface == this.dummySurface) {
                    this.surface = null;
                }
                this.dummySurface.release();
                this.dummySurface = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void flushCodec() throws ExoPlaybackException {
        super.flushCodec();
        this.buffersInCodecCount = 0;
    }

    /* access modifiers changed from: protected */
    public float getCodecOperatingRate(float f, Format format, Format[] formatArr) {
        float f2 = -1.0f;
        for (Format format2 : formatArr) {
            float f3 = format2.frameRate;
            if (f3 != -1.0f) {
                f2 = Math.max(f2, f3);
            }
        }
        if (f2 == -1.0f) {
            return -1.0f;
        }
        return f2 * f;
    }

    /* access modifiers changed from: protected */
    public void onCodecInitialized(String str, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(str);
    }

    /* access modifiers changed from: protected */
    public void onInputFormatChanged(Format format) throws ExoPlaybackException {
        super.onInputFormatChanged(format);
        this.eventDispatcher.inputFormatChanged(format);
        this.pendingPixelWidthHeightRatio = format.pixelWidthHeightRatio;
        this.pendingRotationDegrees = format.rotationDegrees;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        this.buffersInCodecCount++;
        this.lastInputTimeUs = Math.max(decoderInputBuffer.timeUs, this.lastInputTimeUs);
        if (Util.SDK_INT < 23 && this.tunneling) {
            onProcessedTunneledBuffer(decoderInputBuffer.timeUs);
        }
    }

    /* access modifiers changed from: protected */
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int i;
        int i2;
        boolean z = mediaFormat.containsKey(KEY_CROP_RIGHT) && mediaFormat.containsKey(KEY_CROP_LEFT) && mediaFormat.containsKey(KEY_CROP_BOTTOM) && mediaFormat.containsKey(KEY_CROP_TOP);
        if (z) {
            i = (mediaFormat.getInteger(KEY_CROP_RIGHT) - mediaFormat.getInteger(KEY_CROP_LEFT)) + 1;
        } else {
            i = mediaFormat.getInteger("width");
        }
        if (z) {
            i2 = (mediaFormat.getInteger(KEY_CROP_BOTTOM) - mediaFormat.getInteger(KEY_CROP_TOP)) + 1;
        } else {
            i2 = mediaFormat.getInteger("height");
        }
        processOutputFormat(mediaCodec, i, i2);
    }

    /* access modifiers changed from: protected */
    public boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z, Format format) throws ExoPlaybackException {
        long j4 = j;
        long j5 = j2;
        MediaCodec mediaCodec2 = mediaCodec;
        int i3 = i;
        long j6 = j3;
        if (this.initialPositionUs == C.TIME_UNSET) {
            this.initialPositionUs = j4;
        }
        long j7 = j6 - this.outputStreamOffsetUs;
        if (z) {
            skipOutputBuffer(mediaCodec2, i3, j7);
            return true;
        }
        long j8 = j6 - j4;
        if (this.surface != this.dummySurface) {
            long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
            boolean z2 = getState() == 2;
            if (!this.renderedFirstFrame || (z2 && shouldForceRenderOutputBuffer(j8, elapsedRealtime - this.lastRenderTimeUs))) {
                long nanoTime = System.nanoTime();
                notifyFrameMetadataListener(j7, nanoTime, format);
                if (Util.SDK_INT >= 21) {
                    renderOutputBufferV21(mediaCodec, i, j7, nanoTime);
                    return true;
                }
                renderOutputBuffer(mediaCodec2, i3, j7);
                return true;
            } else if (!z2 || j4 == this.initialPositionUs) {
                return false;
            } else {
                long nanoTime2 = System.nanoTime();
                long adjustReleaseTime = this.frameReleaseTimeHelper.adjustReleaseTime(j6, ((j8 - (elapsedRealtime - j5)) * 1000) + nanoTime2);
                long j9 = (adjustReleaseTime - nanoTime2) / 1000;
                if (shouldDropBuffersToKeyframe(j9, j5) && maybeDropBuffersToKeyframe(mediaCodec, i, j7, j)) {
                    return false;
                }
                if (shouldDropOutputBuffer(j9, j5)) {
                    dropOutputBuffer(mediaCodec2, i3, j7);
                    return true;
                }
                if (Util.SDK_INT >= 21) {
                    if (j9 < 50000) {
                        notifyFrameMetadataListener(j7, adjustReleaseTime, format);
                        renderOutputBufferV21(mediaCodec, i, j7, adjustReleaseTime);
                        return true;
                    }
                } else if (j9 < 30000) {
                    if (j9 > 11000) {
                        try {
                            Thread.sleep((j9 - 10000) / 1000);
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                            return false;
                        }
                    }
                    notifyFrameMetadataListener(j7, adjustReleaseTime, format);
                    renderOutputBuffer(mediaCodec2, i3, j7);
                    return true;
                }
                return false;
            }
        } else if (!isBufferLate(j8)) {
            return false;
        } else {
            skipOutputBuffer(mediaCodec2, i3, j7);
            return true;
        }
    }

    private void processOutputFormat(MediaCodec mediaCodec, int i, int i2) {
        this.currentWidth = i;
        this.currentHeight = i2;
        this.currentPixelWidthHeightRatio = this.pendingPixelWidthHeightRatio;
        if (Util.SDK_INT < 21) {
            this.currentUnappliedRotationDegrees = this.pendingRotationDegrees;
        } else if (this.pendingRotationDegrees == 90 || this.pendingRotationDegrees == 270) {
            int i3 = this.currentWidth;
            this.currentWidth = this.currentHeight;
            this.currentHeight = i3;
            this.currentPixelWidthHeightRatio = 1.0f / this.currentPixelWidthHeightRatio;
        }
        mediaCodec.setVideoScalingMode(this.scalingMode);
    }

    private void notifyFrameMetadataListener(long j, long j2, Format format) {
        if (this.frameMetadataListener != null) {
            this.frameMetadataListener.onVideoFrameAboutToBeRendered(j, j2, format);
        }
    }

    /* access modifiers changed from: protected */
    public long getOutputStreamOffsetUs() {
        return this.outputStreamOffsetUs;
    }

    /* access modifiers changed from: protected */
    public void onProcessedTunneledBuffer(long j) {
        Format updateOutputFormatForTime = updateOutputFormatForTime(j);
        if (updateOutputFormatForTime != null) {
            processOutputFormat(getCodec(), updateOutputFormatForTime.width, updateOutputFormatForTime.height);
        }
        maybeNotifyVideoSizeChanged();
        maybeNotifyRenderedFirstFrame();
        onProcessedOutputBuffer(j);
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onProcessedOutputBuffer(long j) {
        this.buffersInCodecCount--;
        while (this.pendingOutputStreamOffsetCount != 0 && j >= this.pendingOutputStreamSwitchTimesUs[0]) {
            this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[0];
            this.pendingOutputStreamOffsetCount--;
            System.arraycopy(this.pendingOutputStreamOffsetsUs, 1, this.pendingOutputStreamOffsetsUs, 0, this.pendingOutputStreamOffsetCount);
            System.arraycopy(this.pendingOutputStreamSwitchTimesUs, 1, this.pendingOutputStreamSwitchTimesUs, 0, this.pendingOutputStreamOffsetCount);
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropOutputBuffer(long j, long j2) {
        return isBufferLate(j);
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropBuffersToKeyframe(long j, long j2) {
        return isBufferVeryLate(j);
    }

    /* access modifiers changed from: protected */
    public boolean shouldForceRenderOutputBuffer(long j, long j2) {
        return isBufferLate(j) && j2 > 100000;
    }

    /* access modifiers changed from: protected */
    public void skipOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        this.decoderCounters.skippedOutputBufferCount++;
    }

    /* access modifiers changed from: protected */
    public void dropOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(1);
    }

    /* access modifiers changed from: protected */
    public boolean maybeDropBuffersToKeyframe(MediaCodec mediaCodec, int i, long j, long j2) throws ExoPlaybackException {
        int skipSource = skipSource(j2);
        if (skipSource == 0) {
            return false;
        }
        this.decoderCounters.droppedToKeyframeCount++;
        updateDroppedBufferCounters(this.buffersInCodecCount + skipSource);
        flushCodec();
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateDroppedBufferCounters(int i) {
        this.decoderCounters.droppedBufferCount += i;
        this.droppedFrames += i;
        this.consecutiveDroppedFrameCount += i;
        this.decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(this.consecutiveDroppedFrameCount, this.decoderCounters.maxConsecutiveDroppedBufferCount);
        if (this.maxDroppedFramesToNotify > 0 && this.droppedFrames >= this.maxDroppedFramesToNotify) {
            maybeNotifyDroppedFrames();
        }
    }

    /* access modifiers changed from: protected */
    public void renderOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    /* access modifiers changed from: protected */
    @TargetApi(21)
    public void renderOutputBufferV21(MediaCodec mediaCodec, int i, long j, long j2) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j2);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    private boolean shouldUseDummySurface(MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 23 && !this.tunneling && !codecNeedsSetOutputSurfaceWorkaround(mediaCodecInfo.name) && (!mediaCodecInfo.secure || DummySurface.isSecureSupported(this.context));
    }

    private void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : C.TIME_UNSET;
    }

    private void clearRenderedFirstFrame() {
        MediaCodec codec;
        this.renderedFirstFrame = false;
        if (Util.SDK_INT >= 23 && this.tunneling && (codec = getCodec()) != null) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(codec);
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeNotifyRenderedFirstFrame() {
        if (!this.renderedFirstFrame) {
            this.renderedFirstFrame = true;
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.renderedFirstFrame) {
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void clearReportedVideoSize() {
        this.reportedWidth = -1;
        this.reportedHeight = -1;
        this.reportedPixelWidthHeightRatio = -1.0f;
        this.reportedUnappliedRotationDegrees = -1;
    }

    private void maybeNotifyVideoSizeChanged() {
        if (this.currentWidth != -1 || this.currentHeight != -1) {
            if (this.reportedWidth != this.currentWidth || this.reportedHeight != this.currentHeight || this.reportedUnappliedRotationDegrees != this.currentUnappliedRotationDegrees || this.reportedPixelWidthHeightRatio != this.currentPixelWidthHeightRatio) {
                this.eventDispatcher.videoSizeChanged(this.currentWidth, this.currentHeight, this.currentUnappliedRotationDegrees, this.currentPixelWidthHeightRatio);
                this.reportedWidth = this.currentWidth;
                this.reportedHeight = this.currentHeight;
                this.reportedUnappliedRotationDegrees = this.currentUnappliedRotationDegrees;
                this.reportedPixelWidthHeightRatio = this.currentPixelWidthHeightRatio;
            }
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        if (this.reportedWidth != -1 || this.reportedHeight != -1) {
            this.eventDispatcher.videoSizeChanged(this.reportedWidth, this.reportedHeight, this.reportedUnappliedRotationDegrees, this.reportedPixelWidthHeightRatio);
        }
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, elapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = elapsedRealtime;
        }
    }

    @TargetApi(23)
    private static void setOutputSurfaceV23(MediaCodec mediaCodec, Surface surface2) {
        mediaCodec.setOutputSurface(surface2);
    }

    @TargetApi(21)
    private static void configureTunnelingV21(MediaFormat mediaFormat, int i) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", i);
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"InlinedApi"})
    public MediaFormat getMediaFormat(Format format, CodecMaxValues codecMaxValues2, float f, boolean z, int i) {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString(IMediaFormat.KEY_MIME, format.sampleMimeType);
        mediaFormat.setInteger("width", format.width);
        mediaFormat.setInteger("height", format.height);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "rotation-degrees", format.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
        mediaFormat.setInteger("max-width", codecMaxValues2.width);
        mediaFormat.setInteger("max-height", codecMaxValues2.height);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", codecMaxValues2.inputSize);
        if (Util.SDK_INT >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (z) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (i != 0) {
            configureTunnelingV21(mediaFormat, i);
        }
        return mediaFormat;
    }

    /* access modifiers changed from: protected */
    public CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) throws MediaCodecUtil.DecoderQueryException {
        int codecMaxInputSize;
        int i = format.width;
        int i2 = format.height;
        int maxInputSize = getMaxInputSize(mediaCodecInfo, format);
        if (formatArr.length == 1) {
            if (!(maxInputSize == -1 || (codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, format.width, format.height)) == -1)) {
                maxInputSize = Math.min((int) (((float) maxInputSize) * INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR), codecMaxInputSize);
            }
            return new CodecMaxValues(i, i2, maxInputSize);
        }
        int i3 = i2;
        int i4 = maxInputSize;
        boolean z = false;
        int i5 = i;
        for (Format format2 : formatArr) {
            if (mediaCodecInfo.isSeamlessAdaptationSupported(format, format2, false)) {
                z |= format2.width == -1 || format2.height == -1;
                i5 = Math.max(i5, format2.width);
                i3 = Math.max(i3, format2.height);
                i4 = Math.max(i4, getMaxInputSize(mediaCodecInfo, format2));
            }
        }
        if (z) {
            Log.w(TAG, "Resolutions unknown. Codec max resolution: " + i5 + "x" + i3);
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format);
            if (codecMaxSize != null) {
                i5 = Math.max(i5, codecMaxSize.x);
                i3 = Math.max(i3, codecMaxSize.y);
                i4 = Math.max(i4, getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, i5, i3));
                Log.w(TAG, "Codec max resolution adjusted to: " + i5 + "x" + i3);
            }
        }
        return new CodecMaxValues(i5, i3, i4);
    }

    private static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format) throws MediaCodecUtil.DecoderQueryException {
        boolean z = format.height > format.width;
        int i = z ? format.height : format.width;
        int i2 = z ? format.width : format.height;
        float f = ((float) i2) / ((float) i);
        for (int i3 : STANDARD_LONG_EDGE_VIDEO_PX) {
            int i4 = (int) (((float) i3) * f);
            if (i3 <= i || i4 <= i2) {
                return null;
            }
            if (Util.SDK_INT >= 21) {
                int i5 = z ? i4 : i3;
                if (!z) {
                    i3 = i4;
                }
                Point alignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i5, i3);
                if (mediaCodecInfo.isVideoSizeAndRateSupportedV21(alignVideoSizeV21.x, alignVideoSizeV21.y, (double) format.frameRate)) {
                    return alignVideoSizeV21;
                }
            } else {
                int ceilDivide = Util.ceilDivide(i3, 16) * 16;
                int ceilDivide2 = Util.ceilDivide(i4, 16) * 16;
                if (ceilDivide * ceilDivide2 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                    int i6 = z ? ceilDivide2 : ceilDivide;
                    if (z) {
                        ceilDivide2 = ceilDivide;
                    }
                    return new Point(i6, ceilDivide2);
                }
            }
        }
        return null;
    }

    private static int getMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        if (format.maxInputSize == -1) {
            return getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, format.width, format.height);
        }
        int size = format.initializationData.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += format.initializationData.get(i2).length;
        }
        return format.maxInputSize + i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int getCodecMaxInputSize(com.google.android.exoplayer2.mediacodec.MediaCodecInfo r5, java.lang.String r6, int r7, int r8) {
        /*
            r0 = -1
            if (r7 == r0) goto L_0x00a7
            if (r8 != r0) goto L_0x0007
            goto L_0x00a7
        L_0x0007:
            int r1 = r6.hashCode()
            r2 = 3
            r3 = 4
            r4 = 2
            switch(r1) {
                case -1664118616: goto L_0x0049;
                case -1662541442: goto L_0x003e;
                case 1187890754: goto L_0x0033;
                case 1331836730: goto L_0x0028;
                case 1599127256: goto L_0x001d;
                case 1599127257: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0054
        L_0x0012:
            java.lang.String r1 = "video/x-vnd.on2.vp9"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0054
            r6 = 5
            goto L_0x0055
        L_0x001d:
            java.lang.String r1 = "video/x-vnd.on2.vp8"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0054
            r6 = 3
            goto L_0x0055
        L_0x0028:
            java.lang.String r1 = "video/avc"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0054
            r6 = 2
            goto L_0x0055
        L_0x0033:
            java.lang.String r1 = "video/mp4v-es"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0054
            r6 = 1
            goto L_0x0055
        L_0x003e:
            java.lang.String r1 = "video/hevc"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0054
            r6 = 4
            goto L_0x0055
        L_0x0049:
            java.lang.String r1 = "video/3gpp"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0054
            r6 = 0
            goto L_0x0055
        L_0x0054:
            r6 = -1
        L_0x0055:
            switch(r6) {
                case 0: goto L_0x009e;
                case 1: goto L_0x009e;
                case 2: goto L_0x005f;
                case 3: goto L_0x005c;
                case 4: goto L_0x0059;
                case 5: goto L_0x0059;
                default: goto L_0x0058;
            }
        L_0x0058:
            return r0
        L_0x0059:
            int r7 = r7 * r8
            goto L_0x00a1
        L_0x005c:
            int r7 = r7 * r8
            goto L_0x00a0
        L_0x005f:
            java.lang.String r6 = "BRAVIA 4K 2015"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.MODEL
            boolean r6 = r6.equals(r1)
            if (r6 != 0) goto L_0x009d
            java.lang.String r6 = "Amazon"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.MANUFACTURER
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x008c
            java.lang.String r6 = "KFSOWI"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.MODEL
            boolean r6 = r6.equals(r1)
            if (r6 != 0) goto L_0x009d
            java.lang.String r6 = "AFTS"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.MODEL
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x008c
            boolean r5 = r5.secure
            if (r5 == 0) goto L_0x008c
            goto L_0x009d
        L_0x008c:
            r5 = 16
            int r6 = com.google.android.exoplayer2.util.Util.ceilDivide((int) r7, (int) r5)
            int r7 = com.google.android.exoplayer2.util.Util.ceilDivide((int) r8, (int) r5)
            int r6 = r6 * r7
            int r6 = r6 * 16
            int r7 = r6 * 16
            goto L_0x00a0
        L_0x009d:
            return r0
        L_0x009e:
            int r7 = r7 * r8
        L_0x00a0:
            r3 = 2
        L_0x00a1:
            int r7 = r7 * 3
            int r3 = r3 * 2
            int r7 = r7 / r3
            return r7
        L_0x00a7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.getCodecMaxInputSize(com.google.android.exoplayer2.mediacodec.MediaCodecInfo, java.lang.String, int, int):int");
    }

    private static boolean deviceNeedsNoPostProcessWorkaround() {
        return "NVIDIA".equals(Util.MANUFACTURER);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:393:0x0608, code lost:
        r1 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:394:0x0609, code lost:
        switch(r1) {
            case 0: goto L_0x060d;
            case 1: goto L_0x060d;
            case 2: goto L_0x060d;
            case 3: goto L_0x060d;
            case 4: goto L_0x060d;
            case 5: goto L_0x060d;
            case 6: goto L_0x060d;
            case 7: goto L_0x060d;
            case 8: goto L_0x060d;
            case 9: goto L_0x060d;
            case 10: goto L_0x060d;
            case 11: goto L_0x060d;
            case 12: goto L_0x060d;
            case 13: goto L_0x060d;
            case 14: goto L_0x060d;
            case 15: goto L_0x060d;
            case 16: goto L_0x060d;
            case 17: goto L_0x060d;
            case 18: goto L_0x060d;
            case 19: goto L_0x060d;
            case 20: goto L_0x060d;
            case 21: goto L_0x060d;
            case 22: goto L_0x060d;
            case 23: goto L_0x060d;
            case 24: goto L_0x060d;
            case 25: goto L_0x060d;
            case 26: goto L_0x060d;
            case 27: goto L_0x060d;
            case 28: goto L_0x060d;
            case 29: goto L_0x060d;
            case 30: goto L_0x060d;
            case 31: goto L_0x060d;
            case 32: goto L_0x060d;
            case 33: goto L_0x060d;
            case 34: goto L_0x060d;
            case 35: goto L_0x060d;
            case 36: goto L_0x060d;
            case 37: goto L_0x060d;
            case 38: goto L_0x060d;
            case 39: goto L_0x060d;
            case 40: goto L_0x060d;
            case 41: goto L_0x060d;
            case 42: goto L_0x060d;
            case 43: goto L_0x060d;
            case 44: goto L_0x060d;
            case 45: goto L_0x060d;
            case 46: goto L_0x060d;
            case 47: goto L_0x060d;
            case 48: goto L_0x060d;
            case 49: goto L_0x060d;
            case 50: goto L_0x060d;
            case 51: goto L_0x060d;
            case 52: goto L_0x060d;
            case 53: goto L_0x060d;
            case 54: goto L_0x060d;
            case 55: goto L_0x060d;
            case 56: goto L_0x060d;
            case 57: goto L_0x060d;
            case 58: goto L_0x060d;
            case 59: goto L_0x060d;
            case 60: goto L_0x060d;
            case 61: goto L_0x060d;
            case 62: goto L_0x060d;
            case 63: goto L_0x060d;
            case 64: goto L_0x060d;
            case 65: goto L_0x060d;
            case 66: goto L_0x060d;
            case 67: goto L_0x060d;
            case 68: goto L_0x060d;
            case 69: goto L_0x060d;
            case 70: goto L_0x060d;
            case 71: goto L_0x060d;
            case 72: goto L_0x060d;
            case 73: goto L_0x060d;
            case 74: goto L_0x060d;
            case 75: goto L_0x060d;
            case 76: goto L_0x060d;
            case 77: goto L_0x060d;
            case 78: goto L_0x060d;
            case 79: goto L_0x060d;
            case 80: goto L_0x060d;
            case 81: goto L_0x060d;
            case 82: goto L_0x060d;
            case 83: goto L_0x060d;
            case 84: goto L_0x060d;
            case 85: goto L_0x060d;
            case 86: goto L_0x060d;
            case 87: goto L_0x060d;
            case 88: goto L_0x060d;
            case 89: goto L_0x060d;
            case 90: goto L_0x060d;
            case 91: goto L_0x060d;
            case 92: goto L_0x060d;
            case 93: goto L_0x060d;
            case 94: goto L_0x060d;
            case 95: goto L_0x060d;
            case 96: goto L_0x060d;
            case 97: goto L_0x060d;
            case 98: goto L_0x060d;
            case 99: goto L_0x060d;
            case 100: goto L_0x060d;
            case 101: goto L_0x060d;
            case 102: goto L_0x060d;
            case 103: goto L_0x060d;
            case 104: goto L_0x060d;
            case 105: goto L_0x060d;
            case 106: goto L_0x060d;
            case 107: goto L_0x060d;
            case 108: goto L_0x060d;
            case 109: goto L_0x060d;
            case 110: goto L_0x060d;
            case 111: goto L_0x060d;
            case 112: goto L_0x060d;
            case 113: goto L_0x060d;
            case 114: goto L_0x060d;
            case 115: goto L_0x060d;
            case 116: goto L_0x060d;
            case 117: goto L_0x060d;
            case 118: goto L_0x060d;
            case 119: goto L_0x060d;
            case 120: goto L_0x060d;
            case 121: goto L_0x060d;
            case 122: goto L_0x060d;
            case 123: goto L_0x060d;
            default: goto L_0x060c;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:396:0x060d, code lost:
        deviceNeedsSetOutputSurfaceWorkaround = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:397:0x060f, code lost:
        r1 = com.google.android.exoplayer2.util.Util.MODEL;
        r2 = r1.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:398:0x0618, code lost:
        if (r2 == 2006354) goto L_0x062a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:400:0x061d, code lost:
        if (r2 == 2006367) goto L_0x0620;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:403:0x0626, code lost:
        if (r1.equals("AFTN") == false) goto L_0x0633;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:404:0x0628, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:406:0x0630, code lost:
        if (r1.equals("AFTA") == false) goto L_0x0633;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:0x0633, code lost:
        r0 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:409:0x0634, code lost:
        switch(r0) {
            case 0: goto L_0x0638;
            case 1: goto L_0x0638;
            default: goto L_0x0637;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:411:0x0638, code lost:
        deviceNeedsSetOutputSurfaceWorkaround = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean codecNeedsSetOutputSurfaceWorkaround(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "OMX.google"
            boolean r7 = r7.startsWith(r0)
            r0 = 0
            if (r7 == 0) goto L_0x000a
            return r0
        L_0x000a:
            java.lang.Class<com.google.android.exoplayer2.video.MediaCodecVideoRenderer> r7 = com.google.android.exoplayer2.video.MediaCodecVideoRenderer.class
            monitor-enter(r7)
            boolean r1 = evaluatedDeviceNeedsSetOutputSurfaceWorkaround     // Catch:{ all -> 0x0640 }
            if (r1 != 0) goto L_0x063c
            int r1 = com.google.android.exoplayer2.util.Util.SDK_INT     // Catch:{ all -> 0x0640 }
            r2 = 27
            r3 = 1
            if (r1 > r2) goto L_0x0030
            java.lang.String r1 = "dangal"
            java.lang.String r4 = com.google.android.exoplayer2.util.Util.DEVICE     // Catch:{ all -> 0x0640 }
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0640 }
            if (r1 != 0) goto L_0x002c
            java.lang.String r1 = "HWEML"
            java.lang.String r4 = com.google.android.exoplayer2.util.Util.DEVICE     // Catch:{ all -> 0x0640 }
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0030
        L_0x002c:
            deviceNeedsSetOutputSurfaceWorkaround = r3     // Catch:{ all -> 0x0640 }
            goto L_0x063a
        L_0x0030:
            int r1 = com.google.android.exoplayer2.util.Util.SDK_INT     // Catch:{ all -> 0x0640 }
            if (r1 < r2) goto L_0x0036
            goto L_0x063a
        L_0x0036:
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.DEVICE     // Catch:{ all -> 0x0640 }
            int r4 = r1.hashCode()     // Catch:{ all -> 0x0640 }
            r5 = -1
            switch(r4) {
                case -2144781245: goto L_0x05fd;
                case -2144781185: goto L_0x05f2;
                case -2144781160: goto L_0x05e7;
                case -2097309513: goto L_0x05dc;
                case -2022874474: goto L_0x05d1;
                case -1978993182: goto L_0x05c6;
                case -1978990237: goto L_0x05bb;
                case -1936688988: goto L_0x05b0;
                case -1936688066: goto L_0x05a5;
                case -1936688065: goto L_0x0599;
                case -1931988508: goto L_0x058d;
                case -1696512866: goto L_0x0581;
                case -1680025915: goto L_0x0575;
                case -1615810839: goto L_0x0569;
                case -1554255044: goto L_0x055c;
                case -1481772737: goto L_0x0550;
                case -1481772730: goto L_0x0544;
                case -1481772729: goto L_0x0538;
                case -1320080169: goto L_0x052c;
                case -1217592143: goto L_0x0520;
                case -1180384755: goto L_0x0514;
                case -1139198265: goto L_0x0508;
                case -1052835013: goto L_0x04fc;
                case -993250464: goto L_0x04f1;
                case -965403638: goto L_0x04e5;
                case -958336948: goto L_0x04d9;
                case -879245230: goto L_0x04cc;
                case -842500323: goto L_0x04c0;
                case -821392978: goto L_0x04b5;
                case -797483286: goto L_0x04a9;
                case -794946968: goto L_0x049c;
                case -788334647: goto L_0x048f;
                case -782144577: goto L_0x0483;
                case -575125681: goto L_0x0477;
                case -521118391: goto L_0x046b;
                case -430914369: goto L_0x045f;
                case -290434366: goto L_0x0452;
                case -282781963: goto L_0x0446;
                case -277133239: goto L_0x043a;
                case -173639913: goto L_0x042e;
                case -56598463: goto L_0x0421;
                case 2126: goto L_0x0415;
                case 2564: goto L_0x0409;
                case 2715: goto L_0x03fd;
                case 2719: goto L_0x03f1;
                case 3483: goto L_0x03e5;
                case 73405: goto L_0x03d9;
                case 75739: goto L_0x03cd;
                case 76779: goto L_0x03c1;
                case 78669: goto L_0x03b5;
                case 79305: goto L_0x03a9;
                case 80618: goto L_0x039d;
                case 88274: goto L_0x0391;
                case 98846: goto L_0x0385;
                case 98848: goto L_0x0379;
                case 99329: goto L_0x036d;
                case 101481: goto L_0x0361;
                case 1513190: goto L_0x0356;
                case 1514184: goto L_0x034b;
                case 1514185: goto L_0x0340;
                case 2436959: goto L_0x0334;
                case 2463773: goto L_0x0328;
                case 2464648: goto L_0x031c;
                case 2689555: goto L_0x0310;
                case 3154429: goto L_0x0304;
                case 3284551: goto L_0x02f8;
                case 3351335: goto L_0x02ec;
                case 3386211: goto L_0x02e0;
                case 41325051: goto L_0x02d4;
                case 55178625: goto L_0x02c8;
                case 61542055: goto L_0x02bd;
                case 65355429: goto L_0x02b1;
                case 66214468: goto L_0x02a5;
                case 66214470: goto L_0x0299;
                case 66214473: goto L_0x028d;
                case 66215429: goto L_0x0281;
                case 66215431: goto L_0x0275;
                case 66215433: goto L_0x0269;
                case 66216390: goto L_0x025d;
                case 76402249: goto L_0x0251;
                case 76404105: goto L_0x0245;
                case 76404911: goto L_0x0239;
                case 80963634: goto L_0x022d;
                case 82882791: goto L_0x0221;
                case 98715550: goto L_0x0215;
                case 102844228: goto L_0x0209;
                case 165221241: goto L_0x01fe;
                case 182191441: goto L_0x01f2;
                case 245388979: goto L_0x01e6;
                case 287431619: goto L_0x01da;
                case 307593612: goto L_0x01ce;
                case 308517133: goto L_0x01c2;
                case 316215098: goto L_0x01b6;
                case 316215116: goto L_0x01aa;
                case 316246811: goto L_0x019e;
                case 316246818: goto L_0x0192;
                case 407160593: goto L_0x0186;
                case 507412548: goto L_0x017a;
                case 793982701: goto L_0x016e;
                case 794038622: goto L_0x0162;
                case 794040393: goto L_0x0156;
                case 835649806: goto L_0x014a;
                case 917340916: goto L_0x013f;
                case 958008161: goto L_0x0133;
                case 1060579533: goto L_0x0127;
                case 1150207623: goto L_0x011b;
                case 1176899427: goto L_0x010f;
                case 1280332038: goto L_0x0103;
                case 1306947716: goto L_0x00f7;
                case 1349174697: goto L_0x00eb;
                case 1522194893: goto L_0x00de;
                case 1691543273: goto L_0x00d2;
                case 1709443163: goto L_0x00c6;
                case 1865889110: goto L_0x00ba;
                case 1906253259: goto L_0x00ae;
                case 1977196784: goto L_0x00a2;
                case 2006372676: goto L_0x0096;
                case 2029784656: goto L_0x008a;
                case 2030379515: goto L_0x007e;
                case 2033393791: goto L_0x0072;
                case 2047190025: goto L_0x0066;
                case 2047252157: goto L_0x005a;
                case 2048319463: goto L_0x004e;
                case 2048855701: goto L_0x0042;
                default: goto L_0x0040;
            }     // Catch:{ all -> 0x0640 }
        L_0x0040:
            goto L_0x0608
        L_0x0042:
            java.lang.String r2 = "HWWAS-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 54
            goto L_0x0609
        L_0x004e:
            java.lang.String r2 = "HWVNS-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 53
            goto L_0x0609
        L_0x005a:
            java.lang.String r4 = "ELUGA_Prim"
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 27
            goto L_0x0609
        L_0x0066:
            java.lang.String r2 = "ELUGA_Note"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 26
            goto L_0x0609
        L_0x0072:
            java.lang.String r2 = "ASUS_X00AD_2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 11
            goto L_0x0609
        L_0x007e:
            java.lang.String r2 = "HWCAM-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 52
            goto L_0x0609
        L_0x008a:
            java.lang.String r2 = "HWBLN-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 51
            goto L_0x0609
        L_0x0096:
            java.lang.String r2 = "BRAVIA_ATV3_4K"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 15
            goto L_0x0609
        L_0x00a2:
            java.lang.String r2 = "Infinix-X572"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 57
            goto L_0x0609
        L_0x00ae:
            java.lang.String r2 = "PB2-670M"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 85
            goto L_0x0609
        L_0x00ba:
            java.lang.String r2 = "santoni"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 101(0x65, float:1.42E-43)
            goto L_0x0609
        L_0x00c6:
            java.lang.String r2 = "iball8735_9806"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 56
            goto L_0x0609
        L_0x00d2:
            java.lang.String r2 = "CPH1609"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 19
            goto L_0x0609
        L_0x00de:
            java.lang.String r2 = "woods_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 117(0x75, float:1.64E-43)
            goto L_0x0609
        L_0x00eb:
            java.lang.String r2 = "htc_e56ml_dtul"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 49
            goto L_0x0609
        L_0x00f7:
            java.lang.String r2 = "EverStar_S"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 29
            goto L_0x0609
        L_0x0103:
            java.lang.String r2 = "hwALE-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 50
            goto L_0x0609
        L_0x010f:
            java.lang.String r2 = "itel_S41"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 59
            goto L_0x0609
        L_0x011b:
            java.lang.String r2 = "LS-5017"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 65
            goto L_0x0609
        L_0x0127:
            java.lang.String r2 = "panell_d"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 81
            goto L_0x0609
        L_0x0133:
            java.lang.String r2 = "j2xlteins"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 60
            goto L_0x0609
        L_0x013f:
            java.lang.String r2 = "A7000plus"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 7
            goto L_0x0609
        L_0x014a:
            java.lang.String r2 = "manning"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 67
            goto L_0x0609
        L_0x0156:
            java.lang.String r2 = "GIONEE_WBL7519"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 47
            goto L_0x0609
        L_0x0162:
            java.lang.String r2 = "GIONEE_WBL7365"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 46
            goto L_0x0609
        L_0x016e:
            java.lang.String r2 = "GIONEE_WBL5708"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 45
            goto L_0x0609
        L_0x017a:
            java.lang.String r2 = "QM16XE_U"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 99
            goto L_0x0609
        L_0x0186:
            java.lang.String r2 = "Pixi5-10_4G"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 91
            goto L_0x0609
        L_0x0192:
            java.lang.String r2 = "TB3-850M"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 109(0x6d, float:1.53E-43)
            goto L_0x0609
        L_0x019e:
            java.lang.String r2 = "TB3-850F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 108(0x6c, float:1.51E-43)
            goto L_0x0609
        L_0x01aa:
            java.lang.String r2 = "TB3-730X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 107(0x6b, float:1.5E-43)
            goto L_0x0609
        L_0x01b6:
            java.lang.String r2 = "TB3-730F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 106(0x6a, float:1.49E-43)
            goto L_0x0609
        L_0x01c2:
            java.lang.String r2 = "A7020a48"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 9
            goto L_0x0609
        L_0x01ce:
            java.lang.String r2 = "A7010a48"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 8
            goto L_0x0609
        L_0x01da:
            java.lang.String r2 = "griffin"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 48
            goto L_0x0609
        L_0x01e6:
            java.lang.String r2 = "marino_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 68
            goto L_0x0609
        L_0x01f2:
            java.lang.String r2 = "CPY83_I00"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 20
            goto L_0x0609
        L_0x01fe:
            java.lang.String r2 = "A2016a40"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 5
            goto L_0x0609
        L_0x0209:
            java.lang.String r2 = "le_x6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 64
            goto L_0x0609
        L_0x0215:
            java.lang.String r2 = "i9031"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 55
            goto L_0x0609
        L_0x0221:
            java.lang.String r2 = "X3_HK"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 119(0x77, float:1.67E-43)
            goto L_0x0609
        L_0x022d:
            java.lang.String r2 = "V23GB"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 112(0x70, float:1.57E-43)
            goto L_0x0609
        L_0x0239:
            java.lang.String r2 = "Q4310"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 97
            goto L_0x0609
        L_0x0245:
            java.lang.String r2 = "Q4260"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 95
            goto L_0x0609
        L_0x0251:
            java.lang.String r2 = "PRO7S"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 93
            goto L_0x0609
        L_0x025d:
            java.lang.String r2 = "F3311"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 36
            goto L_0x0609
        L_0x0269:
            java.lang.String r2 = "F3215"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 35
            goto L_0x0609
        L_0x0275:
            java.lang.String r2 = "F3213"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 34
            goto L_0x0609
        L_0x0281:
            java.lang.String r2 = "F3211"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 33
            goto L_0x0609
        L_0x028d:
            java.lang.String r2 = "F3116"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 32
            goto L_0x0609
        L_0x0299:
            java.lang.String r2 = "F3113"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 31
            goto L_0x0609
        L_0x02a5:
            java.lang.String r2 = "F3111"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 30
            goto L_0x0609
        L_0x02b1:
            java.lang.String r2 = "E5643"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 24
            goto L_0x0609
        L_0x02bd:
            java.lang.String r2 = "A1601"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 4
            goto L_0x0609
        L_0x02c8:
            java.lang.String r2 = "Aura_Note_2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 12
            goto L_0x0609
        L_0x02d4:
            java.lang.String r2 = "MEIZU_M5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 69
            goto L_0x0609
        L_0x02e0:
            java.lang.String r2 = "p212"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 78
            goto L_0x0609
        L_0x02ec:
            java.lang.String r2 = "mido"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 71
            goto L_0x0609
        L_0x02f8:
            java.lang.String r2 = "kate"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 63
            goto L_0x0609
        L_0x0304:
            java.lang.String r2 = "fugu"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 38
            goto L_0x0609
        L_0x0310:
            java.lang.String r2 = "XE2X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 120(0x78, float:1.68E-43)
            goto L_0x0609
        L_0x031c:
            java.lang.String r2 = "Q427"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 96
            goto L_0x0609
        L_0x0328:
            java.lang.String r2 = "Q350"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 94
            goto L_0x0609
        L_0x0334:
            java.lang.String r2 = "P681"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 79
            goto L_0x0609
        L_0x0340:
            java.lang.String r2 = "1714"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 2
            goto L_0x0609
        L_0x034b:
            java.lang.String r2 = "1713"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 1
            goto L_0x0609
        L_0x0356:
            java.lang.String r2 = "1601"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 0
            goto L_0x0609
        L_0x0361:
            java.lang.String r2 = "flo"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 37
            goto L_0x0609
        L_0x036d:
            java.lang.String r2 = "deb"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 23
            goto L_0x0609
        L_0x0379:
            java.lang.String r2 = "cv3"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 22
            goto L_0x0609
        L_0x0385:
            java.lang.String r2 = "cv1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 21
            goto L_0x0609
        L_0x0391:
            java.lang.String r2 = "Z80"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 123(0x7b, float:1.72E-43)
            goto L_0x0609
        L_0x039d:
            java.lang.String r2 = "QX1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 100
            goto L_0x0609
        L_0x03a9:
            java.lang.String r2 = "PLE"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 92
            goto L_0x0609
        L_0x03b5:
            java.lang.String r2 = "P85"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 80
            goto L_0x0609
        L_0x03c1:
            java.lang.String r2 = "MX6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 72
            goto L_0x0609
        L_0x03cd:
            java.lang.String r2 = "M5c"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 66
            goto L_0x0609
        L_0x03d9:
            java.lang.String r2 = "JGZ"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 61
            goto L_0x0609
        L_0x03e5:
            java.lang.String r2 = "mh"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 70
            goto L_0x0609
        L_0x03f1:
            java.lang.String r2 = "V5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 113(0x71, float:1.58E-43)
            goto L_0x0609
        L_0x03fd:
            java.lang.String r2 = "V1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 111(0x6f, float:1.56E-43)
            goto L_0x0609
        L_0x0409:
            java.lang.String r2 = "Q5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 98
            goto L_0x0609
        L_0x0415:
            java.lang.String r2 = "C1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 16
            goto L_0x0609
        L_0x0421:
            java.lang.String r2 = "woods_fn"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 118(0x76, float:1.65E-43)
            goto L_0x0609
        L_0x042e:
            java.lang.String r2 = "ELUGA_A3_Pro"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 25
            goto L_0x0609
        L_0x043a:
            java.lang.String r2 = "Z12_PRO"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 122(0x7a, float:1.71E-43)
            goto L_0x0609
        L_0x0446:
            java.lang.String r2 = "BLACK-1X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 13
            goto L_0x0609
        L_0x0452:
            java.lang.String r2 = "taido_row"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 105(0x69, float:1.47E-43)
            goto L_0x0609
        L_0x045f:
            java.lang.String r2 = "Pixi4-7_3G"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 90
            goto L_0x0609
        L_0x046b:
            java.lang.String r2 = "GIONEE_GBL7360"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 41
            goto L_0x0609
        L_0x0477:
            java.lang.String r2 = "GiONEE_CBL7513"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 39
            goto L_0x0609
        L_0x0483:
            java.lang.String r2 = "OnePlus5T"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 77
            goto L_0x0609
        L_0x048f:
            java.lang.String r2 = "whyred"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 116(0x74, float:1.63E-43)
            goto L_0x0609
        L_0x049c:
            java.lang.String r2 = "watson"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 115(0x73, float:1.61E-43)
            goto L_0x0609
        L_0x04a9:
            java.lang.String r2 = "SVP-DTV15"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 103(0x67, float:1.44E-43)
            goto L_0x0609
        L_0x04b5:
            java.lang.String r2 = "A7000-a"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 6
            goto L_0x0609
        L_0x04c0:
            java.lang.String r2 = "nicklaus_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 74
            goto L_0x0609
        L_0x04cc:
            java.lang.String r2 = "tcl_eu"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 110(0x6e, float:1.54E-43)
            goto L_0x0609
        L_0x04d9:
            java.lang.String r2 = "ELUGA_Ray_X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 28
            goto L_0x0609
        L_0x04e5:
            java.lang.String r2 = "s905x018"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 104(0x68, float:1.46E-43)
            goto L_0x0609
        L_0x04f1:
            java.lang.String r2 = "A10-70F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 3
            goto L_0x0609
        L_0x04fc:
            java.lang.String r2 = "namath"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 73
            goto L_0x0609
        L_0x0508:
            java.lang.String r2 = "Slate_Pro"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 102(0x66, float:1.43E-43)
            goto L_0x0609
        L_0x0514:
            java.lang.String r2 = "iris60"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 58
            goto L_0x0609
        L_0x0520:
            java.lang.String r2 = "BRAVIA_ATV2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 14
            goto L_0x0609
        L_0x052c:
            java.lang.String r2 = "GiONEE_GBL7319"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 40
            goto L_0x0609
        L_0x0538:
            java.lang.String r2 = "panell_dt"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 84
            goto L_0x0609
        L_0x0544:
            java.lang.String r2 = "panell_ds"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 83
            goto L_0x0609
        L_0x0550:
            java.lang.String r2 = "panell_dl"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 82
            goto L_0x0609
        L_0x055c:
            java.lang.String r2 = "vernee_M5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 114(0x72, float:1.6E-43)
            goto L_0x0609
        L_0x0569:
            java.lang.String r2 = "Phantom6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 89
            goto L_0x0609
        L_0x0575:
            java.lang.String r2 = "ComioS1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 17
            goto L_0x0609
        L_0x0581:
            java.lang.String r2 = "XT1663"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 121(0x79, float:1.7E-43)
            goto L_0x0609
        L_0x058d:
            java.lang.String r2 = "AquaPowerM"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 10
            goto L_0x0609
        L_0x0599:
            java.lang.String r2 = "PGN611"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 88
            goto L_0x0609
        L_0x05a5:
            java.lang.String r2 = "PGN610"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 87
            goto L_0x0609
        L_0x05b0:
            java.lang.String r2 = "PGN528"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 86
            goto L_0x0609
        L_0x05bb:
            java.lang.String r2 = "NX573J"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 76
            goto L_0x0609
        L_0x05c6:
            java.lang.String r2 = "NX541J"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 75
            goto L_0x0609
        L_0x05d1:
            java.lang.String r2 = "CP8676_I02"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 18
            goto L_0x0609
        L_0x05dc:
            java.lang.String r2 = "K50a40"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 62
            goto L_0x0609
        L_0x05e7:
            java.lang.String r2 = "GIONEE_SWW1631"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 44
            goto L_0x0609
        L_0x05f2:
            java.lang.String r2 = "GIONEE_SWW1627"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 43
            goto L_0x0609
        L_0x05fd:
            java.lang.String r2 = "GIONEE_SWW1609"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0608
            r1 = 42
            goto L_0x0609
        L_0x0608:
            r1 = -1
        L_0x0609:
            switch(r1) {
                case 0: goto L_0x060d;
                case 1: goto L_0x060d;
                case 2: goto L_0x060d;
                case 3: goto L_0x060d;
                case 4: goto L_0x060d;
                case 5: goto L_0x060d;
                case 6: goto L_0x060d;
                case 7: goto L_0x060d;
                case 8: goto L_0x060d;
                case 9: goto L_0x060d;
                case 10: goto L_0x060d;
                case 11: goto L_0x060d;
                case 12: goto L_0x060d;
                case 13: goto L_0x060d;
                case 14: goto L_0x060d;
                case 15: goto L_0x060d;
                case 16: goto L_0x060d;
                case 17: goto L_0x060d;
                case 18: goto L_0x060d;
                case 19: goto L_0x060d;
                case 20: goto L_0x060d;
                case 21: goto L_0x060d;
                case 22: goto L_0x060d;
                case 23: goto L_0x060d;
                case 24: goto L_0x060d;
                case 25: goto L_0x060d;
                case 26: goto L_0x060d;
                case 27: goto L_0x060d;
                case 28: goto L_0x060d;
                case 29: goto L_0x060d;
                case 30: goto L_0x060d;
                case 31: goto L_0x060d;
                case 32: goto L_0x060d;
                case 33: goto L_0x060d;
                case 34: goto L_0x060d;
                case 35: goto L_0x060d;
                case 36: goto L_0x060d;
                case 37: goto L_0x060d;
                case 38: goto L_0x060d;
                case 39: goto L_0x060d;
                case 40: goto L_0x060d;
                case 41: goto L_0x060d;
                case 42: goto L_0x060d;
                case 43: goto L_0x060d;
                case 44: goto L_0x060d;
                case 45: goto L_0x060d;
                case 46: goto L_0x060d;
                case 47: goto L_0x060d;
                case 48: goto L_0x060d;
                case 49: goto L_0x060d;
                case 50: goto L_0x060d;
                case 51: goto L_0x060d;
                case 52: goto L_0x060d;
                case 53: goto L_0x060d;
                case 54: goto L_0x060d;
                case 55: goto L_0x060d;
                case 56: goto L_0x060d;
                case 57: goto L_0x060d;
                case 58: goto L_0x060d;
                case 59: goto L_0x060d;
                case 60: goto L_0x060d;
                case 61: goto L_0x060d;
                case 62: goto L_0x060d;
                case 63: goto L_0x060d;
                case 64: goto L_0x060d;
                case 65: goto L_0x060d;
                case 66: goto L_0x060d;
                case 67: goto L_0x060d;
                case 68: goto L_0x060d;
                case 69: goto L_0x060d;
                case 70: goto L_0x060d;
                case 71: goto L_0x060d;
                case 72: goto L_0x060d;
                case 73: goto L_0x060d;
                case 74: goto L_0x060d;
                case 75: goto L_0x060d;
                case 76: goto L_0x060d;
                case 77: goto L_0x060d;
                case 78: goto L_0x060d;
                case 79: goto L_0x060d;
                case 80: goto L_0x060d;
                case 81: goto L_0x060d;
                case 82: goto L_0x060d;
                case 83: goto L_0x060d;
                case 84: goto L_0x060d;
                case 85: goto L_0x060d;
                case 86: goto L_0x060d;
                case 87: goto L_0x060d;
                case 88: goto L_0x060d;
                case 89: goto L_0x060d;
                case 90: goto L_0x060d;
                case 91: goto L_0x060d;
                case 92: goto L_0x060d;
                case 93: goto L_0x060d;
                case 94: goto L_0x060d;
                case 95: goto L_0x060d;
                case 96: goto L_0x060d;
                case 97: goto L_0x060d;
                case 98: goto L_0x060d;
                case 99: goto L_0x060d;
                case 100: goto L_0x060d;
                case 101: goto L_0x060d;
                case 102: goto L_0x060d;
                case 103: goto L_0x060d;
                case 104: goto L_0x060d;
                case 105: goto L_0x060d;
                case 106: goto L_0x060d;
                case 107: goto L_0x060d;
                case 108: goto L_0x060d;
                case 109: goto L_0x060d;
                case 110: goto L_0x060d;
                case 111: goto L_0x060d;
                case 112: goto L_0x060d;
                case 113: goto L_0x060d;
                case 114: goto L_0x060d;
                case 115: goto L_0x060d;
                case 116: goto L_0x060d;
                case 117: goto L_0x060d;
                case 118: goto L_0x060d;
                case 119: goto L_0x060d;
                case 120: goto L_0x060d;
                case 121: goto L_0x060d;
                case 122: goto L_0x060d;
                case 123: goto L_0x060d;
                default: goto L_0x060c;
            }     // Catch:{ all -> 0x0640 }
        L_0x060c:
            goto L_0x060f
        L_0x060d:
            deviceNeedsSetOutputSurfaceWorkaround = r3     // Catch:{ all -> 0x0640 }
        L_0x060f:
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.MODEL     // Catch:{ all -> 0x0640 }
            int r2 = r1.hashCode()     // Catch:{ all -> 0x0640 }
            r4 = 2006354(0x1e9d52, float:2.811501E-39)
            if (r2 == r4) goto L_0x062a
            r0 = 2006367(0x1e9d5f, float:2.811519E-39)
            if (r2 == r0) goto L_0x0620
            goto L_0x0633
        L_0x0620:
            java.lang.String r0 = "AFTN"
            boolean r0 = r1.equals(r0)     // Catch:{ all -> 0x0640 }
            if (r0 == 0) goto L_0x0633
            r0 = 1
            goto L_0x0634
        L_0x062a:
            java.lang.String r2 = "AFTA"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0640 }
            if (r1 == 0) goto L_0x0633
            goto L_0x0634
        L_0x0633:
            r0 = -1
        L_0x0634:
            switch(r0) {
                case 0: goto L_0x0638;
                case 1: goto L_0x0638;
                default: goto L_0x0637;
            }     // Catch:{ all -> 0x0640 }
        L_0x0637:
            goto L_0x063a
        L_0x0638:
            deviceNeedsSetOutputSurfaceWorkaround = r3     // Catch:{ all -> 0x0640 }
        L_0x063a:
            evaluatedDeviceNeedsSetOutputSurfaceWorkaround = r3     // Catch:{ all -> 0x0640 }
        L_0x063c:
            monitor-exit(r7)     // Catch:{ all -> 0x0640 }
            boolean r7 = deviceNeedsSetOutputSurfaceWorkaround
            return r7
        L_0x0640:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0640 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.codecNeedsSetOutputSurfaceWorkaround(java.lang.String):boolean");
    }

    protected static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i, int i2, int i3) {
            this.width = i;
            this.height = i2;
            this.inputSize = i3;
        }
    }

    @TargetApi(23)
    private final class OnFrameRenderedListenerV23 implements MediaCodec.OnFrameRenderedListener {
        private OnFrameRenderedListenerV23(MediaCodec mediaCodec) {
            mediaCodec.setOnFrameRenderedListener(this, new Handler());
        }

        public void onFrameRendered(@NonNull MediaCodec mediaCodec, long j, long j2) {
            if (this == MediaCodecVideoRenderer.this.tunnelingOnFrameRenderedListener) {
                MediaCodecVideoRenderer.this.onProcessedTunneledBuffer(j);
            }
        }
    }
}
