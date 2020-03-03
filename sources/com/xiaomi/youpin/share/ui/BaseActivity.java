package com.xiaomi.youpin.share.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import com.xiaomi.youpin.common.util.KeyboardUtils;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashSet;

public abstract class BaseActivity extends FragmentActivity {
    protected Context mContext;
    public Handler mHandler;
    protected boolean mIsBack = false;
    public boolean mIsResumed = false;

    public void handleMessage(Message message) {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mIsResumed = true;
        YouPinShareApi.a().d().a(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mIsResumed = false;
        YouPinShareApi.a().d().c();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r3 = getIntent();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(@android.support.annotation.Nullable android.os.Bundle r3) {
        /*
            r2 = this;
            super.onCreate(r3)
            boolean r3 = r2.isTaskRoot()
            if (r3 != 0) goto L_0x0026
            android.content.Intent r3 = r2.getIntent()
            java.lang.String r0 = r3.getAction()
            if (r0 == 0) goto L_0x0026
            java.lang.String r1 = "android.intent.category.LAUNCHER"
            boolean r3 = r3.hasCategory(r1)
            if (r3 == 0) goto L_0x0026
            java.lang.String r3 = "android.intent.action.MAIN"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x0026
            r2.finish()
        L_0x0026:
            r2.mContext = r2
            r3 = 1
            r2.requestWindowFeature(r3)
            android.view.Window r3 = r2.getWindow()
            com.xiaomi.youpin.common.util.TitleBarUtil.a((android.view.Window) r3)
            com.xiaomi.youpin.share.ui.BaseActivity$ActivityHandler r3 = new com.xiaomi.youpin.share.ui.BaseActivity$ActivityHandler
            r0 = 0
            r3.<init>()
            r2.mHandler = r3
            r2.initMiuiTextTypeface()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.share.ui.BaseActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mIsBack = false;
    }

    public int getIsBackVal() {
        int i = this.mIsBack ? 1 : 2;
        this.mIsBack = true;
        return i;
    }

    /* access modifiers changed from: package-private */
    public void initMiuiTextTypeface() {
        try {
            Class<?> cls = Class.forName("miui.util.TypefaceUtils");
            Field declaredField = cls.getDeclaredField("mFontsWhiteList");
            declaredField.setAccessible(true);
            HashSet hashSet = (HashSet) declaredField.get(cls);
            if (hashSet != null) {
                hashSet.add("com.xiaomi.mijiashop");
            }
        } catch (ClassNotFoundException | Exception | IllegalAccessException | NoSuchFieldException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages((Object) null);
        if (!(getWindow() == null || getWindow().getDecorView() == null || getWindow().getDecorView().getHandler() == null)) {
            getWindow().getDecorView().getHandler().removeCallbacksAndMessages((Object) null);
        }
        KeyboardUtils.e(this);
    }

    private static class ActivityHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<BaseActivity> f23684a;

        private ActivityHandler(BaseActivity baseActivity) {
            this.f23684a = new WeakReference<>(baseActivity);
        }

        public void handleMessage(Message message) {
            BaseActivity baseActivity;
            if (this.f23684a != null && (baseActivity = (BaseActivity) this.f23684a.get()) != null && !baseActivity.isFinishing()) {
                baseActivity.handleMessage(message);
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }
}
