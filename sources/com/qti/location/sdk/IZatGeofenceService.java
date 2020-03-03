package com.qti.location.sdk;

import android.app.PendingIntent;
import android.location.Location;
import java.util.Map;

public interface IZatGeofenceService {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8598a = 0;
    public static final int b = -100;
    public static final int c = -102;
    public static final int d = -103;
    public static final int e = -149;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    public static final int i = 4;
    public static final int j = 5;
    public static final int k = 1;
    public static final int l = 2;
    public static final int m = 4;
    public static final int n = 8;
    public static final int o = 16;
    public static final int p = 1;
    public static final int q = 2;
    public static final int r = 3;
    public static final int s = 4;

    public interface IZatGeofenceCallback {
        void a(int i, Location location);

        void a(IZatGeofenceHandle iZatGeofenceHandle, int i, int i2);

        void a(IZatGeofenceHandle iZatGeofenceHandle, int i, Location location);
    }

    public interface IZatGeofenceHandle {
        Object a();

        void a(IzatGeofence izatGeofence) throws IZatIllegalArgumentException;

        void a(IzatGeofenceTransitionTypes izatGeofenceTransitionTypes, int i) throws IZatIllegalArgumentException;

        void b() throws IZatIllegalArgumentException;

        void c() throws IZatIllegalArgumentException;

        boolean d() throws IZatIllegalArgumentException;
    }

    IZatGeofenceHandle a(Object obj, IzatGeofence izatGeofence) throws IZatIllegalArgumentException;

    Map<IZatGeofenceHandle, IzatGeofence> a() throws IZatIllegalArgumentException;

    void a(PendingIntent pendingIntent) throws IZatIllegalArgumentException;

    void a(IZatGeofenceCallback iZatGeofenceCallback) throws IZatIllegalArgumentException;

    void a(IZatGeofenceHandle iZatGeofenceHandle) throws IZatIllegalArgumentException;

    void b(PendingIntent pendingIntent) throws IZatIllegalArgumentException;

    void b(IZatGeofenceCallback iZatGeofenceCallback) throws IZatIllegalArgumentException;

    public static class IzatGeofence {

        /* renamed from: a  reason: collision with root package name */
        private int f8600a;
        private double b;
        private double c;
        private double d;
        private IzatGeofenceTransitionTypes e;
        private IzatGeofenceConfidence f;
        private IzatDwellNotify g;
        private int h;

        public IzatGeofence(double d2, double d3, double d4) {
            this(Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(d4));
        }

        public IzatGeofence(Double d2, Double d3, Double d4) {
            this.h = 0;
            if (d2 != null) {
                this.b = d2.doubleValue();
                this.h |= 1;
            }
            if (d3 != null) {
                this.c = d3.doubleValue();
                this.h |= 2;
            }
            if (d4 != null) {
                this.d = d4.doubleValue();
                this.h |= 4;
            }
            this.f8600a = 1000;
            this.e = IzatGeofenceTransitionTypes.UNKNOWN;
            this.f = IzatGeofenceConfidence.LOW;
        }

        public void a() {
            this.h = 0;
        }

        public int b() {
            return this.h;
        }

        public double c() {
            return this.b;
        }

        public double d() {
            return this.c;
        }

        public double e() {
            return this.d;
        }

        public void a(IzatGeofenceTransitionTypes izatGeofenceTransitionTypes) {
            this.e = izatGeofenceTransitionTypes;
            this.h |= 8;
        }

        public IzatGeofenceTransitionTypes f() {
            return this.e;
        }

        public void a(int i) {
            this.f8600a = i;
            this.h |= 64;
        }

        public int g() {
            return this.f8600a;
        }

        public void a(IzatGeofenceConfidence izatGeofenceConfidence) {
            this.f = izatGeofenceConfidence;
            this.h |= 16;
        }

        public IzatGeofenceConfidence h() {
            return this.f;
        }

        public void a(IzatDwellNotify izatDwellNotify) {
            this.g = izatDwellNotify;
            this.h |= 32;
        }

        public IzatDwellNotify i() {
            return this.g;
        }
    }

    public enum IzatGeofenceTransitionTypes {
        UNKNOWN(0),
        ENTERED_ONLY(1),
        EXITED_ONLY(2),
        ENTERED_AND_EXITED(3);
        
        private final int value;

        private IzatGeofenceTransitionTypes(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum IzatGeofenceConfidence {
        LOW(1),
        MEDIUM(2),
        HIGH(3);
        
        private final int value;

        private IzatGeofenceConfidence(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static class IzatDwellNotify {

        /* renamed from: a  reason: collision with root package name */
        public static final int f8599a = 1;
        public static final int b = 2;
        int c;
        int d;

        public IzatDwellNotify(int i, int i2) {
            this.c = i;
            this.d = i2;
        }

        public int a() {
            return this.c;
        }

        public int b() {
            return this.d;
        }
    }
}
