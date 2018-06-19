package com.example.aeroperu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esq.models.Route;

@Repository
public interface RouteRepo extends JpaRepository<Route, Long> {

}