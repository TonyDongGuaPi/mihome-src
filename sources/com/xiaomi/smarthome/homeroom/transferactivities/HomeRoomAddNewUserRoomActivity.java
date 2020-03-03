package com.xiaomi.smarthome.homeroom.transferactivities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.RoomConfig;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.ExpandGridView;
import com.xiaomi.smarthome.library.common.widget.SingleWaveView;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeRoomAddNewUserRoomActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18340a = 10001;
    private static final int b = 10002;
    /* access modifiers changed from: private */
    public TagFlowLayout c;
    private ExpandGridView d;
    /* access modifiers changed from: private */
    public ArrayList<String> e = new ArrayList<>();
    /* access modifiers changed from: private */
    public LayoutInflater f;
    /* access modifiers changed from: private */
    public int g;
    /* access modifiers changed from: private */
    public Locale h;

    public void handleMessage(Message message) {
        if (message.what == 10002) {
            c();
        } else if (message.what == 10001) {
            d();
        }
        super.handleMessage(message);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_tag_add_new_user_room);
        a();
        b();
        this.h = CoreApi.a().I();
        if (this.h == null) {
            this.h = Locale.getDefault();
        }
    }

    private void a() {
        TitleBarUtil.b((Activity) this);
        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HomeRoomAddNewUserRoomActivity.this, HomeRoomAddNewUserDeviceActivity.class);
                intent.putStringArrayListExtra("extra_device_model", HomeRoomAddNewUserRoomActivity.this.e);
                HomeRoomAddNewUserRoomActivity.this.startActivity(intent);
                HomeRoomAddNewUserRoomActivity.this.finish();
            }
        });
        this.c = (TagFlowLayout) findViewById(R.id.room_list);
        this.d = (ExpandGridView) findViewById(R.id.room_recommend_list);
        this.d.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
                /*
                    r0 = this;
                    com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity r2 = com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity.this
                    java.util.ArrayList r2 = r2.e
                    int r2 = r2.size()
                    r4 = 50
                    if (r2 < r4) goto L_0x001b
                    com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity r1 = com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity.this
                    r2 = 2131499575(0x7f0c1a37, float:1.8622803E38)
                    java.lang.String r1 = r1.getString(r2)
                    com.xiaomi.smarthome.library.common.util.ToastUtil.a((java.lang.CharSequence) r1)
                    return
                L_0x001b:
                    android.widget.Adapter r1 = r1.getAdapter()
                    java.lang.Object r1 = r1.getItem(r3)
                    com.xiaomi.smarthome.homeroom.model.RoomConfig r1 = (com.xiaomi.smarthome.homeroom.model.RoomConfig) r1
                    com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity r2 = com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity.this
                    java.util.Locale r2 = r2.h
                    java.lang.String r2 = r1.a((java.util.Locale) r2)
                    com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity r3 = com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity.this
                    r4 = 2131499553(0x7f0c1a21, float:1.8622759E38)
                    java.lang.String r3 = r3.getString(r4)
                    boolean r2 = android.text.TextUtils.equals(r2, r3)
                    if (r2 == 0) goto L_0x0044
                    com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity r1 = com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity.this
                    r1.c()
                    goto L_0x00ab
                L_0x0044:
                    com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity r2 = com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity.this
                    java.util.Locale r2 = r2.h
                    java.lang.String r1 = r1.a((java.util.Locale) r2)
                    com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity r2 = com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity.this
                    java.util.ArrayList r2 = r2.e
                    boolean r2 = r2.contains(r1)
                    if (r2 == 0) goto L_0x0089
                    com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity r2 = com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity.this
                    java.util.ArrayList r2 = r2.e
                    java.util.Iterator r2 = r2.iterator()
                    r3 = 1
                L_0x0065:
                    boolean r4 = r2.hasNext()
                    if (r4 == 0) goto L_0x007a
                    java.lang.Object r4 = r2.next()
                    java.lang.String r4 = (java.lang.String) r4
                    boolean r4 = r4.contains(r1)
                    if (r4 == 0) goto L_0x0065
                    int r3 = r3 + 1
                    goto L_0x0065
                L_0x007a:
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    r2.append(r1)
                    r2.append(r3)
                    java.lang.String r1 = r2.toString()
                L_0x0089:
                    com.xiaomi.smarthome.device.SmartHomeDeviceHelper r2 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
                    com.xiaomi.smarthome.device.utils.DeviceTagInterface r2 = r2.b()
                    r3 = 0
                    r2.a((java.lang.String) r1, (java.util.Set<java.lang.String>) r3)
                    com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity r2 = com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity.this
                    java.util.ArrayList r2 = r2.e
                    r2.add(r1)
                    com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity r1 = com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity.this
                    com.zhy.view.flowlayout.TagFlowLayout r1 = r1.c
                    com.zhy.view.flowlayout.TagAdapter r1 = r1.getAdapter()
                    r1.c()
                L_0x00ab:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.transferactivities.HomeRoomAddNewUserRoomActivity.AnonymousClass2.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
            }
        });
        this.f = LayoutInflater.from(this);
    }

    private void b() {
        List<RoomConfig> A = ((DeviceTagManager) SmartHomeDeviceHelper.a().b()).A();
        HashMap hashMap = new HashMap();
        hashMap.put("en_US", getString(R.string.tag_recommend_custom));
        hashMap.put("es_ES", getString(R.string.tag_recommend_custom));
        hashMap.put("ko_KR", getString(R.string.tag_recommend_custom));
        hashMap.put("ru_RU", getString(R.string.tag_recommend_custom));
        hashMap.put("zh_CN", getString(R.string.tag_recommend_custom));
        hashMap.put("zh_HK", getString(R.string.tag_recommend_custom));
        hashMap.put("zh_TW", getString(R.string.tag_recommend_custom));
        A.add(new RoomConfig(hashMap, 0, 0, ""));
        RoomRecommendAdapter roomRecommendAdapter = new RoomRecommendAdapter();
        roomRecommendAdapter.a(A);
        this.d.setAdapter(roomRecommendAdapter);
        Map<String, Set<String>> a2 = SmartHomeDeviceHelper.a().b().a(4);
        if (!a2.isEmpty()) {
            this.e.clear();
            this.e.addAll(a2.keySet());
        }
        this.g = this.e.size();
        this.c.setAdapter(new TagAdapter<String>(this.e) {
            public View a(FlowLayout flowLayout, int i, String str) {
                SingleWaveView.DeletableTextView deletableTextView = new SingleWaveView.DeletableTextView(HomeRoomAddNewUserRoomActivity.this);
                deletableTextView.setText(str);
                deletableTextView.setMaxWidth(DisplayUtils.a(326.0f));
                deletableTextView.setDeletable(i >= HomeRoomAddNewUserRoomActivity.this.g);
                deletableTextView.setOnTextClearListener(new SingleWaveView.DeletableTextView.OnTextClearListener() {
                    public void a(SingleWaveView.DeletableTextView deletableTextView) {
                        HomeRoomAddNewUserRoomActivity.this.e.remove(deletableTextView.getText().toString());
                        SmartHomeDeviceHelper.a().b().a(deletableTextView.getText().toString());
                        HomeRoomAddNewUserRoomActivity.this.c.getAdapter().c();
                    }
                });
                return deletableTextView;
            }
        });
        this.c.setClickable(false);
    }

    /* access modifiers changed from: private */
    public void c() {
        final Pattern compile = Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z0-9 ]+$");
        final DeviceTagInterface<Device> b2 = SmartHomeDeviceHelper.a().b();
        final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) this.f.inflate(R.layout.client_remark_input_view, (ViewGroup) null);
        MLAlertDialog b3 = new MLAlertDialog.Builder(this).a((int) R.string.tag_add_title).b((View) clientRemarkInputView).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String trim = clientRemarkInputView.getEditText().getText().toString().trim();
                if (!HomeManager.A(trim)) {
                    ToastUtil.a((int) R.string.room_name_too_long);
                } else if (!compile.matcher(trim).matches() || StringUtil.t(trim) || TextUtils.isEmpty(trim)) {
                    new MLAlertDialog.Builder(HomeRoomAddNewUserRoomActivity.this).a((CharSequence) String.format(HomeRoomAddNewUserRoomActivity.this.getString(R.string.tag_save_data_title), new Object[]{trim})).b((int) R.string.tag_save_data_description).c((int) R.string.tag_roger, (DialogInterface.OnClickListener) null).d();
                } else if (b2.a(4, trim)) {
                    HomeRoomAddNewUserRoomActivity.this.mHandler.sendEmptyMessage(10001);
                } else {
                    b2.a(trim, (Set<String>) null);
                    HomeRoomAddNewUserRoomActivity.this.e.add(trim.trim());
                    HomeRoomAddNewUserRoomActivity.this.c.getAdapter().c();
                }
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b();
        clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) null, b3, (String) null, getString(R.string.input_tag_name_hint), false);
        clientRemarkInputView.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        b3.show();
        final Button button = b3.getButton(-1);
        button.setEnabled(false);
        button.setTextColor(getResources().getColor(R.color.std_list_subtitle));
        clientRemarkInputView.getEditText().addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                clientRemarkInputView.setAlertText("");
                button.setEnabled(true);
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                int i4;
                if (charSequence.length() > 0) {
                    Matcher matcher = compile.matcher(charSequence);
                    clientRemarkInputView.setAlertText(matcher.matches() ? "" : HomeRoomAddNewUserRoomActivity.this.getString(R.string.tag_save_data_desc));
                    button.setEnabled(matcher.matches());
                    Button button = button;
                    if (matcher.matches()) {
                        i4 = HomeRoomAddNewUserRoomActivity.this.getResources().getColor(R.color.std_dialog_button_green);
                    } else {
                        i4 = HomeRoomAddNewUserRoomActivity.this.getResources().getColor(R.color.std_list_subtitle);
                    }
                    button.setTextColor(i4);
                    return;
                }
                button.setEnabled(false);
                button.setTextColor(HomeRoomAddNewUserRoomActivity.this.getResources().getColor(R.color.std_list_subtitle));
            }
        });
    }

    private void d() {
        new MLAlertDialog.Builder(this).b((int) R.string.tag_name_duplicate).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                HomeRoomAddNewUserRoomActivity.this.mHandler.sendEmptyMessage(10002);
            }
        }).d();
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        SimpleDraweeView f18349a;
        TextView b;

        ViewHolder() {
        }
    }

    private class RoomRecommendAdapter extends BaseAdapter {
        private List<RoomConfig> b;

        public long getItemId(int i) {
            return (long) i;
        }

        private RoomRecommendAdapter() {
        }

        public void a(List<RoomConfig> list) {
            this.b = list;
        }

        public int getCount() {
            if (this.b == null) {
                return 0;
            }
            return this.b.size();
        }

        /* renamed from: a */
        public RoomConfig getItem(int i) {
            if (this.b == null || this.b.size() <= 0) {
                return null;
            }
            return this.b.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = HomeRoomAddNewUserRoomActivity.this.f.inflate(R.layout.device_tag_item_recommend_grid, viewGroup, false);
                viewHolder.f18349a = (SimpleDraweeView) view2.findViewById(R.id.image);
                viewHolder.b = (TextView) view2.findViewById(R.id.name);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.b.setText(this.b.get(i).a(HomeRoomAddNewUserRoomActivity.this.h));
            if (!TextUtils.isEmpty(this.b.get(i).a())) {
                viewHolder.f18349a.setImageURI(Uri.parse(this.b.get(i).a()));
            }
            return view2;
        }
    }
}
