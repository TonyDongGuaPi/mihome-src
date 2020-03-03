package com.ximalaya.ting.android.player;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.ximalaya.ting.android.player.XMediaPlayer;
import com.ximalaya.ting.android.player.liveflv.FlvLiveAudioFile;
import com.ximalaya.ting.android.player.model.JNIDataModel;
import java.nio.ByteBuffer;

public abstract class XMediaplayerJNI implements XMediaplayerImpl {
    public static String Tag = "XMPLAY";
    private int chargeDataRealLength = 0;
    private volatile boolean hasRelease = false;
    private boolean isAfterSetDataSourceStart = false;
    public volatile boolean isBuffing = false;
    private boolean isRelease = true;
    private volatile boolean isReseting = false;
    private boolean isSeeking = false;
    private AudioFileRequestHandler mAudioFileRequestHandler;
    private AudioTrackPlayThread mAudioTrackPlayThread;
    private AudioType mAudioType = AudioType.NORMAL_FILE;
    public Context mContext;
    private long mCurFileSize;
    public String mCurrentDataDecodeUrl = this.mPlayUrl;
    private int mCurrentSeekPosition = -1;
    private int mCurrentSeekPositionState = 0;
    private FlvLiveAudioFile mFlvLiveAudioFile;
    private boolean mHasSeek = true;
    private HlsAudioFile mHlsAudioFile;
    private long mJniHandler;
    XMediaPlayer.OnPlayDataOutputListener mOnPlayDataOutputListener;
    private float mPitch = 0.0f;
    public String mPlayUrl;
    private float mRate = 1.0f;
    private float mTempo = 1.0f;
    public byte[] tmepBuf;

    public native int MediaplayerComplete(long j);

    public native int MediaplayerDestroy(long j);

    public native int MediaplayerFrameworkInit(Context context, boolean z);

    public native int MediaplayerGetCurPlayingFileType(long j);

    public native long MediaplayerGetCurrentDecodedDataTime(long j);

    public native long MediaplayerGetCurrentTime(long j);

    public native long MediaplayerGetMediaDuration(long j);

    public native int MediaplayerGetOutputData(byte[] bArr, int i, long j);

    public native long MediaplayerInit(Context context, boolean z, int i, int i2, int i3, int i4);

    public native int MediaplayerOutputDataAppointment(long j);

    public native int MediaplayerPause(long j);

    public native int MediaplayerPlay(long j);

    public native int MediaplayerPrepareAsync(long j);

    public native int MediaplayerReset(long j);

    public native int MediaplayerSeek(long j, long j2);

    public native int MediaplayerSetDataSourceInfo(String str, int i, long j, double d, double d2);

    public native int MediaplayerStop(long j);

    public abstract void mOnTimedChangeListenerInner();

    public abstract void onBufferingUpdateInner(int i);

    public abstract void onInfoInner(int i);

    public abstract void onSeekCompletedInner();

    public XMediaplayerJNI(Context context, boolean z) {
        this.mContext = context.getApplicationContext();
        this.hasRelease = false;
        this.isRelease = z;
        int MediaplayerFrameworkInit = MediaplayerFrameworkInit(context, z);
        String str = Tag;
        Logger.a(str, (Object) "MediaplayerFrameworkInit result:" + MediaplayerFrameworkInit);
        if (MediaplayerFrameworkInit < 0) {
            handlePlayerStatus(NativeErrorType.ERR_ARCH_NOT_SUPPORT.value());
        }
    }

    public void xmediaplayerInit() {
        this.hasRelease = false;
        String str = Tag;
        Logger.a(str, (Object) "Mediaplayer Init 0000000000:" + this.mJniHandler);
        this.mJniHandler = MediaplayerInit(this.mContext, this.isRelease, 524288, 65536, 1048576, 1048576);
        String str2 = Tag;
        Logger.a(str2, (Object) "Mediaplayer Init 1:" + this.mJniHandler);
        String str3 = Tag;
        Logger.a(str3, (Object) "MediaplayerInit jniHandler:" + this.mJniHandler);
        if (this.mJniHandler == 0) {
            handlePlayerStatus(NativeErrorType.ERR_ARCH_NOT_SUPPORT.value());
        }
        PlayCacheByLRU.a().c();
    }

    public AudioType getAudioType() {
        return this.mAudioType;
    }

    public void setCurFileSize(long j) {
        this.mCurFileSize = j;
    }

    public long getCurFileSize() {
        return this.mCurFileSize;
    }

    public String getPlayUrl() {
        return this.mPlayUrl;
    }

    public boolean isSeeking() {
        return this.isSeeking;
    }

    public AudioTrackPlayThread getAudioTrackPlayThread() {
        if (this.mAudioTrackPlayThread != null && this.mAudioTrackPlayThread.i()) {
            this.mAudioTrackPlayThread = null;
        }
        if (this.mAudioTrackPlayThread == null) {
            Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT getAudioTrackPlayThread new");
            this.mAudioTrackPlayThread = new AudioTrackPlayThread(this);
            this.mAudioTrackPlayThread.a(this.mTempo, this.mPitch, this.mRate);
        }
        return this.mAudioTrackPlayThread;
    }

    private String getCurPlayUrl() {
        if (this.mAudioType != AudioType.M3U8_STATIC_FILE && this.mAudioType != AudioType.HLS_FILE && this.mAudioType != AudioType.M3U8_FILE) {
            return this.mPlayUrl;
        }
        if (this.mHlsAudioFile != null) {
            return this.mHlsAudioFile.b();
        }
        throw new RuntimeException("getCurPlayUrl 错误！");
    }

    public int getOutputData(byte[] bArr, int i) {
        if (this.mAudioTrackPlayThread == null || this.hasRelease) {
            return -1;
        }
        return MediaplayerGetOutputData(bArr, i, this.mJniHandler);
    }

    public int outputDataAppointment() {
        return MediaplayerOutputDataAppointment(this.mJniHandler);
    }

    public void prepareAsync() {
        if (this.mAudioFileRequestHandler != null && this.mAudioFileRequestHandler.b()) {
            this.mAudioFileRequestHandler.c();
        }
        Logger.a(Tag, (Object) "prepareAsync");
        if (!this.isBuffing) {
            onInfoInner(701);
        }
        MediaplayerPrepareAsync(this.mJniHandler);
    }

