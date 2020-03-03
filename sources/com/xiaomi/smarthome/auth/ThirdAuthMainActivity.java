package com.xiaomi.smarthome.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
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
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.authlib.IAuthCallBack;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.common.widget.ListViewWithFixedHeight;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import java.util.ArrayList;
import java.util.List;

public class ThirdAuthMainActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13840a = "ThirdAuthMainActivity";
    String appId;
    private String b;
    private String c;
    private boolean d;
    /* access modifiers changed from: private */
    public IAuthCallBack e;
    final SmartHomeDeviceManager.IClientDeviceListener listener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void a(int i) {
            Miio.b(ThirdAuthMainActivity.f13840a, "onRefreshClientDeviceSuccess" + i);
            if (i == 3) {
                if (AuthManager.h().i()) {
                    ThirdAuthMainActivity.this.a(true);
                } else {
                    ThirdAuthMainActivity.this.a(false);
                }
                if (ThirdAuthMainActivity.this.mDevices != null) {
                    ThirdAuthMainActivity.this.mIsSelectedDevice = new boolean[ThirdAuthMainActivity.this.mDevices.size()];
                    for (int i2 = 0; i2 < ThirdAuthMainActivity.this.mIsSelectedDevice.length; i2++) {
                        ThirdAuthMainActivity.this.mIsSelectedDevice[i2] = true;
                    }
                    ThirdAuthMainActivity.this.mAdapter.notifyDataSetChanged();
                    SmartHomeDeviceManager.a().c(ThirdAuthMainActivity.this.listener);
                }
            }
        }

        public void b(int i) {
            Miio.b(ThirdAuthMainActivity.f13840a, "onRefreshClientDeviceFailed" + i);
            if (i == 3) {
                if (AuthManager.h().i()) {
                    ThirdAuthMainActivity.this.a(true);
                } else {
                    ThirdAuthMainActivity.this.a(false);
                }
                if (ThirdAuthMainActivity.this.mDevices != null) {
                    ThirdAuthMainActivity.this.mIsSelectedDevice = new boolean[ThirdAuthMainActivity.this.mDevices.size()];
                    for (int i2 = 0; i2 < ThirdAuthMainActivity.this.mIsSelectedDevice.length; i2++) {
                        ThirdAuthMainActivity.this.mIsSelectedDevice[i2] = true;
                    }
                    ThirdAuthMainActivity.this.mAdapter.notifyDataSetChanged();
                    SmartHomeDeviceManager.a().c(ThirdAuthMainActivity.this.listener);
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
        Context f13845a;

        public void a() {
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public class ViewHolder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private ViewHolder f13848a;

            @UiThread
            public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
                this.f13848a = viewHolder;
                viewHolder.mImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.image, "field 'mImage'", SimpleDraweeView.class);
                viewHolder.mNameTV = (TextView) Utils.findRequiredViewAsType(view, R.id.name, "field 'mNameTV'", TextView.class);
                viewHolder.mCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ckb_edit_selected, "field 'mCheckBox'", CheckBox.class);
            }

            @CallSuper
            public void unbind() {
                ViewHolder viewHolder = this.f13848a;
                if (viewHolder != null) {
                    this.f13848a = null;
                    viewHolder.mImage = null;
                    viewHolder.mNameTV = null;
                    viewHolder.mCheckBox = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public AuthAdapter(Context context) {
            this.f13845a = context;
        }

        public int getCount() {
            return ThirdAuthMainActivity.this.mDevices.size();
        }

        public Object getItem(int i) {
            return ThirdAuthMainActivity.this.mDevices.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(this.f13845a).inflate(R.layout.activity_auth_main_item, (ViewGroup) null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.mImage.setHierarchy(new GenericDraweeHierarchyBuilder(viewHolder.mImage.getResources()).setFadeDuration(200).setPlaceholderImage(viewHolder.mImage.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
            Device device = ThirdAuthMainActivity.this.mDevices.get(i);
            if (device != null) {
                DeviceFactory.b(device.model, viewHolder.mImage);
            } else {
                DeviceFactory.b((String) null, viewHolder.mImage);
            }
            viewHolder.mNameTV.setText(device.getName());
            viewHolder.mCheckBox.setChecked(ThirdAuthMainActivity.this.mIsSelectedDevice[i]);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ThirdAuthMainActivity.this.mIsSelectedDevice[i] = !ThirdAuthMainActivity.this.mIsSelectedDevice[i];
                    viewHolder.mCheckBox.setChecked(ThirdAuthMainActivity.this.mIsSelectedDevice[i]);
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
        this.mAdapter = new AuthAdapter(this);
        this.mListView.setAdapter(this.mAdapter);
        a();
        c();
    }

    private void a() {
        this.e = AuthManager.h().e();
        this.mAppNameTV.setText(AuthManager.h().a().f13800a);
        this.mAppDescTV.setText(AuthManager.h().a().b);
        this.mAppIconIV.setHierarchy(new GenericDraweeHierarchyBuilder(getResources()).setFadeDuration(200).setPlaceholderImage(getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setRoundingParams(RoundingParams.fromCornersRadius(20.0f)).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        b();
        if (AuthManager.h().i()) {
            if (!SmartHomeDeviceManager.a().u() || SmartHomeDeviceManager.a().t()) {
                SmartHomeDeviceManager.a().a(this.listener);
                SmartHomeDeviceManager.a().p();
            } else {
                a(true);
            }
        } else if (!SmartHomeDeviceManager.a().u() || SmartHomeDeviceManager.a().t()) {
            SmartHomeDeviceManager.a().a(this.listener);
            SmartHomeDeviceManager.a().p();
        } else {
            a(false);
        }
        if (this.mDevices != null) {
            this.mIsSelectedDevice = new boolean[this.mDevices.size()];
            for (int i = 0; i < this.mIsSelectedDevice.length; i++) {
                this.mIsSelectedDevice[i] = true;
            }
            this.mAdapter.notifyDataSetChanged();
        }
    }

    private void b() {
        if (!TextUtils.isEmpty(AuthManager.h().a().c)) {
            this.mAppIconIV.setImageURI(Uri.parse(AuthManager.h().a().c));
        }
    }

    private void c() {
        this.mTitleTV.setText(R.string.auth_title);
        UserMamanger.a().a((AsyncResponseCallback<ShareUserRecord>) new AsyncResponseCallback<ShareUserRecord>() {
            public void a(int i) {
            }

            public void a(int i, Object obj) {
            }

            public void a(ShareUserRecord shareUserRecord) {
                ThirdAuthMainActivity.this.mAuthDesc.setText(String.format(ThirdAuthMainActivity.this.getString(R.string.auth_tip_format), new Object[]{XMStringUtils.c(shareUserRecord.nickName), CoreApi.a().s()}));
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
                    f();
                    return;
                case R.id.auth_cancel:
                    d();
                    return;
                default:
                    return;
            }
        } else {
            e();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        finish();
    }

    private void e() {
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

    private void f() {
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
                Toast.makeText(ThirdAuthMainActivity.this, R.string.auth_success, 0).show();
                AuthManager.h().a("renlei", "https://openapp.io.mi.com", new AsyncCallback() {
                    public void onSuccess(Object obj) {
                    }

                    public void onFailure(Error error) {
                        if (error.a() == 404 && !TextUtils.isEmpty(error.c())) {
                            String c = error.c();
                            if (c.contains("access_token")) {
                                String token = ThirdAuthMainActivity.this.getToken(c);
                                if (!TextUtils.isEmpty(token)) {
                                    Bundle a2 = AuthCode.a(100);
                                    a2.putString(AuthConstants.m, token);
                                    if (ThirdAuthMainActivity.this.e != null) {
                                        try {
                                            ThirdAuthMainActivity.this.e.onSuccess(100, a2);
                                        } catch (RemoteException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    ThirdAuthMainActivity.this.d();
                                    return;
                                }
                            }
                        }
                        if (ThirdAuthMainActivity.this.e != null) {
                            try {
                                ThirdAuthMainActivity.this.e.onFail(-102, AuthCode.a(-102));
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                            }
                        }
                        ThirdAuthMainActivity thirdAuthMainActivity = ThirdAuthMainActivity.this;
                        Toast.makeText(thirdAuthMainActivity, "获取token失败" + error.b(), 0).show();
                        ThirdAuthMainActivity.this.d();
                    }
                });
            }

            public void onFailure(Error error) {
                ThirdAuthMainActivity thirdAuthMainActivity = ThirdAuthMainActivity.this;
                Toast.makeText(thirdAuthMainActivity, ThirdAuthMainActivity.this.getResources().getString(R.string.auth_fail) + error.b(), 0).show();
                if (ThirdAuthMainActivity.this.e != null) {
                    try {
                        ThirdAuthMainActivity.this.e.onFail(-103, AuthCode.a(-103));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                ThirdAuthMainActivity.this.d();
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
        Miio.b(f13840a, "getToken result--" + substring + "----start--" + str.indexOf("access_token=") + "---end--" + str.indexOf("&state="));
        return substring;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
