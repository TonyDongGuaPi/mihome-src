package com.xiaomi.smarthome.homeroom.view;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeRoomEditorActivityV2;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.homeroom.view.RoomListAdapter;
import com.xiaomi.smarthome.stat.STAT;
import java.util.Set;

public class RoomEditChildViewHolder extends RoomChildViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private TextView f18373a;
    protected View m;
    public View n;
    public View o;
    public View p;
    public CheckBox q;
    private TextView r;
    private View s;
    /* access modifiers changed from: private */
    public Set<String> t;

    public RoomEditChildViewHolder(View view, RoomListAdapter.EditModeListener editModeListener) {
        super(view, editModeListener);
        this.m = view.findViewById(R.id.root);
        this.f18373a = (TextView) view.findViewById(R.id.title);
        this.r = (TextView) view.findViewById(R.id.desc);
        this.s = view.findViewById(R.id.next_btn);
        this.n = view.findViewById(R.id.sort_icon);
        this.o = view.findViewById(R.id.delete_btn);
        this.q = (CheckBox) view.findViewById(R.id.checkbox);
        this.p = view.findViewById(R.id.divider_item);
    }

    public void a(final RoomListAdapter roomListAdapter, final Room room, int i, int i2) {
        final boolean i3 = roomListAdapter.i();
        boolean j = roomListAdapter.j();
        this.t = roomListAdapter.k();
        if (!i3) {
            this.q.setVisibility(4);
            this.n.setVisibility(4);
        } else if (j) {
            this.q.setVisibility(4);
            this.n.setVisibility(0);
        } else {
            this.q.setVisibility(0);
            this.q.setTag(room.d());
            this.n.setVisibility(4);
        }
        if (this.t != null) {
            this.q.setChecked(this.t.contains(room.d()));
        }
        this.q.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    if (!RoomEditChildViewHolder.this.t.contains(RoomEditChildViewHolder.this.q.getTag())) {
                        roomListAdapter.a(room, true);
                    }
                } else if (RoomEditChildViewHolder.this.t.contains(RoomEditChildViewHolder.this.q.getTag())) {
                    roomListAdapter.a(room, false);
                }
            }
        });
        this.s.setVisibility(i3 ? 8 : 0);
        this.f18373a.setText(room.e());
        int a2 = HomeManager.a().a(room);
        if (a2 <= 1) {
            this.r.setText(SHApplication.getAppContext().getResources().getQuantityString(R.plurals.choose_device_device_count, a2, new Object[]{Integer.valueOf(a2)}));
        } else {
            this.r.setText(SHApplication.getAppContext().getResources().getQuantityString(R.plurals.choose_device_device_counts, a2, new Object[]{Integer.valueOf(a2)}));
        }
        final int i4 = i;
        final int i5 = i2;
        final boolean z = i3;
        final Room room2 = room;
        final RoomListAdapter roomListAdapter2 = roomListAdapter;
        this.m.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (RoomEditChildViewHolder.this.l != null) {
                    RoomEditChildViewHolder.this.l.a(i4, i5);
                    return;
                }
                if (!z) {
                    Intent intent = new Intent(SHApplication.getAppContext(), HomeRoomEditorActivityV2.class);
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.putExtra("room_id_param", room2.d());
                    SHApplication.getAppContext().startActivity(intent);
                    STAT.d.Z(room2.e());
                }
                if (RoomEditChildViewHolder.this.q.getVisibility() == 0) {
                    boolean z = !RoomEditChildViewHolder.this.q.isChecked();
                    roomListAdapter2.a(room2, z);
                    RoomEditChildViewHolder.this.q.setChecked(z);
                }
            }
        });
        this.m.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (RoomEditChildViewHolder.this.k != null && !i3) {
                    RoomEditChildViewHolder.this.k.a();
                    roomListAdapter.a(room, true);
                }
                STAT.d.Y();
                return true;
            }
        });
    }
}
