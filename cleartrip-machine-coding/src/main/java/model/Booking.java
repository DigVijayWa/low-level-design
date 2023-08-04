package model;

import java.util.List;
import java.util.UUID;
import lombok.ToString;

@ToString
public class Booking {
  private UUID id;
  private User user;
  private Flight flight;
  private List<String> bookedSeats;
  private String fareType;
  private int totalPrice;

  public Booking(UUID id, User user, Flight flight, List<String> bookedSeats,
      String fareType, int totalPrice) {
    this.id = id;
    this.user = user;
    this.flight = flight;
    this.bookedSeats = bookedSeats;
    this.fareType = fareType;
    this.totalPrice = totalPrice;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

  public List<String> getBookedSeats() {
    return bookedSeats;
  }

  public void setBookedSeats(List<String> bookedSeats) {
    this.bookedSeats = bookedSeats;
  }

  public String getFareType() {
    return fareType;
  }

  public void setFareType(String fareType) {
    this.fareType = fareType;
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }
}
