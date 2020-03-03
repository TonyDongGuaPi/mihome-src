package com.mi.global.bbs.ui.forum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.MyViewPagerAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.ui.SearchActivity;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.ui.post.PostActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.view.PagerSlidingTabStrip;
import java.util.ArrayList;

public class NewsForumActivity extends BaseActivity {
    public static final int FROM_FORUM = 1;
    public static final String FROM_KEY = "from";
    public static final int FROM_NEWS = 0;
    private final int RC_SEARCH = PhotoshopDirectory.an;
    private int fromKey;
    private HotFragment hotFragment;
    private LatestFragment latestFragment;
    @BindView(2131493741)
    PagerSlidingTabStrip newsForumNagTab;
    @BindView(2131493743)
    ViewPager newsForumPager;
    ImageView newsForumPost;
    ImageView newsForumSearch;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_news_forum);
        setTitleAndBack(R.string.home_forum, this.titleBackListener);
        ButterKnife.bind((Activity) this);
        this.fromKey = getIntent().getIntExtra("from", 0);
        initToolbarItem();
        initTab();
    }

    private void initToolbarItem() {
        LayoutInflater.from(this).inflate(R.layout.bbs_news_forum_action_layout, this.menuLayout, true);
        this.newsForumPost = (ImageView) this.menuLayout.findViewById(R.id.news_forum_post);
        this.newsForumSearch = (ImageView) this.menuLayout.findViewById(R.id.news_forum_search);
        this.newsForumPost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NewsForumActivity.this.startActivityForResult(new Intent(NewsForumActivity.this, PostActivity.class), Constants.RequestCode.REQUEST_GO_POST);
            }
        });
        this.newsForumSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NewsForumActivity.this.startActivity(new Intent(NewsForumActivity.this, SearchActivity.class));
            }
        });
    }

    public static void jump(Context context, int i) {
        context.startActivity(new Intent(context, NewsForumActivity.class).putExtra("from", i));
    }

    private void initTab() {
        ArrayList arrayList = new ArrayList();
        this.hotFragment = new HotFragment();
        this.latestFragment = new LatestFragment();
        arrayList.add(this.hotFragment);
        arrayList.add(this.latestFragment);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.newsForumPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.forum_tab), arrayList));
        this.newsForumPager.setCurrentItem(this.fromKey);
        this.newsForumNagTab.setViewPager(this.newsForumPager);
        this.newsForumNagTab.setUnderlineHeight((int) TypedValue.applyDimension(1, 1.0f, displayMetrics));
        this.newsForumNagTab.setUnderlineColor(getResources().getColor(R.color.user_center_divider_color));
        this.newsForumNagTab.setIndicatorColor(getResources().getColor(R.color.main_yellow));
        this.newsForumNagTab.setIndicatorHeight((int) TypedValue.applyDimension(1, 2.0f, displayMetrics));
        this.newsForumNagTab.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        this.newsForumNagTab.setSelectedTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR_SELECT));
        this.newsForumNagTab.setTextSize((int) TypedValue.applyDimension(2, 15.0f, displayMetrics));
        this.newsForumNagTab.setDividerColor(0);
        this.newsForumNagTab.setAllCaps(false);
        this.newsForumNagTab.setShouldExpand(true);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Bundle extras;
        super.onActivityResult(i, i2, intent);
        if (!(i != 55555 || intent == null || (extras = intent.getExtras()) == null)) {
            WebActivity.jump(this, extras.getString("url"));
        }
        if (i == 1073 && this.newsForumSearch != null) {
            this.newsForumSearch.setAlpha(1.0f);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }
}
