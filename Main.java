import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("Insira a expressão (digite 'q' para sair): ");
      String expression = scanner.nextLine();

      if ("q".equalsIgnoreCase(expression)) {
        break;
      }

      try {
        new ExpressionValidator(expression).validate();
        LinkedListQueue<CharSequence> RPNExpression = new ReversePolishNotationConverter(expression).convert();
        Calculator calculator = new Calculator(RPNExpression);

        System.out.println("\033[3m" + "Resultado: " + calculator.getResult() + "\033[0m");
      }
      // For when the expression is composed of only '+' or '-'
      catch (NullPointerException _) { }
      catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

    scanner.close();
  }
}
