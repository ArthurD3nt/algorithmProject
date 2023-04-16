package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;

/**
 * Represents the graph data structure, it's a collection
 * of nodes connected between them by edges
 *
 * @author Francesco Mazzucco
 * @param <T> type of the node label
 * @param <W> type of the edge weight
 */

public class Graph<T, W> {

    private Boolean directed = false;
    private Boolean weighted = false;
    private Comparator <T> tComparator;

    private HashMap<T, Node<T, W>> nodeMap = new HashMap<>();

    /**
     * Creates a empty graph
     *
     * @param directed
     * @param weighted
     * @param comparator used to compare labels
     */
    public Graph(Boolean directed, Boolean weighted, Comparator<T> tComparator) {
        this.directed = directed;
        this.weighted = weighted;
        this.tComparator = tComparator;
    }

    /**
     * Adds a node to the graph
     *
     * @param label
     * @throws GraphException if the node already exists
     */
    public T newNode(T label) throws GraphException {
        if (label == null) {
            throw new GraphException("Node label cannot be null");
        }
        if (!nodeMap.containsKey(label))
            nodeMap.put(label, new Node<>(label, tComparator));
        return label;
    }

    /**
     * Adds an edge to the weighted graph
     *
     * @param object
     * @param object2
     * @param weight
     * @throws GraphException if the graph is not weighted or if the nodes do not
     *                        exist
     */
    public void newEdge(T source, T destination, W weight) throws GraphException {
        if (existsEdge(source, destination))
            return;
        if (source == null || destination == null) {
            throw new GraphException("Node label cannot be null");
        }
        if (weight == null) {
            throw new GraphException("Edge weight cannot be null");
        }
        if (!weighted) {
            throw new GraphException("Edge weight cannot be specified");
        }

        if (!nodeMap.containsKey(source) || !nodeMap.containsKey(destination)) {
            throw new GraphException("Node does not exist");
        }

        nodeMap.get(source).addEdge(new Edge<T, W>(source, destination, weight));

        if (!directed) {
            nodeMap.get(destination).addEdge(new Edge<T, W>(destination , source, weight));
        }
    }

    /**
     * Adds an edge to the unweighted graph
     *
     * @param source
     * @param destination
     * @param weight
     * @throws GraphException if the graph is weighted or if the nodes do not exist
     */
    public void newEdge(T source, T destination) throws GraphException {
        if (existsEdge(source, destination))
            return;
        if (source == null || destination == null) {
            throw new GraphException("Node label cannot be null");
        }

        if (weighted) {
            throw new GraphException("Edge weight cannot be null");
        }

        if (!nodeMap.containsKey(source) || !nodeMap.containsKey(destination)) {
            throw new GraphException("Node does not exist");
        }

        nodeMap.get(source).addEdge(new Edge<T, W>(source, destination, null));

        if (!directed) {
            nodeMap.get(destination).addEdge(new Edge<T, W>(destination, source, null));
        }
    }

    /**
     * Returns if the graph is directed
     *
     * @param name
     * @return true if the graph is directed and false otherwise
     */
    public Boolean isDirected() {
        return directed;
    }

    /**
     * Returns if the graph is weighted
     *
     * @param name
     * @return true if the graph is weighted and false otherwise
     */
    public Boolean isWeighted() {
        return weighted;
    }

    /**
     * Returns if the node with the given label exists
     *
     * @param label
     * @return true if the node exists and false otherwise
     */
    public Boolean existsNode(T label) throws GraphException {
        if (label == null)
            throw new GraphException("Node label cannot be null");
        return nodeMap.containsKey(label);
    }

