package com.sandragarciagata.nasaapodapi.repository;

import com.sandragarciagata.nasaapodapi.model.ApodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApodRepository extends JpaRepository<ApodEntity, Long> {

    // Método  con consulta JPQL para el endpoint de archivo (filtra por año y por mes)
    @Query("SELECT a FROM ApodEntity a WHERE YEAR(a.retrievedAt) = :year AND MONTH(a.retrievedAt) = :month")
    List<ApodEntity> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
}
