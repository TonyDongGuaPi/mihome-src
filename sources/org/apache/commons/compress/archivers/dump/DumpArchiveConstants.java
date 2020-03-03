package org.apache.commons.compress.archivers.dump;

public final class DumpArchiveConstants {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3212a = 1024;
    public static final int b = 10;
    public static final int c = 32;
    public static final int d = 60011;
    public static final int e = 60012;
    public static final int f = 424935705;
    public static final int g = 84446;
    public static final int h = 16;
    public static final int i = 64;

    private DumpArchiveConstants() {
    }

    public enum SEGMENT_TYPE {
        TAPE(1),
        INODE(2),
        BITS(3),
        ADDR(4),
        END(5),
        CLRI(6);
        
        int code;

        private SEGMENT_TYPE(int i) {
            this.code = i;
        }

        public static SEGMENT_TYPE find(int i) {
            for (SEGMENT_TYPE segment_type : values()) {
                if (segment_type.code == i) {
                    return segment_type;
                }
            }
            return null;
        }
    }

    public enum COMPRESSION_TYPE {
        ZLIB(0),
        BZLIB(1),
        LZO(2);
        
        int code;

        private COMPRESSION_TYPE(int i) {
            this.code = i;
        }

        public static COMPRESSION_TYPE find(int i) {
            for (COMPRESSION_TYPE compression_type : values()) {
                if (compression_type.code == i) {
                    return compression_type;
                }
            }
            return null;
        }
    }
}
