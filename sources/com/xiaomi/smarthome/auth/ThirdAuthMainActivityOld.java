package com.xiaomi.smarthome.auth;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.ui.LoginMiuiActivity;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.common.widget.ListViewWithFixedHeight;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.wificonfig.WifiScanHomelog;
import java.util.ArrayList;
import java.util.List;

public class ThirdAuthMainActivityOld extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13849a = "ThirdAuthMainActivity";
    String appId;
    private String b;
    private String c;
    private boolean d;
    final SmartHomeDeviceManager.IClientDeviceListener listener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void a(int i) {
            Miio.b(ThirdAuthMainActivityOld.f13849a, "onRefreshClientDeviceSuccess" + i);
            if (i == 3) {
                if (AuthManager.h().i()) {
                    ThirdAuthMainActivityOld.this.a(true);
                } else {
                    ThirdAuthMainActivityOld.this.a(false);
                }
                if (ThirdAuthMainActivityOld.this.mDevices != null) {
                    ThirdAuthMainActivityOld.this.mIsSelectedDevice = new boolean[ThirdAuthMainActivityOld.this.mDevices.size()];
                    for (int i2 = 0; i2 < ThirdAuthMainActivityOld.this.mIsSelectedDevice.length; i2++) {
                        ThirdAuthMainActivityOld.this.mIsSelectedDevice[i2] = true;
                    }
                    ThirdAuthMainActivityOld.this.mAdapter.notifyDataSetChanged();
                    SmartHomeDeviceManager.a().c(ThirdAuthMainActivityOld.this.listener);
                }
            }
        }

        public void b(int i) {
            Miio.b(ThirdAuthMainActivityOld.f13849a, "onRefreshClientDeviceFailed" + i);
            if (i == 3) {
                if (AuthManager.h().i()) {
                    ThirdAuthMainActivityOld.this.a(true);
                } else {
                    ThirdAuthMainActivityOld.this.a(false);
                }
                if (ThirdAuthMainActivityOld.this.mDevices != null) {
                    ThirdAuthMainActivityOld.this.mIsSelectedDevice = new boolean[ThirdAuthMainActivityOld.this.mDevices.size()];
                    for (int i2 = 0; i2 < ThirdAuthMainActivityOld.this.mIsSelectedDevice.length; i2++) {
                        ThirdAuthMainActivityOld.this.mIsSelectedDevice[i2] = true;
                    }
                    ThirdAuthMainActivityOld.this.mAdapter.notifyDataSetChanged();
                    SmartHomeDeviceManager.a().c(ThirdAuthMainActivityOld.this.listener);
                }
            }
        }
    };
    AuthAdapter mAdapter;
    @BindView(2131427691)
    TextView mAppDescTV;
    Bitmap mAppIconBM;
    @BindView(2131427692)
    SimpleDraweeView mAppIconIV;
    @BindView(2131427694)
    TextView mAppNameTV;
    @BindView(2131427732)
    TextView mAuthAgreeTV;
    @BindView(2131427733)
    TextView mAuthCancelTV;
    @BindView(2131427738)
    TextView mAuthDesc;
    @BindView(2131430969)
    ImageView mBackIV;
    List<Device> mDevices = new ArrayList();
    boolean[] mIsSelectedDevice;
    @BindView(2131427750)
    ListViewWithFixedHeight mListView;
    @BindView(2131428358)
    CheckBox mSelectAll;
    @BindView(2131430975)
    TextView mTitleTV;

    class AuthAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        Context f13861a;

        public void a() {
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public class ViewHolder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private ViewHolder f13864a;

            @UiThread
            public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
                this.f13864a = viewHolder;
                viewHolder.mImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.image, "field 'mImage'", SimpleDraweeView.class);
                viewHolder.mNameTV = (TextView) Utils.findRequiredViewAsType(view, R.id.name, "field 'mNameTV'", TextView.class);
                viewHolder.mCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ckb_edit_selected, "field 'mCheckBox'", CheckBox.class);
            }

            @CallSuper
            public void unbind() {
                ViewHolder viewHolder = this.f13864a;
                if (viewHolder != null) {
                    this.f13864a = null;
                    viewHolder.mImage = null;
                    viewHolder.mNameTV = null;
                    viewHolder.mCheckBox = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public AuthAdapter(Context context) {
            this.f13861a = context;
        }

        public int getCount() {
            return ThirdAuthMainActivityOld.this.mDevices.size();
        }

        public Object getItem(int i) {
            return ThirdAuthMainActivityOld.this.mDevices.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(this.f13861a).inflate(R.layout.activity_auth_main_item, (ViewGroup) null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.mImage.setHierarchy(new GenericDraweeHierarchyBuilder(viewHolder.mImage.getResources()).setFadeDuration(200).setPlaceholderImage(viewHolder.mImage.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
            Device device = ThirdAuthMainActivityOld.this.mDevices.get(i);
            if (device != null) {
                DeviceFactory.b(device.model, viewHolder.mImage);
            } else {
                DeviceFactory.b((String) null, viewHolder.mImage);
            }
            viewHolder.mNameTV.setText(device.getName());
            viewHolder.mCheckBox.setChecked(ThirdAuthMainActivityOld.this.mIsSelectedDevice[i]);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ThirdAuthMainActivityOld.this.mIsSelectedDevice[i] = !ThirdAuthMainActivityOld.this.mIsSelectedDevice[i];
                    viewHolder.mCheckBox.setChecked(ThirdAuthMainActivityOld.this.mIsSelectedDevice[i]);
                }
            });
            return view;
        }

        class ViewHolder {
            @BindView(2131428355)
            CheckBox mCheckBox;
            @BindView(2131429757)
            SimpleDraweeView mImage;
            @BindView(2131431126)
            TextView mNameTV;

            public ViewHolder(View view) {
                ButterKnife.bind((Object) this, view);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_auth_main_layout);
        ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        if (intent != null) {
            this.appId = intent.getStringExtra(AuthConstants.f13798a);
            this.b = intent.getStringExtra(AuthConstants.b);
            this.c = intent.getStringExtra(AuthConstants.c);
            if (TextUtils.isEmpty(this.appId)) {
                setResult(-2, AuthCode.b(-104));
                finish();
            }
            if (!AuthManager.h().e(this.b)) {
                setResult(-2, AuthCode.b(-100));
                finish();
            }
            if (TextUtils.isEmpty(this.c)) {
                setResult(-2, AuthCode.b(-105));
                finish();
            }
            AuthManager.h().c(this.c);
            AuthManager.h().b(this.b);
            AuthManager.h().a(this.appId);
        } else {
            setResult(-2, AuthCode.b(-101));
            finish();
        }
        StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
            public void a() {
            }

            public void c() {
            }

            public void b() {
                ThirdAuthMainActivityOld.this.finish();
            }

            public void d() {
                ThirdAuthMainActivityOld.this.finish();
            }

            public void e() {
                CoreApi.a().a((Context) ThirdAuthMainActivityOld.this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                    public void onCoreReady() {
                        ThirdAuthMainActivityOld.this.a();
                    }
                });
            }
        });
        this.mAdapter = new AuthAdapter(this);
        this.mListView.setAdapter(this.mAdapter);
    }

    /* access modifiers changed from: private */
    public void a() {
        if (!CoreApi.a().q()) {
            new MLAlertDialog.Builder(this).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ThirdAuthMainActivityOld.this.b();
                    dialogInterface.dismiss();
                    ThirdAuthMainActivityOld.this.finish();
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    ThirdAuthMainActivityOld.this.finish();
                }
            }).a(true).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    ThirdAuthMainActivityOld.this.finish();
                }
            }).b((int) R.string.loing_helper_title).d();
            return;
        }
        c();
        e();
    }

    /* access modifiers changed from: private */
    public void b() {
        MultiHomeDeviceManager.a().b();
        SmartHomeDeviceManager.a().v();
        SceneManager.x().A();
        SmartHomeDeviceManager.a().p();
        sendBroadcast(new Intent(WifiScanHomelog.c));
        startActivity(new Intent(this, LoginMiuiActivity.class));
    }

    private void c() {
        AuthManager.h().a((AsyncCallback) new AsyncCallback() {
            public void onSuccess(Object obj) {
                ThirdAuthMainActivityOld.this.mAppNameTV.setText(AuthManager.h().a().f13800a);
                ThirdAuthMainActivityOld.this.mAppDescTV.setText(AuthManager.h().a().b);
                ThirdAuthMainActivityOld.this.mAppIconIV.setHierarchy(new GenericDraweeHierarchyBuilder(ThirdAuthMainActivityOld.this.getResources()).setFadeDuration(200).setPlaceholderImage(ThirdAuthMainActivityOld.this.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setRoundingParams(RoundingParams.fromCornersRadius(20.0f)).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
                ThirdAuthMainActivityOld.this.d();
                if (AuthManager.h().i()) {
                    if (!SmartHomeDeviceManager.a().u() || SmartHomeDeviceManager.a().t()) {
                        SmartHomeDeviceManager.a().a(ThirdAuthMainActivityOld.this.listener);
                        SmartHomeDeviceManager.a().p();
                    } else {
                        ThirdAuthMainActivityOld.this.a(true);
                    }
                } else if (!SmartHomeDeviceManager.a().u() || SmartHomeDeviceManager.a().t()) {
                    SmartHomeDeviceManager.a().a(ThirdAuthMainActivityOld.this.listener);
                    SmartHomeDeviceManager.a().p();
                } else {
                    ThirdAuthMainActivityOld.this.a(false);
                }
                if (ThirdAuthMainActivityOld.this.mDevices != null) {
                    ThirdAuthMainActivityOld.this.mIsSelectedDevice = new boolean[ThirdAuthMainActivityOld.this.mDevices.size()];
                    for (int i = 0; i < ThirdAuthMainActivityOld.this.mIsSelectedDevice.length; i++) {
                        ThirdAuthMainActivityOld.this.mIsSelectedDevice[i] = true;
                    }
                    ThirdAuthMainActivityOld.this.mAdapter.notifyDataSetChanged();
                }
            }

            public void onFailure(Error error) {
                Toast.makeText(ThirdAuthMainActivityOld.this, "获取数据失败", 0).show();
                ThirdAuthMainActivityOld.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        ThirdAuthMainActivityOld.this.setResult(-2, AuthCode.b(-104));
                        ThirdAuthMainActivityOld.this.finish();
                    }
                }, 1000);
            }
        }, true);
    }

    /* access modifiers changed from: private */
    public void d() {
        if (!TextUtils.isEmpty(AuthManager.h().a().c)) {
            this.mAppIconIV.setImageURI(Uri.parse(AuthManager.h().a().c));
        }
    }

    private void e() {
        this.mTitleTV.setText(R.string.auth_title);
        UserMamanger.a().a((AsyncResponseCallback<ShareUserRecord>) new AsyncResponseCallback<ShareUserRecord>() {
            public void a(int i) {
            }

            public void a(int i, Object obj) {
            }

            public void a(ShareUserRecord shareUserRecord) {
                ThirdAuthMainActivityOld.this.mAuthDesc.setText(String.format(ThirdAuthMainActivityOld.this.getString(R.string.auth_tip_format), new Object[]{XMStringUtils.c(shareUserRecord.nickName), CoreApi.a().s()}));
            }
        });
        this.mAuthCancelTV.setOnClickListener(this);
        this.mAuthAgreeTV.setOnClickListener(this);
        this.mSelectAll.setOnClickListener(this);
        this.mSelectAll.setChecked(true);
        this.d = true;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.ckb_select_all) {
            switch (id) {
                case R.id.auth_agree:
                    g();
                    return;
                case R.id.auth_cancel:
                    finish();
                    return;
                default:
                    return;
            }
        } else {
            f();
        }
    }

    private void f() {
        if (this.d) {
            this.mSelectAll.setChecked(false);
            for (int i = 0; i < this.mIsSelectedDevice.length; i++) {
                this.mIsSelectedDevice[i] = false;
            }
            this.mAdapter.notifyDataSetChanged();
            this.d = false;
            return;
        }
        this.mSelectAll.setChecked(true);
        for (int i2 = 0; i2 < this.mIsSelectedDevice.length; i2++) {
            this.mIsSelectedDevice[i2] = true;
        }
        this.mAdapter.notifyDataSetChanged();
        this.d = true;
    }

    private void g() {
        StringBuilder sb = new StringBuilder();
        if (this.mIsSelectedDevice != null && this.mIsSelectedDevice.length == this.mDevices.size()) {
            int i = 0;
            for (int i2 = 0; i2 < this.mIsSelectedDevice.length; i2++) {
                if (this.mIsSelectedDevice[i2]) {
                    if (i == 0) {
                        sb.append("4");
                        sb.append(" ");
                        sb.append(this.mDevices.get(i2).did);
                    } else {
                        sb.append(",");
                        sb.append("4");
                        sb.append(" ");
                        sb.append(this.mDevices.get(i2).did);
                    }
                    if (AuthManager.h().j()) {
                        sb.append(",");
                        sb.append("5");
                        sb.append(" ");
                        sb.append(this.mDevices.get(i2).did);
                    }
                    i++;
                }
            }
        }
        AuthManager.h().a(sb.toString(), (AsyncCallback) new AsyncCallback() {
            public void onSuccess(Object obj) {
                Toast.makeText(ThirdAuthMainActivityOld.this, R.string.auth_success, 0).show();
                AuthManager.h().a("renlei", "https://openapp.io.mi.com", new AsyncCallback() {
                    public void onSuccess(Object obj) {
                    }

                    public void onFailure(Error error) {
                        if (error.a() == 404 && !TextUtils.isEmpty(error.c())) {
                            String c = error.c();
                            if (c.contains("access_token")) {
                                String token = ThirdAuthMainActivityOld.this.getToken(c);
                                if (!TextUtils.isEmpty(token)) {
                                    Intent b = AuthCode.b(100);
                                    b.putExtra(AuthConstants.m, token);
                                    ThirdAuthMainActivityOld.this.setResult(-1, b);
                                    ThirdAuthMainActivityOld.this.finish();
                                    return;
                                }
                            }
                        }
                        ThirdAuthMainActivityOld thirdAuthMainActivityOld = ThirdAuthMainActivityOld.this;
                        Toast.makeText(thirdAuthMainActivityOld, "获取token失败" + error.b(), 0).show();
                        ThirdAuthMainActivityOld.this.setResult(-2, AuthCode.b(-102));
                        ThirdAuthMainActivityOld.this.finish();
                    }
                });
            }

            public void onFailure(Error error) {
                ThirdAuthMainActivityOld thirdAuthMainActivityOld = ThirdAuthMainActivityOld.this;
                Toast.makeText(thirdAuthMainActivityOld, ThirdAuthMainActivityOld.this.getResources().getString(R.string.auth_fail) + error.b(), 0).show();
                ThirdAuthMainActivityOld.this.setResult(-2, AuthCode.b(-103));
                ThirdAuthMainActivityOld.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        Miio.b("AuthManager", "initDevices--checkCanAuth--" + z);
        Miio.b("AuthManager", SmartHomeDeviceManager.a().d().size() + "subsize" + SmartHomeDeviceManager.a().k().size());
        this.mDevices.clear();
        if (z) {
            for (int i = 0; i < SmartHomeDeviceManager.a().d().size(); i++) {
                Device device = SmartHomeDeviceManager.a().d().get(i);
                if (AuthManager.h().d(device.did)) {
                    this.mDevices.add(device);
                }
            }
            for (int i2 = 0; i2 < SmartHomeDeviceManager.a().k().size(); i2++) {
                Device device2 = SmartHomeDeviceManager.a().k().get(i2);
                if (AuthManager.h().d(device2.did)) {
                    this.mDevices.add(device2);
                }
            }
        } else {
            this.mDevices.addAll(SmartHomeDeviceManager.a().d());
            this.mDevices.addAll(SmartHomeDeviceManager.a().k());
        }
        Miio.b("AuthManager", "initDevices end mDevices.size()----" + this.mDevices.size());
    }

    public String getToken(String str) {
        String substring = str.substring(str.indexOf("access_token=") + 13, str.indexOf("&state="));
        Miio.b(f13849a, "getToken result--" + substring + "----start--" + str.indexOf("access_token=") + "---end--" + str.indexOf("&state="));
        return substring;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
