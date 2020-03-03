package com.mi.global.shop.adapter.cart;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.ShoppingCartActivity;
import com.mi.global.shop.activity.ShoppingCartInsuranceActivity;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.cart.NewCartItem;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.ImageUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.AddAndSubView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.EasyTextView;
import com.mi.log.LogUtil;
import com.mi.util.Coder;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Iterator;

public class CartItemListAdapter extends ArrayAdapter<NewCartItem> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5521a = "CartItemListAdapter";

    public int getViewTypeCount() {
        return 2;
    }

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f5525a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f5525a = viewHolder;
            viewHolder.item_del = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_del, "field 'item_del'", ImageView.class);
            viewHolder.item_image = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.item_image, "field 'item_image'", SimpleDraweeView.class);
            viewHolder.item_type = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_type, "field 'item_type'", CustomTextView.class);
            viewHolder.item_title = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_title, "field 'item_title'", CustomTextView.class);
            viewHolder.item_price = (EasyTextView) Utils.findRequiredViewAsType(view, R.id.item_price, "field 'item_price'", EasyTextView.class);
            viewHolder.item_limit = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_limit, "field 'item_limit'", CustomTextView.class);
            viewHolder.item_warning = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_warning, "field 'item_warning'", CustomTextView.class);
            viewHolder.item_dealer = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_dealer, "field 'item_dealer'", CustomTextView.class);
            viewHolder.item_warning_layout = Utils.findRequiredView(view, R.id.item_warning_layout, "field 'item_warning_layout'");
            viewHolder.item_mask = Utils.findRequiredView(view, R.id.item_mask, "field 'item_mask'");
            viewHolder.item_num = (AddAndSubView) Utils.findRequiredViewAsType(view, R.id.item_num, "field 'item_num'", AddAndSubView.class);
            viewHolder.insurance_layout = Utils.findRequiredView(view, R.id.insurance_layout, "field 'insurance_layout'");
            viewHolder.insurance_buy_layout = Utils.findRequiredView(view, R.id.insurance_buy_layout, "field 'insurance_buy_layout'");
            viewHolder.insurance_image = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.insurance_image, "field 'insurance_image'", SimpleDraweeView.class);
            viewHolder.insurance_info = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.insurance_info, "field 'insurance_info'", CustomTextView.class);
            viewHolder.insurance_price = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.insurance_price, "field 'insurance_price'", CustomTextView.class);
            viewHolder.insurance_market_price = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.insurance_market_price, "field 'insurance_market_price'", CustomTextView.class);
            viewHolder.insurance_buy_btn = (ImageButton) Utils.findRequiredViewAsType(view, R.id.insurance_buy_btn, "field 'insurance_buy_btn'", ImageButton.class);
            viewHolder.insurance_item_layout = Utils.findRequiredView(view, R.id.insurance_item_layout, "field 'insurance_item_layout'");
            viewHolder.insurance_item_image = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.insurance_item_image, "field 'insurance_item_image'", SimpleDraweeView.class);
            viewHolder.insurance_item_del = (ImageView) Utils.findRequiredViewAsType(view, R.id.insurance_item_del, "field 'insurance_item_del'", ImageView.class);
            viewHolder.insurance_item_title = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.insurance_item_title, "field 'insurance_item_title'", CustomTextView.class);
            viewHolder.insurance_item_price = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.insurance_item_price, "field 'insurance_item_price'", CustomTextView.class);
            viewHolder.insurance_item_dealer = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.insurance_item_dealer, "field 'insurance_item_dealer'", CustomTextView.class);
            viewHolder.insurance_item_num = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.insurance_item_num, "field 'insurance_item_num'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f5525a;
            if (viewHolder != null) {
                this.f5525a = null;
                viewHolder.item_del = null;
                viewHolder.item_image = null;
                viewHolder.item_type = null;
                viewHolder.item_title = null;
                viewHolder.item_price = null;
                viewHolder.item_limit = null;
                viewHolder.item_warning = null;
                viewHolder.item_dealer = null;
                viewHolder.item_warning_layout = null;
                viewHolder.item_mask = null;
                viewHolder.item_num = null;
                viewHolder.insurance_layout = null;
                viewHolder.insurance_buy_layout = null;
                viewHolder.insurance_image = null;
                viewHolder.insurance_info = null;
                viewHolder.insurance_price = null;
                viewHolder.insurance_market_price = null;
                viewHolder.insurance_buy_btn = null;
                viewHolder.insurance_item_layout = null;
                viewHolder.insurance_item_image = null;
                viewHolder.insurance_item_del = null;
                viewHolder.insurance_item_title = null;
                viewHolder.insurance_item_price = null;
                viewHolder.insurance_item_dealer = null;
                viewHolder.insurance_item_num = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public CartItemListAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, NewCartItem newCartItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_cart_list_item, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void a(View view, int i, NewCartItem newCartItem) {
        MyOnNumChangeListener myOnNumChangeListener = new MyOnNumChangeListener(newCartItem, this.d);
        MyListener myListener = new MyListener(newCartItem, this.d);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int a2 = Coder.a(90.0f);
        int a3 = Coder.a(90.0f);
        String str = newCartItem.image_url;
        if (!TextUtils.isEmpty(str)) {
            str = ImageUtil.a(a2, a3, str);
        }
        FrescoUtils.a(str, viewHolder.item_image);
        newCartItem.timeout = false;
        if (newCartItem.item_timeout) {
            newCartItem.timeout = true;
        }
        viewHolder.item_title.setText(newCartItem.product_name);
        if (TextUtils.isEmpty(newCartItem.item_type_name)) {
            viewHolder.item_type.setVisibility(8);
        } else {
            viewHolder.item_type.setText(newCartItem.item_type_name);
            viewHolder.item_type.setVisibility(0);
        }
        if (TextUtils.isEmpty(newCartItem.goods_dealer)) {
            viewHolder.item_dealer.setVisibility(8);
        } else {
            viewHolder.item_dealer.setText(String.format(ShopApp.g().getString(R.string.goods_dealer), new Object[]{newCartItem.goods_dealer}));
        }
        viewHolder.item_price.setPrize(newCartItem);
        if (newCartItem.getType.equalsIgnoreCase("seckill") || newCartItem.buy_limit == 1 || newCartItem.getType.equalsIgnoreCase("fcode")) {
            viewHolder.item_limit.setText(String.format(ShopApp.g().getString(R.string.cart_limt), new Object[]{String.valueOf(newCartItem.num)}));
            viewHolder.item_limit.setVisibility(0);
            viewHolder.item_num.setVisibility(8);
        } else if (newCartItem.is_cos || newCartItem.timeout || newCartItem.getType.equalsIgnoreCase("insurance")) {
            viewHolder.item_num.setVisibility(8);
            viewHolder.item_limit.setVisibility(8);
        } else {
            viewHolder.item_num.setVisibility(0);
            viewHolder.item_limit.setVisibility(8);
            viewHolder.item_num.setMax(newCartItem.buy_limit);
            viewHolder.item_num.setMin(1);
            viewHolder.item_num.setNum(newCartItem.num);
            viewHolder.item_num.setOnNumChangeListener(myOnNumChangeListener);
        }
        if (newCartItem.timeout) {
            viewHolder.item_warning_layout.setVisibility(0);
            viewHolder.item_warning.setText(ShopApp.g().getString(R.string.cart_timeout));
            a(viewHolder, true);
        } else if (newCartItem.is_cos) {
            viewHolder.item_warning_layout.setVisibility(0);
            viewHolder.item_warning.setText(ShopApp.g().getString(R.string.cart_iscos));
            a(viewHolder, true);
        } else if (newCartItem.showType.equalsIgnoreCase("bigtap")) {
            viewHolder.item_warning_layout.setVisibility(0);
            viewHolder.item_warning.setText(String.format(ShopApp.g().getString(R.string.cart_bigtapwarning), new Object[]{LocaleHelper.a(Long.valueOf(newCartItem.cartTTL * 1000))}));
            a(viewHolder, false);
        } else {
            viewHolder.item_warning_layout.setVisibility(8);
            a(viewHolder, false);
        }
        if (newCartItem.properties == null || newCartItem.properties.insurance == null) {
            viewHolder.insurance_layout.setVisibility(8);
        } else {
            viewHolder.insurance_layout.setVisibility(0);
            if (TextUtils.isEmpty(newCartItem.properties.insurance.itemId)) {
                viewHolder.insurance_buy_layout.setVisibility(0);
                viewHolder.insurance_item_layout.setVisibility(8);
                String str2 = newCartItem.properties.insurance.img_insurance;
                if (!TextUtils.isEmpty(str2)) {
                    str2 = ImageUtil.a(Coder.a(this.d, 30.0f), Coder.a(this.d, 30.0f), str2);
                }
                FrescoUtils.a(str2, viewHolder.insurance_image);
                viewHolder.insurance_price.setText(Html.fromHtml(String.format("<font color=\"#ff6600\">%s</font>", new Object[]{LocaleHelper.e() + " " + newCartItem.properties.insurance.price + Operators.BRACKET_START_STR + newCartItem.properties.insurance.validperiod + Operators.BRACKET_END_STR})));
                if (TextUtils.isEmpty(newCartItem.properties.insurance.market_price) || newCartItem.properties.insurance.market_price.equals(newCartItem.properties.insurance.price)) {
                    viewHolder.insurance_market_price.setVisibility(8);
                } else {
                    viewHolder.insurance_market_price.setVisibility(0);
                    CustomTextView customTextView = viewHolder.insurance_market_price;
                    customTextView.setText(LocaleHelper.e() + " " + newCartItem.properties.insurance.market_price);
                    viewHolder.insurance_market_price.getPaint().setFlags(16);
                }
                viewHolder.insurance_info.setText(newCartItem.properties.insurance.insurance_desc);
                viewHolder.insurance_buy_btn.setOnClickListener(myListener);
            } else {
                viewHolder.insurance_buy_layout.setVisibility(8);
                viewHolder.insurance_item_layout.setVisibility(0);
                String str3 = newCartItem.properties.insurance.image_url;
                if (!TextUtils.isEmpty(str3)) {
                    str3 = ImageUtil.a(a2, a3, str3);
                }
                FrescoUtils.a(str3, viewHolder.insurance_item_image);
                viewHolder.insurance_item_title.setText(newCartItem.properties.insurance.product_name);
                viewHolder.insurance_item_dealer.setText(String.format(ShopApp.g().getString(R.string.goods_dealer), new Object[]{newCartItem.properties.insurance.goods_dealer}));
                CustomTextView customTextView2 = viewHolder.insurance_item_price;
                customTextView2.setText(LocaleHelper.e() + newCartItem.properties.insurance.price);
                CustomTextView customTextView3 = viewHolder.insurance_item_num;
                customTextView3.setText("X" + newCartItem.properties.insurance.num);
                viewHolder.insurance_item_del.setOnClickListener(myListener);
            }
        }
        viewHolder.item_del.setOnClickListener(myListener);
        viewHolder.item_image.setOnClickListener(myListener);
    }

    public void a(ArrayList<NewCartItem> arrayList) {
        if (arrayList != null) {
            this.f = true;
            Iterator<NewCartItem> it = arrayList.iterator();
            while (it.hasNext()) {
                NewCartItem next = it.next();
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

    private void a(ViewHolder viewHolder, Boolean bool) {
        if (bool.booleanValue()) {
            viewHolder.item_num.setEnable(false);
            viewHolder.item_mask.setVisibility(0);
            return;
        }
        viewHolder.item_num.setEnable(true);
        viewHolder.item_mask.setVisibility(8);
    }

    public int getItemViewType(int i) {
        return (((NewCartItem) this.e.get(i)).properties == null || ((NewCartItem) this.e.get(i)).properties.insurance == null || TextUtils.isEmpty(((NewCartItem) this.e.get(i)).properties.insurance.itemId)) ? 0 : 1;
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f5524a;
        @BindView(2131493480)
        ImageButton insurance_buy_btn;
        @BindView(2131493481)
        View insurance_buy_layout;
        @BindView(2131493486)
        SimpleDraweeView insurance_image;
        @BindView(2131493487)
        CustomTextView insurance_info;
        @BindView(2131493488)
        CustomTextView insurance_item_dealer;
        @BindView(2131493489)
        ImageView insurance_item_del;
        @BindView(2131493490)
        SimpleDraweeView insurance_item_image;
        @BindView(2131493491)
        View insurance_item_layout;
        @BindView(2131493492)
        CustomTextView insurance_item_num;
        @BindView(2131493493)
        CustomTextView insurance_item_price;
        @BindView(2131493494)
        CustomTextView insurance_item_title;
        @BindView(2131493495)
        View insurance_layout;
        @BindView(2131493496)
        CustomTextView insurance_market_price;
        @BindView(2131493499)
        CustomTextView insurance_price;
        @BindView(2131493509)
        CustomTextView item_dealer;
        @BindView(2131493510)
        ImageView item_del;
        @BindView(2131493513)
        SimpleDraweeView item_image;
        @BindView(2131493514)
        CustomTextView item_limit;
        @BindView(2131493516)
        View item_mask;
        @BindView(2131493518)
        AddAndSubView item_num;
        @BindView(2131493519)
        EasyTextView item_price;
        @BindView(2131493523)
        CustomTextView item_title;
        @BindView(2131493525)
        CustomTextView item_type;
        @BindView(2131493526)
        CustomTextView item_warning;
        @BindView(2131493527)
        View item_warning_layout;

        public ViewHolder(View view) {
            view.setTag(this);
            this.f5524a = view;
            ButterKnife.bind((Object) this, view);
        }
    }

    private class MyOnNumChangeListener implements AddAndSubView.OnNumChangeListener {

        /* renamed from: a  reason: collision with root package name */
        NewCartItem f5523a;
        Context b;

        public MyOnNumChangeListener(NewCartItem newCartItem, Context context) {
            this.f5523a = newCartItem;
            this.b = context;
        }

        public void a(View view, int i) {
            LogUtil.b(CartItemListAdapter.f5521a, "item:" + this.f5523a.product_name + " num:" + i);
            ((ShoppingCartActivity) this.b).updateCartRequest(this.f5523a.itemId, i);
        }
    }

    private class MyListener implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        NewCartItem f5522a;
        Context b;

        public MyListener(NewCartItem newCartItem, Context context) {
            this.f5522a = newCartItem;
            this.b = context;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.item_del) {
                LogUtil.b(CartItemListAdapter.f5521a, "del item:" + this.f5522a.product_name);
                ((ShoppingCartActivity) this.b).delItemDialog(this.f5522a.itemId);
            }
            if (view.getId() == R.id.insurance_item_del) {
                LogUtil.b(CartItemListAdapter.f5521a, "del item:" + this.f5522a.product_name);
                if (!(this.f5522a.properties == null || this.f5522a.properties.insurance == null || TextUtils.isEmpty(this.f5522a.properties.insurance.itemId))) {
                    ((ShoppingCartActivity) this.b).delItemDialog(this.f5522a.properties.insurance.itemId);
                }
            }
            if (view.getId() == R.id.insurance_buy_btn) {
                LogUtil.b(CartItemListAdapter.f5521a, "buy item:" + this.f5522a.product_name);
                Intent intent = new Intent(this.b, ShoppingCartInsuranceActivity.class);
                intent.putExtra("cart_insurance_parentid", this.f5522a.itemId);
                intent.putExtra("cart_insurance_goodsid", this.f5522a.properties.insurance.goods_id);
                ((ShoppingCartActivity) this.b).startActivityForResult(intent, 21);
            }
            if (view.getId() == R.id.item_image && !TextUtils.isEmpty(this.f5522a.commodity_id)) {
                Intent intent2 = new Intent(this.b, WebActivity.class);
                if (TextUtils.isEmpty(this.f5522a.jump_url)) {
                    intent2.putExtra("url", ConnectionHelper.d(this.f5522a.commodity_id));
                } else {
                    intent2.putExtra("url", this.f5522a.jump_url);
                }
                intent2.putExtra("cart_webview", true);
                ((ShoppingCartActivity) this.b).startActivityForResult(intent2, 23);
            }
        }
    }
}
