package com.mi.global.bbs.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.OnSheetDismissedListener;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.NewShareAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.model.NewShareInfo;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.ImageUtil;
import com.mi.global.bbs.utils.LocaleHelper;
import com.mi.global.bbs.utils.ShareHelper;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.util.permission.Permission;
import com.mi.util.permission.PermissionCallback;
import com.mi.util.permission.PermissionUtil;
import com.mi.util.permission.SamplePermissionCallback;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.xiaomi.stat.d;
import com.xiaomi.youpin.share.ShareObject;
import com.xiaomi.youpin.share.model.ShareChannel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NewShareDialog extends Dialog implements View.OnClickListener, NewShareAdapter.onItemClickListener {
    public static final String FROM_COLUMN = "from_column";
    public static final String FROM_GALLERY = "from_gallery";
    public static final String FROM_THREAD = "from_thread";
    public static final String INSTAGRAM_PACKAGE_NAME = "com.instagram.android";
    public static final String LINE_PACKAGE_NAME = "jp.naver.line.android";
    public static final String MESSENGER_PACKAGE_NAME = "com.facebook.orca";
    public static final String OK_PACKAGE_NAME = "ru.ok.android";
    public static final int PHOTO_SHARE_WITH_IMAGE = 1;
    public static final int PHOTO_SHARE_WITH_LINK = 2;
    public static final String TELEGRAM_PACKAGE_NAME = "org.telegram.messenger";
    public static final String VIBER_PACKAGE_NAME = "com.viber.voip";
    public static final String VK_PACKAGE_NAME = "com.vkontakte.android";
    public static final String WHATSAPP_PACKAGE_NAME = "com.whatsapp";
    public static final String ZALO_PACKAGE_NAME = "com.zing.zalo";
    BottomSheetLayout bottomSheetLayout;
    private CallbackManager callbackManager;
    private Runnable clickRunnable;
    /* access modifiers changed from: private */
    public Context context;
    @BindView(2131493169)
    LinearLayout copyLinkLy;
    @BindView(2131493218)
    LinearLayout emailLy;
    /* access modifiers changed from: private */
    public String exif = "";
    private String from = FROM_THREAD;
    /* access modifiers changed from: private */
    public String imgPosition = "";
    /* access modifiers changed from: private */
    public String mShareImgUrl = "";
    /* access modifiers changed from: private */
    public String mTid = "";
    @BindView(2131493736)
    LinearLayout newShareImageLayout;
    @BindView(2131493737)
    LinearLayout newShareLinkLayout;
    @BindView(2131493738)
    LinearLayout newShareTopLayout;
    @BindView(2131493886)
    LinearLayout reportLy;
    @BindView(2131493908)
    LinearLayout saveLy;
    private List<NewShareInfo> shareAppList;
    @BindView(2131493962)
    ImageView shareImageImg;
    @BindView(2131493963)
    ImageView shareImgSelectImg;
    @BindView(2131493967)
    TextView shareLinkDes;
    @BindView(2131493968)
    ImageView shareLinkImg;
    @BindView(2131493969)
    ImageView shareLinkSelectImg;
    @BindView(2131493970)
    RecyclerView shareListView;
    /* access modifiers changed from: private */
    public int shareStyle = 2;
    private String shareText;
    @BindView(2131493974)
    TextView shareToTitle;
    private String shareUrl;
    @BindView(2131494006)
    LinearLayout smsLy;
    private String title;

    public NewShareDialog(Context context2) {
        super(context2, R.style.ShareDialog);
        this.context = context2;
        this.from = FROM_THREAD;
        this.exif = "";
        setContentView(R.layout.bottom_sheet_layout);
        this.bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottom_root);
        View inflate = LayoutInflater.from(context2).inflate(R.layout.new_share_dialog_layout, this.bottomSheetLayout, false);
        ButterKnife.bind((Object) this, inflate);
        initDialog(inflate);
    }

    public NewShareDialog(Context context2, String str) {
        super(context2, R.style.ShareDialog);
        this.context = context2;
        this.from = str;
        this.exif = "";
        this.mShareImgUrl = "";
        setContentView(R.layout.bottom_sheet_layout);
        this.bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottom_root);
        View inflate = LayoutInflater.from(context2).inflate(R.layout.new_share_dialog_layout, this.bottomSheetLayout, false);
        ButterKnife.bind((Object) this, inflate);
        initDialog(inflate);
    }

    public NewShareDialog(Context context2, String str, String str2, String str3) {
        super(context2, R.style.ShareDialog);
        this.context = context2;
        this.from = str3;
        this.exif = "";
        this.title = str;
        this.mShareImgUrl = str2;
        setContentView(R.layout.bottom_sheet_layout);
        this.bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottom_root);
        View inflate = LayoutInflater.from(context2).inflate(R.layout.new_share_dialog_layout, this.bottomSheetLayout, false);
        ButterKnife.bind((Object) this, inflate);
        initDialog(inflate);
    }

    public NewShareDialog(Context context2, String str, String str2, String str3, String str4) {
        super(context2, R.style.ShareDialog);
        this.context = context2;
        this.from = str;
        this.exif = "";
        this.mShareImgUrl = str2;
        this.imgPosition = str4;
        this.mTid = str3;
        this.exif = "";
        setContentView(R.layout.bottom_sheet_layout);
        this.bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottom_root);
        View inflate = LayoutInflater.from(context2).inflate(R.layout.new_share_dialog_layout, this.bottomSheetLayout, false);
        ButterKnife.bind((Object) this, inflate);
        initDialog(inflate);
    }

    public NewShareDialog(Context context2, String str, String str2, String str3, String str4, String str5) {
        super(context2, R.style.ShareDialog);
        this.context = context2;
        this.from = str;
        this.mShareImgUrl = str2;
        this.imgPosition = str4;
        this.exif = str5;
        this.mTid = str3;
        setContentView(R.layout.bottom_sheet_layout);
        this.bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottom_root);
        View inflate = LayoutInflater.from(context2).inflate(R.layout.new_share_dialog_layout, this.bottomSheetLayout, false);
        ButterKnife.bind((Object) this, inflate);
        initDialog(inflate);
    }

    public NewShareDialog(Context context2, String str, String str2, String str3, String str4, String str5, String str6) {
        super(context2, R.style.ShareDialog);
        this.context = context2;
        this.from = str;
        this.mShareImgUrl = str2;
        this.imgPosition = str4;
        this.exif = str5;
        this.mTid = str3;
        this.title = str6;
        setContentView(R.layout.bottom_sheet_layout);
        this.bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottom_root);
        View inflate = LayoutInflater.from(context2).inflate(R.layout.new_share_dialog_layout, this.bottomSheetLayout, false);
        ButterKnife.bind((Object) this, inflate);
        initDialog(inflate);
    }

    private void initDialog(View view) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = getWindow();
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -1;
        window.setAttributes(layoutParams);
        setVisiable();
        this.reportLy.setOnClickListener(this);
        this.emailLy.setOnClickListener(this);
        this.smsLy.setOnClickListener(this);
        this.copyLinkLy.setOnClickListener(this);
        this.saveLy.setOnClickListener(this);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this.context);
        wrapContentLinearLayoutManager.setOrientation(0);
        this.shareListView.setLayoutManager(wrapContentLinearLayoutManager);
        this.shareAppList = setShareApps(this.context);
        NewShareAdapter newShareAdapter = new NewShareAdapter(this.context, this.shareAppList);
        newShareAdapter.setOnItemClickListener(this);
        this.shareListView.setAdapter(newShareAdapter);
        if (this.from.equals(FROM_GALLERY)) {
            this.bottomSheetLayout.setPeekSheetTranslation((float) this.context.getResources().getDimensionPixelOffset(R.dimen.new_share_dialog_img_default_height));
        } else {
            this.bottomSheetLayout.setPeekSheetTranslation((float) this.context.getResources().getDimensionPixelOffset(R.dimen.new_share_dialog_default_height));
        }
        this.bottomSheetLayout.showWithSheetView(view);
        this.bottomSheetLayout.peekSheet();
        this.bottomSheetLayout.addOnSheetDismissedListener(new OnSheetDismissedListener() {
            public void onDismissed(BottomSheetLayout bottomSheetLayout) {
                NewShareDialog.this.dismiss();
            }
        });
        setPhotoTopView();
    }

    public NewShareDialog setShareTitle(String str) {
        this.title = str;
        return this;
    }

    public NewShareDialog setShareText(String str) {
        this.shareText = str;
        return this;
    }

    public NewShareDialog setShareUrl(String str) {
        this.shareUrl = str;
        return this;
    }

    public NewShareDialog setShareImgUrl(String str) {
        this.mShareImgUrl = str;
        return this;
    }

    public NewShareDialog setCallbackManager(CallbackManager callbackManager2) {
        this.callbackManager = callbackManager2;
        return this;
    }

    public NewShareDialog setClickRunnable(Runnable runnable) {
        this.clickRunnable = runnable;
        return this;
    }

    private void setPhotoTopView() {
        if (this.from.equals(FROM_GALLERY)) {
            this.shareStyle = 1;
            ImageLoader.showImage(this.shareImageImg, this.mShareImgUrl);
            ImageLoader.showImage(this.shareLinkImg, this.mShareImgUrl);
            if (!TextUtils.isEmpty(this.title)) {
                this.shareLinkDes.setText(this.title);
            }
            this.shareToTitle.setVisibility(8);
            this.newShareTopLayout.setVisibility(0);
        } else {
            this.shareStyle = 2;
            this.shareToTitle.setVisibility(0);
            this.newShareTopLayout.setVisibility(8);
        }
        this.newShareImageLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NewShareDialog.this.newShareImageLayout.setBackgroundColor(NewShareDialog.this.context.getResources().getColor(R.color.press_bg_gray));
                NewShareDialog.this.newShareLinkLayout.setBackgroundColor(NewShareDialog.this.context.getResources().getColor(R.color.white));
                NewShareDialog.this.shareLinkSelectImg.setBackground(NewShareDialog.this.context.getResources().getDrawable(R.drawable.new_share_style_unselect));
                NewShareDialog.this.shareImgSelectImg.setBackground(NewShareDialog.this.context.getResources().getDrawable(R.drawable.new_share_style_select));
                int unused = NewShareDialog.this.shareStyle = 1;
            }
        });
        this.newShareLinkLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NewShareDialog.this.newShareLinkLayout.setBackgroundColor(NewShareDialog.this.context.getResources().getColor(R.color.press_bg_gray));
                NewShareDialog.this.newShareImageLayout.setBackgroundColor(NewShareDialog.this.context.getResources().getColor(R.color.white));
                NewShareDialog.this.shareLinkSelectImg.setBackground(NewShareDialog.this.context.getResources().getDrawable(R.drawable.new_share_style_select));
                NewShareDialog.this.shareImgSelectImg.setBackground(NewShareDialog.this.context.getResources().getDrawable(R.drawable.new_share_style_unselect));
                int unused = NewShareDialog.this.shareStyle = 2;
            }
        });
    }

    public void onClick(View view) {
        dismiss();
        switch (view.getId()) {
            case com.xiaomi.smarthome.R.string.action_update_phone /*2131493169*/:
                StringBuilder sb = new StringBuilder();
                if (!TextUtils.isEmpty(this.title)) {
                    sb.append(this.title);
                }
                sb.append("  ");
                sb.append(this.shareUrl.trim());
                ((ClipboardManager) this.context.getSystemService(ShareChannel.d)).setText(sb.toString());
                Toast.makeText(this.context, this.context.getText(R.string.copy_success), 0).show();
                return;
            case com.xiaomi.smarthome.R.string.add_scene /*2131493218*/:
                sendEmail();
                return;
            case com.xiaomi.smarthome.R.string.cardless_emi_proceed /*2131493886*/:
                WebActivity.jump(this.context, ConnectionHelper.getAppIndexUrl() + "/id/thread/report?rid=6144984&tid=1019241&fid=168??rid=6144984&tid=1019241&fid=168&mobile=2");
                dismiss();
                return;
            case com.xiaomi.smarthome.R.string.cart_insurance_cancel /*2131493908*/:
                if (TextUtils.isEmpty(this.exif)) {
                    saveImg();
                    return;
                } else {
                    saveImgWithExif();
                    return;
                }
            case com.xiaomi.smarthome.R.string.cb_register_for_pin /*2131494006*/:
                PermissionUtil.a((Activity) (BaseActivity) this.context, (PermissionCallback) new SamplePermissionCallback() {
                    public void onResult() {
                        NewShareDialog.this.sendSms();
                    }

                    public void onDenied() {
                        PermissionUtil.a(NewShareDialog.this.context, NewShareDialog.this.context.getResources().getString(R.string.launch_permission_dialog_tip));
                    }
                }, Permission.Group.h);
                return;
            default:
                return;
        }
    }

    public void saveImg() {
        PermissionUtil.a((Activity) (BaseActivity) this.context, (PermissionCallback) new SamplePermissionCallback() {
            public void onResult() {
                Observable.just(NewShareDialog.this.mShareImgUrl).map(new Function<String, File>() {
                    public File apply(@NonNull String str) throws Exception {
                        return ImageUtil.checkExistBoforeSaveBitmap((BaseActivity) NewShareDialog.this.context, NewShareDialog.this.context.getResources().getString(R.string.mi_community) + "-" + NewShareDialog.this.mTid + "-" + NewShareDialog.this.imgPosition, (Bitmap) Glide.c(NewShareDialog.this.context).j().a(str).b().get());
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(((BaseActivity) NewShareDialog.this.context).bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<File>() {
                    public void accept(@NonNull File file) throws Exception {
                        NewShareDialog.this.context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
                        Toast.makeText(NewShareDialog.this.context, NewShareDialog.this.context.getResources().getString(R.string.saved_successfully), 0).show();
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                    }
                });
            }

            public void onDenied() {
                PermissionUtil.a(NewShareDialog.this.context, NewShareDialog.this.context.getResources().getString(R.string.launch_permission_dialog_tip));
            }
        }, Permission.Group.i);
    }

    public void saveImgWithExif() {
        PermissionUtil.a((Activity) (BaseActivity) this.context, (PermissionCallback) new SamplePermissionCallback() {
            public void onResult() {
                Observable.just(NewShareDialog.this.mShareImgUrl).map(new Function<String, File>() {
                    public File apply(@NonNull String str) throws Exception {
                        return ImageUtil.checkExistBoforeSaveBitmapAndExif((BaseActivity) NewShareDialog.this.context, NewShareDialog.this.context.getResources().getString(R.string.mi_community) + "-" + NewShareDialog.this.mTid + "-" + NewShareDialog.this.imgPosition, (Bitmap) Glide.c(NewShareDialog.this.context).j().a(str).b().get(), NewShareDialog.this.exif, NewShareDialog.this.mShareImgUrl.substring(NewShareDialog.this.mShareImgUrl.lastIndexOf("."), NewShareDialog.this.mShareImgUrl.length()));
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(((BaseActivity) NewShareDialog.this.context).bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<File>() {
                    public void accept(@NonNull File file) throws Exception {
                        NewShareDialog.this.context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
                        Toast.makeText(NewShareDialog.this.context, NewShareDialog.this.context.getResources().getString(R.string.saved_successfully), 0).show();
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                    }
                });
            }

            public void onDenied() {
                PermissionUtil.a(NewShareDialog.this.context, NewShareDialog.this.context.getResources().getString(R.string.launch_permission_dialog_tip));
            }
        }, Permission.Group.i);
    }

    private void setVisiable() {
        if (this.from.equals(FROM_THREAD)) {
            this.saveLy.setVisibility(8);
            this.copyLinkLy.setVisibility(0);
            this.smsLy.setVisibility(0);
            this.emailLy.setVisibility(0);
            this.reportLy.setVisibility(0);
        } else if (this.from.equals(FROM_GALLERY)) {
            this.saveLy.setVisibility(0);
            this.copyLinkLy.setVisibility(0);
            this.smsLy.setVisibility(8);
            this.emailLy.setVisibility(8);
            this.reportLy.setVisibility(0);
        } else if (this.from.equals(FROM_COLUMN)) {
            this.saveLy.setVisibility(8);
            this.copyLinkLy.setVisibility(0);
            this.smsLy.setVisibility(0);
            this.emailLy.setVisibility(0);
            this.reportLy.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void sendSms() {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:"));
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.shareText)) {
            sb.append(this.shareText);
        } else if (!TextUtils.isEmpty(this.title)) {
            sb.append(this.title);
        }
        sb.append(" ");
        sb.append(this.shareUrl);
        intent.putExtra("sms_body", sb.toString());
        this.context.startActivity(intent);
    }

    private void sendEmail() {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setType("message/rfc822");
        intent.setType(ShareObject.d);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.shareText)) {
            sb.append(this.shareText);
        } else if (!TextUtils.isEmpty(this.title)) {
            sb.append(this.title);
        }
        sb.append(" ");
        sb.append(this.shareUrl);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra("android.intent.extra.SUBJECT", "");
        intent.putExtra("android.intent.extra.TEXT", sb.toString());
        this.context.startActivity(intent);
    }

    private List<NewShareInfo> setShareApps(Context context2) {
        NewShareInfo newShareInfo = new NewShareInfo();
        newShareInfo.icon = R.drawable.share_facebook;
        newShareInfo.labelRes = R.string.str_facebook;
        NewShareInfo newShareInfo2 = new NewShareInfo();
        newShareInfo2.icon = R.drawable.share_twitter;
        newShareInfo2.labelRes = R.string.str_twitter;
        NewShareInfo newShareInfo3 = new NewShareInfo();
        newShareInfo3.icon = R.drawable.share_instagram;
        newShareInfo3.labelRes = R.string.str_instagram;
        NewShareInfo newShareInfo4 = new NewShareInfo();
        newShareInfo4.icon = R.drawable.share_messenger;
        newShareInfo4.labelRes = R.string.str_messager;
        NewShareInfo newShareInfo5 = new NewShareInfo();
        newShareInfo5.icon = R.drawable.share_whatsapp;
        newShareInfo5.labelRes = R.string.str_whatsapp;
        NewShareInfo newShareInfo6 = new NewShareInfo();
        newShareInfo6.icon = R.drawable.share_zalo;
        newShareInfo6.labelRes = R.string.str_zalo;
        NewShareInfo newShareInfo7 = new NewShareInfo();
        newShareInfo7.icon = R.drawable.share_line;
        newShareInfo7.labelRes = R.string.str_line;
        NewShareInfo newShareInfo8 = new NewShareInfo();
        newShareInfo8.icon = R.drawable.share_telegram;
        newShareInfo8.labelRes = R.string.str_telegram;
        NewShareInfo newShareInfo9 = new NewShareInfo();
        newShareInfo9.icon = R.drawable.share_vk;
        newShareInfo9.labelRes = R.string.str_vk;
        NewShareInfo newShareInfo10 = new NewShareInfo();
        newShareInfo10.icon = R.drawable.share_ok;
        newShareInfo10.labelRes = R.string.dialog_ask_ok;
        NewShareInfo newShareInfo11 = new NewShareInfo();
        newShareInfo11.icon = R.drawable.share_viber;
        newShareInfo11.labelRes = R.string.str_viber;
        NewShareInfo newShareInfo12 = new NewShareInfo();
        newShareInfo12.icon = R.drawable.share_more;
        newShareInfo12.labelRes = R.string.more;
        ArrayList arrayList = new ArrayList();
        if (LocaleHelper.APP_LOCALE.equals("in") || LocaleHelper.APP_LOCALE.equals("sg") || LocaleHelper.APP_LOCALE.equals(LocaleHelper.ARAB_LOCAL) || LocaleHelper.APP_LOCALE.equals(d.u) || LocaleHelper.APP_LOCALE.equals("mx") || LocaleHelper.APP_LOCALE.equals("it") || LocaleHelper.APP_LOCALE.equals("fr")) {
            arrayList.add(newShareInfo);
            arrayList.add(newShareInfo2);
            arrayList.add(newShareInfo3);
            arrayList.add(newShareInfo4);
            arrayList.add(newShareInfo5);
            arrayList.add(newShareInfo12);
        } else if (LocaleHelper.isVietnam()) {
            arrayList.add(newShareInfo);
            arrayList.add(newShareInfo4);
            arrayList.add(newShareInfo6);
            arrayList.add(newShareInfo3);
            arrayList.add(newShareInfo12);
        } else if (LocaleHelper.APP_LOCALE.equals("id") || LocaleHelper.APP_LOCALE.equals("th") || LocaleHelper.APP_LOCALE.equals("ph")) {
            arrayList.add(newShareInfo);
            arrayList.add(newShareInfo2);
            arrayList.add(newShareInfo3);
            arrayList.add(newShareInfo5);
            arrayList.add(newShareInfo7);
            arrayList.add(newShareInfo8);
            arrayList.add(newShareInfo12);
        } else if (LocaleHelper.isRu()) {
            arrayList.add(newShareInfo9);
            arrayList.add(newShareInfo10);
            arrayList.add(newShareInfo5);
            arrayList.add(newShareInfo3);
            arrayList.add(newShareInfo12);
        } else if (LocaleHelper.isUa()) {
            arrayList.add(newShareInfo);
            arrayList.add(newShareInfo3);
            arrayList.add(newShareInfo11);
            arrayList.add(newShareInfo8);
            arrayList.add(newShareInfo12);
        } else {
            arrayList.add(newShareInfo);
            arrayList.add(newShareInfo2);
            arrayList.add(newShareInfo3);
            arrayList.add(newShareInfo12);
        }
        return arrayList;
    }

    public void onItemClick(int i) {
        NewShareInfo newShareInfo = this.shareAppList.get(i);
        if (newShareInfo.labelRes == R.string.more) {
            ShareDialog shareDialog = new ShareDialog(this.context);
            if (this.shareStyle == 1) {
                shareDialog.setShareTitle(this.title).setShareStyle(1).setShareUrl(this.shareUrl).setShareImgUrl(this.mShareImgUrl).setCallbackManager(this.callbackManager).show();
            } else if (!this.from.equals(FROM_COLUMN)) {
                shareDialog.setShareTitle(this.title).setShareUrl(this.shareUrl).setCallbackManager(this.callbackManager).show();
            } else {
                shareDialog.setShareTitle(this.title).setShareUrl(this.shareUrl).setShareText(this.shareText).setCallbackManager(this.callbackManager).show();
            }
        } else if (newShareInfo.labelRes == R.string.str_facebook) {
            this.shareUrl += "?utm_source=share&utm_medium=facebook";
            if (this.shareStyle == 1) {
                ShareHelper.geShareImage(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), this.mShareImgUrl, "com.facebook.katana");
            } else {
                ShareHelper.shareToFacebook((BaseActivity) this.context, this.title, this.shareUrl, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), this.mShareImgUrl, this.callbackManager);
            }
        } else if (newShareInfo.labelRes == R.string.str_twitter) {
            this.shareUrl += "?utm_source=share&utm_medium=twitter";
            if (this.shareStyle == 1) {
                ShareHelper.geShareImage(this.context, (BaseActivity) this.context, getShareDesTextTW(this.context.getResources().getString(newShareInfo.labelRes)), this.mShareImgUrl, ShareDialog.TWITTER_PACKAGE_NAME);
            } else {
                ShareHelper.goShareTW(this.context, (BaseActivity) this.context, getShareDesTextTW(this.context.getResources().getString(newShareInfo.labelRes)), this.shareUrl);
            }
        } else if (newShareInfo.labelRes == R.string.str_instagram) {
            if (this.shareStyle == 1) {
                ShareHelper.geShareImage(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), this.mShareImgUrl, INSTAGRAM_PACKAGE_NAME);
            } else {
                ShareHelper.geShareNormal(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), INSTAGRAM_PACKAGE_NAME);
            }
        } else if (newShareInfo.labelRes == R.string.str_messager) {
            if (this.shareStyle == 1) {
                ShareHelper.geShareImage(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), this.mShareImgUrl, "com.facebook.orca");
            } else {
                ShareHelper.geShareNormal(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), "com.facebook.orca");
            }
        } else if (newShareInfo.labelRes == R.string.str_whatsapp) {
            if (this.shareStyle == 1) {
                ShareHelper.geShareImage(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), this.mShareImgUrl, WHATSAPP_PACKAGE_NAME);
            } else {
                ShareHelper.geShareNormal(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), WHATSAPP_PACKAGE_NAME);
            }
        } else if (newShareInfo.labelRes == R.string.str_zalo) {
            if (this.shareStyle == 1) {
                ShareHelper.geShareImage(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), this.mShareImgUrl, ZALO_PACKAGE_NAME);
            } else {
                ShareHelper.geShareNormal(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), ZALO_PACKAGE_NAME);
            }
        } else if (newShareInfo.labelRes == R.string.str_line) {
            if (this.shareStyle == 1) {
                ShareHelper.geShareImage(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), this.mShareImgUrl, LINE_PACKAGE_NAME);
            } else {
                ShareHelper.geShareNormal(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), LINE_PACKAGE_NAME);
            }
        } else if (newShareInfo.labelRes == R.string.str_telegram) {
            if (this.shareStyle == 1) {
                ShareHelper.geShareImage(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), this.mShareImgUrl, TELEGRAM_PACKAGE_NAME);
            } else {
                ShareHelper.geShareNormal(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), TELEGRAM_PACKAGE_NAME);
            }
        } else if (newShareInfo.labelRes == R.string.str_vk) {
            if (this.shareStyle == 1) {
                ShareHelper.geShareImage(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), this.mShareImgUrl, VK_PACKAGE_NAME);
            } else {
                ShareHelper.geShareNormal(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), VK_PACKAGE_NAME);
            }
        } else if (newShareInfo.labelRes == R.string.dialog_ask_ok) {
            if (this.shareStyle == 1) {
                ShareHelper.geShareImage(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), this.mShareImgUrl, OK_PACKAGE_NAME);
            } else {
                ShareHelper.geShareNormal(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), OK_PACKAGE_NAME);
            }
        } else if (newShareInfo.labelRes == R.string.str_viber) {
            if (this.shareStyle == 1) {
                ShareHelper.geShareImage(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), this.mShareImgUrl, VIBER_PACKAGE_NAME);
            } else {
                ShareHelper.geShareNormal(this.context, (BaseActivity) this.context, getShareDesText(this.context.getResources().getString(newShareInfo.labelRes)), VIBER_PACKAGE_NAME);
            }
        }
        dismiss();
        if (this.clickRunnable != null) {
            this.clickRunnable.run();
        }
    }

    private String getShareDesText(String str) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.shareText)) {
            sb.append(this.shareText);
        } else if (!TextUtils.isEmpty(this.title)) {
            sb.append(this.title);
        }
        sb.append(" ");
        sb.append(this.shareUrl);
        sb.append("?utm_source=share&utm_medium=");
        sb.append(str);
        return sb.toString();
    }

    private String getShareDesTextTW(String str) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.shareText)) {
            sb.append(this.shareText);
        } else if (!TextUtils.isEmpty(this.title)) {
            sb.append(this.title);
        }
        sb.append(" ");
        return sb.toString();
    }
}
