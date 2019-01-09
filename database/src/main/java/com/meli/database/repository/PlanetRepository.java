package com.meli.database.repository;

import com.meli.database.entity.Planet;
import org.springframework.data.repository.CrudRepository;

public interface PlanetRepository extends CrudRepository<Planet, Integer> {
}
