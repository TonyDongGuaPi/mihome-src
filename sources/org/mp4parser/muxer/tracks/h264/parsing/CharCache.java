package org.mp4parser.muxer.tracks.h264.parsing;

public class CharCache {

    /* renamed from: a  reason: collision with root package name */
    private char[] f4034a;
    private int b;

    public CharCache(int i) {
        this.f4034a = new char[i];
    }

    public void a(String str) {
        char[] charArray = str.toCharArray();
        int length = this.f4034a.length - this.b;
        if (charArray.length < length) {
            length = charArray.length;
        }
        System.arraycopy(charArray, 0, this.f4034a, this.b, length);
        this.b += length;
    }

    public String toString() {
        return new String(this.f4034a, 0, this.b);
    }

    public void a() {
        this.b = 0;
    }

    public void a(char c) {
        if (this.b < this.f4034a.length - 1) {
            this.f4034a[this.b] = c;
            this.b++;
        }
    }

    public int b() {
        return this.b;
    }
}
