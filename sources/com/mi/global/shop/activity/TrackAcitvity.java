package com.mi.global.shop.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.volley.Request;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.newmodel.expresstrack.NewExpressInfoData;
import com.mi.global.shop.newmodel.expresstrack.NewExpressInfoResult;
import com.mi.global.shop.newmodel.expresstrack.NewExpressTraceItem;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.BaseTypeConvertUtil;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;
import com.mi.util.RequestQueueUtil;
import com.taobao.weex.el.parse.Operators;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrackAcitvity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5466a = "TrackAcitvity";
    /* access modifiers changed from: private */
    public SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String c;
    @BindView(2131493337)
    CustomTextView expressName;
    @BindView(2131493338)
    CustomTextView expressSn;
    @BindView(2131493568)
    ImageView ivShipping;
    @BindView(2131493691)
    LinearLayout loading;
    protected NewExpressInfoData model;
    @BindView(2131494163)
    NoScrollListView trackItemList;
    @BindView(2131494216)
    CustomTextView tvExpressHint;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_track);
        ButterKnife.bind((Activity) this);
        setTitle((CharSequence) getString(R.string.track));
        this.mBackView.setVisibility(0);
        findViewById(R.id.title_bar_cart_view).setVisibility(4);
        this.loading.setVisibility(0);
        this.trackItemList = (NoScrollListView) findViewById(R.id.trackItemList);
        this.c = getIntent().getStringExtra("expresssn");
        a();
    }

    private void a() {
        Request request;
        String c2 = c();
        AnonymousClass1 r1 = new SimpleCallback<NewExpressInfoResult>() {
            public void a(NewExpressInfoResult newExpressInfoResult) {
                TrackAcitvity.this.model = newExpressInfoResult.data;
                TrackAcitvity.this.updateUi();
            }

            public void a(String str) {
                super.a(str);
                TrackAcitvity.this.finish();
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(c2, NewExpressInfoResult.class, r1);
        } else {
            request = new SimpleJsonRequest(c2, NewExpressInfoResult.class, r1);
        }
        request.setTag(f5466a);
        RequestQueueUtil.a().add(request);
    }

    /* access modifiers changed from: protected */
    public void updateUi() {
        this.loading.setVisibility(8);
        CustomTextView customTextView = this.expressName;
        customTextView.setText(getString(R.string.transport_company) + " " + this.model.expressInfo.express_name);
        CustomTextView customTextView2 = this.expressSn;
        customTextView2.setText(getString(R.string.tracking_number) + " " + this.model.expressInfo.express_sn);
        TrackListAdapter trackListAdapter = new TrackListAdapter(this);
        if (this.model.expressInfo.express_trace == null || this.model.expressInfo.express_trace.size() == 0) {
            this.tvExpressHint.setVisibility(0);
        } else {
            this.tvExpressHint.setVisibility(8);
        }
        try {
            if (!(getIntent().getStringExtra("order_placed") == null || getIntent().getStringExtra("order_paid") == null)) {
                NewExpressTraceItem newExpressTraceItem = new NewExpressTraceItem();
                newExpressTraceItem.time = getIntent().getStringExtra("order_paid");
                newExpressTraceItem.city = "";
                newExpressTraceItem.track = getString(R.string.order_status_paid);
                NewExpressTraceItem newExpressTraceItem2 = new NewExpressTraceItem();
                newExpressTraceItem2.time = getIntent().getStringExtra("order_placed");
                newExpressTraceItem.city = "";
                newExpressTraceItem2.track = getString(R.string.order_status_placed);
                this.model.expressInfo.express_trace.add(newExpressTraceItem);
                this.model.expressInfo.express_trace.add(newExpressTraceItem2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        trackListAdapter.a(this.model.expressInfo.express_trace);
        this.trackItemList.setDividerHeight(0);
        this.trackItemList.setAdapter(trackListAdapter);
    }

    private String[] b() {
        String[] E = ConnectionHelper.E();
        String[] strArr = new String[E.length];
        for (int i = 0; i < E.length; i++) {
            Uri.Builder buildUpon = Uri.parse(E[i] + this.c).buildUpon();
            buildUpon.appendQueryParameter("security", "true");
            strArr[i] = buildUpon.toString();
        }
        return strArr;
    }

    private String c() {
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.F()).buildUpon();
        buildUpon.appendQueryParameter("deliverId", this.c);
        buildUpon.appendQueryParameter("ot", "5");
        return buildUpon.toString();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public class TrackListAdapter extends ArrayAdapter<NewExpressTraceItem> {
        private Drawable b;
        private Drawable c;
        private Drawable g;
        private Drawable h;

        public TrackListAdapter(Context context) {
            super(context);
        }

        public View a(Context context, int i, NewExpressTraceItem newExpressTraceItem, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_track_item, (ViewGroup) null, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.f5469a = (ImageView) inflate.findViewById(R.id.track_status_image_normal);
            viewHolder.b = (ImageView) inflate.findViewById(R.id.track_status_image_selected);
            viewHolder.c = (CustomTextView) inflate.findViewById(R.id.track_info);
            viewHolder.d = (CustomTextView) inflate.findViewById(R.id.track_time);
            viewHolder.e = (ImageView) inflate.findViewById(R.id.line_top);
            viewHolder.f = (ImageView) inflate.findViewById(R.id.line_bottom);
            inflate.setTag(viewHolder);
            this.b = context.getResources().getDrawable(R.drawable.shop_order_progress_circle_green);
            this.c = context.getResources().getDrawable(R.drawable.shop_order_progress_circle_grey);
            this.g = context.getResources().getDrawable(R.drawable.shop_order_progress_green_bg);
            this.h = context.getResources().getDrawable(R.drawable.shop_order_progress_grey_bg);
            return inflate;
        }

        public void a(View view, int i, NewExpressTraceItem newExpressTraceItem) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (!TextUtils.isEmpty(newExpressTraceItem.city)) {
                CustomTextView customTextView = viewHolder.c;
                customTextView.setText(Operators.ARRAY_START_STR + newExpressTraceItem.city + Operators.ARRAY_END_STR + newExpressTraceItem.track);
            } else {
                viewHolder.c.setText(newExpressTraceItem.track);
            }
            viewHolder.d.setText(TrackAcitvity.this.b.format(new Date(BaseTypeConvertUtil.a(newExpressTraceItem.time, 0) * 1000)));
            if (BaseTypeConvertUtil.a(newExpressTraceItem.time, 0) != 0) {
                viewHolder.d.setVisibility(0);
            } else {
                viewHolder.e.setImageDrawable(this.h);
                viewHolder.f.setImageDrawable(this.h);
                viewHolder.d.setVisibility(4);
            }
            if (i == 0) {
                viewHolder.f5469a.setVisibility(8);
                viewHolder.b.setVisibility(0);
                viewHolder.e.setVisibility(4);
            } else {
                viewHolder.f5469a.setVisibility(0);
                viewHolder.b.setVisibility(8);
                viewHolder.e.setVisibility(0);
            }
            if (i == TrackAcitvity.this.model.expressInfo.express_trace.size() - 1) {
                viewHolder.f.setVisibility(4);
            } else {
                viewHolder.f.setVisibility(0);
            }
        }
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f5469a;
        ImageView b;
        CustomTextView c;
        CustomTextView d;
        ImageView e;
        ImageView f;

        ViewHolder() {
        }
    }
}
