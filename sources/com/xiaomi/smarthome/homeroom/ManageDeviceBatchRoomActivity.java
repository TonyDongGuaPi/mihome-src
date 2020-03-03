package com.xiaomi.smarthome.homeroom;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity;
import com.xiaomi.smarthome.frame.plugin.runtime.util.TitleBarUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.ManageDeviceBatchRoomActivity;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.homeroom.view.AddRoomFlowGroup;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.newui.MoveRoomDialogHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ManageDeviceBatchRoomActivity extends BaseActivity {
    public static final String INTENT_KEY_DID_LIST = "device_did_list";

    /* renamed from: a  reason: collision with root package name */
    private static final String f18125a = "ManageDeviceBatchRoomActivity";
    private static final int b = 1;
    /* access modifiers changed from: private */
    public List<String> c = new ArrayList();
    /* access modifiers changed from: private */
    public XQProgressDialog d;
    /* access modifiers changed from: private */
    public Device e;
    /* access modifiers changed from: private */
    public String f;
    /* access modifiers changed from: private */
    public AddRoomFlowGroup g;
    private SimpleDraweeView h;
    private TextView i;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public SaveLogicModule k;
    /* access modifiers changed from: private */
    public String l;
    SmartHomeDeviceManager.IClientDeviceListener mDeviceLoadingListener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void a(int i) {
            if (3 == i) {
                Device unused = ManageDeviceBatchRoomActivity.this.e = SmartHomeDeviceManager.a().b(ManageDeviceBatchRoomActivity.this.f);
                if (ManageDeviceBatchRoomActivity.this.e != null) {
                    ManageDeviceBatchRoomActivity.this.a();
                    if (ManageDeviceBatchRoomActivity.this.d != null) {
                        ManageDeviceBatchRoomActivity.this.d.dismiss();
                        return;
                    }
                    return;
                }
                ManageDeviceBatchRoomActivity.this.finish();
            }
        }

        public void b(int i) {
            ManageDeviceBatchRoomActivity.this.finish();
        }
    };

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(DialogInterface dialogInterface) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.c = intent.getStringArrayListExtra(INTENT_KEY_DID_LIST);
        if (this.c == null || this.c.isEmpty()) {
            finish();
            return;
        }
        this.f = this.c.get(0);
        this.e = SmartHomeDeviceManager.a().b(this.f);
        if (!SmartHomeDeviceManager.a().u() || this.e != null) {
            setContentView(R.layout.activity_manage_device_room);
            TitleBarUtil.setTitleBar(this);
            if (!SmartHomeDeviceManager.a().u()) {
                b();
                return;
            }
            a();
            this.mHandler.postDelayed(new Runnable() {
                public final void run() {
                    ManageDeviceBatchRoomActivity.this.l();
                }
            }, 1000);
            return;
        }
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void l() {
        if (isValid()) {
            SmartHomeDeviceManager.a().p();
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        this.l = this.e.name;
        this.k = new SaveLogicModule();
        c();
        g();
    }

    private void b() {
        this.d = new XQProgressDialog(this);
        this.d.setCancelable(true);
        this.d.setMessage(getResources().getString(R.string.loading));
        this.d.show();
        this.d.a((XQProgressDialog.OnProgressCancelListener) new XQProgressDialog.OnProgressCancelListener() {
            public final void onCancel() {
                ManageDeviceBatchRoomActivity.this.finish();
            }
        });
        SmartHomeDeviceManager.a().a(this.mDeviceLoadingListener);
        SmartHomeDeviceManager.a().p();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        SmartHomeDeviceManager.a().c(this.mDeviceLoadingListener);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void onBackPressed() {
        findViewById(R.id.title_bar_return).performClick();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        Bundle extras;
        if (i3 == -1 && (extras = intent.getExtras()) != null && i2 == 1) {
            Object obj = extras.get("result");
            if (obj instanceof Room) {
                this.g.setCurrentSelect(1, ((Room) obj).d());
            } else if (obj instanceof String) {
                this.g.setCurrentSelect(2, (String) obj);
            }
        }
    }

    private void c() {
        this.g = (AddRoomFlowGroup) findViewById(R.id.tag_choose);
        this.h = (SimpleDraweeView) findViewById(R.id.device_icon);
        findViewById(R.id.device_name_container).setVisibility(8);
        ImageView imageView = (ImageView) findViewById(R.id.title_bar_return);
        imageView.setImageResource(R.drawable.std_tittlebar_main_device_back_black);
        imageView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ManageDeviceBatchRoomActivity.this.b(view);
            }
        });
        findViewById(R.id.add_to_common_use).setVisibility(8);
        findViewById(R.id.short_cut_container).setVisibility(8);
        findViewById(R.id.space_2).setVisibility(8);
        findViewById(R.id.space_3).setVisibility(8);
        findViewById(R.id.init_next).setVisibility(8);
        ((ScrollView) findViewById(R.id.scrollView)).setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.i = (TextView) findViewById(R.id.open_device);
        d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        new MLAlertDialog.Builder(this).a((int) R.string.save_auth_or_not).a((int) R.string.save_auth, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                ManageDeviceBatchRoomActivity.this.b(dialogInterface, i);
            }
        }).b((int) R.string.not_save_auth, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                ManageDeviceBatchRoomActivity.this.a(dialogInterface, i);
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i2) {
        this.j = true;
        h();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        this.e.name = this.l;
        finish();
    }

    private void d() {
        if (this.e != null) {
            DeviceFactory.b(this.e.model, this.h);
            e();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        h();
    }

    private void e() {
        this.i.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ManageDeviceBatchRoomActivity.this.a(view);
            }
        });
        this.i.setBackgroundResource(R.drawable.common_button_v2);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.i.getLayoutParams();
        layoutParams.leftMargin = getResources().getDimensionPixelSize(R.dimen.common_button_margin);
        this.i.setLayoutParams(layoutParams);
        this.i.getParent().requestLayout();
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.j) {
            this.e.name = this.l;
            finish();
        }
        if (this.d != null) {
            this.d.dismiss();
        }
        a((CommonActivity) this);
    }

    private void g() {
        PluginDeviceInfo c2;
        if (this.e != null) {
            Room p = HomeManager.a().p(this.e.did);
            this.g.setCurrentSelect(1, p == null ? null : p.d());
            if (!(this.e == null || (c2 = CoreApi.a().d(this.e.model).c()) == null || c2.t() != Device.PID_BLE_MESH)) {
                this.k.c = true;
            }
            this.g.setOnTagClickListener(new AddRoomFlowGroup.TagClickListener() {
                public void a() {
                    Intent intent = new Intent(ManageDeviceBatchRoomActivity.this.getContext(), PickRoomActivity.class);
                    intent.putExtra("home_id", HomeManager.a().l());
                    ManageDeviceBatchRoomActivity.this.startActivityForResult(intent, 1);
                }

                public void b() {
                    MoveRoomDialogHelper.a((Context) ManageDeviceBatchRoomActivity.this, (List<String>) null, (MoveRoomDialogHelper.addRoomListener) new MoveRoomDialogHelper.addRoomListener() {
                        public final void onSuccess(String str) {
                            ManageDeviceBatchRoomActivity.AnonymousClass2.this.a(str);
                        }
                    });
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a(String str) {
                    ManageDeviceBatchRoomActivity.this.runOnUiThread(new Runnable() {
                        public final void run() {
                            ManageDeviceBatchRoomActivity.AnonymousClass2.this.c();
                        }
                    });
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void c() {
                    ManageDeviceBatchRoomActivity.this.g.setCurrentSelect(ManageDeviceBatchRoomActivity.this.g.getCurrentSelectType(), ManageDeviceBatchRoomActivity.this.g.getCurrentSelectValue());
                }

                public void a(Object obj) {
                    if (obj != null) {
                        if (obj instanceof String) {
                            ManageDeviceBatchRoomActivity.this.k.c = true;
                            ManageDeviceBatchRoomActivity.this.k.f = true;
                            ManageDeviceBatchRoomActivity.this.k.g = false;
                            ManageDeviceBatchRoomActivity.this.k.d = false;
                        } else if (obj instanceof Room) {
                            ManageDeviceBatchRoomActivity.this.k.c = true;
                            ManageDeviceBatchRoomActivity.this.k.f = false;
                            ManageDeviceBatchRoomActivity.this.k.d = false;
                            ManageDeviceBatchRoomActivity.this.k.g = false;
                        }
                    }
                }
            });
        }
    }

    private void h() {
        if (!NetworkUtils.c()) {
            ToastUtil.a((int) R.string.popup_select_loc_no_network);
            this.j = false;
            return;
        }
        if (this.d == null || !this.d.isShowing()) {
            this.d = new XQProgressDialog(this);
            this.d.setCancelable(true);
            this.d.setMessage(getResources().getString(R.string.loading_share_info));
            this.d.show();
            this.d.setOnDismissListener($$Lambda$ManageDeviceBatchRoomActivity$MByulnAsjQLjyvyi_CopM8olEeg.INSTANCE);
        }
        this.k.d();
    }

    /* access modifiers changed from: private */
    public String i() {
        Object selected = this.g.getSelected();
        if (selected == null || !(selected instanceof String)) {
            return null;
        }
        return (String) selected;
    }

    /* access modifiers changed from: private */
    public Room j() {
        Object selected = this.g.getSelected();
        if (selected == null || !(selected instanceof Room)) {
            return null;
        }
        return (Room) selected;
    }

    /* access modifiers changed from: private */
    public void k() {
        if (this.d != null) {
            this.d.dismiss();
        }
        ToastUtil.a((int) R.string.save_fail);
    }

    private static void a(CommonActivity commonActivity) {
        ArrayList<Activity> arrayList = new ArrayList<>();
        Activity activity = null;
        try {
            for (Map.Entry next : SHApplication.sActivityStack.entrySet()) {
                if (next != null) {
                    WeakReference weakReference = (WeakReference) next.getValue();
                    if (weakReference != null) {
                        Activity activity2 = (Activity) weakReference.get();
                        if (activity2 != null) {
                            arrayList.add(activity2);
                            if (activity == null) {
                                activity = activity2;
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (activity == null) {
            if (commonActivity != null && commonActivity.isValid()) {
                commonActivity.finish();
            }
        } else if (activity instanceof SmartHomeMainActivity) {
            for (Activity activity3 : arrayList) {
                if (!(activity3 instanceof SmartHomeMainActivity) && !(activity3 instanceof PluginHostActivity)) {
                    activity3.finish();
                }
            }
        } else if (commonActivity != null && commonActivity.isValid()) {
            commonActivity.finish();
        }
    }

    public class SaveLogicModule {
        private static final String j = "MDRActivity";
        private static final int l = 1000;
        private static final int m = 1;

        /* renamed from: a  reason: collision with root package name */
        public volatile boolean f18128a = false;
        public volatile boolean b = false;
        public volatile boolean c = false;
        public volatile boolean d = false;
        public volatile boolean e = false;
        public volatile boolean f = false;
        public volatile boolean g = false;
        public volatile boolean h = false;
        /* access modifiers changed from: private */
        public AtomicInteger k = new AtomicInteger(0);
        /* access modifiers changed from: private */
        public Handler n = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    SaveLogicModule.this.c();
                }
            }
        };

        public SaveLogicModule() {
        }

        public void a() {
            this.n.removeCallbacksAndMessages((Object) null);
        }

        public void b() {
            this.f18128a = false;
            this.b = false;
            this.c = false;
            this.d = false;
            this.e = false;
            this.f = false;
            this.g = false;
            this.h = false;
        }

        /* access modifiers changed from: private */
        public void c() {
            LogUtilGrey.a(j, "checkAllFinished in mPendingReq=" + this.k.get());
            LogUtilGrey.a(j, "checkAllFinished in mDeviceNameNeedSave=" + this.f18128a + ",mDeviceNameSaved=" + this.b + "; mDeviceRoomNeedSave=" + this.c + ",mDeviceRoomSaved; mNeedCreateRoom=" + this.f + ",mRoomSaved=" + this.g + "; mIsRemovedFromUninitedList=" + this.h);
            if (this.k.get() <= 0) {
                if ((this.f18128a && !this.b) || ((this.c && !this.d) || (this.f && !this.g))) {
                    boolean unused = ManageDeviceBatchRoomActivity.this.j = false;
                    ManageDeviceBatchRoomActivity.this.k();
                } else if (!this.h) {
                    e();
                } else {
                    ManageDeviceBatchRoomActivity.this.f();
                }
            }
        }

        /* access modifiers changed from: private */
        public void d() {
            if (this.f18128a) {
                f();
            }
            if (this.f) {
                g();
            } else if (this.c) {
                h();
            }
            LogUtilGrey.a(j, "startSavingCurrentState end mPendingReq=" + this.k.get());
            c();
        }

        private void e() {
            DeviceInitChecker.a(ManageDeviceBatchRoomActivity.this.c, new AsyncCallback() {
                public void onSuccess(Object obj) {
                    if (ManageDeviceBatchRoomActivity.this.isValid()) {
                        SaveLogicModule.this.h = true;
                        SaveLogicModule.this.n.removeMessages(1);
                        SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                    }
                }

                public void onFailure(Error error) {
                    if (ManageDeviceBatchRoomActivity.this.isValid()) {
                        ManageDeviceBatchRoomActivity.this.k();
                    }
                }
            });
        }

        private void f() {
            final String access$1300 = ManageDeviceBatchRoomActivity.this.l;
            if (!TextUtils.isEmpty(access$1300)) {
                this.k.incrementAndGet();
                DeviceRenderer.a(ManageDeviceBatchRoomActivity.this, ManageDeviceBatchRoomActivity.this.e, access$1300, (XQProgressDialog) null, new AsyncCallback() {
                    public void onSuccess(Object obj) {
                        if (ManageDeviceBatchRoomActivity.this.isValid()) {
                            String unused = ManageDeviceBatchRoomActivity.this.l = access$1300;
                            SaveLogicModule.this.k.decrementAndGet();
                            SaveLogicModule.this.f18128a = false;
                            SaveLogicModule.this.b = true;
                            SaveLogicModule.this.n.removeMessages(1);
                            SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                        }
                    }

                    public void onFailure(Error error) {
                        if (ManageDeviceBatchRoomActivity.this.isValid()) {
                            SaveLogicModule.this.k.decrementAndGet();
                            SaveLogicModule.this.n.removeMessages(1);
                            SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                        }
                    }
                }, true);
            }
        }

        private void g() {
            String access$1500 = ManageDeviceBatchRoomActivity.this.i();
            if (!TextUtils.isEmpty(access$1500)) {
                this.k.incrementAndGet();
                HomeManager.a().a(access$1500, (List<String>) ManageDeviceBatchRoomActivity.this.c, (String) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                    public void a() {
                        if (ManageDeviceBatchRoomActivity.this.isValid()) {
                            SaveLogicModule.this.k.decrementAndGet();
                            SaveLogicModule.this.c = false;
                            SaveLogicModule.this.d = true;
                            SaveLogicModule.this.f = false;
                            SaveLogicModule.this.g = true;
                            SaveLogicModule.this.n.removeMessages(1);
                            SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                        }
                    }

                    public void a(int i, Error error) {
                        if (ManageDeviceBatchRoomActivity.this.isValid()) {
                            SaveLogicModule.this.k.decrementAndGet();
                            SaveLogicModule.this.n.removeMessages(1);
                            SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                        }
                    }
                });
            }
        }

        private void h() {
            Room access$1600 = ManageDeviceBatchRoomActivity.this.j();
            this.k.incrementAndGet();
            if (access$1600 == null || TextUtils.isEmpty(access$1600.d())) {
                HomeManager.a().a(HomeManager.a().m(), (Room) null, (List<String>) ManageDeviceBatchRoomActivity.this.c, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                    public void a() {
                        if (ManageDeviceBatchRoomActivity.this.isValid()) {
                            SaveLogicModule.this.k.decrementAndGet();
                            SaveLogicModule.this.c = false;
                            SaveLogicModule.this.d = true;
                            SaveLogicModule.this.n.removeMessages(1);
                            SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                            HomeManager.a().a((String) null, HomeManager.a().e());
                            HomeManager.a().a((List<String>) ManageDeviceBatchRoomActivity.this.c, true);
                        }
                    }

                    public void a(int i, Error error) {
                        if (ManageDeviceBatchRoomActivity.this.isValid()) {
                            SaveLogicModule.this.k.decrementAndGet();
                            SaveLogicModule.this.n.removeMessages(1);
                            SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                        }
                    }
                });
                return;
            }
            HomeManager.a().a(access$1600, (List<String>) ManageDeviceBatchRoomActivity.this.c, (List<String>) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                public void a() {
                    if (ManageDeviceBatchRoomActivity.this.isValid()) {
                        SaveLogicModule.this.k.decrementAndGet();
                        SaveLogicModule.this.c = false;
                        SaveLogicModule.this.d = true;
                        SaveLogicModule.this.n.removeMessages(1);
                        SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                        HomeManager.a().a((String) null, HomeManager.a().e());
                        HomeManager.a().a((List<String>) ManageDeviceBatchRoomActivity.this.c, true);
                    }
                }

                public void a(int i, Error error) {
                    if (ManageDeviceBatchRoomActivity.this.isValid()) {
                        SaveLogicModule.this.k.decrementAndGet();
                        SaveLogicModule.this.n.removeMessages(1);
                        SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                    }
                }
            });
        }
    }
}
