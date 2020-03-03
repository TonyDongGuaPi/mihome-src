package com.xiaomi.smarthome.smartconfig.step;

import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.ECCPointConvert;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.Hkdf;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.SecurityChipUtil;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import javax.crypto.SecretKey;

public enum ECCurve {
    SECP192R1(1, "secp192r1"),
    SECP224R1(2, "secp224r1"),
    SECP256R1(3, "secp256r1"),
    SECP384R1(4, "secp384r1"),
    SECP521R1(5, "secp521r1"),
    SECP192K1(10, "secp192k1"),
    SECP224K1(11, "secp224k1"),
    SECP256K1(12, "secp256k1");
    
    private KeyPairGenerator generator;
    private int index;
    private KeyPair keyPair;
    private String name;

    private ECCurve(int i, String str) {
        this.generator = null;
        this.name = str;
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    public static ECCurve search(int i) {
        ECCurve[] values = values();
        int length = values.length;
        int i2 = 0;
        while (i2 < length) {
            ECCurve eCCurve = values[i2];
            if (eCCurve.index == i) {
                try {
                    eCCurve.createGenerator();
                    return eCCurve;
                } catch (Exception unused) {
                    return null;
                }
            } else {
                i2++;
            }
        }
        return null;
    }

    public KeyPair generateKeyPair() {
        if (this.generator == null) {
            try {
                createGenerator();
            } catch (Exception unused) {
                return null;
            }
        }
        this.keyPair = this.generator.generateKeyPair();
        return this.keyPair;
    }

    private void createGenerator() throws Exception {
        this.generator = KeyPairGenerator.getInstance("EC");
        this.generator.initialize(new ECGenParameterSpec(this.name));
    }

    public byte[] verify(SignType signType, String str, byte[] bArr, byte[] bArr2) {
        try {
            ECPublicKey eCPublicKey = (ECPublicKey) this.keyPair.getPublic();
            SecretKey a2 = SecurityChipUtil.a((PublicKey) ECCPointConvert.a(bArr, eCPublicKey.getParams()), this.keyPair.getPrivate());
            Hkdf a3 = Hkdf.a("HmacSHA256");
            a3.a(a2.getEncoded(), str.getBytes());
            byte[] a4 = a3.a("".getBytes(), 16);
            if (SignType.HMACSHA256 == signType) {
                if (ByteUtils.a(SecurityChipUtil.a(a4, bArr), bArr2)) {
                    return a4;
                }
                return null;
            } else if (SignType.ECDSASHA256 != signType) {
                return null;
            } else {
                Signature instance = Signature.getInstance("SHA256WithECDSA");
                instance.initVerify(eCPublicKey);
                instance.update(bArr);
                if (instance.verify(bArr2)) {
                    return a4;
                }
                return null;
            }
        } catch (Exception e) {
            Log.e(AppMeasurement.Param.FATAL, "ECCurve.verify", e);
            return null;
        }
    }

    public enum SignType {
        ECDSASHA256(2),
        HMACSHA256(1);
        
        private final int type;

        private SignType(int i) {
            this.type = i;
        }

        public static SignType index(int i) {
            switch (i) {
                case 1:
                    return HMACSHA256;
                case 2:
                    return ECDSASHA256;
                default:
                    return null;
            }
        }
    }
}
