package com.xiaomi.smarthome.mibrain.roomsetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.Section;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionParameters;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import com.xiaomi.smarthome.mibrain.roomsetting.model.XiaoAiVoiceCategory;
import com.xiaomi.smarthome.ui.DeviceBigHeaderView;
import java.util.ArrayList;
import java.util.List;

public class XiaoAiSpeechListActivity extends BaseActivity {
    public static final String INTENT_KEY_DIDS_LIST = "dids_list_id";
    public static final String INTENT_KEY_HOME_ID = "home_id";
    public static final String INTENT_KEY_ROOM_ID = "room_id";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Home f10678a;
    /* access modifiers changed from: private */
    public Room b;
    /* access modifiers changed from: private */
    public List<String> c;
    private DeviceBigHeaderView d;
    private TextView e;
    private RecyclerView f;
    private SectionedRecyclerViewAdapter g;
    private View h;
    private XQProgressDialog i;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        setContentView(R.layout.activity_xiaoai_speech_list_layout);
        a();
        a(intent);
    }

    private void a() {
        this.d = (DeviceBigHeaderView) findViewById(R.id.device_big_header_view);
        this.e = (TextView) findViewById(R.id.home_room_name_tv);
        this.f = (RecyclerView) findViewById(R.id.recyclerview);
        this.f.setLayoutManager(new LinearLayoutManager(this));
        this.g = new SectionedRecyclerViewAdapter();
        this.f.setAdapter(this.g);
        this.h = findViewById(R.id.multi_xiaoai_tips_tv);
        b();
    }

    private void b() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.mi_brain_most_used_speech_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                XiaoAiSpeechListActivity.this.finish();
            }
        });
    }

    private void a(Intent intent) {
        String stringExtra = intent.getStringExtra("home_id");
        this.f10678a = HomeManager.a().j(stringExtra);
        if (this.f10678a == null) {
            LogUtilGrey.a("XiaoAiSpeechListActivity", "mHome is null for homeId=" + stringExtra + ", finish activity");
            finish();
            return;
        }
        this.b = HomeManager.a().i(intent.getStringExtra("room_id"));
        this.c = intent.getStringArrayListExtra("dids_list_id");
        d();
        c();
    }

    private void c() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            Device b2 = SmartHomeDeviceManager.a().b(this.c.get(i2));
            if (b2 != null) {
                arrayList.add(new DeviceBigHeaderView.DeviceBigHeaderModel(b2.model, b2.name));
            }
        }
        this.d.setModel(arrayList);
        TextView textView = this.e;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f10678a.k());
        sb.append(" | ");
        sb.append(this.b == null ? getResources().getString(R.string.default_room) : this.b.e());
        textView.setText(sb.toString());
        if (arrayList.size() > 1) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
        }
    }

    private void d() {
        if (this.c.size() > 0) {
            e();
            XiaoAiRoomSettingManager.a().a(this.b, this.c.get(0), (AsyncCallback<List<XiaoAiVoiceCategory>, Error>) new AsyncCallback<List<XiaoAiVoiceCategory>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<XiaoAiVoiceCategory> list) {
                    if (XiaoAiSpeechListActivity.this.isValid()) {
                        XiaoAiSpeechListActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                XiaoAiSpeechListActivity.this.f();
                            }
                        });
                        XiaoAiSpeechListActivity.this.a(list);
                    }
                }

                public void onFailure(Error error) {
                    if (XiaoAiSpeechListActivity.this.isValid()) {
                        XiaoAiSpeechListActivity.this.f();
                    }
                }

                /* renamed from: b */
                public void onCache(List<XiaoAiVoiceCategory> list) {
                    if (XiaoAiSpeechListActivity.this.isValid()) {
                        XiaoAiSpeechListActivity.this.f();
                        XiaoAiSpeechListActivity.this.a(list);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(List<XiaoAiVoiceCategory> list) {
        if (list != null) {
            this.g.a();
            this.g.a((Section) new XiaoAiSpeechSection(list));
            this.g.notifyDataSetChanged();
        }
    }

    private void e() {
        f();
        this.i = XQProgressDialog.a(this, (CharSequence) null, getString(R.string.loading));
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.i != null && this.i.isShowing()) {
            this.i.dismiss();
        }
    }

    private class XiaoAiSpeechSection extends Section {
        private List<XiaoAiVoiceCategory> b;

        public XiaoAiSpeechSection(List<XiaoAiVoiceCategory> list) {
            super(new SectionParameters.Builder(R.layout.xiaoai_speech_list_item_section).a((int) R.layout.xiaoai_list_setting_item_section_header).a());
            this.b = list;
        }

        public int a() {
            return this.b.size();
        }

        public RecyclerView.ViewHolder a(View view) {
            return new HeaderViewHolder(view);
        }

        public RecyclerView.ViewHolder b(View view) {
            return new ChildViewHolder(view);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000b, code lost:
            r8 = r6.b.get(r8);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(android.support.v7.widget.RecyclerView.ViewHolder r7, int r8) {
            /*
                r6 = this;
                if (r8 < 0) goto L_0x006c
                java.util.List<com.xiaomi.smarthome.mibrain.roomsetting.model.XiaoAiVoiceCategory> r0 = r6.b
                int r0 = r0.size()
                if (r8 < r0) goto L_0x000b
                goto L_0x006c
            L_0x000b:
                java.util.List<com.xiaomi.smarthome.mibrain.roomsetting.model.XiaoAiVoiceCategory> r0 = r6.b
                java.lang.Object r8 = r0.get(r8)
                com.xiaomi.smarthome.mibrain.roomsetting.model.XiaoAiVoiceCategory r8 = (com.xiaomi.smarthome.mibrain.roomsetting.model.XiaoAiVoiceCategory) r8
                java.util.List r0 = r8.c()
                if (r0 != 0) goto L_0x001a
                return
            L_0x001a:
                r1 = r7
                com.xiaomi.smarthome.mibrain.roomsetting.XiaoAiSpeechListActivity$XiaoAiSpeechSection$ChildViewHolder r1 = (com.xiaomi.smarthome.mibrain.roomsetting.XiaoAiSpeechListActivity.XiaoAiSpeechSection.ChildViewHolder) r1
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                r3 = 0
            L_0x0023:
                int r4 = r0.size()
                if (r3 >= r4) goto L_0x0058
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "“"
                r4.append(r5)
                java.lang.Object r5 = r0.get(r3)
                java.lang.String r5 = (java.lang.String) r5
                r4.append(r5)
                java.lang.String r5 = "”"
                r4.append(r5)
                java.lang.String r4 = r4.toString()
                r2.append(r4)
                int r4 = r0.size()
                int r4 = r4 + -1
                if (r3 >= r4) goto L_0x0055
                java.lang.String r4 = "、"
                r2.append(r4)
            L_0x0055:
                int r3 = r3 + 1
                goto L_0x0023
            L_0x0058:
                android.widget.TextView r0 = r1.f10684a
                java.lang.String r1 = r2.toString()
                r0.setText(r1)
                android.view.View r7 = r7.itemView
                com.xiaomi.smarthome.mibrain.roomsetting.XiaoAiSpeechListActivity$XiaoAiSpeechSection$1 r0 = new com.xiaomi.smarthome.mibrain.roomsetting.XiaoAiSpeechListActivity$XiaoAiSpeechSection$1
                r0.<init>(r8)
                r7.setOnClickListener(r0)
                return
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.mibrain.roomsetting.XiaoAiSpeechListActivity.XiaoAiSpeechSection.a(android.support.v7.widget.RecyclerView$ViewHolder, int):void");
        }

        private class HeaderViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f10685a;

            public HeaderViewHolder(View view) {
                super(view);
                this.f10685a = (TextView) view.findViewById(R.id.name_tv);
                this.f10685a.setText(R.string.mi_brain_select_follow_xiaoai_to_control);
            }
        }

        private class ChildViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f10684a;

            public ChildViewHolder(View view) {
                super(view);
                this.f10684a = (TextView) view.findViewById(R.id.speech_tv);
            }
        }
    }
}
