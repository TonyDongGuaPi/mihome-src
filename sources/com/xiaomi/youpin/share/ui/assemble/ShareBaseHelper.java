package com.xiaomi.youpin.share.ui.assemble;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.youpin.share.R;
import com.xiaomi.youpin.share.ShareAsyncTask;
import com.xiaomi.youpin.share.ShareEventUtil;
import com.xiaomi.youpin.share.ShareObject;
import com.xiaomi.youpin.share.model.ShareChannel;
import com.xiaomi.youpin.share.ui.assemble.ShareBaseHelper;
import com.xiaomiyoupin.toast.YPDToast;
import java.lang.ref.WeakReference;

class ShareBaseHelper {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Activity f23740a;
    /* access modifiers changed from: private */
    public ShareViewHelper b;
    private DownloadShareObjectTask c;
    /* access modifiers changed from: private */
    @ShareChannel.Type
    public String d;
    /* access modifiers changed from: private */
    public boolean e = false;
    private String f;
    private int g = 0;
    /* access modifiers changed from: private */
    public boolean h;
    private ShareObject i;
    private BroadcastReceiver j;

    ShareBaseHelper(Activity activity, ShareViewHelper shareViewHelper) {
        this.f23740a = activity;
        this.b = shareViewHelper;
        this.h = activity.getIntent().getBooleanExtra("isShareApp", false);
    }

    /* access modifiers changed from: package-private */
    public Activity a() {
        return this.f23740a;
    }

    /* access modifiers changed from: package-private */
    public ShareViewHelper b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.d = str;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.e = z;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public String e() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return this.f23740a.getIntent().getParcelableExtra("poster") != null;
    }

    /* access modifiers changed from: package-private */
    public ShareObject h() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public void i() {
        this.f = this.f23740a.getIntent().getStringExtra("shareUrl");
        if (TextUtils.isEmpty(this.f)) {
            this.f23740a.finish();
            return;
        }
        this.j = new InnerBroadcastReceiver();
        ShareEventUtil.e(this.f23740a, this.j);
        this.c = new DownloadShareObjectTask(this);
        this.c.execute(new String[]{this.f});
    }

    /* access modifiers changed from: package-private */
    public void j() {
        this.g++;
    }

    /* access modifiers changed from: package-private */
    public void k() {
        this.g--;
    }

    /* access modifiers changed from: package-private */
    public void l() {
        if (!(ShareChannel.b.equals(this.d) || ShareChannel.f.equals(this.d)) || this.g <= 0) {
            this.e = false;
            return;
        }
        ShareEventUtil.a((Context) this.f23740a, this.d);
        this.e = false;
        this.b.i();
    }

    /* access modifiers changed from: package-private */
    public void m() {
        if (this.c != null) {
            this.c.cancel(false);
        }
        ShareEventUtil.f(this.f23740a, this.j);
    }

    /* access modifiers changed from: package-private */
    public void n() {
        ShareEventUtil.b((Context) this.f23740a);
    }

    /* access modifiers changed from: private */
    public void a(ShareObject shareObject) {
        this.i = shareObject;
        this.b.b();
        this.b.c();
        if (shareObject == null) {
            YPDToast.getInstance().toast((Context) this.f23740a, R.string.device_shop_dl_share_failed);
            this.f23740a.onBackPressed();
        } else if (Build.VERSION.SDK_INT >= 17) {
            this.f23740a.isDestroyed();
        }
    }

    private class InnerBroadcastReceiver extends BroadcastReceiver {
        private InnerBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
                String action = intent.getAction();
                char c = 65535;
                if (action.hashCode() == -1583157845 && action.equals(ShareEventUtil.g)) {
                    c = 0;
                }
                if (c == 0) {
                    a(intent);
                }
            }
        }

        private void a(Intent intent) {
            ShareEventUtil.f(ShareBaseHelper.this.f23740a, this);
            boolean a2 = ShareEventUtil.a(intent);
            int b = ShareEventUtil.b(intent);
            String c = ShareEventUtil.c(intent);
            String e = ShareEventUtil.e(intent);
            if (a2) {
                if (ShareBaseHelper.this.h) {
                    YPDToast.getInstance().toast((Context) ShareBaseHelper.this.f23740a, "分享成功");
                }
                ShareEventUtil.a((Context) ShareBaseHelper.this.f23740a, ShareBaseHelper.this.d);
            } else {
                if (ShareBaseHelper.this.h) {
                    YPDToast.getInstance().toast((Context) ShareBaseHelper.this.f23740a, R.string.device_shop_share_failure);
                }
                if ("weibo".equals(e)) {
                    if (b == 1) {
                        ShareEventUtil.b((Context) ShareBaseHelper.this.f23740a);
                    } else {
                        ShareEventUtil.a(ShareBaseHelper.this.f23740a, ShareBaseHelper.this.d, b, c);
                    }
                } else if (ShareChannel.b.equals(e)) {
                    if (b == -2) {
                        ShareEventUtil.b((Context) ShareBaseHelper.this.f23740a);
                    } else {
                        ShareEventUtil.a(ShareBaseHelper.this.f23740a, ShareBaseHelper.this.d, b, c);
                    }
                }
            }
            ShareBaseHelper.this.b.j().post(new Runnable() {
                public final void run() {
                    ShareBaseHelper.InnerBroadcastReceiver.this.a();
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a() {
            boolean unused = ShareBaseHelper.this.e = false;
            ShareBaseHelper.this.b.i();
        }
    }

    private static class DownloadShareObjectTask extends ShareAsyncTask {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<ShareBaseHelper> f23741a;

        DownloadShareObjectTask(ShareBaseHelper shareBaseHelper) {
            this.f23741a = new WeakReference<>(shareBaseHelper);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(ShareObject shareObject) {
            ShareBaseHelper shareBaseHelper;
            if (!isCancelled() && this.f23741a != null && (shareBaseHelper = (ShareBaseHelper) this.f23741a.get()) != null) {
                shareBaseHelper.a(shareObject);
            }
        }
    }
}
