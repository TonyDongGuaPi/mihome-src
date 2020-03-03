package com.xiaomi.jr.antifraud.por;

import android.content.Context;
import android.net.NetworkInfo;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.xiaomi.jr.antifraud.por.EventTracker;
import com.xiaomi.jr.common.utils.NetworkUtils;
import com.xiaomi.jr.http.NetworkStatusReceiver;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class NetworkMonitor {

    /* renamed from: a  reason: collision with root package name */
    private static NetworkStatusReceiver.NetworkStatusListener f10303a = new NetworkStatusReceiver.NetworkStatusListener() {
        public void a(Context context, NetworkInfo networkInfo) {
            NetworkChange networkChange = new NetworkChange();
            networkChange.f10300a = EventTracker.a().b();
            networkChange.c = NetworkUtils.a(true);
            networkChange.d = NetworkUtils.c(context);
        }
    };

    static class NetworkChange extends EventTracker.Event {
        public String c;
        public String d;

        public NetworkChange() {
            super(LogCategory.CATEGORY_NETWORK);
        }

        /* access modifiers changed from: protected */
        public void a(JSONObject jSONObject) throws JSONException {
            jSONObject.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, this.c);
            jSONObject.put("type", this.d);
        }
    }

    public static void a(Context context) {
        NetworkStatusReceiver.addNetworkStatusListener(context, f10303a, false);
    }

    public static void a() {
        NetworkStatusReceiver.removeNetworkStatusListener(f10303a);
    }
}
