package com.xiaomi.smarthome.camera.activity.alarm2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mijia.model.alarmcloud.AlarmCloudManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.alarm2.bean.EventType;
import java.util.ArrayList;

public class RecyclerEventTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    /* access modifiers changed from: private */
    public OnItemClick onItemClick;
    private ArrayList<EventType> types;

    public interface OnItemClick {
        void onItemClick(int i);
    }

    public RecyclerEventTypeAdapter(Context context2, ArrayList<EventType> arrayList, OnItemClick onItemClick2) {
        this.context = context2;
        this.types = arrayList;
        this.onItemClick = onItemClick2;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TypeHolder(LayoutInflater.from(this.context).inflate(R.layout.item_event_type_recycler, viewGroup, false));
    }

    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {
        TypeHolder typeHolder = (TypeHolder) viewHolder;
        typeHolder.type_name.setText(this.types.get(viewHolder.getAdapterPosition()).desc);
        typeHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (RecyclerEventTypeAdapter.this.onItemClick != null) {
                    RecyclerEventTypeAdapter.this.onItemClick.onItemClick(viewHolder.getAdapterPosition());
                }
            }
        });
    }

    public void addEvent(EventType eventType) {
        this.types.add(eventType);
        notifyDataSetChanged();
    }

    public boolean containsAiType() {
        if (AlarmCloudManager.AI.equalsIgnoreCase(this.types.get(this.types.size() - 1).name)) {
            return true;
        }
        return false;
    }

    public int getItemCount() {
        return this.types.size();
    }

    class TypeHolder extends RecyclerView.ViewHolder {
        TextView type_name;

        public TypeHolder(View view) {
            super(view);
            this.type_name = (TextView) view.findViewById(R.id.type_name);
        }
    }
}
