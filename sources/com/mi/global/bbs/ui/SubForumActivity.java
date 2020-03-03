package com.mi.global.bbs.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;

public class SubForumActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_forum);
        setTitleAndBack(R.string.subforums_title, this.titleBackListener);
        setLeftAndTitleAndRight(R.drawable.bbs_arrow_up_black, R.string.subforums_title, R.drawable.bbs_ic_search, new View.OnClickListener() {
            public void onClick(View view) {
                SubForumActivity.this.startActivity(new Intent(SubForumActivity.this, SearchActivity.class));
            }
        });
    }

    public static void jump(Context context) {
        context.startActivity(new Intent(context, SubForumActivity.class));
    }
}
