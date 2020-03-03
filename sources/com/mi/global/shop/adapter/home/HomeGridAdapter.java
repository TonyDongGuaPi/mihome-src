package com.mi.global.shop.adapter.home;

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
import com.mi.global.shop.newmodel.home.NewHomeBlockInfoItem;
import com.mi.global.shop.util.ImageUtil;
import com.mi.global.shop.util.UIAdapter;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.EasyTextView;
import com.mi.global.shop.widget.home.HomeThemeItemClick;
import com.mi.log.LogUtil;
import com.mi.util.ScreenInfo;
import com.xiaomi.smarthome.camera.activity.timelapse.TCPClient;

public class HomeGridAdapter extends ArrayAdapter<NewHomeBlockInfoItem> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5538a = "HomeGridAdapter";
    private Context b;

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f5540a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f5540a = viewHolder;
            viewHolder.image = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.home_product_item_image, "field 'image'", SimpleDraweeView.class);
            viewHolder.name = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.home_product_item_name, "field 'name'", CustomTextView.class);
            viewHolder.price = (EasyTextView) Utils.findRequiredViewAsType(view, R.id.home_product_item_price, "field 'price'", EasyTextView.class);
            viewHolder.iconImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.icon_image, "field 'iconImage'", SimpleDraweeView.class);
            viewHolder.iconText = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.icon_text, "field 'iconText'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f5540a;
            if (viewHolder != null) {
                this.f5540a = null;
                viewHolder.image = null;
                viewHolder.name = null;
                viewHolder.price = null;
                viewHolder.iconImage = null;
                viewHolder.iconText = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public HomeGridAdapter(Context context) {
        super(context);
        this.b = context;
    }

    public View a(Context context, int i, NewHomeBlockInfoItem newHomeBlockInfoItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_home_hot_product_item_view, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void a(View view, int i, final NewHomeBlockInfoItem newHomeBlockInfoItem) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (newHomeBlockInfoItem != null) {
            int b2 = ScreenInfo.a().b() / 2;
            String imageUrl = newHomeBlockInfoItem.getImageUrl();
            if (!TextUtils.isEmpty(imageUrl)) {
                imageUrl = ImageUtil.a(b2, (int) TCPClient.SOCKET_CONNECTTIMEOUT, newHomeBlockInfoItem.getImageUrl());
            }
            String str = f5538a;
            LogUtil.b(str, "image url=" + imageUrl);
            FrescoUtils.a(imageUrl, viewHolder.image);
            viewHolder.name.setText(newHomeBlockInfoItem.mProductName);
            viewHolder.price.setPrize(newHomeBlockInfoItem);
            if (TextUtils.isEmpty(newHomeBlockInfoItem.mIconImg)) {
                viewHolder.iconImage.setVisibility(8);
            } else {
                viewHolder.iconImage.setVisibility(0);
                FrescoUtils.a(newHomeBlockInfoItem.mIconImg, viewHolder.iconImage);
            }
            if (TextUtils.isEmpty(newHomeBlockInfoItem.mIconContent)) {
                viewHolder.iconText.setVisibility(8);
            } else {
                viewHolder.iconText.setVisibility(0);
                viewHolder.iconText.setText(newHomeBlockInfoItem.mIconContent);
            }
        }
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeGridAdapter.this.a(newHomeBlockInfoItem);
            }
        });
        HomeThemeItemClick.a(newHomeBlockInfoItem);
    }

    /* access modifiers changed from: private */
    public void a(NewHomeBlockInfoItem newHomeBlockInfoItem) {
        HomeThemeItemClick.a(b(), newHomeBlockInfoItem);
    }

    static class ViewHolder {
        @BindView(2131493439)
        SimpleDraweeView iconImage;
        @BindView(2131493443)
        CustomTextView iconText;
        @BindView(2131493420)
        SimpleDraweeView image;
        @BindView(2131493421)
        CustomTextView name;
        @BindView(2131493422)
        EasyTextView price;

        public ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
            this.image.getLayoutParams().width = UIAdapter.a().a(13);
            this.image.getLayoutParams().height = UIAdapter.a().a(14);
        }
    }
}
