package org.mp4parser.muxer;

import com.taobao.weex.el.parse.Operators;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.support.Matrix;
import org.mp4parser.tools.Mp4Math;

public class Movie {

    /* renamed from: a  reason: collision with root package name */
    Matrix f3987a = Matrix.f4105a;
    List<Track> b = new LinkedList();

    public Movie() {
    }

    public Movie(List<Track> list) {
        this.b = list;
    }

    public List<Track> a() {
        return this.b;
    }

    public void a(List<Track> list) {
        this.b = list;
    }

    public void a(Track track) {
        if (a(track.o().g()) != null) {
            track.o().b(b());
        }
        this.b.add(track);
    }

    public String toString() {
        String str = "Movie{ ";
        for (Track next : this.b) {
            str = str + "track_" + next.o().g() + " (" + next.p() + ") ";
        }
        return str + Operators.BLOCK_END;
    }

    public long b() {
        long j = 0;
        for (Track next : this.b) {
            if (j < next.o().g()) {
                j = next.o().g();
            }
        }
        return j + 1;
    }

    public Track a(long j) {
        for (Track next : this.b) {
            if (next.o().g() == j) {
                return next;
            }
        }
        return null;
    }

    public long c() {
        long b2 = a().iterator().next().o().b();
        for (Track o : a()) {
            b2 = Mp4Math.a(o.o().b(), b2);
        }
        return b2;
    }

    public Matrix d() {
        return this.f3987a;
    }

    public void a(Matrix matrix) {
        this.f3987a = matrix;
    }
}
