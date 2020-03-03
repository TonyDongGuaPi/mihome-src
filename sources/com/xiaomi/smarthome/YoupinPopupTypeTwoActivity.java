package com.xiaomi.smarthome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sina.weibo.sdk.statistic.LogBuilder;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.util.DateUtils;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.Date;

public class YoupinPopupTypeTwoActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13537a = "https://home.mi.com/shop/promotion";
    private static final String b = "YoupinPopupTypeTwoActivity";
    PopupInfo mInfo;
    @BindView(2131432056)
    RelativeLayout mRootView;
    @BindView(2131434067)
    SimpleDraweeView mYoupinSdv;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_youpin_type_two_layout);
        ButterKnife.bind((Activity) this);
        a();
        StatHelper.aJ();
    }

    private void a() {
        Intent intent = getIntent();
        if (intent == null) {
            b();
        }
        this.mInfo = (PopupInfo) intent.getParcelableExtra("info");
        if (this.mInfo == null) {
            b();
        }
        this.mYoupinSdv.setHierarchy(new GenericDraweeHierarchyBuilder(getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        if (this.mInfo.h.size() > 0 && !TextUtils.isEmpty(this.mInfo.h.get(0))) {
            this.mYoupinSdv.setImageURI(Uri.parse(this.mInfo.h.get(0)));
        }
        this.mYoupinSdv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UrlDispatchManger.a().c(YoupinPopupTypeTwoActivity.this.mInfo.c);
                Context appContext = SHApplication.getAppContext();
                PreferenceUtils.a(appContext, YoupinPopupTypeTwoActivity.this.mInfo.g + "yp_popup_type_two_has_shown", new Date().getTime());
                YoupinPopupTypeTwoActivity.this.b();
                StatHelper.aL();
            }
        });
        this.mRootView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context appContext = SHApplication.getAppContext();
                PreferenceUtils.a(appContext, YoupinPopupTypeTwoActivity.this.mInfo.g + "yp_popup_type_two_has_shown", new Date().getTime());
                YoupinPopupTypeTwoActivity.this.b();
                StatHelper.aK();
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        finish();
        overridePendingTransition(0, 0);
    }

    public void onBackPressed() {
        Context appContext = SHApplication.getAppContext();
        PreferenceUtils.a(appContext, this.mInfo.g + "yp_popup_type_two_has_shown", new Date().getTime());
        b();
        StatHelper.aM();
    }

    public static class PopupInfo implements Parcelable {
        public static final Parcelable.Creator<PopupInfo> CREATOR = new Parcelable.Creator<PopupInfo>() {
            /* renamed from: a */
            public PopupInfo createFromParcel(Parcel parcel) {
                return new PopupInfo(parcel);
            }

            /* renamed from: a */
            public PopupInfo[] newArray(int i) {
                return new PopupInfo[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public long f13540a;
        public long b;
        public String c = "";
        public int d;
        public int e;
        public int f;
        public String g = "";
        public ArrayList<String> h = new ArrayList<>();

        public int describeContents() {
            return 0;
        }

        public void a(JsonObject jsonObject) {
            if (jsonObject != null) {
                this.f13540a = jsonObject.getAsJsonPrimitive(LogBuilder.i).getAsLong();
                this.b = jsonObject.getAsJsonPrimitive(LogBuilder.j).getAsLong();
                this.c = jsonObject.getAsJsonPrimitive("url").getAsString();
                this.d = jsonObject.getAsJsonPrimitive("showtime").getAsInt();
                this.e = jsonObject.getAsJsonPrimitive("imageWidth").getAsInt();
                this.f = jsonObject.getAsJsonPrimitive("imageHeight").getAsInt();
                JsonArray asJsonArray = jsonObject.getAsJsonArray("images");
                if (asJsonArray != null && asJsonArray.size() > 0) {
                    this.h.clear();
                    for (int i = 0; i < asJsonArray.size(); i++) {
                        this.h.add(asJsonArray.get(i).getAsString());
                    }
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.f13540a);
            parcel.writeLong(this.b);
            parcel.writeString(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeString(this.g);
            parcel.writeStringList(this.h);
        }

        public PopupInfo() {
        }

        protected PopupInfo(Parcel parcel) {
            this.f13540a = parcel.readLong();
            this.b = parcel.readLong();
            this.c = parcel.readString();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readString();
            this.h = parcel.createStringArrayList();
        }

        public String toString() {
            return "PopupInfo{mStartTime=" + this.f13540a + ", mEndTime=" + this.b + ", mLocationUrl='" + this.c + Operators.SINGLE_QUOTE + ", mShowTime=" + this.d + ", mImageWidth=" + this.e + ", mImageHeight=" + this.f + ", metags=" + this.g + ", mImages=" + this.h + Operators.BLOCK_END;
        }
    }

    public static boolean checkCanShow(PopupInfo popupInfo) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (currentTimeMillis > popupInfo.b || currentTimeMillis < popupInfo.f13540a) {
            return false;
        }
        Context appContext = SHApplication.getAppContext();
        long b2 = PreferenceUtils.b(appContext, popupInfo.g + "yp_popup_type_two_has_shown", 0);
        LogUtil.a(b, "shareTime: " + b2);
        if (DateUtils.a(new Date(b2), new Date(System.currentTimeMillis()))) {
            return false;
        }
        return true;
    }
}
