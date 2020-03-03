package com.xiaomi.smarthome.family;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.library.common.imagecache.CircleAvatarProcessor;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.db.record.FamilyRecord;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.yanzhenjie.permission.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AddFamilyActivity extends BaseActivity {
    public static final int SEND_SMS_TYPE = 100;

    /* renamed from: a  reason: collision with root package name */
    private static final int f15580a = 0;
    private static final int b = 1;
    private static final int d = 11;
    /* access modifiers changed from: private */
    public List<String> c = new ArrayList();
    /* access modifiers changed from: private */
    public EditText e;
    /* access modifiers changed from: private */
    public EditText f;
    private View g;
    private ImageView h;
    private View i;
    /* access modifiers changed from: private */
    public Button j;
    /* access modifiers changed from: private */
    public Button k;
    /* access modifiers changed from: private */
    public Button l;
    /* access modifiers changed from: private */
    public SimpleDraweeView m;
    /* access modifiers changed from: private */
    public TextView n;
    /* access modifiers changed from: private */
    public TextView o;
    /* access modifiers changed from: private */
    public UserInfo p;
    /* access modifiers changed from: private */
    public int q;
    /* access modifiers changed from: private */
    public int r = 1000;
    private String[] s;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.add_family_layout);
        initView();
    }

    /* access modifiers changed from: package-private */
    public void initView() {
        this.s = getResources().getStringArray(R.array.family_memeber_names);
        if (getIntent().getExtras() != null) {
            String string = getIntent().getExtras().getString(FamilyRecord.FIELD_RELATION_ID);
            if (!TextUtils.isEmpty(string)) {
                try {
                    this.r = Integer.valueOf(string).intValue();
                    if (this.r == 7) {
                        this.r = 1000;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.add_other_family);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddFamilyActivity.this.setResult(0);
                AddFamilyActivity.this.finish();
            }
        });
        this.e = (EditText) findViewById(R.id.relation_input_view);
        this.f = (EditText) findViewById(R.id.userid_input_view);
        if (this.r != 1000) {
            this.e.setText(this.s[this.r - 1]);
        }
        this.g = findViewById(R.id.input_container);
        this.i = findViewById(R.id.family_member_container);
        this.i.setVisibility(8);
        this.j = (Button) findViewById(R.id.button3);
        this.j.setText(R.string.add_btn_text);
        this.j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AddFamilyActivity.this.q == 0) {
                    ((InputMethodManager) AddFamilyActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(AddFamilyActivity.this.f.getWindowToken(), 0);
                    if (TextUtils.isEmpty(AddFamilyActivity.this.e.getText().toString()) || TextUtils.isEmpty(AddFamilyActivity.this.f.getText().toString())) {
                        ToastUtil.a((Context) AddFamilyActivity.this, (int) R.string.input_account_null);
                    } else {
                        AddFamilyActivity.this.b(AddFamilyActivity.this.f.getText().toString());
                    }
                } else if (AddFamilyActivity.this.j.getText().equals(AddFamilyActivity.this.getString(R.string.give_up_invite))) {
                    AddFamilyActivity.this.finish();
                } else {
                    RemoteFamilyApi.a().b(AddFamilyActivity.this, AddFamilyActivity.this.p.f16462a, AddFamilyActivity.this.e.getText().toString(), String.valueOf(AddFamilyActivity.this.r), new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            ToastUtil.a((Context) AddFamilyActivity.this, (int) R.string.add_family_success);
                            AddFamilyActivity.this.setResult(-1);
                            AddFamilyActivity.this.finish();
                        }

                        public void onFailure(Error error) {
                            if (error.a() == -12) {
                                ToastUtil.a((Context) AddFamilyActivity.this, (int) R.string.add_failed_duplicate);
                            } else {
                                ToastUtil.a((Context) AddFamilyActivity.this, (int) R.string.add_failed);
                            }
                        }
                    });
                }
            }
        });
        this.h = (ImageView) findViewById(R.id.goto_contact);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.h.setVisibility(8);
        this.k = (Button) findViewById(R.id.button2);
        this.k.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddFamilyActivity.this.finish();
            }
        });
        this.l = (Button) findViewById(R.id.button1);
        this.l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + AddFamilyActivity.this.f.getText().toString()));
                intent.putExtra("sms_body", "");
                AddFamilyActivity.this.startActivity(intent);
            }
        });
        this.k.setVisibility(8);
        this.l.setVisibility(8);
        this.m = (SimpleDraweeView) findViewById(R.id.icon);
        this.n = (TextView) findViewById(R.id.nick_name);
        this.o = (TextView) findViewById(R.id.relation_ship);
        if (this.r != 1000) {
            this.f.requestFocus();
        }
    }

    /* access modifiers changed from: private */
    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (Character.isDigit(str.charAt(i2))) {
                sb.append(str.charAt(i2));
            }
        }
        int indexOf = sb.indexOf("1");
        if (indexOf == -1) {
            return null;
        }
        String substring = sb.substring(indexOf);
        if (substring.length() != 11) {
            return null;
        }
        return substring;
    }

    /* access modifiers changed from: private */
    public void b(final String str) {
        if (TextUtils.isEmpty(str)) {
            ToastUtil.a((Context) this, (int) R.string.please_inpt_account);
        } else {
            RemoteFamilyApi.a().a((Context) this, str, (AsyncCallback<UserInfo, Error>) new AsyncCallback<UserInfo, Error>() {
                /* renamed from: a */
                public void onSuccess(UserInfo userInfo) {
                    if (userInfo == null || TextUtils.isEmpty(userInfo.f16462a) || userInfo.f16462a.equalsIgnoreCase("-1") || userInfo.f16462a.equalsIgnoreCase("0")) {
                        AddFamilyActivity.this.gotoGetUserInfoStatus();
                        AddFamilyActivity.this.o.setText("");
                        AddFamilyActivity.this.m.setImageURI(CommonUtils.c((int) R.drawable.user_default));
                        if (AddFamilyActivity.this.c(AddFamilyActivity.this.f.getText().toString())) {
                            AddFamilyActivity.this.n.setText(R.string.invate_user_by_sms);
                            AddFamilyActivity.this.o.setText("");
                            AddFamilyActivity.this.j.setVisibility(8);
                            AddFamilyActivity.this.k.setVisibility(0);
                            AddFamilyActivity.this.k.setText(R.string.cancel);
                            AddFamilyActivity.this.l.setVisibility(0);
                            AddFamilyActivity.this.l.setText(R.string.confirm_button);
                            return;
                        }
                        AddFamilyActivity.this.n.setText(R.string.find_user_info_error);
                        AddFamilyActivity.this.o.setText("");
                        AddFamilyActivity.this.j.setText(R.string.give_up_invite);
                        return;
                    }
                    AddFamilyActivity.this.gotoGetUserInfoStatus();
                    UserInfo unused = AddFamilyActivity.this.p = userInfo;
                    AddFamilyActivity.this.n.setText(AddFamilyActivity.this.p.e);
                    AddFamilyActivity.this.o.setText(AddFamilyActivity.this.e.getText().toString());
                    ShareUserRecord shareUserRecord = ShareUserRecord.get(str);
                    if (shareUserRecord != null) {
                        shareUserRecord.nickName = AddFamilyActivity.this.p.e;
                        ShareUserRecord.insert(shareUserRecord);
                    }
                    UserMamanger.a().a(AddFamilyActivity.this.p.f16462a, AddFamilyActivity.this.m, (BasePostprocessor) new CircleAvatarProcessor());
                }

                public void onFailure(Error error) {
                    if (error.a() == ErrorCode.ERROR_NETWORK_ERROR.getCode()) {
                        ToastUtil.a((Context) AddFamilyActivity.this, (int) R.string.sh_network_not_available);
                    } else {
                        ToastUtil.a((Context) AddFamilyActivity.this, (int) R.string.sh_user_not_exist);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void gotoGetUserInfoStatus() {
        this.q = 1;
        this.i.setVisibility(0);
        this.g.setVisibility(8);
    }

    /* access modifiers changed from: package-private */
    public void gotoShareStatus() {
        this.q = 0;
        this.i.setVisibility(8);
        this.g.setVisibility(0);
    }

    private void a(final Cursor cursor) {
        PermissionHelper.d(this, true, new Action() {
            public void onAction(List<String> list) {
                int columnIndex = cursor.getColumnIndex("_id");
                if (columnIndex == -1) {
                    ToastUtil.a((Context) AddFamilyActivity.this, (int) R.string.sh_user_not_exist);
                    return;
                }
                String string = cursor.getString(columnIndex);
                ContentResolver contentResolver = AddFamilyActivity.this.getContentResolver();
                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                Cursor query = contentResolver.query(uri, (String[]) null, "contact_id = " + string, (String[]) null, (String) null);
                if (query == null) {
                    ToastUtil.a((Context) AddFamilyActivity.this, (int) R.string.sh_user_not_exist);
                } else if (query.moveToFirst()) {
                    while (!query.isAfterLast()) {
                        String access$1300 = AddFamilyActivity.this.a(query.getString(query.getColumnIndex("data1")));
                        if (!TextUtils.isEmpty(access$1300)) {
                            AddFamilyActivity.this.c.add(access$1300);
                        }
                        query.moveToNext();
                    }
                    if (!query.isClosed()) {
                        query.close();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 100 && i3 == -1) {
            Cursor query = getContentResolver().query(intent.getData(), (String[]) null, (String) null, (String[]) null, (String) null);
            if (query == null || !query.moveToFirst()) {
                ToastUtil.a((Context) this, (int) R.string.sh_user_not_exist);
            } else {
                a(query);
            }
            if (query != null && !query.isClosed()) {
                query.close();
            }
            if (this.c.size() > 0) {
                this.f.setText(this.c.remove(0));
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean c(String str) {
        return Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(str).matches();
    }
}
