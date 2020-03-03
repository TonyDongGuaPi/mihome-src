package com.xiaomi.smarthome.scene.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.baseui.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.smarthome.scene.location.model.WifiGroupData;
import java.util.ArrayList;
import java.util.List;

public class WifiGroupDataList extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private PullDownDragListView f21600a;
    private SimpleAdapter b;
    List<WifiGroupData> mData = new ArrayList();

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.message_center_v2);
        findViewById(R.id.module_a_3_right_iv_setting_btn).setVisibility(0);
        findViewById(R.id.module_a_3_right_iv_setting_btn).setContentDescription(getString(R.string.scene_enter_or_leave_wifi));
        findViewById(R.id.module_a_3_right_iv_setting_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiGroupDataList.this.startActivityForResult(new Intent(WifiGroupDataList.this, ScenePoiSelectWifiActivity.class), 1);
            }
        });
        this.f21600a = (PullDownDragListView) findViewById(R.id.share_message_list);
        this.b = new SimpleAdapter();
        this.f21600a.setAdapter(this.b);
        this.f21600a.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
            }
        });
        this.f21600a.doRefresh();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    private class SimpleAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return 0;
        }

        private SimpleAdapter() {
        }

        public int getCount() {
            return WifiGroupDataList.this.mData.size();
        }

        public Object getItem(int i) {
            return WifiGroupDataList.this.mData.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(WifiGroupDataList.this).inflate(R.layout.message_item_new, viewGroup, false);
            }
            final WifiGroupData wifiGroupData = WifiGroupDataList.this.mData.get(i);
            ((TextView) view.findViewById(R.id.device_item)).setText(wifiGroupData.b());
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(WifiGroupDataList.this, ScenePoiSelectWifiActivity.class);
                    intent.putExtra("data", wifiGroupData);
                    WifiGroupDataList.this.startActivity(intent);
                }
            });
            return view;
        }
    }
}
