package com.xiaomi.smarthome.screenshot;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.webkit.WebView;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.screenshot.callback.ScreenshotListener;
import com.xiaomi.smarthome.screenshot.callback.ScrollListener;

public class Screenshot {

    /* renamed from: a  reason: collision with root package name */
    public static final int f22039a = 1001;
    /* access modifiers changed from: private */
    public static final String b = Screenshot.class.getCanonicalName();
    /* access modifiers changed from: private */
    public static final Object c = new Object();
    private static final int d = 100;
    private static final int e = 200;
    private static final int f = 300;
    private static final int g = 400;
    private Context h;
    /* access modifiers changed from: private */
    public View i;
    private String j;
    /* access modifiers changed from: private */
    public boolean k;
    /* access modifiers changed from: private */
    public volatile int l;
    /* access modifiers changed from: private */
    public int m;
    /* access modifiers changed from: private */
    public volatile int n;
    /* access modifiers changed from: private */
    public volatile int o;
    /* access modifiers changed from: private */
    public volatile Bitmap p;
    /* access modifiers changed from: private */
    public volatile ScreenshotHandler q;
    /* access modifiers changed from: private */
    public ScreenshotListener r;
    private LongScreenshotRunabable s;
    private Thread t;
    /* access modifiers changed from: private */
    public ScrollListener u;

    private Screenshot(Builder builder) {
        this.j = "";
        this.k = false;
        this.u = new ScrollListener.Adapter() {
            public void a() {
                Bitmap unused = Screenshot.this.p = ScreenshotUtils.a(Screenshot.this.i);
            }
        };
        this.h = builder.f22043a;
        this.i = builder.b;
        this.j = builder.c;
        this.k = builder.d;
        this.r = builder.e;
        this.q = new ScreenshotHandler(this.h.getMainLooper());
    }

    private String e() {
        if (this.h == null) {
            return "context not null";
        }
        return this.i == null ? "target view not null" : "";
    }

    public void a() {
        String e2 = e();
        if (!TextUtils.isEmpty(e2)) {
            this.q.a(1001, e2);
            return;
        }
        LogUtil.a(b, "------------ start screenshot ------------");
        if (!this.k) {
            f();
        } else {
            g();
        }
    }

    private void f() {
        Bitmap a2 = ScreenshotUtils.a(ScreenshotUtils.a(this.i));
        a(a2);
        this.q.a(a2);
    }

    private void g() {
        this.q.a();
        this.m = this.i.getHeight();
        this.i.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.l = this.i.getMeasuredHeight();
        this.n = this.l / this.m;
        this.o = this.l - (this.n * this.m);
        String str = b;
        LogUtil.a(str, "WebView内容高度: " + this.l);
        String str2 = b;
        LogUtil.a(str2, "WebView控件高度: " + this.m);
        String str3 = b;
        LogUtil.a(str3, "WebView滚动次数: " + this.n);
        String str4 = b;
        LogUtil.a(str4, "WebView剩余高度: " + this.o);
        h();
    }

    private void h() {
        this.s = new LongScreenshotRunabable();
        this.t = new Thread(this.s);
        this.t.start();
    }

