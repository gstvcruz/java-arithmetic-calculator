import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) {
    String[] expressionTokens = getExpressionTokens(args[1]);
  }

  // Remove os espaços da expressão aritmética e guarda seus tokens em um array de String 
  private static String[] getExpressionTokens(String exp) {
    String expression = exp.replaceAll("\\s+", "");
    StringTokenizer st = new StringTokenizer(expression, "+-*/^()", true);
    String[] result = new String[expression.length() - 1];
    
    for (int i = 0; i < expression.length(); i++)
      if (st.hasMoreTokens())
        result[i] += st.nextToken();

    return result;
  }
}
