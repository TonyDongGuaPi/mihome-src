package com.xiaomi.smarthome.frame.log;

import android.util.Log;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import java.util.Arrays;

public class MyLogHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16121a = "MyLogHelper";

    public static void a(NetRequest netRequest, Error error) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(">>>request fail start\n");
            sb.append("path=" + netRequest.b() + "\n");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("query params=");
            String str = null;
            sb2.append(netRequest.e() == null ? null : Arrays.toString(netRequest.e().toArray()));
            sb2.append("\n");
            sb.append(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("headers=");
            sb3.append(netRequest.d() == null ? null : Arrays.toString(netRequest.d().toArray()));
            sb3.append("\n");
            sb.append(sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            sb4.append("error=");
            if (error != null) {
                str = error.a() + "," + error.b();
            }
            sb4.append(str);
            sb4.append("\n");
            sb.append(sb4.toString());
            sb.append("<<<request fail end\n");
            String sb5 = sb.toString();
            CoreApi.a().a(f16121a.hashCode(), "", sb5);
            if (GlobalSetting.q) {
                Log.e(f16121a, sb5);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                MyLogger.a().a(f16121a, e.getMessage());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(String str, Error error) {
        String str2;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(">>>request fail start\n");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(" error=");
            if (error == null) {
                str2 = null;
            } else {
                str2 = error.a() + "," + error.b();
            }
            sb2.append(str2);
            sb2.append("\n");
            sb.append(sb2.toString());
            sb.append("<<<request fail end\n");
            String sb3 = sb.toString();
            CoreApi.a().a(f16121a.hashCode(), "", sb3);
            if (GlobalSetting.q) {
                Log.e(f16121a, sb3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                MyLogger.a().a(f16121a, e.getMessage());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(long j) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SHPerformance: app takes " + j + "ms to be visible\n");
            String sb2 = sb.toString();
            CoreApi.a().a(f16121a.hashCode(), "", sb2);
            if (GlobalSetting.q) {
                Log.e(f16121a, sb2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                MyLogger.a().a(f16121a, e.getMessage());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(long j, long j2, long j3) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("coreready is at " + j + "ms\n");
            sb.append("login is at " + j2 + "ms\n");
            sb.append("device list is at " + j3 + "ms to be show\n");
            String sb2 = sb.toString();
            CoreApi.a().a(f16121a.hashCode(), "", sb2);
            if (GlobalSetting.q) {
                Log.e(f16121a, sb2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                MyLogger.a().a(f16121a, e.getMessage());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
