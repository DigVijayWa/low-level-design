package service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Booking;
import model.Flight;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storage.Storage;

public class FlightBookingService {

  Logger logger = LoggerFactory.getLogger(FlightBookingService.class.getName());

  public UUID bookFlight(UUID userId, int flightNumber, int deptDate, String fareType,
      List<String> seats) {
    // validations check if user exists or not.
    if (!Storage.userExists(userId)) {
      logger.error("No user with {} id exists in current users", userId);
      return null;
    }

    //filter flights based on the parameters
    List<Flight> filteredFlight = Storage.getAllFlights().stream()
        .filter(flight -> flight.getFlightNumber() == flightNumber &&
            flight.getDeptDate() == deptDate &&
            flight.getFareType().equalsIgnoreCase(fareType) &&
            flight.getAvailableSeats().containsAll(seats)).toList();

    if (filteredFlight.size() == 0) {
      logger.error("No flight exists for the following filter {} {} {} {}", flightNumber, deptDate,
          fareType, seats);
      return null;
    }

    Flight selectedFlight = filteredFlight.stream().findAny().get();
    User user = Storage.getUserWithUUID(userId);

    int totalSeatPrice = selectedFlight.getPrice() * seats.size();

    if (user.getFunds() < totalSeatPrice) {
      logger.error("User {} has insufficient funds. Current Fund {}, required fund {}", user,
          user.getFunds(), totalSeatPrice);
      return null;
    }

    //update available seats
    List<String> updatedAvailableSeats = selectedFlight.getAvailableSeats().stream()
        .filter(s -> !seats.contains(s)).toList();

    selectedFlight.setAvailableSeats(updatedAvailableSeats);
    selectedFlight.setAvailableSeatCount(updatedAvailableSeats.size());

    //update user funds
    user.setFunds(user.getFunds() - totalSeatPrice);


    //update bookings
    final UUID bookingId = UUID.randomUUID();
    Storage.addBookings(new Booking(bookingId, user, selectedFlight, seats, fareType, totalSeatPrice));

    Storage.updateFlight(selectedFlight);
    Storage.updateUser(user);

    return bookingId;
  }

  public boolean cancelFlight(UUID userId, UUID bookingId) {
    final Booking booking = Storage.getBookingWithBookingId(bookingId);

    if(booking == null) {
      logger.error("Booking with {} id does not exists", bookingId);
      return false;
    }

    User user = Storage.getUserWithUUID(userId);

    if(user == null) {
      logger.error("User with {} id does not exists", userId);
      return false;
    }

    Flight bookedFlight = booking.getFlight();
    List<String> availableSeats = new ArrayList<>(bookedFlight.getAvailableSeats());
    int totalPrice = booking.getTotalPrice();

    availableSeats.addAll(booking.getBookedSeats());
    bookedFlight.setAvailableSeats(availableSeats);
    bookedFlight.setAvailableSeatCount(availableSeats.size());

    user.setFunds(user.getFunds() + totalPrice);

    Storage.updateUser(user);
    Storage.updateFlight(bookedFlight);

    Storage.removeBooking(bookingId);
    return true;
  }

  private boolean bookFlight(int flightNumber, int deptDate, String fareType, List<String> seats) {
    return false;
  }
}
