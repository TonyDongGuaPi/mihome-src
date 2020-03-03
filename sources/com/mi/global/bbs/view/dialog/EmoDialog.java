package com.mi.global.bbs.view.dialog;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.EmoAdapter;
import com.mi.global.bbs.model.SignHomeData;
import com.mi.global.bbs.utils.BasePageChangeListener;
import com.mi.global.bbs.view.CheckedImageView;
import java.util.ArrayList;
import java.util.List;

public class EmoDialog {
    int EVERY_PAGE_COUNT = 9;
    View contentView;
    /* access modifiers changed from: private */
    public Context context;
    @BindView(2131493222)
    LinearLayout mEmoPageIndicate;
    @BindView(2131493223)
    ViewPager mEmoPager;
    private EmoAdapter.OnEmotionItemClickListener mOnEmotionItemClickListener;

    public View getContentView() {
        return this.contentView;
    }

    public EmoDialog(Context context2, ViewGroup viewGroup) {
        this.context = context2;
        this.contentView = LayoutInflater.from(context2).inflate(R.layout.bbs_emo_dialog_layout, viewGroup, false);
        ButterKnife.bind((Object) this, this.contentView);
    }

    public void setOnEmotionItemClickListener(EmoAdapter.OnEmotionItemClickListener onEmotionItemClickListener) {
        this.mOnEmotionItemClickListener = onEmotionItemClickListener;
    }

    public void setEmoList(List<SignHomeData.SignBean.EmotionBean> list) {
        int size = list.size() / this.EVERY_PAGE_COUNT;
        if (list.size() % this.EVERY_PAGE_COUNT != 0) {
            size++;
        }
        ArrayList arrayList = new ArrayList();
        int dimensionPixelOffset = this.context.getResources().getDimensionPixelOffset(R.dimen.indicate_height);
        int dimensionPixelOffset2 = this.context.getResources().getDimensionPixelOffset(R.dimen.indicate_margin);
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            if (this.EVERY_PAGE_COUNT * i2 < list.size()) {
                arrayList.add(list.subList(this.EVERY_PAGE_COUNT * i, this.EVERY_PAGE_COUNT * i2));
            } else {
                arrayList.add(list.subList(this.EVERY_PAGE_COUNT * i, list.size()));
            }
            CheckedImageView checkedImageView = new CheckedImageView(this.context);
            checkedImageView.setImageResource(R.drawable.circle_indicate_point);
            if (i == 0) {
                checkedImageView.setChecked(true);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dimensionPixelOffset, dimensionPixelOffset);
            layoutParams.setMarginEnd(dimensionPixelOffset2);
            this.mEmoPageIndicate.addView(checkedImageView, i, layoutParams);
            i = i2;
        }
        EmoPagerAdapter emoPagerAdapter = new EmoPagerAdapter(arrayList, this.context);
        emoPagerAdapter.setOnEmotionItemClickListener(this.mOnEmotionItemClickListener);
        this.mEmoPager.setAdapter(emoPagerAdapter);
        this.mEmoPager.addOnPageChangeListener(new BasePageChangeListener() {
            public void onPageSelected(int i) {
                EmoDialog.this.onSelectedOnIndicateLayout(i);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onSelectedOnIndicateLayout(int i) {
        int i2 = 0;
        while (i2 < this.mEmoPageIndicate.getChildCount()) {
            ((CheckedImageView) this.mEmoPageIndicate.getChildAt(i2)).setChecked(i2 == i);
            i2++;
        }
    }

    class EmoPagerAdapter extends PagerAdapter {
        List<List<SignHomeData.SignBean.EmotionBean>> emotionlists;
        Context mContext;
        private EmoAdapter.OnEmotionItemClickListener mOnEmotionItemClickListener;

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public EmoPagerAdapter(List<List<SignHomeData.SignBean.EmotionBean>> list, Context context) {
            this.emotionlists = list;
            this.mContext = context;
        }

        public int getCount() {
            return this.emotionlists.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.bbs_emo_list, viewGroup, false);
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.emo_recyclelist);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(EmoDialog.this.context, 3);
            gridLayoutManager.setOrientation(1);
            recyclerView.setLayoutManager(gridLayoutManager);
            EmoAdapter emoAdapter = new EmoAdapter(this.mContext, this.emotionlists.get(i));
            recyclerView.setAdapter(emoAdapter);
            emoAdapter.setOnEmotionItemClickListener(this.mOnEmotionItemClickListener);
            viewGroup.addView(inflate, i);
            return inflate;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public void setOnEmotionItemClickListener(EmoAdapter.OnEmotionItemClickListener onEmotionItemClickListener) {
            this.mOnEmotionItemClickListener = onEmotionItemClickListener;
        }
    }
}
