package com.mi.global.bbs.ui.forum;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.PagerSlidingTabStrip;

public class NewsForumActivity_ViewBinding implements Unbinder {
    private NewsForumActivity target;

    @UiThread
    public NewsForumActivity_ViewBinding(NewsForumActivity newsForumActivity) {
        this(newsForumActivity, newsForumActivity.getWindow().getDecorView());
    }

    @UiThread
    public NewsForumActivity_ViewBinding(NewsForumActivity newsForumActivity, View view) {
        this.target = newsForumActivity;
        newsForumActivity.newsForumNagTab = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.news_forum_nag, "field 'newsForumNagTab'", PagerSlidingTabStrip.class);
        newsForumActivity.newsForumPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.news_forum_pager, "field 'newsForumPager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        NewsForumActivity newsForumActivity = this.target;
        if (newsForumActivity != null) {
            this.target = null;
            newsForumActivity.newsForumNagTab = null;
            newsForumActivity.newsForumPager = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
