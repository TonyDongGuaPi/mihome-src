package com.huawei.hms.api;

import android.content.Context;
import com.huawei.hms.api.Api;
import com.huawei.hms.c.a;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class HuaweiApiClient implements ApiClient {

    public interface ConnectionCallbacks {
        public static final int CAUSE_API_CLIENT_EXPIRED = 3;
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected();

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    public abstract void connect();

    public abstract void disconnect();

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract void setConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    public abstract void setConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    public abstract boolean setSubAppInfo(SubAppInfo subAppInfo);

    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        private final Context f5846a;
        private final List<Scope> b = new ArrayList();
        private final List<PermissionInfo> c = new ArrayList();
        private final Map<Api<?>, Api.ApiOptions> d = new HashMap();
        private OnConnectionFailedListener e;
        private ConnectionCallbacks f;

        public Builder(Context context) throws NullPointerException {
            a.a(context, "context must not be null.");
            this.f5846a = context.getApplicationContext();
        }

        public HuaweiApiClient build() {
            HuaweiApiClientImpl huaweiApiClientImpl = new HuaweiApiClientImpl(this.f5846a);
            huaweiApiClientImpl.setScopes(this.b);
            huaweiApiClientImpl.setPermissionInfos(this.c);
            huaweiApiClientImpl.setApiMap(this.d);
            huaweiApiClientImpl.setConnectionCallbacks(this.f);
            huaweiApiClientImpl.setConnectionFailedListener(this.e);
            return huaweiApiClientImpl;
        }

        public Builder addConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
            a.a(connectionCallbacks, "listener must not be null.");
            this.f = connectionCallbacks;
            return this;
        }

        public Builder addOnConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
            a.a(onConnectionFailedListener, "listener must not be null.");
            this.e = onConnectionFailedListener;
            return this;
        }

        public Builder addScope(Scope scope) {
            a.a(scope, "scope must not be null.");
            this.b.add(scope);
            return this;
        }

        public Builder addApi(Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            this.d.put(api, (Object) null);
            return this;
        }

        public <O extends Api.ApiOptions.HasOptions> Builder addApi(Api<O> api, O o) {
            a.a(api, "Api must not be null");
            a.a(o, "Null options are not permitted for this Api");
            this.d.put(api, o);
            if (api.getOptions() != null) {
                this.b.addAll(api.getOptions().getScopeList(o));
                this.c.addAll(api.getOptions().getPermissionInfoList(o));
            }
            return this;
        }
    }
}
