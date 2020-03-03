package com.xiaomi.smarthome.share;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.ShareDeviceActivity;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class ShareActivity extends BaseActivity {
    public static final String ARGS_KEY_BATCH_DIDS = "batch_dids";
    public static final String ARGS_KEY_DID = "did";
    public static final String ARGS_KEY_SHARE_TYPE = "share_type";
    public static final String ARGS_KEY_USER_INFO = "user_INFO";
    public static String ARG_KEYS_FROM = "jump_from";
    public static int FROM_XIAOMI_ACCOUNT = 1;
    public static final int SEND_SMS_TYPE = 100;
    public static int SHARE_FROM_FAMILY = 0;
    static final int STEP_GET_USER_INFO = 1;
    static final int STEP_SHARE = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f22095a = "ShareActivity";
    private static final int b = 11;
    private int c = 0;
    /* access modifiers changed from: private */
    public UserInfo d;
    private String e = "";
    private int f = -1;
    /* access modifiers changed from: private */
    public List<String> g = new ArrayList();
    /* access modifiers changed from: private */
    public Context h;
    /* access modifiers changed from: private */
    public ArrayList<String> i;
    /* access modifiers changed from: private */
    public Device j;
    /* access modifiers changed from: private */
    public SimpleDraweeView k;
    /* access modifiers changed from: private */
    public TextView l;
    /* access modifiers changed from: private */
    public TextView m;
    /* access modifiers changed from: private */
    public AutoCompleteTextView n;
    private TextView o;
    private View p;
    private View q;
    /* access modifiers changed from: private */
    public boolean r = false;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        this.h = this;
        setContentView(R.layout.sh_share_activity);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            str = extras.getString("did");
            this.e = extras.getString("share_type");
            this.f = extras.getInt(ARG_KEYS_FROM, -1);
        } else {
            str = null;
        }
        if (str != null) {
            this.j = SmartHomeDeviceManager.a().b(str);
        } else if (extras != null) {
            this.i = extras.getStringArrayList(ARGS_KEY_BATCH_DIDS);
        }
        this.c = 1;
        if (extras != null) {
            this.d = (UserInfo) extras.getParcelable(ARGS_KEY_USER_INFO);
        }
        a();
        if (this.d != null) {
            gotoShareStatus();
            this.l.setText(this.d.e);
            UserMamanger.a().a(this.d.f16462a, this.k, (BasePostprocessor) null);
        }
    }

    private void a() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) ShareActivity.this.getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(ShareActivity.this.n.getWindowToken(), 0);
                }
                ShareActivity.this.finish();
                if (ShareActivity.this.j != null) {
                    STAT.d.au();
                }
                boolean unused = ShareActivity.this.r = false;
            }
        });
        b();
        c();
        e();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.j != null) {
            STAT.c.d();
        }
    }

    private void b() {
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (this.f == SHARE_FROM_FAMILY) {
            textView.setText(R.string.share_to_family);
        } else if (this.f == FROM_XIAOMI_ACCOUNT) {
            textView.setText(R.string.smarthome_device_auth_xiaomi);
        } else if (this.j == null) {
            textView.setText(R.string.smarthome_share_multiple_devices);
        } else {
            textView.setText(String.format(getString(R.string.sh_share_tile), new Object[]{this.j.name}));
        }
    }

    private void c() {
        this.p = findViewById(R.id.scene_search);
        this.n = (AutoCompleteTextView) findViewById(R.id.account_editor);
        this.n.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return (i == 6 || (keyEvent != null && keyEvent.getKeyCode() == 66)) && ShareActivity.this.d();
            }
        });
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.device_image);
        simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.std_share_device_icon_small)).build());
        TextView textView = (TextView) findViewById(R.id.device_name);
        if (this.j != null) {
            DeviceFactory.a(this.j.model, simpleDraweeView, (int) R.drawable.moredevice_yellowlight);
            textView.setText(this.j.name);
        } else {
            textView.setText("");
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_share_device_icon_small));
        }
        ((ImageView) findViewById(R.id.btn_share_device)).setVisibility(8);
        this.o = (TextView) findViewById(R.id.button_search);
        this.o.setText(R.string.sh_confirm);
        this.o.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareActivity.this.a(ShareActivity.this.n.getText().toString());
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean d() {
        if (this.o == null || this.o.getVisibility() != 0) {
            return false;
        }
        return this.o.performClick();
    }

    private void e() {
        this.q = findViewById(R.id.scene_confirm);
        this.k = (SimpleDraweeView) findViewById(R.id.icon_image);
        this.l = (TextView) findViewById(R.id.account_name);
        this.m = (TextView) findViewById(R.id.share_state);
        this.m.setVisibility(4);
        String str = "";
        if ("".equals(this.e)) {
            str = getResources().getString(R.string.share_permission_can_control);
            this.m.setVisibility(0);
        } else if (ShareDeviceActivity.KEY_SHARE_TYPE_READONLY.equals(this.e)) {
            str = getResources().getString(R.string.share_permission_cannot_control);
            this.m.setVisibility(0);
        } else {
            this.m.setVisibility(4);
        }
        if (this.d == null) {
            this.m.setVisibility(4);
        }
        String str2 = "";
        if (this.j != null && !TextUtils.isEmpty(this.j.name)) {
            str2 = this.j.name;
        } else if (this.i != null && this.i.size() > 0) {
            str2 = SmartHomeDeviceManager.a().b(this.i.get(0)).name;
            if (this.i.size() > 1) {
                str2 = getResources().getQuantityString(R.plurals.share_wx_device_title_2, this.i.size(), new Object[]{SmartHomeDeviceManager.a().b(this.i.get(0)).name, Integer.valueOf(this.i.size())});
            }
        }
        this.m.setText(getResources().getString(R.string.share_permission_with_device_new, new Object[]{str, str2}));
        findViewById(R.id.button_confirm).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ShareActivity.this.j != null) {
                    ShareActivity.this.f();
                } else if (ShareActivity.this.i != null) {
                    ShareActivity.this.g();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.r = false;
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        if (TextUtils.isEmpty(str)) {
            ToastUtil.a((Context) this, (int) R.string.please_inpt_account);
        } else {
            RemoteFamilyApi.a().a((Context) this, str, (AsyncCallback<UserInfo, Error>) new AsyncCallback<UserInfo, Error>() {
                /* renamed from: a */
                public void onSuccess(UserInfo userInfo) {
                    if (userInfo != null && !TextUtils.isEmpty(userInfo.f16462a) && !userInfo.f16462a.equalsIgnoreCase("-1") && !userInfo.f16462a.equalsIgnoreCase("0")) {
                        ShareActivity.this.gotoShareStatus();
                        UserInfo unused = ShareActivity.this.d = userInfo;
                        ShareActivity.this.m.setVisibility(0);
                        ShareActivity.this.l.setText(ShareActivity.this.d.e);
                        ShareUserRecord shareUserRecord = ShareUserRecord.get(str);
                        if (shareUserRecord != null) {
                            shareUserRecord.nickName = ShareActivity.this.d.e;
                            ShareUserRecord.insert(shareUserRecord);
                        }
                        UserMamanger.a().a(ShareActivity.this.d.f16462a, ShareActivity.this.k, (BasePostprocessor) null);
                    } else if (ShareActivity.this.g.size() > 0) {
                        ShareActivity.this.a((String) ShareActivity.this.g.remove(0));
                    } else {
                        ToastUtil.a((Context) ShareActivity.this, (int) R.string.sh_user_not_exist);
                        ShareActivity.this.gotoGetUserInfoStatus();
                    }
                    if (!ShareActivity.this.r) {
                        boolean unused2 = ShareActivity.this.r = true;
                        if (ShareActivity.this.j != null) {
                            STAT.d.a(true, str);
                        }
                    }
                }

                public void onFailure(Error error) {
                    if (error.a() == ErrorCode.ERROR_NETWORK_ERROR.getCode()) {
                        ToastUtil.a((Context) ShareActivity.this, (int) R.string.sh_network_not_available);
                    } else {
                        ToastUtil.a((Context) ShareActivity.this, (int) R.string.sh_user_not_exist);
                    }
                    if (!ShareActivity.this.r) {
                        boolean unused = ShareActivity.this.r = true;
                        if (ShareActivity.this.j != null) {
                            STAT.d.a(false, str);
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void gotoGetUserInfoStatus() {
        this.c = 1;
        this.q.setVisibility(8);
        this.p.setVisibility(0);
        this.o.setText(R.string.sh_confirm);
        this.n.setText("");
    }

    /* access modifiers changed from: package-private */
    public void gotoShareStatus() {
        this.c = 2;
        if (Build.VERSION.SDK_INT >= 19) {
            Fade fade = new Fade();
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.setDuration(200);
            transitionSet.addTransition(fade);
            TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.root), transitionSet);
        }
        this.p.setVisibility(8);
        this.q.setVisibility(0);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.n.getWindowToken(), 0);
        }
    }

    private String b(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (Character.isDigit(str.charAt(i2))) {
                sb.append(str.charAt(i2));
            }
        }
        int indexOf = sb.indexOf("1");
        if (indexOf == -1) {
            Log.e(f22095a, "not a phone num");
            return null;
        }
        String substring = sb.substring(indexOf);
        if (substring.length() == 11) {
            return substring;
        }
        Log.e(f22095a, "not a phone num");
        return null;
    }

    private void a(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("_id");
        if (columnIndex == -1) {
            ToastUtil.a((Context) this, (int) R.string.sh_user_not_exist);
            return;
        }
        String string = cursor.getString(columnIndex);
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor query = contentResolver.query(uri, (String[]) null, "contact_id = " + string, (String[]) null, (String) null);
        if (query == null) {
            ToastUtil.a((Context) this, (int) R.string.sh_user_not_exist);
        } else if (query.moveToFirst()) {
            while (!query.isAfterLast()) {
                String b2 = b(query.getString(query.getColumnIndex("data1")));
                if (b2 != null) {
                    this.g.add(b2);
                }
                query.moveToNext();
            }
            if (!query.isClosed()) {
                query.close();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 100 && i3 == -1) {
            try {
                Cursor query = getContentResolver().query(intent.getData(), (String[]) null, (String) null, (String[]) null, (String) null);
                if (query == null || !query.moveToFirst()) {
                    ToastUtil.a((Context) this, (int) R.string.sh_user_not_exist);
                } else {
                    a(query);
                }
                if (query != null && !query.isClosed()) {
                    query.close();
                }
                if (this.g.size() > 0) {
                    a(this.g.remove(0));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        if (TextUtils.equals(CoreApi.a().s(), this.d.f16462a)) {
            ToastUtil.a((int) R.string.sh_share_request_fail_owner);
            return;
        }
        RemoteFamilyApi.a().a((Context) this, this.j.did, this.d.f16462a, this.e, (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                if (ShareActivity.this.isValid()) {
                    ToastUtil.a((Context) ShareActivity.this, (int) R.string.sh_share_request_success);
                    ShareActivity.this.finish();
                }
            }

            public void onFailure(Error error) {
                if (ShareActivity.this.isValid()) {
                    if (error.a() == ErrorCode.ERROR_MAXIMAL_SHARE_REQUEST.getCode()) {
                        new MLAlertDialog.Builder(ShareActivity.this.h).b((CharSequence) ShareActivity.this.h.getResources().getString(R.string.sh_share_fremax_request)).a((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).b().show();
                    } else if (error.a() == ErrorCode.ERROR_FEQUENT_REQUEST.getCode()) {
                        new MLAlertDialog.Builder(ShareActivity.this.h).b((CharSequence) ShareActivity.this.h.getResources().getString(R.string.sh_share_frequent_request)).a((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).b().show();
                    } else if (error.a() == ErrorCode.ERROR_REPEATED_REQUEST.getCode()) {
                        new MLAlertDialog.Builder(ShareActivity.this.h).b((CharSequence) ShareActivity.this.h.getResources().getString(R.string.sh_share_repeated_request)).a((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).b().show();
                    } else {
                        ToastUtil.a((Context) ShareActivity.this, (int) R.string.sh_share_request_fail);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void g() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.d.f16462a);
        RemoteFamilyApi.a().a((Context) this, (List<String>) this.i, (List<String>) arrayList, this.e, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                JSONObject optJSONObject = jSONObject.optJSONObject(ShareActivity.this.d.f16462a);
                boolean z = true;
                if (optJSONObject != null) {
                    Iterator<String> keys = optJSONObject.keys();
                    while (true) {
                        if (keys.hasNext()) {
                            if (1 == optJSONObject.optInt(keys.next())) {
                                z = false;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (!z || optJSONObject == null) {
                    ToastUtil.a((Context) ShareActivity.this, (int) R.string.sh_share_request_success);
                } else {
                    ToastUtil.a((Context) ShareActivity.this, (int) R.string.sh_share_repeated_request);
                }
                ShareActivity.this.finish();
            }

            public void onFailure(Error error) {
                if (error.a() == ErrorCode.ERROR_MAXIMAL_SHARE_REQUEST.getCode()) {
                    new MLAlertDialog.Builder(ShareActivity.this.h).b((CharSequence) ShareActivity.this.h.getResources().getString(R.string.sh_share_fremax_request)).a((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).b().show();
                } else if (error.a() == ErrorCode.ERROR_FEQUENT_REQUEST.getCode()) {
                    new MLAlertDialog.Builder(ShareActivity.this.h).b((CharSequence) ShareActivity.this.h.getResources().getString(R.string.sh_share_frequent_request)).a((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).b().show();
                } else {
                    ToastUtil.a((Context) ShareActivity.this, (int) R.string.sh_share_request_fail);
                }
            }
        });
    }
}
