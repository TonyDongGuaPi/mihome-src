package com.amap.api.services.poisearch;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiSearch;
import java.util.ArrayList;
import java.util.List;

public final class PoiResult {

    /* renamed from: a  reason: collision with root package name */
    private int f4480a;
    private ArrayList<PoiItem> b = new ArrayList<>();
    private PoiSearch.Query c;
    private PoiSearch.SearchBound d;
    private List<String> e;
    private List<SuggestionCity> f;
    private int g;

    public static PoiResult createPagedResult(PoiSearch.Query query, PoiSearch.SearchBound searchBound, List<String> list, List<SuggestionCity> list2, int i, int i2, ArrayList<PoiItem> arrayList) {
        return new PoiResult(query, searchBound, list, list2, i, i2, arrayList);
    }

    private PoiResult(PoiSearch.Query query, PoiSearch.SearchBound searchBound, List<String> list, List<SuggestionCity> list2, int i, int i2, ArrayList<PoiItem> arrayList) {
        this.c = query;
        this.d = searchBound;
        this.e = list;
        this.f = list2;
        this.g = i;
        this.f4480a = a(i2);
        this.b = arrayList;
    }

    public int getPageCount() {
        return this.f4480a;
    }

    public PoiSearch.Query getQuery() {
        return this.c;
    }

    public PoiSearch.SearchBound getBound() {
        return this.d;
    }

    public ArrayList<PoiItem> getPois() {
        return this.b;
    }

    public List<String> getSearchSuggestionKeywords() {
        return this.e;
    }

    public List<SuggestionCity> getSearchSuggestionCitys() {
        return this.f;
    }

    private int a(int i) {
        return ((i + this.g) - 1) / this.g;
    }
}
