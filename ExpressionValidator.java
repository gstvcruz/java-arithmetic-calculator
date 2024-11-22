public class ExpressionValidator {
  private final String expression;

  public ExpressionValidator(String expression) throws IllegalArgumentException {
    if (expression == null) throw new IllegalArgumentException("Expressão vazia! Forneça uma expressão válida.");
    this.expression = expression;
  }

  public void validate() {
    if (containsInvalidCharacters()) {
      throw new IllegalArgumentException(
        "\033[31m" + "Valor e/ou operador inválidos encontrados!\nValores permitidos: 0-9\nOperadores permitidos: '+', '-', '*', '/', '^'" + "\033[0m"
      );
    }
    if (hasConsecutiveOperators()) {
      throw new IllegalArgumentException(
        "\033[31m" + "A expressão não pode conter um operador seguido de outro!" + "\033[0m"
      );
    }
    if (startsWithInvalidOperator()) {
      throw new IllegalArgumentException(
        "\033[31m" + "A expressão não pode iniciar com '*', '/' ou '^'!" + "\033[0m"
      );
    }
    if (!areParenthesesCorrectlyEnclosedAndBalanced()) {
      throw new IllegalArgumentException(
        "\033[31m" + "Os parênteses na expressão não estão corretamente balanceados e/ou fechados!" + "\033[0m"
      );
    }
  }

  private boolean containsInvalidCharacters() {
    // Matches any character not allowed in the expression
    return !expression.matches("^[0-9+\\-*/^()\\s]*$");
  }

  private boolean hasConsecutiveOperators() {
    // Matches two or more consecutive operators
    return expression.matches(".*[+\\-*/^]{2,}.*");
  }

  private boolean startsWithInvalidOperator() {
    // Matches expressions starting with '*', '/' or '^'
    return expression.matches("^[*/^].*");
  }

  private boolean areParenthesesCorrectlyEnclosedAndBalanced() {
    // Use a counter to ensure correct ordering of parentheses
    int balance = 0;

    for (char c : expression.toCharArray()) {
      if (c == '(') {
        balance++; // Increment for an opening parenthesis
      } else if (c == ')') {
        balance--; // Decrement for a closing parenthesis
        if (balance < 0) {
          // If balance goes negative, there's a mismatch
          return false;
        }
      }
    }

    // Balance must be zero if parentheses are correctly enclosed
    return balance == 0;
  }
}
