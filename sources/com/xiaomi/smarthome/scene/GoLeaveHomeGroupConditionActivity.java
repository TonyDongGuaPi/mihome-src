package com.xiaomi.smarthome.scene;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.RecommendSceneItem;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.miio.activity.HomeLogWifiSetting;
import com.xiaomi.smarthome.scene.SceneItemChooseFragment;
import com.xiaomi.smarthome.scene.api.SceneApi;
import java.util.ArrayList;
import java.util.List;

public class GoLeaveHomeGroupConditionActivity extends BaseActivity {
    public static final String EXTRA_DID = "extra_did";
    public static final String ITEM_CHOOSE_RESULT = "item_choose_result";
    public static final String MI_ROUTER_PREFIX = "xiaomi.router.";
    public static final int REQUEST_FOR_BODY_SENSOR = 12288;
    public static final int REQUEST_FOR_MI_CAMERA = 12290;
    public static final int REQUEST_FOR_MI_ROUTER = 12289;
    public static final int REQUEST_FOR_WIFI = 12291;

    /* renamed from: a  reason: collision with root package name */
    private ListView f21213a;
    /* access modifiers changed from: private */
    public View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private boolean g;
    /* access modifiers changed from: private */
    public SceneApi.SmartHomeScene h;
    /* access modifiers changed from: private */
    public RecommendSceneItem i;
    /* access modifiers changed from: private */
    public SceneApi.Condition j;
    /* access modifiers changed from: private */
    public SceneApi.Condition k;
    /* access modifiers changed from: private */
    public SceneApi.Condition l;
    /* access modifiers changed from: private */
    public SceneApi.Condition m;
    ConditionAdapter mConditionAdapter;
    /* access modifiers changed from: private */
    public List<Device> n = new ArrayList();
    /* access modifiers changed from: private */
    public List<Device> o = new ArrayList();
    /* access modifiers changed from: private */
    public List<Device> p = new ArrayList();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.come_leave_home_group_condition_layout);
        this.g = getIntent().getBooleanExtra(GoLeaveHomeSceneCreateEditActivity.GO_HOME_RECOMMEND_FLAG, true);
        this.h = SceneDataCache.INSTANCE.getCahcedScene();
        if (this.g) {
            this.i = SceneManager.x().u();
        } else {
            this.i = SceneManager.x().t();
        }
        a();
        c();
        d();
        e();
    }

    private void a() {
        this.n.clear();
        this.o.clear();
        this.p.clear();
        for (Device next : SmartHomeDeviceManager.a().d()) {
            if (!next.isSubDevice()) {
                if (DeviceFactory.i("yunyi.camera.v1", next.model)) {
                    this.o.add(next);
                }
                if (DeviceFactory.i(MI_ROUTER_PREFIX, next.model)) {
                    this.p.add(next);
                }
            }
        }
        for (Device next2 : SmartHomeDeviceManager.a().k()) {
            if (next2.isSubDevice() && DeviceFactory.i("lumi.sensor_motion.v1", next2.model)) {
                this.n.add(next2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        SceneDataCache.INSTANCE.setCachedScene(this.h);
        setResult(-1);
    }

    private void c() {
        if (this.h != null && this.h.x != null && this.h.x.e != null) {
            for (SceneApi.Condition next : this.h.x.e) {
                if (next.c != null) {
                    if (DeviceFactory.i("lumi.sensor_motion.v1", next.c.d)) {
                        this.j = next;
                    } else if (DeviceFactory.i("yunyi.camera.v1", next.c.d)) {
                        this.k = next;
                    } else if (DeviceFactory.i(MI_ROUTER_PREFIX, next.c.d)) {
                        this.m = next;
                    }
                }
                if (next.d != null) {
                    this.l = next;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    private void d() {
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GoLeaveHomeGroupConditionActivity.this.b();
                    GoLeaveHomeGroupConditionActivity.this.finish();
                }
            });
        }
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        TextView textView2 = (TextView) findViewById(R.id.condition_title);
        if (f()) {
            textView2.setText(R.string.scene_any_condition_satified);
            textView.setText(R.string.scene2_device_group_condition_come);
            return;
        }
        textView2.setText(R.string.scene_all_condition_satified);
        textView.setText(R.string.scene2_device_group_condition_leave);
    }

    public void onBackPressed() {
        b();
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        SceneApi.Condition createHomeDeviceCondition;
        if (i2 == 12288 || i2 == 12289 || i2 == 12290) {
            if (i3 == -1 && intent != null) {
                ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(ITEM_CHOOSE_RESULT);
                if (parcelableArrayListExtra.size() > 0 && (createHomeDeviceCondition = HomeSceneFactory.INSTANCE.createHomeDeviceCondition(SmartHomeDeviceManager.a().n(((SceneItemChooseFragment.ItemData) parcelableArrayListExtra.get(0)).f21310a), this.i)) != null) {
                    if (i2 == 12288) {
                        ((SceneApi.ConditionDeviceCommon) createHomeDeviceCondition.c).l = 120;
                        if (this.j != null) {
                            createHomeDeviceCondition.l = this.j.l;
                        } else {
                            this.h.x.e.add(createHomeDeviceCondition);
                        }
                        this.j = createHomeDeviceCondition;
                        ViewHolder viewHolder = (ViewHolder) this.e.getTag();
                        viewHolder.c.setEnabled(false);
                        viewHolder.c.setChecked(createHomeDeviceCondition.l);
                        viewHolder.c.setEnabled(true);
                    } else if (i2 == 12290) {
                        if (this.k != null) {
                            createHomeDeviceCondition.l = this.k.l;
                        } else {
                            this.h.x.e.add(createHomeDeviceCondition);
                        }
                        this.k = createHomeDeviceCondition;
                    } else {
                        String d2 = WifiUtil.d(getContext());
                        if (d2 != null && !d2.isEmpty()) {
                            ((SceneApi.ConditionDeviceCommon) createHomeDeviceCondition.c).l = d2;
                            if (this.m != null) {
                                createHomeDeviceCondition.l = this.m.l;
                            } else {
                                this.h.x.e.add(createHomeDeviceCondition);
                            }
                            this.m = createHomeDeviceCondition;
                            ViewHolder viewHolder2 = (ViewHolder) this.c.getTag();
                            viewHolder2.c.setEnabled(false);
                            viewHolder2.c.setChecked(createHomeDeviceCondition.l);
                            viewHolder2.c.setEnabled(true);
                        }
                    }
                }
            }
        } else if (i2 == 12291) {
            SceneApi.Condition createPhoneCondition = HomeSceneFactory.INSTANCE.createPhoneCondition(this.i);
            if (this.l != null) {
                createPhoneCondition.l = this.l.l;
            } else {
                this.h.x.e.add(createPhoneCondition);
            }
            this.l = createPhoneCondition;
            ViewHolder viewHolder3 = (ViewHolder) this.b.getTag();
            viewHolder3.c.setEnabled(false);
            viewHolder3.c.setChecked(createPhoneCondition.l);
            viewHolder3.c.setEnabled(true);
        }
    }

    private void e() {
        this.f21213a = (ListView) findViewById(R.id.content_list_view);
        this.b = g();
        boolean z = true;
        if (this.b != null) {
            ViewHolder viewHolder = (ViewHolder) this.b.getTag();
            viewHolder.b.setImageURI(CommonUtils.c((int) R.drawable.device_list_phone));
            viewHolder.d.setText(R.string.phone);
            viewHolder.c.setVisibility(0);
            if (f()) {
                viewHolder.e.setText(R.string.phone_connect_wifi);
            } else {
                viewHolder.e.setText(R.string.phone_disconnect_wifi);
            }
            viewHolder.c.setChecked(this.l != null && this.l.l);
            viewHolder.f21233a.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (GoLeaveHomeGroupConditionActivity.this.l == null) {
                        SceneApi.Condition unused = GoLeaveHomeGroupConditionActivity.this.l = HomeSceneFactory.INSTANCE.createPhoneCondition(GoLeaveHomeGroupConditionActivity.this.i);
                        GoLeaveHomeGroupConditionActivity.this.h.x.e.add(GoLeaveHomeGroupConditionActivity.this.l);
                        ViewHolder viewHolder = (ViewHolder) GoLeaveHomeGroupConditionActivity.this.b.getTag();
                        viewHolder.c.setEnabled(false);
                        viewHolder.c.setChecked(true);
                        viewHolder.c.setEnabled(true);
                    }
                    GoLeaveHomeGroupConditionActivity.this.startActivityForResult(new Intent(GoLeaveHomeGroupConditionActivity.this.getContext(), HomeLogWifiSetting.class), 12291);
                }
            });
            viewHolder.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(final CompoundButton compoundButton, boolean z) {
                    if (compoundButton.isEnabled()) {
                        compoundButton.setEnabled(false);
                        if (GoLeaveHomeGroupConditionActivity.this.l == null) {
                            SceneApi.Condition unused = GoLeaveHomeGroupConditionActivity.this.l = HomeSceneFactory.INSTANCE.createPhoneCondition(GoLeaveHomeGroupConditionActivity.this.i);
                            GoLeaveHomeGroupConditionActivity.this.h.x.e.add(GoLeaveHomeGroupConditionActivity.this.l);
                        }
                        GoLeaveHomeGroupConditionActivity.this.l.l = z;
                        GoLeaveHomeGroupConditionActivity.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                compoundButton.setEnabled(true);
                            }
                        }, 400);
                    }
                }
            });
            this.f21213a.addHeaderView(this.b);
        }
        if (f()) {
            this.c = g();
            if (this.c != null) {
                ViewHolder viewHolder2 = (ViewHolder) this.c.getTag();
                viewHolder2.b.setImageURI(CommonUtils.c((int) R.drawable.kuailian_router_icon));
                viewHolder2.d.setText(R.string.mi_router);
                viewHolder2.c.setVisibility(0);
                if (f()) {
                    viewHolder2.e.setText(R.string.mi_router_connect_device);
                } else {
                    viewHolder2.e.setText(R.string.mi_router_disconnect_device);
                }
                viewHolder2.c.setChecked(this.m != null && this.m.l);
                viewHolder2.f21233a.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (GoLeaveHomeGroupConditionActivity.this.p.isEmpty()) {
                            GoLeaveHomeGroupConditionActivity.this.showBuyDialog();
                            return;
                        }
                        Intent intent = new Intent(GoLeaveHomeGroupConditionActivity.this.getContext(), MiRouterChooseActivity.class);
                        if (!(GoLeaveHomeGroupConditionActivity.this.m == null || GoLeaveHomeGroupConditionActivity.this.m.c == null)) {
                            intent.putExtra("extra_did", GoLeaveHomeGroupConditionActivity.this.m.c.f21523a);
                        }
                        GoLeaveHomeGroupConditionActivity.this.startActivityForResult(intent, 12289);
                    }
                });
                viewHolder2.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(final CompoundButton compoundButton, boolean z) {
                        if (compoundButton.isEnabled()) {
                            compoundButton.setEnabled(false);
                            if (GoLeaveHomeGroupConditionActivity.this.p.isEmpty()) {
                                GoLeaveHomeGroupConditionActivity.this.mHandler.post(new Runnable() {
                                    public void run() {
                                        compoundButton.setChecked(false);
                                    }
                                });
                                GoLeaveHomeGroupConditionActivity.this.showBuyDialog();
                            } else if (GoLeaveHomeGroupConditionActivity.this.m == null) {
                                GoLeaveHomeGroupConditionActivity.this.startActivityForResult(new Intent(GoLeaveHomeGroupConditionActivity.this.getContext(), MiRouterChooseActivity.class), 12289);
                            } else {
                                GoLeaveHomeGroupConditionActivity.this.m.l = z;
                            }
                            GoLeaveHomeGroupConditionActivity.this.mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    compoundButton.setEnabled(true);
                                }
                            }, 400);
                        }
                    }
                });
                this.f21213a.addHeaderView(this.c);
            }
        }
        this.e = g();
        if (this.e != null) {
            ViewHolder viewHolder3 = (ViewHolder) this.e.getTag();
            Device k2 = DeviceFactory.k("lumi.sensor_motion.v1");
            viewHolder3.d.setText(R.string.gateway_motion_name);
            viewHolder3.c.setVisibility(0);
            if (f()) {
                viewHolder3.e.setText(R.string.body_sensor_motion);
            } else {
                viewHolder3.e.setText(R.string.body_sensor_no_motion);
            }
            if (k2 != null) {
                DeviceFactory.c(k2.model, viewHolder3.b);
            }
            viewHolder3.c.setChecked(this.j != null && this.j.l);
            viewHolder3.f21233a.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!GoLeaveHomeGroupConditionActivity.this.n.isEmpty() || GoLeaveHomeGroupConditionActivity.this.j != null) {
                        Intent intent = new Intent(GoLeaveHomeGroupConditionActivity.this.getContext(), BodySensorChooseActivity.class);
                        if (GoLeaveHomeGroupConditionActivity.this.j != null) {
                            intent.putExtra("extra_did", GoLeaveHomeGroupConditionActivity.this.j.c.f21523a);
                        }
                        GoLeaveHomeGroupConditionActivity.this.startActivityForResult(intent, 12288);
                        return;
                    }
                    GoLeaveHomeGroupConditionActivity.this.showBuyDialog();
                }
            });
            viewHolder3.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(final CompoundButton compoundButton, boolean z) {
                    if (compoundButton.isEnabled()) {
                        compoundButton.setEnabled(false);
                        if (GoLeaveHomeGroupConditionActivity.this.n.isEmpty()) {
                            GoLeaveHomeGroupConditionActivity.this.mHandler.post(new Runnable() {
                                public void run() {
                                    compoundButton.setChecked(false);
                                }
                            });
                            GoLeaveHomeGroupConditionActivity.this.showBuyDialog();
                        } else if (GoLeaveHomeGroupConditionActivity.this.j == null) {
                            GoLeaveHomeGroupConditionActivity.this.startActivityForResult(new Intent(GoLeaveHomeGroupConditionActivity.this.getContext(), BodySensorChooseActivity.class), 12288);
                        } else {
                            GoLeaveHomeGroupConditionActivity.this.j.l = z;
                        }
                        GoLeaveHomeGroupConditionActivity.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                compoundButton.setEnabled(true);
                            }
                        }, 400);
                    }
                }
            });
        }
        if (f()) {
            this.f = g();
            if (this.f != null) {
                ViewHolder viewHolder4 = (ViewHolder) this.f.getTag();
                viewHolder4.d.setText(R.string.mi_camera);
                viewHolder4.c.setVisibility(0);
                if (f()) {
                    viewHolder4.e.setText(R.string.mi_camera_change);
                } else {
                    viewHolder4.e.setText(R.string.mi_camera_no_change);
                }
                Device k3 = DeviceFactory.k("yunyi.camera.v1");
                if (k3 != null) {
                    DeviceFactory.b(k3.model, viewHolder4.b);
                }
                SwitchButton switchButton = viewHolder4.c;
                if (this.k == null || !this.k.l) {
                    z = false;
                }
                switchButton.setChecked(z);
                viewHolder4.f21233a.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (GoLeaveHomeGroupConditionActivity.this.o.isEmpty()) {
                            GoLeaveHomeGroupConditionActivity.this.showBuyDialog();
                            return;
                        }
                        Intent intent = new Intent(GoLeaveHomeGroupConditionActivity.this.getContext(), CameraChooseActivity.class);
                        if (!(GoLeaveHomeGroupConditionActivity.this.k == null || GoLeaveHomeGroupConditionActivity.this.k.c == null)) {
                            intent.putExtra("extra_did", GoLeaveHomeGroupConditionActivity.this.k.c.f21523a);
                        }
                        GoLeaveHomeGroupConditionActivity.this.startActivityForResult(intent, 12290);
                    }
                });
                viewHolder4.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(final CompoundButton compoundButton, boolean z) {
                        if (compoundButton.isEnabled()) {
                            compoundButton.setEnabled(false);
                            if (GoLeaveHomeGroupConditionActivity.this.o.isEmpty()) {
                                GoLeaveHomeGroupConditionActivity.this.mHandler.post(new Runnable() {
                                    public void run() {
                                        compoundButton.setChecked(false);
                                    }
                                });
                                GoLeaveHomeGroupConditionActivity.this.showBuyDialog();
                            } else if (GoLeaveHomeGroupConditionActivity.this.k == null) {
                                GoLeaveHomeGroupConditionActivity.this.startActivityForResult(new Intent(GoLeaveHomeGroupConditionActivity.this.getContext(), BodySensorChooseActivity.class), 12288);
                            } else {
                                GoLeaveHomeGroupConditionActivity.this.k.l = z;
                            }
                            GoLeaveHomeGroupConditionActivity.this.mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    compoundButton.setEnabled(true);
                                }
                            }, 400);
                        }
                    }
                });
            }
        }
        this.mConditionAdapter = new ConditionAdapter();
        this.f21213a.setAdapter(this.mConditionAdapter);
    }

    /* access modifiers changed from: package-private */
    public void showBuyDialog() {
        new MLAlertDialog.Builder(this).b((int) R.string.no_device_title).a((int) R.string.go_to_buy, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                GoLeaveHomeGroupConditionActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://m.mi.com")));
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).d();
    }

    private boolean f() {
        return this.g;
    }

    private class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public View f21233a;
        public SimpleDraweeView b;
        public SwitchButton c;
        public TextView d;
        public TextView e;
        public TextView f;
        public ImageView g;
        public TextView h;
        public TextView i;

        private ViewHolder() {
        }
    }

    private View g() {
        View inflate = getLayoutInflater().inflate(R.layout.scene_add_scene_condition_item, (ViewGroup) null);
        if (inflate != null) {
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.b = (SimpleDraweeView) inflate.findViewById(R.id.icon);
            viewHolder.b.setHierarchy(new GenericDraweeHierarchyBuilder(viewHolder.b.getResources()).setFadeDuration(200).setPlaceholderImage(viewHolder.b.getResources().getDrawable(R.drawable.device_list_phone_no)).build());
            viewHolder.f21233a = inflate.findViewById(R.id.content_container);
            viewHolder.c = (SwitchButton) inflate.findViewById(R.id.item_enable);
            viewHolder.f = (TextView) inflate.findViewById(R.id.add_timesp);
            viewHolder.f.setVisibility(8);
            viewHolder.g = (ImageView) inflate.findViewById(R.id.set_time_btn);
            viewHolder.g.setVisibility(8);
            viewHolder.d = (TextView) inflate.findViewById(R.id.name);
            viewHolder.e = (TextView) inflate.findViewById(R.id.key_name);
            viewHolder.h = (TextView) inflate.findViewById(R.id.offline);
            viewHolder.h.setVisibility(8);
            viewHolder.i = (TextView) inflate.findViewById(R.id.buy_button);
            viewHolder.i.setVisibility(8);
            inflate.setTag(viewHolder);
        }
        return inflate;
    }

    class ConditionAdapter extends BaseAdapter {
        public int getCount() {
            return 0;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }

        ConditionAdapter() {
        }
    }
}
