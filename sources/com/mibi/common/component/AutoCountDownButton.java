package com.mibi.common.component;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.Button;

public class AutoCountDownButton extends Button {
    private static final int DEFAULT_TICK_COUNT = 3;
    /* access modifiers changed from: private */
    public CountDownListener mListener;
    private CountDownTask mTask;
    private int mTickNum = 3;
    /* access modifiers changed from: private */
    public boolean mTicking;

    public interface CountDownListener {
        void a();

        void a(int i);

        void b();
    }

    public AutoCountDownButton(Context context) {
        super(context);
    }

    public AutoCountDownButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setTickNum(int i) {
        this.mTickNum = i;
    }

    public int getTickNum() {
        return this.mTickNum;
    }

    public void startTick() {
        if (!this.mTicking) {
            this.mTask = new CountDownTask();
            this.mTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Integer[]{Integer.valueOf(this.mTickNum)});
            this.mTicking = true;
        }
    }

    public void stopTick() {
        if (this.mTicking) {
            if (this.mTask != null) {
                this.mTask.cancel(true);
            }
            this.mTicking = false;
        }
    }

    public void setCountDownListener(CountDownListener countDownListener) {
        this.mListener = countDownListener;
    }

    private class CountDownTask extends AsyncTask<Integer, Integer, Void> {
        private CountDownTask() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Integer... numArr) {
            int intValue = numArr.length > 0 ? numArr[0].intValue() : 0;
            while (intValue > 0 && !isCancelled()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                intValue--;
                publishProgress(new Integer[]{Integer.valueOf(intValue)});
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            if (AutoCountDownButton.this.mListener != null) {
                AutoCountDownButton.this.mListener.a();
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void onProgressUpdate(Integer... numArr) {
            if (numArr.length > 0) {
                int intValue = numArr[0].intValue();
                if (AutoCountDownButton.this.mListener != null) {
                    AutoCountDownButton.this.mListener.a(intValue);
                }
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Void voidR) {
            boolean unused = AutoCountDownButton.this.mTicking = false;
            if (AutoCountDownButton.this.mListener != null) {
                AutoCountDownButton.this.mListener.b();
            }
        }
    }
}
