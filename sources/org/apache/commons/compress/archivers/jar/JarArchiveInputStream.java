package org.apache.commons.compress.archivers.jar;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

public class JarArchiveInputStream extends ZipArchiveInputStream {
    public JarArchiveInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public JarArchiveInputStream(InputStream inputStream, String str) {
        super(inputStream, str);
    }

    public JarArchiveEntry d() throws IOException {
        ZipArchiveEntry e = e();
        if (e == null) {
            return null;
        }
        return new JarArchiveEntry(e);
    }

    public ArchiveEntry a() throws IOException {
        return d();
    }

    public static boolean a(byte[] bArr, int i) {
        return ZipArchiveInputStream.b(bArr, i);
    }
}
