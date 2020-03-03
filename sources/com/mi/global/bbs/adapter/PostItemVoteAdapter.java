package com.mi.global.bbs.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.PostDetailModel;
import java.util.ArrayList;
import java.util.List;

public class PostItemVoteAdapter extends BaseAdapter {
    Context context;
    LayoutInflater mInflater;
    int maxMultiChoices;
    boolean multiple = false;
    boolean polled = false;
    int selectPostion = -1;
    List<String> selectedIdList = new ArrayList();
    List<PostDetailModel.DataBean.SpecialInfo.PollBean> voteList = new ArrayList();

    public long getItemId(int i) {
        return (long) i;
    }

    public PostItemVoteAdapter(Context context2) {
        this.context = context2;
        this.voteList = new ArrayList();
        this.mInflater = (LayoutInflater) context2.getSystemService("layout_inflater");
    }

    public void setPollData(List<PostDetailModel.DataBean.SpecialInfo.PollBean> list) {
        clear();
        if (list != null) {
            this.voteList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        this.voteList.clear();
    }

    public void setSelectPostion(int i) {
        this.selectPostion = i;
        notifyDataSetChanged();
    }

    public void setMaxMultiChoices(int i) {
        this.maxMultiChoices = i;
        notifyDataSetChanged();
    }

    public void setMultiple(boolean z) {
        this.multiple = z;
        notifyDataSetChanged();
    }

    public void setPolled(boolean z) {
        this.polled = z;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.voteList.size();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public List<String> getSelectedList() {
        return this.selectedIdList;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.bbs_vote_list_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tvVoteName = (TextView) view.findViewById(R.id.id_name);
            viewHolder.progressBar = (NumberProgressBar) view.findViewById(R.id.id_num_progress);
            viewHolder.tvVoteNum = (TextView) view.findViewById(R.id.id_vote_num);
            viewHolder.select = (RadioButton) view.findViewById(R.id.id_select);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        PostDetailModel.DataBean.SpecialInfo.PollBean pollBean = this.voteList.get(i);
        viewHolder.tvVoteName.setText(pollBean.name);
        boolean z = pollBean.voted == 1;
        if (this.polled) {
            if (z) {
                Drawable drawable = this.context.getResources().getDrawable(R.drawable.bbs_multiple_choice_s);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                viewHolder.tvVoteName.setCompoundDrawables((Drawable) null, (Drawable) null, drawable, (Drawable) null);
            }
            viewHolder.progressBar.setVisibility(0);
            viewHolder.progressBar.setProgress((int) pollBean.percent);
            viewHolder.select.setVisibility(8);
            viewHolder.tvVoteNum.setVisibility(0);
            TextView textView = viewHolder.tvVoteNum;
            textView.setText(pollBean.voters + "");
        } else {
            viewHolder.progressBar.setVisibility(8);
            viewHolder.select.setVisibility(0);
            viewHolder.tvVoteNum.setVisibility(8);
            if (this.selectPostion == i) {
                if (!this.multiple) {
                    this.selectedIdList.clear();
                    viewHolder.select.setChecked(true);
                    this.selectedIdList.add(this.voteList.get(i).id);
                } else if (viewHolder.select.isChecked()) {
                    this.selectedIdList.remove(this.voteList.get(i).id);
                    viewHolder.select.setChecked(false);
                } else if (this.selectedIdList.size() < this.maxMultiChoices) {
                    this.selectedIdList.add(this.voteList.get(i).id);
                    viewHolder.select.setChecked(true);
                }
            } else if (!this.multiple) {
                viewHolder.select.setChecked(false);
            }
        }
        return view;
    }

    public class ViewHolder {
        NumberProgressBar progressBar;
        RadioButton select;
        TextView tvVoteName;
        TextView tvVoteNum;

        public ViewHolder() {
        }
    }
}
