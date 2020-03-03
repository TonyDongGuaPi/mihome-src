package com.mi.global.bbs.service;

import com.google.gson.Gson;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.FeedbackUploadResult;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;

public class UploadImageEngine {
    /* access modifiers changed from: private */
    public int currentPosition = 0;
    private boolean isUploading = false;
    /* access modifiers changed from: private */
    public volatile ArrayList<UploadModel> mUploadFileList = new ArrayList<>();
    private int nextPosition = 1;
    UploadListener uploadListener;

    public interface UploadListener {
        void onFinish(String str, int i, boolean z, String str2);

        void onUpload(String str, int i);
    }

    public static class UploadModel {
        public String fileName;
        public boolean isUploaded;
    }

    public boolean isUploading() {
        return this.isUploading;
    }

    public void setUploadListener(UploadListener uploadListener2) {
        this.uploadListener = uploadListener2;
    }

    public void addToQueue(String str) {
        UploadModel uploadModel = new UploadModel();
        uploadModel.fileName = str;
        uploadModel.isUploaded = false;
        this.mUploadFileList.add(uploadModel);
        queueToUpload(this.mUploadFileList.size() - 1);
    }

    private void queueToUpload(int i) {
        if (!this.isUploading) {
            this.currentPosition = i;
            upload(this.mUploadFileList.get(i).fileName);
        }
    }

    public void setNext(int i) {
        if (i >= 0 && i <= this.mUploadFileList.size() - 1) {
            this.nextPosition = i;
            if (!this.isUploading) {
                this.currentPosition = i;
                this.nextPosition = this.currentPosition + 1;
                upload(this.mUploadFileList.get(i).fileName);
            }
        }
    }

    private void upload(final String str) {
        this.isUploading = true;
        if (this.uploadListener != null) {
            this.uploadListener.onUpload(str, this.currentPosition);
        }
        ApiClient.uploadFeedbackImage(str).subscribe(new Consumer<FeedbackUploadResult>() {
            public void accept(@NonNull FeedbackUploadResult feedbackUploadResult) throws Exception {
                if (UploadImageEngine.this.uploadListener != null) {
                    UploadImageEngine.this.uploadListener.onFinish(str, UploadImageEngine.this.currentPosition, true, new Gson().toJson((Object) feedbackUploadResult));
                }
                ((UploadModel) UploadImageEngine.this.mUploadFileList.get(UploadImageEngine.this.currentPosition)).isUploaded = true;
                UploadImageEngine.this.uploadNext();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                if (UploadImageEngine.this.uploadListener != null) {
                    UploadImageEngine.this.uploadListener.onFinish(str, UploadImageEngine.this.currentPosition, false, th.getMessage());
                }
                UploadImageEngine.this.uploadNext();
            }
        });
    }

    /* access modifiers changed from: private */
    public void uploadNext() {
        int i;
        this.isUploading = false;
        if (this.mUploadFileList.size() > this.currentPosition && (i = this.currentPosition + 1) < this.mUploadFileList.size()) {
            if (i <= this.nextPosition) {
                UploadModel uploadModel = this.mUploadFileList.get(i);
                while (uploadModel.isUploaded) {
                    this.currentPosition = i;
                    if (this.mUploadFileList.size() > this.currentPosition) {
                        i = this.currentPosition + 1;
                        if (i < this.mUploadFileList.size()) {
                            uploadModel = this.mUploadFileList.get(i);
                        } else {
                            return;
                        }
                    }
                }
                this.currentPosition = i;
                this.nextPosition = this.currentPosition + 1;
                if (!uploadModel.isUploaded) {
                    upload(uploadModel.fileName);
                    return;
                }
                return;
            }
            this.currentPosition = this.nextPosition;
            this.nextPosition = this.currentPosition + 1;
            upload(this.mUploadFileList.get(this.nextPosition).fileName);
        }
    }
}
