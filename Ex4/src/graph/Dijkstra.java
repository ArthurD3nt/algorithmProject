package graph;

import java.util.Scanner;
import java.util.Comparator;
import java.util.HashMap;
import java.io.File;

import heap.*;


public class Dijkstra {

    static String start;
    static String end;

    static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) throws GraphException, HeapException {
        // Graph with CSV values
        Graph<String, Float> graph = new Graph<>(false, true, new StringComparator());
        Scanner scan = null;
        try {
            scan = new Scanner(new File(args[0]));
        } catch (Exception e) {
            System.out.println("File not found");
            return;
        }
        scan.useDelimiter("[,\n]");
        start = args[1].toLowerCase();
        end = args[2].toLowerCase();
        while (scan.hasNext()) {
            String one = scan.next().toLowerCase();
            String two = scan.next().toLowerCase();
            Float number = scan.nextFloat();
            graph.newEdge(graph.newNode(one), graph.newNode(two), number);
        }

        scan.close();

        if (!graph.existsNode(start) || !graph.existsNode(end)) {
            throw new GraphException("One or more of the cities do not exist in the current dataset");
        }

        System.out.println("The distance from " + start + " to " + end + " is " +
                dijkstra(graph, start, end));
    }

    static Float dijkstra(Graph<String, Float> graph, String start, String end)
            throws HeapException, GraphException {

        HashMap<String, DijkstraData> map = new HashMap<>();

        Heap<DijkstraData> heap = new Heap<>(new Comparator<DijkstraData>() {
            @Override
            public int compare(DijkstraData obj1, DijkstraData obj2) {
                if (obj1.distance < obj2.distance) {
                    return -1;
                } else if (obj1.distance > obj2.distance) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // Initialization of the heap, setting all paths but the start to infinity and
        // adding them to the priority queue
        for (Node<String, Float> node : graph.getNodeList()) {
            if (node.label.equals(start)) {
                DijkstraData data = new DijkstraData(start);
                data.distance = 0;
                heap.insert(data);
                map.put(node.label, data);
            } else {
                DijkstraData data = new DijkstraData(node.label);
                heap.insert(data);
                map.put(node.label, data);
            }
        }

        while (!heap.isEmpty()) {
            DijkstraData min = heap.extractMin();
            min.visited = true;
            for (Node<String, Float> adj : graph.getAdiacent(min.label)) {
                DijkstraData adjData = map.get(adj.label);
                if (!adjData.visited) {
                    float tmp = min.distance + graph.getEdge(min.label, adj.label).weight;
                    if (tmp < adjData.distance) {
                        DijkstraData tmpData = new DijkstraData(adj.label);
                        tmpData.distance = tmp;
                        tmpData.previous = min.label;
                        heap.decrement(adjData, tmpData);
                        map.remove(adj.label);
                        map.put(adj.label, tmpData);
                    }
                }
            }
        }
        return map.get(end).distance;
    }
}