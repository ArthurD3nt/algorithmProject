package heap;

/**
 * Exception throwable by the heap library
 * @author Francesco Mazzucco
 */

public class HeapException extends Exception {
	public HeapException(String message){
		super("Library Heap: " + message);
	}
}
