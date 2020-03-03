package com.xiaomi.smarthome.family;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.imagecache.CircleAvatarProcessor;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.user.UserMamanger;

@Deprecated
public class FamilyEditMemberActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public FamilyItemData f15680a;
    /* access modifiers changed from: private */
    public FamilyMemberData b;
    private View c;
    /* access modifiers changed from: private */
    public EditText d;
    /* access modifiers changed from: private */
    public XQProgressDialog e;
    /* access modifiers changed from: private */
    public String f;
    private View.OnClickListener g = new View.OnClickListener() {
        public void onClick(final View view) {
            if (view.isEnabled()) {
                view.setEnabled(false);
                FamilyEditMemberActivity.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        view.setEnabled(true);
                    }
                }, 400);
                int id = view.getId();
                if (id != R.id.btn_remove_member) {
                    if (id != R.id.nick_name) {
                        switch (id) {
                            case R.id.nick_name_edit:
                                FamilyEditMemberActivity.this.d.setCursorVisible(true);
                                ((InputMethodManager) FamilyEditMemberActivity.this.getSystemService("input_method")).showSoftInput(FamilyEditMemberActivity.this.d, 0);
                                return;
                            case R.id.nick_name_father:
                                FamilyEditMemberActivity.this.a(FamilyEditMemberActivity.this.getString(R.string.family_nick_name_father));
                                return;
                            case R.id.nick_name_husband:
                                FamilyEditMemberActivity.this.a(FamilyEditMemberActivity.this.getString(R.string.family_nick_name_husband));
                                return;
                            case R.id.nick_name_mother:
                                FamilyEditMemberActivity.this.a(FamilyEditMemberActivity.this.getString(R.string.family_nick_name_mother));
                                return;
                            case R.id.nick_name_son:
                                FamilyEditMemberActivity.this.a(FamilyEditMemberActivity.this.getString(R.string.family_nick_name_son));
                                return;
                            case R.id.nick_name_wife:
                                FamilyEditMemberActivity.this.a(FamilyEditMemberActivity.this.getString(R.string.family_nick_name_wife));
                                return;
                            default:
                                return;
                        }
                    } else if (view.getVisibility() == 0) {
                    }
                } else if (FamilyEditMemberActivity.this.b.g.equals(FamilyEditMemberActivity.this.f)) {
                    FamilyEditMemberActivity.this.c();
                } else if (FamilyEditMemberActivity.this.f15680a.h.equals(FamilyEditMemberActivity.this.f)) {
                    FamilyEditMemberActivity.this.d();
                }
            }
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.f15680a = (FamilyItemData) intent.getParcelableExtra(FamilyItemData.f15689a);
        this.b = (FamilyMemberData) intent.getParcelableExtra(FamilyMemberData.f15708a);
        this.f = CoreApi.a().s();
        if (this.b == null || this.f15680a == null) {
            finish();
            return;
        }
        setContentView(R.layout.family_edit_member);
        a();
        b();
    }

    private void a() {
        if (this.f15680a != null) {
            ((TextView) findViewById(R.id.module_a_3_return_title)).setText(this.f15680a.g);
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyEditMemberActivity.this.finish();
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.e != null) {
            this.e.dismiss();
            this.e = null;
        }
    }

    private void b() {
        this.e = new XQProgressDialog(this);
        this.e.setMessage(getString(R.string.family_updating));
        this.e.setCancelable(false);
        if (this.b != null) {
            ((TextView) findViewById(R.id.user_name)).setText(this.b.i);
            ((TextView) findViewById(R.id.nick_name)).setText(this.b.k);
            ((TextView) findViewById(R.id.nick_name_edit)).setText(this.b.k);
            UserMamanger.a().b(this.b.j, (SimpleDraweeView) findViewById(R.id.icon), new CircleAvatarProcessor());
        }
        TextView textView = (TextView) findViewById(R.id.btn_remove_member);
        if (this.b.g.equals(this.f)) {
            textView.setText(R.string.family_quit);
        } else {
            textView.setText(R.string.family_remove_member);
            if (this.f15680a.h.equals(this.f)) {
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
        }
        textView.setOnClickListener(this.g);
        this.c = findViewById(R.id.fast_nick_name_container);
        this.d = (EditText) findViewById(R.id.nick_name_edit);
        this.d.setCursorVisible(false);
        this.d.setOnClickListener(this.g);
        findViewById(R.id.nick_name_husband).setOnClickListener(this.g);
        findViewById(R.id.nick_name_wife).setOnClickListener(this.g);
        findViewById(R.id.nick_name_father).setOnClickListener(this.g);
        findViewById(R.id.nick_name_mother).setOnClickListener(this.g);
        findViewById(R.id.nick_name_son).setOnClickListener(this.g);
        this.d.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(final TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6 && (keyEvent == null || keyEvent.getKeyCode() != 66)) {
                    return false;
                }
                if (textView.getText().length() > 0) {
                    FamilyEditMemberActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            FamilyEditMemberActivity.this.a(textView.getText().toString());
                        }
                    });
                    return true;
                }
                ToastUtil.a((Context) FamilyEditMemberActivity.this, (int) R.string.family_nick_name_null, 0);
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.d.getWindowToken(), 0);
        this.d.setCursorVisible(false);
        this.d.setText(str);
        this.e.show();
        RemoteFamilyApi.a().d(this, this.f15680a.f, this.b.g, str, new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (FamilyEditMemberActivity.this.e != null) {
                    FamilyEditMemberActivity.this.e.dismiss();
                }
            }

            public void onFailure(Error error) {
                if (FamilyEditMemberActivity.this.e != null) {
                    Toast.makeText(FamilyEditMemberActivity.this, R.string.family_update_failed, 0).show();
                    FamilyEditMemberActivity.this.e.dismiss();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.f15680a != null && this.b != null) {
            RemoteFamilyApi.a().g(this, this.f15680a.f, new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (FamilyEditMemberActivity.this.e != null) {
                        FamilyEditMemberActivity.this.setResult(201);
                        Toast.makeText(FamilyEditMemberActivity.this, R.string.family_quit_success, 0).show();
                        FamilyEditMemberActivity.this.finish();
                    }
                }

                public void onFailure(Error error) {
                    if (FamilyEditMemberActivity.this.e != null) {
                        FamilyEditMemberActivity.this.a(error.a());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.f15680a != null && this.b != null) {
            RemoteFamilyApi.a().b((Context) this, this.b.g, this.f15680a.f, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (FamilyEditMemberActivity.this.e != null) {
                        Toast.makeText(FamilyEditMemberActivity.this, R.string.family_remove_success, 0).show();
                        FamilyEditMemberActivity.this.setResult(-1);
                        FamilyEditMemberActivity.this.finish();
                    }
                }

                public void onFailure(Error error) {
                    if (FamilyEditMemberActivity.this.e != null) {
                        FamilyEditMemberActivity.this.a(error.a());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        Toast.makeText(this, R.string.family_handle_failed, 0).show();
    }
}
