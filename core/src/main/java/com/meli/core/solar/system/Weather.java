package com.meli.core.solar.system;

import com.meli.core.forecast.strategy.MeliForecastStrategy;

public class Weather {

    public Weather(final Integer day) {
        period = MeliForecastStrategy.Period.INVALIDO;
        this.day = day;
    }

    private MeliForecastStrategy.Period period;

    private Double perimeter;

    private Integer day;

    public MeliForecastStrategy.Period getPeriod() {
        return period;
    }

    public void setPeriod(MeliForecastStrategy.Period period) {
        this.period = period;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "period=" + period +
                ", perimeter=" + perimeter +
                ", Day=" + day +
                '}';
    }
}
