package com.xiaomi.youpin.share.ui;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.xiaomi.youpin.business_common.FrescoUtils;
import com.xiaomi.youpin.common.base.AsyncCallback;
import com.xiaomi.youpin.common.base.ExceptionError;
import com.xiaomi.youpin.common.base.YouPinError;
import com.xiaomi.youpin.common.util.ConvertUtils;
import com.xiaomi.youpin.common.util.TitleBarUtil;
import com.xiaomi.youpin.share.R;
import com.xiaomi.youpin.share.ShareEventUtil;
import com.xiaomi.youpin.share.ShareManager;
import com.xiaomi.youpin.share.ShareObject;
import com.xiaomi.youpin.share.ShareRecordConstant;
import com.xiaomi.youpin.share.ShareUtil;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.youpin.share.model.PosterData;
import com.xiaomi.youpin.share.model.ShareChannel;
import com.xiaomi.youpin.yp_permission.PermissionCallback;
import com.xiaomi.youpin.yp_permission.YouPinPermissionManager;
import com.xiaomiyoupin.toast.YPDToast;

public class PosterShareActivity extends NewShareAnimationActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public SharePosterView f23711a;
    /* access modifiers changed from: private */
    public PosterShareView b;
    /* access modifiers changed from: private */
    public Bitmap c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public String f;
    private String g;
    /* access modifiers changed from: private */
    @ShareChannel.Type
    public String h;
    /* access modifiers changed from: private */
    public boolean i = false;
    private BroadcastReceiver j = new BroadcastReceiver() {
        private boolean b = false;

        public void onReceive(Context context, Intent intent) {
            if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
                String action = intent.getAction();
                char c = 65535;
                if (action.hashCode() == -1583157845 && action.equals(ShareEventUtil.g)) {
                    c = 0;
                }
                if (c == 0 && !this.b) {
                    this.b = true;
                    ShareEventUtil.f(PosterShareActivity.this.mContext, this);
                    boolean a2 = ShareEventUtil.a(intent);
                    int b2 = ShareEventUtil.b(intent);
                    String c2 = ShareEventUtil.c(intent);
                    String e = ShareEventUtil.e(intent);
                    if (a2) {
                        ShareEventUtil.a(PosterShareActivity.this.mContext, PosterShareActivity.this.h);
                    } else if ("weibo".equals(e)) {
                        if (b2 == 1) {
                            ShareEventUtil.b(PosterShareActivity.this.mContext);
                        } else {
                            ShareEventUtil.a(PosterShareActivity.this.mContext, PosterShareActivity.this.h, b2, c2);
                        }
                    } else if (ShareChannel.b.equals(e)) {
                        if (b2 == -2) {
                            ShareEventUtil.b(PosterShareActivity.this.mContext);
                        } else {
                            ShareEventUtil.a(PosterShareActivity.this.mContext, PosterShareActivity.this.h, b2, c2);
                        }
                    }
                    boolean unused = PosterShareActivity.this.i = false;
                    PosterShareActivity.this.finishPage();
                }
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onActivityShow() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.yp_act_poster);
        TitleBarUtil.a(getWindow());
        ShareEventUtil.e(this.mContext, this.j);
        this.d = getIntent().getStringExtra("shareUrl");
        Uri parse = Uri.parse(this.d);
        this.e = parse.getQueryParameter("title");
        this.f = parse.getQueryParameter("content");
        this.g = parse.getQueryParameter("url");
        this.f23711a = (SharePosterView) findViewById(R.id.yp_new_share_poster);
        this.b = (PosterShareView) findViewById(R.id.yp_new_share_share);
        this.b.setWxShareAvailable(YouPinShareApi.a().f());
        this.b.setWeiboShareAvailable(YouPinShareApi.a().g());
        this.f23711a.setVisibility(8);
        this.f23711a.setZoomAnimatorListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                PosterShareActivity.this.b.dismiss();
            }
        }, new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                PosterShareActivity.this.b.show();
            }
        });
        this.f23711a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PosterShareActivity.this.f23711a.startZoom();
            }
        });
        this.b.setVisibility(8);
        this.b.setOnDismissListener(new View.OnClickListener() {
            public void onClick(View view) {
                PosterShareActivity.this.onBackPressed();
            }
        });
        final PosterData posterData = (PosterData) getIntent().getParcelableExtra("poster");
        final Dialog a2 = YouPinShareApi.a().d().a(this.mContext, "正在生成海报中，请稍候...");
        String str = this.g;
        int a3 = ConvertUtils.a(70.0f);
        if (TextUtils.isEmpty(str)) {
            a2.dismiss();
            YPDToast.getInstance().toast((Context) this, "海报生成失败，请重试(-3)~");
            finish();
            return;
        }
        ShareUtil.a(str, a3, a3, new AsyncCallback<Bitmap, ExceptionError>() {
            public void a(Bitmap bitmap) {
                ShareEventUtil.a(PosterShareActivity.this.mContext);
                a2.dismiss();
                PosterShareActivity.this.f23711a.setVisibility(0);
                PosterShareActivity.this.f23711a.setPoster(posterData, FrescoUtils.a(posterData.c), bitmap, new AsyncCallback<Void, YouPinError>() {
                    public void a(Void voidR) {
                        PosterShareActivity.this.f23711a.post(new Runnable() {
                            public void run() {
                                Bitmap unused = PosterShareActivity.this.c = PosterShareActivity.this.f23711a.getBitmap();
                                PosterShareActivity.this.f23711a.startScreenshots(new Animator.AnimatorListener() {
                                    public void onAnimationCancel(Animator animator) {
                                    }

                                    public void onAnimationEnd(Animator animator) {
                                    }

                                    public void onAnimationRepeat(Animator animator) {
                                    }

                                    public void onAnimationStart(Animator animator) {
                                        PosterShareActivity.this.b.show();
                                    }
                                });
                            }
                        });
                    }

                    public void a(YouPinError youPinError) {
                        a2.dismiss();
                        YPDToast instance = YPDToast.getInstance();
                        PosterShareActivity posterShareActivity = PosterShareActivity.this;
                        instance.toast((Context) posterShareActivity, "海报生成失败，请重试(-2)~ " + youPinError.c());
                        PosterShareActivity.this.finish();
                    }
                });
            }

            public void a(ExceptionError exceptionError) {
                a2.dismiss();
                YPDToast instance = YPDToast.getInstance();
                PosterShareActivity posterShareActivity = PosterShareActivity.this;
                instance.toast((Context) posterShareActivity, "海报生成失败，请重试(-1)~ " + exceptionError.c());
                PosterShareActivity.this.finish();
            }
        });
        this.b.setOnShareItemClickListener(new OnShareItemClickListener() {
            public void d() {
            }

            public void e() {
            }

            public void a() {
                if (!PosterShareActivity.this.i) {
                    YouPinShareApi.a().d().a(ShareRecordConstant.e, PosterShareActivity.this.d, (String) null);
                    boolean unused = PosterShareActivity.this.i = true;
                    String unused2 = PosterShareActivity.this.h = ShareChannel.f;
                    String a2 = ShareUtil.a(PosterShareActivity.this.mContext, PosterShareActivity.this.c);
                    if (TextUtils.isEmpty(a2)) {
                        boolean unused3 = PosterShareActivity.this.i = false;
                        return;
                    }
                    Uri a3 = ShareUtil.a(PosterShareActivity.this.mContext, Uri.parse(a2));
                    ShareObject shareObject = new ShareObject();
                    shareObject.k("pic");
                    shareObject.b(a3);
                    if (!ShareManager.b(PosterShareActivity.this.mContext, shareObject)) {
                        boolean unused4 = PosterShareActivity.this.i = false;
                    }
                }
            }

            public void b() {
                if (!PosterShareActivity.this.i) {
                    YouPinShareApi.a().d().a(ShareRecordConstant.f, PosterShareActivity.this.d, (String) null);
                    boolean unused = PosterShareActivity.this.i = true;
                    String unused2 = PosterShareActivity.this.h = ShareChannel.e;
                    String a2 = ShareUtil.a(PosterShareActivity.this.mContext, PosterShareActivity.this.c);
                    if (TextUtils.isEmpty(a2)) {
                        YPDToast.getInstance().toast((Context) PosterShareActivity.this, R.string.device_shop_share_failure);
                        boolean unused3 = PosterShareActivity.this.i = false;
                        return;
                    }
                    Uri a3 = ShareUtil.a(PosterShareActivity.this.mContext, Uri.parse(a2));
                    ShareObject shareObject = new ShareObject();
                    shareObject.k("pic");
                    shareObject.b(a3);
                    if (!ShareManager.c(PosterShareActivity.this.mContext, shareObject)) {
                        boolean unused4 = PosterShareActivity.this.i = false;
                    }
                }
            }

            public void c() {
                if (!PosterShareActivity.this.i) {
                    YouPinShareApi.a().d().a("weibo", PosterShareActivity.this.d, (String) null);
                    boolean unused = PosterShareActivity.this.i = true;
                    String unused2 = PosterShareActivity.this.h = ShareChannel.g;
                    if (!ShareUtil.a(PosterShareActivity.this.mContext)) {
                        YPDToast.getInstance().toast((Context) PosterShareActivity.this, R.string.device_shop_share_no_weibo);
                        boolean unused3 = PosterShareActivity.this.i = false;
                        return;
                    }
                    String a2 = ShareUtil.a(PosterShareActivity.this.mContext, PosterShareActivity.this.c);
                    if (TextUtils.isEmpty(a2)) {
                        YPDToast.getInstance().toast((Context) PosterShareActivity.this, R.string.device_shop_share_failure);
                        boolean unused4 = PosterShareActivity.this.i = false;
                        return;
                    }
                    Uri a3 = ShareUtil.a(PosterShareActivity.this.mContext, Uri.parse(a2));
                    ShareObject shareObject = new ShareObject();
                    shareObject.f(PosterShareActivity.this.e);
                    shareObject.g(PosterShareActivity.this.f);
                    shareObject.n(ShareObject.d);
                    shareObject.b(a3);
                    if (!ShareManager.a(PosterShareActivity.this.mContext, shareObject, true)) {
                        boolean unused5 = PosterShareActivity.this.i = false;
                    }
                }
            }

            public void f() {
                if (!PosterShareActivity.this.i) {
                    YouPinShareApi.a().d().a(ShareRecordConstant.i, PosterShareActivity.this.d, (String) null);
                    boolean unused = PosterShareActivity.this.i = true;
                    if (YouPinPermissionManager.a(PosterShareActivity.this.mContext, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                        g();
                    } else {
                        YouPinPermissionManager.a((Activity) PosterShareActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE", (PermissionCallback) new PermissionCallback() {
                            public void a() {
                                AnonymousClass7.this.g();
                            }

                            public void a(boolean z) {
                                if (!z) {
                                    YPDToast.getInstance().toast((Context) PosterShareActivity.this, "产品海报生成失败，请重试~");
                                }
                            }

                            public void b() {
                                YPDToast.getInstance().toast((Context) PosterShareActivity.this, "产品海报生成失败，请重试~");
                            }
                        });
                    }
                }
            }

            /* access modifiers changed from: private */
            public void g() {
                if (TextUtils.isEmpty(ShareUtil.a(PosterShareActivity.this.mContext, PosterShareActivity.this.c))) {
                    YPDToast.getInstance().toast((Context) PosterShareActivity.this, "产品海报生成失败，请重试~");
                    boolean unused = PosterShareActivity.this.i = false;
                    return;
                }
                YPDToast.getInstance().toast((Context) PosterShareActivity.this, "保存成功，快去分享吧");
                ShareEventUtil.a(PosterShareActivity.this.mContext, "poster");
                boolean unused2 = PosterShareActivity.this.i = false;
                PosterShareActivity.this.finishPage();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.i = false;
        YouPinShareApi.a().d().a(ShareRecordConstant.b, "", "", getIsBackVal());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        YouPinShareApi.a().d().b();
    }

    public void onBackPressed() {
        super.onBackPressed();
        ShareEventUtil.b(this.mContext);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.c != null && !this.c.isRecycled()) {
            this.c.recycle();
            this.c = null;
        }
        ShareEventUtil.f(this.mContext, this.j);
        super.onDestroy();
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
    public void onActivityDismiss() {
        this.f23711a.dismiss(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                if (PosterShareActivity.this.b.isShow()) {
                    PosterShareActivity.this.b.dismiss();
                }
            }

            public void onAnimationEnd(Animator animator) {
                PosterShareActivity.this.finishFinal();
            }
        });
    }
}
