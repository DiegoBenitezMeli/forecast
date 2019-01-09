package com.meli.database.controller;

import com.meli.database.entity.QuantityReport;
import com.meli.database.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sound.sampled.Line;
import java.time.Period;
import java.util.List;

@Controller
public class PlanetController {

    @Autowired
    PlanetRepository planetRepository;

    @Autowired
    EntityManager entityManager;


    public Integer getQuantity() {

      TypedQuery<QuantityReport> query = entityManager.createNamedQuery("quantityReport", QuantityReport.class);
      List<QuantityReport> reports = query.getResultList();
       // Query query = entityManager.createNamedQuery("quantityReport", QuantityReport.class);
       // List report =  query).getResultList();

        return 0;
    }


}
