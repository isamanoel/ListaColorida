package com.example.listacolorida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private Button btnAddText;
    private DataBase db;
    private Adapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.listaColorida);
        btnAddText = findViewById(R.id.btnAddText);
        db = new DataBase(this);

        Cursor cursor = db.obterTodosDados();

        cursorAdapter = new Adapter(this, cursor, 0);
        lista.setAdapter(cursorAdapter);

        btnAddText.setOnClickListener(new EscutadorBotao());
    }

    private class EscutadorBotao implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(MainActivity.this, AddTextActivity.class);
            startActivityForResult(i, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String texto = data.getStringExtra("text");
            int cor = data.getIntExtra("color", 0);

            Texto texto1 = new Texto(texto, cor);
            db.inserirDado(texto1.getTexto(), texto1.getCor());
            atualizarDados();
        }
    }

    private void atualizarDados() {
        Cursor cursor = db.obterTodosDados();
        cursorAdapter.changeCursor(cursor);
        cursorAdapter.notifyDataSetChanged();
    }
}

