package com.example.android.vogue;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.vogue.vogue.VogueContract;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class editorActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_PET_LOADER = 0;

    private Uri mCurrentUri;

    private EditText mid;
    private EditText mbrand;
    private EditText mprice;
    private EditText mcolor;
    private EditText mmaterial;
    private EditText mclosure;
    private EditText mquantity1;
    private Spinner mavailability;
    private TextView mquantity2;
    private TextView mquantity3;
    private Bundle bundle;
    private Button button;
    private ImageView imageView;
    private Uri uri;
    private Bitmap bitmap;
    private byte[] image, mimage;
    private int PICK_IMAGE_REQUEST = 1;
    private int availability = VogueContract.VogueEntry.AVAILABILITY_INSTOCK;
    private boolean mHasChanged = false;
    private boolean imageHasChanged = false;
    private String table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Intent intent = getIntent();
        bundle = getIntent().getExtras();
        uri = Uri.parse(bundle.getString("name"));
        table = bundle.getString("table");
        mCurrentUri = intent.getData();
        if (mCurrentUri == null) {
            setTitle("Add a Product");

        } else {
            setTitle("Edit the Product");
            getLoaderManager().initLoader(EXISTING_PET_LOADER, null, this);
        }
        mid = (EditText) findViewById(R.id.edit_id);
        mbrand = (EditText) findViewById(R.id.edit_brand);
        mprice = (EditText) findViewById(R.id.edit_price);
        mcolor = (EditText) findViewById(R.id.edit_color);
        mmaterial = (EditText) findViewById(R.id.edit_material);
        mclosure = (EditText) findViewById(R.id.edit_closure);
        mquantity1 = (EditText) findViewById(R.id.edit_quantity1);
        mquantity2 = (TextView) findViewById(R.id.edit_quantity2);
        mquantity3 = (TextView) findViewById(R.id.edit_quantity3);
        imageView = (ImageView) findViewById(R.id.img);
        mavailability = (Spinner) findViewById(R.id.spinner_availability);
        setupSpinner();
        button = (Button) findViewById(R.id.setimage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        mid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mHasChanged = true;
                return false;
            }
        });
        mbrand.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mHasChanged = true;
                return false;
            }
        });
        mprice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mHasChanged = true;
                return false;
            }
        });
        mcolor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mHasChanged = true;
                return false;
            }
        });
        mmaterial.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mHasChanged = true;
                return false;
            }
        });
        mclosure.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mHasChanged = true;
                return false;
            }
        });
        mquantity1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mHasChanged = true;
                return false;
            }
        });
        mavailability.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mHasChanged = true;
                return false;
            }
        });
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mHasChanged = true;
                imageHasChanged = true;
                return false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                image = bos.toByteArray();
                ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(theImage);
                imageView.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupSpinner() {

        ArrayAdapter availabilitySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.availability, android.R.layout.simple_spinner_item);

        availabilitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mavailability.setAdapter(availabilitySpinnerAdapter);

        mavailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.instock))) {
                        availability = VogueContract.VogueEntry.AVAILABILITY_INSTOCK;
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                    } else {
                        availability = VogueContract.VogueEntry.AVAILABILITY_OUTSTOCK;
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                availability = VogueContract.VogueEntry.AVAILABILITY_INSTOCK;
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            }
        });
    }

    private void saveVogue() {

        String idString = mid.getText().toString().trim();
        String brandString = mbrand.getText().toString().trim();
        String priceString = mprice.getText().toString().trim();
        String colorString = mcolor.getText().toString().trim();
        String materialString = mmaterial.getText().toString().trim();
        String closureString = mclosure.getText().toString().trim();

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
        imageView.setDrawingCacheEnabled(false);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        mimage = bos.toByteArray();

        if (availability == 1)
            mquantity1.setText(Integer.toString(0));

        String quantityString = mquantity1.getText().toString().trim();
        if (mCurrentUri == null &&
                TextUtils.isEmpty(brandString) && TextUtils.isEmpty(idString) && TextUtils.isEmpty(priceString) && TextUtils.isEmpty(quantityString) && imageView.getDrawable() == null &&
                TextUtils.isEmpty(colorString) && TextUtils.isEmpty(materialString) && TextUtils.isEmpty(closureString) && availability == VogueContract.VogueEntry.AVAILABILITY_INSTOCK) {
            return;
        }

        ContentValues values = new ContentValues();
        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_BRAND, brandString);
        int price = 0;
        if (!TextUtils.isEmpty(priceString)) {
            price = Integer.parseInt(priceString);
        }
        int id = 0;
        if (!TextUtils.isEmpty(idString)) {
            id = Integer.parseInt(idString);
        }

        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_ID, id);
        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_PRICE, price);
        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_AVAILABILITY, availability);
        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_COLOR, colorString);
        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_MATERIAL, materialString);
        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_CLOSURE, closureString);
        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_IMAGE, mimage);
        int quantity = 0;
        if (!TextUtils.isEmpty(quantityString)) {
            quantity = Integer.parseInt(quantityString);
        }
        values.put(VogueContract.VogueEntry.COLUMN_VOGUE_QUANTITY, quantity);

        if (mCurrentUri == null) {
            Uri newUri = getContentResolver().insert(uri, values);

        } else {

            int rowsAffected = getContentResolver().update(mCurrentUri, values, null, null);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor_menu, menu);
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
            case R.id.action_save:
                String empty = "";
                String idString = mid.getText().toString().trim();
                String brandString = mbrand.getText().toString().trim();
                String priceString = mprice.getText().toString().trim();
                String colorString = mcolor.getText().toString().trim();
                String materialString = mmaterial.getText().toString().trim();
                String closureString = mclosure.getText().toString().trim();
                String quantityString = mquantity1.getText().toString().trim();
                int price = 0;
                int id = 0;
                if (!priceString.equals(empty))
                    price = Integer.parseInt(priceString);
                if (!idString.equals(empty))
                    id = Integer.parseInt(idString);
                int quantity = 0;
                if (!TextUtils.isEmpty(quantityString)) {
                    quantity = Integer.parseInt(quantityString);
                }
                if (imageView.getDrawable() != null)
                    imageView.setVisibility(View.VISIBLE);
                if (mCurrentUri == null &&
                        TextUtils.isEmpty(brandString) && TextUtils.isEmpty(idString) && TextUtils.isEmpty(priceString) && TextUtils.isEmpty(quantityString) && imageView.getDrawable() == null &&
                        TextUtils.isEmpty(colorString) && TextUtils.isEmpty(materialString) && TextUtils.isEmpty(closureString) && availability == VogueContract.VogueEntry.AVAILABILITY_INSTOCK) {
                    Toast.makeText(this, "Enter Valid Details", Toast.LENGTH_SHORT).show();
                    finish();
                    return true;
                }
                if (price == 0 || id == 0 || imageView.getDrawable() == null || (availability == 0 && quantity == 0)) {
                    Toast.makeText(this, "Enter All Compulsory Details", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    saveVogue();
                    finish();
                    return true;
                }

            case android.R.id.home:
                if (!mHasChanged) {
                    Intent intent = NavUtils.getParentActivityIntent(this);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this, intent);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = NavUtils.getParentActivityIntent(editorActivity.this);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                NavUtils.navigateUpTo(editorActivity.this, intent);
                            }
                        };

                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!mHasChanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
            int availability = cursor.getInt(availabilityColumnIndex);

            int price = cursor.getInt(priceColumnIndex);
            int quantity = cursor.getInt(quantityColumnIndex);
            byte[] image = cursor.getBlob(imageColumnIndex);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            imageView.setImageBitmap(theImage);

            mid.setText(Integer.toString(id));
            mbrand.setText(brand);
            mcolor.setText(color);
            mmaterial.setText(material);
            mclosure.setText(closure);
            mprice.setText(Integer.toString(price));
            mquantity1.setText(Integer.toString(quantity));

            switch (availability) {
                case VogueContract.VogueEntry.AVAILABILITY_INSTOCK:
                    mavailability.setSelection(0);
                    break;
                case VogueContract.VogueEntry.AVAILABILITY_OUTSTOCK:
                    mavailability.setSelection(1);
                    break;
                default:
                    mavailability.setSelection(0);
                    break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mid.setText("");
        mbrand.setText("");
        mprice.setText("");
        mcolor.setText("");
        mmaterial.setText("");
        mcolor.setText("");
        mquantity1.setText("");
        mavailability.setSelection(0);

    }
}
