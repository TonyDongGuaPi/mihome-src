package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.model.ForYouCustomizeModel;
import com.mi.global.bbs.ui.plate.PlateActivity;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.TextHelper;
import java.util.HashMap;
import java.util.List;

public class ForYouCustomizeAdapter extends RecyclerView.Adapter {
    private static final String CHILD_POSITION = "child_position";
    private static final String PARENT_POSITION = "parent_position";
    private final int TYPE_ITEM = 1;
    private final int TYPE_TITLE = 0;
    /* access modifiers changed from: private */
    public Context context;
    private LayoutInflater inflater;
    List<ForYouCustomizeModel.DataBean> mList;
    /* access modifiers changed from: private */
    public OnFollowListener mOnFollowListener;

    public interface OnFollowListener {
        void onFollow(String str, String str2);
    }

    public class ForYouCustomizeTitleItemHolder_ViewBinding implements Unbinder {
        private ForYouCustomizeTitleItemHolder target;

        @UiThread
        public ForYouCustomizeTitleItemHolder_ViewBinding(ForYouCustomizeTitleItemHolder forYouCustomizeTitleItemHolder, View view) {
            this.target = forYouCustomizeTitleItemHolder;
            forYouCustomizeTitleItemHolder.mTitleTx = (TextView) Utils.findRequiredViewAsType(view, R.id.for_you_customize_title_item_text, "field 'mTitleTx'", TextView.class);
            forYouCustomizeTitleItemHolder.mView = Utils.findRequiredView(view, R.id.item_top_divider, "field 'mView'");
        }

        @CallSuper
        public void unbind() {
            ForYouCustomizeTitleItemHolder forYouCustomizeTitleItemHolder = this.target;
            if (forYouCustomizeTitleItemHolder != null) {
                this.target = null;
                forYouCustomizeTitleItemHolder.mTitleTx = null;
                forYouCustomizeTitleItemHolder.mView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ForYouCustomizeItemHolder_ViewBinding implements Unbinder {
        private ForYouCustomizeItemHolder target;

        @UiThread
        public ForYouCustomizeItemHolder_ViewBinding(ForYouCustomizeItemHolder forYouCustomizeItemHolder, View view) {
            this.target = forYouCustomizeItemHolder;
            forYouCustomizeItemHolder.itemContainer = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.for_you_customize_item_container, "field 'itemContainer'", RelativeLayout.class);
            forYouCustomizeItemHolder.mTitleTv = (TextView) Utils.findRequiredViewAsType(view, R.id.for_you_customize_title, "field 'mTitleTv'", TextView.class);
            forYouCustomizeItemHolder.mCountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.for_you_customize_follow_count, "field 'mCountTv'", TextView.class);
            forYouCustomizeItemHolder.mIconView = (ImageView) Utils.findRequiredViewAsType(view, R.id.for_you_customize_item_icon, "field 'mIconView'", ImageView.class);
            forYouCustomizeItemHolder.mFollow = (TextView) Utils.findRequiredViewAsType(view, R.id.for_you_customize_item_follower, "field 'mFollow'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ForYouCustomizeItemHolder forYouCustomizeItemHolder = this.target;
            if (forYouCustomizeItemHolder != null) {
                this.target = null;
                forYouCustomizeItemHolder.itemContainer = null;
                forYouCustomizeItemHolder.mTitleTv = null;
                forYouCustomizeItemHolder.mCountTv = null;
                forYouCustomizeItemHolder.mIconView = null;
                forYouCustomizeItemHolder.mFollow = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public ForYouCustomizeAdapter(Context context2, List<ForYouCustomizeModel.DataBean> list) {
        this.context = context2;
        this.inflater = LayoutInflater.from(context2);
        this.mList = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new ForYouCustomizeTitleItemHolder(this.inflater.inflate(R.layout.bbs_for_you_customize_title_item, viewGroup, false));
            case 1:
                return new ForYouCustomizeItemHolder(this.inflater.inflate(R.layout.bbs_for_you_customize_item, viewGroup, false));
            default:
                return null;
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case 0:
                bindForYouCustomizeTitle((ForYouCustomizeTitleItemHolder) viewHolder, i);
                return;
            case 1:
                bindForYouCustomizeItem((ForYouCustomizeItemHolder) viewHolder, i);
                return;
            default:
                return;
        }
    }

    private void bindForYouCustomizeItem(ForYouCustomizeItemHolder forYouCustomizeItemHolder, int i) {
        HashMap<String, Integer> isItem = isItem(i);
        if (isItem != null) {
            int intValue = isItem.get(PARENT_POSITION).intValue();
            ForYouCustomizeModel.DataBean.ForYouCustomizeBean forYouCustomizeBean = this.mList.get(intValue).submenu.get(isItem.get(CHILD_POSITION).intValue());
            ImageLoader.showImage(forYouCustomizeItemHolder.mIconView, forYouCustomizeBean.icon);
            forYouCustomizeItemHolder.mTitleTv.setText(forYouCustomizeBean.name);
            TextHelper.setQuantityString(this.context, forYouCustomizeItemHolder.mCountTv, R.plurals._Followers, forYouCustomizeBean.followCount);
            if (forYouCustomizeBean.followStatus) {
                forYouCustomizeItemHolder.mFollow.setText(this.context.getResources().getString(R.string.following));
            } else {
                forYouCustomizeItemHolder.mFollow.setText(this.context.getResources().getString(R.string.add_follow));
            }
            setClickListener(forYouCustomizeItemHolder, forYouCustomizeBean);
        }
    }

    private void setClickListener(final ForYouCustomizeItemHolder forYouCustomizeItemHolder, final ForYouCustomizeModel.DataBean.ForYouCustomizeBean forYouCustomizeBean) {
        forYouCustomizeItemHolder.itemContainer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String substring = forYouCustomizeBean.url.substring(forYouCustomizeBean.url.indexOf("-") + 1, forYouCustomizeBean.url.lastIndexOf("-"));
                    if (!TextUtils.isEmpty(substring)) {
                        PlateActivity.jumpWithId(view.getContext(), substring, forYouCustomizeBean.name);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        forYouCustomizeItemHolder.mFollow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginManager.getInstance().hasLogin()) {
                    forYouCustomizeBean.followStatus = !forYouCustomizeBean.followStatus;
                    if (forYouCustomizeBean.followStatus) {
                        forYouCustomizeItemHolder.mFollow.setText(R.string.following);
                    } else {
                        forYouCustomizeItemHolder.mFollow.setText(R.string.add_follow);
                    }
                    if (ForYouCustomizeAdapter.this.mOnFollowListener != null) {
                        ForYouCustomizeAdapter.this.mOnFollowListener.onFollow(String.valueOf(forYouCustomizeBean.fid), "home_for_you");
                        return;
                    }
                    return;
                }
                ((BaseActivity) ForYouCustomizeAdapter.this.context).gotoAccount();
            }
        });
    }

