package com.example.aeroperu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esq.models.Airport;

@Repository
public interface AirportRepo extends JpaRepository<Airport, Long> {

    @Query(value = "SELECT * FROM aeropuerto a WHERE a.iata = :iataCode", nativeQuery = true)
    public Airport getAttribute(@Param("iataCode") String iata);
}