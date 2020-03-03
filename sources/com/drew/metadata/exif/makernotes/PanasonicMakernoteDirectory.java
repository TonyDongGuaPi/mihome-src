package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.alipay.sdk.packet.e;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Age;
import com.drew.metadata.Directory;
import com.drew.metadata.Face;
import com.drew.metadata.TagDescriptor;
import java.io.IOException;
import java.util.HashMap;

public class PanasonicMakernoteDirectory extends Directory {
    public static final int A = 48;
    public static final int B = 49;
    public static final int C = 50;
    public static final int D = 51;
    public static final int E = 52;
    public static final int F = 53;
    public static final int G = 54;
    public static final int H = 57;
    public static final int I = 58;
    public static final int J = 59;
    public static final int K = 60;
    public static final int L = 61;
    public static final int M = 62;
    public static final int N = 63;
    public static final int O = 64;
    public static final int P = 65;
    public static final int Q = 66;
    public static final int R = 68;
    public static final int S = 69;
    public static final int T = 70;
    public static final int U = 71;
    public static final int V = 72;
    public static final int W = 73;
    public static final int X = 75;
    public static final int Y = 76;
    public static final int Z = 77;
    public static final int aA = 163;
    public static final int aB = 171;
    public static final int aC = 3584;
    public static final int aD = 97;
    public static final int aE = 98;
    public static final int aF = 99;
    public static final int aG = 101;
    public static final int aH = 102;
    public static final int aI = 103;
    public static final int aJ = 105;
    public static final int aK = 107;
    public static final int aL = 109;
    public static final int aM = 111;
    public static final int aN = 112;
    public static final int aO = 32768;
    public static final int aP = 32769;
    public static final int aQ = 32772;
    public static final int aR = 32773;
    public static final int aS = 32774;
    public static final int aT = 32775;
    public static final int aU = 32776;
    public static final int aV = 32777;
    public static final int aW = 32784;
    public static final int aX = 32786;
    @NotNull
    protected static final HashMap<Integer, String> aY = new HashMap<>();
    public static final int aa = 78;
    public static final int ab = 81;
    public static final int ac = 82;
    public static final int ad = 83;
    public static final int ae = 84;
    public static final int af = 89;
    public static final int ag = 93;
    public static final int ah = 96;
    public static final int ai = 119;
    public static final int aj = 121;
    public static final int ak = 124;
    public static final int al = 128;
    public static final int am = 137;
    public static final int an = 138;
    public static final int ao = 140;
    public static final int ap = 141;
    public static final int aq = 142;
    public static final int ar = 143;
    public static final int as = 144;
    public static final int at = 145;
    public static final int au = 147;
    public static final int av = 148;
    public static final int aw = 150;
    public static final int ax = 157;
    public static final int ay = 158;
    public static final int az = 159;
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 7;
    public static final int i = 15;
    public static final int j = 26;
    public static final int k = 28;
    public static final int l = 31;
    public static final int m = 32;
    public static final int n = 33;
    public static final int o = 34;
    public static final int p = 35;
    public static final int q = 36;
    public static final int r = 37;
    public static final int s = 38;
    public static final int t = 40;
    public static final int u = 41;
    public static final int v = 42;
    public static final int w = 43;
    public static final int x = 44;
    public static final int y = 45;
    public static final int z = 46;

    @NotNull
    public String a() {
        return "Panasonic Makernote";
    }

