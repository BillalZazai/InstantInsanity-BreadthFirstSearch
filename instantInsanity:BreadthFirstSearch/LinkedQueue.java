public class LinkedQueue<E> implements Queue<E> {
	private static class Elem <T> {
		private T value;
		private Elem <T> next;
		Elem (T value, Elem <T> next ){
			this.value=value;
			this.next=next;
		}
	}
	private int size=0;
	private Elem <E> front;
	private Elem <E> rear;
	public int Size (){
		return size;
	}
	public boolean isEmpty (){
		return front==null;
	}
	public void enqueue (E elem){
		Elem<E> newElem;
        newElem = new Elem<E>( elem, null );

        if ( rear == null ) {
            front = rear = newElem;
            size++;
        } else {
            rear.next = newElem;
            rear = newElem;
        	size++;
        }
	}
	public E dequeue (){
		E result = front.value;
        if ( front != null & front.next == null ) {
            front = rear = null;
            size--;
        } else {
            front = front.next;
            size--;
        }
        return result;
	}
	public E peek (){
		return front.value;
	}
}