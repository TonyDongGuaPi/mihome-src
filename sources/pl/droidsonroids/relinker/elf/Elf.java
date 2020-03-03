package pl.droidsonroids.relinker.elf;

import java.io.IOException;

public interface Elf {

    public static abstract class DynamicStructure {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11985a = 0;
        public static final int b = 1;
        public static final int c = 5;
        public long d;
        public long e;
    }

    public static abstract class Header {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11986a = 1;
        public static final int b = 2;
        public static final int c = 2;
        public boolean d;
        public int e;
        public long f;
        public long g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int l;

        public abstract DynamicStructure a(long j2, int i2) throws IOException;

        public abstract ProgramHeader a(long j2) throws IOException;

        public abstract SectionHeader a(int i2) throws IOException;
    }

    public static abstract class ProgramHeader {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11987a = 1;
        public static final int b = 2;
        public long c;
        public long d;
        public long e;
        public long f;
    }

    public static abstract class SectionHeader {

        /* renamed from: a  reason: collision with root package name */
        public long f11988a;
    }
}
