package com.xiaomi.smarthome.audiorecord;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordButton extends Button {
    private static final int MAX_INTERVAL_TIME = 60000;
    private static final int MIN_INTERVAL_TIME = 700;
    public String AUDOI_DIR;
    int delta = 50;
    private String mAudioFile;
    /* access modifiers changed from: private */
    public RecordButtonUtil mAudioUtil = new RecordButtonUtil();
    Dialog mRecordDialog;
    RecordListener mRecordListerer;
    long mStartTime;
    ObtainDecibelThread mThread;
    /* access modifiers changed from: private */
    public Handler mVolumeHandler = new ShowVolumeHandler(this);
    private final DialogInterface.OnDismissListener onDismiss = new DialogInterface.OnDismissListener() {
        public void onDismiss(DialogInterface dialogInterface) {
            RecordButton.this.stopRecording();
        }
    };

    public interface OnFinishedRecordListener {
        void a();

        void a(String str, int i);
    }

    public interface OnVolumeChangeListener {
        void a(Dialog dialog, int i);
    }

    public interface RecordListener {
        void a();

        void a(String str, int i);

        void a(boolean z);

        void b();
    }

    public RecordButton(Context context) {
        super(context);
    }

    public RecordButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private String getReadableDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                initlization();
                break;
            case 1:
                if (!isTouchOutOfBound(motionEvent)) {
                    finishRecord();
                    break;
                } else {
                    cancelRecord();
                    break;
                }
            case 2:
                if (!isTouchOutOfBound(motionEvent)) {
                    if (this.mRecordListerer != null) {
                        this.mRecordListerer.a(true);
                        break;
                    }
                } else if (this.mRecordListerer != null) {
                    this.mRecordListerer.a(false);
                    break;
                }
                break;
        }
        return true;
    }

    private boolean isTouchOutOfBound(MotionEvent motionEvent) {
        int width = getWidth();
        int height = getHeight();
        int y = (int) motionEvent.getY();
        int x = (int) motionEvent.getX();
        return y < (-this.delta) || y > this.delta + height || x < (-this.delta) || x > width + this.delta;
    }

    private void initlization() {
        if (TextUtils.isEmpty(this.AUDOI_DIR)) {
            this.AUDOI_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + SHApplication.getAppContext().getPackageName() + "/audio";
        }
        this.mAudioFile = this.AUDOI_DIR + "/" + getReadableDate() + "-" + System.currentTimeMillis();
        File file = new File(this.AUDOI_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            new File(this.mAudioFile).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mStartTime = System.currentTimeMillis();
        if (this.mRecordDialog == null) {
            this.mRecordDialog = new Dialog(getContext());
            this.mRecordDialog.setOnDismissListener(this.onDismiss);
        }
        startRecording();
    }

    /* access modifiers changed from: private */
    public void finishRecord() {
        stopRecording();
        this.mRecordDialog.dismiss();
        if (System.currentTimeMillis() - this.mStartTime < 700) {
            ToastUtil.a((CharSequence) "too short");
            new File(this.mAudioFile).delete();
        } else if (this.mRecordListerer != null) {
            this.mRecordListerer.a(this.mAudioFile, (int) ((System.currentTimeMillis() - this.mStartTime) / 1000));
        }
    }

    public void setRecordListener(RecordListener recordListener) {
        this.mRecordListerer = recordListener;
    }

    private void cancelRecord() {
        stopRecording();
        this.mRecordDialog.dismiss();
        new File(this.mAudioFile).delete();
        if (this.mRecordListerer != null) {
            this.mRecordListerer.a();
        }
    }

    private void startRecording() {
        this.mAudioUtil.a(this.mAudioFile);
        this.mAudioUtil.a();
        this.mThread = new ObtainDecibelThread();
        this.mThread.start();
        if (this.mRecordListerer != null) {
            this.mRecordListerer.b();
        }
    }

    /* access modifiers changed from: private */
    public void stopRecording() {
        if (this.mThread != null) {
            this.mThread.a();
            this.mThread = null;
        }
        if (this.mAudioUtil != null) {
            this.mAudioUtil.c();
        }
    }

    private class ObtainDecibelThread extends Thread {
        private volatile boolean b;

        private ObtainDecibelThread() {
            this.b = true;
        }

        public void a() {
            this.b = false;
        }

        public void run() {
            while (this.b) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (System.currentTimeMillis() - RecordButton.this.mStartTime >= 60000) {
                    RecordButton.this.mVolumeHandler.sendEmptyMessage(-1);
                }
                if (RecordButton.this.mAudioUtil == null || !this.b) {
                    a();
                } else {
                    int b2 = RecordButton.this.mAudioUtil.b();
                    if (b2 != 0) {
                        RecordButton.this.mVolumeHandler.sendEmptyMessage(b2);
                    }
                }
            }
        }
    }

    static class ShowVolumeHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<RecordButton> f13772a;

        public ShowVolumeHandler(RecordButton recordButton) {
            this.f13772a = new WeakReference<>(recordButton);
        }

        public void handleMessage(Message message) {
            RecordButton recordButton = (RecordButton) this.f13772a.get();
            if (message.what == -1) {
                recordButton.finishRecord();
            }
        }
    }

    public static class RecordButtonUtil {

        /* renamed from: a  reason: collision with root package name */
        MediaPlayer f13770a;
        private String b;
        private boolean c;
        /* access modifiers changed from: private */
        public boolean d;
        /* access modifiers changed from: private */
        public OnPlayListener e;
        private MediaRecorder f;

        public interface OnPlayListener {
            void a();

            void b();
        }

        private void d() {
            this.f = new MediaRecorder();
            try {
                this.f.setAudioSource(1);
                this.f.setOutputFormat(3);
                this.f.setAudioEncoder(1);
                this.f.setOutputFile(this.b);
                this.c = true;
            } catch (Exception e2) {
                Log.d(RecordButton.class.getSimpleName(), "safe log");
                e2.printStackTrace();
                this.c = false;
            }
        }

        public void a(String str) {
            this.b = str;
        }

        public void a() {
            d();
            try {
                this.f.prepare();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.f.start();
        }

        public int b() {
            if (this.f == null || !this.c) {
                return 0;
            }
            int maxAmplitude = this.f.getMaxAmplitude();
            return maxAmplitude != 0 ? ((int) ((Math.log((double) maxAmplitude) * 10.0d) / Math.log(10.0d))) / 7 : maxAmplitude;
        }

        public void c() {
            if (this.f != null) {
                this.f.stop();
                this.f.release();
                this.f = null;
                this.c = false;
            }
        }

        public void b(String str) {
            if (this.d) {
                return;
            }
            if (!TextUtils.isEmpty(str)) {
                this.f13770a = new MediaPlayer();
                try {
                    this.f13770a.setDataSource(str);
                    this.f13770a.prepare();
                    this.f13770a.start();
                    if (this.e != null) {
                        this.e.b();
                    }
                    this.d = true;
                    this.f13770a.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            if (RecordButtonUtil.this.e != null) {
                                RecordButtonUtil.this.e.a();
                            }
                            mediaPlayer.release();
                            RecordButtonUtil.this.f13770a = null;
                            boolean unused = RecordButtonUtil.this.d = false;
                        }
                    });
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                ToastUtil.a((CharSequence) "not found audio file");
            }
        }
    }
}
