package com.alipay.mobile.security.bio.utils;

import android.util.Base64;
import com.miuipub.internal.hybrid.SignUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.lang.CharUtils;

public class RSASign {
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    public static boolean doCheck(byte[] bArr, byte[] bArr2, InputStream inputStream) {
        try {
            RSAPublicKey a2 = a(inputStream);
            Signature instance = Signature.getInstance(SIGN_ALGORITHMS);
            instance.initVerify(a2);
            instance.update(bArr);
            return instance.verify(bArr2);
        } catch (SignatureException e) {
            BioLog.e(e.toString());
            return false;
        } catch (Exception e2) {
            BioLog.e(e2.toString());
            return false;
        }
    }

    private static RSAPublicKey a(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return a(sb.toString());
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

    private static RSAPublicKey a(String str) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance(SignUtils.f8267a).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException unused) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException unused2) {
            throw new Exception("公钥非法");
        } catch (NullPointerException unused3) {
            throw new Exception("公钥数据为空");
        }
    }
}
