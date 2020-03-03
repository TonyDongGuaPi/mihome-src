package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.DeviceApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.IBleDeviceLauncher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.SecurityChipUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.crypto.Base64Coder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BleMeshRegisterConnector extends BleSecurityChipConnector {
    private static final String f = "BleMeshRegisterConnector";
    private static final int g = 4101;
    private static final byte[] h = {64, 0, 0, 0};
    private static final byte[] i = {Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0};
    /* access modifiers changed from: private */
    public static final byte[] j = {Constants.TagName.INVOICE_TOKEN, 0, 0, 0};
    private static final byte[] k = {Constants.TagName.TERMINAL_BACK_INFO_TYPE, 0, 0, 0};
    private static final byte[] l = {Constants.TagName.TERMINAL_OS_VERSION, 0, 0, 0};
    private static final byte[] m = {Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0};
    private static final byte[] n = {Constants.TagName.TERMINAL_BASEBAND_VERSION, 0, 0, 0};
    /* access modifiers changed from: private */
    public static final byte[] o = {Constants.TagName.ACTIVITY_INFO, 0, 0, 0};
    private static final byte[] p = {Constants.TagName.BUSINESS_ORDER_TYPE, 0, 0, 0};
    /* access modifiers changed from: private */
    public static final byte[] q = {Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0};
    private static final byte[] r = {-31, 0, 0, 0};
    /* access modifiers changed from: private */
    public String A;
    /* access modifiers changed from: private */
    public int B = 0;
    /* access modifiers changed from: private */
    public String C;
    /* access modifiers changed from: private */
    public int D;
    /* access modifiers changed from: private */
    public byte[] E;
    /* access modifiers changed from: private */
    public byte[] F;
    private byte[] G;
    /* access modifiers changed from: private */
    public int H = -7;
    public Map<Integer, List<Integer>> e = new LinkedHashMap();
    private byte[] s;
    private byte[] t;
    private byte[] u;
    private byte[] v;
    /* access modifiers changed from: private */
    public byte[] w;
    /* access modifiers changed from: private */
    public String x;
    /* access modifiers changed from: private */
    public String y;
    /* access modifiers changed from: private */
    public String z;

    /* access modifiers changed from: protected */
    public byte[] i() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean k() {
        return true;
    }

    protected BleMeshRegisterConnector(IBleDeviceLauncher iBleDeviceLauncher) {
        super(iBleDeviceLauncher);
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr, int i2) {
        if (d()) {
            a(-2);
        } else if (i2 != 1) {
            switch (i2) {
                case 3:
                    c(bArr);
                    return;
                case 4:
                    d(bArr);
                    return;
                default:
                    return;
            }
        } else {
            b(bArr);
        }
    }

    /* access modifiers changed from: protected */
    public void a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.M)) {
            g(bArr);
        } else if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.S)) {
            super.a(uuid, uuid2, bArr);
        }
    }

    /* access modifiers changed from: protected */
    public void j() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleMeshRegisterConnector Process Step 1 ...");
        BluetoothCache.d(e(), "".getBytes());
        b((BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleMeshRegisterConnector Step 1 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleMeshRegisterConnector.this.q();
                } else {
                    BleMeshRegisterConnector.this.a(-27);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void q() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleMeshRegisterConnector Process Step 1 plus ...");
        a((BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleMeshRegisterConnector Step 1 plus onResponse: " + Code.a(i));
                if (i == 0) {
                    BleMeshRegisterConnector.this.r();
                } else {
                    BleMeshRegisterConnector.this.a(-27);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void r() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleMeshRegisterConnector Process Step 2 ...");
        BleConnectManager.a().b(e(), BluetoothConstants.i, BluetoothConstants.M, h, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleMeshRegisterConnector Step 2 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleMeshRegisterConnector.this.d.removeMessages(4101);
                    BleMeshRegisterConnector.this.d.sendEmptyMessageDelayed(4101, 15000);
                    return;
                }
                BleMeshRegisterConnector.this.a(-28);
            }
        });
    }

    private void b(byte[] bArr) {
        BluetoothMyLogger.d("BleMeshRegisterConnector Process recvDeviceCert ...");
        this.v = Arrays.copyOf(bArr, bArr.length);
        this.d.removeMessages(4101);
        this.d.sendEmptyMessageDelayed(4101, 15000);
    }

    private void c(byte[] bArr) {
        BluetoothMyLogger.d("BleMeshRegisterConnector Process recvDevicePub ...");
        this.t = Arrays.copyOfRange(bArr, 0, 12);
        this.u = Arrays.copyOfRange(bArr, 12, 20);
        this.s = Arrays.copyOfRange(bArr, 20, 84);
        this.d.removeMessages(4101);
        s();
    }

    private void d(byte[] bArr) {
        BluetoothMyLogger.d("BleMeshRegisterConnector Process recvDeviceSign ...");
        this.d.removeMessages(4101);
        e(bArr);
    }

    private void s() {
        DeviceApi.a(BluetoothCache.g(e()), Base64Coder.a(this.s, 24), ByteUtils.c(this.u), Base64Coder.a(this.v, 24), ByteUtils.d(this.t), "123456", (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String unused = BleMeshRegisterConnector.this.z = jSONObject.optString("sign");
                    String unused2 = BleMeshRegisterConnector.this.y = jSONObject.optString("pub");
                    String unused3 = BleMeshRegisterConnector.this.x = jSONObject.optString("server_cert");
                    jSONObject.optString("code");
                    String unused4 = BleMeshRegisterConnector.this.A = jSONObject.optString("did");
                    if (!TextUtils.isEmpty(BleMeshRegisterConnector.this.z) && !TextUtils.isEmpty(BleMeshRegisterConnector.this.y) && !TextUtils.isEmpty(BleMeshRegisterConnector.this.x)) {
                        if (!TextUtils.isEmpty(BleMeshRegisterConnector.this.A)) {
                            BleMeshRegisterConnector.this.t();
                            return;
                        }
                    }
                    int unused5 = BleMeshRegisterConnector.this.H = -39;
                    BleMeshRegisterConnector.this.f(BleMeshRegisterConnector.o);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Error error) {
                int unused = BleMeshRegisterConnector.this.H = -39;
                BleMeshRegisterConnector.this.f(BleMeshRegisterConnector.o);
            }
        });
    }

    public String l() {
        return this.A;
    }

    /* access modifiers changed from: private */
    public void t() {
        if (!a(Base64Coder.a(this.x, 24), 7, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleMeshRegisterConnector Step 3 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleMeshRegisterConnector.this.u();
                } else {
                    BleMeshRegisterConnector.this.a(-28);
                }
            }
        })) {
            a(-28);
        }
    }

    /* access modifiers changed from: private */
    public void u() {
        if (!a(Base64Coder.a(this.y, 24), 3, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleMeshRegisterConnector Step 4 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleMeshRegisterConnector.this.v();
                } else {
                    BleMeshRegisterConnector.this.a(-28);
                }
            }
        })) {
            a(-28);
        }
    }

    /* access modifiers changed from: private */
    public void v() {
        if (!a(Base64Coder.a(this.z, 24), 8, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleMeshRegisterConnector Step 5 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleMeshRegisterConnector.this.d.removeMessages(4101);
                    BleMeshRegisterConnector.this.d.sendEmptyMessageDelayed(4101, 20000);
                    return;
                }
                BleMeshRegisterConnector.this.a(-28);
            }
        })) {
            a(-28);
        }
    }

    private void e(byte[] bArr) {
        int g2 = BluetoothCache.g(e());
        DeviceApi.a((long) g2, e(), Base64Coder.a(bArr, 24), this.A, "", new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String unused = BleMeshRegisterConnector.this.C = jSONObject.optString("static_oob");
                    String optString = jSONObject.optString("gatt_ltmk");
                    int unused2 = BleMeshRegisterConnector.this.D = jSONObject.optInt("address");
                    JSONObject optJSONObject = jSONObject.optJSONObject("appkey");
                    if (optJSONObject != null) {
                        optJSONObject.optString("key");
                        optJSONObject.optString("appkey_id");
                    }
                    if (TextUtils.isEmpty(optString)) {
                        int unused3 = BleMeshRegisterConnector.this.H = -40;
                        BleMeshRegisterConnector.this.f(BleMeshRegisterConnector.q);
                        return;
                    }
                    BluetoothCache.n(BleMeshRegisterConnector.this.e(), str);
                    byte[] unused4 = BleMeshRegisterConnector.this.w = Base64Coder.a(optString, 24);
                    BleMeshRegisterConnector.this.w();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Error error) {
                int unused = BleMeshRegisterConnector.this.H = -40;
                BleMeshRegisterConnector.this.f(BleMeshRegisterConnector.q);
            }
        });
    }

    /* access modifiers changed from: private */
    public void w() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleMeshRegisterConnector, sendRegVerifySuccessToDevice");
        BleConnectManager.a().b(e(), BluetoothConstants.i, BluetoothConstants.M, k, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleMeshRegisterConnector sendRegVerifySuccessToDevice onResponse: " + Code.a(i));
                if (i == 0) {
                    BleMeshRegisterConnector.this.x();
                } else {
                    BleMeshRegisterConnector.this.a(-28);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void x() {
        DeviceApi.b(new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    try {
                        int unused = BleMeshRegisterConnector.this.B = Integer.parseInt(jSONObject.optString("iv_index"), 16);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    JSONObject optJSONObject = jSONObject.optJSONObject("primary_netkey");
                    if (optJSONObject != null) {
                        byte[] unused2 = BleMeshRegisterConnector.this.E = ByteUtils.a(optJSONObject.optString("key"));
                    }
                    JSONObject optJSONObject2 = jSONObject.optJSONObject("ctl_appkey");
                    if (optJSONObject2 != null) {
                        byte[] unused3 = BleMeshRegisterConnector.this.F = ByteUtils.a(optJSONObject2.optString("key"));
                    }
                    if (BleMeshRegisterConnector.this.E == null || BleMeshRegisterConnector.this.F == null) {
                        int unused4 = BleMeshRegisterConnector.this.H = -44;
                        BleMeshRegisterConnector.this.f(BleMeshRegisterConnector.j);
                        return;
                    }
                    BleMeshRegisterConnector.this.y();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }

            public void onFailure(Error error) {
                int unused = BleMeshRegisterConnector.this.H = -44;
                BleMeshRegisterConnector.this.f(BleMeshRegisterConnector.j);
            }
        });
    }

    /* access modifiers changed from: private */
    public void y() {
        DeviceApi.a(BluetoothCache.g(e()), (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                try {
                    JSONArray optJSONArray = new JSONObject(str).optJSONArray(MessengerShareContentUtility.ELEMENTS);
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                int optInt = optJSONObject.optInt("num");
                                ArrayList arrayList = new ArrayList();
                                JSONArray optJSONArray2 = optJSONObject.optJSONArray("model_id");
                                if (optJSONArray2 != null) {
                                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                        try {
                                            arrayList.add(Integer.valueOf(Integer.parseInt(optJSONArray2.optString(i2), 16)));
                                        } catch (NumberFormatException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                BleMeshRegisterConnector.this.e.put(Integer.valueOf(optInt), arrayList);
                            }
                        }
                    }
                    if (BleMeshRegisterConnector.this.e != null) {
                        BleMeshRegisterConnector.this.A();
                        return;
                    }
                    int unused = BleMeshRegisterConnector.this.H = -44;
                    BleMeshRegisterConnector.this.f(BleMeshRegisterConnector.j);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }

            public void onFailure(Error error) {
                int unused = BleMeshRegisterConnector.this.H = -44;
                BleMeshRegisterConnector.this.f(BleMeshRegisterConnector.j);
            }
        });
    }

    private int z() {
        int i2 = 0;
        for (Map.Entry<Integer, List<Integer>> value : this.e.entrySet()) {
            i2 += ((List) value.getValue()).size();
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public void A() {
        ByteBuffer order = ByteBuffer.allocate(1024).order(ByteOrder.LITTLE_ENDIAN);
        this.G = m();
        order.put((byte) 1);
        order.put((byte) 16);
        order.put(this.G);
        order.put((byte) 2);
        order.put((byte) 25);
        order.put(this.E);
        order.putShort(0);
        order.put((byte) 0);
        order.putInt(this.B);
        order.putShort((short) this.D);
        order.put((byte) 3);
        order.put((byte) 20);
        order.putShort(0);
        order.putShort(0);
        order.put(this.F);
        order.put((byte) 4);
        int z2 = z();
        order.put((byte) (z2 * 8));
        if (z2 != 0) {
            for (Map.Entry next : this.e.entrySet()) {
                Integer num = (Integer) next.getKey();
                for (Integer num2 : (List) next.getValue()) {
                    order.putShort(num.shortValue());
                    Integer valueOf = Integer.valueOf(num2.intValue() >> 16);
                    Integer valueOf2 = Integer.valueOf(num2.intValue() & 65535);
                    order.putShort(valueOf.shortValue());
                    order.putShort(valueOf2.shortValue());
                    order.putShort(0);
                }
            }
        }
        byte[] bArr = new byte[order.position()];
        System.arraycopy(order.array(), 0, bArr, 0, bArr.length);
        byte[] a2 = ByteUtils.a(this.C);
        byte[] bArr2 = new byte[8];
        System.arraycopy(a2, 0, bArr2, 0, 8);
        if (!a(SecurityChipUtil.a(a2, bArr2, bArr), 9, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleMeshRegisterConnector sendMeshProvisionInfoToDevice onResponse: " + Code.a(i));
                if (i == 0) {
                    BleMeshRegisterConnector.this.d.removeMessages(4101);
                    BleMeshRegisterConnector.this.d.sendEmptyMessageDelayed(4101, 20000);
                    return;
                }
                BleMeshRegisterConnector.this.a(-28);
            }
        })) {
            a(-28);
        }
    }

    public static final byte[] m() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    /* access modifiers changed from: private */
    public void f(byte[] bArr) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleMeshRegisterConnector, sendErrorCodeToDevice errorCode = " + ByteUtils.d(bArr));
        BleConnectManager.a().b(e(), BluetoothConstants.i, BluetoothConstants.M, bArr, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleMeshRegisterConnector sendErrorCodeToDevice onResponse: " + Code.a(i));
                if (i == 0) {
                    BleMeshRegisterConnector.this.d.removeMessages(4101);
                    BleMeshRegisterConnector.this.d.sendEmptyMessageDelayed(4101, 15000);
                    return;
                }
                BleMeshRegisterConnector.this.a(-28);
            }
        });
    }

    private void B() {
        DeviceApi.a(true, this.A, ByteUtils.d(this.G), ByteUtils.d(a(ByteUtils.a(this.C))).toLowerCase().substring(0, 32), (AsyncCallback<Boolean, Error>) new AsyncCallback<Boolean, Error>() {
            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                if (bool.booleanValue()) {
                    BleMeshRegisterConnector.this.a(0);
                } else {
                    BleMeshRegisterConnector.this.a(-45);
                }
            }

            public void onFailure(Error error) {
                BleMeshRegisterConnector.this.a(-45);
            }
        });
    }

    private void g(byte[] bArr) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleMeshRegisterConnector Process Step 10 ..., value = " + ByteUtils.d(bArr));
        this.d.removeMessages(4101);
        if (ByteUtils.b(bArr, i)) {
            B();
        } else if (ByteUtils.b(bArr, l)) {
            a(-41);
        } else if (ByteUtils.b(bArr, n)) {
            a(-42);
        } else if (ByteUtils.b(bArr, m)) {
            a(-43);
        } else if (ByteUtils.b(bArr, r)) {
            a(-17);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        if (message.what == 4101) {
            BluetoothMyLogger.f("BleMeshRegisterConnector notify timeout");
            a(this.H);
        }
    }

    public static byte[] a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(bArr);
            return instance.digest();
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }
}
