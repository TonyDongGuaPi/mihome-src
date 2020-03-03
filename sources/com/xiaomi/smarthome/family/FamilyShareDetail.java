package com.xiaomi.smarthome.family;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.ExpandListView;
import com.xiaomi.smarthome.miio.db.record.FamilyRecord;
import com.xiaomi.smarthome.miio.page.ShareDeviceDetail;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FamilyShareDetail extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private ExpandListView f15709a;
    /* access modifiers changed from: private */
    public BaseAdapter b;
    /* access modifiers changed from: private */
    public List<Device> c = new ArrayList();
    private SimpleDraweeView d;
    private TextView e;
    /* access modifiers changed from: private */
    public TextView f;
    /* access modifiers changed from: private */
    public Map<String, ShareDeviceDetail.ShareUser> g = new HashMap();
    /* access modifiers changed from: private */
    public String h;
    XQProgressDialog mProcessDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.family_share_detail);
        initView();
        initData();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                CoreApi.a().n();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void initView() {
        this.f15709a = (ExpandListView) findViewById(R.id.faimlly_device_list_view);
        this.b = new CustomAdapter();
        this.f15709a.setAdapter(this.b);
        this.d = (SimpleDraweeView) findViewById(R.id.icon);
        this.e = (TextView) findViewById(R.id.nick_name);
        this.f = (TextView) findViewById(R.id.relation_ship);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyShareDetail.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.family_share_detail_title);
        findViewById(R.id.add_device_container).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(FamilyShareDetail.this, ShareDeviceToFamily.class);
                intent.putExtra("target_user_id", FamilyShareDetail.this.h);
                FamilyShareDetail.this.startActivityForResult(intent, 100);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void initData() {
        this.mProcessDialog = new XQProgressDialog(this);
        this.mProcessDialog.setCancelable(false);
        this.mProcessDialog.setMessage(getResources().getString(R.string.loading_share_info));
        this.mProcessDialog.show();
        this.h = getIntent().getExtras().getString("user_id");
        a(this.h);
        RemoteFamilyApi.a().a((Context) this, (AsyncCallback<HashMap<String, List<ShareDeviceDetail.ShareUser>>, Error>) new AsyncCallback<HashMap<String, List<ShareDeviceDetail.ShareUser>>, Error>() {
            /* renamed from: a */
            public void onSuccess(HashMap<String, List<ShareDeviceDetail.ShareUser>> hashMap) {
                FamilyShareDetail.this.g.clear();
                HashMap hashMap2 = new HashMap();
                Iterator<String> it = hashMap.keySet().iterator();
                while (true) {
                    int i = 0;
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    List list = hashMap.get(next);
                    while (true) {
                        if (i >= list.size()) {
                            break;
                        }
                        ShareDeviceDetail.ShareUser shareUser = (ShareDeviceDetail.ShareUser) list.get(i);
                        if (shareUser.f19679a.equals(FamilyShareDetail.this.h)) {
                            FamilyShareDetail.this.g.put(next, shareUser);
                            hashMap2.put(next, next);
                            break;
                        }
                        i++;
                    }
                }
                for (Device next2 : SmartHomeDeviceManager.a().d()) {
                    if (hashMap2.containsKey(next2.did)) {
                        FamilyShareDetail.this.c.add(next2);
                    }
                }
                FamilyShareDetail.this.f.setText(FamilyShareDetail.this.getResources().getQuantityString(R.plurals.family_share_count, FamilyShareDetail.this.c.size(), new Object[]{Integer.valueOf(FamilyShareDetail.this.c.size())}));
                FamilyShareDetail.this.b.notifyDataSetChanged();
                FamilyShareDetail.this.mProcessDialog.dismiss();
            }

            public void onFailure(Error error) {
                FamilyShareDetail.this.f.setText(FamilyShareDetail.this.getResources().getQuantityString(R.plurals.family_share_count, 0, new Object[]{0}));
                FamilyShareDetail.this.mProcessDialog.dismiss();
                FamilyShareDetail.this.b.notifyDataSetChanged();
            }
        });
    }

    private void a(String str) {
        List<FamilyRecord> query = FamilyRecord.query(str);
        if (query.size() > 0) {
            this.e.setText(query.get(0).nickName);
            UserMamanger.a().b(query.get(0).url, this.d, (BasePostprocessor) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && i2 == -1) {
            initData();
        }
    }

    class CustomAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        CustomAdapter() {
        }

        public int getCount() {
            return FamilyShareDetail.this.c.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(FamilyShareDetail.this).inflate(R.layout.family_item, (ViewGroup) null);
            }
            Device device = (Device) FamilyShareDetail.this.c.get(i);
            view.findViewById(R.id.arrow).setVisibility(4);
            DeviceFactory.b(device.model, (SimpleDraweeView) view.findViewById(R.id.family_mem_icon));
            ((TextView) view.findViewById(R.id.nick_name)).setText(device.name);
            Time time = new Time();
            time.set(((ShareDeviceDetail.ShareUser) FamilyShareDetail.this.g.get(device.did)).d * 1000);
            ((TextView) view.findViewById(R.id.user_define_nick_name)).setText(String.format(FamilyShareDetail.this.getString(R.string.family_share_time), new Object[]{Integer.valueOf(time.year), Integer.valueOf(time.month + 1), Integer.valueOf(time.monthDay)}));
            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    return false;
                }
            });
            return view;
        }
    }
}
