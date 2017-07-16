package com.example.android.vogue;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.vogue.vogue.VogueContract;
import com.example.android.vogue.vogue.VogueContract.VogueEntry;
import com.example.android.vogue.vogue.VogueDbHelper;

import java.io.ByteArrayInputStream;

public class VogueCursorAdapter extends CursorAdapter {
    String mfragment;

    public VogueCursorAdapter(Context context, Cursor c, String fragment) {

        super(context, c, 0);
        mfragment = fragment;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        TextView id = (TextView) view.findViewById(R.id.id);
        TextView brand = (TextView) view.findViewById(R.id.brand);
        TextView price = (TextView) view.findViewById(R.id.price);
        final TextView availability = (TextView) view.findViewById(R.id.stock);
        final TextView quantity1 = (TextView) view.findViewById(R.id.quantity1);
        final TextView quantity2 = (TextView) view.findViewById(R.id.quantity2);
        ImageView imageview = (ImageView) view.findViewById(R.id.image);
        ImageView buy = (ImageView) view.findViewById(R.id.buy_image);

        int idColumnIndex = cursor.getColumnIndex(VogueEntry.COLUMN_VOGUE_ID);
        int brandColumnIndex = cursor.getColumnIndex(VogueEntry.COLUMN_VOGUE_BRAND);
        int priceColumnIndex = cursor.getColumnIndex(VogueEntry.COLUMN_VOGUE_PRICE);
        int availabilityColumnIndex = cursor.getColumnIndex(VogueEntry.COLUMN_VOGUE_AVAILABILITY);
        int quantityColumnIndex = cursor.getColumnIndex(VogueEntry.COLUMN_VOGUE_QUANTITY);
        int imageColumnIndex = cursor.getColumnIndex(VogueContract.VogueEntry.COLUMN_VOGUE_IMAGE);

        String vogueid = cursor.getString(idColumnIndex);
        String voguebrand = cursor.getString(brandColumnIndex);
        String vogueprice = cursor.getString(priceColumnIndex);
        String voguequantity = cursor.getString(quantityColumnIndex);
        Integer quantity3 = Integer.parseInt(voguequantity);
        int vogueavailability = cursor.getInt(availabilityColumnIndex);
        byte[] image = cursor.getBlob(imageColumnIndex);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(quantity1.getText().toString());
                count--;
                if (count > 0) {
                    VogueDbHelper mDbHelper = new VogueDbHelper(context);
                    SQLiteDatabase database = mDbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(VogueEntry.COLUMN_VOGUE_QUANTITY, count);
                    database.update(mfragment, values, null, null);
                    quantity1.setText(String.valueOf(count));
                    database.close();
                }
                if (count == 0) {
                    VogueDbHelper mDbHelper = new VogueDbHelper(context);
                    SQLiteDatabase database = mDbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(VogueEntry.COLUMN_VOGUE_AVAILABILITY, 1);
                    values.put(VogueEntry.COLUMN_VOGUE_QUANTITY, count);
                    database.update(mfragment, values, null, null);
                    availability.setText("Out Of Stock");
                    quantity1.setText(String.valueOf(count));
                    quantity1.setVisibility(View.GONE);
                    quantity2.setVisibility(View.GONE);
                    database.close();
                }
                if (count < 0)
                    Toast.makeText(context, "The Desired Product is Out Of Stock", Toast.LENGTH_SHORT).show();

            }
        });
        String brand1 = voguebrand;
        if (TextUtils.isEmpty(voguebrand)) {
            brand1 = context.getString(R.string.unknown);
        }
        String vogueactualid = "#" + vogueid;
        String vogueactualprice = "Rs. " + vogueprice;
        String vogueactualavailability;
        if (vogueavailability == 1)
            vogueactualavailability = "Out Of Stock";
        else
            vogueactualavailability = "In Stock";
        if (vogueavailability == 0) {
            quantity1.setVisibility(View.VISIBLE);
            quantity2.setVisibility(View.VISIBLE);
        } else {
            quantity1.setVisibility(View.GONE);
            quantity2.setVisibility(View.GONE);
        }
        id.setText(vogueactualid);
        brand.setText(brand1);
        price.setText(vogueactualprice);
        availability.setText(vogueactualavailability);
        quantity1.setText(voguequantity);
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        imageview.setImageBitmap(theImage);

    }
}
