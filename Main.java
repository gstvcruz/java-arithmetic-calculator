import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("Enter expression (type 'exit' to quit): ");
      String input = scanner.nextLine();

      if ("exit".equalsIgnoreCase(input)) {
        System.out.println("Goodbye!");
        break;
      }

      System.out.println("Result: ");
    }

    scanner.close();
    NotationConverter nc = new NotationConverter(args[0]);
    StringQueue postFixExp = nc.getPostFixNotation();
    Calculator calculator = new Calculator(postFixExp);
    System.out.println(calculator.getResult());
  }
}
