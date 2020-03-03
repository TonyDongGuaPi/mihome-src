package com.xiaomi.smarthome.mibrain.anothernamesetting;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.mibrain.roomsetting.BaseViewModel;
import com.xiaomi.smarthome.multikey.PowerMultikeyBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

public class AnotherNameEditViewModel extends BaseViewModel<List<String>> {
    private String b = AnotherNameEditViewModel.class.getSimpleName();

    public MutableLiveData<List<String>> a() {
        return this.f10663a;
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            this.f10663a.postValue(new ArrayList());
        } else {
            AnotherNameManager.a().a(str, new AsyncCallback<List<String>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<String> list) {
                    AnotherNameEditViewModel.this.f10663a.postValue(list);
                }

                public void onFailure(Error error) {
                    AnotherNameEditViewModel.this.f10663a.postValue(new ArrayList());
                }
            }, true, str2);
        }
    }

    public void a(String str, String str2, String str3, List<PowerMultikeyBean> list) {
        final ArrayList arrayList = new ArrayList();
        if (this.f10663a.getValue() != null) {
            arrayList.addAll((Collection) this.f10663a.getValue());
        }
        arrayList.add(str2);
        AnotherNameManager.a().a(str, arrayList, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null || jSONObject.optInt("code") != 0) {
                    AnotherNameEditViewModel.this.f10663a.postValue(AnotherNameEditViewModel.this.f10663a.getValue());
                } else {
                    AnotherNameEditViewModel.this.f10663a.postValue(arrayList);
                }
            }

            public void onFailure(Error error) {
                AnotherNameEditViewModel.this.f10663a.postValue(AnotherNameEditViewModel.this.f10663a.getValue());
            }
        }, str3, list);
    }

    public void a(String str, final List<String> list, String str2, List<PowerMultikeyBean> list2) {
        final ArrayList arrayList = new ArrayList();
        if (this.f10663a.getValue() != null) {
            arrayList.addAll((Collection) this.f10663a.getValue());
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            int parseInt = Integer.parseInt(list.get(size).substring(0, list.get(size).indexOf(JSMethod.NOT_SET)));
            if (parseInt >= 0 && parseInt < arrayList.size()) {
                arrayList.remove(parseInt);
            }
        }
        AnotherNameManager.a().a(str, arrayList, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null || jSONObject.optInt("code") != 0) {
                    AnotherNameEditViewModel.this.f10663a.postValue(AnotherNameEditViewModel.this.f10663a.getValue());
                    return;
                }
                list.clear();
                AnotherNameEditViewModel.this.f10663a.postValue(arrayList);
            }

            public void onFailure(Error error) {
                AnotherNameEditViewModel.this.f10663a.postValue(AnotherNameEditViewModel.this.f10663a.getValue());
            }
        }, str2, list2);
    }
}
