package com.mi.global.bbs.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.ImgFragmentPageAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.view.Editor.FontTextView;
import java.util.HashSet;
import java.util.Iterator;

public class WatchBigPicActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private static final String ARRAY_TAG = "IMAGE_URLS";
    private static final String TAG = "WatchBigPicActivity";
    @BindView(2131493617)
    ViewPager mViewPager;
    private HashSet<SaveImageListener> onSaveImageListeners = new HashSet<>();
    int totalUrls = 0;
    String[] urls;

    public interface SaveImageListener {
        void onSaveImageAction(String str);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public static void jump(Context context, String str, String[] strArr) {
        context.startActivity(new Intent(context, WatchBigPicActivity.class).putExtra(TAG, str).putExtra(ARRAY_TAG, strArr));
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setTitleAndBack("", this.titleBackListener);
        String stringExtra = getIntent().getStringExtra(TAG);
        this.urls = getIntent().getStringArrayExtra(ARRAY_TAG);
        if (this.urls == null) {
            Log.d(TAG, "urls is null");
            finish();
            return;
        }
        setCustomContentView(R.layout.bbs_activity_null);
        ButterKnife.bind((Activity) this);
        this.mViewPager.setAdapter(new ImgFragmentPageAdapter(getSupportFragmentManager(), this.urls));
        this.mViewPager.addOnPageChangeListener(this);
        int index = getIndex(this.urls, stringExtra);
        this.totalUrls = this.urls.length;
        this.mViewPager.setCurrentItem(index, false);
        setTitle((CharSequence) (index + 1) + "/" + this.totalUrls);
        addSaveMenu();
    }

    private void addSaveMenu() {
        FontTextView fontTextView = new FontTextView(this);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.settings_margin_lr);
        fontTextView.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        fontTextView.setGravity(17);
        fontTextView.setTextColor(getResources().getColor(R.color.activity_black_color));
        fontTextView.setTextSize(1, 16.0f);
        fontTextView.setText(getString(R.string.save));
        fontTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.this.dispatchSaveMessage();
            }
        });
        this.menuLayout.addView(fontTextView);
    }

    private int getIndex(String[] strArr, String str) {
        if (strArr != null && !TextUtils.isEmpty(str)) {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (str.equals(strArr[i])) {
                    return i;
                }
            }
        }
        return 0;
    }

    public void onPageSelected(int i) {
        setTitle((CharSequence) (i + 1) + "/" + this.totalUrls);
    }

    /* access modifiers changed from: private */
    public void dispatchSaveMessage() {
        Iterator<SaveImageListener> it = this.onSaveImageListeners.iterator();
        while (it.hasNext()) {
            SaveImageListener next = it.next();
            if (next != null) {
                next.onSaveImageAction(this.urls[this.mViewPager.getCurrentItem()]);
            }
        }
    }

    public void addSaveImageListener(SaveImageListener saveImageListener) {
        this.onSaveImageListeners.add(saveImageListener);
    }

    public void removeSaveImageListener(SaveImageListener saveImageListener) {
        this.onSaveImageListeners.remove(saveImageListener);
    }
}
