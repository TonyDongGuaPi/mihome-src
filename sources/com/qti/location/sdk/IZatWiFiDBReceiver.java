package com.qti.location.sdk;

import android.location.Location;
import com.qti.location.sdk.IZatDBCommon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class IZatWiFiDBReceiver {
    public static final int e = 500;
    protected IZatWiFiDBReceiverResponseListener c = null;
    protected IZatWiFiDBReceiverResponseListenerExt d = null;

    @Deprecated
    public interface IZatWiFiDBReceiverResponseListener {
        void a();

        void a(List<IZatAPInfo> list);

        void a(boolean z, String str);
    }

    public interface IZatWiFiDBReceiverResponseListenerExt {
        void a();

        void a(List<IZatAPInfo> list, Location location);

        void a(List<IZatAPInfo> list, IZatDBCommon.IZatApBsListStatus iZatApBsListStatus, Location location);

        void a(boolean z, String str);
    }

    public abstract void a();

    public abstract void a(int i);

    public abstract void a(List<IZatAPLocationData> list, List<IZatAPSpecialInfo> list2);

    public abstract void a(List<IZatAPLocationData> list, List<IZatAPSpecialInfo> list2, int i);

    protected IZatWiFiDBReceiver(Object obj) throws IZatIllegalArgumentException {
        if (obj == null) {
            throw new IZatIllegalArgumentException("Unable to obtain IZatWiFiDBReceiver instance");
        } else if (obj instanceof IZatWiFiDBReceiverResponseListener) {
            this.c = (IZatWiFiDBReceiverResponseListener) obj;
        } else if (obj instanceof IZatWiFiDBReceiverResponseListenerExt) {
            this.d = (IZatWiFiDBReceiverResponseListenerExt) obj;
        }
    }

    public static class IZatAPInfo {

        /* renamed from: a  reason: collision with root package name */
        String f8621a;
        IZatAPInfoExtra b;
        protected int c;
        protected IZatFdalStatus d = IZatFdalStatus.NA;

        public enum IZatFdalStatus {
            NOT_IN_FDAL(0),
            IN_FDAL(1),
            BLACKLISTED(2),
            NA(3);
            
            private static final Map<Integer, IZatFdalStatus> typesByValue = null;
            private final int cValue;

            static {
                int i;
                typesByValue = new HashMap();
                for (IZatFdalStatus iZatFdalStatus : values()) {
                    typesByValue.put(Integer.valueOf(iZatFdalStatus.cValue), iZatFdalStatus);
                }
            }

            private IZatFdalStatus(int i) {
                this.cValue = i;
            }

            protected static IZatFdalStatus fromInt(int i) {
                return typesByValue.get(Integer.valueOf(i));
            }
        }

        public IZatAPInfo(String str, int i, IZatAPInfoExtra iZatAPInfoExtra) {
            this.f8621a = str;
            this.c = i;
            this.b = iZatAPInfoExtra;
        }

        /* access modifiers changed from: protected */
        public void a(int i) {
            this.d = IZatFdalStatus.fromInt(i);
        }

        public String a() {
            return this.f8621a;
        }

        public int b() {
            return this.c;
        }

        public IZatAPInfoExtra c() {
            return this.b;
        }

        public IZatFdalStatus d() {
            return this.d;
        }

        public boolean e() {
            if (this.b == null) {
                return false;
            }
            return this.b.c();
        }
    }

    public static class IZatAPInfoExtra {

        /* renamed from: a  reason: collision with root package name */
        IZatDBCommon.IZatCellInfo f8622a;
        IZatDBCommon.IZatAPSSIDInfo b;

        public IZatAPInfoExtra(IZatDBCommon.IZatCellInfo iZatCellInfo, IZatDBCommon.IZatAPSSIDInfo iZatAPSSIDInfo) {
            this.f8622a = iZatCellInfo;
            this.b = iZatAPSSIDInfo;
        }

        public IZatDBCommon.IZatCellInfo a() {
            return this.f8622a;
        }

        public IZatDBCommon.IZatAPSSIDInfo b() {
            return this.b;
        }

        public boolean c() {
            return (this.b == null && this.f8622a == null) ? false : true;
        }
    }

    public static class IZatAPLocationData {
        public static final int b = 0;
        public static final int c = 1;
        public static final int d = 2;
        public static final int e = 4;

        /* renamed from: a  reason: collision with root package name */
        IZatReliablityTypes f8623a;
        int f;
        private String g;
        private float h;
        private float i;
        private float j;
        private float k;

        public enum IZatReliablityTypes {
            VERY_LOW,
            LOW,
            MEDIUM,
            HIGH,
            VERY_HIGH
        }

        public IZatAPLocationData(String str) {
            this.g = str;
        }

        public IZatAPLocationData(String str, float f2, float f3) {
            this.g = str;
            this.h = f2;
            this.i = f3;
            this.f = 0;
        }

        public void a(float f2) {
            this.h = f2;
        }

        public void b(float f2) {
            this.i = f2;
        }

        public void c(float f2) {
            this.j = f2;
            this.f |= 1;
        }

        public void d(float f2) {
            this.k = f2;
            this.f |= 2;
        }

        public void a(IZatReliablityTypes iZatReliablityTypes) {
            this.f8623a = iZatReliablityTypes;
            this.f |= 4;
        }

        public String a() {
            return this.g;
        }

        public float b() {
            return this.h;
        }

        public float c() {
            return this.i;
        }

        public float d() throws IZatStaleDataException {
            if ((this.f & 1) != 0) {
                return this.j;
            }
            throw new IZatStaleDataException("Maximum Antena Range information is not valid");
        }

        public float e() throws IZatStaleDataException {
            if ((this.f & 2) != 0) {
                return this.k;
            }
            throw new IZatStaleDataException("Horizontal error information is not valid");
        }

        public IZatReliablityTypes f() throws IZatStaleDataException {
            if ((this.f & 4) != 0) {
                return this.f8623a;
            }
            throw new IZatStaleDataException("Reliability information is not valid");
        }
    }

    public static class IZatAPSpecialInfo {

        /* renamed from: a  reason: collision with root package name */
        public final String f8624a;
        public final IZatDBCommon.IZatAPBSSpecialInfoTypes b;

        @Deprecated
        public enum IZatAPSpecialInfoTypes {
            NO_INFO_AVAILABLE,
            MOVING_AP
        }

        @Deprecated
        public IZatAPSpecialInfo(String str, IZatAPSpecialInfoTypes iZatAPSpecialInfoTypes) {
            this.f8624a = str;
            if (IZatAPSpecialInfoTypes.MOVING_AP == iZatAPSpecialInfoTypes) {
                this.b = IZatDBCommon.IZatAPBSSpecialInfoTypes.MOVING_AP_BS;
            } else {
                this.b = IZatDBCommon.IZatAPBSSpecialInfoTypes.NO_INFO_AVAILABLE;
            }
        }

        @Deprecated
        public IZatAPSpecialInfo(String str, IZatDBCommon.IZatAPBSSpecialInfoTypes iZatAPBSSpecialInfoTypes) {
            this.f8624a = str;
            this.b = iZatAPBSSpecialInfoTypes;
        }
    }
}
