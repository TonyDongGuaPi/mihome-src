package com.xiaomi.smarthome.core.server.internal.bluetooth.classicbt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.support.annotation.NonNull;
import com.xiaomi.smarthome.core.server.internal.util.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

public abstract class AbsClassicBTProvider {

    /* renamed from: a  reason: collision with root package name */
    private final String f14172a = "AbsClassicBTProvider";
    private BluetoothDevice b = null;
    private final BluetoothAdapter c;
    /* access modifiers changed from: private */
    public ConnectionThread d = null;
    /* access modifiers changed from: private */
    public CommunicationThread e = null;
    private UUID f;
    /* access modifiers changed from: private */
    public int g = 4;

    @Retention(RetentionPolicy.SOURCE)
    @interface Errors {
        public static final int CONNECTION_FAILED = 0;
        public static final int CONNECTION_LOST = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface State {
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int DISCONNECTED = 0;
        public static final int DISCONNECTING = 3;
        public static final int NO_STATE = 4;
    }

    private static String d(int i) {
        switch (i) {
            case 0:
                return "DISCONNECTED";
            case 1:
                return "CONNECTING";
            case 2:
                return "CONNECTED";
            case 3:
                return "DISCONNECTING";
            case 4:
                return "NO STATE";
            default:
                return "UNKNOWN";
        }
    }

    /* access modifiers changed from: package-private */
    public abstract void a(int i);

    /* access modifiers changed from: package-private */
    public abstract void b(int i);

    /* access modifiers changed from: package-private */
    public abstract void b(byte[] bArr);

    /* access modifiers changed from: package-private */
    public abstract void f();

    AbsClassicBTProvider(BluetoothManager bluetoothManager) {
        if (bluetoothManager == null) {
            this.c = BluetoothAdapter.getDefaultAdapter();
            Logger.a("AbsClassicBTProvider:No available BluetoothManager, BluetoothAdapter initialised with BluetoothAdapter.getDefaultAdapter.");
        } else {
            this.c = bluetoothManager.getAdapter();
        }
        if (this.c == null) {
            Logger.b("AbsClassicBTProvider", "Initialisation of the Bluetooth Adapter failed: unable to initialize BluetoothAdapter.");
        }
    }

    /* access modifiers changed from: package-private */
    public BluetoothDevice a() {
        return this.b;
    }

    public boolean a(String str, UUID uuid) {
        if (str == null) {
            Logger.c("AbsClassicBTProvider:connection failed: Bluetooth address is null.");
            return false;
        } else if (str.length() == 0) {
            Logger.c("AbsClassicBTProvider:connection failed: Bluetooth address null or empty.");
            return false;
        } else if (!i()) {
            Logger.c("AbsClassicBTProvider:connection failed: unable to get the adapter to get the device object from BT address.");
            return false;
        } else if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            Logger.c("AbsClassicBTProvider:connection failed: unknown BT address.");
            return false;
        } else {
            BluetoothDevice remoteDevice = this.c.getRemoteDevice(str);
            if (remoteDevice != null) {
                return a(remoteDevice, uuid);
            }
            Logger.c("AbsClassicBTProvider:connection failed: get device from BT address failed.");
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.b != null && a(this.b, this.f);
    }

    public boolean c() {
        if (this.g == 0) {
            Logger.c("AbsClassicBTProvider:disconnection failed: no device connected.");
            return false;
        }
        c(3);
        g();
        h();
        c(0);
        StringBuilder sb = new StringBuilder();
        sb.append("AbsClassicBTProvider:Provider disconnected from BluetoothDevice ");
        sb.append(this.b != null ? this.b.getAddress() : "null");
        Logger.a(sb.toString());
        return true;
    }

