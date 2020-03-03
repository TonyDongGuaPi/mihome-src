package miui.bluetooth.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import java.util.List;

public interface IBluetoothMiBle extends IInterface {
    boolean authenticate(String str, ParcelUuid parcelUuid) throws RemoteException;

    boolean authorize(String str, ParcelUuid parcelUuid, String str2) throws RemoteException;

    void connect(String str, ParcelUuid parcelUuid) throws RemoteException;

    void disconnect(String str, ParcelUuid parcelUuid) throws RemoteException;

    byte[] encrypt(String str, ParcelUuid parcelUuid, byte[] bArr) throws RemoteException;

    List<BluetoothDevice> getConnectedDevices() throws RemoteException;

    byte[] getProperty(String str, ParcelUuid parcelUuid, int i) throws RemoteException;

    int getRssi(String str, ParcelUuid parcelUuid) throws RemoteException;

    int getServiceVersion() throws RemoteException;

    boolean isConnected(String str) throws RemoteException;

    void registerClient(IBinder iBinder, String str, ParcelUuid parcelUuid, IBluetoothMiBleCallback iBluetoothMiBleCallback) throws RemoteException;

    boolean registerPropertyCallback(String str, ParcelUuid parcelUuid, int i, IBluetoothMiBlePropertyCallback iBluetoothMiBlePropertyCallback) throws RemoteException;

    boolean setEncryptionKey(String str, ParcelUuid parcelUuid, byte[] bArr) throws RemoteException;

    boolean setProperty(String str, ParcelUuid parcelUuid, int i, byte[] bArr) throws RemoteException;

    boolean setRssiThreshold(String str, ParcelUuid parcelUuid, int i) throws RemoteException;

    boolean supportProperty(String str, int i) throws RemoteException;

    void unregisterClient(IBinder iBinder, String str, ParcelUuid parcelUuid) throws RemoteException;

