package com.xiaomi.smarthome.miio;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.library.crypto.rc4coder.Coder;
import com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WifiAccount {

    /* renamed from: a  reason: collision with root package name */
    private List<WifiItem> f11583a = new ArrayList();

    public static class WifiItem {

        /* renamed from: a  reason: collision with root package name */
        public int f11584a;
        public boolean b;
        public String c;
        public String d;
        public String e;
        public String f;

        public static WifiItem a(JSONObject jSONObject, RC4DropCoder rC4DropCoder) {
            if (TextUtils.isEmpty(jSONObject.optString("password"))) {
                return null;
            }
            WifiItem wifiItem = new WifiItem();
            wifiItem.f11584a = jSONObject.optInt("networkId");
            wifiItem.c = jSONObject.optString(DeviceTagInterface.e);
            wifiItem.d = TextUtils.isEmpty(jSONObject.optString("password")) ? "" : rC4DropCoder.a(jSONObject.optString("password"));
            wifiItem.e = jSONObject.optString("bssid");
            wifiItem.f = jSONObject.optString("capabilities");
            wifiItem.b = true;
            return wifiItem;
        }

        public static JSONObject a(WifiItem wifiItem, RC4DropCoder rC4DropCoder) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("networkId", wifiItem.f11584a);
                jSONObject.put(DeviceTagInterface.e, wifiItem.c);
                jSONObject.put("password", TextUtils.isEmpty(wifiItem.d) ? "" : rC4DropCoder.b(wifiItem.d));
                jSONObject.put("bssid", wifiItem.e);
                jSONObject.put("capabilities", wifiItem.f);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }
    }

    public void a(String str) {
        if (str != null) {
            this.f11583a.clear();
            RC4DropCoder rC4DropCoder = null;
            try {
                rC4DropCoder = new RC4DropCoder(Coder.d("com.xiaomi.smarthome".getBytes()));
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (SecurityException e2) {
                e2.printStackTrace();
            } catch (NoSuchAlgorithmException e3) {
                e3.printStackTrace();
            }
            if (rC4DropCoder != null) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        WifiItem a2 = WifiItem.a((JSONObject) jSONArray.get(i), rC4DropCoder);
                        if (a2 != null) {
                            this.f11583a.add(a2);
                        }
                    }
                } catch (JSONException unused) {
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0022 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a() {
        /*
            r4 = this;
            r0 = 0
            com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder r1 = new com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder     // Catch:{ InvalidKeyException -> 0x001b, SecurityException -> 0x0016, NoSuchAlgorithmException -> 0x0011 }
            java.lang.String r2 = "com.xiaomi.smarthome"
            byte[] r2 = r2.getBytes()     // Catch:{ InvalidKeyException -> 0x001b, SecurityException -> 0x0016, NoSuchAlgorithmException -> 0x0011 }
            byte[] r2 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.d(r2)     // Catch:{ InvalidKeyException -> 0x001b, SecurityException -> 0x0016, NoSuchAlgorithmException -> 0x0011 }
            r1.<init>((byte[]) r2)     // Catch:{ InvalidKeyException -> 0x001b, SecurityException -> 0x0016, NoSuchAlgorithmException -> 0x0011 }
            goto L_0x0020
        L_0x0011:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x001f
        L_0x0016:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x001f
        L_0x001b:
            r1 = move-exception
            r1.printStackTrace()
        L_0x001f:
            r1 = r0
        L_0x0020:
            if (r1 != 0) goto L_0x0023
            return r0
        L_0x0023:
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            r2 = 0
        L_0x0029:
            java.util.List<com.xiaomi.smarthome.miio.WifiAccount$WifiItem> r3 = r4.f11583a
            int r3 = r3.size()
            if (r2 >= r3) goto L_0x004f
            java.util.List<com.xiaomi.smarthome.miio.WifiAccount$WifiItem> r3 = r4.f11583a
            java.lang.Object r3 = r3.get(r2)
            com.xiaomi.smarthome.miio.WifiAccount$WifiItem r3 = (com.xiaomi.smarthome.miio.WifiAccount.WifiItem) r3
            boolean r3 = r3.b
            if (r3 == 0) goto L_0x004c
            java.util.List<com.xiaomi.smarthome.miio.WifiAccount$WifiItem> r3 = r4.f11583a
            java.lang.Object r3 = r3.get(r2)
            com.xiaomi.smarthome.miio.WifiAccount$WifiItem r3 = (com.xiaomi.smarthome.miio.WifiAccount.WifiItem) r3
            org.json.JSONObject r3 = com.xiaomi.smarthome.miio.WifiAccount.WifiItem.a((com.xiaomi.smarthome.miio.WifiAccount.WifiItem) r3, (com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder) r1)
            r0.put(r3)
        L_0x004c:
            int r2 = r2 + 1
            goto L_0x0029
        L_0x004f:
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.WifiAccount.a():java.lang.String");
    }

    public List<WifiItem> b() {
        return this.f11583a;
    }

    public WifiItem b(String str) {
        for (WifiItem next : this.f11583a) {
            if (next != null && next.e != null && next.e.equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    public void c(String str) {
        for (WifiItem next : this.f11583a) {
            if (next.e.equalsIgnoreCase(str)) {
                this.f11583a.remove(next);
                return;
            }
        }
    }

    public void a(WifiItem wifiItem) {
        for (WifiItem next : this.f11583a) {
            if (next.e.equalsIgnoreCase(wifiItem.e)) {
                this.f11583a.remove(next);
                this.f11583a.add(wifiItem);
                return;
            }
        }
        this.f11583a.add(wifiItem);
    }
}
