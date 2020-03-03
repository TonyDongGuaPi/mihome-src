package com.amap.api.services.cloud;

import android.content.Context;
import com.amap.api.services.a.aj;
import com.amap.api.services.a.bb;
import com.amap.api.services.a.s;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.interfaces.ICloudSearch;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CloudSearch {

    /* renamed from: a  reason: collision with root package name */
    private ICloudSearch f4441a;

    public interface OnCloudSearchListener {
        void onCloudItemDetailSearched(CloudItemDetail cloudItemDetail, int i);

        void onCloudSearched(CloudResult cloudResult, int i);
    }

    public CloudSearch(Context context) {
        if (this.f4441a == null) {
            try {
                this.f4441a = new bb(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setOnCloudSearchListener(OnCloudSearchListener onCloudSearchListener) {
        if (this.f4441a != null) {
            this.f4441a.setOnCloudSearchListener(onCloudSearchListener);
        }
    }

    public void searchCloudAsyn(Query query) {
        if (this.f4441a != null) {
            this.f4441a.searchCloudAsyn(query);
        }
    }

    public void searchCloudDetailAsyn(String str, String str2) {
        if (this.f4441a != null) {
            this.f4441a.searchCloudDetailAsyn(str, str2);
        }
    }

    public static class Query implements Cloneable {

        /* renamed from: a  reason: collision with root package name */
        private String f4442a;
        private int b = 1;
        private int c = 20;
        private String d;
        private SearchBound e;
        private Sortingrules f;
        private ArrayList<aj> g = new ArrayList<>();
        private HashMap<String, String> h = new HashMap<>();

        public Query(String str, String str2, SearchBound searchBound) throws AMapException {
            if (s.a(str) || searchBound == null) {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            }
            this.d = str;
            this.f4442a = str2;
            this.e = searchBound;
        }

        private Query() {
        }

        public String getQueryString() {
            return this.f4442a;
        }

        public void setTableID(String str) {
            this.d = str;
        }

        public String getTableID() {
            return this.d;
        }

        public int getPageNum() {
            return this.b;
        }

        public void setPageNum(int i) {
            this.b = i;
        }

        public void setPageSize(int i) {
            if (i <= 0) {
                this.c = 20;
            } else if (i > 100) {
                this.c = 100;
            } else {
                this.c = i;
            }
        }

        public int getPageSize() {
            return this.c;
        }

        public void setBound(SearchBound searchBound) {
            this.e = searchBound;
        }

        public SearchBound getBound() {
            return this.e;
        }

        public void addFilterString(String str, String str2) {
            this.h.put(str, str2);
        }

        public String getFilterString() {
            StringBuffer stringBuffer = new StringBuffer();
            try {
                for (Map.Entry next : this.h.entrySet()) {
                    Object key = next.getKey();
                    Object value = next.getValue();
                    stringBuffer.append(key.toString());
                    stringBuffer.append(":");
                    stringBuffer.append(value.toString());
                    stringBuffer.append("+");
                }
                if (stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return stringBuffer.toString();
        }

        public void addFilterNum(String str, String str2, String str3) {
            this.g.add(new aj(str, str2, str3));
        }

        private ArrayList<aj> a() {
            if (this.g == null) {
                return null;
            }
            ArrayList<aj> arrayList = new ArrayList<>();
            arrayList.addAll(this.g);
            return arrayList;
        }

        private HashMap<String, String> b() {
            if (this.h == null) {
                return null;
            }
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.putAll(this.h);
            return hashMap;
        }

        public String getFilterNumString() {
            StringBuffer stringBuffer = new StringBuffer();
            try {
                Iterator<aj> it = this.g.iterator();
                while (it.hasNext()) {
                    aj next = it.next();
                    stringBuffer.append(next.a());
                    stringBuffer.append(":[");
                    stringBuffer.append(next.b());
                    stringBuffer.append(",");
                    stringBuffer.append(next.c());
                    stringBuffer.append(Operators.ARRAY_END_STR);
                    stringBuffer.append("+");
                }
                if (stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return stringBuffer.toString();
        }

        public void setSortingrules(Sortingrules sortingrules) {
            this.f = sortingrules;
        }

        public Sortingrules getSortingrules() {
            return this.f;
        }

        private boolean a(SearchBound searchBound, SearchBound searchBound2) {
            if (searchBound == null && searchBound2 == null) {
                return true;
            }
            if (searchBound == null || searchBound2 == null) {
                return false;
            }
            return searchBound.equals(searchBound2);
        }

        private boolean a(Sortingrules sortingrules, Sortingrules sortingrules2) {
            if (sortingrules == null && sortingrules2 == null) {
                return true;
            }
            if (sortingrules == null || sortingrules2 == null) {
                return false;
            }
            return sortingrules.equals(sortingrules2);
        }

        public boolean queryEquals(Query query) {
            if (query == null) {
                return false;
            }
            if (query == this) {
                return true;
            }
            if (!CloudSearch.b(query.f4442a, this.f4442a) || !CloudSearch.b(query.getTableID(), getTableID()) || !CloudSearch.b(query.getFilterString(), getFilterString()) || !CloudSearch.b(query.getFilterNumString(), getFilterNumString()) || query.c != this.c || !a(query.getBound(), getBound()) || !a(query.getSortingrules(), getSortingrules())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            int i6 = 0;
            if (this.g == null) {
                i = 0;
            } else {
                i = this.g.hashCode();
            }
            int i7 = (i + 31) * 31;
            if (this.h == null) {
                i2 = 0;
            } else {
                i2 = this.h.hashCode();
            }
            int i8 = (i7 + i2) * 31;
            if (this.e == null) {
                i3 = 0;
            } else {
                i3 = this.e.hashCode();
            }
            int i9 = (((((i8 + i3) * 31) + this.b) * 31) + this.c) * 31;
            if (this.f4442a == null) {
                i4 = 0;
            } else {
                i4 = this.f4442a.hashCode();
            }
            int i10 = (i9 + i4) * 31;
            if (this.f == null) {
                i5 = 0;
            } else {
                i5 = this.f.hashCode();
            }
            int i11 = (i10 + i5) * 31;
            if (this.d != null) {
                i6 = this.d.hashCode();
            }
            return i11 + i6;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Query)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            Query query = (Query) obj;
            if (!queryEquals(query) || query.b != this.b) {
                return false;
            }
            return true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0043 A[RETURN] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.amap.api.services.cloud.CloudSearch.Query clone() {
            /*
                r6 = this;
                super.clone()     // Catch:{ CloneNotSupportedException -> 0x0004 }
                goto L_0x0008
            L_0x0004:
                r0 = move-exception
                r0.printStackTrace()
            L_0x0008:
                r0 = 0
                com.amap.api.services.cloud.CloudSearch$Query r1 = new com.amap.api.services.cloud.CloudSearch$Query     // Catch:{ AMapException -> 0x0034 }
                java.lang.String r2 = r6.d     // Catch:{ AMapException -> 0x0034 }
                java.lang.String r3 = r6.f4442a     // Catch:{ AMapException -> 0x0034 }
                com.amap.api.services.cloud.CloudSearch$SearchBound r4 = r6.e     // Catch:{ AMapException -> 0x0034 }
                r1.<init>(r2, r3, r4)     // Catch:{ AMapException -> 0x0034 }
                int r0 = r6.b     // Catch:{ AMapException -> 0x0032 }
                r1.setPageNum(r0)     // Catch:{ AMapException -> 0x0032 }
                int r0 = r6.c     // Catch:{ AMapException -> 0x0032 }
                r1.setPageSize(r0)     // Catch:{ AMapException -> 0x0032 }
                com.amap.api.services.cloud.CloudSearch$Sortingrules r0 = r6.getSortingrules()     // Catch:{ AMapException -> 0x0032 }
                r1.setSortingrules(r0)     // Catch:{ AMapException -> 0x0032 }
                java.util.ArrayList r0 = r6.a()     // Catch:{ AMapException -> 0x0032 }
                r1.g = r0     // Catch:{ AMapException -> 0x0032 }
                java.util.HashMap r0 = r6.b()     // Catch:{ AMapException -> 0x0032 }
                r1.h = r0     // Catch:{ AMapException -> 0x0032 }
                goto L_0x003b
            L_0x0032:
                r0 = move-exception
                goto L_0x0038
            L_0x0034:
                r1 = move-exception
                r5 = r1
                r1 = r0
                r0 = r5
            L_0x0038:
                r0.printStackTrace()
            L_0x003b:
                if (r1 != 0) goto L_0x0043
                com.amap.api.services.cloud.CloudSearch$Query r0 = new com.amap.api.services.cloud.CloudSearch$Query
                r0.<init>()
                return r0
            L_0x0043:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.cloud.CloudSearch.Query.clone():com.amap.api.services.cloud.CloudSearch$Query");
        }
    }

    public static class Sortingrules {
        public static final int DISTANCE = 1;
        public static final int WEIGHT = 0;

        /* renamed from: a  reason: collision with root package name */
        private int f4444a = 0;
        private String b;
        private boolean c = true;

        public Sortingrules(String str, boolean z) {
            this.b = str;
            this.c = z;
        }

        public Sortingrules(int i) {
            this.f4444a = i;
        }

        public String toString() {
            if (s.a(this.b)) {
                if (this.f4444a == 0) {
                    return "_weight";
                }
                if (this.f4444a == 1) {
                    return "_distance";
                }
                return "";
            } else if (this.c) {
                return this.b + ":" + 1;
            } else {
                return this.b + ":" + 0;
            }
        }

        public int hashCode() {
            return (((((this.c ? 1231 : 1237) + 31) * 31) + (this.b == null ? 0 : this.b.hashCode())) * 31) + this.f4444a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Sortingrules sortingrules = (Sortingrules) obj;
            if (this.c != sortingrules.c) {
                return false;
            }
            if (this.b == null) {
                if (sortingrules.b != null) {
                    return false;
                }
            } else if (!this.b.equals(sortingrules.b)) {
                return false;
            }
            return this.f4444a == sortingrules.f4444a;
        }
    }

    public static class SearchBound implements Cloneable {
        public static final String BOUND_SHAPE = "Bound";
        public static final String LOCAL_SHAPE = "Local";
        public static final String POLYGON_SHAPE = "Polygon";
        public static final String RECTANGLE_SHAPE = "Rectangle";

        /* renamed from: a  reason: collision with root package name */
        private LatLonPoint f4443a;
        private LatLonPoint b;
        private int c;
        private LatLonPoint d;
        private String e;
        private List<LatLonPoint> f;
        private String g;

        public SearchBound(LatLonPoint latLonPoint, int i) {
            this.e = "Bound";
            this.c = i;
            this.d = latLonPoint;
        }

        public SearchBound(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.e = "Rectangle";
            if (!a(latLonPoint, latLonPoint2)) {
                new IllegalArgumentException("invalid rect ").printStackTrace();
            }
        }

        public SearchBound(List<LatLonPoint> list) {
            this.e = "Polygon";
            this.f = list;
        }

        public SearchBound(String str) {
            this.e = LOCAL_SHAPE;
            this.g = str;
        }

        private boolean a(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.f4443a = latLonPoint;
            this.b = latLonPoint2;
            if (this.f4443a == null || this.b == null || this.f4443a.getLatitude() >= this.b.getLatitude() || this.f4443a.getLongitude() >= this.b.getLongitude()) {
                return false;
            }
            return true;
        }

        public LatLonPoint getLowerLeft() {
            return this.f4443a;
        }

        public LatLonPoint getUpperRight() {
            return this.b;
        }

        public LatLonPoint getCenter() {
            return this.d;
        }

        public int getRange() {
            return this.c;
        }

        public String getShape() {
            return this.e;
        }

        public String getCity() {
            return this.g;
        }

        public List<LatLonPoint> getPolyGonList() {
            return this.f;
        }

        private boolean a(List<LatLonPoint> list, List<LatLonPoint> list2) {
            if (list == null && list2 == null) {
                return true;
            }
            if (list == null || list2 == null || list.size() != list2.size()) {
                return false;
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (!list.get(i).equals(list2.get(i))) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i;
            int i2;
            int i3;
            int i4;
            int i5 = 0;
            if (this.d == null) {
                i = 0;
            } else {
                i = this.d.hashCode();
            }
            int i6 = (i + 31) * 31;
            if (this.f4443a == null) {
                i2 = 0;
            } else {
                i2 = this.f4443a.hashCode();
            }
            int i7 = (i6 + i2) * 31;
            if (this.b == null) {
                i3 = 0;
            } else {
                i3 = this.b.hashCode();
            }
            int i8 = (i7 + i3) * 31;
            if (this.f == null) {
                i4 = 0;
            } else {
                i4 = this.f.hashCode();
            }
            int hashCode = (((((i8 + i4) * 31) + this.c) * 31) + (this.e == null ? 0 : this.e.hashCode())) * 31;
            if (this.g != null) {
                i5 = this.g.hashCode();
            }
            return hashCode + i5;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof SearchBound)) {
                return false;
            }
            SearchBound searchBound = (SearchBound) obj;
            if (!getShape().equalsIgnoreCase(searchBound.getShape())) {
                return false;
            }
            if (getShape().equals("Bound")) {
                if (!searchBound.d.equals(this.d) || searchBound.c != this.c) {
                    return false;
                }
                return true;
            } else if (getShape().equals("Polygon")) {
                return a(searchBound.f, this.f);
            } else {
                if (getShape().equals(LOCAL_SHAPE)) {
                    return searchBound.g.equals(this.g);
                }
                if (!searchBound.f4443a.equals(this.f4443a) || !searchBound.b.equals(this.b)) {
                    return false;
                }
                return true;
            }
        }

        private List<LatLonPoint> a() {
            if (this.f == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (LatLonPoint next : this.f) {
                arrayList.add(new LatLonPoint(next.getLatitude(), next.getLongitude()));
            }
            return arrayList;
        }

        public SearchBound clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e2) {
                e2.printStackTrace();
            }
            if (getShape().equals("Bound")) {
                return new SearchBound(this.d, this.c);
            }
            if (getShape().equals("Polygon")) {
                return new SearchBound(a());
            }
            if (getShape().equals(LOCAL_SHAPE)) {
                return new SearchBound(this.g);
            }
            return new SearchBound(this.f4443a, this.b);
        }
    }

    /* access modifiers changed from: private */
    public static boolean b(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }
}
