package com.xiaomi.smarthome.newui;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.newui.NameEditDialogHelper;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MoveRoomDialogHelper {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static List<String> f20319a = new ArrayList();
    /* access modifiers changed from: private */
    public static WeakReference<DeviceMainPage> b = new WeakReference<>((Object) null);

    public interface addRoomListener {
        void onSuccess(String str);
    }

    public static void a(DeviceMainPage deviceMainPage) {
        b = new WeakReference<>(deviceMainPage);
    }

    public static void a(final Context context, List<String> list) {
        f20319a = list;
        View inflate = LayoutInflater.from(context).inflate(R.layout.move_room_dialog_layout, (ViewGroup) null);
        final MLAlertDialog b2 = new MLAlertDialog.Builder(context).a((int) R.string.menu_edit_move).b(inflate).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                STAT.d.ac();
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                STAT.d.ac();
            }
        }).b();
        View findViewById = inflate.findViewById(R.id.add_room);
        ((ImageView) findViewById.findViewById(R.id.room_icon)).setImageResource(R.drawable.selector_add_custom_room);
        ((TextView) findViewById.findViewById(R.id.title)).setText(R.string.tag_recommend_custom);
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MoveRoomDialogHelper.b(context, MoveRoomDialogHelper.f20319a);
                b2.dismiss();
            }
        });
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        List<Room> e = HomeManager.a().e();
        ViewGroup.LayoutParams layoutParams = inflate.findViewById(R.id.recycler_view_container).getLayoutParams();
        if (e == null || e.size() <= 3) {
            layoutParams.height = context.getResources().getDimensionPixelSize(R.dimen.listitem_7_height) * (e == null ? 0 : e.size() + 1);
        } else {
            layoutParams.height = context.getResources().getDimensionPixelSize(R.dimen.listitem_7_height) * 4;
        }
        inflate.findViewById(R.id.recycler_view_container).setLayoutParams(layoutParams);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        InternalRoomListAdapter internalRoomListAdapter = new InternalRoomListAdapter(context, b2, list);
        recyclerView.setAdapter(internalRoomListAdapter);
        internalRoomListAdapter.a();
        b2.show();
        LinearLayout linearLayout = (LinearLayout) b2.getButton(-2).getParent();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams2.topMargin = 0;
        linearLayout.setLayoutParams(layoutParams2);
    }

    private static void c(final Context context, final List<String> list) {
        new MLAlertDialog.Builder(context).b((int) R.string.tag_name_duplicate).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MoveRoomDialogHelper.b(context, list);
            }
        }).d();
    }

    public static void a(Context context) {
        a(context, (List<String>) new ArrayList(), (addRoomListener) null);
    }

    public static void b(Context context, List<String> list) {
        a(context, list, (addRoomListener) null);
    }

    public static void a(final Context context, final List<String> list, final addRoomListener addroomlistener) {
        final MLAlertDialog a2 = NameEditDialogHelper.a(context, "", context.getString(R.string.room_name_update), context.getString(R.string.input_tag_name_hint), new NameEditDialogHelper.NameEditListener() {
            public void a(final String str) {
                HomeManager.a().a(str, (List<String>) list, (String) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                    public void a() {
                        if (!(context instanceof BaseActivity) || ((BaseActivity) context).isValid()) {
                            ToastUtil.a((int) R.string.create_success);
                            if (addroomlistener != null) {
                                addroomlistener.onSuccess(str);
                            }
                            DeviceMainPage deviceMainPage = (DeviceMainPage) MoveRoomDialogHelper.b.get();
                            if (deviceMainPage != null) {
                                deviceMainPage.exitEditMode();
                            }
                            STAT.d.aa(str);
                        }
                    }

                    public void a(int i, Error error) {
                        if ((context instanceof BaseActivity) && !((BaseActivity) context).isValid()) {
                            return;
                        }
                        if (error == null || error.a() != -35) {
                            ToastUtil.a((int) R.string.add_failed);
                        } else {
                            ToastUtil.a((int) R.string.name_repeat);
                        }
                    }
                });
                STAT.d.ad();
            }

            public String b(String str) {
                if (HomeManager.a().a((Room) null, str)) {
                    return context.getString(R.string.room_name_duplicate);
                }
                return null;
            }
        });
        a2.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                STAT.d.ac();
            }
        });
        a2.getButton(-2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                a2.dismiss();
                STAT.d.ac();
            }
        });
    }

    public static void b(final Context context, List<String> list, final addRoomListener addroomlistener) {
        MLAlertDialog a2 = NameEditDialogHelper.a(context, "", context.getString(R.string.room_name_update), context.getString(R.string.input_tag_name_hint), new NameEditDialogHelper.NameEditListener() {
            public void a(String str) {
                if (addroomlistener != null) {
                    addroomlistener.onSuccess(str);
                }
            }

            public String b(String str) {
                if (HomeManager.a().a((Room) null, str)) {
                    return context.getString(R.string.room_name_duplicate);
                }
                return null;
            }
        });
        a2.getButton(-2).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MLAlertDialog.this.dismiss();
            }
        });
    }

    public static class InternalRoomListAdapter extends RecyclerView.Adapter<RoomViewHolder> {

        /* renamed from: a  reason: collision with root package name */
        protected Context f20327a;
        protected List<Room> b = new ArrayList();
        /* access modifiers changed from: private */
        public MLAlertDialog c;
        /* access modifiers changed from: private */
        public List<String> d;

        public InternalRoomListAdapter(Context context, MLAlertDialog mLAlertDialog, List<String> list) {
            this.c = mLAlertDialog;
            this.f20327a = context;
            this.d = list;
        }

        /* access modifiers changed from: protected */
        public void a() {
            List<Room> e = HomeManager.a().e();
            if (e == null) {
                this.b = new ArrayList();
            } else {
                this.b = new ArrayList(e);
            }
        }

        /* renamed from: a */
        public RoomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(this.f20327a).inflate(R.layout.tag_child_item_sort_edit, viewGroup, false);
            inflate.findViewById(R.id.next_btn).setVisibility(8);
            inflate.findViewById(R.id.desc).setVisibility(8);
            return new RoomViewHolder(inflate);
        }

        /* renamed from: a */
        public void onBindViewHolder(RoomViewHolder roomViewHolder, int i) {
            roomViewHolder.b.setVisibility(i == getItemCount() + -1 ? 8 : 0);
            if (i == 0) {
                roomViewHolder.f20330a.setText(R.string.tag_recommend_custom);
                roomViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        MoveRoomDialogHelper.b(InternalRoomListAdapter.this.f20327a, MoveRoomDialogHelper.f20319a);
                        InternalRoomListAdapter.this.c.dismiss();
                        DeviceMainPage deviceMainPage = (DeviceMainPage) MoveRoomDialogHelper.b.get();
                        if (deviceMainPage != null) {
                            deviceMainPage.exitEditMode();
                        }
                        STAT.d.ab();
                    }
                });
                return;
            }
            final Room room = this.b.get(i - 1);
            roomViewHolder.f20330a.setText(room.e());
            roomViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    STAT.d.aa(room.e());
                    MoveRoomDialogHelper.b(InternalRoomListAdapter.this.f20327a, room, (List<String>) InternalRoomListAdapter.this.d);
                    InternalRoomListAdapter.this.c.dismiss();
                    DeviceMainPage deviceMainPage = (DeviceMainPage) MoveRoomDialogHelper.b.get();
                    if (deviceMainPage != null) {
                        deviceMainPage.exitEditMode();
                    }
                }
            });
        }

        public int getItemCount() {
            return this.b.size() + 1;
        }

        public static class RoomViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            public TextView f20330a;
            public View b;

            public RoomViewHolder(View view) {
                super(view);
                this.f20330a = (TextView) view.findViewById(R.id.title);
                this.b = view.findViewById(R.id.divider_item);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(final Context context, final Room room, final List<String> list) {
        HomeManager.a().a(room, list, (List<String>) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
            public void a() {
                if (((context instanceof BaseActivity) && !((BaseActivity) context).isValid()) || list == null) {
                    return;
                }
                if (list.size() == 1) {
                    Device b2 = SmartHomeDeviceManager.a().b((String) list.get(0));
                    if (b2 != null) {
                        ToastUtil.a((CharSequence) context.getString(R.string.move_single_device_to_room, new Object[]{b2.getName(), room.e()}));
                        return;
                    }
                    return;
                }
                Device b3 = SmartHomeDeviceManager.a().b((String) list.get(0));
                if (b3 != null) {
                    ToastUtil.a((CharSequence) context.getString(R.string.move_multiple_devices_to_room, new Object[]{b3.getName(), room.e()}));
                }
            }

            public void a(int i, Error error) {
                if (error == null || error.a() != -35) {
                    ToastUtil.a((int) R.string.add_failed);
                } else {
                    ToastUtil.a((int) R.string.name_repeat);
                }
            }
        });
        SmartHomeDeviceHelper.a().b().j();
        SmartHomeDeviceHelper.a().b().k();
    }
}
