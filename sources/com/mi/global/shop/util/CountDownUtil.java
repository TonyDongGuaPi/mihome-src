package com.mi.global.shop.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.util.MiToast;
import java.util.Timer;
import java.util.TimerTask;

public class CountDownUtil {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Activity f7085a;
    /* access modifiers changed from: private */
    public Timer b = null;
    /* access modifiers changed from: private */
    public Handler c = null;
    /* access modifiers changed from: private */
    public long d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public long f;
    /* access modifiers changed from: private */
    public long g;
    /* access modifiers changed from: private */
    public long h;

    static /* synthetic */ long h(CountDownUtil countDownUtil) {
        long j = countDownUtil.d;
        countDownUtil.d = j - 1;
        return j;
    }

    public CountDownUtil(Activity activity, long j) {
        this.f7085a = activity;
        this.d = j;
    }

    public void a(final CustomTextView customTextView, final String str) {
        this.b = new Timer();
        this.c = new Handler() {
            public void handleMessage(Message message) {
                Object obj;
                Object obj2;
                Object obj3;
                if (message.what <= 0) {
                    if (CountDownUtil.this.b != null) {
                        CountDownUtil.this.b.cancel();
                    }
                    if (CountDownUtil.this.f7085a != null) {
                        if (!TextUtils.isEmpty(str)) {
                            MiToast.a((Context) CountDownUtil.this.f7085a, (CharSequence) str, 3000);
                        }
                        CountDownUtil.this.f7085a.finish();
                    }
                }
                try {
                    long unused = CountDownUtil.this.f = CountDownUtil.this.d / 3600;
                    long unused2 = CountDownUtil.this.g = (CountDownUtil.this.d - (CountDownUtil.this.f * 3600)) / 60;
                    long unused3 = CountDownUtil.this.h = (CountDownUtil.this.d - (CountDownUtil.this.f * 3600)) - (CountDownUtil.this.g * 60);
                    CountDownUtil countDownUtil = CountDownUtil.this;
                    StringBuilder sb = new StringBuilder();
                    if (CountDownUtil.this.f < 10) {
                        obj = "0" + CountDownUtil.this.f;
                    } else {
                        obj = Long.valueOf(CountDownUtil.this.f);
                    }
                    sb.append(obj);
                    sb.append(" : ");
                    if (CountDownUtil.this.g < 10) {
                        obj2 = "0" + CountDownUtil.this.g;
                    } else {
                        obj2 = Long.valueOf(CountDownUtil.this.g);
                    }
                    sb.append(obj2);
                    sb.append(" : ");
                    if (CountDownUtil.this.h < 10) {
                        obj3 = "0" + CountDownUtil.this.h;
                    } else {
                        obj3 = Long.valueOf(CountDownUtil.this.h);
                    }
                    sb.append(obj3);
                    String unused4 = countDownUtil.e = sb.toString();
                    customTextView.setText(CountDownUtil.this.e);
                } catch (Exception e) {
                    e.printStackTrace();
                    customTextView.setVisibility(8);
                }
            }
        };
        this.b.schedule(new TimerTask() {
            public void run() {
                Message message = new Message();
                CountDownUtil.h(CountDownUtil.this);
                if (CountDownUtil.this.d > 0) {
                    message.what = 1;
                } else {
                    message.what = -1;
                }
                CountDownUtil.this.c.sendMessage(message);
            }
        }, 0, 1000);
    }

    public void a() {
        this.b.cancel();
        this.b = null;
        this.f7085a = null;
    }
}
