package com.xiaomi.smarthome.miio.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.FontManager;
import java.util.ArrayList;

public class WifiLogStatActivity extends BaseActivity implements View.OnClickListener {
    public static int Text_Normal_Color = -8354926;
    public static int Text_Select_Color = -10779402;

    /* renamed from: a  reason: collision with root package name */
    private ArrayList<Long> f11828a = new ArrayList<>();
    /* access modifiers changed from: private */
    public LinearLayout b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private View h;
    private LogStatMode i;
    private int j;
    private View k = null;
    private int l = -1;
    private Typeface m;
    /* access modifiers changed from: private */
    public WifiLogHorizontalScrollView n;

    enum LogStatMode {
        AtHome,
        OutHome,
        AtOffice,
        OutOffice
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wifi_log_stat);
        initLog();
        initViews();
    }

    /* access modifiers changed from: protected */
    public int initLog() {
        Intent intent = getIntent();
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("list");
        for (int i2 = 0; i2 < stringArrayListExtra.size(); i2++) {
            this.f11828a.add(Long.valueOf(stringArrayListExtra.get(i2)));
        }
        this.j = intent.getIntExtra("current", stringArrayListExtra.size() - 1);
        switch (intent.getIntExtra("type", 0)) {
            case 0:
                this.i = LogStatMode.AtHome;
                break;
            case 1:
                this.i = LogStatMode.AtOffice;
                break;
            case 2:
                this.i = LogStatMode.OutHome;
                break;
            case 3:
                this.i = LogStatMode.OutOffice;
                break;
            default:
                this.i = LogStatMode.AtHome;
                break;
        }
        return this.f11828a.size();
    }

    /* access modifiers changed from: protected */
    public void initViews() {
        this.b = (LinearLayout) findViewById(R.id.list_items);
        this.c = (TextView) findViewById(R.id.title);
        this.d = (TextView) findViewById(R.id.title_desc);
        this.e = (TextView) findViewById(R.id.time_period);
        this.f = (TextView) findViewById(R.id.item_time_desc1);
        this.g = (TextView) findViewById(R.id.item_time_desc2);
        this.h = findViewById(R.id.split_line);
        this.m = FontManager.a("fonts/D-DIN.otf");
        this.f.setTypeface(this.m);
        this.g.setTypeface(this.m);
        this.n = (WifiLogHorizontalScrollView) findViewById(R.id.scroll_view);
        float f2 = getBaseContext().getResources().getDisplayMetrics().density;
        WifiLogHorizontalScrollView wifiLogHorizontalScrollView = this.n;
        double d2 = (double) (f2 * 46.0f);
        Double.isNaN(d2);
        wifiLogHorizontalScrollView.setItemWidth((int) (d2 + 0.5d));
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiLogStatActivity.this.finish();
            }
        });
        findViewById(R.id.prev_week).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View childAt;
                int itemIndex = WifiLogStatActivity.this.n.getItemIndex() - 7;
                if (itemIndex < 0) {
                    itemIndex = 0;
                }
                if (WifiLogStatActivity.this.n.scrollItemCount(-7) && (childAt = WifiLogStatActivity.this.b.getChildAt(itemIndex)) != null) {
                    WifiLogStatActivity.this.updateDayInfo((WifiLogStatItemView) childAt.findViewById(R.id.item_name));
                }
            }
        });
        findViewById(R.id.next_week).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View childAt;
                int itemIndex = WifiLogStatActivity.this.n.getItemIndex() + 7;
                if (itemIndex < 0) {
                    itemIndex = 0;
                }
                if (WifiLogStatActivity.this.n.scrollItemCount(7) && (childAt = WifiLogStatActivity.this.b.getChildAt(itemIndex)) != null) {
                    WifiLogStatActivity.this.updateDayInfo((WifiLogStatItemView) childAt.findViewById(R.id.item_name));
                }
            }
        });
        switch (this.i) {
            case AtHome:
                this.c.setText(R.string.wifi_log_stat_at_home_title);
                break;
            case OutHome:
                this.c.setText(R.string.wifi_log_stat_out_home_title);
                break;
            case AtOffice:
                this.c.setText(R.string.wifi_log_stat_at_office_title);
                break;
            case OutOffice:
                this.c.setText(R.string.wifi_log_stat_out_office_title);
                break;
        }
        refreshView();
        if (this.k != null) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i3 < this.b.getChildCount()) {
                    if (this.k == this.b.getChildAt(i3)) {
                        i2 = i3;
                    } else {
                        i3++;
                    }
                }
            }
            this.n.scrollItemCount(i2);
        }
    }

    /* access modifiers changed from: protected */
    public View createItemView(Time time) {
        View inflate = getLayoutInflater().inflate(R.layout.wifi_log_stat_item, (ViewGroup) null);
        inflate.setOnClickListener(this);
        ((WifiLogStatItemView) inflate.findViewById(R.id.item_name)).setItemTime(time);
        TextView textView = (TextView) inflate.findViewById(R.id.item_text);
        if (time.weekDay == 1) {
            textView.setText(R.string.wifi_log_monday);
        } else {
            textView.setText(String.valueOf(time.month + 1) + "." + String.valueOf(time.monthDay));
        }
        this.b.addView(inflate);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void refreshView() {
        if (this.f11828a.size() != 0) {
            this.b.removeAllViews();
            new Time().set(86400);
            Time time = new Time();
            time.set(this.f11828a.get(0).longValue());
            int i2 = time.yearDay;
            View createItemView = createItemView(time);
            for (int i3 = 1; i3 < this.f11828a.size(); i3++) {
                Time time2 = new Time();
                time2.set(this.f11828a.get(i3).longValue());
                int i4 = time2.yearDay;
                if (i2 == i4) {
                    if (this.j == i3) {
                        this.k = createItemView;
                    }
                    ((WifiLogStatItemView) createItemView.findViewById(R.id.item_name)).setItemTime(time2);
                } else {
                    if (createItemView != null) {
                        ((WifiLogStatItemView) createItemView.findViewById(R.id.item_name)).setTimeEnd();
                    }
                    int i5 = i2;
                    int i6 = 0;
                    while (true) {
                        i6++;
                        i5++;
                        if (i5 >= 365) {
                            i5 -= 365;
                        }
                        if (i5 == i4) {
                            break;
                        }
                        Time time3 = new Time();
                        time3.set(this.f11828a.get(i3 - 1).longValue() + ((long) (86400000 * i6)));
                        time3.hour = 0;
                        time3.minute = 0;
                        time3.second = 0;
                        createItemView(time3);
                    }
                    View createItemView2 = createItemView(time2);
                    if (this.j == i3) {
                        this.k = createItemView2;
                    }
                    int i7 = i5;
                    createItemView = createItemView2;
                    i2 = i7;
                }
            }
            if (createItemView != null) {
                WifiLogStatItemView wifiLogStatItemView = (WifiLogStatItemView) createItemView.findViewById(R.id.item_name);
                wifiLogStatItemView.setTimeEnd();
                int i8 = wifiLogStatItemView.getItemTime(true).weekDay;
                long longValue = this.f11828a.get(this.f11828a.size() - 1).longValue();
                while (i8 < 6) {
                    longValue += 86400000;
                    Time time4 = new Time();
                    time4.set(longValue);
                    time4.hour = 0;
                    time4.minute = 0;
                    time4.second = 0;
                    int i9 = time4.weekDay;
                    createItemView(time4);
                    i8 = i9;
                }
            }
            this.n.setTotalCount(this.b.getChildCount());
            updateCurrentItemInfo(this.k);
        }
    }

    public void onClick(View view) {
        if (this.k != view) {
            if (this.k != null) {
                ((WifiLogStatItemView) this.k.findViewById(R.id.item_name)).setFocusPoint(false);
                ((TextView) this.k.findViewById(R.id.item_text)).setTextColor(Text_Normal_Color);
                this.k = null;
            }
            updateCurrentItemInfo(view);
            this.k = view;
        }
    }

    public void updateCurrentItemInfo(View view) {
        if (view != null) {
            WifiLogStatItemView wifiLogStatItemView = (WifiLogStatItemView) view.findViewById(R.id.item_name);
            wifiLogStatItemView.setFocusPoint(true);
            ((TextView) view.findViewById(R.id.item_text)).setTextColor(Text_Select_Color);
            Time itemTime = wifiLogStatItemView.getItemTime(false);
            Time itemTime2 = wifiLogStatItemView.getItemTime(true);
            this.f.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(itemTime.hour), Integer.valueOf(itemTime.minute)}));
            this.g.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(itemTime2.hour), Integer.valueOf(itemTime2.minute)}));
            if (itemTime == itemTime2) {
                if (itemTime.hour == 0 && itemTime.minute == 0) {
                    this.f.setText("");
                }
                this.h.setVisibility(8);
                this.g.setVisibility(8);
            } else {
                this.h.setVisibility(0);
                this.g.setVisibility(0);
            }
            updateDayInfo(wifiLogStatItemView);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateDayInfo(WifiLogStatItemView wifiLogStatItemView) {
        int i2 = 0;
        Time itemTime = wifiLogStatItemView.getItemTime(false);
        if (itemTime.getWeekNumber() != this.l) {
            ArrayList arrayList = new ArrayList();
            this.l = itemTime.getWeekNumber();
            int i3 = -1;
            for (int i4 = 0; i4 < this.f11828a.size(); i4++) {
                Time time = new Time();
                time.set(this.f11828a.get(i4).longValue());
                if (time.getWeekNumber() == this.l && !(this.i == LogStatMode.AtOffice && (time.weekDay == 0 || time.weekDay == 6))) {
                    int i5 = (time.hour * 60) + time.minute;
                    if (time.weekDay == i3) {
                        int intValue = ((Integer) arrayList.get(arrayList.size() - 1)).intValue();
                        if (this.i == LogStatMode.AtOffice || this.i == LogStatMode.OutHome) {
                            if (intValue > i5) {
                                arrayList.remove(arrayList.size() - 1);
                                arrayList.add(Integer.valueOf(i5));
                            }
                        } else if (intValue < i5) {
                            arrayList.remove(arrayList.size() - 1);
                            arrayList.add(Integer.valueOf(i5));
                        }
                    } else {
                        i3 = time.weekDay;
                        arrayList.add(Integer.valueOf(i5));
                    }
                }
            }
            if (arrayList.size() == 0) {
                this.d.setText("");
                return;
            }
            double d2 = 0.0d;
            int size = arrayList.size();
            double d3 = 0.0d;
            for (int i6 = 0; i6 < arrayList.size(); i6++) {
                if (((Integer) arrayList.get(i6)).intValue() == 0) {
                    size--;
                } else {
                    double intValue2 = (double) ((Integer) arrayList.get(i6)).intValue();
                    Double.isNaN(intValue2);
                    d3 += intValue2;
                }
            }
            if (size != 0) {
                double d4 = (double) size;
                Double.isNaN(d4);
                double d5 = d3 / d4;
                for (int i7 = 0; i7 < arrayList.size(); i7++) {
                    int intValue3 = ((Integer) arrayList.get(i7)).intValue();
                    if (intValue3 != 0) {
                        double d6 = (double) intValue3;
                        Double.isNaN(d6);
                        double d7 = d6 - d5;
                        d2 += d7 * d7;
                    }
                }
                Double.isNaN(d4);
                double sqrt = Math.sqrt(d2 / d4);
                if (sqrt >= 60.0d) {
                    if (sqrt <= 180.0d) {
                        switch (this.i) {
                            case AtHome:
                                i2 = R.string.log_week_athome_normal;
                                break;
                            case OutHome:
                                i2 = R.string.log_week_outhome_normal;
                                break;
                            case AtOffice:
                                i2 = R.string.log_week_atoffice_normal;
                                break;
                            case OutOffice:
                                i2 = R.string.log_week_outoffice_normal;
                                break;
                        }
                    } else {
                        switch (this.i) {
                            case AtHome:
                                i2 = R.string.log_week_athome_Random;
                                break;
                            case OutHome:
                                i2 = R.string.log_week_outhome_Random;
                                break;
                            case AtOffice:
                                i2 = R.string.log_week_atoffice_Random;
                                break;
                            case OutOffice:
                                i2 = R.string.log_week_outoffice_Random;
                                break;
                        }
                    }
                } else {
                    switch (this.i) {
                        case AtHome:
                            i2 = R.string.log_week_athome_stdest;
                            break;
                        case OutHome:
                            i2 = R.string.log_week_outhome_stdest;
                            break;
                        case AtOffice:
                            i2 = R.string.log_week_atoffice_stdest;
                            break;
                        case OutOffice:
                            i2 = R.string.log_week_outoffice_stdest;
                            break;
                    }
                }
                this.d.setText(getString(i2));
            }
        }
    }
}
