package com.example.buskacep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText ipt;
    private TextView rcep;
    private TextView rrua;
    private TextView rbairro;
    private TextView rcidade;
    private TextView restado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        ipt = findViewById(R.id.ipt);
        /*
        rcep = findViewById(R.id.rcep);
        rrua = findViewById(R.id.rrua);
        rbairro = findViewById(R.id.rbairro);
        rcidade = findViewById(R.id.rcidade);
        restado = findViewById(R.id.restado);
        */
    }
    public void getCEP(View view){
        if(ipt.length()>0 && ipt.length()==8){

            HTTPService service = new HTTPService(ipt.getText().toString());
            try {
                CEP retorno = service.execute().get();

                /*
                rcep.setText(retorno.cep);
                rrua.setText(retorno.street);
                rbairro.setText(retorno.neighborhood);
                rcidade.setText(retorno.city);
                restado.setText(retorno.state);
                */

                Intent intent = new Intent(getApplicationContext(),mostrar_resultado.class);
                intent.putExtra("cep",retorno.cep);
                intent.putExtra("rua",retorno.street);
                intent.putExtra("bairro",retorno.neighborhood);
                intent.putExtra("cidade",retorno.city);
                intent.putExtra("estado",retorno.state);

                startActivity(intent);



            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}