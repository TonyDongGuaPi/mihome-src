package com.mi.global.bbs.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.CallbackManager;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.OnSheetDismissedListener;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.ShareAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.utils.ShareHelper;
import com.xiaomi.youpin.share.ShareObject;
import java.util.List;

public class ShareDialog extends Dialog implements ShareAdapter.onItemClickListener {
    public static final String FACEBOOK_PACKAGE_NAME = "com.facebook.katana";
    public static final String TWITTER_PACKAGE_NAME = "com.twitter.android";
    BottomSheetLayout bottomSheetLayout;
    private CallbackManager callbackManager;
    private Runnable clickRunnable;
    private Context context;
    private List<ResolveInfo> shareAppList;
    private String shareImgUrl;
    @BindView(2131493966)
    LinearLayout shareLayout;
    @BindView(2131493970)
    RecyclerView shareListView;
    private int shareStyle = 2;
    private String shareText;
    private String shareUrl;
    private String title;

    public ShareDialog(Context context2) {
        super(context2, R.style.ShareDialog);
        this.context = context2;
        setContentView(R.layout.bottom_sheet_layout);
        this.bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottom_root);
        View inflate = LayoutInflater.from(context2).inflate(R.layout.share_dialog_layout, this.bottomSheetLayout, false);
        ButterKnife.bind((Object) this, inflate);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = getWindow();
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -1;
        window.setAttributes(layoutParams);
        this.shareAppList = getShareImgApps(context2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context2, 3);
        gridLayoutManager.setOrientation(1);
        this.shareListView.setLayoutManager(gridLayoutManager);
        ShareAdapter shareAdapter = new ShareAdapter(context2, this.shareAppList);
        shareAdapter.setOnItemClickListener(this);
        this.shareListView.setAdapter(shareAdapter);
        this.bottomSheetLayout.setPeekSheetTranslation((float) context2.getResources().getDimensionPixelOffset(R.dimen.share_dialog_default_height));
        this.bottomSheetLayout.showWithSheetView(inflate);
        this.bottomSheetLayout.peekSheet();
        this.bottomSheetLayout.addOnSheetDismissedListener(new OnSheetDismissedListener() {
            public void onDismissed(BottomSheetLayout bottomSheetLayout) {
                ShareDialog.this.dismiss();
            }
        });
    }

    public static List<ResolveInfo> getShareApps(Context context2) {
        Intent intent = new Intent("android.intent.action.SEND", (Uri) null);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("text/plain");
        return context2.getPackageManager().queryIntentActivities(intent, 0);
    }

    public static List<ResolveInfo> getShareImgApps(Context context2) {
        Intent intent = new Intent("android.intent.action.SEND", (Uri) null);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType(ShareObject.d);
        return context2.getPackageManager().queryIntentActivities(intent, 0);
    }

    public ShareDialog setShareTitle(String str) {
        this.title = str;
        return this;
    }

    public ShareDialog setShareStyle(int i) {
        this.shareStyle = i;
        return this;
    }

    public ShareDialog setShareText(String str) {
        this.shareText = str;
        return this;
    }

    public ShareDialog setShareImgUrl(String str) {
        this.shareImgUrl = str;
        return this;
    }

    public ShareDialog setShareUrl(String str) {
        this.shareUrl = str;
        return this;
    }

    public ShareDialog setCallbackManager(CallbackManager callbackManager2) {
        this.callbackManager = callbackManager2;
        return this;
    }

    public void onItemClick(int i) {
        ResolveInfo resolveInfo = this.shareAppList.get(i);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
        String shareUrlModify = shareUrlModify(resolveInfo.activityInfo.packageName);
        if (resolveInfo.activityInfo.packageName.equals("com.facebook.katana")) {
            if (this.shareStyle == 1) {
                ShareHelper.geShareImage(this.context, (BaseActivity) this.context, this.title, this.shareImgUrl, resolveInfo.activityInfo.packageName);
            } else {
                ShareHelper.shareToFacebook((Activity) this.context, this.title, shareUrlModify, this.shareText, this.shareImgUrl, this.callbackManager);
            }
        } else if (this.shareStyle == 1) {
            ShareHelper.geShareImage(this.context, (BaseActivity) this.context, this.title, this.shareImgUrl, resolveInfo.activityInfo.packageName);
        } else {
            intent.setType("text/plain");
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(this.shareText)) {
                sb.append(this.shareText);
            } else if (!TextUtils.isEmpty(this.title)) {
                sb.append(this.title);
            }
            sb.append(" ");
            sb.append(shareUrlModify);
            intent.putExtra("android.intent.extra.TEXT", sb.toString());
            this.context.startActivity(Intent.createChooser(intent, "share"));
        }
        dismiss();
        if (this.clickRunnable != null) {
            this.clickRunnable.run();
        }
    }

    public ShareDialog setClickRunnable(Runnable runnable) {
        this.clickRunnable = runnable;
        return this;
    }

    private String shareUrlModify(String str) {
        if (str.contains("com.facebook.katana")) {
            return this.shareUrl + "?utm_source=share&utm_medium=facebook";
        } else if (str.contains(TWITTER_PACKAGE_NAME)) {
            return this.shareUrl + "?utm_source=share&utm_medium=twitter";
        } else if (str.contains(NewShareDialog.INSTAGRAM_PACKAGE_NAME)) {
            return this.shareUrl + "?utm_source=share&utm_medium=instagram";
        } else if (str.contains("com.facebook.orca")) {
            return this.shareUrl + "?utm_source=share&utm_medium=messager";
        } else if (str.contains(NewShareDialog.WHATSAPP_PACKAGE_NAME)) {
            return this.shareUrl + "?utm_source=share&utm_medium=whatsapp";
        } else if (str.contains(NewShareDialog.ZALO_PACKAGE_NAME)) {
            return this.shareUrl + "?utm_source=share&utm_medium=zalo";
        } else if (str.contains(NewShareDialog.LINE_PACKAGE_NAME)) {
            return this.shareUrl + "?utm_source=share&utm_medium=line";
        } else if (str.contains(NewShareDialog.TELEGRAM_PACKAGE_NAME)) {
            return this.shareUrl + "?utm_source=share&utm_medium=telegram";
        } else if (str.contains(NewShareDialog.VK_PACKAGE_NAME)) {
            return this.shareUrl + "?utm_source=share&utm_medium=vk";
        } else if (str.contains(NewShareDialog.OK_PACKAGE_NAME)) {
            return this.shareUrl + "?utm_source=share&utm_medium=ok";
        } else if (str.contains(NewShareDialog.VIBER_PACKAGE_NAME)) {
            return this.shareUrl + "?utm_source=share&utm_medium=viber";
        } else {
            return this.shareUrl + "?utm_source=share";
        }
    }
}
