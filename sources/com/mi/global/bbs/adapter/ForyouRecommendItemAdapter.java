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
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.ForYouRecoomend;
import com.mi.global.bbs.ui.plate.PlateActivity;
import com.mi.global.bbs.utils.ImageLoader;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.List;

public class ForyouRecommendItemAdapter extends RecyclerView.Adapter {
    public static final String TAG = "ForyouRecommendItemAdapter";
    protected LayoutInflater layoutInflater;
    /* access modifiers changed from: private */
    public Context mContext;
    private List<ForYouRecoomend> mListData;
    OnFollowListener mOnFollowListener;

    public class ColumnViewHolder_ViewBinding implements Unbinder {
        private ColumnViewHolder target;

        @UiThread
        public ColumnViewHolder_ViewBinding(ColumnViewHolder columnViewHolder, View view) {
            this.target = columnViewHolder;
            columnViewHolder.itemRelayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.item_Recommend_layout, "field 'itemRelayout'", RelativeLayout.class);
            columnViewHolder.tvRecommendTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_recommend_name, "field 'tvRecommendTitle'", TextView.class);
            columnViewHolder.tvThreadCount = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_recommend_thread_count, "field 'tvThreadCount'", TextView.class);
            columnViewHolder.ivRecIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.tv_recommend_head, "field 'ivRecIcon'", ImageView.class);
            columnViewHolder.tvFollow = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_recommend_follower_bt, "field 'tvFollow'", TextView.class);
            columnViewHolder.foryouRecommendItemFixLeft = Utils.findRequiredView(view, R.id.foryou_list_item_fix_left, "field 'foryouRecommendItemFixLeft'");
            columnViewHolder.foryouRecommendItemFixRight = Utils.findRequiredView(view, R.id.foryou_list_item_fix_right, "field 'foryouRecommendItemFixRight'");
        }

        @CallSuper
        public void unbind() {
            ColumnViewHolder columnViewHolder = this.target;
            if (columnViewHolder != null) {
                this.target = null;
                columnViewHolder.itemRelayout = null;
                columnViewHolder.tvRecommendTitle = null;
                columnViewHolder.tvThreadCount = null;
                columnViewHolder.ivRecIcon = null;
                columnViewHolder.tvFollow = null;
                columnViewHolder.foryouRecommendItemFixLeft = null;
                columnViewHolder.foryouRecommendItemFixRight = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public ForyouRecommendItemAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void setData(List<ForYouRecoomend> list) {
        this.mListData = list;
        notifyDataSetChanged();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ColumnViewHolder(this.layoutInflater.inflate(R.layout.bbs_column_foryou_rec_recycle_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        bindViewHolder((ColumnViewHolder) viewHolder, i);
    }

    public void setOnFollowListener(OnFollowListener onFollowListener) {
        this.mOnFollowListener = onFollowListener;
    }

    public int getItemCount() {
        return this.mListData.size();
    }

    private void bindViewHolder(final ColumnViewHolder columnViewHolder, int i) {
        final ForYouRecoomend forYouRecoomend = this.mListData.get(i);
        columnViewHolder.tvThreadCount.setText(this.mContext.getString(R.string.forum_threads_, new Object[]{forYouRecoomend.threads}));
        ImageLoader.showImage(columnViewHolder.ivRecIcon, forYouRecoomend.icon);
        columnViewHolder.foryouRecommendItemFixLeft.setVisibility(8);
        columnViewHolder.foryouRecommendItemFixRight.setVisibility(8);
        if (i == 0) {
            columnViewHolder.foryouRecommendItemFixLeft.setVisibility(0);
        }
        if (i == this.mListData.size() - 1) {
            columnViewHolder.foryouRecommendItemFixRight.setVisibility(0);
        }
        columnViewHolder.itemRelayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str = forYouRecoomend.fid;
                String str2 = forYouRecoomend.name;
                if (!TextUtils.isEmpty(str)) {
                    PlateActivity.jumpWithId(ForyouRecommendItemAdapter.this.mContext, str, str2);
                }
            }
        });
        columnViewHolder.tvRecommendTitle.setText(forYouRecoomend.name);
        if (forYouRecoomend.thumbupstatus == 1) {
            columnViewHolder.tvFollow.setText(R.string.following);
        } else {
            columnViewHolder.tvFollow.setText(R.string.add_follow);
        }
        columnViewHolder.tvFollow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginManager.getInstance().hasLogin()) {
                    forYouRecoomend.thumbupstatus = forYouRecoomend.thumbupstatus == 1 ? 0 : 1;
                    if (forYouRecoomend.thumbupstatus == 1) {
                        columnViewHolder.tvFollow.setText(R.string.following);
                    } else {
                        columnViewHolder.tvFollow.setText(R.string.add_follow);
                    }
                    ApiClient.followForum(forYouRecoomend.fid, "home_for_you").subscribe(new Consumer<BaseResult>() {
                        public void accept(@NonNull BaseResult baseResult) throws Exception {
                            if (baseResult != null && baseResult.getErrno() == 0) {
                                ForyouRecommendItemAdapter.this.notifyDataSetChanged();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        public void accept(@NonNull Throwable th) throws Exception {
                        }
                    });
                    return;
                }
                ((BaseActivity) ForyouRecommendItemAdapter.this.mContext).gotoAccount();
            }
        });
    }

    class ColumnViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131493341)
        View foryouRecommendItemFixLeft;
        @BindView(2131493342)
        View foryouRecommendItemFixRight;
        @BindView(2131493477)
        RelativeLayout itemRelayout;
        @BindView(2131494156)
        ImageView ivRecIcon;
        @BindView(2131494155)
        TextView tvFollow;
        @BindView(2131494158)
        TextView tvRecommendTitle;
        @BindView(2131494159)
        TextView tvThreadCount;

        ColumnViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, this.itemView);
        }
    }
}
