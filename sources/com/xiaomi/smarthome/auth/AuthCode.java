package com.xiaomi.smarthome.auth;

import android.content.Intent;
import android.os.Bundle;
import com.xiaomi.smarthome.frame.core.CoreApi;
import org.json.JSONObject;

public class AuthCode {

    /* renamed from: a  reason: collision with root package name */
    public static final int f13797a = 100;
    public static final int b = 101;
    public static final int c = -100;
    public static final int d = -101;
    public static final int e = -102;
    public static final int f = -103;
    public static final int g = -104;
    public static final int h = -105;
    public static final int i = -106;
    public static final int j = -107;
    public static final int k = -108;
    public static final int l = -109;
    public static final int m = -112;
    public static final int n = -113;
    public static final int o = -110;
    public static final int p = -111;
    public static final int q = -114;
    public static final int r = -115;
    public static final int s = -116;
    public static final int t = 4;
    public static final int u = 2;
    public static final int v = 6;

    public static Bundle a(int i2) {
        Bundle bundle = new Bundle();
        if (i2 != 100) {
            switch (i2) {
                case s /*-116*/:
                    bundle.putString(AuthConstants.o, "网络不稳定或者没有联网，请检查网络后重试");
                    break;
                case r /*-115*/:
                    bundle.putString(AuthConstants.o, "暂时不支持海外版");
                    break;
                case q /*-114*/:
                    bundle.putString(AuthConstants.o, "版本号不匹配，请升级sdk版本或者米家app版本");
                    break;
                default:
                    switch (i2) {
                        case p /*-111*/:
                            bundle.putString(AuthConstants.o, "该账号不支持该类型授权，请到开放平台申请");
                            break;
                        case -110:
                            bundle.putString(AuthConstants.o, "该设备不支持语音授权，或者该设备不属于你的名下");
                            break;
                        case l /*-109*/:
                            bundle.putString(AuthConstants.o, "缺少device_id");
                            break;
                        case -108:
                            bundle.putString(AuthConstants.o, "请求的code错误");
                            break;
                        case -107:
                            bundle.putString(AuthConstants.o, "请求授权失败");
                            break;
                        case -106:
                            bundle.putString(AuthConstants.o, "取消授权");
                            break;
                        case -105:
                            bundle.putString(AuthConstants.o, "签名错误");
                            break;
                        case -104:
                            bundle.putString(AuthConstants.o, "App_Id错误");
                            break;
                        case -103:
                            bundle.putString(AuthConstants.o, "授权失败");
                            break;
                        case -102:
                            bundle.putString(AuthConstants.o, "获取token失败");
                            break;
                        case -101:
                            bundle.putString(AuthConstants.o, "缺少参数");
                            break;
                        case -100:
                            bundle.putString(AuthConstants.o, "包名错误");
                            break;
                        default:
                            bundle.putString(AuthConstants.o, "请求授权失败");
                            break;
                    }
            }
        } else {
            bundle.putString(AuthConstants.o, "授权成功");
        }
        bundle.putInt(AuthConstants.n, i2);
        if (CoreApi.a().q()) {
            bundle.putString("extra_user_id", CoreApi.a().s());
        }
        JSONObject m2 = AuthManager.h().m();
        if (m2 != null) {
            bundle.putString(AuthConstants.q, m2.toString());
        }
        return bundle;
    }

    public static Intent b(int i2) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        if (i2 != 100) {
            switch (i2) {
                case p /*-111*/:
                    bundle.putString(AuthConstants.o, "该账号不支持该类型授权，请到开放平台申请");
                    break;
                case -110:
                    bundle.putString(AuthConstants.o, "该设备不支持语音授权，或者该设备不属于你的名下");
                    break;
                case l /*-109*/:
                    bundle.putString(AuthConstants.o, "缺少device_id");
                    break;
                case -108:
                    bundle.putString(AuthConstants.o, "请求的code错误");
                    break;
                case -107:
                    bundle.putString(AuthConstants.o, "请求授权失败");
                    break;
                case -106:
                    bundle.putString(AuthConstants.o, "取消授权");
                    break;
                case -105:
                    bundle.putString(AuthConstants.o, "签名错误");
                    break;
                case -104:
                    bundle.putString(AuthConstants.o, "App_Id错误");
                    break;
                case -103:
                    bundle.putString(AuthConstants.o, "授权失败");
                    break;
                case -102:
                    bundle.putString(AuthConstants.o, "获取token失败");
                    break;
                case -101:
                    bundle.putString(AuthConstants.o, "缺少参数");
                    break;
                case -100:
                    bundle.putString(AuthConstants.o, "包名错误");
                    break;
                default:
                    bundle.putString(AuthConstants.o, "请求授权失败");
                    break;
            }
        } else {
            bundle.putString(AuthConstants.o, "授权成功");
        }
        bundle.putInt(AuthConstants.n, i2);
        if (CoreApi.a().q()) {
            bundle.putString("extra_user_id", CoreApi.a().s());
        }
        intent.putExtras(bundle);
        return intent;
    }
}
