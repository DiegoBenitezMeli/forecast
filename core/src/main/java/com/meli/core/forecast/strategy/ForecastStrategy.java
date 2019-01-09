package com.meli.core.forecast.strategy;

import com.meli.core.solar.system.Planet;
import com.meli.core.solar.system.Weather;

import java.util.List;

public interface ForecastStrategy {
    public Weather forecast(Integer days, List<Planet> planets);
}
