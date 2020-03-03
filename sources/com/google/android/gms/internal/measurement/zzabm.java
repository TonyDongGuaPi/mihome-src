package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzabm {
    private static final Logger logger = Logger.getLogger(zzabm.class.getName());
    private static final Class<?> zzbqv = zzyv.zzsw();
    private static final Unsafe zzbtj = zzur();
    private static final boolean zzbun = zzk(Long.TYPE);
    private static final boolean zzbuo = zzk(Integer.TYPE);
    private static final zzd zzbup;
    private static final boolean zzbuq = zzut();
    private static final boolean zzbur = zzus();
    private static final long zzbus = ((long) zzi(byte[].class));
    private static final long zzbut = ((long) zzi(boolean[].class));
    private static final long zzbuu = ((long) zzj(boolean[].class));
    private static final long zzbuv = ((long) zzi(int[].class));
    private static final long zzbuw = ((long) zzj(int[].class));
    private static final long zzbux = ((long) zzi(long[].class));
    private static final long zzbuy = ((long) zzj(long[].class));
    private static final long zzbuz = ((long) zzi(float[].class));
    private static final long zzbva = ((long) zzj(float[].class));
    private static final long zzbvb = ((long) zzi(double[].class));
    private static final long zzbvc = ((long) zzj(double[].class));
    private static final long zzbvd = ((long) zzi(Object[].class));
    private static final long zzbve = ((long) zzj(Object[].class));
    private static final long zzbvf = zza(zzuu());
    private static final long zzbvg;
    private static final boolean zzbvh = (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN);

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }
    }

    static abstract class zzd {
        Unsafe zzbvi;

        zzd(Unsafe unsafe) {
            this.zzbvi = unsafe;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0106  */
    static {
        /*
            java.lang.Class<com.google.android.gms.internal.measurement.zzabm> r0 = com.google.android.gms.internal.measurement.zzabm.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            logger = r0
            sun.misc.Unsafe r0 = zzur()
            zzbtj = r0
            java.lang.Class r0 = com.google.android.gms.internal.measurement.zzyv.zzsw()
            zzbqv = r0
            java.lang.Class r0 = java.lang.Long.TYPE
            boolean r0 = zzk(r0)
            zzbun = r0
            java.lang.Class r0 = java.lang.Integer.TYPE
            boolean r0 = zzk(r0)
            zzbuo = r0
            sun.misc.Unsafe r0 = zzbtj
            r1 = 0
            if (r0 != 0) goto L_0x002f
        L_0x002d:
            r0 = r1
            goto L_0x0054
        L_0x002f:
            boolean r0 = com.google.android.gms.internal.measurement.zzyv.zzsv()
            if (r0 == 0) goto L_0x004d
            boolean r0 = zzbun
            if (r0 == 0) goto L_0x0041
            com.google.android.gms.internal.measurement.zzabm$zzb r0 = new com.google.android.gms.internal.measurement.zzabm$zzb
            sun.misc.Unsafe r2 = zzbtj
            r0.<init>(r2)
            goto L_0x0054
        L_0x0041:
            boolean r0 = zzbuo
            if (r0 == 0) goto L_0x002d
            com.google.android.gms.internal.measurement.zzabm$zza r0 = new com.google.android.gms.internal.measurement.zzabm$zza
            sun.misc.Unsafe r2 = zzbtj
            r0.<init>(r2)
            goto L_0x0054
        L_0x004d:
            com.google.android.gms.internal.measurement.zzabm$zzc r0 = new com.google.android.gms.internal.measurement.zzabm$zzc
            sun.misc.Unsafe r2 = zzbtj
            r0.<init>(r2)
        L_0x0054:
            zzbup = r0
            boolean r0 = zzut()
            zzbuq = r0
            boolean r0 = zzus()
            zzbur = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzi(r0)
            long r2 = (long) r0
            zzbus = r2
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzi(r0)
            long r2 = (long) r0
            zzbut = r2
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzj(r0)
            long r2 = (long) r0
            zzbuu = r2
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzi(r0)
            long r2 = (long) r0
            zzbuv = r2
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzj(r0)
            long r2 = (long) r0
            zzbuw = r2
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzi(r0)
            long r2 = (long) r0
            zzbux = r2
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzj(r0)
            long r2 = (long) r0
            zzbuy = r2
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzi(r0)
            long r2 = (long) r0
            zzbuz = r2
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzj(r0)
            long r2 = (long) r0
            zzbva = r2
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzi(r0)
            long r2 = (long) r0
            zzbvb = r2
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzj(r0)
            long r2 = (long) r0
            zzbvc = r2
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzi(r0)
            long r2 = (long) r0
            zzbvd = r2
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzj(r0)
            long r2 = (long) r0
            zzbve = r2
            java.lang.reflect.Field r0 = zzuu()
            long r2 = zza(r0)
            zzbvf = r2
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            java.lang.String r2 = "value"
            java.lang.reflect.Field r0 = zza(r0, r2)
            if (r0 == 0) goto L_0x00f5
            java.lang.Class r2 = r0.getType()
            java.lang.Class<char[]> r3 = char[].class
            if (r2 != r3) goto L_0x00f5
            goto L_0x00f6
        L_0x00f5:
            r0 = r1
        L_0x00f6:
            long r0 = zza(r0)
            zzbvg = r0
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x0106
            r0 = 1
            goto L_0x0107
        L_0x0106:
            r0 = 0
        L_0x0107:
            zzbvh = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzabm.<clinit>():void");
    }

    private zzabm() {
    }

    private static long zza(Field field) {
        if (field == null || zzbup == null) {
            return -1;
        }
        return zzbup.zzbvi.objectFieldOffset(field);
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static int zzi(Class<?> cls) {
        if (zzbur) {
            return zzbup.zzbvi.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzj(Class<?> cls) {
        if (zzbur) {
            return zzbup.zzbvi.arrayIndexScale(cls);
        }
        return -1;
    }

    private static boolean zzk(Class<?> cls) {
        if (!zzyv.zzsv()) {
            return false;
        }
        try {
            Class<?> cls2 = zzbqv;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    static Unsafe zzur() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzabn());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzus() {
        if (zzbtj == null) {
            return false;
        }
        try {
            Class<?> cls = zzbtj.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzyv.zzsv()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzut() {
        if (zzbtj == null) {
            return false;
        }
        try {
            Class<?> cls = zzbtj.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzuu() == null) {
                return false;
            }
            if (zzyv.zzsv()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static Field zzuu() {
        Field zza2;
        if (zzyv.zzsv() && (zza2 = zza(Buffer.class, "effectiveDirectAddress")) != null) {
            return zza2;
        }
        Field zza3 = zza(Buffer.class, "address");
        if (zza3 == null || zza3.getType() != Long.TYPE) {
            return null;
        }
        return zza3;
    }
}
