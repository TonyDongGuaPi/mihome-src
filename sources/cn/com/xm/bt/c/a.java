package cn.com.xm.bt.c;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import cn.com.xm.bt.c.a;
import cn.com.xm.bt.c.d;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SuppressLint({"NewApi"})
abstract class a extends BluetoothGattCallback implements d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f559a = "a";
    private static final String b = (f559a + "*");
    private static final String c = f559a;
    private static final ExecutorService q = Executors.newSingleThreadExecutor();
    private static volatile boolean r = false;
    private final HandlerThread d;
    private final Handler e;
    private final ReentrantLock f = new ReentrantLock();
    private final Condition g = this.f.newCondition();
    private boolean h = false;
    private int i = 65534;
    private final Context j;
    private final BluetoothDevice k;
    private BluetoothGatt l = null;
    /* access modifiers changed from: private */
    public d.c m = d.c.DISCONNECTED;
    private final Map<BluetoothGattCharacteristic, d.b> n = new HashMap();
    private final Runnable o = new Runnable() {
        public void run() {
            d.c unused = a.this.m = d.c.DISCONNECTED;
            cn.com.xm.bt.a.a.a("AbsGattCallback", "=================================================");
            cn.com.xm.bt.a.a.a("AbsGattCallback", "=============== CONNECT TIMEOUT ===============");
            cn.com.xm.bt.a.a.a("AbsGattCallback", "=================================================");
            a.this.p.execute(new Runnable() {
                public final void run() {
                    a.AnonymousClass1.this.a();
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a() {
            a.this.g();
        }
    };
    /* access modifiers changed from: private */
    public final ExecutorService p = Executors.newSingleThreadExecutor($$Lambda$a$UolvyYtySH4R5pbM_vEP7aiVIzE.INSTANCE);

    private boolean a(BluetoothGatt bluetoothGatt) {
        return bluetoothGatt != null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void d(BluetoothGattDescriptor bluetoothGattDescriptor);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void c(BluetoothGattDescriptor bluetoothGattDescriptor);

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract void g(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract void i(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    /* access modifiers changed from: protected */
    public abstract void e();

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public abstract void h(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    /* access modifiers changed from: protected */
    public abstract void f();

    /* access modifiers changed from: protected */
    public abstract void g();

    /* access modifiers changed from: protected */
    public abstract void h();

    /* access modifiers changed from: private */
    public static /* synthetic */ Thread a(Runnable runnable) {
        cn.com.xm.bt.a.a.a();
        cn.com.xm.bt.a.a.d("********************************");
        cn.com.xm.bt.a.a.d("NEW THREAD: " + c);
        cn.com.xm.bt.a.a.d("********************************");
        Thread thread = new Thread(runnable, c);
        thread.setUncaughtExceptionHandler($$Lambda$a$8PWK9x7N1XabFqDufo0OmzilzO0.INSTANCE);
        return thread;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void b(Thread thread, Throwable th) {
        th.printStackTrace();
        cn.com.xm.bt.a.a.a("AbsGattCallback", "ExecutorService uncaughtException:" + th.getMessage());
    }

    public final void a() {
        cn.com.xm.bt.a.a.a();
        this.p.execute(new Runnable() {
            public final void run() {
                a.this.j();
            }
        });
    }

    public final void b() {
        cn.com.xm.bt.a.a.a();
        this.p.execute(new Runnable() {
            public final void run() {
                a.this.m();
            }
        });
    }

    public void c() {
        try {
            BluetoothGatt.class.getDeclaredMethod("refresh", (Class[]) null).invoke(this.l, (Object[]) null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final BluetoothGattService a(UUID uuid) {
        cn.com.xm.bt.a.a.a();
        cn.com.xm.bt.a.a.a((Object) this.l);
        if (this.l == null) {
            return null;
        }
        return this.l.getService(uuid);
    }

    public final int a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return a(this.l, bluetoothGattCharacteristic);
    }

    public final int a(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        return a(this.l, bluetoothGattCharacteristic, bArr);
    }

    public final boolean a(BluetoothGattCharacteristic bluetoothGattCharacteristic, d.b bVar) {
        cn.com.xm.bt.a.a.a();
        if (bVar != null) {
            this.n.put(bluetoothGattCharacteristic, bVar);
        }
        if (this.l == null) {
            return false;
        }
        boolean characteristicNotification = this.l.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        cn.com.xm.bt.a.a.b(characteristicNotification);
        if (!characteristicNotification) {
            return false;
        }
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(c.f562a);
        cn.com.xm.bt.a.a.a((Object) descriptor);
        if (descriptor != null) {
            if ((bluetoothGattCharacteristic.getProperties() & 16) > 0) {
                if (a(this.l, descriptor, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE) == 0) {
                    return true;
                }
                return false;
            } else if ((bluetoothGattCharacteristic.getProperties() & 32) <= 0 || a(this.l, descriptor, BluetoothGattDescriptor.ENABLE_INDICATION_VALUE) != 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public final boolean b(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        cn.com.xm.bt.a.a.a();
        this.n.remove(bluetoothGattCharacteristic);
        if (this.m != d.c.CONNECTED) {
            cn.com.xm.bt.a.a.a("m_State != STATE_CONNECTED");
            if (this.l != null) {
                cn.com.xm.bt.a.a.a("m_Gatt != null, trying to unregister locally...");
                boolean characteristicNotification = this.l.setCharacteristicNotification(bluetoothGattCharacteristic, false);
                cn.com.xm.bt.a.a.b(characteristicNotification);
                if (!characteristicNotification) {
                    return false;
                }
            }
            return true;
        }
        cn.com.xm.bt.a.a.a((Object) this.l);
        boolean characteristicNotification2 = this.l.setCharacteristicNotification(bluetoothGattCharacteristic, false);
        cn.com.xm.bt.a.a.b(characteristicNotification2);
        if (!characteristicNotification2) {
            return false;
        }
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(c.f562a);
        cn.com.xm.bt.a.a.a((Object) descriptor);
        if (descriptor == null || a(this.l, descriptor, BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE) != 0) {
            return false;
        }
        return true;
    }

    public final BluetoothDevice d() {
        return this.k;
    }

    public final void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.append("onConnectionStateChange Gatt:");
        sb.append(bluetoothGatt != null ? Integer.toHexString(bluetoothGatt.hashCode()) : "null");
        sb.append(",device:");
        sb.append(bluetoothGatt != null ? bluetoothGatt.getDevice() : "null");
        sb.append(",status:");
        sb.append(i2);
        sb.append(",newState:");
        sb.append(i3);
        cn.com.xm.bt.a.a.a("AbsGattCallback", sb.toString());
        this.e.removeCallbacks(this.o);
        cn.com.xm.bt.a.a.a();
        cn.com.xm.bt.a.a.b(i2 == 0);
        if (this.l != bluetoothGatt) {
            if (this.l != null) {
                cn.com.xm.bt.a.a.a("AbsGattCallback", "m_Gatt.getDevice(): " + this.l.getDevice() + ",old gatt:" + c.a(this.l));
            }
            if (bluetoothGatt != null) {
                cn.com.xm.bt.a.a.a("AbsGattCallback", "  gatt.getDevice(): " + bluetoothGatt.getDevice() + ",new gatt:" + c.a(bluetoothGatt));
            }
            this.l = bluetoothGatt;
        }
        int a2 = c.a(this.l);
        if (i2 != 0) {
            this.m = d.c.DISCONNECTED;
            cn.com.xm.bt.a.a.c("=================================================");
            cn.com.xm.bt.a.a.c("=============== CONNECTION FAILED ===============");
            cn.com.xm.bt.a.a.c("=================================================");
            cn.com.xm.bt.a.a.a("AbsGattCallback", "clientIf is " + a2);
            if (a2 == 0) {
                o();
            } else {
                this.p.execute(new Runnable() {
                    public final void run() {
                        a.this.g();
                    }
                });
            }
        } else if (i3 == 0) {
            this.m = d.c.DISCONNECTED;
            cn.com.xm.bt.a.a.d("=================================================");
            cn.com.xm.bt.a.a.d("================== DISCONNECTED =================");
            cn.com.xm.bt.a.a.d("=================================================");
            this.p.execute(new Runnable() {
                public final void run() {
                    a.this.f();
                }
            });
        } else if (i3 != 2) {
            cn.com.xm.bt.a.a.e(">>> UNEXPECTED <<<");
        } else {
            this.m = d.c.CONNECTED;
            cn.com.xm.bt.a.a.d("=================================================");
            cn.com.xm.bt.a.a.d("=================== CONNECTED ===================");
            cn.com.xm.bt.a.a.d("=================================================");
            cn.com.xm.bt.a.a.a("AbsGattCallback", "******Gatt connected:<" + Integer.toHexString(this.l.hashCode()) + ">******");
            cn.com.xm.bt.a.a.a("AbsGattCallback", "******Gatt clientIf=" + a2 + "******");
            this.p.execute(new Runnable() {
                public final void run() {
                    a.this.e();
                }
            });
        }
    }

    public final void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("onServicesDiscovered Gatt:");
        sb.append(bluetoothGatt != null ? Integer.toHexString(bluetoothGatt.hashCode()) : "null");
        sb.append(",device:");
        sb.append(bluetoothGatt != null ? bluetoothGatt.getDevice() : "null");
        sb.append(",status:");
        sb.append(i2);
        cn.com.xm.bt.a.a.a("AbsGattCallback", sb.toString());
        cn.com.xm.bt.a.a.a();
        boolean z = false;
        cn.com.xm.bt.a.a.b(i2 == 0);
        if (this.l == bluetoothGatt) {
            z = true;
        }
        cn.com.xm.bt.a.a.b(z);
        if (i2 == 0) {
            this.p.execute(new Runnable() {
                public final void run() {
                    a.this.h();
                }
            });
        } else {
            k();
        }
    }

    public final void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        cn.com.xm.bt.a.a.a();
        boolean z = true;
        cn.com.xm.bt.a.a.b(i2 == 0);
        if (this.l != bluetoothGatt) {
            z = false;
        }
        cn.com.xm.bt.a.a.b(z);
        cn.com.xm.bt.a.a.a("Characteristic Read: " + c.a(bluetoothGattCharacteristic.getValue()));
        this.f.lock();
        try {
            this.i = i2;
            cn.com.xm.bt.a.a.b("NOTIFY: onCharacteristicRead(): " + this.i);
            this.h = false;
            this.g.signal();
            if (i2 == 0) {
                this.p.execute(new Runnable(bluetoothGattCharacteristic) {
                    private final /* synthetic */ BluetoothGattCharacteristic f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        a.this.i(this.f$1);
                    }
                });
            }
        } finally {
            this.f.unlock();
        }
    }

    public final void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        cn.com.xm.bt.a.a.a();
        boolean z = true;
        cn.com.xm.bt.a.a.b(i2 == 0);
        if (this.l != bluetoothGatt) {
            z = false;
        }
        cn.com.xm.bt.a.a.b(z);
        cn.com.xm.bt.a.a.a("Characteristic Write: " + c.a(bluetoothGattCharacteristic.getValue()));
        this.f.lock();
        try {
            this.i = i2;
            cn.com.xm.bt.a.a.b("NOTIFY: onCharacteristicWrite(): " + this.i);
            this.h = false;
            this.g.signal();
            if (i2 == 0) {
                this.p.execute(new Runnable(bluetoothGattCharacteristic) {
                    private final /* synthetic */ BluetoothGattCharacteristic f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        a.this.h(this.f$1);
                    }
                });
            }
        } finally {
            this.f.unlock();
        }
    }

    public final void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        cn.com.xm.bt.a.a.a();
        cn.com.xm.bt.a.a.b(this.l == bluetoothGatt);
        byte[] value = bluetoothGattCharacteristic.getValue();
        cn.com.xm.bt.a.a.a("Characteristic Changed: " + c.a(value));
        d.b bVar = this.n.get(bluetoothGattCharacteristic);
        cn.com.xm.bt.a.a.a((Object) bVar);
        this.p.execute(new Runnable(value) {
            private final /* synthetic */ byte[] f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                d.b.this.notify(this.f$1);
            }
        });
        this.p.execute(new Runnable(bluetoothGattCharacteristic) {
            private final /* synthetic */ BluetoothGattCharacteristic f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                a.this.g(this.f$1);
            }
        });
    }

    public final void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
        cn.com.xm.bt.a.a.a();
        boolean z = true;
        cn.com.xm.bt.a.a.b(i2 == 0);
        if (this.l != bluetoothGatt) {
            z = false;
        }
        cn.com.xm.bt.a.a.b(z);
        cn.com.xm.bt.a.a.a("Descriptor Read: " + c.a(bluetoothGattDescriptor.getValue()));
        this.f.lock();
        try {
            this.i = i2;
            cn.com.xm.bt.a.a.b("NOTIFY: onDescriptorRead(): " + this.i);
            this.h = false;
            this.g.signal();
            if (i2 == 0) {
                this.p.execute(new Runnable(bluetoothGattDescriptor) {
                    private final /* synthetic */ BluetoothGattDescriptor f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        a.this.d(this.f$1);
                    }
                });
            }
        } finally {
            this.f.unlock();
        }
    }

    public final void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
        cn.com.xm.bt.a.a.a();
        boolean z = true;
        cn.com.xm.bt.a.a.b(i2 == 0);
        if (this.l != bluetoothGatt) {
            z = false;
        }
        cn.com.xm.bt.a.a.b(z);
        cn.com.xm.bt.a.a.a("Descriptor Write: " + c.a(bluetoothGattDescriptor.getValue()));
        this.f.lock();
        try {
            this.i = i2;
            cn.com.xm.bt.a.a.b("NOTIFY: onDescriptorWrite(): " + this.i);
            this.h = false;
            this.g.signal();
            if (i2 == 0) {
                this.p.execute(new Runnable(bluetoothGattDescriptor) {
                    private final /* synthetic */ BluetoothGattDescriptor f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        a.this.c(this.f$1);
                    }
                });
            }
        } finally {
            this.f.unlock();
        }
    }

    public final void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i2) {
        cn.com.xm.bt.a.a.a();
        boolean z = false;
        cn.com.xm.bt.a.a.b(i2 == 0);
        if (this.l == bluetoothGatt) {
            z = true;
        }
        cn.com.xm.bt.a.a.b(z);
        cn.com.xm.bt.a.a.e(">>> UNEXPECTED <<<");
    }

    a(Context context, BluetoothDevice bluetoothDevice) {
        cn.com.xm.bt.a.a.a();
        this.d = new HandlerThread(b);
        cn.com.xm.bt.a.a.d("********************************");
        cn.com.xm.bt.a.a.d("NEW THREAD: " + b);
        cn.com.xm.bt.a.a.d("********************************");
        this.d.setUncaughtExceptionHandler($$Lambda$a$sDVhb39Rm3TFsXXFZjGNVcT4kA.INSTANCE);
        this.d.start();
        this.e = new Handler(this.d.getLooper());
        this.j = context;
        this.k = bluetoothDevice;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Thread thread, Throwable th) {
        th.printStackTrace();
        cn.com.xm.bt.a.a.a("AbsGattCallback", "c_GattQueueThread uncaughtException:" + th.getMessage());
    }

    /* access modifiers changed from: package-private */
    public final BluetoothGatt i() {
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public void j() {
        cn.com.xm.bt.a.a.a();
        cn.com.xm.bt.a.a.a("m_State: " + this.m);
        if (this.m != d.c.DISCONNECTED) {
            cn.com.xm.bt.a.a.e("connectInternal Illegal state: m_State = " + this.m);
        }
        this.m = d.c.CONNECTING;
        if (this.l != null) {
            cn.com.xm.bt.a.a.a("AbsGattCallback", "connect back...");
            this.l.close();
        }
        this.e.postDelayed(this.o, (long) 45000);
        this.l = this.k.connectGatt(this.j, false, this);
        if (this.l == null) {
            cn.com.xm.bt.a.a.a("AbsGattCallback", "connectGatt<" + this.k.getAddress() + "> return null, now disable bluetooth!!!");
            this.e.removeCallbacks(this.o);
            o();
        } else {
            cn.com.xm.bt.a.a.a("AbsGattCallback", "******Gatt create:<" + Integer.toHexString(this.l.hashCode()) + ">******");
        }
        cn.com.xm.bt.a.a.a((Object) this.l);
    }

    /* access modifiers changed from: package-private */
    public void k() {
        cn.com.xm.bt.a.a.a();
        cn.com.xm.bt.a.a.a("m_State: " + this.m);
        if (this.m == d.c.CONNECTING || this.m == d.c.CONNECTED) {
            cn.com.xm.bt.a.a.a((Object) this.l);
            this.m = d.c.DISCONNECTING;
            this.l.disconnect();
            return;
        }
        cn.com.xm.bt.a.a.e("Illegal state: m_State != STATE_CONNECTING && m_State != STATE_CONNECTED");
    }

    /* access modifiers changed from: private */
    public void m() {
        b(false);
        if (this.l != null) {
            cn.com.xm.bt.a.a.a("AbsGattCallback", "******Gatt destory:<" + Integer.toHexString(this.l.hashCode()) + ">******");
            cn.com.xm.bt.a.a.a("AbsGattCallback", "******Gatt clientIf=" + c.a(this.l) + "******");
            this.l.disconnect();
            this.l.close();
        }
        this.e.removeCallbacksAndMessages((Object) null);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void l() {
        cn.com.xm.bt.a.a.a();
        cn.com.xm.bt.a.a.a("m_State: " + this.m);
        if (this.m != d.c.DISCONNECTED) {
            cn.com.xm.bt.a.a.a("AbsGattCallback", "cleanupInternal Illegal state: m_State = " + this.m);
            return;
        }
        this.e.removeCallbacksAndMessages((Object) null);
        this.n.clear();
        this.f.lock();
        try {
            this.h = false;
            this.i = 65534;
            cn.com.xm.bt.a.a.b("RELEASE: c_condGattOperationComplete");
            this.g.signalAll();
            this.f.unlock();
            if (n()) {
                c();
                a(false);
            }
            cn.com.xm.bt.a.a.a("AbsGattCallback", "******Gatt close:<" + Integer.toHexString(this.l.hashCode()) + ">******");
            cn.com.xm.bt.a.a.a("AbsGattCallback", "******Gatt clientIf=" + c.a(this.l) + "******");
            this.l.close();
        } catch (Throwable th) {
            this.f.unlock();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r5 = r4.f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized int a(android.bluetooth.BluetoothGatt r5, android.bluetooth.BluetoothGattCharacteristic r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            cn.com.xm.bt.a.a.a()     // Catch:{ all -> 0x00c8 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c8 }
            r0.<init>()     // Catch:{ all -> 0x00c8 }
            java.lang.String r1 = "gatt="
            r0.append(r1)     // Catch:{ all -> 0x00c8 }
            r0.append(r5)     // Catch:{ all -> 0x00c8 }
            java.lang.String r1 = ", characteristic="
            r0.append(r1)     // Catch:{ all -> 0x00c8 }
            r0.append(r6)     // Catch:{ all -> 0x00c8 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00c8 }
            cn.com.xm.bt.a.a.a((java.lang.String) r0)     // Catch:{ all -> 0x00c8 }
            cn.com.xm.bt.a.a.a((java.lang.Object) r5)     // Catch:{ all -> 0x00c8 }
            cn.com.xm.bt.a.a.a((java.lang.Object) r6)     // Catch:{ all -> 0x00c8 }
            cn.com.xm.bt.c.-$$Lambda$a$xmZDz7g5e80ep1WzuJ-eyEnnias r0 = new cn.com.xm.bt.c.-$$Lambda$a$xmZDz7g5e80ep1WzuJ-eyEnnias     // Catch:{ all -> 0x00c8 }
            r0.<init>(r5, r6)     // Catch:{ all -> 0x00c8 }
            java.util.concurrent.locks.ReentrantLock r1 = r4.f     // Catch:{ all -> 0x00c8 }
            r1.lock()     // Catch:{ all -> 0x00c8 }
            r1 = 1
            r4.h = r1     // Catch:{ InterruptedException -> 0x00b7 }
            android.os.Handler r1 = r4.e     // Catch:{ InterruptedException -> 0x00b7 }
            r1.post(r0)     // Catch:{ InterruptedException -> 0x00b7 }
        L_0x0038:
            boolean r0 = r4.h     // Catch:{ InterruptedException -> 0x00b7 }
            if (r0 == 0) goto L_0x00af
            boolean r0 = r4.a((android.bluetooth.BluetoothGatt) r5)     // Catch:{ InterruptedException -> 0x00b7 }
            if (r0 == 0) goto L_0x00aa
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00b7 }
            r0.<init>()     // Catch:{ InterruptedException -> 0x00b7 }
            java.lang.String r1 = "WAIT: synchronizedReadCharacteristic(): "
            r0.append(r1)     // Catch:{ InterruptedException -> 0x00b7 }
            java.util.UUID r1 = r6.getUuid()     // Catch:{ InterruptedException -> 0x00b7 }
            java.lang.String r1 = cn.com.xm.bt.c.c.a((java.util.UUID) r1)     // Catch:{ InterruptedException -> 0x00b7 }
            r0.append(r1)     // Catch:{ InterruptedException -> 0x00b7 }
            java.lang.String r0 = r0.toString()     // Catch:{ InterruptedException -> 0x00b7 }
            cn.com.xm.bt.a.a.b((java.lang.String) r0)     // Catch:{ InterruptedException -> 0x00b7 }
            java.util.concurrent.locks.Condition r0 = r4.g     // Catch:{ InterruptedException -> 0x00b7 }
            r1 = 30
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x00b7 }
            r0.await(r1, r3)     // Catch:{ InterruptedException -> 0x00b7 }
            boolean r0 = r4.h     // Catch:{ InterruptedException -> 0x00b7 }
            if (r0 == 0) goto L_0x008d
            java.lang.String r5 = "GATT OPERATION TIMEOUT"
            cn.com.xm.bt.a.a.e(r5)     // Catch:{ InterruptedException -> 0x00b7 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00b7 }
            r5.<init>()     // Catch:{ InterruptedException -> 0x00b7 }
            java.lang.String r0 = "CONTINUE: synchronizedReadCharacteristic(): "
            r5.append(r0)     // Catch:{ InterruptedException -> 0x00b7 }
            java.util.UUID r6 = r6.getUuid()     // Catch:{ InterruptedException -> 0x00b7 }
            java.lang.String r6 = cn.com.xm.bt.c.c.a((java.util.UUID) r6)     // Catch:{ InterruptedException -> 0x00b7 }
            r5.append(r6)     // Catch:{ InterruptedException -> 0x00b7 }
            java.lang.String r5 = r5.toString()     // Catch:{ InterruptedException -> 0x00b7 }
            cn.com.xm.bt.a.a.b((java.lang.String) r5)     // Catch:{ InterruptedException -> 0x00b7 }
            goto L_0x00af
        L_0x008d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00b7 }
            r0.<init>()     // Catch:{ InterruptedException -> 0x00b7 }
            java.lang.String r1 = "CONTINUE: synchronizedReadCharacteristic(): "
            r0.append(r1)     // Catch:{ InterruptedException -> 0x00b7 }
            java.util.UUID r1 = r6.getUuid()     // Catch:{ InterruptedException -> 0x00b7 }
            java.lang.String r1 = cn.com.xm.bt.c.c.a((java.util.UUID) r1)     // Catch:{ InterruptedException -> 0x00b7 }
            r0.append(r1)     // Catch:{ InterruptedException -> 0x00b7 }
            java.lang.String r0 = r0.toString()     // Catch:{ InterruptedException -> 0x00b7 }
            cn.com.xm.bt.a.a.b((java.lang.String) r0)     // Catch:{ InterruptedException -> 0x00b7 }
            goto L_0x0038
        L_0x00aa:
            java.lang.String r5 = "NO CONNECTION"
            cn.com.xm.bt.a.a.e(r5)     // Catch:{ InterruptedException -> 0x00b7 }
        L_0x00af:
            java.util.concurrent.locks.ReentrantLock r5 = r4.f     // Catch:{ all -> 0x00c8 }
        L_0x00b1:
            r5.unlock()     // Catch:{ all -> 0x00c8 }
            goto L_0x00be
        L_0x00b5:
            r5 = move-exception
            goto L_0x00c2
        L_0x00b7:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ all -> 0x00b5 }
            java.util.concurrent.locks.ReentrantLock r5 = r4.f     // Catch:{ all -> 0x00c8 }
            goto L_0x00b1
        L_0x00be:
            int r5 = r4.i     // Catch:{ all -> 0x00c8 }
            monitor-exit(r4)
            return r5
        L_0x00c2:
            java.util.concurrent.locks.ReentrantLock r6 = r4.f     // Catch:{ all -> 0x00c8 }
            r6.unlock()     // Catch:{ all -> 0x00c8 }
            throw r5     // Catch:{ all -> 0x00c8 }
        L_0x00c8:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.xm.bt.c.a.a(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic):int");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        cn.com.xm.bt.a.a.a();
        cn.com.xm.bt.a.a.a((Thread) this.d);
        cn.com.xm.bt.a.a.a((Object) bluetoothGatt);
        if (bluetoothGatt == null) {
            this.f.lock();
            try {
                this.i = HomeRoomAddNewUserDeviceAdapter.f18333a;
                cn.com.xm.bt.a.a.b("NOTIFY: synchronizedReadCharacteristic(): " + this.i);
                this.h = false;
                this.g.signal();
            } finally {
                this.f.unlock();
            }
        } else {
            boolean readCharacteristic = bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
            cn.com.xm.bt.a.a.b(readCharacteristic);
            if (!readCharacteristic) {
                this.f.lock();
                try {
                    this.i = HomeRoomAddNewUserDeviceAdapter.b;
                    cn.com.xm.bt.a.a.b("NOTIFY: synchronizedReadCharacteristic(): " + this.i);
                    this.h = false;
                    this.g.signal();
                } finally {
                    this.f.unlock();
                }
            }
        }
    }

    private synchronized int a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        ReentrantLock reentrantLock;
        cn.com.xm.bt.a.a.a();
        cn.com.xm.bt.a.a.a("gatt=" + bluetoothGatt + ", characteristic=" + bluetoothGattCharacteristic + ", value=" + Arrays.toString(bArr));
        cn.com.xm.bt.a.a.a((Object) bluetoothGatt);
        cn.com.xm.bt.a.a.a((Object) bluetoothGattCharacteristic);
        cn.com.xm.bt.a.a.a((Object) bArr);
        $$Lambda$a$yGzLAkt_zvXaXo296e_j_zrX1u0 r0 = new Runnable(bluetoothGatt, bluetoothGattCharacteristic, bArr) {
            private final /* synthetic */ BluetoothGatt f$1;
            private final /* synthetic */ BluetoothGattCharacteristic f$2;
            private final /* synthetic */ byte[] f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                a.this.b(this.f$1, this.f$2, this.f$3);
            }
        };
        this.f.lock();
        try {
            this.h = true;
            this.e.post(r0);
            while (true) {
                if (this.h) {
                    if (!a(bluetoothGatt)) {
                        cn.com.xm.bt.a.a.e("NO CONNECTION");
                        break;
                    }
                    cn.com.xm.bt.a.a.b("WAIT: synchronizedWriteCharacteristic(): " + c.a(bluetoothGattCharacteristic.getUuid()));
                    this.g.await(30, TimeUnit.SECONDS);
                    if (this.h) {
                        cn.com.xm.bt.a.a.e("GATT OPERATION TIMEOUT");
                        cn.com.xm.bt.a.a.b("CONTINUE: synchronizedWriteCharacteristic(): " + c.a(bluetoothGattCharacteristic.getUuid()));
                        break;
                    }
                    cn.com.xm.bt.a.a.b("CONTINUE: synchronizedWriteCharacteristic(): " + c.a(bluetoothGattCharacteristic.getUuid()));
                }
            }
            reentrantLock = this.f;
            break;
        } catch (InterruptedException e2) {
            try {
                e2.printStackTrace();
                reentrantLock = this.f;
            } catch (Throwable th) {
                this.f.unlock();
                throw th;
            }
        }
        reentrantLock.unlock();
        return this.i;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        cn.com.xm.bt.a.a.a();
        cn.com.xm.bt.a.a.a((Thread) this.d);
        cn.com.xm.bt.a.a.a((Object) bluetoothGatt);
        if (bluetoothGatt == null) {
            this.f.lock();
            try {
                this.i = HomeRoomAddNewUserDeviceAdapter.f18333a;
                cn.com.xm.bt.a.a.b("NOTIFY: synchronizedWriteCharacteristic(): " + this.i);
                this.h = false;
                this.g.signal();
            } finally {
                this.f.unlock();
            }
        } else {
            bluetoothGattCharacteristic.setValue(bArr);
            boolean writeCharacteristic = bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
            cn.com.xm.bt.a.a.b(writeCharacteristic);
            if (!writeCharacteristic) {
                this.f.lock();
                try {
                    this.i = HomeRoomAddNewUserDeviceAdapter.b;
                    cn.com.xm.bt.a.a.b("NOTIFY: synchronizedWriteCharacteristic(): " + this.i);
                    this.h = false;
                    this.g.signal();
                } finally {
                    this.f.unlock();
                }
            }
        }
    }

    private synchronized int a(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        ReentrantLock reentrantLock;
        cn.com.xm.bt.a.a.a();
        cn.com.xm.bt.a.a.a("gatt=" + bluetoothGatt + ", characteristic=" + bluetoothGattDescriptor + ", value=" + Arrays.toString(bArr));
        cn.com.xm.bt.a.a.a((Object) bluetoothGatt);
        cn.com.xm.bt.a.a.a((Object) bluetoothGattDescriptor);
        cn.com.xm.bt.a.a.a((Object) bArr);
        $$Lambda$a$KfT0HmYpN9d9cEWMYzJzmldbx9s r0 = new Runnable(bluetoothGatt, bluetoothGattDescriptor, bArr) {
            private final /* synthetic */ BluetoothGatt f$1;
            private final /* synthetic */ BluetoothGattDescriptor f$2;
            private final /* synthetic */ byte[] f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                a.this.b(this.f$1, this.f$2, this.f$3);
            }
        };
        this.f.lock();
        try {
            this.h = true;
            this.e.post(r0);
            while (true) {
                if (this.h) {
                    if (!a(bluetoothGatt)) {
                        cn.com.xm.bt.a.a.e("NO CONNECTION");
                        break;
                    }
                    cn.com.xm.bt.a.a.b("WAIT: synchronizedWriteDescriptor(): " + c.a(bluetoothGattDescriptor.getUuid()));
                    this.g.await(30, TimeUnit.SECONDS);
                    if (this.h) {
                        cn.com.xm.bt.a.a.e("GATT OPERATION TIMEOUT");
                        cn.com.xm.bt.a.a.b("CONTINUE: synchronizedWriteDescriptor(): " + c.a(bluetoothGattDescriptor.getUuid()));
                        break;
                    }
                    cn.com.xm.bt.a.a.b("CONTINUE: synchronizedWriteDescriptor(): " + c.a(bluetoothGattDescriptor.getUuid()));
                }
            }
            reentrantLock = this.f;
            break;
        } catch (InterruptedException e2) {
            try {
                e2.printStackTrace();
                reentrantLock = this.f;
            } catch (Throwable th) {
                this.f.unlock();
                throw th;
            }
        }
        reentrantLock.unlock();
        return this.i;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        cn.com.xm.bt.a.a.a();
        cn.com.xm.bt.a.a.a((Thread) this.d);
        cn.com.xm.bt.a.a.a((Object) bluetoothGatt);
        if (bluetoothGatt == null) {
            this.f.lock();
            try {
                this.i = HomeRoomAddNewUserDeviceAdapter.f18333a;
                cn.com.xm.bt.a.a.b("NOTIFY: synchronizedWriteDescriptor(): " + this.i);
                this.h = false;
                this.g.signal();
            } finally {
                this.f.unlock();
            }
        } else {
            bluetoothGattDescriptor.setValue(bArr);
            boolean writeDescriptor = bluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
            cn.com.xm.bt.a.a.b(writeDescriptor);
            if (!writeDescriptor) {
                this.f.lock();
                try {
                    this.i = HomeRoomAddNewUserDeviceAdapter.b;
                    cn.com.xm.bt.a.a.b("NOTIFY: synchronizedWriteDescriptor(): " + this.i);
                    this.h = false;
                    this.g.signal();
                } finally {
                    this.f.unlock();
                }
            }
        }
    }

    public void a(boolean z) {
        if (this.j != null) {
            SharedPreferences.Editor edit = this.j.getSharedPreferences("hm_bluetooth", 0).edit();
            edit.putBoolean("refresh_gatt", z);
            edit.commit();
        }
    }

    private boolean n() {
        return this.j != null && this.j.getSharedPreferences("hm_bluetooth", 0).getBoolean("refresh_gatt", false);
    }

    private static synchronized void o() {
        synchronized (a.class) {
            cn.com.xm.bt.a.a.a("AbsGattCallback", "bluetoothSwitch");
            if (!r) {
                r = true;
                q.execute($$Lambda$a$B4KkRVzo1wxpMER2mF4CdQkXpUk.INSTANCE);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void p() {
        cn.com.xm.bt.a.a.a("AbsGattCallback", "bluetoothSwitch start~");
        BluetoothAdapter.getDefaultAdapter().disable();
        int i2 = 0;
        while (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            int i3 = i2 + 1;
            if (i2 >= 5) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception unused) {
            }
            i2 = i3;
        }
        BluetoothAdapter.getDefaultAdapter().enable();
        int i4 = 0;
        while (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            int i5 = i4 + 1;
            if (i4 >= 5) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception unused2) {
            }
            i4 = i5;
        }
        r = false;
        cn.com.xm.bt.a.a.a("AbsGattCallback", "bluetoothSwitch stop~");
    }

    public final boolean f(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return this.n != null && this.n.containsKey(bluetoothGattCharacteristic);
    }
}
