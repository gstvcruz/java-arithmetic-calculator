public class Calculator {
  private final StringQueue outQueue;
  private final StringStack outStack;

  public Calculator(StringQueue outQueue) throws Exception {
    this.outQueue = outQueue;
    outStack = new StringStack(outQueue.size());
  }

  public double getResult() throws Exception {
    double v1, v2 = 0;
    char op;

    while(!outQueue.isEmpty()) {
      String element = outQueue.dequeue();
      try {
        Double.parseDouble(element);
        outStack.push(element);
      } catch (NumberFormatException e) {
        op = element.charAt(0);
        v2 = Double.parseDouble(outStack.pop());
        v1 = Double.parseDouble(outStack.pop());
        Double result = switch (op) {
          case '+' -> v1 + v2;
          case '-' -> v1 - v2;
          case '*' -> v1 * v2;
          case '/' -> v1 / v2;
          case '^' -> Math.pow(v1, v2);
          default -> throw new IllegalStateException("Unexpected value: " + op);
        };
        outStack.push(String.valueOf(result));
      }
    }

    return Double.parseDouble(outStack.pop());
  }
}
