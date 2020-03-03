package com.xiaomi.smarthome.camera.activity.alarm2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.xiaomi.smarthome.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class AlarmVideoAdapter extends RecyclerView.Adapter<ViewHolder> {
    final int ITEM_TYPE_LAST = 2;
    final int ITEM_TYPE_NORMAL = 1;
    private boolean isToTheEnd;
    private boolean isVip;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public List<AlarmVideo> mData;
    /* access modifiers changed from: private */
    public ItemClickedListener mItemClickedListener;
    /* access modifiers changed from: private */
    public SimpleDateFormat mSdf = new SimpleDateFormat("HH:mm", Locale.CHINA);

    public interface ItemClickedListener {
        void onItemClicked(AlarmVideo alarmVideo, int i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public AlarmVideoAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList();
        ImageLoader.a().a((ImageLoadingListener) new ImageLoadingListener() {
            public void onLoadingCancelled(String str, View view) {
            }

            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            }

            public void onLoadingFailed(String str, View view, FailReason failReason) {
            }

            public void onLoadingStarted(String str, View view) {
            }
        });
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        if (i == 1) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.event_list_item_normal, (ViewGroup) null);
        } else if (i == 2) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.event_list_item_last, (ViewGroup) null);
        }
        return new ViewHolder(view, i);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindView(i);
    }

    public void setData(List<AlarmVideo> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if (!this.isToTheEnd) {
            return this.mData.size();
        }
        return this.mData.size() + 1;
    }

    public boolean isTheLastVideo(int i) {
        if (!this.isToTheEnd || i != getItemCount() - 2) {
            return !this.isToTheEnd && i == getItemCount() - 1;
        }
        return true;
    }

    public AlarmVideo getItem(int i) {
        if (i >= this.mData.size()) {
            return null;
        }
        return this.mData.get(i);
    }

    public int getItemViewType(int i) {
        if (this.isToTheEnd && i == getItemCount() - 1) {
            return 2;
        }
        return 1;
    }

    public void update(List<AlarmVideo> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    public void setVipStatus(boolean z) {
        this.isVip = z;
    }

    public void setToTheEnd(boolean z) {
        this.isToTheEnd = z;
        notifyDataSetChanged();
    }

    public void highLightPlaying(int i) {
        AlarmVideo item = getItem(i);
        if (item != null) {
            resetPlayingStatus();
            item.isPlaying = true;
            notifyDataSetChanged();
        }
    }

    private void resetPlayingStatus() {
        if (this.mData != null && this.mData.size() > 0) {
            for (AlarmVideo alarmVideo : this.mData) {
                alarmVideo.isPlaying = false;
            }
        }
    }

    public void setOnItemClickedListener(ItemClickedListener itemClickedListener) {
        this.mItemClickedListener = itemClickedListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView eventDesc;
        private TextView eventTime;
        private ImageView[] imageViews;
        private ImageView ivCover;
        private ImageView ivRing;
        private ImageView ivRun;
        int type;
        private View vDividerLine;

        public ViewHolder(View view, @NonNull int i) {
            super(view);
            this.type = i;
            if (i != 2) {
                this.ivCover = (ImageView) view.findViewById(R.id.crv_event_image);
                this.eventTime = (TextView) view.findViewById(R.id.tv_event_time);
                this.eventDesc = (TextView) view.findViewById(R.id.tv_event_des);
                this.ivRun = (ImageView) view.findViewById(R.id.iv_event_type_pepole);
                this.ivRing = (ImageView) view.findViewById(R.id.iv_event_type_ring);
                this.vDividerLine = view.findViewById(R.id.v_divider_line);
                this.imageViews = new ImageView[]{this.ivRing, this.ivRun};
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0133, code lost:
            r7 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x02a5, code lost:
            r2.append(com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.access$400(r14.this$0).getResources().getString(com.xiaomi.smarthome.R.string.and));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:76:0x02b6, code lost:
            if (r7 == false) goto L_0x02b9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:77:0x02b9, code lost:
            r6 = r6 + 1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void bindView(final int r15) {
            /*
                r14 = this;
                int r0 = r14.type
                r1 = 2
                if (r0 != r1) goto L_0x0006
                return
            L_0x0006:
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter r0 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.this
                java.util.List r0 = r0.mData
                java.lang.Object r0 = r0.get(r15)
                com.mijia.model.alarmcloud.AlarmVideo r0 = (com.mijia.model.alarmcloud.AlarmVideo) r0
                android.view.View r2 = r14.itemView
                r3 = 2131431997(0x7f0b123d, float:1.848574E38)
                android.view.View r2 = r2.findViewById(r3)
                boolean r3 = r0.isPlaying
                if (r3 == 0) goto L_0x0026
                java.lang.String r3 = "#32BAC0"
            L_0x0021:
                int r3 = android.graphics.Color.parseColor(r3)
                goto L_0x0029
            L_0x0026:
                java.lang.String r3 = "#00000000"
                goto L_0x0021
            L_0x0029:
                r2.setBackgroundColor(r3)
                com.nostra13.universalimageloader.core.ImageLoader r2 = com.nostra13.universalimageloader.core.ImageLoader.a()
                java.lang.String r3 = r0.imgStoreUrl
                android.widget.ImageView r4 = r14.ivCover
                r2.a((java.lang.String) r3, (android.widget.ImageView) r4)
                android.view.View r2 = r14.itemView
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter$ViewHolder$1 r3 = new com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter$ViewHolder$1
                r3.<init>(r15)
                r2.setOnClickListener(r3)
                boolean r15 = r0.isRead
                if (r15 == 0) goto L_0x005c
                android.widget.TextView r15 = r14.eventTime
                java.lang.String r2 = "#999999"
                int r2 = android.graphics.Color.parseColor(r2)
                r15.setTextColor(r2)
                android.widget.TextView r15 = r14.eventDesc
                java.lang.String r2 = "#999999"
                int r2 = android.graphics.Color.parseColor(r2)
                r15.setTextColor(r2)
                goto L_0x0072
            L_0x005c:
                android.widget.TextView r15 = r14.eventTime
                java.lang.String r2 = "#000000"
                int r2 = android.graphics.Color.parseColor(r2)
                r15.setTextColor(r2)
                android.widget.TextView r15 = r14.eventDesc
                java.lang.String r2 = "#000000"
                int r2 = android.graphics.Color.parseColor(r2)
                r15.setTextColor(r2)
            L_0x0072:
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter r15 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.this
                java.text.SimpleDateFormat r15 = r15.mSdf
                long r2 = r0.createTime
                java.lang.Long r2 = java.lang.Long.valueOf(r2)
                java.lang.String r15 = r15.format(r2)
                android.widget.TextView r2 = r14.eventTime
                r2.setText(r15)
                java.lang.String r15 = r0.eventType
                boolean r2 = android.text.TextUtils.isEmpty(r15)
                if (r2 == 0) goto L_0x0097
                android.widget.TextView r15 = r14.eventDesc
                java.lang.String r0 = ""
                r15.setText(r0)
                return
            L_0x0097:
                android.widget.ImageView[] r2 = r14.imageViews
                r3 = 0
                r2 = r2[r3]
                r4 = 8
                r2.setVisibility(r4)
                android.widget.ImageView[] r2 = r14.imageViews
                r5 = 1
                r2 = r2[r5]
                r2.setVisibility(r4)
                java.lang.String r2 = ":"
                java.lang.String[] r15 = r15.split(r2)
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter r2 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.this
                r2.sortByEventType(r15)
                int r2 = r15.length
                if (r2 <= r1) goto L_0x00bd
                java.lang.Object[] r15 = java.util.Arrays.copyOfRange(r15, r3, r1)
                java.lang.String[] r15 = (java.lang.String[]) r15
            L_0x00bd:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                android.view.View r6 = r14.vDividerLine
                r6.setVisibility(r4)
                r6 = 0
            L_0x00c8:
                int r7 = r15.length
                r8 = 2131493307(0x7f0c01bb, float:1.861009E38)
                if (r6 >= r7) goto L_0x02bd
                r7 = r15[r6]
                r9 = -1
                int r10 = r7.hashCode()
                r11 = -1293551627(0xffffffffb2e5f7f5, float:-2.6771867E-8)
                if (r10 == r11) goto L_0x0116
                r11 = 2088(0x828, float:2.926E-42)
                if (r10 == r11) goto L_0x010c
                r11 = 2181757(0x214a7d, float:3.057293E-39)
                if (r10 == r11) goto L_0x0102
                r11 = 722651973(0x2b12cb45, float:5.2151713E-13)
                if (r10 == r11) goto L_0x00f8
                r11 = 1316906260(0x4e7e6514, float:1.06700928E9)
                if (r10 == r11) goto L_0x00ee
                goto L_0x011f
            L_0x00ee:
                java.lang.String r10 = "BabyCry"
                boolean r10 = r7.equals(r10)
                if (r10 == 0) goto L_0x011f
                r9 = 4
                goto L_0x011f
            L_0x00f8:
                java.lang.String r10 = "PeopleMotion"
                boolean r10 = r7.equals(r10)
                if (r10 == 0) goto L_0x011f
                r9 = 2
                goto L_0x011f
            L_0x0102:
                java.lang.String r10 = "Face"
                boolean r10 = r7.equals(r10)
                if (r10 == 0) goto L_0x011f
                r9 = 3
                goto L_0x011f
            L_0x010c:
                java.lang.String r10 = "AI"
                boolean r10 = r7.equals(r10)
                if (r10 == 0) goto L_0x011f
                r9 = 0
                goto L_0x011f
            L_0x0116:
                java.lang.String r10 = "ObjectMotion"
                boolean r10 = r7.equals(r10)
                if (r10 == 0) goto L_0x011f
                r9 = 1
            L_0x011f:
                switch(r9) {
                    case 0: goto L_0x027a;
                    case 1: goto L_0x0250;
                    case 2: goto L_0x0224;
                    case 3: goto L_0x0163;
                    case 4: goto L_0x0136;
                    default: goto L_0x0122;
                }
            L_0x0122:
                r2.append(r7)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r3]
                r7.setVisibility(r4)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r5]
                r7.setVisibility(r4)
            L_0x0133:
                r7 = 1
                goto L_0x02a5
            L_0x0136:
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter r7 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.this
                android.content.Context r7 = r7.mContext
                android.content.res.Resources r7 = r7.getResources()
                r9 = 2131493395(0x7f0c0213, float:1.8610269E38)
                java.lang.String r7 = r7.getString(r9)
                r2.append(r7)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r6]
                r7.setVisibility(r3)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r6]
                r9 = 2130840250(0x7f020aba, float:1.7285533E38)
                r7.setImageResource(r9)
                android.view.View r7 = r14.vDividerLine
                r7.setVisibility(r3)
                r7 = 0
                goto L_0x02a5
            L_0x0163:
                com.mijia.model.alarmcloud.FileIdMetaResult r7 = r0.fileIdMetaResult
                r9 = 2131499810(0x7f0c1b22, float:1.862328E38)
                r10 = 2130840254(0x7f020abe, float:1.7285542E38)
                if (r7 == 0) goto L_0x01fe
                com.mijia.model.alarmcloud.FileIdMetaResult r7 = r0.fileIdMetaResult
                java.util.List<com.mijia.model.alarmcloud.FaceInfoMeta> r7 = r7.faceInfoMetas
                if (r7 == 0) goto L_0x01fe
                com.mijia.model.alarmcloud.FileIdMetaResult r7 = r0.fileIdMetaResult
                java.util.List<com.mijia.model.alarmcloud.FaceInfoMeta> r7 = r7.faceInfoMetas
                int r7 = r7.size()
                if (r7 != 0) goto L_0x017f
                goto L_0x01fe
            L_0x017f:
                java.lang.String r7 = ""
                com.mijia.model.alarmcloud.FileIdMetaResult r11 = r0.fileIdMetaResult
                java.util.List<com.mijia.model.alarmcloud.FaceInfoMeta> r11 = r11.faceInfoMetas
                java.util.Iterator r11 = r11.iterator()
            L_0x0189:
                boolean r12 = r11.hasNext()
                if (r12 == 0) goto L_0x019e
                java.lang.Object r12 = r11.next()
                com.mijia.model.alarmcloud.FaceInfoMeta r12 = (com.mijia.model.alarmcloud.FaceInfoMeta) r12
                if (r12 != 0) goto L_0x0198
                goto L_0x0189
            L_0x0198:
                boolean r13 = r12.matched
                if (r13 == 0) goto L_0x0189
                java.lang.String r7 = r12.figureName
            L_0x019e:
                boolean r11 = android.text.TextUtils.isEmpty(r7)
                if (r11 == 0) goto L_0x01ca
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r6]
                r7.setVisibility(r3)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r6]
                r7.setImageResource(r10)
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter r7 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.this
                android.content.Context r7 = r7.mContext
                android.content.res.Resources r7 = r7.getResources()
                java.lang.String r7 = r7.getString(r9)
                r2.append(r7)
                android.view.View r7 = r14.vDividerLine
                r7.setVisibility(r3)
                goto L_0x0133
            L_0x01ca:
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter r9 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.this
                android.content.Context r9 = r9.mContext
                android.content.res.Resources r9 = r9.getResources()
                r10 = 2131495969(0x7f0c0c21, float:1.861549E38)
                java.lang.String r9 = r9.getString(r10)
                java.lang.Object[] r10 = new java.lang.Object[r5]
                r10[r3] = r7
                java.lang.String r7 = java.lang.String.format(r9, r10)
                r2.append(r7)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r6]
                r7.setVisibility(r3)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r6]
                r9 = 2130840251(0x7f020abb, float:1.7285536E38)
                r7.setImageResource(r9)
                android.view.View r7 = r14.vDividerLine
                r7.setVisibility(r3)
                goto L_0x0133
            L_0x01fe:
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r6]
                r7.setVisibility(r3)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r6]
                r7.setImageResource(r10)
                android.view.View r7 = r14.vDividerLine
                r7.setVisibility(r3)
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter r7 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.this
                android.content.Context r7 = r7.mContext
                android.content.res.Resources r7 = r7.getResources()
                java.lang.String r7 = r7.getString(r9)
                r2.append(r7)
                goto L_0x0133
            L_0x0224:
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter r7 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.this
                android.content.Context r7 = r7.mContext
                android.content.res.Resources r7 = r7.getResources()
                r9 = 2131494906(0x7f0c07fa, float:1.8613334E38)
                java.lang.String r7 = r7.getString(r9)
                r2.append(r7)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r6]
                r7.setVisibility(r3)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r6]
                r9 = 2130840252(0x7f020abc, float:1.7285538E38)
                r7.setImageResource(r9)
                android.view.View r7 = r14.vDividerLine
                r7.setVisibility(r3)
                goto L_0x0133
            L_0x0250:
                boolean r7 = android.text.TextUtils.isEmpty(r2)
                if (r7 == 0) goto L_0x0133
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter r7 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.this
                android.content.Context r7 = r7.mContext
                android.content.res.Resources r7 = r7.getResources()
                r9 = 2131494904(0x7f0c07f8, float:1.861333E38)
                java.lang.String r7 = r7.getString(r9)
                r2.append(r7)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r3]
                r7.setVisibility(r4)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r5]
                r7.setVisibility(r4)
                goto L_0x0133
            L_0x027a:
                int r7 = r2.length()
                r2.delete(r3, r7)
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter r7 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.this
                android.content.Context r7 = r7.mContext
                android.content.res.Resources r7 = r7.getResources()
                r9 = 2131493230(0x7f0c016e, float:1.8609934E38)
                java.lang.String r7 = r7.getString(r9)
                r2.append(r7)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r3]
                r7.setVisibility(r4)
                android.widget.ImageView[] r7 = r14.imageViews
                r7 = r7[r5]
                r7.setVisibility(r4)
                goto L_0x0133
            L_0x02a5:
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter r9 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.this
                android.content.Context r9 = r9.mContext
                android.content.res.Resources r9 = r9.getResources()
                java.lang.String r9 = r9.getString(r8)
                r2.append(r9)
                if (r7 == 0) goto L_0x02b9
                goto L_0x02bd
            L_0x02b9:
                int r6 = r6 + 1
                goto L_0x00c8
            L_0x02bd:
                int r15 = r2.length()
                com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter r0 = com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.this
                android.content.Context r0 = r0.mContext
                android.content.res.Resources r0 = r0.getResources()
                java.lang.String r0 = r0.getString(r8)
                int r0 = r0.length()
                int r15 = r15 - r0
                int r0 = r2.length()
                java.lang.String r1 = "!"
                r2.replace(r15, r0, r1)
                android.widget.TextView r15 = r14.eventDesc
                java.lang.String r0 = r2.toString()
                r15.setText(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter.ViewHolder.bindView(int):void");
        }
    }

    /* access modifiers changed from: private */
    public void sortByEventType(String[] strArr) {
        if (strArr != null && strArr.length > 1) {
            Arrays.sort(strArr, new Comparator<String>() {
                public int compare(String str, String str2) {
                    return AlarmVideoAdapter.this.getIntByType(str) - AlarmVideoAdapter.this.getIntByType(str2);
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
