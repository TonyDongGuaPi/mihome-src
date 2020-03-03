package com.xiaomi.smarthome.miio.page.deviceophistory;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import java.util.ArrayList;
import java.util.List;

public class DeviceOpHistoryListActivity extends BaseActivity {
    private static final int c = 1;
    private static final int d = 2;

    /* renamed from: a  reason: collision with root package name */
    private XQProgressDialog f19740a;
    private SimpleAdapter b;
    @BindView(2131428501)
    TextView commonWhiteEmptyText;
    @BindView(2131428502)
    TextView commonWhiteEmptyText2;
    @BindView(2131428503)
    LinearLayout commonWhiteEmptyView;
    private Handler e = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    DeviceOpHistoryListActivity.this.b();
                    return;
                case 2:
                    DeviceOpHistoryListActivity.this.c();
                    return;
                default:
                    return;
            }
        }
    };
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

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_op_history_list);
        ButterKnife.bind((Activity) this);
        d();
        a();
    }

    private void a() {
        PluginDeviceInfo c2;
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        if (d2 == null || d2.isEmpty()) {
            b();
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < d2.size(); i++) {
            Device device = d2.get(i);
            PluginRecord d3 = CoreApi.a().d(device.model);
            if (!(d3 == null || (c2 = d3.c()) == null || c2.F() != 1)) {
                arrayList.add(device);
            }
        }
        if (arrayList.size() == 0) {
            b();
            return;
        }
        this.b.a(arrayList);
        c();
    }

    /* access modifiers changed from: private */
    public void b() {
        this.commonWhiteEmptyView.setVisibility(0);
        this.list.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void c() {
        this.commonWhiteEmptyView.setVisibility(8);
        this.list.setVisibility(0);
        this.b.notifyDataSetChanged();
        try {
            this.list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.common_list_space_empty, this.list, false));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
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

    private void d() {
        this.b = new SimpleAdapter();
        this.list.setAdapter(this.b);
        ((TextView) this.commonWhiteEmptyView.findViewById(R.id.common_white_empty_text)).setText(R.string.no_data_tips);
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.device_op_history);
    }

    private void e() {
        this.f19740a = new XQProgressDialog(this);
        this.f19740a.setCancelable(false);
        this.f19740a.setMessage(getResources().getString(R.string.loading_share_info));
        this.f19740a.show();
        this.f19740a.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
    }

    private void f() {
        if (this.f19740a != null) {
            this.f19740a.dismiss();
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
                view = LayoutInflater.from(DeviceOpHistoryListActivity.this).inflate(R.layout.device_auth_master_list_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f19745a = (TextView) view.findViewById(R.id.title);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final Device device = this.b.get(i);
            viewHolder.f19745a.setText(device.getName());
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DeviceOpHistoryActivity.openOpHistoryActivity(DeviceOpHistoryListActivity.this, device.did);
                }
            });
            if (i == getCount() - 1) {
                view.setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
            } else {
                view.setBackgroundResource(R.drawable.common_white_list_padding);
            }
            return view;
        }

        private class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f19745a;

            private ViewHolder() {
            }
        }
    }
}
