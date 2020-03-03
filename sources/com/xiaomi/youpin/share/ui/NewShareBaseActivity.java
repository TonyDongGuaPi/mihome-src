package com.xiaomi.youpin.share.ui;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.share.R;
import com.xiaomi.youpin.share.ShareAsyncTask;
import com.xiaomi.youpin.share.ShareEventUtil;
import com.xiaomi.youpin.share.ShareObject;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.youpin.share.model.ShareChannel;
import com.xiaomiyoupin.toast.YPDToast;
import java.lang.ref.WeakReference;

public abstract class NewShareBaseActivity extends NewShareAnimationActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23688a = "NewShareBaseActivity";
    private Dialog b;
    private boolean c = false;
    private BroadcastReceiver d = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
                String action = intent.getAction();
                char c = 65535;
                if (action.hashCode() == -1583157845 && action.equals(ShareEventUtil.g)) {
                    c = 0;
                }
                if (c == 0) {
                    ShareEventUtil.f(NewShareBaseActivity.this.mContext, this);
                    boolean a2 = ShareEventUtil.a(intent);
                    int b = ShareEventUtil.b(intent);
                    String c2 = ShareEventUtil.c(intent);
                    String e = ShareEventUtil.e(intent);
                    if (a2) {
                        if (NewShareBaseActivity.this.isShareApp()) {
                            YPDToast.getInstance().toast(NewShareBaseActivity.this.getBaseContext(), "分享成功");
                        }
                        ShareEventUtil.a(NewShareBaseActivity.this.mContext, NewShareBaseActivity.this.mShareType);
                    } else {
                        if (NewShareBaseActivity.this.isShareApp()) {
                            YPDToast.getInstance().toast(NewShareBaseActivity.this.getBaseContext(), R.string.device_shop_share_failure);
                        }
                        if ("weibo".equals(e)) {
                            if (b == 1) {
                                ShareEventUtil.b(NewShareBaseActivity.this.mContext);
                            } else {
                                ShareEventUtil.a(NewShareBaseActivity.this.mContext, NewShareBaseActivity.this.mShareType, b, c2);
                            }
                        } else if (ShareChannel.b.equals(e)) {
                            if (b == -2) {
                                ShareEventUtil.b(NewShareBaseActivity.this.mContext);
                            } else {
                                ShareEventUtil.a(NewShareBaseActivity.this.mContext, NewShareBaseActivity.this.mShareType, b, c2);
                            }
                        }
                    }
                    NewShareBaseActivity.this.mIsSharing = false;
                    NewShareBaseActivity.this.finishPage();
                }
            }
        }
    };
    private int e = 0;
    protected Context mContext;
    protected boolean mIsSharing = false;
    @ShareChannel.Type
    protected String mShareType;
    protected String mShareUrl;
    protected DownloadShareObjectTask mTask;
    protected NewShareView vNewShare;

    /* access modifiers changed from: protected */
    public boolean isShareApp() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (YouPinShareApi.a() == null) {
            finish();
            return;
        }
        this.mContext = this;
        setContentView(R.layout.yp_act_new_share);
        this.vNewShare = (NewShareView) findViewById(R.id.yp_new_share);
        this.vNewShare.setWxShareAvailable(YouPinShareApi.a().f());
        this.vNewShare.setWeiboShareAvailable(YouPinShareApi.a().g());
        this.vNewShare.setOnDismissListener(new View.OnClickListener() {
            public void onClick(View view) {
                NewShareBaseActivity.this.onBackPressed();
            }
        });
        ShareEventUtil.e(this.mContext, this.d);
        findViewById(R.id.yp_share_background).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NewShareBaseActivity.this.onBackPressed();
            }
        });
        this.vNewShare.setOnShareItemClickListener(new OnShareItemClickListener() {
            public void a() {
            }

            public void b() {
            }

            public void c() {
            }

            public void d() {
            }

            public void e() {
            }

            public void f() {
            }
        });
        this.mShareUrl = getIntent().getStringExtra("shareUrl");
        this.mTask = new DownloadShareObjectTask(this);
        this.mTask.execute(new String[]{this.mShareUrl});
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        LogUtils.d(f23688a, "onStart");
        this.e++;
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        LogUtils.d(f23688a, "onResume");
        boolean z = true;
        this.e--;
        super.onResume();
        if (!ShareChannel.b.equals(this.mShareType) && !ShareChannel.f.equals(this.mShareType)) {
            z = false;
        }
        if (!z || this.e <= 0) {
            this.mIsSharing = false;
            return;
        }
        ShareEventUtil.a(this.mContext, this.mShareType);
        this.mIsSharing = false;
        finishPage();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        LogUtils.d(f23688a, "onPause");
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        LogUtils.d(f23688a, "onStop");
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mTask != null) {
            this.mTask.cancel(false);
        }
        ShareEventUtil.f(this.mContext, this.d);
        super.onDestroy();
    }

    public void onBackPressed() {
        super.onBackPressed();
        ShareEventUtil.b(this.mContext);
    }

    /* access modifiers changed from: protected */
    public void showInAnimation() {
        if (this.c) {
            super.showInAnimation();
        } else if (this.b == null) {
            this.b = YouPinShareApi.a().d().a(this.mContext, getString(R.string.yp_share_downloading));
            this.b.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    if (!NewShareBaseActivity.this.isFinishing()) {
                        NewShareBaseActivity.super.showInAnimation();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public View getContainerView() {
        return findViewById(R.id.yp_share_root_container);
    }

    /* access modifiers changed from: protected */
    public View getBackgroundView() {
        return findViewById(R.id.yp_share_background);
    }

    /* access modifiers changed from: protected */
    public void onActivityShow() {
        this.vNewShare.show((Animation.AnimationListener) null);
    }

    /* access modifiers changed from: protected */
    public void onActivityDismiss() {
        this.vNewShare.dismiss(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                NewShareBaseActivity.this.finishFinal();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(ShareObject shareObject) {
        this.c = true;
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
        }
        if (shareObject == null) {
            YPDToast.getInstance().toast((Context) this, R.string.device_shop_dl_share_failed);
            onBackPressed();
        } else if (Build.VERSION.SDK_INT >= 17 && !isDestroyed()) {
        }
    }

    private static class DownloadShareObjectTask extends ShareAsyncTask {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<NewShareBaseActivity> f23695a;

        public DownloadShareObjectTask(NewShareBaseActivity newShareBaseActivity) {
            this.f23695a = new WeakReference<>(newShareBaseActivity);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(ShareObject shareObject) {
            NewShareBaseActivity newShareBaseActivity;
            if (!isCancelled() && this.f23695a != null && (newShareBaseActivity = (NewShareBaseActivity) this.f23695a.get()) != null) {
                newShareBaseActivity.onPostExecute(shareObject);
            }
        }
    }
}
