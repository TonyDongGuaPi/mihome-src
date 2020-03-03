package com.xiaomi.miot.store.component.video;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.taobao.weex.ui.view.gesture.WXGestureType;

class VideoEventEmitter {
    private static final String EVENT_AUDIO_BECOMING_NOISY = "onVideoAudioBecomingNoisy";
    private static final String EVENT_AUDIO_FOCUS_CHANGE = "onAudioFocusChanged";
    private static final String EVENT_BUFFER = "onVideoBuffer";
    private static final String EVENT_DRAG = "onDrag";
    private static final String EVENT_END = "onEnd";
    private static final String EVENT_ERROR = "onError";
    private static final String EVENT_FULLSCREEN_DID = "onFullScreenDid";
    private static final String EVENT_FULLSCREEN_WILL = "onFullScreenWill";
    private static final String EVENT_IDLE = "onVideoIdle";
    private static final String EVENT_LOAD = "onLoad";
    private static final String EVENT_LOAD_START = "onLoadStart";
    private static final String EVENT_MUTED = "onMuted";
    private static final String EVENT_NAME = "name";
    private static final String EVENT_PARAMS = "params";
    private static final String EVENT_PAUSE = "onPause";
    private static final String EVENT_PLAYBACK_RATE_CHANGE = "onPlaybackRateChange";
    private static final String EVENT_PLAY_EVENT = "onPlayEvent";
    private static final String EVENT_PROGRESS = "onProgress";
    private static final String EVENT_PROP_AUDIO_TRACKS = "audioTracks";
    private static final String EVENT_PROP_CURRENT_TIME = "currentTime";
    private static final String EVENT_PROP_DURATION = "duration";
    private static final String EVENT_PROP_ERROR = "error";
    private static final String EVENT_PROP_ERROR_EXCEPTION = "errorException";
    private static final String EVENT_PROP_ERROR_STRING = "errorString";
    private static final String EVENT_PROP_FAST_FORWARD = "canPlayFastForward";
    private static final String EVENT_PROP_HAS_AUDIO_FOCUS = "hasAudioFocus";
    private static final String EVENT_PROP_HEIGHT = "height";
    private static final String EVENT_PROP_IS_BUFFERING = "isBuffering";
    private static final String EVENT_PROP_NATURAL_SIZE = "naturalSize";
    private static final String EVENT_PROP_ORIENTATION = "orientation";
    private static final String EVENT_PROP_PLAYABLE_DURATION = "playableDuration";
    private static final String EVENT_PROP_PLAYBACK_RATE = "playbackRate";
    private static final String EVENT_PROP_REVERSE = "canPlayReverse";
    private static final String EVENT_PROP_SEEKABLE_DURATION = "seekableDuration";
    private static final String EVENT_PROP_SEEK_TIME = "seekTime";
    private static final String EVENT_PROP_SLOW_FORWARD = "canPlaySlowForward";
    private static final String EVENT_PROP_SLOW_REVERSE = "canPlaySlowReverse";
    private static final String EVENT_PROP_STATUS = "status";
    private static final String EVENT_PROP_STEP_BACKWARD = "canStepBackward";
    private static final String EVENT_PROP_STEP_FORWARD = "canStepForward";
    private static final String EVENT_PROP_TEXT_TRACKS = "textTracks";
    private static final String EVENT_PROP_TIMED_METADATA = "metadata";
    private static final String EVENT_PROP_WIDTH = "width";
    private static final String EVENT_READY = "onReadyForDisplay";
    private static final String EVENT_RESUME = "onPlaybackResume";
    private static final String EVENT_SEEK = "onSeek";
    private static final String EVENT_STALLED = "onPlaybackStalled";
    private static final String EVENT_START = "onStart";
    private static final String EVENT_TIMED_METADATA = "onTimedMetadata";
    static final String[] Events = {EVENT_PLAY_EVENT};
    private final RCTEventEmitter eventEmitter;
    private int viewId = -1;

    VideoEventEmitter(ReactContext reactContext) {
        this.eventEmitter = (RCTEventEmitter) reactContext.getJSModule(RCTEventEmitter.class);
    }

    /* access modifiers changed from: package-private */
    public void setViewId(int i) {
        this.viewId = i;
    }

    /* access modifiers changed from: package-private */
    public void loadStart() {
        receivePlayEvent(wrapEvent(EVENT_LOAD_START, Arguments.createMap()));
    }

    /* access modifiers changed from: package-private */
    public void load(double d, double d2, int i, int i2, WritableArray writableArray, WritableArray writableArray2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("duration", d / 1000.0d);
        createMap.putDouble("currentTime", d2 / 1000.0d);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putInt("width", i);
        createMap2.putInt("height", i2);
        if (i > i2) {
            createMap2.putString("orientation", "landscape");
        } else {
            createMap2.putString("orientation", "portrait");
        }
        createMap.putMap("naturalSize", createMap2);
        createMap.putArray(EVENT_PROP_AUDIO_TRACKS, writableArray);
        createMap.putArray(EVENT_PROP_TEXT_TRACKS, writableArray2);
        createMap.putBoolean("canPlayFastForward", true);
        createMap.putBoolean("canPlaySlowForward", true);
        createMap.putBoolean("canPlaySlowReverse", true);
        createMap.putBoolean("canPlayReverse", true);
        createMap.putBoolean("canPlayFastForward", true);
        createMap.putBoolean("canStepBackward", true);
        createMap.putBoolean("canStepForward", true);
        receivePlayEvent(wrapEvent(EVENT_LOAD, createMap));
    }

