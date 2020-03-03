package com.mibi.common.data;

import android.text.TextUtils;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Client;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.exception.ServiceTokenExpiredException;
import com.mipay.core.runtime.BundleActivator;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7516a = "DeviceManager";
    private static String b = "deviceId";

    public static void a(Session session) throws PaymentException {
        String str;
        if (session != null) {
            synchronized (session.a(b)) {
                if (TextUtils.isEmpty(c(session))) {
                    if (session.e() instanceof FakeAccountLoader) {
                        str = CommonConstants.a(CommonConstants.I);
                    } else {
                        str = CommonConstants.a("device");
                    }
                    Connection a2 = ConnectionFactory.a(BundleActivator.getAppContext(), session, str);
                    SortedParameter d = a2.d();
                    d.a("la", (Object) Client.a());
                    d.a("co", (Object) Client.b());
                    d.a("model", (Object) Client.f());
                    d.a("device", (Object) Client.g());
                    d.a("product", (Object) Client.h());
                    d.a("manufacturer", (Object) Client.i());
                    d.a("brand", (Object) Client.j());
                    d.a("buildType", (Object) Client.k());
                    d.a("sdk", (Object) Integer.valueOf(Client.l()));
                    d.a("systemVersion", (Object) Integer.valueOf(Client.l()));
                    d.a("systemRelease", (Object) Client.o());
                    d.a("os", (Object) Client.p());
                    d.a("miuiVersion", (Object) Client.m());
                    d.a("miuiUiVersion", (Object) Client.w());
                    d.a("miuiUiVersionCode", (Object) Integer.valueOf(Client.x()));
                    d.a("platform", (Object) Client.q());
                    Client.DisplayInfo z = Client.z();
                    d.a("displayResolution", (Object) z.c());
                    d.a("displayDensity", (Object) Integer.valueOf(z.d()));
                    d.a("screenSize", (Object) Integer.valueOf(z.f()));
                    Client.AppInfo F = Client.F();
                    d.a("version", (Object) F.a());
                    d.a("package", (Object) F.e());
                    d.a("apkSign", (Object) F.f());
                    d.a("apkChannel", (Object) Client.D());
                    d.a("romChannel", (Object) Client.E());
                    Client.TelephonyInfo A = Client.A();
                    d.a("carrier", (Object) A.a());
                    d.a("iccid", (Object) A.h());
                    d.a("uuid", (Object) A.j());
                    d.a("imei", (Object) A.i());
                    d.a("imsi", (Object) A.l());
                    d.a("mac", (Object) Client.B().a());
                    d.a("androidId", (Object) Client.C());
                    d.a("xiaomiDeviceToken", (Object) Client.M());
                    JSONObject e = a2.e();
                    try {
                        if (e.getInt("errcode") != 1984) {
                            String string = e.getString("deviceId");
                            if (!TextUtils.isEmpty(string)) {
                                session.m().a("groupGlobalSettings", b, (Object) string);
                                return;
                            }
                            throw new ResultException("result has error");
                        }
                        throw new ServiceTokenExpiredException();
                    } catch (JSONException e2) {
                        throw new ResultException("error code not exists", e2);
                    } catch (JSONException e3) {
                        throw new ResultException((Throwable) e3);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("session should not be null when upload device info.");
        }
    }

    private static String c(Session session) {
        return (String) session.m().c("groupGlobalSettings", b);
    }

    public static String b(Session session) {
        String c;
        synchronized (session.a(b)) {
            c = c(session);
        }
        return c;
    }
}
