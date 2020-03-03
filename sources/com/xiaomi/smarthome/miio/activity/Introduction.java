package com.xiaomi.smarthome.miio.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;

public class Introduction extends BaseActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.intruduction);
        String string = getIntent().getExtras().getString("model");
        View findViewById = findViewById(R.id.main_icon_layout);
        ImageView imageView = (ImageView) findViewById(R.id.main_icon);
        ImageView imageView2 = (ImageView) findViewById(R.id.main_text_icon);
        TextView textView = (TextView) findViewById(R.id.device_intro_text_1);
        TextView textView2 = (TextView) findViewById(R.id.device_intro_text_2);
        TextView textView3 = (TextView) findViewById(R.id.device_intro_text_3);
        TextView textView4 = (TextView) findViewById(R.id.btn_go_detail);
        ((ImageView) findViewById(R.id.module_a_3_return_transparent_btn)).setImageResource(R.drawable.intro_back_icon);
        findViewById(R.id.module_a_3_return_transparent_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Introduction.this.finish();
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UrlDispatchManger.a().c("https://home.mi.com/shop/main");
            }
        });
        if (string.equalsIgnoreCase("yunyi.camera.v1")) {
            imageView.setImageResource(R.drawable.intro_camera);
            imageView2.setImageResource(R.drawable.intro_camera_text);
            textView.setText(R.string.intro_camera_text_1);
            textView2.setText(R.string.intro_camera_text_2);
            textView3.setText(R.string.intro_camera_text_3);
            textView4.setBackgroundResource(R.drawable.intro_camera_btn);
            textView4.setTextColor(getResources().getColor(R.color.intro_camera_btn_color));
            findViewById.setBackgroundColor(getResources().getColor(R.color.intro_camera_btn_color));
            return;
        }
        imageView.setImageResource(R.drawable.intro_plug);
        imageView2.setImageResource(R.drawable.intro_plug_text);
        textView.setText(R.string.intro_plug_text_1);
        textView2.setText(R.string.intro_plug_text_2);
        textView3.setText(R.string.intro_plug_text_3);
        textView4.setBackgroundResource(R.drawable.intro_plug_btn);
        textView4.setTextColor(getResources().getColor(R.color.intro_plug_btn_color));
        findViewById.setBackgroundColor(getResources().getColor(R.color.intro_plug_btn_color));
    }
}
