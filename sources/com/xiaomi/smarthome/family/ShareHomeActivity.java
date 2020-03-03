package com.xiaomi.smarthome.family;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeMemberManager;
import com.xiaomi.smarthome.homeroom.model.HomeMember;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.ListItemView;
import com.xiaomi.smarthome.stat.STAT;

public class ShareHomeActivity extends BaseActivity {
    public static String KEY_HOME_ID = "key_home_id";
    public static int MAX_HOME_MEMBER = 11;

    /* renamed from: a  reason: collision with root package name */
    private ShareHomeInputUidFragment f15767a;
    /* access modifiers changed from: private */
    public String b;
    @BindView(2131429395)
    FrameLayout fragmentContainer;
    @BindView(2131430313)
    LinearLayout lastList;
    @BindView(2131430314)
    ListItemView lastTitle;
    @BindView(2131430474)
    ListItemView listSpace;
    @BindView(2131430969)
    ImageView moduleA3ReturnBtn;
    @BindView(2131430975)
    TextView moduleA3ReturnTitle;
    @BindView(2131430982)
    ImageView moduleA3RightIvSettingBtn;
    @BindView(2131432397)
    LinearLayout shareFamily;
    @BindView(2131432399)
    LinearLayout shareFriend;
    @BindView(2131432419)
    LinearLayout shareWx;

    public static void startActivity(Context context, String str) {
        LongSparseArray<HomeMember> a2 = HomeMemberManager.a().a(str);
        if (a2 == null || a2.size() < MAX_HOME_MEMBER) {
            Intent intent = new Intent(context, ShareHomeActivity.class);
            intent.putExtra(KEY_HOME_ID, str);
            context.startActivity(intent);
            return;
        }
        ToastUtil.a((int) R.string.home_member_max);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.share_device_activity);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.b = intent.getStringExtra(KEY_HOME_ID);
        if (TextUtils.isEmpty(this.b)) {
            finish();
            return;
        }
        ButterKnife.bind((Activity) this);
        a();
    }

    private void a() {
        this.moduleA3RightIvSettingBtn.setVisibility(8);
        this.shareFamily.setVisibility(8);
        this.moduleA3ReturnTitle.setText(R.string.home_member_share_to);
        this.f15767a = new ShareHomeInputUidFragment();
        this.listSpace.setVisibility(8);
        this.lastList.setVisibility(8);
        this.lastTitle.setVisibility(8);
        if (ServerCompact.e((Context) this)) {
            this.shareWx.setVisibility(8);
        } else {
            this.shareWx.setVisibility(0);
        }
    }

    @OnClick({2131430969, 2131432399, 2131432419})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.module_a_3_return_btn) {
            onBackPressed();
            STAT.d.aV();
        } else if (id == R.id.share_friend) {
            goUidFragment();
            STAT.d.aG(this.b);
        } else if (id == R.id.share_wx) {
            if (SHApplication.getIWXAPI().isWXAppInstalled()) {
                HomeMemberManager.a().a(this.b, (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                    /* renamed from: a */
                    public void onSuccess(String str) {
                        HomeMemberManager.a().a(str, ShareHomeActivity.this.b);
                    }

                    public void onFailure(Error error) {
                        if (error != null && TextUtils.isEmpty(error.b())) {
                            ToastUtil.a((CharSequence) error.b());
                        }
                    }
                });
            } else {
                ToastUtil.a(getContext(), (CharSequence) getString(R.string.wx_not_installed));
            }
            STAT.d.aH(this.b);
        }
    }

    public void goUidFragment() {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(ShareHomeInputUidFragment.class.getSimpleName());
        if (findFragmentByTag == null) {
            this.fragmentContainer.setVisibility(0);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out).add(R.id.fragment_container, this.f15767a, ShareHomeInputUidFragment.class.getSimpleName()).addToBackStack(ShareHomeInputUidFragment.class.getSimpleName()).commit();
            return;
        }
        this.fragmentContainer.setVisibility(0);
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out).show(findFragmentByTag).commit();
    }

    public String getHomeId() {
        return this.b;
    }
}
