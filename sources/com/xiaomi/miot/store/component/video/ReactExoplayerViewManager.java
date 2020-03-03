package com.xiaomi.miot.store.component.video;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.youpin.youpin_common.widget.video.DynamicProxy;
import com.xiaomi.youpin.youpin_common.widget.video.ResizeMode;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class ReactExoplayerViewManager extends ViewGroupManager<ReactExoplayerView> {
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
    private static final String REACT_CLASS = "RCTYPVideo";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ReactExoplayerView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactExoplayerView(themedReactContext);
    }

    public void onDropViewInstance(ReactExoplayerView reactExoplayerView) {
        reactExoplayerView.cleanUpResources();
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder builder = MapBuilder.builder();
        for (String str : VideoEventEmitter.Events) {
            builder.put(str, MapBuilder.of("registrationName", str));
        }
        return builder.build();
    }

    @Nullable
    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.of("ScaleNone", Integer.toString(0), "ScaleAspectFit", Integer.toString(0), "ScaleToFill", Integer.toString(3), "ScaleAspectFill", Integer.toString(4));
    }

    @ReactProp(name = "src")
    public void setSrc(ReactExoplayerView reactExoplayerView, @Nullable ReadableMap readableMap) {
        Uri buildRawResourceUri;
        Context applicationContext = reactExoplayerView.getContext().getApplicationContext();
        Map<String, String> map = null;
        String string = readableMap.hasKey("uri") ? readableMap.getString("uri") : null;
        String string2 = readableMap.hasKey("type") ? readableMap.getString("type") : null;
        if (readableMap.hasKey(PROP_SRC_HEADERS)) {
            map = toStringMap(readableMap.getMap(PROP_SRC_HEADERS));
        }
        if (!TextUtils.isEmpty(string)) {
            if (startsWithValidScheme(string)) {
                Uri parse = Uri.parse(string);
                if (parse != null) {
                    reactExoplayerView.setSrc(parse, string2, map);
                    return;
                }
                return;
            }
            int identifier = applicationContext.getResources().getIdentifier(string, "drawable", applicationContext.getPackageName());
            if (identifier == 0) {
                identifier = applicationContext.getResources().getIdentifier(string, ShareConstants.V, applicationContext.getPackageName());
            }
            if (identifier > 0 && (buildRawResourceUri = RawResourceDataSource.buildRawResourceUri(identifier)) != null) {
                reactExoplayerView.setRawSrc(buildRawResourceUri, string2);
            }
        }
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(ReactExoplayerView reactExoplayerView, String str) {
        reactExoplayerView.setResizeModeModifier(convertToIntDef(str));
    }

    @ReactProp(defaultBoolean = false, name = "repeat")
    public void setRepeat(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setRepeatModifier(z);
    }

    @ReactProp(name = "selectedAudioTrack")
    public void setSelectedAudioTrack(ReactExoplayerView reactExoplayerView, @Nullable ReadableMap readableMap) {
        String str;
        final Dynamic dynamic = null;
        if (readableMap != null) {
            str = readableMap.hasKey("type") ? readableMap.getString("type") : null;
            if (readableMap.hasKey("value")) {
                dynamic = readableMap.getDynamic("value");
            }
        } else {
            str = null;
        }
        reactExoplayerView.setSelectedAudioTrack(str, new DynamicProxy() {
            public int asInt() {
                return dynamic.asInt();
            }

            public String asString() {
                return dynamic.asString();
            }
        });
    }

    @ReactProp(name = "selectedTextTrack")
    public void setSelectedTextTrack(ReactExoplayerView reactExoplayerView, @Nullable ReadableMap readableMap) {
        String str;
        final Dynamic dynamic = null;
        if (readableMap != null) {
            str = readableMap.hasKey("type") ? readableMap.getString("type") : null;
            if (readableMap.hasKey("value")) {
                dynamic = readableMap.getDynamic("value");
            }
        } else {
            str = null;
        }
        reactExoplayerView.setSelectedTextTrack(str, new DynamicProxy() {
            public int asInt() {
                return dynamic.asInt();
            }

            public String asString() {
                return dynamic.asString();
            }
        });
    }

    @ReactProp(name = "textTracks")
    public void setPropTextTracks(ReactExoplayerView reactExoplayerView, @Nullable ReadableArray readableArray) {
        reactExoplayerView.setTextTracks(readableArray);
    }

    @ReactProp(defaultBoolean = false, name = "paused")
    public void setPaused(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setPausedModifier(z);
    }

    @ReactProp(defaultBoolean = false, name = "muted")
    public void setMuted(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setMutedModifier(z);
    }

    @ReactProp(defaultFloat = 1.0f, name = "volume")
    public void setVolume(ReactExoplayerView reactExoplayerView, float f) {
        reactExoplayerView.setVolumeModifier(f);
    }

    @ReactProp(defaultFloat = 250.0f, name = "progressUpdateInterval")
    public void setProgressUpdateInterval(ReactExoplayerView reactExoplayerView, float f) {
        reactExoplayerView.setProgressUpdateInterval(f);
    }

    @ReactProp(name = "seek")
    public void setSeek(ReactExoplayerView reactExoplayerView, float f) {
        reactExoplayerView.seekTo((long) Math.round(f * 1000.0f));
    }

    @ReactProp(name = "rate")
    public void setRate(ReactExoplayerView reactExoplayerView, float f) {
        reactExoplayerView.setRateModifier(f);
    }

    @ReactProp(defaultBoolean = false, name = "playInBackground")
    public void setPlayInBackground(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setPlayInBackground(z);
    }

    @ReactProp(defaultBoolean = false, name = "disableFocus")
    public void setDisableFocus(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setDisableFocus(z);
    }

    @ReactProp(defaultBoolean = false, name = "fullscreen")
    public void setFullscreen(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setFullscreen(z);
    }

    @ReactProp(defaultBoolean = false, name = "useTextureView")
    public void setUseTextureView(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setUseTextureView(z);
    }

    @ReactProp(name = "bufferConfig")
    public void setBufferConfig(ReactExoplayerView reactExoplayerView, @Nullable ReadableMap readableMap) {
        if (readableMap != null) {
            reactExoplayerView.setBufferConfig(readableMap.hasKey(PROP_BUFFER_CONFIG_MIN_BUFFER_MS) ? readableMap.getInt(PROP_BUFFER_CONFIG_MIN_BUFFER_MS) : 15000, readableMap.hasKey(PROP_BUFFER_CONFIG_MAX_BUFFER_MS) ? readableMap.getInt(PROP_BUFFER_CONFIG_MAX_BUFFER_MS) : 50000, readableMap.hasKey(PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_MS) ? readableMap.getInt(PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_MS) : 2500, readableMap.hasKey(PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS) ? readableMap.getInt(PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS) : 5000);
        }
    }

    @ReactProp(defaultBoolean = false, name = "controls")
    public void setControls(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setControls(z);
    }

    @ReactProp(defaultBoolean = false, name = "autoStart")
    public void setAutoStart(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setAutoStart(z);
    }

    @ReactProp(defaultBoolean = true, name = "drag")
    public void setDrag(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setDrag(z);
    }

    @ReactProp(name = "bgColor")
    public void setBackgroundColor(ReactExoplayerView reactExoplayerView, String str) {
        reactExoplayerView.setBackgroundColor(str);
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

    public static Map<String, String> toStringMap(@Nullable ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        if (!keySetIterator.hasNextKey()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            hashMap.put(nextKey, readableMap.getString(nextKey));
        }
        return hashMap;
    }
}
