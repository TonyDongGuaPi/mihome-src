package com.mi.global.shop.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.DeliveryPopAdapter;

public class PopSpinnerView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private final int f7163a = 12;
    private String b;
    private Drawable c;
    private ImageView d;
    /* access modifiers changed from: private */
    public TextView e;
    /* access modifiers changed from: private */
    public PopupWindow f;
    private ListView g;
    private int h;
    /* access modifiers changed from: private */
    public DeliveryPopAdapter i;
    private int j = -1;
    /* access modifiers changed from: private */
    public NameFilter k;

    public interface NameFilter {
        String a(int i);
    }

    public PopSpinnerView(Context context) {
        super(context);
        a(context);
    }

    public PopSpinnerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public PopSpinnerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }

    public void init(DeliveryPopAdapter deliveryPopAdapter, NameFilter nameFilter) {
        this.i = deliveryPopAdapter;
        this.k = nameFilter;
    }

    private void a(final Context context) {
        LayoutInflater.from(context).inflate(R.layout.shop_pop_spinerview, this);
        this.d = (ImageView) findViewById(R.id.iv_arrow);
        this.e = (TextView) findViewById(R.id.tv_content);
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.shop_pop_layout, (ViewGroup) null);
                PopSpinnerView.this.a(context, inflate);
                PopSpinnerView.this.a(inflate, context);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(Context context, View view) {
        this.g = (ListView) view.findViewById(R.id.lv);
        this.g.setAdapter(this.i);
        this.g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (PopSpinnerView.this.i.f5489a.get(i).isServiceable) {
                    PopSpinnerView.this.i.b = PopSpinnerView.this.i.f5489a.get(i).id;
                    PopSpinnerView.this.e.setText(PopSpinnerView.this.k.a(i));
                    PopSpinnerView.this.i.notifyDataSetChanged();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if (PopSpinnerView.this.f != null) {
                                PopSpinnerView.this.f.dismiss();
                            }
                        }
                    }, 300);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(View view, Context context) {
        this.f = new PopupWindow(view, -1, -2);
        this.f.setFocusable(true);
        this.f.setOutsideTouchable(true);
        this.f.setBackgroundDrawable(new BitmapDrawable());
        this.f.setAnimationStyle(0);
        this.f.showAsDropDown(this.e, 0, -1);
    }

    public void setContent(String str) {
        this.e.setText(str);
    }

    public String getContent() {
        CharSequence text = this.e.getText();
        if (text == null) {
            return "";
        }
        return text.toString();
    }

    public int getSelectIndex() {
        return this.j;
    }

    public void setSelectIndex(int i2) {
        this.j = i2;
    }

    public void setSelectId(String str) {
        this.i.b = str;
    }

    private void a(int i2) {
        int paddingLeft = this.e.getPaddingLeft();
        this.e.setBackgroundResource(i2);
        this.e.setPadding(paddingLeft, 0, 0, 0);
    }
}
