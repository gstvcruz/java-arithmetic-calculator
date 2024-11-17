public class StringQueue {
  private final String[] arr;
  private final int p_size; // physical size
  private int l_size; // logical size
  private int head;   // first element pointer
  private int tail;   // last element pointer

  public StringQueue(int capacity) throws Exception {
    if (capacity <= 0) throw new Exception("Queue must be initialized with a capacity greater than zero.");
    p_size = capacity;
    l_size = 0;
    arr = new String[p_size];
    head = tail = -1;
  }

  public void enqueue(String element) throws Exception {
    if (l_size == p_size) throw new Exception("Queue is full.");
    if (head == -1 && tail == -1) {
      l_size++;
      head++;
      tail++;
      arr[tail] = element;
      return;
    }
    l_size++;
    if (tail + 1 == p_size) tail = -1; // goes around the array
    arr[++tail] = element;
  }

  public String dequeue() throws Exception {
    if (l_size == 0) throw new Exception("No elements to remove.");
    l_size--;
    if (head + 1 == p_size) {
      String e = arr[head];
      head = 0; // goes around the array
      return e;
    }
    return arr[head++];
  }
}
