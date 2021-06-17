package com.example.buskacep;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class HTTPService extends AsyncTask<Void,Void,CEP> {

    private final String cep;

    public HTTPService(String cep) {
        this.cep = cep;
    }

    @Override
    protected CEP doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        try {
            // intanciando a classe URL com a url da BrasilAPI.
            URL url = new URL("https://brasilapi.com.br/api/cep/v1/"+this.cep);

            // Inicializa a classe HttpURLConnection com as configurações da requisição
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            connection.setConnectTimeout(5000);

            // Aqui a requisição é feita
            connection.connect();

            // Inicializa a classe Scanner para escanear o JSON
            Scanner scanner = new Scanner(url.openStream());

            while(scanner.hasNext()){
                resposta.append(scanner.next());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // objeto Gson do Google para converter JSON para uma string.
        return new Gson().fromJson((resposta.toString()),CEP.class);
    }
}
