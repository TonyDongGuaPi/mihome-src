package com.amap.api.services.busline;

public class BusLineQuery implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private String f4430a;
    private String b;
    private int c = 20;
    private int d = 1;
    private SearchType e;

    public enum SearchType {
        BY_LINE_ID,
        BY_LINE_NAME
    }

    public BusLineQuery(String str, SearchType searchType, String str2) {
        this.f4430a = str;
        this.e = searchType;
        this.b = str2;
    }

    public SearchType getCategory() {
        return this.e;
    }

    public String getQueryString() {
        return this.f4430a;
    }

    public void setQueryString(String str) {
        this.f4430a = str;
    }

    public String getCity() {
        return this.b;
    }

    public void setCity(String str) {
        this.b = str;
    }

    public int getPageSize() {
        return this.c;
    }

    public void setPageSize(int i) {
        this.c = i;
    }

    public int getPageNumber() {
        return this.d;
    }

    public void setPageNumber(int i) {
        if (i < 1) {
            i = 1;
        }
        this.d = i;
    }

    public void setCategory(SearchType searchType) {
        this.e = searchType;
    }

    public BusLineQuery clone() {
        BusLineQuery busLineQuery = new BusLineQuery(this.f4430a, this.e, this.b);
        busLineQuery.setPageNumber(this.d);
        busLineQuery.setPageSize(this.c);
        return busLineQuery;
    }

    public boolean weakEquals(BusLineQuery busLineQuery) {
        if (this == busLineQuery) {
            return true;
        }
        if (busLineQuery == null) {
            return false;
        }
        if (this.f4430a == null) {
            if (busLineQuery.getQueryString() != null) {
                return false;
            }
        } else if (!busLineQuery.getQueryString().equals(this.f4430a)) {
            return false;
        }
        if (this.b == null) {
            if (busLineQuery.getCity() != null) {
                return false;
            }
        } else if (!busLineQuery.getCity().equals(this.b)) {
            return false;
        }
        return this.c == busLineQuery.getPageSize() && busLineQuery.getCategory().compareTo(this.e) == 0;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (this.e == null) {
            i = 0;
        } else {
            i = this.e.hashCode();
        }
        int hashCode = (((((((i + 31) * 31) + (this.b == null ? 0 : this.b.hashCode())) * 31) + this.d) * 31) + this.c) * 31;
        if (this.f4430a != null) {
            i2 = this.f4430a.hashCode();
        }
        return hashCode + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BusLineQuery busLineQuery = (BusLineQuery) obj;
        if (this.e != busLineQuery.e) {
            return false;
        }
        if (this.b == null) {
            if (busLineQuery.b != null) {
                return false;
            }
        } else if (!this.b.equals(busLineQuery.b)) {
            return false;
        }
        if (this.d != busLineQuery.d || this.c != busLineQuery.c) {
            return false;
        }
        if (this.f4430a == null) {
            if (busLineQuery.f4430a != null) {
                return false;
            }
        } else if (!this.f4430a.equals(busLineQuery.f4430a)) {
            return false;
        }
        return true;
    }
}
