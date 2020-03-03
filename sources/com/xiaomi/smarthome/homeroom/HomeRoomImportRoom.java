package com.xiaomi.smarthome.homeroom;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.homeroom.view.BaseGroupViewHolder;
import com.xiaomi.smarthome.homeroom.view.CommonGroupTitleViewHolder;
import com.xiaomi.smarthome.homeroom.view.RoomChildViewHolder;
import com.xiaomi.smarthome.homeroom.view.RoomEditChildViewHolder;
import com.xiaomi.smarthome.homeroom.view.RoomListAdapter;
import java.util.ArrayList;
import java.util.List;

public class HomeRoomImportRoom extends BaseActivity {
    public static final String HOME_ID_PARAM = "home_id_param";

    /* renamed from: a  reason: collision with root package name */
    private HomeRoomListAdapter f18096a;
    private RecyclerViewExpandableItemManager b;
    private View c;
    /* access modifiers changed from: private */
    public String d = null;
    private BroadcastReceiver e = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (HomeRoomImportRoom.this.isValid()) {
                HomeRoomImportRoom.this.refresh();
            }
        }
    };
    protected LinearLayoutManager mLayoutManager;
    protected RecyclerView mRecyclerView;

    public static void startActivity(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(context, HomeRoomImportRoom.class);
            intent.putExtra("home_id_param", str);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_room_import_room);
        this.d = getIntent().getStringExtra("home_id_param");
        if (TextUtils.isEmpty(this.d)) {
            this.d = HomeManager.a().l();
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeRoomImportRoom.this.finish();
            }
        });
        findViewById(R.id.module_a_3_right_text_btn).setVisibility(8);
        this.c = findViewById(R.id.common_white_empty_view);
        ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.import_room_empty);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.import_room);
        a();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.e, new IntentFilter(HomeManager.t));
    }

    private void a() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.b = new RecyclerViewExpandableItemManager((Parcelable) null);
        this.f18096a = new HomeRoomListAdapter(this);
        refresh();
        this.mRecyclerView.setAdapter(this.b.a((RecyclerView.Adapter) this.f18096a));
        ((SimpleItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.b.a(this.mRecyclerView);
        this.b.d();
    }

    public void refresh() {
        if (this.f18096a != null) {
            this.f18096a.b();
            this.f18096a.notifyDataSetChanged();
            if (this.f18096a.a() < 1) {
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.e);
    }

    private class ChildViewHolder extends RoomEditChildViewHolder {
        public ChildViewHolder(View view, RoomListAdapter.EditModeListener editModeListener) {
            super(view, editModeListener);
        }

        public void a(RoomListAdapter roomListAdapter, final Room room, int i, int i2) {
            super.a(roomListAdapter, room, i, i2);
            this.m.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(HomeRoomImportRoom.this, HomeRoomEditorActivityV2.class);
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.putExtra("room_id_param", room.d());
                    intent.putExtra("home_id_param", HomeRoomImportRoom.this.d);
                    intent.putExtra(HomeRoomEditorActivityV2.ROOM_IMPORT, true);
                    HomeRoomImportRoom.this.startActivity(intent);
                }
            });
        }
    }

    private class HomeRoomListAdapter extends RoomListAdapter {
        private List<Home> d = new ArrayList();

        public HomeRoomListAdapter(Activity activity) {
            super(activity);
        }

        /* access modifiers changed from: protected */
        public void b() {
            this.d.clear();
            List<Home> f = HomeManager.a().f();
            for (int i = 0; i < f.size(); i++) {
                if (!TextUtils.equals(f.get(i).j(), HomeRoomImportRoom.this.d) && f.get(i).d().size() != 0 && f.get(i).p()) {
                    this.d.add(f.get(i));
                }
            }
        }

        public int a() {
            return this.d.size();
        }

        public int a(int i) {
            if (i < this.d.size()) {
                return this.d.get(i).d().size();
            }
            return 0;
        }

        public long b(int i) {
            if (i >= this.d.size()) {
                return 0;
            }
            return (long) this.d.get(i).j().hashCode();
        }

        public long c(int i, int i2) {
            if (i2 < 0 || i2 >= this.d.get(i).d().size()) {
                return 0;
            }
            return (long) this.d.get(i).d().get(i2).d().hashCode();
        }

        /* renamed from: c */
        public BaseGroupViewHolder a(ViewGroup viewGroup, int i) {
            return new CommonGroupTitleViewHolder(LayoutInflater.from(this.b).inflate(R.layout.tag_group_item_common_4, viewGroup, false));
        }

        /* renamed from: d */
        public RoomChildViewHolder b(ViewGroup viewGroup, int i) {
            return new ChildViewHolder(LayoutInflater.from(this.b).inflate(R.layout.tag_child_item_sort_edit, viewGroup, false), (RoomListAdapter.EditModeListener) null);
        }

        /* renamed from: a */
        public void b(BaseGroupViewHolder baseGroupViewHolder, int i, int i2) {
            if (i < this.d.size()) {
                baseGroupViewHolder.a((RecyclerView.Adapter) this, this.d.get(i).k());
            }
        }

        /* renamed from: a */
        public void b(RoomChildViewHolder roomChildViewHolder, int i, int i2, int i3) {
            Room room;
            if (i < this.d.size() && i2 < this.d.get(i).d().size() && (room = this.d.get(i).d().get(i2)) != null) {
                roomChildViewHolder.a((RoomListAdapter) this, room, i, i2);
                roomChildViewHolder.e(i2 == a(i) + -1 ? 8 : 0);
            }
        }
    }
}
