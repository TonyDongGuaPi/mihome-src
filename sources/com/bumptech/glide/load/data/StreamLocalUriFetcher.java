package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class StreamLocalUriFetcher extends LocalUriFetcher<InputStream> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f4846a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private static final int d = 4;
    private static final int e = 5;
    private static final UriMatcher f = new UriMatcher(-1);

    static {
        f.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        f.addURI("com.android.contacts", "contacts/lookup/*", 1);
        f.addURI("com.android.contacts", "contacts/#/photo", 2);
        f.addURI("com.android.contacts", "contacts/#", 3);
        f.addURI("com.android.contacts", "contacts/#/display_photo", 4);
        f.addURI("com.android.contacts", "phone_lookup/*", 5);
    }

    public StreamLocalUriFetcher(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public InputStream b(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        InputStream c2 = c(uri, contentResolver);
        if (c2 != null) {
            return c2;
        }
        throw new FileNotFoundException("InputStream is null for " + uri);
    }

    private InputStream c(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        int match = f.match(uri);
        if (match != 1) {
            if (match == 3) {
                return a(contentResolver, uri);
            }
            if (match != 5) {
                return contentResolver.openInputStream(uri);
            }
        }
        Uri lookupContact = ContactsContract.Contacts.lookupContact(contentResolver, uri);
        if (lookupContact != null) {
            return a(contentResolver, lookupContact);
        }
        throw new FileNotFoundException("Contact cannot be found");
    }

    private InputStream a(ContentResolver contentResolver, Uri uri) {
        return ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri, true);
    }

    /* access modifiers changed from: protected */
    public void a(InputStream inputStream) throws IOException {
        inputStream.close();
    }

    @NonNull
    public Class<InputStream> a() {
        return InputStream.class;
    }
}
