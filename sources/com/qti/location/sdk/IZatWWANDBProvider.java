package com.qti.location.sdk;

import android.location.Location;
import com.qti.location.sdk.IZatDBCommon;
import com.qti.location.sdk.IZatWWANDBReceiver;
import java.util.List;

public abstract class IZatWWANDBProvider {
    protected final IZatWWANDBProviderResponseListener c;

    public interface IZatWWANDBProviderResponseListener {
        void a();

        void a(List<IZatBSObsLocationData> list, IZatDBCommon.IZatApBsListStatus iZatApBsListStatus);
    }

    public abstract void a();

    protected IZatWWANDBProvider(IZatWWANDBProviderResponseListener iZatWWANDBProviderResponseListener) throws IZatIllegalArgumentException {
        if (iZatWWANDBProviderResponseListener != null) {
            this.c = iZatWWANDBProviderResponseListener;
            return;
        }
        throw new IZatIllegalArgumentException("Unable to obtain IZatWWANDBProvider instance");
    }

    public static class IZatBSObsLocationData extends IZatWWANDBReceiver.IZatBSLocationDataBase {

        /* renamed from: a  reason: collision with root package name */
        public static final int f8614a = 16;
        private long k;

        public IZatBSObsLocationData(IZatDBCommon.IZatCellInfo iZatCellInfo, Location location, IZatWWANDBReceiver.IZatBSLocationDataBase.IZatReliablityTypes iZatReliablityTypes, IZatWWANDBReceiver.IZatBSLocationDataBase.IZatReliablityTypes iZatReliablityTypes2) {
            super(iZatCellInfo, location, iZatReliablityTypes, iZatReliablityTypes2);
        }

        public IZatBSObsLocationData(IZatDBCommon.IZatCellInfo iZatCellInfo, Location location, IZatWWANDBReceiver.IZatBSLocationDataBase.IZatReliablityTypes iZatReliablityTypes, IZatWWANDBReceiver.IZatBSLocationDataBase.IZatReliablityTypes iZatReliablityTypes2, long j) {
            super(iZatCellInfo, location, iZatReliablityTypes, iZatReliablityTypes2);
            this.k = j;
            this.f |= 16;
        }

        public long a() {
            if ((this.f & 16) != 0) {
                return this.k;
            }
            throw new IZatStaleDataException("Timestamp information is not valid");
        }

        public void a(long j) {
            this.k = j;
            this.f |= 16;
        }
    }

    public static class IZatBSSpecialInfo {

        /* renamed from: a  reason: collision with root package name */
        private final IZatDBCommon.IZatCellInfo f8615a;
        private final IZatDBCommon.IZatAPBSSpecialInfoTypes b;

        public IZatBSSpecialInfo(IZatDBCommon.IZatCellInfo iZatCellInfo, IZatDBCommon.IZatAPBSSpecialInfoTypes iZatAPBSSpecialInfoTypes) {
            this.f8615a = iZatCellInfo;
            this.b = iZatAPBSSpecialInfoTypes;
        }

        public IZatDBCommon.IZatCellInfo a() {
            return this.f8615a;
        }

        public IZatDBCommon.IZatAPBSSpecialInfoTypes b() {
            return this.b;
        }
    }
}
