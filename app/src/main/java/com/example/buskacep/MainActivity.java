package com.example.buskacep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText ipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        ipt = findViewById(R.id.ipt);

    }
    public void getCEP(View view){

        if(ipt.length()==8){

            // instanciando a classe HTTPService e passando input do usuário
            HTTPService service = new HTTPService(ipt.getText().toString());

            try {

                // Chama os métodos execute() e get(), herdados de AsyncTask. Então atribui como objeto CEP
                CEP retorno = service.execute().get();

                // Uso do objeto Intent para transferir informações com outras telas.
                Intent intent = new Intent(getApplicationContext(),mostrar_resultado.class);
                intent.putExtra("cep",retorno.cep);
                intent.putExtra("rua",retorno.street);
                intent.putExtra("bairro",retorno.neighborhood);
                intent.putExtra("cidade",retorno.city);
                intent.putExtra("estado",retorno.state);

                // Chama a mostrar_resultado.
                startActivity(intent);


            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this,"Por favor, digite um CEP válido.",Toast.LENGTH_LONG).show();
        }
    }
}