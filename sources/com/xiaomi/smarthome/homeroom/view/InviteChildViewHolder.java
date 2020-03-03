package com.xiaomi.smarthome.homeroom.view;

import android.content.DialogInterface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.homeroom.HomeMemberManager;
import com.xiaomi.smarthome.homeroom.UserInfoManager;
import com.xiaomi.smarthome.homeroom.model.HomeInviteInfo;
import com.xiaomi.smarthome.homeroom.view.HomeListAdapter;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.newui.MultiHomeManagerActivity;
import com.xiaomi.smarthome.stat.STAT;
import org.json.JSONObject;

public class InviteChildViewHolder extends BaseViewHolder {

    /* renamed from: a  reason: collision with root package name */
    public View f18361a;
    private View c;
    private TextView d;
    private View e;
    private TextView f;
    private HomeListAdapter.EditModeListener g;
    private TextView h;
    private TextView i;
    private SimpleDraweeView j;
    private View.OnClickListener k;

    public InviteChildViewHolder(View view, HomeListAdapter.EditModeListener editModeListener) {
        super(view);
        this.c = view;
        this.d = (TextView) view.findViewById(R.id.name);
        this.f18361a = view.findViewById(R.id.sort_icon);
        this.e = view.findViewById(R.id.divider_item);
        this.f = (TextView) view.findViewById(R.id.desc);
        this.h = (TextView) view.findViewById(R.id.tv_accept_btn);
        this.i = (TextView) view.findViewById(R.id.tv_expire);
        this.j = (SimpleDraweeView) view.findViewById(R.id.icon);
        this.g = editModeListener;
    }

    public void a(View.OnClickListener onClickListener) {
        this.k = onClickListener;
    }

    public void e(int i2) {
        this.e.setVisibility(i2);
    }

    public void a(boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.e.getLayoutParams();
        if (z) {
            layoutParams.leftMargin = 0;
        } else {
            layoutParams.leftMargin = DisplayUtils.a(SHApplication.getAppContext(), 24.0f);
        }
    }

    public void a(final HomeListAdapter homeListAdapter, final HomeInviteInfo homeInviteInfo) {
        String str;
        boolean k2 = homeListAdapter.k();
        boolean l = homeListAdapter.l();
        if (homeInviteInfo != null) {
            if (!k2) {
                this.f18361a.setVisibility(4);
            } else if (l) {
                this.f18361a.setVisibility(0);
            } else {
                this.f18361a.setVisibility(4);
            }
            this.f.setText(CalendarUtils.d(homeInviteInfo.j() * 1000));
            String string = homeListAdapter.e.getResources().getString(R.string.home_member_new_invite_item_title);
            UserInfo a2 = UserInfoManager.a().a(homeInviteInfo.e());
            boolean z = true;
            if (a2 != null) {
                UserMamanger.a().b(a2.c, this.j, (BasePostprocessor) null);
                str = String.format(string, new Object[]{a2.e, homeInviteInfo.a()});
            } else {
                UserMamanger.a().b((String) null, this.j, (BasePostprocessor) null);
                str = String.format(string, new Object[]{String.valueOf(homeInviteInfo.e()), homeInviteInfo.a()});
            }
            this.d.setText(str);
            if (System.currentTimeMillis() - (homeInviteInfo.k() * 1000) <= 0) {
                z = false;
            }
            int i2 = 8;
            this.i.setVisibility(z ? 0 : 8);
            TextView textView = this.h;
            if (!z) {
                i2 = 0;
            }
            textView.setVisibility(i2);
            this.h.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (homeListAdapter.e instanceof MultiHomeManagerActivity) {
                        ((MultiHomeManagerActivity) homeListAdapter.e).showProgressDialog((String) null);
                    }
                    STAT.d.a(homeInviteInfo.g());
                    HomeMemberManager.a().a(homeInviteInfo, 1, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            if (homeListAdapter.e instanceof MultiHomeManagerActivity) {
                                ((MultiHomeManagerActivity) homeListAdapter.e).setLatestAcceptHomeId(String.valueOf(homeInviteInfo.g()));
                            }
                        }

                        public void onFailure(Error error) {
                            if (homeListAdapter.e instanceof MultiHomeManagerActivity) {
                                ((MultiHomeManagerActivity) homeListAdapter.e).contrlProgreassDialog(false, false, 500);
                            }
                        }
                    });
                }
            });
            this.c.setOnLongClickListener(new View.OnLongClickListener(homeListAdapter, homeInviteInfo) {
                private final /* synthetic */ HomeListAdapter f$1;
                private final /* synthetic */ HomeInviteInfo f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final boolean onLongClick(View view) {
                    return InviteChildViewHolder.this.a(this.f$1, this.f$2, view);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean a(HomeListAdapter homeListAdapter, HomeInviteInfo homeInviteInfo, View view) {
        b(homeListAdapter, homeInviteInfo);
        return false;
    }

    private void b(final HomeListAdapter homeListAdapter, final HomeInviteInfo homeInviteInfo) {
        if (homeListAdapter != null && homeListAdapter.e != null) {
            new MLAlertDialog.Builder(homeListAdapter.e).b((CharSequence) homeListAdapter.e.getString(R.string.home_member_new_invite_reject)).a((int) R.string.tag_remove, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    HomeMemberManager.a().a(homeInviteInfo, 2, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            homeListAdapter.g();
                        }

                        public void onFailure(Error error) {
                            ToastUtil.a((int) R.string.toast_failed_retry);
                        }
                    });
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a(homeListAdapter.e.getResources().getColor(R.color.std_dialog_button_red), -1).d();
        }
    }
}
