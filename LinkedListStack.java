public class LinkedListStack<T> {
  private ListaEncadeadaSimplesDesordenada<T> stack = new ListaEncadeadaSimplesDesordenada<>();

  public void push(T element) {
    stack.guardeNoInicio(element);
  }

  public T pop() {
    T top = stack.getPrimeiro();
    stack.remova(0);
    return top;
  }

  public T getTop() {
    return stack.getPrimeiro();
  }

  public boolean isEmpty() {
    return stack.getTamanho() == 0;
  }

  @Override
  public String toString() {
    return stack.toString();
  }
}
