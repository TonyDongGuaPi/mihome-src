package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import com.alipay.mobile.security.bio.utils.DESCoder;
import java.lang.reflect.Array;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.cybergarage.http.HTTP;

public class DES {
    private static FMLog log = LogFactory.getInstance().getLog();

    public static void main(String[] strArr) {
    }

    public static byte[] encrypt4des(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr.length > 8) {
            bArr = FM_Bytes.copyOf(bArr, 8);
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "DES");
        try {
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(1, secretKeySpec);
            return instance.doFinal(bArr2);
        } catch (NoSuchAlgorithmException e) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e));
            return null;
        } catch (NoSuchPaddingException e2) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e2));
            return null;
        } catch (IllegalBlockSizeException e3) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e3));
            return null;
        } catch (BadPaddingException e4) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e4));
            return null;
        } catch (InvalidKeyException e5) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e5));
            return null;
        }
    }

    public static byte[] encrypt4desCBC(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr != null && bArr.length > 8) {
            bArr = FM_Bytes.copyOf(bArr, 8);
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "DES");
        try {
            Cipher instance = Cipher.getInstance("DES/CBC/NoPadding");
            instance.init(1, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr2);
        } catch (NoSuchAlgorithmException e) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e));
            return null;
        } catch (NoSuchPaddingException e2) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e2));
            return null;
        } catch (IllegalBlockSizeException e3) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e3));
            return null;
        } catch (BadPaddingException e4) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e4));
            return null;
        } catch (InvalidKeyException e5) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e5));
            return null;
        } catch (InvalidAlgorithmParameterException e6) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e6));
            return null;
        }
    }

    public static byte[] decrypt4desCBC(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "DES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        try {
            Cipher instance = Cipher.getInstance("DES/CBC/NoPadding");
            instance.init(2, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr2);
        } catch (NoSuchAlgorithmException e) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e));
            return null;
        } catch (NoSuchPaddingException e2) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e2));
            return null;
        } catch (IllegalBlockSizeException e3) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e3));
            return null;
        } catch (BadPaddingException e4) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e4));
            return null;
        } catch (InvalidKeyException e5) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e5));
            return null;
        } catch (InvalidAlgorithmParameterException e6) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e6));
            return null;
        }
    }

    public static byte[] decrypt4des(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "DES");
        try {
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(2, secretKeySpec);
            return instance.doFinal(bArr2);
        } catch (NoSuchAlgorithmException e) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e));
            return null;
        } catch (NoSuchPaddingException e2) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e2));
            return null;
        } catch (IllegalBlockSizeException e3) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e3));
            return null;
        } catch (BadPaddingException e4) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e4));
            return null;
        } catch (InvalidKeyException e5) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e5));
            return null;
        }
    }

    public static byte[] encrypt4desPadding(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr.length > 8) {
            bArr = FM_Bytes.copyOf(bArr, 8);
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "DES");
        try {
            Cipher instance = Cipher.getInstance("DES/ECB/Padding");
            instance.init(1, secretKeySpec);
            return instance.doFinal(bArr2);
        } catch (NoSuchAlgorithmException e) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e));
            return null;
        } catch (NoSuchPaddingException e2) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e2));
            return null;
        } catch (IllegalBlockSizeException e3) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e3));
            return null;
        } catch (BadPaddingException e4) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e4));
            return null;
        } catch (InvalidKeyException e5) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e5));
            return null;
        }
    }

    public static byte[] decrypt4desPadding(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "DES");
        try {
            Cipher instance = Cipher.getInstance("DES/ECB/Padding");
            instance.init(2, secretKeySpec);
            return instance.doFinal(bArr2);
        } catch (NoSuchAlgorithmException e) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e));
            return null;
        } catch (NoSuchPaddingException e2) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e2));
            return null;
        } catch (IllegalBlockSizeException e3) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e3));
            return null;
        } catch (BadPaddingException e4) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e4));
            return null;
        } catch (InvalidKeyException e5) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e5));
            return null;
        }
    }

    public static byte[] encrypt4des3(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            log.error(DES.class.getName(), "对数据进行3DES加密时，传入的KEY或者待加密的数据为null");
            return null;
        }
        if (bArr.length % 8 != 0) {
            log.error(DES.class.getName(), "对数据进行3DES加密时，传入的KEY的数据长度不合法");
        }
        byte[] copyOf = FM_Bytes.copyOf(bArr, 8);
        return encrypt4des(copyOf, decrypt4des(FM_Bytes.copyOfRange(bArr, 8, bArr.length), encrypt4des(copyOf, bArr2)));
    }

    public static byte[] encrypt4des3CBC(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr2 == null) {
            log.error(DES.class.getName(), "对数据进行3DES加密时，传入的KEY或者待加密的数据为null");
            return null;
        }
        if (bArr.length % 8 != 0) {
            log.error(DES.class.getName(), "对数据进行3DES加密时，传入的KEY的数据长度不合法");
        }
        int length = bArr2.length / 8;
        byte[][] bArr4 = (byte[][]) Array.newInstance(byte.class, new int[]{length, 8});
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                bArr4[i][i2] = bArr2[(i * 8) + i2];
            }
        }
        byte[] bArr5 = new byte[8];
        byte[] encrypt4des3 = encrypt4des3(bArr, FM_Bytes.xor(bArr4[0], bArr3));
        byte[] bArr6 = encrypt4des3;
        for (int i3 = 1; i3 < length; i3++) {
            bArr6 = encrypt4des3(bArr, FM_Bytes.xor(bArr6, bArr4[i3]));
            encrypt4des3 = FM_Bytes.join(encrypt4des3, bArr6);
        }
        return encrypt4des3;
    }

    public static byte[] encrypt4des3Padding(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            log.error(DES.class.getName(), "对数据进行3DES加密时，传入的KEY或者待加密的数据为null");
            return null;
        }
        if (bArr.length % 8 != 0) {
            log.error(DES.class.getName(), "对数据进行3DES加密时，传入的KEY的数据长度不合法");
        }
        byte[] copyOf = FM_Bytes.copyOf(bArr, 8);
        return encrypt4desPadding(copyOf, decrypt4desPadding(FM_Bytes.copyOfRange(bArr, 8, bArr.length), encrypt4desPadding(copyOf, bArr2)));
    }

    public static byte[] decrypt4des3CBC(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr2 == null) {
            log.error(DES.class.getName(), "对数据进行3DES解密时，传入的KEY或者待加密的数据为null");
            return null;
        }
        if (bArr.length % 8 != 0) {
            log.error(DES.class.getName(), "对数据进行3DES解密时，传入的KEY的数据长度不合法");
        }
        int length = bArr2.length / 8;
        byte[][] bArr4 = (byte[][]) Array.newInstance(byte.class, new int[]{length, 8});
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                bArr4[i][i2] = bArr2[(i * 8) + i2];
            }
        }
        byte[] xor = FM_Bytes.xor(decrypt4des3(bArr, bArr4[0]), bArr3);
        for (int i3 = 1; i3 < length; i3++) {
            xor = FM_Bytes.join(xor, FM_Bytes.xor(decrypt4des3(bArr, bArr4[i3]), bArr4[i3 - 1]));
        }
        return xor;
    }

    public static byte[] decrypt4des3(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            log.error(DES.class.getName(), "对数据进行3DES解密时，传入的KEY或者待加密的数据为null");
            return null;
        }
        if (bArr.length % 8 != 0) {
            log.error(DES.class.getName(), "对数据进行3DES解密时，传入的KEY的数据长度不合法");
        }
        byte[] copyOf = FM_Bytes.copyOf(bArr, 8);
        return decrypt4des(copyOf, encrypt4des(FM_Bytes.copyOfRange(bArr, 8, bArr.length), decrypt4des(copyOf, bArr2)));
    }

    public static void showArray(byte[] bArr) {
        for (byte b : bArr) {
            System.out.print(String.valueOf(b) + HTTP.TAB);
        }
        System.out.println();
    }

    public static byte[] javaDes3(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, DESCoder.ALGORITHM);
        try {
            Cipher instance = Cipher.getInstance(DESCoder.ALGORITHM);
            instance.init(1, secretKeySpec);
            return instance.doFinal(bArr2);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (NoSuchPaddingException e2) {
            System.out.println(e2.getMessage());
            return null;
        } catch (IllegalBlockSizeException e3) {
            System.out.println(e3.getMessage());
            return null;
        } catch (BadPaddingException e4) {
            System.out.println(e4.getMessage());
            return null;
        } catch (InvalidKeyException e5) {
            System.out.println(e5.getMessage());
            return null;
        }
    }
}
