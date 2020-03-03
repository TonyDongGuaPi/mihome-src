package com.xiaomi.smarthome.camera;

import com.tiqiaa.icontrol.util.TiqiaaService;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule;
import com.xiaomi.smarthome.plugin.DeviceConstant;

public class AVFrame {
    public static final int AUDIO_CHANNEL_MONO = 0;
    public static final int AUDIO_CHANNEL_STERO = 1;
    public static final int AUDIO_CODEC_AAC = 136;
    public static final int AUDIO_CODEC_G711 = 138;
    public static final int AUDIO_CODEC_PCM = 1024;
    public static final int AUDIO_DATABITS_16 = 1;
    public static final int AUDIO_DATABITS_8 = 0;
    public static final int AUDIO_SAMPLE_11K = 1;
    public static final int AUDIO_SAMPLE_12K = 2;
    public static final int AUDIO_SAMPLE_16K = 3;
    public static final int AUDIO_SAMPLE_22K = 4;
    public static final int AUDIO_SAMPLE_24K = 5;
    public static final int AUDIO_SAMPLE_32K = 6;
    public static final int AUDIO_SAMPLE_44K = 7;
    public static final int AUDIO_SAMPLE_48K = 8;
    public static final int AUDIO_SAMPLE_8K = 0;
    public static final int FRAMEINFO_SIZE = 24;
    public static final byte FRM_STATE_COMPLETE = 0;
    public static final byte FRM_STATE_INCOMPLETE = 1;
    public static final byte FRM_STATE_LOSED = 2;
    public static final byte FRM_STATE_UNKOWN = -1;
    public static final int IPC_FRAME_FLAG_IFRAME = 1;
    public static final int IPC_FRAME_FLAG_IO = 3;
    public static final int IPC_FRAME_FLAG_MD = 2;
    public static final int IPC_FRAME_FLAG_PBFRAME = 0;
    public static final int MEDIA_CODEC_AUDIO_ADPCM = 139;
    public static final int MEDIA_CODEC_AUDIO_G726 = 143;
    public static final int MEDIA_CODEC_AUDIO_MP3 = 142;
    public static final int MEDIA_CODEC_AUDIO_PCM = 140;
    public static final int MEDIA_CODEC_AUDIO_SPEEX = 141;
    public static final int MEDIA_CODEC_UNKNOWN = 0;
    public static final int MEDIA_CODEC_VIDEO_H263 = 77;
    public static final int MEDIA_CODEC_VIDEO_H264 = 78;
    public static final int MEDIA_CODEC_VIDEO_HEVC = 80;
    public static final int MEDIA_CODEC_VIDEO_MJPEG = 79;
    public static final int MEDIA_CODEC_VIDEO_MPEG4 = 76;
    public long cam_index;
    private short codec_id = 0;
    private boolean decrypt = false;
    private byte flags = -1;
    public byte[] frmData = null;
    private long frmNo = -1;
    private int frmSize = 0;
    private byte frmState = 0;
    public byte isHaveLogo;
    public byte isPlayback;
    public byte isShowTime;
    private String model = "";
    private byte onlineNum = 0;
    private int timestamp = 0;
    public int timestamp_ms;
    public byte usecount;
    private int videoHeight = 0;
    private int videoWidth = 0;

    public static int getSamplerate(byte b) {
        switch (b >>> 2) {
            case 1:
                return 11025;
            case 2:
                return TiqiaaService.BaseCallBack.ERROR_CODE_NO_NET;
            case 3:
                return RecordDevice.PCM_FREQUENCE_16K;
            case 4:
                return 22050;
            case 5:
                return 24000;
            case 6:
                return AsrRequest.d;
            case 7:
                return 44100;
            case 8:
                return MIOTAudioModule.SAMPLING_RATE;
            default:
                return 8000;
        }
    }

    public AVFrame(byte b, byte[] bArr, byte[] bArr2, int i, boolean z) {
        this.codec_id = byteArrayToShort(bArr, 0, z);
        this.flags = bArr[2];
        this.cam_index = (long) bArr[3];
        this.onlineNum = bArr[4];
        this.usecount = bArr[5];
        this.frmNo = (long) byteArrayToShort(bArr, 6, z);
        this.videoWidth = byteArrayToShort(bArr, 8, z);
        this.videoHeight = byteArrayToShort(bArr, 10, z);
        this.timestamp = byteArrayToInt(bArr, 12, z);
        byte b2 = bArr[16];
        this.isPlayback = (byte) (b2 & 1);
        this.isShowTime = (byte) ((b2 >> 1) & 1);
        this.isHaveLogo = (byte) ((b2 >> 2) & 1);
        this.frmState = b;
        this.frmSize = i;
        this.frmData = bArr2;
        if (bArr.length > 20) {
            this.timestamp_ms = byteArrayToInt(bArr, 20, z);
        }
    }

    public AVFrame(byte b, byte[] bArr, byte[] bArr2, int i, boolean z, String str) {
        this.codec_id = byteArrayToShort(bArr, 0, z);
        this.flags = bArr[2];
        this.cam_index = (long) bArr[3];
        this.model = str;
        if (DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(str)) {
            this.frmNo = (long) byteArrayToInt(bArr, 4, z);
        } else {
            this.onlineNum = bArr[4];
            this.usecount = bArr[5];
            this.frmNo = (long) byteArrayToShort(bArr, 6, z);
        }
        this.videoWidth = byteArrayToShort(bArr, 8, z);
        this.videoHeight = byteArrayToShort(bArr, 10, z);
        this.timestamp = byteArrayToInt(bArr, 12, z);
        byte b2 = bArr[16];
        this.isPlayback = (byte) (b2 & 1);
        this.isShowTime = (byte) ((b2 >> 1) & 1);
        this.isHaveLogo = (byte) ((b2 >> 2) & 1);
        this.frmState = b;
        this.frmSize = i;
        this.frmData = bArr2;
        if (bArr.length > 20) {
            this.timestamp_ms = byteArrayToInt(bArr, 20, z);
        }
    }

    public boolean isIFrame() {
        return (this.flags & 1) == 1;
    }

    public byte getFlags() {
        return this.flags;
    }

    public byte getFrmState() {
        return this.frmState;
    }

    public long getFrmNo() {
        return this.frmNo;
    }

    public int getFrmSize() {
        return this.frmSize;
    }

    public void setFrmSize(int i) {
        this.frmSize = i;
    }

    public byte getOnlineNum() {
        return this.onlineNum;
    }

    public short getCodecId() {
        return this.codec_id;
    }

    public int getVideoType() {
        return this.codec_id == 78 ? 1 : 2;
    }

    public int getTimeStamp() {
        return this.timestamp;
    }

    public int getTimeStampReal() {
        return this.timestamp_ms;
    }

    public boolean isPlayback() {
        return this.isPlayback != 0;
    }

    public boolean isWartTime() {
        return this.isShowTime != 0;
    }

    public boolean isHaveLogo() {
        return this.isHaveLogo != 0;
    }

    public int getVideoWidth() {
        return this.videoWidth;
    }

    public int getVideoHeight() {
        return this.videoHeight;
    }

    public final short byteArrayToShort(byte[] bArr, int i, boolean z) {
        if (z) {
            return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
        }
        return (short) (((bArr[i + 1] & 255) << 8) | (bArr[i] & 255));
    }

    public final int byteArrayToInt(byte[] bArr, int i, boolean z) {
        if (z) {
            return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
        }
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }
}
