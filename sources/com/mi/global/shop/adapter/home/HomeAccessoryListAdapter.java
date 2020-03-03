package com.mi.global.shop.adapter.home;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
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
import com.mi.global.shop.widget.EasyTextView;
import com.mi.global.shop.widget.home.HomeThemeItemClick;
import java.util.ArrayList;
import java.util.List;

public class HomeAccessoryListAdapter extends RecyclerView.Adapter<AccessoryViewHolder> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f5534a;
    private List<NewHomeBlockInfoItem> b = new ArrayList();

    public class AccessoryViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private AccessoryViewHolder f5536a;

        @UiThread
        public AccessoryViewHolder_ViewBinding(AccessoryViewHolder accessoryViewHolder, View view) {
            this.f5536a = accessoryViewHolder;
            accessoryViewHolder.accessoryImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.accessory_image, "field 'accessoryImage'", SimpleDraweeView.class);
            accessoryViewHolder.accessoryName = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.accessory_name, "field 'accessoryName'", CustomTextView.class);
            accessoryViewHolder.accessoryPrize = (EasyTextView) Utils.findRequiredViewAsType(view, R.id.accessory_prize, "field 'accessoryPrize'", EasyTextView.class);
        }

        @CallSuper
        public void unbind() {
            AccessoryViewHolder accessoryViewHolder = this.f5536a;
            if (accessoryViewHolder != null) {
                this.f5536a = null;
                accessoryViewHolder.accessoryImage = null;
                accessoryViewHolder.accessoryName = null;
                accessoryViewHolder.accessoryPrize = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public HomeAccessoryListAdapter(Context context) {
        this.f5534a = context;
    }

    public void a(List<NewHomeBlockInfoItem> list) {
        if (list != null) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public AccessoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AccessoryViewHolder(LayoutInflater.from(this.f5534a).inflate(R.layout.shop_accessory_list_item, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(AccessoryViewHolder accessoryViewHolder, int i) {
        final NewHomeBlockInfoItem newHomeBlockInfoItem = this.b.get(i);
        accessoryViewHolder.accessoryName.setText(newHomeBlockInfoItem.mProductName);
        accessoryViewHolder.accessoryPrize.setPrize(newHomeBlockInfoItem);
        FrescoUtils.a(newHomeBlockInfoItem.getImageUrl(), accessoryViewHolder.accessoryImage);
        accessoryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeThemeItemClick.a(HomeAccessoryListAdapter.this.f5534a, newHomeBlockInfoItem);
            }
        });
        HomeThemeItemClick.a(newHomeBlockInfoItem);
    }

    public int getItemCount() {
        return this.b.size();
    }

    static class AccessoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131492893)
        SimpleDraweeView accessoryImage;
        @BindView(2131492897)
        CustomTextView accessoryName;
        @BindView(2131492898)
        EasyTextView accessoryPrize;

        AccessoryViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
