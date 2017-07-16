package com.example.android.vogue.vogue;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android.vogue.vogue.VogueContract.VogueEntry;

public class VogueProvider extends ContentProvider {

    private static final int STUD = 100;
    private static final int STUD_ID = 101;

    private static final int JHUMKA = 110;
    private static final int JHUMKA_ID = 111;

    private static final int CHANDELIER = 104;
    private static final int CHANDELIER_ID = 105;

    private static final int THREADER = 102;
    private static final int THREADER_ID = 103;

    private static final int EARCUFF = 106;
    private static final int EARCUFF_ID = 107;

    private static final int HOOP = 108;
    private static final int HOOP_ID = 109;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI(VogueContract.CONTENT_AUTHORITY, VogueContract.PATH_STUD, STUD);
        sUriMatcher.addURI(VogueContract.CONTENT_AUTHORITY, VogueContract.PATH_STUD + "/#", STUD_ID);

        sUriMatcher.addURI(VogueContract.CONTENT_AUTHORITY, VogueContract.PATH_THREADER, THREADER);
        sUriMatcher.addURI(VogueContract.CONTENT_AUTHORITY, VogueContract.PATH_THREADER + "/#", THREADER_ID);

        sUriMatcher.addURI(VogueContract.CONTENT_AUTHORITY, VogueContract.PATH_CHANDELIER, CHANDELIER);
        sUriMatcher.addURI(VogueContract.CONTENT_AUTHORITY, VogueContract.PATH_CHANDELIER + "/#", CHANDELIER_ID);

        sUriMatcher.addURI(VogueContract.CONTENT_AUTHORITY, VogueContract.PATH_EARCUFF, EARCUFF);
        sUriMatcher.addURI(VogueContract.CONTENT_AUTHORITY, VogueContract.PATH_EARCUFF + "/#", EARCUFF_ID);

        sUriMatcher.addURI(VogueContract.CONTENT_AUTHORITY, VogueContract.PATH_HOOP, HOOP);
        sUriMatcher.addURI(VogueContract.CONTENT_AUTHORITY, VogueContract.PATH_HOOP + "/#", HOOP);

