package com.umeng.analytics;

import android.content.Context;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;

public class MobclickAgent {
    public static void a() {
    }

    public static void a(double d, double d2) {
    }

    public static void a(long j) {
    }

    public static void a(Context context) {
    }

    public static void a(Context context, EScenarioType eScenarioType) {
    }

    public static void a(Context context, String str) {
    }

    public static void a(Context context, String str, String str2) {
    }

    public static void a(Context context, String str, Map<String, String> map) {
    }

    public static void a(Context context, String str, Map<String, String> map, int i) {
    }

    public static void a(Context context, Throwable th) {
    }

    public static void a(Context context, List<String> list, int i, String str) {
    }

    public static void a(UMAnalyticsConfig uMAnalyticsConfig) {
    }

    public static void a(String str) {
    }

    public static void a(String str, String str2) {
    }

    public static void a(GL10 gl10) {
    }

    public static void a(boolean z) {
    }

    public static void b(long j) {
    }

    public static void b(Context context) {
    }

    public static void b(Context context, String str) {
    }

    public static void b(String str) {
    }

    public static void b(boolean z) {
    }

    public static void c(Context context, String str) {
    }

    public static void c(String str) {
    }

    public static void c(boolean z) {
    }

    public static void d(boolean z) {
    }

    public static void e(boolean z) {
    }

    public static class UMAnalyticsConfig {

        /* renamed from: a  reason: collision with root package name */
        public String f9532a;
        public String b;
        public boolean c;
        public EScenarioType d;
        public Context e;

        private UMAnalyticsConfig() {
        }

        public UMAnalyticsConfig(Context context, String str, String str2) {
        }

        public UMAnalyticsConfig(Context context, String str, String str2, EScenarioType eScenarioType) {
        }

        public UMAnalyticsConfig(Context context, String str, String str2, EScenarioType eScenarioType, boolean z) {
        }
    }

    public enum EScenarioType {
        E_UM_NORMAL(0),
        E_UM_GAME(1),
        E_UM_ANALYTICS_OEM(224),
        E_UM_GAME_OEM(225);
        

        /* renamed from: a  reason: collision with root package name */
        private int f9531a;

        private EScenarioType(int i) {
            this.f9531a = i;
        }

        public int toValue() {
            return this.f9531a;
        }
    }
}
