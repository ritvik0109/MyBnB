import java.util.Scanner;

public class RunReportHandler {

  public static void handleRunReport(Scanner scanner) {
    scanner.nextLine();
    boolean exit = false;

    while (!exit) {
      displayReportToRun();
      int mainChoice = getUserChoice(scanner);

      switch (mainChoice) {
        case 1:
          ReportGenerator.handleTotalNumBookingsPerCity(scanner);
          break;
        case 2:
          ReportGenerator.handleTotalNumBookingsPerPostalCode(scanner);
          break;
        case 3:
          ReportGenerator.handleTotalNumListingsPerCountry(scanner);
          break;
        case 4:
          ReportGenerator.handleTotalNumListingsPeCountryCity(scanner);
          break;
        case 5:
          ReportGenerator.handleTotalNumListingsPerCountryCityPostal(scanner);
          break;
        case 6:
          ReportGenerator.handleRankHostsByListingsCountry(scanner);
          break;
        case 7:
          ReportGenerator.handleRankHostsByListingsCountryCity(scanner);
          break;
        case 8:
          ReportGenerator.handleListHosts10PercentCountry(scanner);
          break;
        case 9:
          ReportGenerator.handleListHosts10PercentCountryCity(scanner);
          break;
        case 10:
          ReportGenerator.handleListHosts10PercentAllCountryCity(scanner);
          break;
        case 15:
          System.out.println("...returning to main menu");
          exit = true; // Go back to the main menu
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }
    }
    return;
  }

  private static void displayReportToRun() {
    System.out.println("\n --- Run a Report. ---");
    System.out.println("Select a report to run:");
    System.out.println("1. Total number of bookings in a specific date range by city.");
    System.out.println("2. Total number of bookings in a specific date range by zip code, within a city.");
    System.out.println("3. Total number of listings in per country.");
    System.out.println("4. Total number of listings in per city and country.");
    System.out.println("5. Total number of listings in per postal code, city and country.");
    System.out.println("6. Rank hosts by total number of listings in a country.");
    System.out.println("7. Rank hosts by total number of listings in a country, refined by city.");
    System.out.println("8. List the hosts that have > 10% of listings in a country.");
    System.out.println("9. List the hosts that have > 10% of listings in a city, and country.");
    System.out.println("10. List the hosts that have > 10% of listings for all cities, and countries.");

    System.out.println("15. Exit, go back to main menu.");
    System.out.print("Enter your choice: ");
  }

  private static int getUserChoice(Scanner scanner) {
    while (!scanner.hasNextInt()) {
      System.out.println("Invalid input. Please enter a valid integer choice.");
      scanner.next(); // Clear the invalid input from the buffer
    }
    return scanner.nextInt();
  }

}
