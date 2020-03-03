package com.mishopsdk.volley;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.taobao.weex.ui.component.AbstractEditComponent;

public class HandlerDelivery implements ResponseDelivery {
    private ResponderHandler mHandler = new ResponderHandler();

    public void postStart(Request<?> request) {
        request.addMarker("post-start");
        sendMessage(1, request, (Response<?>) null, (Runnable) null);
    }

    public void postResponse(Request<?> request, Response<?> response) {
        postResponse(request, response, (Runnable) null);
    }

    public void postResponse(Request<?> request, Response<?> response, Runnable runnable) {
        request.markDelivered();
        request.addMarker("post-response");
        sendMessage(2, request, response, runnable);
    }

    private void sendMessage(int i, Request<?> request, Response<?> response, Runnable runnable) {
        this.mHandler.sendMessage(Message.obtain(this.mHandler, i, new Object[]{request, response, runnable}));
    }

    public void postError(Request<?> request, VolleyError volleyError) {
        request.addMarker("post-error");
        sendMessage(3, request, Response.error(volleyError), (Runnable) null);
    }

    public void postFinish(Request<?> request) {
        sendMessage(4, request, (Response<?>) null, (Runnable) null);
    }

    /* access modifiers changed from: private */
    public void handleMessage(Message message) {
        Object[] objArr = (Object[]) message.obj;
        Request request = (Request) objArr[0];
        Response response = (Response) objArr[1];
        Runnable runnable = (Runnable) objArr[2];
        if (!request.isCanceled()) {
            switch (message.what) {
                case 1:
                    request.deliverStart();
                    break;
                case 2:
                    if (response.isSuccess()) {
                        request.deliverResponse(response.result, response.isResponseFromCache);
                    } else {
                        request.deliverError(response.error);
                    }
                    if (!response.intermediate) {
                        request.finish(AbstractEditComponent.ReturnTypes.DONE);
                        break;
                    } else {
                        request.addMarker("intermediate-response");
                        break;
                    }
                case 3:
                    request.deliverError(response.error);
                    if (!response.intermediate) {
                        request.finish(AbstractEditComponent.ReturnTypes.DONE);
                        break;
                    } else {
                        request.addMarker("intermediate-response");
                        break;
                    }
                case 4:
                    request.deliverFinish();
                    break;
            }
            if (runnable != null) {
                this.mHandler.post(runnable);
            }
        } else if (message.what != 4) {
            request.finish("canceled-at-delivery");
        }
    }

    private static class ResponderHandler extends Handler {
        private final HandlerDelivery mDelivery;

        private ResponderHandler(HandlerDelivery handlerDelivery) {
            super(Looper.getMainLooper());
            this.mDelivery = handlerDelivery;
        }

        public void handleMessage(Message message) {
            this.mDelivery.handleMessage(message);
        }
    }
}
