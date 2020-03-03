package com.xiaomi.smarthome.light.group;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceRoomActivity;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.light.group.LightGroupSettingActivity;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;

public class LightGroupSettingActivity extends BaseActivity {
    public static final String ARGS_KEY_CREATE_MODE = "create_mode";
    public static final String ARGS_KEY_DID = "did";
    public static final String ARGS_KEY_GROUP_MODEL = "key_group_model";
    public static final int sMaxChildSize = 50;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public boolean f19154a;
    private String b;
    /* access modifiers changed from: private */
    public String c;
    private Home d;
    private TextView e;
    private LightGroupAdapter f;
    private ArrayList<LightGroupData> g = new ArrayList<>();
    /* access modifiers changed from: private */
    public Set<String> h = new HashSet();
    /* access modifiers changed from: private */
    public String i;
    private XQProgressDialog j;
    private String k = "";
    private int l = 0;
    SmartHomeDeviceManager.IClientDeviceListener listener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            Device b = SmartHomeDeviceManager.a().b(LightGroupSettingActivity.this.i);
            if (i == 3 && b != null) {
                SmartHomeDeviceManager.a().c(LightGroupSettingActivity.this.listener);
                if (LightGroupSettingActivity.this.f19154a) {
                    HomeManager.a().b(LightGroupSettingActivity.this.i, true);
                    Intent intent = new Intent(LightGroupSettingActivity.this, InitDeviceRoomActivity.class);
                    intent.putExtra("device_id", LightGroupSettingActivity.this.i);
                    if (LightGroupSettingActivity.this.getIntent() != null) {
                        intent.putExtra(AppConstants.O, LightGroupSettingActivity.this.getIntent().getBooleanExtra(AppConstants.O, false));
                    }
                    DeviceFinder.a().c(LightGroupSettingActivity.this.i);
                    SmartHomeDeviceManager.a().q();
                    LightGroupSettingActivity.this.startActivity(intent);
                } else {
                    LightGroupSettingActivity.this.setResult(-1, new Intent());
                }
                LightGroupSettingActivity.this.finish();
            }
        }
    };
    private TextView m;
    /* access modifiers changed from: private */
    public String n;

    class LightGroupData {

        /* renamed from: a  reason: collision with root package name */
        public Device f19159a;
        public String b;
        public String c;
        public boolean d;

        public LightGroupData(Device device, String str, String str2, boolean z) {
            this.f19159a = device;
            this.b = str;
            this.c = str2 + " | " + str;
            this.d = z;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_light_group_setting);
        this.b = getIntent().getStringExtra(ARGS_KEY_GROUP_MODEL);
        if (TextUtils.isEmpty(this.b)) {
            finish();
            return;
        }
        this.f19154a = getIntent().getBooleanExtra(ARGS_KEY_CREATE_MODE, true);
        this.c = getIntent().getStringExtra("did");
        this.d = HomeManager.a().q(this.c);
        if (this.d == null) {
            this.d = HomeManager.a().m();
        }
        if (this.d == null) {
            finish();
            return;
        }
        if (this.f19154a) {
            b();
        } else {
            c();
        }
        Iterator<LightGroupData> it = this.g.iterator();
        while (it.hasNext()) {
            LightGroupData next = it.next();
            if (next.d && next.f19159a.isOnline) {
                this.l++;
            }
        }
        this.n = XMStringUtils.a((Context) this, (int) R.plurals.light_group_child_max_size, 50, (Object) 50);
        d();
    }

    private void a() {
        for (String b2 : HomeManager.a().a(this.d.j(), new boolean[0])) {
            Device b3 = SmartHomeDeviceManager.a().b(b2);
            if (b3 != null && !TextUtils.equals(this.c, b3.did) && LightGroupManager.a().a(b3, this.b)) {
                this.g.add(new LightGroupData(b3, getString(R.string.light_ungrouped), HomeManager.a().r(b3.did), true));
            }
        }
    }

    private void b() {
        Device b2 = SmartHomeDeviceManager.a().b(this.c);
        if (b2 != null) {
            this.g.add(new LightGroupData(b2, getString(R.string.light_ungrouped), HomeManager.a().r(b2.did), true));
            if (b2.isOnline) {
                this.h.add(b2.did);
            }
        }
        a();
        List<String> n2 = SmartHomeDeviceManager.a().n();
        for (int i2 = 0; i2 < n2.size(); i2++) {
            Device b3 = SmartHomeDeviceManager.a().b(n2.get(i2));
            if (b3 != null && this.d.equals(HomeManager.a().q(b3.did)) && TextUtils.equals(b3.model, this.b)) {
                for (Device lightGroupData : SmartHomeDeviceManager.a().d(b3.did)) {
                    this.g.add(new LightGroupData(lightGroupData, b3.name, HomeManager.a().r(b3.did), false));
                }
            }
        }
    }

    private void c() {
        List<Device> d2 = SmartHomeDeviceManager.a().d(this.c);
        Device b2 = SmartHomeDeviceManager.a().b(this.c);
        if (b2 == null) {
            finish();
            return;
        }
        for (Device lightGroupData : d2) {
            this.g.add(new LightGroupData(lightGroupData, b2.name, HomeManager.a().r(b2.did), true));
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            this.h.add(this.g.get(i2).f19159a.did);
        }
        a();
        List<String> n2 = SmartHomeDeviceManager.a().n();
        for (int i3 = 0; i3 < n2.size(); i3++) {
            Device b3 = SmartHomeDeviceManager.a().b(n2.get(i3));
            if (b3 != null && this.d.equals(HomeManager.a().q(b3.did)) && !b3.equals(b2) && TextUtils.equals(b3.model, b2.model)) {
                for (Device lightGroupData2 : SmartHomeDeviceManager.a().d(b3.did)) {
                    this.g.add(new LightGroupData(lightGroupData2, b3.name, HomeManager.a().r(b3.did), false));
                }
            }
        }
    }

    private void d() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupSettingActivity.this.d(view);
            }
        });
        findViewById(R.id.module_a_3_right_btn).setVisibility(8);
        this.m = (TextView) findViewById(R.id.create_button);
        this.m.setEnabled(false);
        if (this.f19154a) {
            PluginRecord d2 = CoreApi.a().d(this.b);
            ((ViewStub) findViewById(R.id.layout_top)).inflate();
            ((TextView) findViewById(R.id.tv_child_size)).setText(this.n);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.bg_light);
            if (d2 != null) {
                textView.setText(d2.p());
                DeviceFactory.b(this.b, simpleDraweeView);
                this.k = d2.p();
            }
            this.m.setText(R.string.create);
            this.m.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    LightGroupSettingActivity.this.c(view);
                }
            });
        } else {
            textView.setText(R.string.light_group_manager);
            this.m.setText(R.string.save);
            ((TextView) findViewById(R.id.tv_child_size)).setText(this.n);
            this.m.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    LightGroupSettingActivity.this.b(view);
                }
            });
        }
        this.e = (TextView) findViewById(R.id.module_a_3_right_text_btn);
        this.e.setVisibility(0);
        this.e.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupSettingActivity.this.a(view);
            }
        });
        if (this.l <= 0) {
            this.e.setEnabled(false);
        } else {
            this.e.setEnabled(true);
            if (this.h.size() < this.l) {
                this.e.setText(R.string.select_all);
            } else {
                this.e.setText(R.string.unselect_all);
            }
        }
        this.f = new LightGroupAdapter(this, this.g);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        f();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        e();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        g();
    }

    private void e() {
        showLoadingDialog();
        ArrayList arrayList = new ArrayList(this.f.d);
        ArrayList arrayList2 = new ArrayList();
        for (String next : this.h) {
            if (!arrayList.contains(next)) {
                arrayList2.add(next);
            }
        }
        arrayList.removeAll(this.h);
        DeviceApi.getInstance().modLightGroupOld(this, this.c, arrayList, arrayList2, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                String unused = LightGroupSettingActivity.this.i = LightGroupSettingActivity.this.c;
                SmartHomeDeviceManager.a().a(LightGroupSettingActivity.this.listener);
                SmartHomeDeviceManager.a().p();
                LightGroupSettingActivity.this.hideLoadingDialog();
            }

            public void onFailure(Error error) {
                ToastUtil.a((CharSequence) error.b());
            }
        });
    }

    private void f() {
        showLoadingDialog();
        DeviceApi.getInstance().createLightGroup(this, new ArrayList(this.f.d), this.k, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (!jSONObject.isNull("group_did")) {
                        String unused = LightGroupSettingActivity.this.i = jSONObject.optString("group_did", "");
                    }
                    ToastUtil.a((int) R.string.light_group_create_succ);
                    SmartHomeDeviceManager.a().a(LightGroupSettingActivity.this.listener);
                    SmartHomeDeviceManager.a().p();
                    LightGroupSettingActivity.this.hideLoadingDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Error error) {
                ToastUtil.a((CharSequence) error.b());
                LightGroupSettingActivity.this.hideLoadingDialog();
            }
        });
    }

    private void g() {
        boolean z = false;
        if (this.f.d.size() >= 50) {
            this.f.a(false);
        } else {
            LightGroupAdapter lightGroupAdapter = this.f;
            if (this.f.d.size() < this.l) {
                z = true;
            }
            lightGroupAdapter.a(z);
        }
        this.f.notifyDataSetChanged();
    }

    public void onBackPressed() {
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        hideLoadingDialog();
        SmartHomeDeviceManager.a().c(this.listener);
    }

    /* access modifiers changed from: protected */
    public void showLoadingDialog() {
        if (isValid()) {
            this.j = new XQProgressDialog(this);
            this.j.setMessage(getString(R.string.device_more_security_loading_operation));
            this.j.show();
        }
    }

    /* access modifiers changed from: protected */
    public void hideLoadingDialog() {
        if (this.j != null) {
            this.j.dismiss();
        }
    }

    public void updateActionItems(int i2) {
        boolean z = false;
        if (this.f19154a) {
            TextView textView = this.m;
            if (i2 >= 2) {
                z = true;
            }
            textView.setEnabled(z);
        } else {
            TextView textView2 = this.m;
            if (i2 > 0) {
                z = true;
            }
            textView2.setEnabled(z);
        }
        if ((i2 >= this.l && this.l > 0) || i2 >= 50) {
            this.e.setText(R.string.unselect_all);
        } else if (this.l > 0) {
            this.e.setText(R.string.select_all);
        }
    }

    public class LightGroupAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private LayoutInflater b;
        private List<LightGroupData> c = new ArrayList();
        /* access modifiers changed from: private */
        public Set<String> d = new LinkedHashSet();

        public LightGroupAdapter(Context context, ArrayList<LightGroupData> arrayList) {
            this.b = LayoutInflater.from(context);
            this.c = arrayList;
            this.d.addAll(LightGroupSettingActivity.this.h);
        }

        /* renamed from: a */
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHolder(this.b.inflate(R.layout.item_light_group, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
            Device device = this.c.get(i).f19159a;
            if (device != null && myViewHolder != null) {
                myViewHolder.e.setEnabled(true);
                int i2 = 0;
                if (!this.c.get(i).d || !device.isOnline || (this.d.size() >= 50 && !this.d.contains(device.did))) {
                    myViewHolder.e.setEnabled(false);
                }
                myViewHolder.e.setVisibility(myViewHolder.e.isEnabled() ? 0 : 4);
                myViewHolder.e.setTag(device.did);
                myViewHolder.b.setText(device.name);
                myViewHolder.c.setText(this.c.get(i).c);
                ImageView imageView = myViewHolder.f;
                if (device.isOnline) {
                    i2 = 8;
                }
                imageView.setVisibility(i2);
                DeviceFactory.b(device.model, myViewHolder.d);
                if (this.d != null) {
                    myViewHolder.e.setChecked(this.d.contains(device.did));
                }
                myViewHolder.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(myViewHolder, device) {
                    private final /* synthetic */ LightGroupSettingActivity.MyViewHolder f$1;
                    private final /* synthetic */ Device f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        LightGroupSettingActivity.LightGroupAdapter.this.a(this.f$1, this.f$2, compoundButton, z);
                    }
                });
                myViewHolder.f19160a.setOnClickListener(new View.OnClickListener(i, device, myViewHolder) {
                    private final /* synthetic */ int f$1;
                    private final /* synthetic */ Device f$2;
                    private final /* synthetic */ LightGroupSettingActivity.MyViewHolder f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    public final void onClick(View view) {
                        LightGroupSettingActivity.LightGroupAdapter.this.a(this.f$1, this.f$2, this.f$3, view);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(MyViewHolder myViewHolder, Device device, CompoundButton compoundButton, boolean z) {
            if (z) {
                if (!this.d.contains(myViewHolder.e.getTag())) {
                    a(device.did, true);
                }
            } else if (this.d.contains(myViewHolder.e.getTag())) {
                a(device.did, false);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(int i, Device device, MyViewHolder myViewHolder, View view) {
            if (!this.c.get(i).d) {
                ToastUtil.a((int) R.string.light_already_grouped);
            } else if (!device.isOnline) {
                ToastUtil.a((int) R.string.offline_device);
            } else if (myViewHolder.e.isEnabled()) {
                myViewHolder.e.performClick();
            } else if (this.d.size() >= 50) {
                ToastUtil.a((CharSequence) LightGroupSettingActivity.this.n);
            }
        }

        public int getItemCount() {
            return this.c.size();
        }

        private void a(String str, boolean z) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    if (!z) {
                        if (this.d.size() >= 50) {
                            notifyDataSetChanged();
                        }
                        this.d.remove(str);
                    } else if (this.d.size() <= 50) {
                        this.d.add(str);
                        if (this.d.size() >= 50) {
                            ToastUtil.a((CharSequence) LightGroupSettingActivity.this.n);
                            notifyDataSetChanged();
                        }
                    }
                    LightGroupSettingActivity.this.updateActionItems(this.d.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            boolean z2 = false;
            for (int i = 0; i < this.c.size(); i++) {
                LightGroupData lightGroupData = this.c.get(i);
                boolean z3 = z && lightGroupData.d && lightGroupData.f19159a.isOnline;
                if (!TextUtils.isEmpty(lightGroupData.f19159a.did)) {
                    if (!z3) {
                        this.d.remove(lightGroupData.f19159a.did);
                    } else if (this.d.size() >= 50) {
                        z2 = true;
                    } else {
                        this.d.add(lightGroupData.f19159a.did);
                    }
                }
            }
            if (z2) {
                ToastUtil.a((CharSequence) LightGroupSettingActivity.this.n);
            }
            LightGroupSettingActivity.this.updateActionItems(this.d.size());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f19160a;
        TextView b;
        TextView c;
        SimpleDraweeView d;
        CheckBox e;
        ImageView f;

        public MyViewHolder(View view) {
            super(view);
            this.f19160a = view;
            this.b = (TextView) view.findViewById(R.id.title);
            this.c = (TextView) view.findViewById(R.id.desc);
            this.d = (SimpleDraweeView) view.findViewById(R.id.icon);
            this.e = (CheckBox) view.findViewById(R.id.checkbox);
            this.f = (ImageView) view.findViewById(R.id.offline);
        }
    }
}
