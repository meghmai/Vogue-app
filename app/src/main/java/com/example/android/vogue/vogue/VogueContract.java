package com.example.android.vogue.vogue;


import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class VogueContract {
    private VogueContract() {

    }

    public static final String CONTENT_AUTHORITY = "com.example.android.vogue";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public final static String PATH_JHUMKA = "jhumka";
    public final static String PATH_STUD = "stud";
    public final static String PATH_THREADER = "threader";
    public final static String PATH_CHANDELIER = "chandelier";
    public final static String PATH_EARCUFF = "earcuff";
    public final static String PATH_HOOP = "hoop";

    public static final class VogueEntry implements BaseColumns {

        public static final Uri JHUMKA_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_JHUMKA);
        public static final Uri STUD_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_STUD);
        public static final Uri THREADER_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_THREADER);
        public static final Uri CHANDELIER_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CHANDELIER);
        public static final Uri EARCUFF_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_EARCUFF);
        public static final Uri HOOP_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_HOOP);

        public static final String JHUMKA_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_JHUMKA;

        public static final String JHUMKA_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_JHUMKA;

        public static final String STUD_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STUD;

        public static final String STUD_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STUD;

        public static final String THREADER_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_THREADER;

        public static final String THREADER_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_THREADER;

        public static final String CHANDELIER_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CHANDELIER;

        public static final String CHANDELIER_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CHANDELIER;

        public static final String EARCUFF_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EARCUFF;

        public static final String EARCUFF_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EARCUFF;

        public static final String HOOP_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HOOP;

        public static final String HOOP_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HOOP;

        public final static String TABLE_JHUMKA = "jhumka";
        public final static String TABLE_STUD = "stud";
        public final static String TABLE_THREADER = "threader";
        public final static String TABLE_CHANDELIER = "chandelier";
        public final static String TABLE_EARCUFF = "earcuff";
        public final static String TABLE_HOOP = "hoop";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_VOGUE_BRAND = "brand";

        public final static String COLUMN_VOGUE_ID = "id";

        public final static String COLUMN_VOGUE_PRICE = "price";

        public final static String COLUMN_VOGUE_COLOR = "color";

        public final static String COLUMN_VOGUE_MATERIAL = "material";

        public final static String COLUMN_VOGUE_CLOSURE = "closure";

        public final static String COLUMN_VOGUE_AVAILABILITY = "availability";

        public final static String COLUMN_VOGUE_QUANTITY = "quantity";

        public final static String COLUMN_VOGUE_IMAGE = "image";

        public static final int AVAILABILITY_INSTOCK = 0;
        public static final int AVAILABILITY_OUTSTOCK = 1;

    }
}
