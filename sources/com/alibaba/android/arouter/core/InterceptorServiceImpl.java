package com.alibaba.android.arouter.core;

import android.content.Context;
import com.alibaba.android.arouter.exception.HandlerException;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.service.InterceptorService;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.thread.CancelableCountDownLatch;
import com.alibaba.android.arouter.utils.MapUtils;
import com.taobao.weex.el.parse.Operators;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Route(path = "/arouter/service/interceptor")
public class InterceptorServiceImpl implements InterceptorService {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static boolean f715a;
    /* access modifiers changed from: private */
    public static final Object b = new Object();

    public void doInterceptions(final Postcard postcard, final InterceptorCallback interceptorCallback) {
        if (Warehouse.f == null || Warehouse.f.size() <= 0) {
            interceptorCallback.onContinue(postcard);
            return;
        }
        b();
        if (!f715a) {
            interceptorCallback.onInterrupt(new HandlerException("Interceptors initialization takes too much time."));
        } else {
            LogisticsCenter.f719a.execute(new Runnable() {
                public void run() {
                    CancelableCountDownLatch cancelableCountDownLatch = new CancelableCountDownLatch(Warehouse.f.size());
                    try {
                        InterceptorServiceImpl.b(0, cancelableCountDownLatch, postcard);
                        cancelableCountDownLatch.await((long) postcard.getTimeout(), TimeUnit.SECONDS);
                        if (cancelableCountDownLatch.getCount() > 0) {
                            interceptorCallback.onInterrupt(new HandlerException("The interceptor processing timed out."));
                        } else if (postcard.getTag() != null) {
                            interceptorCallback.onInterrupt(new HandlerException(postcard.getTag().toString()));
                        } else {
                            interceptorCallback.onContinue(postcard);
                        }
                    } catch (Exception e) {
                        interceptorCallback.onInterrupt(e);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void b(final int i, final CancelableCountDownLatch cancelableCountDownLatch, final Postcard postcard) {
        if (i < Warehouse.f.size()) {
            Warehouse.f.get(i).process(postcard, new InterceptorCallback() {
                public void onContinue(Postcard postcard) {
                    cancelableCountDownLatch.countDown();
                    InterceptorServiceImpl.b(i + 1, cancelableCountDownLatch, postcard);
                }

                public void onInterrupt(Throwable th) {
                    postcard.setTag(th == null ? new HandlerException("No message.") : th.getMessage());
                    cancelableCountDownLatch.a();
                }
            });
        }
    }

    public void init(final Context context) {
        LogisticsCenter.f719a.execute(new Runnable() {
            public void run() {
                if (MapUtils.a(Warehouse.e)) {
                    for (Map.Entry<Integer, Class<? extends IInterceptor>> value : Warehouse.e.entrySet()) {
                        Class cls = (Class) value.getValue();
                        try {
                            IInterceptor iInterceptor = (IInterceptor) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
                            iInterceptor.init(context);
                            Warehouse.f.add(iInterceptor);
                        } catch (Exception e) {
                            throw new HandlerException("ARouter::ARouter init interceptor error! name = [" + cls.getName() + "], reason = [" + e.getMessage() + Operators.ARRAY_END_STR);
                        }
                    }
                    boolean unused = InterceptorServiceImpl.f715a = true;
                    ARouter.c.info("ARouter::", "ARouter interceptors init over.");
                    synchronized (InterceptorServiceImpl.b) {
                        InterceptorServiceImpl.b.notifyAll();
                    }
                }
            }
        });
    }

    private static void b() {
        synchronized (b) {
            while (!f715a) {
                try {
                    b.wait(10000);
                } catch (InterruptedException e) {
                    throw new HandlerException("ARouter::Interceptor init cost too much time error! reason = [" + e.getMessage() + Operators.ARRAY_END_STR);
                }
            }
        }
    }
}
