package com.amap.api.services.nearby;

import android.content.Context;
import com.amap.api.services.a.bg;
import com.amap.api.services.a.s;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.interfaces.INearbySearch;

public class NearbySearch {
    public static final int AMAP = 1;
    public static final int GPS = 0;

    /* renamed from: a  reason: collision with root package name */
    private static NearbySearch f4471a;
    private INearbySearch b;

    public interface NearbyListener {
        void onNearbyInfoSearched(NearbySearchResult nearbySearchResult, int i);

        void onNearbyInfoUploaded(int i);

        void onUserInfoCleared(int i);
    }

    public static synchronized NearbySearch getInstance(Context context) {
        NearbySearch nearbySearch;
        synchronized (NearbySearch.class) {
            if (f4471a == null) {
                f4471a = new NearbySearch(context);
            }
            nearbySearch = f4471a;
        }
        return nearbySearch;
    }

    private NearbySearch(Context context) {
        if (this.b == null) {
            try {
                this.b = new bg(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void addNearbyListener(NearbyListener nearbyListener) {
        if (this.b != null) {
            this.b.addNearbyListener(nearbyListener);
        }
    }

    public synchronized void removeNearbyListener(NearbyListener nearbyListener) {
        if (this.b != null) {
            this.b.removeNearbyListener(nearbyListener);
        }
    }

    public void clearUserInfoAsyn() {
        if (this.b != null) {
            this.b.clearUserInfoAsyn();
        }
    }

    public void setUserID(String str) {
        if (this.b != null) {
            this.b.setUserID(str);
        }
    }

    public synchronized void startUploadNearbyInfoAuto(UploadInfoCallback uploadInfoCallback, int i) {
        if (this.b != null) {
            this.b.startUploadNearbyInfoAuto(uploadInfoCallback, i);
        }
    }

    public synchronized void stopUploadNearbyInfoAuto() {
        if (this.b != null) {
            this.b.stopUploadNearbyInfoAuto();
        }
    }

    public void uploadNearbyInfoAsyn(UploadInfo uploadInfo) {
        if (this.b != null) {
            this.b.uploadNearbyInfoAsyn(uploadInfo);
        }
    }

    public void searchNearbyInfoAsyn(NearbyQuery nearbyQuery) {
        if (this.b != null) {
            this.b.searchNearbyInfoAsyn(nearbyQuery);
        }
    }

    public NearbySearchResult searchNearbyInfo(NearbyQuery nearbyQuery) throws AMapException {
        if (this.b != null) {
            return this.b.searchNearbyInfo(nearbyQuery);
        }
        return null;
    }

    public static class NearbyQuery {

        /* renamed from: a  reason: collision with root package name */
        private LatLonPoint f4473a;
        private NearbySearchFunctionType b = NearbySearchFunctionType.DISTANCE_SEARCH;
        private int c = 1000;
        private int d = 1800;
        private int e = 1;

        public void setCenterPoint(LatLonPoint latLonPoint) {
            this.f4473a = latLonPoint;
        }

        public LatLonPoint getCenterPoint() {
            return this.f4473a;
        }

        public int getRadius() {
            return this.c;
        }

        public void setRadius(int i) {
            if (i > 10000) {
                i = 10000;
            }
            this.c = i;
        }

        public void setType(NearbySearchFunctionType nearbySearchFunctionType) {
            this.b = nearbySearchFunctionType;
        }

        public int getType() {
            switch (this.b) {
                case DRIVING_DISTANCE_SEARCH:
                    return 1;
                default:
                    return 0;
            }
        }

        public void setCoordType(int i) {
            if (i == 0 || i == 1) {
                this.e = i;
            } else {
                this.e = 1;
            }
        }

        public int getCoordType() {
            return this.e;
        }

        public void setTimeRange(int i) {
            if (i < 5) {
                i = 5;
            } else if (i > 86400) {
                i = 86400;
            }
            this.d = i;
        }

        public int getTimeRange() {
            return this.d;
        }
    }

    public static synchronized void destroy() {
        synchronized (NearbySearch.class) {
            if (f4471a != null) {
                try {
                    f4471a.a();
                } catch (Throwable th) {
                    s.a(th, "NearbySearch", "destryoy");
                }
            }
            f4471a = null;
        }
    }

    private void a() {
        if (this.b != null) {
            this.b.destroy();
        }
        this.b = null;
    }
}
