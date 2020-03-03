package com.xiaomi.smarthome.miio.camera.face.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.util.FaceUtils;

public class FaceEmptyActivity extends FaceManagerBaseActivity implements View.OnClickListener {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_face_empty);
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.type_in_face);
        findViewById(R.id.title_bar_more).setVisibility(8);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.btn_album).setOnClickListener(this);
        findViewById(R.id.btn_camera).setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_album) {
            FaceUtils.tryChoosePicture(this, mFaceManager.getDeviceId());
        } else if (id == R.id.btn_camera) {
            FaceUtils.tryJumpFaceCamera(this);
        } else if (id == R.id.title_bar_return) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 101:
            case 102:
                if (i2 == -1) {
                    FaceUtils.processMarkFace(getContext(), intent.getStringExtra("faceId"), mFaceManager, new FaceManager.IFaceCallback() {
                        public void onSuccess(Object obj, Object obj2) {
                            ToastUtil.a((int) R.string.action_success);
                            FaceEmptyActivity.this.startActivity(new Intent(FaceEmptyActivity.this, FaceManagerActivity.class));
                            FaceEmptyActivity.this.finish();
                        }

                        public void onFailure(int i, String str) {
                            ToastUtil.a((int) R.string.action_fail);
                        }
                    });
                    return;
                }
                return;
            default:
                return;
        }
    }
}
