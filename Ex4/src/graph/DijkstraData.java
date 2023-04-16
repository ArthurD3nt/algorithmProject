package graph;

/**
 * Data structure used inside the Dijkstra algorithm
 *
 * @author Francesco Mazzucco
 */

public class DijkstraData {
    float INFTY = Float.POSITIVE_INFINITY;

    float distance = INFTY;
    String previous = null;
    Boolean visited = false;
    String label = null;

    /**
     * Creates new DijkstraData instance
     * @param label
     */
    public DijkstraData(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        DijkstraData object = (DijkstraData) obj;
        if (object == null)
            return false;
        else if (object.label.compareTo(this.label) == 0)
            return true;
        return false;
    }
}
