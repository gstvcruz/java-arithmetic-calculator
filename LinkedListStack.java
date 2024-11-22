public class LinkedListStack<T> {
    private ListaEncadeadaSimplesDesordenada<T> stack = new ListaEncadeadaSimplesDesordenada<>();

    public void push(T element) throws Exception {
        stack.guardeNoInicio(element);
    }

    public T pop() throws Exception {
        T top = this.top();
        stack.remova(0);
        return top;
    }

    public T top() throws Exception {
        return stack.getPrimeiro();
    }

    public T bottom() throws Exception {
        return stack.getUltimo();
    }

    public boolean has(T element) {
        return stack.tem(element);
    }

    public int size() {
        return stack.getTamanho();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
