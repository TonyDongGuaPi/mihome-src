package com.xiaomi.youpin.share.ui;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.xiaomi.youpin.share.R;
import com.xiaomi.youpin.share.ShareEventUtil;
import com.xiaomi.youpin.share.ShareManager;
import com.xiaomi.youpin.share.ShareObject;
import com.xiaomi.youpin.share.ShareRecordConstant;
import com.xiaomi.youpin.share.ShareUtil;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.youpin.share.model.ShareChannel;
import com.xiaomiyoupin.toast.YPDToast;

public class NewShareNormalActivity extends NewShareBaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public boolean f23696a;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.vNewShare.setIsShowPoster(false);
        this.vNewShare.setIsShowCopyUrl(true);
        String stringExtra = getIntent().getStringExtra("shareUrl");
        this.f23696a = getIntent().getBooleanExtra("isShareApp", false);
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public boolean isShareApp() {
        return this.f23696a;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        YouPinShareApi.a().d().a(this.f23696a ? ShareRecordConstant.d : "Share", "", "", getIsBackVal());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        YouPinShareApi.a().d().b();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(final ShareObject shareObject) {
        super.onPostExecute(shareObject);
        if (shareObject != null) {
            this.vNewShare.setOnShareItemClickListener(new OnShareItemClickListener() {
                public void d() {
                }

                public void f() {
                }

                public void a() {
                    if (!NewShareNormalActivity.this.mIsSharing) {
                        YouPinShareApi.a().d().a(ShareRecordConstant.e, NewShareNormalActivity.this.mShareUrl, (String) null);
                        NewShareNormalActivity.this.mIsSharing = true;
                        NewShareNormalActivity.this.mShareType = ShareChannel.b;
                        if (!ShareUtil.a()) {
                            YPDToast.getInstance().toast((Context) NewShareNormalActivity.this, R.string.device_shop_share_no_weixin);
                            NewShareNormalActivity.this.mIsSharing = false;
                        } else if (!ShareManager.b(NewShareNormalActivity.this.mContext, shareObject)) {
                            NewShareNormalActivity.this.mIsSharing = false;
                        }
                    }
                }

                public void b() {
                    if (!NewShareNormalActivity.this.mIsSharing) {
                        YouPinShareApi.a().d().a(ShareRecordConstant.f, NewShareNormalActivity.this.mShareUrl, (String) null);
                        NewShareNormalActivity.this.mIsSharing = true;
                        NewShareNormalActivity.this.mShareType = ShareChannel.f23683a;
                        if (!ShareUtil.a()) {
                            YPDToast.getInstance().toast((Context) NewShareNormalActivity.this, R.string.device_shop_share_no_weixin);
                            NewShareNormalActivity.this.mIsSharing = false;
                        } else if (!ShareManager.c(NewShareNormalActivity.this.mContext, shareObject)) {
                            NewShareNormalActivity.this.mIsSharing = false;
                        }
                    }
                }

                public void c() {
                    if (!NewShareNormalActivity.this.mIsSharing) {
                        YouPinShareApi.a().d().a("weibo", NewShareNormalActivity.this.mShareUrl, (String) null);
                        NewShareNormalActivity.this.mIsSharing = true;
                        NewShareNormalActivity.this.mShareType = "weibo";
                        if (!ShareUtil.a(NewShareNormalActivity.this.mContext)) {
                            YPDToast.getInstance().toast((Context) NewShareNormalActivity.this, R.string.device_shop_share_no_weibo);
                            NewShareNormalActivity.this.mIsSharing = false;
                        } else if (!ShareManager.a(NewShareNormalActivity.this.mContext, shareObject, true)) {
                            NewShareNormalActivity.this.mIsSharing = false;
                        }
                    }
                }

                public void e() {
                    if (!NewShareNormalActivity.this.mIsSharing) {
                        YouPinShareApi.a().d().a(ShareRecordConstant.j, NewShareNormalActivity.this.mShareUrl, (String) null);
                        NewShareNormalActivity.this.mIsSharing = true;
                        NewShareNormalActivity.this.mShareType = ShareChannel.d;
                        ClipboardManager clipboardManager = (ClipboardManager) NewShareNormalActivity.this.getApplicationContext().getSystemService(ShareChannel.d);
                        if (clipboardManager != null) {
                            clipboardManager.setText(shareObject.i());
                            if (NewShareNormalActivity.this.f23696a) {
                                YPDToast.getInstance().toast((Context) NewShareNormalActivity.this, "复制成功~");
                            }
                            ShareEventUtil.a(NewShareNormalActivity.this.mContext, ShareChannel.d);
                            NewShareNormalActivity.this.finishPage();
                        } else {
                            if (NewShareNormalActivity.this.f23696a) {
                                YPDToast.getInstance().toast((Context) NewShareNormalActivity.this, "复制失败，请重试~");
                            }
                            ShareEventUtil.a(NewShareNormalActivity.this.mContext, ShareChannel.d, -1, "ClipboardManager is null");
                        }
                        NewShareNormalActivity.this.mIsSharing = false;
                    }
                }
            });
        }
    }
}
