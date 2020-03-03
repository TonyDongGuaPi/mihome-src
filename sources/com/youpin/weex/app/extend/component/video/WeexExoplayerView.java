package com.youpin.weex.app.extend.component.video;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSource;
import com.xiaomi.youpin.youpin_common.widget.video.BaseExoplayerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressLint({"ViewConstructor"})
class WeexExoplayerView extends BaseExoplayerView {
    private final VideoEventEmitter eventEmitter;
    private final Context mWeexContext;
    private List<Object> textTracks;

    public WeexExoplayerView(Context context, VideoEventEmitter videoEventEmitter) {
        super(context);
        this.mWeexContext = context;
        this.eventEmitter = videoEventEmitter;
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
    public void callLoad(double d, double d2, int i, int i2, List list, List list2) {
        this.eventEmitter.load(d, d2, i, i2, list, list2);
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
    public void callOnPause(boolean z, long j) {
        this.eventEmitter.callOnPause(z, j);
    }

    /* access modifiers changed from: protected */
    public void callMuted(boolean z) {
        this.eventEmitter.muted(z);
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
    public void callTimedMetadata(Metadata metadata) {
        this.eventEmitter.timedMetadata(metadata);
    }

    /* access modifiers changed from: protected */
    public Activity getCurrentActivity() {
        if (this.mWeexContext instanceof Activity) {
            return (Activity) this.mWeexContext;
        }
        return null;
    }

    public ArrayList<MediaSource> buildTextSources() {
        String str;
        ArrayList<MediaSource> arrayList = new ArrayList<>();
        if (this.textTracks == null) {
            return arrayList;
        }
        for (int i = 0; i < this.textTracks.size(); i++) {
            Map map = (Map) this.textTracks.get(i);
            String str2 = (String) map.get("language");
            if (map.containsKey("title")) {
                str = (String) map.get("title");
            } else {
                str = str2 + " " + i;
            }
            MediaSource buildTextSource = buildTextSource(str, Uri.parse((String) map.get("uri")), (String) map.get("type"), str2);
            if (buildTextSource != null) {
                arrayList.add(buildTextSource);
            }
        }
        return arrayList;
    }

    public void setTextTracks(List<Object> list) {
        this.textTracks = list;
        reloadSource();
    }
}
