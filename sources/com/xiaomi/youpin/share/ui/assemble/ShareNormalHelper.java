package com.xiaomi.youpin.share.ui.assemble;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import com.xiaomi.youpin.share.R;
import com.xiaomi.youpin.share.ShareEventUtil;
import com.xiaomi.youpin.share.ShareManager;
import com.xiaomi.youpin.share.ShareRecordConstant;
import com.xiaomi.youpin.share.ShareUtil;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.youpin.share.model.ShareChannel;
import com.xiaomi.youpin.share.ui.BaseActivity;
import com.xiaomiyoupin.toast.YPDToast;

class ShareNormalHelper {

    /* renamed from: a  reason: collision with root package name */
    private Activity f23743a;
    private ShareBaseHelper b;
    private ShareViewHelper c;

    ShareNormalHelper(ShareBaseHelper shareBaseHelper) {
        this.b = shareBaseHelper;
        this.f23743a = shareBaseHelper.a();
        this.c = shareBaseHelper.b();
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.c.a((View.OnClickListener) new View.OnClickListener() {
            public final void onClick(View view) {
                ShareNormalHelper.this.d(view);
            }
        });
        this.c.b((View.OnClickListener) new View.OnClickListener() {
            public final void onClick(View view) {
                ShareNormalHelper.this.c(view);
            }
        });
        this.c.c((View.OnClickListener) new View.OnClickListener() {
            public final void onClick(View view) {
                ShareNormalHelper.this.b(view);
            }
        });
        this.c.e((View.OnClickListener) new View.OnClickListener() {
            public final void onClick(View view) {
                ShareNormalHelper.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        if (!this.b.d()) {
            YouPinShareApi.a().d().a(ShareRecordConstant.e, this.b.e(), (String) null);
            this.b.a(true);
            this.b.a(ShareChannel.b);
            if (!ShareUtil.a()) {
                YPDToast.getInstance().toast((Context) this.f23743a, R.string.device_shop_share_no_weixin);
                this.b.a(false);
            } else if (!ShareManager.b(this.f23743a, this.b.h())) {
                this.b.a(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        if (!this.b.d()) {
            YouPinShareApi.a().d().a(ShareRecordConstant.f, this.b.e(), (String) null);
            this.b.a(true);
            this.b.a(ShareChannel.f23683a);
            if (!ShareUtil.a()) {
                YPDToast.getInstance().toast((Context) this.f23743a, R.string.device_shop_share_no_weixin);
                this.b.a(false);
            } else if (!ShareManager.c(this.f23743a, this.b.h())) {
                this.b.a(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        if (!this.b.d()) {
            YouPinShareApi.a().d().a("weibo", this.b.e(), (String) null);
            this.b.a(true);
            this.b.a("weibo");
            if (!ShareUtil.a((Context) this.f23743a)) {
                YPDToast.getInstance().toast((Context) this.f23743a, R.string.device_shop_share_no_weibo);
                this.b.a(false);
            } else if (!ShareManager.a(this.f23743a, this.b.h(), true)) {
                this.b.a(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        if (!this.b.d()) {
            YouPinShareApi.a().d().a(ShareRecordConstant.j, this.b.e(), (String) null);
            this.b.a(true);
            this.b.a(ShareChannel.d);
            ClipboardManager clipboardManager = (ClipboardManager) this.f23743a.getApplicationContext().getSystemService(ShareChannel.d);
            if (clipboardManager == null || this.b.h() == null) {
                if (this.b.f()) {
                    YPDToast.getInstance().toast((Context) this.f23743a, "复制失败，请重试~");
                }
                ShareEventUtil.a(this.f23743a, ShareChannel.d, -1, "ClipboardManager is null");
            } else {
                clipboardManager.setPrimaryClip(ClipData.newPlainText((CharSequence) null, this.b.h().i()));
                if (this.b.f()) {
                    YPDToast.getInstance().toast((Context) this.f23743a, "复制成功~");
                }
                ShareEventUtil.a((Context) this.f23743a, ShareChannel.d);
                this.c.i();
            }
            this.b.a(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        String str = this.b.f() ? ShareRecordConstant.d : "Share";
        int i = 0;
        if (this.f23743a != null && (this.f23743a instanceof BaseActivity)) {
            i = ((BaseActivity) this.f23743a).getIsBackVal();
        }
        YouPinShareApi.a().d().a(str, "", "", i);
    }

    /* access modifiers changed from: package-private */
    public void c() {
        YouPinShareApi.a().d().b();
    }
}
