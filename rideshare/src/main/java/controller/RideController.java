package controller;

@RestController
@RequestMapping("/api/v1")
public class RideController {
    @Autowired private RideService rideService;
    @Autowired private UserRepository userRepository;

    // Helper to get current user ID from SecurityContext (simplified)
    private User getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow();
    }

    // 1. Create Ride (Passenger)
    @PostMapping("/rides")
    public Ride requestRide(@RequestBody @Valid RideRequest request, Authentication auth) {
        User user = getCurrentUser(auth);
        Ride ride = new Ride();
        ride.setUserId(user.getId());
        ride.setPickupLocation(request.getPickupLocation());
        ride.setDropLocation(request.getDropLocation());
        return rideService.createRide(ride);
    }

    // 2. View My Rides (Passenger)
    @GetMapping("/user/rides")
    public List<Ride> getMyRides(Authentication auth) {
        User user = getCurrentUser(auth);
        return rideService.getMyRides(user.getId());
    }

    // 3. View Pending Requests (Driver)
    @GetMapping("/driver/rides/requests")
    public List<Ride> getPendingRides() {
        return rideService.getAvailableRides();
    }

    // 4. Accept Ride (Driver)
    @PostMapping("/driver/rides/{id}/accept")
    public Ride acceptRide(@PathVariable String id, Authentication auth) {
        User driver = getCurrentUser(auth);
        // Check if role is DRIVER if not checked by SecurityConfig
        return rideService.acceptRide(id, driver.getId());
    }

    // 5. Complete Ride
    @PostMapping("/rides/{id}/complete")
    public Ride completeRide(@PathVariable String id) {
        return rideService.completeRide(id);
    }
}