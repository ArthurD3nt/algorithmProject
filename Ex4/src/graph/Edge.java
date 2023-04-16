package graph;

/**
 * Represents a edge connecting source and destination
 *
 * @author Francesco Mazzucco
 * @param <T> type of the source label
 * @param <W> type used to represent the weight
 *
 *
 */

class Edge<T, W> {

    T source;
    T destination;
    W weight;

    /**
     * Creates new edge
     *
     * @param label_node1 source node
     * @param label_node2 target node
     * @param weight of the edge
     */
    Edge(T label_node1, T label_node2, W weight) {
        this.source = label_node1;
        this.destination = label_node2;
        this.weight = weight;
    }
}