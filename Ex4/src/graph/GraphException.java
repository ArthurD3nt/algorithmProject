package graph;

/**
 * Exception throwable by the heap library
 * @author Francesco Mazzucco
 */

public class GraphException extends Exception {
    public GraphException(String message){
        super("Library Graph: " + message);
    }
}