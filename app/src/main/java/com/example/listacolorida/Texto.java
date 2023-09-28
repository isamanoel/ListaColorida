package com.example.listacolorida;

import android.graphics.Color;

public class Texto {
    private String texto;
    private Color cor;

    public Texto (String texto, Color cor){
        this.texto = texto;
        this.cor = cor;
    }

    public String getTexto() {
        return texto;
    }

    public Color getCor() {
        return cor;
    }
}
