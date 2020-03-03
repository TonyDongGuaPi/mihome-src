package com.mi.global.shop.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.shop.R;
import com.mi.global.shop.util.ImageUtil;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.util.MiToast;
import com.mi.util.ResourceUtil;
import com.xiaomi.smarthome.library.common.widget.crop.CropImageActivity;
import com.xiaomi.youpin.share.ShareObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public class ReviewImageEditActivity extends BaseActivity implements View.OnClickListener {
    public static final int REQUEST_CROP_IMAGE = 1;

    /* renamed from: a  reason: collision with root package name */
    private Uri f5426a;
    private String b;
    @BindView(2131493026)
    CustomButtonView btnDone;
    @BindView(2131493363)
    RelativeLayout footer;
    @BindView(2131493543)
    ImageView ivEditCrop;
    @BindView(2131493544)
    ImageView ivEditDelete;
    @BindView(2131493550)
    ImageView ivImage;
    @BindView(2131493961)
    RelativeLayout rlEditCrop;
    @BindView(2131493962)
    RelativeLayout rlEditDelete;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_order_review_image_edit);
        ButterKnife.bind((Activity) this);
        setTitle((CharSequence) getString(R.string.order_review_edit));
        this.mBackView.setVisibility(0);
        this.mCartView.setVisibility(8);
        if (TextUtils.isEmpty(getIntent().getStringExtra("path"))) {
            MiToast.a((Context) this, (CharSequence) getString(R.string.error_invalid_data), 3000);
            finish();
        }
        initView();
        initListener();
    }

    public void initView() {
        String stringExtra = getIntent().getStringExtra("path");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.ivImage.setImageBitmap(ImageUtil.a(stringExtra, 600, 600));
        }
    }

    public void initListener() {
        this.rlEditCrop.setOnClickListener(this);
        this.ivEditCrop.setOnClickListener(this);
        this.rlEditDelete.setOnClickListener(this);
        this.ivEditDelete.setOnClickListener(this);
        this.btnDone.setOnClickListener(this);
    }

    public void onClick(View view) {
        Uri uri;
        int id = view.getId();
        if (id == R.id.rl_edit_crop || id == R.id.iv_edit_crop) {
            File file = new File(getIntent().getStringExtra("path"));
            this.b = addRandomNumber(getIntent().getStringExtra("path"));
            if (!TextUtils.isEmpty(this.b)) {
                File file2 = new File(this.b);
                if (!file2.exists()) {
                    file2.mkdir();
                }
                copyfile(file, file2, true);
                this.f5426a = Uri.fromFile(file2);
                if (Build.VERSION.SDK_INT >= 24) {
                    uri = FileProvider.getUriForFile(this, ResourceUtil.a("file_provider_authorities"), file2);
                } else {
                    uri = Uri.fromFile(file2);
                }
                a(uri, 400, 400, 1);
                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                intent.setData(Uri.fromFile(file2));
                sendBroadcast(intent);
            }
        } else if (id == R.id.rl_edit_delete || id == R.id.iv_edit_delete) {
            Intent intent2 = new Intent();
            intent2.putExtra("deleteUrl", getIntent().getStringExtra("path"));
            setResult(100, intent2);
            finish();
        } else if (id == R.id.btn_done) {
            if (!TextUtils.isEmpty(this.b)) {
                Intent intent3 = new Intent();
                intent3.putExtra("currentPath", getIntent().getStringExtra("path"));
                intent3.putExtra("newPath", this.b);
                setResult(101, intent3);
            }
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.f5426a != null && intent != null && i == 1) {
            try {
                this.ivImage.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), this.f5426a));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void a(Uri uri, int i, int i2, int i3) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(1);
        }
        intent.setDataAndType(uri, ShareObject.d);
        intent.putExtra("crop", "true");
        intent.putExtra(CropImageActivity.ASPECT_X, 1);
        intent.putExtra(CropImageActivity.ASPECT_Y, 1);
        intent.putExtra(CropImageActivity.OUTPUT_X, i);
        intent.putExtra(CropImageActivity.OUTPUT_Y, i2);
        intent.putExtra("scale", false);
        intent.putExtra(AgentOptions.k, this.f5426a);
        intent.putExtra(CropImageActivity.RETURN_DATA, false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, i3);
    }

    public static void copyfile(File file, File file2, Boolean bool) {
        if (file.exists() && file.isFile() && file.canRead()) {
            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            if (file2.exists() && bool.booleanValue()) {
                file2.delete();
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileInputStream.close();
                        fileOutputStream.close();
                        return;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public String addRandomNumber(String str) {
        Random random = new Random();
        if (!str.contains(".")) {
            return "";
        }
        String substring = str.substring(0, str.lastIndexOf(46));
        String substring2 = str.substring(str.lastIndexOf(46) + 1, str.length());
        return substring + random.nextInt(100000) + '.' + substring2;
    }
}
