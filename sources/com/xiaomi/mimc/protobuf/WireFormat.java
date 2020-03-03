package com.xiaomi.mimc.protobuf;

import java.io.IOException;

public final class WireFormat {

    /* renamed from: a  reason: collision with root package name */
    public static final int f11355a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    static final int g = 3;
    static final int h = 7;
    static final int i = 1;
    static final int j = 2;
    static final int k = 3;
    static final int l = a(1, 3);
    static final int m = a(1, 4);
    static final int n = a(2, 0);
    static final int o = a(3, 2);

    enum Utf8Validation {
        LOOSE {
            /* access modifiers changed from: package-private */
            public Object readString(CodedInputStream codedInputStream) throws IOException {
                return codedInputStream.l();
            }
        },
        STRICT {
            /* access modifiers changed from: package-private */
            public Object readString(CodedInputStream codedInputStream) throws IOException {
                return codedInputStream.m();
            }
        },
        LAZY {
            /* access modifiers changed from: package-private */
            public Object readString(CodedInputStream codedInputStream) throws IOException {
                return codedInputStream.n();
            }
        };

        /* access modifiers changed from: package-private */
        public abstract Object readString(CodedInputStream codedInputStream) throws IOException;
    }

    public static int a(int i2) {
        return i2 & 7;
    }

    static int a(int i2, int i3) {
        return (i2 << 3) | i3;
    }

    public static int b(int i2) {
        return i2 >>> 3;
    }

    private WireFormat() {
    }

    public enum JavaType {
        INT(0),
        LONG(0L),
        FLOAT(Float.valueOf(0.0f)),
        DOUBLE(Double.valueOf(0.0d)),
        BOOLEAN(false),
        STRING(""),
        BYTE_STRING(ByteString.EMPTY),
        ENUM((String) null),
        MESSAGE((String) null);
        
        private final Object defaultDefault;

        private JavaType(Object obj) {
            this.defaultDefault = obj;
        }

        /* access modifiers changed from: package-private */
        public Object getDefaultDefault() {
            return this.defaultDefault;
        }
    }

    public enum FieldType {
        DOUBLE(JavaType.DOUBLE, 1),
        FLOAT(JavaType.FLOAT, 5),
        INT64(JavaType.LONG, 0),
        UINT64(JavaType.LONG, 0),
        INT32(JavaType.INT, 0),
        FIXED64(JavaType.LONG, 1),
        FIXED32(JavaType.INT, 5),
        BOOL(JavaType.BOOLEAN, 0),
        STRING(JavaType.STRING, 2) {
            public boolean isPackable() {
                return false;
            }
        },
        GROUP(JavaType.MESSAGE, 3) {
            public boolean isPackable() {
                return false;
            }
        },
        MESSAGE(JavaType.MESSAGE, 2) {
            public boolean isPackable() {
                return false;
            }
        },
        BYTES(JavaType.BYTE_STRING, 2) {
            public boolean isPackable() {
                return false;
            }
        },
        UINT32(JavaType.INT, 0),
        ENUM(JavaType.ENUM, 0),
        SFIXED32(JavaType.INT, 5),
        SFIXED64(JavaType.LONG, 1),
        SINT32(JavaType.INT, 0),
        SINT64(JavaType.LONG, 0);
        
        private final JavaType javaType;
        private final int wireType;

        public boolean isPackable() {
            return true;
        }

        private FieldType(JavaType javaType2, int i) {
            this.javaType = javaType2;
            this.wireType = i;
        }

        public JavaType getJavaType() {
            return this.javaType;
        }

        public int getWireType() {
            return this.wireType;
        }
    }

    static Object a(CodedInputStream codedInputStream, FieldType fieldType, Utf8Validation utf8Validation) throws IOException {
        switch (fieldType) {
            case DOUBLE:
                return Double.valueOf(codedInputStream.d());
            case FLOAT:
                return Float.valueOf(codedInputStream.e());
            case INT64:
                return Long.valueOf(codedInputStream.g());
            case UINT64:
                return Long.valueOf(codedInputStream.f());
            case INT32:
                return Integer.valueOf(codedInputStream.h());
            case FIXED64:
                return Long.valueOf(codedInputStream.i());
            case FIXED32:
                return Integer.valueOf(codedInputStream.j());
            case BOOL:
                return Boolean.valueOf(codedInputStream.k());
            case BYTES:
                return codedInputStream.n();
            case UINT32:
                return Integer.valueOf(codedInputStream.q());
            case SFIXED32:
                return Integer.valueOf(codedInputStream.s());
            case SFIXED64:
                return Long.valueOf(codedInputStream.t());
            case SINT32:
                return Integer.valueOf(codedInputStream.u());
            case SINT64:
                return Long.valueOf(codedInputStream.v());
            case STRING:
                return utf8Validation.readString(codedInputStream);
            case GROUP:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case MESSAGE:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case ENUM:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }
}
