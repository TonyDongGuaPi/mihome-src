package com.xiaomi.smarthome.library.common.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.exoplayer2.C;
import com.mi.global.shop.BuildConfig;
import com.xiaomi.smarthome.OpenExternalBrowserCompat;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.device.utils.LockedDeviceAdapter;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.login.util.LoginUtil;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.navigate.UrlResolver;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.activity.ClientAllLockedActivity;
import com.xiaomi.smarthome.miio.ir.IRHeaderTabs;
import com.xiaomi.smarthome.miio.lockscreen.ClientAllLockedDialog;
import java.util.Map;

public class AnimateFakeList extends AnimateLinearLayout {
    static String TAG = "AnimateFakeList";

    /* renamed from: a  reason: collision with root package name */
    private boolean f18760a = false;
    LockedDeviceAdapter mAdapter = null;

    public void addHeaderView(View view, Object obj, boolean z) {
    }

    public int getFirstVisiblePosition() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f18760a = true;
    }

    public AnimateFakeList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
    }

    public void setAdapter(LockedDeviceAdapter lockedDeviceAdapter, final ClientAllLockedActivity clientAllLockedActivity) {
        getChildCount();
        boolean z = this.mIsAnimating;
        removeAllViews();
        int count = lockedDeviceAdapter.getCount();
        if (ClientAllLockedDialog.o()) {
            try {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.locked_empty_item_login, this, false);
                inflate.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Uri uri;
                        if (LoginUtil.a()) {
                            uri = Uri.parse("https://home.mi.com/main/login_mi_system?account_name=" + LoginUtil.b());
                        } else {
                            uri = Uri.parse("https://home.mi.com/main/login");
                        }
                        Intent intent = new Intent();
                        intent.setData(uri);
                        intent.setPackage(AnimateFakeList.this.getContext().getPackageName());
                        try {
                            AnimateFakeList.this.getContext().startActivity(intent);
                            DisplayUtils.a(AnimateFakeList.this.getContext(), 17432576, 17432577);
                            OpenApi.a(AnimateFakeList.this.getContext());
                        } catch (Exception unused) {
                        }
                    }
                });
                addView(inflate, new LinearLayout.LayoutParams(-1, -2));
            } catch (Throwable th) {
                String str = TAG;
                Log.e(str, th + "");
            }
        }
        if (count > 0) {
            try {
                addView(LayoutInflater.from(getContext()).inflate(R.layout.device_header_view_layout_locked, this, false), new LinearLayout.LayoutParams(-1, DisplayUtils.a(41.0f)));
            } catch (Throwable th2) {
                String str2 = TAG;
                Log.e(str2, th2 + "");
            }
            for (int i = 0; i < count; i++) {
                View view = lockedDeviceAdapter.getView(i, (View) null, this);
                if (view instanceof IRHeaderTabs) {
                    try {
                        addView(view, new LinearLayout.LayoutParams(-1, -2));
                    } catch (Throwable th3) {
                        String str3 = TAG;
                        Log.e(str3, th3 + "");
                    }
                } else {
                    try {
                        addView(view, new LinearLayout.LayoutParams(-1, DisplayUtils.a(74.0f)));
                        if (i == count - 1) {
                            view.findViewById(R.id.bottom_divider).setVisibility(8);
                        }
                    } catch (Throwable th4) {
                        String str4 = TAG;
                        Log.e(str4, th4 + "");
                    }
                }
            }
        } else if (!IRDeviceUtil.c() && !ClientAllLockedDialog.o()) {
            try {
                View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.locked_empty_item, this, false);
                inflate2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!CoreApi.a().D()) {
                            Uri parse = Uri.parse("https://home.mi.com/shop/main");
                            UrlResolver.Parameter a2 = UrlResolver.Parameter.a(parse);
                            Class<?> a3 = PageUrl.a(a2);
                            if (a3 != null) {
                                Intent intent = new Intent(SHApplication.getAppContext(), a3);
                                for (Map.Entry next : a2.b.entrySet()) {
                                    intent.putExtra((String) next.getKey(), (String) next.getValue());
                                }
                                if (a3 == SmartHomeMainActivity.class) {
                                    intent.putExtra("source", 4);
                                    intent.setFlags(335544320);
                                } else {
                                    intent.setFlags(C.ENCODING_PCM_MU_LAW);
                                }
                                intent.setData(parse);
                                SHApplication.getAppContext().startActivity(intent);
                                DisplayUtils.a(AnimateFakeList.this.getContext(), 17432576, 17432577);
                                OpenApi.a(AnimateFakeList.this.getContext());
                            }
                        } else if (CommonUtils.d(SHApplication.getAppContext(), BuildConfig.b)) {
                            AnimateFakeList.this.a(BuildConfig.b);
                        } else if (OpenExternalBrowserCompat.a(clientAllLockedActivity, "https://www.mi.com/en/")) {
                            DisplayUtils.a(AnimateFakeList.this.getContext(), 17432576, 17432577);
                            OpenApi.a(AnimateFakeList.this.getContext());
                            clientAllLockedActivity.finish();
                        }
                    }
                });
                addView(inflate2, new LinearLayout.LayoutParams(-1, -2));
            } catch (Throwable th5) {
                String str5 = TAG;
                Log.e(str5, th5 + "");
            }
        }
        this.mAdapter = lockedDeviceAdapter;
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        try {
            PackageInfo packageInfo = SHApplication.getAppContext().getPackageManager().getPackageInfo(str, 0);
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.setPackage(packageInfo.packageName);
            ResolveInfo next = SHApplication.getAppContext().getPackageManager().queryIntentActivities(intent, 0).iterator().next();
            if (next != null) {
                String str2 = next.activityInfo.packageName;
                String str3 = next.activityInfo.name;
                Intent intent2 = new Intent();
                intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                intent2.setComponent(new ComponentName(str2, str3));
                SHApplication.getAppContext().startActivity(intent2);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onDataChanged(boolean z, ClientAllLockedActivity clientAllLockedActivity) {
        setAdapter(this.mAdapter, clientAllLockedActivity);
        if (z && this.mAdapter != null && this.mAdapter.getCount() > 0) {
            doMoveInAnim(0, calcStepDelay(this.mAdapter.getCount()));
        }
    }
}
