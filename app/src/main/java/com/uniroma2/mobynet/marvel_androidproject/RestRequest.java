package com.uniroma2.mobynet.marvel_androidproject;

import android.os.AsyncTask;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

public class RestRequest {

    /* Attributi */
    private static AsyncHttpClient client = new AsyncHttpClient();
    private URL url;
    private long timestamp;
    private final String privateKey = "614dac0d45ede934f75eaba93f13e1ae5eb1f38f";
    private final String publicKey = "4b4c2ad8ede8e2cfd66baa32cee65dda";
    private String requestType; //characters o creators

    /* Costruttore */
    public RestRequest(String request){
        this.requestType = request;
    }

    /* Metodi */
    private static String convertToMd5(final String md5) throws UnsupportedEncodingException {
        StringBuffer sb=null;
        try {
            final java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            final byte[] array = md.digest(md5.getBytes("UTF-8"));
            sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (final java.security.NoSuchAlgorithmException e) {
        }
        return sb.toString();
    }
    public URL getUrl() {
        /*
        String origin = getTimestamp() + getPrivateKey() + getPublicKey();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(origin.getBytes());
            byte[] digest = messageDigest.digest();

            url = new URL("http://gateway.marvel.com/v1/public/" + getRequestType() + "?ts=" +
                    getTimestamp() + "&apikey=" + getPublicKey() + "&hash=" + messageDigest);

        } catch (NoSuchAlgorithmException|MalformedURLException e) {
            e.printStackTrace();
        }*/
        String md5String;
        try {
            md5String = convertToMd5(getTimestamp() + getPrivateKey() + getPublicKey());
            url = new URL("http://gateway.marvel.com/v1/public/" + getRequestType() + "?ts=" +
                    getTimestamp() + "&apikey=" + getPublicKey() + "&hash=" + md5String);

        } catch (UnsupportedEncodingException | MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public long getTimestamp() {
        timestamp = System.currentTimeMillis();
        return timestamp;
    }
    public String getPrivateKey() {
        return privateKey;
    }
    public String getPublicKey() {
        return publicKey;
    }
    public String getRequestType() {
        return requestType;
    }
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * Il metodo ...
     * @return: File JSon sottoforma di stringa
     */
    public void request(URL url, RequestParams params) {
        /*
        AsyncHttpResponseHandler responseHandler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + responseBody);
                try {
                    JSONObject serverResp = new JSONObject(responseBody.toString());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        };
        client.get(url.toString(), params, responseHandler);
        System.out.println(responseHandler.getRequestURI());
        System.out.println(responseHandler.getCharset());*/

    }

    // HTTP GET request
    public void sendGet() throws Exception {

        String url = "http://www.google.com/search?q=mkyong";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}
