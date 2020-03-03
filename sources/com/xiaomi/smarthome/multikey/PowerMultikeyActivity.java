package com.xiaomi.smarthome.multikey;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceShareActivity;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.FontManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.HeaderAndFooterRecyclerViewAdapter;
import com.xiaomi.smarthome.mibrain.MiBrainManager;
import com.xiaomi.smarthome.multikey.PowerMultikeyBean;
import com.xiaomi.smarthome.printer.DeviceImageApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PowerMultikeyActivity extends BaseActivity implements View.OnClickListener {
    public static final String EXTRA_ENTITY = "extra_entity";
    public static final String EXTRA_TITLE = "extra_title";
    public static final int FROM_SETTING = 1;
    public static final String INTENT_FROM = "from";
    public static final String INTENT_KEY_DID = "device_id";
    public static final String INTENT_KEY_MAC = "device_mac";
    public static final int STATE_BACK = 3;
    public static final int STATE_IDLE = 0;
    public static final int STATE_NEXT = 2;
    public static final int STATE_UPDATING = 1;

    /* renamed from: a  reason: collision with root package name */
    private static final int f20175a = 11;
    private static final String b = "extra_position";
    private static final byte c = 1;
    private static final byte d = 2;
    private static final byte e = 4;
    private static final byte f = 8;
    private static final String g = "PowerMultikeyActivity";
    private XQProgressDialog h;
    private PowerMultikeyApi i = new PowerMultikeyApi();
    /* access modifiers changed from: private */
    public ArrayList<PowerMultikeyBean> j = new ArrayList<>();
    /* access modifiers changed from: private */
    public int k = 0;
    /* access modifiers changed from: private */
    public SimpleDraweeView l;
    /* access modifiers changed from: private */
    public ArrayList<PowerMultikeyBean> m;
    public PowerMultikeyAdapter mAdapter;
    public Device mDevice;
    ArrayList<PowerMultikeyBean> mList = new ArrayList<>();
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131430982)
    ImageView mRightImage;
    @BindView(2131432499)
    TextView mSkipTv;
    @BindView(2131430975)
    public TextView mTitleTv;
    /* access modifiers changed from: private */
    public byte n;
    /* access modifiers changed from: private */
    public boolean o;
    private int p;
    /* access modifiers changed from: private */
    public HashMap<String, PowerMultikeyBean> q;
    @BindView(2131431788)
    RecyclerView recycle_view;
    @BindView(2131432617)
    View step;
    @BindView(2131432919)
    View titleBar;

    public interface ItemClickListener {
        void onItemClick(List<PowerMultikeyBean> list, int i);
    }

    public static void startActivity(Activity activity, String str, String str2) {
        Intent intent = new Intent(activity, PowerMultikeyActivity.class);
        intent.putExtra("from", 1);
        intent.putExtra("device_id", str);
        intent.putExtra("device_mac", str2);
        activity.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_powermultikey);
        ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.p = intent.getIntExtra("from", 0);
        String stringExtra = intent.getStringExtra("device_id");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.mDevice = SmartHomeDeviceManager.a().b(stringExtra);
        }
        if (this.mDevice == null) {
            String stringExtra2 = intent.getStringExtra("device_mac");
            if (!TextUtils.isEmpty(stringExtra2)) {
                this.mDevice = SmartHomeDeviceManager.a().f(stringExtra2);
            }
        }
        if (this.mDevice == null) {
            finish();
        } else if (!SmartHomeDeviceManager.a().u() || this.mDevice != null) {
            PluginRecord d2 = CoreApi.a().d(this.mDevice.model);
            MiBrainManager.a().a(stringExtra, false, (AsyncCallback) null);
            a(intent, d2);
            a(d2);
        } else {
            finish();
        }
    }

    private void a(Intent intent, PluginRecord pluginRecord) {
        View view;
        this.mTitleTv.setText(R.string.multikey_title);
        if (1 == this.p) {
            this.step.setVisibility(8);
            this.mSkipTv.setVisibility(8);
            view = View.inflate(this, R.layout.header_powermultikey, (ViewGroup) null);
            view.setLayoutParams(new RecyclerView.LayoutParams(-1, Math.max((int) (((float) DisplayUtils.a()) / 2.5f), DisplayUtils.a(200.0f))));
            this.l = (SimpleDraweeView) view.findViewById(R.id.device_img);
        } else {
            view = View.inflate(this, R.layout.header_powermultikey_bind, (ViewGroup) null);
            view.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            DeviceFactory.b(this.mDevice.model, (SimpleDraweeView) view.findViewById(R.id.device_img));
        }
        TextView textView = (TextView) view.findViewById(R.id.device_name);
        if (!TextUtils.isEmpty(this.mDevice.name)) {
            textView.setText(this.mDevice.name);
        } else if (pluginRecord != null) {
            textView.setText(pluginRecord.p());
        }
        this.titleBar.setBackground((Drawable) null);
        this.mRightImage.setVisibility(0);
        this.mRightImage.setImageResource(R.drawable.std_titlebar_icon_help_selector);
        this.mRightImage.setOnClickListener(this);
        this.mReturnBtn.setOnClickListener(this);
        this.mSkipTv.setOnClickListener(this);
        this.recycle_view.setLayoutManager(new LinearLayoutManager(this));
        this.mAdapter = new PowerMultikeyAdapter(this, this.mList, this.mDevice);
        HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(this.mAdapter);
        headerAndFooterRecyclerViewAdapter.a(view);
        this.recycle_view.setAdapter(headerAndFooterRecyclerViewAdapter);
    }

    private void a(PluginRecord pluginRecord) {
        this.i.a(this.mDevice.did, (AsyncCallback<HashMap<String, PowerMultikeyBean>, Error>) new AsyncCallback<HashMap<String, PowerMultikeyBean>, Error>() {
            /* renamed from: a */
            public void onSuccess(HashMap<String, PowerMultikeyBean> hashMap) {
                PowerMultikeyActivity.this.f();
                if (hashMap == null || hashMap.size() == 0) {
                    byte unused = PowerMultikeyActivity.this.n = (byte) (PowerMultikeyActivity.this.n | 1);
                    PowerMultikeyActivity.this.b();
                    return;
                }
                HashMap unused2 = PowerMultikeyActivity.this.q = hashMap;
                PowerMultikeyActivity.this.a();
                byte unused3 = PowerMultikeyActivity.this.n = (byte) (PowerMultikeyActivity.this.n | 2);
                PowerMultikeyActivity.this.b();
            }

            public void onFailure(Error error) {
                PowerMultikeyActivity.this.f();
                ToastUtil.a((int) R.string.loading_failed);
                PowerMultikeyActivity.super.onBackPressed();
            }
        });
        this.i.b(this.mDevice, (AsyncCallback<PowerMultikeyBean.PowerMultikeyList, Error>) new AsyncCallback<PowerMultikeyBean.PowerMultikeyList, Error>() {
            /* renamed from: a */
            public void onSuccess(PowerMultikeyBean.PowerMultikeyList powerMultikeyList) {
                if (PowerMultikeyActivity.this.l != null) {
                    if (TextUtils.isEmpty(powerMultikeyList.b)) {
                        PowerMultikeyActivity.this.c();
                    } else {
                        PowerMultikeyActivity.this.l.setImageURI(powerMultikeyList.b);
                    }
                }
                ArrayList unused = PowerMultikeyActivity.this.m = powerMultikeyList.f20192a;
                PowerMultikeyActivity.this.mList.clear();
                PowerMultikeyActivity.this.mList.addAll(PowerMultikeyActivity.this.m);
                PowerMultikeyActivity.this.a();
                byte unused2 = PowerMultikeyActivity.this.n = (byte) (PowerMultikeyActivity.this.n | 4);
                PowerMultikeyActivity.this.b();
            }

            public void onFailure(Error error) {
                byte unused = PowerMultikeyActivity.this.n = (byte) (PowerMultikeyActivity.this.n | 8);
                PowerMultikeyActivity.this.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.q != null) {
            Iterator<PowerMultikeyBean> it = this.mList.iterator();
            while (it.hasNext()) {
                PowerMultikeyBean next = it.next();
                next.a(this.q.get(next.f20191a));
            }
        }
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void b() {
        String str;
        if (this.n == 5) {
            try {
                Room p2 = HomeManager.a().p(this.mDevice.did);
                String l2 = HomeManager.a().l();
                this.mList.clear();
                for (int i2 = 0; i2 < this.m.size(); i2++) {
                    PowerMultikeyBean powerMultikeyBean = this.m.get(i2);
                    if (p2 == null) {
                        str = l2;
                    } else {
                        str = p2.d();
                    }
                    powerMultikeyBean.b = str;
                    powerMultikeyBean.c = l2;
                    if (TextUtils.isEmpty(powerMultikeyBean.d)) {
                        powerMultikeyBean.d = PowerMultikeyApi.a((List<PowerMultikeyBean>) this.mList, i2);
                    }
                    this.mList.add(powerMultikeyBean);
                }
            } catch (Exception unused) {
                ToastUtil.a((int) R.string.tip_no_info);
                super.onBackPressed();
            }
            d();
            this.mAdapter.notifyDataSetChanged();
            this.i.a(this.mDevice.did, this.mList, new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                }

                public void onFailure(Error error) {
                    boolean unused = PowerMultikeyActivity.this.o = true;
                }
            });
        } else if (this.n == 9) {
            ToastUtil.a((int) R.string.no_data_tips);
            super.onBackPressed();
        } else if (this.n == 10) {
            ToastUtil.a((int) R.string.no_data_tips);
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        this.i.a(this.mDevice, (AsyncCallback<DeviceImageApi.DeviceImageEntity, Error>) new AsyncCallback<DeviceImageApi.DeviceImageEntity, Error>() {
            /* renamed from: a */
            public void onSuccess(DeviceImageApi.DeviceImageEntity deviceImageEntity) {
                PowerMultikeyActivity.this.l.setImageURI(deviceImageEntity.f21157a);
            }

            public void onFailure(Error error) {
                DeviceFactory.b(PowerMultikeyActivity.this.mDevice.model, PowerMultikeyActivity.this.l);
            }
        });
    }

    public void requestUpdate(final ArrayList<PowerMultikeyBean> arrayList) {
        boolean z;
        if (this.j.size() != 0) {
            Iterator<PowerMultikeyBean> it = this.j.iterator();
            while (it.hasNext()) {
                PowerMultikeyBean next = it.next();
                Iterator<PowerMultikeyBean> it2 = arrayList.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (it2.next().f20191a.equals(next.f20191a)) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    arrayList.add(next);
                }
            }
            this.j.clear();
        }
        AnonymousClass5 r0 = new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                PowerMultikeyActivity.this.f();
                boolean unused = PowerMultikeyActivity.this.o = false;
                if (PowerMultikeyActivity.this.k == 2) {
                    PowerMultikeyActivity.this.e();
                } else if (PowerMultikeyActivity.this.k == 3) {
                    PowerMultikeyActivity.super.onBackPressed();
                }
                int unused2 = PowerMultikeyActivity.this.k = 0;
            }

            public void onFailure(Error error) {
                PowerMultikeyActivity.this.f();
                if (PowerMultikeyActivity.this.k == 3) {
                    ToastUtil.a((int) R.string.save_fail);
                    PowerMultikeyActivity.super.onBackPressed();
                } else {
                    if (PowerMultikeyActivity.this.k == 2) {
                        ToastUtil.a((int) R.string.save_fail);
                    }
                    PowerMultikeyActivity.this.j.addAll(arrayList);
                }
                int unused = PowerMultikeyActivity.this.k = 0;
            }
        };
        if (this.o) {
            Iterator<PowerMultikeyBean> it3 = this.mList.iterator();
            while (it3.hasNext()) {
                PowerMultikeyBean next2 = it3.next();
                Iterator<PowerMultikeyBean> it4 = arrayList.iterator();
                boolean z2 = false;
                while (it4.hasNext()) {
                    if (TextUtils.equals(it4.next().f20191a, next2.f20191a)) {
                        z2 = true;
                    }
                }
                if (!z2) {
                    arrayList.add(next2);
                }
            }
            LogUtil.c(g, "requestInit" + this.k);
            if (this.k == 0) {
                this.k = 1;
            }
            this.i.a(this.mDevice.did, arrayList, r0);
        } else if (this.k == 2) {
            if (arrayList.size() == 0) {
                e();
                LogUtil.c(g, "goNext" + this.k);
                return;
            }
            LogUtil.c(g, "requestUpdate" + this.k);
            g();
            this.i.b(this.mDevice.did, arrayList, r0);
        } else if (this.k != 3) {
            LogUtil.c(g, "requestUpdate" + this.k);
            this.k = 1;
            this.i.b(this.mDevice.did, arrayList, r0);
        } else if (arrayList.size() == 0) {
            super.onBackPressed();
            LogUtil.c(g, "onBackPressed" + this.k);
        } else {
            LogUtil.c(g, "requestUpdate" + this.k);
            g();
            this.i.b(this.mDevice.did, arrayList, r0);
        }
    }

    private void d() {
        if (("lumi.ctrl_neutral2.v1".equals(this.mDevice.model) || "llumi.ctrl_ln2.v1".equals(this.mDevice.model) || "lumi.ctrl_ln2.aq1".equals(this.mDevice.model)) && !TextUtils.isEmpty(this.mDevice.name)) {
            String[] split = this.mDevice.name.split("/");
            if (split.length == 2 && this.mList.size() >= 2) {
                int length = split.length;
                for (int i2 = 0; i2 < length; i2++) {
                    this.mList.get(i2).d = split[i2];
                }
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.module_a_3_return_btn) {
            onBackPressed();
        } else if (id == R.id.module_a_3_right_iv_setting_btn) {
            MLAlertDialog c2 = new MLAlertDialog.Builder(this).b((int) R.string.multikey_device_tip).a((int) R.string.confirm, (DialogInterface.OnClickListener) null).a((int) R.string.multikey_title).c();
            c2.show();
            View findViewById = c2.findViewById(R.id.message);
            if (findViewById instanceof TextView) {
                ((TextView) findViewById).setGravity(3);
            }
        } else if (id == R.id.skip) {
            if (this.k == 0) {
                this.k = 2;
                requestUpdate(new ArrayList());
            } else if (this.k == 1) {
                this.k = 2;
                g();
            }
        }
    }

    public void onBackPressed() {
        if (this.k == 0) {
            this.k = 3;
            requestUpdate(new ArrayList());
        } else if (this.k == 1) {
            this.k = 3;
            g();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (-1 == i3 && 11 == i2 && intent != null) {
            int intExtra = intent.getIntExtra(b, 0);
            PowerMultikeyBean powerMultikeyBean = (PowerMultikeyBean) intent.getParcelableExtra(EXTRA_ENTITY);
            if (intExtra < this.mList.size() && powerMultikeyBean != null && !powerMultikeyBean.equals(this.mList.get(intExtra))) {
                if (TextUtils.isEmpty(powerMultikeyBean.d)) {
                    powerMultikeyBean.d = PowerMultikeyApi.a((List<PowerMultikeyBean>) this.mList, intExtra);
                }
                this.mList.set(intExtra, powerMultikeyBean);
                this.mAdapter.notifyItemChanged(intExtra);
                ArrayList arrayList = new ArrayList();
                arrayList.add(powerMultikeyBean);
                requestUpdate(arrayList);
            }
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        Intent intent;
        if (MiBrainManager.a().a(this.mDevice.did)) {
            intent = new Intent(this, InitDeviceMiBrainActivity.class);
        } else {
            intent = new Intent(this, InitDeviceShareActivity.class);
        }
        intent.putExtras(getIntent());
        startActivity(intent);
        setResult(-1);
        finish();
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.h != null && this.h.isShowing()) {
            this.h.dismiss();
        }
    }

    private void g() {
        if (this.h == null || !this.h.isShowing()) {
            this.h = new XQProgressDialog(this);
            this.h.setCancelable(true);
            this.h.setMessage(getResources().getString(R.string.loading_share_info));
            this.h.show();
        }
    }

    public static class PowerMultikeyAdapter extends RecyclerView.Adapter<PowerMultikeyViewHolder> {

        /* renamed from: a  reason: collision with root package name */
        private final Typeface f20181a = FontManager.a("fonts/MI-LANTING--GBK1-Thin.ttf");
        /* access modifiers changed from: private */
        public final PowerMultikeyActivity b;
        /* access modifiers changed from: private */
        public final List<PowerMultikeyBean> c;
        /* access modifiers changed from: private */
        public ItemClickListener d;

        public PowerMultikeyAdapter(PowerMultikeyActivity powerMultikeyActivity, List<PowerMultikeyBean> list, Device device) {
            this.b = powerMultikeyActivity;
            this.c = list;
        }

        @NonNull
        /* renamed from: a */
        public PowerMultikeyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new PowerMultikeyViewHolder(View.inflate(this.b, R.layout.item_powermultikey, (ViewGroup) null));
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull PowerMultikeyViewHolder powerMultikeyViewHolder, final int i) {
            final PowerMultikeyBean powerMultikeyBean = this.c.get(i);
            final String a2 = PowerMultikeyApi.a(this.c, i);
            powerMultikeyViewHolder.f20183a.setText(a2);
            powerMultikeyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (PowerMultikeyAdapter.this.d != null) {
                        PowerMultikeyAdapter.this.d.onItemClick(PowerMultikeyAdapter.this.c, i);
                        return;
                    }
                    PowerMultikeyAdapter.this.b.startActivityForResult(new Intent(PowerMultikeyAdapter.this.b, PowerItemkeyActivity.class).putExtra("extra_title", a2).putExtra(PowerMultikeyActivity.EXTRA_ENTITY, powerMultikeyBean).putExtra(PowerMultikeyActivity.b, i).putExtras(PowerMultikeyAdapter.this.b.getIntent()), 11);
                }
            });
        }

        public int getItemCount() {
            if (this.c == null) {
                return 0;
            }
            return this.c.size();
        }

        public void a(ItemClickListener itemClickListener) {
            this.d = itemClickListener;
        }
    }

    private static class PowerMultikeyViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final TextView f20183a;

        public PowerMultikeyViewHolder(@NonNull View view) {
            super(view);
            this.f20183a = (TextView) view.findViewById(R.id.txt_device_name);
        }
    }
}
