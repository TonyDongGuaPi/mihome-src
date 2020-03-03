package org.apache.commons.compress.archivers.sevenz;

class StreamMap {

    /* renamed from: a  reason: collision with root package name */
    int[] f3239a;
    long[] b;
    int[] c;
    int[] d;

    StreamMap() {
    }

    public String toString() {
        return "StreamMap with indices of " + this.f3239a.length + " folders, offsets of " + this.b.length + " packed streams," + " first files of " + this.c.length + " folders and" + " folder indices for " + this.d.length + " files";
    }
}
