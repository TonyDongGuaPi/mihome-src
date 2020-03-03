package com.mi.global.shop.imageselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.imageselector.bean.Image;
import com.mi.global.shop.model.Tags;
import com.mi.global.shop.widget.CustomButtonView;
import java.util.ArrayList;
import java.util.Iterator;

public class ImageViewActivity extends BaseActivity {
    public static final int MODE_MULTI = 1;
    public static final int MODE_SINGLE = 0;

    /* renamed from: a  reason: collision with root package name */
    private ViewPager f6902a;
    private ImageViewPageAdapter b;
    private CustomButtonView c;
    /* access modifiers changed from: private */
    public ImageView d;
    /* access modifiers changed from: private */
    public ArrayList<Image> e;
    /* access modifiers changed from: private */
    public ArrayList<String> f;
    /* access modifiers changed from: private */
    public int g;
    private int h;
    private int i;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_activity_image);
        this.mBackView.setVisibility(0);
        this.mBackView.setVisibility(0);
        this.mBackView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra("result", ImageViewActivity.this.f);
                ImageViewActivity.this.setResult(0, intent);
                ImageViewActivity.this.finish();
            }
        });
        this.mCartView.setVisibility(8);
        setTitle((CharSequence) "Preview");
        this.f6902a = (ViewPager) findViewById(R.id.pager);
        this.b = new ImageViewPageAdapter(getSupportFragmentManager());
        this.f6902a.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                int unused = ImageViewActivity.this.g = i;
                Image image = (Image) ImageViewActivity.this.e.get(ImageViewActivity.this.g);
                ImageViewActivity.this.d.setSelected(false);
                Iterator it = ImageViewActivity.this.f.iterator();
                while (it.hasNext()) {
                    if (((String) it.next()).equals(image.f6928a)) {
                        ImageViewActivity.this.d.setSelected(true);
                    }
                }
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            this.g = intent.getIntExtra("pager", 0);
            this.h = intent.getIntExtra("count", 0);
            this.i = intent.getIntExtra("mode", 0);
            this.e = intent.getParcelableArrayListExtra("data");
            this.f = intent.getStringArrayListExtra(Tags.MiHomeStorage.RESULTS);
        }
        this.c = (CustomButtonView) findViewById(R.id.commit);
        if (this.i == 1) {
            a(this.f);
            this.c.setVisibility(0);
            this.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putStringArrayListExtra("result", ImageViewActivity.this.f);
                    ImageViewActivity.this.setResult(-1, intent);
                    ImageViewActivity.this.finish();
                }
            });
        } else {
            this.c.setVisibility(8);
        }
        this.d = (ImageView) findViewById(R.id.check_mark);
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImageViewActivity.this.a((Image) ImageViewActivity.this.e.get(ImageViewActivity.this.g));
            }
        });
        this.f6902a.setAdapter(this.b);
        this.f6902a.setCurrentItem(this.g, false);
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putStringArrayListExtra("result", this.f);
        setResult(0, intent);
        finish();
    }

    /* access modifiers changed from: private */
    public void a(Image image) {
        if (image == null) {
            return;
        }
        if (this.f.contains(image.f6928a)) {
            this.f.remove(image.f6928a);
            a(this.f);
            this.d.setSelected(false);
        } else if (this.h == this.f.size()) {
            Toast.makeText(this, R.string.mis_msg_amount_limit, 0).show();
        } else {
            this.f.add(image.f6928a);
            a(this.f);
            this.d.setSelected(true);
        }
    }

    private void a(ArrayList<String> arrayList) {
        int i2;
        if (arrayList == null || arrayList.size() <= 0) {
            this.c.setText(R.string.shop_mis_action_done);
            this.c.setEnabled(false);
            i2 = 0;
        } else {
            i2 = arrayList.size();
            this.c.setEnabled(true);
        }
        this.c.setText(getString(R.string.mis_action_button_string, new Object[]{getString(R.string.shop_mis_action_done), Integer.valueOf(i2), Integer.valueOf(this.h)}));
    }

    class ImageViewPageAdapter extends FragmentPagerAdapter {
        public ImageViewPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return ImageViewFragment.a(((Image) ImageViewActivity.this.e.get(i)).f6928a);
        }

        public int getCount() {
            if (ImageViewActivity.this.e == null) {
                return 0;
            }
            return ImageViewActivity.this.e.size();
        }
    }
}
