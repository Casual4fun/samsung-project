package com.example.samscurr.apis;

public class CurrencySearchService
{
    Float USD;
    Float RUB;
    Float CNY;
    Float TRY;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    public Float getTRY() {
        return TRY;
    }

    public void setTRY(Float TRY) {
        this.TRY = TRY;
    }

    public Float getUSD() {
        return USD;
    }

    public void setUSD(Float USD) {
        this.USD = USD;
    }

    public Float getRUB() {
        return RUB;
    }

    public void setRUB(Float RUB) {
        this.RUB = RUB;
    }

    public Float getCNY() {
        return CNY;
    }

    public void setCHY(Float CNY) {
        this.CNY = CNY;
    }


    public CurrencySearchService(Float USD, Float RUB, Float CNY, Float TRY, String data) {
        this.USD = USD;
        this.RUB = RUB;
        this.CNY = CNY;
        this.TRY = TRY;
        this.date = date;
    }
}
