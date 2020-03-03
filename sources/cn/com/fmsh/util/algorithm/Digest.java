package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Digest {
    private static FMLog log = LogFactory.getInstance().getLog();

    public static byte[] md5(byte[] bArr) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error(Digest.class.getName(), "初始化失败，MessageDigest不支持MD5。");
            log.error(Digest.class.getName(), Util4Java.getExceptionInfo(e));
            messageDigest = null;
        }
        messageDigest.update(bArr);
        return messageDigest.digest();
    }

    public static byte[] sha(byte[] bArr) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            log.error(Digest.class.getName(), "初始化失败，MessageDigest不支持SHA。");
            log.error(Digest.class.getName(), Util4Java.getExceptionInfo(e));
            messageDigest = null;
        }
        messageDigest.update(bArr);
        return messageDigest.digest();
    }

    public static void main(String[] strArr) {
        byte[] bArr = new byte[64];
        new Random().nextBytes(bArr);
        byte[] md5 = md5(bArr);
        PrintStream printStream = System.out;
        printStream.println("length:" + md5.length);
        System.out.println(FM_Bytes.bytesToHexString(md5));
    }
}
