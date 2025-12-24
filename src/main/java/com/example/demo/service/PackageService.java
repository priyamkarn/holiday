package com.example.demo.service;
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
    
    public String getCancellationTerms(Long packageId) {
        Optional<Package> pkg = packageRepository.findById(packageId);
        return pkg.map(Package::getCancellationTerms)
                  .orElse("Package not found");
    }
}