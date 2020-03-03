package com.alipay.mobile.security.bio.security;

import android.util.Base64;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.google.code.microlog4android.format.PatternFormatter;
import com.miuipub.internal.hybrid.SignUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.lang.CharUtils;

public class RSAEncrypt {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f997a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f'};
    public static final String privateKeyName = "privateKey.keystore";
    public static final String publickKeyName = "publicKey.keystore";

    public static void genKeyPair(String str) {
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(SignUtils.f8267a);
        } catch (NoSuchAlgorithmException e) {
            BioLog.e(e.toString());
            keyPairGenerator = null;
        }
        keyPairGenerator.initialize(2048, new SecureRandom());
        KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) generateKeyPair.getPrivate();
        try {
            String encodeToString = Base64.encodeToString(((RSAPublicKey) generateKeyPair.getPublic()).getEncoded(), 0);
            String encodeToString2 = Base64.encodeToString(rSAPrivateKey.getEncoded(), 0);
            FileWriter fileWriter = new FileWriter(str + "/" + "publicKey.keystore");
            FileWriter fileWriter2 = new FileWriter(str + "/" + "privateKey.keystore");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
            bufferedWriter.write(encodeToString);
            bufferedWriter2.write(encodeToString2);
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
            bufferedWriter2.flush();
            bufferedWriter2.close();
            fileWriter2.close();
        } catch (Exception e2) {
            BioLog.e(e2.toString());
        }
    }

    public static RSAPublicKey loadPublicKey(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return loadPublicKeyByStr(sb.toString());
                }
                if (readLine.charAt(0) != '-') {
                    sb.append(readLine);
                    sb.append(CharUtils.b);
                }
            }
        } catch (IOException unused) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException unused2) {
            throw new Exception("公钥输入流为空");
        }
    }

    public static String loadPublicKeyByFile(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str + "/" + "publicKey.keystore"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return sb.toString();
                } else if (readLine.charAt(0) != '-') {
                    sb.append(readLine);
                    sb.append(CharUtils.b);
                }
            }
        } catch (IOException unused) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException unused2) {
            throw new Exception("公钥输入流为空");
        }
    }

    public static RSAPublicKey loadPublicKeyByStr(String str) {
        StringBuilder sb = new StringBuilder();
        for (String str2 : str.split("\n")) {
            if (str2.charAt(0) != '-') {
                sb.append(str2);
                sb.append(CharUtils.b);
            }
        }
        try {
            return (RSAPublicKey) KeyFactory.getInstance(SignUtils.f8267a).generatePublic(new X509EncodedKeySpec(Base64.decode(sb.toString(), 0)));
        } catch (NoSuchAlgorithmException unused) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException unused2) {
            throw new Exception("公钥非法");
        } catch (NullPointerException unused3) {
            throw new Exception("公钥数据为空");
        }
    }

    public static RSAPrivateKey loadPrivateKey(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return loadPrivateKeyByStr(sb.toString());
                }
                if (readLine.charAt(0) != '-') {
                    sb.append(readLine);
                    sb.append(CharUtils.b);
                }
            }
        } catch (IOException unused) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException unused2) {
            throw new Exception("私钥输入流为空");
        }
    }

    public static String loadPrivateKeyByFile(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str + "/" + "privateKey.keystore"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return sb.toString();
                } else if (readLine.charAt(0) != '-') {
                    sb.append(readLine);
                    sb.append(CharUtils.b);
                }
            }
        } catch (IOException unused) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException unused2) {
            throw new Exception("私钥输入流为空");
        }
    }

    public static RSAPrivateKey loadPrivateKeyByStr(String str) {
        try {
            return (RSAPrivateKey) KeyFactory.getInstance(SignUtils.f8267a).generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException unused) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException unused2) {
            throw new Exception("私钥非法");
        } catch (NullPointerException unused3) {
            throw new Exception("私钥数据为空");
        }
    }

    public static byte[] encrypt(RSAPublicKey rSAPublicKey, byte[] bArr) {
        if (rSAPublicKey != null) {
            try {
                Cipher instance = Cipher.getInstance(SignUtils.b);
                instance.init(1, rSAPublicKey);
                return instance.doFinal(bArr);
            } catch (NoSuchAlgorithmException unused) {
                throw new Exception("无此加密算法");
            } catch (NoSuchPaddingException e) {
                BioLog.e(e.toString());
                return null;
            } catch (InvalidKeyException unused2) {
                throw new Exception("加密公钥非法,请检查");
            } catch (IllegalBlockSizeException unused3) {
                throw new Exception("明文长度非法");
            } catch (BadPaddingException unused4) {
                throw new Exception("明文数据已损坏");
            }
        } else {
            throw new Exception("加密公钥为空, 请设置");
        }
    }

    public static byte[] encrypt(RSAPrivateKey rSAPrivateKey, byte[] bArr) {
        if (rSAPrivateKey != null) {
            try {
                Cipher instance = Cipher.getInstance(SignUtils.b);
                instance.init(1, rSAPrivateKey);
                return instance.doFinal(bArr);
            } catch (NoSuchAlgorithmException unused) {
                throw new Exception("无此加密算法");
            } catch (NoSuchPaddingException e) {
                BioLog.e(e.toString());
                return null;
            } catch (InvalidKeyException unused2) {
                throw new Exception("加密私钥非法,请检查");
            } catch (IllegalBlockSizeException unused3) {
                throw new Exception("明文长度非法");
            } catch (BadPaddingException unused4) {
                throw new Exception("明文数据已损坏");
            }
        } else {
            throw new Exception("加密私钥为空, 请设置");
        }
    }

    public static byte[] decrypt(RSAPrivateKey rSAPrivateKey, byte[] bArr) {
        if (rSAPrivateKey != null) {
            try {
                Cipher instance = Cipher.getInstance(SignUtils.b);
                instance.init(2, rSAPrivateKey);
                return instance.doFinal(bArr);
            } catch (NoSuchAlgorithmException unused) {
                throw new Exception("无此解密算法");
            } catch (NoSuchPaddingException e) {
                BioLog.e(e.toString());
                return null;
            } catch (InvalidKeyException unused2) {
                throw new Exception("解密私钥非法,请检查");
            } catch (IllegalBlockSizeException unused3) {
                throw new Exception("密文长度非法");
            } catch (BadPaddingException unused4) {
                throw new Exception("密文数据已损坏");
            }
        } else {
            throw new Exception("解密私钥为空, 请设置");
        }
    }
}
