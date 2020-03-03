package com.xiaomi.smarthome.core.entity.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SearchRequest implements Parcelable {
    public static final Parcelable.Creator<SearchRequest> CREATOR = new Parcelable.Creator<SearchRequest>() {
        /* renamed from: a */
        public SearchRequest createFromParcel(Parcel parcel) {
            return new SearchRequest(parcel);
        }

        /* renamed from: a */
        public SearchRequest[] newArray(int i) {
            return new SearchRequest[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    protected List<SearchTask> f13967a;

    public int describeContents() {
        return 0;
    }

    protected SearchRequest(List<SearchTask> list) {
        this.f13967a = list;
    }

    public BluetoothSearchRequest a() {
        BluetoothSearchRequest.Builder builder = new BluetoothSearchRequest.Builder();
        if (this.f13967a != null) {
            for (SearchTask next : this.f13967a) {
                if (next.a() == 2) {
                    builder.a(next.b());
                } else if (next.a() == 1) {
                    builder.b(next.b());
                }
            }
        }
        return builder.b();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f13967a);
    }

    public SearchRequest() {
    }

    protected SearchRequest(Parcel parcel) {
        this.f13967a = new ArrayList();
        parcel.readTypedList(this.f13967a, SearchTask.CREATOR);
    }

    public List<SearchTask> b() {
        return this.f13967a;
    }

    public void a(List<SearchTask> list) {
        this.f13967a = list;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private List<SearchTask> f13968a = new ArrayList();

        public Builder a(int i) {
            return a(i, (UUID[]) null);
        }

        public Builder a(int i, UUID[] uuidArr) {
            SearchTask searchTask = new SearchTask();
            searchTask.a(2);
            searchTask.b(i);
            searchTask.a(uuidArr);
            this.f13968a.add(searchTask);
            return this;
        }

        public Builder a(int i, int i2) {
            return a(i, i2, (UUID[]) null);
        }

        public Builder a(int i, int i2, UUID[] uuidArr) {
            for (int i3 = 0; i3 < i2; i3++) {
                a(i, uuidArr);
            }
            return this;
        }

        public Builder b(int i) {
            SearchTask searchTask = new SearchTask();
            searchTask.a(1);
            searchTask.b(i);
            this.f13968a.add(searchTask);
            return this;
        }

        public Builder b(int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                b(i);
            }
            return this;
        }

        public SearchRequest a() {
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.a(this.f13968a);
            return searchRequest;
        }
    }
}
