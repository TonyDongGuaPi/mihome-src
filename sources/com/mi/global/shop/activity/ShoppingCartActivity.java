package com.mi.global.shop.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.adapter.cart.CartBargainListAdapter;
import com.mi.global.shop.adapter.cart.CartGiftListAdapter;
import com.mi.global.shop.adapter.cart.CartItemListAdapter;
import com.mi.global.shop.adapter.cart.CartOfferListAdapter;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.cart.NewAddCartResult;
import com.mi.global.shop.newmodel.cart.NewCartData;
import com.mi.global.shop.newmodel.cart.NewCartGiftItem;
import com.mi.global.shop.newmodel.cart.NewCartItem;
import com.mi.global.shop.newmodel.cart.NewCartResult;
import com.mi.global.shop.newmodel.cart.NewCartSelectInfo;
import com.mi.global.shop.newmodel.cart.NewEditCartData;
import com.mi.global.shop.newmodel.cart.NewEditCartResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.widget.CommonButton;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;
import com.mi.global.shop.widget.dialog.CartDialogFragment;
import com.mi.global.shop.widget.dialog.CustomCancelDialog;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.util.ArrayList;
import java.util.Iterator;

public class ShoppingCartActivity extends BaseActivity implements View.OnClickListener, LoginManager.AccountListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5442a = "ShoppingCartActivity";
    /* access modifiers changed from: private */
    public NewCartData b;
    @BindView(2131493123)
    LinearLayout bargainLL;
    @BindView(2131493124)
    NoScrollListView bargainLV;
    private AppEventsLogger c;
    @BindView(2131493126)
    CustomTextView cartBottomTotal;
    @BindView(2131493125)
    View cartBottomView;
    @BindView(2131493140)
    CustomTextView cartTotal;
    @BindView(2131493127)
    CustomButtonView checkoutBtn;
    @BindView(2131493128)
    LinearLayout emptyLL;
    @BindView(2131493129)
    View giftDivider;
    @BindView(2131493130)
    NoScrollListView giftLV;
    @BindView(2131493132)
    NoScrollListView itemLV;
    @BindView(2131493133)
    LinearLayout loadingLL;
    public CartGiftListAdapter mCartGiftListAdapter;
    @BindView(2131494139)
    View mHomeView;
    @BindView(2131493134)
    View mainFrame;
    @BindView(2131493135)
    View offerDivider;
    @BindView(2131493136)
    NoScrollListView offerLV;
    @BindView(2131493907)
    View promoteLayout;
    @BindView(2131493137)
    CustomTextView promotionTV;
    @BindView(2131493138)
    CustomTextView shippingTV;
    @BindView(2131494068)
    View subtotalLayout;
    @BindView(2131493139)
    CustomTextView subtotalTV;
    @BindView(2131494157)
    View totalLayout;
    @BindView(2131493141)
    CommonButton viewBtn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_cart_activity);
        ButterKnife.bind((Activity) this);
        a();
        c();
    }

    private void a() {
        setTitle(R.string.cart_title);
        this.mCartView.setVisibility(4);
        this.mHomeView.setVisibility(0);
        this.mHomeView.setOnClickListener(this);
        this.viewBtn.setOnClickListener(this);
    }

    private void b() {
        this.loadingLL.setVisibility(8);
        this.emptyLL.setVisibility(8);
        this.mainFrame.setVisibility(8);
        this.cartBottomView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void a(NewCartData newCartData) {
        if (newCartData == null) {
            a(getString(R.string.shop_error_network));
            return;
        }
        b();
        if (newCartData.items == null || newCartData.items.isEmpty()) {
            this.emptyLL.setVisibility(0);
            updateShoppingCart(0);
            return;
        }
        String str = f5442a;
        LogUtil.b(str, "updateView items number:" + newCartData.items.size());
        updateShoppingCart(newCartData.items.size());
        for (int i = 0; i < newCartData.items.size(); i++) {
            if (newCartData.items.get(i).cartTTL == 0 || System.currentTimeMillis() <= newCartData.items.get(i).cartTTL * 1000) {
                newCartData.items.get(i).timeout = false;
            } else {
                newCartData.items.get(i).timeout = true;
            }
        }
        this.mainFrame.setVisibility(0);
        this.cartBottomView.setVisibility(0);
        CartItemListAdapter cartItemListAdapter = new CartItemListAdapter(this);
        cartItemListAdapter.c();
        cartItemListAdapter.a(newCartData.items);
        this.itemLV.setAdapter(cartItemListAdapter);
        if (newCartData.activitys == null || newCartData.activitys.gift == null || newCartData.activitys.gift.size() <= 0) {
            this.giftDivider.setVisibility(8);
            this.giftLV.setVisibility(8);
        } else {
            this.giftDivider.setVisibility(0);
            this.giftLV.setVisibility(0);
            this.mCartGiftListAdapter = new CartGiftListAdapter(this);
            this.mCartGiftListAdapter.c();
            this.mCartGiftListAdapter.a(newCartData.activitys.gift);
            this.giftLV.setAdapter(this.mCartGiftListAdapter);
        }
        if (newCartData.activitys == null || (newCartData.activitys.reduction == null && newCartData.activitys.postFree == null)) {
            this.offerDivider.setVisibility(8);
            this.offerLV.setVisibility(8);
        } else {
            ArrayList arrayList = new ArrayList();
            if (newCartData.activitys.reduction != null) {
                for (int i2 = 0; i2 < newCartData.activitys.reduction.size(); i2++) {
                    arrayList.add(newCartData.activitys.reduction.get(i2).actName);
                }
            }
            if (newCartData.activitys.postFree != null) {
                arrayList.add(newCartData.activitys.postFree.actName);
            }
            if (arrayList.size() > 0) {
                this.offerDivider.setVisibility(0);
                this.offerLV.setVisibility(0);
                CartOfferListAdapter cartOfferListAdapter = new CartOfferListAdapter(this);
                cartOfferListAdapter.c();
                cartOfferListAdapter.a(arrayList);
                this.offerLV.setAdapter(cartOfferListAdapter);
            } else {
                this.offerDivider.setVisibility(8);
                this.offerLV.setVisibility(8);
            }
        }
        if (((double) newCartData.postFreeBalance) <= 0.0d || newCartData.postFree) {
            this.shippingTV.setText(ShopApp.g().getString(R.string.cart_freedelivery));
        } else {
            String string = ShopApp.g().getString(R.string.cart_shippingfee);
            this.shippingTV.setText(String.format(string, new Object[]{LocaleHelper.e() + newCartData.postFreeBalance_txt}));
        }
        if (newCartData.bargains == null || newCartData.bargains.size() <= 0) {
            this.bargainLL.setVisibility(8);
        } else {
            CartBargainListAdapter cartBargainListAdapter = new CartBargainListAdapter(this);
            cartBargainListAdapter.c();
            cartBargainListAdapter.a(newCartData.bargains);
            this.bargainLV.setAdapter(cartBargainListAdapter);
            this.bargainLL.setVisibility(0);
        }
        CustomTextView customTextView = this.subtotalTV;
        customTextView.setText(LocaleHelper.e() + newCartData.productMoney_txt);
        if (TextUtils.isEmpty(newCartData.activityDiscountMoney_txt) || newCartData.activityDiscountMoney_txt.equalsIgnoreCase("0")) {
            this.promoteLayout.setVisibility(8);
        } else {
            this.promoteLayout.setVisibility(0);
            CustomTextView customTextView2 = this.promotionTV;
            customTextView2.setText(" -" + LocaleHelper.e() + newCartData.activityDiscountMoney_txt);
        }
        CustomTextView customTextView3 = this.cartTotal;
        customTextView3.setText(LocaleHelper.e() + newCartData.orderMoney_txt);
        CustomTextView customTextView4 = this.cartBottomTotal;
        customTextView4.setText("Total  " + LocaleHelper.e() + newCartData.orderMoney_txt);
        this.checkoutBtn.setEnabled(true);
        this.checkoutBtn.setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void c() {
        runOnUiThread(new Runnable() {
            public void run() {
                ShoppingCartActivity.this.loadingLL.setVisibility(0);
                ShoppingCartActivity.this.checkoutBtn.setEnabled(false);
            }
        });
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(ConnectionHelper.ay(), NewCartResult.class, new SimpleCallback<NewCartResult>() {
            public void a(NewCartResult newCartResult) {
                NewCartData unused = ShoppingCartActivity.this.b = newCartResult.data;
                ShoppingCartActivity.this.a(ShoppingCartActivity.this.b);
                if (ShoppingCartActivity.this.b != null) {
                    ShoppingCartActivity.this.showPageNotice(ShoppingCartActivity.this.b.pagemsg);
                }
            }

            public void a(String str) {
                super.a(str);
                ShoppingCartActivity.this.loadingLL.setVisibility(8);
                ShoppingCartActivity.this.finish();
            }
        });
        simpleProtobufRequest.setTag(f5442a);
        RequestQueueUtil.a().add(simpleProtobufRequest);
    }

    private void a(String str) {
        this.loadingLL.setVisibility(8);
        MiToast.a((Context) this, (CharSequence) str, 0);
        setResult(0);
        finish();
    }

    private void b(String str) {
        this.loadingLL.setVisibility(8);
        if (TextUtils.isEmpty(str)) {
            str = getString(R.string.shop_error_network);
        }
        MiToast.a((Context) this, (CharSequence) str, 0);
    }

    public void delItemDialog(final String str) {
        CustomCancelDialog.Builder builder = new CustomCancelDialog.Builder(this);
        builder.a(getString(R.string.cart_delpromote)).a((Boolean) true).a(getString(R.string.orderview_confirm), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ShoppingCartActivity.this.delCartRequest(str);
            }
        }).b(getString(R.string.orderview_no), (DialogInterface.OnClickListener) null);
        builder.a().show();
    }

    public void onLogin(String str, String str2, String str3) {
        c();
        super.onLogin(str, str2, str3);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.title_bar_home) {
            onBackPressed();
        } else if (id == R.id.cart_viewbtn) {
            setResult(-1);
            finish();
        } else if (id == R.id.cart_checkout) {
            gotoCheckout();
        }
    }

    public void gotoCheckout() {
        if (!LoginManager.u().x()) {
            LogUtil.b(f5442a, "OnClickLog in");
            if (ShopApp.h.f.equals("community_sdk")) {
                gotoAccount();
            } else {
                ShopApp.e();
            }
        } else {
            if (!(this.b == null || this.b.items == null)) {
                ArrayList arrayList = new ArrayList();
                Iterator<NewCartItem> it = this.b.items.iterator();
                while (it.hasNext()) {
                    NewCartItem next = it.next();
                    if (!next.isInsurance && (next.is_cos || next.timeout)) {
                        arrayList.add(next);
                    }
                }
                if (arrayList.size() > 0) {
                    CartDialogFragment a2 = CartDialogFragment.a(arrayList);
                    a2.setCancelable(false);
                    a2.show(getSupportFragmentManager().beginTransaction(), UrlConstants.cart);
                    return;
                }
            }
            startActivityForResult(new Intent(this, CheckoutActivity.class), 16);
        }
    }

    private String a(String str, int i) {
        return ConnectionHelper.a(ConnectionHelper.P(), "", str, "", String.valueOf(i));
    }

    public void updateCartRequest(final String str, final int i) {
        if (!TextUtils.isEmpty(str) && i >= 0) {
            this.checkoutBtn.setEnabled(false);
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(a(str, i), NewEditCartResult.class, new SimpleCallback<NewEditCartResult>() {
                public void a(NewEditCartResult newEditCartResult) {
                    ShoppingCartActivity.this.a(newEditCartResult.data, str, i);
                }

                public void a(String str) {
                    super.a(str);
                    ShoppingCartActivity.this.a(ShoppingCartActivity.this.b);
                }
            });
            simpleProtobufRequest.setTag(f5442a);
            RequestQueueUtil.a().add(simpleProtobufRequest);
        }
    }

    public void delCartRequest(final String str) {
        if (!TextUtils.isEmpty(str)) {
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(d(str), NewEditCartResult.class, new SimpleCallback<NewEditCartResult>() {
                public void a(NewEditCartResult newEditCartResult) {
                    ShoppingCartActivity.this.b(newEditCartResult.data, str);
                }

                public void a(String str) {
                    super.a(str);
                }
            });
            simpleProtobufRequest.setTag(f5442a);
            RequestQueueUtil.a().add(simpleProtobufRequest);
        }
    }

    public void deleteInvalidCart() {
        if (this.b != null && this.b.items != null) {
            StringBuilder sb = new StringBuilder();
            Iterator<NewCartItem> it = this.b.items.iterator();
            while (it.hasNext()) {
                NewCartItem next = it.next();
                if (next.is_cos || next.timeout) {
                    if (TextUtils.isEmpty(sb.toString())) {
                        sb.append(next.itemId);
                    } else {
                        sb.append(",");
                        sb.append(next.itemId);
                    }
                }
            }
            if (!TextUtils.isEmpty(sb.toString())) {
                c(sb.toString());
            }
        }
    }

    private void c(final String str) {
        if (!TextUtils.isEmpty(str)) {
            this.loadingLL.setVisibility(0);
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(ConnectionHelper.a(ConnectionHelper.N(), str), NewEditCartResult.class, new SimpleCallback<NewEditCartResult>() {
                public void a(NewEditCartResult newEditCartResult) {
                    ShoppingCartActivity.this.loadingLL.setVisibility(8);
                    ShoppingCartActivity.this.delBatchCartSuccess(newEditCartResult.data, str);
                }

                public void a(String str) {
                    super.a(str);
                    ShoppingCartActivity.this.loadingLL.setVisibility(8);
                    ShoppingCartActivity.this.c();
                }
            });
            simpleProtobufRequest.setTag(f5442a);
            RequestQueueUtil.a().add(simpleProtobufRequest);
        }
    }

    public void delBatchCartSuccess(NewEditCartData newEditCartData, String str) {
        if (newEditCartData != null && this.b != null && !TextUtils.isEmpty(str)) {
            String[] split = str.split(",");
            if (newEditCartData.totalWithoutGift >= 0) {
                updateShoppingCart(newEditCartData.totalWithoutGift);
            }
            if (!TextUtils.isEmpty(newEditCartData.CartActivityDiscountMoney)) {
                this.b.activityDiscountMoney_txt = LocaleHelper.c(newEditCartData.CartActivityDiscountMoney);
            }
            if (!TextUtils.isEmpty(newEditCartData.CartOrderMoney)) {
                this.b.orderMoney_txt = LocaleHelper.c(newEditCartData.CartOrderMoney);
            }
            if (!TextUtils.isEmpty(newEditCartData.CartProductMoney)) {
                this.b.productMoney_txt = LocaleHelper.c(newEditCartData.CartProductMoney);
            }
            if (!Float.isNaN(newEditCartData.CartPostFreeBalance)) {
                this.b.postFreeBalance = newEditCartData.CartPostFreeBalance;
                this.b.postFreeBalance_txt = LocaleHelper.c(String.valueOf(newEditCartData.CartPostFreeBalance));
            }
            if (this.b.items != null) {
                LogUtil.b(f5442a, "delSuccess before , has item:" + this.b.items.size());
                for (String a2 : split) {
                    a(newEditCartData, a2);
                }
            }
            if (this.b.items != null) {
                LogUtil.b(f5442a, "delSuccess item remain:" + this.b.items.size());
            }
            this.b.activitys = newEditCartData.activitys;
            this.b.bargains = newEditCartData.bargains;
            a(this.b);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x016c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.mi.global.shop.newmodel.cart.NewEditCartData r10, java.lang.String r11) {
        /*
            r9 = this;
            r0 = 0
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
            java.lang.String r2 = ""
            r3 = 0
        L_0x0008:
            com.mi.global.shop.newmodel.cart.NewCartData r4 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r4 = r4.items
            int r4 = r4.size()
            r5 = 1
            if (r3 >= r4) goto L_0x0086
            com.mi.global.shop.newmodel.cart.NewCartData r4 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r4 = r4.items
            java.lang.Object r4 = r4.get(r3)
            com.mi.global.shop.newmodel.cart.NewCartItem r4 = (com.mi.global.shop.newmodel.cart.NewCartItem) r4
            java.lang.String r4 = r4.itemId
            boolean r4 = r4.equalsIgnoreCase(r11)
            if (r4 == 0) goto L_0x0083
            com.mi.global.shop.newmodel.cart.NewCartData r4 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r4 = r4.items
            java.lang.Object r4 = r4.get(r3)
            com.mi.global.shop.newmodel.cart.NewCartItem r4 = (com.mi.global.shop.newmodel.cart.NewCartItem) r4
            com.mi.global.shop.newmodel.cart.NewCartProperty r4 = r4.properties
            if (r4 == 0) goto L_0x0059
            com.mi.global.shop.newmodel.cart.NewCartData r4 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r4 = r4.items
            java.lang.Object r4 = r4.get(r3)
            com.mi.global.shop.newmodel.cart.NewCartItem r4 = (com.mi.global.shop.newmodel.cart.NewCartItem) r4
            com.mi.global.shop.newmodel.cart.NewCartProperty r4 = r4.properties
            java.lang.String r4 = r4.parent_itemId
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x0059
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r5)
            com.mi.global.shop.newmodel.cart.NewCartData r2 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r2 = r2.items
            java.lang.Object r2 = r2.get(r3)
            com.mi.global.shop.newmodel.cart.NewCartItem r2 = (com.mi.global.shop.newmodel.cart.NewCartItem) r2
            com.mi.global.shop.newmodel.cart.NewCartProperty r2 = r2.properties
            java.lang.String r2 = r2.parent_itemId
        L_0x0059:
            java.lang.String r4 = f5442a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "delSuccess remove:"
            r6.append(r7)
            com.mi.global.shop.newmodel.cart.NewCartData r7 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r7 = r7.items
            java.lang.Object r7 = r7.get(r3)
            com.mi.global.shop.newmodel.cart.NewCartItem r7 = (com.mi.global.shop.newmodel.cart.NewCartItem) r7
            java.lang.String r7 = r7.itemId
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.mi.log.LogUtil.b(r4, r6)
            com.mi.global.shop.newmodel.cart.NewCartData r4 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r4 = r4.items
            r4.remove(r3)
            goto L_0x0086
        L_0x0083:
            int r3 = r3 + 1
            goto L_0x0008
        L_0x0086:
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x00dd
            r1 = 0
        L_0x008d:
            com.mi.global.shop.newmodel.cart.NewCartData r3 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r3 = r3.items
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x00dd
            com.mi.global.shop.newmodel.cart.NewCartData r3 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r3 = r3.items
            java.lang.Object r3 = r3.get(r1)
            com.mi.global.shop.newmodel.cart.NewCartItem r3 = (com.mi.global.shop.newmodel.cart.NewCartItem) r3
            java.lang.String r3 = r3.itemId
            boolean r3 = r3.equalsIgnoreCase(r2)
            if (r3 == 0) goto L_0x00da
            com.mi.global.shop.newmodel.cart.NewCartData r3 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r3 = r3.items
            java.lang.Object r3 = r3.get(r1)
            com.mi.global.shop.newmodel.cart.NewCartItem r3 = (com.mi.global.shop.newmodel.cart.NewCartItem) r3
            com.mi.global.shop.newmodel.cart.NewCartProperty r3 = r3.properties
            if (r3 == 0) goto L_0x00da
            com.mi.global.shop.newmodel.cart.NewCartData r3 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r3 = r3.items
            java.lang.Object r3 = r3.get(r1)
            com.mi.global.shop.newmodel.cart.NewCartItem r3 = (com.mi.global.shop.newmodel.cart.NewCartItem) r3
            com.mi.global.shop.newmodel.cart.NewCartProperty r3 = r3.properties
            com.mi.global.shop.newmodel.cart.NewCartInsurance r3 = r3.insurance
            if (r3 == 0) goto L_0x00da
            com.mi.global.shop.newmodel.cart.NewCartData r2 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r2 = r2.items
            java.lang.Object r1 = r2.get(r1)
            com.mi.global.shop.newmodel.cart.NewCartItem r1 = (com.mi.global.shop.newmodel.cart.NewCartItem) r1
            com.mi.global.shop.newmodel.cart.NewCartProperty r1 = r1.properties
            com.mi.global.shop.newmodel.cart.NewCartInsurance r1 = r1.insurance
            java.lang.String r2 = ""
            r1.itemId = r2
            goto L_0x00dd
        L_0x00da:
            int r1 = r1 + 1
            goto L_0x008d
        L_0x00dd:
            r1 = 0
        L_0x00de:
            com.mi.global.shop.newmodel.cart.NewCartData r2 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r2 = r2.items
            int r2 = r2.size()
            if (r1 >= r2) goto L_0x0170
            com.mi.global.shop.newmodel.cart.NewCartData r2 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r2 = r2.items
            java.lang.Object r2 = r2.get(r1)
            com.mi.global.shop.newmodel.cart.NewCartItem r2 = (com.mi.global.shop.newmodel.cart.NewCartItem) r2
            java.lang.String r3 = "bargain"
            java.lang.String r4 = r2.getType
            boolean r3 = r3.equalsIgnoreCase(r4)
            if (r3 == 0) goto L_0x016c
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartBargainItem> r3 = r10.bargains
            if (r3 == 0) goto L_0x0106
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartBargainItem> r3 = r10.bargains
            int r3 = r3.size()
        L_0x0106:
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartBargainItem> r3 = r10.bargains
            if (r3 == 0) goto L_0x0140
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartBargainItem> r3 = r10.bargains
            java.util.Iterator r3 = r3.iterator()
        L_0x0110:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0140
            java.lang.Object r4 = r3.next()
            com.mi.global.shop.newmodel.cart.NewCartBargainItem r4 = (com.mi.global.shop.newmodel.cart.NewCartBargainItem) r4
            java.lang.String r6 = f5442a
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "delSuccess remain bargain:"
            r7.append(r8)
            java.lang.String r8 = r4.bargainItemId
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            com.mi.log.LogUtil.b(r6, r7)
            java.lang.String r6 = r2.itemId
            java.lang.String r4 = r4.bargainItemId
            boolean r4 = r6.contains(r4)
            if (r4 == 0) goto L_0x0110
            r2 = 0
            goto L_0x0141
        L_0x0140:
            r2 = 1
        L_0x0141:
            if (r2 == 0) goto L_0x016c
            java.lang.String r2 = f5442a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "delSuccess remove bargain item:"
            r3.append(r4)
            com.mi.global.shop.newmodel.cart.NewCartData r4 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r4 = r4.items
            java.lang.Object r4 = r4.get(r1)
            com.mi.global.shop.newmodel.cart.NewCartItem r4 = (com.mi.global.shop.newmodel.cart.NewCartItem) r4
            java.lang.String r4 = r4.itemId
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.mi.log.LogUtil.b(r2, r3)
            com.mi.global.shop.newmodel.cart.NewCartData r2 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r2 = r2.items
            r2.remove(r1)
        L_0x016c:
            int r1 = r1 + 1
            goto L_0x00de
        L_0x0170:
            com.mi.global.shop.newmodel.cart.NewCartData r10 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r10 = r10.items
            int r10 = r10.size()
            if (r0 >= r10) goto L_0x01d0
            com.mi.global.shop.newmodel.cart.NewCartData r10 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r10 = r10.items
            java.lang.Object r10 = r10.get(r0)
            com.mi.global.shop.newmodel.cart.NewCartItem r10 = (com.mi.global.shop.newmodel.cart.NewCartItem) r10
            com.mi.global.shop.newmodel.cart.NewCartProperty r10 = r10.properties
            if (r10 == 0) goto L_0x01cd
            java.lang.String r10 = "insurance"
            com.mi.global.shop.newmodel.cart.NewCartData r1 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r1 = r1.items
            java.lang.Object r1 = r1.get(r0)
            com.mi.global.shop.newmodel.cart.NewCartItem r1 = (com.mi.global.shop.newmodel.cart.NewCartItem) r1
            java.lang.String r1 = r1.getType
            boolean r10 = r10.equalsIgnoreCase(r1)
            if (r10 == 0) goto L_0x01cd
            com.mi.global.shop.newmodel.cart.NewCartData r10 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r10 = r10.items
            java.lang.Object r10 = r10.get(r0)
            com.mi.global.shop.newmodel.cart.NewCartItem r10 = (com.mi.global.shop.newmodel.cart.NewCartItem) r10
            com.mi.global.shop.newmodel.cart.NewCartProperty r10 = r10.properties
            java.lang.String r10 = r10.parent_itemId
            boolean r10 = r11.equalsIgnoreCase(r10)
            if (r10 == 0) goto L_0x01cd
            com.mi.global.shop.newmodel.cart.NewCartData r10 = r9.b
            java.util.ArrayList<com.mi.global.shop.newmodel.cart.NewCartItem> r10 = r10.items
            r10.remove(r0)
            java.lang.String r10 = f5442a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "delSuccess remove insurance item:"
            r1.append(r2)
            r1.append(r11)
            java.lang.String r1 = r1.toString()
            com.mi.log.LogUtil.b(r10, r1)
        L_0x01cd:
            int r0 = r0 + 1
            goto L_0x0170
        L_0x01d0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.activity.ShoppingCartActivity.a(com.mi.global.shop.newmodel.cart.NewEditCartData, java.lang.String):void");
    }

    private String d(String str) {
        return ConnectionHelper.a(ConnectionHelper.M(), "", str, "", "");
    }

    /* access modifiers changed from: private */
    public void b(NewEditCartData newEditCartData, String str) {
        if (newEditCartData != null && this.b != null) {
            if (newEditCartData.totalWithoutGift >= 0) {
                updateShoppingCart(newEditCartData.totalWithoutGift);
            }
            if (!TextUtils.isEmpty(newEditCartData.CartActivityDiscountMoney)) {
                this.b.activityDiscountMoney_txt = LocaleHelper.c(newEditCartData.CartActivityDiscountMoney);
            }
            if (!TextUtils.isEmpty(newEditCartData.CartOrderMoney)) {
                this.b.orderMoney_txt = LocaleHelper.c(newEditCartData.CartOrderMoney);
            }
            if (!TextUtils.isEmpty(newEditCartData.CartProductMoney)) {
                this.b.productMoney_txt = LocaleHelper.c(newEditCartData.CartProductMoney);
            }
            if (!Float.isNaN(newEditCartData.CartPostFreeBalance)) {
                this.b.postFreeBalance = newEditCartData.CartPostFreeBalance;
                this.b.postFreeBalance_txt = LocaleHelper.c(String.valueOf(newEditCartData.CartPostFreeBalance));
            }
            if (this.b.items != null) {
                String str2 = f5442a;
                LogUtil.b(str2, "delSuccess before , has item:" + this.b.items.size());
                a(newEditCartData, str);
            }
            if (this.b.items != null) {
                String str3 = f5442a;
                LogUtil.b(str3, "delSuccess item remain:" + this.b.items.size());
            }
            this.b.activitys = newEditCartData.activitys;
            this.b.bargains = newEditCartData.bargains;
            a(this.b);
        }
    }

    /* access modifiers changed from: private */
    public void a(NewEditCartData newEditCartData, String str, int i) {
        if (newEditCartData != null && this.b != null) {
            if (newEditCartData.totalWithoutGift >= 0) {
                updateShoppingCart(newEditCartData.totalWithoutGift);
            }
            if (!TextUtils.isEmpty(newEditCartData.CartActivityDiscountMoney)) {
                this.b.activityDiscountMoney_txt = LocaleHelper.c(String.valueOf(newEditCartData.CartActivityDiscountMoney));
            }
            if (!TextUtils.isEmpty(newEditCartData.CartOrderMoney)) {
                this.b.orderMoney_txt = LocaleHelper.c(String.valueOf(newEditCartData.CartOrderMoney));
            }
            if (!TextUtils.isEmpty(newEditCartData.CartProductMoney)) {
                this.b.productMoney_txt = LocaleHelper.c(String.valueOf(newEditCartData.CartProductMoney));
            }
            if (!Float.isNaN(newEditCartData.CartPostFreeBalance)) {
                this.b.postFreeBalance = newEditCartData.CartPostFreeBalance;
                this.b.postFreeBalance_txt = LocaleHelper.c(String.valueOf(newEditCartData.CartPostFreeBalance));
            }
            if (this.b.items != null) {
                Iterator<NewCartItem> it = this.b.items.iterator();
                while (it.hasNext()) {
                    NewCartItem next = it.next();
                    if (next.itemId.equalsIgnoreCase(str)) {
                        next.num = i;
                        next.subtotal = next.salePrice * ((float) i);
                        next.subtotal_txt = LocaleHelper.c(String.valueOf(next.subtotal));
                        if (!(next.properties == null || next.properties.insurance == null || TextUtils.isEmpty(next.properties.insurance.itemId))) {
                            next.properties.insurance.num = i;
                        }
                    }
                    if (next.properties != null && !TextUtils.isEmpty(next.properties.parent_itemId) && next.properties.parent_itemId.equalsIgnoreCase(str) && "insurance".equalsIgnoreCase(next.getType)) {
                        next.num = i;
                        next.subtotal = next.salePrice * ((float) i);
                        next.subtotal_txt = LocaleHelper.c(String.valueOf(next.subtotal));
                    }
                }
            }
            this.b.activitys = newEditCartData.activitys;
            this.b.bargains = newEditCartData.bargains;
            if (this.b.items != null) {
                if (this.b.bargains == null || this.b.bargains.size() <= 0) {
                    for (int i2 = 0; i2 < this.b.items.size(); i2++) {
                        if ("bargain".equalsIgnoreCase(this.b.items.get(i2).getType)) {
                            this.b.items.remove(i2);
                        }
                    }
                } else {
                    Boolean.valueOf(false);
                    for (int i3 = 0; i3 < this.b.items.size(); i3++) {
                        NewCartItem newCartItem = this.b.items.get(i3);
                        if ("bargain".equalsIgnoreCase(newCartItem.getType)) {
                            Boolean bool = false;
                            int i4 = 0;
                            while (true) {
                                if (i4 >= this.b.bargains.size()) {
                                    break;
                                } else if (newCartItem.itemId.toLowerCase().contains(this.b.bargains.get(i4).bargainItemId.toLowerCase())) {
                                    bool = true;
                                    break;
                                } else {
                                    i4++;
                                }
                            }
                            if (!bool.booleanValue()) {
                                this.b.items.remove(i3);
                            }
                        }
                    }
                }
            }
            a(this.b);
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        int shoppingCart = getShoppingCart();
        int i = 1;
        if (shoppingCart > 0) {
            i = 1 + shoppingCart;
        }
        updateShoppingCart(i);
    }

    private void e(String str) {
        String str2 = f5442a;
        LogUtil.b(str2, "recordCartEvent : itemId" + str);
        if (this.c == null) {
            this.c = AppEventsLogger.newLogger(this);
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, str);
            this.c.logEvent(AppEventsConstants.EVENT_NAME_ADDED_TO_CART, 1.0d, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCartRequest(String str, final boolean z) {
        if (!TextUtils.isEmpty(str)) {
            e(str);
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(ConnectionHelper.a(ConnectionHelper.K(), str, "", "", ""), NewAddCartResult.class, new SimpleCallback<NewAddCartResult>() {
                public void a(NewAddCartResult newAddCartResult) {
                    if (z) {
                        ShoppingCartActivity.this.c();
                    }
                    ShoppingCartActivity.this.d();
                }

                public void a(String str) {
                    super.a(str);
                }
            });
            simpleProtobufRequest.setTag(f5442a);
            RequestQueueUtil.a().add(simpleProtobufRequest);
        }
    }

    public void chooseGift(NewCartGiftItem newCartGiftItem, NewCartSelectInfo newCartSelectInfo) {
        NewCartData newCartData = this.b;
        if (newCartData != null && newCartData.activitys != null && newCartData.activitys.gift != null && this.mCartGiftListAdapter != null) {
            int i = 0;
            while (true) {
                if (i >= newCartData.activitys.gift.size()) {
                    break;
                } else if (newCartGiftItem.itemId.equalsIgnoreCase(newCartData.activitys.gift.get(i).itemId)) {
                    newCartData.activitys.gift.get(i).product_name = newCartSelectInfo.product_name;
                    newCartData.activitys.gift.get(i).image_url = newCartSelectInfo.image_url;
                    break;
                } else {
                    i++;
                }
            }
            this.mCartGiftListAdapter.notifyDataSetChanged();
        }
    }

    public String getBarginItembyId(String str) {
        if (this.b == null || this.b.items == null) {
            return "";
        }
        for (int i = 0; i < this.b.items.size(); i++) {
            NewCartItem newCartItem = this.b.items.get(i);
            if (newCartItem.itemId.startsWith(str) && "bargain".equalsIgnoreCase(newCartItem.getType)) {
                return newCartItem.itemId;
            }
        }
        return "";
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 16) {
            if (i != 23) {
                switch (i) {
                    case 20:
                        if (i2 == -1) {
                            c();
                            return;
                        }
                        return;
                    case 21:
                        if (i2 == -1) {
                            c();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else if (i2 == -1) {
                c();
            }
        } else if (i2 == -1) {
            setResult(-1);
            finish();
        }
    }
}
