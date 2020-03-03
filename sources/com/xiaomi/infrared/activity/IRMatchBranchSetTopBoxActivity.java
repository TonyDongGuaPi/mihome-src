package com.xiaomi.infrared.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.infrared.adapter.IRSetTopBranchAdapter;
import com.xiaomi.infrared.bean.IRSTBData;
import com.xiaomi.infrared.bean.IRType;
import com.xiaomi.infrared.bean.MJAreaID;
import com.xiaomi.infrared.request.InifraredRequestApi;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.audiorecord.ToastUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.ArrayList;
import java.util.Iterator;

public class IRMatchBranchSetTopBoxActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private TextView f10172a;
    /* access modifiers changed from: private */
    public IRSetTopBranchAdapter b;
    private InifraredRequestApi c = new InifraredRequestApi();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ir_match_set_top_box);
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        ListView listView = (ListView) findViewById(R.id.ir_match_set_top_box_list);
        this.f10172a = (TextView) findViewById(R.id.ir_match_location);
        this.c.a((Context) this);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.ir_match_set_top_box);
        this.b = new IRSetTopBranchAdapter(this, new ArrayList());
        listView.setAdapter(this.b);
        a();
        findViewById.setOnClickListener(this);
        this.f10172a.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                IRSTBData iRSTBData = (IRSTBData) IRMatchBranchSetTopBoxActivity.this.b.getItem(i);
                if (iRSTBData.e() == 1) {
                    IRMatchingBrandActivity.showMatchingBrandActivity(IRMatchBranchSetTopBoxActivity.this, iRSTBData, IRType.STB.value());
                } else {
                    IRSingleMatchBaseActivity.showStbSingleMatchActivity(IRMatchBranchSetTopBoxActivity.this, IRType.STB.value(), iRSTBData);
                }
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ir_match_location) {
            IRSelectProvinceActivity.startProvince(this, 100);
        } else if (id == R.id.module_a_3_return_btn) {
            finish();
        }
    }

    private void a() {
        this.f10172a.setText(getString(R.string.ir_match_location_unknown));
        a(SHLocationManager.a().b());
        SHLocationManager.a().a((SHLocationManager.LocationCallback) new SHLocationManager.LocationCallback() {
            public void onSucceed(String str, Location location) {
                IRMatchBranchSetTopBoxActivity.this.a(location);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(Location location) {
        Bundle extras;
        if (location != null && (extras = location.getExtras()) != null) {
            Object obj = extras.get("address");
            if (obj instanceof Address) {
                Address address = (Address) obj;
                String adminArea = address.getAdminArea();
                String locality = address.getLocality();
                String subLocality = address.getSubLocality();
                if (TextUtils.isEmpty(subLocality)) {
                    subLocality = locality;
                    locality = adminArea;
                }
                if (!TextUtils.isEmpty(adminArea) && !TextUtils.isEmpty(locality)) {
                    TextView textView = this.f10172a;
                    String string = getString(R.string.ir_match_location_format);
                    Object[] objArr = new Object[3];
                    objArr[0] = adminArea;
                    objArr[1] = adminArea.equals(locality) ? "" : locality;
                    objArr[2] = subLocality;
                    textView.setText(String.format(string, objArr));
                    this.c.a(adminArea, locality, subLocality, new AsyncCallback<MJAreaID, Error>() {
                        public void onFailure(Error error) {
                        }

                        /* renamed from: a */
                        public void onSuccess(MJAreaID mJAreaID) {
                            IRMatchBranchSetTopBoxActivity.this.a(mJAreaID.a(), mJAreaID.b());
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str, String str2) {
        this.c.a(str2, (AsyncCallback<ArrayList<IRSTBData>, Error>) new AsyncCallback<ArrayList<IRSTBData>, Error>() {
            /* renamed from: a */
            public void onSuccess(ArrayList<IRSTBData> arrayList) {
                Iterator<IRSTBData> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().b(str);
                }
                IRMatchBranchSetTopBoxActivity.this.b.a(arrayList);
            }

            public void onFailure(Error error) {
                ToastUtil.a((Context) IRMatchBranchSetTopBoxActivity.this, (int) R.string.ir_toast_get_data_failed, 1);
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        this.c.a();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent == null) {
            return;
        }
        if (intent.getBooleanExtra(InifraredContants.IntentParams.d, false)) {
            setResult(-1, intent);
            finish();
        } else if (i == 100 && i2 == -1) {
            String stringExtra = intent.getStringExtra(InifraredContants.IntentParams.k);
            String stringExtra2 = intent.getStringExtra(InifraredContants.IntentParams.j);
            String stringExtra3 = intent.getStringExtra(InifraredContants.IntentParams.o);
            String stringExtra4 = intent.getStringExtra(InifraredContants.IntentParams.m);
            String stringExtra5 = intent.getStringExtra(InifraredContants.IntentParams.n);
            if (TextUtils.equals(stringExtra, stringExtra2)) {
                stringExtra2 = "";
            }
            this.f10172a.setText(String.format(getResources().getString(R.string.ir_match_location_format), new Object[]{substringLocalName(stringExtra), substringLocalName(stringExtra2), stringExtra3}));
            a(stringExtra4, String.valueOf(stringExtra5));
        }
    }

    public static String substringLocalName(String str) {
        if (str.length() <= 1) {
            return "";
        }
        return str.substring(0, str.length() - 1);
    }
}
