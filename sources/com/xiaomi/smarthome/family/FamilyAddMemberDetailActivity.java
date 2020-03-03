package com.xiaomi.smarthome.family;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.imagecache.CircleAvatarProcessor;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.user.UserMamanger;

@Deprecated
public class FamilyAddMemberDetailActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public FamilyMemberData f15635a;
    private FamilyItemData b;
    private View c;
    private View d;
    private View e;
    private View f;
    /* access modifiers changed from: private */
    public EditText g;
    private TextView h;
    /* access modifiers changed from: private */
    public XQProgressDialog i;
    private View.OnClickListener j = new View.OnClickListener() {
        public void onClick(final View view) {
            if (view.isEnabled()) {
                view.setEnabled(false);
                FamilyAddMemberDetailActivity.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        view.setEnabled(true);
                    }
                }, 400);
                int id = view.getId();
                if (id != R.id.btn_continue_add) {
                    switch (id) {
                        case R.id.btn_add_complete:
                            FamilyAddMemberDetailActivity.this.setResult(201);
                            FamilyAddMemberDetailActivity.this.finish();
                            return;
                        case R.id.btn_add_member:
                            if (FamilyAddMemberDetailActivity.this.f15635a != null) {
                                FamilyAddMemberDetailActivity.this.f15635a.k = FamilyAddMemberDetailActivity.this.g.getText().toString();
                            }
                            FamilyAddMemberDetailActivity.this.c();
                            return;
                        default:
                            switch (id) {
                                case R.id.nick_name_father:
                                    FamilyAddMemberDetailActivity.this.a(FamilyAddMemberDetailActivity.this.getString(R.string.family_nick_name_father));
                                    return;
                                case R.id.nick_name_husband:
                                    FamilyAddMemberDetailActivity.this.a(FamilyAddMemberDetailActivity.this.getString(R.string.family_nick_name_husband));
                                    return;
                                case R.id.nick_name_mother:
                                    FamilyAddMemberDetailActivity.this.a(FamilyAddMemberDetailActivity.this.getString(R.string.family_nick_name_mother));
                                    return;
                                case R.id.nick_name_son:
                                    FamilyAddMemberDetailActivity.this.a(FamilyAddMemberDetailActivity.this.getString(R.string.family_nick_name_son));
                                    return;
                                case R.id.nick_name_wife:
                                    FamilyAddMemberDetailActivity.this.a(FamilyAddMemberDetailActivity.this.getString(R.string.family_nick_name_wife));
                                    return;
                                default:
                                    return;
                            }
                    }
                } else {
                    FamilyAddMemberDetailActivity.this.setResult(200);
                    FamilyAddMemberDetailActivity.this.finish();
                }
            }
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.f15635a = (FamilyMemberData) intent.getParcelableExtra(FamilyMemberData.f15708a);
        this.b = (FamilyItemData) intent.getParcelableExtra(FamilyItemData.f15689a);
        setContentView(R.layout.family_add_member_detail);
        a();
        b();
    }

    private void a() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.family_add_member);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyAddMemberDetailActivity.this.finish();
            }
        });
    }

    private void b() {
        this.c = findViewById(R.id.fast_nick_name_container);
        this.g = (EditText) findViewById(R.id.nick_name_edit);
        this.h = (TextView) findViewById(R.id.nick_name);
        this.d = findViewById(R.id.btn_continue_container);
        this.e = findViewById(R.id.btn_add_member);
        this.f = findViewById(R.id.icon_confirm);
        this.i = new XQProgressDialog(this);
        this.i.setCancelable(false);
        this.i.setMessage(getString(R.string.family_adding));
        if (this.f15635a != null) {
            if (this.f15635a.i != null) {
                ((TextView) findViewById(R.id.user_name)).setText(this.f15635a.i);
            }
            if (this.f15635a.k != null) {
                this.g.setText(this.f15635a.k);
            }
            if (this.f15635a.j != null) {
                UserMamanger.a().a(this.f15635a.g, (SimpleDraweeView) findViewById(R.id.icon), (BasePostprocessor) new CircleAvatarProcessor());
            }
        }
        findViewById(R.id.nick_name_husband).setOnClickListener(this.j);
        findViewById(R.id.nick_name_wife).setOnClickListener(this.j);
        findViewById(R.id.nick_name_father).setOnClickListener(this.j);
        findViewById(R.id.nick_name_mother).setOnClickListener(this.j);
        findViewById(R.id.nick_name_son).setOnClickListener(this.j);
        findViewById(R.id.btn_add_member).setOnClickListener(this.j);
        findViewById(R.id.btn_continue_add).setOnClickListener(this.j);
        findViewById(R.id.btn_add_complete).setOnClickListener(this.j);
        d();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.i != null) {
            this.i.dismiss();
            this.i = null;
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.f15635a != null && this.b != null) {
            this.i.show();
            RemoteFamilyApi.a().c(this, this.f15635a.g, this.b.f, this.f15635a.k, new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (FamilyAddMemberDetailActivity.this.i != null) {
                        FamilyAddMemberDetailActivity.this.i.dismiss();
                        FamilyAddMemberDetailActivity.this.e();
                    }
                }

                public void onFailure(Error error) {
                    if (FamilyAddMemberDetailActivity.this.i != null) {
                        FamilyAddMemberDetailActivity.this.i.dismiss();
                        ToastUtil.a((Context) FamilyAddMemberDetailActivity.this, (int) R.string.family_handle_failed);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        FamilyUtils.a(this.g, str);
    }

    private void d() {
        this.c.setVisibility(0);
        this.e.setVisibility(0);
        this.d.setVisibility(8);
        this.f.setVisibility(4);
        this.g.setCursorVisible(true);
        this.h.setVisibility(8);
        this.g.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void e() {
        this.c.setVisibility(4);
        this.e.setVisibility(8);
        this.g.setCursorVisible(false);
        this.h.setText(this.g.getText());
        this.g.setVisibility(8);
        this.h.setVisibility(0);
        this.d.setVisibility(0);
        this.f.setVisibility(0);
    }
}
