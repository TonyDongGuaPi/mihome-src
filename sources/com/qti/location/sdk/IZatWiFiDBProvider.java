package com.qti.location.sdk;

import android.location.Location;
import com.qti.location.sdk.IZatDBCommon;
import java.util.List;

public abstract class IZatWiFiDBProvider {
    protected final IZatWiFiDBProviderResponseListener c;

    public interface IZatWiFiDBProviderResponseListener {
        void a();

        void a(List<IZatAPObsLocData> list, IZatDBCommon.IZatApBsListStatus iZatApBsListStatus);
    }

    public abstract void a();

    protected IZatWiFiDBProvider(IZatWiFiDBProviderResponseListener iZatWiFiDBProviderResponseListener) throws IZatIllegalArgumentException {
        if (iZatWiFiDBProviderResponseListener != null) {
            this.c = iZatWiFiDBProviderResponseListener;
            return;
        }
        throw new IZatIllegalArgumentException("Unable to obtain IZatWiFiDBProvider instance");
    }

    public static class IZatAPObsLocData {

        /* renamed from: a  reason: collision with root package name */
        private Location f8619a;
        private IZatDBCommon.IZatCellInfo b;
        private long c;
        private List<IZatAPScan> d;

        public IZatAPObsLocData(Location location, IZatDBCommon.IZatCellInfo iZatCellInfo, long j, List<IZatAPScan> list) {
            this.f8619a = location;
            this.b = iZatCellInfo;
            this.c = j;
            this.d = list;
        }

        public Location a() {
            return this.f8619a;
        }

        public IZatDBCommon.IZatCellInfo b() {
            return this.b;
        }

        public List<IZatAPScan> c() {
            return this.d;
        }

        public long d() {
            return this.c;
        }
    }

    public static class IZatAPScan {

        /* renamed from: a  reason: collision with root package name */
        private String f8620a;
        private float b;
        private int c;
        private IZatDBCommon.IZatAPSSIDInfo d;
        private int e;

        public IZatAPScan(String str, float f, int i, IZatDBCommon.IZatAPSSIDInfo iZatAPSSIDInfo, int i2) {
            this.f8620a = str;
            this.b = f;
            this.c = i;
            this.d = iZatAPSSIDInfo;
            this.e = i2;
        }

        public String a() {
            return this.f8620a;
        }

        public float b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public IZatDBCommon.IZatAPSSIDInfo d() {
            return this.d;
        }

        public int e() {
            return this.e;
        }
    }
}
