package com.xiaomi.youpin.hawkeye.display.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.miui.tsmclient.util.StringUtils;
import com.xiaomi.youpin.hawkeye.R;
import com.xiaomi.youpin.hawkeye.appstart.AppStartInfo;
import com.xiaomi.youpin.hawkeye.entity.BaseInfo;
import com.xiaomi.youpin.hawkeye.memory.MemoryRecordInfo;
import com.xiaomi.youpin.hawkeye.network.NetWorkRecordInfo;
import com.xiaomi.youpin.hawkeye.timecounter.ActivityCounterInfo;
import com.xiaomi.youpin.hawkeye.timecounter.BlockInfo;
import com.xiaomi.youpin.hawkeye.utils.DeviceUtils;
import java.text.SimpleDateFormat;
import java.util.List;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private List<BaseInfo> f23354a;
    private Context b;

    public DisplayAdapter(List<BaseInfo> list, Context context) {
        this.f23354a = list;
        this.b = context;
    }

    @NonNull
    /* renamed from: a */
    public DisplayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DisplayViewHolder(LayoutInflater.from(this.b).inflate(R.layout.item_display_view, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(@NonNull DisplayViewHolder displayViewHolder, int i) {
        BaseInfo baseInfo = this.f23354a.get(i);
        if (baseInfo != null) {
            displayViewHolder.f23355a.setText(baseInfo.mTaskName);
            StringBuilder sb = new StringBuilder();
            sb.append(" record time    :   ");
            sb.append(new SimpleDateFormat(StringUtils.EXPECT_TIME_FORMAT).format(Long.valueOf(baseInfo.timestamp)));
            sb.append("\n");
            if (baseInfo instanceof AppStartInfo) {
                sb.append(" launchTime   :   ");
                sb.append(((AppStartInfo) baseInfo).launchTime);
            } else if (baseInfo instanceof BlockInfo) {
                BlockInfo blockInfo = (BlockInfo) baseInfo;
                StringBuilder sb2 = new StringBuilder();
                if (!blockInfo.blockStack.isEmpty()) {
                    for (String append : blockInfo.blockStack) {
                        sb2.append(append);
                        sb2.append("\n");
                    }
                }
                sb.append(" Block Time  :   ");
                sb.append(blockInfo.blockTime);
                sb.append("\n Block Stack :   ");
                sb.append(sb2);
            } else if (baseInfo instanceof ActivityCounterInfo) {
                ActivityCounterInfo activityCounterInfo = (ActivityCounterInfo) baseInfo;
                sb.append(activityCounterInfo.prevActivity);
                sb.append("  -->  ");
                sb.append(activityCounterInfo.curActivity);
                sb.append("\n totalCost :   ");
                sb.append(activityCounterInfo.totalCost);
                sb.append("\n pauseCost :   ");
                sb.append(activityCounterInfo.pauseCost);
                sb.append("\n launchCost :   ");
                sb.append(activityCounterInfo.launchCost);
                sb.append("\n renderCost :   ");
                sb.append(activityCounterInfo.renderCost);
                sb.append("\n otherCost :   ");
                sb.append(activityCounterInfo.otherCost);
            } else if (baseInfo instanceof NetWorkRecordInfo) {
                NetWorkRecordInfo netWorkRecordInfo = (NetWorkRecordInfo) baseInfo;
                sb.append(" url  :   ");
                sb.append(netWorkRecordInfo.url);
                sb.append("\n method :   ");
                sb.append(netWorkRecordInfo.method);
                sb.append("\n totalCost :   ");
                sb.append(netWorkRecordInfo.totalCost);
                sb.append("\n responseCode :   ");
                sb.append(netWorkRecordInfo.responseCode);
                sb.append("\n requestLength :   ");
                sb.append(DeviceUtils.a(netWorkRecordInfo.requestLength));
                sb.append("\n responseLength :   ");
                sb.append(DeviceUtils.a(netWorkRecordInfo.responseLength));
                sb.append("\n totalLength :   ");
                sb.append(DeviceUtils.a(netWorkRecordInfo.totalLength));
                sb.append("\n errorMessage :   ");
                sb.append(netWorkRecordInfo.errorMessage);
            } else if (baseInfo instanceof MemoryRecordInfo) {
                MemoryRecordInfo memoryRecordInfo = (MemoryRecordInfo) baseInfo;
                sb.append(" processName  :   ");
                sb.append(memoryRecordInfo.processName);
                sb.append("\n dalvikPss :   ");
                sb.append((memoryRecordInfo.dalvikPss / 1024) + " MB");
                sb.append("\n nativePss :   ");
                sb.append((memoryRecordInfo.nativePss / 1024) + " MB");
                sb.append("\n otherPss :   ");
                sb.append((memoryRecordInfo.otherPss / 1024) + " MB");
                sb.append("\n totalPss :   ");
                sb.append((memoryRecordInfo.totalPss / 1024) + " MB");
            }
            displayViewHolder.b.setText(sb.toString());
        }
    }

    public int getItemCount() {
        if (this.f23354a.isEmpty()) {
            return 0;
        }
        return this.f23354a.size();
    }

    class DisplayViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f23355a;
        TextView b;

        public DisplayViewHolder(View view) {
            super(view);
            this.f23355a = (TextView) view.findViewById(R.id.display_title);
            this.b = (TextView) view.findViewById(R.id.tv_display_data);
        }
    }
}
