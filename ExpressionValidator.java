public class ExpressionValidator {
  private final String expression;

  public ExpressionValidator(String expression) throws IllegalArgumentException {
    if (expression.isBlank()) throw new IllegalArgumentException("Expressão vazia! Forneça uma expressão válida.");
    this.expression = expression;
  }

  public void validate() {
    if (containsInvalidCharacters()) {
      throw new IllegalArgumentException("Valor e/ou operador inválidos!");
    }
    if (hasConsecutiveOperators()) {
      throw new IllegalArgumentException("A expressão não pode conter um operador seguido de outro!");
    }
    if (startsOrEndsWithArithmeticOperator()) {
      throw new IllegalArgumentException("A expressão não pode iniciar/terminar com um operador!");
    }
    if (!areParenthesesCorrectlyEnclosedAndBalanced()) {
      throw new IllegalArgumentException("Os parênteses na expressão não estão corretamente balanceados e/ou fechados!");
    }
  }

  private boolean containsInvalidCharacters() {
    // Matches any character not allowed in the expression or invalid placement of `.`
    return !expression.matches("^(\\s*[0-9]+(\\.[0-9]*)?|\\s*\\.[0-9]+|[+\\-*/^()\\s])*\\s*$");
  }

  private boolean hasConsecutiveOperators() {
    // Matches two or more consecutive operators, with or without spaces between them
    return expression.matches(".*[+\\-*/^]\\s*[+\\-*/^].*");
  }

  private boolean startsOrEndsWithArithmeticOperator() {
    // Matches expressions starting or ending with arithmetic operators
    return expression.matches("^[+\\-*/^].*") || expression.matches(".*[+\\-*/^]$");
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
          // If balance ever goes negative, there's a closing parenthesis before an opening one
          return false;
        }
      }
    }

    // Balance must be zero if parentheses are correctly enclosed
    return balance == 0;
  }
}
