package com.xiaomi.market.sdk;

public class Constants {
    public static final String A = "glEsVersion";
    public static final String B = "feature";
    public static final String C = "library";
    public static final String D = "glExtension";
    public static final String E = "sdk";
    public static final String F = "version";
    public static final String G = "release";
    public static final String H = "androidId";
    public static final String I = "imei";
    public static final String J = "ext_abTestIdentifier";
    public static final String K = "host";
    public static final String L = "fitness";
    public static final String M = "source";
    public static final String N = "updateLog";
    public static final String O = "versionCode";
    public static final String P = "versionName";
    public static final String Q = "apk";
    public static final String R = "apkHash";
    public static final String S = "apkSize";
    public static final String T = "diffFile";
    public static final String U = "diffFileHash";
    public static final String V = "diffFileSize";
    private static volatile boolean W = Client.s();
    private static volatile ServerType X = ServerType.PRODUCT;

    /* renamed from: a  reason: collision with root package name */
    public static final String f11100a = ",";
    public static volatile String b = null;
    public static String c = null;
    public static final String d = "packageName";
    public static final String e = "versionCode";
    public static final String f = "apkHash";
    public static final String g = "signature";
    public static final String h = "clientId";
    public static final String i = "sdk";
    public static final String j = "os";
    public static final String k = "la";
    public static final String l = "co";
    public static final String m = "lo";
    public static final String n = "xiaomiSDKVersion";
    public static final String o = "debug";
    public static final String p = "miuiBigVersionName";
    public static final String q = "miuiBigVersionCode";
    public static final String r = "model";
    public static final String s = "device";
    public static final String t = "cpuArchitecture";
    public static final String u = "deviceType";
    public static final String v = "info";
    public static final String w = "screenSize";
    public static final String x = "resolution";
    public static final String y = "density";
    public static final String z = "touchScreen";

    public interface BaseColumns {

        /* renamed from: a  reason: collision with root package name */
        public static final String f11101a = "_id";
    }

    public static final class Update implements BaseColumns {
        public static final String b = "update_download";
        public static final String c = "package_name";
        public static final String d = "download_id";
        public static final String e = "version_code";
        public static final String f = "apk_url";
        public static final String g = "apk_hash";
        public static final String h = "diff_url";
        public static final String i = "diff_hash";
        public static final String j = "apk_path";
        public static final String k = "CREATE TABLE update_download (_id INTEGER PRIMARY KEY AUTOINCREMENT,package_name TEXT,download_id INTEGER, version_code INTEGER, apk_url TEXT, apk_hash TEXT, diff_url TEXT, diff_hash TEXT, apk_path TEXT, UNIQUE(package_name));";
        public static final String[] l = {"update_download.package_name", "update_download.download_id", "update_download.version_code", "update_download.apk_url", "update_download.apk_hash", "update_download.diff_url", "update_download.diff_hash", "update_download.apk_path"};
    }

    public enum UpdateMethod {
        MARKET,
        DOWNLOAD_MANAGER
    }

    public static void a(boolean z2) {
        W = z2;
        a();
    }

    public static void a(ServerType serverType) {
        X = serverType;
        a();
    }

    public static void a() {
        if (W) {
            b = X.getGlobalBaseUrl();
        } else {
            b = X.getBaseUrl();
        }
        c = b + "updateself";
    }
}
