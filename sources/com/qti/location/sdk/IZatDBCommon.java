package com.qti.location.sdk;

import java.util.HashMap;
import java.util.Map;

public abstract class IZatDBCommon {

    /* renamed from: a  reason: collision with root package name */
    public static final Integer f8594a = 99;
    public static final Integer b = 68;
    public static final Integer c = 99;

    public enum IZatAPBSSpecialInfoTypes {
        NO_INFO_AVAILABLE,
        MOVING_AP_BS,
        NOT_RESOLVED
    }

    public enum IZatCellTypes {
        GSM,
        WCDMA,
        CDMA,
        LTE
    }

    public static class IZatCellInfo {
        public static final int h = 1;

        /* renamed from: a  reason: collision with root package name */
        protected final int f8596a;
        protected final int b;
        protected final int c;
        protected final int d;
        protected IZatCellTypes e;
        protected int f;
        protected int g;

        public IZatCellInfo(int i, int i2, int i3, int i4, IZatCellTypes iZatCellTypes) {
            this.f8596a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = iZatCellTypes;
            this.g = 0;
        }

        public IZatCellInfo(int i, int i2, int i3, int i4, Integer num) {
            this(i, i2, i3, i4, IZatCellTypes.values()[num.intValue()]);
        }

        public int a() {
            if ((this.g & 1) != 0) {
                return this.f;
            }
            throw new IZatStaleDataException("Local timestamp is not valid");
        }

        public void a(int i) {
            this.f = i;
            this.g |= 1;
        }

        public int b() {
            return this.f8596a;
        }

        public int c() {
            return this.b;
        }

        public int d() {
            return this.c;
        }

        public int e() {
            return this.d;
        }

        public IZatCellTypes f() {
            return this.e;
        }

        public void a(IZatCellTypes iZatCellTypes) {
            this.e = iZatCellTypes;
        }
    }

    public static class IZatAPSSIDInfo {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f8595a = new byte[0];

        public IZatAPSSIDInfo(byte[] bArr, short s) {
            if (s > 0) {
                this.f8595a = (byte[]) bArr.clone();
            }
        }
    }

    public enum IZatApBsListStatus {
        STD_CONT(0),
        STD_FINAL(1),
        SCAN_FINAL(2);
        
        private static final Map<Integer, IZatApBsListStatus> typesByValue = null;
        private final int cValue;

        static {
            int i;
            typesByValue = new HashMap();
            for (IZatApBsListStatus iZatApBsListStatus : values()) {
                typesByValue.put(Integer.valueOf(iZatApBsListStatus.cValue), iZatApBsListStatus);
            }
        }

        private IZatApBsListStatus(int i) {
            this.cValue = i;
        }

        protected static IZatApBsListStatus fromInt(int i) {
            return typesByValue.get(Integer.valueOf(i));
        }
    }
}
