public class LinkedListQueue<T> {
    private ListaEncadeadaSimplesDesordenada<T> queue = new ListaEncadeadaSimplesDesordenada<T>();

    public void enqueue(T element) throws Exception {
        queue.guardeNoFinal(element);
    }

    public T dequeue() throws Exception {
        T head = this.head();
        queue.remova(0);
        return head;
    }

    public T head() throws Exception {
        return queue.getPrimeiro();
    }

    public T tail() throws Exception {
        return queue.getUltimo();
    }

    public boolean has(T element) {
        return queue.tem(element);
    }

    public int size() {
        return queue.getTamanho();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
