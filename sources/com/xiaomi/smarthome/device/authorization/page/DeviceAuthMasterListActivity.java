package com.xiaomi.smarthome.device.authorization.page;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.auth.AuthManager;
import com.xiaomi.smarthome.auth.bindaccount.ThirdAccountBindManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeviceAuthMasterListActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15034a = "DeviceAuthMasterListActivity";
    public static Set<String> denyModels = new HashSet();
    private XQProgressDialog b;
    private SimpleAdapter c;
    @BindView(2131428501)
    TextView commonWhiteEmptyText;
    @BindView(2131428502)
    TextView commonWhiteEmptyText2;
    @BindView(2131428503)
    LinearLayout commonWhiteEmptyView;
    private AtomicBoolean d = new AtomicBoolean(false);
    @BindView(2131428999)
    ImageView emptyIcon;
    @BindView(2131430457)
    PullDownDragListView list;
    @BindView(2131430969)
    ImageView moduleA3ReturnBtn;
    @BindView(2131430971)
    ImageView moduleA3ReturnMoreMoreBtn;
    @BindView(2131430975)
    TextView moduleA3ReturnTitle;
    @BindView(2131432919)
    FrameLayout titleBar;

    public static void addLocalDeny() {
        if (denyModels == null) {
            denyModels = new HashSet();
        }
        denyModels.add("midr.watch.ds");
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_auth_list);
        ButterKnife.bind((Activity) this);
        AuthManager.h().l();
        e();
    }

    private static class MyAsyncCallback extends AsyncCallback<Void, Error> {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<DeviceAuthMasterListActivity> f15037a;

        private MyAsyncCallback(DeviceAuthMasterListActivity deviceAuthMasterListActivity) {
            this.f15037a = new WeakReference<>(deviceAuthMasterListActivity);
        }

        /* renamed from: a */
        public void onSuccess(Void voidR) {
            DeviceAuthMasterListActivity deviceAuthMasterListActivity = (DeviceAuthMasterListActivity) this.f15037a.get();
            if (deviceAuthMasterListActivity != null && deviceAuthMasterListActivity.isValid()) {
                deviceAuthMasterListActivity.b();
            }
        }

        public void onFailure(Error error) {
            DeviceAuthMasterListActivity deviceAuthMasterListActivity = (DeviceAuthMasterListActivity) this.f15037a.get();
            if (deviceAuthMasterListActivity != null && deviceAuthMasterListActivity.isValid()) {
                deviceAuthMasterListActivity.b();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        ThirdAccountBindManager.a().a((AsyncCallback<Void, Error>) new MyAsyncCallback());
    }

    /* access modifiers changed from: private */
    public void b() {
        if (isValid()) {
            List<Device> d2 = SmartHomeDeviceManager.a().d();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < d2.size(); i++) {
                Device device = d2.get(i);
                if (device != null && device.voiceCtrl > 0 && !denyModels.contains(device.model)) {
                    LogUtil.a(f15034a, device.voiceCtrl + " did:" + device.did + " name:" + device.name);
                    arrayList.add(device);
                }
            }
            if (arrayList.isEmpty()) {
                c();
                return;
            }
            if (!this.d.getAndSet(true) && arrayList.size() > 0) {
                try {
                    this.list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.activity_device_auth_list_header, this.list, false));
                } catch (Exception unused) {
                }
            }
            this.c.a(arrayList);
            d();
            this.list.postRefresh();
        }
    }

    private void c() {
        this.commonWhiteEmptyView.setVisibility(0);
        this.list.setVisibility(8);
    }

    private void d() {
        this.commonWhiteEmptyView.setVisibility(8);
        this.list.setVisibility(0);
        this.c.notifyDataSetChanged();
    }

    public boolean isValid() {
        if (isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 || !isDestroyed()) {
            return true;
        }
        return false;
    }

    private void e() {
        this.c = new SimpleAdapter();
        this.list.setAdapter(this.c);
        ((TextView) this.commonWhiteEmptyView.findViewById(R.id.common_white_empty_text)).setText(R.string.no_data_tips);
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.device_auth);
        this.list.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                LogUtil.a(DeviceAuthMasterListActivity.f15034a, "startRefresh");
                DeviceAuthMasterListActivity.this.a();
            }
        });
        this.list.doRefresh();
        this.commonWhiteEmptyView.setVisibility(8);
    }

    private void f() {
        this.b = new XQProgressDialog(this);
        this.b.setCancelable(false);
        this.b.setMessage(getResources().getString(R.string.loading_share_info));
        this.b.show();
        this.b.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
    }

    private void g() {
        if (this.b != null) {
            this.b.dismiss();
        }
    }

    @OnClick({2131430969, 2131428503})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.common_white_empty_view) {
            a();
        } else if (id == R.id.module_a_3_return_btn) {
            finish();
        }
    }

    private class SimpleAdapter extends BaseAdapter {
        private List<Device> b;

        public long getItemId(int i) {
            return 0;
        }

        private SimpleAdapter() {
            this.b = new ArrayList();
        }

        public void a(List<Device> list) {
            this.b.clear();
            this.b.addAll(list);
        }

        public int getCount() {
            return this.b.size();
        }

        public Object getItem(int i) {
            if (i < 0 || i >= this.b.size()) {
                return null;
            }
            return this.b.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(DeviceAuthMasterListActivity.this).inflate(R.layout.device_auth_master_list_item_v2, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f15040a = (TextView) view.findViewById(R.id.title);
                viewHolder.b = (TextView) view.findViewById(R.id.subtitle);
                viewHolder.c = (SimpleDraweeView) view.findViewById(R.id.icon);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final Device device = this.b.get(i);
            if (device == null) {
                return view;
            }
            if (i == getCount() - 1) {
                view.setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
            } else {
                view.setBackgroundResource(R.drawable.common_white_list_padding);
            }
            viewHolder.f15040a.setText(device.getName());
            Home q = HomeManager.a().q(device.did);
            if (q != null) {
                viewHolder.b.setText(q.k() + "-" + HomeManager.a().r(device.did));
            }
            DeviceFactory.b(device.model, viewHolder.c);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(DeviceAuthMasterListActivity.this, DeviceAuthSlaveListActivity.class);
                    intent.putExtra("device_id", device.did);
                    intent.putExtra(DeviceAuthSlaveListActivity.INTENT_KEY_SHOW_BOTTOM_BAR, false);
                    DeviceAuthMasterListActivity.this.startActivity(intent);
                }
            });
            if (i != getCount() - 1) {
                view.setBackgroundResource(R.drawable.common_white_list_padding);
            } else {
                view.setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
            }
            return view;
        }

        private class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f15040a;
            TextView b;
            SimpleDraweeView c;

            private ViewHolder() {
            }
        }
    }
}
