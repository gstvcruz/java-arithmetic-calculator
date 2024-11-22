public class Calculator {
  private final LinkedListQueue<String> queue;
  private final LinkedListStack<String> stack = new LinkedListStack<>();

  public Calculator(LinkedListQueue<String> queue) {
    this.queue = queue;
  }

  public double getResult() throws Exception {
    double v1, v2 = 0;
    char op;

    while(!queue.isEmpty()) {
      String element = queue.dequeue();
      try {
        Double.parseDouble(element);
        stack.push(element);
      } catch (NumberFormatException e) {
        op = element.charAt(0);
        v2 = Double.parseDouble(stack.pop());
        v1 = Double.parseDouble(stack.pop());
        Double result = switch (op) {
          case '+' -> v1 + v2;
          case '-' -> v1 - v2;
          case '*' -> v1 * v2;
          case '/' -> v1 / v2;
          case '^' -> Math.pow(v1, v2);
          default -> throw new IllegalStateException("Unexpected value: " + op);
        };
        stack.push(String.valueOf(result));
      }
    }

    return Double.parseDouble(stack.pop());
  }
}
