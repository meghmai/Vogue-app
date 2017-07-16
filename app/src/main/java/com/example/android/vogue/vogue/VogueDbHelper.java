package com.example.android.vogue.vogue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.vogue.vogue.VogueContract.VogueEntry;

public class VogueDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "vogue.db";

    private static final int DATABASE_VERSION = 1;

    public VogueDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_STUD_TABLE = "CREATE TABLE " + VogueEntry.TABLE_STUD + " ("
                + VogueEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VogueEntry.COLUMN_VOGUE_ID + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_BRAND + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_PRICE + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_COLOR + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_MATERIAL + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_CLOSURE + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_AVAILABILITY + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_QUANTITY + " INTEGER NOT NULL DEFAULT 0,"
                + VogueEntry.COLUMN_VOGUE_IMAGE + " BLOB);";

        String SQL_CREATE_JHUMKA_TABLE = "CREATE TABLE " + VogueEntry.TABLE_JHUMKA + " ("
                + VogueEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VogueEntry.COLUMN_VOGUE_ID + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_BRAND + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_PRICE + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_COLOR + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_MATERIAL + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_CLOSURE + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_AVAILABILITY + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_QUANTITY + " INTEGER NOT NULL DEFAULT 0,"
                + VogueEntry.COLUMN_VOGUE_IMAGE + " BLOB);";

        String SQL_CREATE_THREADER_TABLE = "CREATE TABLE " + VogueEntry.TABLE_THREADER + " ("
                + VogueEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VogueEntry.COLUMN_VOGUE_ID + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_BRAND + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_PRICE + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_COLOR + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_MATERIAL + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_CLOSURE + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_AVAILABILITY + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_QUANTITY + " INTEGER NOT NULL DEFAULT 0,"
                + VogueEntry.COLUMN_VOGUE_IMAGE + " BLOB);";

        String SQL_CREATE_CHANDELIER_TABLE = "CREATE TABLE " + VogueEntry.TABLE_CHANDELIER + " ("
                + VogueEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VogueEntry.COLUMN_VOGUE_ID + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_BRAND + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_PRICE + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_COLOR + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_MATERIAL + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_CLOSURE + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_AVAILABILITY + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_QUANTITY + " INTEGER NOT NULL DEFAULT 0,"
                + VogueEntry.COLUMN_VOGUE_IMAGE + " BLOB);";

        String SQL_CREATE_EARCUFF_TABLE = "CREATE TABLE " + VogueEntry.TABLE_EARCUFF + " ("
                + VogueEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VogueEntry.COLUMN_VOGUE_ID + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_BRAND + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_PRICE + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_COLOR + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_MATERIAL + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_CLOSURE + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_AVAILABILITY + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_QUANTITY + " INTEGER NOT NULL DEFAULT 0,"
                + VogueEntry.COLUMN_VOGUE_IMAGE + " BLOB);";

        String SQL_CREATE_HOOP_TABLE = "CREATE TABLE " + VogueEntry.TABLE_HOOP + " ("
                + VogueEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VogueEntry.COLUMN_VOGUE_ID + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_BRAND + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_PRICE + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_COLOR + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_MATERIAL + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_CLOSURE + " TEXT, "
                + VogueEntry.COLUMN_VOGUE_AVAILABILITY + " INTEGER NOT NULL, "
                + VogueEntry.COLUMN_VOGUE_QUANTITY + " INTEGER NOT NULL DEFAULT 0,"
                + VogueEntry.COLUMN_VOGUE_IMAGE + " BLOB );";

        db.execSQL(SQL_CREATE_STUD_TABLE);
        db.execSQL(SQL_CREATE_JHUMKA_TABLE);
        db.execSQL(SQL_CREATE_HOOP_TABLE);
        db.execSQL(SQL_CREATE_THREADER_TABLE);
        db.execSQL(SQL_CREATE_CHANDELIER_TABLE);
        db.execSQL(SQL_CREATE_EARCUFF_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
