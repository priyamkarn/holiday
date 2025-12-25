package com.example.demo.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Package;
import com.example.demo.model.TransportMode;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    List<Package> findByDuration(Integer duration);
    List<Package> findByDurationBetween(Integer minDuration, Integer maxDuration);
    List<Package> findByTransportMode(TransportMode transportMode);
    List<Package> findByTransportModeAndDurationBetween(TransportMode transportMode, Integer minDuration, Integer maxDuration);
    
    // Filter by price/budget
    List<Package> findByPriceLessThanEqual(Double maxPrice);
    List<Package> findByPriceBetween(Double minPrice, Double maxPrice);
    
    // Filter by travel dates
    List<Package> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    List<Package> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT p FROM Package p WHERE p.startDate >= :startDate")
    List<Package> findByStartDateAfter(@Param("startDate") LocalDate startDate);
}