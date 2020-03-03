package com.mi.global.shop.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.newmodel.order.NewListProduct;
import com.mi.global.shop.util.ImageUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.util.Coder;

public class OrderItemListAdapter extends ArrayAdapter<NewListProduct> {

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f5493a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f5493a = viewHolder;
            viewHolder.image = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.image, "field 'image'", SimpleDraweeView.class);
            viewHolder.name = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.name, "field 'name'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f5493a;
            if (viewHolder != null) {
                this.f5493a = null;
                viewHolder.image = null;
                viewHolder.name = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public OrderItemListAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, NewListProduct newListProduct, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_order_item_product_item, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void a(View view, int i, NewListProduct newListProduct) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int a2 = Coder.a(50.0f);
        int a3 = Coder.a(50.0f);
        String str = newListProduct.image_url;
        if (!TextUtils.isEmpty(str)) {
            FrescoUtils.a(ImageUtil.a(a2, a3, str), viewHolder.image);
        }
        viewHolder.name.setText(newListProduct.product_name);
    }

    static class ViewHolder {
        @BindView(2131493452)
        SimpleDraweeView image;
        @BindView(2131493728)
        CustomTextView name;

        public ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }
    }
}
