package com.xiaomi.smarthome.family;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.miio.activity.HomeLogContants;
import com.xiaomi.smarthome.miio.camera.match.CameraDevice;
import com.xiaomi.smarthome.miio.device.PhoneDevice;
import com.xiaomi.smarthome.wificonfig.WifiScanHomelog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class ShareDeviceToFamily extends BaseActivity {
    public static final String ARGS_KEY_USERID = "target_user_id";

    /* renamed from: a  reason: collision with root package name */
    private ListView f15746a;
    /* access modifiers changed from: private */
    public List<Device> b = new ArrayList();
    /* access modifiers changed from: private */
    public List<Integer> c = new ArrayList();
    private BaseAdapter d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public View f;
    XQProgressDialog mProgressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.share_family_device_2);
        initData();
        initView();
    }

    /* access modifiers changed from: package-private */
    public void initView() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.smarthome_device_auth);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceToFamily.this.finish();
            }
        });
        this.f = findViewById(R.id.button3);
        ((Button) this.f).setText(R.string.complete);
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ArrayList arrayList = new ArrayList();
                for (Integer intValue : ShareDeviceToFamily.this.c) {
                    arrayList.add(((Device) ShareDeviceToFamily.this.b.get(intValue.intValue())).did);
                }
                final ArrayList arrayList2 = new ArrayList();
                if (!TextUtils.isEmpty(ShareDeviceToFamily.this.e)) {
                    arrayList2.add(ShareDeviceToFamily.this.e);
                }
                RemoteFamilyApi.a().a((Context) ShareDeviceToFamily.this, (List<String>) arrayList, (List<String>) arrayList2, (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                    /* renamed from: a */
                    public void onSuccess(String str) {
                        for (String jSONObject : arrayList2) {
                            try {
                                JSONObject jSONObject2 = new JSONObject(str).getJSONObject(jSONObject);
                                if (jSONObject2.isNull("error")) {
                                    Toast.makeText(ShareDeviceToFamily.this, R.string.sh_share_request_success, 0).show();
                                } else if ("REPEATED".equals(jSONObject2.getString("error"))) {
                                    Toast.makeText(ShareDeviceToFamily.this, R.string.sh_share_repeated_request, 0).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        ShareDeviceToFamily.this.finish();
                    }

                    public void onFailure(Error error) {
                        if (error.a() == ErrorCode.ERROR_MAXIMAL_SHARE_REQUEST.getCode()) {
                            new MLAlertDialog.Builder(ShareDeviceToFamily.this).b((CharSequence) ShareDeviceToFamily.this.getResources().getString(R.string.sh_share_fremax_request)).a((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).b().show();
                        } else if (error.a() == ErrorCode.ERROR_FEQUENT_REQUEST.getCode()) {
                            new MLAlertDialog.Builder(ShareDeviceToFamily.this).b((CharSequence) ShareDeviceToFamily.this.getResources().getString(R.string.sh_share_frequent_request)).a((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).b().show();
                        } else {
                            Toast.makeText(ShareDeviceToFamily.this, R.string.sh_share_request_fail, 0).show();
                        }
                    }
                });
            }
        });
        this.f15746a = (ListView) findViewById(R.id.faimlly_list_view);
        this.d = new CustomAdapter();
        this.f15746a.setAdapter(this.d);
    }

    /* access modifiers changed from: package-private */
    public void initData() {
        this.e = getIntent().getExtras().getString("target_user_id");
        this.b.addAll(SmartHomeDeviceManager.a().d());
        int i = 0;
        while (i < this.b.size()) {
            if (!this.b.get(i).isOwner() || !this.b.get(i).canAuth || this.b.get(i).isSubDevice() || !this.b.get(i).canBeShared()) {
                this.b.remove(i);
                i--;
            }
            i++;
        }
        if (this.b.size() == 0) {
            findViewById(R.id.button3).setEnabled(false);
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
            return ShareDeviceToFamily.this.b.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            String str;
            if (view == null) {
                view = LayoutInflater.from(ShareDeviceToFamily.this).inflate(R.layout.family_item, (ViewGroup) null);
            }
            if (view == null) {
                view = LayoutInflater.from(ShareDeviceToFamily.this).inflate(R.layout.family_item, (ViewGroup) null);
            }
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.family_mem_icon);
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_box);
            boolean z = false;
            checkBox.setVisibility(0);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_box);
                    boolean z = true;
                    checkBox.setChecked(!checkBox.isChecked());
                    Iterator it = ShareDeviceToFamily.this.c.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Integer num = (Integer) it.next();
                        if (num.intValue() == i) {
                            ShareDeviceToFamily.this.c.remove(num);
                            break;
                        }
                    }
                    if (checkBox.isChecked()) {
                        ShareDeviceToFamily.this.c.add(Integer.valueOf(i));
                    }
                    View access$300 = ShareDeviceToFamily.this.f;
                    if (ShareDeviceToFamily.this.c.size() <= 0) {
                        z = false;
                    }
                    access$300.setEnabled(z);
                }
            });
            if (ShareDeviceToFamily.this.c.contains(Integer.valueOf(i))) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
            View access$300 = ShareDeviceToFamily.this.f;
            if (ShareDeviceToFamily.this.c.size() > 0) {
                z = true;
            }
            access$300.setEnabled(z);
            view.findViewById(R.id.arrow).setVisibility(4);
            Device device = (Device) ShareDeviceToFamily.this.b.get(i);
            DeviceFactory.b(device.model, simpleDraweeView);
            TextView textView = (TextView) view.findViewById(R.id.user_define_nick_name);
            if (device instanceof PhoneDevice) {
                String m = WifiScanHomelog.m();
                if (TextUtils.isEmpty(m)) {
                    textView.setText(R.string.no_location_log);
                } else if (m.equals("home")) {
                    textView.setText(R.string.header_title_home);
                } else if (m.equals("office")) {
                    textView.setText(R.string.header_title_office);
                } else if (m.equals(HomeLogContants.j)) {
                    textView.setText(R.string.header_title_outside);
                } else {
                    textView.setText(R.string.no_location_log);
                }
            } else if (!device.isConnected) {
                textView.setText("");
            } else if (device instanceof CameraDevice) {
                textView.setText((String) device.getStatusDescription(ShareDeviceToFamily.this));
            } else if (device instanceof MiTVDevice) {
                textView.setText((String) device.getStatusDescription(ShareDeviceToFamily.this));
            } else if (device instanceof Device) {
                if (!device.isOnline) {
                    str = ShareDeviceToFamily.this.getString(R.string.offline_device);
                } else {
                    str = (String) device.getStatusDescription(ShareDeviceToFamily.this);
                }
                if (device.isShared() || device.isFamily()) {
                    str = str + ShareDeviceToFamily.this.getString(R.string.comefrom_device) + " " + device.ownerName;
                } else if (!device.isBinded()) {
                    str = ShareDeviceToFamily.this.getString(R.string.local_device);
                }
                textView.setText(str);
            } else {
                textView.setText(device.getStatusDescription(ShareDeviceToFamily.this));
            }
            ((TextView) view.findViewById(R.id.nick_name)).setText(((Device) ShareDeviceToFamily.this.b.get(i)).name);
            return view;
        }
    }
}
