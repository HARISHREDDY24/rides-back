package service;

import org.example.rideshare.model.Ride;
import org.example.rideshare.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RideService {
    @Autowired private RideRepository rideRepository;

    public Ride createRide(Ride ride) {
        ride.setStatus("REQUESTED");
        return rideRepository.save(ride);
    }

    public List<Ride> getMyRides(String userId) {
        return rideRepository.findByUserId(userId);
    }

    public List<Ride> getAvailableRides() {
        return rideRepository.findByStatus("REQUESTED");
    }

    public Ride acceptRide(String rideId, String driverId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RuntimeException("Ride not found"));
        if (!"REQUESTED".equals(ride.getStatus())) throw new RuntimeException("Ride already taken");

        ride.setDriverId(driverId);
        ride.setStatus("ACCEPTED");
        return rideRepository.save(ride);
    }

    public Ride completeRide(String rideId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RuntimeException("Ride not found"));
        if (!"ACCEPTED".equals(ride.getStatus())) throw new RuntimeException("Ride not active");

        ride.setStatus("COMPLETED");
        return rideRepository.save(ride);
    }
}