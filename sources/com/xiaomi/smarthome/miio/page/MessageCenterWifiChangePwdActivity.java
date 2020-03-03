package com.xiaomi.smarthome.miio.page;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.widget.PinnedSectionListView;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.cybergarage.upnp.DeviceList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageCenterWifiChangePwdActivity extends BaseActivity {
    public static final String INTENT_KEY_MSG_RECORD = "message_record";
    private static final int j = 0;
    private static final int k = 1;

    /* renamed from: a  reason: collision with root package name */
    private PinnedSectionListView f19608a;
    /* access modifiers changed from: private */
    public View b;
    private View c;
    private SimpleAdapter d;
    /* access modifiers changed from: private */
    public LayoutInflater e;
    /* access modifiers changed from: private */
    public WifiChangeData f;
    /* access modifiers changed from: private */
    public View g;
    private TextView h;
    /* access modifiers changed from: private */
    public int i;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra(INTENT_KEY_MSG_RECORD);
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
            return;
        }
        this.f = WifiChangeData.a(stringExtra);
        if (this.f == null) {
            finish();
            return;
        }
        setContentView(R.layout.message_center_wifi_change_pwd);
        TitleBarUtil.b((Activity) this);
        this.e = LayoutInflater.from(this);
        initViews();
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        this.b = findViewById(R.id.title_bar);
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageCenterWifiChangePwdActivity.this.finish();
            }
        });
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.f19608a = (PinnedSectionListView) findViewById(R.id.message_detail_list);
        this.d = new SimpleAdapter();
        this.f19608a.setAdapter((ListAdapter) this.d);
        b();
        a();
    }

    private void a() {
        if (this.b.getBackground() != null) {
            this.b.getBackground().setAlpha(0);
        }
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= 16) {
                    MessageCenterWifiChangePwdActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int unused = MessageCenterWifiChangePwdActivity.this.i = MessageCenterWifiChangePwdActivity.this.b.getHeight();
                if (TitleBarUtil.f11582a) {
                    MessageCenterWifiChangePwdActivity.this.g.setPadding(MessageCenterWifiChangePwdActivity.this.g.getPaddingLeft(), MessageCenterWifiChangePwdActivity.this.getResources().getDimensionPixelSize(R.dimen.title_bar_top_padding), MessageCenterWifiChangePwdActivity.this.g.getPaddingRight(), MessageCenterWifiChangePwdActivity.this.g.getPaddingBottom());
                }
            }
        });
    }

    private void b() {
        this.g = findViewById(R.id.header_view);
        this.h = (TextView) this.g.findViewById(R.id.date_time_tv);
        this.h.setText(CalendarUtils.c(this.f.e * 1000));
    }

    class SimpleAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {
        public boolean a(int i) {
            return false;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getViewTypeCount() {
            return 2;
        }

        SimpleAdapter() {
        }

        public int getCount() {
            return MessageCenterWifiChangePwdActivity.this.f.g.size();
        }

        public Object getItem(int i) {
            if (i < 0 || i >= MessageCenterWifiChangePwdActivity.this.f.g.size()) {
                return null;
            }
            return MessageCenterWifiChangePwdActivity.this.f.g.get(i);
        }

        public int getItemViewType(int i) {
            return ((WifiChangeData.WifiChangeDateItem) getItem(i)).g;
        }

        class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f19613a;
            SimpleDraweeView b;
            View c;
            View d;
            TextView e;
            TextView f;
            TextView g;

            ViewHolder() {
            }
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = MessageCenterWifiChangePwdActivity.this.e.inflate(R.layout.message_center_wifi_change_pwd_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f19613a = (TextView) view.findViewById(R.id.status);
                viewHolder.b = (SimpleDraweeView) view.findViewById(R.id.device_icon);
                viewHolder.b.setHierarchy(new GenericDraweeHierarchyBuilder(viewHolder.b.getResources()).setFadeDuration(200).setPlaceholderImage(viewHolder.b.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY).build());
                viewHolder.d = view.findViewById(R.id.section_ll);
                viewHolder.c = view.findViewById(R.id.content_ll);
                viewHolder.e = (TextView) view.findViewById(R.id.device_status);
                viewHolder.f = (TextView) view.findViewById(R.id.date_tv);
                viewHolder.g = (TextView) view.findViewById(R.id.reset_tv);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final WifiChangeData.WifiChangeDateItem wifiChangeDateItem = (WifiChangeData.WifiChangeDateItem) getItem(i);
            if (wifiChangeDateItem.g == 0) {
                viewHolder.d.setVisibility(0);
                viewHolder.c.setVisibility(8);
                if (wifiChangeDateItem.f == 0) {
                    viewHolder.f19613a.setText(R.string.wifi_pwd_changed_succ);
                } else {
                    viewHolder.f19613a.setText(R.string.wifi_pwd_changed_fail);
                }
            } else {
                viewHolder.d.setVisibility(8);
                viewHolder.c.setVisibility(0);
                Device g = DeviceFactory.g(wifiChangeDateItem.d, wifiChangeDateItem.c);
                if (g != null) {
                    DeviceFactory.b(g.model, viewHolder.b);
                } else {
                    viewHolder.b.setImageURI(CommonUtils.c((int) R.drawable.ic_launcher));
                }
                viewHolder.e.setText(wifiChangeDateItem.e);
                if (wifiChangeDateItem.f == 0) {
                    viewHolder.f.setText(CalendarUtils.b(MessageCenterWifiChangePwdActivity.this.f.e * 1000));
                    viewHolder.g.setVisibility(8);
                } else {
                    viewHolder.f.setText(CalendarUtils.b(MessageCenterWifiChangePwdActivity.this.f.f * 1000));
                    viewHolder.g.setVisibility(0);
                    viewHolder.g.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = new Intent(MessageCenterWifiChangePwdActivity.this, ChooseDeviceActivity.class);
                            intent.putExtra("model", wifiChangeDateItem.c);
                            MessageCenterWifiChangePwdActivity.this.startActivity(intent);
                        }
                    });
                }
            }
            return view;
        }
    }

    public static class WifiChangeData {

        /* renamed from: a  reason: collision with root package name */
        String f19614a;
        int b;
        String c;
        String d;
        long e;
        long f;
        List<WifiChangeDateItem> g = new ArrayList();

        public static class WifiChangeDateItem {

            /* renamed from: a  reason: collision with root package name */
            public static final int f19615a = 0;
            public static final int b = 1;
            String c;
            String d;
            String e;
            int f = -1;
            int g = 1;
        }

        private WifiChangeData() {
        }

        public static WifiChangeData a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                WifiChangeData wifiChangeData = new WifiChangeData();
                wifiChangeData.f19614a = jSONObject.optString("type");
                wifiChangeData.b = jSONObject.optInt("sub_type");
                wifiChangeData.c = jSONObject.optString("title");
                wifiChangeData.d = jSONObject.optString("content");
                wifiChangeData.e = jSONObject.optLong("sucTime");
                wifiChangeData.f = jSONObject.optLong("failTime");
                JSONArray optJSONArray = jSONObject.optJSONArray(DeviceList.ELEM_NAME);
                if (optJSONArray != null) {
                    boolean z = false;
                    boolean z2 = false;
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            WifiChangeDateItem wifiChangeDateItem = new WifiChangeDateItem();
                            wifiChangeDateItem.c = optJSONObject.optString("model");
                            wifiChangeDateItem.d = optJSONObject.optString("did");
                            wifiChangeDateItem.e = optJSONObject.optString("name");
                            wifiChangeData.g.add(wifiChangeDateItem);
                            wifiChangeDateItem.f = optJSONObject.optInt("status");
                            if (wifiChangeDateItem.f == 0) {
                                z = true;
                            }
                            if (wifiChangeDateItem.f == 1) {
                                z2 = true;
                            }
                        }
                    }
                    if (z) {
                        WifiChangeDateItem wifiChangeDateItem2 = new WifiChangeDateItem();
                        wifiChangeDateItem2.g = 0;
                        wifiChangeDateItem2.f = 0;
                        wifiChangeData.g.add(wifiChangeDateItem2);
                    }
                    if (z2) {
                        WifiChangeDateItem wifiChangeDateItem3 = new WifiChangeDateItem();
                        wifiChangeDateItem3.g = 0;
                        wifiChangeDateItem3.f = 1;
                        wifiChangeData.g.add(wifiChangeDateItem3);
                    }
                    Collections.sort(wifiChangeData.g, new Comparator<WifiChangeDateItem>() {
                        /* renamed from: a */
                        public int compare(WifiChangeDateItem wifiChangeDateItem, WifiChangeDateItem wifiChangeDateItem2) {
                            if (wifiChangeDateItem.g == 0 && wifiChangeDateItem.f == 0) {
                                return -1;
                            }
                            if (wifiChangeDateItem2.g == 0 && wifiChangeDateItem2.f == 0) {
                                return 1;
                            }
                            if (wifiChangeDateItem.g == 0 && wifiChangeDateItem.f == 1) {
                                if (wifiChangeDateItem2.f == 0) {
                                    return 1;
                                }
                                return -1;
                            } else if (wifiChangeDateItem2.g == 0 && wifiChangeDateItem2.f == 1) {
                                if (wifiChangeDateItem.f == 0) {
                                    return -1;
                                }
                                return 1;
                            } else if (wifiChangeDateItem.g != 1 || wifiChangeDateItem2.g != 1) {
                                return 0;
                            } else {
                                if (wifiChangeDateItem.f == 1 && wifiChangeDateItem2.f == 0) {
                                    return 1;
                                }
                                if (wifiChangeDateItem2.f == 1 && wifiChangeDateItem.f == 0) {
                                    return -1;
                                }
                                return 0;
                            }
                        }
                    });
                }
                return wifiChangeData;
            } catch (JSONException unused) {
                return null;
            }
        }
    }
}
