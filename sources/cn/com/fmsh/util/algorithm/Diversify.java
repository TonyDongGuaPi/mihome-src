package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class Diversify {
    private static FMLog log = LogFactory.getInstance().getLog();

    public static byte[] singleLength(byte[] bArr, byte[] bArr2, int i) {
        if (bArr == null) {
            log.warn(Diversify.class.getName(), "分散产生密钥是，父密钥为null");
            return null;
        } else if (i == 0) {
            return bArr;
        } else {
            if (i > 0) {
                if (bArr2 == null) {
                    log.warn(Diversify.class.getName(), "分散产生密钥时，分散数据为null");
                    return null;
                }
                int length = bArr2.length;
                if (length % 8 != 0) {
                    log.warn(Diversify.class.getName(), "分散产生密钥时，分散数据长度不合法");
                    return null;
                } else if (length / 8 != i) {
                    log.warn(Diversify.class.getName(), "分散产生密钥时，分散数据长度不合法");
                    return null;
                }
            }
            byte[] encrypt4des = DES.encrypt4des(bArr, FM_Bytes.copyOfRange(bArr2, 0, 8));
            int i2 = 1;
            while (i2 < i) {
                int i3 = i2 * 8;
                i2++;
                encrypt4des = DES.encrypt4des(encrypt4des, FM_Bytes.copyOfRange(bArr2, i3, i2 * 8));
            }
            return encrypt4des;
        }
    }

    public static byte[] doubleLength(byte[] bArr, byte[] bArr2, int i) {
        if (bArr == null) {
            log.warn(Diversify.class.getName(), "分散产生密钥是，父密钥为null");
            return null;
        } else if (i == 0) {
            return bArr;
        } else {
            if (i > 0) {
                if (bArr2 == null) {
                    log.warn(Diversify.class.getName(), "分散产生密钥时，分散数据为null");
                    return null;
                }
                int length = bArr2.length;
                if (length % 8 != 0) {
                    log.warn(Diversify.class.getName(), "分散产生密钥时，分散数据长度不合法");
                    return null;
                } else if (length / 8 != i) {
                    log.warn(Diversify.class.getName(), "分散产生密钥时，分散数据长度不合法");
                    return null;
                }
            }
            byte[] copyOfRange = FM_Bytes.copyOfRange(bArr2, 0, 8);
            byte[] join = FM_Bytes.join(DES.encrypt4des3(bArr, copyOfRange), DES.encrypt4des3(bArr, FM_Bytes.not(copyOfRange)));
            int i2 = 1;
            while (i2 < i) {
                int i3 = i2 * 8;
                i2++;
                byte[] copyOfRange2 = FM_Bytes.copyOfRange(bArr2, i3, i2 * 8);
                join = FM_Bytes.join(DES.encrypt4des3(join, copyOfRange2), DES.encrypt4des3(join, FM_Bytes.not(copyOfRange2)));
            }
            return join;
        }
    }
}
