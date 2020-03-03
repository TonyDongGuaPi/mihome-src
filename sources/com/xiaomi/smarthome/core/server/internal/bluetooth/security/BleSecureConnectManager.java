package com.xiaomi.smarthome.core.server.internal.bluetooth.security;

import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothHelper;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth.BleSecurityLogin;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth.BleSecurityRegister;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleMeshLogin;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleMeshRegister;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipLogin;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipRegister;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipSharedLogin;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.standardauth.BleStandardAuthLogin;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.standardauth.BleStandardAuthRegister;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONException;
import org.json.JSONObject;

public class BleSecureConnectManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static ConcurrentMap<String, SecureConnectInfo> f14289a = new ConcurrentHashMap();
    private static final BleConnectOptions b = new BleConnectOptions.Builder().a(3).c(25000).b(2).d(20000).a();
    private static final SecureConnectOptions c = new SecureConnectOptions.Builder().a(b).a();

    private static class SecureConnectInfo {

        /* renamed from: a  reason: collision with root package name */
        String f14294a;
        BleSecurityLauncher b;
        ISecureConnectHandler c;

        SecureConnectInfo(String str, BleSecurityLauncher bleSecurityLauncher, ISecureConnectHandler iSecureConnectHandler) {
            this.f14294a = str;
            this.b = bleSecurityLauncher;
            this.c = iSecureConnectHandler;
        }

        public void a(BleSecurityLauncher bleSecurityLauncher) {
            this.b = bleSecurityLauncher;
        }
    }

    public static ISecureConnectHandler a(String str, SecureConnectOptions secureConnectOptions, IBleSecureConnectResponse iBleSecureConnectResponse) {
        ConnectResponse connectResponse = null;
        if (f14289a.containsKey(str)) {
            BluetoothMyLogger.c(String.format("SecureConnect for %s error: previous ongoing!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-13, (Bundle) null);
            return null;
        }
        int b2 = BluetoothHelper.b(str);
        if (b2 <= 0) {
            BluetoothMyLogger.c(String.format("SecureConnect for %s error: productId invalid!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-12, (Bundle) null);
            return null;
        }
        byte[] p = BluetoothCache.p(str);
        if (iBleSecureConnectResponse != null) {
            connectResponse = new ConnectResponse(str, iBleSecureConnectResponse);
        }
        if (secureConnectOptions == null) {
            secureConnectOptions = c;
        }
        BleConnectOptions a2 = secureConnectOptions.a();
        BleSecurityLauncher bleSecurityRegister = ByteUtils.e(p) ? new BleSecurityRegister(str, b2, a2) : new BleSecurityLogin(str, b2, p, a2);
        SecureConnectHandler secureConnectHandler = new SecureConnectHandler(str, bleSecurityRegister);
        f14289a.put(str, new SecureConnectInfo(str, bleSecurityRegister, secureConnectHandler));
        bleSecurityRegister.a((IBleSecureConnectResponse) connectResponse);
        return secureConnectHandler;
    }

    public static ISecureConnectHandler b(String str, SecureConnectOptions secureConnectOptions, IBleSecureConnectResponse iBleSecureConnectResponse) {
        BleSecurityLauncher bleSecurityChipLogin;
        BleSecurityLauncher bleSecurityLauncher = null;
        if (f14289a.containsKey(str)) {
            BluetoothMyLogger.c(String.format("securityChipConnect for %s error: previous ongoing!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-13, (Bundle) null);
            return null;
        }
        final int b2 = BluetoothHelper.b(str);
        if (b2 <= 0) {
            BluetoothMyLogger.c(String.format("securityChipConnect for %s error: productId invalid!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-12, (Bundle) null);
            return null;
        }
        ConnectResponse connectResponse = iBleSecureConnectResponse != null ? new ConnectResponse(str, iBleSecureConnectResponse) : null;
        if (secureConnectOptions == null) {
            secureConnectOptions = c;
        }
        final BleConnectOptions a2 = secureConnectOptions.a();
        byte[] r = BluetoothCache.r(str);
        String f = BluetoothCache.f(str);
        SecureConnectHandler secureConnectHandler = new SecureConnectHandler(str, (BleSecurityLauncher) null);
        SecureConnectInfo secureConnectInfo = new SecureConnectInfo(str, (BleSecurityLauncher) null, secureConnectHandler);
        f14289a.put(str, secureConnectInfo);
        if (!ByteUtils.e(r)) {
            String s = BluetoothCache.s(str);
            int t = BluetoothCache.t(str);
            if (t == 0) {
                bleSecurityChipLogin = new BleSecurityChipLogin(str, b2, r, a2);
            } else if (!TextUtils.isEmpty(s)) {
                bleSecurityChipLogin = new BleSecurityChipLogin(str, b2, ByteUtils.a(LtmkEncryptUtil.b(s, ByteUtils.d(r), t)), a2);
            } else if (connectResponse != null) {
                connectResponse.d(-38, (Bundle) null);
            }
            bleSecurityLauncher = bleSecurityChipLogin;
        } else if (TextUtils.isEmpty(f)) {
            bleSecurityLauncher = new BleSecurityChipRegister(str, b2, a2);
        } else {
            final String str2 = str;
            final SecureConnectHandler secureConnectHandler2 = secureConnectHandler;
            final SecureConnectInfo secureConnectInfo2 = secureConnectInfo;
            final ConnectResponse connectResponse2 = connectResponse;
            DeviceApi.a(f, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    String str = "";
                    if (jSONObject != null) {
                        str = jSONObject.optString("key");
                    }
                    if (!TextUtils.isEmpty(str)) {
                        BluetoothCache.j(str2, str);
                        int optInt = jSONObject.optInt("encrypt_type", 0);
                        if (optInt == 0) {
                            BleSecurityChipLogin bleSecurityChipLogin = new BleSecurityChipLogin(str2, b2, ByteUtils.a(str), a2);
                            secureConnectHandler2.setLauncher(bleSecurityChipLogin);
                            secureConnectInfo2.a(bleSecurityChipLogin);
                            bleSecurityChipLogin.a(connectResponse2);
                            return;
                        }
                        String s = BluetoothCache.s(str2);
                        if (!TextUtils.isEmpty(s)) {
                            BleSecurityChipLogin bleSecurityChipLogin2 = new BleSecurityChipLogin(str2, b2, ByteUtils.a(LtmkEncryptUtil.b(s, str, optInt)), a2);
                            secureConnectHandler2.setLauncher(bleSecurityChipLogin2);
                            secureConnectInfo2.a(bleSecurityChipLogin2);
                            bleSecurityChipLogin2.a(connectResponse2);
                        } else if (connectResponse2 != null) {
                            connectResponse2.d(-38, (Bundle) null);
                        }
                    } else {
                        BleSecurityChipRegister bleSecurityChipRegister = new BleSecurityChipRegister(str2, b2, a2);
                        secureConnectHandler2.setLauncher(bleSecurityChipRegister);
                        secureConnectInfo2.a(bleSecurityChipRegister);
                        bleSecurityChipRegister.a(connectResponse2);
                    }
                }

                public void onFailure(Error error) {
                    BluetoothMyLogger.d(String.format("securityChipConnect for %s error: getOwnLtmk failed(%s), try to register", new Object[]{BluetoothMyLogger.a(str2), error.b()}));
                    BleSecurityChipRegister bleSecurityChipRegister = new BleSecurityChipRegister(str2, b2, a2);
                    secureConnectHandler2.setLauncher(bleSecurityChipRegister);
                    secureConnectInfo2.a(bleSecurityChipRegister);
                    bleSecurityChipRegister.a(connectResponse2);
                }
            });
        }
        if (bleSecurityLauncher != null) {
            secureConnectHandler.setLauncher(bleSecurityLauncher);
            secureConnectInfo.a(bleSecurityLauncher);
            bleSecurityLauncher.a((IBleSecureConnectResponse) connectResponse);
        }
        return secureConnectHandler;
    }

    public static ISecureConnectHandler a(String str, String str2, String str3, int i, SecureConnectOptions secureConnectOptions, IBleSecureConnectResponse iBleSecureConnectResponse) {
        IBleSecureConnectResponse iBleSecureConnectResponse2;
        SecureConnectInfo secureConnectInfo = (SecureConnectInfo) f14289a.remove(str);
        ConnectResponse connectResponse = null;
        if (!(secureConnectInfo == null || secureConnectInfo.b == null || (iBleSecureConnectResponse2 = secureConnectInfo.b.i) == null)) {
            secureConnectInfo.b.i = null;
            Bundle bundle = new Bundle();
            bundle.putString("result", "new request");
            BleConnectManager.a().a(str);
            iBleSecureConnectResponse2.d(-2, bundle);
            BluetoothMyLogger.c(String.format("securityChipConnect for %s previous cancel for new request!!", new Object[]{BluetoothMyLogger.a(str)}));
        }
        int b2 = BluetoothHelper.b(str);
        if (b2 <= 0) {
            BluetoothMyLogger.c(String.format("securityChipConnect for %s error: productId invalid!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-12, (Bundle) null);
            return null;
        }
        if (iBleSecureConnectResponse != null) {
            connectResponse = new ConnectResponse(str, iBleSecureConnectResponse);
        }
        if (secureConnectOptions == null) {
            secureConnectOptions = c;
        }
        BleSecurityChipLogin bleSecurityChipLogin = new BleSecurityChipLogin(str, b2, ByteUtils.a(LtmkEncryptUtil.b(str2, str3, i)), secureConnectOptions.a());
        SecureConnectHandler secureConnectHandler = new SecureConnectHandler(str, bleSecurityChipLogin);
        f14289a.put(str, new SecureConnectInfo(str, bleSecurityChipLogin, secureConnectHandler));
        bleSecurityChipLogin.a((IBleSecureConnectResponse) connectResponse);
        return secureConnectHandler;
    }

    public static ISecureConnectHandler c(String str, SecureConnectOptions secureConnectOptions, IBleSecureConnectResponse iBleSecureConnectResponse) {
        ConnectResponse connectResponse = null;
        if (f14289a.containsKey(str)) {
            BluetoothMyLogger.c(String.format("securityChipSharedDeviceConnect for %s error: previous ongoing!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-13, (Bundle) null);
            return null;
        }
        int b2 = BluetoothHelper.b(str);
        if (b2 <= 0) {
            BluetoothMyLogger.c(String.format("securityChipSharedDeviceConnect for %s error: productId invalid!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-12, (Bundle) null);
            return null;
        }
        if (iBleSecureConnectResponse != null) {
            connectResponse = new ConnectResponse(str, iBleSecureConnectResponse);
        }
        if (secureConnectOptions == null) {
            secureConnectOptions = c;
        }
        BleSecurityChipSharedLogin bleSecurityChipSharedLogin = new BleSecurityChipSharedLogin(str, b2, secureConnectOptions.a());
        SecureConnectHandler secureConnectHandler = new SecureConnectHandler(str, bleSecurityChipSharedLogin);
        f14289a.put(str, new SecureConnectInfo(str, bleSecurityChipSharedLogin, secureConnectHandler));
        bleSecurityChipSharedLogin.a((IBleSecureConnectResponse) connectResponse);
        return secureConnectHandler;
    }

    public static ISecureConnectHandler d(String str, SecureConnectOptions secureConnectOptions, IBleSecureConnectResponse iBleSecureConnectResponse) {
        ConnectResponse connectResponse = null;
        if (f14289a.containsKey(str)) {
            BluetoothMyLogger.c(String.format("bleMeshBind for %s error: previous ongoing!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-13, (Bundle) null);
            return null;
        }
        int b2 = BluetoothHelper.b(str);
        if (iBleSecureConnectResponse != null) {
            connectResponse = new ConnectResponse(str, iBleSecureConnectResponse);
        }
        if (secureConnectOptions == null) {
            secureConnectOptions = c;
        }
        BleMeshRegister bleMeshRegister = new BleMeshRegister(str, b2, secureConnectOptions.a());
        SecureConnectHandler secureConnectHandler = new SecureConnectHandler(str, bleMeshRegister);
        f14289a.put(str, new SecureConnectInfo(str, bleMeshRegister, secureConnectHandler));
        bleMeshRegister.a((IBleSecureConnectResponse) connectResponse);
        return secureConnectHandler;
    }

    public static ISecureConnectHandler a(String str, String str2, String str3, SecureConnectOptions secureConnectOptions, IBleSecureConnectResponse iBleSecureConnectResponse) {
        if (f14289a.containsKey(str)) {
            BluetoothMyLogger.c(String.format("bleMeshConnect for %s error: previous ongoing!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-13, (Bundle) null);
            return null;
        }
        final int b2 = BluetoothHelper.b(str);
        final ConnectResponse connectResponse = iBleSecureConnectResponse != null ? new ConnectResponse(str, iBleSecureConnectResponse) : null;
        if (secureConnectOptions == null) {
            secureConnectOptions = c;
        }
        final BleConnectOptions a2 = secureConnectOptions.a();
        SecureConnectHandler secureConnectHandler = new SecureConnectHandler(str, (BleSecurityLauncher) null);
        final SecureConnectInfo secureConnectInfo = new SecureConnectInfo(str, (BleSecurityLauncher) null, secureConnectHandler);
        String o = BluetoothCache.o(str);
        String q = BluetoothCache.q(str);
        f14289a.put(str, secureConnectInfo);
        if (!TextUtils.equals(o, str3) || TextUtils.isEmpty(q)) {
            final String str4 = str;
            final String str5 = str3;
            final SecureConnectHandler secureConnectHandler2 = secureConnectHandler;
            a(str2, new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    if (!TextUtils.isEmpty(str)) {
                        BluetoothCache.i(str4, str5);
                        BluetoothCache.j(str4, str);
                        BleMeshLogin bleMeshLogin = new BleMeshLogin(str4, b2, ByteUtils.a(str), a2);
                        secureConnectHandler2.setLauncher(bleMeshLogin);
                        secureConnectInfo.a(bleMeshLogin);
                        bleMeshLogin.a(connectResponse);
                        return;
                    }
                    BluetoothMyLogger.d(String.format("bleMeshConnect for %s error: gatt ltmk is null", new Object[]{BluetoothMyLogger.a(str4)}));
                    if (connectResponse != null) {
                        connectResponse.d(-15, (Bundle) null);
                    }
                }

                public void onFailure(Error error) {
                    BluetoothMyLogger.d(String.format("bleMeshConnect for %s error: getGattLtmk failed(%s)", new Object[]{BluetoothMyLogger.a(str4), error.b()}));
                    if (connectResponse != null) {
                        connectResponse.d(-15, (Bundle) null);
                    }
                }
            });
        } else {
            BleMeshLogin bleMeshLogin = new BleMeshLogin(str, b2, ByteUtils.a(q), a2);
            secureConnectHandler.setLauncher(bleMeshLogin);
            secureConnectInfo.a(bleMeshLogin);
            bleMeshLogin.a((IBleSecureConnectResponse) connectResponse);
        }
        return secureConnectHandler;
    }

    public static ISecureConnectHandler e(String str, SecureConnectOptions secureConnectOptions, IBleSecureConnectResponse iBleSecureConnectResponse) {
        ConnectResponse connectResponse = null;
        if (f14289a.containsKey(str)) {
            BluetoothMyLogger.c(String.format("bleStandardAuthBind for %s error: previous ongoing!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-13, (Bundle) null);
            return null;
        }
        int b2 = BluetoothHelper.b(str);
        if (b2 <= 0) {
            BluetoothMyLogger.c(String.format("bleStandardAuthBind for %s error: productId invalid!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-12, (Bundle) null);
            return null;
        }
        if (iBleSecureConnectResponse != null) {
            connectResponse = new ConnectResponse(str, iBleSecureConnectResponse);
        }
        if (secureConnectOptions == null) {
            secureConnectOptions = c;
        }
        BleStandardAuthRegister bleStandardAuthRegister = new BleStandardAuthRegister(str, b2, secureConnectOptions.a());
        SecureConnectHandler secureConnectHandler = new SecureConnectHandler(str, bleStandardAuthRegister);
        f14289a.put(str, new SecureConnectInfo(str, bleStandardAuthRegister, secureConnectHandler));
        bleStandardAuthRegister.a((IBleSecureConnectResponse) connectResponse);
        return secureConnectHandler;
    }

    public static ISecureConnectHandler f(String str, SecureConnectOptions secureConnectOptions, IBleSecureConnectResponse iBleSecureConnectResponse) {
        ConnectResponse connectResponse = null;
        if (f14289a.containsKey(str)) {
            BluetoothMyLogger.c(String.format("bleStandardAuthConnect for %s error: previous ongoing!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-13, (Bundle) null);
            return null;
        }
        int b2 = BluetoothHelper.b(str);
        if (b2 <= 0) {
            BluetoothMyLogger.c(String.format("bleStandardAuthConnect for %s error: productId invalid!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-12, (Bundle) null);
            return null;
        }
        byte[] p = BluetoothCache.p(str);
        if (ByteUtils.e(p)) {
            BluetoothMyLogger.c(String.format("bleStandardAuthConnect for %s error: token is empty!!", new Object[]{BluetoothMyLogger.a(str)}));
            iBleSecureConnectResponse.d(-54, (Bundle) null);
            return null;
        }
        if (iBleSecureConnectResponse != null) {
            connectResponse = new ConnectResponse(str, iBleSecureConnectResponse);
        }
        if (secureConnectOptions == null) {
            secureConnectOptions = c;
        }
        BleStandardAuthLogin bleStandardAuthLogin = new BleStandardAuthLogin(str, b2, p, secureConnectOptions.a());
        SecureConnectHandler secureConnectHandler = new SecureConnectHandler(str, bleStandardAuthLogin);
        f14289a.put(str, new SecureConnectInfo(str, bleStandardAuthLogin, secureConnectHandler));
        bleStandardAuthLogin.a((IBleSecureConnectResponse) connectResponse);
        return secureConnectHandler;
    }

    private static void a(String str, final AsyncCallback<String, Error> asyncCallback) {
        DeviceApi.b(str, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                String str2;
                try {
                    str2 = new JSONObject(str).optString("gatt_ltmk");
                } catch (JSONException e) {
                    e.printStackTrace();
                    str2 = "";
                }
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(str2);
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    private static class SecureConnectHandler extends ISecureConnectHandler.Stub {
        BleSecurityLauncher launcher;
        String mac;

        SecureConnectHandler(String str, BleSecurityLauncher bleSecurityLauncher) {
            this.mac = str;
            this.launcher = bleSecurityLauncher;
        }

        public void setLauncher(BleSecurityLauncher bleSecurityLauncher) {
            this.launcher = bleSecurityLauncher;
        }

        public void cancel() throws RemoteException {
            if (this.launcher != null) {
                this.launcher.a();
            }
        }
    }

    private static class ConnectResponse implements IBleSecureConnectResponse {

        /* renamed from: a  reason: collision with root package name */
        String f14293a;
        IBleSecureConnectResponse b;

        ConnectResponse(String str, IBleSecureConnectResponse iBleSecureConnectResponse) {
            this.f14293a = str;
            this.b = iBleSecureConnectResponse;
        }

        public void a(int i, Bundle bundle) {
            BluetoothMyLogger.f(String.format("ConnectResponse onConnectResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            if (this.b != null) {
                this.b.a(i, bundle);
            }
        }

        public void b(int i, Bundle bundle) {
            BluetoothMyLogger.f(String.format("ConnectResponse onAuthResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            if (this.b != null) {
                this.b.b(i, bundle);
            }
        }

        public void c(int i, Bundle bundle) {
            BluetoothMyLogger.f(String.format("ConnectResponse onBindResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            if (this.b != null) {
                this.b.c(i, bundle);
            }
        }

        public void d(int i, Bundle bundle) {
            BluetoothMyLogger.f(String.format("ConnectResponse onLastResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            BleSecureConnectManager.f14289a.remove(this.f14293a);
            if (this.b != null) {
                this.b.d(i, bundle);
            }
        }
    }
}
