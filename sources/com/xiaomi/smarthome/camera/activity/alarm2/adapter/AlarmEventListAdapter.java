package com.xiaomi.smarthome.camera.activity.alarm2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mijia.model.alarmcloud.AlarmCloudManager;
import com.mijia.model.alarmcloud.AlarmVideo;
import com.miui.tsmclient.util.StringUtils;
import com.xiaomi.smarthome.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class AlarmEventListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    /* access modifiers changed from: private */
    public int currentOffset;
    /* access modifiers changed from: private */
    public boolean isShareUser;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public List<AlarmVideo> mData;
    /* access modifiers changed from: private */
    public OnItemClickLister mOnItemClickLister;
    /* access modifiers changed from: private */
    public SimpleDateFormat mSdf = new SimpleDateFormat(StringUtils.EXPECT_TIME_FORMAT, Locale.CHINA);

    public interface OnItemClickLister {
        void onBtnClick(AlarmVideo alarmVideo, int i, boolean z);

        void onItemClick(AlarmVideo alarmVideo, int i);

        void smoothScrollToPosition(int i);
    }

    public AlarmEventListAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList();
    }

    @NonNull
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.recycler_item_alarm_player_event, viewGroup, false));
    }

    public void setData(List<AlarmVideo> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    public void setShareUser(boolean z) {
        this.isShareUser = z;
    }

    public AlarmVideo getItem(int i) {
        return this.mData.get(i);
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public void parseProgress(long j) {
        int size = this.mData.size();
        if (size != 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= size) {
                    break;
                } else if (new Double(this.mData.get(i).startDuration * 1000.0d).longValue() > j) {
                    AlarmVideo alarmVideo = this.mData.get(i);
                    i2 = i - 1;
                    break;
                } else {
                    i2 = i;
                    i++;
                }
            }
            if (i2 >= 0 && i2 < getItemCount()) {
                AlarmVideo item = getItem(i2);
                if (this.currentOffset != item.offset) {
                    this.currentOffset = item.offset;
                    notifyDataSetChanged();
                    if (this.mOnItemClickLister != null) {
                        this.mOnItemClickLister.smoothScrollToPosition(i2);
                    }
                }
            }
        }
    }

    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.bindView(i);
    }

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.mOnItemClickLister = onItemClickLister;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final View dividerLine;
        private final View dividerLineFull;
        private ImageView ivPlayIcon;
        /* access modifiers changed from: private */
        public TextView tvButton;
        private TextView tvEventName;
        private TextView tvTime;

        public ItemViewHolder(View view) {
            super(view);
            this.tvTime = (TextView) view.findViewById(R.id.tv_time);
            this.tvEventName = (TextView) view.findViewById(R.id.tv_event);
            this.tvButton = (TextView) view.findViewById(R.id.tv_btn);
            this.ivPlayIcon = (ImageView) view.findViewById(R.id.iv_play);
            this.dividerLine = view.findViewById(R.id.divider_line);
            this.dividerLineFull = view.findViewById(R.id.divider_line_full);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0126, code lost:
            r8 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x01f4, code lost:
            r8 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:73:0x0213, code lost:
            r7.append(com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.access$200(r0.this$0).getResources().getString(com.xiaomi.smarthome.R.string.and));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:74:0x0224, code lost:
            if (r8 == false) goto L_0x0227;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x0227, code lost:
            r10 = r10 + 1;
         */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x0129  */
        /* JADX WARNING: Removed duplicated region for block: B:47:0x013f  */
        /* JADX WARNING: Removed duplicated region for block: B:67:0x01c2  */
        /* JADX WARNING: Removed duplicated region for block: B:68:0x01d8  */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x01f6  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void bindView(int r17) {
            /*
                r16 = this;
                r0 = r16
                r1 = r17
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r2 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                int r2 = r2.getItemCount()
                r3 = 1
                int r2 = r2 - r3
                r4 = 8
                r5 = 0
                if (r1 != r2) goto L_0x001c
                android.view.View r2 = r0.dividerLineFull
                r2.setVisibility(r5)
                android.view.View r2 = r0.dividerLine
                r2.setVisibility(r4)
                goto L_0x0026
            L_0x001c:
                android.view.View r2 = r0.dividerLineFull
                r2.setVisibility(r4)
                android.view.View r2 = r0.dividerLine
                r2.setVisibility(r5)
            L_0x0026:
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r2 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                com.mijia.model.alarmcloud.AlarmVideo r2 = r2.getItem(r1)
                com.mijia.model.alarmcloud.FileIdMetaResult r6 = r2.fileIdMetaResult
                android.widget.TextView r6 = r0.tvTime
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r7 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                java.text.SimpleDateFormat r7 = r7.mSdf
                long r8 = r2.createTime
                java.lang.Long r8 = java.lang.Long.valueOf(r8)
                java.lang.String r7 = r7.format(r8)
                r6.setText(r7)
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r6 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                int r6 = r6.currentOffset
                int r7 = r2.offset
                r8 = 4
                if (r6 != r7) goto L_0x007d
                android.widget.ImageView r6 = r0.ivPlayIcon
                r6.setVisibility(r5)
                android.widget.TextView r6 = r0.tvTime
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r7 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                android.content.Context r7 = r7.mContext
                android.content.res.Resources r7 = r7.getResources()
                r9 = 2131166849(0x7f070681, float:1.7947955E38)
                int r7 = r7.getColor(r9)
                r6.setTextColor(r7)
                android.widget.TextView r6 = r0.tvEventName
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r7 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                android.content.Context r7 = r7.mContext
                android.content.res.Resources r7 = r7.getResources()
                int r7 = r7.getColor(r9)
                r6.setTextColor(r7)
                goto L_0x009f
            L_0x007d:
                android.widget.ImageView r6 = r0.ivPlayIcon
                r6.setVisibility(r8)
                android.widget.TextView r6 = r0.tvTime
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r7 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                android.content.Context r7 = r7.mContext
                android.content.res.Resources r7 = r7.getResources()
                r9 = 2131165335(0x7f070097, float:1.7944884E38)
                int r7 = r7.getColor(r9)
                r6.setTextColor(r7)
                android.widget.TextView r6 = r0.tvEventName
                r7 = -16777216(0xffffffffff000000, float:-1.7014118E38)
                r6.setTextColor(r7)
            L_0x009f:
                java.lang.String r6 = r2.eventType
                boolean r7 = android.text.TextUtils.isEmpty(r6)
                if (r7 == 0) goto L_0x00af
                android.widget.TextView r1 = r0.tvEventName
                java.lang.String r2 = ""
                r1.setText(r2)
                return
            L_0x00af:
                java.lang.String r7 = ":"
                java.lang.String[] r6 = r6.split(r7)
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r7 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                r7.sortByEventType(r6)
                int r7 = r6.length
                r9 = 2
                if (r7 <= r9) goto L_0x00c4
                java.lang.Object[] r6 = java.util.Arrays.copyOfRange(r6, r5, r9)
                java.lang.String[] r6 = (java.lang.String[]) r6
            L_0x00c4:
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                r10 = 0
            L_0x00ca:
                int r11 = r6.length
                r12 = 2131493307(0x7f0c01bb, float:1.861009E38)
                if (r10 >= r11) goto L_0x022c
                r11 = r6[r10]
                r13 = -1
                int r14 = r11.hashCode()
                r15 = -1293551627(0xffffffffb2e5f7f5, float:-2.6771867E-8)
                if (r14 == r15) goto L_0x0118
                r15 = 2088(0x828, float:2.926E-42)
                if (r14 == r15) goto L_0x010e
                r15 = 2181757(0x214a7d, float:3.057293E-39)
                if (r14 == r15) goto L_0x0104
                r15 = 722651973(0x2b12cb45, float:5.2151713E-13)
                if (r14 == r15) goto L_0x00fa
                r15 = 1316906260(0x4e7e6514, float:1.06700928E9)
                if (r14 == r15) goto L_0x00f0
                goto L_0x0122
            L_0x00f0:
                java.lang.String r14 = "BabyCry"
                boolean r11 = r11.equals(r14)
                if (r11 == 0) goto L_0x0122
                r11 = 4
                goto L_0x0123
            L_0x00fa:
                java.lang.String r14 = "PeopleMotion"
                boolean r11 = r11.equals(r14)
                if (r11 == 0) goto L_0x0122
                r11 = 2
                goto L_0x0123
            L_0x0104:
                java.lang.String r14 = "Face"
                boolean r11 = r11.equals(r14)
                if (r11 == 0) goto L_0x0122
                r11 = 3
                goto L_0x0123
            L_0x010e:
                java.lang.String r14 = "AI"
                boolean r11 = r11.equals(r14)
                if (r11 == 0) goto L_0x0122
                r11 = 0
                goto L_0x0123
            L_0x0118:
                java.lang.String r14 = "ObjectMotion"
                boolean r11 = r11.equals(r14)
                if (r11 == 0) goto L_0x0122
                r11 = 1
                goto L_0x0123
            L_0x0122:
                r11 = -1
            L_0x0123:
                switch(r11) {
                    case 0: goto L_0x01f6;
                    case 1: goto L_0x01d8;
                    case 2: goto L_0x01c2;
                    case 3: goto L_0x013f;
                    case 4: goto L_0x0129;
                    default: goto L_0x0126;
                }
            L_0x0126:
                r8 = 1
                goto L_0x0213
            L_0x0129:
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r11 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                android.content.Context r11 = r11.mContext
                android.content.res.Resources r11 = r11.getResources()
                r13 = 2131493395(0x7f0c0213, float:1.8610269E38)
                java.lang.String r11 = r11.getString(r13)
                r7.append(r11)
                goto L_0x01f4
            L_0x013f:
                com.mijia.model.alarmcloud.FileIdMetaResult r11 = r2.fileIdMetaResult
                r13 = 2131499810(0x7f0c1b22, float:1.862328E38)
                if (r11 == 0) goto L_0x01af
                com.mijia.model.alarmcloud.FileIdMetaResult r11 = r2.fileIdMetaResult
                java.util.List<com.mijia.model.alarmcloud.FaceInfoMeta> r11 = r11.faceInfoMetas
                if (r11 == 0) goto L_0x01af
                com.mijia.model.alarmcloud.FileIdMetaResult r11 = r2.fileIdMetaResult
                java.util.List<com.mijia.model.alarmcloud.FaceInfoMeta> r11 = r11.faceInfoMetas
                int r11 = r11.size()
                if (r11 != 0) goto L_0x0157
                goto L_0x01af
            L_0x0157:
                java.lang.String r11 = ""
                com.mijia.model.alarmcloud.FileIdMetaResult r14 = r2.fileIdMetaResult
                java.util.List<com.mijia.model.alarmcloud.FaceInfoMeta> r14 = r14.faceInfoMetas
                java.util.Iterator r14 = r14.iterator()
            L_0x0161:
                boolean r15 = r14.hasNext()
                if (r15 == 0) goto L_0x0179
                java.lang.Object r15 = r14.next()
                com.mijia.model.alarmcloud.FaceInfoMeta r15 = (com.mijia.model.alarmcloud.FaceInfoMeta) r15
                if (r15 != 0) goto L_0x0170
                goto L_0x0161
            L_0x0170:
                boolean r8 = r15.matched
                if (r8 == 0) goto L_0x0177
                java.lang.String r11 = r15.figureName
                goto L_0x0179
            L_0x0177:
                r8 = 4
                goto L_0x0161
            L_0x0179:
                boolean r8 = android.text.TextUtils.isEmpty(r11)
                if (r8 == 0) goto L_0x0191
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r8 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                android.content.Context r8 = r8.mContext
                android.content.res.Resources r8 = r8.getResources()
                java.lang.String r8 = r8.getString(r13)
                r7.append(r8)
                goto L_0x0126
            L_0x0191:
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r8 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                android.content.Context r8 = r8.mContext
                android.content.res.Resources r8 = r8.getResources()
                r13 = 2131495969(0x7f0c0c21, float:1.861549E38)
                java.lang.String r8 = r8.getString(r13)
                java.lang.Object[] r13 = new java.lang.Object[r3]
                r13[r5] = r11
                java.lang.String r8 = java.lang.String.format(r8, r13)
                r7.append(r8)
                goto L_0x0126
            L_0x01af:
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r8 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                android.content.Context r8 = r8.mContext
                android.content.res.Resources r8 = r8.getResources()
                java.lang.String r8 = r8.getString(r13)
                r7.append(r8)
                goto L_0x0126
            L_0x01c2:
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r8 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                android.content.Context r8 = r8.mContext
                android.content.res.Resources r8 = r8.getResources()
                r11 = 2131494906(0x7f0c07fa, float:1.8613334E38)
                java.lang.String r8 = r8.getString(r11)
                r7.append(r8)
                goto L_0x0126
            L_0x01d8:
                boolean r8 = android.text.TextUtils.isEmpty(r7)
                if (r8 == 0) goto L_0x01f4
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r8 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                android.content.Context r8 = r8.mContext
                android.content.res.Resources r8 = r8.getResources()
                r11 = 2131494904(0x7f0c07f8, float:1.861333E38)
                java.lang.String r8 = r8.getString(r11)
                r7.append(r8)
                goto L_0x0126
            L_0x01f4:
                r8 = 0
                goto L_0x0213
            L_0x01f6:
                int r8 = r7.length()
                r7.delete(r5, r8)
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r8 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                android.content.Context r8 = r8.mContext
                android.content.res.Resources r8 = r8.getResources()
                r11 = 2131493230(0x7f0c016e, float:1.8609934E38)
                java.lang.String r8 = r8.getString(r11)
                r7.append(r8)
                goto L_0x0126
            L_0x0213:
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r11 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                android.content.Context r11 = r11.mContext
                android.content.res.Resources r11 = r11.getResources()
                java.lang.String r11 = r11.getString(r12)
                r7.append(r11)
                if (r8 == 0) goto L_0x0227
                goto L_0x022c
            L_0x0227:
                int r10 = r10 + 1
                r8 = 4
                goto L_0x00ca
            L_0x022c:
                int r6 = r7.length()
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r8 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                android.content.Context r8 = r8.mContext
                android.content.res.Resources r8 = r8.getResources()
                java.lang.String r8 = r8.getString(r12)
                int r8 = r8.length()
                int r6 = r6 - r8
                int r8 = r7.length()
                java.lang.String r9 = "!"
                r7.replace(r6, r8, r9)
                android.widget.TextView r6 = r0.tvEventName
                java.lang.String r7 = r7.toString()
                r6.setText(r7)
                com.mijia.model.alarmcloud.FileIdMetaResult r6 = r2.fileIdMetaResult
                if (r6 == 0) goto L_0x0297
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter r6 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.this
                boolean r6 = r6.isShareUser
                if (r6 != 0) goto L_0x0297
                com.mijia.model.alarmcloud.FileIdMetaResult r4 = r2.fileIdMetaResult
                java.util.List<com.mijia.model.alarmcloud.FaceInfoMeta> r4 = r4.faceInfoMetas
                java.util.Iterator r4 = r4.iterator()
            L_0x0269:
                boolean r6 = r4.hasNext()
                if (r6 == 0) goto L_0x027d
                java.lang.Object r6 = r4.next()
                com.mijia.model.alarmcloud.FaceInfoMeta r6 = (com.mijia.model.alarmcloud.FaceInfoMeta) r6
                if (r6 != 0) goto L_0x0278
                goto L_0x0269
            L_0x0278:
                boolean r6 = r6.matched
                if (r6 == 0) goto L_0x0269
                goto L_0x027e
            L_0x027d:
                r3 = 0
            L_0x027e:
                android.widget.TextView r4 = r0.tvButton
                r4.setVisibility(r5)
                if (r3 == 0) goto L_0x028e
                android.widget.TextView r3 = r0.tvButton
                r4 = 2131497096(0x7f0c1088, float:1.8617775E38)
                r3.setText(r4)
                goto L_0x029c
            L_0x028e:
                android.widget.TextView r3 = r0.tvButton
                r4 = 2131493215(0x7f0c015f, float:1.8609904E38)
                r3.setText(r4)
                goto L_0x029c
            L_0x0297:
                android.widget.TextView r3 = r0.tvButton
                r3.setVisibility(r4)
            L_0x029c:
                android.widget.TextView r3 = r0.tvButton
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter$ItemViewHolder$1 r4 = new com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter$ItemViewHolder$1
                r4.<init>(r1)
                r3.setOnClickListener(r4)
                android.view.View r3 = r0.itemView
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter$ItemViewHolder$2 r4 = new com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter$ItemViewHolder$2
                r4.<init>(r2, r1)
                r3.setOnClickListener(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter.ItemViewHolder.bindView(int):void");
        }
    }

    private void smoothScrollToPosition(int i) {
        if (this.mOnItemClickLister != null) {
            this.mOnItemClickLister.smoothScrollToPosition(i);
        }
    }

    /* access modifiers changed from: private */
    public void sortByEventType(String[] strArr) {
        if (strArr != null && strArr.length > 1) {
            Arrays.sort(strArr, new Comparator<String>() {
                public int compare(String str, String str2) {
                    return AlarmEventListAdapter.this.getIntByType(str) - AlarmEventListAdapter.this.getIntByType(str2);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public int getIntByType(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1293551627:
                if (str.equals(AlarmCloudManager.OBJECT_MOTION)) {
                    c = 4;
                    break;
                }
                break;
            case -740191200:
                if (str.equals(AlarmCloudManager.KNOWN_FACE)) {
                    c = 1;
                    break;
                }
                break;
            case 2088:
                if (str.equals(AlarmCloudManager.AI)) {
                    c = 5;
                    break;
                }
                break;
            case 2181757:
                if (str.equals(AlarmCloudManager.FACE)) {
                    c = 2;
                    break;
                }
                break;
            case 722651973:
                if (str.equals(AlarmCloudManager.PEOPLE_MOTION)) {
                    c = 3;
                    break;
                }
                break;
            case 1316906260:
                if (str.equals(AlarmCloudManager.BABY_CRY)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            default:
                return 6;
        }
    }
}
