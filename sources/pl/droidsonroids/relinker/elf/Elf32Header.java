package pl.droidsonroids.relinker.elf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import pl.droidsonroids.relinker.elf.Elf;

public class Elf32Header extends Elf.Header {
    private final ElfParser m;

    public Elf32Header(boolean z, ElfParser elfParser) throws IOException {
        this.d = z;
        this.m = elfParser;
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.e = elfParser.d(allocate, 16);
        this.f = elfParser.c(allocate, 28);
        this.g = elfParser.c(allocate, 32);
        this.h = elfParser.d(allocate, 42);
        this.i = elfParser.d(allocate, 44);
        this.j = elfParser.d(allocate, 46);
        this.k = elfParser.d(allocate, 48);
        this.l = elfParser.d(allocate, 50);
    }

    public Elf.SectionHeader a(int i) throws IOException {
        return new Section32Header(this.m, this, i);
    }

    public Elf.ProgramHeader a(long j) throws IOException {
        return new Program32Header(this.m, this, j);
    }

    public Elf.DynamicStructure a(long j, int i) throws IOException {
        return new Dynamic32Structure(this.m, this, j, i);
    }
}
