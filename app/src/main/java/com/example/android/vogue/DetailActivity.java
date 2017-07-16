package com.example.android.vogue;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.vogue.vogue.VogueContract;

import java.io.ByteArrayInputStream;

public class DetailActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
    private Uri mCurrentUri;
    private TextView mid;
    private TextView mbrand;
    private TextView mprice;
    private TextView mavailability;
    private TextView mquantity1;
    private TextView mquantity2;
    private TextView mquantity3;
    private TextView mcolor;
    private TextView mmaterial;
    private TextView mclosure;
    private Button sale;
    private Button add;
    private Button addbutton;
    private Button minusbutton;
    private EditText editsale;
    private EditText editadd;
    private ImageView mimage;
    private static final int VOGUE_LOADER = 0;
    private Uri uri;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("Details");

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        uri = Uri.parse(bundle.getString("name"));
        mCurrentUri = intent.getData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, editorActivity.class);
                intent.putExtra("name", uri.toString());
                intent.setData(mCurrentUri);
                startActivity(intent);
            }
        });

        mimage = (ImageView) findViewById(R.id.detail_image);
        mid = (TextView) findViewById(R.id.detail_id);
        mbrand = (TextView) findViewById(R.id.detail_brand);
        mprice = (TextView) findViewById(R.id.detail_price);
        mavailability = (TextView) findViewById(R.id.detail_stock);
        mquantity1 = (TextView) findViewById(R.id.detail_quantity1);
        mquantity2 = (TextView) findViewById(R.id.detail_quantity2);
        mquantity3 = (TextView) findViewById(R.id.detail_quantity3);
        addbutton = (Button) findViewById(R.id.addbutton);
        minusbutton = (Button) findViewById(R.id.minusbutton);
        mcolor = (TextView) findViewById(R.id.detail_color);
        mmaterial = (TextView) findViewById(R.id.detail_material);
        mclosure = (TextView) findViewById(R.id.detail_closure);
        sale = (Button) findViewById(R.id.sale);
        add = (Button) findViewById(R.id.add);
        editadd = (EditText) findViewById(R.id.editadd);
        editsale = (EditText) findViewById(R.id.editsale);

        getLoaderManager().initLoader(VOGUE_LOADER, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {VogueContract.VogueEntry._ID,
                VogueContract.VogueEntry.COLUMN_VOGUE_ID,
                VogueContract.VogueEntry.COLUMN_VOGUE_BRAND,
                VogueContract.VogueEntry.COLUMN_VOGUE_PRICE,
                VogueContract.VogueEntry.COLUMN_VOGUE_COLOR,
                VogueContract.VogueEntry.COLUMN_VOGUE_MATERIAL,
                VogueContract.VogueEntry.COLUMN_VOGUE_CLOSURE,
                VogueContract.VogueEntry.COLUMN_VOGUE_AVAILABILITY,
                VogueContract.VogueEntry.COLUMN_VOGUE_QUANTITY,
                VogueContract.VogueEntry.COLUMN_VOGUE_IMAGE

        };
        return new CursorLoader(this, mCurrentUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if (cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndex(VogueContract.VogueEntry.COLUMN_VOGUE_ID);
            int brandColumnIndex = cursor.getColumnIndex(VogueContract.VogueEntry.COLUMN_VOGUE_BRAND);
            int priceColumnIndex = cursor.getColumnIndex(VogueContract.VogueEntry.COLUMN_VOGUE_PRICE);
            int colorColumnIndex = cursor.getColumnIndex(VogueContract.VogueEntry.COLUMN_VOGUE_COLOR);
            int materialColumnIndex = cursor.getColumnIndex(VogueContract.VogueEntry.COLUMN_VOGUE_MATERIAL);
            int closureColumnIndex = cursor.getColumnIndex(VogueContract.VogueEntry.COLUMN_VOGUE_CLOSURE);
            int availabilityColumnIndex = cursor.getColumnIndex(VogueContract.VogueEntry.COLUMN_VOGUE_AVAILABILITY);
            int quantityColumnIndex = cursor.getColumnIndex(VogueContract.VogueEntry.COLUMN_VOGUE_QUANTITY);
            int imageColumnIndex = cursor.getColumnIndex(VogueContract.VogueEntry.COLUMN_VOGUE_IMAGE);

            int id = cursor.getInt(idColumnIndex);
            String brand = cursor.getString(brandColumnIndex);
            String color = cursor.getString(colorColumnIndex);
            String material = cursor.getString(materialColumnIndex);
            String closure = cursor.getString(closureColumnIndex);
            final int availability = cursor.getInt(availabilityColumnIndex);
            final int price = cursor.getInt(priceColumnIndex);
            int quantity = cursor.getInt(quantityColumnIndex);
            byte[] image = cursor.getBlob(imageColumnIndex);

            String actualprice = "Rs. " + Integer.toString(price);
            final String actualid = "#" + Integer.toString(id);
            mid.setText(actualid);
            mbrand.setText(brand);
            mcolor.setText(color);
            mmaterial.setText(material);
            mclosure.setText(closure);
            mprice.setText(actualprice);
            mquantity1.setText(Integer.toString(quantity));
            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            mimage.setImageBitmap(theImage);
            String vogueactualavailability;
            if (availability == 1)
                vogueactualavailability = "Out Of Stock";
            else
                vogueactualavailability = "In Stock";
            if (availability == 0) {
                mquantity1.setVisibility(View.VISIBLE);
                mquantity2.setVisibility(View.VISIBLE);
                mquantity3.setVisibility(View.VISIBLE);
                sale.setVisibility(View.VISIBLE);
                editsale.setVisibility(View.VISIBLE);
                addbutton.setVisibility(View.VISIBLE);
                minusbutton.setVisibility(View.VISIBLE);
            } else {
                mquantity1.setVisibility(View.GONE);
                mquantity2.setVisibility(View.GONE);
                mquantity3.setVisibility(View.GONE);
                sale.setVisibility(View.GONE);
                editsale.setVisibility(View.GONE);
                addbutton.setVisibility(View.GONE);
                minusbutton.setVisibility(View.GONE);
            }
            mavailability.setText(vogueactualavailability);
            final int quantityChanged = quantity;
            sale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int count = Integer.valueOf(editsale.getText().toString());
                        int quantity = quantityChanged - count;
                        String count1 = String.valueOf(count);
                        mquantity1.setText(count1);
                        if (quantity < 0) {
                            quantity = 0;
                            Toast.makeText(getApplicationContext(), "Product isnot available in desired quantity", Toast.LENGTH_SHORT).show();
                            count = quantityChanged;
                        }
                        String order = "Order Earrings :\nID :" + actualid + "\nQuantity :" + count + "\nTotal Price :" + count * price + "\n\nThank You :)";
                        if (quantity <= 0)
                            mavailability.setText("Out Of Stock");
                        ContentValues values = new ContentValues();
                        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_QUANTITY, quantity);
                        if (quantity <= 0)
                            values.put(VogueContract.VogueEntry.COLUMN_VOGUE_AVAILABILITY, 1);
                        int rowsAffected = getContentResolver().update(mCurrentUri, values, null, null);
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"));
                        intent.putExtra(Intent.EXTRA_EMAIL, "meghmai.p@gmail.com");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for Earrings");
                        intent.putExtra(Intent.EXTRA_TEXT, order);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Enter Valid Quantity", Toast.LENGTH_SHORT).show();
                    } finally {
                        editsale.setText("");
                    }
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int count = Integer.parseInt(editadd.getText().toString());
                        int quantity = quantityChanged + count;
                        String count1 = String.valueOf(count);
                        mquantity1.setText(count1);
                        if (availability == 1 && count != 0)
                            mavailability.setText("In Stock");
                        ContentValues values = new ContentValues();
                        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_QUANTITY, quantity);
                        if (availability == 1 && count != 0)
                            values.put(VogueContract.VogueEntry.COLUMN_VOGUE_AVAILABILITY, 0);
                        int rowsAffected = getContentResolver().update(mCurrentUri, values, null, null);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Enter Valid Quantity", Toast.LENGTH_SHORT).show();
                    } finally {
                        editadd.setText("");
                    }
                }
            });
            addbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = quantityChanged + 1;
                    mquantity1.setText(String.valueOf(quantity));
                    ContentValues values = new ContentValues();
                    values.put(VogueContract.VogueEntry.COLUMN_VOGUE_QUANTITY, quantity);
                    int rowsAffected = getContentResolver().update(mCurrentUri, values, null, null);
                }
            });
            minusbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = quantityChanged - 1;
                    if (quantity > 0) {
                        mquantity1.setText(String.valueOf(quantity));
                        ContentValues values = new ContentValues();
                        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_QUANTITY, quantity);
                        int rowsAffected = getContentResolver().update(mCurrentUri, values, null, null);
                    }
                    if (quantity == 0) {
                        mquantity1.setText(String.valueOf(quantity));
                        ContentValues values = new ContentValues();
                        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_QUANTITY, quantity);
                        mavailability.setText("Out Of Stock");
                        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_AVAILABILITY, 1);
                        int rowsAffected = getContentResolver().update(mCurrentUri, values, null, null);
                    }
                }
            });
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mid.setText("");
        mbrand.setText("");
        mcolor.setText("");
        mmaterial.setText("");
        mclosure.setText("");
        mprice.setText("");
        mquantity1.setText("");
        mavailability.setText("");
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteVogue();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteVogue() {
        if (mCurrentUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentUri, null, null);
        }
        finish();
    }


}


