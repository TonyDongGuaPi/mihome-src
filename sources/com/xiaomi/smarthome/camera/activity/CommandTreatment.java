package com.xiaomi.smarthome.camera.activity;

import android.os.Handler;
import android.util.Log;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.tutk.CameraClient;
import com.xiaomi.connection.ByteOperator;
import com.xiaomi.connection.CamProtocolUtils;
import com.xiaomi.smarthome.camera.activity.timelapse.TimelapseTask;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import java.util.ArrayList;

public class CommandTreatment {
    public static final int CHANNEL_VERIFY_FAILED = 10000;
    public static final int CLOSE_CAMERA_SD_TIMELAPSE_PHOTOGRAPHY_SEVER = 210011;
    public static final int CONNECT_CAMERA_SUCCESS = 21002;
    public static final int DEL_VIDEO_CLIP_RESULT = 21007;
    public static final int GET_CAMERA_SD_TIMELAPSE_PHOTOGRAPHY = 21010;
    public static final int GET_CURRENT_FRAME_RATE = 210012;
    public static final int GET_TIMELAPSE_TASK_FROM_SD = 21006;
    public static final int GET_VOICE_STATE_RESULT = 21009;
    public static final int OPEN_SPEAK = 210013;
    public static final int RES_TIMELAPSE_STATUS = 21005;
    public static final int RES_VIDEO_PARAM = 21003;
    public static final int SEND_RECORD_VOICE_SUCCESS_RESULT = 21008;
    public static final int SET_TIMELAPSE_RESULT = 21004;
    public static final int SET_VOICE_STATE_RESULT = 210010;
    private static final String TAG = "CommandTreatment ";
    public static final int VOICE_AUDITION = 210014;
    private static int logDataIndex;
    private static int logDataTotalLength;
    private final int DATA_SIZE = 1024;
    private int dataIndex = 0;
    private int dataLength = 0;
    private Handler handler;
    private CameraClient mCameraClient;
    private MijiaCameraDevice mCameraDevice;
    private byte[] processData;
    private byte[] tempData = new byte[1024];

    public CommandTreatment(Handler handler2, MijiaCameraDevice mijiaCameraDevice) {
        this.handler = handler2;
        this.mCameraDevice = mijiaCameraDevice;
    }

    public void processData(byte[] bArr) {
        if (this.dataLength <= this.dataIndex) {
            this.dataIndex = 0;
            System.arraycopy(bArr, 0, this.tempData, 0, bArr.length);
            this.dataLength = bArr.length;
        } else if (1024 - this.dataLength > bArr.length) {
            System.arraycopy(bArr, 0, this.tempData, this.dataLength, bArr.length);
            this.dataLength += bArr.length;
        } else if ((this.dataLength - this.dataIndex) + bArr.length > 1024) {
            this.dataIndex = 0;
            System.arraycopy(bArr, 0, this.tempData, 0, bArr.length);
            this.dataLength = bArr.length;
            SDKLog.b(TAG, "processData: too large, drop");
        } else {
            byte[] bArr2 = new byte[(this.dataLength - this.dataIndex)];
            System.arraycopy(this.tempData, this.dataIndex, bArr2, 0, this.dataLength - this.dataIndex);
            System.arraycopy(bArr2, 0, this.tempData, 0, this.dataLength - this.dataIndex);
            this.dataLength = bArr2.length;
            this.dataIndex = 0;
            System.arraycopy(bArr, 0, this.tempData, this.dataLength, bArr.length);
            this.dataLength += bArr.length;
            SDKLog.b(TAG, "processData: move froward！");
        }
        boolean z = false;
        do {
            int searchHead = searchHead(this.tempData, this.dataIndex, this.dataLength);
            if (searchHead != -1) {
                this.dataIndex = searchHead;
                if (this.dataLength - this.dataIndex >= 16) {
                    byte[] bArr3 = new byte[16];
                    System.arraycopy(this.tempData, this.dataIndex, bArr3, 0, 16);
                    boolean z2 = true;
                    int a2 = CamProtocolUtils.a(bArr3, true);
                    if (a2 >= 0) {
                        if (a2 <= (this.dataLength - this.dataIndex) - 16) {
                            int i = a2 + 16;
                            this.processData = new byte[i];
                            System.arraycopy(this.tempData, this.dataIndex, this.processData, 0, i);
                            if (this.processData instanceof byte[]) {
                                protocolControl(this.processData);
                                this.dataIndex = this.dataIndex + 16 + a2;
                                z = z2;
                                continue;
                            }
                        }
                        z2 = false;
                        z = z2;
                        continue;
                    } else {
                        continue;
                    }
                }
            }
            z = false;
            continue;
        } while (z);
    }

