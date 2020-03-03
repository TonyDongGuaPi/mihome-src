package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.SubForumModel;
import com.mi.global.bbs.ui.plate.PlateActivity;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.view.CircleImageView;
import java.util.HashMap;
import java.util.List;

public class SubForumAdapter extends RecyclerView.Adapter {
    private static final String CHILD_POSITION = "child_position";
    private static final String PARENT_POSITION = "parent_position";
    private final int TYPE_ITEM = 1;
    private final int TYPE_TITLE = 0;
    protected int columns;
    private LayoutInflater inflater;
    List<SubForumModel.DataBean> mList;

    public class SubForumTitleItemHolder_ViewBinding implements Unbinder {
        private SubForumTitleItemHolder target;

        @UiThread
        public SubForumTitleItemHolder_ViewBinding(SubForumTitleItemHolder subForumTitleItemHolder, View view) {
            this.target = subForumTitleItemHolder;
            subForumTitleItemHolder.mTitleTx = (TextView) Utils.findRequiredViewAsType(view, R.id.sub_forum_title_item_text, "field 'mTitleTx'", TextView.class);
            subForumTitleItemHolder.mSubForumTitleItemDivide = Utils.findRequiredView(view, R.id.sub_forum_title_item_divide, "field 'mSubForumTitleItemDivide'");
        }

        @CallSuper
        public void unbind() {
            SubForumTitleItemHolder subForumTitleItemHolder = this.target;
            if (subForumTitleItemHolder != null) {
                this.target = null;
                subForumTitleItemHolder.mTitleTx = null;
                subForumTitleItemHolder.mSubForumTitleItemDivide = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class SubForumItemHolder_ViewBinding implements Unbinder {
        private SubForumItemHolder target;

        @UiThread
        public SubForumItemHolder_ViewBinding(SubForumItemHolder subForumItemHolder, View view) {
            this.target = subForumItemHolder;
            subForumItemHolder.itemContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.sub_forum_item_container, "field 'itemContainer'", LinearLayout.class);
            subForumItemHolder.mNameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.sub_forum_item_text, "field 'mNameTv'", TextView.class);
            subForumItemHolder.mIconView = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.sub_forum_item_icon, "field 'mIconView'", CircleImageView.class);
        }

        @CallSuper
        public void unbind() {
            SubForumItemHolder subForumItemHolder = this.target;
            if (subForumItemHolder != null) {
                this.target = null;
                subForumItemHolder.itemContainer = null;
                subForumItemHolder.mNameTv = null;
                subForumItemHolder.mIconView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public SubForumAdapter(Context context, List<SubForumModel.DataBean> list) {
        this.inflater = LayoutInflater.from(context);
        this.columns = context.getResources().getInteger(R.integer.num_columns);
        this.mList = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new SubForumTitleItemHolder(this.inflater.inflate(R.layout.bbs_subforum_title_item, viewGroup, false));
            case 1:
                return new SubForumItemHolder(this.inflater.inflate(R.layout.bbs_subforum_item, viewGroup, false));
            default:
                return null;
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case 0:
                bindSubForumTitleViewHolder((SubForumTitleItemHolder) viewHolder, i);
                return;
            case 1:
                bindSubForumItemViewHolder((SubForumItemHolder) viewHolder, i);
                return;
            default:
                return;
        }
    }

    private void bindSubForumItemViewHolder(SubForumItemHolder subForumItemHolder, int i) {
        HashMap<String, Integer> isItem = isItem(i);
        if (isItem != null) {
            int intValue = isItem.get(PARENT_POSITION).intValue();
            SubForumModel.DataBean.SubmenuBean submenuBean = this.mList.get(intValue).submenu.get(isItem.get(CHILD_POSITION).intValue());
            ImageLoader.showImage(subForumItemHolder.mIconView, submenuBean.icon);
            final String str = submenuBean.name;
            subForumItemHolder.mNameTv.setText(str);
            final String str2 = submenuBean.url;
            subForumItemHolder.itemContainer.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SubForumAdapter.this.recordClick(str);
                    try {
                        String substring = str2.substring(str2.indexOf("-") + 1, str2.lastIndexOf("-"));
                        if (!TextUtils.isEmpty(substring)) {
                            PlateActivity.jumpWithId(view.getContext(), substring, str);
                        }
                    } catch (Exception unused) {
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void recordClick(String str) {
        GoogleTrackerUtil.sendRecordEvent("subforum", "click_subforum", String.format("click_subforum_%s", new Object[]{str}));
    }

    private void bindSubForumTitleViewHolder(SubForumTitleItemHolder subForumTitleItemHolder, int i) {
        subForumTitleItemHolder.mTitleTx.setText(this.mList.get(isTitleItem(i)).name);
        subForumTitleItemHolder.mSubForumTitleItemDivide.setVisibility(i == 0 ? 8 : 0);
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

    public int getParentPositionByItem(int i) {
        HashMap<String, Integer> isItem = isItem(i);
        if (isItem != null) {
            return isItem.get(PARENT_POSITION).intValue();
        }
        return -1;
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

    public int getItemColumnSpan(int i) {
        switch (getItemViewType(i)) {
            case 0:
                return this.columns;
            case 1:
                return 1;
            default:
                return this.columns;
        }
    }

    public void addData(List<SubForumModel.DataBean> list) {
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

    public class SubForumItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131494035)
        LinearLayout itemContainer;
        @BindView(2131494036)
        CircleImageView mIconView;
        @BindView(2131494037)
        TextView mNameTv;

        public SubForumItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class SubForumTitleItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131494039)
        View mSubForumTitleItemDivide;
        @BindView(2131494040)
        TextView mTitleTx;

        public SubForumTitleItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