    boolean unregisterPropertyCallback(String str, ParcelUuid parcelUuid, int i, IBluetoothMiBlePropertyCallback iBluetoothMiBlePropertyCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothMiBle {
        private static final String DESCRIPTOR = "miui.bluetooth.ble.IBluetoothMiBle";
        static final int TRANSACTION_authenticate = 15;
        static final int TRANSACTION_authorize = 13;
        static final int TRANSACTION_connect = 4;
        static final int TRANSACTION_disconnect = 5;
        static final int TRANSACTION_encrypt = 17;
        static final int TRANSACTION_getConnectedDevices = 6;
        static final int TRANSACTION_getProperty = 12;
        static final int TRANSACTION_getRssi = 7;
        static final int TRANSACTION_getServiceVersion = 18;
        static final int TRANSACTION_isConnected = 3;
        static final int TRANSACTION_registerClient = 1;
        static final int TRANSACTION_registerPropertyCallback = 9;
        static final int TRANSACTION_setEncryptionKey = 16;
        static final int TRANSACTION_setProperty = 11;
        static final int TRANSACTION_setRssiThreshold = 14;
        static final int TRANSACTION_supportProperty = 8;
        static final int TRANSACTION_unregisterClient = 2;
        static final int TRANSACTION_unregisterPropertyCallback = 10;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBluetoothMiBle asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IBluetoothMiBle)) {
                return new Proxy(iBinder);
            }
            return (IBluetoothMiBle) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v20, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v23, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v29, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v26, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v32, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v29, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v35, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v38, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v34, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v41, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v37, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v44, resolved type: android.os.ParcelUuid} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r4 == r0) goto L_0x0278
                r0 = 0
                switch(r4) {
                    case 1: goto L_0x024e;
                    case 2: goto L_0x022b;
                    case 3: goto L_0x0217;
                    case 4: goto L_0x01f8;
                    case 5: goto L_0x01d9;
                    case 6: goto L_0x01c9;
                    case 7: goto L_0x01a6;
                    case 8: goto L_0x018e;
                    case 9: goto L_0x015f;
                    case 10: goto L_0x0130;
                    case 11: goto L_0x0105;
                    case 12: goto L_0x00de;
                    case 13: goto L_0x00b7;
                    case 14: goto L_0x0090;
                    case 15: goto L_0x006d;
                    case 16: goto L_0x0046;
                    case 17: goto L_0x001f;
                    case 18: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x000f:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                int r4 = r3.getServiceVersion()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x001f:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0037
                android.os.Parcelable$Creator r7 = android.os.ParcelUuid.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                r0 = r7
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x0037:
                byte[] r5 = r5.createByteArray()
                byte[] r4 = r3.encrypt(r4, r0, r5)
                r6.writeNoException()
                r6.writeByteArray(r4)
                return r1
            L_0x0046:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x005e
                android.os.Parcelable$Creator r7 = android.os.ParcelUuid.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                r0 = r7
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x005e:
                byte[] r5 = r5.createByteArray()
                boolean r4 = r3.setEncryptionKey(r4, r0, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x006d:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0085
                android.os.Parcelable$Creator r7 = android.os.ParcelUuid.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r0 = r5
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x0085:
                boolean r4 = r3.authenticate(r4, r0)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x0090:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x00a8
                android.os.Parcelable$Creator r7 = android.os.ParcelUuid.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                r0 = r7
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x00a8:
                int r5 = r5.readInt()
                boolean r4 = r3.setRssiThreshold(r4, r0, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x00b7:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x00cf
                android.os.Parcelable$Creator r7 = android.os.ParcelUuid.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                r0 = r7
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x00cf:
                java.lang.String r5 = r5.readString()
                boolean r4 = r3.authorize(r4, r0, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x00de:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x00f6
                android.os.Parcelable$Creator r7 = android.os.ParcelUuid.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                r0 = r7
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x00f6:
                int r5 = r5.readInt()
                byte[] r4 = r3.getProperty(r4, r0, r5)
                r6.writeNoException()
                r6.writeByteArray(r4)
                return r1
            L_0x0105:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x011d
                android.os.Parcelable$Creator r7 = android.os.ParcelUuid.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                r0 = r7
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x011d:
                int r7 = r5.readInt()
                byte[] r5 = r5.createByteArray()
                boolean r4 = r3.setProperty(r4, r0, r7, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x0130:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0148
                android.os.Parcelable$Creator r7 = android.os.ParcelUuid.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                r0 = r7
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x0148:
                int r7 = r5.readInt()
                android.os.IBinder r5 = r5.readStrongBinder()
                miui.bluetooth.ble.IBluetoothMiBlePropertyCallback r5 = miui.bluetooth.ble.IBluetoothMiBlePropertyCallback.Stub.asInterface(r5)
                boolean r4 = r3.unregisterPropertyCallback(r4, r0, r7, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x015f:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0177
                android.os.Parcelable$Creator r7 = android.os.ParcelUuid.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                r0 = r7
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x0177:
                int r7 = r5.readInt()
                android.os.IBinder r5 = r5.readStrongBinder()
                miui.bluetooth.ble.IBluetoothMiBlePropertyCallback r5 = miui.bluetooth.ble.IBluetoothMiBlePropertyCallback.Stub.asInterface(r5)
                boolean r4 = r3.registerPropertyCallback(r4, r0, r7, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x018e:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r5 = r5.readInt()
                boolean r4 = r3.supportProperty(r4, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x01a6:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x01be
                android.os.Parcelable$Creator r7 = android.os.ParcelUuid.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r0 = r5
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x01be:
                int r4 = r3.getRssi(r4, r0)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x01c9:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.util.List r4 = r3.getConnectedDevices()
                r6.writeNoException()
                r6.writeTypedList(r4)
                return r1
            L_0x01d9:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x01f1
                android.os.Parcelable$Creator r7 = android.os.ParcelUuid.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r0 = r5
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x01f1:
                r3.disconnect(r4, r0)
                r6.writeNoException()
                return r1
            L_0x01f8:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0210
                android.os.Parcelable$Creator r7 = android.os.ParcelUuid.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r0 = r5
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x0210:
                r3.connect(r4, r0)
                r6.writeNoException()
                return r1
            L_0x0217:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                boolean r4 = r3.isConnected(r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x022b:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                java.lang.String r7 = r5.readString()
                int r2 = r5.readInt()
                if (r2 == 0) goto L_0x0247
                android.os.Parcelable$Creator r0 = android.os.ParcelUuid.CREATOR
                java.lang.Object r5 = r0.createFromParcel(r5)
                r0 = r5
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x0247:
                r3.unregisterClient(r4, r7, r0)
                r6.writeNoException()
                return r1
            L_0x024e:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                java.lang.String r7 = r5.readString()
                int r2 = r5.readInt()
                if (r2 == 0) goto L_0x0269
                android.os.Parcelable$Creator r0 = android.os.ParcelUuid.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r5)
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x0269:
                android.os.IBinder r5 = r5.readStrongBinder()
                miui.bluetooth.ble.IBluetoothMiBleCallback r5 = miui.bluetooth.ble.IBluetoothMiBleCallback.Stub.asInterface(r5)
                r3.registerClient(r4, r7, r0, r5)
                r6.writeNoException()
                return r1
            L_0x0278:
                java.lang.String r4 = "miui.bluetooth.ble.IBluetoothMiBle"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: miui.bluetooth.ble.IBluetoothMiBle.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IBluetoothMiBle {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void registerClient(IBinder iBinder, String str, ParcelUuid parcelUuid, IBluetoothMiBleCallback iBluetoothMiBleCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeString(str);
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iBluetoothMiBleCallback != null ? iBluetoothMiBleCallback.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisterClient(IBinder iBinder, String str, ParcelUuid parcelUuid) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeString(str);
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isConnected(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void connect(String str, ParcelUuid parcelUuid) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void disconnect(String str, ParcelUuid parcelUuid) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(BluetoothDevice.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getRssi(String str, ParcelUuid parcelUuid) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean supportProperty(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean registerPropertyCallback(String str, ParcelUuid parcelUuid, int i, IBluetoothMiBlePropertyCallback iBluetoothMiBlePropertyCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = true;
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBluetoothMiBlePropertyCallback != null ? iBluetoothMiBlePropertyCallback.asBinder() : null);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean unregisterPropertyCallback(String str, ParcelUuid parcelUuid, int i, IBluetoothMiBlePropertyCallback iBluetoothMiBlePropertyCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = true;
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBluetoothMiBlePropertyCallback != null ? iBluetoothMiBlePropertyCallback.asBinder() : null);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setProperty(String str, ParcelUuid parcelUuid, int i, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = true;
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public byte[] getProperty(String str, ParcelUuid parcelUuid, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean authorize(String str, ParcelUuid parcelUuid, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = true;
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str2);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setRssiThreshold(String str, ParcelUuid parcelUuid, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = true;
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean authenticate(String str, ParcelUuid parcelUuid) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = true;
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setEncryptionKey(String str, ParcelUuid parcelUuid, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = true;
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public byte[] encrypt(String str, ParcelUuid parcelUuid, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getServiceVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
