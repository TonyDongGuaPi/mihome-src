package com.xiaomi.mishopsdk.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.mishopsdk.volley.Response;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.fragment.BasePluginFragment;
import com.xiaomi.mishopsdk.io.http.HostManager;
import com.xiaomi.mishopsdk.io.http.RequestConstants;
import com.xiaomi.mishopsdk.io.http.RequestQueueManager;
import com.xiaomi.mishopsdk.io.http.ShopJSONRequest;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.DensityUtil;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.util.PicUtil;
import com.xiaomi.mishopsdk.util.PreferenceUtil;
import com.xiaomi.mobilestats.StatService;
import com.xiaomi.shop2.util.DeviceUtil;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class HomepageFloadAd {
    private static final String PREF_KEY_AD_INFO_ID = "pref_key_ad_info_id";
    private static final String TAG = "HomepageFloadAd";

    public static class AdInfo {
        public int heightInDp;
        public String id;
        public String jump_info;
        public String url;
        public int widthInDp;
    }

    public static void requestAndShowAd(final Activity activity) {
        ShopJSONRequest.Builder<?> builder = ShopJSONRequest.builder();
        RequestQueueManager.getInstance().addRequest(((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) builder.setUrl(HostManager.FORMAL_DOMAIN_APP_SHOPAPI + "app/sdk_ad")).addHeader("App-Others", DeviceUtil.getDToken())).setClass(AdInfo.class).setShouldCache(false)).setListner(new Response.SimpleListener<AdInfo>() {
            public void onSuccess(AdInfo adInfo, boolean z) {
                if (adInfo != null && !TextUtils.isEmpty(adInfo.url) && !TextUtils.isEmpty(adInfo.id) && !TextUtils.equals(adInfo.id, PreferenceUtil.getStringPref(ShopApp.instance, HomepageFloadAd.PREF_KEY_AD_INFO_ID, ""))) {
                    HomepageFloadAd.downloadImgAndShowAd(adInfo, activity);
                }
            }
        })).build());
    }

    /* access modifiers changed from: private */
    public static void downloadImgAndShowAd(final AdInfo adInfo, final Activity activity) {
        AndroidUtil.sStageQueue.postRunnable(new Runnable() {
            public void run() {
                final Bitmap bitmap;
                try {
                    if (adInfo.widthInDp > 0) {
                        if (adInfo.heightInDp > 0) {
                            bitmap = PicUtil.getInstance().load(adInfo.url).resize(DensityUtil.dip2px((float) adInfo.widthInDp), DensityUtil.dip2px((float) adInfo.heightInDp)).get();
                            AndroidUtil.runOnUIThread(new Runnable() {
                                public void run() {
                                    HomepageFloadAd.displayAdImage(activity, bitmap, adInfo.jump_info, adInfo.id);
                                }
                            });
                        }
                    }
                    bitmap = PicUtil.getInstance().load(adInfo.url).get();
                    AndroidUtil.runOnUIThread(new Runnable() {
                        public void run() {
                            HomepageFloadAd.displayAdImage(activity, bitmap, adInfo.jump_info, adInfo.id);
                        }
                    });
                } catch (IOException e) {
                    Log.e(HomepageFloadAd.TAG, "download image failed.", (Object) e);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void displayAdImage(Activity activity, Bitmap bitmap, String str, String str2) {
        if (bitmap != null && activity != null && !activity.isFinishing()) {
            createAdDialog(activity, bitmap, str, str2).show();
            PreferenceUtil.setStringPref(ShopApp.instance, PREF_KEY_AD_INFO_ID, str2);
            try {
                String string = new JSONObject(str).getString(Constants.Plugin.ARGUMENT_LOGCODE);
                if (!TextUtils.isEmpty(string)) {
                    StatService.onPostEvent(ShopApp.instance, string, str2, new HashMap());
                }
            } catch (JSONException unused) {
            }
        }
    }

    private static Dialog createAdDialog(final Activity activity, Bitmap bitmap, final String str, final String str2) {
        ImageView imageView = new ImageView(activity);
        imageView.setAdjustViewBounds(true);
        imageView.setImageBitmap(bitmap);
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(true);
        dialog.setContentView(imageView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomepageFloadAd.jumpToDstActivity(activity, str, str2);
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        return dialog;
    }

    /* access modifiers changed from: private */
    public static void jumpToDstActivity(Activity activity, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle = new Bundle();
                String nextCid = ChannelUtils.getNextCid(RequestConstants.Keys.ChANNEL_ID, ChannelUtils.getAddCartPath("m0", jSONObject));
                String string = jSONObject.getString(Constants.Plugin.ARGUMENT_LOGCODE);
                bundle.putString(Constants.Plugin.ARGUMENT_PLUGINPREVIOUS, ChannelUtils.getNextCid(nextCid, jSONObject.getString(Constants.Plugin.ARGUMENT_LOGCODE)));
                BasePluginFragment.startNewPlugin(activity, jSONObject, bundle);
                if (!TextUtils.isEmpty(string)) {
                    StatService.onEvent(activity, string, str2);
                }
            } catch (JSONException e) {
                Log.e(TAG, "start ad plugin failed, jumpInfo=%s", str, e);
            }
        }
    }
}
