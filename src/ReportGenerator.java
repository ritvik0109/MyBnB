import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ReportGenerator {
  private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  public static void handleRunReport(Scanner scanner) {
    scanner.nextLine();
    return;
  }

  // 1. Total number of bookings in a specific date range by city
  public static void handleTotalNumBookingsPerCity(Scanner scanner) {
    scanner.nextLine();
    LocalDate startDate = handleInputStartDate(scanner);
    LocalDate endDate = handleInputEndDate(scanner, startDate);
    String city = handleInputWord(scanner, "city");

    String sql = "SELECT city, COUNT(*) AS total_bookings " +
        "FROM Bookings " +
        "JOIN Listings ON Bookings.list_id = Listings.list_id " +
        "WHERE start_date >= ? AND end_date <= ? AND city = ? " +
        "GROUP BY city";

    try (ResultSet resultSet = SQL.executeQuery(sql, startDate, endDate, city)) {
      if (resultSet != null) {
        System.out.println("\nTotal Bookings per City:");
        System.out.println("+---------------+----------------+");
        System.out.println("| City          | Total Bookings |");
        System.out.println("+---------------+----------------+");
        while (resultSet.next()) {
          String cityName = resultSet.getString("city");
          int totalBookings = resultSet.getInt("total_bookings");
          System.out.printf("| %-13s | %-14d |\n", cityName, totalBookings);
        }
        System.out.println("+---------------+----------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 2. Total number of bookings in a specific date range by postal code / zip
  // code within a city.
  public static void handleTotalNumBookingsPerPostalCode(Scanner scanner) {
    scanner.nextLine();
    LocalDate startDate = handleInputStartDate(scanner);
    LocalDate endDate = handleInputEndDate(scanner, startDate);
    String city = handleInputWord(scanner, "city");
    int postalCode = handleInputPostalCode(scanner, "postal code");

    String sql = "SELECT postal_code, COUNT(*) AS total_bookings " +
        "FROM Bookings " +
        "JOIN Listings ON Bookings.list_id = Listings.list_id " +
        "WHERE start_date >= ? AND end_date <= ? AND city = ? AND postal_code = ? " +
        "GROUP BY postal_code";

    try (ResultSet resultSet = SQL.executeQuery(sql, startDate, endDate, city, postalCode)) {
      if (resultSet != null) {
        System.out.println("\nTotal Bookings per Postal Code:");
        System.out.println("+---------------+----------------+");
        System.out.println("| Postal Code   | Total Bookings |");
        System.out.println("+---------------+----------------+");
        while (resultSet.next()) {
          int postal = resultSet.getInt("postal_code");
          int totalBookings = resultSet.getInt("total_bookings");
          System.out.printf("| %-13d | %-14d |\n", postal, totalBookings);
        }
        System.out.println("+---------------+----------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 3. Total number of listings per country
  public static void handleTotalNumListingsPerCountry(Scanner scanner) {
    scanner.nextLine();
    String country = handleInputWord(scanner, "country");

    String sql = "SELECT country, COUNT(*) AS total_listings " +
        "FROM Listings " +
        "WHERE country = ? " +
        "GROUP BY country";

    try (ResultSet resultSet = SQL.executeQuery(sql, country)) {
      if (resultSet != null) {
        System.out.println("\nTotal Listings for Country:");
        System.out.println("+-----------------+----------------+");
        System.out.println("| Country         | Total Listings |");
        System.out.println("+-----------------+----------------+");
        while (resultSet.next()) {
          String countryName = resultSet.getString("country");
          int totalListings = resultSet.getInt("total_listings");
          System.out.printf("| %-15s | %-14d |\n", countryName, totalListings);
        }
        System.out.println("+-----------------+----------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 4. Total number of listings per city and country.
  public static void handleTotalNumListingsPeCountryCity(Scanner scanner) {
    scanner.nextLine();
    String country = handleInputWord(scanner, "country");
    String city = handleInputWord(scanner, "city");

    String sql = "SELECT city, country, COUNT(*) AS total_listings " +
        "FROM Listings " +
        "WHERE country = ? AND city = ? " +
        "GROUP BY city, country";

    try (ResultSet resultSet = SQL.executeQuery(sql, country, city)) {
      if (resultSet != null) {
        System.out.println("\nTotal Listings for City and Country:");
        System.out.println("+-----------------+----------------+----------------+");
        System.out.println("| Country         | City           | Total Listings |");
        System.out.println("+-----------------+----------------+----------------+");
        while (resultSet.next()) {
          String countryName = resultSet.getString("country");
          String cityName = resultSet.getString("city");
          int totalListings = resultSet.getInt("total_listings");
          System.out.printf("| %-15s | %-14s | %-14d |\n", countryName, cityName, totalListings);
        }
        System.out.println("+-----------------+----------------+----------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 5. Total number of listings per country, city, postal code.
  public static void handleTotalNumListingsPerCountryCityPostal(Scanner scanner) {
    scanner.nextLine();
    String country = handleInputWord(scanner, "country");
    String city = handleInputWord(scanner, "city");
    int postalCode = handleInputPostalCode(scanner, "postal code");

    String sql = "SELECT country, city, postal_code, COUNT(*) AS total_listings " +
        "FROM Listings " +
        "WHERE country = ? AND city = ? AND postal_code = ? " +
        "GROUP BY country, city, postal_code";

    try (ResultSet resultSet = SQL.executeQuery(sql, country, city, postalCode)) {
      if (resultSet != null) {
        System.out.println("\nTotal Listings for Country, City, and Postal Code:");
        System.out.println("+-----------------+----------------+--------------+----------------+");
        System.out.println("| Country         | City           | Postal Code  | Total Listings |");
        System.out.println("+-----------------+----------------+--------------+----------------+");
        while (resultSet.next()) {
          String countryName = resultSet.getString("country");
          String cityName = resultSet.getString("city");
          int postalCodeResult = resultSet.getInt("postal_code");
          int totalListings = resultSet.getInt("total_listings");
          System.out.printf("| %-15s | %-14s | %-12d | %-14d |\n",
              countryName, cityName, postalCodeResult, totalListings);
        }
        System.out.println("+-----------------+----------------+--------------+----------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 6. Rank hosts by total number of listings in a country.
  public static void handleRankHostsByListingsCountry(Scanner scanner) {
    scanner.nextLine();
    String country = handleInputWord(scanner, "country");

    String sql = "SELECT l.country, u.user_id AS host_id, u.name AS host_name, COUNT(l.user_id) AS total_listings " +
        "FROM Users u " +
        "JOIN Listings l ON u.user_id = l.user_id " +
        "WHERE l.country = ? " +
        "GROUP BY l.country, u.user_id, host_name " +
        "ORDER BY total_listings DESC";

    try (ResultSet resultSet = SQL.executeQuery(sql, country)) {
      if (resultSet != null) {
        System.out.println("\nRank of Hosts by Total Listings in " + country + ":");
        System.out.println("+-----------------+-----------------+------------------+----------------+");
        System.out.println("| Country         | UserID (Host)   | Host Name        | Total Listings |");
        System.out.println("+-----------------+-----------------+------------------+----------------+");
        while (resultSet.next()) {
          String countryName = resultSet.getString("country");
          int hostId = resultSet.getInt("host_id");
          String hostName = resultSet.getString("host_name");
          int totalListings = resultSet.getInt("total_listings");
          System.out.printf("| %-15s | %-15d | %-16s | %-14d |\n",
              countryName, hostId, hostName, totalListings);
        }
        System.out.println("+-----------------+-----------------+------------------+----------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 7. Rank hosts by total number of listings in a country and city.
  public static void handleRankHostsByListingsCountryCity(Scanner scanner) {
    scanner.nextLine();
    String country = handleInputWord(scanner, "country");
    String city = handleInputWord(scanner, "city");

    String sql = "SELECT l.country, l.city, u.user_id AS host_id, u.name AS host_name, COUNT(l.user_id) AS total_listings "
        +
        "FROM Users u " +
        "JOIN Listings l ON u.user_id = l.user_id " +
        "WHERE l.country = ? AND l.city = ? " +
        "GROUP BY l.country, l.city, u.user_id, host_name " +
        "ORDER BY total_listings DESC";

    try (ResultSet resultSet = SQL.executeQuery(sql, country, city)) {
      if (resultSet != null) {
        System.out.println("\nRank of Hosts by Total Listings in " + city + ", " + country + ":");
        System.out
            .println("+-----------------+-----------------+-----------------+------------------+----------------+");
        System.out
            .println("| Country         | City            | UserID (Host)   | Host Name        | Total Listings |");
        System.out
            .println("+-----------------+-----------------+-----------------+------------------+----------------+");
        while (resultSet.next()) {
          String countryName = resultSet.getString("country");
          String cityName = resultSet.getString("city");
          int hostId = resultSet.getInt("host_id");
          String hostName = resultSet.getString("host_name");
          int totalListings = resultSet.getInt("total_listings");
          System.out.printf("| %-15s | %-15s | %-15d | %-16s | %-14d |\n",
              countryName, cityName, hostId, hostName, totalListings);
        }
        System.out
            .println("+-----------------+-----------------+-----------------+------------------+----------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 8. List the hosts that have > 10% of listings in a country
  public static void handleListHosts10PercentCountry(Scanner scanner) {
    scanner.nextLine();
    String country = handleInputWord(scanner, "country");

    String sql = "SELECT l.country, u.user_id AS host_id, u.name AS host_name, " +
        "COUNT(l.user_id) AS total_listings, " +
        "(COUNT(l.user_id) / (SELECT COUNT(*) FROM Listings WHERE country = ?)) * 100 AS percentage " +
        "FROM Users u " +
        "JOIN Listings l ON u.user_id = l.user_id " +
        "WHERE l.country = ? " +
        "GROUP BY l.country, u.user_id, host_name " +
        "HAVING percentage > 10 " +
        "ORDER BY total_listings DESC";

    try (ResultSet resultSet = SQL.executeQuery(sql, country, country)) {
      if (resultSet != null) {
        System.out.println("\nHosts with > 10% of Listings in " + country + ":");
        System.out.println("+-----------------+-----------------+------------------+----------------+------------+");
        System.out.println("| Country         | UserID (Host)   | Host Name        | Total Listings | Percentage |");
        System.out.println("+-----------------+-----------------+------------------+----------------+------------+");
        while (resultSet.next()) {
          String countryName = resultSet.getString("country");
          int hostId = resultSet.getInt("host_id");
          String hostName = resultSet.getString("host_name");
          int totalListings = resultSet.getInt("total_listings");
          double percentage = resultSet.getDouble("percentage");
          System.out.printf("| %-15s | %-15d | %-16s | %-14d | %-10.2f |\n",
              countryName, hostId, hostName, totalListings, percentage);
        }
        System.out.println("+-----------------+-----------------+------------------+----------------+------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 9. List the hosts that have > 10% of listings in a city, country
  public static void handleListHosts10PercentCountryCity(Scanner scanner) {
    scanner.nextLine();
    String country = handleInputWord(scanner, "country");
    String city = handleInputWord(scanner, "city");

    String sql = "SELECT l.country, l.city, u.user_id AS host_id, u.name AS host_name, " +
        "COUNT(l.user_id) AS total_listings, " +
        "(COUNT(l.user_id) / (SELECT COUNT(*) FROM Listings WHERE country = ? AND city = ?)) * 100 AS percentage " +
        "FROM Users u " +
        "JOIN Listings l ON u.user_id = l.user_id " +
        "WHERE l.country = ? AND l.city = ? " +
        "GROUP BY l.country, l.city, u.user_id, host_name " +
        "HAVING percentage > 10 " +
        "ORDER BY total_listings DESC";

    try (ResultSet resultSet = SQL.executeQuery(sql, country, city, country, city)) {
      if (resultSet != null) {
        System.out.println("\nHosts with > 10% of Listings in " + city + ", " + country + ":");
        System.out.println(
            "+-----------------+-----------------+-----------------+------------------+----------------+------------+");
        System.out.println(
            "| Country         | City            | UserID (Host)   | Host Name        | Total Listings | Percentage |");
        System.out.println(
            "+-----------------+-----------------+-----------------+------------------+----------------+------------+");
        while (resultSet.next()) {
          String countryName = resultSet.getString("country");
          String cityName = resultSet.getString("city");
          int hostId = resultSet.getInt("host_id");
          String hostName = resultSet.getString("host_name");
          int totalListings = resultSet.getInt("total_listings");
          double percentage = resultSet.getDouble("percentage");
          System.out.printf("| %-15s | %-15s | %-15d | %-16s | %-14d | %-10.2f |\n",
              countryName, cityName, hostId, hostName, totalListings, percentage);
        }
        System.out.println(
            "+-----------------+-----------------+-----------------+------------------+----------------+------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 10. List the hosts that have > 10% of listings in all countries, and cities
  public static void handleListHosts10PercentAllCountryCity(Scanner scanner) {
    scanner.nextLine();
    String sql = "SELECT l.country, l.city, u.user_id AS host_id, u.name AS host_name, " +
        "COUNT(l.user_id) AS total_listings, " +
        "(COUNT(l.user_id) / (SELECT COUNT(*) FROM Listings WHERE country = l.country AND city = l.city)) * 100 AS percentage "
        +
        "FROM Users u " +
        "JOIN Listings l ON u.user_id = l.user_id " +
        "GROUP BY l.country, l.city, u.user_id, host_name " +
        "HAVING percentage > 10 " +
        "ORDER BY l.country, l.city, total_listings DESC";

    try (ResultSet resultSet = SQL.executeQuery(sql)) {
      if (resultSet != null) {
        System.out.println("\nHosts with > 10% of Listings in All Countries and Cities:");
        System.out.println(
            "+-----------------+-----------------+-----------------+------------------+----------------+------------+");
        System.out.println(
            "| Country         | City            | UserID (Host)   | Host Name        | Total Listings | Percentage |");
        System.out.println(
            "+-----------------+-----------------+-----------------+------------------+----------------+------------+");
        while (resultSet.next()) {
          String countryName = resultSet.getString("country");
          String cityName = resultSet.getString("city");
          int hostId = resultSet.getInt("host_id");
          String hostName = resultSet.getString("host_name");
          int totalListings = resultSet.getInt("total_listings");
          double percentage = resultSet.getDouble("percentage");
          System.out.printf("| %-15s | %-15s | %-15d | %-16s | %-14d | %-10.2f |\n",
              countryName, cityName, hostId, hostName, totalListings, percentage);
        }
        System.out.println(
            "+-----------------+-----------------+-----------------+------------------+----------------+------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 11. Rank renters by num of bookings in a specific time
  public static void handleRankNumBookingsTime(Scanner scanner) {
    scanner.nextLine();
    LocalDate startDate = handleInputStartDate(scanner);
    LocalDate endDate = handleInputEndDate(scanner, startDate);

    String sql = "SELECT u.user_id AS renter_id, u.name AS renter_name, COUNT(b.user_id) AS total_bookings " +
        "FROM Users u " +
        "JOIN Bookings b ON u.user_id = b.user_id " +
        "WHERE b.start_date >= ? AND b.end_date <= ? " +
        "GROUP BY u.user_id, renter_name " +
        "ORDER BY total_bookings DESC";

    try (ResultSet resultSet = SQL.executeQuery(sql, startDate, endDate)) {
      if (resultSet != null) {
        System.out.println("\nRank of Renters by Total Number of Bookings (" + startDate + " to " + endDate + "):");
        System.out.println("+-----------------+------------------+----------------+");
        System.out.println("| Renter ID       | Renter Name      | Total Bookings |");
        System.out.println("+-----------------+------------------+----------------+");
        while (resultSet.next()) {
          int renterId = resultSet.getInt("renter_id");
          String renterName = resultSet.getString("renter_name");
          int totalBookings = resultSet.getInt("total_bookings");
          System.out.printf("| %-15d | %-16s | %-14d |\n",
              renterId, renterName, totalBookings);
        }
        System.out.println("+-----------------+------------------+----------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 12. Rank renters by number of bookings in a specific time, per city
  public static void handleRankNumBookingsTimeCity(Scanner scanner) {
    scanner.nextLine();
    LocalDate startDate = handleInputStartDate(scanner);
    LocalDate endDate = handleInputEndDate(scanner, startDate);
    String city = handleInputWord(scanner, "city");

    String sql = "SELECT l.city, u.user_id AS renter_id, u.name AS renter_name, COUNT(b.user_id) AS total_bookings " +
        "FROM Users u " +
        "JOIN Bookings b ON u.user_id = b.user_id " +
        "JOIN Listings l ON b.list_id = l.list_id " +
        "WHERE b.start_date >= ? AND b.end_date <= ? AND l.city = ? " +
        "GROUP BY l.city, u.user_id, renter_name " +
        "ORDER BY total_bookings DESC";

    try (ResultSet resultSet = SQL.executeQuery(sql, startDate, endDate, city)) {
      if (resultSet != null) {
        System.out
            .println("\nRank of Renters by Number of Bookings in " + city + " (" + startDate + " to " + endDate + "):");
        System.out.println("+-----------------+-----------------+------------------+----------------+");
        System.out.println("| City            | Renter ID       | Renter Name      | Total Bookings |");
        System.out.println("+-----------------+-----------------+------------------+----------------+");
        while (resultSet.next()) {
          int renterId = resultSet.getInt("renter_id");
          String renterName = resultSet.getString("renter_name");
          int totalBookings = resultSet.getInt("total_bookings");
          System.out.printf("| %-15s | %-15d | %-16s | %-14d |\n",
              city, renterId, renterName, totalBookings);
        }
        System.out.println("+-----------------+-----------------+------------------+----------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 13. Rank renters by number of bookings in a specific time, per city,
  // with a minimum of 2 bookings.
  public static void handleRankNumBookingsTimeCityMin2(Scanner scanner) {
    scanner.nextLine();
    LocalDate startDate = handleInputStartDate(scanner);
    LocalDate endDate = handleInputEndDate(scanner, startDate);
    String city = handleInputWord(scanner, "city");

    String sql = "SELECT l.city, u.user_id AS renter_id, u.name AS renter_name, COUNT(b.user_id) AS total_bookings " +
        "FROM Users u " +
        "JOIN Bookings b ON u.user_id = b.user_id " +
        "JOIN Listings l ON b.list_id = l.list_id " +
        "WHERE b.start_date >= ? AND b.end_date <= ? AND l.city = ? " +
        "GROUP BY l.city, u.user_id, renter_name " +
        "HAVING total_bookings >= 2 " +
        "ORDER BY total_bookings DESC";

    try (ResultSet resultSet = SQL.executeQuery(sql, startDate, endDate, city)) {
      if (resultSet != null) {
        System.out
            .println("\nRank of Renters by Number of Bookings in " + city + " (" + startDate + " to " + endDate + "):");
        System.out.println("+-----------------+-----------------+------------------+----------------+");
        System.out.println("| City            | Renter ID       | Renter Name      | Total Bookings |");
        System.out.println("+-----------------+-----------------+------------------+----------------+");
        while (resultSet.next()) {
          int renterId = resultSet.getInt("renter_id");
          String renterName = resultSet.getString("renter_name");
          int totalBookings = resultSet.getInt("total_bookings");
          System.out.printf("| %-15s | %-15d | %-16s | %-14d |\n",
              city, renterId, renterName, totalBookings);
        }
        System.out.println("+-----------------+-----------------+------------------+----------------+");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 14. Report hosts and renters with the largest number of cancellations within
  // a year.
  public static void handleHostsRentersTotalCancellations(Scanner scanner) {
    scanner.nextLine();
    String year = handleInputYear(scanner, "year");

    String renterSql = "SELECT u.user_id AS renter_id, u.name AS renter_name, COUNT(*) AS total_cancellations " +
        "FROM Users u " +
        "JOIN Bookings b ON u.user_id = b.user_id " +
        "WHERE YEAR(start_date) = ? AND is_cancelled = true AND is_cancelled_by_host = false " +
        "GROUP BY u.user_id, renter_name " +
        "ORDER BY total_cancellations DESC";

    String hostSql = "SELECT u.user_id AS host_id, u.name AS host_name, COUNT(*) AS total_cancellations " +
        "FROM Users u " +
        "JOIN Listings l ON u.user_id = l.user_id " +
        "JOIN Bookings b ON l.list_id = b.list_id " +
        "WHERE YEAR(start_date) = ? AND is_cancelled = true AND is_cancelled_by_host = true " +
        "GROUP BY u.user_id, host_name " +
        "ORDER BY total_cancellations DESC";

    try (ResultSet renterResultSet = SQL.executeQuery(renterSql, year);
        ResultSet hostResultSet = SQL.executeQuery(hostSql, year)) {

      if (renterResultSet != null) {
        System.out.println("\nRank of Renters by Total Number of Cancellations in " + year + ":");
        System.out.println("+------------+-----------------+---------------------+");
        System.out.println("| User ID    | Renter Name     | Total Cancellations |");
        System.out.println("+------------+-----------------+---------------------+");
        while (renterResultSet.next()) {
          int renterId = renterResultSet.getInt("renter_id");
          String renterName = renterResultSet.getString("renter_name");
          int totalCancellations = renterResultSet.getInt("total_cancellations");
          System.out.printf("| %-10d | %-15s | %-18d |\n", renterId, renterName, totalCancellations);
        }
        System.out.println("+------------+-----------------+--------------------+");
      }

      if (hostResultSet != null) {
        System.out.println("\nRank of Hosts by Total Number of Cancellations in " + year + ":");
        System.out.println("+----------+-----------------+---------------------+");
        System.out.println("| User ID  | Host Name       | Total Cancellations |");
        System.out.println("+----------+-----------------+---------------------+");
        while (hostResultSet.next()) {
          int hostId = hostResultSet.getInt("host_id");
          String hostName = hostResultSet.getString("host_name");
          int totalCancellations = hostResultSet.getInt("total_cancellations");
          System.out.printf("| %-8d | %-15s | %-18d |\n", hostId, hostName, totalCancellations);
        }
        System.out.println("+----------+-----------------+--------------------+");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 15. For each listing, report the most popular noun phrases in the comments
  // for that listing
  public static void handleReportPopularNounPhrasesForListing(Scanner scanner) {
    scanner.nextLine();
    int listingId = handleInputListingId(scanner, "Listing ID");

    // Retrieve the listing and associated comments
    String sql = "SELECT title, comment_on_listing " +
        "FROM Listings l " +
        "JOIN Bookings b ON l.list_id = b.list_id " +
        "WHERE l.list_id = ? AND b.comment_on_listing IS NOT NULL";

    System.out.println("...currently loading comments and noun phrases");

    PrintStream originalOut = System.out;
    PrintStream originalErr = System.err;

    // Redirect System.out and System.err to do-nothing streams
    System.setOut(new PrintStream(new OutputStream() {
      public void write(int b) {
      }
    }));
    System.setErr(new PrintStream(new OutputStream() {
      public void write(int b) {
      }
    }));

    NLPProcessor nlpProcessor = new NLPProcessor();

    System.setOut(originalOut);
    System.setErr(originalErr);

    ArrayList<String> commentList = new ArrayList<String>();

    try (ResultSet resultSet = SQL.executeQuery(sql, listingId)) {
      if (resultSet != null) {

        while (resultSet.next()) {
          String comment = resultSet.getString("comment_on_listing");
          commentList.add(comment.toLowerCase());
        }

        // Perform NLP processing to extract noun phrases
        List<String> nounPhrases = nlpProcessor.extractNounPhrases(commentList);
        // Count and rank noun phrases for the listing
        updateNounPhraseRanking(listingId, nounPhrases);

        // Display the results
        displayNounPhraseRankings(listingId);
      } else {
        System.out.println("No comments found for the specified listing.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return;
  }

  private static void updateNounPhraseRanking(int listingId, List<String> nounPhrases) {
    String deleteSql = "DELETE * FROM NounPhraseRanking WHERE listing_id = ?";
    SQL.executeUpdate(deleteSql, listingId);

    String insertSql = "INSERT INTO NounPhraseRanking (listing_id, noun_phrase, frequency) VALUES (?, ?, ?)";
    try {
      for (String nounPhrase : nounPhrases) {
        // Count the frequency of each noun phrase in the list
        int frequency = Collections.frequency(nounPhrases, nounPhrase);
        SQL.executeUpdate(insertSql, listingId, nounPhrase, frequency);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void displayNounPhraseRankings(int listingId) {
    String selectSql = "SELECT noun_phrase, frequency FROM NounPhraseRanking WHERE listing_id = ? ORDER BY frequency DESC";
    try {
      Connection connection = SQL.getConnection();
      try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
        selectStatement.setInt(1, listingId);
        try (ResultSet resultSet = selectStatement.executeQuery()) {
          System.out.println("\nNoun Phrase Rankings for Listing ID " + listingId + ":");
          System.out.println("+-----------------------------------------------+-----------+");
          System.out.println("| Noun Phrase                                   | Frequency |");
          System.out.println("+-----------------------------------------------+-----------+");

          // Use a HashSet to store and check for duplicate noun phrases
          Set<String> displayedNounPhrases = new HashSet<>();

          while (resultSet.next()) {
            String nounPhrase = resultSet.getString("noun_phrase");
            int frequency = resultSet.getInt("frequency");

            // Check if the noun phrase has already been displayed
            if (!displayedNounPhrases.contains(nounPhrase)) {
              System.out.printf("| %-45s | %-9d |\n", nounPhrase, frequency);
              displayedNounPhrases.add(nounPhrase);
            }
          }
          System.out.println("+-----------------------------------------------+-----------+");
        }
      }
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // --- Helpers to retrieve user input:

  private static String handleInputWord(Scanner scanner, String value) {
    System.out.print("Enter " + value + ": ");
    String stringValue = scanner.nextLine();
    while (!isValidWord(stringValue)) {
      String message = String.format("Invalid %s, Please use only alphabetical characters %s: ", value, value);
      System.out.println(message);
      System.out.print("Enter " + value + ": ");
      stringValue = scanner.nextLine();
    }
    return stringValue;
  }

  private static boolean isValidWord(String word) {
    return word.matches("[a-zA-Z]+");
  }

  private static String handleInputYear(Scanner scanner, String value) {
    System.out.print("Enter " + value + " (YYYY): ");
    String stringValue = scanner.nextLine();
    while (!isValidYear(stringValue)) {
      String message = String.format("Invalid %s, Please enter a valid year in YYYY format: ", value);
      System.out.println(message);
      System.out.print("Enter " + value + " (YYYY): ");
      stringValue = scanner.nextLine();
    }
    return stringValue;
  }

  private static boolean isValidYear(String input) {
    // Check if the input matches the YYYY format
    return input.matches("\\d{4}");
  }

  private static int handleInputPostalCode(Scanner scanner, String value) {
    System.out.print("Enter " + value + ": ");

    while (!scanner.hasNextInt()) {
      // Consume the invalid input
      scanner.nextLine();
      String message = String.format("Invalid %s, Please enter a valid integer %s: ", value, value);
      System.out.println(message);
    }

    int intValue = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character left by nextInt()

    // Check if intValue has more than 10 digits
    while (intValue > 9999999999L || intValue < 0) {
      System.out.println(value + " must have 10 or fewer digits. Please enter a valid integer " + value + ": ");
      intValue = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character left by nextInt()
    }

    return intValue;
  }

  private static LocalDate handleInputStartDate(Scanner scanner) {
    System.out.print("Enter the start date (YYYY-MM-DD): ");
    String start = scanner.nextLine();

    while (!isValidStartDate(start)) {
      System.out.print("Invalid start date. Please enter a valid date in the format YYYY-MM-DD: ");
      start = scanner.nextLine();
    }

    return LocalDate.parse(start);
  }

  private static LocalDate handleInputEndDate(Scanner scanner, LocalDate startDate) {
    System.out.print("Enter the end date (YYYY-MM-DD): ");
    String end = scanner.nextLine();

    while (!isValidEndDate(startDate.toString(), end)) {
      System.out.print("Invalid end date. Please enter a valid date in the format YYYY-MM-DD: ");
      end = scanner.nextLine();
    }

    return LocalDate.parse(end);
  }

  private static boolean isValidStartDate(String start) {
    try {
      // Parse the input date string to a LocalDate object
      LocalDate startDate = LocalDate.parse(start);

      // Get the current date
      LocalDate currentDate = LocalDate.of(1900, 02, 02);

      // Check if the start date is after the current date
      return startDate.isAfter(currentDate);
    } catch (DateTimeParseException e) {
      return false;
    }
  }

  private static boolean isValidEndDate(String start, String end) {
    try {
      // Parse the input date strings to LocalDate objects
      LocalDate startDate = LocalDate.parse(start);
      LocalDate endDate = LocalDate.parse(end);

      // Check if the end date is after the start date
      return endDate.isAfter(startDate);
    } catch (DateTimeParseException e) {
      return false;
    }
  }

  private static int handleInputListingId(Scanner scanner, String value) {
    System.out.print("Enter " + value + ": ");
    int listingId = scanner.nextInt();

    // Perform validation: Check if the listing exists in the database
    if (!isListingExists(listingId)) {
      System.out.println("Invalid Listing ID. The specified listing does not exist.");
      return handleInputListingId(scanner, value);
    }

    return listingId;
  }

  private static boolean isListingExists(int listingId) {
    String sql = "SELECT COUNT(*) AS count FROM Listings WHERE list_id = ?";

    try (ResultSet resultSet = SQL.executeQuery(sql, listingId)) {
      if (resultSet != null && resultSet.next()) {
        int count = resultSet.getInt("count");
        return count > 0;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return false;
  }
}