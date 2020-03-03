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
import com.mi.global.bbs.model.MIUIContentModel;
import com.mi.global.bbs.ui.plate.PlateActivity;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import java.util.List;

public class MIUISubForumAdapter extends RecyclerView.Adapter {
    public List<MIUIContentModel.DataBean.SubmenuBean> submenu;

    public class SubForumHolder_ViewBinding implements Unbinder {
        private SubForumHolder target;

        @UiThread
        public SubForumHolder_ViewBinding(SubForumHolder subForumHolder, View view) {
            this.target = subForumHolder;
            subForumHolder.mForumIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_icon, "field 'mForumIcon'", ImageView.class);
            subForumHolder.mForumName = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_name, "field 'mForumName'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            SubForumHolder subForumHolder = this.target;
            if (subForumHolder != null) {
                this.target = null;
                subForumHolder.mForumIcon = null;
                subForumHolder.mForumName = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public MIUISubForumAdapter(List<MIUIContentModel.DataBean.SubmenuBean> list) {
        this.submenu = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SubForumHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bbs_miui_forum_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        SubForumHolder subForumHolder = (SubForumHolder) viewHolder;
        final MIUIContentModel.DataBean.SubmenuBean submenuBean = this.submenu.get(i);
        subForumHolder.mForumName.setText(submenuBean.name);
        ImageLoader.showImage(subForumHolder.mForumIcon, submenuBean.icon);
        subForumHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent("miui", "click_subforum", "click_subforum");
                Context context = view.getContext();
                PlateActivity.jumpWithId(context, submenuBean.fid + "", submenuBean.name);
            }
        });
    }

    public int getItemCount() {
        if (this.submenu == null) {
            return 0;
        }
        return this.submenu.size();
    }

    public class SubForumHolder extends RecyclerView.ViewHolder {
        @BindView(2131493302)
        ImageView mForumIcon;
        @BindView(2131493340)
        TextView mForumName;

        public SubForumHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
