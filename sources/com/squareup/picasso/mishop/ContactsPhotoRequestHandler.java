package com.squareup.picasso.mishop;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import com.squareup.picasso.mishop.Picasso;
import com.squareup.picasso.mishop.RequestHandler;
import java.io.IOException;
import java.io.InputStream;

class ContactsPhotoRequestHandler extends RequestHandler {
    private static final int ID_CONTACT = 3;
    private static final int ID_DISPLAY_PHOTO = 4;
    private static final int ID_LOOKUP = 1;
    private static final int ID_THUMBNAIL = 2;
    private static final UriMatcher matcher = new UriMatcher(-1);
    private final Context context;

    static {
        matcher.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        matcher.addURI("com.android.contacts", "contacts/lookup/*", 1);
        matcher.addURI("com.android.contacts", "contacts/#/photo", 2);
        matcher.addURI("com.android.contacts", "contacts/#", 3);
        matcher.addURI("com.android.contacts", "display_photo/#", 4);
    }

    ContactsPhotoRequestHandler(Context context2) {
        this.context = context2;
    }

    public boolean canHandleRequest(Request request) {
        Uri uri = request.uri;
        return "content".equals(uri.getScheme()) && ContactsContract.Contacts.CONTENT_URI.getHost().equals(uri.getHost()) && matcher.match(request.uri) != -1;
    }

    public RequestHandler.Result load(Request request, int i) throws IOException {
        InputStream inputStream = getInputStream(request);
        if (inputStream != null) {
            return new RequestHandler.Result(inputStream, Picasso.LoadedFrom.DISK);
        }
        return null;
    }

    private InputStream getInputStream(Request request) throws IOException {
        ContentResolver contentResolver = this.context.getContentResolver();
        Uri uri = request.uri;
        switch (matcher.match(uri)) {
            case 1:
                uri = ContactsContract.Contacts.lookupContact(contentResolver, uri);
                if (uri == null) {
                    return null;
                }
                break;
            case 2:
            case 4:
                return contentResolver.openInputStream(uri);
            case 3:
                break;
            default:
                throw new IllegalStateException("Invalid uri: " + uri);
        }
        if (Build.VERSION.SDK_INT < 14) {
            return ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri);
        }
        return ContactPhotoStreamIcs.get(contentResolver, uri);
    }

    @TargetApi(14)
    private static class ContactPhotoStreamIcs {
        private ContactPhotoStreamIcs() {
        }

        static InputStream get(ContentResolver contentResolver, Uri uri) {
            return ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri, true);
        }
    }
}