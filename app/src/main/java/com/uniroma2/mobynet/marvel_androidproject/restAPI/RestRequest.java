package com.uniroma2.mobynet.marvel_androidproject.restAPI;

import org.apache.commons.codec.digest.DigestUtils;
import java.net.URL;
import java.util.Objects;
import io.mikael.urlbuilder.UrlBuilder;

/**
 * La classe RestRequest implementa le richieste REST in base al tipo di ricerca
 * definita dall'utente
 */
public class RestRequest {

    /* Attributi */
    private final String entryPoint = "http://gateway.marvel.com/v1/public/";
    private final String privateKey = "614dac0d45ede934f75eaba93f13e1ae5eb1f38f";
    private final String publicKey = "4b4c2ad8ede8e2cfd66baa32cee65dda";

    private String requestType; // "characters" o "creators"
    private String name; // Nome di characters o di creators

    private String url;
    private long timestamp;
    private String result;

    /* Costruttore */
    public RestRequest(String request, String name){
        this.requestType = request;
        this.name = name;
    }

    /**
     *
     * @param ts
     * @return
     */
    private String createHash(long ts) {
        String stringToHash = ts+getPrivateKey()+getPublicKey();
        return DigestUtils.md5Hex(stringToHash);
    }

    // Tale metodo costruisce appositamente un url per la richiesta GET

    /**
     *
     * @return
     */
    public String getUrl() {
        String url;
        long ts = getTimestamp();
        if(getName() == null) {
            url = getEntryPoint()+getRequestType()+ "?ts="+ts+"&apikey="+getPublicKey()+"&hash="+createHash(ts);
        } else {
            if (Objects.equals(getRequestType(), "characters")) {
                url = getEntryPoint() + getRequestType() + "?nameStartsWith=" + getName() +
                        "&orderBy=name&ts=" + ts + "&apikey=" + getPublicKey() + "&hash=" + createHash(ts);
            } else {
                url = getEntryPoint() + getRequestType() + "?nameStartsWith=" + getName() +
                        "&orderBy=lastName&ts=" + ts + "&apikey=" + getPublicKey() + "&hash=" + createHash(ts);
            }
        }
        UrlBuilder urlBuilder = UrlBuilder.fromString(url);
        this.url = urlBuilder.toString();
        return this.url;
    }

    // Il timestamp viene ottenuto come secondi correnti, in modo da essere diverso ad ogni richiesta

    /**
     *
     * @return
     */
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
    public String getName() {
        return name;
    }
    public String getEntryPoint() {
        return entryPoint;
    }
    public String getResult() {
        return result;
    }

    // Tale metodo invia la richiesta GET effettiva e ottiene il risultato come file JSon

    /**
     *
     * @throws Exception
     */
    public void sendGet() throws Exception {
        // Si prende l'url per la richiesta
        String url = getUrl();
        URL effectiveURL = new URL(url);
        String result;  // Variabile che conterra' il risultato
        MyAsyncTask request = new MyAsyncTask();

        // Esecuzione del metodo doInBackground, tramite l'url
        result = (String) request.execute(effectiveURL).get();

        if(result==null){
            System.out.println("Errore nella richiesta GET!");
            return;
        }
        this.result = result;
    }
}