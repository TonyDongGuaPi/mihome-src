package org.apache.commons.compress.archivers;

import java.util.Date;

public interface ArchiveEntry {
    public static final long t_ = -1;

    Date a();

    String getName();

    long getSize();

    boolean isDirectory();
}
