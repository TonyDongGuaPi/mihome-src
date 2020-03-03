package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.mi.global.bbs.view.dialog.NewShareDialog;
import com.mi.global.shop.model.Tags;
import com.sina.weibo.BuildConfig;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
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
import com.xiaomi.smarthome.framework.page.CommonShareActivity;
import com.xiaomi.smarthome.framework.plugin.FileUtils;
import com.xiaomi.smarthome.framework.plugin.ZipFileUtils;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ImageUtils;
import com.xiaomi.smarthome.library.common.widget.GridImageView;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.youpin.share.ShareObject;
import com.xiaomi.youpin.share.model.ShareChannel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CommonShareActivity extends BaseActivity {
    public static final String SHARE_CONTENT = "ShareContent";
    public static final String SHARE_DEVICE_MODEL = "share_device_model";
    public static final String SHARE_IMAGE = "Image";
    public static final String SHARE_IMAGE_BYTE_ARRAY = "ImageByteArray";
    public static final String SHARE_IMAGE_FILE_ZIP_URL = "SHARE_IMAGE_FILE_ZIP_URL";
    public static final String SHARE_IMAGE_FIlE_LIST = "ImageFileList";
    public static final String SHARE_IMAGE_URL_NO_ZIP = "ShareImageUrlNotZip";
    public static final String SHARE_NOT_USE_SDK = "NotUseSdk";
    public static final String SHARE_THUMB = "Thumb";
    public static final String SHARE_THUMB_URL = "ThumbUrl";
    public static final String SHARE_TITLE = "ShareTitle";
    public static final String SHARE_URL = "ShareUrl";
    public static final String SHARE_URL_LIST = "ShareUrlList";
    public static final String START_ACTIVITY_FROM = "StartActivityFrom";
    public static final String START_ACTIVITY_FROM_RN = "rn";
    public static final String TAG = "CommonShareActivity";

    /* renamed from: a  reason: collision with root package name */
    private String f16697a = "";
    private WbShareHandler b;
    private WebChromeClient c = new WebChromeClient() {
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (TextUtils.isEmpty(CommonShareActivity.this.mShareTitle)) {
                CommonShareActivity.this.mShareTitle = str;
            }
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
        }

        public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(CommonShareActivity.this);
            builder.b((CharSequence) str2);
            builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.confirm();
                }
            });
            builder.a(false);
            builder.b().show();
            return true;
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(CommonShareActivity.this);
            builder.b((CharSequence) str2);
            builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.confirm();
                }
            });
            builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.cancel();
                }
            });
            builder.a(false);
            builder.b().show();
            return true;
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            Toast.makeText(CommonShareActivity.this, str2, 0).show();
            return true;
        }

        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            super.onShowCustomView(view, customViewCallback);
        }
    };
    /* access modifiers changed from: private */
    public String d;
    GridImageView mGridImageView;
    Handler mHander = new Handler();
    ImageView mImageView;
    boolean mImageZipFileUrlIsNotZip = false;
    String mImagesZipFileUrl;
    byte[] mSharaImageByte;
    String mShareContent;
    String[] mShareFiles;
    Bitmap mShareImage;
    String mShareImageFile;
    String mShareThumbUrl;
    String mShareTitle;
    String mShareUrl;
    boolean mShareUseSdk = true;
    Bitmap mThumb;
    String mThumbFile;
    WebView mWebView;
    XQProgressDialog mXQProgressDialog;

    static {
        GameServiceClient.c(SHApplication.getAppContext());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TitleBarUtil.c(this);
        setContentView(R.layout.comm_share_activity);
        Intent intent = getIntent();
        this.mShareTitle = intent.getStringExtra("ShareTitle");
        this.mShareContent = intent.getStringExtra("ShareContent");
        this.mShareUrl = intent.getStringExtra(SHARE_URL);
        this.mShareThumbUrl = intent.getStringExtra(SHARE_THUMB_URL);
        this.mThumb = (Bitmap) intent.getParcelableExtra(SHARE_THUMB);
        this.mShareImage = (Bitmap) intent.getParcelableExtra(SHARE_IMAGE);
        this.mSharaImageByte = intent.getByteArrayExtra(SHARE_IMAGE_BYTE_ARRAY);
        this.mShareUseSdk = !intent.getBooleanExtra(SHARE_NOT_USE_SDK, false);
        this.mShareFiles = intent.getStringArrayExtra(SHARE_IMAGE_FIlE_LIST);
        this.f16697a = intent.getStringExtra(START_ACTIVITY_FROM);
        this.mImagesZipFileUrl = intent.getStringExtra(SHARE_IMAGE_FILE_ZIP_URL);
        if (TextUtils.isEmpty(this.mShareTitle)) {
            this.mShareTitle = intent.getStringExtra("title");
        }
        if (TextUtils.isEmpty(this.mShareContent)) {
            this.mShareContent = intent.getStringExtra("content");
        }
        if (TextUtils.isEmpty(this.mImagesZipFileUrl)) {
            this.mImagesZipFileUrl = intent.getStringExtra("pics");
        }
        if (TextUtils.isEmpty(this.mImagesZipFileUrl)) {
            this.mImagesZipFileUrl = intent.getStringExtra(SHARE_IMAGE_URL_NO_ZIP);
            this.mImageZipFileUrlIsNotZip = !TextUtils.isEmpty(this.mImagesZipFileUrl);
        }
        if (TextUtils.isEmpty(this.mShareUrl)) {
            this.mShareUrl = intent.getStringExtra("jump");
        }
        this.d = intent.getStringExtra(SHARE_DEVICE_MODEL);
        this.mWebView = (WebView) findViewById(R.id.webview);
        initWebView();
        this.mImageView = (ImageView) findViewById(R.id.imageview);
        this.mGridImageView = (GridImageView) findViewById(R.id.grid_image);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonShareActivity.this.finish();
                if (!TextUtils.isEmpty(CommonShareActivity.this.d)) {
                    STAT.d.ao(CommonShareActivity.this.d);
                }
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.share_title);
        if (!TextUtils.isEmpty(this.mImagesZipFileUrl)) {
            loadMultiImages();
        } else if (!TextUtils.isEmpty(this.mShareUrl)) {
            this.mWebView.setVisibility(0);
            this.mWebView.loadUrl(this.mShareUrl);
        } else if (this.mShareImage != null) {
            this.mImageView.setImageBitmap(this.mShareImage);
            this.mImageView.setVisibility(0);
            if (this.mThumb == null) {
                this.mThumb = ImageUtils.b(this.mShareImage, 150);
            }
        } else if (this.mSharaImageByte != null) {
            this.mShareImage = BitmapFactory.decodeByteArray(this.mSharaImageByte, 0, this.mSharaImageByte.length);
            this.mImageView.setImageBitmap(this.mShareImage);
            this.mImageView.setVisibility(0);
            if (this.mThumb == null) {
                this.mThumb = ImageUtils.b(this.mShareImage, 150);
            }
        } else {
            finish();
            return;
        }
        View findViewById = findViewById(R.id.weibo_share);
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonShareActivity.this.shareWeibo();
            }
        });
        View findViewById2 = findViewById(R.id.miliao_share);
        findViewById2.setVisibility(8);
        View findViewById3 = findViewById(R.id.wx_share);
        findViewById3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonShareActivity.this.shareWeixin();
            }
        });
        View findViewById4 = findViewById(R.id.friends_share);
        findViewById4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonShareActivity.this.shareWeixinPyq();
            }
        });
        View findViewById5 = findViewById(R.id.facebook_share);
        findViewById5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonShareActivity.this.shareFacebook();
            }
        });
        View findViewById6 = findViewById(R.id.line_share);
        findViewById6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonShareActivity.this.shareLine();
            }
        });
        View findViewById7 = findViewById(R.id.copy_share);
        findViewById7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((ClipboardManager) CommonShareActivity.this.getSystemService(ShareChannel.d)).setPrimaryClip(ClipData.newRawUri("", Uri.parse(CommonShareActivity.this.mShareUrl)));
                Toast.makeText(CommonShareActivity.this, R.string.share_url_copy_success, 0).show();
            }
        });
        View findViewById8 = findViewById(R.id.copy_share_foreign);
        findViewById8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((ClipboardManager) CommonShareActivity.this.getSystemService(ShareChannel.d)).setPrimaryClip(ClipData.newRawUri("", Uri.parse(CommonShareActivity.this.mShareUrl)));
                Toast.makeText(CommonShareActivity.this, R.string.share_url_copy_success, 0).show();
            }
        });
        View findViewById9 = findViewById(R.id.whatsapp_share);
        findViewById9.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CommonShareActivity.this.a(view);
            }
        });
        if (TextUtils.isEmpty(this.mShareUrl)) {
            findViewById7.setVisibility(8);
            findViewById8.setVisibility(8);
        }
        if (CoreApi.a().D()) {
            findViewById4.setVisibility(8);
            findViewById.setVisibility(8);
            findViewById3.setVisibility(8);
            findViewById2.setVisibility(8);
            findViewById7.setVisibility(8);
        } else {
            this.b = new WbShareHandler(this);
            findViewById5.setVisibility(8);
            findViewById6.setVisibility(8);
            findViewById8.setVisibility(8);
            findViewById9.setVisibility(8);
        }
        loadThumb();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        a();
    }

    /* access modifiers changed from: package-private */
    public void loadMultiImages() {
        this.mXQProgressDialog = new XQProgressDialog(this);
        this.mXQProgressDialog.setMessage(getString(R.string.refreshing));
        this.mXQProgressDialog.setCancelable(true);
        this.mXQProgressDialog.show();
        new Thread() {
            public void run() {
                CommonShareActivity.this.doLoadMultiImages();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0022, code lost:
        r1 = r7.mImagesZipFileUrl.substring(r2 + 1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doLoadMultiImages() {
        /*
            r7 = this;
            java.lang.String r0 = r7.mImagesZipFileUrl
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x00a9
            java.lang.String r0 = r7.mImagesZipFileUrl
            java.lang.String r1 = "http"
            boolean r0 = r0.startsWith(r1)
            if (r0 == 0) goto L_0x0082
            java.io.File r0 = r7.getExternalCacheDir()
            java.lang.String r1 = "tmp.zip"
            java.lang.String r2 = r7.mImagesZipFileUrl
            r3 = 47
            int r2 = r2.lastIndexOf(r3)
            if (r2 <= 0) goto L_0x0043
            java.lang.String r1 = r7.mImagesZipFileUrl
            int r2 = r2 + 1
            java.lang.String r1 = r1.substring(r2)
            r2 = 63
            int r2 = r1.indexOf(r2)
            if (r2 <= 0) goto L_0x0043
            r3 = 0
            java.lang.String r1 = r1.substring(r3, r2)
            java.lang.String r1 = android.net.Uri.encode(r1)
            r2 = 37
            r3 = 95
            java.lang.String r1 = r1.replace(r2, r3)
        L_0x0043:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r0 = r0.getAbsolutePath()
            r2.append(r0)
            java.lang.String r0 = java.io.File.separator
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = r2.toString()
            java.io.File r3 = new java.io.File
            r3.<init>(r0)
            android.content.Context r1 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.String r2 = r7.mImagesZipFileUrl
            r4 = 0
            r5 = 0
            r6 = 0
            com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse r1 = com.xiaomi.smarthome.library.common.network.NetworkUtils.a(r1, r2, r3, r4, r5, r6)
            int r1 = r1.b
            r2 = 3
            if (r1 != r2) goto L_0x007e
            boolean r1 = r7.mImageZipFileUrlIsNotZip
            if (r1 == 0) goto L_0x007a
            r7.loadLocalBitmap(r0)
            goto L_0x00ac
        L_0x007a:
            r7.loadMultiImagesFromZipFile(r0)
            goto L_0x00ac
        L_0x007e:
            r7.loadCompleted()
            goto L_0x00ac
        L_0x0082:
            java.io.File r0 = new java.io.File
            java.lang.String r1 = r7.mImagesZipFileUrl
            r0.<init>(r1)
            boolean r0 = r0.exists()
            if (r0 == 0) goto L_0x00a5
            java.lang.String r0 = r7.mImagesZipFileUrl
            java.lang.String r1 = ".zip"
            boolean r0 = r0.endsWith(r1)
            if (r0 == 0) goto L_0x009f
            java.lang.String r0 = r7.mImagesZipFileUrl
            r7.loadMultiImagesFromZipFile(r0)
            goto L_0x00ac
        L_0x009f:
            java.lang.String r0 = r7.mImagesZipFileUrl
            r7.loadLocalBitmap(r0)
            goto L_0x00ac
        L_0x00a5:
            r7.loadCompleted()
            goto L_0x00ac
        L_0x00a9:
            r7.loadCompleted()
        L_0x00ac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.page.CommonShareActivity.doLoadMultiImages():void");
    }

    /* access modifiers changed from: package-private */
    public void loadMultiImagesFromZipFile(String str) {
        Bitmap decodeFile;
        File externalCacheDir = getExternalCacheDir();
        FileUtils.e(externalCacheDir.getAbsolutePath());
        externalCacheDir.mkdirs();
        ZipFileUtils.a(str, externalCacheDir.getAbsolutePath());
        File[] listFiles = externalCacheDir.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    String lowerCase = listFiles[i].getPath().toLowerCase();
                    if (lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".png")) {
                        int lastIndexOf = lowerCase.lastIndexOf(File.separator);
                        if (lastIndexOf >= 0) {
                            String substring = lowerCase.substring(lastIndexOf + 1, lowerCase.lastIndexOf("."));
                            if (Tags.Kuwan.IMAGE_URL.equals(substring)) {
                                this.mThumbFile = listFiles[i].getAbsolutePath();
                            } else if ("pic".equals(substring)) {
                                this.mShareImageFile = listFiles[i].getAbsolutePath();
                            }
                        }
                        arrayList.add(listFiles[i].getAbsolutePath());
                    }
                }
            }
            this.mShareFiles = (String[]) arrayList.toArray(new String[0]);
        }
        if (!TextUtils.isEmpty(this.mThumbFile) && (decodeFile = BitmapFactory.decodeFile(this.mThumbFile)) != null) {
            this.mThumb = ImageUtils.b(decodeFile, 150);
        }
        if (!TextUtils.isEmpty(this.mShareImageFile)) {
            this.mShareImage = BitmapFactory.decodeFile(this.mShareImageFile);
        }
        if (this.mShareFiles != null && this.mShareFiles.length > 0) {
            Bitmap decodeFile2 = BitmapFactory.decodeFile(this.mShareFiles[0]);
            if (this.mShareImage == null) {
                this.mShareImage = decodeFile2;
            }
            if (this.mThumb == null) {
                this.mThumb = ImageUtils.b(decodeFile2, 150);
            }
        }
        loadCompleted();
    }

    /* access modifiers changed from: package-private */
    public void loadLocalBitmap(String str) {
        Bitmap decodeFile;
        this.mShareFiles = new String[]{str};
        if (!TextUtils.isEmpty(this.mThumbFile) && this.mThumb == null && (decodeFile = BitmapFactory.decodeFile(this.mThumbFile)) != null) {
            this.mThumb = ImageUtils.b(decodeFile, 150);
        }
        if (!TextUtils.isEmpty(this.mShareImageFile)) {
            this.mShareImage = BitmapFactory.decodeFile(this.mShareImageFile);
        }
        if (this.mShareFiles != null && this.mShareFiles.length > 0) {
            Bitmap decodeFile2 = BitmapFactory.decodeFile(this.mShareFiles[0]);
            if (this.mShareImage == null) {
                this.mShareImage = decodeFile2;
            }
            if (this.mShareImage == null || decodeFile2 == null) {
                Log.e(TAG, "mShareImage/bitmap is null, cannot share!!");
                finish();
                return;
            } else if (this.mThumb == null) {
                this.mThumb = ImageUtils.b(decodeFile2, 150);
            }
        }
        loadCompleted();
    }

    /* access modifiers changed from: package-private */
    public void loadCompleted() {
        this.mHander.post(new Runnable() {
            public void run() {
                if (CommonShareActivity.this.mShareFiles != null) {
                    if (CommonShareActivity.this.mShareFiles.length == 1) {
                        CommonShareActivity.this.mImageView.setImageBitmap(CommonShareActivity.this.mShareImage);
                        CommonShareActivity.this.mImageView.setVisibility(0);
                    } else {
                        CommonShareActivity.this.mGridImageView.setImageFiles(CommonShareActivity.this.mShareFiles);
                        CommonShareActivity.this.mGridImageView.setVisibility(0);
                    }
                    CommonShareActivity.this.mHander.postDelayed(new Runnable() {
                        public final void run() {
                            CommonShareActivity.AnonymousClass11.this.a();
                        }
                    }, 500);
                }
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a() {
                if (CommonShareActivity.this.mXQProgressDialog != null) {
                    CommonShareActivity.this.mXQProgressDialog.dismiss();
                    CommonShareActivity.this.mXQProgressDialog = null;
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void loadThumb() {
        if (this.mThumb == null && !TextUtils.isEmpty(this.mShareThumbUrl)) {
            Picasso.get().load(this.mShareThumbUrl).error((int) R.drawable.device_shop_image_default_logo).resize(150, 150).into((Target) new Target() {
                public void onBitmapFailed(Exception exc, Drawable drawable) {
                }

                public void onPrepareLoad(Drawable drawable) {
                }

                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                    CommonShareActivity.this.mThumb = bitmap;
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void initWebView() {
        WebSettings settings = this.mWebView.getSettings();
        settings.setLoadsImagesAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(getApplicationContext().getDir("database", 0).getPath());
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        this.mWebView.setWebChromeClient(this.c);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
        this.mWebView.requestFocus();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(this.mWebView, (Object[]) null);
            this.mWebView.loadUrl("");
        } catch (Exception unused) {
        }
        this.mWebView.removeAllViews();
        this.mWebView.destroy();
        this.mWebView = null;
        this.mThumb = null;
        this.mShareImage = null;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mWebView != null) {
            this.mWebView.onResume();
        }
        if (!TextUtils.isEmpty(this.d)) {
            STAT.c.a(this.d);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.mWebView != null) {
            this.mWebView.onPause();
        }
    }

    public void onBackPressed() {
        if (this.mWebView == null || !this.mWebView.isShown() || !this.mWebView.canGoBack()) {
            super.onBackPressed();
        } else {
            this.mWebView.goBack();
        }
    }

    /* access modifiers changed from: package-private */
    public void shareResult(boolean z) {
        if (z) {
            Toast.makeText(this, R.string.device_shop_share_success, 0).show();
        } else {
            Toast.makeText(this, R.string.device_shop_share_failure, 0).show();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isNotUseSdkShare() {
        return this.mShareFiles != null && this.mShareFiles.length > 1;
    }

    public void shareMiliao() {
        try {
            if (!checkAppValidate("com.xiaomi.channel")) {
                Toast.makeText(this, R.string.device_shop_share_no_miliao, 1).show();
                return;
            }
            StatHelper.V();
            if (this.mShareFiles == null || this.mShareFiles.length <= 1) {
                MLShareApiFactory mLShareApiFactory = new MLShareApiFactory(this);
                mLShareApiFactory.a(getPackageName(), getString(R.string.app_name2));
                MLShareMessage mLShareMessage = new MLShareMessage();
                mLShareMessage.c = this.mShareTitle;
                mLShareMessage.b = this.mShareContent;
                mLShareMessage.e = this.mShareThumbUrl;
                mLShareMessage.f10070a = this.mShareUrl;
                if (this.mShareImage != null) {
                    mLShareMessage.d = new MLImgObj(this.mShareImage);
                } else if (this.mThumb != null) {
                    mLShareMessage.d = new MLImgObj(this.mThumb);
                }
                mLShareApiFactory.a((IShareReq) new MLShareReq(mLShareMessage, ShareConstants.N), false);
                return;
            }
            share(new String[]{"com.xiaomi.channel.control.SystemShareActivity"}, this.mShareFiles, this.mShareTitle, this.mShareContent);
        } catch (RemoteException unused) {
            shareResult(false);
        }
    }

    private void a() {
        if (!checkAppValidate(NewShareDialog.WHATSAPP_PACKAGE_NAME)) {
            Toast.makeText(this, getResources().getString(R.string.device_shop_share_no_whatsapp), 0).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setPackage(NewShareDialog.WHATSAPP_PACKAGE_NAME);
        if (this.mShareImage != null) {
            intent.putExtra("android.intent.extra.STREAM", Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), this.mShareImage, (String) null, (String) null)));
            intent.setType("image/jpeg");
        } else if (this.mShareUrl != null) {
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TEXT", this.mShareUrl);
        }
        startActivity(Intent.createChooser(intent, "Share to"));
        if (!TextUtils.isEmpty(this.d)) {
            STAT.d.au(this.d);
        }
    }

    public void shareLine() {
        if (!checkAppValidate(NewShareDialog.LINE_PACKAGE_NAME)) {
            Toast.makeText(this, getResources().getString(R.string.device_shop_share_no_line), 0).show();
            return;
        }
        try {
            ComponentName componentName = new ComponentName(NewShareDialog.LINE_PACKAGE_NAME, "jp.naver.line.android.activity.selectchat.SelectChatActivity");
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            if (this.mShareImage != null) {
                intent.putExtra("android.intent.extra.STREAM", Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), this.mShareImage, (String) null, (String) null)));
                intent.setType("image/jpeg");
            } else if (this.mShareUrl != null) {
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", this.mShareUrl);
            }
            intent.setComponent(componentName);
            startActivity(Intent.createChooser(intent, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(this.d)) {
            STAT.d.at(this.d);
        }
    }

    public void shareFacebook() {
        if (!checkAppValidate("com.facebook.katana")) {
            Toast.makeText(this, getResources().getString(R.string.device_shop_share_no_facebook), 0).show();
            return;
        }
        if (this.mShareImage != null) {
            new ShareDialog((Activity) this).show((ShareContent) new SharePhotoContent.Builder().addPhoto(new SharePhoto.Builder().setBitmap(this.mShareImage).build()).build(), ShareDialog.Mode.AUTOMATIC);
        } else if (this.mShareUrl != null) {
            new ShareDialog((Activity) this).show((ShareContent) new ShareLinkContent.Builder().readFrom(((ShareLinkContent.Builder) new ShareLinkContent.Builder().setContentUrl(Uri.parse(this.mShareUrl))).build()).build(), ShareDialog.Mode.AUTOMATIC);
        }
        if (!TextUtils.isEmpty(this.d)) {
            STAT.d.as(this.d);
        }
    }

    public void shareWeixin() {
        if (!checkAppValidate("com.tencent.mm")) {
            Toast.makeText(this, R.string.device_shop_share_no_weixin, 1).show();
            return;
        }
        StatHelper.X();
        if (this.mShareFiles == null || this.mShareFiles.length <= 1) {
            IWXAPI iwxapi = SHApplication.getIWXAPI();
            WXMediaMessage wXMediaMessage = new WXMediaMessage();
            wXMediaMessage.title = this.mShareTitle;
            wXMediaMessage.description = this.mShareContent;
            wXMediaMessage.setThumbImage(this.mThumb);
            if (!TextUtils.isEmpty(this.mShareUrl)) {
                wXMediaMessage.mediaObject = new WXWebpageObject(this.mShareUrl);
            } else if (this.mShareImage != null) {
                wXMediaMessage.mediaObject = new WXImageObject(this.mShareImage);
            }
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = wXMediaMessage;
            req.scene = 0;
            boolean sendReq = iwxapi.sendReq(req);
            Miio.h(TAG, "sendReq return " + sendReq);
            if (!TextUtils.isEmpty(this.d)) {
                STAT.d.ap(this.d);
                return;
            }
            return;
        }
        share(new String[]{"com.tencent.mm.ui.tools.ShareImgUI"}, this.mShareFiles, this.mShareTitle, this.mShareContent);
    }

    public void shareWeixinPyq() {
        if (!checkAppValidate("com.tencent.mm")) {
            Toast.makeText(this, R.string.device_shop_share_no_weixin, 1).show();
            return;
        }
        StatHelper.Y();
        if (isNotUseSdkShare()) {
            share(new String[]{"com.tencent.mm.ui.tools.ShareToTimeLineUI"}, this.mShareFiles, this.mShareTitle, this.mShareContent);
            return;
        }
        IWXAPI iwxapi = SHApplication.getIWXAPI();
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = this.mShareTitle;
        wXMediaMessage.description = this.mShareContent;
        wXMediaMessage.setThumbImage(this.mThumb);
        if (!TextUtils.isEmpty(this.mShareUrl)) {
            wXMediaMessage.mediaObject = new WXWebpageObject(this.mShareUrl);
        } else if (this.mShareImage != null) {
            wXMediaMessage.mediaObject = new WXImageObject(this.mShareImage);
        }
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wXMediaMessage;
        req.scene = 1;
        boolean sendReq = iwxapi.sendReq(req);
        Miio.h(TAG, "sendReq return " + sendReq);
        if (!TextUtils.isEmpty(this.d)) {
            STAT.d.aq(this.d);
        }
    }

    public void shareWeibo() {
        if (this.b == null || !checkAppValidate(BuildConfig.b)) {
            Toast.makeText(this, R.string.device_shop_share_no_weibo, 1).show();
            return;
        }
        StatHelper.W();
        if (isNotUseSdkShare()) {
            share(new String[]{"com.sina.weibo.EditActivity", "com.sina.weibo.ComposerDispatchActivity", "com.sina.weibo.composerinde.ComposerDispatchActivity"}, this.mShareFiles, this.mShareTitle, this.mShareContent);
            return;
        }
        this.b.registerApp();
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        if (this.mShareImage != null) {
            ImageObject imageObject = new ImageObject();
            imageObject.setImageObject(this.mShareImage);
            weiboMultiMessage.imageObject = imageObject;
        }
        TextObject textObject = new TextObject();
        textObject.text = "";
        if (!TextUtils.isEmpty(this.mShareTitle)) {
            textObject.text = "#" + this.mShareTitle + "#";
            weiboMultiMessage.textObject = textObject;
        }
        if ("rn".equalsIgnoreCase(this.f16697a) && !TextUtils.isEmpty(this.mShareContent)) {
            weiboMultiMessage.textObject = textObject;
            textObject.text += this.mShareContent + " ";
        }
        if (!TextUtils.isEmpty(this.mShareUrl)) {
            textObject.text += this.mShareUrl;
        }
        this.b.shareMessage(weiboMultiMessage, true);
        if (!TextUtils.isEmpty(this.d)) {
            STAT.d.ar(this.d);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.b != null) {
            this.b.doResultIntent(intent, new WbShareCallback() {
                public void onWbShareCancel() {
                }

                public void onWbShareSuccess() {
                    Toast.makeText(CommonShareActivity.this, R.string.device_shop_share_success, 0).show();
                }

                public void onWbShareFail() {
                    Toast.makeText(CommonShareActivity.this, R.string.device_shop_share_failure, 0).show();
                }
            });
        }
    }

    public boolean checkAppValidate(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public void share(String[] strArr, String[] strArr2, String str, String str2) {
        if (strArr != null && strArr.length != 0 && strArr2 != null && strArr2.length != 0) {
            String str3 = "android.intent.action.SEND";
            if (strArr2.length > 1) {
                str3 = "android.intent.action.SEND_MULTIPLE";
            }
            Intent shareActivityIntent = getShareActivityIntent(strArr, str3);
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
                shareActivityIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(a(strArr2[0])));
            }
            getContext().startActivity(shareActivityIntent);
        }
    }

    private File a(String str) {
        if (!str.startsWith(com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.a((Context) this))) {
            return new File(str);
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        if (lastIndexOf < 1) {
            return new File(str);
        }
        int lastIndexOf2 = str.lastIndexOf(46);
        File file = new File(getExternalCacheDir().getAbsolutePath(), "temp_share");
        FileUtils.e(file.getAbsolutePath());
        String absolutePath = file.getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        sb.append("p");
        sb.append(lastIndexOf2 > lastIndexOf ? str.substring(lastIndexOf2) : "");
        File file2 = new File(absolutePath, sb.toString());
        FileUtils.g(file2.getAbsolutePath());
        FileUtils.a(str, file2.getAbsolutePath());
        return file2;
    }

    /* access modifiers changed from: package-private */
    public Intent getShareActivityIntent(String[] strArr, String str) {
        Intent intent = new Intent();
        intent.setAction(str).setType(ShareObject.d);
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
                        intent2.setType(ShareObject.d);
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
