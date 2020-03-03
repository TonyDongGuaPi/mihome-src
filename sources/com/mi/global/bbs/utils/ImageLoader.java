package com.mi.global.bbs.utils;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.HeadLogoView;
import com.mi.util.ScreenInfo;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class ImageLoader {
    private static final int CROSS_FADE_TIME = 500;
    private static HashMap<String, String> groupLogoMap = new HashMap<>();

    static {
        initLogoMap();
    }

    private ImageLoader() {
    }

    public static void initLogoMap() {
        try {
            JSONArray jSONArray = new JSONArray(Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_GROUP_ICON, ""));
            groupLogoMap.clear();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                groupLogoMap.put(optJSONObject.optString("groupid"), optJSONObject.optString("icon"));
            }
        } catch (Exception unused) {
        }
    }

    public static void showImage(ImageView imageView, String str) {
        Glide.a((View) imageView).a(str).b((BaseRequestOptions<?>) getDefaultOptions()).a(imageView);
    }

    public static void showImage(ImageView imageView, String str, RequestOptions requestOptions) {
        if (requestOptions != null) {
            Glide.a((View) imageView).a(str).b((BaseRequestOptions<?>) requestOptions).a(imageView);
            return;
        }
        throw new NullPointerException("options is null");
    }

    public static void showHeadIcon(ImageView imageView, String str) {
        Glide.a((View) imageView).a(str).b((BaseRequestOptions<?>) getUserOptions()).a(imageView);
    }

    public static void showListItemImage(ImageView imageView, String str) {
        Glide.a((View) imageView).a(str).a(new DrawableTransitionOptions().c(500)).b((BaseRequestOptions<?>) getFadeAnimationOptions()).a(imageView);
    }

    public static void showCircleHeadIcon(ImageView imageView, String str) {
        Glide.a((View) imageView).a(str).b((BaseRequestOptions<?>) getUserHeadOptions()).a(imageView);
    }

    public static void showCircleHeadLogoIcon(ImageView imageView, String str) {
        Glide.a((View) imageView).a(str).b((BaseRequestOptions<?>) (RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().k()).q()).a(R.drawable.bbs_sub_forum_icon)).c(R.drawable.bbs_sub_forum_icon)).b(R.drawable.bbs_sub_forum_icon)).a(imageView);
    }

    public static void showHeadLogo(HeadLogoView headLogoView, String str, int i, String str2) {
        if (!(headLogoView instanceof HeadLogoView)) {
            return;
        }
        if (i != 1 || TextUtils.isEmpty(str2) || groupLogoMap == null || !groupLogoMap.containsKey(str2)) {
            headLogoView.setUserHead(str);
        } else {
            headLogoView.setUserHead(str, groupLogoMap.get(str2));
        }
    }

    public static void showFixXYBigPic(ImageView imageView, String str, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        int b = ScreenInfo.a().b();
        if (i2 <= 0) {
            if (layoutParams.height <= b) {
                b = layoutParams.height;
            }
            layoutParams.height = b;
        } else if (i != 0) {
            int i3 = (i2 * b) / i;
            if (i3 > b) {
                i3 = b;
            }
            layoutParams.height = i3;
        } else {
            if (i2 > b) {
                i2 = b;
            }
            layoutParams.height = i2;
        }
        imageView.setLayoutParams(layoutParams);
        Glide.a((View) imageView).a(str).b((BaseRequestOptions<?>) (RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().k()).a(R.drawable.sub_forum_icon)).c(R.drawable.sub_forum_icon)).b(R.drawable.sub_forum_icon)).a(imageView);
    }

    public static RequestOptions getDefaultOptions() {
        return (RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().a(R.drawable.bbs_sub_forum_icon)).c(R.drawable.bbs_sub_forum_icon)).b(R.drawable.bbs_sub_forum_icon);
    }

    private static RequestOptions getUserOptions() {
        return (RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().k()).a(R.drawable.bbs_sub_forum_icon)).c(R.drawable.bbs_user_head_icon)).b(R.drawable.bbs_user_head_icon);
    }

    private static RequestOptions getUserHeadOptions() {
        return (RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().k()).q()).a(R.drawable.bbs_icon_default_head)).c(R.drawable.bbs_icon_default_head)).b(R.drawable.bbs_icon_default_head);
    }

    private static RequestOptions getCenterOptions() {
        return (RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().a(R.drawable.bbs_sub_forum_icon)).c(R.drawable.bbs_sub_forum_icon)).b(R.drawable.bbs_sub_forum_icon);
    }

    private static RequestOptions getFadeAnimationOptions() {
        return (RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().k()).a(R.drawable.bbs_sub_forum_icon)).c(R.drawable.bbs_sub_forum_icon)).b(R.drawable.bbs_sub_forum_icon);
    }

    public static void showCenterAdjustBoundImage(ImageView imageView, String str) {
        Glide.a((View) imageView).a(str).b((BaseRequestOptions<?>) getCenterOptions()).a(new DrawableTransitionOptions().c(500)).a(imageView);
    }
}
