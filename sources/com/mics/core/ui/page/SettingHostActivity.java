package com.mics.core.ui.page;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Pair;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.biubiu.kit.core.AbsKit;
import com.biubiu.kit.core.KitBaseAdapter;
import com.mics.R;
import com.mics.constant.API;
import com.mics.util.Divider;
import com.mics.util.Logger;
import java.util.ArrayList;
import java.util.List;

public class SettingHostActivity extends AppCompatActivity {

    /* renamed from: a  reason: collision with root package name */
    private KitBaseAdapter f7730a;
    private List<Object> b;
    private int c;

    public static class Data {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f7732a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public boolean c;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mics_activity_setting_host);
        this.c = getIntent().getIntExtra("setting_type", 0);
        d();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            try {
                Drawable drawable = getResources().getDrawable(R.drawable.mics_back_material);
                drawable.setColorFilter(getResources().getColor(R.color.micsColorAccent), PorterDuff.Mode.SRC_ATOP);
                getSupportActionBar().setHomeAsUpIndicator(drawable);
                ActionBar supportActionBar = getSupportActionBar();
                supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color='" + "#4290f7" + "'>" + a() + "</font>"));
            } catch (Exception unused) {
                getSupportActionBar().setTitle((CharSequence) a());
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    private String a() {
        if (this.c == 0) {
            return "Nothing";
        }
        if (this.c == 1) {
            return "Http";
        }
        return this.c == 2 ? "WebSocket" : "Nothing";
    }

    private List<Pair<String, String>> b() {
        if (this.c == 0) {
            Logger.d("请设置type", new Object[0]);
        } else if (this.c == 1) {
            return API.s();
        } else {
            if (this.c == 2) {
                return API.w();
            }
        }
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (this.c == 0) {
            Logger.d("请设置type", new Object[0]);
        } else if (this.c == 1) {
            API.a(str);
        } else if (this.c == 2) {
            API.b(str);
        }
    }

    private String c() {
        if (this.c == 0) {
            Logger.d("请设置type", new Object[0]);
        } else if (this.c == 1) {
            return API.t();
        } else {
            if (this.c == 2) {
                return API.x();
            }
        }
        return API.t();
    }

    private void d() {
        this.b = new ArrayList();
        this.f7730a = new Adapter(this, this.b);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_host);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.f7730a);
        recyclerView.addItemDecoration(new Divider(this, 0, (int) TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics()), Color.parseColor("#f5f5f5"), true));
        e();
    }

    /* access modifiers changed from: private */
    public void e() {
        this.b.clear();
        this.b.add(new HostTitleKit.Data());
        for (Pair next : b()) {
            Data data = new Data();
            String str = (String) next.first;
            String unused = data.b = str;
            String unused2 = data.f7732a = ((String) next.second) + "          " + str;
            boolean unused3 = data.c = c().equals(str);
            this.b.add(data);
        }
        this.f7730a.notifyDataSetChanged();
    }

    private class Adapter extends KitBaseAdapter {
        public Adapter(Context context, List<Object> list) {
            super(context, list);
        }

        /* access modifiers changed from: package-private */
        public void c() {
            SettingHostActivity.this.e();
            SettingHostActivity.this.finish();
        }

        /* access modifiers changed from: package-private */
        public void a(String str) {
            SettingHostActivity.this.a(str);
        }
    }

    public static class HostKit extends AbsKit implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        private Adapter f7733a;
        private TextView b;
        private ImageView c;
        private Data d;

        public void a(KitBaseAdapter kitBaseAdapter) {
            super.a(kitBaseAdapter);
            if (kitBaseAdapter instanceof Adapter) {
                this.f7733a = (Adapter) kitBaseAdapter;
            }
        }

        public View a(ViewGroup viewGroup) {
            View a2 = a(viewGroup, R.layout.mics_item_setting_host);
            this.b = (TextView) a2.findViewById(R.id.tv_host_title);
            this.c = (ImageView) a2.findViewById(R.id.iv_host_selected);
            a2.setOnClickListener(this);
            return a2;
        }

        public void a(int i, Object obj) {
            if (obj instanceof Data) {
                Data data = (Data) obj;
                this.d = data;
                this.b.setText(data.f7732a);
                this.c.setVisibility(data.c ? 0 : 8);
            }
        }

        public void onClick(View view) {
            if (this.f7733a != null) {
                this.f7733a.a(this.d.b);
                this.f7733a.c();
            }
        }
    }

    public static class HostTitleKit extends AbsKit {

        public static class Data {
        }

        public void a(int i, Object obj) {
        }

        public View a(ViewGroup viewGroup) {
            View a2 = a(viewGroup, R.layout.mics_item_setting_host_title);
            ((TextView) a2.findViewById(R.id.tv_host_title_item)).setText("请选择服务器环境");
            return a2;
        }
    }
}
