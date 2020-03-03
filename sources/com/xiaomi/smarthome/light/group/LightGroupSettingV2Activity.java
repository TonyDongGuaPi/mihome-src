package com.xiaomi.smarthome.light.group;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.light.group.LightGroupSettingV2Activity;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class LightGroupSettingV2Activity extends BaseActivity {
    public static final String ARGS_KEY_DID_LIST = "args_key_did_list";
    public static final String ARGS_KEY_EDIT_MODE = "args_key_edit_mode";

    /* renamed from: a  reason: collision with root package name */
    private static final String f19161a = "LightGroupSettingV2Activity";
    public static final int sMaxChildSize = 50;
    public View[] TAG_FUNC_VIEW;
    /* access modifiers changed from: private */
    public boolean b = true;
    @BindView(2131427840)
    SimpleDraweeView bgLight;
    private List<String> c;
    private Home d;
    @BindView(2131428897)
    View divider;
    /* access modifiers changed from: private */
    public ArrayList<LightGroupData> e = new ArrayList<>();
    private ArrayList<LightGroupData> f = new ArrayList<>();
    @BindView(2131432958)
    TextView funcTitle;
    private Set<String> g = new HashSet();
    /* access modifiers changed from: private */
    public Map<String, LightGroupData> h = new HashMap();
    private XQProgressDialog i;
    @BindView(2131429698)
    TextView iconBright;
    @BindView(2131429701)
    TextView iconColor;
    @BindView(2131429702)
    TextView iconColorTemperature;
    @BindView(2131429723)
    TextView iconOn;
    /* access modifiers changed from: private */
    public Device j;
    private LightGroupAdapter k;
    /* access modifiers changed from: private */
    public String l;
    SmartHomeDeviceManager.IClientDeviceListener listener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            Device b = SmartHomeDeviceManager.a().b(LightGroupSettingV2Activity.this.l);
            if (i == 3 && b != null) {
                SmartHomeDeviceManager.a().c(LightGroupSettingV2Activity.this.listener);
                if (!LightGroupSettingV2Activity.this.b) {
                    HomeManager.a().b(LightGroupSettingV2Activity.this.l, true);
                    DeviceFinder.a().c(LightGroupSettingV2Activity.this.l);
                    SmartHomeDeviceManager.a().q();
                    if (LightGroupSettingV2Activity.this.m) {
                        LightGroupInitActivity.open(LightGroupSettingV2Activity.this, LightGroupSettingV2Activity.this.l, 100);
                    } else {
                        LightGroupTestActivity.open(LightGroupSettingV2Activity.this, LightGroupSettingV2Activity.this.l, 200);
                    }
                } else if (!LightGroupSettingV2Activity.this.o) {
                    LightGroupSettingV2Activity.this.setResult(-1);
                    LightGroupSettingV2Activity.this.finish();
                } else {
                    Intent intent = new Intent(LightGroupSettingV2Activity.this, SmartHomeMainActivity.class);
                    intent.setFlags(335544320);
                    LightGroupSettingV2Activity.this.startActivity(intent);
                }
                LightGroupSettingV2Activity.this.i();
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean m;
    @BindView(2131430969)
    ImageView moduleA3ReturnBtn;
    @BindView(2131430975)
    TextView moduleA3ReturnTitle;
    @BindView(2131430980)
    ImageView moduleA3RightBtn;
    @BindView(2131430983)
    TextView moduleA3RightTextBtn;
    private long n;
    @BindView(2131428623)
    TextView next;
    /* access modifiers changed from: private */
    public boolean o = false;
    @BindView(2131431796)
    RecyclerView recyclerview;
    @BindView(2131433371)
    TextView tvLightGroupTop1;
    @BindView(2131433372)
    TextView tvLightGroupTop2;

    class LightGroupData {

        /* renamed from: a  reason: collision with root package name */
        public Device f19168a;
        public String b;
        public String c;
        public boolean d;
        public int e = 0;

        public LightGroupData(Device device, String str, String str2, boolean z) {
            this.f19168a = device;
            this.b = str;
            this.c = str2;
            this.d = z;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_light_group_setting_v2);
        ButterKnife.bind((Activity) this);
        this.n = System.currentTimeMillis();
        this.b = getIntent().getBooleanExtra(ARGS_KEY_EDIT_MODE, false);
        this.c = getIntent().getStringArrayListExtra(ARGS_KEY_DID_LIST);
        if (this.c == null) {
            this.c = new ArrayList();
        }
        if (this.c.size() > 0) {
            this.j = SmartHomeDeviceManager.a().b(this.c.get(0));
            this.d = HomeManager.a().q(this.c.get(0));
        }
        if (this.d == null) {
            this.d = HomeManager.a().m();
        }
        if (this.d == null) {
            ToastUtil.a((CharSequence) "home == null");
            finish();
            return;
        }
        if (this.b) {
            if (this.j != null) {
                STAT.c.p(this.j.model);
            }
            a();
        } else {
            STAT.c.i(this.c.size() > 2 ? "multiple devices" : "single device", this.c.size());
            b();
        }
        h();
        d();
        LightGroupManager.a().a(this.g, (AsyncCallback<SparseArray<List<String>>, Error>) new AsyncCallback<SparseArray<List<String>>, Error>() {
            /* renamed from: a */
            public void onSuccess(SparseArray<List<String>> sparseArray) {
                LightGroupSettingV2Activity.this.i();
                for (int i = 0; i < LightGroupSettingV2Activity.this.e.size(); i++) {
                    LightGroupData lightGroupData = (LightGroupData) LightGroupSettingV2Activity.this.e.get(i);
                    for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                        if (sparseArray.get(i2).contains(lightGroupData.f19168a.model)) {
                            lightGroupData.e |= 1 << i2;
                        }
                    }
                }
                int i3 = 15;
                for (LightGroupData lightGroupData2 : LightGroupSettingV2Activity.this.h.values()) {
                    i3 &= lightGroupData2.e;
                }
                LightGroupSettingV2Activity.this.a(i3);
            }

            public void onFailure(Error error) {
                LightGroupSettingV2Activity.this.i();
                ToastUtil.a((CharSequence) "queryModelFunction: " + error.toString());
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        for (int i3 = 0; i3 < this.TAG_FUNC_VIEW.length; i3++) {
            View view = this.TAG_FUNC_VIEW[i3];
            if (view != null) {
                boolean z = true;
                if (((1 << i3) & i2) == 0) {
                    z = false;
                }
                view.setEnabled(z);
            }
        }
    }

    private void a() {
        if (this.j == null) {
            finish();
            return;
        }
        for (Device next2 : SmartHomeDeviceManager.a().d(this.j.did)) {
            LightGroupData lightGroupData = new LightGroupData(next2, "", HomeManager.a().r(this.j.did), true);
            if (next2.isOnlineAdvance()) {
                this.e.add(lightGroupData);
                if (this.h.size() <= 50) {
                    this.h.put(next2.did, lightGroupData);
                }
            } else {
                this.f.add(lightGroupData);
            }
            this.g.add(next2.model);
        }
        if (!this.f.isEmpty()) {
            this.e.addAll(this.f);
        }
    }

    private void b() {
        int i2 = 0;
        if (this.c == null || this.c.size() <= 1) {
            c();
            List<String> n2 = SmartHomeDeviceManager.a().n();
            while (i2 < n2.size()) {
                Device b2 = SmartHomeDeviceManager.a().b(n2.get(i2));
                if (b2 != null && this.d.equals(HomeManager.a().q(b2.did))) {
                    for (Device next2 : SmartHomeDeviceManager.a().d(b2.did)) {
                        this.e.add(new LightGroupData(next2, getString(R.string.light_grouped), HomeManager.a().r(b2.did), false));
                        this.g.add(next2.model);
                    }
                }
                i2++;
            }
        } else {
            while (i2 < this.c.size()) {
                Device b3 = SmartHomeDeviceManager.a().b(this.c.get(i2));
                if (!a(b3)) {
                    LightGroupData lightGroupData = new LightGroupData(b3, getString(R.string.light_ungrouped), "", true);
                    if (b3.isOnlineAdvance()) {
                        this.e.add(lightGroupData);
                        if (this.h.size() <= 50) {
                            this.h.put(b3.did, lightGroupData);
                        }
                    } else {
                        this.f.add(lightGroupData);
                    }
                    this.g.add(b3.model);
                }
                i2++;
            }
        }
        if (!this.f.isEmpty()) {
            this.e.addAll(this.f);
        }
    }

    private void c() {
        for (String b2 : HomeManager.a().a(this.d.j(), new boolean[0])) {
            Device b3 = SmartHomeDeviceManager.a().b(b2);
            if (!a(b3)) {
                LightGroupData lightGroupData = new LightGroupData(b3, getString(R.string.light_ungrouped), HomeManager.a().r(b3.did), true);
                if (this.j != null && TextUtils.equals(this.j.did, b3.did)) {
                    this.e.add(0, lightGroupData);
                    if (b3.isOnlineAdvance()) {
                        this.h.put(b3.did, lightGroupData);
                    }
                } else if (b3.isOnlineAdvance()) {
                    this.e.add(lightGroupData);
                } else {
                    this.f.add(lightGroupData);
                }
                this.g.add(b3.model);
            }
        }
    }

    private boolean a(Device device) {
        if (device == null || !TextUtils.equals("light", DeviceUtils.e(device)) || device.pid == Device.PID_VIRTUAL_GROUP) {
            return true;
        }
        return false;
    }

    private void d() {
        this.divider.setVisibility(8);
        this.moduleA3ReturnBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupSettingV2Activity.this.b(view);
            }
        });
        TextView textView = this.moduleA3ReturnTitle;
        boolean z = this.b;
        int i2 = R.string.device_light_group_create;
        textView.setText(z ? R.string.light_group_edit : R.string.device_light_group_create);
        this.moduleA3RightBtn.setVisibility(8);
        this.next.setEnabled(!this.b && this.h.size() >= 2);
        TextView textView2 = this.next;
        if (this.b) {
            i2 = R.string.save;
        }
        textView2.setText(i2);
        this.next.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupSettingV2Activity.this.a(view);
            }
        });
        this.k = new LightGroupAdapter(this, this.e);
        this.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerview.setAdapter(this.k);
        this.funcTitle.setText(R.string.light_group_current_function);
        this.TAG_FUNC_VIEW = new View[]{this.iconOn, this.iconBright, this.iconColorTemperature, this.iconColor};
        if (this.b) {
            this.tvLightGroupTop1.setVisibility(8);
            this.tvLightGroupTop2.setVisibility(8);
            this.bgLight.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        String str;
        if (this.b) {
            e();
            if (this.j != null) {
                String g2 = g();
                if (TextUtils.equals(this.j.model.substring(this.j.model.lastIndexOf(".")), g2)) {
                    str = "not modified";
                    this.o = false;
                } else {
                    str = "modified" + g2;
                    this.o = true;
                }
                STAT.d.u(this.j.model, str);
                return;
            }
            return;
        }
        f();
        STAT.d.a(g(), this.k.d.size(), System.currentTimeMillis() - this.n);
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.j != null) {
            STAT.d.bk(this.j.model);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if ((i2 == 100 || i2 == 200) && i3 != -1) {
            h();
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.l);
            SmartHomeDeviceManager.a().a((List<String>) arrayList, (Context) this, (SmartHomeDeviceManager.IDelDeviceBatchCallback) new SmartHomeDeviceManager.IDelDeviceBatchCallback() {
                public void a() {
                    SmartHomeDeviceManager.a().p();
                    LightGroupSettingV2Activity.this.i();
                }

                public void a(Error error) {
                    LightGroupSettingV2Activity.this.i();
                }
            });
        } else if (i2 == 100) {
            LightGroupTestActivity.open(this, this.l, 200);
        } else if (i3 == -1) {
            finish();
        }
    }

    private void e() {
        h();
        ArrayList arrayList = new ArrayList(this.k.d.keySet());
        ArrayList arrayList2 = new ArrayList();
        for (String next2 : this.h.keySet()) {
            if (!arrayList.contains(next2)) {
                arrayList2.add(next2);
            }
        }
        arrayList.removeAll(this.h.keySet());
        DeviceApi.getInstance().modLightGroup(this, this.j.did, arrayList, arrayList2, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                String unused = LightGroupSettingV2Activity.this.l = LightGroupSettingV2Activity.this.j.did;
                SmartHomeDeviceManager.a().a(LightGroupSettingV2Activity.this.listener);
                SmartHomeDeviceManager.a().p();
            }

            public void onFailure(Error error) {
                ToastUtil.a((CharSequence) error.b());
                LightGroupSettingV2Activity.this.i();
            }
        }, false);
    }

    private void f() {
        h();
        final ArrayList arrayList = new ArrayList(this.k.d.keySet());
        DeviceApi.getInstance().createLightGroupV2(this, arrayList, "", new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (!jSONObject.isNull("group_did")) {
                        String unused = LightGroupSettingV2Activity.this.l = jSONObject.optString("group_did", "");
                    }
                    if (!jSONObject.isNull("need_alter_device")) {
                        boolean unused2 = LightGroupSettingV2Activity.this.m = jSONObject.optBoolean("need_alter_device", false);
                    }
                    Observable.fromIterable(arrayList).subscribe($$Lambda$LightGroupSettingV2Activity$5$XxLIrZOLMplU2Tkj5PMW0_uC6dY.INSTANCE);
                    SmartHomeDeviceManager.a().a(LightGroupSettingV2Activity.this.listener);
                    SmartHomeDeviceManager.a().p();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Error error) {
                ToastUtil.a((CharSequence) error.b());
                LightGroupSettingV2Activity.this.i();
            }
        });
    }

    public void updateActionItems(Map<String, LightGroupData> map) {
        int i2 = 15;
        try {
            this.next.setEnabled(map.size() >= 2);
            for (LightGroupData lightGroupData : map.values()) {
                i2 &= lightGroupData.e;
            }
            if (map.values().size() == 0) {
                i2 = 0;
            }
            a(i2);
            String str = f19161a;
            LogUtil.a(str, "updateActionItems: final = " + i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String g() {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (View view : this.TAG_FUNC_VIEW) {
            if (view != null) {
                i2 += view.isEnabled() ? 1 : 0;
            }
        }
        sb.append("group");
        sb.append(i2);
        return sb.toString();
    }

    private void h() {
        this.i = new XQProgressDialog(this);
        this.i.setCancelable(true);
        this.i.setMessage(getResources().getString(R.string.loading_share_info));
        this.i.show();
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.i != null) {
            this.i.dismiss();
        }
    }

    public class LightGroupAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private LayoutInflater b;
        private List<LightGroupData> c;
        /* access modifiers changed from: private */
        public Map<String, LightGroupData> d = new HashMap();

        public LightGroupAdapter(Context context, ArrayList<LightGroupData> arrayList) {
            this.b = LayoutInflater.from(context);
            this.c = arrayList;
            this.d.putAll(LightGroupSettingV2Activity.this.h);
        }

        /* renamed from: a */
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHolder(this.b.inflate(R.layout.item_light_group, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
            LightGroupData lightGroupData = this.c.get(i);
            Device device = lightGroupData.f19168a;
            if (device != null && myViewHolder != null) {
                myViewHolder.f.setEnabled(true);
                if (!this.c.get(i).d || !device.isOnlineAdvance() || (this.d.size() >= 50 && !this.d.keySet().contains(device.did))) {
                    myViewHolder.f.setEnabled(false);
                }
                myViewHolder.f.setTag(device.did);
                myViewHolder.b.setText(device.name);
                myViewHolder.c.setText(this.c.get(i).c);
                int i2 = 8;
                myViewHolder.c.setVisibility(TextUtils.isEmpty(this.c.get(i).c) ? 8 : 0);
                myViewHolder.d.setText(this.c.get(i).b);
                myViewHolder.d.setTextColor(LightGroupSettingV2Activity.this.getResources().getColor(R.color.class_D));
                myViewHolder.d.setVisibility(0);
                if (LightGroupSettingV2Activity.this.j != null && TextUtils.equals(LightGroupSettingV2Activity.this.j.did, device.did)) {
                    myViewHolder.d.setText(R.string.light_group_current_device);
                    myViewHolder.d.setTextColor(LightGroupSettingV2Activity.this.getResources().getColor(R.color.class_text_17));
                    myViewHolder.f.setEnabled(false);
                }
                if (!device.isOnlineAdvance()) {
                    myViewHolder.d.setText(R.string.list_device_offline);
                } else if (LightGroupSettingV2Activity.this.b) {
                    myViewHolder.d.setText(R.string.list_device_online);
                }
                myViewHolder.i.setVisibility(device.isOnlineAdvance() ? 8 : 0);
                CheckBox checkBox = myViewHolder.f;
                if (device.isOnlineAdvance()) {
                    i2 = 0;
                }
                checkBox.setVisibility(i2);
                a(device.isOnlineAdvance() ? 1.0f : 0.3f, myViewHolder);
                DeviceFactory.b(device.model, myViewHolder.e);
                if (this.d != null) {
                    myViewHolder.f.setChecked(this.d.keySet().contains(device.did));
                }
                myViewHolder.f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(myViewHolder, device, lightGroupData) {
                    private final /* synthetic */ LightGroupSettingV2Activity.MyViewHolder f$1;
                    private final /* synthetic */ Device f$2;
                    private final /* synthetic */ LightGroupSettingV2Activity.LightGroupData f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        LightGroupSettingV2Activity.LightGroupAdapter.this.a(this.f$1, this.f$2, this.f$3, compoundButton, z);
                    }
                });
                myViewHolder.f19169a.setOnClickListener(new View.OnClickListener(i, device, myViewHolder) {
                    private final /* synthetic */ int f$1;
                    private final /* synthetic */ Device f$2;
                    private final /* synthetic */ LightGroupSettingV2Activity.MyViewHolder f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    public final void onClick(View view) {
                        LightGroupSettingV2Activity.LightGroupAdapter.this.a(this.f$1, this.f$2, this.f$3, view);
                    }
                });
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) myViewHolder.h.getLayoutParams();
                if (i == getItemCount() - 1) {
                    layoutParams.leftMargin = 0;
                } else {
                    layoutParams.leftMargin = DisplayUtils.a((Context) LightGroupSettingV2Activity.this, 24.0f);
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(MyViewHolder myViewHolder, Device device, LightGroupData lightGroupData, CompoundButton compoundButton, boolean z) {
            if (z) {
                if (!this.d.keySet().contains(myViewHolder.f.getTag())) {
                    a(device.did, true, lightGroupData);
                }
            } else if (this.d.keySet().contains(myViewHolder.f.getTag())) {
                a(device.did, false, lightGroupData);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(int i, Device device, MyViewHolder myViewHolder, View view) {
            if (!this.c.get(i).d) {
                ToastUtil.a((int) R.string.light_already_grouped_v2);
            } else if (!device.isOnlineAdvance()) {
                ToastUtil.a((int) R.string.light_already_offline);
            } else if (myViewHolder.f.isEnabled()) {
                myViewHolder.f.performClick();
            } else if (this.d.size() >= 50) {
                ToastUtil.a((CharSequence) XMStringUtils.a((Context) LightGroupSettingV2Activity.this, (int) R.plurals.light_group_child_max_size, 50, (Object) 50));
            }
        }

        public int getItemCount() {
            return this.c.size();
        }

        private void a(float f, MyViewHolder myViewHolder) {
            myViewHolder.b.setAlpha(f);
            myViewHolder.c.setAlpha(f);
            myViewHolder.d.setAlpha(f);
            myViewHolder.f.setAlpha(f);
            myViewHolder.i.setAlpha(f);
        }

        private void a(String str, boolean z, LightGroupData lightGroupData) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    if (!z) {
                        if (this.d.size() >= 50) {
                            notifyDataSetChanged();
                        }
                        this.d.remove(str);
                    } else if (this.d.size() <= 50) {
                        this.d.put(str, lightGroupData);
                        if (this.d.size() >= 50) {
                            ToastUtil.a((CharSequence) XMStringUtils.a((Context) LightGroupSettingV2Activity.this, (int) R.plurals.light_group_child_max_size, 50, (Object) 50));
                            notifyDataSetChanged();
                        }
                    }
                    LightGroupSettingV2Activity.this.updateActionItems(this.d);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f19169a;
        TextView b;
        TextView c;
        TextView d;
        SimpleDraweeView e;
        CheckBox f;
        ImageView g;
        View h;
        ImageView i;

        MyViewHolder(View view) {
            super(view);
            this.f19169a = view;
            this.b = (TextView) view.findViewById(R.id.title);
            this.c = (TextView) view.findViewById(R.id.desc);
            this.d = (TextView) view.findViewById(R.id.desc_tips);
            this.e = (SimpleDraweeView) view.findViewById(R.id.icon);
            this.f = (CheckBox) view.findViewById(R.id.checkbox);
            this.g = (ImageView) view.findViewById(R.id.offline);
            this.h = view.findViewById(R.id.divider_item);
            this.i = (ImageView) view.findViewById(R.id.iv_right);
        }
    }
}
