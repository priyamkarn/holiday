package com.example.demo.service;
import com.example.demo.model.Booking;
import com.example.demo.model.BookingStatus;
import com.example.demo.model.Package;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private PackageRepository packageRepository;
    
    public Booking createBooking(Booking booking) {
        if (booking.getTravelPackage() != null && booking.getTravelPackage().getId() != null) {
            // Fetch the complete package from database
            Optional<Package> packageOpt = packageRepository.findById(booking.getTravelPackage().getId());
            if (packageOpt.isPresent()) {
                Package fullPackage = packageOpt.get();
                booking.setTravelPackage(fullPackage);
                Double totalPrice = fullPackage.getPrice() * booking.getNumberOfPeople();
                booking.setTotalPrice(totalPrice);
            } else {
                throw new RuntimeException("Package not found with ID: " + booking.getTravelPackage().getId());
            }
        }
        return bookingRepository.save(booking);
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
    
    public List<Booking> getBookingsByEmail(String email) {
        return bookingRepository.findByCustomerEmail(email);
    }
    
    public Optional<Booking> trackFlightBooking(String flightNumber) {
        return bookingRepository.findByFlightNumber(flightNumber);
    }
    
    public Booking updateTrackingInfo(Long bookingId, String trackingInfo) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            Booking b = booking.get();
            b.setTrackingInfo(trackingInfo);
            return bookingRepository.save(b);
        }
        return null;
    }
    
    public Booking cancelBooking(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            Booking b = booking.get();
            b.setStatus(BookingStatus.CANCELLED);
            return bookingRepository.save(b);
        }
        return null;
    }
}