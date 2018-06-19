package com.example.aeroperu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esq.models.Route;

@Repository
public interface RouteRepo extends JpaRepository<Route, Long> {

    // @Query(value = "SELECT * " + "FROM ruta r " + "INNER JOIN aeropuerto
    // air_Begin ON r.origen_id = air_Begin.id "
    // + "INNER JOIN aeropuerto air_End ON r.destino_id = air_End.id "
    // + "WHERE air_Begin.iata = :iataAirportBegin AND air_End.iata =
    // :iataAirportEnd", nativeQuery = true, name = "getAttributeByAirports")
    // Optional<Route> getAttributeByAirports(@Param("iataAirportBegin") String
    // iataAirporBegin,
    // @Param("iataAirportEnd") String iataAirportEnd);
    //
    // @Query(value = "SELECT r.* " + "FROM ruta r INNER JOIN aeropuerto a ON
    // r.origen_id = a.id "
    // + "WHERE a.iata LIKE :iataAirportBegin", nativeQuery = true, name =
    // "getByInitAirport")
    // List<Route> getByInitAirport(@Param("iataAirportBegin") String
    // iataAirportBegin);
}