    public boolean a(byte[] bArr) {
        synchronized (this) {
            if (this.g != 2) {
                Logger.c("AbsClassicBTProvider:Attempt to send data failed: provider not currently connected to a device.");
                return false;
            } else if (this.e == null) {
                Logger.c("AbsClassicBTProvider:Attempt to send data failed: CommunicationThread is null.");
                return false;
            } else {
                CommunicationThread communicationThread = this.e;
                return communicationThread.a(bArr);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.g == 2 && this.e != null && this.e.e;
    }

    /* access modifiers changed from: package-private */
    public synchronized int e() {
        return this.g;
    }

    private synchronized void c(int i) {
        this.g = i;
        a(i);
    }

    private void g() {
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
    }

    private void h() {
        if (this.e != null) {
            Logger.a("MyTest", "cancel CommunicationThread: " + this.e);
            this.e.a();
            this.e = null;
        }
    }

    private boolean a(@NonNull BluetoothDevice bluetoothDevice, @NonNull UUID uuid) {
        if (this.g == 2) {
            Logger.c("AbsClassicBTProvider:connection failed: a device is already connected");
            return false;
        }
        if (!(bluetoothDevice.getType() == 1 || bluetoothDevice.getType() == 3)) {
            Logger.c("AbsClassicBTProvider:connection failed: the device is not BR/EDR compatible.");
        }
        if (!i()) {
            Logger.c("AbsClassicBTProvider:connection failed: Bluetooth is not available.");
            return false;
        } else if (!BluetoothAdapter.checkBluetoothAddress(bluetoothDevice.getAddress())) {
            Logger.c("AbsClassicBTProvider:connection failed: device address not found in list of devices known by the system.");
            return false;
        } else if (uuid == null) {
            Logger.c("AbsClassicBTProvider:connection failed: device bonded and no compatible UUID available.");
            return false;
        } else if (this.g != 2 || this.e == null) {
            g();
            h();
            c(1);
            BluetoothSocket b2 = b(bluetoothDevice, uuid);
            if (b2 == null) {
                Logger.c("AbsClassicBTProvider:connection failed: creation of a Bluetooth socket failed.");
                return false;
            }
            this.f = uuid;
            this.b = bluetoothDevice;
            this.d = new ConnectionThread(b2);
            this.d.start();
            return true;
        } else {
            Logger.c("AbsClassicBTProvider:connection failed: Provider is already connected to a device with an active communication.");
            return false;
        }
    }

    private boolean i() {
        return this.c != null;
    }

    private boolean j() {
        return Build.VERSION.SDK_INT >= 10;
    }

    private BluetoothSocket b(BluetoothDevice bluetoothDevice, UUID uuid) {
        try {
            if (j()) {
                return bluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuid);
            }
            return bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e2) {
            Logger.c("AbsClassicBTProvider:Exception occurs while creating Bluetooth socket: " + e2.toString());
            Logger.a("AbsClassicBTProvider:Attempting to invoke method to create Bluetooth Socket.");
            try {
                return (BluetoothSocket) bluetoothDevice.getClass().getMethod("createRfcommSocket", new Class[]{Integer.TYPE}).invoke(bluetoothDevice, new Object[]{1});
            } catch (Exception unused) {
                Logger.c("AbsClassicBTProvider:Exception occurs while creating Bluetooth socket by invoking method: " + e2.toString());
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        c(0);
        b(0);
    }

    /* access modifiers changed from: private */
    public void l() {
        c(0);
        b(1);
    }

    /* access modifiers changed from: private */
    public void a(BluetoothSocket bluetoothSocket) {
        g();
        h();
        this.e = new CommunicationThread(bluetoothSocket);
        this.e.start();
        c(2);
        Logger.a("AbsClassicBTProvider:Successful connection to device: " + a().getAddress());
    }

    private class ConnectionThread extends Thread {
        private final BluetoothSocket b;
        private final String c;

        private ConnectionThread(BluetoothSocket bluetoothSocket) {
            this.c = "ConnectionThread";
            setName("ConnectionThread" + getId());
            this.b = bluetoothSocket;
        }

        public void run() {
            try {
                this.b.connect();
                AbsClassicBTProvider.this.a(this.b);
            } catch (IOException e) {
                Logger.a("MyTest", "ConnectException " + e.toString());
                Logger.c("AbsClassicBTProvider:Exception while connecting: " + e.toString());
                try {
                    this.b.close();
                } catch (IOException e2) {
                    Logger.c("AbsClassicBTProvider:Could not close the client socket" + e2.getMessage());
                }
                AbsClassicBTProvider.this.k();
                ConnectionThread unused = AbsClassicBTProvider.this.d = null;
            }
        }

        /* access modifiers changed from: private */
        public void a() {
            interrupt();
        }
    }

    private class CommunicationThread extends Thread {
        private final InputStream b;
        private final OutputStream c;
        private final BluetoothSocket d;
        /* access modifiers changed from: private */
        public boolean e = false;
        private final String f = "CommunicationThread";

        CommunicationThread(BluetoothSocket bluetoothSocket) {
            InputStream inputStream;
            setName("CommunicationThread" + getId());
            this.d = bluetoothSocket;
            OutputStream outputStream = null;
            try {
                inputStream = this.d.getInputStream();
                try {
                    outputStream = this.d.getOutputStream();
                } catch (IOException e2) {
                    e = e2;
                    Logger.b("CommunicationThread", "Error occurred when getting input and output streams" + e.getMessage());
                    this.b = inputStream;
                    this.c = outputStream;
                }
            } catch (IOException e3) {
                e = e3;
                inputStream = null;
                Logger.b("CommunicationThread", "Error occurred when getting input and output streams" + e.getMessage());
                this.b = inputStream;
                this.c = outputStream;
            }
            this.b = inputStream;
            this.c = outputStream;
        }

        public void run() {
            if (this.b == null) {
                Logger.c("AbsClassicBTProvider:Run thread failed: InputStream is null.");
                AbsClassicBTProvider.this.c();
            } else if (this.c == null) {
                Logger.c("AbsClassicBTProvider:Run thread failed: OutputStream is null.");
                AbsClassicBTProvider.this.c();
            } else if (this.d == null) {
                Logger.c("AbsClassicBTProvider:Run thread failed: BluetoothSocket is null.");
                AbsClassicBTProvider.this.c();
            } else if (!this.d.isConnected()) {
                Logger.c("AbsClassicBTProvider:Run thread failed: BluetoothSocket is not connected.");
                AbsClassicBTProvider.this.c();
            } else {
                b();
            }
        }

        private void b() {
            byte[] bArr = new byte[1024];
            this.e = true;
            AbsClassicBTProvider.this.f();
            while (AbsClassicBTProvider.this.g == 2 && this.e) {
                try {
                    int read = this.b.read(bArr);
                    if (read > 0) {
                        byte[] bArr2 = new byte[read];
                        System.arraycopy(bArr, 0, bArr2, 0, read);
                        Logger.b("MyTest", "receiveData = " + AbsClassicBTProvider.c(bArr2));
                        AbsClassicBTProvider.this.b(bArr2);
                    }
                } catch (IOException e2) {
                    Logger.b("CommunicationThread", "Reception of data failed: exception occurred while reading: " + e2.toString());
                    this.e = false;
                    if (AbsClassicBTProvider.this.g == 2) {
                        AbsClassicBTProvider.this.l();
                    }
                    Logger.a("MyTest", "clear CommunicationThread:" + AbsClassicBTProvider.this.e);
                    CommunicationThread unused = AbsClassicBTProvider.this.e = null;
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(byte[] bArr) {
            if (this.d == null) {
                Logger.c("AbsClassicBTProvider:Sending of data failed: BluetoothSocket is null.");
                return false;
            } else if (!this.d.isConnected()) {
                Logger.c("AbsClassicBTProvider:Sending of data failed: BluetoothSocket is not connected.");
                return false;
            } else if (AbsClassicBTProvider.this.g != 2) {
                Logger.c("AbsClassicBTProvider:Sending of data failed: Provider is not connected.");
                return false;
            } else if (this.c == null) {
                Logger.c("AbsClassicBTProvider:Sending of data failed: OutputStream is null.");
                return false;
            } else {
                try {
                    this.c.write(bArr);
                    this.c.flush();
                    Logger.c("MyTest", "sendData = " + AbsClassicBTProvider.c(bArr));
                    return true;
                } catch (IOException e2) {
                    Logger.a("MyTest", "sendData error");
                    Logger.c("AbsClassicBTProvider:Sending of data failed: Exception occurred while writing data: " + e2.toString());
                    return false;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.e = false;
            try {
                this.d.close();
            } catch (IOException e2) {
                Logger.c("AbsClassicBTProvider:Cancellation of the Thread: Close of BluetoothSocket failed: " + e2.toString());
            }
        }
    }

    public static String c(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null && bArr.length > 0) {
            for (int i = 0; i < bArr.length; i++) {
                sb.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
            }
        }
        return sb.toString();
    }
}
