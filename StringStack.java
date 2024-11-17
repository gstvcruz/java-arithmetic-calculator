public class StringStack {
  private String[] arr;
  private int p_size; // physical size
  private int top;    // top of the stack pointer

  public StringStack(int capacity) throws Exception {
    if (p_size <= 0) throw new Exception("Stack must be initialized with a capacity greater than zero.");
    p_size = capacity;
    arr = new String[p_size];
    top = -1;
  }

  public void push(String element) throws Exception {
    if (top + 1 == p_size) throw new Exception("Stack is full.");
    arr[++top] = element;
  }

  public String pop() throws Exception {
    if (top == -1) throw new Exception("No elements to remove.");
    return arr[top--];
  }
}
