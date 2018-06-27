package com.uniroma2.mobynet.marvel_androidproject;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Matteo on 24/06/2018.
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
            // Creo una connessione
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

            // Imposta metodi e timeout
            connection.setRequestMethod (REQUEST_METHOD);
            connection.setReadTimeout (READ_TIMEOUT);
            connection.setConnectTimeout (CONNECTION_TIMEOUT);

            // Connetti alla nostra url
            connection.connect ();

            // Crea un nuovo InputStreamReader che legge il nostro input di richiesta
            InputStreamReader streamReader = new InputStreamReader (connection.getInputStream ());

            // Crea un nuovo BufferedReader per scorrere la risposta e aggingerle allo StringBuilder
            BufferedReader reader = new BufferedReader (streamReader);
            StringBuilder stringBuilder = new StringBuilder ();

            // Controlla se la linea che stiamo leggendo non è nullo
            while ((inputLine = reader.readLine ()) != null) {
                stringBuilder.append (inputLine);
            }

            // Chiudi il nostro lettore InputStream e Buffered
            reader.close ();
            streamReader.close ();

            // Imposta il nostro risultato uguale al nostro stringBuilder
            result = stringBuilder.toString ();

            //return result;

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

