package com.example.buskacep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class mostrar_resultado extends AppCompatActivity {

    private TextView scep;
    private TextView srua;
    private TextView sbairro;
    private TextView scidade;
    private TextView sestado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mostrar_resultado);

        scep = findViewById(R.id.scep);
        srua = findViewById(R.id.srua);
        sbairro = findViewById(R.id.sbairro);
        scidade = findViewById(R.id.scidade);
        sestado = findViewById(R.id.sestado);

        Bundle dados = getIntent().getExtras();

        // Busca as strings passados pelo objeto Intent
        scep.setText(dados.getString("cep"));
        srua.setText(dados.getString("rua"));
        sbairro.setText(dados.getString("bairro"));
        scidade.setText(dados.getString("cidade"));
        sestado.setText(dados.getString("estado"));

    }
}