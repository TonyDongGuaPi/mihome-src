package com.xiaomi.smarthome.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.ListViewWithFixedHeight;
import com.xiaomi.smarthome.miio.Miio;
import java.util.ArrayList;
import java.util.List;

public class AuthManagerDetailActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13813a = "AuthManagerDetailActivity";
    private List<AuthAppInfo> b = new ArrayList();
    /* access modifiers changed from: private */
    public String c;
    final SmartHomeDeviceManager.IClientDeviceListener listener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void a(int i) {
            if (i == 3) {
                if (AuthManager.h().i()) {
                    AuthManagerDetailActivity.this.a(true);
                } else {
                    AuthManagerDetailActivity.this.a(false);
                }
                if (AuthManagerDetailActivity.this.mDevices != null) {
                    AuthManagerDetailActivity.this.mAdapter.notifyDataSetChanged();
                    SmartHomeDeviceManager.a().c(AuthManagerDetailActivity.this.listener);
                }
            }
        }

        public void b(int i) {
            if (i == 3) {
                if (AuthManager.h().i()) {
                    AuthManagerDetailActivity.this.a(true);
                } else {
                    AuthManagerDetailActivity.this.a(false);
                }
                if (AuthManagerDetailActivity.this.mDevices != null) {
                    AuthManagerDetailActivity.this.mAdapter.notifyDataSetChanged();
                    SmartHomeDeviceManager.a().c(AuthManagerDetailActivity.this.listener);
                }
            }
        }
    };
    AuthAdapter mAdapter;
    @BindView(2131427741)
    TextView mAppAuthTimeTV;
    @BindView(2131429757)
    SimpleDraweeView mAppIcon;
    @BindView(2131431126)
    TextView mAppNameTV;
    @BindView(2131430969)
    ImageView mBackIV;
    @BindView(2131428724)
    TextView mDeleteAuth;
    List<Device> mDevices = new ArrayList();
    @BindView(2131427739)
    ListViewWithFixedHeight mListView;
    @BindView(2131430975)
    TextView mTitleTV;

    class AuthAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        Context f13819a;

        public void a() {
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public class ViewHolder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private ViewHolder f13821a;

            @UiThread
            public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
                this.f13821a = viewHolder;
                viewHolder.mImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.image, "field 'mImage'", SimpleDraweeView.class);
                viewHolder.mNameTV = (TextView) Utils.findRequiredViewAsType(view, R.id.name, "field 'mNameTV'", TextView.class);
            }

            @CallSuper
            public void unbind() {
                ViewHolder viewHolder = this.f13821a;
                if (viewHolder != null) {
                    this.f13821a = null;
                    viewHolder.mImage = null;
                    viewHolder.mNameTV = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public AuthAdapter(Context context) {
            this.f13819a = context;
        }

        public int getCount() {
            return AuthManagerDetailActivity.this.mDevices.size();
        }

        public Object getItem(int i) {
            return AuthManagerDetailActivity.this.mDevices.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(this.f13819a).inflate(R.layout.activity_auth_manager_detail_item, (ViewGroup) null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.mImage.setHierarchy(new GenericDraweeHierarchyBuilder(viewHolder.mImage.getResources()).setFadeDuration(200).setPlaceholderImage(viewHolder.mImage.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
            Device device = AuthManagerDetailActivity.this.mDevices.get(i);
            if (device != null) {
                DeviceFactory.b(device.model, viewHolder.mImage);
            } else {
                DeviceFactory.b((String) null, viewHolder.mImage);
            }
            viewHolder.mNameTV.setText(device.getName());
            return view;
        }

        class ViewHolder {
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
        setContentView(R.layout.activity_auth_manager_detail_layout);
        ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getStringExtra(AuthConstants.f13798a);
            AuthManager.h().a(this.c);
        } else {
            setResult(-100, (Intent) null);
            finish();
        }
        this.mListView.addFooterView(LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.scene_all_activity_placehold, (ViewGroup) null));
        this.mAdapter = new AuthAdapter(this);
        this.mListView.setAdapter(this.mAdapter);
        a();
        b();
    }

    private void a() {
        AuthManager.h().a((AsyncCallback) new AsyncCallback() {
            public void onSuccess(Object obj) {
                AuthManagerDetailActivity.this.mAppNameTV.setText(AuthManager.h().a().f13800a);
                AuthManagerDetailActivity.this.mAppAuthTimeTV.setText(AuthManager.h().a().b);
                AuthManagerDetailActivity.this.mAppIcon.setHierarchy(new GenericDraweeHierarchyBuilder(AuthManagerDetailActivity.this.getResources()).setFadeDuration(200).setPlaceholderImage(AuthManagerDetailActivity.this.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setRoundingParams(RoundingParams.fromCornersRadius(20.0f)).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
                if (!TextUtils.isEmpty(AuthManager.h().a().c)) {
                    AuthManagerDetailActivity.this.mAppIcon.setImageURI(Uri.parse(AuthManager.h().a().c));
                }
                if (AuthManager.h().i()) {
                    if (!SmartHomeDeviceManager.a().u() || SmartHomeDeviceManager.a().t()) {
                        SmartHomeDeviceManager.a().a(AuthManagerDetailActivity.this.listener);
                        SmartHomeDeviceManager.a().p();
                    } else {
                        AuthManagerDetailActivity.this.a(true);
                    }
                } else if (!SmartHomeDeviceManager.a().u() || SmartHomeDeviceManager.a().t()) {
                    SmartHomeDeviceManager.a().a(AuthManagerDetailActivity.this.listener);
                    SmartHomeDeviceManager.a().p();
                } else {
                    AuthManagerDetailActivity.this.a(false);
                }
                if (AuthManagerDetailActivity.this.mDevices != null) {
                    AuthManagerDetailActivity.this.mAdapter.notifyDataSetChanged();
                }
            }

            public void onFailure(Error error) {
                Toast.makeText(AuthManagerDetailActivity.this, "获取数据失败", 0).show();
            }
        }, false);
    }

    private void b() {
        this.mTitleTV.setText(R.string.auth_manager);
        this.mBackIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AuthManagerDetailActivity.this.setResult(0, (Intent) null);
                AuthManagerDetailActivity.this.finish();
            }
        });
        this.mDeleteAuth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AuthManager.h().b((AsyncCallback) new AsyncCallback() {
                    public void onSuccess(Object obj) {
                        Toast.makeText(AuthManagerDetailActivity.this, AuthManagerDetailActivity.this.getString(R.string.delete_auth_success), 0).show();
                        Intent intent = new Intent();
                        intent.putExtra(AuthConstants.f13798a, AuthManagerDetailActivity.this.c);
                        AuthManagerDetailActivity.this.setResult(-1, intent);
                        AuthManagerDetailActivity.this.finish();
                    }

                    public void onFailure(Error error) {
                        Toast.makeText(AuthManagerDetailActivity.this, AuthManagerDetailActivity.this.getString(R.string.delete_auth_fail), 0).show();
                    }
                });
            }
        });
    }

    public void onBackPressed() {
        setResult(0, (Intent) null);
        finish();
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        Miio.b(f13813a, "initDevices--checkCanAuth--" + z);
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
            return;
        }
        this.mDevices.addAll(SmartHomeDeviceManager.a().d());
        this.mDevices.addAll(SmartHomeDeviceManager.a().k());
    }
}
