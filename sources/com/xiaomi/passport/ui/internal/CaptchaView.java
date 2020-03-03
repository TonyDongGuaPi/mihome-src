package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.uicontroller.SimpleFutureTask;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class CaptchaView extends LinearLayout {
    private static final String TAG = "CaptchaView";
    /* access modifiers changed from: private */
    public ImageView mCaptchaImageView;
    private EditText mCodeView;
    /* access modifiers changed from: private */
    public volatile String mIck;
    private SimpleFutureTask<Pair<Bitmap, String>> mImageCaptchaTask;
    private String mImageCaptchaUrl;

    public CaptchaView(Context context) {
        super(context);
        init(context);
    }

    public CaptchaView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CaptchaView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.passport_captcha, this);
        this.mCaptchaImageView = (ImageView) inflate.findViewById(R.id.et_captcha_image);
        this.mCodeView = (EditText) inflate.findViewById(R.id.et_captcha_code);
        this.mCaptchaImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CaptchaView.this.startDownLoad();
            }
        });
    }

    public String getCaptchaCode() {
        String obj = this.mCodeView.getText().toString();
        if (!TextUtils.isEmpty(obj)) {
            return obj;
        }
        this.mCodeView.requestFocus();
        this.mCodeView.setError(getResources().getString(R.string.passport_error_empty_captcha_code));
        return null;
    }

    public String getCaptchaIck() {
        return this.mIck;
    }

    public void onCaptchaError() {
        this.mCodeView.requestFocus();
        this.mCodeView.setError(getResources().getString(R.string.passport_wrong_captcha));
    }

    public void downloadCaptcha(String str) {
        this.mImageCaptchaUrl = str;
        startDownLoad();
    }

    /* access modifiers changed from: private */
    public void startDownLoad() {
        downloadImageCaptcha(this.mImageCaptchaUrl);
    }

    private Drawable getDrawable(int i) {
        return getResources().getDrawable(i);
    }

    private void cancelCaptchaDownloadTask() {
        if (this.mImageCaptchaTask != null) {
            this.mImageCaptchaTask.cancel(true);
            this.mImageCaptchaTask = null;
        }
    }

    private void downloadImageCaptcha(String str) {
        if (this.mImageCaptchaTask == null || this.mImageCaptchaTask.isDone()) {
            final Context applicationContext = getContext().getApplicationContext();
            final int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.passport_captcha_img_w);
            final int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.passport_captcha_img_h);
            final String str2 = str;
            this.mImageCaptchaTask = new SimpleFutureTask<>(new Callable<Pair<Bitmap, String>>() {
                public Pair<Bitmap, String> call() throws Exception {
                    Pair access$300 = CaptchaView.this.blockingDownloadCaptcha(applicationContext, str2);
                    if (access$300 != null) {
                        return Pair.create(CaptchaView.getFixedImageBitmap(((File) access$300.first).getPath(), dimensionPixelSize, dimensionPixelSize2), access$300.second);
                    }
                    AccountLog.e(CaptchaView.TAG, "image captcha result is null");
                    return null;
                }
            }, new SimpleFutureTask.Callback<Pair<Bitmap, String>>() {
                public void call(SimpleFutureTask<Pair<Bitmap, String>> simpleFutureTask) {
                    try {
                        Pair pair = (Pair) simpleFutureTask.get();
                        if (pair == null) {
                            Toast.makeText(CaptchaView.this.getContext(), R.string.passport_input_captcha_hint, 1).show();
                            return;
                        }
                        String unused = CaptchaView.this.mIck = (String) pair.second;
                        CaptchaView.this.mCaptchaImageView.setImageBitmap((Bitmap) pair.first);
                    } catch (InterruptedException e) {
                        AccountLog.e(CaptchaView.TAG, "downloadCaptchaImage", e);
                    } catch (ExecutionException e2) {
                        AccountLog.e(CaptchaView.TAG, "downloadCaptchaImage", e2);
                    }
                }
            });
            XiaomiPassportExecutor.getSingleton().execute(this.mImageCaptchaTask);
            return;
        }
        AccountLog.w(TAG, "pre image task passport_input_speaker_capcha_hintis doing");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0023 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0024 A[SYNTHETIC, Splitter:B:12:0x0024] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.util.Pair<java.io.File, java.lang.String> blockingDownloadCaptcha(android.content.Context r4, java.lang.String r5) {
        /*
            r3 = this;
            r0 = 0
            com.xiaomi.accountsdk.request.SimpleRequest$StreamContent r5 = com.xiaomi.accountsdk.request.SimpleRequestForAccount.getAsStream(r5, r0, r0)     // Catch:{ IOException -> 0x0018, AccessDeniedException -> 0x000f, AuthenticationFailureException -> 0x0006 }
            goto L_0x0021
        L_0x0006:
            r5 = move-exception
            java.lang.String r1 = "CaptchaView"
            java.lang.String r2 = "getCaptcha"
            com.xiaomi.accountsdk.utils.AccountLog.w(r1, r2, r5)
            goto L_0x0020
        L_0x000f:
            r5 = move-exception
            java.lang.String r1 = "CaptchaView"
            java.lang.String r2 = "getCaptcha"
            com.xiaomi.accountsdk.utils.AccountLog.w(r1, r2, r5)
            goto L_0x0020
        L_0x0018:
            r5 = move-exception
            java.lang.String r1 = "CaptchaView"
            java.lang.String r2 = "getCaptcha"
            com.xiaomi.accountsdk.utils.AccountLog.w(r1, r2, r5)
        L_0x0020:
            r5 = r0
        L_0x0021:
            if (r5 != 0) goto L_0x0024
            return r0
        L_0x0024:
            java.io.InputStream r1 = r5.getStream()     // Catch:{ IOException -> 0x003e }
            java.lang.String r2 = "captcha"
            java.io.File r4 = com.xiaomi.passport.ui.internal.util.BitmapFactory.saveAsFile(r4, r1, r2)     // Catch:{ IOException -> 0x003e }
            java.lang.String r1 = "ick"
            java.lang.String r1 = r5.getHeader(r1)     // Catch:{ IOException -> 0x003e }
            android.util.Pair r4 = android.util.Pair.create(r4, r1)     // Catch:{ IOException -> 0x003e }
            r5.closeStream()
            return r4
        L_0x003c:
            r4 = move-exception
            goto L_0x004a
        L_0x003e:
            r4 = move-exception
            java.lang.String r1 = "CaptchaView"
            java.lang.String r2 = "getCaptcha"
            com.xiaomi.accountsdk.utils.AccountLog.w(r1, r2, r4)     // Catch:{ all -> 0x003c }
            r5.closeStream()
            return r0
        L_0x004a:
            r5.closeStream()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.internal.CaptchaView.blockingDownloadCaptcha(android.content.Context, java.lang.String):android.util.Pair");
    }

    /* access modifiers changed from: private */
    public static Bitmap getFixedImageBitmap(String str, int i, int i2) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeFile, i, i2, true);
        if (decodeFile != createScaledBitmap) {
            decodeFile.recycle();
        }
        return createScaledBitmap;
    }
}
