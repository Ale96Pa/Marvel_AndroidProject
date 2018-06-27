package com.uniroma2.mobynet.marvel_androidproject;

import org.apache.commons.codec.digest.DigestUtils;
import java.net.URL;
import io.mikael.urlbuilder.UrlBuilder;

public class RestRequest {
    /* Attributi */
    private String url;
    private final String entryPoint = "http://gateway.marvel.com/v1/public/";
    private long timestamp;
    private final String privateKey = "614dac0d45ede934f75eaba93f13e1ae5eb1f38f";
    private final String publicKey = "4b4c2ad8ede8e2cfd66baa32cee65dda";
    private String requestType; //characters o creators
    private String result;

    /* Costruttore */
    public RestRequest(String request){
        this.requestType = request;
    }

    /* Metodi */
    private String createHash(long ts) {
        String stringToHash = ts+getPrivateKey()+getPublicKey();
        String hash = DigestUtils.md5Hex(stringToHash);
        //System.out.println("******* HASH: ******: " + hash);
        return hash;
    }
    public String getUrl() {
        long ts = getTimestamp();
        String url = getEntryPoint()+getRequestType()+"?ts="+ts+"&apikey="+getPublicKey()+"&hash="+createHash(ts);
        UrlBuilder urlBuilder = UrlBuilder.fromString(url);
        //System.out.println("***URL: ****************: " + urlBuilder.toString());
        //this.url = urlBuilder.toString();
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

    public String getResult() {
        return result;
    }

    // HTTP GET request
    public void sendGet() throws Exception {
        String url = getUrl();     //prendo il mio url
        URL effectiveURL = new URL(url);
        String result;  //gli inserisco il risultato
        MyAsyncTask request = new MyAsyncTask();

        // Esegue il metodo doInBackground, passando il nostro url
        result = (String) request.execute(effectiveURL).get();

        if(result==null){
            System.out.println("C' e' stato un errore nella richiesta GET!");
            return;

        }
        this.result = result;

    }


}
