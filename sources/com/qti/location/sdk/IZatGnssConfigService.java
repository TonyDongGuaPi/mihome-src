package com.qti.location.sdk;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface IZatGnssConfigService {

    public interface IZatGnssConfigCallback {
        void a(Set<IzatGnssSvType> set, Set<IzatGnssSvType> set2);
    }

    void a();

    void a(IZatGnssConfigCallback iZatGnssConfigCallback) throws IZatIllegalArgumentException;

    void a(Set<IzatGnssSvType> set, Set<IzatGnssSvType> set2) throws IZatIllegalArgumentException;

    public enum IzatGnssSvType {
        UNKNOWN(0),
        GPS(1),
        SBAS(2),
        GLONASS(3),
        QZSS(4),
        BEIDOU(5),
        GALILEO(6);
        
        private static final Map<Integer, IzatGnssSvType> valueMap = null;
        private final int value;

        static {
            int i;
            valueMap = new HashMap();
            for (IzatGnssSvType izatGnssSvType : values()) {
                valueMap.put(Integer.valueOf(izatGnssSvType.value), izatGnssSvType);
            }
        }

        private IzatGnssSvType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        protected static IzatGnssSvType fromInt(int i) {
            return valueMap.get(Integer.valueOf(i));
        }
    }
}
