package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Package;
import com.example.demo.model.TransportMode;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    List<Package> findByDuration(Integer duration);
    List<Package> findByDurationBetween(Integer minDuration, Integer maxDuration);
    List<Package> findByTransportMode(TransportMode transportMode);
    List<Package> findByTransportModeAndDurationBetween(TransportMode transportMode, Integer minDuration, Integer maxDuration);
}