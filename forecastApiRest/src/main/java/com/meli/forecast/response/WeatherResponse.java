package com.meli.forecast.response;

public class WeatherResponse {


    public final Integer dia;
    public final String clima;

    public WeatherResponse(final Integer dia, final String clima) {
        this.dia = dia;
        this.clima = clima;
    }

    public Integer getDia() {
        return dia;
    }

    public String getClima() {
        return clima;
    }
}
