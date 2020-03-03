package com.xiaomi.channel.gamesdk.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaomi.channel.gamesdk.Retobj;

public interface IGameService extends IInterface {
    Retobj addFriend(String str, String str2, String str3, String str4) throws RemoteException;

    Retobj checkHasJoinedUnion(String str) throws RemoteException;

    Retobj checkMiLiaoStatus() throws RemoteException;

    Retobj checkVipIsScubscribed(String str) throws RemoteException;

    String doShare(Bundle bundle) throws RemoteException;

    Bundle getAccount() throws RemoteException;

    Bundle getAuthToken(String str, String str2) throws RemoteException;

    String getCurrentMiId() throws RemoteException;

    Retobj isMiIdMyFriend(String str) throws RemoteException;

    Retobj joinUnion(String str, String str2) throws RemoteException;

    Retobj openComposeOrSixinActivity(String str, String str2) throws RemoteException;

    Retobj sendTextMsgToFriend(String str, String str2) throws RemoteException;

    Retobj subscribeVip(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IGameService {
        private static final String DESCRIPTOR = "com.xiaomi.channel.gamesdk.aidl.IGameService";
        static final int TRANSACTION_addFriend = 5;
        static final int TRANSACTION_checkHasJoinedUnion = 10;
        static final int TRANSACTION_checkMiLiaoStatus = 6;
        static final int TRANSACTION_checkVipIsScubscribed = 8;
        static final int TRANSACTION_doShare = 11;
        static final int TRANSACTION_getAccount = 3;
        static final int TRANSACTION_getAuthToken = 4;
        static final int TRANSACTION_getCurrentMiId = 13;
        static final int TRANSACTION_isMiIdMyFriend = 1;
        static final int TRANSACTION_joinUnion = 9;
        static final int TRANSACTION_openComposeOrSixinActivity = 12;
        static final int TRANSACTION_sendTextMsgToFriend = 2;
        static final int TRANSACTION_subscribeVip = 7;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGameService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGameService)) {
                return new Proxy(iBinder);
            }
            return (IGameService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        Retobj isMiIdMyFriend = isMiIdMyFriend(parcel.readString());
                        parcel2.writeNoException();
                        if (isMiIdMyFriend != null) {
                            parcel2.writeInt(1);
                            isMiIdMyFriend.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        Retobj sendTextMsgToFriend = sendTextMsgToFriend(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        if (sendTextMsgToFriend != null) {
                            parcel2.writeInt(1);
                            sendTextMsgToFriend.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        Bundle account = getAccount();
                        parcel2.writeNoException();
                        if (account != null) {
                            parcel2.writeInt(1);
                            account.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        Bundle authToken = getAuthToken(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        if (authToken != null) {
                            parcel2.writeInt(1);
                            authToken.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        Retobj addFriend = addFriend(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        if (addFriend != null) {
                            parcel2.writeInt(1);
                            addFriend.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        Retobj checkMiLiaoStatus = checkMiLiaoStatus();
                        parcel2.writeNoException();
                        if (checkMiLiaoStatus != null) {
                            parcel2.writeInt(1);
                            checkMiLiaoStatus.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        Retobj subscribeVip = subscribeVip(parcel.readString());
                        parcel2.writeNoException();
                        if (subscribeVip != null) {
                            parcel2.writeInt(1);
                            subscribeVip.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        Retobj checkVipIsScubscribed = checkVipIsScubscribed(parcel.readString());
                        parcel2.writeNoException();
                        if (checkVipIsScubscribed != null) {
                            parcel2.writeInt(1);
                            checkVipIsScubscribed.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        Retobj joinUnion = joinUnion(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        if (joinUnion != null) {
                            parcel2.writeInt(1);
                            joinUnion.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        Retobj checkHasJoinedUnion = checkHasJoinedUnion(parcel.readString());
                        parcel2.writeNoException();
                        if (checkHasJoinedUnion != null) {
                            parcel2.writeInt(1);
                            checkHasJoinedUnion.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        String doShare = doShare(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        parcel2.writeString(doShare);
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        Retobj openComposeOrSixinActivity = openComposeOrSixinActivity(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        if (openComposeOrSixinActivity != null) {
                            parcel2.writeInt(1);
                            openComposeOrSixinActivity.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        String currentMiId = getCurrentMiId();
                        parcel2.writeNoException();
                        parcel2.writeString(currentMiId);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IGameService {
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

            public Retobj isMiIdMyFriend(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Retobj.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Retobj sendTextMsgToFriend(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Retobj.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getAccount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getAuthToken(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Retobj addFriend(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Retobj.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Retobj checkMiLiaoStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Retobj.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Retobj subscribeVip(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Retobj.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Retobj checkVipIsScubscribed(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Retobj.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Retobj joinUnion(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Retobj.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Retobj checkHasJoinedUnion(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Retobj.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String doShare(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Retobj openComposeOrSixinActivity(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Retobj.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getCurrentMiId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
