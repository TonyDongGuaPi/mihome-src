package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.QuestionsModel;
import com.mi.global.bbs.utils.ImageLoader;
import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private List<QuestionsModel.DataBean.StepsBean> list = new ArrayList();

    public class ItemHolder_ViewBinding implements Unbinder {
        private ItemHolder target;

        @UiThread
        public ItemHolder_ViewBinding(ItemHolder itemHolder, View view) {
            this.target = itemHolder;
            itemHolder.icon = (ImageView) Utils.findRequiredViewAsType(view, R.id.icon, "field 'icon'", ImageView.class);
            itemHolder.mark = (ImageView) Utils.findRequiredViewAsType(view, R.id.mark, "field 'mark'", ImageView.class);
            itemHolder.name = (TextView) Utils.findRequiredViewAsType(view, R.id.name, "field 'name'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ItemHolder itemHolder = this.target;
            if (itemHolder != null) {
                this.target = null;
                itemHolder.icon = null;
                itemHolder.mark = null;
                itemHolder.name = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public QuestionAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void refreshData(List<QuestionsModel.DataBean.StepsBean> list2) {
        this.list.clear();
        this.list.addAll(list2);
        notifyDataSetChanged();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemHolder(this.inflater.inflate(R.layout.bbs_question_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemHolder) viewHolder).bindData(this.list.get(i));
    }

    public int getItemCount() {
        if (this.list == null) {
            return 0;
        }
        return this.list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131493430)
        ImageView icon;
        @BindView(2131493620)
        ImageView mark;
        @BindView(2131493727)
        TextView name;

        public ItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }

        public void bindData(final QuestionsModel.DataBean.StepsBean stepsBean) {
            ImageLoader.showImage(this.icon, stepsBean.icon);
            this.mark.setVisibility(stepsBean.following ? 0 : 4);
            this.name.setText(stepsBean.name);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    stepsBean.following = !stepsBean.following;
                    ItemHolder.this.mark.setVisibility(stepsBean.following ? 0 : 4);
                }
            });
        }
    }
}
