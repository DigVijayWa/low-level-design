package service;

import java.util.List;
import java.util.stream.Collectors;
import model.Flight;
import storage.Storage;

public class FlightSearchService {

  public List<Flight> searchFlight(String from, String to, int deptDate, int seatCount) {
    return Storage.getAllFlights().stream()
        .filter(flight -> flight.getDeptFrom().equalsIgnoreCase(from) &&
            flight.getDeptTo().equalsIgnoreCase(to) &&
            flight.getDeptDate() == deptDate &&
            flight.getAvailableSeatCount() >= seatCount
        )
        .collect(Collectors.toList());
  }
}
