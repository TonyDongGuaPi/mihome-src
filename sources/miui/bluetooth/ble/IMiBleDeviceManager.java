package miui.bluetooth.ble;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

public interface IMiBleDeviceManager extends IInterface {
    boolean deleteSettings(String str) throws RemoteException;

    List<String> getBoundDevices() throws RemoteException;

    Map getDeviceSettings(String str) throws RemoteException;

    int getDeviceType(String str) throws RemoteException;

    String getRegisterAppForBleEvent(String str, int i) throws RemoteException;

    ScanResult getScanResult(String str) throws RemoteException;

    int getServiceVersion() throws RemoteException;

    int getSettingInteger(String str, String str2) throws RemoteException;

    String getSettingString(String str, String str2) throws RemoteException;

    boolean registerAppForBleEvent(String str, int i) throws RemoteException;

    boolean registerBleEventListener(String str, int i, IBleEventCallback iBleEventCallback) throws RemoteException;

    boolean setSettingInteger(String str, String str2, int i) throws RemoteException;

    boolean setSettingString(String str, String str2, String str3) throws RemoteException;

    boolean setToken(String str, byte[] bArr) throws RemoteException;

    boolean startScanDevice(IBinder iBinder, ParcelUuid parcelUuid, int i, IScanDeviceCallback iScanDeviceCallback) throws RemoteException;

    void stopScanDevice(ParcelUuid parcelUuid) throws RemoteException;

    boolean unregisterAppForBleEvent(String str, int i) throws RemoteException;

