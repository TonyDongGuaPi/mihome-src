package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.brentvatne.react.ReactVideoView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.exoplayer2.util.MimeTypes;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.plugin.rn.RNEventReceiver;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.AudioConvert;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnCallbackMapUtil;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class MIOTAudioModule extends MIOTBaseJavaModule {
    public static final int COMPRESSED_AUDIO_FILE_BIT_RATE = 64000;
    public static final String COMPRESSED_AUDIO_FILE_MIME_TYPE = "audio/mp4a-latm";
    public static final int MIN_VOLUME = 10;
    private static final short MSG_PLAYER_UPDATE_TIME = 1;
    public static final int SAMPLING_RATE = 48000;
    private String mAudioPlayerUid = "";
    /* access modifiers changed from: private */
    public AudioRecord mAudioRecord = null;
    /* access modifiers changed from: private */
    public AudioTrack mAudioTrack;
    /* access modifiers changed from: private */
    public Handler mHandler = null;
    /* access modifiers changed from: private */
    public boolean mIsAudioRecording = false;
    /* access modifiers changed from: private */
    public boolean mIsAudioTrackPlaying = false;
    private boolean mIsMediaRecording = false;
    /* access modifiers changed from: private */
    public MediaPlayer mMediaPlayer = new MediaPlayer();
    private MediaRecorder mMediaRecorder = null;
    /* access modifiers changed from: private */
    public boolean mShouldSendPlayerTime = false;

    public interface AudioSupport {
        void a();
    }

    public String getName() {
        return "MIOTAudio";
    }

    public MIOTAudioModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private void initHandler() {
        if (this.mHandler == null) {
            this.mHandler = new Handler() {
                public void handleMessage(Message message) {
                    if (MIOTAudioModule.this.mShouldSendPlayerTime && message.what == 1 && MIOTAudioModule.this.mMediaPlayer != null && MIOTAudioModule.this.mMediaPlayer.isPlaying()) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putInt(ReactVideoView.EVENT_PROP_CURRENT_TIME, MIOTAudioModule.this.mMediaPlayer.getCurrentPosition() / 1000);
                        MIOTAudioModule.this.sendPlayerEventToJs(createMap, RNEventReceiver.AUDIO_UPDATE_AUDIOPLAYER_TIME);
                        MIOTAudioModule.this.mHandler.sendEmptyMessageDelayed(1, 1000);
                    }
                }
            };
        }
    }

    @Nullable
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("isAbleToRecord", Boolean.valueOf(getReactApplicationContext().checkPermission("android.permission.RECORD_AUDIO", Process.myPid(), Process.myUid()) == 0));
        return hashMap;
    }

    @ReactMethod
    public void startRecord(String str, ReadableMap readableMap, Callback callback) {
        if (TextUtils.isEmpty(str)) {
            callback.invoke(false, "audioName is empty...");
        } else if ("audioRecord".equals(MIOTUtils.a(readableMap, "RecordType", "mediaRecord"))) {
            try {
                startAudioRecord(str, readableMap);
                callback.invoke(true);
            } catch (Exception e) {
                e.printStackTrace();
                callback.invoke(false, e.toString());
            }
        } else {
            try {
                startMediaRecorder(str, readableMap);
                callback.invoke(true);
            } catch (Exception e2) {
                e2.printStackTrace();
                callback.invoke(false, e2.toString());
            }
        }
    }

    @ReactMethod
    public void stopRecord(Callback callback) {
        try {
            stopMediaRecorder();
            stopAudioRecord();
            callback.invoke(true);
        } catch (Exception e) {
            callback.invoke(false, e.toString());
        }
    }

    @ReactMethod
    public void getRecordingPeakPower(Callback callback) {
        int i;
        if (callback != null) {
            if (this.mMediaRecorder == null) {
                callback.invoke(false, RnCallbackMapUtil.a("MediaRecorder is not init..."));
            } else if (!this.mIsMediaRecording) {
                callback.invoke(false, RnCallbackMapUtil.a("MediaRecorder is not recording..."));
            } else {
                try {
                    i = this.mMediaRecorder.getMaxAmplitude();
                } catch (IllegalStateException e) {
                    RnPluginLog.b(e.toString());
                    i = 0;
                }
                callback.invoke(true, RnCallbackMapUtil.a((Object) Integer.valueOf(i)));
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01d3, code lost:
        if (r2.equals("audioQualityMin") == false) goto L_0x01f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01d5, code lost:
        r16 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01de, code lost:
        if (r2.equals("audioQualityMax") == false) goto L_0x01f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01e0, code lost:
        r16 = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01e9, code lost:
        if (r2.equals("audioQualityLow") == false) goto L_0x01f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01eb, code lost:
        r16 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01f4, code lost:
        if (r2.equals("Medium") == false) goto L_0x01f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01f6, code lost:
        r16 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01f9, code lost:
        r16 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01fb, code lost:
        switch(r16) {
            case 0: goto L_0x0223;
            case 1: goto L_0x0223;
            case 2: goto L_0x0204;
            case 3: goto L_0x0204;
            case 4: goto L_0x0204;
            case 5: goto L_0x0204;
            case 6: goto L_0x0213;
            case 7: goto L_0x0213;
            case 8: goto L_0x0213;
            case 9: goto L_0x0213;
            default: goto L_0x01fe;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01fe, code lost:
        r1.mMediaRecorder.setAudioEncoder(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0206, code lost:
        if (android.os.Build.VERSION.SDK_INT < 16) goto L_0x020e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0208, code lost:
        r1.mMediaRecorder.setAudioEncoder(5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x020e, code lost:
        r1.mMediaRecorder.setAudioEncoder(3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0215, code lost:
        if (android.os.Build.VERSION.SDK_INT < 16) goto L_0x021d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0217, code lost:
        r1.mMediaRecorder.setAudioEncoder(4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x021d, code lost:
        r1.mMediaRecorder.setAudioEncoder(3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0223, code lost:
        r1.mMediaRecorder.setAudioEncoder(3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0228, code lost:
        r1.mMediaRecorder.setOnErrorListener(new com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule.AnonymousClass2(r1));
        r1.mMediaRecorder.setOutputFile(r3.getAbsolutePath());
        r1.mMediaRecorder.prepare();
        r1.mMediaRecorder.start();
        r1.mIsMediaRecording = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0247, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x017c, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x017e, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x017f, code lost:
        if (r0 != false) goto L_0x0228;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0185, code lost:
        switch(r2.hashCode()) {
            case -1994163307: goto L_0x01ee;
            case -1636599221: goto L_0x01e3;
            case -1636598693: goto L_0x01d8;
            case -1636598455: goto L_0x01cd;
            case 76596: goto L_0x01c2;
            case 77124: goto L_0x01b7;
            case 77362: goto L_0x01ac;
            case 2249154: goto L_0x01a1;
            case 560283934: goto L_0x0196;
            case 804906379: goto L_0x018a;
            default: goto L_0x0188;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0190, code lost:
        if (r2.equals("audioQualityHigh") == false) goto L_0x01f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0192, code lost:
        r16 = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x019c, code lost:
        if (r2.equals("audioQualityMedium") == false) goto L_0x01f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x019e, code lost:
        r16 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01a7, code lost:
        if (r2.equals("High") == false) goto L_0x01f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01a9, code lost:
        r16 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01b2, code lost:
        if (r2.equals("Min") == false) goto L_0x01f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01b4, code lost:
        r16 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01bd, code lost:
        if (r2.equals("Max") == false) goto L_0x01f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01bf, code lost:
        r16 = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01c8, code lost:
        if (r2.equals("Low") == false) goto L_0x01f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01ca, code lost:
        r16 = 4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void startMediaRecorder(java.lang.String r18, com.facebook.react.bridge.ReadableMap r19) throws java.lang.Exception {
        /*
            r17 = this;
            r1 = r17
            r2 = r19
            java.lang.String r0 = r17.getPathName(r18)
            java.lang.String r3 = "ReactNativeJS"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "录音路径"
            r4.append(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.c(r3, r4)
            java.io.File r3 = new java.io.File
            r3.<init>(r0)
            boolean r0 = r3.exists()
            if (r0 == 0) goto L_0x002c
            r3.delete()
        L_0x002c:
            r3.createNewFile()
            r17.stopMediaRecorder()     // Catch:{ Throwable -> 0x0033 }
            goto L_0x003e
        L_0x0033:
            r0 = move-exception
            r4 = r0
            java.lang.String r0 = "ReactNativeJS"
            java.lang.String r4 = android.util.Log.getStackTraceString(r4)
            com.xiaomi.smarthome.framework.log.LogUtil.b(r0, r4)
        L_0x003e:
            android.media.MediaRecorder r0 = new android.media.MediaRecorder
            r0.<init>()
            r1.mMediaRecorder = r0
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r4 = 1
            r0.setAudioSource(r4)
            java.lang.String r0 = "AVSampleRateKey"
            int r0 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.d(r2, r0)
            java.lang.String r5 = "AVNumberOfChannelsKey"
            int r5 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.d(r2, r5)
            java.lang.String r6 = "AVLinearPCMBitDepthKey"
            int r6 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.d(r2, r6)
            java.lang.String r7 = "AVFormatIDKey"
            java.lang.String r7 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableMap) r2, (java.lang.String) r7)
            java.lang.String r8 = "AVEncoderAudioQualityKey"
            java.lang.String r2 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableMap) r2, (java.lang.String) r8)
            android.media.MediaRecorder r8 = r1.mMediaRecorder
            if (r0 != 0) goto L_0x0070
            r0 = 44100(0xac44, float:6.1797E-41)
        L_0x0070:
            r8.setAudioSamplingRate(r0)
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r8 = 16
            if (r6 != 0) goto L_0x007b
            r6 = 16
        L_0x007b:
            r0.setAudioEncodingBitRate(r6)
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r6 = 2
            if (r5 != 0) goto L_0x0084
            r5 = 2
        L_0x0084:
            r0.setAudioChannels(r5)
            int r0 = r7.hashCode()
            r9 = 7
            r10 = 9
            r11 = -1
            r12 = 6
            r13 = 5
            r14 = 4
            r15 = 3
            r5 = 0
            switch(r0) {
                case -1186453540: goto L_0x013c;
                case -1061120799: goto L_0x0132;
                case -853164747: goto L_0x0127;
                case -852999096: goto L_0x011c;
                case -295359931: goto L_0x0112;
                case 64593: goto L_0x0107;
                case 64934: goto L_0x00fd;
                case 739019185: goto L_0x00f3;
                case 955522415: goto L_0x00e8;
                case 981198802: goto L_0x00dd;
                case 1080855156: goto L_0x00d1;
                case 1465057384: goto L_0x00c6;
                case 1465223035: goto L_0x00bb;
                case 1934542852: goto L_0x00b0;
                case 2036425156: goto L_0x00a4;
                case 2036425497: goto L_0x0099;
                default: goto L_0x0097;
            }
        L_0x0097:
            goto L_0x0147
        L_0x0099:
            java.lang.String r0 = "audioFormatAMR"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 0
            goto L_0x0148
        L_0x00a4:
            java.lang.String r0 = "audioFormatAC3"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 12
            goto L_0x0148
        L_0x00b0:
            java.lang.String r0 = "AMR_WB"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 3
            goto L_0x0148
        L_0x00bb:
            java.lang.String r0 = "audioFormatMPEG4HVXC"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 6
            goto L_0x0148
        L_0x00c6:
            java.lang.String r0 = "audioFormatMPEG4CELP"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 5
            goto L_0x0148
        L_0x00d1:
            java.lang.String r0 = "MPEG4AAC"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 8
            goto L_0x0148
        L_0x00dd:
            java.lang.String r0 = "MPEG4TwinVQ"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 11
            goto L_0x0148
        L_0x00e8:
            java.lang.String r0 = "60958AC3"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 15
            goto L_0x0148
        L_0x00f3:
            java.lang.String r0 = "audioFormatAMR_WB"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 2
            goto L_0x0148
        L_0x00fd:
            java.lang.String r0 = "AMR"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 1
            goto L_0x0148
        L_0x0107:
            java.lang.String r0 = "AC3"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 14
            goto L_0x0148
        L_0x0112:
            java.lang.String r0 = "audioFormatMPEG4TwinVQ"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 7
            goto L_0x0148
        L_0x011c:
            java.lang.String r0 = "MPEG4HVXC"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 10
            goto L_0x0148
        L_0x0127:
            java.lang.String r0 = "MPEG4CELP"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 9
            goto L_0x0148
        L_0x0132:
            java.lang.String r0 = "audioFormatMPEG4AAC"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 4
            goto L_0x0148
        L_0x013c:
            java.lang.String r0 = "audioFormat60958AC3"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0147
            r0 = 13
            goto L_0x0148
        L_0x0147:
            r0 = -1
        L_0x0148:
            switch(r0) {
                case 0: goto L_0x0172;
                case 1: goto L_0x0172;
                case 2: goto L_0x0167;
                case 3: goto L_0x0167;
                case 4: goto L_0x0161;
                case 5: goto L_0x0161;
                case 6: goto L_0x0161;
                case 7: goto L_0x0161;
                case 8: goto L_0x0161;
                case 9: goto L_0x0161;
                case 10: goto L_0x0161;
                case 11: goto L_0x0161;
                case 12: goto L_0x0151;
                case 13: goto L_0x0151;
                case 14: goto L_0x0151;
                case 15: goto L_0x0151;
                default: goto L_0x014b;
            }
        L_0x014b:
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setOutputFormat(r5)
            goto L_0x017e
        L_0x0151:
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 < r8) goto L_0x015b
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setOutputFormat(r12)
            goto L_0x017e
        L_0x015b:
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setOutputFormat(r5)
            goto L_0x017e
        L_0x0161:
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setOutputFormat(r6)
            goto L_0x017e
        L_0x0167:
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setOutputFormat(r14)
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setAudioEncoder(r6)
            goto L_0x017c
        L_0x0172:
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setOutputFormat(r15)
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setAudioEncoder(r4)
        L_0x017c:
            r0 = 1
            goto L_0x017f
        L_0x017e:
            r0 = 0
        L_0x017f:
            if (r0 != 0) goto L_0x0228
            int r0 = r2.hashCode()
            switch(r0) {
                case -1994163307: goto L_0x01ee;
                case -1636599221: goto L_0x01e3;
                case -1636598693: goto L_0x01d8;
                case -1636598455: goto L_0x01cd;
                case 76596: goto L_0x01c2;
                case 77124: goto L_0x01b7;
                case 77362: goto L_0x01ac;
                case 2249154: goto L_0x01a1;
                case 560283934: goto L_0x0196;
                case 804906379: goto L_0x018a;
                default: goto L_0x0188;
            }
        L_0x0188:
            goto L_0x01f9
        L_0x018a:
            java.lang.String r0 = "audioQualityHigh"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x01f9
            r16 = 6
            goto L_0x01fb
        L_0x0196:
            java.lang.String r0 = "audioQualityMedium"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x01f9
            r16 = 3
            goto L_0x01fb
        L_0x01a1:
            java.lang.String r0 = "High"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x01f9
            r16 = 8
            goto L_0x01fb
        L_0x01ac:
            java.lang.String r0 = "Min"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x01f9
            r16 = 1
            goto L_0x01fb
        L_0x01b7:
            java.lang.String r0 = "Max"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x01f9
            r16 = 9
            goto L_0x01fb
        L_0x01c2:
            java.lang.String r0 = "Low"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x01f9
            r16 = 4
            goto L_0x01fb
        L_0x01cd:
            java.lang.String r0 = "audioQualityMin"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x01f9
            r16 = 0
            goto L_0x01fb
        L_0x01d8:
            java.lang.String r0 = "audioQualityMax"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x01f9
            r16 = 7
            goto L_0x01fb
        L_0x01e3:
            java.lang.String r0 = "audioQualityLow"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x01f9
            r16 = 2
            goto L_0x01fb
        L_0x01ee:
            java.lang.String r0 = "Medium"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x01f9
            r16 = 5
            goto L_0x01fb
        L_0x01f9:
            r16 = -1
        L_0x01fb:
            switch(r16) {
                case 0: goto L_0x0223;
                case 1: goto L_0x0223;
                case 2: goto L_0x0204;
                case 3: goto L_0x0204;
                case 4: goto L_0x0204;
                case 5: goto L_0x0204;
                case 6: goto L_0x0213;
                case 7: goto L_0x0213;
                case 8: goto L_0x0213;
                case 9: goto L_0x0213;
                default: goto L_0x01fe;
            }
        L_0x01fe:
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setAudioEncoder(r5)
            goto L_0x0228
        L_0x0204:
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 < r8) goto L_0x020e
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setAudioEncoder(r13)
            goto L_0x0213
        L_0x020e:
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setAudioEncoder(r15)
        L_0x0213:
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 < r8) goto L_0x021d
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setAudioEncoder(r14)
            goto L_0x0228
        L_0x021d:
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setAudioEncoder(r15)
            goto L_0x0228
        L_0x0223:
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.setAudioEncoder(r15)
        L_0x0228:
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule$2 r2 = new com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule$2
            r2.<init>()
            r0.setOnErrorListener(r2)
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            java.lang.String r2 = r3.getAbsolutePath()
            r0.setOutputFile(r2)
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.prepare()
            android.media.MediaRecorder r0 = r1.mMediaRecorder
            r0.start()
            r1.mIsMediaRecording = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule.startMediaRecorder(java.lang.String, com.facebook.react.bridge.ReadableMap):void");
    }

    private void stopMediaRecorder() throws Exception {
        if (this.mMediaRecorder != null) {
            RnPluginLog.a("MIOTAudioModule  MediaRecorder will stop...");
            this.mMediaRecorder.stop();
            this.mMediaRecorder.release();
            this.mMediaRecorder = null;
        }
        this.mIsMediaRecording = false;
    }

    private void startAudioRecord(String str, ReadableMap readableMap) throws Exception {
        final String pathName = getPathName(str);
        LogUtil.c("ReactNativeJS", "录音路径" + pathName);
        File file = new File(pathName);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        try {
            stopAudioRecord();
        } catch (Throwable th) {
            LogUtil.b("ReactNativeJS", Log.getStackTraceString(th));
        }
        int d = MIOTUtils.d(readableMap, "AVSampleRateKey");
        int d2 = MIOTUtils.d(readableMap, "AVNumberOfChannelsKey");
        int d3 = MIOTUtils.d(readableMap, "AVLinearPCMBitDepthKey");
        int i = d == 0 ? 44100 : d;
        if (d3 == 0) {
            d3 = 16;
        }
        if (d2 == 0) {
            d2 = 2;
        }
        int i2 = d2 == 1 ? 16 : 12;
        int i3 = d3 == 16 ? 2 : 3;
        final int minBufferSize = AudioRecord.getMinBufferSize(i, i2, i3);
        this.mAudioRecord = new AudioRecord(1, i, i2, i3, minBufferSize);
        new Thread(new Runnable() {
            public void run() {
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(pathName)));
                    byte[] bArr = new byte[minBufferSize];
                    MIOTAudioModule.this.mAudioRecord.startRecording();
                    boolean unused = MIOTAudioModule.this.mIsAudioRecording = true;
                    while (MIOTAudioModule.this.mIsAudioRecording) {
                        int read = MIOTAudioModule.this.mAudioRecord.read(bArr, 0, bArr.length);
                        for (int i = 0; i < read; i++) {
                            dataOutputStream.write(bArr[i]);
                        }
                        RnPluginLog.a(Thread.currentThread().getName() + "  录制中....");
                    }
                    RnPluginLog.a(Thread.currentThread().getName() + "  录制结束....");
                    MIOTAudioModule.this.stopAudioRecord();
                    dataOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public synchronized void stopAudioRecord() throws Exception {
        RnPluginLog.a(Thread.currentThread().getName() + "  stopAudioRecord...mIsAudioRecording=" + this.mIsAudioRecording);
        this.mIsAudioRecording = false;
        if (this.mAudioRecord != null) {
            this.mAudioRecord.stop();
            this.mAudioRecord.release();
            this.mAudioRecord = null;
        }
    }

    @ReactMethod
    public void startPlay(String str, ReadableMap readableMap, Callback callback) {
        if (TextUtils.isEmpty(str)) {
            callback.invoke(false, "audio player path is empty...");
        } else if ("audioTrack".equals(MIOTUtils.a(readableMap, "playerType", "mediaPlayer"))) {
            startAudioTrack(str, readableMap, callback);
        } else {
            startMediaPlayer(str, readableMap, callback);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002b A[Catch:{ Exception -> 0x0022 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002d A[Catch:{ Exception -> 0x0022 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0061 A[Catch:{ Exception -> 0x0022 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008d A[Catch:{ Exception -> 0x0022 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void startMediaPlayer(java.lang.String r6, com.facebook.react.bridge.ReadableMap r7, com.facebook.react.bridge.Callback r8) {
        /*
            r5 = this;
            r5.initHandler()
            r0 = 2
            r1 = 1
            r2 = 0
            if (r6 == 0) goto L_0x0025
            java.lang.String r3 = "http://"
            boolean r3 = r6.startsWith(r3)     // Catch:{ Exception -> 0x0022 }
            if (r3 != 0) goto L_0x0020
            java.lang.String r3 = "https://"
            boolean r3 = r6.startsWith(r3)     // Catch:{ Exception -> 0x0022 }
            if (r3 != 0) goto L_0x0020
            java.lang.String r3 = "rtsp://"
            boolean r3 = r6.startsWith(r3)     // Catch:{ Exception -> 0x0022 }
            if (r3 == 0) goto L_0x0025
        L_0x0020:
            r3 = r6
            goto L_0x0029
        L_0x0022:
            r6 = move-exception
            goto L_0x00eb
        L_0x0025:
            java.lang.String r3 = r5.getPathName(r6)     // Catch:{ Exception -> 0x0022 }
        L_0x0029:
            if (r7 != 0) goto L_0x002d
            r4 = 0
            goto L_0x0033
        L_0x002d:
            java.lang.String r4 = "updateAudioPlayerTimeInterval"
            boolean r4 = r7.hasKey(r4)     // Catch:{ Exception -> 0x0022 }
        L_0x0033:
            r5.mShouldSendPlayerTime = r4     // Catch:{ Exception -> 0x0022 }
            java.lang.String r4 = "audioPlayerUid"
            java.lang.String r7 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableMap) r7, (java.lang.String) r4)     // Catch:{ Exception -> 0x0022 }
            r5.mAudioPlayerUid = r7     // Catch:{ Exception -> 0x0022 }
            boolean r7 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x0022 }
            if (r7 != 0) goto L_0x008d
            java.lang.String r7 = "http://"
            boolean r7 = r3.startsWith(r7)     // Catch:{ Exception -> 0x0022 }
            if (r7 != 0) goto L_0x008d
            java.lang.String r7 = "https://"
            boolean r7 = r3.startsWith(r7)     // Catch:{ Exception -> 0x0022 }
            if (r7 != 0) goto L_0x008d
            java.lang.String r7 = "rtsp://"
            boolean r7 = r3.startsWith(r7)     // Catch:{ Exception -> 0x0022 }
            if (r7 != 0) goto L_0x008d
            boolean r7 = com.xiaomi.smarthome.framework.plugin.FileUtils.f(r3)     // Catch:{ Exception -> 0x0022 }
            if (r7 != 0) goto L_0x008d
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0022 }
            r7.<init>()     // Catch:{ Exception -> 0x0022 }
            java.lang.String r4 = "startMediaPlayer, audio file not exist: aPath="
            r7.append(r4)     // Catch:{ Exception -> 0x0022 }
            r7.append(r6)     // Catch:{ Exception -> 0x0022 }
            java.lang.String r6 = ", pathName="
            r7.append(r6)     // Catch:{ Exception -> 0x0022 }
            r7.append(r3)     // Catch:{ Exception -> 0x0022 }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x0022 }
            com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog.e(r6)     // Catch:{ Exception -> 0x0022 }
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x0022 }
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x0022 }
            r6[r2] = r7     // Catch:{ Exception -> 0x0022 }
            java.lang.String r7 = "audio file not exist"
            r6[r1] = r7     // Catch:{ Exception -> 0x0022 }
            r8.invoke(r6)     // Catch:{ Exception -> 0x0022 }
            return
        L_0x008d:
            android.media.MediaPlayer r6 = r5.mMediaPlayer     // Catch:{ Exception -> 0x0022 }
            if (r6 != 0) goto L_0x0099
            android.media.MediaPlayer r6 = new android.media.MediaPlayer     // Catch:{ Exception -> 0x0022 }
            r6.<init>()     // Catch:{ Exception -> 0x0022 }
            r5.mMediaPlayer = r6     // Catch:{ Exception -> 0x0022 }
            goto L_0x009e
        L_0x0099:
            android.media.MediaPlayer r6 = r5.mMediaPlayer     // Catch:{ Exception -> 0x0022 }
            r6.reset()     // Catch:{ Exception -> 0x0022 }
        L_0x009e:
            android.media.MediaPlayer r6 = r5.mMediaPlayer     // Catch:{ Exception -> 0x0022 }
            r6.setDataSource(r3)     // Catch:{ Exception -> 0x0022 }
            android.media.MediaPlayer r6 = r5.mMediaPlayer     // Catch:{ Exception -> 0x0022 }
            r7 = 3
            r6.setAudioStreamType(r7)     // Catch:{ Exception -> 0x0022 }
            com.facebook.react.bridge.ReactApplicationContext r6 = r5.getReactApplicationContext()     // Catch:{ Exception -> 0x0022 }
            java.lang.String r3 = "audio"
            java.lang.Object r6 = r6.getSystemService(r3)     // Catch:{ Exception -> 0x0022 }
            android.media.AudioManager r6 = (android.media.AudioManager) r6     // Catch:{ Exception -> 0x0022 }
            int r3 = r6.getStreamMaxVolume(r7)     // Catch:{ Exception -> 0x0022 }
            r4 = 10
            if (r3 >= r4) goto L_0x00c1
            r3 = 4
            r6.setStreamVolume(r7, r4, r3)     // Catch:{ Exception -> 0x0022 }
        L_0x00c1:
            android.media.MediaPlayer r6 = r5.mMediaPlayer     // Catch:{ Exception -> 0x0022 }
            r6.setLooping(r2)     // Catch:{ Exception -> 0x0022 }
            android.media.MediaPlayer r6 = r5.mMediaPlayer     // Catch:{ Exception -> 0x0022 }
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule$4 r7 = new com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule$4     // Catch:{ Exception -> 0x0022 }
            r7.<init>()     // Catch:{ Exception -> 0x0022 }
            r6.setOnCompletionListener(r7)     // Catch:{ Exception -> 0x0022 }
            android.media.MediaPlayer r6 = r5.mMediaPlayer     // Catch:{ Exception -> 0x0022 }
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule$5 r7 = new com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule$5     // Catch:{ Exception -> 0x0022 }
            r7.<init>()     // Catch:{ Exception -> 0x0022 }
            r6.setOnPreparedListener(r7)     // Catch:{ Exception -> 0x0022 }
            android.media.MediaPlayer r6 = r5.mMediaPlayer     // Catch:{ Exception -> 0x0022 }
            r6.prepareAsync()     // Catch:{ Exception -> 0x0022 }
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0022 }
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x0022 }
            r6[r2] = r7     // Catch:{ Exception -> 0x0022 }
            r8.invoke(r6)     // Catch:{ Exception -> 0x0022 }
            goto L_0x00fc
        L_0x00eb:
            java.lang.Object[] r7 = new java.lang.Object[r0]
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
            r7[r2] = r0
            java.lang.String r6 = android.util.Log.getStackTraceString(r6)
            r7[r1] = r6
            r8.invoke(r7)
        L_0x00fc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule.startMediaPlayer(java.lang.String, com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.Callback):void");
    }

    private void startAudioTrack(final String str, ReadableMap readableMap, Callback callback) {
        if (TextUtils.isEmpty(str)) {
            callback.invoke(false, "audio path is empty...");
            return;
        }
        if (str == null || (!str.startsWith(ConnectionHelper.HTTP_PREFIX) && !str.startsWith("https://") && !str.startsWith("rtsp://"))) {
            str = getPathName(str);
        }
        if (this.mAudioTrack != null) {
            try {
                stopAudioTrack();
            } catch (Exception unused) {
            }
        }
        this.mAudioPlayerUid = MIOTUtils.a(readableMap, "audioPlayerUid");
        int d = MIOTUtils.d(readableMap, "AVSampleRateKey");
        int d2 = MIOTUtils.d(readableMap, "AVNumberOfChannelsKey");
        int d3 = MIOTUtils.d(readableMap, "AVLinearPCMBitDepthKey");
        int i = d == 0 ? 44100 : d;
        if (d3 == 0) {
            d3 = 16;
        }
        if (d2 == 0) {
            d2 = 2;
        }
        int i2 = d2 == 1 ? 16 : 12;
        int i3 = d3 == 16 ? 2 : 3;
        final int minBufferSize = AudioTrack.getMinBufferSize(i, i2, i3);
        this.mAudioTrack = new AudioTrack(3, i, i2, i3, minBufferSize, 1);
        this.mIsAudioTrackPlaying = true;
        this.mAudioTrack.play();
        callback.invoke(true);
        new Thread(new Runnable() {
            public void run() {
                try {
                    DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(str)));
                    byte[] bArr = new byte[minBufferSize];
                    int read = dataInputStream.read(bArr);
                    if (MIOTAudioModule.this.mIsAudioTrackPlaying && read > 0) {
                        MIOTAudioModule.this.sendPlayerEventToJs(Arguments.createMap(), RNEventReceiver.AUDIO_PLAYER_DID_START_PLAYING);
                    }
                    RnPluginLog.a(Thread.currentThread().getName() + "  开始播放   readCount=" + read);
                    while (MIOTAudioModule.this.mIsAudioTrackPlaying && read > 0) {
                        MIOTAudioModule.this.mAudioTrack.write(bArr, 0, read);
                        read = dataInputStream.read(bArr);
                        RnPluginLog.a(Thread.currentThread().getName() + "  播放   readCount=" + read);
                    }
                    if (read <= 0) {
                        MIOTAudioModule.this.sendPlayerEventToJs(Arguments.createMap(), RNEventReceiver.AUDIO_PLAYER_DID_FINISH_PLAYING);
                    }
                    RnPluginLog.a(Thread.currentThread().getName() + "  停止播放   readCount=" + read);
                    MIOTAudioModule.this.stopAudioTrack();
                    dataInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @ReactMethod
    public void stopPlay(Callback callback) {
        try {
            stopMediaPlayer();
            stopAudioTrack();
            if (callback != null) {
                callback.invoke(true);
            }
        } catch (Exception e) {
            if (callback != null) {
                callback.invoke(false, Log.getStackTraceString(e));
            }
        }
    }

    private void stopMediaPlayer() throws Exception {
        if (this.mMediaPlayer != null) {
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
            }
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }

    /* access modifiers changed from: private */
    public synchronized void stopAudioTrack() throws Exception {
        RnPluginLog.a(Thread.currentThread().getName() + "  stopPlayTrack...");
        this.mIsAudioTrackPlaying = false;
        if (this.mAudioTrack != null) {
            if (this.mAudioTrack.getPlayState() == 3) {
                this.mAudioTrack.flush();
                this.mAudioTrack.stop();
            }
            this.mAudioTrack.release();
            this.mAudioTrack = null;
        }
    }

    /* access modifiers changed from: private */
    public void sendPlayerEventToJs(WritableMap writableMap, String str) {
        if (writableMap == null) {
            writableMap = Arguments.createMap();
        }
        writableMap.putString("audioPlayerUid", this.mAudioPlayerUid);
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext != null) {
            RnPluginLog.a("MIOTAudio send event  " + str);
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
            return;
        }
        RnPluginLog.b("MIOTAudio reactContext is null ...");
    }

    private String getPathName(String str) {
        if (str == null) {
            return getFilesPath() + File.separator + "defaultname";
        } else if (str.startsWith("file://")) {
            return str.substring(7);
        } else {
            if (str.startsWith(File.separator)) {
                return str;
            }
            return getFilesPath() + File.separator + str;
        }
    }

    @ReactMethod
    public void wavToAmr(String str, String str2, final Callback callback) {
        try {
            final AudioConvert audioConvert = new AudioConvert();
            audioConvert.a(MimeTypes.AUDIO_AMR_WB);
            audioConvert.a(getPathName(str), getPathName(str2));
            audioConvert.a();
            audioConvert.b();
            audioConvert.a((AudioConvert.OnCompleteListener) new AudioConvert.OnCompleteListener() {
                public void a() {
                    audioConvert.c();
                    callback.invoke(true);
                }
            });
        } catch (Throwable th) {
            callback.invoke(false, Log.getStackTraceString(th));
        }
    }

    @ReactMethod
    public void amrToWav(String str, String str2, final Callback callback) {
        try {
            final AudioConvert audioConvert = new AudioConvert();
            audioConvert.a("audio/x-wav");
            audioConvert.a(getPathName(str), getPathName(str2));
            audioConvert.a();
            audioConvert.b();
            audioConvert.a((AudioConvert.OnCompleteListener) new AudioConvert.OnCompleteListener() {
                public void a() {
                    audioConvert.c();
                    callback.invoke(true);
                }
            });
        } catch (Throwable th) {
            callback.invoke(false, Log.getStackTraceString(th));
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0003 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0006 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCatalystInstanceDestroy() {
        /*
            r0 = this;
            r0.stopMediaPlayer()     // Catch:{ Exception -> 0x0003 }
        L_0x0003:
            r0.stopAudioTrack()     // Catch:{ Exception -> 0x0006 }
        L_0x0006:
            r0.stopMediaRecorder()     // Catch:{ Exception -> 0x0009 }
        L_0x0009:
            r0.stopAudioRecord()     // Catch:{ Exception -> 0x000c }
        L_0x000c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule.onCatalystInstanceDestroy():void");
    }
}
