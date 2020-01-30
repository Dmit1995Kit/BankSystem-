package com.yakovlev.BankSystem.utiles;

public class JsonRequest {
    private String stringParam;
    private Long longParam;

    public JsonRequest() {
    }

    public String getStringParam() {
        return stringParam;
    }

    public void setStringParam(String stringParam) {
        this.stringParam = stringParam;
    }

    public Long getLongParam() {
        return longParam;
    }

    public void setLongParam(Long longParam) {
        this.longParam = longParam;
    }
}
