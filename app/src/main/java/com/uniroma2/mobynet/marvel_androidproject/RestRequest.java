package com.uniroma2.mobynet.marvel_androidproject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Matteo on 24/06/2018.
 */

public class RestRequest {
    /* Attributi */
    //private static AsyncHttpClient client = new AsyncHttpClient();
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

    // HTTP GET request
    public void sendGet() throws Exception {
        URL url = getUrl();     //prendo il mio url
        String result;  //gli inserisco il risultato
        MyAsyncTask request = new MyAsyncTask();

        // Esegue il metodo doInBackground, passando il nostro url
        result = (String) request.execute(url).get();

        if(result==null){
            System.out.println("C' e' stato un errore nella richiesta GET!");
            return;
        }
        System.out.println(result);


    }
}
