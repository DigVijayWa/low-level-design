import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import model.Flight;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.FlightBookingService;
import service.FlightSearchService;
import storage.Storage;

public class Main {

  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(Main.class.getName());

    String inputData = "123 6E DEL BLR 3 2 10 11 2000 F1 1b,2c,4e\n"
        + "156 6E DEL BLR 1 2 14 15 4000 F2 4e\n"
        + "234 6E DEL HYD 4 2 15 16 1000 F3 29a,40e,1e,4e\n"
        + "456 6E AMD CCU 10 2 18 22 10000 F5 7c,7d,12c,5f,8e,7e,4d,3e,4a,10a\n"
        + "987 SJ DEL BLR 6 2 11 16 2500 F11 12c,5f,4d,3e,4a,10a\n"
        + "1001 SJ DEL HYD 5 2 9 12 2300 F1 8e,7e,4d,6a,15a\n"
        + "890 SJ DEL AMD 7 2 12 18 2100 F2 45e,30a,1e,4e,7c,7d,12c\n"
        + "999 SJ DEL HYD 12 2 8 12 2900 F1d 1e,4e,7c,7d,12c,5f,8e,7e,4d,3e,4a,10a\n"
        + "432 UK DEL BLR 8 2 16 18 2000 F1x 5a,10b,34e,20c,20a,8b,8a,9b\n"
        + "444 UK BLR DEL 2 2 11 13 4300 F1d 12a,13b\n"
        + "456 UK DEL BLR 7 2 19 21 2800 F1 5a,10b,34e,20c,3e,4a,10a\n"
        + "654 AI DEL BLR 4 2 21 23 3600 F1 41a,44b,44c,12d\n"
        + "236 AI DEL BLR 10 2 17 19 6700 F1 20c,20a,8b,8a,9b,10c,21a,18b,18a,19b\n"
        + "980 QP DEL BLR 4 2 13 14 1500 F1 1a,1b,1c,1d\n"
        + "875 QP DEL BLR 4 2 10 12 2800 F1 4a,1b,11c,21d\n"
        + "989 QP DEL BLR 4 2 19 21 3400 F1 34a,41c,11c,7d\n"
        + "998 QP DEL BLR 4 2 18 22 4500 F1 34a,41c,11c,7d\n"
        + "123 6E DEL BLR 2 2 10 11 2100 F11 3b,12c\n"
        + "156 6E DEL BLR 1 2 14 15 4300 F21 41e\n"
        + "234 6E DEL HYD 4 2 15 16 2000 Fx3 28a,12e,14e,14e\n"
        + "456 6E AMD CCU 5 2 18 22 4000 F5x 17c,17d,15c,51f,81e\n"
        + "987 SJ DEL BLR 2 2 11 16 1500 F1 2c,15f\n"
        + "1001 SJ DEL HYD 5 2 9 12 2600 F2 18e,17e,14d,26a,5a\n"
        + "890 SJ DEL AMD 9 2 12 18 2400 F3 41e,3a,11e,14e,27c,17d,22c,22d,23e\n"
        + "999 SJ DEL HYD 12 2 8 12 2700 Fd 11e,14e,17c,17d,22c,15f,28e,17e,14d,23e,34a,12a\n"
        + "236 AI DEL BLR 10 2 17 19 6500 Fe 21c,10a,18b,28a,19b,12c,22a,28b,38a,29b\n"
        + "980 QP DEL BLR 4 2 13 14 2500 F2 12a,11b,21c,12d\n"
        + "875 QP DEL BLR 4 2 10 12 2800 F11 24a,11b,12c,22d\n"
        + "989 QP DEL BLR 3 2 19 21 2400 F12 4a,4c,12c\n"
        + "998 QP DEL BLR 2 2 18 22 1500 F1e 34a,41c";

    String[] inputs = inputData.split("\n");

    for (String input : inputs) {
      String[] spaceSeparated = input.split(" ");
      int flightNumber = Integer.parseInt(spaceSeparated[0]);
      String airline = spaceSeparated[1];
      String from = spaceSeparated[2];
      String to = spaceSeparated[3];
      int availableCount = Integer.parseInt(spaceSeparated[4]);
      int deptDate = Integer.parseInt(spaceSeparated[5]);
      int deptTime = Integer.parseInt(spaceSeparated[6]);
      int arrTime = Integer.parseInt(spaceSeparated[7]);
      int price = Integer.parseInt(spaceSeparated[8]);
      String fareType = spaceSeparated[9];
      List<String> seats = Arrays.stream(spaceSeparated[10].split(",")).toList();

      Storage.addFlight(
          new Flight(UUID.randomUUID(), flightNumber, airline, from, to, availableCount, deptDate,
              deptTime, arrTime, price, fareType, seats));
    }
    //Add user
    UUID user1 = UUID.randomUUID();
    UUID user2 = UUID.randomUUID();

    Storage.addUser(new User(user1, "vinit", 5000));
    Storage.addUser(new User(user2, "neha", 1500));

    FlightSearchService flightSearchService = new FlightSearchService();
    FlightBookingService flightBookingService = new FlightBookingService();

    List<Flight> search1 = flightSearchService.searchFlight("DEL", "BLR", 2, 1);

    logger.info("Search results for search 1:  {}",search1);

    List<Flight> search2 = flightSearchService.searchFlight("DEL", "BLR", 2, 2);

    logger.info("Search results for search 2:  {}",search2);

    List<Flight> search3 = flightSearchService.searchFlight("DEL", "HYD", 2, 22);

    logger.info("Search results for search 3:  {}",search3);

    UUID booking1 = flightBookingService.bookFlight(user1, 123, 2, "F1", List.of("1b"));

    if(booking1 == null) {
      logger.error("Booking failed");
    } else {
      logger.info("Booking successful {}", Storage.getBookingWithBookingId(booking1));
    }

    UUID booking2 = flightBookingService.bookFlight(user1, 211, 2, "F2", List.of("10a", "11c", "20b"));

    if(booking2 == null) {
      logger.error("Booking failed");
    } else {
      logger.info("Booking successful {}", Storage.getBookingWithBookingId(booking2));
    }

    UUID booking3 = flightBookingService.bookFlight(user2, 141, 2, "F4", List.of("32e"));

    if(booking3 == null) {
      logger.error("Booking failed");
    } else {
      logger.info("Booking successful {}", Storage.getBookingWithBookingId(booking3));
    }

    boolean result = flightBookingService.cancelFlight(user1, booking1);

    if(result) {
      logger.info("Booking cancellation is successful");
    }
  }
}
