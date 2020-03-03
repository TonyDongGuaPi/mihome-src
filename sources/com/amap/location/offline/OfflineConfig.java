package com.amap.location.offline;

import com.amap.location.common.network.IHttpClient;
import com.amap.location.offline.upload.UploadConfig;

public class OfflineConfig {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f4596a = false;
    public static final byte b = -1;
    public static final byte c = 0;
    public static final byte d = 1;
    public static final byte e = 2;
    public static final byte f = 3;
    public static final byte g = 4;
    public byte h = -1;
    public String i = "";
    public String j = "";
    public String k = "";
    public String l = "";
    public String m = "";
    public String n = "";
    public String o = "";
    public String p = "";
    public boolean q = true;
    public String[] r = null;
    public ILocateLogRecorder s = null;
    public IHttpClient t = null;
    public UploadConfig u = null;
    public ICoordinateConverter v = null;

    public interface ICoordinateConverter {
        double[] a(double[] dArr);
    }

    public interface ILocateLogRecorder {
        void a(byte[] bArr);
    }
}
