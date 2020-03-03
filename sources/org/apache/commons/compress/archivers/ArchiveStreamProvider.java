package org.apache.commons.compress.archivers;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

public interface ArchiveStreamProvider {
    ArchiveInputStream a(String str, InputStream inputStream, String str2) throws ArchiveException;

    ArchiveOutputStream a(String str, OutputStream outputStream, String str2) throws ArchiveException;

    Set<String> f();

    Set<String> g();
}
