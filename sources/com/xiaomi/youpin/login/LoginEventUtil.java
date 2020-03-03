package com.xiaomi.youpin.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

public class LoginEventUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23395a = "mijia.login.risk";
    public static final String b = "mijia.login.risk.param.userid";
    public static final String c = "mijia.login.risk.param.passtoken";
    public static final String d = "mijia.login.risk.param.code";
    public static final String e = "mijia.login.risk.param.error_msg";
    public static final String f = "mijia.access.xm_account";
    public static final String g = "mijia.access.xm_account.param.is_success";
    public static final String h = "mijia.access.xm_account.param.account_name";
    public static final String i = "mijia.access.xm_account.param.account_type";
    public static final String j = "mijia.login.wx_sns_bind";
    public static final String k = "mijia.login.wx_sns_bind.param.is_success";
    public static final String l = "mijia.login.wx_sns_bind.param.err_code";
    public static final String m = "mijia.login.wx_sns_bind.param.err_msg";
    public static final String n = "mijia.login.system_risk";
    public static final String o = "mijia.login.system_risk.param.is_success";

    public static void a(Context context, BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(f23395a);
        LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void b(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(context.getApplicationContext()).unregisterReceiver(broadcastReceiver);
    }

    public static void a(Context context, String str, String str2) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context.getApplicationContext());
        Intent intent = new Intent(f23395a);
        intent.putExtra(d, 0);
        intent.putExtra(b, str);
        intent.putExtra(c, str2);
        instance.sendBroadcast(intent);
    }

    public static void a(Context context, int i2, String str) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context.getApplicationContext());
        Intent intent = new Intent(f23395a);
        intent.putExtra(d, i2);
        intent.putExtra(e, str);
        instance.sendBroadcast(intent);
    }

    public static void c(Context context, BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(f);
        LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void d(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(context.getApplicationContext()).unregisterReceiver(broadcastReceiver);
    }

    public static void b(Context context, String str, String str2) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context.getApplicationContext());
        Intent intent = new Intent(f);
        intent.putExtra(g, true);
        intent.putExtra(h, str);
        intent.putExtra(i, str2);
        instance.sendBroadcast(intent);
    }

    public static void a(Context context) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context.getApplicationContext());
        Intent intent = new Intent(f);
        intent.putExtra(g, false);
        instance.sendBroadcast(intent);
    }

    public static void e(Context context, BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(j);
        LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void f(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(context.getApplicationContext()).unregisterReceiver(broadcastReceiver);
    }

    public static void b(Context context) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context.getApplicationContext());
        Intent intent = new Intent(j);
        intent.putExtra(k, true);
        instance.sendBroadcast(intent);
    }

    public static void b(Context context, int i2, String str) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context.getApplicationContext());
        Intent intent = new Intent(j);
        intent.putExtra(k, false);
        intent.putExtra(l, i2);
        intent.putExtra(m, str);
        instance.sendBroadcast(intent);
    }

    public static void g(Context context, BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(n);
        LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void h(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(context.getApplicationContext()).unregisterReceiver(broadcastReceiver);
    }

    public static void c(Context context) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context.getApplicationContext());
        Intent intent = new Intent(n);
        intent.putExtra(o, true);
        instance.sendBroadcast(intent);
    }

    public static void d(Context context) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context.getApplicationContext());
        Intent intent = new Intent(n);
        intent.putExtra(o, false);
        instance.sendBroadcast(intent);
    }
}