    /* access modifiers changed from: private */
    public void a(Bitmap bitmap) {
        if (!TextUtils.isEmpty(this.j)) {
            ScreenshotUtils.a(bitmap, this.j);
            String str = b;
            LogUtil.a(str, "filePath: " + this.j);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        if (this.i instanceof WebView) {
            WebView webView = (WebView) this.i;
            boolean isVerticalScrollBarEnabled = webView.isVerticalScrollBarEnabled();
            if (i2 != 400 || !isVerticalScrollBarEnabled) {
                webView.setVerticalScrollBarEnabled(true);
            } else {
                webView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    public void b() {
        Bitmap bitmap = this.p;
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        this.p = null;
        this.t.interrupt();
        if (this.s != null) {
            boolean unused = this.s.b = true;
        }
        if (this.q != null) {
            this.q.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        final int scrollY = this.i.getScrollY();
        if (i2 <= 0) {
            synchronized (c) {
                this.u.a();
                LogUtil.a(b, "主线程滚动截图完毕,环境LongScreenshotRunabable继续工作");
                c.notify();
            }
            return;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, i2});
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.setDuration(1000);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Screenshot.this.i.scrollTo(0, ((Integer) valueAnimator.getAnimatedValue()).intValue() + scrollY);
            }
        });
        ofInt.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                synchronized (Screenshot.c) {
                    Screenshot.this.u.a();
                    LogUtil.a(Screenshot.b, "主线程滚动截图完毕,环境LongScreenshotRunabable继续工作");
                    Screenshot.c.notify();
                }
            }

            public void onAnimationStart(Animator animator) {
                Screenshot.this.u.b();
            }
        });
        ofInt.start();
    }

    class LongScreenshotRunabable implements Runnable {
        /* access modifiers changed from: private */
        public volatile boolean b = false;

        LongScreenshotRunabable() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r7 = this;
                java.lang.Object r0 = com.xiaomi.smarthome.screenshot.Screenshot.c     // Catch:{ Exception -> 0x00e1 }
                monitor-enter(r0)     // Catch:{ Exception -> 0x00e1 }
                java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x00de }
                r1.<init>()     // Catch:{ all -> 0x00de }
                com.xiaomi.smarthome.screenshot.Screenshot r2 = com.xiaomi.smarthome.screenshot.Screenshot.this     // Catch:{ all -> 0x00de }
                int r2 = r2.n     // Catch:{ all -> 0x00de }
                com.xiaomi.smarthome.screenshot.Screenshot r3 = com.xiaomi.smarthome.screenshot.Screenshot.this     // Catch:{ all -> 0x00de }
                int r3 = r3.o     // Catch:{ all -> 0x00de }
                if (r3 <= 0) goto L_0x001a
                int r2 = r2 + 1
            L_0x001a:
                r3 = 0
                r4 = 0
            L_0x001c:
                if (r4 >= r2) goto L_0x0095
                java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00de }
                boolean r5 = r5.isInterrupted()     // Catch:{ all -> 0x00de }
                if (r5 == 0) goto L_0x002a
                monitor-exit(r0)     // Catch:{ all -> 0x00de }
                return
            L_0x002a:
                boolean r5 = r7.b     // Catch:{ all -> 0x00de }
                if (r5 == 0) goto L_0x002f
                goto L_0x0095
            L_0x002f:
                if (r4 != 0) goto L_0x003b
                com.xiaomi.smarthome.screenshot.Screenshot r5 = com.xiaomi.smarthome.screenshot.Screenshot.this     // Catch:{ all -> 0x00de }
                com.xiaomi.smarthome.screenshot.Screenshot$ScreenshotHandler r5 = r5.q     // Catch:{ all -> 0x00de }
                r5.a((int) r3)     // Catch:{ all -> 0x00de }
                goto L_0x004a
            L_0x003b:
                com.xiaomi.smarthome.screenshot.Screenshot r5 = com.xiaomi.smarthome.screenshot.Screenshot.this     // Catch:{ all -> 0x00de }
                com.xiaomi.smarthome.screenshot.Screenshot$ScreenshotHandler r5 = r5.q     // Catch:{ all -> 0x00de }
                com.xiaomi.smarthome.screenshot.Screenshot r6 = com.xiaomi.smarthome.screenshot.Screenshot.this     // Catch:{ all -> 0x00de }
                int r6 = r6.m     // Catch:{ all -> 0x00de }
                r5.a((int) r6)     // Catch:{ all -> 0x00de }
            L_0x004a:
                java.lang.String r5 = com.xiaomi.smarthome.screenshot.Screenshot.b     // Catch:{ InterruptedException -> 0x0073 }
                java.lang.String r6 = "当前线程阻塞,等待主(UI)线程滚动截图"
                com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ InterruptedException -> 0x0073 }
                java.lang.Object r5 = com.xiaomi.smarthome.screenshot.Screenshot.c     // Catch:{ InterruptedException -> 0x0073 }
                r5.wait()     // Catch:{ InterruptedException -> 0x0073 }
                com.xiaomi.smarthome.screenshot.Screenshot r5 = com.xiaomi.smarthome.screenshot.Screenshot.this     // Catch:{ all -> 0x00de }
                android.graphics.Bitmap r5 = r5.p     // Catch:{ all -> 0x00de }
                android.graphics.Bitmap r5 = com.xiaomi.smarthome.screenshot.ScreenshotUtils.a((android.graphics.Bitmap) r5)     // Catch:{ all -> 0x00de }
                r1.add(r5)     // Catch:{ all -> 0x00de }
                com.xiaomi.smarthome.screenshot.Screenshot r5 = com.xiaomi.smarthome.screenshot.Screenshot.this     // Catch:{ all -> 0x00de }
                android.graphics.Bitmap r5 = r5.p     // Catch:{ all -> 0x00de }
                r5.recycle()     // Catch:{ all -> 0x00de }
                int r4 = r4 + 1
                goto L_0x001c
            L_0x0073:
                r2 = move-exception
                r2.printStackTrace()     // Catch:{ all -> 0x00de }
                java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00de }
            L_0x007b:
                boolean r2 = r1.hasNext()     // Catch:{ all -> 0x00de }
                if (r2 == 0) goto L_0x0093
                java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x00de }
                android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2     // Catch:{ all -> 0x00de }
                if (r2 == 0) goto L_0x007b
                boolean r3 = r2.isRecycled()     // Catch:{ all -> 0x00de }
                if (r3 != 0) goto L_0x007b
                r2.recycle()     // Catch:{ all -> 0x00de }
                goto L_0x007b
            L_0x0093:
                monitor-exit(r0)     // Catch:{ all -> 0x00de }
                return
            L_0x0095:
                boolean r2 = r7.b     // Catch:{ all -> 0x00de }
                if (r2 != 0) goto L_0x00dc
                com.xiaomi.smarthome.screenshot.Screenshot r2 = com.xiaomi.smarthome.screenshot.Screenshot.this     // Catch:{ all -> 0x00de }
                int r2 = r2.l     // Catch:{ all -> 0x00de }
                com.xiaomi.smarthome.screenshot.Screenshot r3 = com.xiaomi.smarthome.screenshot.Screenshot.this     // Catch:{ all -> 0x00de }
                int r3 = r3.o     // Catch:{ all -> 0x00de }
                android.graphics.Bitmap r2 = com.xiaomi.smarthome.screenshot.ScreenshotUtils.a(r1, r2, r3)     // Catch:{ all -> 0x00de }
                java.lang.String r3 = com.xiaomi.smarthome.screenshot.Screenshot.b     // Catch:{ all -> 0x00de }
                java.lang.String r4 = "合并图片成功"
                com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x00de }
                com.xiaomi.smarthome.screenshot.Screenshot r3 = com.xiaomi.smarthome.screenshot.Screenshot.this     // Catch:{ all -> 0x00de }
                r3.a((android.graphics.Bitmap) r2)     // Catch:{ all -> 0x00de }
                java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00de }
            L_0x00bb:
                boolean r3 = r1.hasNext()     // Catch:{ all -> 0x00de }
                if (r3 == 0) goto L_0x00d3
                java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x00de }
                android.graphics.Bitmap r3 = (android.graphics.Bitmap) r3     // Catch:{ all -> 0x00de }
                if (r3 == 0) goto L_0x00bb
                boolean r4 = r3.isRecycled()     // Catch:{ all -> 0x00de }
                if (r4 != 0) goto L_0x00bb
                r3.recycle()     // Catch:{ all -> 0x00de }
                goto L_0x00bb
            L_0x00d3:
                com.xiaomi.smarthome.screenshot.Screenshot r1 = com.xiaomi.smarthome.screenshot.Screenshot.this     // Catch:{ all -> 0x00de }
                com.xiaomi.smarthome.screenshot.Screenshot$ScreenshotHandler r1 = r1.q     // Catch:{ all -> 0x00de }
                r1.a((android.graphics.Bitmap) r2)     // Catch:{ all -> 0x00de }
            L_0x00dc:
                monitor-exit(r0)     // Catch:{ all -> 0x00de }
                goto L_0x00e5
            L_0x00de:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x00de }
                throw r1     // Catch:{ Exception -> 0x00e1 }
            L_0x00e1:
                r0 = move-exception
                r0.printStackTrace()
            L_0x00e5:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.screenshot.Screenshot.LongScreenshotRunabable.run():void");
        }
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public Context f22043a;
        /* access modifiers changed from: private */
        public View b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public boolean d;
        /* access modifiers changed from: private */
        public ScreenshotListener e;

        public Builder(@NonNull Context context) {
            this.f22043a = context;
        }

        public Builder a(View view) {
            this.b = view;
            return this;
        }

        public Builder a(boolean z) {
            this.d = z;
            return this;
        }

        public Builder a(String str) {
            this.c = str;
            return this;
        }

        public Builder a(ScreenshotListener screenshotListener) {
            this.e = screenshotListener;
            return this;
        }

        public Screenshot a() {
            return new Screenshot(this);
        }
    }

    class ScreenshotHandler extends Handler {
        public ScreenshotHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 100) {
                Bitmap bitmap = (Bitmap) message.obj;
                if (Screenshot.this.r != null) {
                    Screenshot.this.r.a(bitmap, Screenshot.this.k);
                }
                Screenshot.this.a(message.what);
                LogUtil.a(Screenshot.b, "------------ finish screenshot ------------");
            } else if (i == 200) {
                int i2 = message.arg1;
                String str = (String) message.obj;
                if (Screenshot.this.r != null) {
                    Screenshot.this.r.a(i2, str);
                }
                Screenshot.this.a(message.what);
            } else if (i == 300) {
                Screenshot.this.b(message.arg1);
            } else if (i == 400) {
                if (Screenshot.this.r != null) {
                    Screenshot.this.r.a();
                }
                Screenshot.this.a(message.what);
            }
        }

        public void a(int i) {
            Message obtainMessage = obtainMessage(300);
            obtainMessage.arg1 = i;
            sendMessage(obtainMessage);
        }

        public void a(Bitmap bitmap) {
            Message obtainMessage = obtainMessage(100);
            obtainMessage.obj = bitmap;
            sendMessage(obtainMessage);
        }

        public void a(int i, String str) {
            Message obtainMessage = obtainMessage(100);
            obtainMessage.arg1 = i;
            obtainMessage.obj = str;
            sendMessage(obtainMessage);
        }

        public void a() {
            obtainMessage(400).sendToTarget();
        }
    }
}
