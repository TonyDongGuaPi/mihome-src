package com.xiaomi.smarthome.listcamera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.mijia.debug.SDKLog;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.ClientApiStub;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.corereceiver.CoreHostApiImpl;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.redpoint.ProfileRedPointManager;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.DevicePtrFrameLayout;
import com.xiaomi.smarthome.listcamera.CameraGroupManager;
import com.xiaomi.smarthome.listcamera.CameraInfoRefreshManager;
import com.xiaomi.smarthome.listcamera.adapter.CameraLargeAdapter;
import com.xiaomi.smarthome.listcamera.adapter.CameraSmallAdapter;
import com.xiaomi.smarthome.messagecenter.MessageCenter;
import com.xiaomi.smarthome.miio.page.BaseClientAllPage;
import com.xiaomi.youpin.login.other.common.ConvertUtils;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import java.util.List;

public class AllCameraPage extends BaseClientAllPage {
    public static final String b = "AllCameraPage";
    public static final String c = "refresh_list";
    public static final String d = "change_adapter";
    /* access modifiers changed from: private */
    public ImageView A;
    private View B;
    private MLAlertDialog C;
    /* access modifiers changed from: private */
    public int D;
    private String E;
    private final String F = "showedToast";
    private CameraInfoRefreshManager.RefreshListener G = new CameraInfoRefreshManager.RefreshListener() {
        public void a() {
        }

        public void b() {
            AllCameraPage.this.i.refreshComplete();
            AllCameraPage.this.r.notifyDataSetChanged();
            AllCameraPage.this.a();
        }
    };
    public boolean e = false;
    public boolean f = false;
    RecyclerView g;
    boolean h = false;
    DevicePtrFrameLayout i;
    boolean j = false;
    boolean k = false;
    boolean l = false;
    SmartHomeDeviceHelper.IPluginInfoEventListener m = new SmartHomeDeviceHelper.IPluginInfoEventListener() {
        public void a(int i) {
            switch (i) {
                case 0:
                    AllCameraPage.this.k = true;
                    AllCameraPage.this.e();
                    return;
                case 1:
                    AllCameraPage.this.k = false;
                    if (AllCameraPage.this.l) {
                        AllCameraPage.this.b(false);
                        return;
                    } else {
                        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                            public void run() {
                                AllCameraPage.this.b(false);
                            }
                        }, 900);
                        return;
                    }
                case 2:
                    AllCameraPage.this.k = false;
                    if (AllCameraPage.this.l) {
                        AllCameraPage.this.b(false);
                        return;
                    } else {
                        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                            public void run() {
                                AllCameraPage.this.b(false);
                            }
                        }, 900);
                        return;
                    }
                default:
                    return;
            }
        }
    };
    Runnable n = new Runnable() {
        public void run() {
            AllCameraPage.this.i.refreshComplete();
            AllCameraPage.this.j = false;
            BaseActivity baseActivity = (BaseActivity) AllCameraPage.this.getActivity();
            if (baseActivity != null && !baseActivity.isFinishing() && AllCameraPage.this.h) {
                AllCameraPage.this.h = false;
            }
        }
    };
    SmartHomeDeviceManager.IClientDeviceListener o = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i) {
            if (i == 3) {
                if (AllCameraPage.this.l) {
                    AllCameraPage.this.b(false);
                    CameraInfoRefreshManager.a().g();
                    return;
                }
                SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                    public void run() {
                        AllCameraPage.this.b(false);
                        CameraInfoRefreshManager.a().g();
                    }
                }, 900);
            }
        }

        public void a(int i, Device device) {
            if (AllCameraPage.this.r != null && device != null && i == 2 && (AllCameraPage.this.r instanceof CameraLargeAdapter)) {
                ((CameraLargeAdapter) AllCameraPage.this.r).a(device.did);
            }
        }

        public void b(int i) {
            if (i == 3 && AllCameraPage.this.g != null) {
                AllCameraPage.this.i.refreshComplete();
            }
        }
    };
    BroadcastReceiver p = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            AllCameraPage.this.a(intent);
        }
    };
    private Handler q = new Handler();
    /* access modifiers changed from: private */
    public RecyclerView.Adapter r;
    private ImageView s;
    private RecyclerView.Adapter t;
    private RecyclerViewExpandableItemManager u;
    private LinearLayoutManager v;
    private CameraLargeAdapter w;
    private CameraSmallAdapter x;
    private GridLayoutManager y;
    private PtrIndicator z;

    private void l() {
    }

    public void a(boolean z2) {
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z2) {
    }

    public void g() {
    }

    public void h() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CameraInfoRefreshManager.a().a(this.G);
        SmartHomeDeviceManager.a().a(this.o);
        this.q.postDelayed(new Runnable() {
            public void run() {
                AllCameraPage.this.m();
            }
        }, 1000);
    }

    public boolean onBackPressed() {
        getActivity().finish();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.D == 0) {
            for (int i2 = 0; i2 < CameraGroupManager.a().c().size(); i2++) {
                if (CameraGroupManager.a().c().get(i2).d) {
                    this.u.a(i2);
                } else {
                    this.u.b(i2);
                }
            }
        }
    }

    public void onResume() {
        super.onResume();
        this.f = false;
        if (this.r != null) {
            this.r.notifyDataSetChanged();
            a();
        }
    }

    public void onPause() {
        super.onPause();
        this.f = true;
        CameraFrameManager.a().b(this.mContext);
        if (this.D == 0) {
            this.w.c();
        } else {
            this.x.b();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.w.d();
        CameraInfoRefreshManager.a().b(this.G);
        SmartHomeDeviceManager.a().c(this.o);
        LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.p);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f16923a == null) {
            this.f16923a = layoutInflater.inflate(R.layout.client_all_activity_camera, (ViewGroup) null);
            j();
            c();
            b();
        }
        IntentFilter intentFilter = new IntentFilter("action_on_login_success");
        intentFilter.addAction(c);
        intentFilter.addAction("action_on_logout");
        intentFilter.addAction(ClientApiStub.ACTION_UPDATE_DEVICE_LIST);
        intentFilter.addAction(CoreHostApiImpl.e);
        intentFilter.addAction("wifi_scan_result_broadcast");
        intentFilter.addAction(d);
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.p, intentFilter);
        SharePrefHelper sharePrefHelper = new SharePrefHelper(getActivity(), b, "");
        if (!sharePrefHelper.getGlobalBlnValue("showedToast")) {
            ToastUtil.a((int) R.string.all_camera_play_limit_3, 1);
            sharePrefHelper.putGlobalBln("showedToast", true);
        }
        return this.f16923a;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        ((TextView) this.f16923a.findViewById(R.id.module_a_2_more_title)).setText(R.string.camera_page_title);
        this.A = (ImageView) this.f16923a.findViewById(R.id.module_a_3_more_btn);
        this.A.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View inflate = View.inflate(AllCameraPage.this.mContext, R.layout.popup_camera_select_showmode, (ViewGroup) null);
                final TextView textView = (TextView) inflate.findViewById(R.id.select_large_mode);
                final TextView textView2 = (TextView) inflate.findViewById(R.id.select_small_mode);
                if (AllCameraPage.this.D == 0) {
                    textView.setSelected(true);
                } else {
                    textView2.setSelected(true);
                }
                final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        textView.setSelected(true);
                        textView2.setSelected(false);
                        CameraGroupManager.a().a(0, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Void voidR) {
                                SDKLog.b(AllCameraPage.b, "setShowViewType onSuccess");
                                LocalBroadcastManager.getInstance(AllCameraPage.this.mContext).sendBroadcast(new Intent(AllCameraPage.d));
                            }

                            public void onFailure(Error error) {
                                SDKLog.b(AllCameraPage.b, "setShowViewType onFailure=" + error);
                            }
                        });
                        popupWindow.dismiss();
                        AllCameraPage.this.A.setImageResource(R.drawable.icon_camera_large_mode);
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        textView2.setSelected(true);
                        textView.setSelected(false);
                        CameraGroupManager.a().a(1, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Void voidR) {
                                SDKLog.b(AllCameraPage.b, "setShowViewType onSuccess");
                                LocalBroadcastManager.getInstance(AllCameraPage.this.mContext).sendBroadcast(new Intent(AllCameraPage.d));
                            }

                            public void onFailure(Error error) {
                                SDKLog.b(AllCameraPage.b, "setShowViewType onFailure=" + error);
                            }
                        });
                        popupWindow.dismiss();
                        AllCameraPage.this.A.setImageResource(R.drawable.icon_camera_small_mode);
                    }
                });
                if (Build.VERSION.SDK_INT >= 21) {
                    popupWindow.showAsDropDown(AllCameraPage.this.A, -ConvertUtils.a(AllCameraPage.this.mContext, 0.0f), -ConvertUtils.a(AllCameraPage.this.mContext, 18.0f), GravityCompat.END);
                } else {
                    popupWindow.showAsDropDown(AllCameraPage.this.A, -ConvertUtils.a(AllCameraPage.this.mContext, 0.0f), -ConvertUtils.a(AllCameraPage.this.mContext, 18.0f));
                }
            }
        });
        this.f16923a.findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AllCameraPage.this.getActivity().finish();
            }
        });
        ImageView imageView = (ImageView) this.f16923a.findViewById(R.id.sequence_img);
        if (DarkModeCompat.a(getContext())) {
            DarkModeCompat.a((View) imageView, false);
            imageView.setImageResource(R.drawable.icon_camera_sequence_dark);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AllCameraPage.this.startActivity(new Intent(AllCameraPage.this.getActivity(), CameraSortActivity.class));
            }
        });
    }

    private void a(PopupWindow popupWindow) {
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha = 0.7f;
        getActivity().getWindow().setAttributes(attributes);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams attributes = AllCameraPage.this.getActivity().getWindow().getAttributes();
                attributes.alpha = 1.0f;
                AllCameraPage.this.getActivity().getWindow().setAttributes(attributes);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.v = new LinearLayoutManager(getContext());
        this.y = new GridLayoutManager(getContext(), 2);
        this.g = (RecyclerView) this.f16923a.findViewById(R.id.device_grid_view);
        this.g.setPadding(DisplayUtils.a(7.0f), 0, DisplayUtils.a(7.0f), 0);
        this.u = new RecyclerViewExpandableItemManager((Parcelable) null);
        this.w = new CameraLargeAdapter(getContext(), this, this.g, this.u);
        this.x = new CameraSmallAdapter(getContext(), this, this.g);
        this.D = CameraGroupManager.a().f();
        if (this.D == 0) {
            this.r = this.w;
        } else {
            this.r = this.x;
        }
        this.u.a((RecyclerViewExpandableItemManager.OnGroupExpandListener) new RecyclerViewExpandableItemManager.OnGroupExpandListener() {
            public void b(int i, boolean z) {
                if (z && !SmartHomeDeviceHelper.a().d()) {
                    CameraGroupManager.a().a(i, true);
                }
            }
        });
        this.u.a((RecyclerViewExpandableItemManager.OnGroupCollapseListener) new RecyclerViewExpandableItemManager.OnGroupCollapseListener() {
            public void a(int i, boolean z) {
                if (z && !SmartHomeDeviceHelper.a().d()) {
                    CameraGroupManager.a().a(i, false);
                }
            }
        });
        if (this.D == 0) {
            this.t = this.u.a(this.r);
            this.g.setAdapter(this.t);
            this.g.setLayoutManager(this.v);
        } else {
            this.g.setLayoutManager(this.y);
            this.g.setAdapter(this.x);
        }
        new SwipeDismissItemAnimator().setSupportsChangeAnimations(false);
        this.g.setItemAnimator((RecyclerView.ItemAnimator) null);
        this.g.setHasFixedSize(false);
        if (this.D == 0) {
            this.u.a(this.g);
        }
        List<CameraGroupManager.GroupInfo> c2 = CameraGroupManager.a().c();
        if (c2 != null) {
            for (CameraGroupManager.GroupInfo groupInfo : c2) {
                Device b2 = SmartHomeDeviceManager.a().b(groupInfo.f19240a);
                if (b2 != null && CameraFrameManager.a().a(b2.did) == null) {
                    CameraFrameManager.a().a(b2, false);
                }
            }
            this.g.scrollToPosition(CameraGroupManager.a().h());
        }
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i3 != -1 || i2 != 100) {
            return;
        }
        if (this.D == 0) {
            this.w.b();
        } else {
            this.x.a();
        }
    }

    private void j() {
        this.z = new PtrIndicator();
        this.i = (DevicePtrFrameLayout) this.f16923a.findViewById(R.id.pull_down_refresh);
        this.i.setVisibility(0);
        this.i.disableWhenHorizontalMove(true);
        this.i.setPullToRefresh(false);
        this.i.setPtrIndicator(this.z);
        this.i.addPtrUIHandler(new PtrUIHandler() {
            public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
            }

            public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
            }

            public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
            }

            public void onUIReset(PtrFrameLayout ptrFrameLayout) {
            }

            public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
                ((TextView) ptrFrameLayout.getHeaderView().findViewById(R.id.pull_header_txt)).setTextColor(-16777216);
            }
        });
        this.i.setPtrHandler(new PtrDefaultHandler() {
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                AllCameraPage.this.h = true;
                if (!AllCameraPage.this.j) {
                    AllCameraPage.this.d();
                }
            }
        });
        this.l = true;
    }

    public void d() {
        AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                CameraInfoRefreshManager.a().f();
                SmartHomeDeviceManager.a().s();
                return null;
            }
        }, new Void[0]);
    }

    /* access modifiers changed from: private */
    public void a(Intent intent) {
        if ("action_on_login_success".equals(intent.getAction())) {
            k();
        } else if ("action_on_logout".equals(intent.getAction())) {
            l();
        } else if (c.equals(intent.getAction())) {
            this.r.notifyDataSetChanged();
            a();
        } else if (d.equals(intent.getAction()) && this.D != CameraGroupManager.a().f()) {
            this.D = CameraGroupManager.a().f();
            CameraFrameManager.a().a(this.mContext);
            f();
        }
    }

    private void k() {
        o();
        n();
    }

    /* access modifiers changed from: private */
    public void m() {
        SmartHomeDeviceManager.a().s();
        CameraInfoRefreshManager.a().f();
    }

    private void n() {
        if (this.s == null) {
            return;
        }
        if (CoreApi.a().D()) {
            this.s.setVisibility(4);
        } else {
            this.s.setVisibility(0);
        }
    }

    private void o() {
        MessageCenter a2 = MessageCenter.a();
        a2.b();
        Context appContext = SHApplication.getAppContext();
        a2.a(PreferenceUtils.b(appContext, ProfileRedPointManager.d + CoreApi.a().s(), System.currentTimeMillis()), 2);
        Context appContext2 = SHApplication.getAppContext();
        a2.a(PreferenceUtils.b(appContext2, ProfileRedPointManager.e + CoreApi.a().s(), System.currentTimeMillis()), 1);
        a2.c();
    }

    public void e() {
        BaseActivity baseActivity = (BaseActivity) getActivity();
        if (baseActivity != null && !baseActivity.isFinishing() && baseActivity.mHandler != null) {
            baseActivity.mHandler.postDelayed(new Runnable() {
                public void run() {
                    AllCameraPage.this.i.autoRefresh(true);
                    AllCameraPage.this.j = true;
                }
            }, 200);
        }
    }

    /* access modifiers changed from: package-private */
    public void f() {
        if (this.D == 0) {
            this.r = this.w;
            if (this.t == null) {
                this.t = this.u.a(this.r);
                this.u.a(this.g);
            }
            this.g.setAdapter(this.t);
            this.g.setLayoutManager(this.v);
        } else {
            this.r = this.x;
            this.g.setLayoutManager(this.y);
            this.g.setAdapter(this.x);
        }
        this.r.notifyDataSetChanged();
        this.g.scrollToPosition(CameraGroupManager.a().h());
    }
}
