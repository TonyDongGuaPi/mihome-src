package com.xiaomi.smarthome.share;

import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.messagecenter.HomeShareMessageManager;
import com.xiaomi.smarthome.messagecenter.IMessage;
import com.xiaomi.smarthome.messagecenter.IMessageManager;
import com.xiaomi.smarthome.messagecenter.ShareMessageManager;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.share.ShareAlertDialogHelper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareAlertDialogHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final int f22108a = 1;
    public static final int b = 8;
    public static volatile int c;
    public static volatile List<IMessage> d;

    public static void a(BaseActivity baseActivity, int i) {
        if (baseActivity != null && baseActivity.isValid()) {
            final WeakReference weakReference = new WeakReference(baseActivity);
            a(i).flatMap($$Lambda$ShareAlertDialogHelper$xCJl8drGNK8A0XK8dmoADAFRqqw.INSTANCE, $$Lambda$ShareAlertDialogHelper$Q2WdCX933XY5hD6zV8mQvfrlbQ.INSTANCE).flatMap(new Function(i) {
                private final /* synthetic */ int f$0;

                {
                    this.f$0 = r1;
                }

                public final Object apply(Object obj) {
                    return ShareAlertDialogHelper.a((List<MessageRecord>) ((ShareAlertDialogHelper.MessageAlert) obj).b(), this.f$0);
                }
            }, $$Lambda$ShareAlertDialogHelper$O6crbU21yQZrYlCE2R3EeV1gpY.INSTANCE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MessageAlert>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(Disposable disposable) {
                }

                /* renamed from: a */
                public void onNext(MessageAlert messageAlert) {
                    BaseActivity baseActivity;
                    if (messageAlert != null && messageAlert.a() > 0 && (baseActivity = (BaseActivity) weakReference.get()) != null && baseActivity.isValid()) {
                        ShareAlertDialogHelper.a(baseActivity, messageAlert.f22113a, messageAlert.b());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ObservableSource a(MessageAlert messageAlert) throws Exception {
        if (messageAlert.a() <= 0 || messageAlert.b() == null || messageAlert.b().size() <= 0) {
            return Observable.empty();
        }
        return a((List<String>) messageAlert.b());
    }

    private static MessageAlert a() {
        MessageAlert messageAlert = new MessageAlert();
        messageAlert.a(1);
        ShareMessageManager shareMessageManager = new ShareMessageManager();
        MessageRecord messageRecord = new MessageRecord();
        try {
            MessageRecord.parse(new JSONObject("{\"msg_id\":\"1129299142155440128\",\"uid\":\"87602279\",\"sender_uid\":\"346378191\",\"type\":\"1\",\"ctime\":\"1558080871\",\"title\":\"\\u81ea\\u52a8\\u5316\\u6d4b\\u8bd5\\u7684\\u8d26\\u53f7\\u5462\\uff01\\u5411\\u60a8\\u5171\\u4eab\\u8bbe\\u5907\",\"content\":\"\\u7c73\\u5bb6\\u5e8a\\u5934\\u706f\",\"img_url\":\"\",\"params\":{\"inv_id\":13842672,\"did\":\"88427833\",\"sender\":346378191,\"status\":\"0\",\"type\":\"share_request\",\"model\":\"yeelink.light.bslamp1\",\"is_new_message\":1,\"user_name\":\"\\u81ea\\u52a8\\u5316\\u6d4b\\u8bd5\\u7684\\u8d26\\u53f7\\u5462\\uff01\",\"content\":\"\\u7c73\\u5bb6\\u5e8a\\u5934\\u706f\",\"title\":\"%s\\u5411\\u60a8\\u5171\\u4eab\\u8bbe\\u5907\",\"expire_time\":1558340071},\"pd_id\":\"-1\",\"is_new\":\"0\",\"is_read\":\"0\",\"last_modify\":\"1558080871\",\"status\":\"0\"}"), messageRecord);
            IMessage a2 = shareMessageManager.a(messageRecord);
            ArrayList arrayList = new ArrayList();
            arrayList.add(a2);
            messageAlert.a((List<?>) arrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messageAlert;
    }

    /* access modifiers changed from: private */
    public static ObservableSource<List<IMessage>> a(List<MessageRecord> list, int i) {
        final IMessageManager iMessageManager;
        if (list == null || list.isEmpty()) {
            return Observable.empty();
        }
        if (i == 8) {
            iMessageManager = new HomeShareMessageManager();
        } else {
            iMessageManager = new ShareMessageManager();
        }
        return Observable.fromIterable(list).map(new Function<MessageRecord, IMessage>() {
            /* renamed from: a */
            public IMessage apply(MessageRecord messageRecord) throws Exception {
                return iMessageManager.a(messageRecord);
            }
        }).toList().toObservable();
    }

    public static Observable<MessageAlert> a(int i) {
        return Observable.create(new ObservableOnSubscribe(i) {
            private final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                RemoteFamilyApi.a().a(this.f$0, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>(observableEmitter) {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (!r3.isDisposed()) {
                            r3.onNext(MessageAlert.a(jSONObject));
                            r3.onComplete();
                        }
                    }

                    public void onFailure(Error error) {
                        if (!r3.isDisposed()) {
                            r3.onComplete();
                        }
                    }
                });
            }
        });
    }

    public static Observable<List<MessageRecord>> a(List<String> list) {
        return Observable.create(new ObservableOnSubscribe(list) {
            private final /* synthetic */ List f$0;

            {
                this.f$0 = r1;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                RemoteFamilyApi.a().g((List<?>) this.f$0, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>(observableEmitter) {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (!r3.isDisposed()) {
                            ArrayList arrayList = new ArrayList();
                            JSONArray optJSONArray = jSONObject.optJSONArray("messages");
                            int length = optJSONArray.length();
                            for (int i = 0; i < length; i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                MessageRecord messageRecord = new MessageRecord();
                                if (MessageRecord.parse(optJSONObject, messageRecord)) {
                                    arrayList.add(messageRecord);
                                }
                            }
                            r3.onNext(arrayList);
                            r3.onComplete();
                        }
                    }

                    public void onFailure(Error error) {
                        if (!r3.isDisposed()) {
                            r3.onComplete();
                        }
                    }
                });
            }
        });
    }

    public static void a(BaseActivity baseActivity, int i, List<IMessage> list) {
        c = 0;
        d = list;
        if (baseActivity != null && baseActivity.isValid() && i > 0 && list != null && !list.isEmpty()) {
            c = i;
            d = list;
            baseActivity.startActivity(new Intent(baseActivity, ShareMsgAlertActivity.class));
            baseActivity.overridePendingTransition(0, 0);
        }
    }

    public static class MessageAlert {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f22113a;
        private List<?> b;

        public int a() {
            return this.f22113a;
        }

        public void a(int i) {
            this.f22113a = i;
        }

        public List<?> b() {
            return this.b;
        }

        public void a(List<?> list) {
            this.b = list;
        }

        public static MessageAlert a(JSONObject jSONObject) {
            MessageAlert messageAlert = new MessageAlert();
            try {
                messageAlert.a(jSONObject.optInt("msgCount", 0));
                JSONArray optJSONArray = jSONObject.optJSONArray("msgIds");
                if (optJSONArray != null) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        String optString = optJSONArray.optString(i);
                        if (!TextUtils.isEmpty(optString)) {
                            arrayList.add(optString);
                        }
                    }
                    messageAlert.a((List<?>) arrayList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return messageAlert;
        }
    }
}
