package com.mi.mistatistic.sdk.controller;

import com.mi.mistatistic.sdk.MiStatInterface;
import com.mi.mistatistic.sdk.controller.AsyncJobDispatcher;
import com.mi.mistatistic.sdk.data.CustomDataEvent;

public abstract class LocalEventRecorder {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7330a = "LER";

    public static void a(CustomDataEvent customDataEvent) {
        if (ApplicationContextHolder.a() == null || MiStatInterface.a().isDisableStat()) {
            Logger.a(f7330a, "mistats is not initialized properly.");
            return;
        }
        AsyncJobDispatcher.a().a((AsyncJobDispatcher.AsyncJob) new LocalInsertDataJob(customDataEvent));
        UploadPolicyEngine.a().c();
    }

    private static class LocalInsertDataJob implements AsyncJobDispatcher.AsyncJob {

        /* renamed from: a  reason: collision with root package name */
        private CustomDataEvent f7331a;

        public LocalInsertDataJob(CustomDataEvent customDataEvent) {
            this.f7331a = customDataEvent;
        }

        public void a() {
            try {
                new EventDAO();
                EventDAO.a(this.f7331a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
