package com.xiaomi.smarthome.homeroom;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alipay.sdk.util.i;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.device.utils.DeviceShortcutUtils;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity;
import com.xiaomi.smarthome.frame.plugin.runtime.util.TitleBarUtil;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.homeroom.view.AddRoomFlowGroup;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.light.group.LightGroupManager;
import com.xiaomi.smarthome.newui.MoveRoomDialogHelper;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.stat.StatClick;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ManageDeviceRoomActivity extends BaseActivity {
    public static final String INTENT_KEY_DID = "device_id";
    public static final String INTENT_KEY_MAC = "device_mac";

    /* renamed from: a  reason: collision with root package name */
    private static final String f18135a = "ManageDeviceRoomActivity";
    private static final int p = 1;
    /* access modifiers changed from: private */
    public AddRoomFlowGroup b;
    private List<String> c;
    private SimpleDraweeView d;
    /* access modifiers changed from: private */
    public TextView e;
    private TextView f;
    private TextView g;
    private View h;
    /* access modifiers changed from: private */
    public boolean i = true;
    private View j;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public Device l;
    /* access modifiers changed from: private */
    public String m;
    SmartHomeDeviceManager.IClientDeviceListener mDeviceLoadingListener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void a(int i) {
            if (3 == i) {
                Device unused = ManageDeviceRoomActivity.this.l = SmartHomeDeviceManager.a().b(ManageDeviceRoomActivity.this.m);
                if (ManageDeviceRoomActivity.this.l != null) {
                    ManageDeviceRoomActivity.this.a();
                    if (ManageDeviceRoomActivity.this.q != null) {
                        ManageDeviceRoomActivity.this.q.dismiss();
                        return;
                    }
                    return;
                }
                ManageDeviceRoomActivity.this.finish();
            }
        }

        public void b(int i) {
            ManageDeviceRoomActivity.this.finish();
        }
    };
    /* access modifiers changed from: private */
    public String n;
    private Device o;
    /* access modifiers changed from: private */
    public XQProgressDialog q;
    /* access modifiers changed from: private */
    public SaveLogicModule r;
    private boolean s = false;
    /* access modifiers changed from: private */
    public boolean t = false;
    /* access modifiers changed from: private */
    public boolean u = true;

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.m = intent.getStringExtra("device_id");
        if (!TextUtils.isEmpty(this.m)) {
            this.l = SmartHomeDeviceManager.a().b(this.m);
        }
        if (this.l == null) {
            String stringExtra = intent.getStringExtra("device_mac");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.l = SmartHomeDeviceManager.a().f(stringExtra);
            }
        }
        if (!SmartHomeDeviceManager.a().u() || this.l != null) {
            setContentView(R.layout.activity_manage_device_room);
            TitleBarUtil.setTitleBar(this);
            if (!SmartHomeDeviceManager.a().u()) {
                b();
                return;
            }
            a();
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        SmartHomeDeviceManager.a().p();
                    }
                }
            }, 1000);
            return;
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void a() {
        this.n = this.l.name;
        this.o = DeviceInitChecker.a(this.m);
        this.r = new SaveLogicModule();
        c();
        j();
    }

    private void b() {
        this.q = new XQProgressDialog(this);
        this.q.setCancelable(true);
        this.q.setMessage(getResources().getString(R.string.loading));
        this.q.show();
        this.q.a((XQProgressDialog.OnProgressCancelListener) new XQProgressDialog.OnProgressCancelListener() {
            public void onCancel() {
                ManageDeviceRoomActivity.this.finish();
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
        if (this.l != null) {
            STAT.c.b(0, this.l.model);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.l != null) {
            STAT.c.b(this.mEnterTime, this.l.model);
        }
    }

    private void c() {
        this.b = (AddRoomFlowGroup) findViewById(R.id.tag_choose);
        this.d = (SimpleDraweeView) findViewById(R.id.device_icon);
        this.e = (TextView) findViewById(R.id.subtitle);
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceRenderer.a((Context) ManageDeviceRoomActivity.this, ManageDeviceRoomActivity.this.l, (ClientRemarkInputView.RenameInterface) new ClientRemarkInputView.RenameInterface() {
                    public void modifyBackName(String str) {
                        ManageDeviceRoomActivity.this.r.f18162a = true;
                        ManageDeviceRoomActivity.this.r.b = false;
                        ManageDeviceRoomActivity.this.e.setText(str);
                        ManageDeviceRoomActivity.this.l.name = str;
                    }
                }, new boolean[0]);
            }
        });
        findViewById(R.id.device_name_container).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceRenderer.a((Context) ManageDeviceRoomActivity.this, ManageDeviceRoomActivity.this.l, (ClientRemarkInputView.RenameInterface) new ClientRemarkInputView.RenameInterface() {
                    public void modifyBackName(String str) {
                        ManageDeviceRoomActivity.this.r.f18162a = true;
                        ManageDeviceRoomActivity.this.r.b = false;
                        ManageDeviceRoomActivity.this.e.setText(str);
                        ManageDeviceRoomActivity.this.l.name = str;
                    }
                }, new boolean[0]);
            }
        });
        findViewById(R.id.title).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceRenderer.a((Context) ManageDeviceRoomActivity.this, ManageDeviceRoomActivity.this.l, (ClientRemarkInputView.RenameInterface) new ClientRemarkInputView.RenameInterface() {
                    public void modifyBackName(String str) {
                        ManageDeviceRoomActivity.this.r.f18162a = true;
                        ManageDeviceRoomActivity.this.r.b = false;
                        ManageDeviceRoomActivity.this.e.setText(str);
                        ManageDeviceRoomActivity.this.l.name = str;
                    }
                }, new boolean[0]);
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.title_bar_return);
        imageView.setImageResource(R.drawable.std_tittlebar_main_device_back_black);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new MLAlertDialog.Builder(ManageDeviceRoomActivity.this).a((int) R.string.save_auth_or_not).a((int) R.string.save_auth, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean unused = ManageDeviceRoomActivity.this.t = true;
                        ManageDeviceRoomActivity.this.k();
                    }
                }).b((int) R.string.not_save_auth, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (LightGroupManager.a().b(ManageDeviceRoomActivity.this.l)) {
                            boolean unused = ManageDeviceRoomActivity.this.t = true;
                            ManageDeviceRoomActivity.this.k();
                            return;
                        }
                        ManageDeviceRoomActivity.this.l.name = ManageDeviceRoomActivity.this.n;
                        ManageDeviceRoomActivity.this.finish();
                    }
                }).d();
            }
        });
        this.h = findViewById(R.id.add_to_common_use);
        this.h.setVisibility(8);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = ManageDeviceRoomActivity.this.i = !ManageDeviceRoomActivity.this.i;
                ManageDeviceRoomActivity.this.i();
            }
        });
        ((TextView) this.h.findViewById(R.id.title)).setText(R.string.device_add_to_commonuse);
        this.j = findViewById(R.id.add_to_short_cut);
        this.j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = ManageDeviceRoomActivity.this.k = !ManageDeviceRoomActivity.this.k;
                ManageDeviceRoomActivity.this.i();
            }
        });
        ((TextView) this.j.findViewById(R.id.title)).setText(R.string.device_add_to_shortcut);
        this.f = (TextView) findViewById(R.id.init_next);
        this.g = (TextView) findViewById(R.id.open_device);
        d();
    }

    private void d() {
        if (this.l != null) {
            this.e.setText(this.l.getName());
            DeviceFactory.b(this.l.model, this.d);
            i();
            e();
        }
    }

    private void e() {
        if (this.o == null) {
            this.f.setVisibility(8);
            this.g.setBackgroundResource(R.drawable.common_button_v2);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.g.getLayoutParams();
            layoutParams.leftMargin = getResources().getDimensionPixelSize(R.dimen.common_button_margin);
            this.g.setLayoutParams(layoutParams);
            this.g.getParent().requestLayout();
        } else {
            this.f.setVisibility(0);
            this.g.setBackgroundResource(R.drawable.common_button_right_v2);
            this.f.setBackgroundResource(R.drawable.common_button_left_v2);
            this.g.getParent().requestLayout();
        }
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = ManageDeviceRoomActivity.this.u = true;
                ManageDeviceRoomActivity.this.k();
            }
        });
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = ManageDeviceRoomActivity.this.u = false;
                ManageDeviceRoomActivity.this.k();
                Room access$1300 = ManageDeviceRoomActivity.this.n();
                StatClick statClick = STAT.d;
                String charSequence = ManageDeviceRoomActivity.this.e.getText().toString();
                String e = access$1300 == null ? "null" : access$1300.e();
                statClick.b(charSequence, e, (ManageDeviceRoomActivity.this.i ? 1 : 0) + i.b + (ManageDeviceRoomActivity.this.k ? 1 : 0));
            }
        });
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.t) {
            this.l.name = this.n;
            finish();
            if (LightGroupManager.a().b(this.l)) {
                overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
                Intent intent = new Intent(getApplicationContext(), SmartHomeMainActivity.class);
                intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                startActivity(intent);
                return;
            }
            return;
        }
        if (this.q != null) {
            this.q.dismiss();
        }
        if (this.o != null && this.u) {
            this.r.b();
            this.l = this.o;
            this.n = this.l.name;
            this.o = DeviceInitChecker.a(this.l.did);
            d();
            this.t = false;
        } else if (LightGroupManager.a().b(this.l)) {
            finish();
            overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
            Intent intent2 = new Intent(getApplicationContext(), SmartHomeMainActivity.class);
            intent2.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
            startActivity(intent2);
        } else {
            g();
        }
    }

    public void onBackPressed() {
        findViewById(R.id.title_bar_return).performClick();
    }

    private void g() {
        final boolean z;
        if (this.q != null && this.q.isShowing()) {
            this.q.dismiss();
        }
        PluginRecord d2 = CoreApi.a().d(this.l.model);
        if (d2 != null) {
            final XQProgressHorizontalDialog b2 = XQProgressHorizontalDialog.b(this, getResources().getString(R.string.plugin_downloading) + d2.p());
            final PluginDownloadTask pluginDownloadTask = new PluginDownloadTask();
            final XQProgressDialog xQProgressDialog = new XQProgressDialog(this);
            xQProgressDialog.setCancelable(true);
            xQProgressDialog.setMessage(getResources().getString(R.string.loading_share_info));
            xQProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                }
            });
            if (d2.l() || d2.k()) {
                xQProgressDialog.show();
                z = false;
            } else {
                z = true;
            }
            final PluginRecord pluginRecord = d2;
            PluginApi.getInstance().sendMessage(this, d2, 1, new Intent(), this.l.newDeviceStat(), (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
                private static final float j = 85.0f;

                /* renamed from: a  reason: collision with root package name */
                ValueAnimator f18140a;
                private long h;
                private final Interpolator i = new DecelerateInterpolator();

                public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        LogUtil.a("ManageDeviceRoomActivity", "onDownloadStart");
                        pluginDownloadTask.a(pluginDownloadTask);
                        if (b2 != null) {
                            b2.a(true);
                            b2.c();
                            b2.setCancelable(true);
                            b2.show();
                            b2.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                public void onCancel(DialogInterface dialogInterface) {
                                    CoreApi.a().a(pluginRecord.o(), pluginDownloadTask, (CoreApi.CancelPluginDownloadCallback) null);
                                }
                            });
                        }
                    }
                }

                /* access modifiers changed from: package-private */
                public float a() {
                    if (this.f18140a == null) {
                        synchronized (this) {
                            double min = (double) Math.min(1.0f, ((float) (System.currentTimeMillis() - this.h)) / 4000.0f);
                            Double.isNaN(min);
                            this.f18140a = ValueAnimator.ofFloat(new float[]{(float) ((min * 0.14d) + 0.85d), 0.99f});
                            this.f18140a.setDuration(4000);
                            this.f18140a.setInterpolator(this.i);
                            this.f18140a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    if (b2 != null) {
                                        b2.a(100, (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 100.0f));
                                    }
                                }
                            });
                            this.f18140a.start();
                        }
                    }
                    return ((Float) this.f18140a.getAnimatedValue()).floatValue();
                }

                public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        if (z) {
                            int i2 = (int) (f2 * 100.0f);
                            if (i2 >= 99) {
                                if (this.h == 0) {
                                    this.h = System.currentTimeMillis();
                                }
                                i2 = 99;
                            }
                            if (i2 == 99) {
                                a();
                            } else if (b2 != null) {
                                XQProgressHorizontalDialog xQProgressHorizontalDialog = b2;
                                double d2 = (double) i2;
                                Double.isNaN(d2);
                                xQProgressHorizontalDialog.a(100, (int) (d2 * 0.85d));
                            }
                        } else if (b2 != null) {
                            b2.a(100, (int) (f2 * 100.0f));
                        }
                    }
                }

                public void onDownloadSuccess(PluginRecord pluginRecord) {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        LogUtil.a("ManageDeviceRoomActivity", "onDownloadSuccess");
                        if (!z && b2 != null) {
                            b2.dismiss();
                        }
                    }
                }

                public void onDownloadFailure(PluginError pluginError) {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        LogUtil.a("ManageDeviceRoomActivity", "onDownloadFailure");
                        if (!z && b2 != null) {
                            b2.dismiss();
                        }
                    }
                }

                public void onDownloadCancel() {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        LogUtil.a("ManageDeviceRoomActivity", "onDownloadCancel");
                        if (!z && b2 != null) {
                            b2.dismiss();
                        }
                    }
                }

                public void onSendSuccess(Bundle bundle) {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        LogUtil.a("ManageDeviceRoomActivity", "onSendSuccess");
                        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                            public void run() {
                                xQProgressDialog.dismiss();
                                if (b2 != null) {
                                    b2.dismiss();
                                }
                                ManageDeviceRoomActivity.a(ManageDeviceRoomActivity.this);
                            }
                        }, 100);
                    }
                }

                public void onSendFailure(Error error) {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        LogUtil.a("ManageDeviceRoomActivity", "onSendFailure");
                        if (z && b2 != null) {
                            b2.dismiss();
                        }
                    }
                }

                public void onSendCancel() {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        LogUtil.a("ManageDeviceRoomActivity", "onSendCancel");
                        if (z && b2 != null) {
                            b2.dismiss();
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.q != null) {
            this.q.dismiss();
        }
        ToastUtil.a((int) R.string.save_fail);
    }

    /* access modifiers changed from: private */
    public void i() {
        ((CheckBox) this.h.findViewById(R.id.ratio_btn)).setChecked(this.i);
        ((CheckBox) this.j.findViewById(R.id.ratio_btn)).setChecked(this.k);
    }

    private void j() {
        if (this.l != null) {
            this.c = SmartHomeDeviceHelper.a().b().c();
            Room p2 = HomeManager.a().p(this.l.did);
            this.b.setCurrentSelect(1, p2 == null ? null : p2.d());
            this.b.setOnTagClickListener(new AddRoomFlowGroup.TagClickListener() {
                public void a() {
                    Intent intent = new Intent(ManageDeviceRoomActivity.this.getContext(), PickRoomActivity.class);
                    intent.putExtra("home_id", HomeManager.a().l());
                    ManageDeviceRoomActivity.this.startActivityForResult(intent, 1);
                }

                public void b() {
                    MoveRoomDialogHelper.a((Context) ManageDeviceRoomActivity.this, (List<String>) null, (MoveRoomDialogHelper.addRoomListener) new MoveRoomDialogHelper.addRoomListener() {
                        public void onSuccess(String str) {
                            ManageDeviceRoomActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ManageDeviceRoomActivity.this.b.setCurrentSelect(ManageDeviceRoomActivity.this.b.getCurrentSelectType(), ManageDeviceRoomActivity.this.b.getCurrentSelectValue());
                                }
                            });
                        }
                    });
                }

                public void a(Object obj) {
                    if (obj != null) {
                        if (obj instanceof String) {
                            ManageDeviceRoomActivity.this.r.c = true;
                            ManageDeviceRoomActivity.this.r.f = true;
                            ManageDeviceRoomActivity.this.r.g = false;
                            ManageDeviceRoomActivity.this.r.d = false;
                        } else if (obj instanceof Room) {
                            ManageDeviceRoomActivity.this.r.c = true;
                            ManageDeviceRoomActivity.this.r.f = false;
                            ManageDeviceRoomActivity.this.r.d = false;
                            ManageDeviceRoomActivity.this.r.g = false;
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        Bundle extras;
        if (i3 == -1 && (extras = intent.getExtras()) != null && i2 == 1) {
            Object obj = extras.get("result");
            if (obj instanceof Room) {
                this.b.setCurrentSelect(1, ((Room) obj).d());
            } else if (obj instanceof String) {
                this.b.setCurrentSelect(2, (String) obj);
            }
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        if (!NetworkUtils.c()) {
            ToastUtil.a((int) R.string.popup_select_loc_no_network);
            this.t = false;
            return;
        }
        if (this.q == null || !this.q.isShowing()) {
            this.q = new XQProgressDialog(this);
            this.q.setCancelable(true);
            this.q.setMessage(getResources().getString(R.string.loading_share_info));
            this.q.show();
            this.q.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                }
            });
        }
        if (!this.s && ((CheckBox) this.j.findViewById(R.id.ratio_btn)).isChecked()) {
            this.s = true;
            DeviceShortcutUtils.a(false, this.l, (Intent) null, (String) null, (AsyncResponseCallback<Void>) new AsyncResponseCallback<Void>() {
                public void a(int i, Object obj) {
                }

                public void a(Void voidR) {
                }

                public void a(int i) {
                    if (i != -1) {
                        return;
                    }
                    if (Build.VERSION.SDK_INT < 23 || !ManageDeviceRoomActivity.this.shouldShowRequestPermissionRationale("com.android.launcher.permission.INSTALL_SHORTCUT")) {
                        ToastUtil.a((int) R.string.permission_tips_denied_msg);
                        return;
                    }
                    ManageDeviceRoomActivity.this.requestPermissions(new String[]{"com.android.launcher.permission.INSTALL_SHORTCUT"}, 1);
                }
            });
        }
        this.r.d();
    }

    /* access modifiers changed from: private */
    public String l() {
        return String.valueOf(this.e.getText());
    }

    /* access modifiers changed from: private */
    public String m() {
        Object selected = this.b.getSelected();
        if (selected == null || !(selected instanceof String)) {
            return null;
        }
        return (String) selected;
    }

    /* access modifiers changed from: private */
    public Room n() {
        Object selected = this.b.getSelected();
        if (selected == null || !(selected instanceof Room)) {
            return null;
        }
        return (Room) selected;
    }

    /* access modifiers changed from: private */
    public static void a(CommonActivity commonActivity) {
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
        public volatile boolean f18162a = false;
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
            this.f18162a = false;
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
            LogUtilGrey.a(j, "checkAllFinished in mDeviceNameNeedSave=" + this.f18162a + ",mDeviceNameSaved=" + this.b + "; mDeviceRoomNeedSave=" + this.c + ",mDeviceRoomSaved=" + this.d + "; mNeedCreateRoom=" + this.f + ",mRoomSaved=" + this.g + "; mIsRemovedFromUninitedList=" + this.h);
            if (this.k.get() <= 0) {
                if ((this.f18162a && !this.b) || ((this.c && !this.d) || (this.f && !this.g))) {
                    boolean unused = ManageDeviceRoomActivity.this.t = false;
                    ManageDeviceRoomActivity.this.h();
                } else if (!this.h) {
                    e();
                } else {
                    ManageDeviceRoomActivity.this.f();
                }
            }
        }

        /* access modifiers changed from: private */
        public void d() {
            if (this.f18162a) {
                f();
            }
            if (this.f) {
                g();
            } else if (this.c) {
                i();
            } else {
                h();
            }
            LogUtil.a(j, "startSavingCurrentState end mPendingReq=" + this.k.get());
            c();
        }

        private void e() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(ManageDeviceRoomActivity.this.l.did);
            DeviceInitChecker.a(arrayList, new AsyncCallback() {
                public void onSuccess(Object obj) {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        SaveLogicModule.this.h = true;
                        SaveLogicModule.this.n.removeMessages(1);
                        SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                    }
                }

                public void onFailure(Error error) {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        ManageDeviceRoomActivity.this.h();
                    }
                }
            });
        }

        private void f() {
            final String access$2100 = ManageDeviceRoomActivity.this.l();
            LogUtilGrey.a(j, "doSaveDeviceName in " + access$2100);
            if (!TextUtils.isEmpty(access$2100)) {
                this.k.incrementAndGet();
                DeviceRenderer.a(ManageDeviceRoomActivity.this, ManageDeviceRoomActivity.this.l, access$2100, (XQProgressDialog) null, new AsyncCallback() {
                    public void onSuccess(Object obj) {
                        if (ManageDeviceRoomActivity.this.isValid()) {
                            LogUtilGrey.a(SaveLogicModule.j, "doSaveDeviceName onSuccess ");
                            String unused = ManageDeviceRoomActivity.this.n = access$2100;
                            SaveLogicModule.this.k.decrementAndGet();
                            SaveLogicModule.this.f18162a = false;
                            SaveLogicModule.this.b = true;
                            SaveLogicModule.this.n.removeMessages(1);
                            SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                        }
                    }

                    public void onFailure(Error error) {
                        if (ManageDeviceRoomActivity.this.isValid()) {
                            LogUtilGrey.a(SaveLogicModule.j, "doSaveDeviceName onFailure ");
                            SaveLogicModule.this.k.decrementAndGet();
                            SaveLogicModule.this.n.removeMessages(1);
                            SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                        }
                    }
                }, true);
            }
        }

        private void g() {
            String access$2300 = ManageDeviceRoomActivity.this.m();
            LogUtilGrey.a(j, "doSaveRoom in " + access$2300);
            if (!TextUtils.isEmpty(access$2300)) {
                this.k.incrementAndGet();
                ArrayList arrayList = new ArrayList();
                arrayList.add(ManageDeviceRoomActivity.this.l.did);
                HomeManager.a().a(access$2300, (List<String>) arrayList, (String) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                    public void a() {
                        if (ManageDeviceRoomActivity.this.isValid()) {
                            LogUtilGrey.a(SaveLogicModule.j, "doSaveRoom onSuccess");
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
                        if (ManageDeviceRoomActivity.this.isValid()) {
                            LogUtilGrey.a(SaveLogicModule.j, "doSaveRoom onFail " + i + "," + error);
                            SaveLogicModule.this.k.decrementAndGet();
                            SaveLogicModule.this.n.removeMessages(1);
                            SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                        }
                    }
                });
            }
        }

        private void h() {
            this.k.incrementAndGet();
            LogUtilGrey.a(j, "doSaveDeviceToCurrentHomeDefaultRoom in");
            HomeManager.a().a(HomeManager.a().m(), (Room) null, ManageDeviceRoomActivity.this.l, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                public void a() {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        LogUtilGrey.a(SaveLogicModule.j, "doSaveDeviceToCurrentHomeDefaultRoom onSuccess");
                        SaveLogicModule.this.k.decrementAndGet();
                        SaveLogicModule.this.c = false;
                        SaveLogicModule.this.d = true;
                        SaveLogicModule.this.n.removeMessages(1);
                        SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                    }
                }

                public void a(int i, Error error) {
                    if (ManageDeviceRoomActivity.this.isValid()) {
                        LogUtilGrey.a(SaveLogicModule.j, "doSaveDeviceToCurrentHomeDefaultRoom onFail " + i + "," + error);
                        SaveLogicModule.this.k.decrementAndGet();
                        SaveLogicModule.this.n.removeMessages(1);
                        SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                    }
                }
            });
        }

        private void i() {
            Room access$1300 = ManageDeviceRoomActivity.this.n();
            LogUtilGrey.a(j, "doSaveDeviceToRoom in " + access$1300);
            if (access$1300 != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(ManageDeviceRoomActivity.this.l.did);
                if (TextUtils.isEmpty(access$1300.d())) {
                    h();
                    return;
                }
                this.k.incrementAndGet();
                HomeManager.a().a(access$1300, (List<String>) arrayList, (List<String>) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                    public void a() {
                        if (ManageDeviceRoomActivity.this.isValid()) {
                            LogUtilGrey.a(SaveLogicModule.j, "doSaveDeviceToRoom onSuccess");
                            SaveLogicModule.this.k.decrementAndGet();
                            SaveLogicModule.this.c = false;
                            SaveLogicModule.this.d = true;
                            SaveLogicModule.this.n.removeMessages(1);
                            SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                        }
                    }

                    public void a(int i, Error error) {
                        if (ManageDeviceRoomActivity.this.isValid()) {
                            LogUtilGrey.a(SaveLogicModule.j, "doSaveDeviceToRoom onFail " + i + "," + error);
                            SaveLogicModule.this.k.decrementAndGet();
                            SaveLogicModule.this.n.removeMessages(1);
                            SaveLogicModule.this.n.sendEmptyMessageDelayed(1, 1000);
                        }
                    }
                });
            }
        }

        private void a(boolean z) {
            List<String> C = HomeManager.a().C();
            ArrayList arrayList = C == null ? new ArrayList() : new ArrayList(C);
            if (z) {
                arrayList.remove(ManageDeviceRoomActivity.this.l.did);
                arrayList.add(0, ManageDeviceRoomActivity.this.l.did);
                CommonUseDeviceDataManager.a().a((List<String>) arrayList);
                boolean access$900 = ManageDeviceRoomActivity.this.i;
            } else if (arrayList.remove(ManageDeviceRoomActivity.this.l.did)) {
                CommonUseDeviceDataManager.a().a((List<String>) arrayList);
            }
        }
    }
}
