package com.xiaomi.smarthome.lite.scene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.scene.CreateSceneManager;
import com.xiaomi.smarthome.scene.action.BaseInnerAction;
import com.xiaomi.smarthome.scene.action.InnerActionCommon;
import com.xiaomi.smarthome.scene.condition.BaseInnerCondition;
import com.xiaomi.smarthome.scene.condition.InnerConditionCommon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LiteSceneActionDetailPage extends BaseActivity {
    public static final String EXTRA_GROUP_ID = "extra_group_id";
    public static final String EXTRA_INDEX = "extra_index";
    public static final String EXTRA_SELECTED_INDEX = "extra_selected_title";
    public static final String EXTRA_SHOW_GROUP_INFO = "show_group_info";
    public static final String EXTRA_TITLE = "extra_title";
    public static final int GET_CONDITION_DETAIL = 101;

    /* renamed from: a  reason: collision with root package name */
    private BaseInnerCondition f19383a;
    private BaseInnerAction b;
    private ItemAdapter c;
    /* access modifiers changed from: private */
    public int d = -1;
    /* access modifiers changed from: private */
    public int e = -1;
    /* access modifiers changed from: private */
    public boolean f = true;
    private int g;
    /* access modifiers changed from: private */
    public List<Integer> h = new ArrayList();
    /* access modifiers changed from: private */
    public HashMap<Integer, String> i = new HashMap<>();
    /* access modifiers changed from: private */
    public List<String> j = new ArrayList();
    @BindView(2131430969)
    ImageView mBackBtn;
    @BindView(2131428548)
    ListView mContentList;
    @BindView(2131430975)
    TextView mTitle;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_scene_detail);
        ButterKnife.bind((Activity) this);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LiteSceneActionDetailPage.this.finish();
            }
        });
        this.f19383a = CreateSceneManager.a().h();
        this.b = CreateSceneManager.a().j();
        String stringExtra = getIntent().getStringExtra("extra_title");
        this.e = getIntent().getIntExtra("extra_selected_title", -1);
        this.g = getIntent().getIntExtra("extra_group_id", -1);
        this.f = getIntent().getBooleanExtra("show_group_info", true);
        this.mTitle.setText(stringExtra);
        this.c = new ItemAdapter();
        this.mContentList.setAdapter(this.c);
        if (this.b != null) {
            int i2 = 0;
            if (!(this.b instanceof InnerActionCommon) || ((InnerActionCommon) this.b).f() == null) {
                this.f = false;
            }
            if (this.f) {
                ArrayList arrayList = new ArrayList();
                for (int valueOf : ((InnerActionCommon) this.b).f()) {
                    arrayList.add(Integer.valueOf(valueOf));
                }
                this.b.d();
                ArrayList arrayList2 = new ArrayList();
                if (this.e != -1) {
                    int[] c2 = this.b.c();
                    int length = c2.length;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        }
                        Integer valueOf2 = Integer.valueOf(c2[i2]);
                        if (valueOf2.intValue() == this.e) {
                            this.d = ((InnerActionCommon) this.b).f(valueOf2.intValue());
                            break;
                        }
                        i2++;
                    }
                }
                generateDisplayIds(arrayList2, arrayList);
                return;
            }
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            arrayList4.addAll(this.b.d());
            if (this.g != -1) {
                for (int i3 = 0; i3 < ((InnerActionCommon) this.b).c().length; i3++) {
                    if (this.g == ((InnerActionCommon) this.b).f(i3)) {
                        arrayList3.add(Integer.valueOf(this.b.c()[i3]));
                    }
                }
            } else {
                for (int valueOf3 : this.b.c()) {
                    arrayList3.add(Integer.valueOf(valueOf3));
                }
            }
            while (i2 < arrayList3.size()) {
                if (!CreateSceneManager.a().b(Integer.valueOf(this.b.a(((Integer) arrayList3.get(i2)).intValue())))) {
                    arrayList4.add(arrayList3.get(i2));
                }
                i2++;
            }
            generateDisplayIds(arrayList4, arrayList3);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        int intExtra;
        if (this.f && i3 == -1 && (intExtra = intent.getIntExtra("extra_index", -1)) != -1) {
            Intent intent2 = new Intent();
            intent2.putExtra("extra_index", intExtra);
            setResult(-1, intent2);
            finish();
            CreateSceneManager.a().l();
        }
    }

    class ItemAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        ItemAdapter() {
        }

        public int getCount() {
            return LiteSceneActionDetailPage.this.h.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(LiteSceneActionDetailPage.this).inflate(R.layout.lite_scene_action_detail_item, viewGroup, false);
            }
            view.findViewById(R.id.root).setBackgroundResource(i != getCount() - 1 ? R.drawable.choose_device_list_item_last_position_bg : R.drawable.common_white_list_padding_no_left_margin);
            view.findViewById(R.id.divide_line).setVisibility(i != getCount() - 1 ? 0 : 8);
            if ((!LiteSceneActionDetailPage.this.f || ((Integer) LiteSceneActionDetailPage.this.h.get(i)).intValue() != LiteSceneActionDetailPage.this.d) && (LiteSceneActionDetailPage.this.f || ((Integer) LiteSceneActionDetailPage.this.h.get(i)).intValue() != LiteSceneActionDetailPage.this.e)) {
                view.findViewById(R.id.description_text).setSelected(false);
                view.findViewById(R.id.expand_hint).setVisibility(8);
            } else {
                view.findViewById(R.id.description_text).setSelected(true);
                view.findViewById(R.id.expand_hint).setVisibility(8);
            }
            ((TextView) view.findViewById(R.id.description_text)).setText((CharSequence) LiteSceneActionDetailPage.this.j.get(i));
            if (LiteSceneActionDetailPage.this.i.containsKey(LiteSceneActionDetailPage.this.h.get(i))) {
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(LiteSceneActionDetailPage.this, R.string.scene_unclickable_toast_2, 0).show();
                    }
                });
                view.findViewById(R.id.lock_item).setVisibility(0);
            } else {
                view.findViewById(R.id.lock_item).setVisibility(8);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (LiteSceneActionDetailPage.this.f) {
                            Intent intent = new Intent();
                            intent.setClass(LiteSceneActionDetailPage.this, LiteSceneActionDetailPage.class);
                            if (LiteSceneActionDetailPage.this.e != -1) {
                                intent.putExtra("extra_selected_title", LiteSceneActionDetailPage.this.e);
                            }
                            intent.putExtra("extra_title", (String) LiteSceneActionDetailPage.this.j.get(i));
                            intent.putExtra("extra_group_id", (Serializable) LiteSceneActionDetailPage.this.h.get(i));
                            intent.putExtra("show_group_info", false);
                            LiteSceneActionDetailPage.this.startActivityForResult(intent, 101);
                            return;
                        }
                        Intent intent2 = new Intent();
                        intent2.putExtra("extra_index", (Serializable) LiteSceneActionDetailPage.this.h.get(i));
                        LiteSceneActionDetailPage.this.setResult(-1, intent2);
                        LiteSceneActionDetailPage.this.finish();
                        CreateSceneManager.a().l();
                    }
                });
            }
            return view;
        }
    }

    public void generateDisplayIds(List<Integer> list, List<Integer> list2) {
        for (Integer num : list) {
            SceneLogUtil.a("selectedId-----" + num);
        }
        for (Integer num2 : list2) {
            SceneLogUtil.a("ids-----" + num2);
        }
        this.h.clear();
        this.h.addAll(list2);
        for (Integer put : list) {
            this.i.put(put, "Disable");
        }
        if (this.f19383a != null) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                if (this.f) {
                    this.j.add(((InnerConditionCommon) this.f19383a).d(this.h.get(i2).intValue()));
                } else {
                    this.j.add(this.f19383a.b(this.h.get(i2).intValue()));
                }
            }
        }
        if (this.b != null) {
            for (int i3 = 0; i3 < this.h.size(); i3++) {
                if (this.f) {
                    this.j.add(((InnerActionCommon) this.b).e(this.h.get(i3).intValue()));
                } else {
                    this.j.add(this.b.c(this.h.get(i3).intValue()));
                }
            }
        }
    }
}
