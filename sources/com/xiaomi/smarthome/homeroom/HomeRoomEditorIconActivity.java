package com.xiaomi.smarthome.homeroom;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.ExpandGridView;
import java.util.ArrayList;
import java.util.List;

public class HomeRoomEditorIconActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18090a = "HomeRoomEditorIconActivity";
    /* access modifiers changed from: private */
    public RoomPicAdapter b;
    /* access modifiers changed from: private */
    public RoomPicAdapter c;
    private View d;
    private ExpandGridView e;
    private ExpandGridView f;
    /* access modifiers changed from: private */
    public SimpleDraweeView g;
    private View h;
    private String i;
    /* access modifiers changed from: private */
    public String j;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_room_editor_icon);
        this.i = getIntent().getStringExtra("room_name");
        this.j = getIntent().getStringExtra("room_icon");
        a();
        b();
    }

    private void a() {
        findViewById(R.id.title_bar).setBackgroundColor(16777215);
        this.h = findViewById(R.id.module_a_3_return_btn);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeRoomEditorIconActivity.this.setResult(-1, HomeRoomEditorIconActivity.this.getIntent().putExtra("room_icon", HomeRoomEditorIconActivity.this.j));
                HomeRoomEditorIconActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(this.i);
        this.g = (SimpleDraweeView) findViewById(R.id.iv_result);
        this.g.setImageURI(Uri.parse("file://" + HomeManager.a().f(this.j)));
        this.d = findViewById(R.id.rl_recommend);
        this.e = (ExpandGridView) findViewById(R.id.gv_recommend);
        this.e.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
                /*
                    r0 = this;
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity$RoomPicAdapter r2 = r2.b
                    r2.b(r3)
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity$RoomPicAdapter r2 = r2.b
                    r2.notifyDataSetInvalidated()
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity$RoomPicAdapter r2 = r2.c
                    int r2 = r2.a()
                    r4 = -1
                    if (r2 == r4) goto L_0x0031
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity$RoomPicAdapter r2 = r2.c
                    r2.b(r4)
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity$RoomPicAdapter r2 = r2.c
                    r2.notifyDataSetInvalidated()
                L_0x0031:
                    android.widget.Adapter r1 = r1.getAdapter()
                    java.lang.Object r1 = r1.getItem(r3)
                    java.lang.String r1 = (java.lang.String) r1
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    java.lang.String unused = r2.j = r1
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "file://"
                    r2.append(r3)
                    com.xiaomi.smarthome.homeroom.HomeManager r3 = com.xiaomi.smarthome.homeroom.HomeManager.a()
                    java.lang.String r1 = r3.f((java.lang.String) r1)
                    r2.append(r1)
                    java.lang.String r1 = r2.toString()
                    android.net.Uri r1 = android.net.Uri.parse(r1)
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    com.facebook.drawee.view.SimpleDraweeView r2 = r2.g
                    r2.setImageURI((android.net.Uri) r1)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.AnonymousClass2.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
            }
        });
        this.f = (ExpandGridView) findViewById(R.id.gv_all);
        this.f.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
                /*
                    r0 = this;
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity$RoomPicAdapter r2 = r2.c
                    r2.b(r3)
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity$RoomPicAdapter r2 = r2.c
                    r2.notifyDataSetInvalidated()
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity$RoomPicAdapter r2 = r2.b
                    int r2 = r2.a()
                    r4 = -1
                    if (r2 == r4) goto L_0x0031
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity$RoomPicAdapter r2 = r2.b
                    r2.b(r4)
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity$RoomPicAdapter r2 = r2.b
                    r2.notifyDataSetInvalidated()
                L_0x0031:
                    android.widget.Adapter r1 = r1.getAdapter()
                    java.lang.Object r1 = r1.getItem(r3)
                    java.lang.String r1 = (java.lang.String) r1
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    java.lang.String unused = r2.j = r1
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "file://"
                    r2.append(r3)
                    com.xiaomi.smarthome.homeroom.HomeManager r3 = com.xiaomi.smarthome.homeroom.HomeManager.a()
                    java.lang.String r1 = r3.f((java.lang.String) r1)
                    r2.append(r1)
                    java.lang.String r1 = r2.toString()
                    android.net.Uri r1 = android.net.Uri.parse(r1)
                    com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity r2 = com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.this
                    com.facebook.drawee.view.SimpleDraweeView r2 = r2.g
                    r2.setImageURI((android.net.Uri) r1)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.HomeRoomEditorIconActivity.AnonymousClass3.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
            }
        });
        this.b = new RoomPicAdapter(this);
        this.c = new RoomPicAdapter(this);
        this.e.setAdapter(this.b);
        this.f.setAdapter(this.c);
    }

    private void b() {
        this.b.a(HomeManager.a().c(this.j));
        this.c.a(HomeManager.a().d());
    }

    public void onBackPressed() {
        setResult(-1, getIntent().putExtra("room_icon", this.j));
        finish();
    }

    class RoomPicAdapter extends BaseAdapter {
        private List<String> b;
        private Context c;
        private int d = -1;

        public long getItemId(int i) {
            return 0;
        }

        public RoomPicAdapter(Context context) {
            this.c = context;
        }

        public void a(List<String> list) {
            if (list == null) {
                this.b = new ArrayList();
            } else {
                this.b = list;
            }
            notifyDataSetChanged();
        }

        public int getCount() {
            if (this.b == null) {
                return 0;
            }
            return this.b.size();
        }

        /* renamed from: a */
        public String getItem(int i) {
            if (i < 0 || this.b == null || i >= this.b.size()) {
                return null;
            }
            return this.b.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(this.c).inflate(R.layout.item_room_icon, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f18095a = (SimpleDraweeView) view.findViewById(R.id.room_icon);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            String str = this.b.get(i);
            if (i == this.d) {
                viewHolder.f18095a.setImageURI(Uri.parse("file://" + HomeManager.a().g(str)));
                viewHolder.f18095a.setBackgroundResource(R.drawable.shape_bg_room_icon_select);
            } else {
                viewHolder.f18095a.setImageURI(Uri.parse("file://" + HomeManager.a().f(str)));
                viewHolder.f18095a.setBackgroundResource(R.drawable.shape_bg_room_icon);
            }
            return view;
        }

        public void b(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            SimpleDraweeView f18095a;

            ViewHolder() {
            }
        }
    }
}
