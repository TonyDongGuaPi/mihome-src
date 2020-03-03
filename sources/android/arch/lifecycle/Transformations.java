package android.arch.lifecycle;

import android.arch.core.util.Function;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Transformations {
    private Transformations() {
    }

    @MainThread
    public static <X, Y> LiveData<Y> a(@NonNull LiveData<X> liveData, @NonNull final Function<X, Y> function) {
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.a(liveData, new Observer<X>() {
            public void onChanged(@Nullable X x) {
                mediatorLiveData.setValue(function.a(x));
            }
        });
        return mediatorLiveData;
    }

    @MainThread
    public static <X, Y> LiveData<Y> b(@NonNull LiveData<X> liveData, @NonNull final Function<X, LiveData<Y>> function) {
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.a(liveData, new Observer<X>() {

            /* renamed from: a  reason: collision with root package name */
            LiveData<Y> f467a;

            public void onChanged(@Nullable X x) {
                LiveData<Y> liveData = (LiveData) function.a(x);
                if (this.f467a != liveData) {
                    if (this.f467a != null) {
                        mediatorLiveData.a(this.f467a);
                    }
                    this.f467a = liveData;
                    if (this.f467a != null) {
                        mediatorLiveData.a(this.f467a, new Observer<Y>() {
                            public void onChanged(@Nullable Y y) {
                                mediatorLiveData.setValue(y);
                            }
                        });
                    }
                }
            }
        });
        return mediatorLiveData;
    }
}
