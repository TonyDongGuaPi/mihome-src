package com.mi.global.shop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.ReviewSubmitAcitvity;
import com.mi.global.shop.model.orderreview.OrderReviewModel;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import java.util.ArrayList;

public class ReviewOrderListAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5505a = "ReviewOrderListAdapter";
    /* access modifiers changed from: private */
    public Context b;
    /* access modifiers changed from: private */
    public Activity c;
    private ArrayList<OrderReviewModel.OrderReviewItemModel> d = new ArrayList<>();
    private OnLoadMoreListener e;

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public class ReviewViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ReviewViewHolder f5508a;

        @UiThread
        public ReviewViewHolder_ViewBinding(ReviewViewHolder reviewViewHolder, View view) {
            this.f5508a = reviewViewHolder;
            reviewViewHolder.ivOrder = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.iv_order, "field 'ivOrder'", SimpleDraweeView.class);
            reviewViewHolder.itemTitle = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_title, "field 'itemTitle'", CustomTextView.class);
            reviewViewHolder.itemRating = (RatingBar) Utils.findRequiredViewAsType(view, R.id.item_rating, "field 'itemRating'", RatingBar.class);
        }

        @CallSuper
        public void unbind() {
            ReviewViewHolder reviewViewHolder = this.f5508a;
            if (reviewViewHolder != null) {
                this.f5508a = null;
                reviewViewHolder.ivOrder = null;
                reviewViewHolder.itemTitle = null;
                reviewViewHolder.itemRating = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public ReviewOrderListAdapter(Context context, Activity activity) {
        this.b = context;
        this.c = activity;
    }

    public void a(@Nullable OnLoadMoreListener onLoadMoreListener) {
        this.e = onLoadMoreListener;
    }

    public void a(ArrayList<OrderReviewModel.OrderReviewItemModel> arrayList) {
        if (arrayList != null) {
            this.d.clear();
            this.d.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    public void b(ArrayList<OrderReviewModel.OrderReviewItemModel> arrayList) {
        if (arrayList != null) {
            this.d.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    public void a(String str) {
        if (this.d != null) {
            for (int size = this.d.size() - 1; size >= 0; size--) {
                if (this.d.get(size).order_item_id.equals(str)) {
                    this.d.remove(size);
                }
            }
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void onBindViewHolder(final ReviewViewHolder reviewViewHolder, int i) {
        if (i == this.d.size() - 2 && this.e != null) {
            this.e.onLoadMore();
        }
        final OrderReviewModel.OrderReviewItemModel orderReviewItemModel = this.d.get(i);
        FrescoUtils.a(orderReviewItemModel.goods_img, reviewViewHolder.ivOrder);
        reviewViewHolder.itemTitle.setText(orderReviewItemModel.goods_name);
        reviewViewHolder.itemRating.setRating(0.0f);
        reviewViewHolder.itemRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                if (f != 0.0f) {
                    Intent intent = new Intent(ReviewOrderListAdapter.this.b, ReviewSubmitAcitvity.class);
                    intent.putExtra("order_item_id", orderReviewItemModel.order_item_id);
                    intent.putExtra("goods_sku", orderReviewItemModel.goods_sku);
                    intent.putExtra("goods_name", orderReviewItemModel.goods_name);
                    intent.putExtra("goods_img", orderReviewItemModel.goods_img);
                    intent.putExtra("orderRating", f);
                    ReviewOrderListAdapter.this.c.startActivityForResult(intent, 100);
                }
                reviewViewHolder.itemRating.setRating(0.0f);
            }
        });
        reviewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ReviewOrderListAdapter.this.b, ReviewSubmitAcitvity.class);
                intent.putExtra("order_item_id", orderReviewItemModel.order_item_id);
                intent.putExtra("goods_sku", orderReviewItemModel.goods_sku);
                intent.putExtra("goods_name", orderReviewItemModel.goods_name);
                intent.putExtra("goods_img", orderReviewItemModel.goods_img);
                ReviewOrderListAdapter.this.c.startActivityForResult(intent, 100);
            }
        });
    }

    /* renamed from: a */
    public ReviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ReviewViewHolder(LayoutInflater.from(this.b).inflate(R.layout.shop_order_review_item, viewGroup, false));
    }

    public int getItemCount() {
        return this.d.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131493521)
        RatingBar itemRating;
        @BindView(2131493523)
        CustomTextView itemTitle;
        @BindView(2131493558)
        SimpleDraweeView ivOrder;

        ReviewViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
