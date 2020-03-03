package com.xiaomi.smarthome.miio.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.navigate.NavigateUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.DragListView;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.page.ShareDeviceDetail;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShareDeviceInfoActivity extends BaseActivity {
    public static final String PARAM_KEY_USERID = "user_id";

    /* renamed from: a  reason: collision with root package name */
    private DragListView f19689a;
    /* access modifiers changed from: private */
    public SimpleAdapter b;
    /* access modifiers changed from: private */
    public List<Device> c = new ArrayList();
    /* access modifiers changed from: private */
    public HashMap<String, List<ShareDeviceDetail.ShareUser>> d = new HashMap<>();
    private View e;
    private View f;
    private View g;
    XQProgressDialog mProcessDialog;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("user_id");
        if (TextUtils.isEmpty(stringExtra) || !stringExtra.equalsIgnoreCase(CoreApi.a().s())) {
            NavigateUtil.a(this);
            finish();
            return;
        }
        setContentView(R.layout.share_device_layout);
        TitleBarUtil.a(TitleBarUtil.a(), findViewById(R.id.title_bar_choose_device));
        initViews();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        initData();
    }

    /* access modifiers changed from: package-private */
    public void initData() {
        this.mProcessDialog = new XQProgressDialog(this);
        this.mProcessDialog.setCancelable(false);
        this.mProcessDialog.setMessage(getResources().getString(R.string.loading_share_info));
        this.mProcessDialog.show();
        RemoteFamilyApi.a().a((Context) this, (AsyncCallback<HashMap<String, List<ShareDeviceDetail.ShareUser>>, Error>) new AsyncCallback<HashMap<String, List<ShareDeviceDetail.ShareUser>>, Error>() {
            /* renamed from: a */
            public void onSuccess(HashMap<String, List<ShareDeviceDetail.ShareUser>> hashMap) {
                HashMap unused = ShareDeviceInfoActivity.this.d = hashMap;
                for (String str : hashMap.keySet()) {
                    ShareUserRecord.insert(hashMap.get(str));
                }
                ShareDeviceInfoActivity.this.b.notifyDataSetChanged();
                ShareDeviceInfoActivity.this.mProcessDialog.dismiss();
            }

            public void onFailure(Error error) {
                ShareDeviceInfoActivity.this.mProcessDialog.dismiss();
                ShareDeviceInfoActivity.this.b.notifyDataSetChanged();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        this.e = findViewById(R.id.common_white_empty_view);
        this.f = findViewById(R.id.title_bar_choose_device);
        this.g = findViewById(R.id.menu_bar_choose_device);
        this.e.setVisibility(8);
        this.f19689a = (DragListView) findViewById(R.id.share_device_list);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(SmartHomeDeviceManager.a().d());
        int i = 0;
        while (i < arrayList.size()) {
            if (!((Device) arrayList.get(i)).isOwner() || !((Device) arrayList.get(i)).canAuth || ((Device) arrayList.get(i)).isSubDevice()) {
                arrayList.remove(i);
                i--;
            }
            i++;
        }
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.miio_setting_share);
        ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.share_no_device);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceInfoActivity.this.finish();
            }
        });
        this.c.addAll(arrayList);
        this.b = new SimpleAdapter();
        this.f19689a.setAdapter(this.b);
        if (this.c.size() == 0) {
            this.f19689a.setVisibility(8);
            this.e.setVisibility(0);
        }
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f19694a;
        SimpleDraweeView b;
        TextView c;
        CheckBox d;
        TextView e;
        TextView f;

        ViewHolder() {
        }
    }

    class SimpleAdapter extends BaseAdapter {
        private HashMap<String, Boolean> b = new HashMap<>();

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        SimpleAdapter() {
        }

        public int getCount() {
            return ShareDeviceInfoActivity.this.c.size();
        }

        public boolean a(int i) {
            Boolean bool = this.b.get(SceneManager.x().v().get(i).f);
            if (bool == null) {
                return false;
            }
            return bool.booleanValue();
        }

        public void a() {
            this.b.clear();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            String str;
            if (view == null) {
                view = LayoutInflater.from(ShareDeviceInfoActivity.this).inflate(R.layout.message_item_new, (ViewGroup) null);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.b = (SimpleDraweeView) view.findViewById(R.id.device_icon);
                viewHolder.c = (TextView) view.findViewById(R.id.right_tv);
                viewHolder.d = (CheckBox) view.findViewById(R.id.ratio_btn);
                viewHolder.e = (TextView) view.findViewById(R.id.device_item);
                viewHolder.f = (TextView) view.findViewById(R.id.device_item_info);
                viewHolder.f19694a = view.findViewById(R.id.right_fl);
                view.setTag(viewHolder);
            }
            ViewHolder viewHolder2 = (ViewHolder) view.getTag();
            viewHolder2.e.setText(((Device) ShareDeviceInfoActivity.this.c.get(i)).name);
            viewHolder2.f19694a.setVisibility(8);
            List list = (List) ShareDeviceInfoActivity.this.d.get(((Device) ShareDeviceInfoActivity.this.c.get(i)).did);
            if (list == null || list.size() == 0) {
                str = ShareDeviceInfoActivity.this.getString(R.string.smarthome_not_shared);
            } else {
                StringBuilder sb = new StringBuilder();
                int i2 = 0;
                while (i2 < list.size() && i2 < 3) {
                    if (i2 != 0) {
                        sb.append(',');
                    }
                    sb.append(((ShareDeviceDetail.ShareUser) list.get(i2)).b);
                    i2++;
                }
                if (list.size() > 3) {
                    str = ShareDeviceInfoActivity.this.getResources().getQuantityString(R.plurals.smarthome_has_shared_to_user_more, list.size(), new Object[]{sb.toString(), Integer.valueOf(list.size())});
                } else {
                    str = ShareDeviceInfoActivity.this.getString(R.string.smarthome_has_shared_to_user, new Object[]{sb.toString()});
                }
            }
            viewHolder2.f.setText(str);
            Device device = (Device) ShareDeviceInfoActivity.this.c.get(i);
            if (viewHolder2.b != null) {
                DeviceFactory.b(device.model, viewHolder2.b);
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(ShareDeviceInfoActivity.this, ShareDeviceDetail.class);
                    intent.putExtra("did", ((Device) ShareDeviceInfoActivity.this.c.get(i)).did);
                    intent.putExtra("pid", ((Device) ShareDeviceInfoActivity.this.c.get(i)).pid);
                    ShareDeviceInfoActivity.this.startActivity(intent);
                }
            });
            return view;
        }
    }
}
