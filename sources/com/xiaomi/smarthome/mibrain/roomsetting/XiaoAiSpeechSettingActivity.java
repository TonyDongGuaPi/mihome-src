package com.xiaomi.smarthome.mibrain.roomsetting;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.mibrain.roomsetting.model.ControllableDevice;
import com.xiaomi.smarthome.mibrain.roomsetting.model.XiaoAiRoomItem;
import com.xiaomi.smarthome.mibrain.roomsetting.model.XiaoAiVoiceCategory;
import com.xiaomi.smarthome.ui.DeviceBigHeaderView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XiaoAiSpeechSettingActivity extends BaseActivity {
    public static final String INTENT_KEY_DIDS_LIST = "dids_list_id";
    public static final String INTENT_KEY_HOME_ID = "home_id";
    public static final String INTENT_KEY_INTENT_ID = "intent_id";
    public static final String INTENT_KEY_ROOM_ID = "room_id";

    /* renamed from: a  reason: collision with root package name */
    private static final String f10686a = "XiaoAiSpeechSettingActivity";
    private Home b;
    /* access modifiers changed from: private */
    public Room c;
    /* access modifiers changed from: private */
    public List<String> d;
    private String e;
    /* access modifiers changed from: private */
    public boolean f = true;
    private XiaoAiVoiceCategory g;
    private XiaoAiRoomItem h;
    private DeviceBigHeaderView i;
    private ViewGroup j;
    private View k;
    private View l;
    private TextView m;
    private RecyclerView n;
    /* access modifiers changed from: private */
    public CustomAdapter o;
    private View p;
    private View q;
    private XQProgressDialog r;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        setContentView(R.layout.activity_xiaoai_speech_setting_layout);
        a();
        a(intent);
    }

    private void a() {
        this.i = (DeviceBigHeaderView) findViewById(R.id.device_big_header_view);
        this.m = (TextView) findViewById(R.id.home_room_name_tv);
        this.n = (RecyclerView) findViewById(R.id.recyclerview);
        this.n.setLayoutManager(new LinearLayoutManager(this));
        this.p = findViewById(R.id.common_white_empty_view);
        this.j = (ViewGroup) findViewById(R.id.speech_list_container);
        this.k = findViewById(R.id.auto_select_container);
        this.l = findViewById(R.id.manually_select_container);
        this.q = findViewById(R.id.multi_xiaoai_tips_tv);
        ((ImageView) this.p.findViewById(R.id.empty_icon)).setImageResource(R.drawable.emptypage_icon_nodevice_nor_1);
        ((TextView) this.p.findViewById(R.id.common_white_empty_text)).setText(R.string.no_device_in_room);
        b();
        ((TextView) findViewById(R.id.speech_list_header).findViewById(R.id.name_tv)).setText(R.string.when_speaking_to_xiaoai);
        ((TextView) findViewById(R.id.device_list_header).findViewById(R.id.name_tv)).setText(R.string.execute_actions_to_devices);
    }

    private void b() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.mi_brain_most_used_speech_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (XiaoAiSpeechSettingActivity.this.o != null && XiaoAiSpeechSettingActivity.this.o.f) {
                    XiaoAiSpeechSettingActivity.this.o.a();
                }
                XiaoAiSpeechSettingActivity.this.finish();
            }
        });
    }

    private void a(Intent intent) {
        this.b = HomeManager.a().j(intent.getStringExtra("home_id"));
        this.c = HomeManager.a().i(intent.getStringExtra("room_id"));
        this.d = intent.getStringArrayListExtra("dids_list_id");
        this.f = this.h == null ? true : this.h.a(this.g);
        this.e = intent.getStringExtra(INTENT_KEY_INTENT_ID);
        e();
        c();
        a(this.f);
        d();
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (this.o == null || this.o.getItemCount() != 0) {
            this.k.setVisibility(0);
            this.l.setVisibility(0);
            if (z) {
                this.k.findViewById(R.id.auto_select_indicator).setVisibility(0);
                this.l.findViewById(R.id.manually_select_indicator).setVisibility(4);
            } else {
                this.k.findViewById(R.id.auto_select_indicator).setVisibility(4);
                this.l.findViewById(R.id.manually_select_indicator).setVisibility(0);
            }
            this.k.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!XiaoAiSpeechSettingActivity.this.f && XiaoAiSpeechSettingActivity.this.o != null) {
                        XiaoAiSpeechSettingActivity.this.o.a(true);
                    }
                    boolean unused = XiaoAiSpeechSettingActivity.this.f = true;
                    XiaoAiSpeechSettingActivity.this.a(XiaoAiSpeechSettingActivity.this.f);
                }
            });
            this.l.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (XiaoAiSpeechSettingActivity.this.f && XiaoAiSpeechSettingActivity.this.o != null) {
                        XiaoAiSpeechSettingActivity.this.o.a(true);
                    }
                    boolean unused = XiaoAiSpeechSettingActivity.this.f = false;
                    XiaoAiSpeechSettingActivity.this.a(XiaoAiSpeechSettingActivity.this.f);
                }
            });
            if (this.o != null) {
                this.o.notifyDataSetChanged();
                return;
            }
            return;
        }
        this.k.setVisibility(8);
        this.l.setVisibility(8);
    }

    private void c() {
        List<String> c2;
        if (this.g != null && (c2 = this.g.c()) != null && !c2.isEmpty()) {
            for (int i2 = 0; i2 < c2.size(); i2++) {
                String str = c2.get(i2);
                if (!TextUtils.isEmpty(str)) {
                    View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.xiaoai_speech_list_item, this.j, false);
                    ((TextView) inflate.findViewById(R.id.speech_tv)).setText("“" + str + "”");
                    this.j.addView(inflate);
                }
            }
            this.j.requestLayout();
        }
    }

    private void d() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            Device b2 = SmartHomeDeviceManager.a().b(this.d.get(i2));
            if (b2 != null) {
                arrayList.add(new DeviceBigHeaderView.DeviceBigHeaderModel(b2.model, b2.name));
            }
        }
        this.i.setModel(arrayList);
        TextView textView = this.m;
        StringBuilder sb = new StringBuilder();
        sb.append(this.b.k());
        sb.append(" | ");
        sb.append(this.c == null ? getResources().getString(R.string.default_room) : this.c.e());
        textView.setText(sb.toString());
        if (arrayList.size() > 1) {
            this.q.setVisibility(0);
        } else {
            this.q.setVisibility(8);
        }
    }

    private void e() {
        if (this.d.size() > 0) {
            g();
            XiaoAiRoomSettingManager.a().a(this.c, (AsyncCallback<List<XiaoAiRoomItem>, Error>) new AsyncCallback<List<XiaoAiRoomItem>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<XiaoAiRoomItem> list) {
                    XiaoAiSpeechSettingActivity.this.a(list);
                }

                public void onFailure(Error error) {
                    XiaoAiSpeechSettingActivity.this.h();
                }
            });
        }
    }

    private void f() {
        this.p.setVisibility(0);
        this.n.setVisibility(8);
        this.k.setVisibility(8);
        this.l.setVisibility(8);
        ViewGroup.LayoutParams layoutParams = this.p.findViewById(R.id.empty_icon).getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) layoutParams).topMargin = DisplayUtils.a(42.0f);
        }
        ViewGroup.LayoutParams layoutParams2 = this.p.findViewById(R.id.common_white_empty_text).getLayoutParams();
        if (layoutParams2 instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) layoutParams2).bottomMargin = DisplayUtils.a(29.0f);
        }
    }

    /* access modifiers changed from: private */
    public void a(List<XiaoAiRoomItem> list) {
        h();
        if (list != null) {
            int i2 = 0;
            while (true) {
                if (i2 < list.size()) {
                    XiaoAiRoomItem xiaoAiRoomItem = list.get(i2);
                    if (xiaoAiRoomItem != null && TextUtils.equals(xiaoAiRoomItem.b(), this.e)) {
                        this.h = xiaoAiRoomItem;
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
            if (this.h == null) {
                this.h = new XiaoAiRoomItem();
            }
            this.h.b(this.e);
            List<XiaoAiVoiceCategory> a2 = XiaoAiRoomSettingManager.a().a(this.c);
            if (a2 == null || a2.isEmpty()) {
                String str = f10686a;
                LogUtilGrey.a(str, "empty XiaoAiVoiceCategory list for " + this.c.e());
                f();
                return;
            }
            this.g = new XiaoAiVoiceCategory();
            this.g.a(this.h.b());
            int i3 = 0;
            while (true) {
                if (i3 >= a2.size()) {
                    break;
                }
                XiaoAiVoiceCategory xiaoAiVoiceCategory = a2.get(i3);
                if (xiaoAiVoiceCategory != null && TextUtils.equals(xiaoAiVoiceCategory.a(), this.e)) {
                    this.g = xiaoAiVoiceCategory;
                    break;
                }
                i3++;
            }
            if (this.g == null) {
                String str2 = f10686a;
                LogUtilGrey.a(str2, "XiaoAiVoiceCategory not found for " + this.c.e());
                f();
                return;
            }
            this.o = new CustomAdapter(this.h, this.g);
            boolean z = true;
            this.o.setHasStableIds(true);
            if (this.o.getItemCount() > 0) {
                this.p.setVisibility(8);
                this.n.setVisibility(0);
                this.n.setAdapter(this.o);
                runOnUiThread(new Runnable() {
                    public void run() {
                        XiaoAiSpeechSettingActivity.this.o.notifyDataSetChanged();
                    }
                });
            } else {
                f();
            }
            if (this.h != null) {
                z = this.h.a(this.g);
            }
            this.f = z;
            c();
            a(this.f);
            d();
        }
    }

    private void g() {
        h();
        this.r = XQProgressDialog.a(this, (CharSequence) null, getString(R.string.loading));
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.r != null && this.r.isShowing()) {
            this.r.dismiss();
        }
    }

    public void onBackPressed() {
        i();
        super.onBackPressed();
    }

    private void i() {
        if (this.o != null) {
            this.o.a();
        }
    }

    private class CustomAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private List<ControllableDevice> b;
        /* access modifiers changed from: private */
        public Set<ControllableDevice> c;
        private XiaoAiRoomItem d;
        /* access modifiers changed from: private */
        public XiaoAiVoiceCategory e;
        /* access modifiers changed from: private */
        public boolean f = true;

        public CustomAdapter(XiaoAiRoomItem xiaoAiRoomItem, XiaoAiVoiceCategory xiaoAiVoiceCategory) {
            this.d = xiaoAiRoomItem;
            this.e = xiaoAiVoiceCategory;
            List<ControllableDevice> c2 = this.d == null ? null : xiaoAiRoomItem.c();
            this.c = new HashSet();
            this.b = xiaoAiVoiceCategory.e() == null ? new ArrayList<>() : xiaoAiVoiceCategory.e();
            if (c2 != null) {
                for (int i = 0; i < c2.size(); i++) {
                    ControllableDevice controllableDevice = c2.get(i);
                    if (controllableDevice != null && this.b.contains(controllableDevice)) {
                        this.c.add(controllableDevice);
                    }
                }
            }
        }

        public void a() {
            if (this.f) {
                XiaoAiRoomItem xiaoAiRoomItem = new XiaoAiRoomItem();
                xiaoAiRoomItem.a(XiaoAiSpeechSettingActivity.this.c.d());
                xiaoAiRoomItem.b(this.e.a());
                xiaoAiRoomItem.a(XiaoAiSpeechSettingActivity.this.f);
                xiaoAiRoomItem.a((List<ControllableDevice>) XiaoAiSpeechSettingActivity.this.f ? null : new ArrayList(this.c));
                XiaoAiRoomSettingManager.a().a((List<String>) XiaoAiSpeechSettingActivity.this.d, xiaoAiRoomItem, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                    }

                    public void onFailure(Error error) {
                    }
                });
            }
        }

        public void a(boolean z) {
            this.f = z;
        }

        @NonNull
        /* renamed from: a */
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(XiaoAiSpeechSettingActivity.this.getApplicationContext()).inflate(R.layout.item_device_icon_select, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            ControllableDevice controllableDevice;
            if (i >= 0 && i < this.b.size() && (controllableDevice = this.b.get(i)) != null) {
                myViewHolder.a(controllableDevice);
            }
        }

        public int getItemCount() {
            return this.b.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            /* access modifiers changed from: private */
            public CheckBox b;
            private TextView c;
            private TextView d;
            private SimpleDraweeView e;
            private View f;
            private Drawable g = SHApplication.getAppContext().getResources().getDrawable(R.drawable.tag_normal_checkbox_layout);
            private Drawable h = SHApplication.getAppContext().getResources().getDrawable(R.drawable.tag_other_checkbox_layout);

            public MyViewHolder(View view) {
                super(view);
                view.findViewById(R.id.root_item).setOnClickListener(new View.OnClickListener(CustomAdapter.this) {
                    public void onClick(View view) {
                        MyViewHolder.this.b.setChecked(!MyViewHolder.this.b.isChecked());
                    }
                });
                this.e = (SimpleDraweeView) view.findViewById(R.id.icon);
                this.b = (CheckBox) view.findViewById(R.id.select_check);
                this.c = (TextView) view.findViewById(R.id.title);
                this.d = (TextView) view.findViewById(R.id.room);
                this.d.setVisibility(8);
                this.f = view.findViewById(R.id.divider_item);
            }

            public void a(final ControllableDevice controllableDevice) {
                this.b.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
                this.c.setText(ControllableDevice.a(controllableDevice));
                this.b.setButtonDrawable(this.g);
                this.b.setChecked(CustomAdapter.this.c.contains(controllableDevice));
                Device b2 = SmartHomeDeviceManager.a().b(controllableDevice.a());
                if (b2 != null) {
                    DeviceFactory.b(b2.model, this.e);
                }
                if (XiaoAiSpeechSettingActivity.this.f) {
                    this.e.setAlpha(0.5f);
                    this.c.setAlpha(0.5f);
                    this.b.setVisibility(4);
                    return;
                }
                this.e.setAlpha(1.0f);
                this.c.setAlpha(1.0f);
                this.b.setVisibility(0);
                this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        if (z) {
                            if (CustomAdapter.this.e.b() == 1) {
                                CustomAdapter.this.c.clear();
                            }
                            CustomAdapter.this.c.add(controllableDevice);
                            CustomAdapter.this.a(true);
                        } else if (CustomAdapter.this.c.size() == 1) {
                            if (CustomAdapter.this.e.b() == 1) {
                                ToastUtil.a((int) R.string.must_select_one_device_for_single_select);
                            } else {
                                ToastUtil.a((int) R.string.must_select_one_device_for_multi_select);
                            }
                        } else if (CustomAdapter.this.c.size() > 1) {
                            CustomAdapter.this.c.remove(controllableDevice);
                            CustomAdapter.this.a(true);
                        }
                        CustomAdapter.this.notifyDataSetChanged();
                    }
                });
            }
        }
    }
}
