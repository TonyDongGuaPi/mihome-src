package android.arch.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.annotation.NonNull;

public class AndroidViewModel extends ViewModel {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: a  reason: collision with root package name */
    private Application f429a;

    public AndroidViewModel(@NonNull Application application) {
        this.f429a = application;
    }

    @NonNull
    public <T extends Application> T a() {
        return this.f429a;
    }
}
