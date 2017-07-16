package com.example.android.vogue;


import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.vogue.vogue.VogueContract;
import com.example.android.vogue.vogue.VogueContract.VogueEntry;

public class jhumkasFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {

    VogueCursorAdapter mCursorAdapter;
    private static final int VOGUE_LOADER = 0;
    Context context;

    public jhumkasFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_earring, container, false);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = getActivity();
                Intent intent = new Intent(context, editorActivity.class);
                intent.putExtra("name", VogueContract.VogueEntry.JHUMKA_URI.toString());
                intent.putExtra("table", VogueEntry.TABLE_JHUMKA);
                getActivity().startActivity(intent);
            }
        });
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setBackgroundResource(R.drawable.jhumkabackground);
        View emptyView = rootView.findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);
        ImageView image = (ImageView) emptyView.findViewById(R.id.empty_image);
        image.setImageResource(R.drawable.jhumka);
        TextView text = (TextView) emptyView.findViewById(R.id.empty_title_text);
        text.setText("No Jhumkas Found\nClick The Add Button To Add New Jhumkas");
        mCursorAdapter = new VogueCursorAdapter(getActivity(), null, VogueEntry.TABLE_JHUMKA);
        listView.setAdapter(mCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Uri currentPetUri = ContentUris.withAppendedId(VogueEntry.JHUMKA_URI, id);
                intent.putExtra("name", VogueEntry.JHUMKA_URI.toString());
                intent.setData(currentPetUri);
                getActivity().startActivity(intent);
            }
        });

        getLoaderManager().initLoader(VOGUE_LOADER, null, this);
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                VogueEntry._ID,
                VogueEntry.COLUMN_VOGUE_ID,
                VogueEntry.COLUMN_VOGUE_BRAND,
                VogueEntry.COLUMN_VOGUE_PRICE,
                VogueEntry.COLUMN_VOGUE_AVAILABILITY,
                VogueEntry.COLUMN_VOGUE_QUANTITY, VogueEntry.COLUMN_VOGUE_IMAGE};

        return new CursorLoader(getActivity(), VogueEntry.JHUMKA_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
