package com.xiaomi.smarthome.family;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.homeroom.HomeMemberManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class ShareHomeInputUidFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    static final int f15773a = 1;
    static final int b = 2;
    public static final int c = -11;
    @BindView(2131427462)
    AutoCompleteTextView accountEditor;
    @BindView(2131428087)
    TextView buttonSearch;
    View d;
    Unbinder e;
    private String f;
    private int g = 0;
    /* access modifiers changed from: private */
    public List<String> h = new ArrayList();
    /* access modifiers changed from: private */
    public UserInfo i;
    @BindView(2131429715)
    SimpleDraweeView iconImage;
    @BindView(2131430452)
    LinearLayout linearShareDevice;
    @BindView(2131432051)
    ConstraintLayout root;
    @BindView(2131433540)
    TextView tvUserName;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getActivity() instanceof ShareHomeActivity) {
            this.f = ((ShareHomeActivity) getActivity()).getHomeId();
        }
        this.g = 1;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.d == null) {
            this.d = layoutInflater.inflate(R.layout.fragment_share_home_intput_uid, (ViewGroup) null);
            ButterKnife.bind((Object) this, this.d);
        }
        this.e = ButterKnife.bind((Object) this, this.d);
        return this.d;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        c();
    }

    private void c() {
        d();
    }

    public void onResume() {
        super.onResume();
        STAT.c.h();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.e.unbind();
        STAT.d.aW();
    }

    private void d() {
        this.accountEditor.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return (i == 6 || (keyEvent != null && keyEvent.getKeyCode() == 66)) && ShareHomeInputUidFragment.this.e();
            }
        });
        if (this.iconImage != null) {
            this.iconImage.setImageResource(R.drawable.mi_logo);
        }
        this.accountEditor.getText().clear();
        this.tvUserName.setVisibility(8);
        this.linearShareDevice.setVisibility(0);
        this.buttonSearch.setText(R.string.home_member_search_user);
        this.buttonSearch.setEnabled(true);
    }

    /* access modifiers changed from: private */
    public boolean e() {
        if (this.buttonSearch == null || this.buttonSearch.getVisibility() != 0) {
            return false;
        }
        return this.buttonSearch.performClick();
    }

    @OnClick({2131428087})
    public void onSearchClicked() {
        if (this.g == 1) {
            a(this.accountEditor.getText().toString());
        } else if (this.g == 2) {
            this.buttonSearch.setEnabled(false);
            HomeMemberManager.a().a(this.i, this.f, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (ShareHomeInputUidFragment.this.getActivity() != null) {
                        ShareHomeInputUidFragment.this.getActivity().finish();
                    }
                }

                public void onFailure(Error error) {
                    if (error == null || !(error.a() == -11 || error.a() == -12)) {
                        ToastUtil.a((int) R.string.toast_failed_retry);
                    } else {
                        ToastUtil.a((int) R.string.add_failed_duplicate);
                    }
                    ShareHomeInputUidFragment.this.buttonSearch.setEnabled(true);
                }
            });
        }
        STAT.d.aX();
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        if (TextUtils.isEmpty(str)) {
            ToastUtil.a((int) R.string.please_inpt_account);
        } else {
            RemoteFamilyApi.a().a((Context) getActivity(), str, (AsyncCallback<UserInfo, Error>) new AsyncCallback<UserInfo, Error>() {
                /* renamed from: a */
                public void onSuccess(UserInfo userInfo) {
                    if (userInfo != null && !TextUtils.isEmpty(userInfo.f16462a) && !userInfo.f16462a.equalsIgnoreCase("-1") && !userInfo.f16462a.equalsIgnoreCase("0")) {
                        ShareHomeInputUidFragment.this.b();
                        UserInfo unused = ShareHomeInputUidFragment.this.i = userInfo;
                        ShareHomeInputUidFragment.this.tvUserName.setText(ShareHomeInputUidFragment.this.i.e);
                        ShareUserRecord shareUserRecord = ShareUserRecord.get(str);
                        if (shareUserRecord != null) {
                            shareUserRecord.nickName = ShareHomeInputUidFragment.this.i.e;
                            shareUserRecord.url = ShareHomeInputUidFragment.this.i.c;
                            ShareUserRecord.insert(shareUserRecord);
                        }
                        UserMamanger.a().a(ShareHomeInputUidFragment.this.i.f16462a, ShareHomeInputUidFragment.this.iconImage, (BasePostprocessor) null);
                    } else if (ShareHomeInputUidFragment.this.h.size() > 0) {
                        ShareHomeInputUidFragment.this.a((String) ShareHomeInputUidFragment.this.h.remove(0));
                    } else {
                        ToastUtil.a((int) R.string.sh_user_not_exist);
                        ShareHomeInputUidFragment.this.a();
                    }
                }

                public void onFailure(Error error) {
                    if (error.a() == ErrorCode.ERROR_NETWORK_ERROR.getCode()) {
                        ToastUtil.a((int) R.string.sh_network_not_available);
                    } else {
                        ToastUtil.a((int) R.string.sh_user_not_exist);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.g = 1;
        this.tvUserName.setVisibility(8);
        this.linearShareDevice.setVisibility(0);
        this.buttonSearch.setText(R.string.sh_confirm);
        this.accountEditor.setText("");
        this.accountEditor.getText().clear();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        InputMethodManager inputMethodManager;
        this.g = 2;
        if (Build.VERSION.SDK_INT >= 19) {
            Fade fade = new Fade();
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.setDuration(200);
            transitionSet.addTransition(fade);
            TransitionManager.beginDelayedTransition(this.root, transitionSet);
        }
        this.linearShareDevice.setVisibility(8);
        this.tvUserName.setVisibility(0);
        this.buttonSearch.setText(R.string.family_member_invite);
        if (getActivity() != null && (inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method")) != null) {
            inputMethodManager.hideSoftInputFromWindow(this.accountEditor.getWindowToken(), 0);
        }
    }
}
