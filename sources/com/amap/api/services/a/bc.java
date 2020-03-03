package com.amap.api.services.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IDistanceSearch;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.DistanceSearch;

public class bc implements IDistanceSearch {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4304a = "bc";
    private Context b;
    /* access modifiers changed from: private */
    public Handler c = ac.a();
    /* access modifiers changed from: private */
    public DistanceSearch.OnDistanceSearchListener d;

    public bc(Context context) {
        this.b = context.getApplicationContext();
    }

    public DistanceResult calculateRouteDistance(DistanceSearch.DistanceQuery distanceQuery) throws AMapException {
        try {
            aa.a(this.b);
            if (distanceQuery == null) {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            } else if (!a(distanceQuery)) {
                DistanceSearch.DistanceQuery clone = distanceQuery.clone();
                DistanceResult distanceResult = (DistanceResult) new t(this.b, clone).c();
                if (distanceResult != null) {
                    distanceResult.setDistanceQuery(clone);
                }
                return distanceResult;
            } else {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            }
        } catch (AMapException e) {
            s.a(e, f4304a, "calculateWalkRoute");
            throw e;
        }
    }

    private boolean a(DistanceSearch.DistanceQuery distanceQuery) {
        if (distanceQuery.getDestination() == null || distanceQuery.getOrigins() == null || distanceQuery.getOrigins().size() <= 0) {
            return true;
        }
        return false;
    }

    public void calculateRouteDistanceAsyn(final DistanceSearch.DistanceQuery distanceQuery) {
        as.a().a(new Runnable() {
            public void run() {
                Message obtainMessage = ac.a().obtainMessage();
                obtainMessage.what = 400;
                obtainMessage.arg1 = 16;
                Bundle bundle = new Bundle();
                DistanceResult distanceResult = null;
                try {
                    DistanceResult calculateRouteDistance = bc.this.calculateRouteDistance(distanceQuery);
                    try {
                        bundle.putInt("errorCode", 1000);
                        obtainMessage.obj = bc.this.d;
                        bundle.putParcelable("result", calculateRouteDistance);
                    } catch (AMapException e) {
                        DistanceResult distanceResult2 = calculateRouteDistance;
                        e = e;
                        distanceResult = distanceResult2;
                        try {
                            bundle.putInt("errorCode", e.getErrorCode());
                            obtainMessage.obj = bc.this.d;
                            bundle.putParcelable("result", distanceResult);
                            obtainMessage.setData(bundle);
                            bc.this.c.sendMessage(obtainMessage);
                        } catch (Throwable th) {
                            th = th;
                            obtainMessage.obj = bc.this.d;
                            bundle.putParcelable("result", distanceResult);
                            obtainMessage.setData(bundle);
                            bc.this.c.sendMessage(obtainMessage);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        DistanceResult distanceResult3 = calculateRouteDistance;
                        th = th2;
                        distanceResult = distanceResult3;
                        obtainMessage.obj = bc.this.d;
                        bundle.putParcelable("result", distanceResult);
                        obtainMessage.setData(bundle);
                        bc.this.c.sendMessage(obtainMessage);
                        throw th;
                    }
                } catch (AMapException e2) {
                    e = e2;
                    bundle.putInt("errorCode", e.getErrorCode());
                    obtainMessage.obj = bc.this.d;
                    bundle.putParcelable("result", distanceResult);
                    obtainMessage.setData(bundle);
                    bc.this.c.sendMessage(obtainMessage);
                }
                obtainMessage.setData(bundle);
                bc.this.c.sendMessage(obtainMessage);
            }
        });
    }

    public void setDistanceSearchListener(DistanceSearch.OnDistanceSearchListener onDistanceSearchListener) {
        this.d = onDistanceSearchListener;
    }
}
