package service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import model.Booking;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storage.Storage;

public class UserService {

  Logger logger = LoggerFactory.getLogger(FlightBookingService.class.getName());

  public boolean addUser(UUID userId, String name, int funds) {
    final boolean result = Storage.addUser(new User(userId, name, funds));

    if (!result) {
      logger.error("User already exists with {} id", userId);
    }
    return result;
  }

  public List<Booking> getUserBooking(UUID userId) {
    if (!Storage.userExists(userId)) {
      logger.error("User does not exist with {} id", userId);
      return Collections.emptyList();
    }

    return Storage.getAllBookings().stream()
        .filter(booking -> booking.getUser().getId().equals(userId)).toList();
  }
}
