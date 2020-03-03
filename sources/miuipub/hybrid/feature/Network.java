package miuipub.hybrid.feature;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import java.util.Map;
import miuipub.hybrid.Callback;
import miuipub.hybrid.HybridFeature;
import miuipub.hybrid.LifecycleListener;
import miuipub.hybrid.NativeInterface;
import miuipub.hybrid.Request;
import miuipub.hybrid.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class Network implements HybridFeature {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2963a = "Network";
    private static final String b = "getType";
    private static final String c = "enableNotification";
    private static final String d = "disableNotification";
    private static final String e = "metered";
    private static final String f = "connected";
    private IntentFilter g = new IntentFilter();
    /* access modifiers changed from: private */
    public Callback h;
    private NetworkStateReceiver i;
    private LifecycleListener j;

    public void a(Map<String, String> map) {
    }

    private class NetworkStateReceiver extends BroadcastReceiver {
        private NetworkStateReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras() != null) {
                boolean z = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED;
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(Network.f, z);
                    Network.this.h.a(new Response(jSONObject));
                } catch (JSONException e) {
                    Log.e("Network", e.getMessage());
                }
            }
        }
    }

    public Network() {
        this.g.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    }

    public Response a(Request request) {
        String a2 = request.a();
        if (b.equals(a2)) {
            return c(request);
        }
        if (c.equals(a2)) {
            return d(request);
        }
        if (d.equals(a2)) {
            return e(request);
        }
        return new Response(204, "no such action");
    }

    private Response c(Request request) {
        boolean isActiveNetworkMetered = ((ConnectivityManager) request.e().a().getSystemService("connectivity")).isActiveNetworkMetered();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(e, isActiveNetworkMetered);
        } catch (JSONException e2) {
            Log.e("Network", e2.getMessage());
        }
        return new Response(jSONObject);
    }

    /* access modifiers changed from: private */
    public void a(NativeInterface nativeInterface) {
        if (this.i != null) {
            Activity a2 = nativeInterface.a();
            nativeInterface.b(this.j);
            a2.unregisterReceiver(this.i);
            this.i = null;
        }
    }

    private Response d(Request request) {
        final NativeInterface e2 = request.e();
        a(e2);
        Activity a2 = e2.a();
        this.h = request.c();
        this.i = new NetworkStateReceiver();
        a2.registerReceiver(this.i, this.g);
        this.j = new LifecycleListener() {
            public void a() {
                g();
            }

            public void f() {
                g();
            }

            private void g() {
                Network.this.a(e2);
                Network.this.h.a(new Response(100));
            }
        };
        e2.a(this.j);
        return null;
    }

    private Response e(Request request) {
        a(request.e());
        return new Response(100);
    }

    public HybridFeature.Mode b(Request request) {
        String a2 = request.a();
        if (b.equals(a2)) {
            return HybridFeature.Mode.SYNC;
        }
        if (c.equals(a2)) {
            return HybridFeature.Mode.CALLBACK;
        }
        if (d.equals(a2)) {
            return HybridFeature.Mode.SYNC;
        }
        return null;
    }
}