        sUriMatcher.addURI(VogueContract.CONTENT_AUTHORITY, VogueContract.PATH_JHUMKA, JHUMKA);
        sUriMatcher.addURI(VogueContract.CONTENT_AUTHORITY, VogueContract.PATH_JHUMKA + "/#", JHUMKA_ID);

    }

    private VogueDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new VogueDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case STUD:

                cursor = database.query(VogueEntry.TABLE_STUD, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case THREADER:

                cursor = database.query(VogueEntry.TABLE_THREADER, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case CHANDELIER:

                cursor = database.query(VogueEntry.TABLE_CHANDELIER, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case EARCUFF:

                cursor = database.query(VogueEntry.TABLE_EARCUFF, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case HOOP:

                cursor = database.query(VogueEntry.TABLE_HOOP, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case JHUMKA:

                cursor = database.query(VogueEntry.TABLE_JHUMKA, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case STUD_ID:

                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(VogueEntry.TABLE_STUD, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case THREADER_ID:

                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(VogueEntry.TABLE_THREADER, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case CHANDELIER_ID:

                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(VogueEntry.TABLE_CHANDELIER, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case EARCUFF_ID:

                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(VogueEntry.TABLE_EARCUFF, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case HOOP_ID:

                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(VogueEntry.TABLE_HOOP, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case JHUMKA_ID:

                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(VogueEntry.TABLE_JHUMKA, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case STUD:
                return VogueEntry.STUD_LIST_TYPE;
            case STUD_ID:
                return VogueEntry.STUD_ITEM_TYPE;
            case THREADER:
                return VogueEntry.THREADER_LIST_TYPE;
            case THREADER_ID:
                return VogueEntry.THREADER_ITEM_TYPE;
            case CHANDELIER:
                return VogueEntry.CHANDELIER_LIST_TYPE;
            case CHANDELIER_ID:
                return VogueEntry.CHANDELIER_ITEM_TYPE;
            case EARCUFF:
                return VogueEntry.EARCUFF_LIST_TYPE;
            case EARCUFF_ID:
                return VogueEntry.EARCUFF_ITEM_TYPE;
            case HOOP:
                return VogueEntry.HOOP_LIST_TYPE;
            case HOOP_ID:
                return VogueEntry.HOOP_ITEM_TYPE;
            case JHUMKA:
                return VogueEntry.JHUMKA_LIST_TYPE;
            case JHUMKA_ID:
                return VogueEntry.JHUMKA_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case STUD:
                return insertVogue(match, uri, values);
            case THREADER:
                return insertVogue(match, uri, values);
            case CHANDELIER:
                return insertVogue(match, uri, values);
            case EARCUFF:
                return insertVogue(match, uri, values);
            case HOOP:
                return insertVogue(match, uri, values);
            case JHUMKA:
                return insertVogue(match, uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertVogue(int match, Uri uri, ContentValues values) {

        Integer availability = values.getAsInteger(VogueEntry.COLUMN_VOGUE_AVAILABILITY);
        if (availability == null || (availability != 0 && availability != 1)) {
            throw new IllegalArgumentException("Vogue requires valid availability");
        }

        Integer quantity = values.getAsInteger(VogueEntry.COLUMN_VOGUE_QUANTITY);
        if (quantity != null && quantity < 0) {
            throw new IllegalArgumentException("Vogue requires valid quantity");
        }

        Integer price = values.getAsInteger(VogueEntry.COLUMN_VOGUE_PRICE);
        if (price != null && price < 0) {
            throw new IllegalArgumentException("Vogue requires valid price");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        long id = -1;

        if (match == STUD || match == STUD_ID)
            id = database.insert(VogueEntry.TABLE_STUD, null, values);
        else if (match == CHANDELIER || match == CHANDELIER_ID)
            id = database.insert(VogueEntry.TABLE_CHANDELIER, null, values);
        else if (match == THREADER || match == THREADER_ID)
            id = database.insert(VogueEntry.TABLE_THREADER, null, values);
        else if (match == EARCUFF || match == EARCUFF_ID)
            id = database.insert(VogueEntry.TABLE_EARCUFF, null, values);
        else if (match == HOOP || match == HOOP_ID)
            id = database.insert(VogueEntry.TABLE_HOOP, null, values);
        else if (match == JHUMKA || match == JHUMKA_ID)
            id = database.insert(VogueEntry.TABLE_JHUMKA, null, values);
        else
            id = -1;

        if (id == -1) {
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case STUD:
                rowsDeleted = database.delete(VogueEntry.TABLE_STUD, selection, selectionArgs);
                break;
            case STUD_ID:
                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(VogueEntry.TABLE_STUD, selection, selectionArgs);
                break;

            case THREADER:
                rowsDeleted = database.delete(VogueEntry.TABLE_THREADER, selection, selectionArgs);
                break;
            case THREADER_ID:
                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(VogueEntry.TABLE_THREADER, selection, selectionArgs);
                break;

            case CHANDELIER:
                rowsDeleted = database.delete(VogueEntry.TABLE_CHANDELIER, selection, selectionArgs);
                break;
            case CHANDELIER_ID:
                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(VogueEntry.TABLE_CHANDELIER, selection, selectionArgs);
                break;

            case EARCUFF:
                rowsDeleted = database.delete(VogueEntry.TABLE_EARCUFF, selection, selectionArgs);
                break;
            case EARCUFF_ID:
                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(VogueEntry.TABLE_EARCUFF, selection, selectionArgs);
                break;

            case HOOP:
                rowsDeleted = database.delete(VogueEntry.TABLE_HOOP, selection, selectionArgs);
                break;
            case HOOP_ID:
                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(VogueEntry.TABLE_HOOP, selection, selectionArgs);
                break;

            case JHUMKA:
                rowsDeleted = database.delete(VogueEntry.TABLE_JHUMKA, selection, selectionArgs);
                break;
            case JHUMKA_ID:
                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(VogueEntry.TABLE_JHUMKA, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case STUD:
                return updateVogue(match, uri, values, selection, selectionArgs);
            case STUD_ID:
                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateVogue(match, uri, values, selection, selectionArgs);

            case THREADER:
                return updateVogue(match, uri, values, selection, selectionArgs);
            case THREADER_ID:
                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateVogue(match, uri, values, selection, selectionArgs);

            case CHANDELIER:
                return updateVogue(match, uri, values, selection, selectionArgs);
            case CHANDELIER_ID:
                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateVogue(match, uri, values, selection, selectionArgs);

            case EARCUFF:
                return updateVogue(match, uri, values, selection, selectionArgs);
            case EARCUFF_ID:
                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateVogue(match, uri, values, selection, selectionArgs);

            case HOOP:
                return updateVogue(match, uri, values, selection, selectionArgs);
            case HOOP_ID:
                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateVogue(match, uri, values, selection, selectionArgs);

            case JHUMKA:
                return updateVogue(match, uri, values, selection, selectionArgs);
            case JHUMKA_ID:
                selection = VogueEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateVogue(match, uri, values, selection, selectionArgs);

            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateVogue(int match, Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(VogueEntry.COLUMN_VOGUE_PRICE)) {
            Integer price = values.getAsInteger(VogueEntry.COLUMN_VOGUE_PRICE);
            if (price == null) {
                throw new IllegalArgumentException("Vogue requires a price");
            }
        }

        if (values.containsKey(VogueEntry.COLUMN_VOGUE_QUANTITY)) {
            Integer quantity = values.getAsInteger(VogueEntry.COLUMN_VOGUE_QUANTITY);
            if (quantity == null) {
                throw new IllegalArgumentException("Vogue requires a quantity");
            }
        }

        if (values.containsKey(VogueEntry.COLUMN_VOGUE_AVAILABILITY)) {
            Integer availability = values.getAsInteger(VogueEntry.COLUMN_VOGUE_AVAILABILITY);
            if (availability == null) {
                throw new IllegalArgumentException("Vogue requires availability");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsUpdated = 0;

        if (match == STUD || match == STUD_ID)
            rowsUpdated = database.update(VogueEntry.TABLE_STUD, values, selection, selectionArgs);
        else if (match == THREADER || match == THREADER_ID)
            rowsUpdated = database.update(VogueEntry.TABLE_THREADER, values, selection, selectionArgs);
        else if (match == CHANDELIER || match == CHANDELIER_ID)
            rowsUpdated = database.update(VogueEntry.TABLE_CHANDELIER, values, selection, selectionArgs);
        else if (match == EARCUFF || match == EARCUFF_ID)
            rowsUpdated = database.update(VogueEntry.TABLE_EARCUFF, values, selection, selectionArgs);
        else if (match == HOOP || match == HOOP_ID)
            rowsUpdated = database.update(VogueEntry.TABLE_HOOP, values, selection, selectionArgs);
        else if (match == JHUMKA || match == JHUMKA_ID)
            rowsUpdated = database.update(VogueEntry.TABLE_JHUMKA, values, selection, selectionArgs);
        else
            rowsUpdated = 0;

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

}
