package com.mi.global.bbs.ui.qa;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.BaseLoadMoreAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.ui.qa.QAInviteItem;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import java.util.ArrayList;
import java.util.List;

public class QAInviteAdapter extends BaseLoadMoreAdapter {
    public static final int FROM_ADD = 0;
    public static final int FROM_REMOVE = 1;
    public static final int MOST_ADD_COUNT = 10;
    /* access modifiers changed from: private */
    public boolean canInvite = true;
    /* access modifiers changed from: private */
    public BaseActivity context;
    OnClickAddListener onClickAddListener;
    public List<QAInviteItem.DataBean> qaInviteItemList;

    public interface OnClickAddListener {
        void onClickAdd(int i, String str, int i2, QAInviteHolder qAInviteHolder);
    }

    public int getNormalViewType(int i) {
        return 0;
    }

    public class QAInviteHolder_ViewBinding implements Unbinder {
        private QAInviteHolder target;

        @UiThread
        public QAInviteHolder_ViewBinding(QAInviteHolder qAInviteHolder, View view) {
            this.target = qAInviteHolder;
            qAInviteHolder.itemGroup = (TextView) Utils.findRequiredViewAsType(view, R.id.user_group, "field 'itemGroup'", TextView.class);
            qAInviteHolder.itemIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.user_icon, "field 'itemIcon'", ImageView.class);
            qAInviteHolder.itemName = (TextView) Utils.findRequiredViewAsType(view, R.id.user_name, "field 'itemName'", TextView.class);
            qAInviteHolder.itemInvite = (ImageView) Utils.findRequiredViewAsType(view, R.id.user_invite, "field 'itemInvite'", ImageView.class);
            qAInviteHolder.itemInvited = (ImageView) Utils.findRequiredViewAsType(view, R.id.user_invited, "field 'itemInvited'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            QAInviteHolder qAInviteHolder = this.target;
            if (qAInviteHolder != null) {
                this.target = null;
                qAInviteHolder.itemGroup = null;
                qAInviteHolder.itemIcon = null;
                qAInviteHolder.itemName = null;
                qAInviteHolder.itemInvite = null;
                qAInviteHolder.itemInvited = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public QAInviteAdapter(BaseActivity baseActivity, InfiniteScrollListener.DataLoading dataLoading) {
        super(baseActivity, dataLoading);
        this.context = baseActivity;
        this.qaInviteItemList = new ArrayList();
    }

    public void addData(List<QAInviteItem.DataBean> list) {
        if (list != null) {
            this.qaInviteItemList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.qaInviteItemList.clear();
    }

    public int getDataItemCount() {
        return this.qaInviteItemList.size();
    }

    public QAInviteItem.DataBean getDataItemByPosition(int i) {
        return this.qaInviteItemList.get(i);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            return super.onCreateViewHolder(viewGroup, i);
        }
        return new QAInviteHolder(this.layoutInflater.inflate(R.layout.bbs_qa_invite_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        if (getItemViewType(i) == 0) {
            bindQAInviteHolder((QAInviteHolder) viewHolder, i);
        }
    }

    private void bindQAInviteHolder(QAInviteHolder qAInviteHolder, int i) {
        handleCommonPart(qAInviteHolder, this.qaInviteItemList.get(i), i);
    }

    private void handleCommonPart(QAInviteHolder qAInviteHolder, QAInviteItem.DataBean dataBean, int i) {
        qAInviteHolder.itemGroup.setText(dataBean.groupname);
        qAInviteHolder.itemName.setText(dataBean.username);
        ImageLoader.showHeadIcon(qAInviteHolder.itemIcon, dataBean.icon);
        setCommonPartClickListener(qAInviteHolder, dataBean, i);
        if (dataBean.checked) {
            qAInviteHolder.itemInvited.setVisibility(0);
            qAInviteHolder.itemInvite.setVisibility(8);
            return;
        }
        qAInviteHolder.itemInvited.setVisibility(8);
        qAInviteHolder.itemInvite.setVisibility(0);
    }

    private void setCommonPartClickListener(final QAInviteHolder qAInviteHolder, final QAInviteItem.DataBean dataBean, final int i) {
        qAInviteHolder.itemIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(QAInviteAdapter.this.context, dataBean.uid);
            }
        });
        qAInviteHolder.itemInvite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (QAInviteAdapter.this.canInvite) {
                    QAInviteAdapter.this.onClickAddListener.onClickAdd(i, dataBean.uid, 0, qAInviteHolder);
                }
            }
        });
        qAInviteHolder.itemInvited.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QAInviteAdapter.this.onClickAddListener.onClickAdd(i, dataBean.uid, 1, qAInviteHolder);
            }
        });
    }

    public class QAInviteHolder extends RecyclerView.ViewHolder {
        @BindView(2131494211)
        TextView itemGroup;
        @BindView(2131494213)
        ImageView itemIcon;
        @BindView(2131494214)
        ImageView itemInvite;
        @BindView(2131494215)
        ImageView itemInvited;
        @BindView(2131494225)
        TextView itemName;

        public QAInviteHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public void setOnClickAddListener(OnClickAddListener onClickAddListener2) {
        this.onClickAddListener = onClickAddListener2;
    }

    public void setCanInvite(boolean z) {
        this.canInvite = z;
    }
}
