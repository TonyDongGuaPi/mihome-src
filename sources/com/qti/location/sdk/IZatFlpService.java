package com.qti.location.sdk;

import android.location.Location;

public interface IZatFlpService {

    public interface IFlpLocationCallback {
        void a(Location[] locationArr);
    }

    public interface IFlpStatusCallback {
        void a(IzatFlpStatus izatFlpStatus);
    }

    public interface IZatFlpSessionHandle {
        void a();

        void a(IFlpStatusCallback iFlpStatusCallback) throws IZatIllegalArgumentException, IZatFeatureNotSupportedException;

        void b();

        void c();

        void d() throws IZatFeatureNotSupportedException;

        void e() throws IZatFeatureNotSupportedException;
    }

    IZatFlpSessionHandle a(IFlpLocationCallback iFlpLocationCallback, IzatFlpRequest izatFlpRequest) throws IZatIllegalArgumentException;

    void a(IFlpLocationCallback iFlpLocationCallback) throws IZatIllegalArgumentException;

    void a(IZatFlpSessionHandle iZatFlpSessionHandle) throws IZatIllegalArgumentException;

    void b(IFlpLocationCallback iFlpLocationCallback) throws IZatIllegalArgumentException;

    public enum IzatFlpStatus {
        OUTDOOR_TRIP_COMPLETED(0),
        POSITION_AVAILABLE(1),
        POSITION_NOT_AVAILABLE(2);
        
        private final int value;

        private IzatFlpStatus(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static abstract class IzatFlpRequest {

        /* renamed from: a  reason: collision with root package name */
        long f8597a = 0;
        int b = 0;
        long c = 0;
        int d = 0;
        long e = 0;
        protected boolean f = false;

        public static IzatFlpFgRequest b() {
            return new IzatFlpFgRequest();
        }

        public static IzatFlpBgRequest c() {
            return new IzatFlpBgRequest();
        }

        public void a(long j) throws IZatIllegalArgumentException {
            if (j > 0) {
                this.f8597a = j;
                return;
            }
            throw new IZatIllegalArgumentException("invalid time interval");
        }

        public long d() {
            return this.f8597a;
        }

        public void a(int i) throws IZatIllegalArgumentException {
            if (i > 0) {
                this.b = i;
                return;
            }
            throw new IZatIllegalArgumentException("invalid distance of displacement");
        }

        public int e() {
            return this.b;
        }

        public void b(long j) throws IZatIllegalArgumentException {
            if (j > 0) {
                this.c = j;
                return;
            }
            throw new IZatIllegalArgumentException("invalid trip distance");
        }

        public long f() {
            return this.c;
        }

        public void b(int i) throws IZatIllegalArgumentException {
            if (i < 1 || i > 5) {
                throw new IZatIllegalArgumentException("invalid power mode");
            }
            this.d = i;
        }

        public int g() {
            return this.d;
        }

        public void c(long j) throws IZatIllegalArgumentException {
            if (j > 0) {
                this.e = j;
                return;
            }
            throw new IZatIllegalArgumentException("invalid tbm interval");
        }

        public long h() {
            return this.e;
        }
    }

    public static class IzatFlpFgRequest extends IzatFlpRequest {
        IzatFlpFgRequest() {
            this.f = false;
        }
    }

    public static class IzatFlpBgRequest extends IzatFlpRequest {
        private IzatFlpBgRequestMode g = IzatFlpBgRequestMode.ROUTINE_MODE;

        public enum IzatFlpBgRequestMode {
            ROUTINE_MODE(0),
            TRIP_MODE(1);
            
            private final int value;

            private IzatFlpBgRequestMode(int i) {
                this.value = i;
            }

            public int getValue() {
                return this.value;
            }
        }

        IzatFlpBgRequest() {
            this.f = true;
        }

        public void a(IzatFlpBgRequestMode izatFlpBgRequestMode) {
            this.g = izatFlpBgRequestMode;
        }

        public IzatFlpBgRequestMode a() {
            return this.g;
        }
    }
}
