package com.miui.tsmclient.entity;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.miui.tsmclient.database.CardDataUtil;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseModel;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.CacheModel;
import com.miui.tsmclient.model.CardOperationFactory;
import com.miui.tsmclient.model.ICardOperation;
import com.miui.tsmclient.model.IPayableCardOperation;
import com.miui.tsmclient.model.TransitCardModel;
import com.miui.tsmclient.model.bankcard.BankCardOperation;
import com.miui.tsmclient.net.AuthApiException;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.Constants;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.ObjectUtils;
import com.miui.tsmclient.util.PrefUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CardInfoManager {
    private static final Object mContent = new Object();
    private static volatile CardInfoManager sInstance;
    private BankCardOperation mBankCardOperation;
    private CacheModel mCacheModel;
    /* access modifiers changed from: private */
    public Context mContext;
    private int mDiskWritesInFlight = 0;
    private Executor mExecutor;
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public List<CardInfo> mList;
    /* access modifiers changed from: private */
    public final WeakHashMap<OnCardChangedListener, Object> mListeners = new WeakHashMap<>();
    private boolean mLoaded;
    private TransitCardModel mTransitCardModel;
    /* access modifiers changed from: private */
    public final Object mWritingToDiskLock = new Object();

    public interface CacheLauncher {
        void startBuildingCache();
    }

    public interface OnCardChangedListener {
        void onCardRemoved(CardInfo cardInfo);

        void onCardUpdated(CardInfo cardInfo);
    }

    static /* synthetic */ int access$1108(CardInfoManager cardInfoManager) {
        int i = cardInfoManager.mDiskWritesInFlight;
        cardInfoManager.mDiskWritesInFlight = i + 1;
        return i;
    }

    static /* synthetic */ int access$1110(CardInfoManager cardInfoManager) {
        int i = cardInfoManager.mDiskWritesInFlight;
        cardInfoManager.mDiskWritesInFlight = i - 1;
        return i;
    }

    private CardInfoManager(Context context) {
        this.mContext = context.getApplicationContext();
        this.mExecutor = Executors.newSingleThreadExecutor();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mLoaded = false;
        this.mList = null;
        this.mCacheModel = (CacheModel) BaseModel.create(this.mContext, CacheModel.class);
        this.mTransitCardModel = (TransitCardModel) BaseModel.create(this.mContext, TransitCardModel.class);
        this.mBankCardOperation = new BankCardOperation();
        startLoadFromDisk();
    }

    public static CardInfoManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (CardInfoManager.class) {
                if (sInstance == null) {
                    sInstance = new CardInfoManager(context);
                }
            }
        }
        return sInstance;
    }

    public boolean isSanity(CacheLauncher cacheLauncher) {
        boolean isCacheSanity = this.mCacheModel.isCacheSanity();
        lazyLoad(cacheLauncher, isCacheSanity);
        return isCacheSanity;
    }

    public List<CardInfo> getAll(CacheLauncher cacheLauncher) {
        ArrayList arrayList;
        synchronized (this) {
            awaitLoadedLocked();
            arrayList = new ArrayList();
            if (cacheLauncher == null || isSanity(cacheLauncher)) {
                for (CardInfo copy : new ArrayList(this.mList)) {
                    arrayList.add(copy.copy());
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.tsmclient.entity.CardInfo getTransCard(com.miui.tsmclient.entity.CardInfoManager.CacheLauncher r5, com.miui.tsmclient.entity.CardInfo r6) {
        /*
            r4 = this;
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.List r5 = r4.getAll(r5)
            java.util.Map r5 = r4.parseList(r5)
            com.miui.tsmclient.entity.CardGroupType r1 = com.miui.tsmclient.entity.CardGroupType.TRANSCARD
            java.lang.Object r5 = r5.get(r1)
            java.util.List r5 = (java.util.List) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x0018:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0039
            java.lang.Object r1 = r5.next()
            com.miui.tsmclient.entity.CardInfo r1 = (com.miui.tsmclient.entity.CardInfo) r1
            java.lang.String r2 = r6.mCardType
            java.lang.String r3 = r1.mCardType
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 != 0) goto L_0x0038
            java.lang.String r2 = r6.mAid
            java.lang.String r3 = r1.mAid
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 == 0) goto L_0x0018
        L_0x0038:
            r0 = r1
        L_0x0039:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.entity.CardInfoManager.getTransCard(com.miui.tsmclient.entity.CardInfoManager$CacheLauncher, com.miui.tsmclient.entity.CardInfo):com.miui.tsmclient.entity.CardInfo");
    }

    public List<CardInfo> getIssuedTransCards(CacheLauncher cacheLauncher) {
        List<CardInfo> transCards = getTransCards(cacheLauncher);
        ArrayList arrayList = new ArrayList();
        for (CardInfo next : transCards) {
            if (next.mHasIssue) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public int getIssuedTransCardsCount() {
        return getIssuedTransCards((CacheLauncher) null).size();
    }

    public int getMifareCardsCount() {
        return getMifareCards((CacheLauncher) null).size();
    }

    public int getBankCardsCount() {
        return getBankCards((CacheLauncher) null).size();
    }

    public boolean hasCard() {
        return getIssuedTransCardsCount() > 0 || getBankCardsCount() > 0 || getMifareCardsCount() > 0;
    }

    public CardInfo getCardInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List<CardInfo> issuedTransCards = getIssuedTransCards((CacheLauncher) null);
        issuedTransCards.addAll(getMifareCards((CacheLauncher) null));
        issuedTransCards.addAll(getBankCards((CacheLauncher) null));
        for (CardInfo next : issuedTransCards) {
            if (next.isAid(str)) {
                return next;
            }
        }
        return null;
    }

    public List<CardInfo> getTransCards(CacheLauncher cacheLauncher) {
        return parseList(getAll(cacheLauncher)).get(CardGroupType.TRANSCARD);
    }

    public CardInfo getTransCard(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (CardInfo next : getTransCards((CacheLauncher) null)) {
            if (TextUtils.equals(next.mCardType, str)) {
                return next;
            }
        }
        return null;
    }

    public CardInfo getDefaultTransCard() {
        String defaultCard = PrefUtils.getDefaultCard(this.mContext, true);
        for (CardInfo next : getIssuedTransCards((CacheLauncher) null)) {
            if (next.isAid(defaultCard)) {
                return next;
            }
        }
        return null;
    }

    public BaseResponse processCard(@NonNull CardInfo cardInfo) {
        checkNotMainThread();
        BaseResponse updateCardInfo = CardOperationFactory.createCardOperation(cardInfo.mCardType).updateCardInfo(this.mContext, cardInfo);
        cardInfo.mDataSource = CardInfo.DataSource.SE;
        if (updateCardInfo == null) {
            updateCardInfo = new BaseResponse(-2, new Object[0]);
        }
        boolean unused = edit().put(cardInfo).commit();
        return updateCardInfo;
    }

    public List<CardInfo> getMifareCards(CacheLauncher cacheLauncher) {
        return parseList(getAll(cacheLauncher)).get(CardGroupType.MIFARECARD);
    }

    public List<CardInfo> getBankCards(CacheLauncher cacheLauncher) {
        return parseList(getAll(cacheLauncher)).get(CardGroupType.BANKCARD);
    }

    public BaseResponse deleteBankCards() {
        checkNotMainThread();
        BaseResponse deleteCards = this.mBankCardOperation.deleteCards(this.mContext);
        if (deleteCards == null) {
            deleteCards = new BaseResponse(-2, new Object[0]);
        }
        if (deleteCards.isSuccess()) {
            boolean unused = edit().remove("BANKCARD").commit();
        }
        return deleteCards;
    }

    public BaseResponse issue(CardInfo cardInfo, Bundle bundle) {
        checkNotMainThread();
        if (cardInfo == null) {
            return new BaseResponse(1, new Object[0]);
        }
        return getResponseAndUpdate(cardInfo, CardOperationFactory.createCardOperation(cardInfo.mCardType).issue(this.mContext, cardInfo, bundle));
    }

    public BaseResponse transferIn(CardInfo cardInfo, Bundle bundle) {
        checkNotMainThread();
        if (cardInfo == null) {
            return new BaseResponse(1, new Object[0]);
        }
        return getResponseAndUpdate(cardInfo, CardOperationFactory.createCardOperation(cardInfo.mCardType).transferIn(this.mContext, cardInfo, bundle));
    }

    public BaseResponse transferOut(CardInfo cardInfo, Bundle bundle) {
        checkNotMainThread();
        if (cardInfo == null) {
            return new BaseResponse(1, new Object[0]);
        }
        return getResponseAndRemove(cardInfo, CardOperationFactory.createCardOperation(cardInfo.mCardType).transferOut(this.mContext, cardInfo, bundle));
    }

    public BaseResponse deleteCard(CardInfo cardInfo, Bundle bundle) {
        checkNotMainThread();
        if (cardInfo == null) {
            return new BaseResponse(1, new Object[0]);
        }
        return getResponseAndRemove(cardInfo, CardOperationFactory.createCardOperation(cardInfo.mCardType).deleteCard(this.mContext, cardInfo, bundle));
    }

    public BaseResponse returnCard(CardInfo cardInfo, Bundle bundle) {
        checkNotMainThread();
        if (cardInfo == null) {
            return new BaseResponse(1, new Object[0]);
        }
        return getResponseAndRemove(cardInfo, CardOperationFactory.createCardOperation(cardInfo.mCardType).returnCard(this.mContext, cardInfo, bundle));
    }

    public BaseResponse recharge(CardInfo cardInfo, Bundle bundle) {
        checkNotMainThread();
        boolean z = true;
        if (cardInfo == null) {
            return new BaseResponse(1, new Object[0]);
        }
        if (!(cardInfo instanceof PayableCardInfo)) {
            return new BaseResponse(1, new Object[0]);
        }
        PayableCardInfo payableCardInfo = (PayableCardInfo) cardInfo;
        OrderInfo rechargeOrder = payableCardInfo.getRechargeOrder();
        if (bundle == null || !bundle.getBoolean(Constants.EXTRA_OUT_OPERATION)) {
            z = false;
        }
        if (rechargeOrder == null && !z) {
            return new BaseResponse(0, new Object[0]);
        }
        ICardOperation createCardOperation = CardOperationFactory.createCardOperation(cardInfo.mCardType);
        BaseResponse baseResponse = null;
        if (createCardOperation instanceof IPayableCardOperation) {
            baseResponse = ((IPayableCardOperation) createCardOperation).recharge(this.mContext, payableCardInfo, rechargeOrder, (Tag) null, bundle);
        }
        return getResponseAndUpdate(cardInfo, baseResponse);
    }

    public BaseResponse recharge(CardInfo cardInfo) {
        return recharge(cardInfo, (Bundle) null);
    }

    public BaseResponse updateCards(@NonNull CardInfo cardInfo) {
        checkNotMainThread();
        boolean isEmpty = TextUtils.isEmpty(cardInfo.mAid);
        LogUtils.d("updateCards: " + cardInfo.mCardType + ", isUpdateAll=" + isEmpty);
        if (cardInfo.isTransCard()) {
            return updateTransCard(cardInfo);
        }
        BaseResponse updateCardInfo = CardOperationFactory.createCardOperation(cardInfo.mCardType).updateCardInfo(this.mContext, cardInfo);
        if (updateCardInfo == null) {
            updateCardInfo = new BaseResponse(-2, new Object[0]);
        }
        if (updateCardInfo.isSuccess()) {
            Editor edit = edit();
            if (updateCardInfo.mDatas == null || updateCardInfo.mDatas.length <= 0) {
                Editor unused = edit.put(cardInfo);
            } else {
                List list = (List) updateCardInfo.mDatas[0];
                if (isEmpty) {
                    Editor unused2 = edit.remove(cardInfo.mCardType);
                }
                if (!ObjectUtils.isCollectionEmpty((List<?>) list)) {
                    Editor unused3 = edit.put((List<? extends CardInfo>) list);
                }
            }
            boolean unused4 = edit.commit();
        }
        return updateCardInfo;
    }

    public void put(CardInfo cardInfo) {
        edit().put(cardInfo).apply();
    }

    public void put(List<? extends CardInfo> list) {
        edit().put(list).apply();
    }

    public void remove(CardInfo cardInfo) {
        edit().remove(cardInfo).apply();
    }

    public void remove(String str) {
        edit().remove(str).apply();
    }

    public void clear() {
        boolean unused = edit().clear().commit();
    }

    public void registerOnCardChangedListener(OnCardChangedListener onCardChangedListener) {
        synchronized (this) {
            this.mListeners.put(onCardChangedListener, mContent);
        }
    }

    public void unregisterOnCardChangedListener(OnCardChangedListener onCardChangedListener) {
        synchronized (this) {
            this.mListeners.remove(onCardChangedListener);
        }
    }

    private void checkNotMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalThreadStateException("Can't call it on main thread");
        }
    }

    private BaseResponse getResponseAndUpdate(CardInfo cardInfo, BaseResponse baseResponse) {
        if (baseResponse == null) {
            baseResponse = new BaseResponse(-2, new Object[0]);
        }
        if (baseResponse.isSuccess()) {
            updateCards(cardInfo);
        }
        return baseResponse;
    }

    private BaseResponse getResponseAndRemove(CardInfo cardInfo, BaseResponse baseResponse) {
        if (baseResponse == null) {
            baseResponse = new BaseResponse(-2, new Object[0]);
        }
        if (baseResponse.isSuccess()) {
            boolean unused = edit().remove(cardInfo).commit();
        }
        return baseResponse;
    }

    private BaseResponse updateTransCard(CardInfo cardInfo) {
        BaseResponse baseResponse;
        checkNotMainThread();
        CardInfo transCard = getTransCard((CacheLauncher) null, cardInfo);
        try {
            List<CardInfo> cardsFromNetwork = this.mTransitCardModel.getCardsFromNetwork(cardInfo);
            if (!cardsFromNetwork.isEmpty()) {
                transCard = cardsFromNetwork.get(0);
            }
        } catch (AuthApiException e) {
            LogUtils.e("failed to update card info from network, code = " + e.mErrorCode + ", msg = " + e.mErrorMsg, e);
        }
        if (transCard != null) {
            transCard.updateInfo(cardInfo);
            cardInfo.parse(transCard.serialize());
        }
        if (!cardInfo.updateCardFromExtra() || cardInfo.mHasIssue) {
            baseResponse = this.mTransitCardModel.updateFromSE(cardInfo);
        } else {
            LogUtils.d("skip updating card:" + cardInfo.mCardType);
            baseResponse = new BaseResponse(2003, new Object[0]);
        }
        if (baseResponse == null) {
            baseResponse = new BaseResponse(-2, new Object[0]);
        }
        if (baseResponse.isSuccess() || baseResponse.isNotExist()) {
            boolean unused = edit().put(cardInfo).commit();
        }
        return baseResponse;
    }

    private Editor edit() {
        synchronized (this) {
            awaitLoadedLocked();
        }
        return new Editor();
    }

    private boolean lazyLoad(CacheLauncher cacheLauncher, boolean z) {
        if (cacheLauncher == null || z) {
            return false;
        }
        cacheLauncher.startBuildingCache();
        return true;
    }

    private void startLoadFromDisk() {
        LogUtils.d("startLoadFromDisk");
        synchronized (this) {
            this.mLoaded = false;
        }
        new Thread("CardManager startLoadFromLocal") {
            public void run() {
                CardInfoManager.this.loadFromDisk();
            }
        }.start();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r3.mLoaded = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        if (r1 == null) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0020, code lost:
        r3.mList = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
        r3.mList = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0008, code lost:
        r1 = null;
        r0 = com.miui.tsmclient.database.CardDataUtil.loadCardList(r3.mContext, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (com.miui.tsmclient.util.ObjectUtils.isCollectionEmpty((java.util.List<?>) r0) != false) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        r1 = new java.util.ArrayList(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadFromDisk() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.mLoaded     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r3)     // Catch:{ all -> 0x0032 }
            return
        L_0x0007:
            monitor-exit(r3)     // Catch:{ all -> 0x0032 }
            android.content.Context r0 = r3.mContext
            r1 = 0
            java.util.List r0 = com.miui.tsmclient.database.CardDataUtil.loadCardList(r0, r1)
            boolean r2 = com.miui.tsmclient.util.ObjectUtils.isCollectionEmpty((java.util.List<?>) r0)
            if (r2 != 0) goto L_0x001a
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r0)
        L_0x001a:
            monitor-enter(r3)
            r0 = 1
            r3.mLoaded = r0     // Catch:{ all -> 0x002f }
            if (r1 == 0) goto L_0x0023
            r3.mList = r1     // Catch:{ all -> 0x002f }
            goto L_0x002a
        L_0x0023:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x002f }
            r0.<init>()     // Catch:{ all -> 0x002f }
            r3.mList = r0     // Catch:{ all -> 0x002f }
        L_0x002a:
            r3.notifyAll()     // Catch:{ all -> 0x002f }
            monitor-exit(r3)     // Catch:{ all -> 0x002f }
            return
        L_0x002f:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002f }
            throw r0
        L_0x0032:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0032 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.entity.CardInfoManager.loadFromDisk():void");
    }

    private void awaitLoadedLocked() {
        while (!this.mLoaded) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void writeToFile(MemoryCommitResult memoryCommitResult) {
        if (!memoryCommitResult.mChangesMade) {
            memoryCommitResult.setDiskWriteResult(true);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : memoryCommitResult.mMapToWriteToDisk.entrySet()) {
            CardInfo cardInfo = (CardInfo) next.getKey();
            if (cardInfo != null) {
                if (next.getValue() == null) {
                    CardDataUtil.deleteCard(this.mContext, cardInfo);
                } else {
                    arrayList.add(cardInfo);
                }
            }
        }
        Map<CardGroupType, List<CardInfo>> parseList = parseList(arrayList);
        for (Map.Entry<CardGroupType, List<CardInfo>> key : parseList.entrySet()) {
            CardGroupType cardGroupType = (CardGroupType) key.getKey();
            List<CardInfo> list = parseList.get(cardGroupType);
            if (!ObjectUtils.isCollectionEmpty((List<?>) list)) {
                if (cardGroupType == CardGroupType.TRANSCARD) {
                    for (CardInfo saveCardInfo : list) {
                        CardDataUtil.saveCardInfo(this.mContext, saveCardInfo);
                    }
                } else {
                    CardDataUtil.saveCardList(this.mContext, list, ((CardInfo) list.get(0)).mCardType);
                }
            }
        }
        memoryCommitResult.setDiskWriteResult(true);
    }

    private Map<CardGroupType, List<CardInfo>> parseList(List<CardInfo> list) {
        HashMap hashMap = new HashMap();
        for (CardGroupType put : CardGroupType.values()) {
            hashMap.put(put, new ArrayList());
        }
        if (list == null) {
            return hashMap;
        }
        for (CardInfo next : list) {
            CardGroupType[] values = CardGroupType.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                CardGroupType cardGroupType = values[i];
                if (cardGroupType.getId() == next.mCardGroupId) {
                    ((List) hashMap.get(cardGroupType)).add(next);
                    break;
                }
                i++;
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public void enqueueDiskWrite(final MemoryCommitResult memoryCommitResult, final Runnable runnable) {
        AnonymousClass2 r0 = new Runnable() {
            public void run() {
                synchronized (CardInfoManager.this.mWritingToDiskLock) {
                    CardInfoManager.this.writeToFile(memoryCommitResult);
                }
                synchronized (this) {
                    CardInfoManager.access$1110(CardInfoManager.this);
                }
                if (runnable != null) {
                    runnable.run();
                }
            }
        };
        boolean z = false;
        if (runnable == null) {
            synchronized (this) {
                if (this.mDiskWritesInFlight == 1) {
                    z = true;
                }
            }
            if (z) {
                r0.run();
                return;
            }
        }
        this.mExecutor.execute(r0);
    }

    private final class Editor {
        private boolean mClear;
        private final Map<CardInfo, Object> mModified;

        private Editor() {
            this.mModified = new ModifiedMap();
            this.mClear = false;
        }

        /* access modifiers changed from: private */
        public Editor put(CardInfo cardInfo) {
            synchronized (this) {
                this.mModified.put(cardInfo, cardInfo);
            }
            return this;
        }

        /* access modifiers changed from: private */
        public Editor put(List<? extends CardInfo> list) {
            if (ObjectUtils.isCollectionEmpty((List<?>) list)) {
                return this;
            }
            synchronized (this) {
                for (CardInfo cardInfo : list) {
                    this.mModified.put(cardInfo, cardInfo);
                }
            }
            return this;
        }

        /* access modifiers changed from: private */
        public Editor remove(CardInfo cardInfo) {
            synchronized (this) {
                this.mModified.put(cardInfo, (Object) null);
            }
            return this;
        }

        /* access modifiers changed from: private */
        public Editor remove(String str) {
            synchronized (this) {
                Map<CardInfo, Object> map = this.mModified;
                map.put(new CardInfo(CardInfo.CARD_TYPE_DUMMY + str), str);
            }
            return this;
        }

        /* access modifiers changed from: private */
        public Editor clear() {
            synchronized (this) {
                this.mClear = true;
            }
            return this;
        }

        /* access modifiers changed from: private */
        public void apply() {
            final MemoryCommitResult commitToMemory = commitToMemory();
            CardInfoManager.this.enqueueDiskWrite(commitToMemory, new Runnable() {
                public void run() {
                    try {
                        commitToMemory.mWrittenToDiskLatch.await();
                    } catch (InterruptedException unused) {
                    }
                }
            });
            notifyListeners(commitToMemory);
        }

        /* access modifiers changed from: private */
        public boolean commit() {
            MemoryCommitResult commitToMemory = commitToMemory();
            CardInfoManager.this.enqueueDiskWrite(commitToMemory, (Runnable) null);
            try {
                commitToMemory.mWrittenToDiskLatch.await();
                notifyListeners(commitToMemory);
                return commitToMemory.mWriteToDiskResult;
            } catch (InterruptedException unused) {
                return false;
            }
        }

        private MemoryCommitResult commitToMemory() {
            MemoryCommitResult memoryCommitResult = new MemoryCommitResult();
            synchronized (CardInfoManager.this) {
                List unused = CardInfoManager.this.mList = new ArrayList(CardInfoManager.this.mList);
                CardInfoManager.access$1108(CardInfoManager.this);
                memoryCommitResult.mMapToWriteToDisk = new ModifiedMap();
                synchronized (this) {
                    if (this.mClear) {
                        if (!CardInfoManager.this.mList.isEmpty()) {
                            memoryCommitResult.mChangesMade = true;
                            for (CardInfo put : CardInfoManager.this.mList) {
                                memoryCommitResult.mMapToWriteToDisk.put(put, (Object) null);
                            }
                            CardInfoManager.this.mList.clear();
                        }
                        this.mClear = false;
                    }
                    for (Map.Entry next : this.mModified.entrySet()) {
                        CardInfo cardInfo = (CardInfo) next.getKey();
                        Object value = next.getValue();
                        if (value == null) {
                            if (CardInfoManager.this.mList.contains(cardInfo)) {
                                memoryCommitResult.mMapToWriteToDisk.put(cardInfo, (Object) null);
                                CardInfoManager.this.mList.remove(cardInfo);
                            }
                        } else if (cardInfo.mCardType == null || !cardInfo.mCardType.startsWith(CardInfo.CARD_TYPE_DUMMY)) {
                            memoryCommitResult.mMapToWriteToDisk.put(cardInfo, value);
                            int indexOf = CardInfoManager.this.mList.indexOf(cardInfo);
                            if (indexOf > -1) {
                                CardInfoManager.this.mList.set(indexOf, cardInfo);
                            } else {
                                CardInfoManager.this.mList.add(cardInfo);
                            }
                        } else if (value instanceof String) {
                            if (!CardInfoManager.this.mList.isEmpty()) {
                                String str = (String) value;
                                Iterator it = CardInfoManager.this.mList.iterator();
                                while (it.hasNext()) {
                                    CardInfo cardInfo2 = (CardInfo) it.next();
                                    if (str.equals(cardInfo2.mCardType)) {
                                        memoryCommitResult.mMapToWriteToDisk.put(cardInfo2, (Object) null);
                                        it.remove();
                                    }
                                }
                            }
                        }
                        memoryCommitResult.mChangesMade = true;
                    }
                    this.mModified.clear();
                }
            }
            return memoryCommitResult;
        }

        /* access modifiers changed from: private */
        public void notifyListeners(final MemoryCommitResult memoryCommitResult) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                if (!CardInfoManager.this.getBankCards((CacheLauncher) null).isEmpty() || !CardInfoManager.this.getMifareCards((CacheLauncher) null).isEmpty()) {
                    PrefUtils.putSecureSettings(CardInfoManager.this.mContext, PrefUtils.SETTINGS_SYSTEM_PREF_KEY_BANK_CARD, 1);
                } else {
                    PrefUtils.putSecureSettings(CardInfoManager.this.mContext, PrefUtils.SETTINGS_SYSTEM_PREF_KEY_BANK_CARD, 0);
                }
                Set<OnCardChangedListener> keySet = CardInfoManager.this.mListeners.keySet();
                if (!keySet.isEmpty() && !memoryCommitResult.mMapToWriteToDisk.isEmpty()) {
                    for (Map.Entry next : memoryCommitResult.mMapToWriteToDisk.entrySet()) {
                        for (OnCardChangedListener onCardChangedListener : keySet) {
                            if (onCardChangedListener != null) {
                                CardInfo cardInfo = (CardInfo) next.getKey();
                                if (next.getValue() == null) {
                                    onCardChangedListener.onCardUpdated(cardInfo);
                                } else {
                                    onCardChangedListener.onCardRemoved(cardInfo);
                                }
                            }
                        }
                    }
                    return;
                }
                return;
            }
            CardInfoManager.this.mHandler.post(new Runnable() {
                public void run() {
                    Editor.this.notifyListeners(memoryCommitResult);
                }
            });
        }
    }

    private static class MemoryCommitResult {
        boolean mChangesMade;
        Map<CardInfo, Object> mMapToWriteToDisk;
        volatile boolean mWriteToDiskResult;
        final CountDownLatch mWrittenToDiskLatch;

        private MemoryCommitResult() {
            this.mWriteToDiskResult = false;
            this.mWrittenToDiskLatch = new CountDownLatch(1);
        }

        /* access modifiers changed from: package-private */
        public void setDiskWriteResult(boolean z) {
            this.mWriteToDiskResult = z;
            this.mWrittenToDiskLatch.countDown();
        }
    }

    private class ModifiedMap extends LinkedHashMap<CardInfo, Object> {
        private ModifiedMap() {
        }

        public Object put(CardInfo cardInfo, Object obj) {
            remove(cardInfo);
            return super.put(cardInfo, obj);
        }
    }
}