    public void onPreparedInner() {
        if (this.isBuffing) {
            onInfoInner(702);
        }
        if (MediaplayerGetCurPlayingFileType(this.mJniHandler) >= 0) {
            String str = Tag;
            Logger.a(str, (Object) "onPreparedInner mAudioType:" + this.mAudioType.value());
            return;
        }
        onErrorInner(8, 1);
        Logger.a(Tag, (Object) "onPreparedInner mAudioType error");
    }

    public void onErrorInner(int i, int i2) {
        Logger.a(Tag, (Object) "onErrorInner");
    }

    public void stop() {
        Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT stop");
        getAudioTrackPlayThread().g();
        relaseAudioFile();
        MediaplayerStop(this.mJniHandler);
        resetAllData();
    }

    private void relaseAudioFile() {
        if (this.mAudioFileRequestHandler != null) {
            this.mAudioFileRequestHandler.a(AudioFileRequestHandler.f2267a);
        }
        if (this.mHlsAudioFile != null) {
            this.mHlsAudioFile.f();
        }
        if (this.mFlvLiveAudioFile != null) {
            this.mFlvLiveAudioFile.a();
        }
    }

    public void resetAllData() {
        this.isAfterSetDataSourceStart = false;
        this.chargeDataRealLength = 0;
        this.mHasSeek = true;
        this.tmepBuf = null;
        this.mCurrentSeekPosition = -1;
        this.mCurrentSeekPositionState = 0;
        this.mCurFileSize = 0;
        if (this.mAudioFileRequestHandler != null) {
            this.mAudioFileRequestHandler.a(AudioFileRequestHandler.f2267a);
            Logger.a(Tag, (Object) "resetAllData mAudioFileRequestHandler.release()");
        } else {
            Logger.a(Tag, (Object) "resetAllData mAudioFileRequestHandler null");
        }
        if (this.mHlsAudioFile != null) {
            this.mHlsAudioFile.f();
            Logger.a(Tag, (Object) "resetAllData mHlsAudioFile.release()");
        } else {
            Logger.a(Tag, (Object) "resetAllData mHlsAudioFile null");
        }
        if (this.mFlvLiveAudioFile != null) {
            this.mFlvLiveAudioFile.a();
            Logger.a(Tag, (Object) "resetAllData mFlvLiveAudioFile.release()");
        } else {
            Logger.a(Tag, (Object) "resetAllData mFlvLiveAudioFile null");
        }
        Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT initAllData 重置0");
    }

    public void pause() {
        Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT pause");
        this.isAfterSetDataSourceStart = false;
        getAudioTrackPlayThread().d();
        MediaplayerPause(this.mJniHandler);
        if (this.mFlvLiveAudioFile != null) {
            this.mFlvLiveAudioFile.a();
        }
    }

    public void release() {
        this.hasRelease = true;
        relaseAudioFile();
        while (MediaplayerReset(this.mJniHandler) < 0) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT release");
        releaseAudioTrackPlayThread();
        resetAllData();
        MediaplayerDestroy(this.mJniHandler);
        this.tmepBuf = null;
        this.mTempo = 1.0f;
        this.mPitch = 0.0f;
        this.mRate = 1.0f;
    }

    public int getCurrentPosition() {
        return (int) MediaplayerGetCurrentTime(this.mJniHandler);
    }

    public int getDuration() {
        return (int) MediaplayerGetMediaDuration(this.mJniHandler);
    }

    public boolean isPlaying() {
        Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT isPlaying");
        return getAudioTrackPlayThread().j();
    }

    public void reset() {
        getAudioTrackPlayThread().f();
        relaseAudioFile();
        Logger.a(Tag, (Object) "reset start");
        this.isReseting = true;
        MediaplayerReset(this.mJniHandler);
        this.isReseting = false;
        Logger.a(Tag, (Object) "reset end");
        resetAllData();
    }

    public void seekTo(int i) {
        if (!isHttpFlv()) {
            String str = Tag;
            Logger.a(str, (Object) "seekParaTimeStampMs msec:" + i);
            this.isSeeking = true;
            MediaplayerSeek((long) i, this.mJniHandler);
            this.isSeeking = false;
            onSeekCompletedInner();
            mOnTimedChangeListenerInner();
            String str2 = Tag;
            Logger.a(str2, (Object) "ttseek2:" + System.currentTimeMillis());
        }
    }

    public synchronized void start() {
        String str = Tag;
        Logger.a(str, (Object) "dataStreamInputFuncCallBackT start mAudioType" + this.mAudioType);
        if (this.mAudioType != AudioType.FLV_FILE || this.isAfterSetDataSourceStart) {
            if (this.mAudioFileRequestHandler != null && this.mAudioFileRequestHandler.b()) {
                this.mAudioFileRequestHandler.c();
            }
            getAudioTrackPlayThread().c();
            MediaplayerPlay(this.mJniHandler);
        } else {
            Logger.a(Tag, (Object) "FLV_FILE start reset");
            reset();
            setDataSource(this.mPlayUrl);
            prepareAsync();
        }
        this.isAfterSetDataSourceStart = false;
    }

    public void setDataSource(String str) {
        setDataSource(str, (String) null);
    }

