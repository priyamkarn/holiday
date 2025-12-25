package com.example.demo.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Package;
import com.example.demo.model.TransportMode;
import com.example.demo.repository.PackageRepository;

@Service
public class PackageService {
    
    @Autowired
    private PackageRepository packageRepository;
    
    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }
    
    public Optional<Package> getPackageById(Long id) {
        return packageRepository.findById(id);
    }
    
    public Package createPackage(Package pkg) {
        return packageRepository.save(pkg);
    }
    
    public List<Package> filterByDuration(Integer minDuration, Integer maxDuration) {
        if (minDuration != null && maxDuration != null) {
            return packageRepository.findByDurationBetween(minDuration, maxDuration);
        } else if (minDuration != null) {
            return packageRepository.findByDuration(minDuration);
        }
        return packageRepository.findAll();
    }
    
    public List<Package> filterByTransportMode(TransportMode transportMode) {
        return packageRepository.findByTransportMode(transportMode);
    }
    
    public List<Package> filterByTransportAndDuration(TransportMode transportMode, 
                                                      Integer minDuration, 
                                                      Integer maxDuration) {
        return packageRepository.findByTransportModeAndDurationBetween(
            transportMode, minDuration, maxDuration
        );
    }
    
    // Filter by budget
    public List<Package> filterByBudget(Double minPrice, Double maxPrice) {
        if (minPrice != null && maxPrice != null) {
            return packageRepository.findByPriceBetween(minPrice, maxPrice);
        } else if (maxPrice != null) {
            return packageRepository.findByPriceLessThanEqual(maxPrice);
        }
        return packageRepository.findAll();
    }
    
    // Filter by travel dates
    public List<Package> filterByTravelDates(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null) {
            return packageRepository.findByStartDateBetween(startDate, endDate);
        } else if (startDate != null) {
            return packageRepository.findByStartDateAfter(startDate);
        }
        return packageRepository.findAll();
    }
    
    public String getCancellationTerms(Long packageId) {
        Optional<Package> pkg = packageRepository.findById(packageId);
        return pkg.map(Package::getCancellationTerms)
                  .orElse("Package not found");
    }
    
    // Hardcoded refund rules
    public String getRefundRules() {
        return """
               STANDARD REFUND POLICY:
               
               1. Cancellation more than 15 days before departure: 100% refund (minus processing fee of \u20b9500)
               2. Cancellation 8-15 days before departure: 75% refund
               3. Cancellation 4-7 days before departure: 50% refund
               4. Cancellation 1-3 days before departure: 25% refund
               5. Cancellation on departure day or no-show: No refund
               
               Note: Refunds will be processed within 7-10 business days.
               Some packages may have different cancellation policies - please check individual package terms.""";
    }
}