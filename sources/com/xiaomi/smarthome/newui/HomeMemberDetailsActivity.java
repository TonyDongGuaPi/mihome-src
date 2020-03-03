package com.xiaomi.smarthome.newui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeMemberManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.HomeMember;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.stat.STAT;
import org.json.JSONObject;

public class HomeMemberDetailsActivity extends BaseActivity {
    public static String KEY_HOME_ID = "key_home_id";
    public static String KEY_HOME_MEMBER = "key_home_member";
    public static int PERMISSION_ADMIN = Home.b;
    public static int PERMISSION_USER = Home.f18312a;

    /* renamed from: a  reason: collision with root package name */
    private static final String f20308a = "HomeMemberDetailsActivity";
    private int b;
    /* access modifiers changed from: private */
    public HomeMember c;
    /* access modifiers changed from: private */
    public Home d;
    @BindView(2131430969)
    ImageView moduleA3ReturnBtn;
    @BindView(2131430975)
    TextView moduleA3ReturnTitle;
    @BindView(2131430980)
    ImageView moduleA3RightBtn;
    @BindView(2131432174)
    SimpleDraweeView sdUserIcon;
    @BindView(2131433204)
    TextView tvBottomBtn;
    @BindView(2131433363)
    TextView tvReinviting;
    @BindView(2131433538)
    TextView tvUserId;
    @BindView(2131433539)
    TextView tvUserIdentity;
    @BindView(2131433540)
    TextView tvUserName;

    public static void startActivity(Context context, HomeMember homeMember, String str) {
        Intent intent = new Intent(context, HomeMemberDetailsActivity.class);
        intent.putExtra(KEY_HOME_MEMBER, homeMember);
        intent.putExtra(KEY_HOME_ID, str);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_member_details);
        ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra(KEY_HOME_ID);
        this.c = (HomeMember) intent.getParcelableExtra(KEY_HOME_MEMBER);
        this.d = HomeManager.a().j(stringExtra);
        if (this.d == null || this.c == null) {
            finish();
            LogUtil.b(f20308a, "home or homeMember == null!");
            return;
        }
        this.b = this.d.l();
        a();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (r3.d.p() != false) goto L_0x0040;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onResume() {
        /*
            r3 = this;
            super.onResume()
            com.xiaomi.smarthome.homeroom.model.HomeMember r0 = r3.c
            int r0 = r0.b()
            r1 = 2
            if (r0 == r1) goto L_0x0035
            r2 = 10
            if (r0 == r2) goto L_0x0029
            switch(r0) {
                case -1: goto L_0x001d;
                case 0: goto L_0x0014;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x0027
        L_0x0014:
            com.xiaomi.smarthome.homeroom.model.Home r0 = r3.d
            boolean r0 = r0.p()
            if (r0 == 0) goto L_0x0027
            goto L_0x0040
        L_0x001d:
            com.xiaomi.smarthome.homeroom.model.Home r0 = r3.d
            boolean r0 = r0.p()
            if (r0 == 0) goto L_0x0027
            r1 = 3
            goto L_0x0040
        L_0x0027:
            r1 = -1
            goto L_0x0040
        L_0x0029:
            com.xiaomi.smarthome.homeroom.model.Home r0 = r3.d
            boolean r0 = r0.p()
            if (r0 == 0) goto L_0x0033
            r1 = 6
            goto L_0x0040
        L_0x0033:
            r1 = 4
            goto L_0x0040
        L_0x0035:
            com.xiaomi.smarthome.homeroom.model.Home r0 = r3.d
            boolean r0 = r0.p()
            if (r0 == 0) goto L_0x003f
            r1 = 1
            goto L_0x0040
        L_0x003f:
            r1 = 5
        L_0x0040:
            com.xiaomi.smarthome.stat.StatPagev2 r0 = com.xiaomi.smarthome.stat.STAT.c
            r0.e((int) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.HomeMemberDetailsActivity.onResume():void");
    }

    private void a() {
        this.moduleA3RightBtn.setVisibility(8);
        UserInfo c2 = this.c.c();
        if (c2 != null) {
            UserMamanger.a().b(c2.c, this.sdUserIcon, (BasePostprocessor) null);
            this.tvUserName.setText(c2.e);
            this.tvUserId.setText(c2.f16462a);
        }
        this.moduleA3ReturnTitle.setText(R.string.home_member_info);
        if (this.b == PERMISSION_USER) {
            this.tvBottomBtn.setVisibility(8);
            this.tvReinviting.setVisibility(8);
        } else if (this.b == PERMISSION_ADMIN && this.c.b() != 10) {
            this.tvBottomBtn.setVisibility(0);
            if (this.c.b() == 2) {
                this.tvBottomBtn.setText(R.string.home_member_remove_member);
            } else if (this.c.b() == 0) {
                this.tvBottomBtn.setText(R.string.home_member_share_cancel);
                this.moduleA3ReturnTitle.setText(R.string.smarthome_to_user_status_waiting);
            } else if (this.c.b() == -1) {
                this.moduleA3ReturnTitle.setText(R.string.smarthome_share_expired);
                this.tvBottomBtn.setText(R.string.home_member_share_cancel);
                this.tvReinviting.setVisibility(0);
            }
        }
        this.tvUserIdentity.setText(this.c.c(getResources()));
    }

    @OnClick({2131430969})
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick({2131433204})
    public void onBottomBtnClicked() {
        b();
        if (this.c.b() == 2) {
            STAT.d.aF("1");
        } else if (this.c.b() == 0) {
            STAT.d.aF("2");
        } else if (this.c.b() == -1) {
            STAT.d.aF("3_1");
        }
    }

    @OnClick({2131433363})
    public void onReinvitingClicked() {
        UserInfo c2 = this.c.c();
        if (c2 != null) {
            STAT.d.aF("3_2");
            HomeMemberManager.a().a(c2, this.d.j(), (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    HomeMemberDetailsActivity.this.onBackPressed();
                }

                public void onFailure(Error error) {
                    if (error != null) {
                        ToastUtil.a((CharSequence) error.b());
                    }
                }
            });
        }
    }

    private void b() {
        String str;
        String str2;
        String str3;
        if (this.c.b() == 0 || this.c.b() == -1) {
            str3 = getString(R.string.share_home_cancel);
            str2 = getString(R.string.ok_button);
            str = getString(R.string.cancel);
        } else {
            str3 = String.format(getString(R.string.home_member_remove_member_dialog_title), new Object[]{this.tvUserName.getText()});
            str2 = getString(R.string.home_member_remove);
            str = getString(R.string.home_member_do_not_remove);
        }
        new MLAlertDialog.Builder(this).b((CharSequence) str3).a((CharSequence) str2, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                HomeMemberManager.a().a(HomeMemberDetailsActivity.this.c.a(), HomeMemberDetailsActivity.this.d, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        HomeMemberDetailsActivity.this.onBackPressed();
                    }

                    public void onFailure(Error error) {
                        ToastUtil.a((int) R.string.toast_failed_retry);
                    }
                });
            }
        }).b((CharSequence) str, (DialogInterface.OnClickListener) null).a(getResources().getColor(R.color.std_dialog_button_red), -1).d();
    }
}
