package com.xiaomi.smarthome.messagecenter.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.xiaomi.smarthome.ui.base.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class MessageCenterAdapterNew extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final int g = 6;
    private static final int h = 7;

    /* renamed from: a  reason: collision with root package name */
    List<BaseModel> f10540a = new ArrayList();

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    public int getItemCount() {
        return this.f10540a.size();
    }

    public int getItemViewType(int i) {
        List<BaseModel> list = this.f10540a;
        return (list == null || list.isEmpty() || i < 0 || i >= list.size()) ? 0 : 0;
    }
}
