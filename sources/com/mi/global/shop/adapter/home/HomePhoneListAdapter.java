package com.mi.global.shop.adapter.home;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.newmodel.home.NewHomeBlockInfoItem;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.home.HomeThemeItemClick;
import java.util.ArrayList;
import java.util.List;

public class HomePhoneListAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f5568a;
    private List<NewHomeBlockInfoItem> b = new ArrayList();

    public class ProductViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ProductViewHolder f5570a;

        @UiThread
        public ProductViewHolder_ViewBinding(ProductViewHolder productViewHolder, View view) {
            this.f5570a = productViewHolder;
            productViewHolder.productName = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.product_name, "field 'productName'", CustomTextView.class);
            productViewHolder.productImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.product_image, "field 'productImage'", SimpleDraweeView.class);
        }

        @CallSuper
        public void unbind() {
            ProductViewHolder productViewHolder = this.f5570a;
            if (productViewHolder != null) {
                this.f5570a = null;
                productViewHolder.productName = null;
                productViewHolder.productImage = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public HomePhoneListAdapter(Context context) {
        this.f5568a = context;
    }

    public void a(List<NewHomeBlockInfoItem> list) {
        if (list != null) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {
        final NewHomeBlockInfoItem newHomeBlockInfoItem = this.b.get(i);
        productViewHolder.productName.setText(newHomeBlockInfoItem.mProductName);
        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeThemeItemClick.a(HomePhoneListAdapter.this.f5568a, newHomeBlockInfoItem);
            }
        });
        HomeThemeItemClick.a(newHomeBlockInfoItem);
        String imageUrl = newHomeBlockInfoItem.getImageUrl();
        if (!TextUtils.isEmpty(imageUrl) && !imageUrl.equals(productViewHolder.productImage.getTag())) {
            productViewHolder.productImage.setTag(imageUrl);
            FrescoUtils.a(imageUrl, productViewHolder.productImage);
        }
    }

    /* renamed from: a */
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ProductViewHolder(LayoutInflater.from(this.f5568a).inflate(R.layout.shop_phone_list_item, viewGroup, false));
    }

    public int getItemCount() {
        return this.b.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131493887)
        SimpleDraweeView productImage;
        @BindView(2131493893)
        CustomTextView productName;

        ProductViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