    /* access modifiers changed from: protected */
    public int searchHead(byte[] bArr, int i, int i2) {
        while (i < i2 - 2) {
            if (bArr[i] == 72 && bArr[i + 1] == 76) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private void protocolControl(byte[] bArr) {
        CamProtocolUtils a2 = CamProtocolUtils.a(bArr);
        if (a2 == null) {
            SDKLog.d(TAG, "protocolControl receive command is null");
            return;
        }
        SDKLog.c(TAG, "protocolControl receive command " + a2.c());
        switch (a2.c()) {
            case 10003:
                SDKLog.b(TAG, "protocolControl command 10003:" + ByteOperator.d(a2.e()));
                return;
            case 10011:
                if (a2.e() != null && a2.e().length >= 2) {
                    Log.d(Tag.c, "speek通道=" + a2.e()[0] + ",结果=" + a2.e()[1]);
                    this.handler.obtainMessage(OPEN_SPEAK, a2.e()).sendToTarget();
                    return;
                }
                return;
            case 10051:
                if (a2.e() != null && a2.e().length >= 6) {
                    this.handler.obtainMessage(RES_VIDEO_PARAM, a2.e()).sendToTarget();
                    return;
                }
                return;
            case 10135:
                if (a2.e() != null && a2.e().length >= 1) {
                    ArrayList arrayList = new ArrayList();
                    byte b = a2.e()[0];
                    if (b != 0) {
                        int i = 0;
                        for (int i2 = 0; i2 < b; i2++) {
                            int i3 = i + 1;
                            int d = ByteOperator.d(a2.e(), i3);
                            int i4 = i3 + 4;
                            byte b2 = a2.e()[i4];
                            i = i4 + 1;
                            byte b3 = a2.e()[i];
                            LogUtil.a("AlbumActivity", "fileStatue ==" + b3);
                            LogUtil.a("AlbumActivity", "timelapseStatue ==" + b2);
                            if (d == 0 || b2 != 3) {
                                Log.i(TAG, "===================固件返回值error===timelapseStatue=" + b2);
                            } else {
                                TimelapseTask timelapseTask = new TimelapseTask(d, b2, b3, Util.c());
                                timelapseTask.setTimelapseSaveType(1);
                                LogUtil.a("AlbumActivity", "10135 tt status =" + timelapseTask.status);
                                arrayList.add(timelapseTask);
                                Log.d(TAG, "=============timelapseStatue=====" + b2);
                            }
                        }
                    }
                    this.handler.obtainMessage(GET_TIMELAPSE_TASK_FROM_SD, arrayList).sendToTarget();
                    return;
                }
                return;
            case 10137:
                if (a2.e() != null && a2.e().length >= 3) {
                    this.handler.obtainMessage(GET_CAMERA_SD_TIMELAPSE_PHOTOGRAPHY, a2.e()).sendToTarget();
                    return;
                }
                return;
            case 10139:
                if (a2.e() != null && a2.e().length >= 1) {
                    this.handler.obtainMessage(CLOSE_CAMERA_SD_TIMELAPSE_PHOTOGRAPHY_SEVER, a2.e()).sendToTarget();
                    return;
                }
                return;
            case 10143:
                if (a2.e() != null && a2.e().length >= 1) {
                    Log.i(TAG, "====================res.getData()=" + a2.e()[0]);
                    this.handler.obtainMessage(DEL_VIDEO_CLIP_RESULT, a2.e()[0], -1).sendToTarget();
                    return;
                }
                return;
            case 10531:
                if (a2.e() != null && a2.e().length >= 1) {
                    Log.i(TAG, "====================res.getData()=" + a2.e()[0]);
                    this.handler.obtainMessage(SEND_RECORD_VOICE_SUCCESS_RESULT, a2.e()[0], -1).sendToTarget();
                    return;
                }
                return;
            case 10533:
                if (a2.e() != null && a2.e().length >= 3) {
                    this.handler.obtainMessage(GET_VOICE_STATE_RESULT, a2.e()).sendToTarget();
                    return;
                }
                return;
            case 10535:
                if (a2.e() != null && a2.e().length >= 1) {
                    this.handler.obtainMessage(SET_VOICE_STATE_RESULT, a2.e()).sendToTarget();
                    return;
                }
                return;
            case 10537:
                if (a2.e() != null && a2.e().length >= 1) {
                    this.handler.obtainMessage(VOICE_AUDITION, a2.e()).sendToTarget();
                    return;
                }
                return;
            case 10541:
                if (a2.e() != null && a2.e().length >= 1) {
                    this.handler.obtainMessage(GET_CURRENT_FRAME_RATE, a2.e()).sendToTarget();
                    return;
                }
                return;
            case 11081:
                if (a2.e() != null && a2.e().length >= 20) {
                    LogUtil.a("TimeLapsePhotographyActivity", "res.getData().length=" + a2.e().length);
                    this.handler.obtainMessage(RES_TIMELAPSE_STATUS, 1, -1, a2.e()).sendToTarget();
                    return;
                }
                return;
            case 11083:
                if (a2.e() != null && a2.e().length >= 5) {
                    int d2 = ByteOperator.d(a2.e(), 0);
                    Log.i(TAG, "protocolControl command 11083 SET_TIMELAPSE_RESULT num:[" + d2 + "]res.getData4[" + a2.e()[4]);
                    this.handler.obtainMessage(SET_TIMELAPSE_RESULT, d2, a2.e()[4]).sendToTarget();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
