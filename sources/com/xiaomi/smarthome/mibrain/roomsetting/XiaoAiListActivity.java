package com.xiaomi.smarthome.mibrain.roomsetting;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.SetDeviceRoomActivity;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.Section;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionParameters;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import com.xiaomi.smarthome.mibrain.roomsetting.viewmodel.XiaoAiSettingViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XiaoAiListActivity extends BaseActivity {
    public static final String INTENT_KEY_SHOW_GUIDE = "show_guide";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f10664a = "XiaoAiListActivity";
    private static final int f = 1001;
    private View b;
    private RecyclerView c;
    private SectionedRecyclerViewAdapter d;
    private XQProgressDialog e;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_xiaoai_list_layout);
        a();
        c();
        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra("show_guide", false)) {
            startActivity(new Intent(this, XiaoAiTutorialActivity.class));
        }
    }

    private void a() {
        this.b = findViewById(R.id.common_white_empty_view);
        ((ImageView) this.b.findViewById(R.id.empty_icon)).setImageResource(R.drawable.emptypage_icon_nodevice_nor_1);
        ((TextView) this.b.findViewById(R.id.common_white_empty_text)).setText(R.string.no_xiaoai_at_home);
        this.c = (RecyclerView) findViewById(R.id.recyclerview);
        this.c.setLayoutManager(new LinearLayoutManager(this));
        this.d = new SectionedRecyclerViewAdapter();
        this.c.setAdapter(this.d);
        b();
    }

    private void b() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.voice_control_room_shortcut_instruction);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                XiaoAiListActivity.this.finish();
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.module_a_3_right_iv_setting_btn);
        imageView.setImageResource(R.drawable.title_right_info_drawable);
        imageView.setVisibility(0);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                XiaoAiListActivity.this.startActivity(new Intent(XiaoAiListActivity.this, XiaoAiTutorialActivity.class));
            }
        });
    }

    private void c() {
        ((XiaoAiSettingViewModel) ViewModelProviders.a((FragmentActivity) this).a(XiaoAiSettingViewModel.class)).a().observe(this, new Observer<XiaoAiSettingViewModel.XiaoAiSettingBean>() {
            /* renamed from: a */
            public void onChanged(@Nullable XiaoAiSettingViewModel.XiaoAiSettingBean xiaoAiSettingBean) {
                XiaoAiListActivity.this.a(xiaoAiSettingBean);
                XiaoAiListActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        XiaoAiListActivity.this.f();
                    }
                });
            }
        });
        d();
    }

    private void d() {
        e();
        ((XiaoAiSettingViewModel) ViewModelProviders.a((FragmentActivity) this).a(XiaoAiSettingViewModel.class)).b();
    }

    /* access modifiers changed from: private */
    public void a(XiaoAiSettingViewModel.XiaoAiSettingBean xiaoAiSettingBean) {
        if (xiaoAiSettingBean != null && xiaoAiSettingBean.c != null) {
            this.d.a();
            for (Map.Entry next : xiaoAiSettingBean.c.entrySet()) {
                Home j = HomeManager.a().j((String) next.getKey());
                if (j != null) {
                    this.d.a((Section) new XiaoAiSection(j, (Map) next.getValue()));
                }
            }
            if (this.d.getItemCount() == 0) {
                this.b.setVisibility(0);
                this.c.setVisibility(8);
                return;
            }
            this.b.setVisibility(8);
            this.c.setVisibility(0);
            this.d.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (1001 == i && i2 != 0) {
            String stringExtra = intent.getStringExtra("room_id");
            a(HomeManager.a().i(stringExtra), intent.getStringArrayListExtra(SetDeviceRoomActivity.INTENT_KEY_DIDS));
            finish();
        }
    }

    private void e() {
        f();
        this.e = XQProgressDialog.a(this, (CharSequence) null, getString(R.string.loading));
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void a(Room room, ArrayList<String> arrayList) {
        if (room != null) {
            Intent intent = new Intent(this, XiaoAiSpeechListActivity.class);
            intent.putExtra("home_id", room.f());
            intent.putExtra("room_id", room.d());
            intent.putStringArrayListExtra("dids_list_id", arrayList);
            startActivity(intent);
        }
    }

    private class XiaoAiSection extends Section {
        private Map<String, List<String>> b;
        private Home i;
        private String[] j;
        private String[] k;

        public XiaoAiSection(Home home, Map<String, List<String>> map) {
            super(new SectionParameters.Builder(R.layout.xiaoai_list_setting_item_section).a((int) R.layout.xiaoai_list_setting_item_section_header).a());
            this.b = map == null ? new HashMap<>() : map;
            String[] strArr = map == null ? new String[0] : (String[]) map.keySet().toArray(new String[0]);
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                List list = this.b.get(str);
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(str);
                } else if (list != null && this.k == null) {
                    this.k = (String[]) list.toArray(new String[0]);
                }
            }
            this.j = (String[]) arrayList.toArray(new String[0]);
            this.i = home;
        }

        public int a() {
            return this.j.length + (this.k == null ? 0 : this.k.length);
        }

        public RecyclerView.ViewHolder a(View view) {
            return new HeaderViewHolder(view);
        }

        public RecyclerView.ViewHolder b(View view) {
            return new ChildViewHolder(view);
        }

        public void a(RecyclerView.ViewHolder viewHolder, int i2) {
            final String str;
            String str2;
            if (i2 >= 0) {
                String str3 = "";
                final List list = null;
                if (i2 < this.j.length) {
                    str3 = this.j[i2];
                    list = this.b.get(str3);
                    str = null;
                } else {
                    str = i2 < this.j.length + (this.k == null ? 0 : this.k.length) ? this.k[i2 - this.j.length] : null;
                }
                final Room i3 = HomeManager.a().i(str3);
                if (i3 == null) {
                    str2 = viewHolder.itemView.getResources().getString(R.string.room_not_set);
                } else {
                    str2 = i3.e();
                }
                ChildViewHolder childViewHolder = (ChildViewHolder) viewHolder;
                childViewHolder.b.setText(str2);
                if (list == null || list.isEmpty()) {
                    if (!TextUtils.isEmpty(str)) {
                        Device b2 = SmartHomeDeviceManager.a().b(str);
                        if (b2 != null) {
                            childViewHolder.f10672a.setText(b2.getName());
                        }
                    } else {
                        childViewHolder.f10672a.setText(R.string.tag_no_device);
                        LogUtilGrey.a(XiaoAiListActivity.f10664a, "XiaoAiSection no device 1:" + str);
                    }
                } else if (list.size() == 1) {
                    Device b3 = SmartHomeDeviceManager.a().b((String) list.get(0));
                    if (b3 == null) {
                        LogUtilGrey.a(XiaoAiListActivity.f10664a, "XiaoAiSection no device 2:" + ((String) list.get(0)));
                        childViewHolder.f10672a.setText(R.string.tag_no_device);
                    } else {
                        childViewHolder.f10672a.setText(b3.getName());
                    }
                } else {
                    childViewHolder.f10672a.setText(childViewHolder.f10672a.getResources().getQuantityString(R.plurals.room_xiaoai_count, list.size(), new Object[]{Integer.valueOf(list.size())}));
                }
                childViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (i3 != null) {
                            XiaoAiListActivity.this.a(i3, (ArrayList) list);
                        } else if (TextUtils.isEmpty(str)) {
                            Log.d(XiaoAiListActivity.f10664a, "empty did list for xiaoai");
                        } else {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(str);
                            XiaoAiSection.this.a((List<String>) arrayList);
                        }
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public void a(final List<String> list) {
            new MLAlertDialog.Builder(XiaoAiListActivity.this).b((int) R.string.select_a_room_for_xiaoai).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.setting, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Home q = HomeManager.a().q((String) list.get(0));
                    if (q != null) {
                        SetDeviceRoomActivity.startActivity(XiaoAiListActivity.this, q.j(), (ArrayList) list, 1001);
                    }
                    dialogInterface.dismiss();
                }
            }).d();
        }

        public void a(RecyclerView.ViewHolder viewHolder) {
            ((HeaderViewHolder) viewHolder).f10673a.setText(this.i.k());
        }

        private class HeaderViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f10673a;

            public HeaderViewHolder(View view) {
                super(view);
                this.f10673a = (TextView) view.findViewById(R.id.name_tv);
            }
        }

        private class ChildViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f10672a;
            TextView b;

            public ChildViewHolder(View view) {
                super(view);
                this.f10672a = (TextView) view.findViewById(R.id.device_cnt_tv);
                this.b = (TextView) view.findViewById(R.id.room_name_tv);
            }
        }
    }
}