    boolean unregisterBleEventListener(String str, int i, IBleEventCallback iBleEventCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IMiBleDeviceManager {
        private static final String DESCRIPTOR = "miui.bluetooth.ble.IMiBleDeviceManager";
        static final int TRANSACTION_deleteSettings = 6;
        static final int TRANSACTION_getBoundDevices = 13;
        static final int TRANSACTION_getDeviceSettings = 5;
        static final int TRANSACTION_getDeviceType = 7;
        static final int TRANSACTION_getRegisterAppForBleEvent = 16;
        static final int TRANSACTION_getScanResult = 17;
        static final int TRANSACTION_getServiceVersion = 12;
        static final int TRANSACTION_getSettingInteger = 4;
        static final int TRANSACTION_getSettingString = 2;
        static final int TRANSACTION_registerAppForBleEvent = 14;
        static final int TRANSACTION_registerBleEventListener = 10;
        static final int TRANSACTION_setSettingInteger = 3;
        static final int TRANSACTION_setSettingString = 1;
        static final int TRANSACTION_setToken = 18;
        static final int TRANSACTION_startScanDevice = 8;
        static final int TRANSACTION_stopScanDevice = 9;
        static final int TRANSACTION_unregisterAppForBleEvent = 15;
        static final int TRANSACTION_unregisterBleEventListener = 11;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMiBleDeviceManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMiBleDeviceManager)) {
                return new Proxy(iBinder);
            }
            return (IMiBleDeviceManager) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.os.ParcelUuid} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v29, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: android.os.ParcelUuid} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
            /*
                r2 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r3 == r0) goto L_0x01db
                r0 = 0
                switch(r3) {
                    case 1: goto L_0x01bf;
                    case 2: goto L_0x01a7;
                    case 3: goto L_0x018b;
                    case 4: goto L_0x0173;
                    case 5: goto L_0x015f;
                    case 6: goto L_0x014b;
                    case 7: goto L_0x0137;
                    case 8: goto L_0x0108;
                    case 9: goto L_0x00ed;
                    case 10: goto L_0x00cd;
                    case 11: goto L_0x00ad;
                    case 12: goto L_0x009d;
                    case 13: goto L_0x008d;
                    case 14: goto L_0x0075;
                    case 15: goto L_0x005d;
                    case 16: goto L_0x0045;
                    case 17: goto L_0x0027;
                    case 18: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r3 = super.onTransact(r3, r4, r5, r6)
                return r3
            L_0x000f:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                byte[] r4 = r4.createByteArray()
                boolean r3 = r2.setToken(r3, r4)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0027:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                miui.bluetooth.ble.ScanResult r3 = r2.getScanResult(r3)
                r5.writeNoException()
                if (r3 == 0) goto L_0x0040
                r5.writeInt(r1)
                r3.writeToParcel(r5, r1)
                goto L_0x0044
            L_0x0040:
                r3 = 0
                r5.writeInt(r3)
            L_0x0044:
                return r1
            L_0x0045:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                int r4 = r4.readInt()
                java.lang.String r3 = r2.getRegisterAppForBleEvent(r3, r4)
                r5.writeNoException()
                r5.writeString(r3)
                return r1
            L_0x005d:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                int r4 = r4.readInt()
                boolean r3 = r2.unregisterAppForBleEvent(r3, r4)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0075:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                int r4 = r4.readInt()
                boolean r3 = r2.registerAppForBleEvent(r3, r4)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x008d:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.util.List r3 = r2.getBoundDevices()
                r5.writeNoException()
                r5.writeStringList(r3)
                return r1
            L_0x009d:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                int r3 = r2.getServiceVersion()
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x00ad:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                int r6 = r4.readInt()
                android.os.IBinder r4 = r4.readStrongBinder()
                miui.bluetooth.ble.IBleEventCallback r4 = miui.bluetooth.ble.IBleEventCallback.Stub.asInterface(r4)
                boolean r3 = r2.unregisterBleEventListener(r3, r6, r4)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x00cd:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                int r6 = r4.readInt()
                android.os.IBinder r4 = r4.readStrongBinder()
                miui.bluetooth.ble.IBleEventCallback r4 = miui.bluetooth.ble.IBleEventCallback.Stub.asInterface(r4)
                boolean r3 = r2.registerBleEventListener(r3, r6, r4)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x00ed:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0101
                android.os.Parcelable$Creator r3 = android.os.ParcelUuid.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x0101:
                r2.stopScanDevice(r0)
                r5.writeNoException()
                return r1
            L_0x0108:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                android.os.IBinder r3 = r4.readStrongBinder()
                int r6 = r4.readInt()
                if (r6 == 0) goto L_0x0120
                android.os.Parcelable$Creator r6 = android.os.ParcelUuid.CREATOR
                java.lang.Object r6 = r6.createFromParcel(r4)
                r0 = r6
                android.os.ParcelUuid r0 = (android.os.ParcelUuid) r0
            L_0x0120:
                int r6 = r4.readInt()
                android.os.IBinder r4 = r4.readStrongBinder()
                miui.bluetooth.ble.IScanDeviceCallback r4 = miui.bluetooth.ble.IScanDeviceCallback.Stub.asInterface(r4)
                boolean r3 = r2.startScanDevice(r3, r0, r6, r4)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0137:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                int r3 = r2.getDeviceType(r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x014b:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                boolean r3 = r2.deleteSettings(r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x015f:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                java.util.Map r3 = r2.getDeviceSettings(r3)
                r5.writeNoException()
                r5.writeMap(r3)
                return r1
            L_0x0173:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                java.lang.String r4 = r4.readString()
                int r3 = r2.getSettingInteger(r3, r4)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x018b:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                java.lang.String r6 = r4.readString()
                int r4 = r4.readInt()
                boolean r3 = r2.setSettingInteger(r3, r6, r4)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x01a7:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                java.lang.String r4 = r4.readString()
                java.lang.String r3 = r2.getSettingString(r3, r4)
                r5.writeNoException()
                r5.writeString(r3)
                return r1
            L_0x01bf:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                java.lang.String r6 = r4.readString()
                java.lang.String r4 = r4.readString()
                boolean r3 = r2.setSettingString(r3, r6, r4)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x01db:
                java.lang.String r3 = "miui.bluetooth.ble.IMiBleDeviceManager"
                r5.writeString(r3)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: miui.bluetooth.ble.IMiBleDeviceManager.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IMiBleDeviceManager {
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

            public boolean setSettingString(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    boolean z = false;
                    this.mRemote.transact(1, obtain, obtain2, 0);
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

            public String getSettingString(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setSettingInteger(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
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

            public int getSettingInteger(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Map getDeviceSettings(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean deleteSettings(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(6, obtain, obtain2, 0);
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

            public int getDeviceType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean startScanDevice(IBinder iBinder, ParcelUuid parcelUuid, int i, IScanDeviceCallback iScanDeviceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    boolean z = true;
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iScanDeviceCallback != null ? iScanDeviceCallback.asBinder() : null);
                    this.mRemote.transact(8, obtain, obtain2, 0);
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

            public void stopScanDevice(ParcelUuid parcelUuid) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelUuid != null) {
                        obtain.writeInt(1);
                        parcelUuid.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean registerBleEventListener(String str, int i, IBleEventCallback iBleEventCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBleEventCallback != null ? iBleEventCallback.asBinder() : null);
                    boolean z = false;
                    this.mRemote.transact(10, obtain, obtain2, 0);
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

            public boolean unregisterBleEventListener(String str, int i, IBleEventCallback iBleEventCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBleEventCallback != null ? iBleEventCallback.asBinder() : null);
                    boolean z = false;
                    this.mRemote.transact(11, obtain, obtain2, 0);
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

            public int getServiceVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<String> getBoundDevices() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean registerAppForBleEvent(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(14, obtain, obtain2, 0);
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

            public boolean unregisterAppForBleEvent(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(15, obtain, obtain2, 0);
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

            public String getRegisterAppForBleEvent(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ScanResult getScanResult(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? ScanResult.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setToken(String str, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    boolean z = false;
                    this.mRemote.transact(18, obtain, obtain2, 0);
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
        }
    }
}
