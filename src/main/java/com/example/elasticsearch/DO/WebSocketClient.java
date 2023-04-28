package com.example.elasticsearch.DO;

import javax.websocket.Session;

/**
 * webscoket实体类，用来存储url和session
 */
public class WebSocketClient {

    //连接的url
    String url;

    //session
    Session session;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
