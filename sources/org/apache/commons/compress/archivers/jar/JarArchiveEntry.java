package org.apache.commons.compress.archivers.jar;

import java.security.cert.Certificate;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

public class JarArchiveEntry extends ZipArchiveEntry {
    private final Attributes e = null;
    private final Certificate[] f = null;

    public JarArchiveEntry(ZipEntry zipEntry) throws ZipException {
        super(zipEntry);
    }

    public JarArchiveEntry(String str) {
        super(str);
    }

    public JarArchiveEntry(ZipArchiveEntry zipArchiveEntry) throws ZipException {
        super(zipArchiveEntry);
    }

    public JarArchiveEntry(JarEntry jarEntry) throws ZipException {
        super((ZipEntry) jarEntry);
    }

    @Deprecated
    public Attributes b() {
        return this.e;
    }

    @Deprecated
    public Certificate[] c() {
        if (this.f == null) {
            return null;
        }
        Certificate[] certificateArr = new Certificate[this.f.length];
        System.arraycopy(this.f, 0, certificateArr, 0, certificateArr.length);
        return certificateArr;
    }
}