    private void recordClick(String str) {
        GoogleTrackerUtil.sendRecordEvent("for_you_customize", "click_for_you_customize", String.format("click_for_you_customize_%s", new Object[]{str}));
    }

    private void bindForYouCustomizeTitle(ForYouCustomizeTitleItemHolder forYouCustomizeTitleItemHolder, int i) {
        forYouCustomizeTitleItemHolder.mTitleTx.setText(this.mList.get(isTitleItem(i)).name.toUpperCase());
        if (i == 0) {
            forYouCustomizeTitleItemHolder.mView.setVisibility(8);
        } else {
            forYouCustomizeTitleItemHolder.mView.setVisibility(0);
        }
    }

    public int getItemViewType(int i) {
        return isTitleItem(i) != -1 ? 0 : 1;
    }

    public int isTitleItem(int i) {
        for (int i2 = 0; i2 < this.mList.size(); i2++) {
            if (i == getTitlePosition(i2)) {
                return i2;
            }
        }
        return -1;
    }

    public HashMap<String, Integer> isItem(int i) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        int i2 = 0;
        while (i2 < this.mList.size()) {
            if (i < getTitlePosition(i2)) {
                int i3 = i2 - 1;
                hashMap.put(PARENT_POSITION, Integer.valueOf(i3));
                hashMap.put(CHILD_POSITION, Integer.valueOf((i - getTitlePosition(i3)) - 1));
                return hashMap;
            } else if (i2 == this.mList.size() - 1) {
                hashMap.put(PARENT_POSITION, Integer.valueOf(i2));
                hashMap.put(CHILD_POSITION, Integer.valueOf((i - getTitlePosition(i2)) - 1));
                return hashMap;
            } else {
                i2++;
            }
        }
        return null;
    }

    public int getItemCount() {
        if (this.mList == null) {
            return 0;
        }
        return this.mList.size() + traverseAllChildListCount();
    }

    private int traverseAllChildListCount() {
        int i = 0;
        for (int i2 = 0; i2 < this.mList.size(); i2++) {
            i += this.mList.get(i2).submenu == null ? 0 : this.mList.get(i2).submenu.size();
        }
        return i;
    }

    public void addData(List<ForYouCustomizeModel.DataBean> list) {
        if (list != null) {
            this.mList.clear();
            this.mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public int getTitlePosition(int i) {
        return i + traverseChildListCount(i);
    }

    private int traverseChildListCount(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i && i <= this.mList.size(); i3++) {
            i2 += this.mList.get(i3).submenu == null ? 0 : this.mList.get(i3).submenu.size();
        }
        return i2;
    }

    public class ForYouCustomizeItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131493290)
        RelativeLayout itemContainer;
        @BindView(2131493289)
        TextView mCountTv;
        @BindView(2131493291)
        TextView mFollow;
        @BindView(2131493292)
        ImageView mIconView;
        @BindView(2131493293)
        TextView mTitleTv;

        public ForYouCustomizeItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForYouCustomizeTitleItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131493294)
        TextView mTitleTx;
        @BindView(2131493513)
        View mView;

        public ForYouCustomizeTitleItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public void setOnFollowListener(OnFollowListener onFollowListener) {
        this.mOnFollowListener = onFollowListener;
    }
}