    /**
     * Returns if the edge with the given source and destination exists
     *
     * @param source
     * @param destination
     * @return true if the edge exists and false otherwise
     */
    public Boolean existsEdge(T source, T destination) throws GraphException {
        if (source == null || destination == null)
            throw new GraphException("Node label cannot be null");
        if (!nodeMap.containsKey(source) || !nodeMap.containsKey(destination)) {
            return false;
        }
        for (Edge<T, W> edge : nodeMap.get(source).edges) {
            if (edge.destination.equals(destination)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Removes the node with the given label
     *
     * @param label
     * @throws GraphException if the node does not exist
     */
    public void removeNode(T label) throws GraphException {
        if (label == null)
            throw new GraphException("Node label cannot be null");
        if (!nodeMap.containsKey(label)) {
            throw new GraphException("Node does not exist");
        }
        if (directed) {
            removeEdgesConnectedTo(label);
        } else {
            removeEdgesConnectedTo(label);
        }
        nodeMap.remove(label);
    }

    private void removeEdgesConnectedTo(T label) {
        if (directed) {
            for (Node<T, W> node : nodeMap.values()) {
                for (Edge<T, W> edge : node.edges) {
                    if (edge.destination.equals(label)) {
                        node.edges.remove(edge);
                    }
                }
            }
        } else {
            for (Edge<T, W> edge : nodeMap.get(label).edges) {
                for (Edge<T, W> edge2 : nodeMap.get(edge.destination).edges) {
                    if (edge2.destination.equals(label)) {
                        nodeMap.get(edge.destination).edges.remove(edge2);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Removes the edge with the given source and destination
     *
     * @param source
     * @param destination
     * @throws GraphException
     */
    public void removeEdge(T source, T destination) throws GraphException {
        if (source == null || destination == null)
            throw new GraphException("Node label cannot be null");
        if (!nodeMap.containsKey(source) || !nodeMap.containsKey(destination))
            throw new GraphException("Nodes with such labels do not exist");

        if (directed) {
            for (Edge<T, W> edge : nodeMap.get(source).edges) {
                if (edge.destination.equals(destination)) {
                    nodeMap.get(source).edges.remove(edge);
                    return;
                }
            }
        } else {
            for (Edge<T, W> edge : nodeMap.get(source).edges) {
                if (edge.destination.equals(destination)) {
                    for (Edge<T, W> edge2 : nodeMap.get(destination).edges) {
                        if (edge2.destination.equals(source)) {
                            nodeMap.get(destination).edges.remove(edge2);
                            nodeMap.get(source).edges.remove(edge);
                            return;
                        }
                    }
                }
            }
        }
        throw new GraphException("Edge does not exist");
    }

    /**
     * Returns the number of nodes in the graph
     *
     * @return the number of nodes
     */
    public int nNode() {
        return nodeMap.size();
    }

    /**
     * Returns the number of edges in the graph
     *
     * @return the number of edges
     */
    public int nEdge() {
        int count = 0;
        for (Node<T, W> node : nodeMap.values()) {
            count += node.edges.size();
        }
        if (!directed)
            return count / 2;
        else
            return count;
    }

    /**
     * Returns a arrayList of all the nodes in the graph
     *
     * @return the node with the given label
     */
    public ArrayList<Node<T, W>> getNodeList() {
        ArrayList<Node<T, W>> array = new ArrayList<>(nodeMap.size());
        for (Node<T, W> node : nodeMap.values())
            array.add(node);
        return array;
    }

    /**
     * Returns a arrayList of all the edges in the graph
     *
     * @return the edge with the given label
     */
    public ArrayList<Edge<T, W>> getEdgeList() {
        ArrayList<Edge<T, W>> array = new ArrayList<>(nEdge());
        for (Node<T, W> node : nodeMap.values()) {
            for (Edge<T, W> edge : node.edges) {
                array.add(edge);
            }
        }
        return array;
    }

    /**
     * Returns a arrayList of all the adjacent nodes to the given node
     *
     * @param label the label of the node
     * @return a arrayList of all the adjacent nodes to the given node
     */
    public ArrayList<Node<T, W>> getAdiacent(T label) throws GraphException {
        if (!nodeMap.containsKey(label))
            throw new GraphException("No node with such label exists");
        ArrayList<Node<T, W>> array = new ArrayList<>(nodeMap.get(label).edges.size());
        for (Edge<T, W> edge : nodeMap.get(label).edges) {
            array.add(getNode(edge.destination));
        }
        return array;
    }

    /**
     * Returns the label of the edge connecting source and destination
     *
     * @param source
     * @param destination
     * @return the label of the given edge
     * @throws GraphException if the nodes does not exist or the edge does not exist
     */
    public W getEdgeLabel(T source, T destination) throws GraphException {
        if (!nodeMap.containsKey(source) || !nodeMap.containsKey(destination) || existsEdge(source, destination))
            throw new GraphException(
                    "Nodes with such labels do not exist or no " + source + " - " + destination + " pair was found");
        for (Edge<T, W> edge : nodeMap.get(source).edges) {
            if (edge.destination.equals(destination))
                return edge.weight;
        }
        throw new GraphException(
                "Nodes with such labels do not exist or no " + source + " - " + destination + " pair was found");
    }

    /**
     * Returns the node with the given label
     *
     * @param label the label of the node
     * @return the node with the given label
     * @throws GraphException if the node does not exist
     */
    public Node<T, W> getNode(T label) throws GraphException {
        if (!nodeMap.containsKey(label))
            throw new GraphException("No node with such label exists");
        return nodeMap.get(label);
    }

    /**
     * Returns the edge with the given source and destination
     *
     * @param source      the source of the edge
     * @param destination the destination of the edge
     * @return the edge with the given source and destination
     * @throws GraphException if the nodes does not exist or the edge does not exist
     */
    public Edge<T, W> getEdge(T source, T destination) throws GraphException {
        if (!nodeMap.containsKey(source) || !nodeMap.containsKey(destination) || !existsEdge(source, destination))
            throw new GraphException(
                    "Nodes with such labels do not exist or no " + source + " - " + destination + " pair was found");
        for (Edge<T, W> edge : nodeMap.get(source).edges) {
            if (edge.destination.equals(destination))
                return edge;
        }
        throw new GraphException(
                "Nodes with such labels do not exist or no " + source + " - " + destination + " pair was found");
    }

}