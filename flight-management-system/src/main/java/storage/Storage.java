package storage;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import model.Booking;
import model.Flight;
import model.User;

public class Storage {
    private static Map<UUID, User> currentUsersMap = new ConcurrentHashMap<>();
    private static Map<UUID, Flight> currentFlightsMap= new ConcurrentHashMap<>();
    private static Map<UUID, Booking> currentBookingsMap= new ConcurrentHashMap<>();


    public static List<User> getAllUsers() {
        return currentUsersMap.values().stream().toList();
    }

    public static List<Flight> getAllFlights() {
        return currentFlightsMap.values().stream().toList();
    }

    public static boolean updateFlight(Flight flight) {
        if(!currentFlightsMap.containsKey(flight.getFlightId())) {
            return false;
        }
        currentFlightsMap.put(flight.getFlightId(), flight);
        return true;
    }

    public static boolean updateUser(User user) {
        if(!currentUsersMap.containsKey(user.getId())) {
            return false;
        }
        currentUsersMap.put(user.getId(), user);
        return true;
    }

    public static boolean userExists(UUID uuid) {
        return currentUsersMap.containsKey(uuid);
    }

    public static boolean addUser(User user) {
        if(currentUsersMap.containsKey(user.getId())) {
            return false;
        }

        currentUsersMap.put(user.getId(), user);
        return true;
    }

    public static User getUserWithUUID(UUID uuid) {
        return currentUsersMap.get(uuid);
    }

    public static boolean addBookings(Booking booking) {
        if(currentBookingsMap.containsKey(booking.getId())) {
            return false;
        }
        currentBookingsMap.put(booking.getId(), booking);

        return true;
    }

    public static List<Booking> getAllBookings() {
        return currentBookingsMap.values().stream().toList();
    }

    public static Booking getBookingWithBookingId(UUID bookingId) {
        return currentBookingsMap.get(bookingId);
    }

    public static boolean removeBooking(UUID bookingId) {
        if(!currentBookingsMap.containsKey(bookingId)) {
            return false;
        }
        currentBookingsMap.remove(bookingId);
        return true;
    }

    public static boolean addFlight(Flight flight) {
        if(currentFlightsMap.containsKey(flight.getFlightId())) {
            return false;
        }
        currentFlightsMap.put(flight.getFlightId(), flight);
        return true;
    }
}
