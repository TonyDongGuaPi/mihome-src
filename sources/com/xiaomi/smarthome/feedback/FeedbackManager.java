package com.xiaomi.smarthome.feedback;

import android.content.Context;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.feedback.FeedbackList;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public enum FeedbackManager {
    INSTANCE;
    
    /* access modifiers changed from: private */
    public int currentPage;
    /* access modifiers changed from: private */
    public ArrayList<FeedbackList.FeedbackItem> feedbackLists;
    /* access modifiers changed from: private */
    public boolean isLoadingHistory;
    /* access modifiers changed from: private */
    public final Byte[] obj;
    /* access modifiers changed from: private */
    public int pageCount;

    public List<FeedbackList.FeedbackItem> getFeedbackHistory() {
        return this.feedbackLists;
    }

    public String getFeedbackDeviceName(Context context, String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (str.equalsIgnoreCase(FeedbackApi.COMMON_EXP)) {
            return context.getString(R.string.feedback_app_experience);
        }
        if (str.equalsIgnoreCase("shop")) {
            return context.getString(R.string.feedback_eshop);
        }
        if (str.equalsIgnoreCase("other")) {
            return context.getString(R.string.feedback_other);
        }
        if (str.equalsIgnoreCase(FeedbackApi.AUTO_SCENE)) {
            return context.getString(R.string.feedback_auto_scene);
        }
        if (str.equalsIgnoreCase(FeedbackApi.BLE_GATEWAY)) {
            return context.getString(R.string.device_more_activity_bluetooth_gateway);
        }
        return DeviceFactory.h(str);
    }

    public void loadFeedbackHistory(Context context, AsyncCallback<List<FeedbackList.FeedbackItem>, Error> asyncCallback) {
        synchronized (this.obj) {
            this.isLoadingHistory = true;
            this.pageCount = 0;
            this.currentPage = 0;
            this.feedbackLists.clear();
        }
        loadMoreFeedbackHistory(context, asyncCallback);
    }

    public void loadMoreFeedbackHistory(Context context, final AsyncCallback<List<FeedbackList.FeedbackItem>, Error> asyncCallback) {
        int i = this.currentPage + 1;
        synchronized (this.obj) {
            this.isLoadingHistory = true;
        }
        loadFeedbackHistory(context, i, 10, new AsyncCallback<FeedbackList, Error>() {
            /* renamed from: a */
            public void onSuccess(FeedbackList feedbackList) {
                synchronized (FeedbackManager.this.obj) {
                    int unused = FeedbackManager.this.currentPage = feedbackList.f15969a;
                    int unused2 = FeedbackManager.this.pageCount = feedbackList.b;
                    FeedbackManager.this.feedbackLists.addAll(feedbackList.c);
                    boolean unused3 = FeedbackManager.this.isLoadingHistory = false;
                }
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(FeedbackManager.this.feedbackLists);
                }
            }

            public void onFailure(Error error) {
                synchronized (FeedbackManager.this.obj) {
                    boolean unused = FeedbackManager.this.isLoadingHistory = false;
                }
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public boolean isLoadingHistory() {
        return this.isLoadingHistory;
    }

    public boolean isAllLoaded() {
        return this.currentPage == this.pageCount;
    }

    public FeedbackList.FeedbackItem getFeedbackItem(String str) {
        if (this.feedbackLists == null || str == null || str.isEmpty()) {
            return null;
        }
        Iterator<FeedbackList.FeedbackItem> it = this.feedbackLists.iterator();
        while (it.hasNext()) {
            FeedbackList.FeedbackItem next = it.next();
            if (next.f15970a.equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    private void loadFeedbackHistory(Context context, int i, int i2, AsyncCallback<FeedbackList, Error> asyncCallback) {
        FeedbackApi.INSTANCE.getFeedbackList(context, i, i2, asyncCallback);
    }
}
