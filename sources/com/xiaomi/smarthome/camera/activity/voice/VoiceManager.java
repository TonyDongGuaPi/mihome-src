package com.xiaomi.smarthome.camera.activity.voice;

import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.Utils.Util;
import com.xiaomi.CameraDevice;
import com.xiaomi.connection.CamCommand;
import com.xiaomi.mistream.XmStreamClient;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.IStreamCmdMessageListener;

public class VoiceManager {
    /* access modifiers changed from: private */
    public IStreamCmdMessageListener mStreamCmdMessageListener;
    private final XmStreamClient mXmStreamClient;

    public VoiceManager(CameraDevice cameraDevice, IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mXmStreamClient = cameraDevice.v();
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
    }

    public void sendRecordVoiceSuccess(long j, IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
        this.mXmStreamClient.streamCmdMessage(CameraPlayerEx.f, Util.a(CamCommand.b(j)), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }

            public void onFailed(int i, String str) {
                if (VoiceManager.this.mStreamCmdMessageListener != null) {
                    VoiceManager.this.mStreamCmdMessageListener.onSendCmdError();
                }
            }
        });
    }

    public void getVoiceState(IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
        this.mXmStreamClient.streamCmdMessage(CameraPlayerEx.f, Util.a(CamCommand.e()), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }

            public void onFailed(int i, String str) {
                if (VoiceManager.this.mStreamCmdMessageListener != null) {
                    VoiceManager.this.mStreamCmdMessageListener.onSendCmdError();
                }
            }
        });
    }

    public void setVoiceState(int i, int i2, IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
        this.mXmStreamClient.streamCmdMessage(CameraPlayerEx.f, Util.a(CamCommand.a(i, i2)), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }

            public void onFailed(int i, String str) {
                if (VoiceManager.this.mStreamCmdMessageListener != null) {
                    VoiceManager.this.mStreamCmdMessageListener.onSendCmdError();
                }
            }
        });
    }

    public void voiceAudition(IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.mStreamCmdMessageListener = iStreamCmdMessageListener;
        this.mXmStreamClient.streamCmdMessage(CameraPlayerEx.f, Util.a(CamCommand.g()), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }

            public void onFailed(int i, String str) {
                if (VoiceManager.this.mStreamCmdMessageListener != null) {
                    VoiceManager.this.mStreamCmdMessageListener.onSendCmdError();
                }
            }
        });
    }
}
