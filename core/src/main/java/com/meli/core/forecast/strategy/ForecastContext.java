package com.meli.core.forecast.strategy;

import com.meli.core.solar.system.Planet;
import com.meli.core.solar.system.Weather;

import java.util.List;

public class ForecastContext {
    private ForecastStrategy strategy;

    public void setStrategy(ForecastStrategy strategy) {
        this.strategy = strategy;
    }

    public Weather getForecast(Integer days, List<Planet> planets) {
        return strategy.forecast(days, planets);
    }

}
