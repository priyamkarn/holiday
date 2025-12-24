package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Package;
import com.example.demo.model.TransportMode;
import com.example.demo.service.PackageService;

@RestController
@RequestMapping("/api/packages")
@CrossOrigin(origins = "*")
public class PackageController {
    
    @Autowired
    private PackageService packageService;
    
    @GetMapping
    public ResponseEntity<List<Package>> getAllPackages() {
        return ResponseEntity.ok(packageService.getAllPackages());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Package> getPackageById(@PathVariable Long id) {
        return packageService.getPackageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Package> createPackage(@RequestBody Package pkg) {
        return ResponseEntity.ok(packageService.createPackage(pkg));
    }
    
    // Filter by duration
    @GetMapping("/filter/duration")
    public ResponseEntity<List<Package>> filterByDuration(
            @RequestParam(required = false) Integer minDuration,
            @RequestParam(required = false) Integer maxDuration) {
        return ResponseEntity.ok(packageService.filterByDuration(minDuration, maxDuration));
    }
    
    // Filter by transport mode
    @GetMapping("/filter/transport")
    public ResponseEntity<List<Package>> filterByTransportMode(
            @RequestParam TransportMode transportMode) {
        return ResponseEntity.ok(packageService.filterByTransportMode(transportMode));
    }
    
    // Filter by both transport and duration
    @GetMapping("/filter/combined")
    public ResponseEntity<List<Package>> filterByTransportAndDuration(
            @RequestParam TransportMode transportMode,
            @RequestParam Integer minDuration,
            @RequestParam Integer maxDuration) {
        return ResponseEntity.ok(packageService.filterByTransportAndDuration(
            transportMode, minDuration, maxDuration));
    }
    
    // View cancellation terms
    @GetMapping("/{id}/cancellation-terms")
    public ResponseEntity<String> getCancellationTerms(@PathVariable Long id) {
        String terms = packageService.getCancellationTerms(id);
        if (terms.equals("Package not found")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(terms);
    }
}
