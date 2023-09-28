package com.example.listacolorida;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class Adapter extends CursorAdapter {

    public Adapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = view.findViewById(android.R.id.text1);
        @SuppressLint("Range") String texto = cursor.getString(cursor.getColumnIndex("Texto"));
        @SuppressLint("Range") int cor = cursor.getInt(cursor.getColumnIndex("Cor"));

        // Configure a cor do texto
        textView.setTextColor(cor);
        textView.setText(texto);
    }
}
