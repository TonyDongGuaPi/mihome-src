package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class DSA {
    private static final String KEY_ALGORITHM = "DSA";
    private static final String PRIVATE_KEY = "PrivateKey";
    private static final String PUBLIC_KEY = "PublicKey";
    private static FMLog log = LogFactory.getInstance().getLog();

    public static Map<String, byte[]> generateKey(int i) {
        KeyPairGenerator keyPairGenerator;
        HashMap hashMap = new HashMap();
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            if (log != null) {
                log.error(RSA.class.getName(), "RSA产生密钥出现异常");
                log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            }
            System.out.println(Util4Java.getExceptionInfo(e));
            keyPairGenerator = null;
        }
        if (keyPairGenerator == null) {
            return null;
        }
        keyPairGenerator.initialize(i, new SecureRandom());
        KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
        hashMap.put(PUBLIC_KEY, generateKeyPair.getPublic().getEncoded());
        hashMap.put(PRIVATE_KEY, generateKeyPair.getPrivate().getEncoded());
        return hashMap;
    }

    public static void main(String[] strArr) {
        new DSA();
        generateKey(1024);
    }
}
