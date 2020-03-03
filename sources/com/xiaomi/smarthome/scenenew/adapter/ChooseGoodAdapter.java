package com.xiaomi.smarthome.scenenew.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;
import com.xiaomi.smarthome.scenenew.model.GoodInfo;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.List;

public class ChooseGoodAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    Context f21922a = SHApplication.getAppContext();
    LayoutInflater b;
    List<GoodInfo> c = new ArrayList();

    public long getItemId(int i) {
        return (long) i;
    }

    public ChooseGoodAdapter(Context context, List<GoodInfo> list) {
        this.c.clear();
        this.c.addAll(list);
        this.b = LayoutInflater.from(this.f21922a);
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.b.inflate(R.layout.choose_good_item, (ViewGroup) null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final GoodInfo goodInfo = this.c.get(i);
        TextView textView = viewHolder.c;
        textView.setText("￥" + (goodInfo.b / 100.0f));
        viewHolder.b.setText(goodInfo.f21983a);
        viewHolder.f21924a.setHierarchy(new GenericDraweeHierarchyBuilder(this.f21922a.getResources()).setPlaceholderImage(this.f21922a.getResources().getDrawable(R.drawable.device_list_phone_no)).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        viewHolder.f21924a.setImageURI(Uri.parse(goodInfo.d));
        viewHolder.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UrlDispatchManger.a().c(goodInfo.c);
                MLAlertDialog f = RecommendSceneManager.a().f();
                if (f != null) {
                    f.dismiss();
                }
            }
        });
        return view;
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        SimpleDraweeView f21924a;
        TextView b;
        TextView c;
        TextView d;
        View e;

        public ViewHolder(View view) {
            this.e = view;
            this.f21924a = (SimpleDraweeView) view.findViewById(R.id.good_icon);
            this.b = (TextView) view.findViewById(R.id.device_name);
            this.c = (TextView) view.findViewById(R.id.device_price);
            this.d = (TextView) view.findViewById(R.id.buy_tv);
        }
    }
}
