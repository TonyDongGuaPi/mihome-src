package com.ximalaya.ting.android.player.soundtouch;

public final class SoundTouch {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2305a = 1;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 44100;
    public static boolean f = true;
    private static SoundTouch h;
    private long g;

    private final native void deleteInstance(long j);

    public static final native String getErrorString();

    public static final native String getVersionString();

    private static final native long newInstance(int i, int i2);

    private final native int processBytes(long j, SoundTouchDataModel soundTouchDataModel);

    private final native void setPitch(long j, float f2);

    private final native void setPitchSemiTones(long j, float f2);

    private final native void setRate(long j, float f2);

    private final native void setTempo(long j, float f2);

    public SoundTouch(int i, int i2) {
        this.g = 0;
        this.g = newInstance(i, i2);
    }

    private SoundTouch() {
        this.g = 0;
        this.g = newInstance(44100, 2);
        f = true;
    }

    public static SoundTouch a() {
        if (h == null) {
            synchronized (SoundTouch.class) {
                if (h == null) {
                    h = new SoundTouch();
                }
            }
        }
        return h;
    }

    public void b() {
        deleteInstance(this.g);
        this.g = 0;
    }

    public void a(float f2) {
        setTempo(this.g, f2);
    }

    public void b(float f2) {
        setPitchSemiTones(this.g, f2);
    }

    public void c(float f2) {
        setRate(this.g, f2);
    }

    public void d(float f2) {
        setPitch(this.g, f2);
    }

    public int a(SoundTouchDataModel soundTouchDataModel) {
        return processBytes(this.g, soundTouchDataModel);
    }

    static {
        try {
            System.loadLibrary("soundtouch");
        } catch (Exception unused) {
            f = false;
        } catch (Throwable unused2) {
            f = false;
        }
    }
}
