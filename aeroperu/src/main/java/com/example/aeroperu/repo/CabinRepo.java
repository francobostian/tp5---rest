package com.example.aeroperu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esq.models.Cabin;

@Repository
public interface CabinRepo extends JpaRepository<Cabin, Long> {

    @Query(value = "SELECT * FROM cabina c WHERE c.nombre = :tipo", nativeQuery = true, name = "getAttribute")
    public Cabin getAttribute(@Param("tipo") String value);
}