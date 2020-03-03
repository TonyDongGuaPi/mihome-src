package com.xiaomi.miot.store.component.video;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSource;
import com.xiaomi.youpin.youpin_common.widget.video.BaseExoplayerView;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"ViewConstructor"})
class ReactExoplayerView extends BaseExoplayerView implements LifecycleEventListener {
    private final VideoEventEmitter eventEmitter;
    private ReadableArray textTracks;
    private final ThemedReactContext themedReactContext;

    public ReactExoplayerView(ThemedReactContext themedReactContext2) {
        super(themedReactContext2);
        this.themedReactContext = themedReactContext2;
        this.eventEmitter = new VideoEventEmitter(themedReactContext2);
        this.themedReactContext.addLifecycleEventListener(this);
        constructView();
    }

    public void setId(int i) {
        super.setId(i);
        this.eventEmitter.setViewId(i);
    }

    public void callProgressChanged(double d, double d2, double d3) {
        this.eventEmitter.progressChanged(d, d2, d3);
    }

    public void callLoadStart() {
        this.eventEmitter.loadStart();
    }

    public void callAudioFocusChanged(boolean z) {
        this.eventEmitter.audioFocusChanged(false);
    }

    public void callAudioBecomingNoisy() {
        this.eventEmitter.audioBecomingNoisy();
    }

    /* access modifiers changed from: protected */
    public void callIdle() {
        this.eventEmitter.idle();
    }

    /* access modifiers changed from: protected */
    public void callReady() {
        this.eventEmitter.ready();
    }

    /* access modifiers changed from: protected */
    public void callEnd() {
        this.eventEmitter.end();
    }

    /* access modifiers changed from: protected */
    public void callMuted(boolean z) {
        this.eventEmitter.muted(z);
    }

    /* access modifiers changed from: protected */
    public void callLoad(double d, double d2, int i, int i2, List list, List list2) {
        this.eventEmitter.load(d, d2, i, i2, Arguments.makeNativeArray(list), Arguments.makeNativeArray(list2));
    }

    /* access modifiers changed from: protected */
    public void callBuffering(boolean z) {
        this.eventEmitter.buffering(z);
    }

    /* access modifiers changed from: protected */
    public void callSeek(long j, long j2) {
        this.eventEmitter.seek(j, j2);
    }

    /* access modifiers changed from: protected */
    public void callFullscreenWillPresent() {
        this.eventEmitter.fullscreenWillPresent();
    }

    /* access modifiers changed from: protected */
    public void callFullscreenDidPresent() {
        this.eventEmitter.fullscreenDidPresent();
    }

    /* access modifiers changed from: protected */
    public void callFullscreenWillDismiss() {
        this.eventEmitter.fullscreenWillDismiss();
    }

    /* access modifiers changed from: protected */
    public void callFullscreenDidDismiss() {
        this.eventEmitter.fullscreenDidDismiss();
    }

    /* access modifiers changed from: protected */
    public void callError(String str, Exception exc) {
        this.eventEmitter.error(str, exc);
    }

    /* access modifiers changed from: protected */
    public void callPlaybackRateChange(float f) {
        this.eventEmitter.playbackRateChange(f);
    }

    /* access modifiers changed from: protected */
    public void callOnPause(boolean z, long j) {
        this.eventEmitter.callOnPause(z, j);
    }

    /* access modifiers changed from: protected */
    public void callOnDrag(String str, long j) {
        this.eventEmitter.callOnDrag(str, j);
    }

    /* access modifiers changed from: protected */
    public void callTimedMetadata(Metadata metadata) {
        this.eventEmitter.timedMetadata(metadata);
    }

    /* access modifiers changed from: protected */
    public Activity getCurrentActivity() {
        return this.themedReactContext.getCurrentActivity();
    }

    public ArrayList<MediaSource> buildTextSources() {
        String str;
        ArrayList<MediaSource> arrayList = new ArrayList<>();
        if (this.textTracks == null) {
            return arrayList;
        }
        for (int i = 0; i < this.textTracks.size(); i++) {
            ReadableMap map = this.textTracks.getMap(i);
            String string = map.getString("language");
            if (map.hasKey("title")) {
                str = map.getString("title");
            } else {
                str = string + " " + i;
            }
            MediaSource buildTextSource = buildTextSource(str, Uri.parse(map.getString("uri")), map.getString("type"), string);
            if (buildTextSource != null) {
                arrayList.add(buildTextSource);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void callRemoveLifecycleEventListener() {
        this.themedReactContext.removeLifecycleEventListener(this);
    }

    public void onHostResume() {
        super.onHostResume();
    }

    public void onHostPause() {
        super.onHostPause();
    }

    public void onHostDestroy() {
        super.onHostDestroy();
    }

    public void setTextTracks(ReadableArray readableArray) {
        this.textTracks = readableArray;
        reloadSource();
    }
}
