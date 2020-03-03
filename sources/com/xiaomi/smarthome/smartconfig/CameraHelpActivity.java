package com.xiaomi.smarthome.smartconfig;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.CloseableImage;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraHelpActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private SimpleDraweeView f22233a;
    private DataSource<CloseableReference<CloseableImage>> b;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_camera_help);
        String stringExtra = getIntent().getStringExtra("model");
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.follow_me);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraHelpActivity.this.finish();
            }
        });
        this.f22233a = (SimpleDraweeView) findViewById(R.id.image);
        findViewById(R.id.knows).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraHelpActivity.this.finish();
            }
        });
        b(stringExtra);
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        if (this.f22233a.getHierarchy() == null) {
            this.f22233a.setHierarchy(new GenericDraweeHierarchyBuilder(this.f22233a.getResources()).setFadeDuration(200).setPlaceholderImage(this.f22233a.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build());
        }
        if (!TextUtils.isEmpty(str) && str.startsWith("http")) {
            this.f22233a.setImageURI(Uri.parse(str));
            DraweeController controller = this.f22233a.getController();
            if (controller instanceof AbstractDraweeController) {
                ((AbstractDraweeController) controller).addControllerListener(new BaseControllerListener() {
                    public void onFailure(String str, Throwable th) {
                        Fresco.getImagePipeline().evictFromMemoryCache(Uri.parse(str));
                        Fresco.getImagePipeline().evictFromDiskCache(Uri.parse(str));
                    }
                });
            }
        }
    }

    private void b(String str) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a((Context) this, new NetRequest.Builder().a("POST").b("/plugin/get_model_guide_pic").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.optString("guide_pic", "");
            }
        }, Crypto.RC4, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                BluetoothLog.c(String.format("onSuccess %s", new Object[]{str}));
                if (!TextUtils.isEmpty(str)) {
                    CameraHelpActivity.this.a(str);
                }
            }

            public void onFailure(Error error) {
                BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.b != null) {
            this.b.close();
        }
    }
}
