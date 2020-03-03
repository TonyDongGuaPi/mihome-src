package com.rt2zz.reactnativecontacts;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.google.android.exoplayer2.C;
import com.taobao.weex.common.Constants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.download.Downloads;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class ContactsManager extends ReactContextBaseJavaModule {
    private static final String PERMISSION_AUTHORIZED = "authorized";
    private static final String PERMISSION_DENIED = "denied";
    private static final String PERMISSION_READ_CONTACTS = "android.permission.READ_CONTACTS";
    private static final int PERMISSION_REQUEST_CODE = 888;
    private static Callback requestCallback;

    public String getName() {
        return "Contacts";
    }

    public ContactsManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getAll(Callback callback) {
        getAllContacts(callback);
    }

    @ReactMethod
    public void getAllWithoutPhotos(Callback callback) {
        getAllContacts(callback);
    }

    private void getAllContacts(final Callback callback) {
        AsyncTask.execute(new Runnable() {
            public void run() {
                WritableArray a2 = new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).a();
                callback.invoke(null, a2);
            }
        });
    }

    @ReactMethod
    public void getContactsMatchingString(String str, Callback callback) {
        getAllContactsMatchingString(str, callback);
    }

    private void getAllContactsMatchingString(final String str, final Callback callback) {
        AsyncTask.execute(new Runnable() {
            public void run() {
                WritableArray a2 = new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).a(str);
                callback.invoke(null, a2);
            }
        });
    }

    @ReactMethod
    public void getPhotoForId(final String str, final Callback callback) {
        AsyncTask.execute(new Runnable() {
            public void run() {
                String d = new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).d(str);
                callback.invoke(null, d);
            }
        });
    }

    @ReactMethod
    public void openContactForm(ReadableMap readableMap, Callback callback) {
        String str;
        String[] strArr;
        Integer[] numArr;
        int i;
        int i2;
        Integer[] numArr2;
        String[] strArr2;
        Integer[] numArr3;
        String[] strArr3;
        int i3;
        String str2;
        String str3;
        String str4;
        String str5;
        int i4;
        Integer[] numArr4;
        String[] strArr4;
        String[] strArr5;
        String[] strArr6;
        String[] strArr7;
        String[] strArr8;
        String[] strArr9;
        int i5;
        ReadableMap readableMap2 = readableMap;
        String string = readableMap2.hasKey("givenName") ? readableMap2.getString("givenName") : null;
        String string2 = readableMap2.hasKey("middleName") ? readableMap2.getString("middleName") : null;
        String string3 = readableMap2.hasKey("displayName") ? readableMap2.getString("displayName") : null;
        String string4 = readableMap2.hasKey("familyName") ? readableMap2.getString("familyName") : null;
        String string5 = readableMap2.hasKey(Constants.Name.PREFIX) ? readableMap2.getString(Constants.Name.PREFIX) : null;
        String string6 = readableMap2.hasKey(Constants.Name.SUFFIX) ? readableMap2.getString(Constants.Name.SUFFIX) : null;
        String string7 = readableMap2.hasKey(MibiConstants.fg) ? readableMap2.getString(MibiConstants.fg) : null;
        String string8 = readableMap2.hasKey("jobTitle") ? readableMap2.getString("jobTitle") : null;
        String string9 = readableMap2.hasKey("department") ? readableMap2.getString("department") : null;
        ReadableArray array = readableMap2.hasKey("phoneNumbers") ? readableMap2.getArray("phoneNumbers") : null;
        if (array != null) {
            int size = array.size();
            strArr = new String[size];
            Integer[] numArr5 = new Integer[size];
            int i6 = 0;
            while (i6 < size) {
                strArr[i6] = array.getMap(i6).getString("number");
                numArr5[i6] = Integer.valueOf(mapStringToPhoneType(array.getMap(i6).getString("label")));
                i6++;
                size = size;
                string3 = string3;
            }
            str = string3;
            numArr = numArr5;
            i = size;
        } else {
            str = string3;
            i = 0;
            numArr = null;
            strArr = null;
        }
        ReadableArray array2 = readableMap2.hasKey("emailAddresses") ? readableMap2.getArray("emailAddresses") : null;
        if (array2 != null) {
            int size2 = array2.size();
            strArr3 = new String[size2];
            strArr2 = strArr;
            numArr3 = new Integer[size2];
            numArr2 = numArr;
            int i7 = 0;
            while (i7 < size2) {
                strArr3[i7] = array2.getMap(i7).getString("email");
                numArr3[i7] = Integer.valueOf(mapStringToEmailType(array2.getMap(i7).getString("label")));
                i7++;
                size2 = size2;
                i = i;
            }
            i2 = i;
            i3 = size2;
        } else {
            i2 = i;
            numArr2 = numArr;
            strArr2 = strArr;
            i3 = 0;
            strArr3 = null;
            numArr3 = null;
        }
        ReadableArray array3 = readableMap2.hasKey("postalAddresses") ? readableMap2.getArray("postalAddresses") : null;
        if (array3 != null) {
            int size3 = array3.size();
            String[] strArr10 = new String[size3];
            strArr7 = new String[size3];
            strArr4 = strArr3;
            String[] strArr11 = new String[size3];
            numArr4 = numArr3;
            strArr6 = new String[size3];
            i4 = i3;
            strArr9 = new String[size3];
            str5 = string9;
            strArr8 = new String[size3];
            str4 = string8;
            Integer[] numArr6 = new Integer[size3];
            str3 = string7;
            int i8 = 0;
            while (i8 < size3) {
                strArr10[i8] = array3.getMap(i8).getString("street");
                strArr7[i8] = array3.getMap(i8).getString("city");
                strArr11[i8] = array3.getMap(i8).getString("state");
                strArr6[i8] = array3.getMap(i8).getString("region");
                strArr9[i8] = array3.getMap(i8).getString("postCode");
                strArr8[i8] = array3.getMap(i8).getString("country");
                numArr6[i8] = Integer.valueOf(mapStringToPostalAddressType(array3.getMap(i8).getString("label")));
                i8++;
                size3 = size3;
                string6 = string6;
            }
            str2 = string6;
            strArr5 = strArr10;
            i5 = size3;
        } else {
            i4 = i3;
            str2 = string6;
            str3 = string7;
            str4 = string8;
            str5 = string9;
            strArr4 = strArr3;
            numArr4 = numArr3;
            i5 = 0;
            strArr9 = null;
            strArr8 = null;
            strArr7 = null;
            strArr6 = null;
            strArr5 = null;
        }
        ArrayList arrayList = new ArrayList();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/identity");
        contentValues.put("data2", string);
        contentValues.put("data3", string4);
        contentValues.put("data5", string2);
        contentValues.put("data4", string5);
        contentValues.put("data6", str2);
        arrayList.add(contentValues);
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/organization");
        contentValues2.put("data1", str3);
        contentValues2.put("data4", str4);
        contentValues2.put("data5", str5);
        arrayList.add(contentValues2);
        int i9 = i4;
        for (int i10 = 0; i10 < i9; i10++) {
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/email_v2");
            contentValues3.put("data2", numArr4[i10]);
            contentValues3.put("data1", strArr4[i10]);
            arrayList.add(contentValues3);
        }
        int i11 = i2;
        for (int i12 = 0; i12 < i11; i12++) {
            ContentValues contentValues4 = new ContentValues();
            contentValues4.put(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/phone_v2");
            contentValues4.put("data2", numArr2[i12]);
            contentValues4.put("data1", strArr2[i12]);
            arrayList.add(contentValues4);
        }
        for (int i13 = 0; i13 < i5; i13++) {
            ContentValues contentValues5 = new ContentValues();
            contentValues5.put(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/postal-address_v2");
            contentValues5.put("data4", strArr5[i13]);
            contentValues5.put("data7", strArr7[i13]);
            contentValues5.put("data8", strArr6[i13]);
            contentValues5.put("data10", strArr8[i13]);
            contentValues5.put("data9", strArr9[i13]);
            arrayList.add(contentValues5);
        }
        Intent intent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
        intent.putExtra("name", str);
        intent.putParcelableArrayListExtra("data", arrayList);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        getReactApplicationContext().startActivity(intent);
    }

    @ReactMethod
    public void addContact(ReadableMap readableMap, Callback callback) {
        String str;
        String[] strArr;
        Integer[] numArr;
        int i;
        String[] strArr2;
        Integer[] numArr2;
        String[] strArr3;
        int i2;
        Integer[] numArr3;
        ContactsManager contactsManager;
        Callback callback2;
        Bitmap decodeFile;
        ReadableMap readableMap2 = readableMap;
        Callback callback3 = callback;
        String string = readableMap2.hasKey("givenName") ? readableMap2.getString("givenName") : null;
        String string2 = readableMap2.hasKey("middleName") ? readableMap2.getString("middleName") : null;
        String string3 = readableMap2.hasKey("familyName") ? readableMap2.getString("familyName") : null;
        String string4 = readableMap2.hasKey(Constants.Name.PREFIX) ? readableMap2.getString(Constants.Name.PREFIX) : null;
        String string5 = readableMap2.hasKey(Constants.Name.SUFFIX) ? readableMap2.getString(Constants.Name.SUFFIX) : null;
        String string6 = readableMap2.hasKey(MibiConstants.fg) ? readableMap2.getString(MibiConstants.fg) : null;
        String string7 = readableMap2.hasKey("jobTitle") ? readableMap2.getString("jobTitle") : null;
        String string8 = readableMap2.hasKey("department") ? readableMap2.getString("department") : null;
        String string9 = readableMap2.hasKey("thumbnailPath") ? readableMap2.getString("thumbnailPath") : null;
        ReadableArray array = readableMap2.hasKey("phoneNumbers") ? readableMap2.getArray("phoneNumbers") : null;
        if (array != null) {
            int size = array.size();
            strArr = new String[size];
            numArr = new Integer[size];
            int i3 = 0;
            while (i3 < size) {
                strArr[i3] = array.getMap(i3).getString("number");
                numArr[i3] = Integer.valueOf(mapStringToPhoneType(array.getMap(i3).getString("label")));
                i3++;
                size = size;
                string9 = string9;
            }
            str = string9;
            i = size;
        } else {
            str = string9;
            i = 0;
            numArr = null;
            strArr = null;
        }
        ReadableArray array2 = readableMap2.hasKey("emailAddresses") ? readableMap2.getArray("emailAddresses") : null;
        if (array2 != null) {
            int size2 = array2.size();
            strArr3 = new String[size2];
            numArr3 = new Integer[size2];
            numArr2 = numArr;
            int i4 = 0;
            while (i4 < size2) {
                strArr3[i4] = array2.getMap(i4).getString("email");
                numArr3[i4] = Integer.valueOf(mapStringToEmailType(array2.getMap(i4).getString("label")));
                i4++;
                size2 = size2;
                strArr = strArr;
            }
            strArr2 = strArr;
            i2 = size2;
        } else {
            numArr2 = numArr;
            strArr2 = strArr;
            numArr3 = null;
            i2 = 0;
            strArr3 = null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", (Object) null).withValue("account_name", (Object) null).build());
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/name").withValue("data2", string).withValue("data5", string2).withValue("data3", string3).withValue("data4", string4).withValue("data6", string5).build());
        ContentProviderOperation.Builder withValue = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/organization").withValue("data1", string6).withValue("data4", string7).withValue("data5", string8);
        arrayList.add(withValue.build());
        withValue.withYieldAllowed(true);
        for (int i5 = 0; i5 < i; i5++) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/phone_v2").withValue("data1", strArr2[i5]).withValue("data2", numArr2[i5]).build());
        }
        for (int i6 = 0; i6 < i2; i6++) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/email_v2").withValue("data1", strArr3[i6]).withValue("data2", numArr3[i6]).build());
        }
        if (str == null || str.isEmpty() || (decodeFile = BitmapFactory.decodeFile(str)) == null) {
            contactsManager = this;
        } else {
            contactsManager = this;
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/photo").withValue("data15", contactsManager.toByteArray(decodeFile)).build());
        }
        ReadableMap readableMap3 = readableMap;
        ReadableArray array3 = readableMap3.hasKey("postalAddresses") ? readableMap3.getArray("postalAddresses") : null;
        if (array3 != null) {
            for (int i7 = 0; i7 < array3.size(); i7++) {
                ReadableMap map = array3.getMap(i7);
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/postal-address_v2").withValue("data2", Integer.valueOf(contactsManager.mapStringToPostalAddressType(map.getString("label")))).withValue("data4", map.getString("street")).withValue("data7", map.getString("city")).withValue("data8", map.getString("state")).withValue("data9", map.getString("postCode")).withValue("data10", map.getString("country")).build());
            }
        }
        try {
            ContentResolver contentResolver = getReactApplicationContext().getContentResolver();
            ContentProviderResult[] applyBatch = contentResolver.applyBatch("com.android.contacts", arrayList);
            if (applyBatch != null && applyBatch.length > 0) {
                callback2 = callback;
                try {
                    callback2.invoke(null, new ContactsProvider(contentResolver).b(String.valueOf(ContentUris.parseId(applyBatch[0].uri))));
                } catch (Exception e) {
                    e = e;
                }
            }
        } catch (Exception e2) {
            e = e2;
            callback2 = callback;
            callback2.invoke(e.toString());
        }
    }

    public byte[] toByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @ReactMethod
    public void updateContact(ReadableMap readableMap, Callback callback) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        Integer[] numArr;
        String[] strArr;
        int i;
        String[] strArr2;
        Integer[] numArr2;
        String[] strArr3;
        String[] strArr4;
        Integer[] numArr3;
        int i2;
        ContactsManager contactsManager;
        Callback callback2;
        Bitmap decodeFile;
        ContentProviderOperation.Builder builder;
        ContentProviderOperation.Builder builder2;
        ReadableMap readableMap2 = readableMap;
        Callback callback3 = callback;
        String string = readableMap2.hasKey("recordID") ? readableMap2.getString("recordID") : null;
        String string2 = readableMap2.hasKey("rawContactId") ? readableMap2.getString("rawContactId") : null;
        String string3 = readableMap2.hasKey("givenName") ? readableMap2.getString("givenName") : null;
        String string4 = readableMap2.hasKey("middleName") ? readableMap2.getString("middleName") : null;
        String string5 = readableMap2.hasKey("familyName") ? readableMap2.getString("familyName") : null;
        String string6 = readableMap2.hasKey(Constants.Name.PREFIX) ? readableMap2.getString(Constants.Name.PREFIX) : null;
        String string7 = readableMap2.hasKey(Constants.Name.SUFFIX) ? readableMap2.getString(Constants.Name.SUFFIX) : null;
        String string8 = readableMap2.hasKey(MibiConstants.fg) ? readableMap2.getString(MibiConstants.fg) : null;
        String string9 = readableMap2.hasKey("jobTitle") ? readableMap2.getString("jobTitle") : null;
        String string10 = readableMap2.hasKey("department") ? readableMap2.getString("department") : null;
        String string11 = readableMap2.hasKey("thumbnailPath") ? readableMap2.getString("thumbnailPath") : null;
        ReadableArray array = readableMap2.hasKey("phoneNumbers") ? readableMap2.getArray("phoneNumbers") : null;
        if (array != null) {
            i = array.size();
            strArr2 = new String[i];
            str5 = string11;
            numArr = new Integer[i];
            str4 = string2;
            strArr = new String[i];
            str3 = string10;
            int i3 = 0;
            while (i3 < i) {
                int i4 = i;
                ReadableMap map = array.getMap(i3);
                ReadableArray readableArray = array;
                String string12 = map.getString("number");
                String str6 = string9;
                String string13 = map.getString("label");
                String str7 = string8;
                String string14 = map.hasKey("id") ? map.getString("id") : null;
                strArr2[i3] = string12;
                numArr[i3] = Integer.valueOf(mapStringToPhoneType(string13));
                strArr[i3] = string14;
                i3++;
                i = i4;
                array = readableArray;
                string9 = str6;
                string8 = str7;
            }
            int i5 = i;
            str = string8;
            str2 = string9;
        } else {
            str4 = string2;
            str = string8;
            str2 = string9;
            str3 = string10;
            str5 = string11;
            strArr2 = null;
            i = 0;
            strArr = null;
            numArr = null;
        }
        ReadableArray array2 = readableMap2.hasKey("emailAddresses") ? readableMap2.getArray("emailAddresses") : null;
        if (array2 != null) {
            int size = array2.size();
            strArr4 = new String[size];
            strArr3 = new String[size];
            Integer[] numArr4 = new Integer[size];
            numArr2 = numArr;
            int i6 = 0;
            while (i6 < size) {
                int i7 = size;
                ReadableMap map2 = array2.getMap(i6);
                ReadableArray readableArray2 = array2;
                strArr4[i6] = map2.getString("email");
                numArr4[i6] = Integer.valueOf(mapStringToEmailType(map2.getString("label")));
                strArr3[i6] = map2.hasKey("id") ? map2.getString("id") : null;
                i6++;
                size = i7;
                array2 = readableArray2;
            }
            numArr3 = numArr4;
            i2 = size;
        } else {
            numArr2 = numArr;
            i2 = 0;
            numArr3 = null;
            strArr4 = null;
            strArr3 = null;
        }
        ArrayList arrayList = new ArrayList();
        Integer[] numArr5 = numArr3;
        String[] strArr5 = strArr4;
        arrayList.add(ContentProviderOperation.newUpdate(ContactsContract.RawContacts.CONTENT_URI).withSelection("contact_id=?", new String[]{String.valueOf(string)}).withValue("account_type", (Object) null).withValue("account_name", (Object) null).build());
        arrayList.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection("contact_id=?", new String[]{String.valueOf(string)}).withValue(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/name").withValue("data2", string3).withValue("data5", string4).withValue("data3", string5).withValue("data4", string6).withValue("data6", string7).build());
        ContentProviderOperation.Builder withValue = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection("contact_id=? AND mimetype = ?", new String[]{String.valueOf(string), "vnd.android.cursor.item/organization"}).withValue("data1", str).withValue("data4", str2).withValue("data5", str3);
        arrayList.add(withValue.build());
        withValue.withYieldAllowed(true);
        for (int i8 = 0; i8 < i; i8++) {
            if (strArr[i8] == null) {
                builder2 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", String.valueOf(str4)).withValue(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/phone_v2").withValue("data1", strArr2[i8]).withValue("data2", numArr2[i8]);
            } else {
                builder2 = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection("_id=?", new String[]{String.valueOf(strArr[i8])}).withValue("data1", strArr2[i8]).withValue("data2", numArr2[i8]);
            }
            arrayList.add(builder2.build());
        }
        for (int i9 = 0; i9 < i2; i9++) {
            if (strArr3[i9] == null) {
                builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", String.valueOf(str4)).withValue(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/email_v2").withValue("data1", strArr5[i9]).withValue("data2", numArr5[i9]);
            } else {
                builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection("_id=?", new String[]{String.valueOf(strArr3[i9])}).withValue("data1", strArr5[i9]).withValue("data2", numArr5[i9]);
            }
            arrayList.add(builder.build());
        }
        if (str5 == null || str5.isEmpty() || (decodeFile = BitmapFactory.decodeFile(str5)) == null) {
            contactsManager = this;
        } else {
            contactsManager = this;
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/photo").withValue("data15", contactsManager.toByteArray(decodeFile)).build());
        }
        ReadableMap readableMap3 = readableMap;
        ReadableArray array3 = readableMap3.hasKey("postalAddresses") ? readableMap3.getArray("postalAddresses") : null;
        if (array3 != null) {
            for (int i10 = 0; i10 < array3.size(); i10++) {
                ReadableMap map3 = array3.getMap(i10);
                arrayList.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection("raw_contact_id=? AND mimetype = ?", new String[]{String.valueOf(string), "vnd.android.cursor.item/postal-address_v2"}).withValue(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/postal-address_v2").withValue("data2", Integer.valueOf(contactsManager.mapStringToPostalAddressType(map3.getString("label")))).withValue("data4", map3.getString("street")).withValue("data7", map3.getString("city")).withValue("data8", map3.getString("state")).withValue("data9", map3.getString("postCode")).withValue("data10", map3.getString("country")).build());
            }
        }
        try {
            ContentResolver contentResolver = getReactApplicationContext().getContentResolver();
            ContentProviderResult[] applyBatch = contentResolver.applyBatch("com.android.contacts", arrayList);
            if (applyBatch != null && applyBatch.length > 0) {
                callback2 = callback;
                try {
                    callback2.invoke(null, new ContactsProvider(contentResolver).c(string));
                } catch (Exception e) {
                    e = e;
                }
            }
        } catch (Exception e2) {
            e = e2;
            callback2 = callback;
            callback2.invoke(e.toString());
        }
    }

    @ReactMethod
    public void deleteContact(ReadableMap readableMap, Callback callback) {
        String string = readableMap.hasKey("recordID") ? readableMap.getString("recordID") : null;
        try {
            if (getReactApplicationContext().getContentResolver().delete(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, string), (String) null, (String[]) null) > 0) {
                callback.invoke(null, string);
            } else {
                callback.invoke(null, null);
            }
        } catch (Exception e) {
            callback.invoke(e.toString(), null);
        }
    }

    @ReactMethod
    public void checkPermission(Callback callback) {
        callback.invoke(null, isPermissionGranted());
    }

    @ReactMethod
    public void requestPermission(Callback callback) {
        requestReadContactsPermission(callback);
    }

    private void requestReadContactsPermission(Callback callback) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            callback.invoke(null, PERMISSION_DENIED);
        } else if (isPermissionGranted().equals(PERMISSION_AUTHORIZED)) {
            callback.invoke(null, PERMISSION_AUTHORIZED);
        } else {
            requestCallback = callback;
            ActivityCompat.requestPermissions(currentActivity, new String[]{"android.permission.READ_CONTACTS"}, PERMISSION_REQUEST_CODE);
        }
    }

    protected static void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (requestCallback != null) {
            if (i != PERMISSION_REQUEST_CODE) {
                requestCallback.invoke(null, PERMISSION_DENIED);
                return;
            }
            Hashtable hashtable = new Hashtable();
            for (int i2 = 0; i2 < strArr.length; i2++) {
                hashtable.put(strArr[i2], Boolean.valueOf(iArr[i2] == 0));
            }
            if (!hashtable.containsKey("android.permission.READ_CONTACTS") || !((Boolean) hashtable.get("android.permission.READ_CONTACTS")).booleanValue()) {
                requestCallback.invoke(null, PERMISSION_DENIED);
            } else {
                requestCallback.invoke(null, PERMISSION_AUTHORIZED);
            }
            requestCallback = null;
        }
    }

    private String isPermissionGranted() {
        return getReactApplicationContext().checkCallingOrSelfPermission("android.permission.READ_CONTACTS") == 0 ? PERMISSION_AUTHORIZED : PERMISSION_DENIED;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int mapStringToPhoneType(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            r1 = -1068855134(0xffffffffc04a90a2, float:-3.1650777)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x002a
            r1 = 3208415(0x30f4df, float:4.495947E-39)
            if (r0 == r1) goto L_0x0020
            r1 = 3655441(0x37c711, float:5.122364E-39)
            if (r0 == r1) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r0 = "work"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = 1
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "home"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = 0
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "mobile"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = 2
            goto L_0x0035
        L_0x0034:
            r5 = -1
        L_0x0035:
            switch(r5) {
                case 0: goto L_0x003c;
                case 1: goto L_0x003a;
                case 2: goto L_0x003d;
                default: goto L_0x0038;
            }
        L_0x0038:
            r2 = 7
            goto L_0x003d
        L_0x003a:
            r2 = 3
            goto L_0x003d
        L_0x003c:
            r2 = 1
        L_0x003d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rt2zz.reactnativecontacts.ContactsManager.mapStringToPhoneType(java.lang.String):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int mapStringToEmailType(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            r1 = -1068855134(0xffffffffc04a90a2, float:-3.1650777)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x002a
            r1 = 3208415(0x30f4df, float:4.495947E-39)
            if (r0 == r1) goto L_0x0020
            r1 = 3655441(0x37c711, float:5.122364E-39)
            if (r0 == r1) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r0 = "work"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = 1
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "home"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = 0
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "mobile"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = 2
            goto L_0x0035
        L_0x0034:
            r5 = -1
        L_0x0035:
            switch(r5) {
                case 0: goto L_0x003c;
                case 1: goto L_0x003d;
                case 2: goto L_0x003a;
                default: goto L_0x0038;
            }
        L_0x0038:
            r2 = 3
            goto L_0x003d
        L_0x003a:
            r2 = 4
            goto L_0x003d
        L_0x003c:
            r2 = 1
        L_0x003d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rt2zz.reactnativecontacts.ContactsManager.mapStringToEmailType(java.lang.String):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int mapStringToPostalAddressType(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = 3208415(0x30f4df, float:4.495947E-39)
            r2 = 1
            if (r0 == r1) goto L_0x001a
            r1 = 3655441(0x37c711, float:5.122364E-39)
            if (r0 == r1) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r0 = "work"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = 1
            goto L_0x0025
        L_0x001a:
            java.lang.String r0 = "home"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = 0
            goto L_0x0025
        L_0x0024:
            r4 = -1
        L_0x0025:
            switch(r4) {
                case 0: goto L_0x002b;
                case 1: goto L_0x002a;
                default: goto L_0x0028;
            }
        L_0x0028:
            r2 = 3
            goto L_0x002b
        L_0x002a:
            r2 = 2
        L_0x002b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rt2zz.reactnativecontacts.ContactsManager.mapStringToPostalAddressType(java.lang.String):int");
    }
}
