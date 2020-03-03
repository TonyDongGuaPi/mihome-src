package com.mi.global.shop.adapter.cart;

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
import com.mi.global.shop.newmodel.cart.NewCartItem;
import com.mi.global.shop.util.ImageUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.util.Coder;

public class CartInvalidItemListAdapter extends ArrayAdapter<NewCartItem> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5518a = "CartInvalidItemListAdapter";

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f5520a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f5520a = viewHolder;
            viewHolder.item_image = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.item_image, "field 'item_image'", SimpleDraweeView.class);
            viewHolder.item_title = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_title, "field 'item_title'", CustomTextView.class);
            viewHolder.item_num = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_num, "field 'item_num'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f5520a;
            if (viewHolder != null) {
                this.f5520a = null;
                viewHolder.item_image = null;
                viewHolder.item_title = null;
                viewHolder.item_num = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public CartInvalidItemListAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, NewCartItem newCartItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_cart_list_invalid_item, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void a(View view, int i, NewCartItem newCartItem) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int a2 = Coder.a(40.0f);
        int a3 = Coder.a(40.0f);
        String str = newCartItem.image_url;
        if (!TextUtils.isEmpty(str)) {
            str = ImageUtil.a(a2, a3, str);
        }
        FrescoUtils.a(str, viewHolder.item_image);
        viewHolder.item_title.setText(newCartItem.product_name);
        CustomTextView customTextView = viewHolder.item_num;
        customTextView.setText("X" + newCartItem.num);
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f5519a;
        @BindView(2131493513)
        SimpleDraweeView item_image;
        @BindView(2131493518)
        CustomTextView item_num;
        @BindView(2131493523)
        CustomTextView item_title;

        public ViewHolder(View view) {
            view.setTag(this);
            this.f5519a = view;
            ButterKnife.bind((Object) this, view);
        }
    }
}
