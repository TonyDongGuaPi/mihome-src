package com.mi.global.bbs.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.utils.Utils;
import com.mi.log.LogUtil;
import com.mi.mistatistic.sdk.MiStatInterface;
import com.mi.util.ResourceUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.xiaomi.youpin.share.ShareObject;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;

public class ShareHelper {
    /* access modifiers changed from: private */
    public static String TAG = "ShareHelper";

    private static void recordEvent(String str, String str2) {
        if (!LocaleHelper.isEruope()) {
            MiStatInterface.a("share", "NewShareDialog", str2, Constants.getPageTypeByURL(str));
        }
    }

    public static void shareToFacebook(Activity activity, String str, String str2, CallbackManager callbackManager) {
        shareToFacebook(activity, str, str2, (String) null, (String) null, callbackManager);
    }

    public static void shareToFacebook(Activity activity, String str, String str2, String str3, CallbackManager callbackManager) {
        shareToFacebook(activity, str, str2, str3, (String) null, callbackManager);
    }

    public static void shareToFacebook(Activity activity, String str, String str2, String str3, String str4, CallbackManager callbackManager) {
        recordEvent(str2, "share_FB");
        FacebookSdk.setApplicationId(ResourceUtil.a("bbs_facebook_app_id"));
        String str5 = TAG;
        LogUtil.b(str5, "shareToFacebook " + FacebookSdk.getApplicationId());
        if (TextUtils.isEmpty(str3)) {
            str3 = Constants.ShareContent.SHARE_DES;
        }
        if (TextUtils.isEmpty(str4)) {
            str4 = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_SHARE_IMG, Constants.ShareContent.SHARE_IMG_URL);
        }
        ShareLinkContent build = ((ShareLinkContent.Builder) new ShareLinkContent.Builder().setContentTitle(str).setContentDescription(str3).setContentUrl(Uri.parse(str2))).setImageUrl(Uri.parse(str4)).build();
        ShareDialog shareDialog = new ShareDialog(activity);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            public void onSuccess(Sharer.Result result) {
                LogUtil.b(ShareHelper.TAG, "onSuccess");
            }

            public void onCancel() {
                LogUtil.b(ShareHelper.TAG, "onCancel");
            }

            public void onError(FacebookException facebookException) {
                LogUtil.b(ShareHelper.TAG, "onError");
                facebookException.printStackTrace();
            }
        });
        try {
            LogUtil.b(TAG, "goShareFB NewShareDialog    ");
            if (ShareDialog.canShow(ShareLinkContent.class)) {
                shareDialog.show(build);
                LogUtil.b(TAG, "goShareFB NewShareDialog.canShow    ");
                return;
            }
            LogUtil.b(TAG, "goShareFB NewShareDialog. url ");
            String facebook = ConnectionHelper.getFacebook();
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(facebook));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(activity, activity.getResources().getString(R.string.install_app), 0).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goShareTW(Context context, BaseActivity baseActivity, String str, String str2) {
        if (context != null && baseActivity != null) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format("https://twitter.com/intent/tweet?text=%s&url=%s", new Object[]{ConnectionHelper.urlEncode(str), ConnectionHelper.urlEncode(str2)})));
            for (ResolveInfo next : context.getPackageManager().queryIntentActivities(intent, 0)) {
                if (next.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                    intent.setPackage(next.activityInfo.packageName);
                }
            }
            try {
                context.startActivity(Intent.createChooser(intent, "share"));
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(context, context.getResources().getString(R.string.install_app), 0).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void geShareNormal(Context context, BaseActivity baseActivity, String str, String str2) {
        if (context != null && baseActivity != null) {
            Intent intent = new Intent("android.intent.action.SEND");
            for (ResolveInfo next : com.mi.global.bbs.view.dialog.ShareDialog.getShareApps(context)) {
                if (next.activityInfo.packageName.equals(str2)) {
                    intent.setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
                }
            }
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TEXT", str);
            try {
                context.startActivity(Intent.createChooser(intent, "share"));
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(context, context.getResources().getString(R.string.install_app), 0).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void geShareImage(final Context context, BaseActivity baseActivity, final String str, final String str2, final String str3) {
        if (context != null && baseActivity != null) {
            try {
                ((BaseActivity) context).showProDialog("");
                PermissionClaimer.requestPermissionsWithReasonDialog((BaseActivity) context, true, PermissionClaimer.getRequestPermissionReasons(context, R.string.str_permission_access_file), new PermissionClaimer.DefaultPermissionReqListener() {
                    @SuppressLint({"CheckResult"})
                    public void onGranted() {
                        super.onGranted();
                        Observable.just(str2).map(new Function<String, File>() {
                            public File apply(@NonNull String str) throws Exception {
                                return new File(ImageUtil.saveCacheBitmap70(context.getResources().getString(R.string.mi_community) + "_share_cache", (Bitmap) Glide.c(context).j().a(str).b().get()));
                            }
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(((BaseActivity) context).bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<File>() {
                            public void accept(@NonNull File file) throws Exception {
                                ((BaseActivity) context).dismissProDialog();
                                Uri fromFile = Uri.fromFile(file);
                                Intent intent = new Intent("android.intent.action.SEND");
                                for (ResolveInfo next : com.mi.global.bbs.view.dialog.ShareDialog.getShareImgApps(context)) {
                                    if (next.activityInfo.packageName.equals(str3)) {
                                        if (str3.equals(com.mi.global.bbs.view.dialog.ShareDialog.TWITTER_PACKAGE_NAME)) {
                                            if (next.activityInfo.name.equals("com.twitter.composer.SelfThreadComposerActivity")) {
                                                intent.setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
                                            }
                                        } else if (!str3.equals("com.facebook.katana")) {
                                            intent.setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
                                        } else if (next.activityInfo.name.equals("com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias")) {
                                            intent.setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
                                        }
                                    }
                                }
                                intent.setType(ShareObject.d);
                                intent.putExtra("android.intent.extra.STREAM", fromFile);
                                intent.putExtra("android.intent.extra.TEXT", str);
                                try {
                                    context.startActivity(Intent.createChooser(intent, "share"));
                                } catch (ActivityNotFoundException unused) {
                                    Toast.makeText(context, context.getResources().getString(R.string.install_app), 0).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Consumer<Throwable>() {
                            public void accept(@NonNull Throwable th) throws Exception {
                                ((BaseActivity) context).dismissProDialog();
                            }
                        });
                    }
                }, "android.permission.WRITE_EXTERNAL_STORAGE");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