    public void setPreBufferUrl(String str) {
        if (this.mAudioFileRequestHandler != null) {
            this.mAudioFileRequestHandler.a(str);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00eb, code lost:
        r14 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ec, code lost:
        r0 = r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00eb A[ExcHandler: all (th java.lang.Throwable), Splitter:B:17:0x00d5] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00f4 A[SYNTHETIC, Splitter:B:34:0x00f4] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0100 A[SYNTHETIC, Splitter:B:42:0x0100] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDataSource(java.lang.String r14, java.lang.String r15) {
        /*
            r13 = this;
            r0 = 0
            r13.mAudioFileRequestHandler = r0
            r13.mHlsAudioFile = r0
            r13.mFlvLiveAudioFile = r0
            r1 = 1
            r13.isAfterSetDataSourceStart = r1
            java.lang.String r1 = Tag
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "flushAllDataInSoundTouch setDataSource src:"
            r2.append(r3)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)
            r1 = 0
            r13.chargeDataRealLength = r1
            r13.releaseAudioTrackPlayThread()
            java.lang.String r1 = "totalLength"
            boolean r1 = r14.contains(r1)
            if (r1 == 0) goto L_0x005c
            android.net.Uri r1 = android.net.Uri.parse(r14)
            java.lang.String r2 = "totalLength"
            java.lang.String r1 = r1.getQueryParameter(r2)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x005c
            int r1 = java.lang.Integer.parseInt(r1)
            r13.chargeDataRealLength = r1
            java.lang.String r1 = Tag
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "chargeDataRealLength:"
            r2.append(r3)
            int r3 = r13.chargeDataRealLength
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)
        L_0x005c:
            r13.mPlayUrl = r14
            java.lang.String r14 = r13.mPlayUrl
            java.lang.String r1 = ".flv"
            boolean r14 = r14.contains(r1)
            if (r14 == 0) goto L_0x0087
            com.ximalaya.ting.android.player.liveflv.FlvLiveAudioFile r14 = new com.ximalaya.ting.android.player.liveflv.FlvLiveAudioFile
            r14.<init>(r13)
            r13.mFlvLiveAudioFile = r14
            com.ximalaya.ting.android.player.XMediaplayerJNI$AudioType r14 = com.ximalaya.ting.android.player.XMediaplayerJNI.AudioType.FLV_FILE
            r13.mAudioType = r14
            java.lang.String r1 = r13.mPlayUrl
            com.ximalaya.ting.android.player.XMediaplayerJNI$AudioType r14 = r13.mAudioType
            int r2 = r14.value()
            long r3 = r13.mJniHandler
            r5 = 0
            r7 = 0
            r0 = r13
            r0.MediaplayerSetDataSourceInfo(r1, r2, r3, r5, r7)
            goto L_0x0140
        L_0x0087:
            java.lang.String r14 = r13.mPlayUrl
            java.lang.String r1 = "m3u8"
            boolean r14 = r14.contains(r1)
            if (r14 == 0) goto L_0x00b2
            com.ximalaya.ting.android.player.HlsAudioFile r14 = new com.ximalaya.ting.android.player.HlsAudioFile
            java.lang.String r15 = r13.mPlayUrl
            r14.<init>(r15, r13)
            r13.mHlsAudioFile = r14
            com.ximalaya.ting.android.player.XMediaplayerJNI$AudioType r14 = com.ximalaya.ting.android.player.XMediaplayerJNI.AudioType.M3U8_FILE
            r13.mAudioType = r14
            java.lang.String r1 = r13.mPlayUrl
            com.ximalaya.ting.android.player.XMediaplayerJNI$AudioType r14 = r13.mAudioType
            int r2 = r14.value()
            long r3 = r13.mJniHandler
            r5 = 0
            r7 = 0
            r0 = r13
            r0.MediaplayerSetDataSourceInfo(r1, r2, r3, r5, r7)
            goto L_0x0140
        L_0x00b2:
            java.lang.String r14 = r13.mPlayUrl
            java.lang.String r1 = ".xm"
            boolean r14 = r14.endsWith(r1)
            if (r14 == 0) goto L_0x0119
            com.ximalaya.ting.android.player.AudioFileRequestHandler r14 = new com.ximalaya.ting.android.player.AudioFileRequestHandler
            r14.<init>(r13)
            r13.mAudioFileRequestHandler = r14
            com.ximalaya.ting.android.player.XMediaplayerJNI$AudioType r14 = com.ximalaya.ting.android.player.XMediaplayerJNI.AudioType.XMLY_FORMAT
            r13.mAudioType = r14
            r14 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x00fd, all -> 0x00f1 }
            java.lang.String r2 = r13.mPlayUrl     // Catch:{ IOException -> 0x00fd, all -> 0x00f1 }
            java.lang.String r3 = "r"
            r1.<init>(r2, r3)     // Catch:{ IOException -> 0x00fd, all -> 0x00f1 }
            r0 = 24
            long r2 = (long) r0
            r1.seek(r2)     // Catch:{ IOException -> 0x00ee, all -> 0x00eb }
            double r2 = r1.readDouble()     // Catch:{ IOException -> 0x00ee, all -> 0x00eb }
            double r4 = r1.readDouble()     // Catch:{ IOException -> 0x00ef, all -> 0x00eb }
            r1.close()     // Catch:{ IOException -> 0x00e4 }
            goto L_0x00e8
        L_0x00e4:
            r14 = move-exception
            r14.printStackTrace()
        L_0x00e8:
            r9 = r2
            r11 = r4
            goto L_0x010a
        L_0x00eb:
            r14 = move-exception
            r0 = r1
            goto L_0x00f2
        L_0x00ee:
            r2 = r14
        L_0x00ef:
            r0 = r1
            goto L_0x00fe
        L_0x00f1:
            r14 = move-exception
        L_0x00f2:
            if (r0 == 0) goto L_0x00fc
            r0.close()     // Catch:{ IOException -> 0x00f8 }
            goto L_0x00fc
        L_0x00f8:
            r15 = move-exception
            r15.printStackTrace()
        L_0x00fc:
            throw r14
        L_0x00fd:
            r2 = r14
        L_0x00fe:
            if (r0 == 0) goto L_0x0108
            r0.close()     // Catch:{ IOException -> 0x0104 }
            goto L_0x0108
        L_0x0104:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0108:
            r11 = r14
            r9 = r2
        L_0x010a:
            java.lang.String r5 = r13.mPlayUrl
            com.ximalaya.ting.android.player.XMediaplayerJNI$AudioType r14 = r13.mAudioType
            int r6 = r14.value()
            long r7 = r13.mJniHandler
            r4 = r13
            r4.MediaplayerSetDataSourceInfo(r5, r6, r7, r9, r11)
            goto L_0x0140
        L_0x0119:
            if (r15 != 0) goto L_0x0123
            com.ximalaya.ting.android.player.AudioFileRequestHandler r14 = new com.ximalaya.ting.android.player.AudioFileRequestHandler
            r14.<init>(r13)
            r13.mAudioFileRequestHandler = r14
            goto L_0x012a
        L_0x0123:
            com.ximalaya.ting.android.player.AudioFileRequestHandler r14 = new com.ximalaya.ting.android.player.AudioFileRequestHandler
            r14.<init>(r13, r15)
            r13.mAudioFileRequestHandler = r14
        L_0x012a:
            com.ximalaya.ting.android.player.XMediaplayerJNI$AudioType r14 = com.ximalaya.ting.android.player.XMediaplayerJNI.AudioType.NORMAL_FILE
            r13.mAudioType = r14
            java.lang.String r1 = r13.mPlayUrl
            com.ximalaya.ting.android.player.XMediaplayerJNI$AudioType r14 = r13.mAudioType
            int r2 = r14.value()
            long r3 = r13.mJniHandler
            r5 = 0
            r7 = 0
            r0 = r13
            r0.MediaplayerSetDataSourceInfo(r1, r2, r3, r5, r7)
        L_0x0140:
            java.lang.String r14 = Tag
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r0 = "setDataSource mAudioType:"
            r15.append(r0)
            com.ximalaya.ting.android.player.XMediaplayerJNI$AudioType r0 = r13.mAudioType
            int r0 = r0.value()
            r15.append(r0)
            java.lang.String r15 = r15.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r14, (java.lang.Object) r15)
            com.ximalaya.ting.android.player.PlayCacheByLRU r14 = com.ximalaya.ting.android.player.PlayCacheByLRU.a()
            java.lang.String r15 = r13.mPlayUrl
            r14.a((java.lang.String) r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.XMediaplayerJNI.setDataSource(java.lang.String, java.lang.String):void");
    }

    public void setVolume(float f, float f2) {
        AudioTrackPlayThread audioTrackPlayThread = getAudioTrackPlayThread();
        if (audioTrackPlayThread != null) {
            audioTrackPlayThread.b(f);
        }
    }

    public boolean isHls() {
        return this.mAudioType.value() > 0 && this.mAudioType.value() < 4;
    }

    public boolean isHttpFlv() {
        return this.mAudioType == AudioType.FLV_FILE;
    }

    public int getCachePercent() {
        if (isHttpFlv()) {
            return 0;
        }
        if (isHls()) {
            if (this.mHlsAudioFile == null) {
                return 0;
            }
            return this.mHlsAudioFile.a();
        } else if (this.mAudioFileRequestHandler == null) {
            return 0;
        } else {
            return this.mAudioFileRequestHandler.a();
        }
    }

    static {
        if (PlayerUtil.a()) {
            System.loadLibrary("xmediaplayer_x");
            return;
        }
        boolean b = PlayerUtil.b();
        String str = Tag;
        Logger.a(str, (Object) "loadLibrary xmediaplayer isArmV7Plus:" + b);
        if (b) {
            Log.i("loadLibrary", "xmediaplayerv7");
            System.loadLibrary("xmediaplayerv7");
            return;
        }
        Log.i("loadLibrary", "xmediaplayer");
        System.loadLibrary("xmediaplayer");
    }

    public void handlePlayerStatus(int i) {
        NativeErrorType valueOf = NativeErrorType.valueOf(i);
        String str = Tag;
        Logger.a(str, (Object) "handlePlayerStatus status:" + i);
        if (valueOf != null) {
            switch (valueOf) {
                case ERR_DECODEDATA_FILLIO_FAIL:
                    onErrorInner(8, -1004);
                    return;
                case ERR_DECODE_NOT_SUPPORT:
                    onErrorInner(8, -1010);
                    return;
                case ERR_FILE_MANAGER_INNER_ERR:
                    onErrorInner(8, 100);
                    return;
                case ERR_M3U8STREAM_FILLIO_FAIL:
                    onErrorInner(8, -1004);
                    return;
                case ERR_M3U8_FILE_CONTENT_INVALID:
                    onErrorInner(8, -1004);
                    return;
                case ERR_NOTOK:
                    onErrorInner(8, 1);
                    return;
                case NO_ERR:
                    return;
                case ERR_ARCH_NOT_SUPPORT:
                    onErrorInner(8, XMediaPlayer.g);
                    return;
                default:
                    onErrorInner(8, -1004);
                    return;
            }
        }
    }

    public void onCompletionInner() {
        resetAllData();
        MediaplayerComplete(this.mJniHandler);
        Logger.a(Tag, (Object) "flushAllDataInSoundTouch dataStreamInputFuncCallBackT onCompletionInner");
        releaseAudioTrackPlayThread();
    }

    private synchronized void releaseAudioTrackPlayThread() {
        if (this.mAudioTrackPlayThread != null) {
            this.mAudioTrackPlayThread.h();
            this.mAudioTrackPlayThread = null;
        }
        Logger.a(Tag, (Object) "releaseAudioTrackPlayThread releasePlay");
    }

    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r2v17, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v19, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v21, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v23 */
    /* JADX WARNING: type inference failed for: r2v24 */
    /* JADX WARNING: type inference failed for: r2v25 */
    /* JADX WARNING: type inference failed for: r2v26, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v28 */
    /* JADX WARNING: type inference failed for: r2v30 */
    /* JADX WARNING: type inference failed for: r2v32 */
    /* JADX WARNING: type inference failed for: r2v33 */
    /* JADX WARNING: type inference failed for: r2v35 */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x017a, code lost:
        if (r5 == null) goto L_0x01e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01ac, code lost:
        if (r5 == null) goto L_0x01e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01de, code lost:
        if (r5 == null) goto L_0x01e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x020b  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0217 A[SYNTHETIC, Splitter:B:108:0x0217] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0118 A[SYNTHETIC, Splitter:B:46:0x0118] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int m3u8FileStreamInputFuncCallBackT(com.ximalaya.ting.android.player.model.JNIDataModel r14) {
        /*
            r13 = this;
            com.ximalaya.ting.android.player.XMediaplayerJNI$NativeErrorType r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.NativeErrorType.NO_ERR
            int r1 = r14.status
            com.ximalaya.ting.android.player.XMediaplayerJNI$NativeErrorType r1 = com.ximalaya.ting.android.player.XMediaplayerJNI.NativeErrorType.valueOf((int) r1)
            boolean r0 = r0.equals(r1)
            r1 = -1
            if (r0 != 0) goto L_0x0015
            int r14 = r14.status
            r13.handlePlayerStatus(r14)
            return r1
        L_0x0015:
            if (r14 == 0) goto L_0x027c
            int r0 = r14.bufSize
            if (r0 > 0) goto L_0x001d
            goto L_0x027c
        L_0x001d:
            java.lang.String r0 = Tag
            java.lang.String r2 = "HlsReadThread downUrl0  m3u8FileStreamInputFuncCallBackT"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r2)
            r0 = 1
            r2 = 1
        L_0x0026:
            int r3 = r2 + -1
            r4 = 0
            if (r2 <= 0) goto L_0x0222
            r2 = 0
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch:{ MalformedURLException -> 0x01af, IOException -> 0x017d, Exception -> 0x014b, all -> 0x0147 }
            java.lang.String r6 = r13.mPlayUrl     // Catch:{ MalformedURLException -> 0x01af, IOException -> 0x017d, Exception -> 0x014b, all -> 0x0147 }
            r5[r4] = r6     // Catch:{ MalformedURLException -> 0x01af, IOException -> 0x017d, Exception -> 0x014b, all -> 0x0147 }
            r6 = 2
            java.lang.String r7 = "GET"
            java.net.HttpURLConnection r5 = com.ximalaya.ting.android.player.PlayerUtil.a(r5, r2, r6, r4, r7)     // Catch:{ MalformedURLException -> 0x01af, IOException -> 0x017d, Exception -> 0x014b, all -> 0x0147 }
            if (r5 != 0) goto L_0x0057
            java.lang.String r2 = Tag
            java.lang.String r4 = "m3u8FileStreamInputFuncCallBackT tt 0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r4)
            if (r5 == 0) goto L_0x0047
            r5.disconnect()
        L_0x0047:
            java.lang.String r2 = Tag
            java.lang.String r4 = "m3u8FileStreamInputFuncCallBackT tt 1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r4)
            java.lang.String r2 = Tag
            java.lang.String r4 = "m3u8FileStreamInputFuncCallBackT tt 2"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r4)
            goto L_0x01fb
        L_0x0057:
            int r6 = r5.getResponseCode()     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            java.lang.String r7 = Tag     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            r8.<init>()     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            java.lang.String r9 = "m3u8FileStreamInputFuncCallBackT 0 responseCode:"
            r8.append(r9)     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            r8.append(r6)     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            java.lang.String r8 = r8.toString()     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r7, (java.lang.Object) r8)     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            r7 = 200(0xc8, float:2.8E-43)
            if (r6 == r7) goto L_0x00af
            r7 = 206(0xce, float:2.89E-43)
            if (r6 != r7) goto L_0x007a
            goto L_0x00af
        L_0x007a:
            r7 = 400(0x190, float:5.6E-43)
            if (r6 < r7) goto L_0x0103
            java.lang.String r7 = Tag     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            r8.<init>()     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            java.lang.String r9 = "m3u8FileStreamInputFuncCallBackT HTTP_BAD_REQUEST responseCode:"
            r8.append(r9)     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            r8.append(r6)     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            java.lang.String r6 = r8.toString()     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r7, (java.lang.Object) r6)     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            java.lang.String r14 = Tag
            java.lang.String r0 = "m3u8FileStreamInputFuncCallBackT tt 0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r14, (java.lang.Object) r0)
            if (r5 == 0) goto L_0x00a0
            r5.disconnect()
        L_0x00a0:
            java.lang.String r14 = Tag
            java.lang.String r0 = "m3u8FileStreamInputFuncCallBackT tt 1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r14, (java.lang.Object) r0)
            java.lang.String r14 = Tag
            java.lang.String r0 = "m3u8FileStreamInputFuncCallBackT tt 2"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r14, (java.lang.Object) r0)
            return r1
        L_0x00af:
            java.lang.String r6 = Tag     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            java.lang.String r7 = "m3u8FileStreamInputFuncCallBackT 1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r6, (java.lang.Object) r7)     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            java.io.InputStream r6 = r5.getInputStream()     // Catch:{ MalformedURLException -> 0x0142, IOException -> 0x013d, Exception -> 0x0138, all -> 0x0135 }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            r2.<init>()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
        L_0x00c3:
            int r8 = r6.read(r7)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            if (r8 == r1) goto L_0x00e3
            r2.write(r7, r4, r8)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            java.lang.String r9 = Tag     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            r10.<init>()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            java.lang.String r11 = "m3u8FileStreamInputFuncCallBackT len:"
            r10.append(r11)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            r10.append(r8)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            java.lang.String r8 = r10.toString()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r9, (java.lang.Object) r8)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            goto L_0x00c3
        L_0x00e3:
            java.lang.String r7 = Tag     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            r9.<init>()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            java.lang.String r10 = "m3u8FileStreamInputFuncCallBackT len000:"
            r9.append(r10)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            r9.append(r8)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            java.lang.String r8 = r9.toString()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r7, (java.lang.Object) r8)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            byte[] r7 = r2.toByteArray()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            r14.buf = r7     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            r2.close()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x0127, Exception -> 0x0121, all -> 0x011d }
            r2 = r6
        L_0x0103:
            java.lang.String r6 = Tag
            java.lang.String r7 = "m3u8FileStreamInputFuncCallBackT tt 0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r6, (java.lang.Object) r7)
            if (r5 == 0) goto L_0x010f
            r5.disconnect()
        L_0x010f:
            java.lang.String r5 = Tag
            java.lang.String r6 = "m3u8FileStreamInputFuncCallBackT tt 1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r5, (java.lang.Object) r6)
            if (r2 == 0) goto L_0x01e3
            r2.close()     // Catch:{ IOException -> 0x01e3 }
            goto L_0x01e3
        L_0x011d:
            r14 = move-exception
            r2 = r6
            goto L_0x0202
        L_0x0121:
            r2 = move-exception
            r12 = r6
            r6 = r2
            r2 = r5
            r5 = r12
            goto L_0x014d
        L_0x0127:
            r2 = move-exception
            r12 = r6
            r6 = r2
            r2 = r5
            r5 = r12
            goto L_0x017f
        L_0x012e:
            r2 = move-exception
            r12 = r6
            r6 = r2
            r2 = r5
            r5 = r12
            goto L_0x01b1
        L_0x0135:
            r14 = move-exception
            goto L_0x0202
        L_0x0138:
            r6 = move-exception
            r12 = r5
            r5 = r2
            r2 = r12
            goto L_0x014d
        L_0x013d:
            r6 = move-exception
            r12 = r5
            r5 = r2
            r2 = r12
            goto L_0x017f
        L_0x0142:
            r6 = move-exception
            r12 = r5
            r5 = r2
            r2 = r12
            goto L_0x01b1
        L_0x0147:
            r14 = move-exception
            r5 = r2
            goto L_0x0202
        L_0x014b:
            r6 = move-exception
            r5 = r2
        L_0x014d:
            java.lang.String r7 = Tag     // Catch:{ all -> 0x01fe }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01fe }
            r8.<init>()     // Catch:{ all -> 0x01fe }
            java.lang.String r9 = "m3u8FileStreamInputFuncCallBackT Exception:"
            r8.append(r9)     // Catch:{ all -> 0x01fe }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x01fe }
            r8.append(r6)     // Catch:{ all -> 0x01fe }
            java.lang.String r6 = r8.toString()     // Catch:{ all -> 0x01fe }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r7, (java.lang.Object) r6)     // Catch:{ all -> 0x01fe }
            java.lang.String r6 = Tag
            java.lang.String r7 = "m3u8FileStreamInputFuncCallBackT tt 0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r6, (java.lang.Object) r7)
            if (r2 == 0) goto L_0x0173
            r2.disconnect()
        L_0x0173:
            java.lang.String r2 = Tag
            java.lang.String r6 = "m3u8FileStreamInputFuncCallBackT tt 1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r6)
            if (r5 == 0) goto L_0x01e3
            goto L_0x01e0
        L_0x017d:
            r6 = move-exception
            r5 = r2
        L_0x017f:
            java.lang.String r7 = Tag     // Catch:{ all -> 0x01fe }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01fe }
            r8.<init>()     // Catch:{ all -> 0x01fe }
            java.lang.String r9 = "m3u8FileStreamInputFuncCallBackT IOException:"
            r8.append(r9)     // Catch:{ all -> 0x01fe }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x01fe }
            r8.append(r6)     // Catch:{ all -> 0x01fe }
            java.lang.String r6 = r8.toString()     // Catch:{ all -> 0x01fe }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r7, (java.lang.Object) r6)     // Catch:{ all -> 0x01fe }
            java.lang.String r6 = Tag
            java.lang.String r7 = "m3u8FileStreamInputFuncCallBackT tt 0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r6, (java.lang.Object) r7)
            if (r2 == 0) goto L_0x01a5
            r2.disconnect()
        L_0x01a5:
            java.lang.String r2 = Tag
            java.lang.String r6 = "m3u8FileStreamInputFuncCallBackT tt 1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r6)
            if (r5 == 0) goto L_0x01e3
            goto L_0x01e0
        L_0x01af:
            r6 = move-exception
            r5 = r2
        L_0x01b1:
            java.lang.String r7 = Tag     // Catch:{ all -> 0x01fe }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01fe }
            r8.<init>()     // Catch:{ all -> 0x01fe }
            java.lang.String r9 = "m3u8FileStreamInputFuncCallBackT MalformedURLException:"
            r8.append(r9)     // Catch:{ all -> 0x01fe }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x01fe }
            r8.append(r6)     // Catch:{ all -> 0x01fe }
            java.lang.String r6 = r8.toString()     // Catch:{ all -> 0x01fe }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r7, (java.lang.Object) r6)     // Catch:{ all -> 0x01fe }
            java.lang.String r6 = Tag
            java.lang.String r7 = "m3u8FileStreamInputFuncCallBackT tt 0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r6, (java.lang.Object) r7)
            if (r2 == 0) goto L_0x01d7
            r2.disconnect()
        L_0x01d7:
            java.lang.String r2 = Tag
            java.lang.String r6 = "m3u8FileStreamInputFuncCallBackT tt 1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r6)
            if (r5 == 0) goto L_0x01e3
        L_0x01e0:
            r5.close()     // Catch:{ IOException -> 0x01e3 }
        L_0x01e3:
            java.lang.String r2 = Tag
            java.lang.String r5 = "m3u8FileStreamInputFuncCallBackT tt 2"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r5)
            byte[] r2 = r14.buf
            if (r2 == 0) goto L_0x01fb
            byte[] r2 = r14.buf
            int r2 = r2.length
            if (r2 <= 0) goto L_0x01fb
            java.lang.String r0 = Tag
            java.lang.String r1 = "m3u8FileStreamInputFuncCallBackT tt 3"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
            goto L_0x0222
        L_0x01fb:
            r2 = r3
            goto L_0x0026
        L_0x01fe:
            r14 = move-exception
            r12 = r5
            r5 = r2
            r2 = r12
        L_0x0202:
            java.lang.String r0 = Tag
            java.lang.String r1 = "m3u8FileStreamInputFuncCallBackT tt 0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
            if (r5 == 0) goto L_0x020e
            r5.disconnect()
        L_0x020e:
            java.lang.String r0 = Tag
            java.lang.String r1 = "m3u8FileStreamInputFuncCallBackT tt 1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
            if (r2 == 0) goto L_0x021a
            r2.close()     // Catch:{ IOException -> 0x021a }
        L_0x021a:
            java.lang.String r0 = Tag
            java.lang.String r1 = "m3u8FileStreamInputFuncCallBackT tt 2"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
            throw r14
        L_0x0222:
            java.lang.String r0 = Tag
            java.lang.String r1 = "m3u8FileStreamInputFuncCallBackT tt 4"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
            byte[] r0 = r14.buf
            if (r0 == 0) goto L_0x0274
            byte[] r0 = r14.buf
            int r0 = r0.length
            int r1 = r14.bufSize
            if (r0 > r1) goto L_0x0274
            byte[] r0 = r14.buf
            int r0 = r0.length
            if (r0 != 0) goto L_0x023a
            goto L_0x0274
        L_0x023a:
            java.lang.String r0 = Tag
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "m3u8FileStreamInputFuncCallBackT contentLength 5:"
            r1.append(r2)
            byte[] r2 = r14.buf
            int r2 = r2.length
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
            java.lang.String r0 = Tag
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "m3u8FileStreamInputFuncCallBackT buf:"
            r1.append(r2)
            java.lang.String r2 = new java.lang.String
            byte[] r3 = r14.buf
            r2.<init>(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
            byte[] r14 = r14.buf
            int r14 = r14.length
            return r14
        L_0x0274:
            java.lang.String r14 = Tag
            java.lang.String r0 = "m3u8FileStreamInputFuncCallBackT null end 4"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r14, (java.lang.Object) r0)
            return r4
        L_0x027c:
            java.lang.String r14 = Tag
            java.lang.String r0 = "m3u8FileStreamInputFuncCallBackT tJNIDataModel null"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r14, (java.lang.Object) r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.XMediaplayerJNI.m3u8FileStreamInputFuncCallBackT(com.ximalaya.ting.android.player.model.JNIDataModel):int");
    }

    public int m3u8ParsedNewMediaItemInfoFuncCallBackT(String[] strArr, int i, int i2) {
        if (!NativeErrorType.NO_ERR.equals(NativeErrorType.valueOf(i2))) {
            Logger.a(Tag, (Object) "m3u8ParsedNewMediaItemInfoFuncCallBackT onErrorInner");
            handlePlayerStatus(i2);
            return -1;
        } else if (this.mHlsAudioFile == null) {
            return -1;
        } else {
            String str = Tag;
            Logger.a(str, (Object) "HlsReadThread downUrl0 m3u8ParsedNewMediaItemInfoFuncCallBackT length:" + strArr.length);
            int MediaplayerGetCurPlayingFileType = MediaplayerGetCurPlayingFileType(this.mJniHandler);
            if (MediaplayerGetCurPlayingFileType >= 0) {
                this.mAudioType = AudioType.valueOf(MediaplayerGetCurPlayingFileType);
                String str2 = Tag;
                Logger.a(str2, (Object) "m3u8ParsedNewMediaItemInfoFuncCallBackT mAudioType0:" + this.mAudioType.value());
            } else {
                onErrorInner(8, 1);
                Logger.a(Tag, (Object) "m3u8ParsedNewMediaItemInfoFuncCallBackT mAudioType0 error");
            }
            this.mHlsAudioFile.a(strArr);
            return 0;
        }
    }

    public int dataStreamInputFunCallBackT(JNIDataModel jNIDataModel) {
        if (!NativeErrorType.NO_ERR.equals(NativeErrorType.valueOf(jNIDataModel.status))) {
            Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT readData start error:");
            handlePlayerStatus(jNIDataModel.status);
            return -1;
        } else if (this.isReseting) {
            return -2;
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT readData start:" + currentTimeMillis);
            if (jNIDataModel.filePath != null) {
                Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT fill io start");
                if (!jNIDataModel.filePath.equals(getCurPlayUrl())) {
                    this.mCurrentSeekPosition = -1;
                    this.mCurFileSize = 0;
                    this.mCurrentSeekPositionState = 0;
                    this.mHasSeek = true;
                    this.tmepBuf = null;
                    Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT 重置");
                }
                Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT filePath:" + jNIDataModel.filePath);
                Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT seekParaTimeStampMs mCurrentSeekPositionState:" + this.mCurrentSeekPositionState);
                Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT seekParaTimeStampMs mCurrentSeekPosition:" + this.mCurrentSeekPosition);
                if (!this.mHasSeek || this.mCurrentSeekPosition == this.mCurrentSeekPositionState) {
                    this.mHasSeek = false;
                } else {
                    this.mCurrentSeekPosition = this.mCurrentSeekPositionState;
                    Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT seekParaTimeStampMs true mCurrentSeekPosition:" + this.mCurrentSeekPosition);
                    Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT length diff seek true");
                }
                Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT mCurFileSize:" + this.mCurFileSize + "mCurrentSeekPosition:" + this.mCurrentSeekPosition);
                if (this.mCurFileSize <= 0 || ((long) this.mCurrentSeekPosition) < this.mCurFileSize || isHttpFlv()) {
                    if (isHls()) {
                        if (this.mHlsAudioFile != null) {
                            if (this.mHasSeek || this.tmepBuf == null) {
                                this.tmepBuf = null;
                                Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT M3U8_FILE");
                                if (this.mHlsAudioFile.a(jNIDataModel) < 0) {
                                    return -1;
                                }
                                this.mCurFileSize = jNIDataModel.fileSize;
                            } else {
                                jNIDataModel.buf = this.tmepBuf;
                                this.tmepBuf = null;
                                jNIDataModel.fileSize = this.mCurFileSize;
                                Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT read temp buf");
                            }
                            this.mHasSeek = false;
                        } else if (!XMediaPlayerConstants.f2293a) {
                            Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT xx 21");
                            return 0;
                        } else {
                            throw new RuntimeException("mHlsAudioFile==null");
                        }
                    } else if (!isHttpFlv()) {
                        Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT NORMAL_FILE");
                        if (this.mAudioFileRequestHandler != null) {
                            int a2 = this.mAudioFileRequestHandler.a(jNIDataModel, this.mHasSeek, this.mCurrentSeekPosition);
                            if (a2 > 0) {
                                Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT NORMAL_FILE result > 0");
                                this.mHasSeek = false;
                            } else {
                                Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT NORMAL_FILE result == " + a2);
                                return a2;
                            }
                        } else if (!XMediaPlayerConstants.f2293a) {
                            Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT xx 19 return 0 over");
                            return 0;
                        } else {
                            throw new RuntimeException("mAudioFileRequestHandler==null");
                        }
                    } else if (this.mFlvLiveAudioFile != null) {
                        int a3 = this.mFlvLiveAudioFile.a(jNIDataModel);
                        if (a3 < 0) {
                            Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT mFlvLiveAudioFile result < 0");
                            return a3;
                        } else if (a3 == 0) {
                            Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT mFlvLiveAudioFile result == 0");
                            return 0;
                        }
                    } else if (!XMediaPlayerConstants.f2293a) {
                        return 0;
                    } else {
                        throw new RuntimeException("mFlvLiveAudioFile==null");
                    }
                    if (jNIDataModel.buf == null) {
                        Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT xx tJNIDataModel.buf==null");
                        return -1;
                    }
                    Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT buf.length" + jNIDataModel.buf.length);
                    Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT mCurFileSize:" + this.mCurFileSize);
                    Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT tJNIDataModel.buf.length:" + jNIDataModel.buf.length);
                    Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT tJNIDataModel.bufSize:" + jNIDataModel.bufSize);
                    handleSmallBufRequest(jNIDataModel);
                    if (jNIDataModel.buf != null) {
                        this.mCurrentSeekPosition += jNIDataModel.buf.length;
                        Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT tJNIDataModel.buf.length:" + jNIDataModel.buf.length);
                    }
                    Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT mCurFileSize2:" + this.mCurFileSize);
                    Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT tJNIDataModel filesize:" + jNIDataModel.fileSize);
                    Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT xx seekParaTimeStampMs fill io end");
                    Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT readData end:" + (System.currentTimeMillis() - currentTimeMillis));
                    return jNIDataModel.buf.length;
                }
                Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT xx return 0");
                return 0;
            }
            throw new RuntimeException("dataStreamInputFuncCallBackT tJNIDataModel.filePath==null 异常！！！");
        }
    }

    private void handleSmallBufRequest(JNIDataModel jNIDataModel) {
        if (jNIDataModel.buf.length > jNIDataModel.bufSize) {
            ByteBuffer wrap = ByteBuffer.wrap(jNIDataModel.buf);
            wrap.clear();
            wrap.position(jNIDataModel.bufSize).limit(jNIDataModel.buf.length);
            ByteBuffer slice = wrap.slice();
            this.tmepBuf = new byte[slice.remaining()];
            slice.get(this.tmepBuf);
            String str = Tag;
            Logger.a(str, (Object) "dataStreamInputFuncCallBackT length diff tJNIDataModel.bufSize:" + jNIDataModel.bufSize);
            String str2 = Tag;
            Logger.a(str2, (Object) "dataStreamInputFuncCallBackT length diff tJNIDataModel.buf:" + jNIDataModel.buf.length);
            if (this.tmepBuf != null) {
                String str3 = Tag;
                Logger.a(str3, (Object) "dataStreamInputFuncCallBackT length diff tmepBuf:" + this.tmepBuf.length);
            }
            wrap.clear();
            wrap.position(0).limit(jNIDataModel.bufSize);
            ByteBuffer slice2 = wrap.slice();
            jNIDataModel.buf = new byte[slice2.remaining()];
            slice2.get(jNIDataModel.buf);
            String str4 = Tag;
            Logger.a(str4, (Object) "dataStreamInputFuncCallBackT length diff tJNIDataModel.buf:" + jNIDataModel.buf.length);
            if (this.tmepBuf != null) {
                String str5 = Tag;
                Logger.a(str5, (Object) "dataStreamInputFuncCallBackT length diff all1:" + (jNIDataModel.buf.length + this.tmepBuf.length));
            }
        }
    }

    public int dataStreamSeekFuncCallBackT(String str, long j, int i) {
        String str2 = Tag;
        Logger.a(str2, (Object) "dataStreamSeekFuncCallBackT 0 offset:" + j);
        if (!NativeErrorType.NO_ERR.equals(NativeErrorType.valueOf(i))) {
            handlePlayerStatus(i);
            return -1;
        }
        if (!TextUtils.isEmpty(str)) {
            this.mCurrentDataDecodeUrl = str;
        }
        this.mHasSeek = true;
        this.mCurrentSeekPositionState = (int) j;
        String str3 = Tag;
        Logger.a(str3, (Object) "dataStreamInputFuncCallBackT seekParaTimeStampMs true offset:" + j);
        return 0;
    }

    public int dataStreamOutReadyFuncCallBackT(int i, int i2) {
        Logger.a(Tag, (Object) "dataStreamOutReadyFuncCallBackT");
        if (!NativeErrorType.NO_ERR.equals(NativeErrorType.valueOf(i2))) {
            handlePlayerStatus(i2);
            return -1;
        }
        onPreparedInner();
        return 0;
    }

    public int bufferedDataReachThresholdCallBackT(int i) {
        if (!NativeErrorType.NO_ERR.equals(NativeErrorType.valueOf(i))) {
            handlePlayerStatus(i);
            return -1;
        }
        getAudioTrackPlayThread().b();
        Logger.a(Tag, (Object) "dataStreamInputFuncCallBackT bufferedDataReachThresholdCallBackT");
        return 1;
    }

    public enum AudioType {
        NORMAL_FILE(0),
        M3U8_STATIC_FILE(1),
        HLS_FILE(2),
        M3U8_FILE(3),
        XMLY_FORMAT(4),
        FLV_FILE(5);
        
        private int value;

        private AudioType(int i) {
            this.value = 0;
            this.value = i;
        }

        public static AudioType valueOf(int i) {
            switch (i) {
                case 0:
                    return NORMAL_FILE;
                case 1:
                    return M3U8_STATIC_FILE;
                case 2:
                    return HLS_FILE;
                case 3:
                    return M3U8_FILE;
                case 4:
                    return XMLY_FORMAT;
                case 5:
                    return FLV_FILE;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }
    }

    public enum NativeErrorType {
        NO_ERR(0),
        ERR_NOTOK(-1),
        ERR_DECODE_NOT_SUPPORT(-2),
        ERR_M3U8_FILE_CONTENT_INVALID(-3),
        ERR_FILE_MANAGER_INNER_ERR(-4),
        ERR_DECODEDATA_FILLIO_FAIL(-5),
        ERR_M3U8STREAM_FILLIO_FAIL(-6),
        ERR_ARCH_NOT_SUPPORT(-7),
        ERR_LIB_NOT_LOADED(-8),
        ERR_XMLY_DEC_ERR(-9),
        ERR_FLV_DEC_ERR(-10);
        
        private int value;

        private NativeErrorType(int i) {
            this.value = 0;
            this.value = i;
        }

        public static NativeErrorType valueOf(int i) {
            switch (i) {
                case -10:
                    return ERR_FLV_DEC_ERR;
                case -9:
                    return ERR_XMLY_DEC_ERR;
                case -8:
                    return ERR_LIB_NOT_LOADED;
                case -7:
                    return ERR_ARCH_NOT_SUPPORT;
                case -6:
                    return ERR_M3U8STREAM_FILLIO_FAIL;
                case -5:
                    return ERR_DECODEDATA_FILLIO_FAIL;
                case -4:
                    return ERR_FILE_MANAGER_INNER_ERR;
                case -3:
                    return ERR_M3U8_FILE_CONTENT_INVALID;
                case -2:
                    return ERR_DECODE_NOT_SUPPORT;
                case -1:
                    return ERR_NOTOK;
                case 0:
                    return NO_ERR;
                default:
                    return ERR_NOTOK;
            }
        }

        public int value() {
            return this.value;
        }
    }

    public int getChargeDataRealLength() {
        return this.chargeDataRealLength;
    }

    public void setTempo(float f) {
        this.mTempo = f;
        if (this.mAudioTrackPlayThread != null) {
            this.mAudioTrackPlayThread.a(f);
        }
    }

    public void setSoundTouchAllParams(float f, float f2, float f3) {
        this.mTempo = f;
        this.mPitch = f2;
        this.mRate = f3;
        if (this.mAudioTrackPlayThread != null) {
            this.mAudioTrackPlayThread.a(f, f2, f3);
        }
    }

    public void setOnPlayDataOutputListener(XMediaPlayer.OnPlayDataOutputListener onPlayDataOutputListener) {
        this.mOnPlayDataOutputListener = onPlayDataOutputListener;
    }
}
