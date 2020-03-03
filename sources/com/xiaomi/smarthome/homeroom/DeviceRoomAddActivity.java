package com.xiaomi.smarthome.homeroom;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.newui.MoveRoomDialogHelper;
import java.util.ArrayList;
import java.util.List;

public class DeviceRoomAddActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private PullDownDragListView f17945a;
    /* access modifiers changed from: private */
    public RoomSelectAdapter b;
    /* access modifiers changed from: private */
    public Room c;
    private Room d;
    private String e;
    /* access modifiers changed from: private */
    public Room f = new Room();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = getIntent().getStringExtra("did");
        if (TextUtils.isEmpty(this.e)) {
            finish();
            Miio.b("ABC: did null!");
            return;
        }
        setContentView(R.layout.activity_device_room_add);
        this.f17945a = (PullDownDragListView) findViewById(R.id.room_list);
        this.f17945a.addHeaderView(LayoutInflater.from(this).inflate(R.layout.common_list_space_empty, this.f17945a, false));
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.room_loc_title);
        findViewById(R.id.module_a_3_right_iv_confirm_btn).setEnabled(true);
        findViewById(R.id.module_a_3_right_iv_confirm_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceRoomAddActivity.this.a();
            }
        });
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceRoomAddActivity.this.finish();
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MoveRoomDialogHelper.a((Context) DeviceRoomAddActivity.this, (List<String>) new ArrayList(), (MoveRoomDialogHelper.addRoomListener) new MoveRoomDialogHelper.addRoomListener() {
                    public void onSuccess(String str) {
                        if (DeviceRoomAddActivity.this.b != null && DeviceRoomAddActivity.this.isValid()) {
                            DeviceRoomAddActivity.this.b.a();
                            DeviceRoomAddActivity.this.b.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
        this.b = new RoomSelectAdapter();
        this.f17945a.setAdapter(this.b);
        this.d = HomeManager.a().p(this.e);
        if (this.d == null) {
            this.d = this.f;
        }
        this.c = this.d;
        HomeManager.a().b();
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.c == this.d) {
            finish();
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.e);
        ArrayList arrayList2 = new ArrayList();
        Room room = this.c;
        if (this.c == this.f) {
            room = this.d;
            arrayList.clear();
            arrayList2.add(this.e);
        }
        if (!HomeManager.A(room.e())) {
            ToastUtil.a((int) R.string.room_name_too_long);
        } else {
            HomeManager.a().a(room, (List<String>) arrayList, (List<String>) arrayList2, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                public void a() {
                    DeviceRoomAddActivity.this.finish();
                }

                public void a(int i, Error error) {
                    if (error == null || error.a() != -35) {
                        ToastUtil.a((int) R.string.add_failed);
                    } else {
                        ToastUtil.a((int) R.string.name_repeat);
                    }
                }
            });
        }
    }

    private class RoomSelectAdapter extends BaseAdapter {
        /* access modifiers changed from: private */
        public List<Room> b;

        public long getItemId(int i) {
            return 0;
        }

        public RoomSelectAdapter() {
            a();
        }

        public void a() {
            List<Room> e = HomeManager.a().e();
            if (e == null) {
                e = new ArrayList<>();
            }
            this.b = e;
        }

        public int getCount() {
            return this.b.size() + 1;
        }

        public Object getItem(int i) {
            if (i < 0 || i > this.b.size()) {
                return null;
            }
            if (i == this.b.size()) {
                return DeviceRoomAddActivity.this.f;
            }
            return this.b.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(DeviceRoomAddActivity.this).inflate(R.layout.room_child_item_select_edit, viewGroup, false);
                viewHolder = new ViewHolder();
                view.setTag(viewHolder);
                viewHolder.f17957a = (CheckBox) view.findViewById(R.id.select_checkbox);
                viewHolder.b = (TextView) view.findViewById(R.id.tv_title);
                viewHolder.c = view.findViewById(R.id.sort_icon);
                viewHolder.d = view.findViewById(R.id.divider_item);
                viewHolder.e = view.findViewById(R.id.delete_btn);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Room room = (Room) getItem(i);
            if (i == getCount() - 1) {
                a(viewHolder, room);
            } else {
                b(viewHolder, room);
            }
            viewHolder.d.setVisibility(i == getCount() - 1 ? 8 : 0);
            if (TextUtils.equals(room.d(), DeviceRoomAddActivity.this.c.d())) {
                viewHolder.f17957a.setChecked(true);
            } else {
                viewHolder.f17957a.setChecked(false);
            }
            return view;
        }

        private void a(ViewHolder viewHolder, Room room) {
            viewHolder.b.setText(R.string.tag_recommend_defaultroom);
            viewHolder.e.setEnabled(false);
            viewHolder.f17957a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        Room unused = DeviceRoomAddActivity.this.c = DeviceRoomAddActivity.this.f;
                    }
                    RoomSelectAdapter.this.notifyDataSetChanged();
                }
            });
        }

        private void b(final ViewHolder viewHolder, final Room room) {
            viewHolder.b.setText(room.e());
            viewHolder.f17957a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        Room unused = DeviceRoomAddActivity.this.c = room;
                    }
                    RoomSelectAdapter.this.notifyDataSetChanged();
                }
            });
            if (HomeManager.a().a(room) > 0) {
                viewHolder.e.setEnabled(false);
            } else {
                viewHolder.e.setEnabled(true);
            }
            viewHolder.e.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    new MLAlertDialog.Builder(viewHolder.e.getContext()).a((CharSequence) String.format(viewHolder.e.getContext().getString(R.string.tag_remove_confirm), new Object[]{room.e()})).a((int) R.string.tag_remove, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HomeManager.a().a(room, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                                public void a() {
                                    RoomSelectAdapter.this.b.remove(room);
                                    RoomSelectAdapter.this.notifyDataSetChanged();
                                }

                                public void a(int i, Error error) {
                                    ToastUtil.a((int) R.string.room_delete_failed);
                                }
                            });
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a(viewHolder.e.getContext().getResources().getColor(R.color.std_dialog_button_red), -1).d();
                }
            });
        }

        private class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            public CheckBox f17957a;
            public TextView b;
            public View c;
            public View d;
            public View e;

            private ViewHolder() {
            }
        }
    }
}
