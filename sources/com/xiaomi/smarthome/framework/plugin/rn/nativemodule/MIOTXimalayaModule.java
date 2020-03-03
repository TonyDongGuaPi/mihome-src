package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaomi.smarthome.frame.FrameManager;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MIOTXimalayaModule extends ReactContextBaseJavaModule {
    private static String lastAppKey;
    private List<Long> programs = null;

    public String getName() {
        return "MIOTXimalaya";
    }

    public MIOTXimalayaModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private static boolean is_ready() {
        return lastAppKey != null;
    }

    private void init(String str, String str2, String str3) {
        if (!str.equals(lastAppKey)) {
            CommonRequest a2 = CommonRequest.a();
            a2.c(str3);
            a2.b(str);
            a2.a((Context) getCurrentActivity(), str2);
            lastAppKey = str;
        }
    }

    public static void releasePlayer() {
        if (lastAppKey != null) {
            lastAppKey = null;
            player().p();
            player().P();
        }
    }

    private static class Resp extends XimalayaResponse {

        /* renamed from: a  reason: collision with root package name */
        static CommonRequest.IRequestCallBack<Resp> f17435a = new CommonRequest.IRequestCallBack<Resp>() {
            /* renamed from: a */
            public Resp b(String str) throws Exception {
                Resp resp = new Resp();
                String unused = resp.b = str;
                return resp;
            }
        };
        /* access modifiers changed from: private */
        public String b;

        private Resp() {
        }

        static IDataCallBack<Resp> a(final Callback callback) {
            return new IDataCallBack<Resp>() {
                public void a(@Nullable Resp resp) {
                    if (callback != null) {
                        callback.invoke(resp.b, null);
                    }
                }

                public void a(int i, String str) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("code", Integer.valueOf(i));
                    hashMap.put("msg", str);
                    Gson gson = new Gson();
                    callback.invoke(null, gson.toJson((Object) hashMap));
                }
            };
        }
    }

    @ReactMethod
    public void requestXMData(String str, String str2, String str3, Callback callback) {
        Map map = (Map) new Gson().fromJson(str3, Map.class);
        if ("post".equalsIgnoreCase(str)) {
            CommonRequest.b(str2, map, Resp.a(callback), Resp.f17435a);
        } else {
            CommonRequest.a(str2, map, Resp.a(callback), Resp.f17435a);
        }
    }

    private static final XmPlayerManager player() {
        return XmPlayerManager.a(FrameManager.f());
    }

    @ReactMethod
    public void registry(String str, String str2, String str3) {
        init(str, str2, str3);
    }

    @ReactMethod
    public void setPlayMode(int i) {
        if (is_ready()) {
            player().a(XmPlayListControl.PlayMode.getIndex(i));
        }
    }

    @ReactMethod
    public void setTrackPlayMode(int i) {
        setPlayMode(i);
    }

    @ReactMethod
    public void setVolume(float f) {
        if (is_ready()) {
            player().a(f, f);
        }
    }

    @ReactMethod
    public void playWithTrack(String str, int i) {
        if (is_ready()) {
            player().b((List<Track>) (List) new Gson().fromJson(str, new TypeToken<List<Track>>() {
            }.getType()), i);
        }
    }

    @ReactMethod
    public void pauseTrackPlay() {
        if (is_ready()) {
            player().o();
        }
    }

    @ReactMethod
    public void resumeTrackPlay() {
        if (is_ready()) {
            player().m();
        }
    }

    @ReactMethod
    public void stopTrackPlay() {
        if (is_ready()) {
            player().p();
        }
    }

    @ReactMethod
    public void replacePlayList(String str) {
        if (is_ready()) {
            player().G();
            player().c((List<Track>) (List) new Gson().fromJson(str, new TypeToken<List<Track>>() {
            }.getType()), 0);
        }
    }

    @ReactMethod
    public void playNextTrackWithCallback(Callback callback) {
        if (!is_ready()) {
            callback.invoke(false);
            return;
        }
        player().r();
        if (callback != null) {
            callback.invoke(true);
        }
    }

    @ReactMethod
    public void playPrevTrackWithCallback(Callback callback) {
        if (!is_ready()) {
            callback.invoke(false);
            return;
        }
        player().q();
        if (callback != null) {
            callback.invoke(true);
        }
    }

    @ReactMethod
    public void setAutoNexTrack(boolean z) {
        if (is_ready()) {
            player().d(z);
        }
    }

    @ReactMethod
    public void playListWithCallback(Callback callback) {
        if (is_ready() && callback != null) {
            callback.invoke(new Gson().toJson((Object) player().t()).toString());
        }
    }

    @ReactMethod
    public void nextTrackWithCallback(Callback callback) {
        if (is_ready() && callback != null) {
            Gson gson = new Gson();
            Track c = player().c(player().j() + 1);
            Object[] objArr = new Object[1];
            objArr[0] = c == null ? null : gson.toJson((Object) c).toString();
            callback.invoke(objArr);
        }
    }

    @ReactMethod
    public void prevTrackWithCallback(Callback callback) {
        if (is_ready() && callback != null) {
            Gson gson = new Gson();
            Track c = player().c(player().j() - 1);
            Object[] objArr = new Object[1];
            objArr[0] = c == null ? null : gson.toJson((Object) c).toString();
            callback.invoke(objArr);
        }
    }

    @ReactMethod
    public void seekToTime(float f) {
        if (is_ready()) {
            player().b(f);
        }
    }

    @ReactMethod
    public void clearCacheSafely() {
        if (is_ready()) {
            player().C();
        }
    }

    @ReactMethod
    public void currentTrackWithCallback(Callback callback) {
        if (is_ready() && callback != null) {
            Gson gson = new Gson();
            Track c = player().c(player().j());
            Object[] objArr = new Object[1];
            objArr[0] = c == null ? null : gson.toJson((Object) c).toString();
            callback.invoke(objArr);
        }
    }

    @ReactMethod
    public void startLivePlayWithRadio(String str) {
        Radio radio;
        if (is_ready() && (radio = (Radio) new Gson().fromJson(str, Radio.class)) != null) {
            player().b(radio);
        }
    }

    @ReactMethod
    public void pauseLivePlay() {
        if (is_ready()) {
            player().o();
        }
    }

    @ReactMethod
    public void resumeLivePlay() {
        if (is_ready()) {
            player().m();
        }
    }

    @ReactMethod
    public void stopLivePlay() {
        if (is_ready()) {
            player().p();
        }
    }

    @ReactMethod
    public void startHistoryLivePlayWithRadio(String str, long j) {
        if (is_ready()) {
            Radio radio = (Radio) new Gson().fromJson(str, Radio.class);
            radio.d(j);
            player().b(radio);
        }
    }

    @ReactMethod
    public void startHistoryLivePlayWithRadioInProgramList(String str, long j, String str2) {
        if (is_ready()) {
            Gson gson = new Gson();
            Radio radio = (Radio) gson.fromJson(str, Radio.class);
            this.programs = (List) gson.fromJson(str2, new TypeToken<List<Long>>() {
            }.getType());
            radio.d(j);
            player().b(radio);
        }
    }

    @ReactMethod
    public void seekHistoryLivePlay(int i, Callback callback) {
        if (is_ready()) {
            player().d(i);
            if (callback != null) {
                callback.invoke(new Object[0]);
            }
        }
    }

    @ReactMethod
    public void playNextProgram() {
        if (is_ready()) {
            PlayableModel k = player().k();
            if (this.programs != null && this.programs.size() > 0 && (k instanceof Radio)) {
                Radio radio = (Radio) k;
                int indexOf = this.programs.indexOf(Long.valueOf(radio.k())) + 1;
                if (indexOf >= this.programs.size()) {
                    indexOf = 0;
                }
                radio.d(this.programs.get(indexOf).longValue());
            }
        }
    }

    @ReactMethod
    public void playPreProgram() {
        if (is_ready()) {
            PlayableModel k = player().k();
            if (this.programs != null && this.programs.size() > 0 && (k instanceof Radio)) {
                Radio radio = (Radio) k;
                int indexOf = this.programs.indexOf(Long.valueOf(radio.k())) - 1;
                if (indexOf < 0) {
                    indexOf = this.programs.size() - 1;
                }
                radio.d(this.programs.get(indexOf).longValue());
            }
        }
    }

    @ReactMethod
    public void forceClearCacheDataForPath(String str, Callback callback) {
        if (is_ready()) {
            player().C();
            if (callback != null) {
                callback.invoke(new Object[0]);
            }
        }
    }

    @ReactMethod
    public void currentPlayingRadioWithCallback(Callback callback) {
        if (is_ready()) {
            PlayableModel k = player().k();
            if (callback != null) {
                callback.invoke(new Gson().toJson((Object) k));
            }
        }
    }

    @ReactMethod
    public void currentPlayingProgramWithCallback(Callback callback) {
        if (is_ready() && callback != null) {
            PlayableModel k = player().k();
            if (k instanceof Radio) {
                callback.invoke(Long.valueOf(((Radio) k).k()));
                return;
            }
            callback.invoke(0);
        }
    }
}
