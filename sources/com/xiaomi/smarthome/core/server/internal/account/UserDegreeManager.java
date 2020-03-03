package com.xiaomi.smarthome.core.server.internal.account;

import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.SmartHomeApiParser;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class UserDegreeManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile UserDegreeManager f14065a;
    /* access modifiers changed from: private */
    public volatile boolean b = true;
    /* access modifiers changed from: private */
    public AtomicBoolean c = new AtomicBoolean(false);

    private UserDegreeManager() {
    }

    public static UserDegreeManager a() {
        if (f14065a == null) {
            synchronized (UserDegreeManager.class) {
                if (f14065a == null) {
                    f14065a = new UserDegreeManager();
                }
            }
        }
        return f14065a;
    }

    public void b() {
        if (this.b) {
            LogUtil.a("UserDegree", "dirty true");
            if (!this.c.getAndSet(true)) {
                LogUtil.a("UserDegree", "mSyncOngoing set true, start req");
                new ArrayList().add(new KeyValuePair("data", new JSONObject().toString()));
                NetRequest a2 = new NetRequest.Builder().a("POST").b("/user/degree").a();
                final AnonymousClass1 r1 = new JsonParser<JSONObject>() {
                    /* renamed from: a */
                    public JSONObject parse(JSONObject jSONObject) throws JSONException {
                        return jSONObject;
                    }
                };
                final AnonymousClass2 r2 = new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        LogUtil.a("UserDegree", "degree:" + jSONObject.toString());
                        if (jSONObject == null) {
                            boolean unused = UserDegreeManager.this.b = false;
                            UserDegreeManager.this.c.set(false);
                            return;
                        }
                        try {
                            UserDegreeManager.this.a(jSONObject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        boolean unused2 = UserDegreeManager.this.b = false;
                        UserDegreeManager.this.c.set(false);
                    }

                    public void onFailure(Error error) {
                        LogUtil.a("UserDegree", "onFailure");
                        boolean unused = UserDegreeManager.this.b = false;
                        UserDegreeManager.this.c.set(false);
                    }
                };
                SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                    /* renamed from: a */
                    public void b(NetResult netResult) {
                        SmartHomeApiParser.a().a(netResult, r1, r2);
                    }

                    /* renamed from: b */
                    public void a(NetResult netResult) {
                        SmartHomeApiParser.a().a(netResult, r1, r2);
                    }

                    public void a(NetError netError) {
                        r2.onFailure(new Error(netError.a(), netError.b()));
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        AccountManager.a().a(jSONObject);
    }

    public void c() {
        this.b = true;
    }

    public boolean d() {
        return this.b;
    }
}
