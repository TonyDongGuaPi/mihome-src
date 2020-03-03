package com.xiaomi.passport.ui.settings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
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
    /* access modifiers changed from: private */
    public ImageView mCaptchaSwitch;
    /* access modifiers changed from: private */
    public EditText mCodeView;
    /* access modifiers changed from: private */
    public volatile String mIck;
    private SimpleFutureTask<Pair<Bitmap, String>> mImageCaptchaTask;
    private String mImageCaptchaUrl;
    private boolean mIsTallBackAlive;
    /* access modifiers changed from: private */
    public volatile boolean mIsVoiceCaptcha;
    /* access modifiers changed from: private */
    public OnCaptchaSwitchChange mOnCaptchaSwitchChange;
    private SimpleFutureTask<Boolean> mVoiceCaptchaTask;
    private String mVoiceCaptchaUrl;

    public interface OnCaptchaSwitchChange {
        void update(boolean z);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (this.mImageCaptchaTask != null) {
            this.mImageCaptchaTask.cancel(true);
        }
        if (this.mVoiceCaptchaTask != null) {
            this.mVoiceCaptchaTask.cancel(true);
        }
        super.onDetachedFromWindow();
    }

    public CaptchaView(Context context) {
        super(context);
        this.mIsVoiceCaptcha = false;
        init(context);
    }

    public CaptchaView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CaptchaView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsVoiceCaptcha = false;
        init(context);
    }

    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.passport_captcha, this);
        this.mCaptchaImageView = (ImageView) inflate.findViewById(R.id.et_captcha_image);
        this.mCaptchaSwitch = (ImageView) inflate.findViewById(R.id.et_switch);
        this.mCodeView = (EditText) inflate.findViewById(R.id.et_captcha_code);
        if (this.mCaptchaSwitch != null) {
            this.mIsTallBackAlive = AccessibilityUtil.isTallBackActive(context);
            this.mCaptchaSwitch.setVisibility(this.mIsTallBackAlive ? 0 : 8);
            this.mCaptchaSwitch.setContentDescription(getResources().getString(R.string.passport_talkback_switch_voice_captcha));
            this.mCaptchaSwitch.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Drawable drawable;
                    Drawable drawable2 = null;
                    CaptchaView.this.mCodeView.setError((CharSequence) null, (Drawable) null);
                    CaptchaView.this.cancelCaptchaDownloadTask();
                    boolean unused = CaptchaView.this.mIsVoiceCaptcha = !CaptchaView.this.mIsVoiceCaptcha;
                    CaptchaView.this.mCaptchaSwitch.setImageDrawable(CaptchaView.this.getDrawable(CaptchaView.this.mIsVoiceCaptcha ? R.drawable.passport_ic_captcha_switch_image : R.drawable.passport_ic_captcha_switch_speaker));
                    CaptchaView.this.mCaptchaSwitch.setContentDescription(CaptchaView.this.getResources().getString(CaptchaView.this.mIsVoiceCaptcha ? R.string.passport_talkback_switch_image_captcha : R.string.passport_talkback_switch_voice_captcha));
                    ImageView access$500 = CaptchaView.this.mCaptchaImageView;
                    if (CaptchaView.this.mIsVoiceCaptcha) {
                        drawable2 = CaptchaView.this.getDrawable(R.drawable.passport_ic_sound_wave_retry);
                    }
                    access$500.setImageDrawable(drawable2);
                    CaptchaView.this.mCaptchaImageView.setContentDescription(CaptchaView.this.getResources().getString(CaptchaView.this.mIsVoiceCaptcha ? R.string.passport_talkback_voice_captcha : R.string.passport_talkback_image_captcha));
                    if (CaptchaView.this.mOnCaptchaSwitchChange != null) {
                        CaptchaView.this.mOnCaptchaSwitchChange.update(CaptchaView.this.mIsVoiceCaptcha);
                    }
                    ImageView access$5002 = CaptchaView.this.mCaptchaImageView;
                    if (CaptchaView.this.mIsVoiceCaptcha) {
                        drawable = CaptchaView.this.getDrawable(R.drawable.passport_ic_sound_wave_retry);
                    } else {
                        drawable = CaptchaView.this.getDrawable(R.drawable.passport_ic_captch_retry);
                    }
                    access$5002.setImageDrawable(drawable);
                    CaptchaView.this.mCodeView.setHint(CaptchaView.this.mIsVoiceCaptcha ? R.string.passport_input_voice_captcha_hint : R.string.passport_input_captcha_hint);
                    CaptchaView.this.startDownLoad();
                }
            });
        }
        this.mCaptchaImageView.setContentDescription(getResources().getString(R.string.passport_talkback_image_captcha));
        this.mCaptchaImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CaptchaView.this.mCodeView.setText((CharSequence) null);
                CaptchaView.this.startDownLoad();
            }
        });
    }

    public String getCaptchaCode() {
        String str;
        String obj = this.mCodeView.getText().toString();
        if (!TextUtils.isEmpty(obj)) {
            return obj;
        }
        this.mCodeView.requestFocus();
        EditText editText = this.mCodeView;
        if (this.mIsVoiceCaptcha) {
            str = getResources().getString(R.string.passport_error_empty_voice_code);
        } else {
            str = getResources().getString(R.string.passport_error_empty_captcha_code);
        }
        editText.setError(str);
        return null;
    }

    public String getCaptchaIck() {
        return this.mIck;
    }

    public void onCaptchaError() {
        String str;
        this.mCodeView.requestFocus();
        EditText editText = this.mCodeView;
        if (this.mIsVoiceCaptcha) {
            str = getResources().getString(R.string.passport_wrong_voice);
        } else {
            str = getResources().getString(R.string.passport_wrong_captcha);
        }
        editText.setError(str);
    }

    public void downloadCaptcha(String str, String str2) {
        this.mVoiceCaptchaUrl = str2;
        this.mImageCaptchaUrl = str;
        this.mCodeView.setText((CharSequence) null);
        startDownLoad();
    }

    /* access modifiers changed from: private */
    public void startDownLoad() {
        if (this.mIsVoiceCaptcha) {
            downloadAndPlayVoiceCaptcha(this.mVoiceCaptchaUrl);
        } else {
            downloadImageCaptcha(this.mImageCaptchaUrl);
        }
    }

    /* access modifiers changed from: private */
    public Drawable getDrawable(int i) {
        return getResources().getDrawable(i);
    }

    /* access modifiers changed from: private */
    public void cancelCaptchaDownloadTask() {
        if (this.mImageCaptchaTask != null) {
            this.mImageCaptchaTask.cancel(true);
            this.mImageCaptchaTask = null;
        }
        if (this.mVoiceCaptchaTask != null) {
            this.mVoiceCaptchaTask.cancel(true);
            this.mVoiceCaptchaTask = null;
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
                    Pair access$900 = CaptchaView.this.blockingDownloadCaptcha(applicationContext, str2);
                    if (access$900 != null) {
                        return Pair.create(CaptchaView.getFixedImageBitmap(((File) access$900.first).getPath(), dimensionPixelSize, dimensionPixelSize2), access$900.second);
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

    private void downloadAndPlayVoiceCaptcha(final String str) {
        if (this.mVoiceCaptchaTask == null || this.mVoiceCaptchaTask.isDone()) {
            final MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                    if (CaptchaView.this.mIsVoiceCaptcha) {
                        CaptchaView.this.mCaptchaImageView.setImageDrawable(CaptchaView.this.getDrawable(R.drawable.passport_ic_sound_wave_retry));
                    }
                }
            });
            this.mVoiceCaptchaTask = new SimpleFutureTask<>(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    Context applicationContext = CaptchaView.this.getContext().getApplicationContext();
                    Pair access$900 = CaptchaView.this.blockingDownloadCaptcha(applicationContext, str);
                    if (access$900 == null) {
                        AccountLog.w(CaptchaView.TAG, "speaker captcha null");
                        return false;
                    }
                    mediaPlayer.setDataSource(applicationContext, Uri.fromFile((File) access$900.first));
                    mediaPlayer.prepare();
                    String unused = CaptchaView.this.mIck = (String) access$900.second;
                    return true;
                }
            }, new SimpleFutureTask.Callback<Boolean>() {
                /* JADX WARNING: Removed duplicated region for block: B:20:0x004b A[Catch:{ InterruptedException -> 0x004c, ExecutionException -> 0x003f, all -> 0x003b, all -> 0x005e }] */
                /* JADX WARNING: Removed duplicated region for block: B:28:0x0061  */
                /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
                /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void call(com.xiaomi.passport.uicontroller.SimpleFutureTask<java.lang.Boolean> r4) {
                    /*
                        r3 = this;
                        r0 = 0
                        java.lang.Object r4 = r4.get()     // Catch:{ InterruptedException -> 0x004c, ExecutionException -> 0x003f, all -> 0x003b }
                        java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ InterruptedException -> 0x004c, ExecutionException -> 0x003f, all -> 0x003b }
                        boolean r4 = r4.booleanValue()     // Catch:{ InterruptedException -> 0x004c, ExecutionException -> 0x003f, all -> 0x003b }
                        if (r4 == 0) goto L_0x0028
                        com.xiaomi.passport.ui.settings.CaptchaView r0 = com.xiaomi.passport.ui.settings.CaptchaView.this     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                        android.widget.ImageView r0 = r0.mCaptchaImageView     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                        com.xiaomi.passport.ui.settings.CaptchaView r1 = com.xiaomi.passport.ui.settings.CaptchaView.this     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                        int r2 = com.xiaomi.passport.ui.R.drawable.passport_ic_sound_wave     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                        android.graphics.drawable.Drawable r1 = r1.getDrawable(r2)     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                        r0.setImageDrawable(r1)     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                        android.media.MediaPlayer r0 = r0     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                        r0.start()     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                        goto L_0x0038
                    L_0x0024:
                        r0 = move-exception
                        goto L_0x0042
                    L_0x0026:
                        r0 = move-exception
                        goto L_0x004f
                    L_0x0028:
                        com.xiaomi.passport.ui.settings.CaptchaView r0 = com.xiaomi.passport.ui.settings.CaptchaView.this     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                        android.content.Context r0 = r0.getContext()     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                        int r1 = com.xiaomi.passport.ui.R.string.passport_input_voice_captcha_hint     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                        r2 = 1
                        android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r2)     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                        r0.show()     // Catch:{ InterruptedException -> 0x0026, ExecutionException -> 0x0024 }
                    L_0x0038:
                        if (r4 != 0) goto L_0x005d
                        goto L_0x0058
                    L_0x003b:
                        r4 = move-exception
                        r0 = r4
                        r4 = 0
                        goto L_0x005f
                    L_0x003f:
                        r4 = move-exception
                        r0 = r4
                        r4 = 0
                    L_0x0042:
                        java.lang.String r1 = "CaptchaView"
                        java.lang.String r2 = "downloadSpeakerCaptcha"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r0)     // Catch:{ all -> 0x005e }
                        if (r4 != 0) goto L_0x005d
                        goto L_0x0058
                    L_0x004c:
                        r4 = move-exception
                        r0 = r4
                        r4 = 0
                    L_0x004f:
                        java.lang.String r1 = "CaptchaView"
                        java.lang.String r2 = "downloadSpeakerCaptcha"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r0)     // Catch:{ all -> 0x005e }
                        if (r4 != 0) goto L_0x005d
                    L_0x0058:
                        android.media.MediaPlayer r4 = r0
                        r4.release()
                    L_0x005d:
                        return
                    L_0x005e:
                        r0 = move-exception
                    L_0x005f:
                        if (r4 != 0) goto L_0x0066
                        android.media.MediaPlayer r4 = r0
                        r4.release()
                    L_0x0066:
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.settings.CaptchaView.AnonymousClass6.call(com.xiaomi.passport.uicontroller.SimpleFutureTask):void");
                }
            });
            XiaomiPassportExecutor.getSingleton().execute(this.mVoiceCaptchaTask);
            return;
        }
        AccountLog.w(TAG, "pre speaker task is doing");
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
            java.io.File r4 = com.xiaomi.passport.ui.internal.util.BitmapUtils.saveAsFile(r4, r1, r2)     // Catch:{ IOException -> 0x003e }
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.settings.CaptchaView.blockingDownloadCaptcha(android.content.Context, java.lang.String):android.util.Pair");
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

    public void setOnCaptchaSwitchChange(OnCaptchaSwitchChange onCaptchaSwitchChange) {
        this.mOnCaptchaSwitchChange = onCaptchaSwitchChange;
    }
}
