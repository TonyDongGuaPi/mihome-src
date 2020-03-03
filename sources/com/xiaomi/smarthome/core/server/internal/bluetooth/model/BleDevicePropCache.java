package com.xiaomi.smarthome.core.server.internal.bluetooth.model;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattProfile;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public final class BleDevicePropCache extends BluetoothContextManager implements IBlePropCacher {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14198a = "ble_device_prop_cache";
    private static BleDevicePropCache f;
    /* access modifiers changed from: private */
    public static final Executor g = new SerialExecutor();
    private String c;
    private SharedPreferences d;
    private HashMap<String, BleDeviceProp> e = new HashMap<>();

    private interface IPropGetter<T> {
        T b(BleDeviceProp bleDeviceProp);
    }

    private interface IPropSetter {
        boolean a(BleDeviceProp bleDeviceProp);
    }

    public interface IPropTraverse {
        boolean a(String str, BleDeviceProp bleDeviceProp);
    }

    public static BleDevicePropCache a() {
        if (f == null) {
            synchronized (BleDevicePropCache.class) {
                if (f == null) {
                    f = new BleDevicePropCache();
                }
            }
        }
        return f;
    }

    private BleDevicePropCache() {
    }

    public void b() {
        b((BleCacheTask) new BleCacheTask() {
            /* access modifiers changed from: package-private */
            public void a() {
                BleDevicePropCache.this.d();
            }
        });
    }

    /* access modifiers changed from: private */
    public void d() {
        String e2 = e();
        if (TextUtils.isEmpty(e2)) {
            synchronized (this.e) {
                this.e.clear();
            }
            this.d = null;
        } else if (!e2.equalsIgnoreCase(this.c)) {
            this.c = e2;
            System.currentTimeMillis();
            this.d = n().getSharedPreferences(this.c, 0);
            Map<String, ?> all = this.d.getAll();
            HashMap hashMap = new HashMap();
            for (Map.Entry next : all.entrySet()) {
                BleDeviceProp fromJson = BleDeviceProp.fromJson((String) next.getValue());
                if (fromJson != null) {
                    hashMap.put(next.getKey(), fromJson);
                }
            }
            synchronized (this.e) {
                this.e.clear();
                this.e.putAll(hashMap);
            }
        }
    }

    public void a(IPropTraverse iPropTraverse) {
        synchronized (this.e) {
            for (Map.Entry next : this.e.entrySet()) {
                if (iPropTraverse.a((String) next.getKey(), (BleDeviceProp) next.getValue())) {
                    break;
                }
            }
        }
    }

    public void a(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("name") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getName())) {
                    return false;
                }
                bleDeviceProp.setName(str2);
                return true;
            }
        });
    }

    public String a(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getName();
            }
        }, "");
    }

    public void b(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("smac") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getSmac())) {
                    return false;
                }
                bleDeviceProp.setSmac(str2);
                return true;
            }
        });
    }

    public String b(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getSmac();
            }
        }, "");
    }

    public void c(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("ownerName") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getOwnerName())) {
                    return false;
                }
                bleDeviceProp.setOwnerName(str2);
                return true;
            }
        });
    }

    public String c(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getOwnerName();
            }
        }, "");
    }

    public void d(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("beaconName") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getBeaconName())) {
                    return false;
                }
                bleDeviceProp.setBeaconName(str2);
                return true;
            }
        }, false);
    }

    public String d(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getBeaconName();
            }
        }, "");
    }

    public void e(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("did") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getDid())) {
                    return false;
                }
                bleDeviceProp.setDid(str2);
                return true;
            }
        });
    }

    public String e(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getDid();
            }
        }, "");
    }

    public void f(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("ownerId") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getOwnerId())) {
                    return false;
                }
                bleDeviceProp.setOwnerId(str2);
                return true;
            }
        });
    }

    public String f(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getOwnerId();
            }
        }, "");
    }

    public void g(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("desc") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getDesc())) {
                    return false;
                }
                bleDeviceProp.setDesc(str2);
                return true;
            }
        });
    }

    public String g(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getDesc();
            }
        }, "");
    }

    public void h(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("model") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getModel())) {
                    return false;
                }
                bleDeviceProp.setModel(str2);
                return true;
            }
        });
    }

    public String h(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getModel();
            }
        }, "");
    }

    public void a(String str, final int i) {
        a(str, (PropSetter) new PropSetter("pid") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (i == bleDeviceProp.getProductId()) {
                    return false;
                }
                bleDeviceProp.setProductId(i);
                return true;
            }
        });
    }

    public int i(String str) {
        return ((Integer) a(str, new IPropGetter<Integer>() {
            /* renamed from: a */
            public Integer b(BleDeviceProp bleDeviceProp) {
                return Integer.valueOf(bleDeviceProp.getProductId());
            }
        }, 0)).intValue();
    }

    public void b(String str, final int i) {
        a(str, (PropSetter) new PropSetter("rssi") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (i == bleDeviceProp.getRssi()) {
                    return false;
                }
                bleDeviceProp.setRssi(i);
                return true;
            }
        }, false);
    }

    public int j(String str) {
        return ((Integer) a(str, new IPropGetter<Integer>() {
            /* renamed from: a */
            public Integer b(BleDeviceProp bleDeviceProp) {
                return Integer.valueOf(bleDeviceProp.getRssi());
            }
        }, -60)).intValue();
    }

    public void c(String str, final int i) {
        a(str, (PropSetter) new PropSetter("permitLevel") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (i == bleDeviceProp.getPermitLevel()) {
                    return false;
                }
                bleDeviceProp.setPermitLevel(i);
                return true;
            }
        });
    }

    public int k(String str) {
        return ((Integer) a(str, new IPropGetter<Integer>() {
            /* renamed from: a */
            public Integer b(BleDeviceProp bleDeviceProp) {
                return Integer.valueOf(bleDeviceProp.getPermitLevel());
            }
        }, 0)).intValue();
    }

    public void d(String str, final int i) {
        a(str, (PropSetter) new PropSetter("boundStatus") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (i == bleDeviceProp.getBoundStatus()) {
                    return false;
                }
                bleDeviceProp.setBoundStatus(i);
                return true;
            }
        });
    }

    public int l(String str) {
        return ((Integer) a(str, new IPropGetter<Integer>() {
            /* renamed from: a */
            public Integer b(BleDeviceProp bleDeviceProp) {
                return Integer.valueOf(bleDeviceProp.getBoundStatus());
            }
        }, 0)).intValue();
    }

    public String m(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getEncryptedToken();
            }
        }, "");
    }

    public void i(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("encryptedToken") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getEncryptedToken())) {
                    return false;
                }
                bleDeviceProp.setEncryptedToken(str2);
                return true;
            }
        });
    }

    public String n(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getEncryptedLtmk();
            }
        }, "");
    }

    public void j(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("encryptedLtmk") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getEncryptedLtmk())) {
                    return false;
                }
                bleDeviceProp.setEncryptedLtmk(str2);
                return true;
            }
        });
    }

    public String o(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getPincode();
            }
        }, "");
    }

    public void k(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("pincode") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getPincode())) {
                    return false;
                }
                bleDeviceProp.setPincode(str2);
                return true;
            }
        });
    }

    public int p(String str) {
        return ((Integer) a(str, new IPropGetter<Integer>() {
            /* renamed from: a */
            public Integer b(BleDeviceProp bleDeviceProp) {
                return Integer.valueOf(bleDeviceProp.getLtmkEncryptType());
            }
        }, 0)).intValue();
    }

    public void e(String str, final int i) {
        a(str, (PropSetter) new PropSetter("ltmkEncryptType") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (i == bleDeviceProp.getLtmkEncryptType()) {
                    return false;
                }
                bleDeviceProp.setLtmkEncryptType(i);
                return true;
            }
        });
    }

    public boolean q(String str) {
        return ((Boolean) a(str, new IPropGetter<Boolean>() {
            /* renamed from: a */
            public Boolean b(BleDeviceProp bleDeviceProp) {
                return Boolean.valueOf(bleDeviceProp.isShowPincode());
            }
        }, true)).booleanValue();
    }

    public void a(String str, final boolean z) {
        a(str, (PropSetter) new PropSetter("setShowPincode") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (z == bleDeviceProp.isShowPincode()) {
                    return false;
                }
                bleDeviceProp.setShowPincode(z);
                return true;
            }
        });
    }

    public String r(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getEncryptedSession();
            }
        }, "");
    }

    public void l(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("encryptedSession") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getEncryptedSession())) {
                    return false;
                }
                bleDeviceProp.setEncryptedSession(str2);
                return true;
            }
        }, false);
    }

    public String s(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getEncryptedKeyId();
            }
        }, "");
    }

    public void m(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("encryptedKeyId") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getEncryptedKeyId())) {
                    return false;
                }
                bleDeviceProp.setEncryptedKeyId(str2);
                return true;
            }
        });
    }

    public String t(String str) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getEncryptedMeshBindInfo();
            }
        }, "");
    }

    public void n(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("encryptedMeshBindInfo") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str2.equals(bleDeviceProp.getEncryptedMeshBindInfo())) {
                    return false;
                }
                bleDeviceProp.setEncryptedMeshBindInfo(str2);
                return true;
            }
        });
    }

    public byte[] u(String str) {
        return (byte[]) a(str, new IPropGetter<byte[]>() {
            /* renamed from: a */
            public byte[] b(BleDeviceProp bleDeviceProp) {
                String scanRecord = bleDeviceProp.getScanRecord();
                return TextUtils.isEmpty(scanRecord) ? ByteUtils.b : ByteUtils.a(scanRecord);
            }
        }, ByteUtils.b);
    }

    public void a(String str, final byte[] bArr) {
        if (!ByteUtils.e(bArr)) {
            a(str, (PropSetter) new PropSetter("scanRecord") {
                public boolean a(BleDeviceProp bleDeviceProp) {
                    String d = ByteUtils.d(bArr);
                    if (d.equalsIgnoreCase(bleDeviceProp.getScanRecord())) {
                        return false;
                    }
                    bleDeviceProp.setScanRecord(d);
                    return true;
                }
            }, false);
        }
    }

    public int v(String str) {
        return ((Integer) a(str, new IPropGetter<Integer>() {
            /* renamed from: a */
            public Integer b(BleDeviceProp bleDeviceProp) {
                return Integer.valueOf(bleDeviceProp.getType());
            }
        }, 0)).intValue();
    }

    public void f(String str, final int i) {
        a(str, (PropSetter) new PropSetter("type") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (i == bleDeviceProp.getType()) {
                    return false;
                }
                bleDeviceProp.setType(i);
                return true;
            }
        });
    }

    public BleGattProfile w(String str) {
        return (BleGattProfile) a(str, new IPropGetter<BleGattProfile>() {
            /* renamed from: a */
            public BleGattProfile b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getProfile();
            }
        }, (Object) null);
    }

    public void a(String str, final BleGattProfile bleGattProfile) {
        a(str, (PropSetter) new PropSetter("GattProfile") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                bleDeviceProp.setProfile(bleGattProfile);
                return true;
            }
        }, false);
    }

    public int a(String str, final String str2, final int i) {
        return ((Integer) a(str, new IPropGetter<Integer>() {
            /* renamed from: a */
            public Integer b(BleDeviceProp bleDeviceProp) {
                return Integer.valueOf(bleDeviceProp.getExtra(str2, i));
            }
        }, Integer.valueOf(i))).intValue();
    }

    public void b(String str, final String str2, final int i) {
        a(str, (PropSetter) new PropSetter("int extra") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (i == bleDeviceProp.getExtra(str2, 0)) {
                    return false;
                }
                bleDeviceProp.setExtra(str2, i);
                return true;
            }
        });
    }

    public boolean a(String str, final String str2, final boolean z) {
        return ((Boolean) a(str, new IPropGetter<Boolean>() {
            /* renamed from: a */
            public Boolean b(BleDeviceProp bleDeviceProp) {
                return Boolean.valueOf(bleDeviceProp.getExtra(str2, z));
            }
        }, Boolean.valueOf(z))).booleanValue();
    }

    public void b(String str, final String str2, final boolean z) {
        a(str, (PropSetter) new PropSetter("boolean extra") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (z == bleDeviceProp.getExtra(str2, false)) {
                    return false;
                }
                bleDeviceProp.setExtra(str2, z);
                return true;
            }
        });
    }

    public String o(String str, final String str2) {
        return (String) a(str, new IPropGetter<String>() {
            /* renamed from: a */
            public String b(BleDeviceProp bleDeviceProp) {
                return bleDeviceProp.getExtra(str2, "");
            }
        }, "");
    }

    public void a(String str, final String str2, final String str3) {
        a(str, (PropSetter) new PropSetter("String extra") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                if (str3.equals(bleDeviceProp.getExtra(str2, ""))) {
                    return false;
                }
                bleDeviceProp.setExtra(str2, str3);
                return true;
            }
        });
    }

    public void p(String str, final String str2) {
        a(str, (PropSetter) new PropSetter("remove") {
            public boolean a(BleDeviceProp bleDeviceProp) {
                bleDeviceProp.removeExtra(str2);
                return true;
            }
        });
    }

    private abstract class PropSetter implements IPropSetter {

        /* renamed from: a  reason: collision with root package name */
        private String f14256a;

        PropSetter(String str) {
            this.f14256a = str;
        }

        public String toString() {
            return "PropSetter{name='" + this.f14256a + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    private <T> T a(String str, IPropGetter<T> iPropGetter, T t) {
        if (TextUtils.isEmpty(str)) {
            return t;
        }
        synchronized (this.e) {
            BleDeviceProp bleDeviceProp = this.e.get(str);
            if (bleDeviceProp == null) {
                return t;
            }
            T b = iPropGetter.b(bleDeviceProp);
            return b;
        }
    }

    private void a(String str, PropSetter propSetter) {
        a(str, propSetter, true);
    }

    private void a(String str, PropSetter propSetter, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.e) {
                BleDeviceProp bleDeviceProp = this.e.get(str);
                if (bleDeviceProp == null) {
                    bleDeviceProp = new BleDeviceProp();
                    this.e.put(str, bleDeviceProp);
                }
                if (propSetter.a(bleDeviceProp) && z) {
                    a(this.d, str, bleDeviceProp);
                }
            }
        }
    }

    private void a(final SharedPreferences sharedPreferences, final String str, final BleDeviceProp bleDeviceProp) {
        a((BleCacheTask) new BleCacheTask() {
            /* access modifiers changed from: package-private */
            public void a() {
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putString(str, bleDeviceProp.toJson()).apply();
                }
            }
        });
    }

    private static class SerialExecutor implements Executor {

        /* renamed from: a  reason: collision with root package name */
        final ArrayDeque<Runnable> f14257a;
        Runnable b;

        private SerialExecutor() {
            this.f14257a = new ArrayDeque<>();
        }

        public synchronized void execute(final Runnable runnable) {
            this.f14257a.offer(new Runnable() {
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        SerialExecutor.this.a();
                    }
                }
            });
            if (this.b == null) {
                a();
            }
        }

        /* access modifiers changed from: protected */
        public synchronized void a() {
            Runnable poll = this.f14257a.poll();
            this.b = poll;
            if (poll != null) {
                AsyncTask.THREAD_POOL_EXECUTOR.execute(this.b);
            }
        }
    }

    private abstract class BleCacheTask extends AsyncTask<Void, Void, Void> {
        /* access modifiers changed from: package-private */
        public abstract void a();

        private BleCacheTask() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            a();
            return null;
        }
    }

    private void a(final BleCacheTask bleCacheTask) {
        c(new Runnable() {
            public void run() {
                bleCacheTask.executeOnExecutor(BleDevicePropCache.g, new Void[0]);
            }
        });
    }

    private void b(final BleCacheTask bleCacheTask) {
        c(new Runnable() {
            public void run() {
                bleCacheTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        });
    }

    private static String e() {
        String m = AccountManager.a().m();
        if (TextUtils.isEmpty(m) || "0".equals(m)) {
            return null;
        }
        ServerBean d2 = GlobalDynamicSettingManager.a().d();
        Object[] objArr = new Object[3];
        objArr[0] = f14198a;
        objArr[1] = m;
        objArr[2] = d2 == null ? "" : d2.f1530a;
        return String.format("%s.%s.%s", objArr);
    }
}
