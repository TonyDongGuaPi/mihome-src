package com.xiaomi.smarthome.framework.page;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;
import com.mi.global.bbs.view.dialog.NewShareDialog;
import com.sina.weibo.BuildConfig;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xiaomi.channel.gamesdk.GameServiceClient;
import com.xiaomi.channel.sdk.IShareReq;
import com.xiaomi.channel.sdk.MLImgObj;
import com.xiaomi.channel.sdk.MLShareApiFactory;
import com.xiaomi.channel.sdk.MLShareMessage;
import com.xiaomi.channel.sdk.MLShareReq;
import com.xiaomi.channel.sdk.ShareConstants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.util.ImageUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.youpin.share.ShareObject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PictureShareActivity extends BaseActivity {
    public static final String SHARE_CONTENT = "ShareContent";
    public static final String SHARE_PIC_FILE = "SharePicFile";
    public static final String SHARE_TITLE = "ShareTitle";
    public static final String TAG = "PictureShareActivity";

    /* renamed from: a  reason: collision with root package name */
    private FrameLayout f16893a;
    private TextView b;
    private TextView c;
    private TextView d;
    private WbShareHandler e;
    @BindView(2131429434)
    TextView friendsShare;
    protected Context mContext;
    View mEmpty;
    boolean mFinishing;
    boolean mIsVideo = false;
    View mMainview = null;
    View mMainviewFrame = null;
    String mShareContent;
    Bitmap mShareImage;
    String mSharePicFile;
    String mShareTitle;
    Bitmap mThumb;
    @BindView(2131430922)
    TextView miliaoShare;
    @BindView(2131433981)
    TextView weiboShare;
    @BindView(2131434026)
    TextView wxShare;

    /* access modifiers changed from: package-private */
    public boolean isNotUseSdkShare() {
        return true;
    }

    static {
        GameServiceClient.c(SHApplication.getAppContext());
    }

    public static void share(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, PictureShareActivity.class);
        intent.putExtra(SHARE_PIC_FILE, str3);
        intent.putExtra("ShareTitle", str);
        intent.putExtra("ShareContent", str2);
        context.startActivity(intent);
    }

    public static void share(Activity activity, String str, String str2, String str3, int i) {
        Intent intent = new Intent(activity, PictureShareActivity.class);
        intent.putExtra(SHARE_PIC_FILE, str3);
        intent.putExtra("ShareTitle", str);
        intent.putExtra("ShareContent", str2);
        activity.startActivityForResult(intent, i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mFinishing = false;
        overridePendingTransition(0, 0);
        this.mShareTitle = getIntent().getStringExtra("ShareTitle");
        this.mShareContent = getIntent().getStringExtra("ShareContent");
        this.mSharePicFile = getIntent().getStringExtra(SHARE_PIC_FILE);
        if (!TextUtils.isEmpty(this.mSharePicFile)) {
            if (this.mSharePicFile.endsWith(".mp4")) {
                this.mIsVideo = true;
            }
            if (!this.mIsVideo) {
                this.mShareImage = BitmapFactory.decodeFile(this.mSharePicFile);
                if (this.mShareImage == null) {
                    setResult(0);
                    finish();
                    return;
                }
                this.mThumb = ImageUtils.b(this.mShareImage, 150);
            }
            this.mContext = this;
            setContentView(R.layout.picture_share_dialog);
            this.mMainview = findViewById(R.id.share_btn_container);
            this.mMainviewFrame = findViewById(R.id.root_container);
            this.f16893a = (FrameLayout) findViewById(R.id.international_share_container);
            this.b = (TextView) findViewById(R.id.facebook_share);
            this.c = (TextView) findViewById(R.id.line_share);
            this.d = (TextView) findViewById(R.id.whatsapp_share);
            this.mEmpty = findViewById(R.id.empty);
            this.mEmpty.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PictureShareActivity.this.onBackPressed();
                }
            });
            findViewById(R.id.miliao_share).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PictureShareActivity.this.shareMiliao();
                    PictureShareActivity.this.sharedFinish();
                }
            });
            findViewById(R.id.miliao_share).setVisibility(8);
            findViewById(R.id.wx_share).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PictureShareActivity.this.shareWeixin();
                    PictureShareActivity.this.sharedFinish();
                }
            });
            if (this.mIsVideo) {
                findViewById(R.id.friends_share).setVisibility(8);
            }
            findViewById(R.id.friends_share).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PictureShareActivity.this.shareWeixinPyq();
                    PictureShareActivity.this.sharedFinish();
                }
            });
            if (this.mIsVideo) {
                findViewById(R.id.weibo_share).setVisibility(8);
            }
            findViewById(R.id.weibo_share).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PictureShareActivity.this.shareWeibo();
                    PictureShareActivity.this.sharedFinish();
                }
            });
            this.b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PictureShareActivity.this.shareFacebook();
                    PictureShareActivity.this.sharedFinish();
                }
            });
            this.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PictureShareActivity.this.shareLine();
                    PictureShareActivity.this.sharedFinish();
                }
            });
            this.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PictureShareActivity.this.c();
                    PictureShareActivity.this.sharedFinish();
                }
            });
            if (this.mIsVideo) {
                this.c.setVisibility(8);
            }
            if (CoreApi.a().D()) {
                this.mMainview.setVisibility(8);
                return;
            }
            this.f16893a.setVisibility(8);
            this.e = new WbShareHandler(this);
            return;
        }
        setResult(0);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void onBackPressed() {
        setResult(0);
        b();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: package-private */
    public void sharedFinish() {
        setResult(-1);
        b();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: private */
    public void a() {
        this.mFinishing = true;
        finish();
        overridePendingTransition(0, 0);
    }

    private void b() {
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
                PictureShareActivity.this.a();
            }
        });
        if (this.mMainview.getVisibility() == 0) {
            this.mMainview.startAnimation(loadAnimation);
        } else if (this.f16893a.getVisibility() == 0) {
            this.f16893a.startAnimation(loadAnimation);
        }
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
            if (this.mMainview.getVisibility() == 0) {
                this.mMainview.setVisibility(0);
                this.mMainview.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom));
                return;
            }
            this.f16893a.setVisibility(0);
            this.f16893a.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom));
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mShareImage != null) {
            this.mShareImage.recycle();
            this.mShareImage = null;
        }
        if (this.mThumb != null) {
            this.mThumb.recycle();
            this.mThumb = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Miio.h(TAG, "onNewIntent");
        if (this.e != null) {
            this.e.doResultIntent(intent, new WbShareCallback() {
                public void onWbShareSuccess() {
                    ToastUtil.a((int) R.string.device_shop_share_success);
                    PictureShareActivity.this.sharedFinish();
                }

                public void onWbShareCancel() {
                    ToastUtil.a((int) R.string.device_shop_share_cancel);
                    PictureShareActivity.this.sharedFinish();
                }

                public void onWbShareFail() {
                    ToastUtil.a((int) R.string.device_shop_share_failure);
                    PictureShareActivity.this.sharedFinish();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void shareResult(boolean z) {
        if (z) {
            Toast.makeText(this.mContext, R.string.device_shop_share_success, 0).show();
        } else {
            Toast.makeText(this.mContext, R.string.device_shop_share_failure, 0).show();
        }
    }

    public boolean checkAppValidate(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = this.mContext.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public void shareMiliao() {
        if (this.mIsVideo) {
            share(new String[]{"com.xiaomi.channel.share.ui.SystemShareActivity"}, new String[]{this.mSharePicFile}, this.mShareTitle, this.mShareContent);
            return;
        }
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                try {
                    if (!PictureShareActivity.this.checkAppValidate("com.xiaomi.channel")) {
                        Toast.makeText(PictureShareActivity.this.mContext, R.string.device_shop_share_no_miliao, 1).show();
                        return;
                    }
                    MLShareApiFactory mLShareApiFactory = new MLShareApiFactory(PictureShareActivity.this.mContext);
                    mLShareApiFactory.a(PictureShareActivity.this.mContext.getPackageName(), PictureShareActivity.this.getString(R.string.app_name2));
                    MLShareMessage mLShareMessage = new MLShareMessage();
                    mLShareMessage.c = PictureShareActivity.this.mShareTitle;
                    mLShareMessage.b = PictureShareActivity.this.mShareContent;
                    if (PictureShareActivity.this.mShareImage != null) {
                        mLShareMessage.d = new MLImgObj(PictureShareActivity.this.mShareImage);
                    } else if (PictureShareActivity.this.mThumb != null) {
                        mLShareMessage.d = new MLImgObj(PictureShareActivity.this.mThumb);
                    }
                    mLShareApiFactory.a((IShareReq) new MLShareReq(mLShareMessage, ShareConstants.N), false);
                } catch (RemoteException unused) {
                    PictureShareActivity.this.shareResult(false);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        if (!checkAppValidate(NewShareDialog.WHATSAPP_PACKAGE_NAME)) {
            Toast.makeText(this, getResources().getString(R.string.device_shop_share_no_whatsapp), 0).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setPackage(NewShareDialog.WHATSAPP_PACKAGE_NAME);
        try {
            if (this.mShareImage != null) {
                intent.putExtra("android.intent.extra.STREAM", Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), this.mShareImage, (String) null, (String) null)));
                intent.setType("image/jpeg");
            } else if (this.mIsVideo) {
                Uri fromFile = Uri.fromFile(new File(this.mSharePicFile));
                intent.setType(ShareObject.e);
                intent.putExtra("android.intent.extra.STREAM", fromFile);
            } else {
                return;
            }
            startActivity(Intent.createChooser(intent, "Share to"));
        } catch (Exception e2) {
            e2.printStackTrace();
            ToastUtil.a((int) R.string.unknown_error_info);
        }
    }

    public void shareLine() {
        if (!checkAppValidate(NewShareDialog.LINE_PACKAGE_NAME)) {
            Toast.makeText(this, getResources().getString(R.string.device_shop_share_no_line), 0).show();
            return;
        }
        ComponentName componentName = new ComponentName(NewShareDialog.LINE_PACKAGE_NAME, "jp.naver.line.android.activity.selectchat.SelectChatActivity");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        try {
            if (this.mShareImage != null) {
                intent.putExtra("android.intent.extra.STREAM", Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), this.mShareImage, (String) null, (String) null)));
                intent.setType("image/jpeg");
            } else if (this.mIsVideo) {
                intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(this.mSharePicFile)));
                intent.setType(ShareObject.e);
            } else {
                return;
            }
            intent.setComponent(componentName);
            startActivity(Intent.createChooser(intent, ""));
        } catch (Exception e2) {
            e2.printStackTrace();
            ToastUtil.a((int) R.string.unknown_error_info);
        }
    }

    public void shareFacebook() {
        if (!checkAppValidate("com.facebook.katana")) {
            Toast.makeText(this, getResources().getString(R.string.device_shop_share_no_facebook), 0).show();
        } else if (this.mShareImage != null) {
            new ShareDialog((Activity) this).show((ShareContent) new SharePhotoContent.Builder().addPhoto(new SharePhoto.Builder().setBitmap(this.mShareImage).build()).build(), ShareDialog.Mode.AUTOMATIC);
        } else if (this.mIsVideo) {
            new ShareDialog((Activity) this).show((ShareContent) new ShareVideoContent.Builder().setVideo(new ShareVideo.Builder().setLocalUrl(Uri.fromFile(new File(this.mSharePicFile))).build()).build(), ShareDialog.Mode.AUTOMATIC);
        }
    }

    public void shareWeixin() {
        if (!checkAppValidate("com.tencent.mm")) {
            Toast.makeText(this.mContext, R.string.device_shop_share_no_weixin, 1).show();
        } else if (this.mIsVideo) {
            share(new String[]{"com.tencent.mm.ui.tools.ShareImgUI"}, new String[]{this.mSharePicFile}, this.mShareTitle, this.mShareContent);
        } else {
            IWXAPI iwxapi = SHApplication.getIWXAPI();
            WXMediaMessage wXMediaMessage = new WXMediaMessage();
            wXMediaMessage.title = this.mShareTitle;
            wXMediaMessage.description = this.mShareContent;
            wXMediaMessage.setThumbImage(this.mThumb);
            if (this.mShareImage != null) {
                wXMediaMessage.mediaObject = new WXImageObject(this.mShareImage);
            }
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = wXMediaMessage;
            req.scene = 0;
            boolean sendReq = iwxapi.sendReq(req);
            String str = TAG;
            Miio.h(str, "sendReq return " + sendReq);
        }
    }

    public void shareWeixinPyq() {
        if (!this.mIsVideo) {
            if (!checkAppValidate("com.tencent.mm")) {
                Toast.makeText(this.mContext, R.string.device_shop_share_no_weixin, 1).show();
                return;
            }
            IWXAPI iwxapi = SHApplication.getIWXAPI();
            WXMediaMessage wXMediaMessage = new WXMediaMessage();
            wXMediaMessage.title = this.mShareTitle;
            wXMediaMessage.description = this.mShareContent;
            wXMediaMessage.setThumbImage(this.mThumb);
            if (this.mShareImage != null) {
                wXMediaMessage.mediaObject = new WXImageObject(this.mShareImage);
            }
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = wXMediaMessage;
            req.scene = 1;
            boolean sendReq = iwxapi.sendReq(req);
            String str = TAG;
            Miio.h(str, "sendReq return " + sendReq);
        }
    }

    public void shareWeibo() {
        if (!this.mIsVideo) {
            if (this.e == null || !checkAppValidate(BuildConfig.b)) {
                Toast.makeText(this.mContext, R.string.device_shop_share_no_weibo, 1).show();
            } else if (isNotUseSdkShare()) {
                share(new String[]{"com.sina.weibo.EditActivity", "com.sina.weibo.ComposerDispatchActivity", "com.sina.weibo.composerinde.ComposerDispatchActivity"}, new String[]{this.mSharePicFile}, this.mShareTitle, this.mShareContent);
            } else {
                this.e.registerApp();
                WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
                WebpageObject webpageObject = new WebpageObject();
                webpageObject.identify = Utility.a();
                webpageObject.title = this.mShareTitle;
                webpageObject.description = this.mShareContent;
                if (this.mThumb != null) {
                    webpageObject.setThumbImage(this.mThumb);
                }
                webpageObject.actionUrl = "";
                webpageObject.defaultText = this.mShareContent;
                weiboMultiMessage.mediaObject = webpageObject;
                if (this.mShareImage != null) {
                    ImageObject imageObject = new ImageObject();
                    imageObject.setImageObject(this.mShareImage);
                    weiboMultiMessage.imageObject = imageObject;
                }
                TextObject textObject = new TextObject();
                textObject.text = "#" + this.mShareTitle + "# " + this.mShareContent;
                weiboMultiMessage.textObject = textObject;
                this.e.shareMessage(weiboMultiMessage, true);
            }
        }
    }

    public void share(String[] strArr, String[] strArr2, String str, String str2) {
        if (strArr != null && strArr.length != 0 && strArr2 != null && strArr2.length != 0) {
            String str3 = "android.intent.action.SEND";
            if (strArr2.length > 1) {
                str3 = "android.intent.action.SEND_MULTIPLE";
            }
            Intent shareActivityIntent = getShareActivityIntent(strArr, str3, this.mIsVideo);
            if (shareActivityIntent == null) {
                Toast.makeText(getContext(), R.string.share_score_share_no_install, 0).show();
                return;
            }
            if (!TextUtils.isEmpty(this.mShareTitle)) {
                shareActivityIntent.putExtra("android.intent.extra.SUBJECT", str);
            }
            if (!TextUtils.isEmpty(this.mShareContent)) {
                shareActivityIntent.putExtra("android.intent.extra.TEXT", str2);
            }
            if (strArr2.length > 1) {
                ArrayList arrayList = new ArrayList();
                for (String str4 : strArr2) {
                    arrayList.add(Uri.parse("file://" + str4));
                }
                shareActivityIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
            } else {
                shareActivityIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(strArr2[0])));
            }
            getContext().startActivity(shareActivityIntent);
        }
    }

    /* access modifiers changed from: package-private */
    public Intent getShareActivityIntent(String[] strArr, String str, boolean z) {
        Intent intent = new Intent();
        String str2 = ShareObject.d;
        if (z) {
            str2 = ShareObject.e;
        }
        intent.setAction(str).setType(str2);
        List<ResolveInfo> queryIntentActivities = getContext().getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.isEmpty()) {
            return null;
        }
        for (ResolveInfo next : queryIntentActivities) {
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    if (next.activityInfo.name.contains(strArr[i])) {
                        Intent intent2 = new Intent(str);
                        intent2.setType(str2);
                        intent2.setClassName(next.activityInfo.packageName, next.activityInfo.name);
                        intent2.addFlags(268435457);
                        return intent2;
                    }
                    i++;
                }
            }
        }
        return null;
    }
}
