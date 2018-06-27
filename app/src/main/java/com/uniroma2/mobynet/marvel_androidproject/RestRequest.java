package com.uniroma2.mobynet.marvel_androidproject;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import io.mikael.urlbuilder.UrlBuilder;

public class RestRequest {
    /* Attributi */
    //private static AsyncHttpClient client = new AsyncHttpClient();
    private String url;
    private final String entryPoint = "http://gateway.marvel.com/v1/public/";
    private long timestamp;
    private final String privateKey = "614dac0d45ede934f75eaba93f13e1ae5eb1f38f";
    private final String publicKey = "4b4c2ad8ede8e2cfd66baa32cee65dda";
    private String requestType; //characters o creators

    /* Costruttore */
    public RestRequest(String request){
        this.requestType = request;
    }

    /* Metodi */
    private String createHash(long ts) {
        /*
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
        */
        String stringToHash = ts+getPrivateKey()+getPublicKey();
        String hash = DigestUtils.md5Hex(stringToHash);
        System.out.println("******* HASH: ******: " + hash);
        return hash;
    }
    public String getUrl() {
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
        }
        String md5String;

        String hash = DigestUtils.md5Hex(getTimestamp() + getPrivateKey() + getPublicKey());
        url = new URL(getEntryPoint() + getRequestType() + "?ts=" +
                getTimestamp() + "&apikey=" + getPublicKey() + "&hash=" + md5String);
        String url = String.format(getEntryPoint() + getRequestType() + "?ts=" +
               getTimestamp() + "&apikey=" + getPublicKey() + "&hash=" + hash);
        //String output = new Resty().Text(url).toString();
        System.out.println("*******URL: ***********:   "+ url);
        */
        //final String url = String.format(getEntryPoint()+getRequestType());
        //UrlBuilder urlBuilder = UrlBuilder.fromString(getEntryPoint()+getRequestType());
        long ts = getTimestamp();
        String url = getEntryPoint()+getRequestType()+"?ts="+ts+"&apikey="+getPublicKey()+"&hash="+createHash(ts);
        //urlBuilder.addParameter("ts", String.valueOf(ts)).addParameter("apikey", getPublicKey()).addParameter("hash", createHash(ts));
        UrlBuilder urlBuilder = UrlBuilder.fromString(url);
        System.out.println("***URL: ****************: " + urlBuilder.toString());
        return urlBuilder.toString();
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

    public String getEntryPoint() {
        return entryPoint;
    }

    // HTTP GET request
    public void sendGet() throws Exception {
        String url = getUrl();     //prendo il mio url
        //URL url = new URL("https://www.google.it/search?q=ciao&rlz=1C1AVNE_enIT777IT777&oq=ciao&aqs=chrome..69i57j5j0l4.1002j1j8&sourceid=chrome&ie=UTF-8");     //prendo il mio url
        URL effectiveURL = new URL(url);
        String result;  //gli inserisco il risultato
        MyAsyncTask request = new MyAsyncTask();

        // Esegue il metodo doInBackground, passando il nostro url
        result = (String) request.execute(effectiveURL).get();

        if(result==null){
            System.out.println("C' e' stato un errore nella richiesta GET!");
            return;
        }
        System.out.println("******* RESTREQUEST ***************************");
        System.out.println(result);


    }
}
