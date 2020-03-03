package com.xiaomi.smarthome.frame.plugin.runtime.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.util.Pair;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.exoplayer2.C;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.core.entity.device.MiTVDevice;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.statistic.StatHelper;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.IPluginRnActivity;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.miio.activity.DeviceOfflineDetailActivity;
import com.xiaomi.smarthome.sdk.R;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OfflineViewDelegate {
    private static final int INVALID_RES_ID = -1;
    private static final String TAG = "OfflineViewDelegate";
    private boolean haveShown = false;
    private boolean isDestroy = true;
    /* access modifiers changed from: private */
    public boolean isHide = false;
    /* access modifiers changed from: private */
    public Activity mActivity;
    private List<Pair<Integer, Integer>> mBleOfflineDesc = new ArrayList<Pair<Integer, Integer>>() {
        {
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_1), -1));
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_6), Integer.valueOf(R.string.desc_reconnect)));
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_7), -1));
        }
    };
    private ConstraintSet mBottomSet;
    /* access modifiers changed from: private */
    public ConstraintSet mCenterSet;
    private BroadcastReceiver mConnectChangedReceive = null;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public ViewGroup mDecorView;
    /* access modifiers changed from: private */
    public DeviceStat mDevice;
    private List<Pair<Integer, Integer>> mElseOfflineDesc = new ArrayList<Pair<Integer, Integer>>() {
        {
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_8), -1));
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_6), Integer.valueOf(R.string.desc_reconnect)));
        }
    };
    private List<Pair<Integer, Integer>> mElseOfflineDescForBluetooh = new ArrayList<Pair<Integer, Integer>>() {
        {
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_8), -1));
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_6), Integer.valueOf(R.string.retry_network)));
        }
    };
    private List<Pair<Integer, Integer>> mIRfflineDesc = new ArrayList<Pair<Integer, Integer>>() {
        {
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_5), -1));
        }
    };
    private boolean mIsJumpFromPlugin;
    private boolean mIsNeedOfflineView = false;
    private List<Pair<Integer, Integer>> mNBIotOfflineDesc = new ArrayList<Pair<Integer, Integer>>() {
        {
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_8), -1));
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_6), Integer.valueOf(R.string.desc_reconnect)));
        }
    };
    /* access modifiers changed from: private */
    public TouchDelegateConstraintLayout mOfflineView;
    /* access modifiers changed from: private */
    public ConstraintLayout mPluginRecView;
    private List<Pair<Integer, Integer>> mTvOfflineDesc = new ArrayList<Pair<Integer, Integer>>() {
        {
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_9), -1));
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_2), -1));
        }
    };
    private List<Pair<Integer, Integer>> mWifiOfflineDesc = new ArrayList<Pair<Integer, Integer>>() {
        {
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_1), -1));
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_2), -1));
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_3), Integer.valueOf(R.string.desc_reconnect)));
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_4), -1));
        }
    };
    private List<Pair<Integer, Integer>> mZigbeeOfflineDesc = new ArrayList<Pair<Integer, Integer>>() {
        {
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_1), -1));
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_5), -1));
            add(Pair.create(Integer.valueOf(R.string.offline_reason_desc_6), Integer.valueOf(R.string.desc_reconnect)));
        }
    };

    public void onPause() {
    }

    public void onResume() {
    }

    public OfflineViewDelegate(Activity activity, DeviceStat deviceStat) {
        this.mActivity = activity;
        this.mContext = this.mActivity.getApplicationContext();
        this.mDevice = deviceStat;
    }

    public void onCreate() {
        LogUtil.a(TAG, "onCreate: " + this.mActivity.hashCode());
        boolean z = false;
        this.isDestroy = false;
        if (!(this.mActivity.getCallingPackage() == null && this.mActivity.getCallingActivity() == null)) {
            z = true;
        }
        this.mIsJumpFromPlugin = z;
    }

    public void onDestroy() {
        LogUtil.a(TAG, "onDestroy: " + this.mActivity.hashCode());
        this.isDestroy = true;
        unregisterConnectChangedReceive();
    }

    /* access modifiers changed from: private */
    public boolean isValid() {
        return (this.mDevice == null || this.mActivity == null || this.isDestroy) ? false : true;
    }

    private void registerConnectChangedReceive() {
        if (this.mConnectChangedReceive == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.xiaomi.smarthome.bluetooth.connect_status_changed");
            this.mConnectChangedReceive = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (TextUtils.equals("com.xiaomi.smarthome.bluetooth.connect_status_changed", intent.getAction())) {
                        String stringExtra = intent.getStringExtra("key_device_address");
                        if (OfflineViewDelegate.this.mDevice != null && OfflineViewDelegate.this.mDevice.mac.equalsIgnoreCase(stringExtra) && intent.getIntExtra("key_connect_status", 5) == 16 && OfflineViewDelegate.this.isValid()) {
                            if (OfflineViewDelegate.this.isHide) {
                                try {
                                    OfflineViewDelegate.this.mDecorView.removeView(OfflineViewDelegate.this.mOfflineView);
                                    OfflineViewDelegate.this.mOfflineView.setClickable(false);
                                    OfflineViewDelegate.this.mOfflineView.setOnClickListener((View.OnClickListener) null);
                                    OfflineViewDelegate.this.mOfflineView.setDialogIsShowing(false);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                OfflineViewDelegate.this.hide(true);
                            }
                            OfflineViewDelegate.this.unregisterConnectChangedReceive();
                        }
                    }
                }
            };
            BluetoothUtils.a(this.mConnectChangedReceive, intentFilter);
        }
    }

    /* access modifiers changed from: private */
    public void unregisterConnectChangedReceive() {
        if (this.mConnectChangedReceive != null) {
            BluetoothUtils.a(this.mConnectChangedReceive);
            this.mConnectChangedReceive = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        r0 = r0.c();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showOfflineIfNeeded() {
        /*
            r5 = this;
            boolean r0 = r5.mIsJumpFromPlugin
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            com.xiaomi.smarthome.device.api.DeviceStat r0 = r5.mDevice
            if (r0 != 0) goto L_0x000a
            return
        L_0x000a:
            com.xiaomi.smarthome.device.api.DeviceStat r0 = r5.mDevice
            int r0 = r0.pid
            com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory r0 = com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory.fromPid(r0)
            com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory r1 = com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory.Bluetooth
            r2 = 1
            if (r0 != r1) goto L_0x0021
            com.xiaomi.smarthome.device.api.DeviceStat r0 = r5.mDevice
            java.lang.String r0 = r0.mac
            boolean r0 = com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils.c(r0)
        L_0x001f:
            r0 = r0 ^ r2
            goto L_0x0026
        L_0x0021:
            com.xiaomi.smarthome.device.api.DeviceStat r0 = r5.mDevice
            boolean r0 = r0.isOnline
            goto L_0x001f
        L_0x0026:
            if (r0 == 0) goto L_0x0096
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r5.mDevice
            java.lang.String r1 = r1.model
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r0 = r0.d((java.lang.String) r1)
            if (r0 != 0) goto L_0x0037
            return
        L_0x0037:
            com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo r0 = r0.c()
            if (r0 != 0) goto L_0x003e
            return
        L_0x003e:
            int r1 = r0.z()
            r3 = 2
            if (r1 != r3) goto L_0x0046
            return
        L_0x0046:
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r5.mDevice
            int r1 = r1.pid
            com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory r1 = com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory.fromPid(r1)
            com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory r4 = com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory.LocalAp
            if (r1 == r4) goto L_0x0096
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r5.mDevice
            int r1 = r1.pid
            com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory r1 = com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory.fromPid(r1)
            com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory r4 = com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory.BleMesh
            if (r1 != r4) goto L_0x005f
            goto L_0x0096
        L_0x005f:
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r5.mDevice
            int r1 = r1.pid
            com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory r1 = com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory.fromPid(r1)
            com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory r4 = com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory.Bluetooth
            if (r1 != r4) goto L_0x0082
            int r1 = r0.z()
            if (r1 == r2) goto L_0x0096
            int r1 = r0.z()
            if (r1 == r3) goto L_0x0096
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r5.mDevice
            java.lang.String r1 = r1.model
            boolean r1 = com.xiaomi.smarthome.core.server.internal.bluetooth.model.DeviceType.c(r1)
            if (r1 != 0) goto L_0x0082
            goto L_0x0096
        L_0x0082:
            r5.mIsNeedOfflineView = r2
            android.app.Activity r1 = r5.mActivity
            android.view.Window r1 = r1.getWindow()
            android.view.View r1 = r1.getDecorView()
            com.xiaomi.smarthome.frame.plugin.runtime.util.OfflineViewDelegate$10 r2 = new com.xiaomi.smarthome.frame.plugin.runtime.util.OfflineViewDelegate$10
            r2.<init>(r0)
            r1.post(r2)
        L_0x0096:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.plugin.runtime.util.OfflineViewDelegate.showOfflineIfNeeded():void");
    }

    public void showWeakRssiIfNeed(final FrameLayout frameLayout) {
        if (!this.mIsJumpFromPlugin && this.mDevice != null) {
            if (DeviceCategory.fromPid(this.mDevice.pid) != DeviceCategory.Wifi) {
                if (!this.mIsNeedOfflineView) {
                    showPluginRecSceneIdNeed(frameLayout);
                }
            } else if (this.mDevice.isOnline) {
                checkDeviceRssi(this.mContext, new AsyncCallback<Pair<Integer, JSONObject>, Error>() {
                    public void onSuccess(Pair<Integer, JSONObject> pair) {
                        if (pair != null && OfflineViewDelegate.this.isValid() && pair.first != null && pair.second != null) {
                            int intValue = ((Integer) pair.first).intValue();
                            String optString = ((JSONObject) pair.second).optString("note");
                            if (intValue == 0) {
                                OfflineViewDelegate.this.showPluginRecSceneIdNeed(frameLayout);
                            } else {
                                RelativeLayout unused = OfflineViewDelegate.this.addWeakRssiToActivity(frameLayout, optString);
                            }
                        }
                    }

                    public void onFailure(Error error) {
                        if (OfflineViewDelegate.this.isValid()) {
                            OfflineViewDelegate.this.showPluginRecSceneIdNeed(frameLayout);
                        }
                    }
                }, this.mDevice.did, 2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void showPluginRecSceneIdNeed(final FrameLayout frameLayout) {
        Context context = this.mContext;
        if (!SharePrefsManager.b(context, PluginRecommendSceneInfo.SP_NAME, "is_plugin_main_page_show_" + XMStringUtils.d(AccountManager.a().m()) + JSMethod.NOT_SET + this.mDevice.did, false)) {
            XmPluginHostApi.instance().getRecommendScenes(this.mDevice.model, this.mDevice.did, new Callback<JSONObject>() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        final PluginRecommendSceneInfo parse = PluginRecommendSceneInfo.parse(jSONObject);
                        if (!parse.hasScene && parse.mSceneItems != null && parse.mSceneItems.size() > 0) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    try {
                                        OfflineViewDelegate offlineViewDelegate = OfflineViewDelegate.this;
                                        FrameLayout frameLayout = frameLayout;
                                        boolean z = false;
                                        PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem = parse.mSceneItems.get(0);
                                        if (parse.mSceneItems.size() > 1) {
                                            z = true;
                                        }
                                        ConstraintLayout unused = offlineViewDelegate.addRecSceneEntry2Activity(frameLayout, recommendSceneItem, z);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public ConstraintLayout addRecSceneEntry2Activity(final ViewGroup viewGroup, PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem, final boolean z) {
        if (recommendSceneItem == null || viewGroup == null || this.mActivity.isFinishing() || this.mActivity.isDestroyed()) {
            return null;
        }
        if (this.mPluginRecView != null) {
            this.mPluginRecView.setVisibility(0);
            return this.mPluginRecView;
        }
        this.mPluginRecView = (ConstraintLayout) LayoutInflater.from(this.mContext).inflate(R.layout.plugin_rec_scene_entrance, viewGroup, false);
        ((FrameLayout.LayoutParams) this.mPluginRecView.getLayoutParams()).topMargin = (int) ((this.mContext.getResources().getDisplayMetrics().density * 94.0f) + 0.5f);
        viewGroup.addView(this.mPluginRecView);
        if (!TextUtils.isEmpty(recommendSceneItem.entryDesc)) {
            ((TextView) this.mPluginRecView.findViewById(R.id.tv_entrance_title)).setText(recommendSceneItem.entryDesc);
        }
        if (!TextUtils.isEmpty(recommendSceneItem.smallImgUrl)) {
            ((SimpleDraweeView) this.mPluginRecView.findViewById(R.id.sd_entrance_icon)).setImageURI(recommendSceneItem.smallImgUrl);
        }
        final String str = recommendSceneItem.sr_id;
        this.mPluginRecView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if ((OfflineViewDelegate.this.mActivity instanceof PluginHostActivity) || (OfflineViewDelegate.this.mActivity instanceof IPluginRnActivity)) {
                    STAT.d.r(OfflineViewDelegate.this.mDevice.model, str);
                    if (z) {
                        Intent intent = new Intent();
                        intent.setClassName(OfflineViewDelegate.this.mActivity.getPackageName(), "com.xiaomi.smarthome.framework.page.scene.DeviceSceneActivityNew");
                        intent.putExtra("device_id", OfflineViewDelegate.this.mDevice.did);
                        intent.putExtra("is_from_home", true);
                        intent.addFlags(C.ENCODING_PCM_MU_LAW);
                        OfflineViewDelegate.this.mContext.startActivity(intent);
                    } else if (TextUtils.equals(Tags.LuckyShake.VALUE_SUCCESS_CODE, str)) {
                        Intent intent2 = new Intent();
                        intent2.setClassName(OfflineViewDelegate.this.mActivity.getPackageName(), "com.xiaomi.smarthome.scenenew.actiivity.PluginRecommendSceneActivity");
                        intent2.putExtra("sr_id", new Integer(str));
                        intent2.putExtra("did", OfflineViewDelegate.this.mDevice.did);
                        intent2.putExtra("is_from_home", true);
                        intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                        OfflineViewDelegate.this.mContext.startActivity(intent2);
                    } else {
                        try {
                            if (Integer.parseInt(str) / 1000 == 2) {
                                Intent intent3 = new Intent();
                                intent3.setClassName(OfflineViewDelegate.this.mActivity.getPackageName(), "com.xiaomi.smarthome.scenenew.pluginrecommend.CreateSceneFromRecommendActivity");
                                intent3.putExtra("sr_id", new Integer(str));
                                intent3.putExtra("did", OfflineViewDelegate.this.mDevice.did);
                                intent3.putExtra("is_from_home", true);
                                intent3.addFlags(C.ENCODING_PCM_MU_LAW);
                                OfflineViewDelegate.this.mContext.startActivity(intent3);
                            }
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        });
        this.mPluginRecView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                viewGroup.removeView(OfflineViewDelegate.this.mPluginRecView);
                Context access$1200 = OfflineViewDelegate.this.mContext;
                SharePrefsManager.a(access$1200, PluginRecommendSceneInfo.SP_NAME, "is_plugin_main_page_show_" + XMStringUtils.d(AccountManager.a().m()) + JSMethod.NOT_SET + OfflineViewDelegate.this.mDevice.did, true);
            }
        });
        this.mPluginRecView.setVisibility(0);
        STAT.e.k(this.mDevice.model, recommendSceneItem.sr_id);
        return this.mPluginRecView;
    }

    /* access modifiers changed from: private */
    public RelativeLayout addWeakRssiToActivity(final FrameLayout frameLayout, String str) {
        if (TextUtils.isEmpty(str) || frameLayout == null || this.mActivity.isFinishing() || this.mActivity.isDestroyed()) {
            return null;
        }
        final RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this.mContext).inflate(R.layout.plugin_weak_rssi_view, frameLayout, false);
        ((FrameLayout.LayoutParams) relativeLayout.getLayoutParams()).topMargin = (int) ((this.mContext.getResources().getDisplayMetrics().density * 94.0f) + 0.5f);
        frameLayout.addView(relativeLayout);
        ((TextView) relativeLayout.findViewById(R.id.rssi_title)).setText(str);
        TextView textView = (TextView) relativeLayout.findViewById(R.id.rssi_details);
        textView.getPaint().setFlags(8);
        textView.getPaint().setAntiAlias(true);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (OfflineViewDelegate.this.mActivity instanceof PluginHostActivity) {
                    ((PluginHostActivity) OfflineViewDelegate.this.mActivity).openNetworkInfoActivity(OfflineViewDelegate.this.mDevice.did);
                } else if (OfflineViewDelegate.this.mActivity instanceof IPluginRnActivity) {
                    FrameManager.b().k().openNetworkInfoActivity(OfflineViewDelegate.this.mActivity, OfflineViewDelegate.this.mDevice.did);
                }
            }
        });
        relativeLayout.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                frameLayout.removeView(relativeLayout);
            }
        });
        frameLayout.postDelayed(new Runnable() {
            public void run() {
                if (OfflineViewDelegate.this.isValid()) {
                    relativeLayout.setVisibility(0);
                }
            }
        }, Constants.x);
        return relativeLayout;
    }

    /* access modifiers changed from: private */
    public void show(int i) {
        if (isValid() && !this.haveShown) {
            this.haveShown = true;
            StatHelper.f(this.mDevice);
            this.mDecorView = (ViewGroup) this.mActivity.getWindow().getDecorView();
            try {
                this.mOfflineView = (TouchDelegateConstraintLayout) LayoutInflater.from(this.mContext).inflate(R.layout.plugin_offline_view, this.mDecorView, false);
                this.mDecorView.addView(this.mOfflineView);
                final SimpleDraweeView simpleDraweeView = (SimpleDraweeView) this.mOfflineView.findViewById(R.id.device_icon);
                TextView textView = (TextView) this.mOfflineView.findViewById(R.id.offline_desc);
                TextView textView2 = (TextView) this.mOfflineView.findViewById(R.id.offline_reason);
                TextView textView3 = (TextView) this.mOfflineView.findViewById(R.id.more_reason);
                View findViewById = this.mOfflineView.findViewById(R.id.close);
                TextView textView4 = (TextView) this.mOfflineView.findViewById(R.id.back);
                this.mBottomSet = new ConstraintSet();
                this.mBottomSet.clone((ConstraintLayout) this.mOfflineView);
                this.mCenterSet = new ConstraintSet();
                this.mCenterSet.clone(this.mBottomSet);
                this.mCenterSet.centerVertically(R.id.float_main, 0);
                this.mOfflineView.findViewById(R.id.float_main).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                    }
                });
                final boolean z = i == 1 || i == 2;
                LogUtil.a(TAG, "show: canUserOperate: " + z);
                this.mOfflineView.setClickable(true);
                this.mOfflineView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        OfflineViewDelegate.this.hide(z);
                    }
                });
                Locale c = ServerCompact.c(this.mContext);
                StringUtil.a(this.mActivity, c, R.string.device_is_offline, textView);
                StringUtil.a(this.mActivity, c, R.string.action_back_main, textView4);
                setOfflineReasonByDeviceType(textView2);
                String a2 = StringUtil.a(this.mActivity, c, R.string.more_offline_reason);
                SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(a2);
                valueOf.setSpan(new UnderlineSpan(), 0, a2.length(), 33);
                textView3.setHighlightColor(0);
                textView3.setTextColor(-10066330);
                textView3.setText(valueOf);
                textView3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        String str;
                        Intent intent = new Intent();
                        intent.setClassName(OfflineViewDelegate.this.mActivity.getPackageName(), "com.xiaomi.smarthome.miio.activity.DeviceOfflineDetailActivity");
                        intent.putExtra("extra_model", OfflineViewDelegate.this.mDevice.model);
                        intent.putExtra("did", OfflineViewDelegate.this.mDevice.did);
                        if (DeviceFactory.b(OfflineViewDelegate.this.mDevice.model) == MiTVDevice.class) {
                            str = "TV_etc";
                        } else {
                            str = OfflineViewDelegate.this.mDevice.pid + "";
                        }
                        intent.putExtra(DeviceOfflineDetailActivity.EXTRA_DEVICE_TYPE, str);
                        intent.addFlags(C.ENCODING_PCM_MU_LAW);
                        OfflineViewDelegate.this.mContext.startActivity(intent);
                        StatHelper.h(OfflineViewDelegate.this.mDevice);
                    }
                });
                findViewById.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        OfflineViewDelegate.this.hide(z);
                    }
                });
                textView4.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClassName(OfflineViewDelegate.this.mActivity.getPackageName(), "com.xiaomi.smarthome.SmartHomeMainActivity");
                        intent.setFlags(603979776);
                        OfflineViewDelegate.this.mActivity.startActivity(intent);
                        OfflineViewDelegate.this.mActivity.finish();
                    }
                });
                if (!TextUtils.isEmpty(this.mDevice.model)) {
                    requestDeviceImage(this.mDevice.model, new AsyncCallback<String, Error>() {
                        public void onSuccess(String str) {
                            if (OfflineViewDelegate.this.isValid()) {
                                if (!TextUtils.isEmpty(str)) {
                                    simpleDraweeView.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(str).setAutoPlayAnimations(true)).build());
                                    return;
                                }
                                OfflineViewDelegate.setIconByUrl(OfflineViewDelegate.this.mDevice.model, simpleDraweeView, 0);
                            }
                        }

                        public void onFailure(Error error) {
                            if (OfflineViewDelegate.this.isValid()) {
                                OfflineViewDelegate.setIconByUrl(OfflineViewDelegate.this.mDevice.model, simpleDraweeView, 0);
                            }
                        }
                    });
                }
                final TextView textView5 = (TextView) this.mOfflineView.findViewById(R.id.offline_rssi);
                textView5.setVisibility(8);
                if (DeviceCategory.fromPid(this.mDevice.pid) == DeviceCategory.Wifi) {
                    System.currentTimeMillis();
                    checkDeviceRssi(this.mContext, new AsyncCallback<Pair<Integer, JSONObject>, Error>() {
                        public void onSuccess(Pair<Integer, JSONObject> pair) {
                            if (pair != null && OfflineViewDelegate.this.isValid() && pair.first != null && pair.second != null) {
                                int intValue = ((Integer) pair.first).intValue();
                                final String optString = ((JSONObject) pair.second).optString("note");
                                if (intValue != 0 && !TextUtils.isEmpty(optString)) {
                                    textView5.setVisibility(0);
                                    textView5.setText(StringUtil.a(OfflineViewDelegate.this.mActivity, ServerCompact.c(OfflineViewDelegate.this.mContext), R.string.device_rssi_check));
                                    textView5.postDelayed(new Runnable() {
                                        public void run() {
                                            if (OfflineViewDelegate.this.isValid()) {
                                                textView5.setText(optString);
                                            }
                                        }
                                    }, Constants.x);
                                }
                            }
                        }

                        public void onFailure(Error error) {
                            if (OfflineViewDelegate.this.isValid()) {
                            }
                        }
                    }, this.mDevice.did, 0);
                }
                if (Build.VERSION.SDK_INT >= 19) {
                    this.mActivity.getWindow().getDecorView().postDelayed(new Runnable() {
                        public void run() {
                            AutoTransition autoTransition = new AutoTransition();
                            autoTransition.setInterpolator(new DecelerateInterpolator());
                            autoTransition.setDuration(320);
                            TransitionManager.beginDelayedTransition(OfflineViewDelegate.this.mDecorView, autoTransition);
                            OfflineViewDelegate.this.mCenterSet.applyTo(OfflineViewDelegate.this.mOfflineView);
                        }
                    }, 200);
                } else {
                    this.mCenterSet.applyTo(this.mOfflineView);
                }
                registerConnectChangedReceive();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setOfflineReasonByDeviceType(TextView textView) {
        List<Pair<Integer, Integer>> list;
        if (!(DeviceFactory.b(this.mDevice.model) == MiTVDevice.class)) {
            switch (DeviceCategory.fromPid(this.mDevice.pid)) {
                case Wifi:
                    list = this.mWifiOfflineDesc;
                    break;
                case SubDevice:
                    list = this.mZigbeeOfflineDesc;
                    break;
                case BleMesh:
                    list = this.mBleOfflineDesc;
                    break;
                case IR:
                    list = this.mIRfflineDesc;
                    break;
                case NBIOT:
                    list = this.mNBIotOfflineDesc;
                    break;
                default:
                    if (DeviceCategory.fromPid(this.mDevice.pid) != DeviceCategory.Bluetooth) {
                        list = this.mElseOfflineDesc;
                        break;
                    } else {
                        list = this.mElseOfflineDescForBluetooh;
                        break;
                    }
            }
        } else {
            list = this.mTvOfflineDesc;
        }
        Locale c = ServerCompact.c(this.mContext);
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf("");
        if (list.size() == 1) {
            valueOf.append(StringUtil.a(this.mActivity, c, ((Integer) list.get(0).first).intValue()));
            textView.setTextAlignment(4);
            textView.setGravity(17);
        } else {
            int i = 0;
            while (i < list.size()) {
                Pair pair = list.get(i);
                int i2 = i + 1;
                valueOf.append(String.valueOf(i2)).append(".");
                String a2 = StringUtil.a(this.mActivity, ServerCompact.c(this.mContext), ((Integer) pair.first).intValue());
                if (-1 == ((Integer) pair.second).intValue()) {
                    valueOf.append(a2);
                } else {
                    String a3 = StringUtil.a(this.mActivity, ServerCompact.c(this.mContext), ((Integer) pair.second).intValue());
                    String format = String.format(a2, new Object[]{a3});
                    AnonymousClass26 r8 = new ClickableSpan() {
                        public void onClick(View view) {
                            if (DeviceCategory.fromPid(OfflineViewDelegate.this.mDevice.pid) == DeviceCategory.Bluetooth) {
                                XmPluginHostApi.instance().visualSecureBind(OfflineViewDelegate.this.mDevice.did);
                                if (OfflineViewDelegate.this.mActivity != null) {
                                    OfflineViewDelegate.this.mActivity.finish();
                                }
                                StatHelper.i(OfflineViewDelegate.this.mDevice);
                                return;
                            }
                            Intent intent = new Intent();
                            intent.setClassName(OfflineViewDelegate.this.mActivity.getPackageName(), "com.xiaomi.smarthome.device.choosedevice.ResetPageRoute");
                            intent.putExtra("extra_model", OfflineViewDelegate.this.mDevice.model);
                            intent.addFlags(C.ENCODING_PCM_MU_LAW);
                            OfflineViewDelegate.this.mContext.startActivity(intent);
                        }

                        public void updateDrawState(TextPaint textPaint) {
                            super.updateDrawState(textPaint);
                            textPaint.setColor(Color.parseColor("#ff32BAC0"));
                        }
                    };
                    valueOf.append(format);
                    int indexOf = valueOf.toString().indexOf(a3);
                    valueOf.setSpan(r8, indexOf, a3.length() + indexOf, 33);
                }
                if (i != list.size() - 1) {
                    valueOf.append("\n");
                }
                i = i2;
            }
        }
        textView.setHighlightColor(0);
        textView.setText(valueOf);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* access modifiers changed from: private */
    public void hide(final boolean z) {
        if (!this.isHide) {
            this.isHide = true;
            StatHelper.g(this.mDevice);
            if (Build.VERSION.SDK_INT >= 19) {
                AutoTransition autoTransition = new AutoTransition();
                autoTransition.setInterpolator(new AccelerateInterpolator());
                autoTransition.setDuration(300);
                autoTransition.addListener(new Transition.TransitionListener() {
                    public void onTransitionCancel(Transition transition) {
                    }

                    public void onTransitionPause(Transition transition) {
                    }

                    public void onTransitionResume(Transition transition) {
                    }

                    public void onTransitionStart(Transition transition) {
                    }

                    public void onTransitionEnd(Transition transition) {
                        if (z) {
                            OfflineViewDelegate.this.mDecorView.removeView(OfflineViewDelegate.this.mOfflineView);
                        }
                    }
                });
                TransitionManager.beginDelayedTransition(this.mDecorView, autoTransition);
                this.mBottomSet.applyTo(this.mOfflineView);
            } else {
                this.mBottomSet.applyTo(this.mOfflineView);
                if (z) {
                    this.mDecorView.removeView(this.mOfflineView);
                }
            }
            this.mOfflineView.setClickable(false);
            this.mOfflineView.setOnClickListener((View.OnClickListener) null);
            this.mOfflineView.setDialogIsShowing(false);
        }
    }

    private void requestDeviceImage(String str, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("models", str);
            jSONObject.put("device", Build.DEVICE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a((Context) this.mActivity, new NetRequest.Builder().a("POST").b("/v2/public/get_product_config").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                JSONArray optJSONArray = jSONObject.optJSONArray("configs");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i).optJSONObject("neg_screen");
                    if (optJSONObject != null) {
                        optJSONObject.optString("short_480");
                        String optString = optJSONObject.optString("neg_480");
                        optJSONObject.optString("short_video");
                        optJSONObject.optString("neg_video");
                        return optString;
                    }
                }
                return "";
            }
        }, Crypto.NONE, asyncCallback);
    }

    public static void setIconByUrl(String str, SimpleDraweeView simpleDraweeView, int i) {
        if (i == 0) {
            i = R.drawable.device_list_phone_no;
        }
        final String pluginIcon = getPluginIcon(str);
        if (simpleDraweeView.getHierarchy() == null) {
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(i)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        }
        if (TextUtils.isEmpty(pluginIcon) || !pluginIcon.startsWith("http")) {
            int resource = ClientIconMap.getResource(str);
            if (resource != 0) {
                simpleDraweeView.setImageURI(getResoursUri(resource));
            } else {
                simpleDraweeView.setImageURI(getResoursUri(i));
            }
        } else {
            simpleDraweeView.setImageURI(Uri.parse(pluginIcon));
            DraweeController controller = simpleDraweeView.getController();
            if (controller != null && (controller instanceof AbstractDraweeController)) {
                ((AbstractDraweeController) controller).addControllerListener(new BaseControllerListener() {
                    public void onFailure(String str, Throwable th) {
                        Log.e("Failure", str + " decode failed");
                        Fresco.getImagePipeline().evictFromMemoryCache(Uri.parse(pluginIcon));
                        Fresco.getImagePipeline().evictFromDiskCache(Uri.parse(pluginIcon));
                    }
                });
            }
        }
    }

    public static Uri getResoursUri(int i) {
        return Uri.parse("res://com.xiaomi.smarthome/" + i);
    }

    public static String getPluginIcon(String str) {
        PluginRecord d;
        if (str == null || !CoreApi.a().c(str) || (d = CoreApi.a().d(str)) == null) {
            return null;
        }
        return d.t();
    }

    public static void checkDeviceRssi(Context context, AsyncCallback<Pair<Integer, JSONObject>, Error> asyncCallback, final String str, int i) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONObject.put("dids", jSONArray);
            jSONObject.put("type", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        Context context2 = context;
        CoreApi.a().a(context2, new NetRequest.Builder().a("GET").b("/v2/device/devicerssi").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Pair<Integer, JSONObject>>() {
            public Pair<Integer, JSONObject> parse(JSONObject jSONObject) throws JSONException {
                LogUtil.a(OfflineViewDelegate.TAG, jSONObject.toString());
                try {
                    JSONObject optJSONObject = jSONObject.optJSONObject(str);
                    return Pair.create(Integer.valueOf(optJSONObject.getInt("net_stat")), optJSONObject);
                } catch (Exception unused) {
                    return null;
                }
            }
        }, Crypto.RC4, asyncCallback);
    }
}
