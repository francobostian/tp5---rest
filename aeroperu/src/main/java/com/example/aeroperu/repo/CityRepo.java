package com.example.aeroperu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esq.models.City;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {

    @Query(value = "SELECT * FROM ciudad cs WHERE cs.iata = :iataCode", nativeQuery = true, name = "getAttribute")
    public City getAttribute(@Param("iataCode") String iataCode);
}