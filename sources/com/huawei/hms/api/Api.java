package com.huawei.hms.api;

import com.huawei.hms.api.Api.ApiOptions;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import java.util.Collections;
import java.util.List;

public class Api<O extends ApiOptions> {

    /* renamed from: a  reason: collision with root package name */
    private final String f5844a;
    private final Options<O> b;

    public interface ApiOptions {

        public interface HasOptions extends ApiOptions {
        }

        public static final class NoOptions implements NotRequiredOptions {
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public interface Optional extends HasOptions, NotRequiredOptions {
        }
    }

    public Api(String str) {
        this.f5844a = str;
        this.b = null;
    }

    public Api(String str, Options<O> options) {
        this.f5844a = str;
        this.b = options;
    }

    public String getApiName() {
        return this.f5844a;
    }

    public Options<O> getOptions() {
        return this.b;
    }

    public static abstract class Options<O> {
        public List<Scope> getScopeList(O o) {
            return Collections.emptyList();
        }

        public List<PermissionInfo> getPermissionInfoList(O o) {
            return Collections.emptyList();
        }
    }
}
