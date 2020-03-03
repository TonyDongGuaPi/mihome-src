package com.xiaomi.smarthome.homeroom.view;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.homeroom.view.RoomListAdapter;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.stat.STAT;

public class RoomChildViewHolder extends BaseViewHolder {
    public View c;
    public View d;
    public View e;
    public View f;
    public View g;
    public TextView h;
    public View i;
    public View j;
    public RoomListAdapter.EditModeListener k;
    public RoomListAdapter.OnItemClickListener l;

    public RoomChildViewHolder(View view, RoomListAdapter.EditModeListener editModeListener) {
        super(view);
        this.c = view;
        this.d = view.findViewById(R.id.view_group);
        this.e = view.findViewById(R.id.sort_icon);
        this.f = view.findViewById(R.id.delete_btn);
        this.j = view.findViewById(R.id.divider_item);
        this.g = view.findViewById(R.id.imageView);
        this.i = view.findViewById(R.id.desc_edit_mode);
        this.h = (TextView) view.findViewById(R.id.desc);
        this.k = editModeListener;
    }

    public void a(RoomListAdapter.OnItemClickListener onItemClickListener) {
        this.l = onItemClickListener;
    }

    public void e(int i2) {
        this.j.setVisibility(i2);
    }

    public void a(boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
        if (z) {
            layoutParams.leftMargin = 0;
        } else {
            layoutParams.leftMargin = DisplayUtils.a(SHApplication.getAppContext(), 24.0f);
        }
    }

    public void a(RoomListAdapter roomListAdapter, int i2, int i3, Home home) {
        int i4;
        boolean i5 = roomListAdapter.i();
        int i6 = 8;
        this.i.setVisibility((!i5 || roomListAdapter.j()) ? 8 : 0);
        View view = this.g;
        if (!i5) {
            i6 = 0;
        }
        view.setVisibility(i6);
        if (home == null) {
            i4 = HomeManager.a().j().size();
        } else {
            i4 = HomeManager.a().m(home.j()).size();
        }
        this.d.setVisibility(i4 > 0 ? 0 : 4);
        if (i4 <= 1) {
            this.h.setText(SHApplication.getAppContext().getResources().getQuantityString(R.plurals.choose_device_device_count, i4, new Object[]{Integer.valueOf(i4)}));
        } else {
            this.h.setText(SHApplication.getAppContext().getResources().getQuantityString(R.plurals.choose_device_device_counts, i4, new Object[]{Integer.valueOf(i4)}));
        }
        this.c.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (RoomChildViewHolder.this.k != null) {
                    RoomChildViewHolder.this.k.a();
                }
                STAT.d.Y();
                return true;
            }
        });
    }

    public void a(RoomListAdapter roomListAdapter, Room room, int i2, int i3) {
        boolean i4 = roomListAdapter.i();
        int i5 = 8;
        this.i.setVisibility((!i4 || roomListAdapter.j()) ? 8 : 0);
        View view = this.g;
        if (!i4) {
            i5 = 0;
        }
        view.setVisibility(i5);
        int size = HomeManager.a().j().size();
        this.d.setVisibility(size > 0 ? 0 : 4);
        if (size <= 1) {
            this.h.setText(SHApplication.getAppContext().getResources().getQuantityString(R.plurals.choose_device_device_count, size, new Object[]{Integer.valueOf(size)}));
        } else {
            this.h.setText(SHApplication.getAppContext().getResources().getQuantityString(R.plurals.choose_device_device_counts, size, new Object[]{Integer.valueOf(size)}));
        }
        this.c.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (RoomChildViewHolder.this.k == null) {
                    return true;
                }
                RoomChildViewHolder.this.k.a();
                return true;
            }
        });
    }
}
