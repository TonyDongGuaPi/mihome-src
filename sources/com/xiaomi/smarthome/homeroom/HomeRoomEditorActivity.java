package com.xiaomi.smarthome.homeroom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.DeviceTagRoom;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.smarthome.miio.page.devicetag.DeviceTagEditorFragment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomeRoomEditorActivity extends BaseActivity implements TextWatcher, View.OnClickListener {
    public static final String ACTION_EDITOR_CHANGED = "editor_changed_action";
    public static final int ACTIVITY_REQUEST_CODE_BATCH = 1001;
    public static final String CONTAINED_DIDS_PARAM = "contained_dids_param";
    public static final String ROOM_EDITABLE = "room_editable";
    public static final String ROOM_ICON = "room_icon";
    public static final String ROOM_ID_PARAM = "room_id_param";
    public static final String ROOM_NAME = "room_name";
    public static final String ROOM_ONLY_SHOW_ITSELF_DEVICE = "room_only_show_itself";

    /* renamed from: a  reason: collision with root package name */
    private static final String f18067a = "HomeRoomEditorActivity";
    private View b;
    private TextView c;
    /* access modifiers changed from: private */
    public EditText d;
    /* access modifiers changed from: private */
    public Room e;
    private PullDownDragListView f;
    private RoomDeviceAdapter g;
    private View h;
    private SimpleDraweeView i;
    /* access modifiers changed from: private */
    public boolean j = true;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public Set<String> l = new HashSet();
    private BroadcastReceiver m = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals("editor_changed_action", intent.getAction())) {
                HomeRoomEditorActivity.this.c();
            } else if (TextUtils.equals(DeviceTagEditorFragment.f19812a, intent.getAction())) {
                HomeRoomEditorActivity.this.softInputToggle(false);
            }
        }
    };
    private String n;
    /* access modifiers changed from: private */
    public TextView o;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_room_editor);
        String stringExtra = getIntent().getStringExtra("room_id_param");
        if (TextUtils.isEmpty(stringExtra)) {
            this.e = new Room();
            this.e.f(HomeManager.a().l());
            this.n = getIntent().getStringExtra("room_icon");
        } else {
            this.e = HomeManager.a().i(stringExtra);
            if (this.e == null) {
                this.e = new Room();
                this.e.f(HomeManager.a().l());
            }
            this.n = this.e.a();
        }
        this.j = getIntent().getBooleanExtra(ROOM_EDITABLE, true);
        this.k = getIntent().getBooleanExtra(ROOM_ONLY_SHOW_ITSELF_DEVICE, false);
        a();
    }

    private void a() {
        String stringExtra = getIntent().getStringExtra("room_name");
        this.b = findViewById(R.id.module_a_4_return_more_btn);
        this.c = (TextView) findViewById(R.id.module_a_4_return_finish_btn);
        TextView textView = (TextView) findViewById(R.id.module_a_4_return_more_title);
        this.d = (EditText) findViewById(R.id.input_tag);
        this.i = (SimpleDraweeView) findViewById(R.id.icon);
        this.i.setImageURI(Uri.parse("file://" + HomeManager.a().f(this.n)));
        this.i.setOnClickListener(this);
        if (TextUtils.isEmpty(this.e.e())) {
            textView.setText(R.string.tag_add_title);
            if (!this.j || !TextUtils.isEmpty(stringExtra)) {
                this.d.setText(stringExtra);
                this.d.setSelection(this.d.length());
                this.c.setEnabled(true);
            } else {
                this.d.postDelayed(new Runnable() {
                    public void run() {
                        HomeRoomEditorActivity.this.softInputToggle(true);
                    }
                }, 150);
            }
        } else {
            textView.setText(R.string.tag_editor_title);
            this.d.setText(this.e.e());
            this.d.setSelection(this.d.length());
        }
        this.c.setText(R.string.save);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.addTextChangedListener(this);
        this.f = (PullDownDragListView) findViewById(R.id.device_list);
        View inflate = LayoutInflater.from(this).inflate(R.layout.tag_group_item_common_3, this.f, false);
        this.o = (TextView) inflate.findViewById(R.id.title_left);
        this.f.addHeaderView(inflate);
        List<DeviceTagRoom> a2 = HomeManager.a().a(HomeManager.a().l(), this.e);
        this.g = new RoomDeviceAdapter(a2);
        if (a2 == null || a2.isEmpty()) {
            View findViewById = findViewById(R.id.empty);
            findViewById.setVisibility(0);
            ((TextView) findViewById.findViewById(R.id.no_login_tv)).setText(R.string.tag_no_device_to_add);
        } else {
            this.f.setAdapter(this.g);
            this.o.setText(SHApplication.getAppContext().getResources().getQuantityString(R.plurals.tag_selected_title, this.g.a().size()));
        }
        if (!this.j) {
            findViewById(R.id.et_container).setVisibility(8);
            this.c.setVisibility(8);
            this.c.setEnabled(false);
            textView.setText(this.d.getText());
            this.o.setVisibility(8);
            ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
            layoutParams.height = DisplayUtils.a(20.0f);
            inflate.setLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("editor_changed_action");
        intentFilter.addAction(DeviceTagEditorFragment.f19812a);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.m, intentFilter);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.m);
    }

    public void onClick(View view) {
        if (view == this.b) {
            softInputToggle(false);
            onBackPressed();
        } else if (view == this.c) {
            b();
        } else if (view == this.i) {
            Intent intent = new Intent(this, HomeRoomEditorIconActivity.class);
            intent.putExtra("room_name", this.d.getText().toString());
            intent.putExtra("room_icon", this.n);
            startActivityForResult(intent, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 == -1) {
            if (!TextUtils.isEmpty(this.d.getText().toString())) {
                this.c.setEnabled(true);
            }
            this.n = intent.getStringExtra("room_icon");
            this.i.setImageURI(Uri.parse("file://" + HomeManager.a().f(this.n)));
        }
    }

    public void onBackPressed() {
        if (this.c.isEnabled()) {
            new MLAlertDialog.Builder(this).a((int) R.string.tag_dump_data_prompt).b((int) R.string.tag_dump_data_prompt_description).a((int) R.string.save, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    HomeRoomEditorActivity.this.b();
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    HomeRoomEditorActivity.this.finish();
                }
            }).d();
        } else {
            super.onBackPressed();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        this.c.setEnabled(!TextUtils.isEmpty(charSequence));
    }

    /* access modifiers changed from: private */
    public void b() {
        List<Room> e2;
        softInputToggle(false);
        if (!TextUtils.isEmpty(this.e.d()) || (e2 = HomeManager.a().e()) == null || e2.size() < 50) {
            final String trim = this.d.getText().toString().trim();
            if (!HomeManager.A(trim)) {
                ToastUtil.a((int) R.string.room_name_too_long);
            } else if (StringUtil.t(trim)) {
                new MLAlertDialog.Builder(this).a((CharSequence) String.format(getString(R.string.tag_save_data_title), new Object[]{trim})).b((int) R.string.tag_save_data_description).c((int) R.string.tag_roger, (DialogInterface.OnClickListener) null).d();
            } else {
                Set<String> a2 = this.g.a();
                ArrayList arrayList = new ArrayList(a2);
                if (HomeManager.a().a(this.e, trim)) {
                    this.c.setEnabled(false);
                    new MLAlertDialog.Builder(this).b((int) R.string.tag_name_duplicate).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HomeRoomEditorActivity.this.d.setSelection(0, trim.length());
                        }
                    }).d();
                    return;
                }
                if (TextUtils.isEmpty(this.e.d())) {
                    HomeManager.a().a(trim, (List<String>) arrayList, this.n, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                        public void a() {
                            HomeRoomEditorActivity.this.finish();
                        }

                        public void a(int i, Error error) {
                            if (error == null || error.a() != -35) {
                                ToastUtil.a((int) R.string.add_failed);
                            } else {
                                ToastUtil.a((int) R.string.name_repeat);
                            }
                        }
                    });
                    a(a2);
                } else {
                    final String e3 = this.e.e();
                    final String a3 = this.e.a();
                    this.e.e(trim);
                    this.e.a(this.n);
                    ArrayList arrayList2 = new ArrayList();
                    for (String next : this.l) {
                        if (!arrayList.contains(next)) {
                            arrayList2.add(next);
                        }
                    }
                    HomeManager.a().a(this.e, (List<String>) arrayList, (List<String>) arrayList2, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                        public void a() {
                            HomeRoomEditorActivity.this.finish();
                        }

                        public void a(int i, Error error) {
                            if (error == null || error.a() != -35) {
                                ToastUtil.a((int) R.string.add_failed);
                            } else {
                                ToastUtil.a((int) R.string.name_repeat);
                            }
                            HomeRoomEditorActivity.this.e.e(e3);
                            HomeRoomEditorActivity.this.e.a(a3);
                        }
                    });
                    a(a2);
                    StatHelper.h(trim);
                    if (getIntent().getBooleanExtra("result", false)) {
                        Intent intent = new Intent();
                        intent.putExtra("old_name", this.e.e());
                        intent.putExtra("new_name", trim);
                        setResult(-1, intent);
                    }
                }
                SmartHomeDeviceHelper.a().b().j();
                SmartHomeDeviceHelper.a().b().k();
            }
        } else {
            ToastUtil.a((int) R.string.exceed_room_max_count);
        }
    }

    private void a(Set<String> set) {
        if (set != null && !set.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (String l2 : set) {
                Device l3 = SmartHomeDeviceManager.a().l(l2);
                if (l3 != null && !l3.isShowMainList()) {
                    hashSet.add(l3.did);
                }
            }
            if (hashSet.size() > 0) {
                SmartHomeDeviceManager.a().a(true, (Set<String>) hashSet, (Context) this, (AsyncResponseCallback<Void>) null);
            }
        }
    }

    public void softInputToggle(boolean z) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (!inputMethodManager.isActive()) {
            return;
        }
        if (z) {
            inputMethodManager.showSoftInput(this.d, 0);
        } else {
            inputMethodManager.hideSoftInputFromWindow(this.d.getWindowToken(), 0);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        this.c.setEnabled(!TextUtils.isEmpty(this.d.getText().toString()));
    }

    private class RoomDeviceAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        List<DeviceTagRoom> f18075a = new ArrayList();

        public long getItemId(int i) {
            return 0;
        }

        public RoomDeviceAdapter(List<DeviceTagRoom> list) {
            if (HomeRoomEditorActivity.this.k) {
                for (int i = 0; i < list.size(); i++) {
                    DeviceTagRoom deviceTagRoom = list.get(i);
                    if (deviceTagRoom != null && deviceTagRoom.k == 0) {
                        this.f18075a.add(deviceTagRoom);
                    }
                }
                return;
            }
            this.f18075a = list;
        }

        public int getCount() {
            return this.f18075a.size();
        }

        public Set<String> a() {
            HashSet hashSet = new HashSet();
            if (this.f18075a != null && this.f18075a.size() > 0) {
                for (DeviceTagRoom next : this.f18075a) {
                    if (next.g != null && next.h) {
                        hashSet.add(next.g.did);
                    }
                }
            }
            return hashSet;
        }

        public Object getItem(int i) {
            if (i < 0 || i >= this.f18075a.size()) {
                return null;
            }
            return this.f18075a.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(HomeRoomEditorActivity.this).inflate(R.layout.tag_child_item_editor_select, viewGroup, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.a(i, (DeviceTagRoom) getItem(i));
            return view;
        }

        class ViewHolder {
            /* access modifiers changed from: private */
            public CheckBox b;
            private TextView c;
            private TextView d;
            private SimpleDraweeView e;
            private View f;
            private final Drawable g = SHApplication.getAppContext().getResources().getDrawable(R.drawable.tag_normal_checkbox_layout);
            private final Drawable h = SHApplication.getAppContext().getResources().getDrawable(R.drawable.tag_other_checkbox_layout);

            public ViewHolder(View view) {
                view.findViewById(R.id.root_item).setOnClickListener(new View.OnClickListener(RoomDeviceAdapter.this) {
                    public void onClick(View view) {
                        ViewHolder.this.b.setChecked(!ViewHolder.this.b.isChecked());
                    }
                });
                this.e = (SimpleDraweeView) view.findViewById(R.id.icon);
                this.b = (CheckBox) view.findViewById(R.id.select_check);
                this.c = (TextView) view.findViewById(R.id.title);
                this.d = (TextView) view.findViewById(R.id.room);
                this.f = view.findViewById(R.id.divider_item);
            }

            public void a(int i, final DeviceTagRoom deviceTagRoom) {
                this.b.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
                this.c.setText(deviceTagRoom.d);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
                if (i == RoomDeviceAdapter.this.getCount() - 1) {
                    layoutParams.leftMargin = 0;
                } else {
                    layoutParams.leftMargin = DisplayUtils.a((Context) HomeRoomEditorActivity.this, 13.0f);
                }
                this.b.setButtonDrawable(this.g);
                if (deviceTagRoom.k == 0) {
                    this.d.setTextColor(HomeRoomEditorActivity.this.getResources().getColor(R.color.tag_defalut_name_bg));
                    this.d.setText(R.string.tag_recommend_defaultroom);
                } else if (deviceTagRoom.k == 1) {
                    this.d.setTextColor(HomeRoomEditorActivity.this.getResources().getColor(R.color.class_D));
                    this.d.setText(R.string.tag_recommend_added);
                } else if (deviceTagRoom.k == 2) {
                    this.d.setTextColor(HomeRoomEditorActivity.this.getResources().getColor(R.color.class_D));
                    TextView textView = this.d;
                    textView.setText(Operators.ARRAY_START_STR + deviceTagRoom.j.e() + Operators.ARRAY_END_STR);
                    this.b.setButtonDrawable(this.h);
                }
                this.b.setChecked(deviceTagRoom.h);
                if (deviceTagRoom.h && deviceTagRoom.g != null) {
                    HomeRoomEditorActivity.this.l.add(deviceTagRoom.g.did);
                }
                if (deviceTagRoom.g != null) {
                    DeviceFactory.b(deviceTagRoom.g.model, this.e);
                }
                if (HomeRoomEditorActivity.this.j) {
                    this.b.setVisibility(0);
                    this.d.setVisibility(0);
                    this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                            if (deviceTagRoom.k == 2 && ViewHolder.this.b.isChecked()) {
                                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(HomeRoomEditorActivity.this);
                                builder.a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ViewHolder.this.b.setChecked(true);
                                        deviceTagRoom.h = true;
                                    }
                                });
                                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ViewHolder.this.b.setChecked(false);
                                        deviceTagRoom.h = false;
                                    }
                                });
                                builder.a(false);
                                builder.b((CharSequence) String.format(HomeRoomEditorActivity.this.getString(R.string.tag_transfer_device), new Object[]{deviceTagRoom.j.e(), HomeRoomEditorActivity.this.d.getText()}));
                                builder.d();
                            }
                            deviceTagRoom.h = z;
                            HomeRoomEditorActivity.this.o.setText(SHApplication.getAppContext().getResources().getQuantityString(R.plurals.tag_selected_title, RoomDeviceAdapter.this.a().size()));
                            LocalBroadcastManager.getInstance(HomeRoomEditorActivity.this).sendBroadcast(new Intent("editor_changed_action"));
                        }
                    });
                    return;
                }
                this.b.setVisibility(8);
                this.d.setVisibility(8);
            }
        }
    }
}
