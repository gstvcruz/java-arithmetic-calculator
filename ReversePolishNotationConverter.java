import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class ReversePolishNotationConverter {
  private final LinkedListStack<String> operators = new LinkedListStack<>();
  private final LinkedListQueue<String> output = new LinkedListQueue<>();
  private final String[] tokens;

  public ReversePolishNotationConverter(String expression) {
    tokens = getTokens(expression);
  }

  public LinkedListQueue<String> convert() throws Exception {
    Pattern numPattern = Pattern.compile("^[0-9]");
    Pattern opPattern = Pattern.compile("^[*/^+-]$");

    for (String token : tokens) {
      if (token == null) {
        break;
      }

      if (token.equals("(")) {
        operators.push(token);
        continue;
      }

      if (numPattern.matcher(token).find()) {
        output.enqueue(token);
        continue;
      }

      if (opPattern.matcher(token).find()) {
        if (operators.isEmpty()) {
          operators.push(token);
          continue;
        }
        if (tableCheck(token, operators.getTop())) {
          output.enqueue(operators.pop());
        }
        if (!operators.isEmpty() && operators.getTop().equals(token)) {
          output.enqueue(operators.pop());
          operators.push(token);
          continue;
        }
        operators.push(token);
        continue;
      }

      while (!operators.getTop().equals("(")) {
        output.enqueue(operators.pop());
      }
      operators.pop();
    }

    while (!operators.isEmpty()) {
      output.enqueue(operators.pop());
    }

    return output;
  }

  private static boolean tableCheck(String token, String topOfStack) {
    Pattern timesDivisionPattern = Pattern.compile("^[*/^]$");
    Pattern plusMinusPattern = Pattern.compile("^[()]$");
    return switch (token) {
      case "*", "/" -> timesDivisionPattern.matcher(topOfStack).find();
      case "+", "-" -> !plusMinusPattern.matcher(topOfStack).find();
      case ")" -> !topOfStack.equals(")");
      default -> false; // "(" and "^"
    };
  }

  private static String[] getTokens(String expression) {
    expression = expression.replaceAll("\\s+", "");

    StringTokenizer st = new StringTokenizer(expression, "+-*/^()", true);
    String[] tokens = new String[expression.length()];

    for (int i = 0; i < expression.length(); i++) {
      if (st.hasMoreTokens()) {
        tokens[i] = st.nextToken();
      }
    }

    return tokens;
  }
}
