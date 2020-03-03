package com.xiaomi.youpin.share.ui;

import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.xiaomi.youpin.share.R;
import com.xiaomi.youpin.share.ShareEventUtil;
import com.xiaomi.youpin.share.ShareManager;
import com.xiaomi.youpin.share.ShareObject;
import com.xiaomi.youpin.share.ShareRecordConstant;
import com.xiaomi.youpin.share.ShareRouter;
import com.xiaomi.youpin.share.ShareUtil;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.youpin.share.model.PosterData;
import com.xiaomi.youpin.share.model.ShareChannel;
import com.xiaomiyoupin.toast.YPDToast;

public class NewSharePosterActivity extends NewShareBaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private PosterData f23698a;
    private BroadcastReceiver b = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (!TextUtils.isEmpty(intent.getAction())) {
                String action = intent.getAction();
                char c = 65535;
                if (action.hashCode() == 1053729696 && action.equals(ShareEventUtil.e)) {
                    c = 0;
                }
                if (c == 0) {
                    NewSharePosterActivity.this.finishPage();
                }
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f23698a = (PosterData) getIntent().getParcelableExtra("poster");
        this.mShareUrl = getIntent().getStringExtra("shareUrl");
        if (TextUtils.isEmpty(this.mShareUrl)) {
            finish();
            return;
        }
        this.vNewShare.setIsShowPoster(true);
        this.vNewShare.setIsShowCopyUrl(true);
        ShareEventUtil.a(this.mContext, this.b);
        this.vNewShare.setOnShareItemClickListener(new OnShareItemClickListener() {
            public void a() {
            }

            public void b() {
            }

            public void c() {
            }

            public void e() {
            }

            public void f() {
            }

            public void d() {
                NewSharePosterActivity.this.a();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        YouPinShareApi.a().d().a("Share", "", "", getIsBackVal());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        YouPinShareApi.a().d().b();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ShareEventUtil.b(this.mContext, this.b);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(final ShareObject shareObject) {
        super.onPostExecute(shareObject);
        if (shareObject != null) {
            this.vNewShare.setOnShareItemClickListener(new OnShareItemClickListener() {
                public void f() {
                }

                public void a() {
                    if (!NewSharePosterActivity.this.mIsSharing) {
                        YouPinShareApi.a().d().a(ShareRecordConstant.e, NewSharePosterActivity.this.mShareUrl, (String) null);
                        NewSharePosterActivity.this.mIsSharing = true;
                        NewSharePosterActivity.this.mShareType = ShareChannel.b;
                        if (!ShareUtil.a()) {
                            YPDToast.getInstance().toast((Context) NewSharePosterActivity.this, R.string.device_shop_share_no_weixin);
                            NewSharePosterActivity.this.mIsSharing = false;
                        } else if (!ShareManager.b(NewSharePosterActivity.this.mContext, shareObject)) {
                            NewSharePosterActivity.this.mIsSharing = false;
                        }
                    }
                }

                public void b() {
                    if (!NewSharePosterActivity.this.mIsSharing) {
                        YouPinShareApi.a().d().a(ShareRecordConstant.f, NewSharePosterActivity.this.mShareUrl, (String) null);
                        NewSharePosterActivity.this.mIsSharing = true;
                        NewSharePosterActivity.this.mShareType = ShareChannel.f23683a;
                        if (!ShareUtil.a()) {
                            YPDToast.getInstance().toast((Context) NewSharePosterActivity.this, R.string.device_shop_share_no_weixin);
                            NewSharePosterActivity.this.mIsSharing = false;
                        } else if (!ShareManager.c(NewSharePosterActivity.this.mContext, shareObject)) {
                            NewSharePosterActivity.this.mIsSharing = false;
                        }
                    }
                }

                public void c() {
                    if (!NewSharePosterActivity.this.mIsSharing) {
                        YouPinShareApi.a().d().a("weibo", NewSharePosterActivity.this.mShareUrl, (String) null);
                        NewSharePosterActivity.this.mIsSharing = true;
                        NewSharePosterActivity.this.mShareType = "weibo";
                        if (!ShareUtil.a(NewSharePosterActivity.this.mContext)) {
                            YPDToast.getInstance().toast((Context) NewSharePosterActivity.this, R.string.device_shop_share_no_weibo);
                            NewSharePosterActivity.this.mIsSharing = false;
                        } else if (!ShareManager.a(NewSharePosterActivity.this.mContext, shareObject, true)) {
                            NewSharePosterActivity.this.mIsSharing = false;
                        }
                    }
                }

                public void d() {
                    NewSharePosterActivity.this.a();
                }

                public void e() {
                    if (!NewSharePosterActivity.this.mIsSharing) {
                        YouPinShareApi.a().d().a(ShareRecordConstant.j, NewSharePosterActivity.this.mShareUrl, (String) null);
                        NewSharePosterActivity.this.mIsSharing = true;
                        NewSharePosterActivity.this.mShareType = ShareChannel.d;
                        ClipboardManager clipboardManager = (ClipboardManager) NewSharePosterActivity.this.getApplicationContext().getSystemService(ShareChannel.d);
                        if (clipboardManager != null) {
                            clipboardManager.setText(shareObject.i());
                            ShareEventUtil.a(NewSharePosterActivity.this.mContext, ShareChannel.d);
                            NewSharePosterActivity.this.finishPage();
                        } else {
                            ShareEventUtil.a(NewSharePosterActivity.this.mContext, ShareChannel.d, -1, "ClipboardManager is null");
                        }
                        NewSharePosterActivity.this.mIsSharing = false;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        YouPinShareApi.a().d().a("poster", this.mShareUrl, (String) null);
        ShareRouter.b(this.mContext, this.mShareUrl, this.f23698a);
    }
}
