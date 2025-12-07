package repository;

import org.example.rideshare.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RideRepository extends MongoRepository<Ride, String> {
    // Find all rides that are just "REQUESTED" (for drivers to see)
    List<Ride> findByStatus(String status);

    // Find all rides belonging to a specific passenger
    List<Ride> findByUserId(String userId);
}