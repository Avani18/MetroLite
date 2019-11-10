package com.example.shehack;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomCursorAdapter extends CursorAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private SearchView searchView;

    public CustomCursorAdapter(Context context, Cursor cursor, SearchView sv)
    {
        super(context, cursor, false);
        mContext= context;
        searchView= sv;
        mLayoutInflater= LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = mLayoutInflater.inflate(R.layout.item_layout, viewGroup, false);
        return v;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        String station = cursor.getString(cursor.getColumnIndexOrThrow("station"));

        TextView tvStation = (TextView) view.findViewById(R.id.tv_station);
        tvStation.setText(station);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //take next action based user selected item
                TextView dealText = (TextView) view.findViewById(R.id.tv_station);
                searchView.setIconified(true);
                Toast.makeText(context, "Selected suggestion "+dealText.getText(),
                        Toast.LENGTH_LONG).show();

            }
        });
    }
}


