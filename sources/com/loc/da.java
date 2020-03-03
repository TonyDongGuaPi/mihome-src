package com.loc;

import java.util.Random;

public final class da {
    private static Random b = new Random();

    /* renamed from: a  reason: collision with root package name */
    private String f6554a;

    public da() {
        this.f6554a = "XwYp8WL8bm6S4wu6yEYmLGy4RRRdJDIhxCBdk3CiNZTwGoj1bScVZEeVp9vBiiIsgwDtqZHP8QLoFM6o6MRYjW8QqyrZBI654mqoUk5SOLDyzordzOU5QhYguEJh54q3K1KqMEXpdEQJJjs1Urqjm2s4jgPfCZ4hMuIjAMRrEQluA7FeoqWMJOwghcLcPVleQ8PLzAcaKidybmwhvNAxIyKRpbZlcDjNCcUvsJYvyzEA9VUIaHkIAJ62lpA3EE3H";
        this.f6554a = dt.a(this.f6554a.getBytes(), 2);
    }

    public final String a(String str) {
        return ds.a(this.f6554a, str);
    }

    public final String a(byte[] bArr) {
        return ds.a(this.f6554a, dt.a(bArr, 2));
    }

    public final String b(String str) {
        return ds.b(this.f6554a, str);
    }
}
