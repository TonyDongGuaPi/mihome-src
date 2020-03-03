package com.xiaomi.smarthome.camera.v4.activity.alarm;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.Utils.FileUtil;
import com.mijia.model.alarm.AlarmItem;
import com.mijia.player.FileNamer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class AlarmAdapterV2 extends BaseAdapter {
    private Context mContext;
    private String mDid;
    HashSet<String> mDownList = new HashSet<>();
    private List<AlarmItem> mList = new ArrayList();
    private DisplayImageOptions mOptions;
    private SharePrefHelper mSP;
    private String mServer;
    private SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm");

    public long getItemId(int i) {
        return (long) i;
    }

    public void destroyDisplayImageOptions() {
        this.mOptions = null;
    }

    private void initImageDisplayOptions(Context context) {
        this.mOptions = new DisplayImageOptions.Builder().a(context.getResources().getDrawable(R.drawable.camera_v4_remind_load_fail)).b(context.getResources().getDrawable(R.drawable.camera_v4_remind_load_fail)).c(context.getResources().getDrawable(R.drawable.camera_v4_remind_load_fail)).b(true).d(true).e(true).a(Bitmap.Config.RGB_565).a(ImageScaleType.EXACTLY_STRETCHED).d();
    }

    public AlarmAdapterV2(Context context, String str) {
        initImageDisplayOptions(context);
        this.mContext = context;
        this.mDid = str;
        this.mServer = XmPluginHostApi.instance().getGlobalSettingServer();
        this.mSP = new SharePrefHelper(this.mContext, "housekeping", this.mDid);
    }

    public void setData(List<AlarmItem> list, String str) {
        String[] list2;
        for (AlarmItem next : list) {
            next.k = this.mTimeFormat.format(new Date(next.b));
        }
        this.mList = list;
        String c = FileUtil.c(str);
        this.mDownList.clear();
        if (!TextUtils.isEmpty(c)) {
            File file = new File(c);
            if (file.exists() && (list2 = file.list()) != null) {
                this.mDownList.addAll(Arrays.asList(list2));
            }
        }
        notifyDataSetChanged();
    }

    public void addDownSuccess(AlarmItem alarmItem) {
        this.mDownList.add(FileNamer.a().a(alarmItem.b, true) + ".mp4");
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mList.size();
    }

    public AlarmItem getItem(int i) {
        return this.mList.get(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x0255  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x025c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
            r6 = this;
            if (r8 != 0) goto L_0x006d
            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapterV2$ViewHolder r8 = new com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapterV2$ViewHolder
            r8.<init>()
            android.content.Context r9 = r6.mContext
            android.view.LayoutInflater r9 = android.view.LayoutInflater.from(r9)
            r0 = 2130904180(0x7f030474, float:1.7415199E38)
            r1 = 0
            android.view.View r9 = r9.inflate(r0, r1)
            r0 = 2131430813(0x7f0b0d9d, float:1.8483338E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            r8.mTypeImage = r0
            r0 = 2131430812(0x7f0b0d9c, float:1.8483336E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r8.mTime = r0
            r0 = 2131430811(0x7f0b0d9b, float:1.8483334E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r8.mSubTitle = r0
            r0 = 2131430810(0x7f0b0d9a, float:1.8483331E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            r8.mPeople = r0
            r0 = 2131430814(0x7f0b0d9e, float:1.848334E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            r8.mImage = r0
            r0 = 2131431219(0x7f0b0f33, float:1.8484161E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            r8.mNormalItemLayout = r0
            r0 = 2131427890(0x7f0b0232, float:1.847741E38)
            android.view.View r0 = r9.findViewById(r0)
            r8.mBottomLine = r0
            r0 = 2131433010(0x7f0b1632, float:1.8487794E38)
            android.view.View r0 = r9.findViewById(r0)
            r8.mTopLine = r0
            r9.setTag(r8)
            goto L_0x0076
        L_0x006d:
            java.lang.Object r9 = r8.getTag()
            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapterV2$ViewHolder r9 = (com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapterV2.ViewHolder) r9
            r5 = r9
            r9 = r8
            r8 = r5
        L_0x0076:
            com.mijia.model.alarm.AlarmItem r0 = r6.getItem((int) r7)
            android.widget.TextView r1 = r8.mTime
            java.util.List<com.mijia.model.alarm.AlarmItem> r2 = r6.mList
            java.lang.Object r2 = r2.get(r7)
            com.mijia.model.alarm.AlarmItem r2 = (com.mijia.model.alarm.AlarmItem) r2
            java.lang.String r2 = r2.k
            r1.setText(r2)
            boolean r1 = r0.l
            if (r1 == 0) goto L_0x0090
            java.lang.String r1 = r0.r
            goto L_0x0094
        L_0x0090:
            java.lang.String r1 = r0.a()
        L_0x0094:
            com.nostra13.universalimageloader.core.ImageLoader r2 = com.nostra13.universalimageloader.core.ImageLoader.a()
            android.widget.ImageView r3 = r8.mImage
            r2.b((android.widget.ImageView) r3)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L_0x00b8
            android.widget.ImageView r1 = r8.mImage
            android.content.Context r2 = r9.getContext()
            android.content.res.Resources r2 = r2.getResources()
            r3 = 2130843133(0x7f0215fd, float:1.729138E38)
            android.graphics.drawable.Drawable r2 = r2.getDrawable(r3)
            r1.setImageDrawable(r2)
            goto L_0x00c3
        L_0x00b8:
            com.nostra13.universalimageloader.core.ImageLoader r2 = com.nostra13.universalimageloader.core.ImageLoader.a()
            android.widget.ImageView r3 = r8.mImage
            com.nostra13.universalimageloader.core.DisplayImageOptions r4 = r6.mOptions
            r2.a((java.lang.String) r1, (android.widget.ImageView) r3, (com.nostra13.universalimageloader.core.DisplayImageOptions) r4)
        L_0x00c3:
            boolean r1 = r0.l
            r2 = 0
            if (r1 == 0) goto L_0x00fa
            if (r0 == 0) goto L_0x00ea
            java.lang.String r1 = r0.v
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x00ea
            java.lang.String r0 = r0.v
            java.lang.String r1 = "people"
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x00ea
            android.widget.ImageView r0 = r8.mPeople
            r0.setVisibility(r2)
            android.widget.TextView r0 = r8.mSubTitle
            r1 = 2131493246(0x7f0c017e, float:1.8609967E38)
            r0.setText(r1)
            goto L_0x0112
        L_0x00ea:
            android.widget.ImageView r0 = r8.mPeople
            r1 = 8
            r0.setVisibility(r1)
            android.widget.TextView r0 = r8.mSubTitle
            r1 = 2131493247(0x7f0c017f, float:1.8609969E38)
            r0.setText(r1)
            goto L_0x0112
        L_0x00fa:
            java.lang.String r0 = r6.mDid
            com.mijia.camera.MijiaCameraDevice r0 = com.mijia.camera.MijiaCameraDevice.a((java.lang.String) r0)
            java.util.List<com.mijia.model.alarm.AlarmItem> r1 = r6.mList
            java.lang.Object r1 = r1.get(r7)
            com.mijia.model.alarm.AlarmItem r1 = (com.mijia.model.alarm.AlarmItem) r1
            java.lang.String r1 = r1.f
            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapterV2$1 r3 = new com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapterV2$1
            r3.<init>(r8)
            r0.a((java.lang.String) r1, (com.xiaomi.smarthome.device.api.Callback<java.lang.Boolean>) r3)
        L_0x0112:
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r6.mSP
            java.lang.String r1 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r1)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x01cb
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r6.mSP
            java.lang.String r1 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = ""
            r1.append(r3)
            java.util.List<com.mijia.model.alarm.AlarmItem> r3 = r6.mList
            java.lang.Object r3 = r3.get(r7)
            com.mijia.model.alarm.AlarmItem r3 = (com.mijia.model.alarm.AlarmItem) r3
            long r3 = r3.b
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            int r0 = r0.indexOf(r1)
            if (r0 <= 0) goto L_0x01cb
            android.widget.ImageView r0 = r8.mTypeImage
            r1 = 2130843199(0x7f02163f, float:1.7291515E38)
            r0.setImageResource(r1)
            if (r7 != 0) goto L_0x0159
            android.view.View r0 = r8.mTopLine
            r0.setBackgroundColor(r2)
            goto L_0x01b3
        L_0x0159:
            java.util.List<com.mijia.model.alarm.AlarmItem> r0 = r6.mList
            int r0 = r0.size()
            int r0 = r0 + -1
            if (r7 == r0) goto L_0x01a8
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r6.mSP
            java.lang.String r1 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r1)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x01a8
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r6.mSP
            java.lang.String r1 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = ""
            r1.append(r3)
            java.util.List<com.mijia.model.alarm.AlarmItem> r3 = r6.mList
            int r4 = r7 + -1
            java.lang.Object r3 = r3.get(r4)
            com.mijia.model.alarm.AlarmItem r3 = (com.mijia.model.alarm.AlarmItem) r3
            long r3 = r3.b
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            int r0 = r0.indexOf(r1)
            if (r0 <= 0) goto L_0x01a8
            android.view.View r0 = r8.mTopLine
            java.lang.String r1 = "#dfdfdf"
            int r1 = android.graphics.Color.parseColor(r1)
            r0.setBackgroundColor(r1)
            goto L_0x01b3
        L_0x01a8:
            android.view.View r0 = r8.mTopLine
            java.lang.String r1 = "#ff692e"
            int r1 = android.graphics.Color.parseColor(r1)
            r0.setBackgroundColor(r1)
        L_0x01b3:
            android.widget.TextView r0 = r8.mTime
            java.lang.String r1 = "#8d8d8d"
            int r1 = android.graphics.Color.parseColor(r1)
            r0.setTextColor(r1)
            android.view.View r0 = r8.mBottomLine
            java.lang.String r1 = "#dfdfdf"
            int r1 = android.graphics.Color.parseColor(r1)
            r0.setBackgroundColor(r1)
            goto L_0x024b
        L_0x01cb:
            android.widget.ImageView r0 = r8.mTypeImage
            r1 = 2130843198(0x7f02163e, float:1.7291513E38)
            r0.setImageResource(r1)
            if (r7 != 0) goto L_0x01db
            android.view.View r0 = r8.mTopLine
            r0.setBackgroundColor(r2)
            goto L_0x0235
        L_0x01db:
            java.util.List<com.mijia.model.alarm.AlarmItem> r0 = r6.mList
            int r0 = r0.size()
            int r0 = r0 + -1
            if (r7 == r0) goto L_0x022a
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r6.mSP
            java.lang.String r1 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r1)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x022a
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r6.mSP
            java.lang.String r1 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = ""
            r1.append(r3)
            java.util.List<com.mijia.model.alarm.AlarmItem> r3 = r6.mList
            int r4 = r7 + -1
            java.lang.Object r3 = r3.get(r4)
            com.mijia.model.alarm.AlarmItem r3 = (com.mijia.model.alarm.AlarmItem) r3
            long r3 = r3.b
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            int r0 = r0.indexOf(r1)
            if (r0 <= 0) goto L_0x022a
            android.view.View r0 = r8.mTopLine
            java.lang.String r1 = "#dfdfdf"
            int r1 = android.graphics.Color.parseColor(r1)
            r0.setBackgroundColor(r1)
            goto L_0x0235
        L_0x022a:
            android.view.View r0 = r8.mTopLine
            java.lang.String r1 = "#ff692e"
            int r1 = android.graphics.Color.parseColor(r1)
            r0.setBackgroundColor(r1)
        L_0x0235:
            android.widget.TextView r0 = r8.mTime
            java.lang.String r1 = "#ff692e"
            int r1 = android.graphics.Color.parseColor(r1)
            r0.setTextColor(r1)
            android.view.View r0 = r8.mBottomLine
            java.lang.String r1 = "#ff692e"
            int r1 = android.graphics.Color.parseColor(r1)
            r0.setBackgroundColor(r1)
        L_0x024b:
            java.util.List<com.mijia.model.alarm.AlarmItem> r0 = r6.mList
            int r0 = r0.size()
            int r0 = r0 + -1
            if (r7 != r0) goto L_0x025c
            android.view.View r7 = r8.mBottomLine
            r8 = 4
            r7.setVisibility(r8)
            goto L_0x0261
        L_0x025c:
            android.view.View r7 = r8.mBottomLine
            r7.setVisibility(r2)
        L_0x0261:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapterV2.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    class ViewHolder {
        public View mBottomLine;
        public ImageView mImage;
        public LinearLayout mNormalItemLayout;
        public ImageView mPeople;
        public TextView mSubTitle;
        public TextView mTime;
        public View mTopLine;
        public ImageView mTypeImage;

        ViewHolder() {
        }
    }
}
