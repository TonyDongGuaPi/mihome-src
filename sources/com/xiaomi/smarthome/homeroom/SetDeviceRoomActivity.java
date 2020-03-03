package com.xiaomi.smarthome.homeroom;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.exoplayer2.C;
import com.mi.global.bbs.utils.DefaultTextWatcher;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.CommonFlowGroup;
import com.xiaomi.smarthome.multikey.PowerMultikeyApi;
import com.xiaomi.smarthome.newui.NameEditDialogHelper;
import com.xiaomi.smarthome.ui.DeviceBigHeaderView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SetDeviceRoomActivity extends BaseActivity {
    public static final String INTENT_KEY_DIDS = "device_list_id";
    public static final String INTENT_KEY_HOME_ID = "home_id";
    public static final String INTENT_KEY_ROOM_ID = "room_id";
    public static final String INTENT_KEY_START_FOR_RESULT = "start_for_result";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<String> f18207a;
    private ArrayList<Device> b = new ArrayList<>();
    private Home c;
    private String d;
    private XQProgressDialog e;
    /* access modifiers changed from: private */
    public List<String> f = new ArrayList();
    /* access modifiers changed from: private */
    public List<String> g = new ArrayList();
    /* access modifiers changed from: private */
    public List<String> h = new ArrayList();
    private MLAlertDialog i;
    private MLAlertDialog j;
    private boolean k;
    @BindView(2131433231)
    TextView mChooseRoom;
    @BindView(2131428504)
    Button mComplete;
    @BindView(2131428768)
    DeviceBigHeaderView mDeviceListContainer;
    @BindView(2131428907)
    TextView mDivider;
    @BindView(2131431774)
    CommonFlowGroup mRecommendFlowView;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131432050)
    CommonFlowGroup mRoomFlowView;

    public static void startActivity(Context context, String str, ArrayList<String> arrayList, int i2) {
        if (!TextUtils.isEmpty(str) && context != null && arrayList != null && !arrayList.isEmpty()) {
            Intent intent = new Intent(context, SetDeviceRoomActivity.class);
            intent.putExtra("home_id", str);
            intent.putStringArrayListExtra(INTENT_KEY_DIDS, arrayList);
            if (!(context instanceof Activity) || i2 < 0) {
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra(INTENT_KEY_START_FOR_RESULT, false);
                context.startActivity(intent);
                return;
            }
            intent.putExtra(INTENT_KEY_START_FOR_RESULT, true);
            ((Activity) context).startActivityForResult(intent, i2);
        }
    }

    private void a() {
        ArrayList arrayList = new ArrayList();
        Iterator<Device> it = this.b.iterator();
        while (it.hasNext()) {
            Device next = it.next();
            arrayList.add(new DeviceBigHeaderView.DeviceBigHeaderModel(next.model, next.name));
        }
        this.mDeviceListContainer.setModel(arrayList);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_set_device_room);
        ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        if (intent == null) {
            a(0);
            return;
        }
        this.f18207a = intent.getStringArrayListExtra(INTENT_KEY_DIDS);
        if (this.f18207a == null || this.f18207a.isEmpty()) {
            a(0);
            return;
        }
        Iterator<String> it = this.f18207a.iterator();
        while (it.hasNext()) {
            Device b2 = SmartHomeDeviceManager.a().b(it.next());
            if (b2 == null) {
                it.remove();
            } else {
                this.b.add(b2);
            }
        }
        if (this.b == null || this.b.isEmpty()) {
            a(0);
            return;
        }
        this.d = intent.getStringExtra("home_id");
        this.c = HomeManager.a().j(this.d);
        if (this.c == null) {
            a(0);
            return;
        }
        this.k = intent.getBooleanExtra(INTENT_KEY_START_FOR_RESULT, false);
        b();
    }

    public void onBackPressed() {
        a(0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.i != null && this.i.isShowing()) {
            this.i.dismiss();
        }
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
        }
        if (this.j != null && this.j.isShowing()) {
            this.j.dismiss();
        }
        super.onDestroy();
    }

    private void b() {
        a(false);
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SetDeviceRoomActivity.this.b(view);
            }
        });
        this.mComplete.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SetDeviceRoomActivity.this.a(view);
            }
        });
        c();
        a();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        g();
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        this.mComplete.setEnabled(z);
        this.mComplete.setAlpha(z ? 1.0f : 0.3f);
    }

    private void c() {
        this.mRecommendFlowView.showAddView(false);
        this.mRoomFlowView.setOnTagClickListener(new CommonFlowGroup.TagClickListener() {
            public void a() {
                SetDeviceRoomActivity.this.b(SetDeviceRoomActivity.this.mRecommendFlowView.getVisibility() != 0);
            }

            public void a(int i) {
                SetDeviceRoomActivity.this.a(true);
            }
        });
        this.mRoomFlowView.setOnTagCreateListener(new CommonFlowGroup.TagCreateListener() {
            public View onAddCreate(Context context) {
                return LayoutInflater.from(context).inflate(R.layout.common_flow_tag_add_item, (ViewGroup) null);
            }

            public TextView onTagCreate(Context context, int i) {
                return (TextView) LayoutInflater.from(context).inflate(R.layout.common_flow_tag_item_v3, (ViewGroup) null);
            }
        });
        this.mRecommendFlowView.setOnTagClickListener(new CommonFlowGroup.TagClickListener() {
            public void a() {
            }

            public void a(int i) {
                if (SetDeviceRoomActivity.this.h.get(i) != null) {
                    SetDeviceRoomActivity.this.g.add(0, SetDeviceRoomActivity.this.h.get(i));
                    SetDeviceRoomActivity.this.d();
                    SetDeviceRoomActivity.this.mRecommendFlowView.setSelectIndex(-1);
                    SetDeviceRoomActivity.this.mRoomFlowView.setSelectIndex(0);
                    SetDeviceRoomActivity.this.a(true);
                }
            }
        });
        this.mRecommendFlowView.setOnTagCreateListener(new CommonFlowGroup.TagCreateListener() {
            public View onAddCreate(Context context) {
                return LayoutInflater.from(context).inflate(R.layout.common_flow_tag_add_item, (ViewGroup) null);
            }

            public TextView onTagCreate(Context context, int i) {
                return (TextView) LayoutInflater.from(context).inflate(R.layout.common_flow_tag_item_v2, (ViewGroup) null);
            }
        });
        d();
        Room q = HomeManager.a().q();
        if (q == null) {
            this.mRoomFlowView.setSelectIndex(-1);
        } else {
            int i2 = 0;
            while (true) {
                if (i2 >= this.f.size()) {
                    break;
                } else if (this.f.get(i2).equals(q.e())) {
                    this.mRoomFlowView.setSelectIndex(i2);
                    a(true);
                    break;
                } else {
                    i2++;
                }
            }
        }
        if (this.f.size() > 0) {
            this.mChooseRoom.setVisibility(0);
            this.mDivider.setVisibility(8);
            this.mRecommendFlowView.setVisibility(8);
            return;
        }
        this.mChooseRoom.setVisibility(8);
        this.mDivider.setVisibility(0);
        this.mRecommendFlowView.setVisibility(0);
        this.mRecommendFlowView.setData(this.h);
    }

    /* access modifiers changed from: private */
    public void d() {
        e();
        this.mRoomFlowView.setData(this.f);
        this.mRecommendFlowView.setData(this.h);
    }

    private void e() {
        List<Room> d2 = this.c.d();
        ArrayList arrayList = new ArrayList();
        for (Room e2 : d2) {
            arrayList.add(e2.e());
        }
        this.g.removeAll(arrayList);
        arrayList.addAll(0, this.g);
        this.f = arrayList;
        List<String> a2 = PowerMultikeyApi.a();
        a2.removeAll(this.f);
        this.h = a2;
    }

    /* access modifiers changed from: private */
    public void b(boolean z) {
        this.j = NameEditDialogHelper.a(this, "", getString(R.string.tag_add_title), getString(R.string.input_tag_name_hint), new NameEditDialogHelper.NameEditListener() {
            public void a(String str) {
                String trim = str == null ? "" : str.trim();
                if (SetDeviceRoomActivity.this.f.contains(trim)) {
                    SetDeviceRoomActivity.this.mRoomFlowView.setSelectIndex(SetDeviceRoomActivity.this.f.indexOf(trim));
                } else if (!TextUtils.isEmpty(trim)) {
                    SetDeviceRoomActivity.this.g.add(0, trim);
                    SetDeviceRoomActivity.this.d();
                    SetDeviceRoomActivity.this.mRecommendFlowView.setSelectIndex(-1);
                    SetDeviceRoomActivity.this.mRoomFlowView.setSelectIndex(0);
                    SetDeviceRoomActivity.this.a(true);
                }
            }

            public String b(String str) {
                if (str == null || !SetDeviceRoomActivity.this.f.contains(str.trim())) {
                    return null;
                }
                return SetDeviceRoomActivity.this.getString(R.string.room_name_duplicate);
            }
        });
        ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) this.j.getView();
        final CommonFlowGroup commonFlowGroup = clientRemarkInputView.getCommonFlowGroup();
        final EditText editText = clientRemarkInputView.getEditText();
        if (commonFlowGroup != null && editText != null && z) {
            commonFlowGroup.setVisibility(0);
            commonFlowGroup.setMinWidth(64.0f);
            commonFlowGroup.showAddView(false);
            commonFlowGroup.setOnTagCreateListener(new CommonFlowGroup.TagCreateListener() {
                public View onAddCreate(Context context) {
                    return null;
                }

                public TextView onTagCreate(Context context, int i) {
                    return (TextView) LayoutInflater.from(context).inflate(R.layout.common_flow_tag_item_v2, (ViewGroup) null);
                }
            });
            commonFlowGroup.setData(this.h);
            commonFlowGroup.setOnTagClickListener(new CommonFlowGroup.TagClickListener() {
                public void a() {
                }

                public void a(int i) {
                    String str;
                    if (i >= 0 && i < SetDeviceRoomActivity.this.h.size() && (str = (String) SetDeviceRoomActivity.this.h.get(i)) != null && !str.equals(editText.getText().toString())) {
                        editText.setText(str);
                        editText.setSelection(str.length());
                    }
                }
            });
            editText.addTextChangedListener(new DefaultTextWatcher() {
                public void afterTextChanged(Editable editable) {
                    int indexOf = SetDeviceRoomActivity.this.h.indexOf(editable.toString());
                    if (indexOf < 0) {
                        commonFlowGroup.setSelectIndex(-1);
                    } else if (indexOf != commonFlowGroup.getSelectIndex()) {
                        commonFlowGroup.setSelectIndex(indexOf);
                    }
                }
            });
            TextView titleRoomRecommend = clientRemarkInputView.getTitleRoomRecommend();
            if (titleRoomRecommend != null && this.h.size() > 0) {
                titleRoomRecommend.setVisibility(0);
                titleRoomRecommend.setText(R.string.multikey_room_recommend);
            }
        }
    }

    private void f() {
        this.i = new MLAlertDialog.Builder(this).b((int) R.string.set_room_fail).d(true).a((int) R.string.retry, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                SetDeviceRoomActivity.this.a(dialogInterface, i);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        g();
    }

    private void g() {
        int selectIndex = this.mRoomFlowView.getSelectIndex();
        if (selectIndex < 0 || this.f.get(selectIndex) == null) {
            h();
        } else {
            a(this.f.get(selectIndex));
        }
    }

    private void a(String str) {
        if (this.e == null || !this.e.isShowing()) {
            this.e = new XQProgressDialog(this);
            this.e.setCancelable(true);
            this.e.setMessage(getResources().getString(R.string.loading_share_info));
            this.e.show();
        }
        List<Room> d2 = this.c.d();
        ArrayList arrayList = new ArrayList();
        for (Room e2 : d2) {
            arrayList.add(e2.e());
        }
        if (arrayList.contains(str)) {
            int indexOf = arrayList.indexOf(str);
            Room room = null;
            if (indexOf >= 0 && indexOf < d2.size()) {
                room = d2.get(indexOf);
            }
            a(room);
            return;
        }
        b(str);
    }

    private void b(String str) {
        HomeManager.a().a(this.d, str, this.f18207a, (String) null, new HomeManager.IHomeOperationCallback() {
            public void a() {
                if (SetDeviceRoomActivity.this.isValid()) {
                    Room p = HomeManager.a().p((String) SetDeviceRoomActivity.this.f18207a.get(0));
                    if (p == null || TextUtils.isEmpty(p.d())) {
                        SetDeviceRoomActivity.this.h();
                    } else {
                        SetDeviceRoomActivity.this.c(p.d());
                    }
                }
            }

            public void a(int i, Error error) {
                if (SetDeviceRoomActivity.this.isValid()) {
                    SetDeviceRoomActivity.this.h();
                }
            }
        });
    }

    private void a(final Room room) {
        if (room == null || TextUtils.isEmpty(room.d())) {
            h();
        } else {
            HomeManager.a().a(room, (List<String>) this.f18207a, (List<String>) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                public void a() {
                    if (SetDeviceRoomActivity.this.isValid()) {
                        SetDeviceRoomActivity.this.c(room.d());
                    }
                }

                public void a(int i, Error error) {
                    if (SetDeviceRoomActivity.this.isValid()) {
                        SetDeviceRoomActivity.this.h();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
        }
        Intent intent = new Intent();
        intent.putStringArrayListExtra(INTENT_KEY_DIDS, this.f18207a);
        intent.putExtra("room_id", str);
        setResult(-1, intent);
        finish();
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
        }
        f();
    }

    private void a(int i2) {
        if (this.k) {
            setResult(i2, new Intent());
        }
        finish();
    }
}
