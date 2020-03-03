package com.xiaomi.smarthome.scene.geofence.manager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.libra.Color;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.cli.HelpFormatter;

public class MIUIGeoLogActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static SimpleDateFormat f21545a = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
    /* access modifiers changed from: private */
    public static RecyclerView.Adapter c;
    public static List<LogData> data = new ArrayList();
    private RecyclerView b;

    public static void addMsg(String str, String str2) {
        LogData logData = new LogData();
        logData.f21548a = f21545a.format(new Date());
        logData.b = str + " : " + str2;
        data.add(logData);
        if (c != null) {
            c.notifyDataSetChanged();
        }
    }

    private static class LogData {

        /* renamed from: a  reason: collision with root package name */
        public String f21548a;
        public String b;

        private LogData() {
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.miui_geo_log);
        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MIUIGeoLogActivity.data.clear();
                MIUIGeoLogActivity.c.notifyDataSetChanged();
            }
        });
        this.b = (RecyclerView) findViewById(R.id.list);
        this.b.setLayoutManager(new LinearLayoutManager(this));
        c = new RecyclerView.Adapter() {
            @NonNull
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                TextView textView = new TextView(MIUIGeoLogActivity.this);
                textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                return new Holder(textView);
            }

            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                if (viewHolder.itemView instanceof TextView) {
                    SpannableString spannableString = new SpannableString(MIUIGeoLogActivity.data.get(i).f21548a + HelpFormatter.f + MIUIGeoLogActivity.data.get(i).b);
                    int length = MIUIGeoLogActivity.data.get(i).f21548a.length();
                    Matcher matcher = Pattern.compile("add|update|recover|first|processGeoFenceIntent|scene/start success|exception|clearData").matcher(spannableString);
                    while (matcher.find()) {
                        spannableString.setSpan(new ForegroundColorSpan(-65536), matcher.start(), matcher.end(), 33);
                    }
                    spannableString.setSpan(new ForegroundColorSpan(Color.d), 0, length, 33);
                    ((TextView) viewHolder.itemView).setText(spannableString);
                }
            }

            public int getItemCount() {
                return MIUIGeoLogActivity.data.size();
            }
        };
        this.b.setAdapter(c);
    }

    private static class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View view) {
            super(view);
        }
    }
}
