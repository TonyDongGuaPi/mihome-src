package com.mi.global.shop.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.imageselector.MultiImageSelector;
import com.mi.global.shop.imageselector.adapter.ImageAdapter;
import com.mi.global.shop.request.MiJsonObjectRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.RequestUtil;
import com.mi.global.shop.util.UploadUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.dialog.ReviewSubmitCancelDialog;
import com.mi.log.LogUtil;
import com.mi.multimonitor.Request;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.mi.util.permission.Permission;
import com.mi.util.permission.PermissionCallback;
import com.mi.util.permission.PermissionUtil;
import com.mi.util.permission.SamplePermissionCallback;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class ReviewSubmitAcitvity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final int REQUEST_CROP = 1000;
    public static final int REQUEST_IMAGE = 999;
    public static final int RESULT_CROP = 101;
    public static final int RESULT_DELETE = 100;
    public static final int SUBMIT_SUCCESS = 102;

    /* renamed from: a  reason: collision with root package name */
    private static final String f5433a = "ReviewSubmitAcitvity";
    /* access modifiers changed from: private */
    public ImageAdapter b;
    @BindView(2131493038)
    CustomButtonView btnSubmit;
    /* access modifiers changed from: private */
    public ReviewSubmitCancelDialog c;
    private ProgressDialog d;
    @BindView(2131493323)
    CustomEditTextView etReviewContent;
    @BindView(2131493325)
    CustomEditTextView etTitle;
    @BindView(2131493521)
    RatingBar itemRating;
    @BindView(2131493523)
    CustomTextView itemTitle;
    @BindView(2131493558)
    SimpleDraweeView ivOrder;
    @BindView(2131493691)
    LinearLayout loading;
    @BindView(2131493397)
    GridView mGridView;
    @BindView(2131493466)
    CustomTextView mImageCountTipView;
    @BindView(2131494156)
    LinearLayout topReview;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_order_review_submit);
        ButterKnife.bind((Activity) this);
        this.mBackView.setVisibility(0);
        this.mCartView.setVisibility(8);
        initValue();
        this.b = new ImageAdapter(this, 4);
        this.mGridView.setAdapter(this.b);
        this.mGridView.setOnItemClickListener(this);
    }

    public void initValue() {
        if (getIntent().getStringExtra("order_item_id") == null || getIntent().getStringExtra("goods_sku") == null || getIntent().getStringExtra("goods_name") == null || getIntent().getStringExtra("goods_img") == null) {
            MiToast.a((Context) this, (CharSequence) getString(R.string.error_invalid_order), 3000);
            finish();
        }
        String stringExtra = getIntent().getStringExtra("goods_name");
        setTitle((CharSequence) stringExtra + " review");
        this.itemTitle.setText(stringExtra);
        FrescoUtils.a(getIntent().getStringExtra("goods_img"), this.ivOrder);
        this.itemRating.setRating(5.0f);
        checkStar(this.itemRating.getRating());
        this.itemRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                ReviewSubmitAcitvity.this.itemRating.setRating(f);
                ReviewSubmitAcitvity.this.checkStar(f);
            }
        });
        this.btnSubmit.setOnClickListener(this);
        this.mBackView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ReviewSubmitCancelDialog.Builder builder = new ReviewSubmitCancelDialog.Builder(ReviewSubmitAcitvity.this);
                builder.a((DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ReviewSubmitAcitvity.this.finish();
                    }
                });
                ReviewSubmitCancelDialog unused = ReviewSubmitAcitvity.this.c = builder.a();
                ReviewSubmitAcitvity.this.c.show();
            }
        });
        this.d = new ProgressDialog(this);
        this.d.setMessage(getString(R.string.please_wait));
        this.d.setIndeterminate(true);
        this.d.setCancelable(false);
        this.d.setCanceledOnTouchOutside(false);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 999:
                if (i2 == -1) {
                    this.b.a((List<String>) intent.getStringArrayListExtra("select_result"));
                    break;
                }
                break;
            case 1000:
                break;
        }
        if (intent != null) {
            if (i2 == 100 && !TextUtils.isEmpty(intent.getStringExtra("deleteUrl"))) {
                String stringExtra = intent.getStringExtra("deleteUrl");
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.b.a());
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    if (((String) arrayList.get(size)).equals(stringExtra)) {
                        arrayList.remove(size);
                    }
                }
                this.b.a((List<String>) arrayList);
            }
            if (i2 == 101 && !TextUtils.isEmpty(intent.getStringExtra("currentPath")) && !TextUtils.isEmpty(intent.getStringExtra("newPath"))) {
                String stringExtra2 = intent.getStringExtra("currentPath");
                String stringExtra3 = intent.getStringExtra("newPath");
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.b.a());
                for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
                    if (((String) arrayList2.get(size2)).equals(stringExtra2)) {
                        arrayList2.remove(size2);
                        arrayList2.add(stringExtra3);
                    }
                }
                this.b.a((List<String>) arrayList2);
            }
        }
        if (this.b.a() == null || this.b.a().size() <= 0) {
            this.mImageCountTipView.setVisibility(0);
        } else {
            this.mImageCountTipView.setVisibility(8);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_submit) {
            checkData();
        }
    }

    public void checkStar(float f) {
        if (f >= 3.0f && f < 4.0f) {
            this.etTitle.setText(getString(R.string.default_three_star));
        } else if (f >= 4.0f && f < 5.0f) {
            this.etTitle.setText(getString(R.string.default_four_star));
        } else if (f == 5.0f) {
            this.etTitle.setText(getString(R.string.default_fine_star));
        } else {
            this.etTitle.setText("");
        }
    }

    public void checkData() {
        if (this.etTitle.getText().toString().trim().isEmpty()) {
            MiToast.a((Context) this, (CharSequence) getString(R.string.review_title_cannot_empty), 3000);
        } else if (this.etReviewContent.getText().toString().trim().isEmpty()) {
            MiToast.a((Context) this, (CharSequence) getString(R.string.content_cannot_empty), 3000);
        } else {
            uploadImage();
        }
    }

    public void uploadImage() {
        ArrayList arrayList = new ArrayList();
        for (String add : this.b.a()) {
            arrayList.add(add);
        }
        showLoading();
        if (arrayList.size() > 0) {
            UploadUtil.a(arrayList, new UploadUtil.UploadCallback() {
                public void a(ArrayList<String> arrayList) {
                    ReviewSubmitAcitvity.this.doSubmit(arrayList);
                }

                public void a(String str) {
                    MiToast.a((Context) ReviewSubmitAcitvity.this, (CharSequence) ReviewSubmitAcitvity.this.getString(R.string.shop_network_unavaliable), 3000);
                    ReviewSubmitAcitvity.this.hideLoading();
                }
            });
        } else {
            doSubmit(new ArrayList());
        }
    }

    public void doSubmit(ArrayList<String> arrayList) {
        this.btnSubmit.setEnabled(false);
        HashMap hashMap = new HashMap();
        hashMap.put("goods_id", getIntent().getStringExtra("goods_sku"));
        hashMap.put("order_item_id", getIntent().getStringExtra("order_item_id"));
        hashMap.put("total_grade", ((int) this.itemRating.getRating()) + "");
        hashMap.put("comment_title", this.etTitle.getText().toString().trim());
        hashMap.put("comment_content", this.etReviewContent.getText().toString().trim());
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                hashMap.put("comment_pictures[" + i + Operators.ARRAY_END_STR, arrayList.get(i));
            }
        }
        hashMap.put("ot", "5");
        MiJsonObjectRequest miJsonObjectRequest = new MiJsonObjectRequest(1, ConnectionHelper.aW(), RequestUtil.a(RequestUtil.a((Map<String, String>) hashMap, true)), new Response.Listener<JSONObject>() {
            /* renamed from: a */
            public void onResponse(JSONObject jSONObject) {
                ReviewSubmitAcitvity.this.btnSubmit.setEnabled(true);
                ReviewSubmitAcitvity.this.hideLoading();
                if (jSONObject == null) {
                    try {
                        LogUtil.b(ReviewSubmitAcitvity.f5433a, "get response json null");
                        ReviewSubmitAcitvity.this.submitError();
                    } catch (Exception e) {
                        LogUtil.b(ReviewSubmitAcitvity.f5433a, "loadInfo Exception:" + e.toString());
                        e.printStackTrace();
                        ReviewSubmitAcitvity.this.submitError();
                    }
                } else {
                    LogUtil.b(ReviewSubmitAcitvity.f5433a, "get response json:" + jSONObject.toString());
                    try {
                        if (!jSONObject.has(Request.RESULT_CODE_KEY) || jSONObject.getInt(Request.RESULT_CODE_KEY) != 0) {
                            MiToast.a((Context) ReviewSubmitAcitvity.this, (CharSequence) jSONObject.optString("errmsg"), 3000);
                            return;
                        }
                        LogUtil.b(ReviewSubmitAcitvity.f5433a, "loadInfo errno = 0");
                        jSONObject.optJSONObject("data");
                        LogUtil.b(ReviewSubmitAcitvity.f5433a, "Parse json reuslt");
                        MiToast.a((Context) ReviewSubmitAcitvity.this, (CharSequence) ReviewSubmitAcitvity.this.getString(R.string.submit_success), 3000);
                        Intent intent = new Intent();
                        intent.putExtra("submit_success", 102);
                        intent.putExtra("order_item_id", ReviewSubmitAcitvity.this.getIntent().getStringExtra("order_item_id"));
                        ReviewSubmitAcitvity.this.setResult(-1, intent);
                        ReviewSubmitAcitvity.this.finish();
                    } catch (Exception e2) {
                        LogUtil.b(ReviewSubmitAcitvity.f5433a, "loadInfo Exception:" + e2.toString());
                        e2.printStackTrace();
                        ReviewSubmitAcitvity.this.submitError();
                    }
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                ReviewSubmitAcitvity.this.hideLoading();
                ReviewSubmitAcitvity.this.btnSubmit.setEnabled(true);
                LogUtil.b(ReviewSubmitAcitvity.f5433a, "VolleyError error:" + volleyError.getMessage());
                ReviewSubmitAcitvity.this.submitError();
            }
        });
        miJsonObjectRequest.setTag(f5433a);
        RequestQueueUtil.a().add(miJsonObjectRequest);
    }

    public void submitError() {
        MiToast.a((Context) this, (CharSequence) getString(R.string.shop_error_network), 3000);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i == this.b.a().size()) {
            PermissionUtil.a((Activity) this, (PermissionCallback) new SamplePermissionCallback() {
                public void onGranted() {
                    MultiImageSelector.a().a(5).a((ArrayList<String>) (ArrayList) ReviewSubmitAcitvity.this.b.a()).a(ReviewSubmitAcitvity.this, 999);
                }

                public void onDenied() {
                    PermissionUtil.a((Context) ReviewSubmitAcitvity.this, ReviewSubmitAcitvity.this.getString(R.string.permission_storage_error));
                }
            }, Permission.Group.i);
            return;
        }
        Intent intent = new Intent(this, ReviewImageEditActivity.class);
        intent.putExtra("path", this.b.a().get(i));
        startActivityForResult(intent, 1000);
    }

    public void showLoading() {
        if (this.d != null) {
            this.d.show();
        }
    }

    public void hideLoading() {
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
        }
    }
}
