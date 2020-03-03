package com.xiaomi.payment.giftcard;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mibi.common.base.BaseFragment;
import com.xiaomi.payment.platform.R;

public class GiftcardFragment extends BaseFragment {
    private static final int t = 3;
    private GiftcardFragmentPagerAdapter u;
    /* access modifiers changed from: private */
    public ViewPager v;
    private TextView w;
    private TextView x;
    private TextView y;

    /* access modifiers changed from: private */
    public int e(int i) {
        return i + 1;
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_giftcard_container, (ViewGroup) null);
        this.v = (ViewPager) inflate.findViewById(R.id.giftcard_pager);
        this.w = (TextView) inflate.findViewById(R.id.available_text);
        this.x = (TextView) inflate.findViewById(R.id.unavailable_text);
        this.y = (TextView) inflate.findViewById(R.id.used_text);
        return inflate;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        a(R.string.mibi_giftcard_record);
        this.u = new GiftcardFragmentPagerAdapter(getFragmentManager());
        this.v.setAdapter(this.u);
        this.v.addOnPageChangeListener(new PagerScrollListener());
        this.v.setOffscreenPageLimit(3);
        a(1, true);
        this.w.setOnClickListener(new HeaderClickListener(1));
        this.x.setOnClickListener(new HeaderClickListener(2));
        this.y.setOnClickListener(new HeaderClickListener(3));
    }

    private class GiftcardFragmentPagerAdapter extends FragmentPagerAdapter {
        public int getCount() {
            return 3;
        }

        public GiftcardFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            GiftcardTabFragment giftcardTabFragment = new GiftcardTabFragment();
            Bundle bundle = new Bundle(GiftcardFragment.this.getArguments());
            bundle.putInt(GiftcardContract.d, GiftcardFragment.this.e(i));
            giftcardTabFragment.setArguments(bundle);
            return giftcardTabFragment;
        }
    }

    private class PagerScrollListener implements ViewPager.OnPageChangeListener {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        private PagerScrollListener() {
        }

        public void onPageSelected(int i) {
            int i2 = 0;
            while (i2 < 3) {
                GiftcardFragment.this.a(GiftcardFragment.this.e(i2), i == i2);
                i2++;
            }
        }
    }

    private class HeaderClickListener implements View.OnClickListener {
        private int b;

        public HeaderClickListener(int i) {
            this.b = i;
        }

        public void onClick(View view) {
            GiftcardFragment.this.v.setCurrentItem(this.b - 1);
        }
    }

    public void a(int i, boolean z) {
        TextView textView;
        switch (i) {
            case 1:
                textView = this.w;
                break;
            case 2:
                textView = this.x;
                break;
            case 3:
                textView = this.y;
                break;
            default:
                throw new IllegalStateException("type is not available:type = " + i);
        }
        textView.setTextColor(getResources().getColor(z ? R.color.mibi_text_color_giftcard_header_selected : R.color.mibi_text_color_giftcard_header_default));
    }
}
