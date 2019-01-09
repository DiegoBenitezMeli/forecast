package com.meli.database.repository;

import com.meli.database.entity.Forecast;
import org.springframework.data.repository.CrudRepository;

public interface ForecastRepository extends CrudRepository<Forecast, Integer> {
}
