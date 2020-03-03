package com.xiaomi.smarthome.newui;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.adapter.deviceroom.DeviceRoomAdapter;
import java.lang.ref.WeakReference;

public class RoomRecycler extends RecyclerView {
    private static final int c = 1;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public DeviceRoomAdapter f20369a;
    private WeakReference<DeviceMainPage> b;
    private Handler d;

    public void onEnterEditMode() {
    }

    public void onExitEditMode() {
    }

    public RoomRecycler(@NonNull Context context) {
        this(context, (AttributeSet) null);
        a();
    }

    public RoomRecycler(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        a();
    }

    public RoomRecycler(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new Handler(Looper.getMainLooper()) {
            public void dispatchMessage(Message message) {
                if (RoomRecycler.this.f20369a != null) {
                    RoomRecycler.this.f20369a.a();
                }
            }
        };
        a();
    }

    private void a() {
        setLayoutManager(new LinearLayoutManager(getContext()));
        this.f20369a = new DeviceRoomAdapter(getContext());
        setAdapter(this.f20369a);
        addItemDecoration(new ItemDecoration());
    }

    public void setDeviceMainPage(DeviceMainPage deviceMainPage) {
        this.b = new WeakReference<>(deviceMainPage);
    }

    public void refresh() {
        DeviceMainPage deviceMainPage;
        if (this.f20369a == null) {
            return;
        }
        if (this.b == null || (deviceMainPage = (DeviceMainPage) this.b.get()) == null) {
            this.f20369a.a();
        } else if (deviceMainPage.m) {
            this.f20369a.a();
        } else {
            this.d.removeMessages(1);
            this.d.sendEmptyMessageDelayed(1, 500);
        }
    }

    class ItemDecoration extends RecyclerView.ItemDecoration {
        ItemDecoration() {
        }

        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            if (childAdapterPosition < 0 || childAdapterPosition >= RoomRecycler.this.f20369a.getItemCount()) {
                super.getItemOffsets(rect, view, recyclerView, state);
            } else if (childAdapterPosition == 0) {
                rect.set(0, DisplayUtils.a(3.0f), 0, 0);
            } else {
                rect.set(0, 0, 0, 0);
            }
        }
    }
}
