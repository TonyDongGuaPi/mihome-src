package com.xiaomi.smarthome.miio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.ExpandListView;
import java.util.ArrayList;
import java.util.Collections;

public class WifiChooseListActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<String> f11809a;
    /* access modifiers changed from: private */
    public ArrayList<String> b;
    /* access modifiers changed from: private */
    public boolean c = false;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wifi_choose_list);
        Intent intent = getIntent();
        this.c = getIntent().getExtras().getBoolean("is_home_list", false);
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (textView != null) {
            if (this.c) {
                textView.setText(R.string.wifi_select_home_title);
            } else {
                textView.setText(R.string.wifi_select_office_title);
            }
        }
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("select_list");
        this.f11809a = new ArrayList<>();
        for (int i = 0; i < stringArrayListExtra.size(); i++) {
            this.f11809a.add(stringArrayListExtra.get(i));
        }
        this.b = new ArrayList<>();
        Collections.addAll(this.b, intent.getStringArrayExtra(HomeLogContants.f));
        findViewById(R.id.wifi_choose_end_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (WifiChooseListActivity.this.f11809a.size() > 6) {
                    Toast.makeText(WifiChooseListActivity.this, R.string.choose_wifi_count_over, 0).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("is_home_list", WifiChooseListActivity.this.c);
                intent.putStringArrayListExtra("result_list", WifiChooseListActivity.this.f11809a);
                WifiChooseListActivity.this.setResult(-1, intent);
                WifiChooseListActivity.this.finish();
            }
        });
        ExpandListView expandListView = (ExpandListView) findViewById(R.id.wifi_list);
        if (expandListView != null) {
            expandListView.setAdapter(new SimpleAdapter());
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiChooseListActivity.this.setResult(0);
                WifiChooseListActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean isSelected(String str) {
        for (int i = 0; i < this.f11809a.size(); i++) {
            if (this.f11809a.get(i).equals(str)) {
                return true;
            }
        }
        return false;
    }

    class SimpleAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        SimpleAdapter() {
        }

        public int getCount() {
            return WifiChooseListActivity.this.b.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            String str = (String) WifiChooseListActivity.this.b.get(i);
            boolean isSelected = WifiChooseListActivity.this.isSelected(str);
            if (view == null) {
                view = WifiChooseListActivity.this.getLayoutInflater().inflate(R.layout.wifi_choose_list_item, (ViewGroup) null);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        boolean booleanValue = ((Boolean) view.getTag()).booleanValue();
                        TextView textView = (TextView) view.findViewById(R.id.wifi_text);
                        ImageView imageView = (ImageView) view.findViewById(R.id.select_flag_image);
                        if (booleanValue) {
                            view.setTag(false);
                            imageView.setImageResource(R.drawable.wifi_check_normal);
                            WifiChooseListActivity.this.f11809a.remove(textView.getText());
                            return;
                        }
                        view.setTag(true);
                        imageView.setImageResource(R.drawable.wifi_check_press);
                        WifiChooseListActivity.this.f11809a.add(textView.getText().toString());
                    }
                });
            }
            TextView textView = (TextView) view.findViewById(R.id.wifi_text);
            if (textView != null) {
                textView.setText(str);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.select_flag_image);
            if (imageView != null) {
                if (isSelected) {
                    imageView.setImageResource(R.drawable.wifi_check_press);
                } else {
                    imageView.setImageResource(R.drawable.wifi_check_normal);
                }
            }
            view.setTag(Boolean.valueOf(isSelected));
            return view;
        }
    }
}
