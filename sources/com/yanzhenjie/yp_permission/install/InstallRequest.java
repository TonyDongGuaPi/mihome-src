package com.yanzhenjie.yp_permission.install;

import com.yanzhenjie.yp_permission.Action;
import com.yanzhenjie.yp_permission.Rationale;
import java.io.File;

public interface InstallRequest {
    InstallRequest a(Action<File> action);

    InstallRequest a(Rationale<File> rationale);

    InstallRequest a(File file);

    InstallRequest b(Action<File> action);

    void g();
}
