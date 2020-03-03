package com.xiaomi.passport.utils;

import android.text.TextUtils;
import com.xiaomi.accountsdk.account.URLs;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.request.SimpleRequestForAccount;
import com.xiaomi.accountsdk.utils.AccountLog;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class AntiSpamIpAddressController {
    private static final String IP_ADDRESS_KEY = "ipAddress";
    private static final String TAG = "AntiSpamIpAddressController";
    private static final String URL_CHECK_IPV6_REQUEST = (URLs.URL_ACCOUNT_BASE + "/ip/next");
    /* access modifiers changed from: private */
    public static final CountDownLatch countDownLatch = new CountDownLatch(1);
    /* access modifiers changed from: private */
    public static final Map<String, String> ipAddressCookie = new HashMap();

    public void init() {
        Executors.newCachedThreadPool().execute(new Runnable() {
            public void run() {
                if (AntiSpamIpAddressController.hasIPv6()) {
                    String access$100 = AntiSpamIpAddressController.this.getAntiSpamIPAddress();
                    if (!TextUtils.isEmpty(access$100)) {
                        AccountLog.i(AntiSpamIpAddressController.TAG, "ipAddress not empty");
                        AntiSpamIpAddressController.ipAddressCookie.clear();
                        AntiSpamIpAddressController.ipAddressCookie.put(AntiSpamIpAddressController.IP_ADDRESS_KEY, access$100);
                    }
                }
                AntiSpamIpAddressController.countDownLatch.countDown();
            }
        });
    }

    /* access modifiers changed from: private */
    public static boolean hasIPv6() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet6Address) && !nextElement.getHostAddress().startsWith("fe80")) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (SocketException e) {
            AccountLog.w(TAG, (Throwable) e);
            return true;
        }
    }

    public Map<String, String> blockingGetIPAddressCookie() {
        try {
            countDownLatch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            AccountLog.w(TAG, "blockingGetIPAddressCookie", e);
        }
        return ipAddressCookie;
    }

    public Map<String, String> getIPAddressCookie() {
        return ipAddressCookie;
    }

    /* access modifiers changed from: private */
    public String getAntiSpamIPAddress() {
        try {
            SimpleRequest.StringContent asString = SimpleRequestForAccount.getAsString(URL_CHECK_IPV6_REQUEST, (Map<String, String>) null, (Map<String, String>) null, true);
            if (asString == null) {
                AccountLog.i(TAG, "getAntiSpamIPAddress: response content is null");
                return null;
            }
            JSONObject jSONObject = new JSONObject(XMPassport.removeSafePrefixAndGetRealBody(asString));
            int i = jSONObject.getInt("code");
            String optString = jSONObject.optString("description");
            AccountLog.i(TAG, "getAntiSpamIPAddress--code: " + i + " ,desc: " + optString);
            if (i == 0) {
                JSONObject jSONObject2 = new JSONObject(jSONObject.getString("data"));
                boolean z = jSONObject2.getBoolean("hasNextUrl");
                AccountLog.i(TAG, "next: " + z);
                if (z) {
                    return ipv6NextRequest(jSONObject2.getString("url"));
                }
            }
            return null;
        } catch (AccessDeniedException | AuthenticationFailureException | IOException | JSONException e) {
            AccountLog.w(TAG, "getAntiSpamIPAddress", e);
        }
    }

    private String ipv6NextRequest(String str) {
        try {
            SimpleRequest.StringContent asString = SimpleRequestForAccount.getAsString(str, (Map<String, String>) null, (Map<String, String>) null, true);
            if (asString == null) {
                AccountLog.i(TAG, "ipv6NextRequest: next url response content is null");
                return null;
            }
            JSONObject jSONObject = new JSONObject(XMPassport.removeSafePrefixAndGetRealBody(asString));
            int i = jSONObject.getInt("code");
            String string = jSONObject.getString("description");
            AccountLog.i(TAG, "ipv6NextRequest--code: " + i + " ,desc: " + string);
            if (i == 0) {
                return asString.getHeader(IP_ADDRESS_KEY);
            }
            return null;
        } catch (AccessDeniedException | AuthenticationFailureException | IOException | JSONException e) {
            AccountLog.w(TAG, "ipv6NextRequest", e);
        }
    }
}
