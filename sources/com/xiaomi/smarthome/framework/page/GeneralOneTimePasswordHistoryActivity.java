package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.api.model.OneTimePasswordInfo;
import com.xiaomi.smarthome.framework.api.profile.OneTimePwdManager;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.library.common.widget.DevicePtrFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;

public class GeneralOneTimePasswordHistoryActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16856a = "OneTimePwdHistActivity";
    /* access modifiers changed from: private */
    public int b;
    /* access modifiers changed from: private */
    public int c;
    /* access modifiers changed from: private */
    public Device d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public String f;
    SimpleAdapter mAdapter;
    @BindView(2131430969)
    ImageView mBackBt;
    @BindView(2131428994)
    View mEmptyView;
    @BindView(2131429450)
    ImageView mGeneratePwdBtn;
    @BindView(2131431792)
    ListView mListView;
    @BindView(2131431674)
    DevicePtrFrameLayout mPullRefresh;
    @BindView(2131432653)
    View mSubTitle;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!a()) {
            finish();
            return;
        }
        setContentView(R.layout.activity_general_one_time_password_history);
        ButterKnife.bind((Activity) this);
        b();
    }

    private boolean a() {
        String stringExtra = getIntent().getStringExtra("did");
        this.c = getIntent().getIntExtra(Constants.Name.INTERVAL, 0);
        this.b = getIntent().getIntExtra("digits", 0);
        this.d = SmartHomeDeviceManager.a().b(stringExtra);
        if (this.d == null) {
            MyLog.f("GeneralOneTimePasswordActivity failed, device don't exist, " + stringExtra);
            return false;
        } else if (this.c < 1 || this.c > 60) {
            MyLog.f("GeneralOneTimePasswordActivity failed, mInterval is invalid, " + this.c);
            return false;
        } else if (this.b >= 6 && this.b <= 8) {
            return true;
        } else {
            MyLog.f("GeneralOneTimePasswordActivity failed, mPasswordNum is invalid, " + this.b);
            return false;
        }
    }

    private void b() {
        this.mBackBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GeneralOneTimePasswordHistoryActivity.this.finish();
            }
        });
        this.mAdapter = new SimpleAdapter();
        this.mListView.setAdapter(this.mAdapter);
        this.mPullRefresh = (DevicePtrFrameLayout) findViewById(R.id.pull_down_refresh);
        this.mPullRefresh.disableWhenHorizontalMove(true);
        this.mPullRefresh.setPullToRefresh(false);
        this.mPullRefresh.setPtrIndicator(new PtrIndicator());
        this.mPullRefresh.setPtrHandler(new PtrDefaultHandler() {
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                GeneralOneTimePasswordHistoryActivity.this.c();
            }
        });
        this.mGeneratePwdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GeneralOneTimePasswordHistoryActivity.this, GeneralOneTimePasswordActivity.class);
                intent.putExtra("did", GeneralOneTimePasswordHistoryActivity.this.d.did);
                intent.putExtra(Constants.Name.INTERVAL, GeneralOneTimePasswordHistoryActivity.this.c);
                intent.putExtra("digits", GeneralOneTimePasswordHistoryActivity.this.b);
                GeneralOneTimePasswordHistoryActivity.this.startActivity(intent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        XmPluginHostApi.instance().getUTCFromServer("", new Callback<Long>() {
            /* renamed from: a */
            public void onSuccess(final Long l) {
                XmPluginHostApi.instance().getUserDeviceData(GeneralOneTimePasswordHistoryActivity.this.d.model, GeneralOneTimePasswordHistoryActivity.this.d.did, XmBluetoothRecord.TYPE_PROP, "device_lock_token", 0, l.longValue(), new Callback<JSONArray>() {
                    /* renamed from: a */
                    public void onSuccess(JSONArray jSONArray) {
                        Log.i(GeneralOneTimePasswordHistoryActivity.f16856a, " get result =" + jSONArray);
                        String unused = GeneralOneTimePasswordHistoryActivity.this.e = XmBluetoothManager.getInstance().getTokenMd5(GeneralOneTimePasswordHistoryActivity.this.d.mac);
                        String unused2 = GeneralOneTimePasswordHistoryActivity.this.e = GeneralOneTimePasswordHistoryActivity.this.e.substring(2, GeneralOneTimePasswordHistoryActivity.this.e.length() - 2);
                        if (jSONArray == null || jSONArray.length() == 0) {
                            GeneralOneTimePasswordHistoryActivity.this.a(l.longValue(), GeneralOneTimePasswordHistoryActivity.this.e);
                            GeneralOneTimePasswordHistoryActivity.this.a(l.longValue());
                            return;
                        }
                        try {
                            String unused3 = GeneralOneTimePasswordHistoryActivity.this.f = jSONArray.getJSONObject(0).optString("value", "");
                            if (GeneralOneTimePasswordHistoryActivity.this.f.equalsIgnoreCase(GeneralOneTimePasswordHistoryActivity.this.e)) {
                                GeneralOneTimePasswordHistoryActivity.this.a(l.longValue());
                                return;
                            }
                            GeneralOneTimePasswordHistoryActivity.this.b(l.longValue());
                            GeneralOneTimePasswordHistoryActivity.this.a(l.longValue(), GeneralOneTimePasswordHistoryActivity.this.e);
                            GeneralOneTimePasswordHistoryActivity.this.mPullRefresh.refreshComplete();
                        } catch (JSONException e) {
                            GeneralOneTimePasswordHistoryActivity.this.mPullRefresh.refreshComplete();
                            e.printStackTrace();
                        }
                    }

                    public void onFailure(int i, String str) {
                        Log.i(GeneralOneTimePasswordHistoryActivity.f16856a, "get device_lock_token failed, error = " + i + ", msg = " + str);
                        GeneralOneTimePasswordHistoryActivity.this.mPullRefresh.refreshComplete();
                    }
                });
            }

            public void onFailure(int i, String str) {
                Log.i(GeneralOneTimePasswordHistoryActivity.f16856a, "refresh failed, error = " + i + ", msg = " + str);
                GeneralOneTimePasswordHistoryActivity.this.mPullRefresh.refreshComplete();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final long j) {
        XmPluginHostApi.instance().getUserDeviceData(this.d.model, this.d.did, XmBluetoothRecord.TYPE_PROP, "device_lock", 0, j, new Callback<JSONArray>() {
            /* renamed from: a */
            public void onSuccess(JSONArray jSONArray) {
                Log.i(GeneralOneTimePasswordHistoryActivity.f16856a, " get result =" + jSONArray);
                if (jSONArray != null) {
                    OneTimePwdManager.f16465a = OneTimePwdManager.a(GeneralOneTimePasswordHistoryActivity.this.d, jSONArray, j);
                    GeneralOneTimePasswordHistoryActivity.this.a(OneTimePwdManager.f16465a);
                }
                GeneralOneTimePasswordHistoryActivity.this.mPullRefresh.refreshComplete();
            }

            public void onFailure(int i, String str) {
                Log.i(GeneralOneTimePasswordHistoryActivity.f16856a, "getUserDeviceData failed, error = " + i + ", msg = " + str);
                GeneralOneTimePasswordHistoryActivity.this.mPullRefresh.refreshComplete();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(long j, String str) {
        XmPluginHostApi.instance().setUserDeviceData(this.d.model, this.d.did, XmBluetoothRecord.TYPE_PROP, "device_lock_token", j, str, new Callback<JSONArray>() {
            /* renamed from: a */
            public void onSuccess(JSONArray jSONArray) {
                Log.e(GeneralOneTimePasswordHistoryActivity.f16856a, " uploadToken successfully.");
            }

            public void onFailure(int i, String str) {
                Log.e(GeneralOneTimePasswordHistoryActivity.f16856a, "uploadToken onFailure, error = " + i + ", msg = " + str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(long j) {
        long j2 = j;
        XmPluginHostApi.instance().setUserDeviceData(this.d.model, this.d.did, XmBluetoothRecord.TYPE_PROP, "device_lock", j2, new JSONArray(), new Callback<JSONArray>() {
            /* renamed from: a */
            public void onSuccess(JSONArray jSONArray) {
                Log.e(GeneralOneTimePasswordHistoryActivity.f16856a, " deleteHistoryList successfully.");
            }

            public void onFailure(int i, String str) {
                Log.e(GeneralOneTimePasswordHistoryActivity.f16856a, "deleteHistoryList onFailure, error = " + i + ", msg = " + str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(TreeMap<Long, OneTimePasswordInfo> treeMap) {
        ArrayList arrayList = new ArrayList(treeMap.values());
        if (arrayList.size() == 0) {
            e();
            return;
        }
        d();
        this.mAdapter.a(arrayList);
    }

    private void d() {
        this.mEmptyView.setVisibility(8);
        this.mSubTitle.setVisibility(0);
        this.mListView.setVisibility(0);
    }

    private void e() {
        this.mEmptyView.setVisibility(0);
        this.mSubTitle.setVisibility(8);
        this.mListView.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mPullRefresh.autoRefresh();
    }

    private class SimpleAdapter extends BaseAdapter {
        private final List<OneTimePasswordInfo> b;

        public long getItemId(int i) {
            return (long) i;
        }

        private SimpleAdapter() {
            this.b = new ArrayList();
        }

        public void a(List<OneTimePasswordInfo> list) {
            this.b.clear();
            if (list != null) {
                this.b.addAll(list);
            }
            notifyDataSetChanged();
        }

        public Object getItem(int i) {
            return this.b.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(GeneralOneTimePasswordHistoryActivity.this.getContext()).inflate(R.layout.onetimepwd_item_layout, viewGroup, false);
            }
            a(view, i);
            return view;
        }

        public void a(View view, int i) {
            OneTimePasswordInfo oneTimePasswordInfo = this.b.get(i);
            ((TextView) view.findViewById(R.id.title)).setText(oneTimePasswordInfo.g() + com.xiaomi.mipush.sdk.Constants.J + oneTimePasswordInfo.h());
        }

        public int getCount() {
            return this.b.size();
        }
    }
}
