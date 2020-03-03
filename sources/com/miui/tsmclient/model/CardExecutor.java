package com.miui.tsmclient.model;

import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CardExecutor {
    private static final int POOL_SIZE = 2;
    private static CardExecutor sInstance;
    private final Map<String, ICardOperation> mCardType2OperationMap = new ConcurrentHashMap();
    private ExecutorService mExecutor = Executors.newFixedThreadPool(2);
    private int mReferenceCount;
    private final Map<String, List<ICardOperation>> mTag2RunningClientsMap = new HashMap();
    private final Map<String, List<Future>> mTag2RunningResultMap = new HashMap();

    private CardExecutor() {
    }

    public static synchronized CardExecutor getInstance() {
        CardExecutor cardExecutor;
        synchronized (CardExecutor.class) {
            if (sInstance == null) {
                sInstance = new CardExecutor();
            }
            cardExecutor = sInstance;
        }
        return cardExecutor;
    }

    private static synchronized void releaseInstance() {
        synchronized (CardExecutor.class) {
            sInstance = null;
        }
    }

    public ICardOperation createCardOperation(String str, String str2) {
        ICardOperation iCardOperation;
        synchronized (this.mCardType2OperationMap) {
            if (this.mCardType2OperationMap.containsKey(str2)) {
                iCardOperation = this.mCardType2OperationMap.get(str2);
            } else {
                ICardOperation createCardOperation = CardOperationFactory.createCardOperation(str2);
                this.mCardType2OperationMap.put(str2, createCardOperation);
                iCardOperation = createCardOperation;
            }
        }
        synchronized (this.mTag2RunningClientsMap) {
            List list = this.mTag2RunningClientsMap.get(str);
            if (list == null) {
                list = new ArrayList();
                this.mTag2RunningClientsMap.put(str, list);
                this.mReferenceCount++;
            }
            list.add(iCardOperation);
        }
        return iCardOperation;
    }

    public void notifyResult(BaseResponse baseResponse, MiTsmCallback miTsmCallback) {
        if (!this.mExecutor.isShutdown() && baseResponse != null && miTsmCallback != null) {
            if (baseResponse.mResultCode == 0) {
                miTsmCallback.onSuccess(baseResponse.mResultCode, baseResponse.mDatas);
                return;
            }
            LogUtils.d("api execute failed, errorCode: " + baseResponse.mResultCode + ", errorMsg: " + baseResponse.mMsg);
            miTsmCallback.onFail(baseResponse.mResultCode, baseResponse.mMsg, baseResponse.mDatas);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a0, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void release(java.lang.String r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.String, java.util.List<java.util.concurrent.Future>> r0 = r4.mTag2RunningResultMap
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.util.List<java.util.concurrent.Future>> r1 = r4.mTag2RunningResultMap     // Catch:{ all -> 0x00a4 }
            boolean r1 = r1.containsKey(r5)     // Catch:{ all -> 0x00a4 }
            r2 = 1
            if (r1 == 0) goto L_0x002f
            java.util.Map<java.lang.String, java.util.List<java.util.concurrent.Future>> r1 = r4.mTag2RunningResultMap     // Catch:{ all -> 0x00a4 }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x00a4 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x002a
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00a4 }
        L_0x001a:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x00a4 }
            if (r3 == 0) goto L_0x002a
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x00a4 }
            java.util.concurrent.Future r3 = (java.util.concurrent.Future) r3     // Catch:{ all -> 0x00a4 }
            r3.cancel(r2)     // Catch:{ all -> 0x00a4 }
            goto L_0x001a
        L_0x002a:
            java.util.Map<java.lang.String, java.util.List<java.util.concurrent.Future>> r1 = r4.mTag2RunningResultMap     // Catch:{ all -> 0x00a4 }
            r1.remove(r5)     // Catch:{ all -> 0x00a4 }
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x00a4 }
            java.util.Map<java.lang.String, java.util.List<com.miui.tsmclient.model.ICardOperation>> r1 = r4.mTag2RunningClientsMap
            monitor-enter(r1)
            java.util.Map<java.lang.String, java.util.List<com.miui.tsmclient.model.ICardOperation>> r0 = r4.mTag2RunningClientsMap     // Catch:{ all -> 0x00a1 }
            boolean r0 = r0.containsKey(r5)     // Catch:{ all -> 0x00a1 }
            if (r0 != 0) goto L_0x0056
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a1 }
            r0.<init>()     // Catch:{ all -> 0x00a1 }
            java.lang.String r2 = "tag:"
            r0.append(r2)     // Catch:{ all -> 0x00a1 }
            r0.append(r5)     // Catch:{ all -> 0x00a1 }
            java.lang.String r5 = " never used, so return."
            r0.append(r5)     // Catch:{ all -> 0x00a1 }
            java.lang.String r5 = r0.toString()     // Catch:{ all -> 0x00a1 }
            com.miui.tsmclient.util.LogUtils.d(r5)     // Catch:{ all -> 0x00a1 }
            monitor-exit(r1)     // Catch:{ all -> 0x00a1 }
            return
        L_0x0056:
            java.util.Map<java.lang.String, java.util.List<com.miui.tsmclient.model.ICardOperation>> r0 = r4.mTag2RunningClientsMap     // Catch:{ all -> 0x00a1 }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x00a1 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x00a1 }
            if (r0 == 0) goto L_0x007a
            boolean r3 = r0.isEmpty()     // Catch:{ all -> 0x00a1 }
            if (r3 != 0) goto L_0x007a
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x00a1 }
        L_0x006a:
            boolean r3 = r0.hasNext()     // Catch:{ all -> 0x00a1 }
            if (r3 == 0) goto L_0x007a
            java.lang.Object r3 = r0.next()     // Catch:{ all -> 0x00a1 }
            com.miui.tsmclient.model.ICardOperation r3 = (com.miui.tsmclient.model.ICardOperation) r3     // Catch:{ all -> 0x00a1 }
            r3.terminate()     // Catch:{ all -> 0x00a1 }
            goto L_0x006a
        L_0x007a:
            java.util.Map<java.lang.String, java.util.List<com.miui.tsmclient.model.ICardOperation>> r0 = r4.mTag2RunningClientsMap     // Catch:{ all -> 0x00a1 }
            r0.remove(r5)     // Catch:{ all -> 0x00a1 }
            int r5 = r4.mReferenceCount     // Catch:{ all -> 0x00a1 }
            int r5 = r5 - r2
            r4.mReferenceCount = r5     // Catch:{ all -> 0x00a1 }
            int r5 = r4.mReferenceCount     // Catch:{ all -> 0x00a1 }
            if (r5 != 0) goto L_0x009f
            java.lang.String r5 = "card executor has no reference, so shutdown"
            com.miui.tsmclient.util.LogUtils.d(r5)     // Catch:{ all -> 0x00a1 }
            java.util.concurrent.ExecutorService r5 = r4.mExecutor     // Catch:{ all -> 0x00a1 }
            r5.shutdownNow()     // Catch:{ all -> 0x00a1 }
            java.util.Map<java.lang.String, com.miui.tsmclient.model.ICardOperation> r5 = r4.mCardType2OperationMap     // Catch:{ all -> 0x00a1 }
            r5.clear()     // Catch:{ all -> 0x00a1 }
            java.util.Map<java.lang.String, java.util.List<com.miui.tsmclient.model.ICardOperation>> r5 = r4.mTag2RunningClientsMap     // Catch:{ all -> 0x00a1 }
            r5.clear()     // Catch:{ all -> 0x00a1 }
            releaseInstance()     // Catch:{ all -> 0x00a1 }
        L_0x009f:
            monitor-exit(r1)     // Catch:{ all -> 0x00a1 }
            return
        L_0x00a1:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00a1 }
            throw r5
        L_0x00a4:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00a4 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.CardExecutor.release(java.lang.String):void");
    }

    public void execute(Runnable runnable) {
        this.mExecutor.execute(runnable);
    }

    public void submit(String str, Runnable runnable) {
        synchronized (this.mTag2RunningResultMap) {
            List list = this.mTag2RunningResultMap.get(str);
            if (list == null) {
                list = new ArrayList();
            }
            list.add(this.mExecutor.submit(runnable));
            this.mTag2RunningResultMap.put(str, list);
        }
    }
}
