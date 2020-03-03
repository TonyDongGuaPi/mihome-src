package com.xiaomi.smarthome.family;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.family.FamilyManager;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class FamilyListActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public CustomPullDownRefreshListView f15690a;
    private View b;
    private View c;
    /* access modifiers changed from: private */
    public FamilyListAdapter d;
    /* access modifiers changed from: private */
    public List<FamilyItemData> e = new ArrayList();
    private FamilyManager.Listener f = new FamilyManager.Listener() {
        public void a(FamilyData familyData) {
        }

        public void a() {
            FamilyListActivity.this.e.clear();
            List unused = FamilyListActivity.this.e = FamilyManager.a().b();
            FamilyListActivity.this.d.notifyDataSetChanged();
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.family_list_layout);
        a();
        b();
        this.d = new FamilyListAdapter();
        this.f15690a.setAdapter(this.d);
    }

    private void a() {
        this.f15690a = (CustomPullDownRefreshListView) findViewById(R.id.pull_down_lv_family_list);
        this.b = findViewById(R.id.empty_bg);
        this.c = findViewById(R.id.load_error_toast);
    }

    public void onResume() {
        super.onResume();
        FamilyManager.a().a(this.f);
        List<FamilyItemData> b2 = FamilyManager.a().b();
        if (b2 != null) {
            this.e = b2;
            this.d.notifyDataSetChanged();
        }
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                FamilyListActivity.this.f15690a.doRefresh();
            }
        }, 500);
    }

    public void onPause() {
        super.onPause();
        FamilyManager.a().b(this.f);
    }

    public void onDestroy() {
        super.onDestroy();
        this.f15690a = null;
    }

    private void b() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.family_list_name);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyListActivity.this.finish();
            }
        });
        this.f15690a.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                FamilyListActivity.this.d();
            }
        });
        findViewById(R.id.btn_create_family).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FamilyListActivity.this, FamilyCreateEditActivity.class);
                intent.putExtra(FamilyCreateEditActivity.CREATE_FAMILY_KEY, true);
                FamilyListActivity.this.startActivity(intent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            this.f15690a.setVisibility(8);
            this.b.setVisibility(0);
            this.c.setVisibility(8);
            return;
        }
        this.f15690a.setVisibility(0);
        this.b.setVisibility(8);
        this.c.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void c() {
        this.f15690a.setVisibility(0);
        this.b.setVisibility(8);
        this.c.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void d() {
        FamilyManager.a().a((Context) this, (AsyncResponseCallback<List<FamilyItemData>>) new AsyncResponseCallback<List<FamilyItemData>>() {
            public void a(List<FamilyItemData> list) {
                if (FamilyListActivity.this.f15690a != null) {
                    FamilyListActivity.this.e.clear();
                    List unused = FamilyListActivity.this.e = list;
                    FamilyListActivity.this.d.notifyDataSetChanged();
                    FamilyListActivity.this.f15690a.postRefresh();
                    FamilyListActivity.this.a(FamilyListActivity.this.e.size() == 0);
                }
            }

            public void a(int i) {
                if (FamilyListActivity.this.f15690a != null) {
                    FamilyListActivity.this.f15690a.postRefresh();
                    if (FamilyListActivity.this.e.size() > 0) {
                        FamilyListActivity.this.a(false);
                    } else {
                        FamilyListActivity.this.c();
                    }
                }
            }

            public void a(int i, Object obj) {
                if (FamilyListActivity.this.f15690a != null) {
                    FamilyListActivity.this.f15690a.postRefresh();
                    if (FamilyListActivity.this.e.size() > 0) {
                        FamilyListActivity.this.a(false);
                    } else {
                        FamilyListActivity.this.c();
                    }
                }
            }
        });
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f15699a;
        ImageView b;
        TextView c;
        CheckBox d;
        TextView e;
        TextView f;

        ViewHolder() {
        }
    }

    class FamilyListAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return 0;
        }

        FamilyListAdapter() {
        }

        public int getCount() {
            return FamilyListActivity.this.e.size();
        }

        public Object getItem(int i) {
            if (i < 0 || i >= FamilyListActivity.this.e.size()) {
                return null;
            }
            return FamilyListActivity.this.e.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(FamilyListActivity.this).inflate(R.layout.family_list_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.b = (ImageView) view.findViewById(R.id.device_icon);
                viewHolder.c = (TextView) view.findViewById(R.id.right_tv);
                viewHolder.d = (CheckBox) view.findViewById(R.id.ratio_btn);
                viewHolder.e = (TextView) view.findViewById(R.id.device_item);
                viewHolder.f = (TextView) view.findViewById(R.id.device_item_info);
                viewHolder.f15699a = view.findViewById(R.id.right_fl);
                viewHolder.b.setVisibility(8);
                viewHolder.c.setVisibility(8);
                viewHolder.d.setVisibility(8);
                viewHolder.f.setVisibility(8);
                viewHolder.f15699a.setVisibility(8);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            view.setOnClickListener((View.OnClickListener) null);
            final FamilyItemData familyItemData = (FamilyItemData) FamilyListActivity.this.e.get(i);
            if (familyItemData != null) {
                viewHolder.e.setText(familyItemData.g);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(FamilyListActivity.this, FamilyCreateEditActivity.class);
                        intent.putExtra(FamilyItemData.f15689a, familyItemData);
                        intent.putExtra(FamilyCreateEditActivity.CREATE_FAMILY_KEY, false);
                        FamilyListActivity.this.startActivity(intent);
                    }
                });
            }
            return view;
        }
    }
}
