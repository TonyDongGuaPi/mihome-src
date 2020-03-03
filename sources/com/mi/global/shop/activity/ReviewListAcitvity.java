package com.mi.global.shop.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.ReviewOrderListAdapter;
import com.mi.global.shop.db.Setting;
import com.mi.global.shop.loader.BaseResult;
import com.mi.global.shop.model.orderreview.OrderReviewModel;
import com.mi.global.shop.request.MiJsonObjectRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.NetworkUtil;
import com.mi.global.shop.util.RequestUtil;
import com.mi.global.shop.widget.EmptyLoadingViewPlus;
import com.mi.global.shop.widget.RecycleViewDivider;
import com.mi.global.shop.widget.dialog.ReviewTipsDialog;
import com.mi.log.LogUtil;
import com.mi.multimonitor.Request;
import com.mi.util.Coder;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import org.json.JSONObject;

public class ReviewListAcitvity extends BaseActivity implements ReviewOrderListAdapter.OnLoadMoreListener {
    public static final int REQUEST_REVIEW = 100;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f5428a = "ReviewListAcitvity";
    private ReviewOrderListAdapter b;
    private long c = 1;
    private int d = 0;
    /* access modifiers changed from: private */
    public int e = 0;
    @BindView(2131493299)
    EmptyLoadingViewPlus emptyError;
    private boolean f;
    /* access modifiers changed from: private */
    public ReviewTipsDialog g;
    @BindView(2131493673)
    LinearLayout llNoneReview;
    @BindView(2131493681)
    LinearLayout llTips;
    @BindView(2131493691)
    LinearLayout loading;
    @BindView(2131493946)
    RecyclerView reviewRecycleView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_order_review_list);
        ButterKnife.bind((Activity) this);
        setTitle((CharSequence) getString(R.string.account_reviews));
        initTitleBar();
        initView();
        this.loading.setVisibility(0);
        a();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void initTitleBar() {
        this.mBackView.setVisibility(0);
        findViewById(R.id.title_bar_cart_view).setVisibility(4);
    }

    public void initView() {
        this.reviewRecycleView.setLayoutManager(new LinearLayoutManager(this));
        this.b = new ReviewOrderListAdapter(this, this);
        this.b.a((ReviewOrderListAdapter.OnLoadMoreListener) this);
        this.reviewRecycleView.addItemDecoration(new RecycleViewDivider(this, 0, Coder.a(0.5f), getResources().getColor(R.color.divider_color)));
        this.reviewRecycleView.setAdapter(this.b);
        this.llTips.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ReviewTipsDialog unused = ReviewListAcitvity.this.g = new ReviewTipsDialog.Builder(ReviewListAcitvity.this).a();
                ReviewListAcitvity.this.g.show();
                MiShopStatInterface.a("review_banner", ReviewListAcitvity.f5428a);
            }
        });
    }

    private void a() {
        this.llNoneReview.setVisibility(8);
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aU()).buildUpon();
        buildUpon.appendQueryParameter("pageindex", this.c + "");
        if (Setting.a()) {
            buildUpon.appendQueryParameter("_network_type", NetworkUtil.a());
        }
        buildUpon.appendQueryParameter("name", "pages_index");
        buildUpon.appendQueryParameter("ot", "5");
        MiJsonObjectRequest miJsonObjectRequest = new MiJsonObjectRequest(0, buildUpon.toString(), new Response.Listener<JSONObject>() {
            /* renamed from: a */
            public void onResponse(JSONObject jSONObject) {
                int unused = ReviewListAcitvity.this.e = 0;
                if (jSONObject == null) {
                    try {
                        LogUtil.b(ReviewListAcitvity.f5428a, "get response json null");
                        ReviewListAcitvity.this.b();
                    } catch (Exception e) {
                        String access$100 = ReviewListAcitvity.f5428a;
                        LogUtil.b(access$100, "loadInfo Exception:" + e.toString());
                        e.printStackTrace();
                        ReviewListAcitvity.this.b();
                    }
                } else {
                    String access$1002 = ReviewListAcitvity.f5428a;
                    LogUtil.b(access$1002, "get response json:" + jSONObject.toString());
                    try {
                        if (!jSONObject.has(Request.RESULT_CODE_KEY) || jSONObject.getInt(Request.RESULT_CODE_KEY) != 0) {
                            RequestUtil.a((Context) ReviewListAcitvity.this, jSONObject);
                            ReviewListAcitvity.this.b();
                            return;
                        }
                        LogUtil.b(ReviewListAcitvity.f5428a, "loadInfo errno = 0");
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        LogUtil.b(ReviewListAcitvity.f5428a, "Parse json reuslt");
                        ReviewListAcitvity.this.a((OrderReviewModel) new Gson().fromJson(optJSONObject.toString(), OrderReviewModel.class));
                    } catch (Exception e2) {
                        String access$1003 = ReviewListAcitvity.f5428a;
                        LogUtil.b(access$1003, "loadInfo Exception:" + e2.toString());
                        e2.printStackTrace();
                        ReviewListAcitvity.this.b();
                    }
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                int unused = ReviewListAcitvity.this.e = 0;
                String access$100 = ReviewListAcitvity.f5428a;
                LogUtil.b(access$100, "VolleyError error:" + volleyError.getMessage());
                ReviewListAcitvity.this.b();
            }
        });
        miJsonObjectRequest.setTag(f5428a);
        RequestQueueUtil.a().add(miJsonObjectRequest);
        this.e = 1;
    }

    /* access modifiers changed from: private */
    public void a(OrderReviewModel orderReviewModel) {
        this.c++;
        checkMoreState((long) orderReviewModel.page_total);
        this.loading.setVisibility(8);
        if (this.emptyError != null) {
            this.emptyError.setVisibility(8);
        }
        if (orderReviewModel.goodsList == null || orderReviewModel.goodsList.size() <= 0) {
            this.llNoneReview.setVisibility(0);
        } else {
            this.b.b(orderReviewModel.goodsList);
        }
    }

    public void checkMoreState(long j) {
        if (this.c > j) {
            this.d = 1;
            this.c = j + 1;
            return;
        }
        this.d = 0;
    }

    public void checkLoadMore() {
        if (this.d == 0 && this.e == 0) {
            a();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.loading.setVisibility(8);
        if (this.emptyError != null) {
            this.emptyError.setVisibility(0);
            this.emptyError.onError(false, BaseResult.ResultStatus.NETWROK_ERROR, (Handler.Callback) null);
            MiToast.a((Context) this, (CharSequence) getString(R.string.shop_network_unavaliable), 3000);
            finish();
        }
    }

    public void onLoadMore() {
        checkLoadMore();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && i == 100 && intent.getIntExtra("submit_success", 0) == 102) {
            this.b.a(intent.getStringExtra("order_item_id"));
        }
    }
}
