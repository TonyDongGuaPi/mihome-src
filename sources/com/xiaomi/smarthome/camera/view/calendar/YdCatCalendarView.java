package com.xiaomi.smarthome.camera.view.calendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mijia.debug.SDKLog;
import com.miui.tsmclient.util.StringUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.view.calendar.CEn7DayRecyclerAdapterNew;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoListActivity;
import java.util.ArrayList;
import java.util.List;

public class YdCatCalendarView extends LinearLayout {
    public static final int DEFAULT_SHOW_DAYS = 30;
    public static final long ONE_DAY_TIME = 86400000;
    public static final String TAG = "YdCatCalendarView";
    private static final String[] arrMonth = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
    View btn_calendar_left_page;
    View btn_calendar_right_page;
    RecyclerView customRecycler_calendar;
    ImageView iv_arrow_left;
    ImageView iv_arrow_right;
    boolean leftCanClick = true;
    List<CalendarDate> mCalendarDay = new ArrayList();
    CEn7DayRecyclerAdapterNew mCalendarDayAdapter;
    YdCatCalendarListener mCalendarListener;
    private long mCurrentTimeMillis = System.currentTimeMillis();
    private int mShowDaysAfterNow = 30;
    private int mShowDaysBeforeNow = 30;
    boolean rightCanClick = false;
    TextView tv_calendar;

    public interface YdCatCalendarListener {
        void clickOnDate(CalendarDate calendarDate, View view);

        void clickOnLeftPage();

        void clickOnRightPage();
    }

    public YdCatCalendarView(Context context) {
        super(context);
        initView(context);
    }

