package com.xiaomi.smarthome.newui;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.xiaomi.infrared.adapter.BaseListAdapter;
import com.xiaomi.infrared.adapter.ViewHolder;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.family.ShareHomeActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeMemberManager;
import com.xiaomi.smarthome.homeroom.HomeRoomCreatHomeActivity;
import com.xiaomi.smarthome.homeroom.HomeRoomManageListActivity;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.HomeMember;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;
import com.xiaomi.smarthome.miio.areainfo.LocationData;
import com.xiaomi.smarthome.miio.areainfo.MainlandAreaInfo;
import com.xiaomi.smarthome.miio.areainfo.ShowProvinceHelper;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.newui.NameEditDialogHelper;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class HomeEditorActivity extends BaseActivity {
    public static final String INTENT_KEY_HOME_ID = "home_id";
    /* access modifiers changed from: private */
    public static WeakReference<HomeEditorActivity> m;

    /* renamed from: a  reason: collision with root package name */
    private View f20258a;
    private TextView b;
    private View c;
    /* access modifiers changed from: private */
    public TextView d;
    private ListView e;
    /* access modifiers changed from: private */
    public String f;
    /* access modifiers changed from: private */
    public Home g;
    /* access modifiers changed from: private */
    public TextView h;
    private List<String> i = new ArrayList();
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public Dialog l;
    NameEditDialogHelper.NameEditListener listener = new NameEditDialogHelper.NameEditListener() {
        public void a(String str) {
            if (!TextUtils.isEmpty(str)) {
                HomeEditorActivity.this.a(str);
            }
        }

        public String b(String str) {
            if (HomeManager.a().a((Home) null, str)) {
                return HomeEditorActivity.this.getString(R.string.home_name_duplicate);
            }
            return null;
        }
    };
    private BroadcastReceiver n = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), AreaInfoManager.f11897a)) {
                if (HomeEditorActivity.this.j) {
                    HomeEditorActivity.this.e();
                }
                HomeEditorActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (HomeEditorActivity.this.isValid()) {
                            String a2 = HomeManager.a().a((SHLocationManager.LocationCallback) new SHLocationManager.LocationCallback() {
                                public void onSucceed(String str, Location location) {
                                    Bundle extras;
                                    LocationData a2;
                                    if (HomeEditorActivity.this.isValid() && (extras = location.getExtras()) != null && (a2 = ShowProvinceHelper.a(SHApplication.getAppContext(), (Address) extras.getParcelable("address"))) != null) {
                                        HomeEditorActivity.this.h.setText(a2.a());
                                    }
                                }
                            }, false, HomeEditorActivity.this.g);
                            if (!TextUtils.isEmpty(a2)) {
                                HomeEditorActivity.this.h.setText(a2);
                            }
                        }
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public HomeMemberAdapter o;
    boolean onPaused = false;
    /* access modifiers changed from: private */
    public XQProgressDialog p;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        HomeManager a2 = HomeManager.a();
        this.f = intent.getStringExtra("home_id");
        if (TextUtils.isEmpty(this.f)) {
            this.f = a2.l();
        }
        this.g = a2.j(this.f);
        List<Device> o2 = a2.o(this.f);
        if (a2.f().size() > 1 && o2.size() < 1) {
            this.k = true;
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(this.n, new IntentFilter(AreaInfoManager.f11897a));
        setContentView(R.layout.activity_home_editor_layout);
        b();
        a();
    }

    private void a() {
        HomeMemberManager.a().a(this.g, (AsyncCallback<LongSparseArray<HomeMember>, Error>) new AsyncCallback<LongSparseArray<HomeMember>, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(LongSparseArray<HomeMember> longSparseArray) {
                if (longSparseArray != null && longSparseArray.size() >= 1) {
                    ArrayList arrayList = new ArrayList();
                    List<Long> b = HomeMemberManager.a().b(HomeEditorActivity.this.f);
                    for (int i = 0; i < b.size(); i++) {
                        HomeMember homeMember = longSparseArray.get(b.get(i).longValue());
                        if (homeMember != null) {
                            if (HomeEditorActivity.this.g.p()) {
                                arrayList.add(homeMember);
                            } else if (homeMember.b() == 2 || homeMember.b() == 10) {
                                arrayList.add(homeMember);
                            }
                        }
                    }
                    HomeEditorActivity.this.o.a(arrayList);
                    if (HomeEditorActivity.this.g != null) {
                        STAT.c.b(true ^ HomeEditorActivity.this.g.p() ? 1 : 0, arrayList.size());
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.g != null) {
            int size = HomeManager.a().a(this.g.j(), new boolean[0]).size();
            this.b.setText(getResources().getQuantityString(R.plurals.home_device_size, size, new Object[]{Integer.valueOf(size)}));
        }
        if (this.onPaused) {
            a();
            this.onPaused = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.onPaused = true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.n);
            if (this.l != null && this.l.isShowing()) {
                this.l.dismiss();
            }
        } catch (Exception unused) {
        }
    }

    public static void finishActivity() {
        if (m != null && m.get() != null) {
            ((HomeEditorActivity) m.get()).finish();
        }
    }

    private void b() {
        findViewById(R.id.module_a_3_right_btn).setVisibility(8);
        findViewById(R.id.top_divider).setVisibility(0);
        ((TextView) findViewById(R.id.title_left)).setText(R.string.home_member);
        this.c = findViewById(R.id.home_name_container);
        final Home j2 = HomeManager.a().j(this.f);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (j2 == null || j2.p()) {
                    STAT.d.aL();
                    String str = "";
                    if (j2 != null) {
                        str = j2.k();
                    }
                    if (HomeEditorActivity.this.l != null && HomeEditorActivity.this.l.isShowing()) {
                        HomeEditorActivity.this.l.dismiss();
                    }
                    Dialog unused = HomeEditorActivity.this.l = NameEditDialogHelper.a(HomeEditorActivity.this.getContext(), str, HomeEditorActivity.this.getString(R.string.home_name_update), HomeEditorActivity.this.getString(R.string.input_home_hint), HomeEditorActivity.this.listener);
                }
            }
        });
        this.d = (TextView) this.c.findViewById(R.id.name_tv);
        if (j2 != null) {
            this.d.setText(j2.k());
        }
        this.f20258a = findViewById(R.id.home_room_manager_container);
        this.f20258a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (j2 != null) {
                    HomeRoomManageListActivity.startActivity(HomeEditorActivity.this, j2.j());
                    STAT.d.aN();
                }
            }
        });
        this.b = (TextView) findViewById(R.id.home_device_size_tv);
        this.h = (TextView) findViewById(R.id.home_loc_tv);
        this.e = (ListView) findViewById(R.id.lv_memeber);
        this.o = new HomeMemberAdapter(this, new ArrayList());
        d();
        this.e.setAdapter(this.o);
        this.e.setOnItemClickListener(new AdapterView.OnItemClickListener(j2) {
            private final /* synthetic */ Home f$1;

            {
                this.f$1 = r2;
            }

            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                HomeEditorActivity.this.a(this.f$1, adapterView, view, i, j);
            }
        });
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeEditorActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(getString(R.string.home_manage_tite));
        findViewById(R.id.module_a_3_right_text_btn).setVisibility(8);
        c();
        String a2 = HomeManager.a().a((SHLocationManager.LocationCallback) new SHLocationManager.LocationCallback() {
            public void onSucceed(String str, Location location) {
                Bundle extras;
                LocationData a2;
                if (HomeEditorActivity.this.isValid() && (extras = location.getExtras()) != null && (a2 = ShowProvinceHelper.a(SHApplication.getAppContext(), (Address) extras.getParcelable("address"))) != null) {
                    HomeEditorActivity.this.h.setText(a2.a());
                }
            }

            public void onFailure(String str) {
                if (HomeEditorActivity.this.isValid()) {
                }
            }
        }, true, this.g);
        if (!TextUtils.isEmpty(a2)) {
            this.h.setText(a2);
        }
        if (CoreApi.a().D()) {
            findViewById(R.id.home_loc_container).setVisibility(8);
        } else {
            findViewById(R.id.home_loc_container).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (j2 == null || j2.p()) {
                        STAT.d.aM();
                        ShowProvinceHelper.a((Activity) HomeEditorActivity.this, (ShowProvinceHelper.IUpdateLocationCallback) new ShowProvinceHelper.IUpdateLocationCallback() {
                            public void a(Context context, String str, String str2, String str3, String str4) {
                                HomeEditorActivity.this.h.setText(MainlandAreaInfo.a(context, str, str2, str3));
                                if (HomeEditorActivity.this.g != null && !TextUtils.equals(HomeEditorActivity.this.g.a(), str4)) {
                                    HomeEditorActivity.this.g.c(str4);
                                    HomeEditorActivity.this.g.a("0");
                                    HomeEditorActivity.this.g.b("0");
                                    HomeManager.a().b(HomeEditorActivity.this.g, (HomeManager.IHomeOperationCallback) null);
                                    HomeManager.a().H();
                                }
                            }

                            public void a(Context context, Address address, Location location, boolean z, boolean z2) {
                                LocationData a2 = ShowProvinceHelper.a(context, address);
                                if (a2 != null) {
                                    HomeEditorActivity.this.h.setText(a2.a());
                                    if (HomeEditorActivity.this.g != null) {
                                        HomeEditorActivity.this.g.c("0");
                                        Home access$300 = HomeEditorActivity.this.g;
                                        access$300.a(location.getLatitude() + "");
                                        Home access$3002 = HomeEditorActivity.this.g;
                                        access$3002.b(location.getLongitude() + "");
                                        HomeManager.a().b(HomeEditorActivity.this.g, (HomeManager.IHomeOperationCallback) null);
                                        HomeManager.a().H();
                                    }
                                }
                            }
                        });
                        boolean unused = HomeEditorActivity.this.j = true;
                    }
                }
            });
        }
        if (j2 != null && !j2.p()) {
            this.f20258a.setVisibility(8);
            findViewById(R.id.home_device_size_container).setVisibility(8);
            ((RelativeLayout.LayoutParams) findViewById(R.id.home_loc_divider).getLayoutParams()).leftMargin = 0;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Home home, AdapterView adapterView, View view, int i2, long j2) {
        int i3;
        if (j2 == -1) {
            ShareHomeActivity.startActivity(this, this.f);
            STAT.d.aO();
        } else if (home != null) {
            HomeMember homeMember = (HomeMember) adapterView.getItemAtPosition(i2);
            HomeMemberDetailsActivity.startActivity(this, homeMember, home.j());
            int b2 = homeMember.b();
            if (b2 == 2) {
                i3 = 2;
            } else if (b2 != 10) {
                switch (b2) {
                    case -1:
                        i3 = 4;
                        break;
                    case 0:
                        i3 = 3;
                        break;
                    default:
                        i3 = -1;
                        break;
                }
            } else {
                i3 = 1;
            }
            STAT.d.b(i3, home.p() ^ true ? 1 : 0);
        }
    }

    public void showProgressDialog(String str) {
        if (TextUtils.isEmpty(str)) {
            str = getResources().getString(R.string.loading_share_info);
        }
        this.p = new XQProgressDialog(this);
        this.p.setCancelable(true);
        this.p.setMessage(str);
        this.p.show();
    }

    public void contrlProgreassDialog(boolean z, boolean z2, final boolean z3) {
        if (isValid() && !z) {
            if (this.p != null) {
                getWindow().getDecorView().postDelayed(new Runnable() {
                    public void run() {
                        if (HomeEditorActivity.this.p != null) {
                            HomeEditorActivity.this.p.dismiss();
                        }
                        if (z3) {
                            HomeEditorActivity.this.finish();
                        }
                    }
                }, 2000);
            }
            if (!z2) {
                ToastUtil.a((int) R.string.home_set_failed);
            }
        }
    }

    private void c() {
        TextView textView = (TextView) findViewById(R.id.delhome);
        View findViewById = findViewById(R.id.addhome);
        List<Home> f2 = HomeManager.a().f();
        int i2 = 0;
        for (int i3 = 0; i3 < f2.size(); i3++) {
            if (f2.get(i3).p()) {
                i2++;
            }
        }
        if (this.g != null && !this.g.p()) {
            findViewById.setVisibility(4);
            textView.setVisibility(0);
            textView.setText(R.string.family_quit);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (HomeEditorActivity.this.l != null && HomeEditorActivity.this.l.isShowing()) {
                        HomeEditorActivity.this.l.dismiss();
                    }
                    STAT.d.k(1);
                    Dialog unused = HomeEditorActivity.this.l = new MLAlertDialog.Builder(HomeEditorActivity.this).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.exit, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            long parseLong = Long.parseLong(CoreApi.a().s());
                            HomeEditorActivity.this.showProgressDialog((String) null);
                            HomeMemberManager.a().a(parseLong, HomeEditorActivity.this.g, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                                /* renamed from: a */
                                public void onSuccess(JSONObject jSONObject) {
                                    HomeEditorActivity.this.contrlProgreassDialog(false, true, true);
                                }

                                public void onFailure(Error error) {
                                    HomeEditorActivity.this.contrlProgreassDialog(false, false, false);
                                }
                            });
                        }
                    }).b((int) R.string.home_member_exit_home).d();
                }
            });
        } else if (i2 > 1) {
            findViewById.setVisibility(4);
            textView.setVisibility(0);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (HomeEditorActivity.this.l != null && HomeEditorActivity.this.l.isShowing()) {
                        HomeEditorActivity.this.l.dismiss();
                    }
                    STAT.d.k(0);
                    if (HomeEditorActivity.this.k) {
                        Dialog unused = HomeEditorActivity.this.l = new MLAlertDialog.Builder(HomeEditorActivity.this).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                HomeManager.a().c(HomeEditorActivity.this.g, new HomeManager.IHomeOperationCallback() {
                                    public void a() {
                                        HomeEditorActivity.this.finish();
                                    }

                                    public void a(int i, Error error) {
                                        HomeEditorActivity.this.finish();
                                        ToastUtil.a((int) R.string.room_delete_failed);
                                    }
                                });
                            }
                        }).b((int) R.string.message_delete_home_enable).d();
                    } else {
                        Dialog unused2 = HomeEditorActivity.this.l = new MLAlertDialog.Builder(HomeEditorActivity.this).b((int) R.string.action_back, (DialogInterface.OnClickListener) null).b((int) R.string.message_delete_home_unable).d();
                    }
                }
            });
        } else {
            textView.setVisibility(4);
            findViewById.setVisibility(0);
        }
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.aa();
                HomeEditorActivity.this.startActivity(new Intent(HomeEditorActivity.this.getContext(), HomeRoomCreatHomeActivity.class));
                WeakReference unused = HomeEditorActivity.m = new WeakReference(HomeEditorActivity.this);
            }
        });
    }

    private void d() {
        if (this.g != null && this.g.p()) {
            this.e.addFooterView(LayoutInflater.from(this).inflate(R.layout.home_member_list_add_member_item, this.e, false));
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        Home m2 = HomeManager.a().m();
        if (m2 != null) {
            m2.c(AreaInfoManager.a().f());
            m2.a(AreaInfoManager.a().g());
            m2.b(AreaInfoManager.a().h());
            HomeManager.a().H();
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        if (this.g != null) {
            this.g.i(str);
            HomeManager.a().b(this.g, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                public void a() {
                    HomeEditorActivity.this.d.setText(str);
                }

                public void a(int i, Error error) {
                    ToastUtil.a((int) R.string.tip_home_name_save_failed);
                }
            });
        }
    }

    class HomeMemberAdapter extends BaseListAdapter<HomeMember> {
        public int a(int i) {
            return R.layout.home_member_list_item;
        }

        HomeMemberAdapter(Context context, List<HomeMember> list) {
            super(context, list);
        }

        public void a(ViewHolder viewHolder, HomeMember homeMember, int i) {
            if (homeMember != null) {
                TextView textView = (TextView) viewHolder.a((int) R.id.tv_member_name);
                TextView textView2 = (TextView) viewHolder.a((int) R.id.tv_member_identity);
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) viewHolder.a((int) R.id.icon);
                UserInfo c2 = homeMember.c();
                if (c2 != null) {
                    UserMamanger.a().b(c2.c, simpleDraweeView, (BasePostprocessor) null);
                    textView.setText(c2.e);
                }
                textView2.setTextColor(homeMember.b(HomeEditorActivity.this.getResources()));
                textView2.setText(homeMember.a(HomeEditorActivity.this.getResources()));
            }
        }
    }
}
