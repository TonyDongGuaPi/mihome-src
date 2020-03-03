package com.xiaomi.smarthome.camera.activity.timelapse;

import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.xiaomi.CameraDevice;
import com.xiaomi.connection.ByteOperator;
import com.xiaomi.connection.CamCommand;
import com.xiaomi.connection.CamProtocolUtils;
import com.xiaomi.mistream.XmStreamClient;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.IStreamCmdMessageListener;
import java.util.ArrayList;

public class TimeLapseTaskManager {
    IStreamCmdMessageListener mStreamCmdMessageListener;
    private final XmStreamClient mXmStreamClient;

    public TimeLapseTaskManager(CameraDevice cameraDevice, IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mXmStreamClient = cameraDevice.v();
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
    }

    public void setTimeLapseTask(TimelapseTask timelapseTask, IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
        this.mXmStreamClient.streamCmdMessage(CameraPlayerEx.f, Util.a(CamCommand.a(timelapseTask.toProtocolByteArray())), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }

            public void onFailed(int i, String str) {
                if (TimeLapseTaskManager.this.mStreamCmdMessageListener != null) {
                    TimeLapseTaskManager.this.mStreamCmdMessageListener.onSendCmdError();
                }
            }
        });
    }

    public void closeCurrentTimeLapseTask(IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
        this.mXmStreamClient.streamCmdMessage(CameraPlayerEx.f, Util.a(CamCommand.a(TimelapseTask.getEmpty())), new IMISSListener() {
            public void onFailed(int i, String str) {
            }

            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }
        });
    }

    private byte[] K10142_deleteVideoClip(int i, ArrayList<TimelapseTask> arrayList) {
        byte[] bArr = new byte[((arrayList.size() * 5) + 4)];
        ByteOperator.a(bArr, 0, ByteOperator.a(i), 0, 3);
        int i2 = 4;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            bArr[i2] = (byte) arrayList.get(i3).getVideoType();
            int i4 = i2 + 1;
            ByteOperator.a(bArr, i4, ByteOperator.a(arrayList.get(i3).getStartTimestampInSeconds()), 0, 3);
            i2 = i4 + 4;
        }
        return CamProtocolUtils.a(10142, bArr.length, bArr);
    }

    public void getTimeLapseTask(IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
        this.mXmStreamClient.streamCmdMessage(CameraPlayerEx.f, Util.a(CamCommand.c()), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }

            public void onFailed(int i, String str) {
                if (TimeLapseTaskManager.this.mStreamCmdMessageListener != null) {
                    TimeLapseTaskManager.this.mStreamCmdMessageListener.onSendCmdError();
                }
            }
        });
    }

    public void getTimelapseList(IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
        this.mXmStreamClient.streamCmdMessage(CameraPlayerEx.f, Util.a(CamCommand.d()), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }

            public void onFailed(int i, String str) {
                if (TimeLapseTaskManager.this.mStreamCmdMessageListener != null) {
                    TimeLapseTaskManager.this.mStreamCmdMessageListener.onSendCmdError();
                }
            }
        });
    }

    public void getSDTimeLapsePhotographyFile(int i, String str, IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
        this.mXmStreamClient.streamCmdMessage(CameraPlayerEx.f, Util.a(CamCommand.a(i, str)), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }

            public void onFailed(int i, String str) {
                if (TimeLapseTaskManager.this.mStreamCmdMessageListener != null) {
                    TimeLapseTaskManager.this.mStreamCmdMessageListener.onSendCmdError();
                }
            }
        });
    }

    public void closeTimeLapsePhotographySever(int i, IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
        this.mXmStreamClient.streamCmdMessage(CameraPlayerEx.f, Util.a(CamCommand.a(i)), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }

            public void onFailed(int i, String str) {
                if (TimeLapseTaskManager.this.mStreamCmdMessageListener != null) {
                    TimeLapseTaskManager.this.mStreamCmdMessageListener.onSendCmdError();
                }
            }
        });
    }

    public void deleteRecord(ArrayList<TimelapseTask> arrayList, IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
        this.mXmStreamClient.streamCmdMessage(CameraPlayerEx.f, Util.a(K10142_deleteVideoClip(3, arrayList)), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
                SDKLog.b("AlbumActivity", "deleteRecord onSuccess");
            }

            public void onFailed(int i, String str) {
                SDKLog.b("AlbumActivity", "deleteRecord onFailed");
                if (TimeLapseTaskManager.this.mStreamCmdMessageListener != null) {
                    TimeLapseTaskManager.this.mStreamCmdMessageListener.onSendCmdError();
                }
            }
        });
    }
}
