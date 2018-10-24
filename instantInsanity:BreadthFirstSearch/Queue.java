public interface Queue <E> {
	void enqueue (E elem);
	boolean isEmpty ();
	E dequeue ();
	E peek ();
	int Size ();

}