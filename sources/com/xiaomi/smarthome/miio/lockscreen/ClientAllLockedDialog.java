package com.xiaomi.smarthome.miio.lockscreen;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.RouterDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.device.utils.BleManager;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.device.utils.LockedDeviceViewManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.ui.LoginPwdActivity;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.AnimateFakeList;
import com.xiaomi.smarthome.library.common.widget.AnimateLinearLayout;
import com.xiaomi.smarthome.miio.activity.ClientAllLockedActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class ClientAllLockedDialog extends Dialog {
    public static boolean m = false;
    public static int n;
    static int r;
    public static int s;

    /* renamed from: a  reason: collision with root package name */
    final Handler f13596a;
    final Context b;
    final ClientAllLockedActivity c;
    final SmartDeviceListManager d;
    AnimateFakeList e;
    AnimateLinearLayout f;
    TextView g;
    ViewGroup h;
    View i;
    boolean j = false;
    protected BroadcastReceiver k = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_OFF") && ClientAllLockedDialog.this.c != null) {
                try {
                    ClientAllLockedDialog.this.c.finish();
                } catch (Throwable unused) {
                }
            }
        }
    };
    Runnable l = new Runnable() {
        public void run() {
            ClientAllLockedDialog.this.l();
        }
    };
    SmartHomeDeviceManager.IClientDeviceListener o = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            if (i == 3) {
                if (!ClientAllLockedDialog.m || ClientAllLockedDialog.n <= 0) {
                    if (ClientAllLockedDialog.n > 0) {
                        ClientAllLockedDialog.m = true;
                    } else {
                        ClientAllLockedDialog.n = SmartHomeDeviceHelper.a().m().size();
                    }
                    ClientAllLockedDialog.this.l.run();
                }
            }
        }
    };
    public View.OnClickListener p = new View.OnClickListener() {
        public void onClick(View view) {
            if (CoreApi.a().D()) {
                ClientAllLockedDialog.this.c();
            } else {
                ClientAllLockedDialog.this.d();
            }
        }
    };
    LoadLockedDeviceTask q = null;
    protected Runnable t = new Runnable() {
        public void run() {
            ClientAllLockedDialog.this.d.a(true);
        }
    };

    public ClientAllLockedDialog(ClientAllLockedActivity clientAllLockedActivity) {
        super(clientAllLockedActivity, 16973840);
        setCancelable(false);
        DisplayUtils.a(getWindow());
        this.c = clientAllLockedActivity;
        this.b = this.c;
        this.f13596a = new Handler();
        this.d = new SmartDeviceListManager();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.addFlags(4);
        window.addFlags(524288);
        w();
        setContentView(R.layout.client_all_locked);
        b();
        SmartHomeDeviceManager.a().a(this.o);
        this.c.registerReceiver(this.k, new IntentFilter("android.intent.action.SCREEN_OFF"));
    }

    private void w() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setNavigationBarColor(a());
        }
    }

    /* access modifiers changed from: protected */
    public int a() {
        return getContext().getResources().getColor(R.color.transparent);
    }

    public void b() {
        this.e = (AnimateFakeList) findViewById(R.id.device_grid_view);
        this.f = (AnimateLinearLayout) findViewById(R.id.layout_root);
        this.f.setMoveOutAnimListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                ClientAllLockedDialog.this.g();
            }
        });
        this.f.setFlingLeftListener(new AnimateLinearLayout.FlingLeftListener() {
            public void a() {
                ClientAllLockedDialog.this.e();
            }
        });
        this.h = (ViewGroup) this.f.findViewById(R.id.rl_head);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientAllLockedDialog.this.c();
            }
        });
        this.h.setVisibility(0);
        this.g = (TextView) this.f.findViewById(R.id.txt_device_num);
        int w = SmartHomeDeviceManager.a().w();
        this.g.setText(getContext().getResources().getQuantityString(R.plurals.setting_total_device, w, new Object[]{Integer.valueOf(w)}));
        this.f.findViewById(R.id.btn_jump).setOnClickListener(this.p);
        this.i = this.f.findViewById(R.id.divider_lock_header);
        IRDeviceUtil.c();
        this.d.a((View) this.f, this);
    }

    private boolean x() {
        if (CoreApi.a().q() && this.d.b() <= 0) {
            return true;
        }
        return false;
    }

    public void c() {
        Intent intent = new Intent();
        intent.setData(Uri.parse("https://home.mi.com/main"));
        intent.setPackage(getContext().getPackageName());
        this.c.startActivity(intent);
        this.c.finish();
        DisplayUtils.a(getContext(), 17432576, 17432577);
        OpenApi.a(getContext());
    }

    public void d() {
        OpenApi.a();
        this.c.finish();
        DisplayUtils.a((Context) this.c, 17432576, 17432577);
        OpenApi.a((Context) this.c);
    }

    public static void a(Activity activity) {
        OpenApi.a(LoginPwdActivity.class, (Bundle) null, false, 0);
        activity.finish();
        DisplayUtils.a((Context) activity, 17432576, 17432577);
        OpenApi.a((Context) activity);
    }

    /* access modifiers changed from: protected */
    public void e() {
        this.f.doMoveOutAnim(0, AnimateLinearLayout.calcStepDelay(this.d.b() + 0), new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                ClientAllLockedDialog.this.g();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void f() {
        r = 0;
        m = false;
        s = 0;
    }

    /* access modifiers changed from: protected */
    public void g() {
        f();
        this.f.setVisibility(4);
        getWindow().clearFlags(4);
        this.f13596a.postDelayed(new Runnable() {
            public void run() {
                if (ClientAllLockedDialog.this.c != null) {
                    try {
                        ClientAllLockedDialog.this.c.finish();
                        DisplayUtils.a((Context) ClientAllLockedDialog.this.c, 17432576, 17432577);
                    } catch (Throwable unused) {
                    }
                }
            }
        }, 400);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        k();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        i();
    }

    public void h() {
        this.f13596a.removeCallbacks(this.t);
        SmartHomeDeviceManager.a().c(this.o);
    }

    public void i() {
        h();
        try {
            this.c.unregisterReceiver(this.k);
        } catch (Throwable unused) {
        }
        BleManager.g();
        CoreApi.a().n();
        f();
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.c != null) {
            this.c.finish();
            DisplayUtils.a((Context) this.c, 17432576, 17432577);
        }
    }

    /* access modifiers changed from: package-private */
    public void j() {
        if (this.d.b() + 0 <= 0) {
            this.g.setVisibility(8);
            return;
        }
        this.g.setVisibility(0);
        int w = SmartHomeDeviceManager.a().w();
        this.g.setText(getContext().getResources().getQuantityString(R.plurals.setting_total_device, w, new Object[]{Integer.valueOf(w)}));
    }

    public void k() {
        if (IRDeviceUtil.c()) {
            AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
                /* access modifiers changed from: protected */
                public Object doInBackground(Object... objArr) {
                    try {
                        IRDeviceUtil.a(SHApplication.getAppContext());
                        return null;
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return null;
                    }
                }
            }, new Object[0]);
        }
        l();
    }

    /* access modifiers changed from: package-private */
    public void l() {
        if (this.q == null || this.q.getStatus() == AsyncTask.Status.FINISHED) {
            this.q = new LoadLockedDeviceTask();
            this.q.a(this);
            AsyncTaskUtils.a(this.q, new Integer[0]);
        }
    }

    public void m() {
        List<Device> d2 = this.d.d();
        if (d2 != null && d2.size() > 0) {
            for (Device next : d2) {
                if (next.model != null && next.model.equals("ge.light.mono1")) {
                    LockedDeviceViewManager.a((MiioDeviceV2) next, (MiioDeviceV2.DeviceCallback) new MiioDeviceV2.DeviceCallback() {
                        public void a(Object obj) {
                            super.a(obj);
                        }

                        public void a(MiioDeviceV2.DeviceErrorCode deviceErrorCode) {
                            super.a(deviceErrorCode);
                        }
                    });
                }
            }
        }
    }

    public void n() {
        final ArrayList arrayList = (ArrayList) this.d.c();
        if (arrayList != null && !this.j) {
            this.f13596a.postDelayed(new Runnable() {
                public void run() {
                    DevicelibApi.getDeviceExtraInfo(SHApplication.getAppContext(), arrayList, new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            Log.e("test-router", "getDeviceExtraInfo:" + jSONObject.toString());
                            try {
                                JSONArray jSONArray = jSONObject.getJSONArray("result");
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                                    Device b = SmartHomeDeviceManager.a().b(jSONObject2.getString("did"));
                                    if (b != null && (b instanceof RouterDevice)) {
                                        JSONObject jSONObject3 = jSONObject2.getJSONObject("value");
                                        ((RouterDevice) b).i = jSONObject3.getInt("online_device_count");
                                        ((RouterDevice) b).h = jSONObject3.getInt("downloading_count");
                                        ((RouterDevice) b).name = jSONObject3.getString("router_name");
                                        ((RouterDevice) b).e = jSONObject3.optInt("wanRX");
                                        b.notifyStateChanged();
                                    }
                                }
                            } catch (JSONException unused) {
                            }
                            ClientAllLockedDialog.this.j = true;
                        }

                        public void onFailure(Error error) {
                            ClientAllLockedDialog.this.j = true;
                        }
                    });
                }
            }, 700);
        }
    }

    public static boolean o() {
        return !CoreApi.a().q();
    }

    public void p() {
        this.d.a();
    }

    /* access modifiers changed from: package-private */
    public void q() {
        if (r <= 0) {
            r++;
            if (this.h.getVisibility() != 0) {
                this.h.setVisibility(0);
                this.f.doMoveInAnim(0, AnimateLinearLayout.calcStepDelay(this.d.b()));
                return;
            }
            this.e.doMoveInAnim(0, AnimateLinearLayout.calcStepDelay(this.d.b()));
        }
    }

    public void r() {
        this.f13596a.postDelayed(new Runnable() {
            public void run() {
                if (ClientAllLockedDialog.this.isShowing()) {
                    try {
                        ClientAllLockedDialog.this.j();
                        ClientAllLockedDialog.this.d.a((Activity) ClientAllLockedDialog.this.c);
                        ClientAllLockedDialog.this.d.a(ClientAllLockedDialog.this.c);
                        if (!ClientAllLockedDialog.m) {
                            ClientAllLockedDialog.this.y();
                            ClientAllLockedDialog.this.n();
                            ClientAllLockedDialog.this.m();
                        }
                        if (!SmartHomeDeviceManager.a().u() && CoreApi.a().q()) {
                            ClientAllLockedDialog.s();
                        } else if (!ClientAllLockedDialog.m) {
                            ClientAllLockedDialog.this.q();
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }, 100);
    }

    public static void s() {
        if (s <= 0) {
            r = 0;
            m = false;
            SmartHomeDeviceManager.a().p();
            s++;
        }
    }

    public static boolean t() {
        String str = "";
        AccountManager accountManager = AccountManager.get(SHApplication.getAppContext());
        if (accountManager != null) {
            Account[] accountsByType = accountManager.getAccountsByType("com.xiaomi");
            if (accountsByType.length > 0) {
                str = accountsByType[0].name;
            }
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void y() {
        this.j = false;
        this.d.a(false);
        if (this.d.b() <= 0) {
            BleManager.g();
        } else if (u()) {
            BleManager.g();
        } else {
            Device d2 = BleManager.d();
            if (d2 == null) {
                BleManager.g();
            }
            BleManager.a((BleDevice) d2, new BleManager.OnUnlockListener() {
                public void a() {
                    Log.d(BleManager.f15415a, "onUnlocked");
                    if (!ClientAllLockedDialog.this.u()) {
                        ClientAllLockedDialog.this.v();
                    }
                }

                public void b() {
                    Log.d(BleManager.f15415a, "onLocked");
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public boolean u() {
        if (!BleManager.b()) {
            v();
            return true;
        } else if (!BleManager.c()) {
            this.d.a(false);
            return true;
        } else if (!BleManager.e()) {
            this.d.a(false);
            return true;
        } else if (BleManager.d() != null) {
            return false;
        } else {
            this.d.a(false);
            return true;
        }
    }

    public synchronized void v() {
        this.f13596a.removeCallbacks(this.t);
        this.f13596a.postDelayed(this.t, 700);
    }
}