    /* access modifiers changed from: package-private */
    public void progressChanged(double d, double d2, double d3) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("currentTime", d / 1000.0d);
        createMap.putDouble("playableDuration", d2 / 1000.0d);
        createMap.putDouble("seekableDuration", d3 / 1000.0d);
        receivePlayEvent(wrapEvent(EVENT_PROGRESS, createMap));
    }

    /* access modifiers changed from: package-private */
    public void seek(long j, long j2) {
        WritableMap createMap = Arguments.createMap();
        double d = (double) j;
        Double.isNaN(d);
        createMap.putDouble("currentTime", d / 1000.0d);
        double d2 = (double) j2;
        Double.isNaN(d2);
        createMap.putDouble("seekTime", d2 / 1000.0d);
        receivePlayEvent(wrapEvent(EVENT_SEEK, createMap));
    }

    /* access modifiers changed from: package-private */
    public void ready() {
        receivePlayEvent(wrapEvent(EVENT_READY, Arguments.createMap()));
    }

    /* access modifiers changed from: package-private */
    public void buffering(boolean z) {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean(EVENT_PROP_IS_BUFFERING, z);
        receivePlayEvent(wrapEvent(EVENT_BUFFER, createMap));
    }

    /* access modifiers changed from: package-private */
    public void idle() {
        receivePlayEvent(wrapEvent(EVENT_IDLE, Arguments.createMap()));
    }

    /* access modifiers changed from: package-private */
    public void end() {
        receivePlayEvent(wrapEvent(EVENT_END, Arguments.createMap()));
    }

    /* access modifiers changed from: package-private */
    public void muted(boolean z) {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("status", z);
        receivePlayEvent(wrapEvent(EVENT_MUTED, createMap));
    }

    /* access modifiers changed from: package-private */
    public void fullscreenWillPresent() {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("status", true);
        receivePlayEvent(wrapEvent(EVENT_FULLSCREEN_WILL, createMap));
    }

    /* access modifiers changed from: package-private */
    public void fullscreenDidPresent() {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("status", true);
        receivePlayEvent(wrapEvent(EVENT_FULLSCREEN_DID, createMap));
    }

    /* access modifiers changed from: package-private */
    public void fullscreenWillDismiss() {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("status", false);
        receivePlayEvent(wrapEvent(EVENT_FULLSCREEN_WILL, createMap));
    }

    /* access modifiers changed from: package-private */
    public void fullscreenDidDismiss() {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("status", false);
        receivePlayEvent(wrapEvent(EVENT_FULLSCREEN_DID, createMap));
    }

    /* access modifiers changed from: package-private */
    public void error(String str, Exception exc) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(EVENT_PROP_ERROR_STRING, str);
        createMap.putString(EVENT_PROP_ERROR_EXCEPTION, exc.getMessage());
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("error", createMap);
        receivePlayEvent(wrapEvent(EVENT_ERROR, createMap2));
    }

    /* access modifiers changed from: package-private */
    public void playbackRateChange(float f) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(EVENT_PROP_PLAYBACK_RATE, (double) f);
        receivePlayEvent(wrapEvent(EVENT_PLAYBACK_RATE_CHANGE, createMap));
    }

    /* access modifiers changed from: package-private */
    public void callOnPause(boolean z, long j) {
        WritableMap createMap = Arguments.createMap();
        double d = (double) j;
        Double.isNaN(d);
        createMap.putDouble("currentTime", d / 1000.0d);
        receivePlayEvent(wrapEvent(z ? "onPause" : "onStart", createMap));
    }

    /* access modifiers changed from: package-private */
    public void timedMetadata(Metadata metadata) {
        WritableArray createArray = Arguments.createArray();
        for (int i = 0; i < metadata.length(); i++) {
            Id3Frame id3Frame = (Id3Frame) metadata.get(i);
            String str = "";
            if (id3Frame instanceof TextInformationFrame) {
                str = ((TextInformationFrame) id3Frame).value;
            }
            String str2 = id3Frame.id;
            WritableMap createMap = Arguments.createMap();
            createMap.putString(WXGestureType.GestureInfo.POINTER_ID, str2);
            createMap.putString("value", str);
            createArray.pushMap(createMap);
        }
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putArray("metadata", createArray);
        receivePlayEvent(wrapEvent(EVENT_TIMED_METADATA, createMap2));
    }

    /* access modifiers changed from: package-private */
    public void audioFocusChanged(boolean z) {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean(EVENT_PROP_HAS_AUDIO_FOCUS, z);
        receivePlayEvent(wrapEvent(EVENT_AUDIO_FOCUS_CHANGE, createMap));
    }

    /* access modifiers changed from: package-private */
    public void audioBecomingNoisy() {
        receivePlayEvent(wrapEvent(EVENT_AUDIO_BECOMING_NOISY, Arguments.createMap()));
    }

    /* access modifiers changed from: package-private */
    public void callOnDrag(String str, long j) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("status", str);
        double d = (double) j;
        Double.isNaN(d);
        createMap.putDouble("currentTime", d / 1000.0d);
        receivePlayEvent(wrapEvent(EVENT_DRAG, createMap));
    }

    private WritableMap wrapEvent(String str, WritableMap writableMap) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("name", str);
        createMap.putMap("params", writableMap);
        return createMap;
    }

    private void receivePlayEvent(WritableMap writableMap) {
        receiveEvent(EVENT_PLAY_EVENT, writableMap);
    }

    private void receiveEvent(String str, WritableMap writableMap) {
        this.eventEmitter.receiveEvent(this.viewId, str, writableMap);
    }
}
