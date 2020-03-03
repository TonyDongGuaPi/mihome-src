package com.youpin.weex.app.extend.component.video;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.taobao.weex.ui.view.gesture.WXGestureType;
import com.taobao.weex.utils.WXDataStructureUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class VideoEventEmitter {
    private static final String EVENT_AUDIO_BECOMING_NOISY = "videoaudiobecomingnoisy";
    private static final String EVENT_AUDIO_FOCUS_CHANGE = "audiofocuschanged";
    private static final String EVENT_BUFFER = "videobuffer";
    private static final String EVENT_DRAG = "onDrag";
    private static final String EVENT_END = "onEnd";
    private static final String EVENT_ERROR = "onError";
    private static final String EVENT_FULLSCREEN_DID = "onFullScreenDid";
    private static final String EVENT_FULLSCREEN_WILL = "onFullScreenWill";
    private static final String EVENT_IDLE = "videoidle";
    private static final String EVENT_LOAD = "onLoad";
    private static final String EVENT_LOAD_START = "onLoadStart";
    private static final String EVENT_MUTED = "onMuted";
    private static final String EVENT_NAME = "name";
    private static final String EVENT_PARAMS = "params";
    private static final String EVENT_PAUSE = "onPause";
    private static final String EVENT_PLAYBACK_RATE_CHANGE = "playbackratechange";
    private static final String EVENT_PLAY_EVENT = "playevent";
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
    private static final String EVENT_PROP_PAUSED = "paused";
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
    private static final String EVENT_READY = "readyfordisplay";
    private static final String EVENT_RESUME = "playbackresume";
    private static final String EVENT_SEEK = "onSeek";
    private static final String EVENT_STALLED = "playbackstalled";
    private static final String EVENT_START = "onStart";
    private static final String EVENT_TIMED_METADATA = "timedmetadata";
    private int viewId = -1;
    private WeexExoplayer weexExoplayer;

    VideoEventEmitter(WeexExoplayer weexExoplayer2) {
        this.weexExoplayer = weexExoplayer2;
    }

    /* access modifiers changed from: package-private */
    public void setViewId(int i) {
        this.viewId = i;
    }

    /* access modifiers changed from: package-private */
    public void loadStart() {
        if (this.weexExoplayer.getEvents().contains(EVENT_PLAY_EVENT)) {
            firePlayEvent(wrapEvent(EVENT_LOAD_START, new HashMap()));
        }
    }

    /* access modifiers changed from: package-private */
    public void load(double d, double d2, int i, int i2, List list, List list2) {
        if (this.weexExoplayer.getEvents().contains(EVENT_PLAY_EVENT)) {
            HashMap hashMap = new HashMap();
            hashMap.put("duration", Double.valueOf(d / 1000.0d));
            hashMap.put("currentTime", Double.valueOf(d2 / 1000.0d));
            HashMap hashMap2 = new HashMap();
            hashMap2.put("width", Integer.valueOf(i));
            hashMap2.put("height", Integer.valueOf(i2));
            if (i > i2) {
                hashMap2.put("orientation", "landscape");
            } else {
                hashMap2.put("orientation", "portrait");
            }
            hashMap.put("naturalSize", hashMap2);
            hashMap.put(EVENT_PROP_AUDIO_TRACKS, list);
            hashMap.put(EVENT_PROP_TEXT_TRACKS, list2);
            hashMap.put("canPlayFastForward", true);
            hashMap.put("canPlaySlowForward", true);
            hashMap.put("canPlaySlowReverse", true);
            hashMap.put("canPlayReverse", true);
            hashMap.put("canPlayFastForward", true);
            hashMap.put("canStepBackward", true);
            hashMap.put("canStepForward", true);
            firePlayEvent(wrapEvent(EVENT_LOAD, hashMap));
        }
    }

    /* access modifiers changed from: package-private */
    public void progressChanged(double d, double d2, double d3) {
        HashMap hashMap = new HashMap();
        hashMap.put("currentTime", Double.valueOf(d / 1000.0d));
        hashMap.put("playableDuration", Double.valueOf(d2 / 1000.0d));
        hashMap.put("seekableDuration", Double.valueOf(d3 / 1000.0d));
        firePlayEvent(wrapEvent(EVENT_PROGRESS, hashMap));
    }

    /* access modifiers changed from: package-private */
    public void seek(long j, long j2) {
        HashMap newHashMapWithExpectedSize = WXDataStructureUtil.newHashMapWithExpectedSize(2);
        double d = (double) j;
        Double.isNaN(d);
        newHashMapWithExpectedSize.put("currentTime", Double.valueOf(d / 1000.0d));
        double d2 = (double) j2;
        Double.isNaN(d2);
        newHashMapWithExpectedSize.put("seekTime", Double.valueOf(d2 / 1000.0d));
        firePlayEvent(wrapEvent(EVENT_SEEK, newHashMapWithExpectedSize));
    }

    /* access modifiers changed from: package-private */
    public void callOnPause(boolean z, long j) {
        HashMap hashMap = new HashMap();
        double d = (double) j;
        Double.isNaN(d);
        hashMap.put("currentTime", Double.valueOf(d / 1000.0d));
        firePlayEvent(wrapEvent(z ? "onPause" : "onStart", hashMap));
    }

    /* access modifiers changed from: package-private */
    public void muted(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", Boolean.valueOf(z));
        firePlayEvent(wrapEvent(EVENT_MUTED, hashMap));
    }

    /* access modifiers changed from: package-private */
    public void ready() {
        firePlayEvent(wrapEvent(EVENT_READY, new HashMap()));
    }

    /* access modifiers changed from: package-private */
    public void buffering(boolean z) {
        HashMap newHashMapWithExpectedSize = WXDataStructureUtil.newHashMapWithExpectedSize(1);
        newHashMapWithExpectedSize.put(EVENT_PROP_IS_BUFFERING, Boolean.valueOf(z));
        firePlayEvent(wrapEvent(EVENT_BUFFER, newHashMapWithExpectedSize));
    }

    /* access modifiers changed from: package-private */
    public void idle() {
        firePlayEvent(wrapEvent(EVENT_IDLE, new HashMap()));
    }

    /* access modifiers changed from: package-private */
    public void end() {
        firePlayEvent(wrapEvent(EVENT_END, new HashMap()));
    }

    /* access modifiers changed from: package-private */
    public void fullscreenWillPresent() {
        HashMap hashMap = new HashMap();
        hashMap.put("status", true);
        firePlayEvent(wrapEvent(EVENT_FULLSCREEN_WILL, hashMap));
    }

    /* access modifiers changed from: package-private */
    public void fullscreenDidPresent() {
        HashMap hashMap = new HashMap();
        hashMap.put("status", true);
        firePlayEvent(wrapEvent(EVENT_FULLSCREEN_DID, hashMap));
    }

    /* access modifiers changed from: package-private */
    public void fullscreenWillDismiss() {
        HashMap hashMap = new HashMap();
        hashMap.put("status", false);
        firePlayEvent(wrapEvent(EVENT_FULLSCREEN_WILL, hashMap));
    }

    /* access modifiers changed from: package-private */
    public void fullscreenDidDismiss() {
        HashMap hashMap = new HashMap();
        hashMap.put("status", false);
        firePlayEvent(wrapEvent(EVENT_FULLSCREEN_DID, hashMap));
    }

    /* access modifiers changed from: package-private */
    public void error(String str, Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put(EVENT_PROP_ERROR_STRING, str);
        hashMap.put(EVENT_PROP_ERROR_EXCEPTION, exc.getMessage());
        HashMap hashMap2 = new HashMap();
        hashMap2.put("error", hashMap);
        firePlayEvent(wrapEvent(EVENT_ERROR, hashMap2));
    }

    /* access modifiers changed from: package-private */
    public void playbackRateChange(float f) {
        HashMap hashMap = new HashMap();
        hashMap.put(EVENT_PROP_PLAYBACK_RATE, Double.valueOf((double) f));
        firePlayEvent(wrapEvent(EVENT_PLAYBACK_RATE_CHANGE, hashMap));
    }

    /* access modifiers changed from: package-private */
    public void timedMetadata(Metadata metadata) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < metadata.length(); i++) {
            Id3Frame id3Frame = (Id3Frame) metadata.get(i);
            String str = "";
            if (id3Frame instanceof TextInformationFrame) {
                str = ((TextInformationFrame) id3Frame).value;
            }
            String str2 = id3Frame.id;
            HashMap hashMap = new HashMap();
            hashMap.put(WXGestureType.GestureInfo.POINTER_ID, str2);
            hashMap.put("value", str);
            arrayList.add(hashMap);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("metadata", arrayList);
        firePlayEvent(wrapEvent(EVENT_TIMED_METADATA, hashMap2));
    }

    /* access modifiers changed from: package-private */
    public void audioFocusChanged(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(EVENT_PROP_HAS_AUDIO_FOCUS, Boolean.valueOf(z));
        firePlayEvent(wrapEvent(EVENT_AUDIO_FOCUS_CHANGE, hashMap));
    }

    /* access modifiers changed from: package-private */
    public void audioBecomingNoisy() {
        firePlayEvent(wrapEvent(EVENT_AUDIO_BECOMING_NOISY, new HashMap()));
    }

    /* access modifiers changed from: package-private */
    public void callOnDrag(String str, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", str);
        double d = (double) j;
        Double.isNaN(d);
        hashMap.put("currentTime", Double.valueOf(d / 1000.0d));
        firePlayEvent(wrapEvent(EVENT_DRAG, hashMap));
    }

    private Map<String, Object> wrapEvent(String str, Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        hashMap.put("params", map);
        return hashMap;
    }

    private void firePlayEvent(Map<String, Object> map) {
        fireEvent(EVENT_PLAY_EVENT, map);
    }

    private void fireEvent(String str, Map<String, Object> map) {
        this.weexExoplayer.videoFireEvent(str, map, (Map<String, Object>) null);
    }

    private void fireEvent(String str, Map<String, Object> map, Map<String, Object> map2) {
        this.weexExoplayer.videoFireEvent(str, map, map2);
    }
}
