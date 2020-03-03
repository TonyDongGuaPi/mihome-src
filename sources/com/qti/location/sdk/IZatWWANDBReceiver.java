package com.qti.location.sdk;

import android.location.Location;
import com.qti.location.sdk.IZatDBCommon;
import java.util.List;

public abstract class IZatWWANDBReceiver {
    public static final int e = 20;
    protected IZatWWANDBReceiverResponseListener c = null;
    protected IZatWWANDBReceiverResponseListenerExt d = null;

    @Deprecated
    public interface IZatWWANDBReceiverResponseListener {
        void a();

        void a(List<IZatBSInfo> list);

        void a(boolean z, String str);
    }

    public interface IZatWWANDBReceiverResponseListenerExt {
        void a();

        void a(List<IZatDBCommon.IZatCellInfo> list, IZatDBCommon.IZatApBsListStatus iZatApBsListStatus, Location location);

        void a(boolean z, String str);
    }

    public abstract void a(int i);

    public abstract void a(List<IZatBSLocationData> list, List<IZatBSSpecialInfo> list2, int i);

    protected IZatWWANDBReceiver(Object obj) throws IZatIllegalArgumentException {
        if (obj == null) {
            throw new IZatIllegalArgumentException("Unable to obtain IZatWWANDBReceiver instance");
        } else if (obj instanceof IZatWWANDBReceiverResponseListener) {
            this.c = (IZatWWANDBReceiverResponseListener) obj;
        } else if (obj instanceof IZatWWANDBReceiverResponseListenerExt) {
            this.d = (IZatWWANDBReceiverResponseListenerExt) obj;
        }
    }

    @Deprecated
    public static class IZatBSCellInfo extends IZatDBCommon.IZatCellInfo {
        @Deprecated
        public final int i;
        @Deprecated
        public final int j;
        @Deprecated
        public final int k;
        @Deprecated
        public final int l;
        @Deprecated
        public IZatDBCommon.IZatCellTypes m;

        public IZatBSCellInfo(int i2, int i3, int i4, int i5, IZatDBCommon.IZatCellTypes iZatCellTypes) {
            super(i2, i3, i4, i5, iZatCellTypes);
            this.i = i2;
            this.j = i3;
            this.k = i4;
            this.l = i5;
            this.m = iZatCellTypes;
        }

        public IZatBSCellInfo(IZatDBCommon.IZatCellInfo iZatCellInfo) {
            super(iZatCellInfo.f8596a, iZatCellInfo.b, iZatCellInfo.c, iZatCellInfo.d, iZatCellInfo.e);
            this.i = iZatCellInfo.f8596a;
            this.j = iZatCellInfo.b;
            this.k = iZatCellInfo.c;
            this.l = iZatCellInfo.d;
            this.m = iZatCellInfo.e;
        }
    }

    @Deprecated
    public static class IZatBSInfo {

        /* renamed from: a  reason: collision with root package name */
        IZatBSCellInfo f8616a;

        public IZatBSInfo(IZatBSCellInfo iZatBSCellInfo) {
            this.f8616a = iZatBSCellInfo;
        }

        public IZatBSInfo(IZatDBCommon.IZatCellInfo iZatCellInfo) {
            this.f8616a = new IZatBSCellInfo(iZatCellInfo);
        }

        public IZatBSCellInfo a() {
            return this.f8616a;
        }
    }

    public static abstract class IZatBSLocationDataBase {
        public static final int g = 1;
        public static final int h = 2;
        public static final int i = 4;
        public static final int j = 8;
        protected IZatDBCommon.IZatCellInfo b;
        protected Location c;
        protected IZatReliablityTypes d;
        protected IZatReliablityTypes e;
        protected int f;

        public enum IZatReliablityTypes {
            VERY_LOW,
            LOW,
            MEDIUM,
            HIGH,
            VERY_HIGH
        }

        public IZatBSLocationDataBase(IZatDBCommon.IZatCellInfo iZatCellInfo, Location location, IZatReliablityTypes iZatReliablityTypes, IZatReliablityTypes iZatReliablityTypes2) {
            this.b = iZatCellInfo;
            this.c = location;
            this.d = iZatReliablityTypes;
            this.e = iZatReliablityTypes2;
            this.f = 0;
            this.f |= 1;
            this.f |= 2;
            this.f |= 4;
            this.f |= 8;
        }

        public IZatBSLocationDataBase(IZatDBCommon.IZatCellInfo iZatCellInfo, Location location) {
            this.b = iZatCellInfo;
            this.c = location;
            this.f = 0;
            this.f |= 1;
            this.f |= 2;
        }

        public IZatBSLocationDataBase(IZatBSLocationDataBase iZatBSLocationDataBase) {
            this.b = iZatBSLocationDataBase.b;
            this.c = iZatBSLocationDataBase.c;
            this.d = iZatBSLocationDataBase.d;
            this.e = iZatBSLocationDataBase.e;
            this.f = iZatBSLocationDataBase.f;
        }

        public void a(IZatReliablityTypes iZatReliablityTypes) {
            this.d = iZatReliablityTypes;
            this.f |= 4;
        }

        public void b(IZatReliablityTypes iZatReliablityTypes) {
            this.e = iZatReliablityTypes;
            this.f |= 8;
        }

        public Location l() throws IZatStaleDataException {
            if ((this.f & 1) != 0) {
                return this.c;
            }
            throw new IZatStaleDataException("Location information is not valid");
        }

        public IZatDBCommon.IZatCellInfo m() throws IZatStaleDataException {
            if ((this.f & 2) != 0) {
                return this.b;
            }
            throw new IZatStaleDataException("Cell info information is not valid");
        }

