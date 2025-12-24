package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package travelPackage;
    
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Integer numberOfPeople;
    private Double totalPrice;
    
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    
    private LocalDateTime bookingDate;
    private String flightNumber;
    private String trackingInfo;
    
    // Constructors
    public Booking() {
        this.bookingDate = LocalDateTime.now();
        this.status = BookingStatus.CONFIRMED;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Package getTravelPackage() { return travelPackage; }
    public void setTravelPackage(Package travelPackage) { this.travelPackage = travelPackage; }
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    
    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    
    public Integer getNumberOfPeople() { return numberOfPeople; }
    public void setNumberOfPeople(Integer numberOfPeople) { this.numberOfPeople = numberOfPeople; }
    
    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
    
    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }
    
    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
    
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    
    public String getTrackingInfo() { return trackingInfo; }
    public void setTrackingInfo(String trackingInfo) { this.trackingInfo = trackingInfo; }
}
