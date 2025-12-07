package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.Date;

@Data
@Document(collection = "rides")
public class Ride {
    @Id
    private String id;
    private String userId;      // Passenger
    private String driverId;    // Driver (Initially null)
    private String pickupLocation;
    private String dropLocation;
    private String status;      // REQUESTED, ACCEPTED, COMPLETED
    private Date createdAt = new Date();
}