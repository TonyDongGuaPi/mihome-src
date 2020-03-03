package com.amap.api.services.geocoder;

public class GeocodeQuery {

    /* renamed from: a  reason: collision with root package name */
    private String f4459a;
    private String b;

    public GeocodeQuery(String str, String str2) {
        this.f4459a = str;
        this.b = str2;
    }

    public String getLocationName() {
        return this.f4459a;
    }

    public void setLocationName(String str) {
        this.f4459a = str;
    }

    public String getCity() {
        return this.b;
    }

    public void setCity(String str) {
        this.b = str;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.b == null ? 0 : this.b.hashCode()) + 31) * 31;
        if (this.f4459a != null) {
            i = this.f4459a.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeocodeQuery geocodeQuery = (GeocodeQuery) obj;
        if (this.b == null) {
            if (geocodeQuery.b != null) {
                return false;
            }
        } else if (!this.b.equals(geocodeQuery.b)) {
            return false;
        }
        if (this.f4459a == null) {
            if (geocodeQuery.f4459a != null) {
                return false;
            }
        } else if (!this.f4459a.equals(geocodeQuery.f4459a)) {
            return false;
        }
        return true;
    }
}
