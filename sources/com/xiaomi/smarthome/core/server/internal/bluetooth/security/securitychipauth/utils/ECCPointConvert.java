package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils;

import com.unionpay.tsmservice.data.Constant;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.util.Arrays;

public class ECCPointConvert {

    /* renamed from: a  reason: collision with root package name */
    private static final byte f14466a = 4;

    public static ECPublicKey a(byte[] bArr, ECParameterSpec eCParameterSpec) throws Exception {
        if (bArr[0] == 4) {
            int bitLength = ((eCParameterSpec.getOrder().bitLength() + 8) - 1) / 8;
            if (bArr.length == (bitLength * 2) + 1) {
                int i = 1 + bitLength;
                return (ECPublicKey) KeyFactory.getInstance("EC").generatePublic(new ECPublicKeySpec(new ECPoint(new BigInteger(1, Arrays.copyOfRange(bArr, 1, i)), new BigInteger(1, Arrays.copyOfRange(bArr, i, bitLength + i))), eCParameterSpec));
            }
            throw new IllegalArgumentException("Invalid uncompressedPoint encoding, not the correct size");
        }
        throw new IllegalArgumentException("Invalid uncompressedPoint encoding, no uncompressed point indicator");
    }

    public static byte[] a(ECPublicKey eCPublicKey) {
        int bitLength = ((eCPublicKey.getParams().getOrder().bitLength() + 8) - 1) / 8;
        byte[] bArr = new byte[((bitLength * 2) + 1)];
        bArr[0] = 4;
        byte[] byteArray = eCPublicKey.getW().getAffineX().toByteArray();
        if (byteArray.length <= bitLength) {
            System.arraycopy(byteArray, 0, bArr, (1 + bitLength) - byteArray.length, byteArray.length);
        } else if (byteArray.length == bitLength + 1 && byteArray[0] == 0) {
            System.arraycopy(byteArray, 1, bArr, 1, bitLength);
        } else {
            throw new IllegalStateException("x value is too large");
        }
        int i = 1 + bitLength;
        byte[] byteArray2 = eCPublicKey.getW().getAffineY().toByteArray();
        if (byteArray2.length <= bitLength) {
            System.arraycopy(byteArray2, 0, bArr, (i + bitLength) - byteArray2.length, byteArray2.length);
        } else if (byteArray2.length == bitLength + 1 && byteArray2[0] == 0) {
            System.arraycopy(byteArray2, 1, bArr, i, bitLength);
        } else {
            throw new IllegalStateException("y value is too large");
        }
        return bArr;
    }

    public static byte[] a(char[] cArr) throws IllegalArgumentException {
        int length = cArr.length;
        if ((length & 1) == 0) {
            byte[] bArr = new byte[(length >> 1)];
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i + 1;
                i = i3 + 1;
                bArr[i2] = (byte) (((a(cArr[i], i) << 4) | a(cArr[i3], i3)) & 255);
                i2++;
            }
            return bArr;
        }
        throw new IllegalArgumentException("Odd number of characters.");
    }

    protected static int a(char c, int i) throws IllegalArgumentException {
        int digit = Character.digit(c, 16);
        if (digit != -1) {
            return digit;
        }
        throw new IllegalArgumentException("Illegal hexadecimal character " + c + " at index " + i);
    }

    public static byte[] b(ECPublicKey eCPublicKey) throws IllegalArgumentException {
        ECPoint w = eCPublicKey.getW();
        String bigInteger = w.getAffineX().toString(16);
        String bigInteger2 = w.getAffineY().toString(16);
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.RECHARGE_MODE_DESIGNATED_AND_CACH);
        for (int i = 0; i < 64 - bigInteger.length(); i++) {
            sb.append(0);
        }
        sb.append(bigInteger);
        for (int i2 = 0; i2 < 64 - bigInteger2.length(); i2++) {
            sb.append(0);
        }
        sb.append(bigInteger2);
        return a(sb.toString().toCharArray());
    }

    public static byte[] a(ECPrivateKey eCPrivateKey) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        String bigInteger = eCPrivateKey.getS().toString(16);
        for (int i = 0; i < 64 - bigInteger.length(); i++) {
            sb.append(0);
        }
        sb.append(bigInteger);
        return a(sb.toString().toCharArray());
    }
}
