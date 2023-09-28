        package com.example.listacolorida;

        import androidx.appcompat.app.AppCompatActivity;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.database.Cursor;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ListView;

        import android.widget.Toast;

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
        EscutadorLista el = new EscutadorLista();
        lista.setOnItemClickListener( el );
        lista.setOnItemLongClickListener( el );

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

    private class EscutadorLista implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) cursorAdapter.getItem(i);
            if (cursor != null) {
                @SuppressLint("Range") String texto = cursor.getString(cursor.getColumnIndex("Texto"));
                @SuppressLint("Range") int numCor = cursor.getInt(cursor.getColumnIndex("Cor"));
                String cor = "";

                if (numCor == Color.RED){
                    cor = "Vermelho";
                } else if (numCor == Color.GREEN) {
                    cor = "Verde";
                } else if (numCor == Color.BLUE) {
                    cor = "Azul";
                }

                // Exemplo: exibir informações do item clicado
                Toast.makeText(MainActivity.this, texto + "\n" + cor, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) cursorAdapter.getItem(i);

            if (cursor != null) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("_id"));

                // Exclui o item do banco de dados
                db.excluirDadoPorId(id);

                // Atualiza a lista
                atualizarDados();

                Toast.makeText(MainActivity.this, "Texto excluído!", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }


    private void atualizarDados() {
        Cursor cursor = db.obterTodosDados();
        cursorAdapter.changeCursor(cursor);
        cursorAdapter.notifyDataSetChanged();
    }
}

