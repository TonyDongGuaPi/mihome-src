package com.xiaomi.youpin.share;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.youpin.share.model.ShareChannel;

public class ShareEventUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23667a = "share.is_success";
    public static final String b = "share.code";
    public static final String c = "share.err_msg";
    public static final String d = "share.channel";
    public static final String e = "share.gen_poster.success";
    public static final String f = "share";
    public static final String g = "share.sdk";
    public static final String h = "transcation";

    public static boolean a(Intent intent) {
        return intent.getBooleanExtra(f23667a, false);
    }

    public static int b(Intent intent) {
        return intent.getIntExtra(b, 0);
    }

    public static String c(Intent intent) {
        return intent.getStringExtra(c);
    }

    public static String d(Intent intent) {
        return intent.getStringExtra("transcation");
    }

    @ShareChannel.Type
    public static String e(Intent intent) {
        return intent.getStringExtra(d);
    }

    public static void a(Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(e));
    }

    public static void a(Context context, BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(e);
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void b(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
    }

    public static void a(Context context, @ShareChannel.Type String str) {
        Intent intent = new Intent("share");
        intent.putExtra(f23667a, true);
        intent.putExtra(d, str);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void a(Context context, @ShareChannel.Type String str, int i, String str2) {
        Intent intent = new Intent("share");
        intent.putExtra(f23667a, false);
        intent.putExtra(b, i);
        intent.putExtra(c, str2);
        intent.putExtra(d, str);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void b(Context context) {
        Intent intent = new Intent("share");
        intent.putExtra(f23667a, false);
        intent.putExtra(b, -100);
        intent.putExtra(c, "share cancel");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void c(Context context, BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("share");
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void d(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
    }

    public static void a(Context context, boolean z, String str, int i, String str2) {
        Intent intent = new Intent(g);
        intent.putExtra(f23667a, z);
        intent.putExtra("transcation", str);
        intent.putExtra(b, i);
        intent.putExtra(d, ShareChannel.b);
        intent.putExtra(c, str2);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void b(Context context, boolean z, @ShareChannel.Type String str, int i, String str2) {
        Intent intent = new Intent(g);
        intent.putExtra(f23667a, z);
        intent.putExtra(b, i);
        intent.putExtra(d, str);
        intent.putExtra(c, str2);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void e(Context context, BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(g);
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void f(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
    }
}
