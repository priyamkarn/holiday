package com.example.demo.model;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String destination;
    private Integer duration; // in days
    private Double price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    
    @Enumerated(EnumType.STRING)
    private TransportMode transportMode;
    
    private String cancellationTerms;
    
    // Constructors
    public Package() {}
    
    public Package(String name, String destination, Integer duration, Double price, 
                   LocalDate startDate, LocalDate endDate, TransportMode transportMode, 
                   String cancellationTerms, String description) {
        this.name = name;
        this.destination = destination;
        this.duration = duration;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.transportMode = transportMode;
        this.cancellationTerms = cancellationTerms;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    
    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public TransportMode getTransportMode() { return transportMode; }
    public void setTransportMode(TransportMode transportMode) { this.transportMode = transportMode; }
    
    public String getCancellationTerms() { return cancellationTerms; }
    public void setCancellationTerms(String cancellationTerms) { this.cancellationTerms = cancellationTerms; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}