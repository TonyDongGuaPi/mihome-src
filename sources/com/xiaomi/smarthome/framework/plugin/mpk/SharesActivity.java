package com.xiaomi.smarthome.framework.plugin.mpk;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import com.mi.global.shop.model.Tags;
import com.sina.weibo.BuildConfig;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xiaomi.channel.sdk.IShareReq;
import com.xiaomi.channel.sdk.MLShareApiFactory;
import com.xiaomi.channel.sdk.MLShareMessage;
import com.xiaomi.channel.sdk.MLShareReq;
import com.xiaomi.channel.sdk.ShareConstants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.shop.activity.DeviceShopWeiboShareActivity;
import java.io.ByteArrayOutputStream;

public class SharesActivity extends BaseActivity {
    private static final String sShareUrl = "https://home.mi.com/share.html?gid=";
    final String TAG = "SharesActivity";
    protected Context mContext;
    View mEmpty;
    boolean mFinishing;
    View mMainview = null;
    View mMainviewFrame = null;
    String mShareContent;
    String mShareImageUrl;
    String mShareThumbUrl;
    String mShareTitle;
    String mShareUrl;
    public Bitmap mThumb;
    ImageView miliao;
    boolean miliaoEnable = false;
    MLShareApiFactory mlapi;
    boolean sinaweiboEnable = false;
    ImageView weibo;
    ImageView weixin;
    boolean weixinEnable = false;
    IWXAPI wxapi;
    ImageView wxpyq;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mFinishing = false;
        if (!checkValidate()) {
            Toast.makeText(this.mContext, R.string.device_shop_share_no_app, 1).show();
            finish();
            return;
        }
        setContentView(R.layout.shares_activity);
        Intent intent = getIntent();
        this.mShareTitle = intent.getStringExtra("shareTitle");
        this.mShareContent = intent.getStringExtra("shareContent");
        this.mShareUrl = intent.getStringExtra("shareUrl");
        this.mShareImageUrl = intent.getStringExtra("shareImageUrl");
        this.mShareThumbUrl = intent.getStringExtra("shareThumbUrl");
        this.mlapi = new MLShareApiFactory(this.mContext);
        this.mlapi.a(getPackageName(), this.mContext.getString(R.string.app_name2));
        this.wxapi = SHApplication.getIWXAPI();
        this.mMainviewFrame = findViewById(R.id.device_more_frame);
        this.mMainview = findViewById(R.id.device_more);
        this.mEmpty = findViewById(R.id.empty);
        this.mEmpty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharesActivity.this.onBackPressed();
            }
        });
        if (this.mThumb == null && !TextUtils.isEmpty(this.mShareThumbUrl)) {
            AsyncTaskUtils.a(new AsyncTask<String, Void, Bitmap>() {
                XQProgressDialog dialog;

                /* access modifiers changed from: protected */
                public void onPreExecute() {
                    this.dialog = XQProgressDialog.a(SharesActivity.this.mContext, (CharSequence) null, SharesActivity.this.mContext.getString(R.string.device_shop_dialog_loading));
                }

                /* access modifiers changed from: protected */
                public Bitmap doInBackground(String... strArr) {
                    Bitmap decodeByteArray;
                    String str = strArr[0];
                    Bitmap bitmap = null;
                    if (str == null) {
                        return null;
                    }
                    byte[] a2 = NetworkUtils.a(str);
                    if (!(a2 == null || (decodeByteArray = BitmapFactory.decodeByteArray(a2, 0, a2.length)) == null || (bitmap = Bitmap.createScaledBitmap(decodeByteArray, 150, 150, true)) == decodeByteArray)) {
                        decodeByteArray.recycle();
                    }
                    return bitmap;
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Bitmap bitmap) {
                    if (this.dialog != null && this.dialog.isShowing()) {
                        this.dialog.dismiss();
                    }
                    SharesActivity.this.mThumb = bitmap;
                    if (SharesActivity.this.mThumb == null) {
                        Toast.makeText(SharesActivity.this.mContext, R.string.device_shop_share_download_pic_failed, 0).show();
                    }
                }
            }, this.mShareThumbUrl);
        }
        this.miliao = (ImageView) findViewById(R.id.miliao_share);
        this.weixin = (ImageView) findViewById(R.id.wx_share);
        this.wxpyq = (ImageView) findViewById(R.id.friends_share);
        this.weibo = (ImageView) findViewById(R.id.weibo_share);
        this.miliao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SharesActivity.this.miliaoEnable) {
                    Toast.makeText(SharesActivity.this.mContext, R.string.device_shop_share_no_miliao, 1).show();
                } else {
                    AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
                        /* access modifiers changed from: protected */
                        public Void doInBackground(Void... voidArr) {
                            try {
                                MLShareMessage mLShareMessage = new MLShareMessage();
                                mLShareMessage.c = SharesActivity.this.mShareTitle;
                                mLShareMessage.b = SharesActivity.this.mShareContent;
                                mLShareMessage.e = SharesActivity.this.mShareThumbUrl;
                                mLShareMessage.f10070a = SharesActivity.this.mShareUrl;
                                SharesActivity.this.mlapi.a((IShareReq) new MLShareReq(mLShareMessage, ShareConstants.N), false);
                                return null;
                            } catch (RemoteException unused) {
                                return null;
                            }
                        }
                    }, new Void[0]);
                }
            }
        });
        this.weixin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SharesActivity.this.weixinEnable) {
                    Toast.makeText(SharesActivity.this.mContext, R.string.device_shop_share_no_weixin, 1).show();
                    return;
                }
                WXMediaMessage wXMediaMessage = new WXMediaMessage(new WXWebpageObject(SharesActivity.this.mShareUrl));
                wXMediaMessage.title = SharesActivity.this.mShareTitle;
                wXMediaMessage.description = SharesActivity.this.mShareContent;
                if (SharesActivity.this.mThumb != null) {
                    wXMediaMessage.thumbData = SharesActivity.this.bmpToByteArray(SharesActivity.this.mThumb, false);
                }
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = wXMediaMessage;
                req.scene = 0;
                boolean sendReq = SharesActivity.this.wxapi.sendReq(req);
                Miio.h("SharesActivity", "sendReq return " + sendReq);
            }
        });
        this.wxpyq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SharesActivity.this.weixinEnable) {
                    Toast.makeText(SharesActivity.this.mContext, R.string.device_shop_share_no_weixin, 1).show();
                    return;
                }
                WXMediaMessage wXMediaMessage = new WXMediaMessage(new WXWebpageObject(SharesActivity.this.mShareUrl));
                wXMediaMessage.title = SharesActivity.this.mShareContent;
                wXMediaMessage.description = SharesActivity.this.mShareContent;
                if (SharesActivity.this.mThumb != null) {
                    wXMediaMessage.thumbData = SharesActivity.this.bmpToByteArray(SharesActivity.this.mThumb, false);
                }
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = wXMediaMessage;
                req.scene = 1;
                boolean sendReq = SharesActivity.this.wxapi.sendReq(req);
                Miio.h("SharesActivity", "sendReq return " + sendReq);
            }
        });
        this.weibo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SharesActivity.this.sinaweiboEnable) {
                    Toast.makeText(SharesActivity.this.mContext, R.string.device_shop_share_no_weibo, 1).show();
                } else if (SharesActivity.this.mThumb != null) {
                    Intent intent = new Intent(SharesActivity.this.mContext, DeviceShopWeiboShareActivity.class);
                    intent.putExtra("appid", GlobalSetting.g);
                    intent.putExtra("title", SharesActivity.this.mShareTitle);
                    intent.putExtra("text", SharesActivity.this.mShareContent);
                    intent.putExtra("url", SharesActivity.this.mShareUrl);
                    intent.putExtra("imageUrl", SharesActivity.this.mShareImageUrl);
                    intent.putExtra(Tags.Kuwan.IMAGE_URL, SharesActivity.this.mThumb);
                    SharesActivity.this.mContext.startActivity(intent);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mThumb = null;
    }

    public void onBackPressed() {
        setResult(0);
        finishPage();
    }

    /* access modifiers changed from: private */
    public void finishFinal() {
        this.mFinishing = true;
        finish();
        overridePendingTransition(0, 0);
    }

    private void finishPage() {
        this.mFinishing = true;
        if (ApiHelper.f18555a) {
            ObjectAnimator ofObject = ObjectAnimator.ofObject(this.mMainviewFrame, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(getResources().getColor(R.color.black_30_transparent)), Integer.valueOf(getResources().getColor(R.color.black_00_transparent))});
            ofObject.setDuration(300);
            ofObject.start();
        } else {
            this.mMainviewFrame.setBackgroundColor(getResources().getColor(R.color.black_00_transparent));
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                SharesActivity.this.finishFinal();
            }
        });
        this.mMainview.startAnimation(loadAnimation);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && !this.mFinishing) {
            if (ApiHelper.f18555a) {
                ObjectAnimator ofObject = ObjectAnimator.ofObject(this.mMainviewFrame, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(getResources().getColor(R.color.black_00_transparent)), Integer.valueOf(getResources().getColor(R.color.black_30_transparent))});
                ofObject.setDuration(300);
                ofObject.start();
            } else {
                this.mMainviewFrame.setBackgroundColor(getResources().getColor(R.color.black_30_transparent));
            }
            this.mMainview.setVisibility(0);
            this.mMainview.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom));
        }
    }

    public byte[] bmpToByteArray(Bitmap bitmap, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 30, byteArrayOutputStream);
        if (z) {
            bitmap.recycle();
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (Exception unused) {
        }
        return byteArray;
    }

    private boolean checkValidate() {
        PackageInfo packageInfo;
        PackageInfo packageInfo2;
        PackageInfo packageInfo3;
        try {
            packageInfo = this.mContext.getPackageManager().getPackageInfo("com.xiaomi.channel", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo != null) {
            this.miliaoEnable = true;
        } else {
            this.miliaoEnable = false;
        }
        try {
            packageInfo2 = this.mContext.getPackageManager().getPackageInfo("com.tencent.mm", 0);
        } catch (PackageManager.NameNotFoundException unused2) {
            packageInfo2 = null;
        }
        if (packageInfo2 != null) {
            this.weixinEnable = true;
        } else {
            this.weixinEnable = false;
        }
        try {
            packageInfo3 = this.mContext.getPackageManager().getPackageInfo(BuildConfig.b, 0);
        } catch (PackageManager.NameNotFoundException unused3) {
            packageInfo3 = null;
        }
        if (packageInfo3 != null) {
            this.sinaweiboEnable = true;
        } else {
            this.sinaweiboEnable = false;
        }
        return this.miliaoEnable || this.weixinEnable || this.sinaweiboEnable;
    }
}
