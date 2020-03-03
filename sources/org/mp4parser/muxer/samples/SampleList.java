package org.mp4parser.muxer.samples;

import java.util.AbstractList;
import java.util.List;
import org.mp4parser.Container;
import org.mp4parser.muxer.RandomAccessSource;
import org.mp4parser.muxer.Sample;
import org.mp4parser.tools.Path;

public class SampleList extends AbstractList<Sample> {

    /* renamed from: a  reason: collision with root package name */
    List<Sample> f4008a;

    public SampleList(long j, Container container, RandomAccessSource randomAccessSource) {
        if (Path.b(container, "moov/mvex/trex").isEmpty()) {
            this.f4008a = new DefaultMp4SampleList(j, container, randomAccessSource);
        } else {
            this.f4008a = new FragmentedMp4SampleList(j, container, randomAccessSource);
        }
    }

    /* renamed from: a */
    public Sample get(int i) {
        return this.f4008a.get(i);
    }

    public int size() {
        return this.f4008a.size();
    }
}
