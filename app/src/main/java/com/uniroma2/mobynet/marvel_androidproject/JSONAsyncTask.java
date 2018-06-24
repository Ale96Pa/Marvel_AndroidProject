package com.uniroma2.mobynet.marvel_androidproject;
/*
import android.os.AsyncTask;

import com.loopj.android.http.HttpGet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.transform.Result;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Boolean doInBackground(String params) {
        String stringUrl = params[0];
        Result stringa;

        try {

            //------------------>>
            // Crea un oggetto URL contenente il nostro url
            URL myUrl = new URL (stringUrl);
            // Crea una connessione
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection ();
            //RIPRENDERE DA QUI DAL SITO https://medium.com/@JasonCromer/android-asynctask-http-request-tutorial-6b429d833e28
            //DA QUI E' LA PARTE DEL SITO https://stackoverflow.com/questions/24399294/android-asynctask-to-make-an-http-get-request
            HttpGet httppost = new HttpGet("YOU URLS TO JSON");
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httppost);

            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();

            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);


                JSONObject jsono = new JSONObject(data);

                return true;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return false;
    }

    protected void onPostExecute(Boolean result) {

    }
}
*/