    static {
        aY.put(1, "Quality Mode");
        aY.put(2, e.e);
        aY.put(3, "White Balance");
        aY.put(7, "Focus Mode");
        aY.put(15, "AF Area Mode");
        aY.put(26, "Image Stabilization");
        aY.put(28, "Macro Mode");
        aY.put(31, "Record Mode");
        aY.put(32, "Audio");
        aY.put(37, "Internal Serial Number");
        aY.put(33, "Unknown Data Dump");
        aY.put(34, "Easy Mode");
        aY.put(35, "White Balance Bias");
        aY.put(36, "Flash Bias");
        aY.put(38, "Exif Version");
        aY.put(40, "Color Effect");
        aY.put(41, "Camera Uptime");
        aY.put(42, "Burst Mode");
        aY.put(43, "Sequence Number");
        aY.put(44, "Contrast Mode");
        aY.put(45, "Noise Reduction");
        aY.put(46, "Self Timer");
        aY.put(48, "Rotation");
        aY.put(49, "AF Assist Lamp");
        aY.put(50, "Color Mode");
        aY.put(51, "Baby Age");
        aY.put(52, "Optical Zoom Mode");
        aY.put(53, "Conversion Lens");
        aY.put(54, "Travel Day");
        aY.put(57, ExifInterface.TAG_CONTRAST);
        aY.put(58, "World Time Location");
        aY.put(59, "Text Stamp");
        aY.put(60, "Program ISO");
        aY.put(61, "Advanced Scene Mode");
        aY.put(3584, "Print Image Matching (PIM) Info");
        aY.put(63, "Number of Detected Faces");
        aY.put(64, ExifInterface.TAG_SATURATION);
        aY.put(65, ExifInterface.TAG_SHARPNESS);
        aY.put(66, "Film Mode");
        aY.put(68, "Color Temp Kelvin");
        aY.put(69, "Bracket Settings");
        aY.put(70, "White Balance Adjust (AB)");
        aY.put(71, "White Balance Adjust (GM)");
        aY.put(72, "Flash Curtain");
        aY.put(73, "Long Exposure Noise Reduction");
        aY.put(75, "Panasonic Image Width");
        aY.put(76, "Panasonic Image Height");
        aY.put(77, "Af Point Position");
        aY.put(78, "Face Detection Info");
        aY.put(81, "Lens Type");
        aY.put(82, "Lens Serial Number");
        aY.put(83, "Accessory Type");
        aY.put(84, "Accessory Serial Number");
        aY.put(89, "Transform");
        aY.put(93, "Intelligent Exposure");
        aY.put(96, "Lens Firmware Version");
        aY.put(97, "Face Recognition Info");
        aY.put(98, "Flash Warning");
        aY.put(99, "Recognized Face Flags");
        aY.put(101, "Title");
        aY.put(102, "Baby Name");
        aY.put(103, "Location");
        aY.put(105, "Country");
        aY.put(107, "State");
        aY.put(109, "City");
        aY.put(111, "Landmark");
        aY.put(112, "Intelligent Resolution");
        aY.put(119, "Burst Speed");
        aY.put(121, "Intelligent D-Range");
        aY.put(124, "Clear Retouch");
        aY.put(128, "City 2");
        aY.put(137, "Photo Style");
        aY.put(138, "Shading Compensation");
        aY.put(140, "Accelerometer Z");
        aY.put(141, "Accelerometer X");
        aY.put(142, "Accelerometer Y");
        aY.put(143, "Camera Orientation");
        aY.put(144, "Roll Angle");
        aY.put(145, "Pitch Angle");
        aY.put(147, "Sweep Panorama Direction");
        aY.put(148, "Sweep Panorama Field Of View");
        aY.put(150, "Timer Recording");
        aY.put(157, "Internal ND Filter");
        aY.put(158, "HDR");
        aY.put(159, "Shutter Type");
        aY.put(163, "Clear Retouch Value");
        aY.put(171, "Touch AE");
        aY.put(32768, "Makernote Version");
        aY.put(Integer.valueOf(aP), "Scene Mode");
        aY.put(Integer.valueOf(aQ), "White Balance (Red)");
        aY.put(32773, "White Balance (Green)");
        aY.put(Integer.valueOf(aS), "White Balance (Blue)");
        aY.put(Integer.valueOf(aT), "Flash Fired");
        aY.put(62, "Text Stamp 1");
        aY.put(Integer.valueOf(aU), "Text Stamp 2");
        aY.put(Integer.valueOf(aV), "Text Stamp 3");
        aY.put(Integer.valueOf(aW), "Baby Age 1");
        aY.put(Integer.valueOf(aX), "Transform 1");
    }

    public PanasonicMakernoteDirectory() {
        a((TagDescriptor) new PanasonicMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return aY;
    }

    @Nullable
    public Face[] j() {
        byte[] g2 = g(78);
        if (g2 == null) {
            return null;
        }
        ByteArrayReader byteArrayReader = new ByteArrayReader(g2);
        byteArrayReader.a(false);
        try {
            int f2 = byteArrayReader.f(0);
            if (f2 == 0) {
                return null;
            }
            Face[] faceArr = new Face[f2];
            for (int i2 = 0; i2 < f2; i2++) {
                int i3 = (i2 * 8) + 2;
                faceArr[i2] = new Face(byteArrayReader.f(i3), byteArrayReader.f(i3 + 2), byteArrayReader.f(i3 + 4), byteArrayReader.f(i3 + 6), (String) null, (Age) null);
            }
            return faceArr;
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    public Face[] k() {
        byte[] g2 = g(97);
        if (g2 == null) {
            return null;
        }
        ByteArrayReader byteArrayReader = new ByteArrayReader(g2);
        byteArrayReader.a(false);
        try {
            int f2 = byteArrayReader.f(0);
            if (f2 == 0) {
                return null;
            }
            Face[] faceArr = new Face[f2];
            for (int i2 = 0; i2 < f2; i2++) {
                int i3 = (i2 * 44) + 4;
                faceArr[i2] = new Face(byteArrayReader.f(i3 + 20), byteArrayReader.f(i3 + 22), byteArrayReader.f(i3 + 24), byteArrayReader.f(i3 + 26), byteArrayReader.a(i3, 20, "ASCII").trim(), Age.a(byteArrayReader.a(i3 + 28, 20, "ASCII").trim()));
            }
            return faceArr;
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    public Age y(int i2) {
        String s2 = s(i2);
        if (s2 == null) {
            return null;
        }
        return Age.a(s2);
    }
}
