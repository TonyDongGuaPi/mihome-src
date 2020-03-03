package com.mi.global.shop.adapter.checkout;

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
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.checkout.NewCheckoutCartItem;
import com.mi.global.shop.util.ImageUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.util.Coder;
import java.util.List;

public class CheckoutListAdapter extends ArrayAdapter<NewCheckoutCartItem> {

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f5531a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f5531a = viewHolder;
            viewHolder.good_image = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.good_image, "field 'good_image'", SimpleDraweeView.class);
            viewHolder.good_name = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.good_name, "field 'good_name'", CustomTextView.class);
            viewHolder.good_price = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.good_price, "field 'good_price'", CustomTextView.class);
            viewHolder.good_dealer = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.good_dealer, "field 'good_dealer'", CustomTextView.class);
            viewHolder.good_num = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.good_num, "field 'good_num'", CustomTextView.class);
            viewHolder.good_type = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.good_type, "field 'good_type'", CustomTextView.class);
            viewHolder.insurance_content = Utils.findRequiredView(view, R.id.insurance_content, "field 'insurance_content'");
            viewHolder.insurance_image = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.insurance_image, "field 'insurance_image'", SimpleDraweeView.class);
            viewHolder.insurance_name = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.insurance_name, "field 'insurance_name'", CustomTextView.class);
            viewHolder.insurance_price = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.insurance_price, "field 'insurance_price'", CustomTextView.class);
            viewHolder.insurance_dealer = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.insurance_dealer, "field 'insurance_dealer'", CustomTextView.class);
            viewHolder.insurance_num = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.insurance_num, "field 'insurance_num'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f5531a;
            if (viewHolder != null) {
                this.f5531a = null;
                viewHolder.good_image = null;
                viewHolder.good_name = null;
                viewHolder.good_price = null;
                viewHolder.good_dealer = null;
                viewHolder.good_num = null;
                viewHolder.good_type = null;
                viewHolder.insurance_content = null;
                viewHolder.insurance_image = null;
                viewHolder.insurance_name = null;
                viewHolder.insurance_price = null;
                viewHolder.insurance_dealer = null;
                viewHolder.insurance_num = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public CheckoutListAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, NewCheckoutCartItem newCheckoutCartItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_checkout_list_item, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void a(View view, int i, NewCheckoutCartItem newCheckoutCartItem) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int a2 = Coder.a(50.0f);
        int a3 = Coder.a(50.0f);
        String str = newCheckoutCartItem.imageUrl;
        if (!TextUtils.isEmpty(str)) {
            str = ImageUtil.a(a2, a3, str);
        }
        FrescoUtils.a(str, viewHolder.good_image);
        viewHolder.good_name.setText(newCheckoutCartItem.name);
        CustomTextView customTextView = viewHolder.good_price;
        customTextView.setText(LocaleHelper.e() + " " + newCheckoutCartItem.price);
        CustomTextView customTextView2 = viewHolder.good_num;
        customTextView2.setText("X" + newCheckoutCartItem.num);
        viewHolder.good_dealer.setText(String.format(ShopApp.g().getString(R.string.goods_dealer), new Object[]{newCheckoutCartItem.goods_dealer}));
        if (TextUtils.isEmpty(newCheckoutCartItem.type)) {
            viewHolder.good_type.setVisibility(8);
        } else {
            viewHolder.good_type.setText(newCheckoutCartItem.type);
            viewHolder.good_type.setVisibility(0);
        }
        if (newCheckoutCartItem.properties == null || newCheckoutCartItem.properties.insurance == null || TextUtils.isEmpty(newCheckoutCartItem.properties.insurance.itemId)) {
            viewHolder.insurance_content.setVisibility(8);
            return;
        }
        viewHolder.insurance_content.setVisibility(0);
        String str2 = newCheckoutCartItem.properties.insurance.image_url;
        if (!TextUtils.isEmpty(str2)) {
            str2 = ImageUtil.a(a2, a3, str2);
        }
        FrescoUtils.a(str2, viewHolder.insurance_image);
        viewHolder.insurance_name.setText(newCheckoutCartItem.properties.insurance.product_name);
        viewHolder.insurance_dealer.setText(String.format(ShopApp.g().getString(R.string.goods_dealer), new Object[]{newCheckoutCartItem.properties.insurance.goods_dealer}));
        CustomTextView customTextView3 = viewHolder.insurance_price;
        customTextView3.setText(LocaleHelper.e() + newCheckoutCartItem.properties.insurance.price);
        CustomTextView customTextView4 = viewHolder.insurance_num;
        customTextView4.setText("X" + newCheckoutCartItem.properties.insurance.num);
    }

    public void a(List<NewCheckoutCartItem> list) {
        if (list != null) {
            this.f = true;
            this.e.clear();
            for (NewCheckoutCartItem next : list) {
                if (next != null && !next.isInsurance) {
                    this.e.add(next);
                }
            }
            notifyDataSetChanged();
            return;
        }
        this.f = false;
        notifyDataSetInvalidated();
    }

    static class ViewHolder {
        @BindView(2131493389)
        CustomTextView good_dealer;
        @BindView(2131493390)
        SimpleDraweeView good_image;
        @BindView(2131493391)
        CustomTextView good_name;
        @BindView(2131493392)
        CustomTextView good_num;
        @BindView(2131493393)
        CustomTextView good_price;
        @BindView(2131493394)
        CustomTextView good_type;
        @BindView(2131493484)
        View insurance_content;
        @BindView(2131493485)
        CustomTextView insurance_dealer;
        @BindView(2131493486)
        SimpleDraweeView insurance_image;
        @BindView(2131493497)
        CustomTextView insurance_name;
        @BindView(2131493498)
        CustomTextView insurance_num;
        @BindView(2131493499)
        CustomTextView insurance_price;

        public ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }
    }
}
