package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.media.QuickTimeTextDirectory;
import java.io.IOException;

public class TextSampleDescriptionAtom extends SampleDescriptionAtom<TextSampleDescription> {
    public TextSampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public TextSampleDescription b(SequentialReader sequentialReader) throws IOException {
        return new TextSampleDescription(sequentialReader);
    }

    public void a(QuickTimeTextDirectory quickTimeTextDirectory) {
        boolean z = false;
        TextSampleDescription textSampleDescription = (TextSampleDescription) this.f.get(0);
        quickTimeTextDirectory.a(1, (textSampleDescription.f5242a & 2) == 2);
        quickTimeTextDirectory.a(2, (textSampleDescription.f5242a & 8) == 8);
        quickTimeTextDirectory.a(3, (textSampleDescription.f5242a & 32) == 32);
        quickTimeTextDirectory.a(4, (textSampleDescription.f5242a & 64) == 64);
        quickTimeTextDirectory.a(5, (textSampleDescription.f5242a & 128) == 128 ? "Horizontal" : "Vertical");
        quickTimeTextDirectory.a(6, (textSampleDescription.f5242a & 256) == 256 ? "Reverse" : "Normal");
        quickTimeTextDirectory.a(7, (textSampleDescription.f5242a & 512) == 512);
        quickTimeTextDirectory.a(8, (textSampleDescription.f5242a & 4096) == 4096);
        quickTimeTextDirectory.a(9, (textSampleDescription.f5242a & 8192) == 8192);
        if ((textSampleDescription.f5242a & 16384) == 16384) {
            z = true;
        }
        quickTimeTextDirectory.a(10, z);
        switch (textSampleDescription.b) {
            case -1:
                quickTimeTextDirectory.a(11, "Right");
                break;
            case 0:
                quickTimeTextDirectory.a(11, "Left");
                break;
            case 1:
                quickTimeTextDirectory.a(11, "Center");
                break;
        }
        quickTimeTextDirectory.a(12, textSampleDescription.f);
        quickTimeTextDirectory.a(13, textSampleDescription.g);
        quickTimeTextDirectory.a(14, textSampleDescription.h);
        int i = textSampleDescription.i;
        if (i == 4) {
            quickTimeTextDirectory.a(15, "Underline");
        } else if (i == 8) {
            quickTimeTextDirectory.a(15, "Outline");
        } else if (i == 16) {
            quickTimeTextDirectory.a(15, "Shadow");
        } else if (i == 32) {
            quickTimeTextDirectory.a(15, "Condense");
        } else if (i != 64) {
            switch (i) {
                case 1:
                    quickTimeTextDirectory.a(15, "Bold");
                    break;
                case 2:
                    quickTimeTextDirectory.a(15, "Italic");
                    break;
            }
        } else {
            quickTimeTextDirectory.a(15, "Extend");
        }
        quickTimeTextDirectory.a(16, textSampleDescription.j);
        quickTimeTextDirectory.a(17, textSampleDescription.k);
    }

    class TextSampleDescription extends SampleDescription {

        /* renamed from: a  reason: collision with root package name */
        int f5242a;
        int b;
        int[] f;
        long g;
        int h;
        int i;
        int[] j;
        String k;

        public TextSampleDescription(SequentialReader sequentialReader) throws IOException {
            super(sequentialReader);
            this.f5242a = sequentialReader.j();
            this.b = sequentialReader.j();
            this.f = new int[]{sequentialReader.g(), sequentialReader.g(), sequentialReader.g()};
            this.g = sequentialReader.k();
            sequentialReader.a(8);
            this.h = sequentialReader.g();
            this.i = sequentialReader.g();
            sequentialReader.a(1);
            sequentialReader.a(2);
            this.j = new int[]{sequentialReader.g(), sequentialReader.g(), sequentialReader.g()};
            this.k = sequentialReader.b((int) sequentialReader.e());
        }
    }
}
