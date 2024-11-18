import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class NotationConverter {
  private final StringStack opsStack;
  private final StringQueue outQueue;
  private final String[] expressionTokens;

  public NotationConverter(String exp) throws Exception {
    expressionTokens = getExpressionTokens(exp);
    int capacity = expressionTokens.length;
    opsStack = new StringStack(capacity);
    outQueue = new StringQueue(capacity);
  }

  public StringQueue getPostFixNotation() throws Exception {
    Pattern numPattern = Pattern.compile("^[0-9]");
    Pattern opPattern = Pattern.compile("^[*/^+-]$");

    for (String token : expressionTokens) {
      if (token == null) break;

      if (token.equals("(")) {
        opsStack.push(token);
        continue;
      }

      if (numPattern.matcher(token).find()) {
        outQueue.enqueue(token);
        continue;
      }

      if (opPattern.matcher(token).find()) {
        if (opsStack.isEmpty()) {
          opsStack.push(token);
          continue;
        }
        if (tableCheck(token, opsStack.getTop())) outQueue.enqueue(opsStack.pop());
        if (!opsStack.isEmpty() && opsStack.getTop().equals(token)) {
          outQueue.enqueue(opsStack.pop());
          opsStack.push(token);
          continue;
        }
        opsStack.push(token);
        continue;
      }

      while (!opsStack.getTop().equals("(")) outQueue.enqueue(opsStack.pop());
      opsStack.pop();
    }

    while (!opsStack.isEmpty()) outQueue.enqueue(opsStack.pop());

    return outQueue;
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

  private static String[] getExpressionTokens(String exp) throws Exception {
    Pattern opPattern = Pattern.compile("[^0-9+\\-^/*()]+");
    String expression = exp.replaceAll("\\s+", "");

    if (opPattern.matcher(expression).find()) throw new Exception("Invalid character entered.");

    int sum = 0;
    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (c == '(' || c == ')') sum++;
    }

    if (sum % 2 == 1) throw new Exception("Incorrect number of parentheses found.");

    StringTokenizer st = new StringTokenizer(expression, "+-*/^()", true);
    String[] result = new String[expression.length()];

    for (int i = 0; i < expression.length(); i++)
      if (st.hasMoreTokens())
        result[i] = st.nextToken();

    return result;
  }
}
