package com.mics.core.ui.page;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.biubiu.kit.core.AbsKit;
import com.biubiu.kit.core.KitBaseAdapter;
import com.mics.R;
import com.mics.core.MiCS;
import com.mics.util.Divider;
import com.xiaomi.smarthome.download.Downloads;
import java.util.ArrayList;
import java.util.List;

public class SettingSDKActivity extends AppCompatActivity {

    /* renamed from: a  reason: collision with root package name */
    private KitBaseAdapter f7734a;
    private List<Object> b;
    private String[] c = {"channel", Downloads.COLUMN_REFERER, "appName", "appVersion", "deviceId", "userName", "userId", "userAvatar", "userGender", "sdkVersion"};
    private String[] d = {MiCS.a().i(), MiCS.a().j(), MiCS.a().k(), MiCS.a().l(), MiCS.a().m(), MiCS.a().o(), MiCS.a().n(), MiCS.a().p(), MiCS.a().q() + "", "1.0"};

    public static class Data {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public CharSequence f7735a;
        /* access modifiers changed from: private */
        public CharSequence b;
        private String c;
        /* access modifiers changed from: private */
        public boolean d;
    }

    private String a() {
        return "SDK";
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mics_activity_setting_host);
        b();
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

    private void b() {
        this.b = new ArrayList();
        this.f7734a = new KitBaseAdapter(this, this.b);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_host);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.f7734a);
        recyclerView.addItemDecoration(new Divider(this, 0, (int) TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics()), Color.parseColor("#f5f5f5"), true));
        c();
    }

    private void c() {
        this.b.clear();
        this.b.add(new HostTitleKit.Data("初始化配置信息"));
        for (int i = 0; i < this.c.length; i++) {
            Data data = new Data();
            CharSequence unused = data.f7735a = this.c[i];
            CharSequence unused2 = data.b = String.format("%s", new Object[]{this.d[i]});
            this.b.add(data);
        }
        this.f7734a.notifyDataSetChanged();
    }

    public static class HostKit extends AbsKit {

        /* renamed from: a  reason: collision with root package name */
        private TextView f7736a;
        private TextView b;
        private ImageView c;

        public View a(ViewGroup viewGroup) {
            View a2 = a(viewGroup, R.layout.mics_item_setting_host);
            this.f7736a = (TextView) a2.findViewById(R.id.tv_host_title);
            this.b = (TextView) a2.findViewById(R.id.tv_host_content);
            this.c = (ImageView) a2.findViewById(R.id.iv_host_selected);
            return a2;
        }

        public void a(int i, Object obj) {
            if (obj instanceof Data) {
                Data data = (Data) obj;
                this.f7736a.setText(data.f7735a);
                int i2 = 0;
                if (TextUtils.isEmpty(data.b)) {
                    this.b.setVisibility(8);
                } else {
                    this.b.setVisibility(0);
                    this.b.setText(data.b);
                }
                ImageView imageView = this.c;
                if (!data.d) {
                    i2 = 8;
                }
                imageView.setVisibility(i2);
            }
        }
    }

    public static class HostTitleKit extends AbsKit {

        /* renamed from: a  reason: collision with root package name */
        private TextView f7737a;

        public View a(ViewGroup viewGroup) {
            View a2 = a(viewGroup, R.layout.mics_item_setting_host_title);
            this.f7737a = (TextView) a2.findViewById(R.id.tv_host_title_item);
            this.f7737a.setText("请选择服务器环境");
            return a2;
        }

        public void a(int i, Object obj) {
            if (obj instanceof Data) {
                Data data = (Data) obj;
                if (!TextUtils.isEmpty(data.f7738a)) {
                    this.f7737a.setText(data.f7738a);
                } else {
                    this.f7737a.setText("");
                }
            }
        }

        public static class Data {

            /* renamed from: a  reason: collision with root package name */
            public String f7738a;

            public Data(String str) {
                this.f7738a = str;
            }
        }
    }
}
