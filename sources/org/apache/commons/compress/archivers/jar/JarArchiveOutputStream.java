package org.apache.commons.compress.archivers.jar;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.JarMarker;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipExtraField;

public class JarArchiveOutputStream extends ZipArchiveOutputStream {
    private boolean p = false;

    public JarArchiveOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public JarArchiveOutputStream(OutputStream outputStream, String str) {
        super(outputStream);
        a(str);
    }

    public void a(ArchiveEntry archiveEntry) throws IOException {
        if (!this.p) {
            ((ZipArchiveEntry) archiveEntry).b((ZipExtraField) JarMarker.a());
            this.p = true;
        }
        super.a(archiveEntry);
    }
}
