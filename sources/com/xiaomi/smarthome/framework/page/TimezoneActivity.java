package com.xiaomi.smarthome.framework.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.model.MyTimeZone;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

public class TimezoneActivity extends BaseActivity {
    public static final String EXTRA_DEVICE_DID = "extra_device_did";
    public static final String KEY_SYNC_DEVICE = "sync_device";
    public static final String KEY_TIMEZONE = "TimeZone";
    public static final int REQUEST_CODE = 3;
    public static final int RESULT_CODE = 4;

    /* renamed from: a  reason: collision with root package name */
    private static final String f16924a = "TimezoneActivity";
    /* access modifiers changed from: private */
    public boolean b = false;
    /* access modifiers changed from: private */
    public List<MyTimeZone> c = new ArrayList();
    private TextWatcher d = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            ArrayList arrayList = new ArrayList();
            String obj = TimezoneActivity.this.mSearchEt.getText().toString();
            if (!TextUtils.isEmpty(obj)) {
                TimezoneActivity.this.mClearTextImg.setVisibility(0);
                for (MyTimeZone myTimeZone : TimezoneActivity.this.c) {
                    if (myTimeZone.a().contains(obj)) {
                        arrayList.add(myTimeZone);
                    }
                }
                if (arrayList.size() > 0) {
                    TimezoneActivity.this.mAdapter.a((List<MyTimeZone>) arrayList);
                } else {
                    TimezoneActivity.this.mAdapter.a((List<MyTimeZone>) TimezoneActivity.this.c);
                }
            } else {
                TimezoneActivity.this.mClearTextImg.setVisibility(8);
                TimezoneActivity.this.mAdapter.a((List<MyTimeZone>) TimezoneActivity.this.c);
            }
            TimezoneActivity.this.mAdapter.notifyDataSetChanged();
        }
    };
    String did;
    MyAdapter mAdapter;
    ImageView mClearTextImg;
    Context mContext;
    ListView mList;
    EditText mSearchEt;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_timezone);
        this.did = getIntent().getStringExtra("extra_device_did");
        this.b = getIntent().getBooleanExtra(KEY_SYNC_DEVICE, false);
        if (TextUtils.isEmpty(this.did)) {
            finish();
            return;
        }
        this.mList = (ListView) findViewById(R.id.list);
        this.mSearchEt = (EditText) findViewById(R.id.search_et);
        this.mSearchEt.addTextChangedListener(this.d);
        this.mClearTextImg = (ImageView) findViewById(R.id.search_clear_bt);
        this.mClearTextImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TimezoneActivity.this.mSearchEt.setText("");
                TimezoneActivity.this.mAdapter.a((List<MyTimeZone>) TimezoneActivity.this.c);
                TimezoneActivity.this.mAdapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.search_back_img).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TimezoneActivity.this.onBackPressed();
            }
        });
        this.mContext = this;
        String[] availableIDs = TimeZone.getAvailableIDs();
        int i = 0;
        for (String str : getResources().getStringArray(R.array.city_timezone)) {
            int length = availableIDs.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (availableIDs[i2].equals(str)) {
                    String displayName = TimeZone.getTimeZone(str).getDisplayName(false, 0);
                    String str2 = getResources().getStringArray(R.array.timezone_city_name)[i];
                    MyTimeZone myTimeZone = new MyTimeZone();
                    myTimeZone.c(str);
                    myTimeZone.a(str2);
                    myTimeZone.b(displayName);
                    this.c.add(myTimeZone);
                    break;
                } else {
                    i2++;
                }
            }
            i++;
        }
        this.mAdapter = new MyAdapter(this.c, this.mContext);
        this.mList.setAdapter(this.mAdapter);
        this.mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String c = TimezoneActivity.this.mAdapter.a().get(i).c();
                final TimeZone timeZone = TimeZone.getTimeZone(c);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(TimezoneActivity.KEY_SYNC_DEVICE, TimezoneActivity.this.b);
                } catch (JSONException unused) {
                }
                RemoteFamilyApi.a().a(TimezoneActivity.this.did, c, jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        Intent intent = new Intent();
                        intent.putExtra(TimezoneActivity.KEY_TIMEZONE, timeZone);
                        TimezoneActivity.this.setResult(4, intent);
                        TimezoneActivity.this.finish();
                    }

                    public void onFailure(Error error) {
                        ToastUtil.a((CharSequence) TimezoneActivity.this.getString(R.string.phone_wifi_error));
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    static class MyAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        List<MyTimeZone> f16930a = Collections.EMPTY_LIST;
        Context b;
        int c = -1;

        public long getItemId(int i) {
            return (long) i;
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            this.c = i;
        }

        /* access modifiers changed from: package-private */
        public void a(List<MyTimeZone> list) {
            this.f16930a = list;
        }

        /* access modifiers changed from: package-private */
        public List<MyTimeZone> a() {
            return this.f16930a;
        }

        MyAdapter(List<MyTimeZone> list, Context context) {
            this.f16930a = list;
            this.b = context;
        }

        public int getCount() {
            return this.f16930a.size();
        }

        public Object getItem(int i) {
            return this.f16930a.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.item_timezone, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f16931a = (TextView) view.findViewById(R.id.text_name);
                viewHolder.b = (TextView) view.findViewById(R.id.text_gmt);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            if (i < this.f16930a.size()) {
                MyTimeZone myTimeZone = this.f16930a.get(i);
                String a2 = myTimeZone.a();
                if (this.c == i) {
                    a2 = a2 + this.b.getResources().getString(R.string.timezone_activity_current);
                }
                viewHolder.f16931a.setText(a2);
                viewHolder.b.setText(myTimeZone.b());
            }
            return view;
        }
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f16931a;
        TextView b;

        ViewHolder() {
        }
    }
}
