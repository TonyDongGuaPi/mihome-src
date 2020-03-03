package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class Util4Certificate {
    private static FMLog log = LogFactory.getInstance().getLog();

    public static Certificate decodeCertificate(byte[] bArr) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
        } catch (CertificateException e) {
            log.error(DES.class.getName(), Util4Java.getExceptionInfo(e));
            return null;
        }
    }

    public static Certificate getCertificate(InputStream inputStream) throws Exception {
        return CertificateFactory.getInstance("X509").generateCertificate(inputStream);
    }
}
