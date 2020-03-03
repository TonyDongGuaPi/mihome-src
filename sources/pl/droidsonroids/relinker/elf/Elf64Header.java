package pl.droidsonroids.relinker.elf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import pl.droidsonroids.relinker.elf.Elf;

public class Elf64Header extends Elf.Header {
    private final ElfParser m;

    public Elf64Header(boolean z, ElfParser elfParser) throws IOException {
        this.d = z;
        this.m = elfParser;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.e = elfParser.d(allocate, 16);
        this.f = elfParser.b(allocate, 32);
        this.g = elfParser.b(allocate, 40);
        this.h = elfParser.d(allocate, 54);
        this.i = elfParser.d(allocate, 56);
        this.j = elfParser.d(allocate, 58);
        this.k = elfParser.d(allocate, 60);
        this.l = elfParser.d(allocate, 62);
    }

    public Elf.SectionHeader a(int i) throws IOException {
        return new Section64Header(this.m, this, i);
    }

    public Elf.ProgramHeader a(long j) throws IOException {
        return new Program64Header(this.m, this, j);
    }

    public Elf.DynamicStructure a(long j, int i) throws IOException {
        return new Dynamic64Structure(this.m, this, j, i);
    }
}
