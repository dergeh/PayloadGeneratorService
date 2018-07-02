package de.tub.model;

public class RichRequest {
    SimpleRequest request;
    Metainfo metaInfo;


    public SimpleRequest getRequest() {
        return request;
    }

    public void setRequest(SimpleRequest request) {
        this.request = request;
    }

    public Metainfo getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(Metainfo metaInfo) {
        this.metaInfo = metaInfo;
    }
}
