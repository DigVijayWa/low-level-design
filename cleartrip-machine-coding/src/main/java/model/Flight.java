package model;

import java.util.List;
import java.util.UUID;
import lombok.ToString;

@ToString
public class Flight {
  private UUID flightId;
  private int flightNumber;
  private String airline;
  private String deptFrom;
  private String deptTo;
  private int availableSeatCount;
  private int deptDate;
  private int deptTime;
  private int arrivalTime;
  private int price;
  private String fareType;
  private List<String> availableSeats;

  public Flight(UUID flightId, int flightNumber, String airline, String deptFrom, String deptTo, int availableSeatCount, int deptDate, int deptTime,
      int arrivalTime, int price, String fareType, List<String> availableSeats) {
    this.deptFrom = deptFrom;
    this.deptTo = deptTo;
    this.availableSeatCount = availableSeatCount;
    this.deptDate = deptDate;
    this.deptTime = deptTime;
    this.arrivalTime = arrivalTime;
    this.price = price;
    this.fareType = fareType;
    this.availableSeats = availableSeats;
    this.flightId = flightId;
    this.flightNumber = flightNumber;
    this.airline = airline;
  }

  public String getDeptFrom() {
    return deptFrom;
  }

  public void setDeptFrom(String deptFrom) {
    this.deptFrom = deptFrom;
  }

  public String getDeptTo() {
    return deptTo;
  }

  public void setDeptTo(String deptTo) {
    this.deptTo = deptTo;
  }

  public int getAvailableSeatCount() {
    return availableSeatCount;
  }

  public void setAvailableSeatCount(int availableSeatCount) {
    this.availableSeatCount = availableSeatCount;
  }

  public int getDeptDate() {
    return deptDate;
  }

  public void setDeptDate(int deptDate) {
    this.deptDate = deptDate;
  }

  public int getDeptTime() {
    return deptTime;
  }

  public void setDeptTime(int deptTime) {
    this.deptTime = deptTime;
  }

  public int getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(int arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getFareType() {
    return fareType;
  }

  public void setFareType(String fareType) {
    this.fareType = fareType;
  }

  public List<String> getAvailableSeats() {
    return availableSeats;
  }

  public void setAvailableSeats(List<String> availableSeats) {
    this.availableSeats = availableSeats;
  }

  public UUID getFlightId() {
    return flightId;
  }

  public void setFlightId(UUID flightId) {
    this.flightId = flightId;
  }

  public int getFlightNumber() {
    return flightNumber;
  }

  public void setFlightNumber(int flightNumber) {
    this.flightNumber = flightNumber;
  }

  public String getAirline() {
    return airline;
  }

  public void setAirline(String airline) {
    this.airline = airline;
  }
}
