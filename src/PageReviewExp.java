import java.util.Scanner;

public class PageReviewExp {
  public static void handleReviewExp(Scanner scanner) {
    boolean exit = false;

    while (!exit) {
      displayReviewExpMenu();
      int mainChoice = getUserChoice(scanner);

      switch (mainChoice) {
        case 1:
          ReviewAsRenterHandler.handleRenterComments(scanner);
          break;
        case 2:
          System.out.println("Review as Host");
          break;
        case 3:
          System.out.println("...returning to home");
          exit = true; // Go back to the main menu
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }
    }
  }

  private static void displayReviewExpMenu() {
    System.out.println("\n --- Review Your Experience (Rate/Comment) ---");
    System.out.println("1. Rate/Comment as a Renter");
    System.out.println("2. Rate/Comment as a Host");
    System.out.println("3. Exit");
    System.out.print("Enter your choice: ");
  }

  private static int getUserChoice(Scanner scanner) {
    while (!scanner.hasNextInt()) {
      System.out.println("Invalid input. Please enter a valid integer choice.");
      scanner.next(); // Clear the invalid input from the buffer
    }
    return scanner.nextInt();
  }

  // public static class AsRenterReviewHandler {
  // public static void asRenterReview(Scanner scanner) {
  // boolean exit = false;

  // while (!exit) {
  // displayAsRenter();
  // int mainChoice = getUserChoice(scanner);

  // switch (mainChoice) {
  // case 1:
  // System.out.println("Comment on a Listing");
  // break;
  // case 2:
  // System.out.println("Edit Listing");
  // break;
  // case 3:
  // System.out.println("Edit Listing");
  // break;
  // case 4:
  // System.out.println("Edit Listing");
  // break;
  // case 5:
  // System.out.println("...returning to home");
  // exit = true; // Go back to the main menu
  // break;
  // default:
  // System.out.println("Invalid choice. Please try again.");
  // break;
  // }
  // }
  // }

  // private static void displayAsRenter() {
  // System.out.println("\n --- Rate/Comment as a Renter ---");
  // System.out.println("1. Comment on a past listing");
  // System.out.println("2. Rate a host for a past listing.");
  // System.out.println("3. Comment on a host for a past listing");
  // System.out.println("4. Rate a past listing");
  // System.out.println("5. Exit / Back to Review Experience.");
  // System.out.print("Enter your choice: ");
  // }
  // }
}
