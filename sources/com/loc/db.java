package com.loc;

public final class db {

    /* renamed from: a  reason: collision with root package name */
    private String f6555a;

    public db() {
        this.f6555a = "XwYp8WL8bm6S4wu6yEYmLGy4RRRdJDIhxCBdk3CiNZTwGoj1bScVZEeVp9vBiiIsgwDtqZHP8QLoFM6o6MRYjW8QqyrZBI654mqoUk5SOLDyzordzOU5QhYguEJh54q3K1KqMEXpdEQJJjs1Urqjm2s4jgPfCZ4hMuIjAMRrEQluA7FeoqWMJOwghcLcPVleQ8PLzAcaKidybmwhvNAxIyKRpbZlcDjNCcUvsJYvyzEA9VUIaHkIAJ62lpA3EE3H";
        this.f6555a = dt.a(this.f6555a.getBytes(), 0);
    }

    public final String a(String str) {
        return ds.b(this.f6555a, str);
    }

    public final String b(String str) {
        String b = ds.b(this.f6555a, str);
        if (!dw.a(b)) {
            try {
                return new String(dt.a(b));
            } catch (IllegalArgumentException unused) {
            }
        }
        return null;
    }
}
