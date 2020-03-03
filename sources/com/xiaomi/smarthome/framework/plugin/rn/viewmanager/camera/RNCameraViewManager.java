package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.camera;

import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.plugin.rn.RNRuntime;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import java.util.Map;
import javax.annotation.Nullable;

public class RNCameraViewManager extends ViewGroupManager<RNVideoViewEx> {
    private static final int HIDE_SURFACE_VIEW = 8;
    private static String REACT_CLASS = "MHCameraOpenGLView";
    private static final int SET_FULL_ID = 2;
    private static final int START_AUDIO_PLAY = 4;
    private static final int START_AUDIO_RECORD = 6;
    private static final int START_RENDER_ID = 1;
    private static final int STOP_AUDIO_PLAY = 5;
    private static final int STOP_AUDIO_RECORD = 7;
    private static final int STOP_RENDER_ID = 3;
    /* access modifiers changed from: private */
    public int audioCodec;
    /* access modifiers changed from: private */
    public int audioRecordChannel;
    /* access modifiers changed from: private */
    public int audioRecordDataBits;
    /* access modifiers changed from: private */
    public int audioRecordSampleRate;
    private float correctRadius = 1.1f;
    private String did;
    private float distortX = 0.24375f;
    private float distortY = 0.04411765f;
    private boolean forceSoftDecode = false;
    private boolean fullscreenState = false;
    Handler mMainHandler = new Handler(Looper.getMainLooper());
    private ThemedReactContext mReactContext;
    private RNVideoViewEx mRnVideoViewEx;
    private float osdx = 0.24375f;
    private float osdy = 0.04411765f;
    private float scale = 1.0f;
    private int videoCodec;

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, RNVideoViewEx rNVideoViewEx) {
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public RNVideoViewEx createViewInstance(ThemedReactContext themedReactContext) {
        this.mReactContext = themedReactContext;
        RNVideoViewEx rNVideoViewEx = new RNVideoViewEx(this.mReactContext, (AttributeSet) null);
        this.mRnVideoViewEx = rNVideoViewEx;
        return rNVideoViewEx;
    }

    @ReactProp(name = "did")
    public void setDid(RNVideoViewEx rNVideoViewEx, String str) {
        this.did = str;
        rNVideoViewEx.setDid(str);
    }

    @ReactProp(name = "videoCodec")
    public void setVideoCodec(RNVideoViewEx rNVideoViewEx, int i) {
        if (i == 4) {
            this.videoCodec = 0;
        } else {
            this.videoCodec = 1;
        }
    }

    @ReactProp(name = "osdx")
    public void setOsdx(RNVideoViewEx rNVideoViewEx, float f) {
        this.osdx = f;
    }

    @ReactProp(name = "osdy")
    public void setOsdy(RNVideoViewEx rNVideoViewEx, float f) {
        this.osdy = f;
    }

    @ReactProp(name = "scale")
    public void setScale(RNVideoViewEx rNVideoViewEx, float f) {
        this.scale = f;
    }

    @ReactProp(name = "fullscreenState")
    public void setFullscreenState(RNVideoViewEx rNVideoViewEx, boolean z) {
        this.fullscreenState = z;
        this.mRnVideoViewEx.setFullscreenState(z);
        RNRuntime.a().b(z);
    }

    @ReactProp(name = "audioCodec")
    public void setAudioCodec(RNVideoViewEx rNVideoViewEx, int i) {
        this.audioCodec = i;
    }

    @ReactProp(name = "audioRecordSampleRate")
    public void setAudioRecordSampleRate(RNVideoViewEx rNVideoViewEx, int i) {
        this.audioRecordSampleRate = i;
    }

    @ReactProp(name = "audioRecordChannel")
    public void setAudioRecordChannel(RNVideoViewEx rNVideoViewEx, int i) {
        this.audioRecordChannel = i;
    }

    @ReactProp(name = "forceSoftDecode")
    public void setForceSoftDecode(RNVideoViewEx rNVideoViewEx, boolean z) {
        this.forceSoftDecode = z;
    }

    @ReactProp(name = "audioRecordDataBits")
    public void setAudioRecordDataBits(RNVideoViewEx rNVideoViewEx, int i) {
        this.audioRecordDataBits = i;
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("startRender", 1, "stopRender", 3, "startAudioPlay", 4, "stopAudioPlay", 5, "startAudioRecord", 6, "stopAudioRecord", 7, "hidesSurfaceView", 8);
    }

    public void receiveCommand(final RNVideoViewEx rNVideoViewEx, int i, @Nullable ReadableArray readableArray) {
        RnPluginLog.a("commandId=" + i);
        final DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(this.did);
        if (i == 1) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    RNCameraViewManager.this.startRender(rNVideoViewEx);
                }
            });
        } else if (i != 2) {
            if (i == 3) {
                this.mMainHandler.post(new Runnable() {
                    public void run() {
                        RNCameraViewManager.this.stopRender(rNVideoViewEx);
                    }
                });
            } else if (i == 4) {
                this.mMainHandler.post(new Runnable() {
                    public void run() {
                        RNCameraManager.a().a(deviceByDid, RNCameraViewManager.this.audioCodec);
                    }
                });
            } else if (i == 5) {
                this.mMainHandler.post(new Runnable() {
                    public void run() {
                        RNCameraManager.a().b(deviceByDid);
                    }
                });
            } else if (i == 6) {
                this.mMainHandler.post(new Runnable() {
                    public void run() {
                        RNCameraManager.a().a(deviceByDid, RNCameraViewManager.this.audioRecordSampleRate, RNCameraViewManager.this.audioRecordDataBits, RNCameraViewManager.this.audioRecordChannel, RNCameraViewManager.this.audioCodec);
                    }
                });
            } else if (i == 7) {
                this.mMainHandler.post(new Runnable() {
                    public void run() {
                        RNCameraManager.a().c(deviceByDid);
                    }
                });
            } else if (i == 8) {
                this.mMainHandler.post(new Runnable() {
                    public void run() {
                        RNCameraManager.a().b(deviceByDid.did);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void stopRender(RNVideoViewEx rNVideoViewEx) {
        rNVideoViewEx.stopRender();
    }

    /* access modifiers changed from: private */
    public void startRender(RNVideoViewEx rNVideoViewEx) {
        RNCameraManager.a().a((View) rNVideoViewEx, this.did, !this.forceSoftDecode, this.videoCodec);
        rNVideoViewEx.setDistort(this.osdx, this.osdy, this.correctRadius);
        rNVideoViewEx.setScale(this.scale);
        rNVideoViewEx.startRender();
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return super.getExportedCustomDirectEventTypeConstants();
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
    }

    public void onDropViewInstance(RNVideoViewEx rNVideoViewEx) {
        super.onDropViewInstance(rNVideoViewEx);
        RnPluginLog.d("ondropviewinstance");
    }
}
