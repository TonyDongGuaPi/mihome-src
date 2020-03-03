package com.mi.global.bbs.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.amap.location.common.model.AmapLoc;
import com.facebook.share.internal.ShareConstants;
import com.google.gson.JsonArray;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.model.MyReplyModel;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.plate.CommentsActivity;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.shop.model.Tags;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class MyReplyAdapter extends BaseLoadMoreAdapter {
    private static final int TYPE_NORMAL = 0;
    private List<MyReplyModel.DataBean.ListBean> items;
    /* access modifiers changed from: private */
    public BaseActivity mContext;
    OnShareListener onShareListener;

    public int getNormalViewType(int i) {
        return 0;
    }

    public class ReplyItemHolder_ViewBinding implements Unbinder {
        private ReplyItemHolder target;

        @UiThread
        public ReplyItemHolder_ViewBinding(ReplyItemHolder replyItemHolder, View view) {
            this.target = replyItemHolder;
            replyItemHolder.myReplyItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.my_reply_item_title, "field 'myReplyItemTitle'", TextView.class);
            replyItemHolder.myReplyQuotaTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.my_reply_quota_title, "field 'myReplyQuotaTitle'", TextView.class);
            replyItemHolder.myReplyItemContainer = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.my_reply_item_container, "field 'myReplyItemContainer'", RelativeLayout.class);
            replyItemHolder.myReplyDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_reply_date, "field 'myReplyDate'", TextView.class);
            replyItemHolder.myReplyQuotaItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.my_reply_quota_item, "field 'myReplyQuotaItem'", LinearLayout.class);
            replyItemHolder.relMyReplyDelete = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_reply_delete, "field 'relMyReplyDelete'", RelativeLayout.class);
        }

        @CallSuper
        public void unbind() {
            ReplyItemHolder replyItemHolder = this.target;
            if (replyItemHolder != null) {
                this.target = null;
                replyItemHolder.myReplyItemTitle = null;
                replyItemHolder.myReplyQuotaTitle = null;
                replyItemHolder.myReplyItemContainer = null;
                replyItemHolder.myReplyDate = null;
                replyItemHolder.myReplyQuotaItem = null;
                replyItemHolder.relMyReplyDelete = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public MyReplyAdapter(BaseActivity baseActivity, InfiniteScrollListener.DataLoading dataLoading, List<MyReplyModel.DataBean.ListBean> list) {
        super(baseActivity, dataLoading);
        this.mContext = baseActivity;
        this.items = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            return super.onCreateViewHolder(viewGroup, i);
        }
        return creatReplyHolder(viewGroup);
    }

    private ReplyItemHolder creatReplyHolder(ViewGroup viewGroup) {
        return new ReplyItemHolder(this.layoutInflater.inflate(R.layout.bbs_my_reply_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        if (getItemViewType(i) == 0) {
            bindReplyDataHolder((ReplyItemHolder) viewHolder, i);
        }
    }

    private void bindReplyDataHolder(ReplyItemHolder replyItemHolder, int i) {
        final MyReplyModel.DataBean.ListBean listBean = this.items.get(i);
        replyItemHolder.myReplyDate.setText(listBean.dateline);
        replyItemHolder.relMyReplyDelete.setVisibility(8);
        if ("-1".equals(listBean.displayorder) || AmapLoc.n.equals(listBean.invisible)) {
            replyItemHolder.relMyReplyDelete.setVisibility(0);
        }
        final String str = listBean.commenturl;
        replyItemHolder.myReplyItemContainer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommentsActivity.jump(MyReplyAdapter.this.mContext, listBean.fid, listBean.tid, str);
            }
        });
        if (getJsonObjByKey(listBean.message, ShareConstants.WEB_DIALOG_PARAM_QUOTE) != null) {
            replyItemHolder.myReplyItemTitle.setText(getJsonStringByKey(listBean.message, "txt"));
            JSONObject jsonObjByKey = getJsonObjByKey(listBean.message, ShareConstants.WEB_DIALOG_PARAM_QUOTE);
            if (jsonObjByKey != null) {
                String optString = jsonObjByKey.optString(Tags.Kuwan.AUTHOR);
                String optString2 = jsonObjByKey.optString("message");
                TextView textView = replyItemHolder.myReplyQuotaTitle;
                textView.setText(Html.fromHtml("<font color=\"#ff6702\">" + optString + "：</font> " + optString2));
                return;
            }
            return;
        }
        replyItemHolder.myReplyItemTitle.setText(getJsonStringByKey(listBean.message, "txt"));
        replyItemHolder.myReplyQuotaTitle.setText(listBean.title);
        replyItemHolder.myReplyQuotaItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(view.getContext(), listBean.threadurl);
            }
        });
    }

    private JSONObject getJsonObjByKey(JsonArray jsonArray, String str) {
        JSONObject jSONObject = null;
        try {
            JSONArray jSONArray = new JSONArray(jsonArray.toString());
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        Iterator<String> keys = optJSONObject.keys();
                        while (keys.hasNext()) {
                            if (str.equals(keys.next())) {
                                jSONObject = optJSONObject.optJSONObject(str);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private String getJsonStringByKey(JsonArray jsonArray, String str) {
        String str2 = "";
        try {
            JSONArray jSONArray = new JSONArray(jsonArray.toString());
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        Iterator<String> keys = optJSONObject.keys();
                        while (keys.hasNext()) {
                            if (str.equals(keys.next())) {
                                str2 = optJSONObject.optString(str);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    public void clear() {
        if (this.items != null && this.items.size() > 0) {
            this.items.clear();
            notifyDataSetChanged();
        }
    }

    public void add(List<MyReplyModel.DataBean.ListBean> list) {
        if (list != null && list.size() > 0) {
            this.items.addAll(list);
            notifyDataSetChanged();
        }
    }

    public int getDataItemCount() {
        return this.items.size();
    }

    public void setOnShareListener(OnShareListener onShareListener2) {
        this.onShareListener = onShareListener2;
    }

    static class ReplyItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131493713)
        TextView myReplyDate;
        @BindView(2131493714)
        RelativeLayout myReplyItemContainer;
        @BindView(2131493715)
        TextView myReplyItemTitle;
        @BindView(2131493716)
        LinearLayout myReplyQuotaItem;
        @BindView(2131493717)
        TextView myReplyQuotaTitle;
        @BindView(2131493899)
        RelativeLayout relMyReplyDelete;

        public ReplyItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
