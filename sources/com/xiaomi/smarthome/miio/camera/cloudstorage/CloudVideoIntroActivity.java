package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import java.util.Locale;

public class CloudVideoIntroActivity extends BaseActivity {
    private static final String TAG = "CloudVideoIntroActivity";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cloud_video_intro);
        final String stringExtra = getIntent().getStringExtra("extra_device_did");
        ImageView imageView = (ImageView) findViewById(R.id.iv_cv_title);
        ImageView imageView2 = (ImageView) findViewById(R.id.iv_cv_intro_img_1);
        ImageView imageView3 = (ImageView) findViewById(R.id.iv_cv_intro_img_2);
        String language = getResources().getConfiguration().locale.getLanguage();
        String language2 = Locale.getDefault().getLanguage();
        if (!"zh".equalsIgnoreCase(language) || !"zh".equalsIgnoreCase(language2)) {
            imageView.setImageResource(R.drawable.cloud_video_intro_title_en);
            imageView2.setImageResource(R.drawable.cloud_video_intro_img_1_en);
            imageView3.setImageResource(R.drawable.cloud_video_intro_img_2_en);
        } else {
            imageView.setImageResource(R.drawable.cloud_video_intro_title);
            imageView2.setImageResource(R.drawable.cloud_video_intro_img_1);
            imageView3.setImageResource(R.drawable.cloud_video_intro_img_2);
        }
        findViewById(R.id.tv_buy).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(CloudVideoIntroActivity.this.getContext(), stringExtra);
            }
        });
    }
}
