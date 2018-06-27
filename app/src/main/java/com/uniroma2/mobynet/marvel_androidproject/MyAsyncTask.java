package com.uniroma2.mobynet.marvel_androidproject;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * La classe MyAsyncTask implementa la connessione in background tramite un url per ricevere
 * file JSon richiesti tramite un metodo GET
 */
public class MyAsyncTask extends AsyncTask {
    public static String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        URL myUrl = (URL) objects[0];
        String result;
        String inputLine;

        try{
            // Creazione di una connessione
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

            // Impostazione di metodi e timeout
            connection.setRequestMethod (REQUEST_METHOD);
            connection.setReadTimeout (READ_TIMEOUT);
            connection.setConnectTimeout (CONNECTION_TIMEOUT);

            // Connessione all'url
            connection.connect ();

            // Creazione di un nuovo InputStreamReader che legge l'input di richiesta
            InputStreamReader streamReader = new InputStreamReader (connection.getInputStream ());

            // Creazione di un nuovo BufferedReader per scorrere la risposta e aggiungerle allo StringBuilder
            BufferedReader reader = new BufferedReader (streamReader);
            StringBuilder stringBuilder = new StringBuilder ();
            while ((inputLine = reader.readLine ()) != null) {
                stringBuilder.append (inputLine);
            }

            // Chiusura dell'InputStream e Bufferead
            reader.close ();
            streamReader.close ();

            // Impostazione e restituzione del risultato
            result = stringBuilder.toString ();

        } catch (IOException e) {
            e.printStackTrace();
            result=null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
    }
}