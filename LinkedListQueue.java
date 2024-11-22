public class LinkedListQueue<T> {
  private ListaEncadeadaSimplesDesordenada<T> queue = new ListaEncadeadaSimplesDesordenada<T>();

  public void enqueue(T element) {
    queue.guardeNoFinal(element);
  }

  public T dequeue() {
    T head = queue.getPrimeiro();
    queue.remova(0);
    return head;
  }

  public boolean isEmpty() {
    return queue.getTamanho() == 0;
  }

  @Override
  public String toString() {
    return queue.toString();
  }
}
