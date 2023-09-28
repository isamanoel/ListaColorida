package com.example.listacolorida;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddTextActivity {

    private EditText txtTexto;
    private RadioButton radVermelho;
    private RadioButton radVerde;
    private RadioButton radAzul;
    private Button btnInsere;
    private Button btnCancela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_text);

        txtTexto findViewById(R.id.txtTexto);;
        radVermelho findViewById(R.id.radVermelho);;
        radVerde findViewById(R.id.radVerde);;
        radAzul findViewById(R.id.radAzul);;
        btnInsere = findViewById(R.id.btnInsere);
        btnCancela = findViewById(R.id.btnCancela);

        btnInsere.setOnClickListener(new EscutadorBotaoAdicionar());
        btnCancela.setOnClickListener(new EscutadorBotaoCancelar());
    }

}
