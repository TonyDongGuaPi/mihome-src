package com.drew.metadata.exif;

import com.drew.lang.GeoLocation;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class GpsDirectory extends ExifDirectoryBase {
    public static final int cA = 20;
    public static final int cB = 21;
    public static final int cC = 22;
    public static final int cD = 23;
    public static final int cE = 24;
    public static final int cF = 25;
    public static final int cG = 26;
    public static final int cH = 27;
    public static final int cI = 28;
    public static final int cJ = 29;
    public static final int cK = 30;
    @NotNull
    protected static final HashMap<Integer, String> cL = new HashMap<>();
    public static final int cg = 0;
    public static final int ch = 1;
    public static final int ci = 2;
    public static final int cj = 3;
    public static final int ck = 4;
    public static final int cl = 5;
    public static final int cm = 6;

    /* renamed from: cn  reason: collision with root package name */
    public static final int f5219cn = 7;
    public static final int co = 8;
    public static final int cp = 9;
    public static final int cq = 10;
    public static final int cr = 11;
    public static final int cs = 12;
    public static final int ct = 13;
    public static final int cu = 14;
    public static final int cv = 15;
    public static final int cw = 16;
    public static final int cx = 17;
    public static final int cy = 18;
    public static final int cz = 19;

    @NotNull
    public String a() {
        return "GPS";
    }

    static {
        a(cL);
        cL.put(0, "GPS Version ID");
        cL.put(1, "GPS Latitude Ref");
        cL.put(2, "GPS Latitude");
        cL.put(3, "GPS Longitude Ref");
        cL.put(4, "GPS Longitude");
        cL.put(5, "GPS Altitude Ref");
        cL.put(6, "GPS Altitude");
        cL.put(7, "GPS Time-Stamp");
        cL.put(8, "GPS Satellites");
        cL.put(9, "GPS Status");
        cL.put(10, "GPS Measure Mode");
        cL.put(11, "GPS DOP");
        cL.put(12, "GPS Speed Ref");
        cL.put(13, "GPS Speed");
        cL.put(14, "GPS Track Ref");
        cL.put(15, "GPS Track");
        cL.put(16, "GPS Img Direction Ref");
        cL.put(17, "GPS Img Direction");
        cL.put(18, "GPS Map Datum");
        cL.put(19, "GPS Dest Latitude Ref");
        cL.put(20, "GPS Dest Latitude");
        cL.put(21, "GPS Dest Longitude Ref");
        cL.put(22, "GPS Dest Longitude");
        cL.put(23, "GPS Dest Bearing Ref");
        cL.put(24, "GPS Dest Bearing");
        cL.put(25, "GPS Dest Distance Ref");
        cL.put(26, "GPS Dest Distance");
        cL.put(27, "GPS Processing Method");
        cL.put(28, "GPS Area Information");
        cL.put(29, "GPS Date Stamp");
        cL.put(30, "GPS Differential");
    }

    public GpsDirectory() {
        a((TagDescriptor) new GpsDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return cL;
    }

    @Nullable
    public GeoLocation j() {
        Rational[] r = r(2);
        Rational[] r2 = r(4);
        String s = s(1);
        String s2 = s(3);
        if (r == null || r.length != 3 || r2 == null || r2.length != 3 || s == null || s2 == null) {
            return null;
        }
        Double a2 = GeoLocation.a(r[0], r[1], r[2], s.equalsIgnoreCase("S"));
        Double a3 = GeoLocation.a(r2[0], r2[1], r2[2], s2.equalsIgnoreCase("W"));
        if (a2 == null || a3 == null) {
            return null;
        }
        return new GeoLocation(a2.doubleValue(), a3.doubleValue());
    }

    @Nullable
    public Date k() {
        String s = s(29);
        Rational[] r = r(7);
        if (s == null || r == null || r.length != 3) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss.S z").parse(String.format(Locale.US, "%s %02d:%02d:%02.3f UTC", new Object[]{s, Integer.valueOf(r[0].intValue()), Integer.valueOf(r[1].intValue()), Double.valueOf(r[2].doubleValue())}));
        } catch (ParseException unused) {
            return null;
        }
    }
}
