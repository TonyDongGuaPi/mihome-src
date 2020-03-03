package com.xiaomi.smarthome.multikey;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.utils.DefaultTextWatcher;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.initdevice.RecommendDeviceNameApi;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.FontManager;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.CommonFlowGroup;
import com.xiaomi.smarthome.library.common.widget.CornerDrawable;
import com.xiaomi.smarthome.newui.NameEditDialogHelper;
import java.util.ArrayList;
import java.util.List;

public class PowerItemkeyActivity extends BaseActivity implements View.OnClickListener, CommonFlowGroup.TagCreateListener {
    public static final String INTENT_KEY_DEVICE_NAME = "device_name";
    public static final String INTENT_KEY_DID = "device_id";
    public static final String INTENT_KEY_MAC = "device_mac";
    public static final String INTENT_KEY_ROOM_NAME = "device_room";

    /* renamed from: a  reason: collision with root package name */
    private static final String f20164a = "lumi.ctrl_ln2.aq1";
    private Device b;
    /* access modifiers changed from: private */
    public List<String> c = new ArrayList();
    /* access modifiers changed from: private */
    public List<String> d = new ArrayList();
    private XQProgressDialog e;
    @BindView(2131429057)
    EditText etKeyname;
    private PowerMultikeyBean f;
    private PowerMultikeyApi g = new PowerMultikeyApi();
    @BindView(2131428383)
    TextView inputRemarkView;
    @BindView(2131431769)
    CommonFlowGroup mDeviceFlowView;
    @BindView(2131431773)
    View mRecommendNoimg;
    @BindView(2131430969)
    ImageView mReturnBtn;
    @BindView(2131430982)
    ImageView mRightImage;
    @BindView(2131431774)
    CommonFlowGroup mRoomFlowView;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430975)
    TextView mTitleTv;
    @BindView(2131428381)
    View mViewClear;
    @BindView(2131433471)
    TextView tvRoomadd;
    @BindView(2131433369)
    TextView tv_keyname;
    @BindView(2131433472)
    TextView tv_roomname;

    public View onAddCreate(Context context) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_poweritemkey);
        ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("device_id");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.b = SmartHomeDeviceManager.a().b(stringExtra);
        }
        this.f = (PowerMultikeyBean) intent.getParcelableExtra(PowerMultikeyActivity.EXTRA_ENTITY);
        if (this.f == null) {
            finish();
            return;
        }
        if (this.b == null) {
            String stringExtra2 = intent.getStringExtra("device_mac");
            if (!TextUtils.isEmpty(stringExtra2)) {
                this.b = SmartHomeDeviceManager.a().f(stringExtra2);
            }
        }
        if (this.b == null) {
            finish();
        } else if (!SmartHomeDeviceManager.a().u() || this.b != null) {
            a(intent);
            HomeManager.a().a((HomeManager.IHomeOperationCallback) null);
        } else {
            finish();
        }
    }

    private void a(Intent intent) {
        Typeface a2 = FontManager.a("fonts/MI-LANTING--GBK1-Thin.ttf");
        this.tv_keyname.setTypeface(a2);
        this.tv_roomname.setTypeface(a2);
        this.mRightImage.setImageResource(R.drawable.std_titlebar_icon_confirm);
        this.mReturnBtn.setImageResource(R.drawable.close_icon_black);
        this.mRightImage.setOnClickListener(this);
        this.mViewClear.setOnClickListener(this);
        this.mRightImage.setVisibility(0);
        this.mReturnBtn.setOnClickListener(this);
        this.etKeyname.setFilters(new InputFilter[]{new InputFilter.LengthFilter(41)});
        this.etKeyname.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (StringUtil.u(PowerItemkeyActivity.this.etKeyname.getText().toString())) {
                    ToastUtil.a((int) R.string.tag_save_data_description);
                    PowerItemkeyActivity.this.mRightImage.setVisibility(8);
                    return;
                }
                PowerItemkeyActivity.this.mRightImage.setVisibility(0);
            }

            public void afterTextChanged(Editable editable) {
                String obj = editable.toString();
                int indexOf = PowerItemkeyActivity.this.c.indexOf(obj);
                if (indexOf < 0) {
                    PowerItemkeyActivity.this.mDeviceFlowView.setSelectIndex(-1);
                } else if (indexOf != PowerItemkeyActivity.this.mDeviceFlowView.getSelectIndex()) {
                    PowerItemkeyActivity.this.mDeviceFlowView.setSelectIndex(indexOf);
                }
                if (HomeManager.A(obj)) {
                    PowerItemkeyActivity.this.inputRemarkView.setVisibility(8);
                    return;
                }
                PowerItemkeyActivity.this.inputRemarkView.setText(R.string.room_name_too_long);
                PowerItemkeyActivity.this.inputRemarkView.setVisibility(0);
            }
        });
        if (!TextUtils.isEmpty(this.f.d)) {
            this.etKeyname.setText(this.f.d);
            this.etKeyname.setSelection(this.f.d.length());
        }
        this.mTitleTv.setText(intent.getStringExtra("extra_title"));
        this.d.addAll(PowerMultikeyApi.b());
        if (this.d.size() == 0) {
            this.mRoomFlowView.setVisibility(8);
            this.mRecommendNoimg.setVisibility(0);
        } else {
            this.mRoomFlowView.setVisibility(0);
            this.mRecommendNoimg.setVisibility(8);
            this.mRoomFlowView.setData(this.d);
            this.mRoomFlowView.showAddView(false);
            Room i = HomeManager.a().i(this.f.b);
            if (i != null) {
                this.mRoomFlowView.setSelectIndex(this.d.indexOf(i.e()));
            }
        }
        this.mDeviceFlowView.setOnTagClickListener(new CommonFlowGroup.TagClickListener() {
            public void a() {
            }

            public void a(int i) {
                String str;
                if (i >= 0 && i < PowerItemkeyActivity.this.c.size() && (str = (String) PowerItemkeyActivity.this.c.get(i)) != null && !str.equals(PowerItemkeyActivity.this.etKeyname.getText().toString())) {
                    PowerItemkeyActivity.this.etKeyname.setText(str);
                    PowerItemkeyActivity.this.etKeyname.setSelection(str.length());
                }
            }
        });
        this.mDeviceFlowView.setOnTagCreateListener(this);
        this.mDeviceFlowView.showAddView(false);
        new RecommendDeviceNameApi().a(this.b.model, (RecommendDeviceNameApi.OnRecommendNameListener) new RecommendDeviceNameApi.OnRecommendNameListener() {
            public void onRecommendName(String str, List<String> list) {
                PowerItemkeyActivity.this.a(list);
            }
        });
        this.tvRoomadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final List<String> a2 = PowerMultikeyApi.a();
                a2.removeAll(PowerItemkeyActivity.this.d);
                ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) NameEditDialogHelper.a(PowerItemkeyActivity.this, "", PowerItemkeyActivity.this.getString(R.string.tag_add_title), PowerItemkeyActivity.this.getString(R.string.input_tag_name_hint), new NameEditDialogHelper.NameEditListener() {
                    public void a(final String str) {
                        int indexOf = PowerItemkeyActivity.this.d.indexOf(str);
                        if (indexOf >= 0) {
                            PowerItemkeyActivity.this.mRoomFlowView.setSelectIndex(indexOf);
                        } else if (!NetworkUtils.c()) {
                            ToastUtil.a((int) R.string.popup_select_loc_no_network);
                        } else {
                            PowerItemkeyActivity.this.b();
                            HomeManager.a().a(str, (List<String>) new ArrayList(), (String) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                                public void a() {
                                    if (PowerItemkeyActivity.this.isValid()) {
                                        PowerItemkeyActivity.this.a();
                                        PowerItemkeyActivity.this.d.add(0, str);
                                        PowerItemkeyActivity.this.mRecommendNoimg.setVisibility(8);
                                        PowerItemkeyActivity.this.mRoomFlowView.setVisibility(0);
                                        PowerItemkeyActivity.this.mRoomFlowView.setData(PowerItemkeyActivity.this.d);
                                        PowerItemkeyActivity.this.mRoomFlowView.setSelectIndex(0);
                                    }
                                }

                                public void a(int i, Error error) {
                                    if (PowerItemkeyActivity.this.isValid()) {
                                        PowerItemkeyActivity.this.a();
                                    }
                                }
                            });
                        }
                    }

                    public String b(String str) {
                        if (str == null || !PowerItemkeyActivity.this.d.contains(str.trim())) {
                            return null;
                        }
                        return PowerItemkeyActivity.this.getString(R.string.room_name_duplicate);
                    }
                }).getView();
                final CommonFlowGroup commonFlowGroup = clientRemarkInputView.getCommonFlowGroup();
                final EditText editText = clientRemarkInputView.getEditText();
                if (!(commonFlowGroup == null || editText == null)) {
                    commonFlowGroup.setVisibility(0);
                    commonFlowGroup.setMinWidth(64.0f);
                    commonFlowGroup.showAddView(false);
                    commonFlowGroup.setOnTagCreateListener(new CommonFlowGroup.TagCreateListener() {
                        public View onAddCreate(Context context) {
                            return null;
                        }

                        public TextView onTagCreate(Context context, int i) {
                            TextView onTagCreate = PowerItemkeyActivity.this.onTagCreate(context, i);
                            onTagCreate.setTextSize(14.0f);
                            return onTagCreate;
                        }
                    });
                    commonFlowGroup.setData(a2);
                    commonFlowGroup.setOnTagClickListener(new CommonFlowGroup.TagClickListener() {
                        public void a() {
                        }

                        public void a(int i) {
                            String str;
                            if (i >= 0 && i < a2.size() && (str = (String) a2.get(i)) != null && !str.equals(editText.getText().toString())) {
                                editText.setText(str);
                                editText.setSelection(str.length());
                            }
                        }
                    });
                    editText.addTextChangedListener(new DefaultTextWatcher() {
                        public void afterTextChanged(Editable editable) {
                            int indexOf = a2.indexOf(editable.toString());
                            if (indexOf < 0) {
                                commonFlowGroup.setSelectIndex(-1);
                            } else if (indexOf != commonFlowGroup.getSelectIndex()) {
                                commonFlowGroup.setSelectIndex(indexOf);
                            }
                        }
                    });
                }
                TextView titleRoomRecommend = clientRemarkInputView.getTitleRoomRecommend();
                if (titleRoomRecommend != null) {
                    titleRoomRecommend.setVisibility(0);
                    titleRoomRecommend.setText(R.string.multikey_room_recommend);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(List<String> list) {
        if (!this.c.equals(list) && list.size() != 0) {
            this.c.clear();
            this.c.addAll(list);
            this.mDeviceFlowView.setData(this.c);
            int indexOf = this.c.indexOf(this.etKeyname.getText().toString());
            if (indexOf >= 0) {
                this.mDeviceFlowView.setSelectIndex(indexOf);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.e == null || !this.e.isShowing()) {
            this.e = new XQProgressDialog(this);
            this.e.setCancelable(true);
            this.e.setMessage(getResources().getString(R.string.loading_share_info));
            this.e.show();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.client_remark_input_view_clear) {
            this.etKeyname.setText("");
        } else if (id == R.id.module_a_3_return_btn) {
            onBackPressed();
        } else if (id == R.id.module_a_3_right_iv_setting_btn) {
            String obj = this.etKeyname.getText().toString();
            if (!HomeManager.A(obj)) {
                ToastUtil.a((int) R.string.save_fail);
            } else if (StringUtil.t(obj)) {
                ToastUtil.a((int) R.string.tag_save_data_description);
            } else {
                int selectIndex = this.mDeviceFlowView.getSelectIndex();
                if (selectIndex < 0 || selectIndex >= this.c.size()) {
                    this.f.d = obj;
                } else {
                    this.f.d = this.c.get(selectIndex);
                }
                int selectIndex2 = this.mRoomFlowView.getSelectIndex();
                String l = HomeManager.a().l();
                if (selectIndex2 >= 0 && selectIndex2 < this.d.size()) {
                    List<Room> a2 = HomeManager.a().a(l);
                    String str = this.d.get(selectIndex2);
                    for (Room next : a2) {
                        if (TextUtils.equals(next.e(), str)) {
                            this.f.b = next.d();
                            this.f.c = l;
                        }
                    }
                }
                if (TextUtils.isEmpty(this.f.b)) {
                    this.f.b = l;
                    this.f.c = l;
                }
                setResult(-1, getIntent().putExtra(PowerMultikeyActivity.EXTRA_ENTITY, this.f));
                onBackPressed();
            }
        }
    }

    public TextView onTagCreate(Context context, int i) {
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.common_flow_tag_item, (ViewGroup) null);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, new CornerDrawable.RoundSideDrawable(-1907998));
        stateListDrawable.addState(new int[]{0}, new CornerDrawable.RoundSideDrawable(-526345));
        textView.setBackground(stateListDrawable);
        textView.setTextColor(new ColorStateList(new int[][]{new int[]{16842919}, new int[]{0}}, new int[]{-10066330, -10066330}));
        int a2 = DisplayUtils.a(6.0f);
        textView.setPadding(textView.getPaddingLeft(), a2, textView.getPaddingRight(), a2);
        return textView;
    }
}
