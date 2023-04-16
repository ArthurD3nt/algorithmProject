package graph;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Data type representing each node of the graph
 *
 * @param <T> type of the label
 * @param <W> type to use for the weight of the edges connected to it
 */

class Node<T, W> {

    T label;
    ArrayList<Edge<T, W>> edges;
    Comparator<T> tComparator;

    /**
     * Creates new node
     *
     * @param label of the node
     * @param tComparator comparator to compare labels
     */

    Node(T label, Comparator<T> tComparator) {
        this.label = label;
        this.edges = new ArrayList<>();
        this.tComparator = tComparator;
    }

    /**
     * Adds edge inside adjacency list
     * @param edge to be added
     */
    void addEdge(Edge<T, W> edge) {
        edges.add(edge);
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        Node<T, W> node = (Node<T, W>) obj;
        if ((Node<T, W>) obj == null) {
            return false;
        }
        if (tComparator.compare(node.label, this.label) == 0) {
            return true;
        }
        return false;
    }
}