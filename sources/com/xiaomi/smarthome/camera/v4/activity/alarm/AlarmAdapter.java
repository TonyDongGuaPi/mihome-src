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

public class AlarmAdapter extends BaseAdapter {
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

    public AlarmAdapter(Context context, String str) {
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

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0218  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x021f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
            r6 = this;
            if (r8 != 0) goto L_0x006d
            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapter$ViewHolder r8 = new com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapter$ViewHolder
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
            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapter$ViewHolder r9 = (com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapter.ViewHolder) r9
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
            java.lang.String r0 = r0.a()
            com.nostra13.universalimageloader.core.ImageLoader r1 = com.nostra13.universalimageloader.core.ImageLoader.a()
            android.widget.ImageView r2 = r8.mImage
            r1.b((android.widget.ImageView) r2)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x00b1
            android.widget.ImageView r0 = r8.mImage
            android.content.Context r1 = r9.getContext()
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2130843133(0x7f0215fd, float:1.729138E38)
            android.graphics.drawable.Drawable r1 = r1.getDrawable(r2)
            r0.setImageDrawable(r1)
            goto L_0x00bc
        L_0x00b1:
            com.nostra13.universalimageloader.core.ImageLoader r1 = com.nostra13.universalimageloader.core.ImageLoader.a()
            android.widget.ImageView r2 = r8.mImage
            com.nostra13.universalimageloader.core.DisplayImageOptions r3 = r6.mOptions
            r1.a((java.lang.String) r0, (android.widget.ImageView) r2, (com.nostra13.universalimageloader.core.DisplayImageOptions) r3)
        L_0x00bc:
            java.lang.String r0 = r6.mDid
            com.mijia.camera.MijiaCameraDevice r0 = com.mijia.camera.MijiaCameraDevice.a((java.lang.String) r0)
            java.util.List<com.mijia.model.alarm.AlarmItem> r1 = r6.mList
            java.lang.Object r1 = r1.get(r7)
            com.mijia.model.alarm.AlarmItem r1 = (com.mijia.model.alarm.AlarmItem) r1
            java.lang.String r1 = r1.f
            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapter$1 r2 = new com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapter$1
            r2.<init>(r8)
            r0.a((java.lang.String) r1, (com.xiaomi.smarthome.device.api.Callback<java.lang.Boolean>) r2)
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r6.mSP
            java.lang.String r1 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r1)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            if (r0 != 0) goto L_0x018e
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r6.mSP
            java.lang.String r2 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            java.util.List<com.mijia.model.alarm.AlarmItem> r3 = r6.mList
            java.lang.Object r3 = r3.get(r7)
            com.mijia.model.alarm.AlarmItem r3 = (com.mijia.model.alarm.AlarmItem) r3
            long r3 = r3.b
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            int r0 = r0.indexOf(r2)
            if (r0 <= 0) goto L_0x018e
            android.widget.ImageView r0 = r8.mTypeImage
            r2 = 2130843199(0x7f02163f, float:1.7291515E38)
            r0.setImageResource(r2)
            if (r7 != 0) goto L_0x011c
            android.view.View r0 = r8.mTopLine
            r0.setBackgroundColor(r1)
            goto L_0x0176
        L_0x011c:
            java.util.List<com.mijia.model.alarm.AlarmItem> r0 = r6.mList
            int r0 = r0.size()
            int r0 = r0 + -1
            if (r7 == r0) goto L_0x016b
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r6.mSP
            java.lang.String r2 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r2)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x016b
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r6.mSP
            java.lang.String r2 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            java.util.List<com.mijia.model.alarm.AlarmItem> r3 = r6.mList
            int r4 = r7 + -1
            java.lang.Object r3 = r3.get(r4)
            com.mijia.model.alarm.AlarmItem r3 = (com.mijia.model.alarm.AlarmItem) r3
            long r3 = r3.b
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            int r0 = r0.indexOf(r2)
            if (r0 <= 0) goto L_0x016b
            android.view.View r0 = r8.mTopLine
            java.lang.String r2 = "#dfdfdf"
            int r2 = android.graphics.Color.parseColor(r2)
            r0.setBackgroundColor(r2)
            goto L_0x0176
        L_0x016b:
            android.view.View r0 = r8.mTopLine
            java.lang.String r2 = "#ff692e"
            int r2 = android.graphics.Color.parseColor(r2)
            r0.setBackgroundColor(r2)
        L_0x0176:
            android.widget.TextView r0 = r8.mTime
            java.lang.String r2 = "#8d8d8d"
            int r2 = android.graphics.Color.parseColor(r2)
            r0.setTextColor(r2)
            android.view.View r0 = r8.mBottomLine
            java.lang.String r2 = "#dfdfdf"
            int r2 = android.graphics.Color.parseColor(r2)
            r0.setBackgroundColor(r2)
            goto L_0x020e
        L_0x018e:
            android.widget.ImageView r0 = r8.mTypeImage
            r2 = 2130843198(0x7f02163e, float:1.7291513E38)
            r0.setImageResource(r2)
            if (r7 != 0) goto L_0x019e
            android.view.View r0 = r8.mTopLine
            r0.setBackgroundColor(r1)
            goto L_0x01f8
        L_0x019e:
            java.util.List<com.mijia.model.alarm.AlarmItem> r0 = r6.mList
            int r0 = r0.size()
            int r0 = r0 + -1
            if (r7 == r0) goto L_0x01ed
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r6.mSP
            java.lang.String r2 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r2)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x01ed
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r6.mSP
            java.lang.String r2 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            java.util.List<com.mijia.model.alarm.AlarmItem> r3 = r6.mList
            int r4 = r7 + -1
            java.lang.Object r3 = r3.get(r4)
            com.mijia.model.alarm.AlarmItem r3 = (com.mijia.model.alarm.AlarmItem) r3
            long r3 = r3.b
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            int r0 = r0.indexOf(r2)
            if (r0 <= 0) goto L_0x01ed
            android.view.View r0 = r8.mTopLine
            java.lang.String r2 = "#dfdfdf"
            int r2 = android.graphics.Color.parseColor(r2)
            r0.setBackgroundColor(r2)
            goto L_0x01f8
        L_0x01ed:
            android.view.View r0 = r8.mTopLine
            java.lang.String r2 = "#ff692e"
            int r2 = android.graphics.Color.parseColor(r2)
            r0.setBackgroundColor(r2)
        L_0x01f8:
            android.widget.TextView r0 = r8.mTime
            java.lang.String r2 = "#ff692e"
            int r2 = android.graphics.Color.parseColor(r2)
            r0.setTextColor(r2)
            android.view.View r0 = r8.mBottomLine
            java.lang.String r2 = "#ff692e"
            int r2 = android.graphics.Color.parseColor(r2)
            r0.setBackgroundColor(r2)
        L_0x020e:
            java.util.List<com.mijia.model.alarm.AlarmItem> r0 = r6.mList
            int r0 = r0.size()
            int r0 = r0 + -1
            if (r7 != r0) goto L_0x021f
            android.view.View r7 = r8.mBottomLine
            r8 = 4
            r7.setVisibility(r8)
            goto L_0x0224
        L_0x021f:
            android.view.View r7 = r8.mBottomLine
            r7.setVisibility(r1)
        L_0x0224:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
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