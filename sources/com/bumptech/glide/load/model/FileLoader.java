package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import com.google.android.exoplayer2.C;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileLoader<Data> implements ModelLoader<File, Data> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4946a = "FileLoader";
    private final FileOpener<Data> b;

    public interface FileOpener<Data> {
        Class<Data> a();

        void a(Data data) throws IOException;

        Data b(File file) throws FileNotFoundException;
    }

    public boolean a(@NonNull File file) {
        return true;
    }

    public FileLoader(FileOpener<Data> fileOpener) {
        this.b = fileOpener;
    }

    public ModelLoader.LoadData<Data> a(@NonNull File file, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(file), new FileFetcher(file, this.b));
    }

    private static final class FileFetcher<Data> implements DataFetcher<Data> {

        /* renamed from: a  reason: collision with root package name */
        private final File f4948a;
        private final FileOpener<Data> b;
        private Data c;

        public void c() {
        }

        FileFetcher(File file, FileOpener<Data> fileOpener) {
            this.f4948a = file;
            this.b = fileOpener;
        }

        public void a(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super Data> dataCallback) {
            try {
                this.c = this.b.b(this.f4948a);
                dataCallback.a(this.c);
            } catch (FileNotFoundException e) {
                if (Log.isLoggable(FileLoader.f4946a, 3)) {
                    Log.d(FileLoader.f4946a, "Failed to open file", e);
                }
                dataCallback.a((Exception) e);
            }
        }

        public void b() {
            if (this.c != null) {
                try {
                    this.b.a(this.c);
                } catch (IOException unused) {
                }
            }
        }

        @NonNull
        public Class<Data> a() {
            return this.b.a();
        }

        @NonNull
        public DataSource d() {
            return DataSource.LOCAL;
        }
    }

    public static class Factory<Data> implements ModelLoaderFactory<File, Data> {

        /* renamed from: a  reason: collision with root package name */
        private final FileOpener<Data> f4947a;

        public final void a() {
        }

        public Factory(FileOpener<Data> fileOpener) {
            this.f4947a = fileOpener;
        }

        @NonNull
        public final ModelLoader<File, Data> a(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
            return new FileLoader(this.f4947a);
        }
    }

    public static class StreamFactory extends Factory<InputStream> {
        public StreamFactory() {
            super(new FileOpener<InputStream>() {
                /* renamed from: a */
                public InputStream b(File file) throws FileNotFoundException {
                    return new FileInputStream(file);
                }

                public void a(InputStream inputStream) throws IOException {
                    inputStream.close();
                }

                public Class<InputStream> a() {
                    return InputStream.class;
                }
            });
        }
    }

    public static class FileDescriptorFactory extends Factory<ParcelFileDescriptor> {
        public FileDescriptorFactory() {
            super(new FileOpener<ParcelFileDescriptor>() {
                /* renamed from: a */
                public ParcelFileDescriptor b(File file) throws FileNotFoundException {
                    return ParcelFileDescriptor.open(file, C.ENCODING_PCM_MU_LAW);
                }

                public void a(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
                    parcelFileDescriptor.close();
                }

                public Class<ParcelFileDescriptor> a() {
                    return ParcelFileDescriptor.class;
                }
            });
        }
    }
}
