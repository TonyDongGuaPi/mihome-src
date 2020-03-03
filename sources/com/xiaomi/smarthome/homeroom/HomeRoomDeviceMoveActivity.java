package com.xiaomi.smarthome.homeroom;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.infrared.adapter.BaseListAdapter;
import com.xiaomi.infrared.adapter.ViewHolder;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.newui.HomeListDialogHelper;
import java.util.ArrayList;
import java.util.List;

public class HomeRoomDeviceMoveActivity extends BaseActivity {
    public static String KEY_CHECKED_DIDS = "key_checked_dids";
    public static String KEY_TRANSFER_ROOM_NAME = "key_transfer_room_name";

    /* renamed from: a  reason: collision with root package name */
    private static final String f18059a = "HomeRoomDeviceMoveActiv";
    private RoomAdapter b;
    private Dialog c;
    private ArrayList<String> d;
    private boolean e = false;
    private BroadcastReceiver f = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            HomeRoomDeviceMoveActivity.this.b();
        }
    };
    /* access modifiers changed from: private */
    public String g;
    private String h;
    @BindView(2131430969)
    ImageView mBack;
    TextView mDefaultRoomDevCount;
    TextView mDefaultRoomTitle;
    @BindView(2131432956)
    LinearLayout mGroupTitle;
    @BindView(2131428813)
    ListView mListView;
    @BindView(2131430797)
    View mMaskView;
    @BindView(2131429586)
    ImageView mMenuIcom;
    @BindView(2131430975)
    TextView mTitle;
    @BindView(2131432919)
    FrameLayout mTitleBar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_room_device_move);
        ButterKnife.bind((Activity) this);
        this.g = HomeManager.a().l();
        if (TextUtils.isEmpty(this.g)) {
            finish();
            return;
        }
        this.d = getIntent().getStringArrayListExtra(KEY_CHECKED_DIDS);
        if (this.d == null || this.d.isEmpty()) {
            finish();
            return;
        }
        a();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.f, new IntentFilter(HomeManager.t));
    }

    private void a() {
        Home j = HomeManager.a().j(this.g);
        if (j == null) {
            finish();
            return;
        }
        findViewById(R.id.title_bar).setBackgroundColor(getResources().getColor(R.color.white));
        this.mTitle.setText(j.k());
        this.mBack.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                HomeRoomDeviceMoveActivity.this.c(view);
            }
        });
        this.mMenuIcom.setVisibility(0);
        this.mGroupTitle.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                HomeRoomDeviceMoveActivity.this.b(view);
            }
        });
        View inflate = LayoutInflater.from(this).inflate(R.layout.tag_child_item_sort_edit, (ViewGroup) null);
        inflate.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                HomeRoomDeviceMoveActivity.this.a(view);
            }
        });
        this.mDefaultRoomTitle = (TextView) inflate.findViewById(R.id.title);
        inflate.findViewById(R.id.next_btn).setVisibility(8);
        this.mDefaultRoomTitle.setTextSize(2, 17.0f);
        this.mDefaultRoomTitle.setText(R.string.default_room);
        this.mDefaultRoomDevCount = (TextView) inflate.findViewById(R.id.desc);
        this.mListView.addFooterView(inflate);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                HomeRoomDeviceMoveActivity.this.a(adapterView, view, i, j);
            }
        });
        this.b = new RoomAdapter(this, HomeManager.a().a(this.g));
        this.mListView.setAdapter(this.b);
        b();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        a((Room) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(AdapterView adapterView, View view, int i, long j) {
        Room room = (Room) adapterView.getAdapter().getItem(i);
        if (room != null) {
            a(room);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        Home j;
        if (!TextUtils.isEmpty(this.g) && isValid() && (j = HomeManager.a().j(this.g)) != null) {
            this.mTitle.setText(j.k());
            this.b.a(HomeManager.a().a(this.g));
            int size = HomeManager.a().l(this.g).size();
            if (size <= 1) {
                this.mDefaultRoomDevCount.setText(getResources().getQuantityString(R.plurals.choose_device_device_count, size, new Object[]{Integer.valueOf(size)}));
                return;
            }
            this.mDefaultRoomDevCount.setText(getResources().getQuantityString(R.plurals.choose_device_device_counts, size, new Object[]{Integer.valueOf(size)}));
        }
    }

    private void a(Room room) {
        Home j = HomeManager.a().j(this.g);
        if (j != null) {
            this.c = new MLAlertDialog.Builder(this).b((CharSequence) String.format(getString(R.string.move_room_desc), new Object[]{j.k(), room == null ? getString(R.string.default_room) : room.e()})).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(room, j) {
                private final /* synthetic */ Room f$1;
                private final /* synthetic */ Home f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    HomeRoomDeviceMoveActivity.this.a(this.f$1, this.f$2, dialogInterface, i);
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a(getResources().getColor(R.color.mi_brain_tip_text_color), -1).d();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Room room, Home home, DialogInterface dialogInterface, int i) {
        if (room == null) {
            a(new DefaultRoomProcessor(home), this.d);
        } else {
            a(new CommonRoomProcessor(room), this.d);
        }
    }

    private void a(Processor processor, List<String> list) {
        processor.a(list);
        this.e = true;
    }

    class Processor {
        /* access modifiers changed from: package-private */
        public void a(List<String> list) {
        }

        Processor() {
        }
    }

    class DefaultRoomProcessor extends Processor {

        /* renamed from: a  reason: collision with root package name */
        Home f18064a;

        DefaultRoomProcessor(Home home) {
            super();
            this.f18064a = home;
        }

        /* access modifiers changed from: package-private */
        public void a(List<String> list) {
            if (this.f18064a.m() == null || !this.f18064a.m().containsAll(list)) {
                HomeManager.a().a(this.f18064a, (Room) null, list, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                    public void a() {
                        HomeRoomDeviceMoveActivity.this.onEditSuccess(HomeRoomDeviceMoveActivity.this.getString(R.string.default_room));
                        HomeManager.a().b();
                    }

                    public void a(int i, Error error) {
                        HomeRoomDeviceMoveActivity.this.onEditFail(i, error);
                    }
                });
            } else {
                HomeRoomDeviceMoveActivity.this.onEditSuccess(HomeRoomDeviceMoveActivity.this.getString(R.string.default_room));
            }
        }
    }

    class CommonRoomProcessor extends Processor {

        /* renamed from: a  reason: collision with root package name */
        Room f18062a;

        CommonRoomProcessor(Room room) {
            super();
            this.f18062a = room;
        }

        /* access modifiers changed from: package-private */
        public void a(List<String> list) {
            HomeManager.a().a(this.f18062a, list, (List<String>) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                public void a() {
                    HomeRoomDeviceMoveActivity.this.onEditSuccess(CommonRoomProcessor.this.f18062a.e());
                    HomeRoomDeviceMoveActivity.this.b();
                }

                public void a(int i, Error error) {
                    HomeRoomDeviceMoveActivity.this.onEditFail(i, error);
                }
            });
        }
    }

    public void onEditSuccess(String str) {
        this.h = str;
        if (isValid()) {
            Device b2 = SmartHomeDeviceManager.a().b(this.d.get(0));
            if (b2 == null) {
                LogUtilGrey.a(f18059a, "no device find,fail.");
                return;
            }
            if (this.d.size() == 1) {
                ToastUtil.a((CharSequence) getString(R.string.move_single_device_to_room, new Object[]{b2.getName(), str}));
            } else {
                ToastUtil.a((CharSequence) getString(R.string.move_multiple_devices_to_room, new Object[]{b2.getName(), str}));
            }
            HomeManager.a().a((List<String>) this.d, true);
            onBackPressed();
        }
    }

    public void onEditFail(int i, Error error) {
        if (isValid()) {
            if (error == null || error.a() != -35) {
                ToastUtil.a((int) R.string.add_failed);
            } else {
                ToastUtil.a((int) R.string.name_repeat);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void finish() {
        if (!this.e || TextUtils.isEmpty(this.h)) {
            setResult(0);
        } else {
            Intent intent = new Intent();
            intent.putExtra(KEY_TRANSFER_ROOM_NAME, this.h);
            setResult(-1, intent);
        }
        super.finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.c != null && this.c.isShowing()) {
            this.c.dismiss();
            this.c = null;
        }
        this.mMaskView.clearAnimation();
        this.mMenuIcom.clearAnimation();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.f);
    }

    private void c() {
        a(true);
        HomeListDialogHelper.a((Context) this, (View) this.mTitleBar, false, this.g, true, (HomeListDialogHelper.HomeListDialogV2Listener) new HomeListDialogHelper.HomeListDialogV2Listener() {
            public void a() {
                HomeRoomDeviceMoveActivity.this.a(false);
            }

            public void a(Home home) {
                if (home != null && !TextUtils.isEmpty(home.j())) {
                    String unused = HomeRoomDeviceMoveActivity.this.g = home.j();
                    HomeRoomDeviceMoveActivity.this.b();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        this.mMenuIcom.animate().rotation(z ? -180.0f : 0.0f).setDuration(200).setInterpolator(new AccelerateDecelerateInterpolator());
        this.mMaskView.setVisibility(z ? 0 : 8);
        this.mMaskView.setAnimation(AnimationUtils.loadAnimation(SHApplication.getAppContext(), z ? R.anim.dd_mask_in : R.anim.dd_mask_out));
    }

    class RoomAdapter extends BaseListAdapter<Room> {
        public int a(int i) {
            return R.layout.tag_child_item_sort_edit;
        }

        RoomAdapter(Context context, List<Room> list) {
            super(context, list);
        }

        public void a(ViewHolder viewHolder, Room room, int i) {
            Room room2 = (Room) this.b.get(i);
            if (room2 != null) {
                TextView textView = (TextView) viewHolder.a((int) R.id.title);
                TextView textView2 = (TextView) viewHolder.a((int) R.id.desc);
                viewHolder.a((int) R.id.next_btn).setVisibility(8);
                textView.setTextSize(2, 17.0f);
                textView.setText(room2.e());
                int a2 = HomeManager.a().a(room2);
                if (a2 <= 1) {
                    textView2.setText(HomeRoomDeviceMoveActivity.this.getResources().getQuantityString(R.plurals.choose_device_device_count, a2, new Object[]{Integer.valueOf(a2)}));
                    return;
                }
                textView2.setText(HomeRoomDeviceMoveActivity.this.getResources().getQuantityString(R.plurals.choose_device_device_counts, a2, new Object[]{Integer.valueOf(a2)}));
            }
        }
    }
}
