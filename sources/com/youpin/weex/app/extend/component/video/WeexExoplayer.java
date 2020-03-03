package com.youpin.weex.app.extend.component.video;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.youpin.youpin_common.widget.video.DynamicProxy;
import com.xiaomi.youpin.youpin_common.widget.video.ResizeMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeexExoplayer extends WXComponent<WeexExoplayerView> {
    private static final String PROP_AUTOSTART = "autoStart";
    private static final String PROP_BACKGROUND_COLOR = "bgColor";
    private static final String PROP_BUFFER_CONFIG = "bufferConfig";
    private static final String PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = "bufferForPlaybackAfterRebufferMs";
    private static final String PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_MS = "bufferForPlaybackMs";
    private static final String PROP_BUFFER_CONFIG_MAX_BUFFER_MS = "maxBufferMs";
    private static final String PROP_BUFFER_CONFIG_MIN_BUFFER_MS = "minBufferMs";
    private static final String PROP_CONTROLS = "controls";
    private static final String PROP_DISABLE_FOCUS = "disableFocus";
    private static final String PROP_DRAG = "drag";
    private static final String PROP_FULLSCREEN = "fullscreen";
    private static final String PROP_MUTED = "muted";
    private static final String PROP_PAUSED = "paused";
    private static final String PROP_PLAY_IN_BACKGROUND = "playInBackground";
    private static final String PROP_PROGRESS_UPDATE_INTERVAL = "progressUpdateInterval";
    private static final String PROP_RATE = "rate";
    private static final String PROP_REPEAT = "repeat";
    private static final String PROP_RESIZE_MODE = "resizeMode";
    private static final String PROP_SEEK = "seek";
    private static final String PROP_SELECTED_AUDIO_TRACK = "selectedAudioTrack";
    private static final String PROP_SELECTED_AUDIO_TRACK_TYPE = "type";
    private static final String PROP_SELECTED_AUDIO_TRACK_VALUE = "value";
    private static final String PROP_SELECTED_TEXT_TRACK = "selectedTextTrack";
    private static final String PROP_SELECTED_TEXT_TRACK_TYPE = "type";
    private static final String PROP_SELECTED_TEXT_TRACK_VALUE = "value";
    private static final String PROP_SRC = "src";
    private static final String PROP_SRC_HEADERS = "requestHeaders";
    private static final String PROP_SRC_TYPE = "type";
    private static final String PROP_SRC_URI = "uri";
    private static final String PROP_TEXT_TRACKS = "textTracks";
    private static final String PROP_USE_TEXTURE_VIEW = "useTextureView";
    private static final String PROP_VOLUME = "volume";
    private WeexExoplayerView mWeexExoplayerView;

    public WeexExoplayer(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
    }

    public static Map<String, String> toStringMap(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.size() <= 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry next : jSONObject.entrySet()) {
            hashMap.put(next.getKey(), String.valueOf(next.getValue()));
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public WeexExoplayerView initComponentHostView(@NonNull Context context) {
        this.mWeexExoplayerView = new WeexExoplayerView(context, new VideoEventEmitter(this));
        return this.mWeexExoplayerView;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            r1 = 0
            r2 = 1
            switch(r0) {
                case -1009134896: goto L_0x00d2;
                case -995321554: goto L_0x00c8;
                case -934531685: goto L_0x00be;
                case -846240709: goto L_0x00b3;
                case -810883302: goto L_0x00a8;
                case -566933834: goto L_0x009d;
                case 114148: goto L_0x0093;
                case 3493088: goto L_0x0088;
                case 3526264: goto L_0x007d;
                case 104264043: goto L_0x0073;
                case 110066619: goto L_0x0067;
                case 736720473: goto L_0x005b;
                case 784786448: goto L_0x0050;
                case 1018201479: goto L_0x0044;
                case 1560271811: goto L_0x0039;
                case 1650207731: goto L_0x002d;
                case 1779422114: goto L_0x0021;
                case 1878135829: goto L_0x0016;
                case 2049757303: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x00dd
        L_0x000b:
            java.lang.String r0 = "resizeMode"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 1
            goto L_0x00de
        L_0x0016:
            java.lang.String r0 = "textTracks"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 5
            goto L_0x00de
        L_0x0021:
            java.lang.String r0 = "bufferConfig"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 16
            goto L_0x00de
        L_0x002d:
            java.lang.String r0 = "autoStart"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 18
            goto L_0x00de
        L_0x0039:
            java.lang.String r0 = "selectedTextTrack"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 4
            goto L_0x00de
        L_0x0044:
            java.lang.String r0 = "playInBackground"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 12
            goto L_0x00de
        L_0x0050:
            java.lang.String r0 = "selectedAudioTrack"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 3
            goto L_0x00de
        L_0x005b:
            java.lang.String r0 = "useTextureView"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 15
            goto L_0x00de
        L_0x0067:
            java.lang.String r0 = "fullscreen"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 14
            goto L_0x00de
        L_0x0073:
            java.lang.String r0 = "muted"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 7
            goto L_0x00de
        L_0x007d:
            java.lang.String r0 = "seek"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 10
            goto L_0x00de
        L_0x0088:
            java.lang.String r0 = "rate"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 11
            goto L_0x00de
        L_0x0093:
            java.lang.String r0 = "src"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 0
            goto L_0x00de
        L_0x009d:
            java.lang.String r0 = "controls"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 17
            goto L_0x00de
        L_0x00a8:
            java.lang.String r0 = "volume"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 8
            goto L_0x00de
        L_0x00b3:
            java.lang.String r0 = "progressUpdateInterval"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 9
            goto L_0x00de
        L_0x00be:
            java.lang.String r0 = "repeat"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 2
            goto L_0x00de
        L_0x00c8:
            java.lang.String r0 = "paused"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 6
            goto L_0x00de
        L_0x00d2:
            java.lang.String r0 = "disableFocus"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x00dd
            r0 = 13
            goto L_0x00de
        L_0x00dd:
            r0 = -1
        L_0x00de:
            r3 = 0
            switch(r0) {
                case 0: goto L_0x01ea;
                case 1: goto L_0x01df;
                case 2: goto L_0x01cf;
                case 3: goto L_0x01c5;
                case 4: goto L_0x01bb;
                case 5: goto L_0x01b1;
                case 6: goto L_0x01a1;
                case 7: goto L_0x0191;
                case 8: goto L_0x0181;
                case 9: goto L_0x0171;
                case 10: goto L_0x0161;
                case 11: goto L_0x0151;
                case 12: goto L_0x0141;
                case 13: goto L_0x0131;
                case 14: goto L_0x0121;
                case 15: goto L_0x0111;
                case 16: goto L_0x0107;
                case 17: goto L_0x00f7;
                case 18: goto L_0x00e7;
                default: goto L_0x00e2;
            }
        L_0x00e2:
            boolean r5 = super.setProperty(r5, r6)
            return r5
        L_0x00e7:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r5)
            boolean r5 = r5.booleanValue()
            r4.setAutoStart(r5)
            return r2
        L_0x00f7:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r5)
            boolean r5 = r5.booleanValue()
            r4.setControls(r5)
            return r2
        L_0x0107:
            boolean r5 = r6 instanceof java.util.HashMap
            if (r5 == 0) goto L_0x0110
            java.util.Map r6 = (java.util.Map) r6
            r4.setBufferConfig(r6)
        L_0x0110:
            return r2
        L_0x0111:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r5)
            boolean r5 = r5.booleanValue()
            r4.setUseTextureView(r5)
            return r2
        L_0x0121:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r5)
            boolean r5 = r5.booleanValue()
            r4.setFullscreen(r5)
            return r2
        L_0x0131:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r5)
            boolean r5 = r5.booleanValue()
            r4.setDisableFocus(r5)
            return r2
        L_0x0141:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r5)
            boolean r5 = r5.booleanValue()
            r4.setPlayInBackground(r5)
            return r2
        L_0x0151:
            java.lang.Float r5 = java.lang.Float.valueOf(r3)
            java.lang.Float r5 = com.taobao.weex.utils.WXUtils.getFloat(r6, r5)
            float r5 = r5.floatValue()
            r4.setRate(r5)
            return r2
        L_0x0161:
            java.lang.Float r5 = java.lang.Float.valueOf(r3)
            java.lang.Float r5 = com.taobao.weex.utils.WXUtils.getFloat(r6, r5)
            float r5 = r5.floatValue()
            r4.setSeek(r5)
            return r2
        L_0x0171:
            java.lang.Float r5 = java.lang.Float.valueOf(r3)
            java.lang.Float r5 = com.taobao.weex.utils.WXUtils.getFloat(r6, r5)
            float r5 = r5.floatValue()
            r4.setProgressUpdateInterval(r5)
            return r2
        L_0x0181:
            java.lang.Float r5 = java.lang.Float.valueOf(r3)
            java.lang.Float r5 = com.taobao.weex.utils.WXUtils.getFloat(r6, r5)
            float r5 = r5.floatValue()
            r4.setVolume(r5)
            return r2
        L_0x0191:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r5)
            boolean r5 = r5.booleanValue()
            r4.setMuted(r5)
            return r2
        L_0x01a1:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r5)
            boolean r5 = r5.booleanValue()
            r4.setPaused(r5)
            return r2
        L_0x01b1:
            boolean r5 = r6 instanceof java.util.List
            if (r5 == 0) goto L_0x01ba
            java.util.List r6 = (java.util.List) r6
            r4.setPropTextTracks(r6)
        L_0x01ba:
            return r2
        L_0x01bb:
            boolean r5 = r6 instanceof java.util.HashMap
            if (r5 == 0) goto L_0x01c4
            java.util.Map r6 = (java.util.Map) r6
            r4.setSelectedTextTrack(r6)
        L_0x01c4:
            return r2
        L_0x01c5:
            boolean r5 = r6 instanceof java.util.HashMap
            if (r5 == 0) goto L_0x01ce
            java.util.Map r6 = (java.util.Map) r6
            r4.setSelectedAudioTrack(r6)
        L_0x01ce:
            return r2
        L_0x01cf:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r5)
            boolean r5 = r5.booleanValue()
            r4.setRepeat(r5)
            return r2
        L_0x01df:
            r5 = 0
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r5)
            if (r5 == 0) goto L_0x01e9
            r4.setResizeMode(r5)
        L_0x01e9:
            return r2
        L_0x01ea:
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x01f6 }
            com.alibaba.fastjson.JSONObject r5 = com.alibaba.fastjson.JSONObject.parseObject(r5)     // Catch:{ Exception -> 0x01f6 }
            r4.setSrc(r5)     // Catch:{ Exception -> 0x01f6 }
            goto L_0x01fa
        L_0x01f6:
            r5 = move-exception
            r5.printStackTrace()
        L_0x01fa:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youpin.weex.app.extend.component.video.WeexExoplayer.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "src")
    public void setSrc(JSONObject jSONObject) {
        Uri buildRawResourceUri;
        if (jSONObject != null) {
            Context context = getContext();
            String string = jSONObject.getString("uri");
            String string2 = jSONObject.getString("type");
            Map<String, String> stringMap = toStringMap(jSONObject.getJSONObject(PROP_SRC_HEADERS));
            if (!TextUtils.isEmpty(string)) {
                if (startsWithValidScheme(string)) {
                    Uri parse = Uri.parse(string);
                    if (parse != null) {
                        this.mWeexExoplayerView.setSrc(parse, string2, stringMap);
                        return;
                    }
                    return;
                }
                int identifier = context.getResources().getIdentifier(string, "drawable", context.getPackageName());
                if (identifier == 0) {
                    identifier = context.getResources().getIdentifier(string, ShareConstants.V, context.getPackageName());
                }
                if (identifier > 0 && (buildRawResourceUri = RawResourceDataSource.buildRawResourceUri(identifier)) != null) {
                    this.mWeexExoplayerView.setRawSrc(buildRawResourceUri, string2);
                }
            }
        }
    }

    @WXComponentProp(name = "resizeMode")
    public void setResizeMode(String str) {
        this.mWeexExoplayerView.setResizeModeModifier(convertToIntDef(str));
    }

    @WXComponentProp(name = "repeat")
    public void setRepeat(boolean z) {
        this.mWeexExoplayerView.setRepeatModifier(z);
    }

    @WXComponentProp(name = "selectedAudioTrack")
    public void setSelectedAudioTrack(Map<String, Object> map) {
        String str;
        Object obj = null;
        if (map != null) {
            str = map.containsKey("type") ? (String) map.get("type") : null;
            if (map.containsKey("value")) {
                obj = map.get("value");
            }
        } else {
            str = null;
        }
        final String valueOf = String.valueOf(obj);
        this.mWeexExoplayerView.setSelectedAudioTrack(str, new DynamicProxy() {
            public int asInt() {
                try {
                    return Integer.parseInt(valueOf);
                } catch (Exception unused) {
                    return 0;
                }
            }

            public String asString() {
                return valueOf;
            }
        });
    }

    @WXComponentProp(name = "selectedTextTrack")
    public void setSelectedTextTrack(Map<String, Object> map) {
        String str;
        Object obj = null;
        if (map != null) {
            str = map.containsKey("type") ? (String) map.get("type") : null;
            if (map.containsKey("value")) {
                obj = map.get("value");
            }
        } else {
            str = null;
        }
        final String valueOf = String.valueOf(obj);
        this.mWeexExoplayerView.setSelectedTextTrack(str, new DynamicProxy() {
            public int asInt() {
                try {
                    return Integer.parseInt(valueOf);
                } catch (Exception unused) {
                    return 0;
                }
            }

            public String asString() {
                return valueOf;
            }
        });
    }

    @WXComponentProp(name = "textTracks")
    public void setPropTextTracks(List<Object> list) {
        this.mWeexExoplayerView.setTextTracks(list);
    }

    @WXComponentProp(name = "paused")
    public void setPaused(boolean z) {
        this.mWeexExoplayerView.setPausedModifier(z);
    }

    @WXComponentProp(name = "muted")
    public void setMuted(boolean z) {
        this.mWeexExoplayerView.setMutedModifier(z);
    }

    @WXComponentProp(name = "volume")
    public void setVolume(float f) {
        this.mWeexExoplayerView.setVolumeModifier(f);
    }

    @WXComponentProp(name = "progressUpdateInterval")
    public void setProgressUpdateInterval(float f) {
        this.mWeexExoplayerView.setProgressUpdateInterval(f);
    }

    @JSMethod
    @WXComponentProp(name = "seek")
    public void setSeek(float f) {
        this.mWeexExoplayerView.seekTo((long) Math.round(f * 1000.0f));
    }

    @WXComponentProp(name = "rate")
    public void setRate(float f) {
        this.mWeexExoplayerView.setRateModifier(f);
    }

    @WXComponentProp(name = "playInBackground")
    public void setPlayInBackground(boolean z) {
        this.mWeexExoplayerView.setPlayInBackground(z);
    }

    @WXComponentProp(name = "disableFocus")
    public void setDisableFocus(boolean z) {
        this.mWeexExoplayerView.setDisableFocus(z);
    }

    @JSMethod
    @WXComponentProp(name = "fullscreen")
    public void setFullscreen(boolean z) {
        this.mWeexExoplayerView.setFullscreen(z);
    }

    @WXComponentProp(name = "useTextureView")
    public void setUseTextureView(boolean z) {
        this.mWeexExoplayerView.setUseTextureView(z);
    }

    @WXComponentProp(name = "controls")
    public void setControls(boolean z) {
        this.mWeexExoplayerView.setControls(z);
    }

    @WXComponentProp(name = "autoStart")
    public void setAutoStart(boolean z) {
        this.mWeexExoplayerView.setAutoStart(z);
    }

    @WXComponentProp(name = "drag")
    public void setDrag(boolean z) {
        this.mWeexExoplayerView.setDrag(z);
    }

    @WXComponentProp(name = "bgColor")
    public void setBackgroundColor(String str) {
        this.mWeexExoplayerView.setBackgroundColor(str);
    }

    @WXComponentProp(name = "bufferConfig")
    public void setBufferConfig(Map<String, Object> map) {
        if (map != null) {
            this.mWeexExoplayerView.setBufferConfig(map.containsKey(PROP_BUFFER_CONFIG_MIN_BUFFER_MS) ? ((Integer) map.get(PROP_BUFFER_CONFIG_MIN_BUFFER_MS)).intValue() : 15000, map.containsKey(PROP_BUFFER_CONFIG_MAX_BUFFER_MS) ? ((Integer) map.get(PROP_BUFFER_CONFIG_MAX_BUFFER_MS)).intValue() : 50000, map.containsKey(PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_MS) ? ((Integer) map.get(PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_MS)).intValue() : 2500, map.containsKey(PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS) ? ((Integer) map.get(PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS)).intValue() : 5000);
        }
    }

    private boolean startsWithValidScheme(String str) {
        return str.startsWith(ConnectionHelper.HTTP_PREFIX) || str.startsWith("https://") || str.startsWith("content://") || str.startsWith("file://") || str.startsWith("asset://");
    }

    private int convertToIntDef(String str) {
        if (!TextUtils.isEmpty(str)) {
            return ResizeMode.a(Integer.parseInt(str));
        }
        return 0;
    }

    public void bindData(WXComponent wXComponent) {
        super.bindData(wXComponent);
    }

    public void onActivityResume() {
        if (this.mWeexExoplayerView != null) {
            this.mWeexExoplayerView.onHostResume();
        }
    }

    public void onActivityPause() {
        if (this.mWeexExoplayerView != null) {
            this.mWeexExoplayerView.onHostPause();
        }
    }

    public void onActivityDestroy() {
        if (this.mWeexExoplayerView != null) {
            this.mWeexExoplayerView.onHostDestroy();
        }
    }

    @JSMethod
    public void destroy() {
        super.destroy();
        if (this.mWeexExoplayerView != null) {
            this.mWeexExoplayerView.cleanUpResources();
        }
    }

    public void videoFireEvent(String str, Map<String, Object> map, Map<String, Object> map2) {
        fireEvent(str, map, map2);
    }
}
