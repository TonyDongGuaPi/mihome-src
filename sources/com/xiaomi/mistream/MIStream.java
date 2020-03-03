package com.xiaomi.mistream;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import cn.com.fmsh.communication.core.MessageHead;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.debug.SDKLog;
import com.taobao.weex.annotation.JSMethod;
import com.tutk.IOTC.IOTCAPIs;
import com.tutk.IOTC.Packet;
import com.tutk.P2PUtils;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.MissConfig;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.MyLogger;
import com.xiaomi.smarthome.homeroom.HomeManager;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

public class MIStream {
    public static final int CHANNEL_CAP_ENABLED_AUDIO = 128;
    public static final int CHANNEL_CAP_ENABLED_VIDEO = 64;
    public static final int CHANNEL_CAP_ENCRYPTED = 8;
    public static final int CHANNEL_CAP_NETWORK_QOS = 32;
    public static final int CHANNEL_CAP_P2P = 2;
    public static final int CHANNEL_CAP_RELAY = 1;
    public static final int CHANNEL_CAP_RTSP = 4;
    public static final int CHANNEL_CAP_SUPPORT_CONTROL = 16;
    public static final int CHANNEL_TYPE_AGORA = 2;
    public static final int CHANNEL_TYPE_MIMC = 3;
    public static final int CHANNEL_TYPE_NUM = 5;
    public static final int CHANNEL_TYPE_ORBWEB = 4;
    public static final int CHANNEL_TYPE_TUTK = 1;
    public static final int CHANNEL_TYPE_UNDEFINE = 0;
    public static final int FLAG_FRAME_FLAG_IFRAME = 1;
    public static final int FLAG_FRAME_FLAG_IO = 3;
    public static final int FLAG_FRAME_FLAG_MD = 2;
    public static final int FLAG_FRAME_FLAG_PBFRAME = 0;
    public static final int FLAG_FRAME_TYPE_IFRAME = 1;
    public static final int FLAG_FRAME_TYPE_IO = 3;
    public static final int FLAG_FRAME_TYPE_MD = 2;
    public static final int FLAG_FRAME_TYPE_PBFRAME = 0;
    private static final int FLAG_RESOLUTION_AUDIO_DEFAULT = 0;
    private static final int FLAG_RESOLUTION_VIDEO_1080P = 1;
    private static final int FLAG_RESOLUTION_VIDEO_1296P_CHUANGMI = 4;
    private static final int FLAG_RESOLUTION_VIDEO_360P = 3;
    private static final int FLAG_RESOLUTION_VIDEO_720P = 2;
    private static final int FLAG_STREAM_TYPE_LIVE = 0;
    private static final int FLAG_STREAM_TYPE_PLAYBACK = 1;
    private static final int FLAG_WATERMARK_TIMESTAMP_EXIST = 1;
    private static final int FLAG_WATERMARK_TIMESTAMP_NOT_EXIST = 0;
    private static final int LOG_THRESHOLD = 1048576;
    public static final int MEDIA_CODECID_AUDIO_AAC = 136;
    public static final int MEDIA_CODECID_AUDIO_ADPCM = 139;
    public static final int MEDIA_CODECID_AUDIO_G711A = 138;
    public static final int MEDIA_CODECID_AUDIO_G711U = 137;
    public static final int MEDIA_CODECID_AUDIO_G726 = 143;
    public static final int MEDIA_CODECID_AUDIO_MP3 = 142;
    public static final int MEDIA_CODECID_AUDIO_PCM = 140;
    public static final int MEDIA_CODECID_AUDIO_SPEEX = 141;
    public static final int MEDIA_CODECID_UNKNOWN = 0;
    public static final int MEDIA_CODECID_VIDEO_H263 = 77;
    public static final int MEDIA_CODECID_VIDEO_H264 = 78;
    public static final int MEDIA_CODECID_VIDEO_HEVC = 80;
    public static final int MEDIA_CODECID_VIDEO_MJPEG = 79;
    public static final int MEDIA_CODECID_VIDEO_MPEG4 = 76;
    public static final int MISS_CMD_AUDIO_START = 260;
    public static final int MISS_CMD_AUDIO_STOP = 261;
    public static final int MISS_CMD_AUTHENTICATE_REQ = 256;
    public static final int MISS_CMD_AUTHENTICATE_RESP = 257;
    public static final int MISS_CMD_DEFAULT = 255;
    public static final int MISS_CMD_DEVINFO_REQ = 272;
    public static final int MISS_CMD_DEVINFO_RESP = 273;
    public static final int MISS_CMD_GET_AUDIO_FORMAT_REQ = 267;
    public static final int MISS_CMD_GET_AUDIO_FORMAT_RESP = 268;
    public static final int MISS_CMD_MAX = 276;
    public static final int MISS_CMD_MOTOR_REQ = 274;
    public static final int MISS_CMD_MOTOR_RESP = 275;
    public static final int MISS_CMD_PLAYBACK_REQ = 269;
    public static final int MISS_CMD_PLAYBACK_RESP = 270;
    public static final int MISS_CMD_PLAYBACK_SET_SPEED = 271;
    public static final int MISS_CMD_SPEAKER_START_REQ = 262;
    public static final int MISS_CMD_SPEAKER_START_RESP = 263;
    public static final int MISS_CMD_SPEAKER_STOP = 264;
    public static final int MISS_CMD_STREAM_CTRL_REQ = 265;
    public static final int MISS_CMD_STREAM_CTRL_RESP = 266;
    public static final int MISS_CMD_VIDEO_START = 258;
    public static final int MISS_CMD_VIDEO_STOP = 259;
    public static final int MISS_CODEC_AUDIO_AAC = 1030;
    public static final int MISS_CODEC_AUDIO_ADPCM = 1025;
    public static final int MISS_CODEC_AUDIO_CUSTOM = 1032;
    public static final int MISS_CODEC_AUDIO_G711A = 1027;
    public static final int MISS_CODEC_AUDIO_G711U = 1026;
    public static final int MISS_CODEC_AUDIO_G726 = 1028;
    public static final int MISS_CODEC_AUDIO_MP3 = 1029;
    public static final int MISS_CODEC_AUDIO_PCM = 1024;
    public static final int MISS_CODEC_AUDIO_SPEEX = 1031;
    public static final int MISS_CODEC_UNKNOWN = 0;
    public static final int MISS_CODEC_VIDEO_CUSTOM = 6;
    public static final int MISS_CODEC_VIDEO_H263 = 3;
    public static final int MISS_CODEC_VIDEO_H264 = 4;
    public static final int MISS_CODEC_VIDEO_H265 = 5;
    public static final int MISS_CODEC_VIDEO_HEVC = 5;
    public static final int MISS_CODEC_VIDEO_MJPEG = 2;
    public static final int MISS_CODEC_VIDEO_MPEG4 = 1;
    public static final int MISS_ERR_ABORTED = 8;
    public static final int MISS_ERR_ALREADY_INITIALIZED = 6;
    public static final int MISS_ERR_AUTHORIZED = 3;
    public static final int MISS_ERR_CHANNEL_EXCEED_MAX_SIZE = 27;
    public static final int MISS_ERR_CLIENT_NO_SUPPORT = 23;
    public static final int MISS_ERR_CLOSE_BY_LOCAL = 1;
    public static final int MISS_ERR_CLOSE_BY_REMOTE = 2;
    public static final int MISS_ERR_CLOSE_BY_TIMEOUT = 4;
    public static final int MISS_ERR_CREATE_CHANNEL = 21;
    public static final int MISS_ERR_CREATE_MUTEX = 9;
    public static final int MISS_ERR_CREATE_SOCKET = 12;
    public static final int MISS_ERR_CREATE_THREAD = 10;
    public static final int MISS_ERR_DISCONNECT_TIMOUT = 20;
    public static final int MISS_ERR_FRAME_DECRYPTO = 29;
    public static final int MISS_ERR_FRAME_ENCTYPTO = 30;
    public static final int MISS_ERR_FUNCTION_ALREADY_CALLED = 28;
    public static final int MISS_ERR_FUNCTION_DISABLE = 31;
    public static final int MISS_ERR_INVALID_ARG = 22;
    public static final int MISS_ERR_MAX_CHANNEL = 24;
    public static final int MISS_ERR_MAX_SESSION = 15;
    public static final int MISS_ERR_NOT_ENOUGH_MEMORY = 11;
    public static final int MISS_ERR_NOT_INITIALIZED = 7;
    public static final int MISS_ERR_NOT_SUPPORT_VENDOR = 5;
    public static final int MISS_ERR_NO_BUFFER = 26;
    public static final int MISS_ERR_NO_RESPONSE = 25;
    public static final int MISS_ERR_RPC_JSON_PARSE = 19;
    public static final int MISS_ERR_RPC_SEND = 18;
    public static final int MISS_ERR_SESSION_HANDLE = 16;
    public static final int MISS_ERR_SOCKET_BIND = 14;
    public static final int MISS_ERR_SOCKET_OPTIONS = 13;
    public static final int MISS_ERR_TIMOUT = 17;
    public static final int MISS_NO_ERROR = 0;
    public static final int OPERATION_CLEAR_RECV_BUFFER = 20;
    public static final int OPERATION_CLEAR_SEND_BUFFER = 19;
    public static final int OPERATION_ENABLED_AUDIO = 6;
    public static final int OPERATION_ENABLED_ENCRYPT = 11;
    public static final int OPERATION_ENABLED_REMOTE_AUDIO = 8;
    public static final int OPERATION_ENABLED_REMOTE_VIDEO = 9;
    public static final int OPERATION_ENABLED_VIDEO = 7;
    public static final int OPERATION_GET_CAPABILITY = 1;
    public static final int OPERATION_GET_CONFIG = 3;
    public static final int OPERATION_GET_CONNECTION_TYPE = 0;
    public static final int OPERATION_GET_RECV_BUFFER_SIZE = 16;
    public static final int OPERATION_GET_RECV_BUFFER_USAGERATE = 18;
    public static final int OPERATION_GET_SEND_BUFFER_SIZE = 15;
    public static final int OPERATION_GET_SEND_BUFFER_USAGERATE = 17;
    public static final int OPERATION_GET_STATS = 2;
    public static final int OPERATION_REFRESH_KEY = 10;
    public static final int OPERATION_SET_ENCRYPT = 12;
    public static final int OPERATION_SET_QOS = 4;
    public static final int OPERATION_SET_RECV_BUFFER_SIZE = 14;
    public static final int OPERATION_SET_SEND_BUFFER_SIZE = 13;
    public static final int OPERTAION_GET_BUF_PROPOSAL = 22;
    public static final int OPERTAION_RDT_START = 23;
    public static final int OPERTAION_RDT_STOP = 24;
    public static final int OPERTAION_RESET_SENDBUF = 21;
    public static final int OPERTAION_SET_LOG_CONF = 5;
    public static final int OPERTAION_SPEAKER_START = 25;
    public static final int OPERTAION_SPEAKER_STOP = 26;
    public static final int RESOLUTION_AUDIO_DEFAULT = 0;
    public static final int RESOLUTION_VIDEO_1080P = 1;
    public static final int RESOLUTION_VIDEO_360P = 2;
    public static final String RTC_IPCAM_AUDIO_START = "RTC_IPCAM_AUDIO_START";
    public static final String RTC_IPCAM_AUDIO_STOP = "RTC_IPCAM_AUDIO_STOP";
    public static final String RTC_IPCAM_DEVICEINFO_REQ = "RTC_IPCAM_DEVICEINFO_REQ";
    public static final String RTC_IPCAM_PLAYBACK_CTRL_REQ = "RTC_IPCAM_PLAYBACK_CTRL_REQ";
    public static final String RTC_IPCAM_PLAYBACK_CTRL_SPEED = "RTC_IPCAM_PLAYBACK_CTRL_SPEED";
    public static final String RTC_IPCAM_RDT_START = "RTC_IPCAM_RDT_START";
    public static final String RTC_IPCAM_RDT_STOP = "RTC_IPCAM_RDT_STOP";
    public static final String RTC_IPCAM_SETSTREAMCTRL_REQ = "RTC_IPCAM_SETSTREAMCTRL_REQ";
    public static final String RTC_IPCAM_SPEAKER_START = "RTC_IPCAM_SPEAKER_START";
    public static final String RTC_IPCAM_SPEAKER_STOP = "RTC_IPCAM_SPEAKER_STOP";
    public static final String RTC_IPCAM_START = "RTC_IPCAM_START";
    public static final String RTC_IPCAM_STOP = "RTC_IPCAM_STOP";
    public static final String STATISTIC_CAMERA_CONNECTIONS = "cameraConnections";
    public static final String STATISTIC_ERROR_CODE = "error_code";
    public static final String STATISTIC_FIRST_FRAMES = "firstFrames";
    private static final String TAG = "MIStream";
    public static volatile boolean sInit = false;
    private String buildID = (Build.SERIAL + JSMethod.NOT_SET + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.DISPLAY.length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10));
    /* access modifiers changed from: private */
    public String did;
    public boolean isAudioEnabled = false;
    public boolean isCallStarted = false;
    public boolean isMissConnected = false;
    public boolean isMissInit = false;
    public boolean isVideoEnabled = false;
    private Handler mAudioSenderHandler;
    private HandlerThread mAudioSenderThread;
    private Handler mWorkerHandler;
    private HandlerThread mWorkerThread;
    /* access modifiers changed from: private */
    public String model;
    public IStreamHandler streamHandler;

    private int getBits(int i, int i2, int i3) {
        return (int) (((long) (i >> i3)) & (MessageHead.SERIAL_MAK >> (32 - i2)));
    }

    /* access modifiers changed from: private */
    public native void missClientFinish();

    /* access modifiers changed from: private */
    public native int missClientSessionClose(String str);

    /* access modifiers changed from: private */
    public native int missClientSessionOpen(String str, String str2, String str3, boolean z, MissConfig missConfig);

    /* access modifiers changed from: private */
    public native int missCmdSend(int i, String str, String str2);

    private native int missLogSetLevel(int i);

    private native int missLogSetPath(String str);

    /* access modifiers changed from: private */
    public native int missRdtSend(byte[] bArr, int i, String str);

    /* access modifiers changed from: private */
    public static native int missRpcProcess(int i, String str);

    /* access modifiers changed from: private */
    public native int missSendAudioData(byte[] bArr, int i, int i2, int i3, long j, String str);

    static {
        System.loadLibrary("AVAPIs");
        System.loadLibrary("IOTCAPIs");
        System.loadLibrary("RDTAPIs");
        System.loadLibrary("PPCS_API");
        System.loadLibrary("sodiumjni");
        System.loadLibrary("miss");
    }

    public MIStream() {
        StringBuilder sb = new StringBuilder();
        sb.append("buildID:");
        sb.append(this.buildID);
        LogUtil.a(TAG, sb.toString());
        this.mWorkerThread = new HandlerThread("MiStream Handler");
        this.mWorkerThread.start();
        this.mWorkerHandler = new Handler(this.mWorkerThread.getLooper());
    }

    public void setLog() {
        String str = MyLogger.a().d() + "miss.log";
        LogUtil.a(TAG, "logFilePath:" + str);
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.exists() && file.length() > 1048576) {
                    LogUtil.a(TAG, "delete exist file");
                    file.delete();
                }
                if (!file.exists()) {
                    LogUtil.a(TAG, "create new file");
                    file.createNewFile();
                }
                missLogSetLevel(0);
                missLogSetPath(str);
            } catch (Exception e) {
                LogUtil.b(TAG, "delete log failed:" + e.getLocalizedMessage());
            }
        }
    }

    private static void onMissRpcSend(final int i, byte[] bArr, byte[] bArr2) {
        String str = new String(bArr);
        String str2 = new String(bArr2);
        SDKLog.e(TAG, "onMissRpcSend:" + i + ":" + str + ":" + str2);
        try {
            JSONObject jSONObject = new JSONObject(str2);
            sendCloudRequest(jSONObject.optString("did"), str, jSONObject, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("missRpcProcess:");
                        sb.append(jSONObject != null ? jSONObject : "");
                        SDKLog.e(MIStream.TAG, sb.toString());
                        int unused = MIStream.missRpcProcess(i, jSONObject.toString());
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.e(MIStream.TAG, "onMissRpcSend failed:" + i + " errorInfo:" + str);
                    int unused = MIStream.missRpcProcess(i, String.valueOf(i));
                }
            });
        } catch (JSONException e) {
            SDKLog.e(TAG, "sendCloudRequest:" + e.getLocalizedMessage());
        }
    }

    private void onMissStatistics(final byte[] bArr) {
        if (this.isMissInit && bArr != null && bArr.length > 0) {
            this.mWorkerHandler.post(new Runnable() {
                public void run() {
                    try {
                        String str = new String(bArr);
                        SDKLog.e(MIStream.TAG, "onMissStatistics:" + str);
                        MIStreamStatistic.getInstance().sendStatisticRequest(MIStream.this.model, MIStream.this.did, "POST", new JSONObject(str), new Callback() {
                            public void onSuccess(Object obj) {
                                SDKLog.e(MIStream.TAG, "sendStatisticRequest:" + obj);
                            }

                            public void onFailure(int i, String str) {
                                SDKLog.e(MIStream.TAG, "sendStatisticRequest:" + i + " info:" + str);
                            }
                        });
                    } catch (Exception e) {
                        SDKLog.e(MIStream.TAG, "onMissStatistics:" + e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    private void onMissOnConnect() {
        SDKLog.e(TAG, "onMissOnConnect");
        if (this.streamHandler != null) {
            runInUIThread(new Runnable() {
                public void run() {
                    MIStream.this.streamHandler.on_connect(0, (String) null);
                }
            });
        }
        this.isMissConnected = true;
        if (this.streamHandler != null && this.streamHandler.isAutoVideo()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("videoquality", 0);
                jSONObject.put("enableaudio", this.isAudioEnabled ? 1 : 0);
                jSONObject.put("imei", "" + this.buildID);
                missCommandSend(258, jSONObject.toString(), new IStreamCallback<Integer>() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(Integer num, Object obj) {
                        MIStream.this.isVideoEnabled = true;
                        MIStream.this.runInUIThread(new Runnable() {
                            public void run() {
                                if (MIStream.this.streamHandler != null) {
                                    MIStream.this.streamHandler.on_progress(100);
                                }
                            }
                        });
                    }

                    public void onFailed(final int i, final String str) {
                        SDKLog.e(MIStream.TAG, "MISS_CMD_VIDEO_START failed:" + i);
                        MIStream.this.isVideoEnabled = false;
                        MIStream.this.runInUIThread(new Runnable() {
                            public void run() {
                                if (MIStream.this.streamHandler != null) {
                                    MIStream.this.streamHandler.on_error(i, str);
                                }
                            }
                        });
                    }
                });
            } catch (JSONException e) {
                SDKLog.e(TAG, "JSONException error:" + e.getLocalizedMessage());
            }
        }
        if (this.streamHandler != null && this.streamHandler.isAutoAudio()) {
            toggleRemoteAudio(true);
        }
    }

    private void onMissOnDisconnect(int i) {
        SDKLog.e(TAG, "onMissOnDisconnect");
        if (this.streamHandler != null) {
            this.streamHandler.on_disconnect(0, i);
        }
        this.isMissConnected = false;
        this.isCallStarted = false;
        this.isVideoEnabled = false;
        this.isAudioEnabled = false;
    }

    private void onMissOnError(final int i) {
        SDKLog.e(TAG, "onMissOnError:" + i);
        this.isMissConnected = false;
        this.isCallStarted = false;
        this.isVideoEnabled = false;
        this.isAudioEnabled = false;
        if (this.streamHandler != null) {
            runInUIThread(new Runnable() {
                public void run() {
                    MIStream.this.streamHandler.on_error(i, (String) null);
                }
            });
        }
    }

    private void onMissOnAudioData(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, long j, int i5) {
        if (bArr != null && bArr.length > 0 && this.streamHandler != null) {
            byte[] bArr3 = new byte[28];
            if (i2 == 1027) {
                bArr3[0] = Constants.TagName.PAY_CHANNEL_NAME;
            } else if (i2 == 137) {
                bArr3[0] = Constants.TagName.COMPANY_CODE;
            } else if (i2 == 1030) {
                bArr3[0] = Constants.TagName.ACTIVITY_DEFINITION;
            }
            System.arraycopy(Packet.intToByteArray(i3, false), 0, bArr3, 4, 4);
            System.arraycopy(Packet.intToByteArray((int) j, false), 0, bArr3, 12, 4);
            System.arraycopy(Packet.intToByteArray(i5, false), 0, bArr3, 20, 4);
            System.arraycopy(Packet.intToByteArray(getBits(i4, 4, 3), false), 0, bArr3, 8, 4);
            System.arraycopy(Packet.shortToByteArray((short) getBits(i4, 2, 7), false), 0, bArr3, 12, 2);
            System.arraycopy(Packet.shortToByteArray((short) getBits(i4, 2, 9), false), 0, bArr3, 14, 2);
            this.streamHandler.receive_audio_data(bArr, bArr3);
        }
    }

    private void onMissOnVideoData(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, long j, int i5) {
        byte[] bArr3 = bArr;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        long j2 = j;
        int i9 = i5;
        if (bArr3 != null) {
            try {
                if (bArr3.length > 0 && this.streamHandler != null) {
                    if (i6 < 1024) {
                        byte[] bArr4 = new byte[28];
                        if (i6 == 4) {
                            bArr4[0] = 78;
                        } else if (i6 == 5) {
                            bArr4[0] = Constants.TagName.ORDER_BRIEF_INFO_LIST;
                        }
                        int i10 = i8 & 3;
                        if (i10 == 0) {
                            bArr4[2] = 0;
                        } else if (i10 == 1) {
                            bArr4[2] = 1;
                        }
                        System.arraycopy(Packet.intToByteArray(i7, false), 0, bArr4, 4, 4);
                        byte b = ((i8 >> 11) & 1) == 1 ? (byte) 1 : 0;
                        if (((i8 >> 13) & 1) == 1) {
                            b = (byte) (b | 2);
                        }
                        bArr4[16] = b;
                        int i11 = (i8 >> 17) & 15;
                        if (i11 == 1) {
                            System.arraycopy(Packet.shortToByteArray(1920, false), 0, bArr4, 8, 2);
                            System.arraycopy(Packet.shortToByteArray(1080, false), 0, bArr4, 10, 2);
                        } else if (i11 == 3) {
                            System.arraycopy(Packet.shortToByteArray(640, false), 0, bArr4, 8, 2);
                            System.arraycopy(Packet.shortToByteArray(360, false), 0, bArr4, 10, 2);
                        } else if (i11 == 2) {
                            System.arraycopy(Packet.shortToByteArray(1280, false), 0, bArr4, 8, 2);
                            System.arraycopy(Packet.shortToByteArray(720, false), 0, bArr4, 10, 2);
                        } else if (i11 == 4) {
                            System.arraycopy(Packet.shortToByteArray(2304, false), 0, bArr4, 8, 2);
                            System.arraycopy(Packet.shortToByteArray(1296, false), 0, bArr4, 10, 2);
                        }
                        System.arraycopy(Packet.intToByteArray((int) j2, false), 0, bArr4, 12, 4);
                        System.arraycopy(Packet.intToByteArray(i9, false), 0, bArr4, 20, 4);
                        this.streamHandler.receive_video_data(bArr3, bArr4);
                        return;
                    }
                    byte[] bArr5 = new byte[28];
                    if (i6 == 1027) {
                        bArr5[0] = Constants.TagName.PAY_CHANNEL_NAME;
                    } else if (i6 == 1030) {
                        bArr5[0] = Constants.TagName.ACTIVITY_DEFINITION;
                    }
                    System.arraycopy(Packet.intToByteArray(i7, false), 0, bArr5, 4, 4);
                    System.arraycopy(Packet.intToByteArray((int) j2, false), 0, bArr5, 12, 4);
                    System.arraycopy(Packet.intToByteArray(i9, false), 0, bArr5, 20, 4);
                    System.arraycopy(Packet.intToByteArray(getBits(i8, 4, 3), false), 0, bArr5, 8, 4);
                    System.arraycopy(Packet.shortToByteArray((short) getBits(i8, 2, 7), false), 0, bArr5, 12, 2);
                    System.arraycopy(Packet.shortToByteArray((short) getBits(i8, 2, 9), false), 0, bArr5, 14, 2);
                    this.streamHandler.receive_audio_data(bArr3, bArr5);
                }
            } catch (Exception e) {
                SDKLog.e(TAG, "onMissOnVideoData Exception:" + e.getLocalizedMessage());
            }
        }
    }

    private void onMissOnRdtData(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("onMissOnRdtData:");
        sb.append(bArr != null ? Integer.valueOf(bArr.length) : "");
        SDKLog.e(TAG, sb.toString());
        if (this.streamHandler != null && bArr != null && bArr.length > 0) {
            this.streamHandler.receive_rdt_data(bArr);
        }
    }

    private void onMissOnCmd(final int i, final byte[] bArr, byte[] bArr2) {
        runInUIThread(new Runnable() {
            public void run() {
                if (!(MIStream.this.streamHandler == null || bArr == null || bArr.length <= 0)) {
                    MIStream.this.streamHandler.on_command(i, bArr);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("onMissOnCmd:");
                sb.append(i);
                sb.append(" data:");
                sb.append(bArr != null ? new String(bArr) : "");
                SDKLog.e(MIStream.TAG, sb.toString());
            }
        });
    }

    private int onMissOnServerReady() {
        SDKLog.e(TAG, "onMissOnServerReady");
        return 0;
    }

    public String getTutkVersion() {
        int[] iArr = new int[1];
        IOTCAPIs.IOTC_Get_Version(iArr);
        int i = iArr[0];
        return String.format("%d.%d.%d.%d", new Object[]{Integer.valueOf((i >> 24) & 255), Integer.valueOf((i >> 16) & 255), Integer.valueOf((i >> 8) & 255), Integer.valueOf(i & 255)});
    }

    public void connectWithMiss(String str, String str2, IStreamCallback<String> iStreamCallback) {
        connectWithMissConfig(str, str2, iStreamCallback, (MissConfig) null);
    }

    public void connectWithMissConfig(String str, String str2, IStreamCallback<String> iStreamCallback, MissConfig missConfig) {
        this.model = str;
        this.did = str2;
        SDKLog.e(TAG, "connectWithMiss model:" + str + " did:" + str2);
        this.mWorkerHandler.removeCallbacksAndMessages((Object) null);
        final String str3 = str2;
        final String str4 = str;
        final MissConfig missConfig2 = missConfig;
        final IStreamCallback<String> iStreamCallback2 = iStreamCallback;
        this.mWorkerHandler.post(new Runnable() {
            public void run() {
                int i;
                MIStream.this.setLog();
                MIStream.this.isMissInit = true;
                if (P2PUtils.sInit) {
                    i = MIStream.this.missClientSessionOpen(str3, str4, "android_app", true, missConfig2);
                } else {
                    i = MIStream.this.missClientSessionOpen(str3, str4, "android_app", false, missConfig2);
                }
                MIStream.sInit = true;
                if (iStreamCallback2 == null) {
                    return;
                }
                if (i == 0) {
                    iStreamCallback2.onSuccess(String.valueOf(i), (Object) null);
                    return;
                }
                SDKLog.e(MIStream.TAG, "connectWithMiss failed:" + i);
                iStreamCallback2.onFailed(i, "failed");
            }
        });
    }

    public void disconnectWithMiss() {
        SDKLog.e(TAG, "disconnectWithMiss:" + this.did);
        this.isVideoEnabled = false;
        this.isMissConnected = false;
        this.isMissInit = false;
        this.mWorkerHandler.post(new Runnable() {
            public void run() {
                int access$400;
                if (!TextUtils.isEmpty(MIStream.this.did) && (access$400 = MIStream.this.missClientSessionClose(MIStream.this.did)) != 0) {
                    SDKLog.e(MIStream.TAG, "disconnectWithMiss failed:" + access$400);
                }
            }
        });
    }

    public void finishMiss() {
        this.isMissConnected = false;
        this.mWorkerHandler.post(new Runnable() {
            public void run() {
                MIStream.this.missClientFinish();
            }
        });
    }

    public void missCommandSend(final int i, final String str, final IStreamCallback<Integer> iStreamCallback) {
        SDKLog.e(TAG, "missCommandSend command:" + i + " params:" + str);
        if (this.isMissInit) {
            this.mWorkerHandler.post(new Runnable() {
                public void run() {
                    int access$600 = MIStream.this.missCmdSend(i, str, MIStream.this.did);
                    if (iStreamCallback == null) {
                        return;
                    }
                    if (access$600 == 0) {
                        iStreamCallback.onSuccess(Integer.valueOf(access$600), (Object) null);
                        return;
                    }
                    SDKLog.e(MIStream.TAG, "missCommandSend failed command:" + i + " params:" + str);
                    iStreamCallback.onFailed(access$600, "failed with error(s)");
                }
            });
        }
    }

    public void connect(String str, String str2, IStreamCallback<String> iStreamCallback) {
        connectWithMiss(str, str2, iStreamCallback);
    }

    public void connect(String str, String str2, IStreamCallback<String> iStreamCallback, MissConfig missConfig) {
        connectWithMissConfig(str, str2, iStreamCallback, missConfig);
    }

    public void disConnect(String str, String str2, IStreamCallback<String> iStreamCallback) {
        missCommandSend(259, "", (IStreamCallback<Integer>) null);
        missCommandSend(261, "", (IStreamCallback<Integer>) null);
        disconnectWithMiss();
        finishMiss();
    }

    public void speakerStart(String str, String str2, final IMISSListener iMISSListener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("speakerStart", 1);
            missCommandSend(262, jSONObject.toString(), new IStreamCallback<Integer>() {
                public void onProgress(int i) {
                }

                public void onSuccess(final Integer num, final Object obj) {
                    MIStream.this.isCallStarted = true;
                    if (iMISSListener != null) {
                        MIStream.this.runInUIThread(new Runnable() {
                            public void run() {
                                iMISSListener.onSuccess(String.valueOf(num), obj);
                            }
                        });
                    }
                }

                public void onFailed(final int i, final String str) {
                    MIStream.this.isCallStarted = false;
                    SDKLog.e(MIStream.TAG, "miss speakerStart failed:" + i);
                    if (iMISSListener != null) {
                        MIStream.this.runInUIThread(new Runnable() {
                            public void run() {
                                iMISSListener.onFailed(i, str);
                            }
                        });
                    }
                }
            });
        } catch (JSONException unused) {
            this.isCallStarted = false;
        }
    }

    public void speakerStop(String str, String str2, final IMISSListener iMISSListener) {
        JSONObject jSONObject = new JSONObject();
        this.isCallStarted = false;
        try {
            jSONObject.put("speakerStop", 1);
            missCommandSend(264, jSONObject.toString(), new IStreamCallback<Integer>() {
                public void onProgress(int i) {
                }

                public void onSuccess(final Integer num, final Object obj) {
                    if (iMISSListener != null) {
                        MIStream.this.runInUIThread(new Runnable() {
                            public void run() {
                                iMISSListener.onSuccess(String.valueOf(num), obj);
                            }
                        });
                    }
                }

                public void onFailed(final int i, final String str) {
                    SDKLog.e(MIStream.TAG, "miss speakerStop failed:" + i);
                    if (iMISSListener != null) {
                        MIStream.this.runInUIThread(new Runnable() {
                            public void run() {
                                iMISSListener.onFailed(i, str);
                            }
                        });
                    }
                }
            });
        } catch (JSONException unused) {
        }
    }

    public void getDeviceInfo(final IMISSListener iMISSListener) {
        missCommandSend(272, (String) null, new IStreamCallback<Integer>() {
            public void onProgress(int i) {
            }

            public void onSuccess(final Integer num, final Object obj) {
                if (iMISSListener != null) {
                    MIStream.this.runInUIThread(new Runnable() {
                        public void run() {
                            iMISSListener.onSuccess(String.valueOf(num), obj);
                        }
                    });
                }
            }

            public void onFailed(final int i, final String str) {
                if (iMISSListener != null) {
                    MIStream.this.runInUIThread(new Runnable() {
                        public void run() {
                            iMISSListener.onFailed(i, str);
                        }
                    });
                }
            }
        });
    }

    public void setResolution(String str, String str2, int i, final IMISSListener iMISSListener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("videoquality", i);
            missCommandSend(265, jSONObject.toString(), new IStreamCallback<Integer>() {
                public void onProgress(int i) {
                }

                public void onSuccess(final Integer num, final Object obj) {
                    if (iMISSListener != null) {
                        MIStream.this.runInUIThread(new Runnable() {
                            public void run() {
                                iMISSListener.onSuccess(String.valueOf(num), obj);
                            }
                        });
                    }
                }

                public void onFailed(final int i, final String str) {
                    SDKLog.e(MIStream.TAG, "setResolution failed:" + i + " errorInfo:" + str);
                    if (iMISSListener != null) {
                        MIStream.this.runInUIThread(new Runnable() {
                            public void run() {
                                iMISSListener.onFailed(i, str);
                            }
                        });
                    }
                }
            });
        } catch (JSONException e) {
            SDKLog.e(TAG, "JSONException:" + e.getLocalizedMessage());
        }
    }

    public void setDirection(String str, String str2, int i, final IMISSListener iMISSListener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HomeManager.v, i);
            missCommandSend(274, jSONObject.toString(), new IStreamCallback<Integer>() {
                public void onProgress(int i) {
                }

                public void onSuccess(final Integer num, final Object obj) {
                    if (iMISSListener != null) {
                        MIStream.this.runInUIThread(new Runnable() {
                            public void run() {
                                iMISSListener.onSuccess(String.valueOf(num), obj);
                            }
                        });
                    }
                }

                public void onFailed(final int i, final String str) {
                    SDKLog.e(MIStream.TAG, "setDirection failed:" + i + " errorInfo:" + str);
                    if (iMISSListener != null) {
                        MIStream.this.runInUIThread(new Runnable() {
                            public void run() {
                                iMISSListener.onFailed(i, str);
                            }
                        });
                    }
                }
            });
        } catch (JSONException e) {
            SDKLog.e(TAG, "setDirection JSONException:" + e.getLocalizedMessage());
        }
    }

    private static void sendCloudRequest(String str, String str2, JSONObject jSONObject, Callback callback) {
        if (CoreApi.a().l() && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && jSONObject != null) {
            XmPluginHostApi.instance().callSmartHomeApi(str, str2, jSONObject, callback, Parser.DEFAULT_PARSER);
        } else if (callback != null) {
            callback.onFailure(-1000, "params invalid");
        }
    }

    public void audioStart(String str, String str2, final IMISSListener iMISSListener) {
        this.isAudioEnabled = true;
        missCommandSend(260, "", new IStreamCallback<Integer>() {
            public void onProgress(int i) {
            }

            public void onSuccess(final Integer num, final Object obj) {
                if (iMISSListener != null) {
                    MIStream.this.runInUIThread(new Runnable() {
                        public void run() {
                            iMISSListener.onSuccess(String.valueOf(num), obj);
                        }
                    });
                }
            }

            public void onFailed(final int i, final String str) {
                SDKLog.e(MIStream.TAG, "audioStart failed:" + i + " errorInfo:" + str);
                if (iMISSListener != null) {
                    MIStream.this.runInUIThread(new Runnable() {
                        public void run() {
                            iMISSListener.onFailed(i, str);
                        }
                    });
                }
            }
        });
    }

    public void audioStop(String str, String str2, final IMISSListener iMISSListener) {
        this.isAudioEnabled = false;
        missCommandSend(261, "", new IStreamCallback<Integer>() {
            public void onProgress(int i) {
            }

            public void onSuccess(final Integer num, final Object obj) {
                if (iMISSListener != null) {
                    MIStream.this.runInUIThread(new Runnable() {
                        public void run() {
                            iMISSListener.onSuccess(String.valueOf(num), obj);
                        }
                    });
                }
            }

            public void onFailed(final int i, final String str) {
                SDKLog.e(MIStream.TAG, "audioStop failed:" + i + " errorInfo:" + str);
                if (iMISSListener != null) {
                    MIStream.this.runInUIThread(new Runnable() {
                        public void run() {
                            iMISSListener.onFailed(i, str);
                        }
                    });
                }
            }
        });
    }

    public void avChannelSendAudio(final byte[] bArr, final int i) {
        if (this.isMissInit && this.isMissConnected && this.isCallStarted && bArr != null && bArr.length > 0 && !TextUtils.isEmpty(this.did)) {
            if (this.mAudioSenderThread == null) {
                synchronized (this) {
                    if (this.mAudioSenderThread == null) {
                        this.mAudioSenderThread = new HandlerThread("MIStream Audio Sender");
                        this.mAudioSenderThread.start();
                        this.mAudioSenderHandler = new Handler(this.mAudioSenderThread.getLooper());
                    }
                }
            }
            this.mAudioSenderHandler.post(new Runnable() {
                public void run() {
                    try {
                        int access$700 = MIStream.this.missSendAudioData(bArr, bArr.length, 0, i == 136 ? 1030 : 1027, System.currentTimeMillis(), MIStream.this.did);
                        if (access$700 != 0) {
                            SDKLog.e(MIStream.TAG, "missSendAudioData failed:" + access$700);
                        }
                    } catch (Exception e) {
                        SDKLog.e(MIStream.TAG, "avChannelSendAudio Exception:" + e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    public int avChannelSendRDTData(final byte[] bArr) {
        if (!this.isMissInit || !this.isMissConnected || bArr == null || bArr.length <= 0 || TextUtils.isEmpty(this.did)) {
            return 0;
        }
        this.mWorkerHandler.post(new Runnable() {
            public void run() {
                int access$800 = MIStream.this.missRdtSend(bArr, bArr.length, MIStream.this.did);
                if (access$800 != 0) {
                    SDKLog.e(MIStream.TAG, "avChannelSendRDTData failed:" + access$800);
                }
            }
        });
        return 0;
    }

    public void avChannelSendRDTData(final byte[] bArr, final IMISSListener iMISSListener) {
        if (this.isMissInit && this.isMissConnected && bArr != null && bArr.length > 0 && !TextUtils.isEmpty(this.did)) {
            this.mWorkerHandler.post(new Runnable() {
                public void run() {
                    int access$800 = MIStream.this.missRdtSend(bArr, bArr.length, MIStream.this.did);
                    if (iMISSListener == null) {
                        return;
                    }
                    if (access$800 == 0) {
                        iMISSListener.onSuccess(String.valueOf(0), (Object) null);
                        return;
                    }
                    SDKLog.e(MIStream.TAG, "avChannelSendRDTData failed:" + access$800);
                    iMISSListener.onFailed(access$800, (String) null);
                }
            });
        } else if (iMISSListener != null) {
            iMISSListener.onFailed(-1010, "avChannelSendRDTData params invalid");
        }
    }

    public void avPlaybackSpeed(String str, String str2, String str3, final IMISSListener iMISSListener) {
        missCommandSend(271, str3, new IStreamCallback<Integer>() {
            public void onProgress(int i) {
            }

            public void onSuccess(final Integer num, final Object obj) {
                if (iMISSListener != null) {
                    MIStream.this.runInUIThread(new Runnable() {
                        public void run() {
                            iMISSListener.onSuccess(String.valueOf(num), obj);
                        }
                    });
                }
            }

            public void onFailed(final int i, final String str) {
                SDKLog.e(MIStream.TAG, "avPlaybackSpeed failed:" + i + " errorInfo:" + str);
                if (iMISSListener != null) {
                    MIStream.this.runInUIThread(new Runnable() {
                        public void run() {
                            iMISSListener.onFailed(i, str);
                        }
                    });
                }
            }
        });
    }

    public void avPlayback(String str, String str2, String str3, final IMISSListener iMISSListener) {
        missCommandSend(269, str3, new IStreamCallback<Integer>() {
            public void onProgress(int i) {
            }

            public void onSuccess(final Integer num, final Object obj) {
                if (iMISSListener != null) {
                    MIStream.this.runInUIThread(new Runnable() {
                        public void run() {
                            iMISSListener.onSuccess(String.valueOf(num), obj);
                        }
                    });
                }
            }

            public void onFailed(final int i, final String str) {
                SDKLog.e(MIStream.TAG, "avPlayback failed:" + i + " errorInfo:" + str);
                if (iMISSListener != null) {
                    MIStream.this.runInUIThread(new Runnable() {
                        public void run() {
                            iMISSListener.onFailed(i, str);
                        }
                    });
                }
            }
        });
    }

    public int toggleRemoteVideo(boolean z) {
        SDKLog.e(TAG, "toggleRemoteVideo:" + z);
        if (z) {
            missCommandSend(258, "", (IStreamCallback<Integer>) null);
            return 0;
        }
        missCommandSend(259, "", (IStreamCallback<Integer>) null);
        return 0;
    }

    public int toggleRemoteAudio(boolean z) {
        SDKLog.e(TAG, "toggleRemoteAudio:" + z);
        if (z) {
            missCommandSend(260, "", (IStreamCallback<Integer>) null);
            return 0;
        }
        missCommandSend(261, "", (IStreamCallback<Integer>) null);
        return 0;
    }

    public void runInUIThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public void markCallStart(boolean z) {
        this.isCallStarted = z;
    }
}