        public IZatReliablityTypes n() throws IZatStaleDataException {
            if ((this.f & 4) != 0) {
                return this.d;
            }
            throw new IZatStaleDataException("Horizontal reliability information is not valid");
        }

        public IZatReliablityTypes o() throws IZatStaleDataException {
            if ((this.f & 8) != 0) {
                return this.e;
            }
            throw new IZatStaleDataException("Altitude reliability information is not valid");
        }
    }

    public static class IZatBSLocationData extends IZatBSLocationDataBase {

        /* renamed from: a  reason: collision with root package name */
        public static final int f8617a = 16;
        private float k;

        @Deprecated
        public void c(int i) {
        }

        public IZatBSLocationData(IZatDBCommon.IZatCellInfo iZatCellInfo, Location location) {
            super(iZatCellInfo, location);
        }

        public IZatBSLocationData(IZatDBCommon.IZatCellInfo iZatCellInfo, Location location, IZatBSLocationDataBase.IZatReliablityTypes iZatReliablityTypes, IZatBSLocationDataBase.IZatReliablityTypes iZatReliablityTypes2) {
            super(iZatCellInfo, location, iZatReliablityTypes, iZatReliablityTypes2);
        }

        public IZatBSLocationData(IZatDBCommon.IZatCellInfo iZatCellInfo, Location location, IZatBSLocationDataBase.IZatReliablityTypes iZatReliablityTypes, IZatBSLocationDataBase.IZatReliablityTypes iZatReliablityTypes2, float f) {
            super(iZatCellInfo, location, iZatReliablityTypes, iZatReliablityTypes2);
            this.k = f;
            this.f |= 16;
        }

        private static IZatBSLocationData a(int i, int i2, int i3, int i4, int i5, float f, float f2) {
            IZatDBCommon.IZatCellInfo iZatCellInfo = new IZatDBCommon.IZatCellInfo(i2, i3, i4, i5, IZatDBCommon.IZatCellTypes.values()[i]);
            Location location = new Location("");
            location.setLatitude((double) f);
            location.setLongitude((double) f2);
            return new IZatBSLocationData(iZatCellInfo, location);
        }

        public IZatBSLocationData(IZatBSLocationData iZatBSLocationData) {
            super(iZatBSLocationData);
            this.k = iZatBSLocationData.k;
            this.f |= 16;
        }

        @Deprecated
        public IZatBSLocationData(int i, int i2, int i3, int i4, int i5, float f, float f2) {
            this(a(i, i2, i3, i4, i5, f, f2));
        }

        public float a() {
            if ((this.f & 16) != 0) {
                return this.k;
            }
            throw new IZatStaleDataException("Horizontal coverage information is not valid");
        }

        @Deprecated
        public float b() {
            return (float) this.c.getLatitude();
        }

        @Deprecated
        public float c() {
            return (float) this.c.getLongitude();
        }

        @Deprecated
        public float d() {
            return (float) this.c.getAltitude();
        }

        @Deprecated
        public float e() {
            return this.c.getVerticalAccuracyMeters();
        }

        @Deprecated
        public float f() {
            return (float) IZatDBCommon.b.intValue();
        }

        @Deprecated
        public float g() {
            return (float) IZatDBCommon.f8594a.intValue();
        }

        @Deprecated
        public int h() {
            return this.b.b();
        }

        @Deprecated
        public int i() {
            return this.b.c();
        }

        @Deprecated
        public int j() {
            return this.b.d();
        }

        @Deprecated
        public int k() {
            return this.b.e();
        }

        public void a(Location location) {
            this.c = location;
        }

        @Deprecated
        public void a(int i) {
            if (i >= 0 && i <= 4) {
                this.b.a(IZatDBCommon.IZatCellTypes.values()[i]);
            }
        }

        @Deprecated
        public void a(float f) {
            if (this.c != null) {
                this.c.setLatitude((double) f);
            }
        }

        @Deprecated
        public void b(float f) {
            if (this.c != null) {
                this.c.setLongitude((double) f);
            }
        }

        @Deprecated
        public void c(float f) {
            this.k = f;
            this.f |= 16;
        }

        @Deprecated
        public void b(int i) {
            if (this.c != null) {
                this.c.setAccuracy((float) i);
            }
        }

        @Deprecated
        public void a(IZatBSLocationDataBase.IZatReliablityTypes iZatReliablityTypes) {
            this.d = iZatReliablityTypes;
            this.f |= 4;
        }

        @Deprecated
        public void d(float f) {
            if (this.c != null) {
                this.c.setAltitude((double) f);
            }
        }

        @Deprecated
        public void e(float f) {
            if (this.c != null) {
                this.c.setVerticalAccuracyMeters(f);
            }
        }

        @Deprecated
        public void b(IZatBSLocationDataBase.IZatReliablityTypes iZatReliablityTypes) {
            this.e = iZatReliablityTypes;
            this.f |= 8;
        }
    }

    public static class IZatBSSpecialInfo {

        /* renamed from: a  reason: collision with root package name */
        private final IZatDBCommon.IZatCellInfo f8618a;
        private final IZatDBCommon.IZatAPBSSpecialInfoTypes b;

        public IZatBSSpecialInfo(IZatDBCommon.IZatCellInfo iZatCellInfo, IZatDBCommon.IZatAPBSSpecialInfoTypes iZatAPBSSpecialInfoTypes) {
            this.f8618a = iZatCellInfo;
            this.b = iZatAPBSSpecialInfoTypes;
        }

        public IZatDBCommon.IZatCellInfo a() {
            return this.f8618a;
        }

        public IZatDBCommon.IZatAPBSSpecialInfoTypes b() {
            return this.b;
        }
    }
}
