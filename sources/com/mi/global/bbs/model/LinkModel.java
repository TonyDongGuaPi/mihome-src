package com.mi.global.bbs.model;

import com.mi.global.bbs.base.BaseActivity;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class LinkModel {
    public String firstImagePath;
    public String title;

    public interface LinkDispatcher {
        void onDispatch(LinkModel linkModel);
    }

    public static void loadByUrl(BaseActivity baseActivity, String str, final LinkDispatcher linkDispatcher) {
        Observable.just(str).map(new Function<String, LinkModel>() {
            public LinkModel apply(@NonNull String str) throws Exception {
                Document a2 = Jsoup.b(str).a();
                String d = a2.f().k("img").first().d("src");
                LinkModel linkModel = new LinkModel();
                linkModel.firstImagePath = d;
                linkModel.title = a2.g();
                return linkModel;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(baseActivity.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<LinkModel>() {
            public void accept(LinkModel linkModel) throws Exception {
                if (linkDispatcher != null) {
                    linkDispatcher.onDispatch(linkModel);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
            }
        });
    }
}
