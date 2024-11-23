public class Calculator {
  private final LinkedListQueue<CharSequence> outputQueue;
  private final LinkedListStack<CharSequence> resultStack = new LinkedListStack<>();

  public Calculator(LinkedListQueue<CharSequence> outputQueue) {
    this.outputQueue = outputQueue;
  }

  public double getResult() throws Exception {
    double v1, v2 = 0;
    char op;

    while(!outputQueue.isEmpty()) {
      CharSequence element = outputQueue.dequeue();

      // Tries to parse the dequeued element as a number and push it to the stack
      try {
        Double.parseDouble(element.toString());
        resultStack.push(element);
      }
      // If the dequeued element is an operator, calculate
      catch (NumberFormatException e) {
        op = element.charAt(0);
        v2 = Double.parseDouble(resultStack.pop().toString());
        v1 = Double.parseDouble(resultStack.pop().toString());
        Double result = switch (op) {
          case '+' -> v1 + v2;
          case '-' -> v1 - v2;
          case '*' -> v1 * v2;
          case '/' -> v1 / v2;
          default -> Math.pow(v1, v2);
        };
        // Adds calculated value to the result stack
        resultStack.push(String.valueOf(result));
      }
    }

    // Returns the top of the stack (final result) as a double
    return Double.parseDouble(resultStack.pop().toString());
  }
}
