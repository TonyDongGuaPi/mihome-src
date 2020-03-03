package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.MIUIContentModel;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.util.Device;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.List;

public class MIUIProfessionUserAdapter extends RecyclerView.Adapter {
    /* access modifiers changed from: private */
    public BaseActivity mActivity;
    public List<MIUIContentModel.DataBean.UsersBean> users;

    public class ProfessionUserHolder_ViewBinding implements Unbinder {
        private ProfessionUserHolder target;

        @UiThread
        public ProfessionUserHolder_ViewBinding(ProfessionUserHolder professionUserHolder, View view) {
            this.target = professionUserHolder;
            professionUserHolder.mProfessionUserIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.profession_user_icon, "field 'mProfessionUserIcon'", CircleImageView.class);
            professionUserHolder.mProfessionUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.profession_user_name, "field 'mProfessionUserName'", TextView.class);
            professionUserHolder.mProfessionUserDes = (TextView) Utils.findRequiredViewAsType(view, R.id.profession_user_des, "field 'mProfessionUserDes'", TextView.class);
            professionUserHolder.mProfessionUserFollowBt = (TextView) Utils.findRequiredViewAsType(view, R.id.profession_user_follow_bt, "field 'mProfessionUserFollowBt'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ProfessionUserHolder professionUserHolder = this.target;
            if (professionUserHolder != null) {
                this.target = null;
                professionUserHolder.mProfessionUserIcon = null;
                professionUserHolder.mProfessionUserName = null;
                professionUserHolder.mProfessionUserDes = null;
                professionUserHolder.mProfessionUserFollowBt = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public MIUIProfessionUserAdapter(BaseActivity baseActivity, List<MIUIContentModel.DataBean.UsersBean> list) {
        this.users = list;
        this.mActivity = baseActivity;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ProfessionUserHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bbs_miui_professional_user_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ProfessionUserHolder professionUserHolder = (ProfessionUserHolder) viewHolder;
        final MIUIContentModel.DataBean.UsersBean usersBean = this.users.get(i);
        professionUserHolder.mProfessionUserDes.setText(usersBean.description);
        ImageLoader.showHeadIcon(professionUserHolder.mProfessionUserIcon, usersBean.icon);
        professionUserHolder.mProfessionUserName.setText(usersBean.username);
        professionUserHolder.itemView.getLayoutParams().width = (Device.f1349a - professionUserHolder.itemView.getResources().getDimensionPixelOffset(R.dimen.miui_users_left_space)) / 2;
        professionUserHolder.itemView.requestLayout();
        professionUserHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context context = view.getContext();
                UserCenterActivity.jump(context, usersBean.uid + "");
            }
        });
        professionUserHolder.mProfessionUserFollowBt.setText(usersBean.followStatus == 1 ? R.string.following : R.string.add_follow);
        professionUserHolder.mProfessionUserFollowBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginManager.getInstance().hasLogin()) {
                    GoogleTrackerUtil.sendRecordEvent("miui", Constants.ClickEvent.CLICK_FOLLOW, Constants.ClickEvent.CLICK_FOLLOW);
                    MIUIProfessionUserAdapter.this.onFollowClick(usersBean);
                    return;
                }
                MIUIProfessionUserAdapter.this.mActivity.gotoAccount();
            }
        });
    }

    /* access modifiers changed from: private */
    public void onFollowClick(final MIUIContentModel.DataBean.UsersBean usersBean) {
        if (usersBean.followStatus == 0) {
            this.mActivity.showProDialog(this.mActivity.getString(R.string.following_ing));
            ApiClient.follow(usersBean.uid + "", true, this.mActivity.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    MIUIProfessionUserAdapter.this.mActivity.dismissProDialog();
                    if (baseResult.getErrno() == 0) {
                        usersBean.followStatus = 1;
                        MIUIProfessionUserAdapter.this.notifyDataSetChanged();
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    MIUIProfessionUserAdapter.this.mActivity.dismissProDialog();
                }
            });
            return;
        }
        DialogFactory.showAlert((Context) this.mActivity, this.mActivity.getString(R.string.unfollow_hint), this.mActivity.getString(R.string.bbs_yes), this.mActivity.getString(R.string.bbs_cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                MIUIProfessionUserAdapter.this.mActivity.showProDialog(MIUIProfessionUserAdapter.this.mActivity.getString(R.string.unfollowing_ing));
                ApiClient.follow(usersBean.uid + "", false, MIUIProfessionUserAdapter.this.mActivity.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                    public void accept(@NonNull BaseResult baseResult) throws Exception {
                        MIUIProfessionUserAdapter.this.mActivity.dismissProDialog();
                        if (baseResult.getErrno() == 0) {
                            usersBean.followStatus = 0;
                            MIUIProfessionUserAdapter.this.notifyDataSetChanged();
                        }
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                        MIUIProfessionUserAdapter.this.mActivity.dismissProDialog();
                    }
                });
            }
        });
    }

    public int getItemCount() {
        if (this.users == null) {
            return 0;
        }
        return this.users.size();
    }

    public class ProfessionUserHolder extends RecyclerView.ViewHolder {
        @BindView(2131493838)
        TextView mProfessionUserDes;
        @BindView(2131493839)
        TextView mProfessionUserFollowBt;
        @BindView(2131493840)
        CircleImageView mProfessionUserIcon;
        @BindView(2131493841)
        TextView mProfessionUserName;

        public ProfessionUserHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
