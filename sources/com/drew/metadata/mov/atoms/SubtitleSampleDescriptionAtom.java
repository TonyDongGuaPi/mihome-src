package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.media.QuickTimeSubtitleDirectory;
import java.io.IOException;

public class SubtitleSampleDescriptionAtom extends SampleDescriptionAtom<SubtitleSampleDescription> {
    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public SubtitleSampleDescription b(SequentialReader sequentialReader) throws IOException {
        return null;
    }

    public SubtitleSampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
    }

    class SubtitleSampleDescription extends SampleDescription {

        /* renamed from: a  reason: collision with root package name */
        int f5241a;
        long b;
        int f;
        int g;
        int h;
        int[] i;

        public SubtitleSampleDescription(SequentialReader sequentialReader) throws IOException {
            super(sequentialReader);
            this.f5241a = sequentialReader.j();
            sequentialReader.a(1);
            sequentialReader.a(1);
            sequentialReader.a(4);
            this.b = sequentialReader.k();
            sequentialReader.a(4);
            this.f = sequentialReader.h();
            this.g = sequentialReader.f();
            this.h = sequentialReader.f();
            this.i = new int[]{sequentialReader.g(), sequentialReader.g(), sequentialReader.g()};
        }
    }

    public void a(QuickTimeSubtitleDirectory quickTimeSubtitleDirectory) {
        boolean z = false;
        SubtitleSampleDescription subtitleSampleDescription = (SubtitleSampleDescription) this.f.get(0);
        quickTimeSubtitleDirectory.a(1, (subtitleSampleDescription.f5241a & 536870912) == 536870912);
        quickTimeSubtitleDirectory.a(2, (subtitleSampleDescription.f5241a & 1073741824) == 1073741824);
        if ((subtitleSampleDescription.f5241a & -1073741824) == -1073741824) {
            z = true;
        }
        quickTimeSubtitleDirectory.a(3, z);
        quickTimeSubtitleDirectory.a(4, subtitleSampleDescription.b);
        quickTimeSubtitleDirectory.a(5, subtitleSampleDescription.f);
        int i = subtitleSampleDescription.g;
        if (i != 4) {
            switch (i) {
                case 1:
                    quickTimeSubtitleDirectory.a(6, "Bold");
                    break;
                case 2:
                    quickTimeSubtitleDirectory.a(6, "Italic");
                    break;
            }
        } else {
            quickTimeSubtitleDirectory.a(6, "Underline");
        }
        quickTimeSubtitleDirectory.a(7, subtitleSampleDescription.h);
        quickTimeSubtitleDirectory.a(8, subtitleSampleDescription.i);
    }
}
