package com.hanium.pay.adapter;

public class ParticipationRequest {
    private String url;
    private Object requestBody;

    public ParticipationRequest(String url, Object requestBody) {
        this.url = url;
        this.requestBody = requestBody;
    }

    public String getUrl() {
        return url;
    }

    public Object getRequestBody() {
        return requestBody;
    }
}