    public YdCatCalendarView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public YdCatCalendarView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(1);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_cen_calendar_view_new, (ViewGroup) null);
        addView(inflate);
        this.tv_calendar = (TextView) inflate.findViewById(R.id.tv_calendar);
        this.btn_calendar_left_page = inflate.findViewById(R.id.btn_calendar_left_page);
        this.btn_calendar_right_page = inflate.findViewById(R.id.btn_calendar_right_page);
        this.iv_arrow_right = (ImageView) inflate.findViewById(R.id.iv_arrow_right);
        this.iv_arrow_left = (ImageView) inflate.findViewById(R.id.iv_arrow_left);
        this.btn_calendar_left_page.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (YdCatCalendarView.this.leftCanClick) {
                    CalendarDate calendarDate = DateUtils.getCalendarDate(YdCatCalendarView.this.mCalendarDay.get(YdCatCalendarView.this.mCalendarDay.size() / 2).time - CloudVideoListActivity.THIRTY_DAYS_MILLIS);
                    YdCatCalendarView.this.mCalendarDay.clear();
                    YdCatCalendarView.this.initCalender(calendarDate);
                    YdCatCalendarView.this.mCalendarDayAdapter.resetWithNotifyData(calendarDate.year, calendarDate.month);
                    String str = YdCatCalendarView.TAG;
                    SDKLog.b(str, "left click:" + calendarDate.year + "/" + calendarDate.month + "/" + calendarDate.day);
                    YdCatCalendarView.this.checkCalendarLimit(calendarDate);
                    if (YdCatCalendarView.this.mCalendarListener != null) {
                        YdCatCalendarView.this.mCalendarListener.clickOnLeftPage();
                    }
                }
            }
        });
        this.btn_calendar_right_page.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (YdCatCalendarView.this.rightCanClick) {
                    CalendarDate calendarDate = DateUtils.getCalendarDate(YdCatCalendarView.this.mCalendarDay.get(YdCatCalendarView.this.mCalendarDay.size() / 2).time + CloudVideoListActivity.THIRTY_DAYS_MILLIS);
                    YdCatCalendarView.this.mCalendarDay.clear();
                    YdCatCalendarView.this.initCalender(calendarDate);
                    YdCatCalendarView.this.mCalendarDayAdapter.resetWithNotifyData(calendarDate.year, calendarDate.month);
                    String str = YdCatCalendarView.TAG;
                    SDKLog.b(str, "right click:" + calendarDate.year + "/" + calendarDate.month + "/" + calendarDate.day);
                    YdCatCalendarView.this.checkCalendarLimit(calendarDate);
                    if (YdCatCalendarView.this.mCalendarListener != null) {
                        YdCatCalendarView.this.mCalendarListener.clickOnRightPage();
                    }
                }
            }
        });
        this.customRecycler_calendar = (RecyclerView) inflate.findViewById(R.id.customRecycler_calendar);
    }

    private class MyGridLayoutManager extends GridLayoutManager {
        public MyGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
            super(context, attributeSet, i, i2);
        }

        public MyGridLayoutManager(Context context, int i) {
            super(context, i);
        }

        public MyGridLayoutManager(Context context, int i, int i2, boolean z) {
            super(context, i, i2, z);
        }

        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (Exception unused) {
            }
        }
    }

    public void initRecycler(Context context) {
        this.mCalendarDay.clear();
        CalendarDate calendarDate = DateUtils.getCalendarDate(this.mCurrentTimeMillis);
        initCalender(calendarDate);
        this.customRecycler_calendar.setLayoutManager(new MyGridLayoutManager(context, 7));
        this.mCalendarDayAdapter = new CEn7DayRecyclerAdapterNew(context, this.mCalendarDay, calendarDate);
        this.customRecycler_calendar.setAdapter(this.mCalendarDayAdapter);
        this.mCalendarDayAdapter.chooseDate(calendarDate);
        this.mCalendarDayAdapter.setListener(new CEn7DayRecyclerAdapterNew.CEn7DayListener() {
            public void onClickItem(int i, View view) {
                if (YdCatCalendarView.this.mCalendarListener != null) {
                    YdCatCalendarView.this.mCalendarListener.clickOnDate(YdCatCalendarView.this.mCalendarDay.get(i), view);
                }
            }
        });
        checkCalendarLimit(calendarDate);
    }

    /* access modifiers changed from: private */
    public void initCalender(CalendarDate calendarDate) {
        StringBuilder sb;
        String str;
        int i;
        int parseInt = Integer.parseInt(calendarDate.month) + 1;
        TextView textView = this.tv_calendar;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(calendarDate.year);
        sb2.append("-");
        if (parseInt <= 9) {
            sb = new StringBuilder();
            str = "0";
        } else {
            sb = new StringBuilder();
            str = "";
        }
        sb.append(str);
        sb.append(parseInt);
        sb2.append(sb.toString());
        textView.setText(sb2.toString());
        CalendarDate calendarDate2 = DateUtils.getCalendarDate(calendarDate.time - (CEn7DayRecyclerAdapterNew.CLOUD_DAYS * 86400000));
        int parseInt2 = Integer.parseInt(calendarDate2.way);
        String str2 = TAG;
        SDKLog.b(str2, "firstDay=" + calendarDate2.month + "-" + calendarDate2.day);
        String str3 = TAG;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("plus=");
        sb3.append(parseInt2);
        SDKLog.b(str3, sb3.toString());
        this.mShowDaysBeforeNow = (int) (((long) parseInt2) + CEn7DayRecyclerAdapterNew.CLOUD_DAYS);
        String str4 = TAG;
        SDKLog.b(str4, "mShowDaysBeforeNow=" + this.mShowDaysBeforeNow);
        long j = 0;
        while (true) {
            i = 0;
            if (j >= ((long) this.mShowDaysBeforeNow)) {
                break;
            }
            CalendarDate calendarDate3 = DateUtils.getCalendarDate(calendarDate.time - (j * 86400000));
            String str5 = TAG;
            SDKLog.b(str5, calendarDate3.month + "-" + calendarDate3.day);
            this.mCalendarDay.add(0, calendarDate3);
            j++;
        }
        CalendarDate calendarDate4 = this.mCalendarDay.get(this.mCalendarDay.size() - 1);
        int parseInt3 = 7 - Integer.parseInt(calendarDate4.way);
        while (i < parseInt3) {
            i++;
            this.mCalendarDay.add(DateUtils.getCalendarDate(calendarDate4.time + (((long) i) * 86400000)));
        }
    }

    public void setChooseDate(long j) {
        this.mCalendarDayAdapter.chooseDate(DateUtils.getCalendarDate(j));
    }

    public CalendarDate getChoosedDate() {
        return this.mCalendarDayAdapter.getChooseDate();
    }

    public void setCalendarListener(YdCatCalendarListener ydCatCalendarListener) {
        this.mCalendarListener = ydCatCalendarListener;
    }

    public void addHaveVideoDay(CalendarDate calendarDate) {
        this.mCalendarDayAdapter.addHaveRecordDayIntoAll(calendarDate);
    }

    public void addHaveWarningDay(CalendarDate calendarDate) {
        this.mCalendarDayAdapter.addHaveEventDayIntoAll(calendarDate);
    }

    public void refreshFlag() {
        this.mCalendarDayAdapter.resetRecordAndEventFlag();
        CalendarDate calendarDate = this.mCalendarDay.get(this.mCalendarDay.size() / 2);
        String str = TAG;
        SDKLog.b(str, "refreshFlag:" + calendarDate.year + "/" + calendarDate.month + "/" + calendarDate.day);
        checkCalendarLimit(calendarDate);
    }

    public List<CalendarDate> getCalendarDays() {
        return this.mCalendarDay;
    }

    public int getCalendarDayIndex(CalendarDate calendarDate) {
        int size = this.mCalendarDay.size();
        for (int i = 0; i < size; i++) {
            if (this.mCalendarDay.get(i).getDateFormat(StringUtils.SOURCE_DATE_FORMAT).equals(calendarDate.getDateFormat(StringUtils.SOURCE_DATE_FORMAT))) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0115  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkCalendarLimit(com.xiaomi.smarthome.camera.view.calendar.CalendarDate r7) {
        /*
            r6 = this;
            long r0 = r6.mCurrentTimeMillis
            com.xiaomi.smarthome.camera.view.calendar.CalendarDate r0 = com.xiaomi.smarthome.camera.view.calendar.DateUtils.getCalendarDate(r0)
            java.lang.String r1 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "date:"
            r2.append(r3)
            java.lang.String r3 = r7.year
            r2.append(r3)
            java.lang.String r3 = "/"
            r2.append(r3)
            java.lang.String r3 = r7.month
            r2.append(r3)
            java.lang.String r3 = "/"
            r2.append(r3)
            java.lang.String r3 = r7.day
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.mijia.debug.SDKLog.b(r1, r2)
            java.lang.String r1 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "current:"
            r2.append(r3)
            java.lang.String r3 = r0.year
            r2.append(r3)
            java.lang.String r3 = "/"
            r2.append(r3)
            java.lang.String r3 = r0.month
            r2.append(r3)
            java.lang.String r3 = "/"
            r2.append(r3)
            java.lang.String r3 = r0.day
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.mijia.debug.SDKLog.b(r1, r2)
            java.lang.String r1 = r7.year
            java.lang.String r2 = r0.year
            boolean r1 = r1.equals(r2)
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0084
            java.lang.String r1 = r7.month
            java.lang.String r0 = r0.month
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0084
            android.widget.ImageView r0 = r6.iv_arrow_right
            r1 = 2130840199(0x7f020a87, float:1.728543E38)
            r0.setBackgroundResource(r1)
            r6.rightCanClick = r2
            android.view.View r0 = r6.btn_calendar_right_page
            r0.setClickable(r2)
            goto L_0x0093
        L_0x0084:
            android.widget.ImageView r0 = r6.iv_arrow_right
            r1 = 2130840198(0x7f020a86, float:1.7285428E38)
            r0.setBackgroundResource(r1)
            r6.rightCanClick = r3
            android.view.View r0 = r6.btn_calendar_right_page
            r0.setClickable(r3)
        L_0x0093:
            com.xiaomi.smarthome.camera.view.calendar.CEn7DayRecyclerAdapterNew r0 = r6.mCalendarDayAdapter
            java.util.List r0 = r0.getmHaveEventDayAll()
            com.xiaomi.smarthome.camera.view.calendar.CEn7DayRecyclerAdapterNew r1 = r6.mCalendarDayAdapter
            java.util.List r1 = r1.getmHaveRecordDayAll()
            if (r0 == 0) goto L_0x00d1
            int r4 = r0.size()
            if (r4 == 0) goto L_0x00d1
            int r4 = r0.size()
            int r4 = r4 - r3
            java.lang.Object r0 = r0.get(r4)
            com.xiaomi.smarthome.camera.view.calendar.CalendarDate r0 = (com.xiaomi.smarthome.camera.view.calendar.CalendarDate) r0
            java.lang.String r4 = r7.year
            int r4 = java.lang.Integer.parseInt(r4)
            java.lang.String r5 = r0.year
            int r5 = java.lang.Integer.parseInt(r5)
            if (r4 > r5) goto L_0x00cf
            java.lang.String r4 = r7.month
            int r4 = java.lang.Integer.parseInt(r4)
            java.lang.String r0 = r0.month
            int r0 = java.lang.Integer.parseInt(r0)
            if (r4 > r0) goto L_0x00cf
            goto L_0x00d1
        L_0x00cf:
            r0 = 0
            goto L_0x00d2
        L_0x00d1:
            r0 = 1
        L_0x00d2:
            if (r1 == 0) goto L_0x0103
            int r4 = r1.size()
            if (r4 == 0) goto L_0x0103
            int r4 = r1.size()
            int r4 = r4 - r3
            java.lang.Object r1 = r1.get(r4)
            com.xiaomi.smarthome.camera.view.calendar.CalendarDate r1 = (com.xiaomi.smarthome.camera.view.calendar.CalendarDate) r1
            java.lang.String r4 = r7.year
            int r4 = java.lang.Integer.parseInt(r4)
            java.lang.String r5 = r1.year
            int r5 = java.lang.Integer.parseInt(r5)
            if (r4 > r5) goto L_0x0102
            java.lang.String r7 = r7.month
            int r7 = java.lang.Integer.parseInt(r7)
            java.lang.String r1 = r1.month
            int r1 = java.lang.Integer.parseInt(r1)
            if (r7 > r1) goto L_0x0102
            goto L_0x0103
        L_0x0102:
            r0 = 0
        L_0x0103:
            if (r0 == 0) goto L_0x0115
            android.widget.ImageView r7 = r6.iv_arrow_left
            r0 = 2130840197(0x7f020a85, float:1.7285426E38)
            r7.setBackgroundResource(r0)
            r6.leftCanClick = r2
            android.view.View r7 = r6.btn_calendar_left_page
            r7.setClickable(r2)
            goto L_0x0124
        L_0x0115:
            android.widget.ImageView r7 = r6.iv_arrow_left
            r0 = 2130840196(0x7f020a84, float:1.7285424E38)
            r7.setBackgroundResource(r0)
            r6.leftCanClick = r3
            android.view.View r7 = r6.btn_calendar_left_page
            r7.setClickable(r3)
        L_0x0124:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.view.calendar.YdCatCalendarView.checkCalendarLimit(com.xiaomi.smarthome.camera.view.calendar.CalendarDate):void");
    }
}
