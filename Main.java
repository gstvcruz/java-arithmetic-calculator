import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("Insira a expressão (digite 'q' para sair): ");
      String expression = scanner.nextLine();

      if ("q".equalsIgnoreCase(expression)) {
        break;
      }

      try {
        new ExpressionValidator(expression).validate();
        LinkedListQueue<String> RPNExpression = new ReversePolishNotationConverter(expression).convert();
        Calculator calculator = new Calculator(RPNExpression);

        System.out.println("\033[3m" + "Resultado: " + calculator.getResult() + "\033[0m");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

    scanner.close();
  }
}
