package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;

import org.junit.Test;

public class StringGraphTests {

    class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    @Test
    public void testNewNode_many() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());

        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        graph.newNode("D");
        graph.newNode("E");
        graph.newNode("F");

        assertTrue(graph.existsNode("A"));
        assertTrue(graph.existsNode("B"));
        assertTrue(graph.existsNode("C"));
        assertTrue(graph.existsNode("D"));
        assertTrue(graph.existsNode("E"));
        assertTrue(graph.existsNode("F"));
    }

    @Test
    public void testNewNode_alreadyPresent() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        graph.newNode("A");
        graph.newNode("A");
        graph.newNode("A");
        assertTrue(graph.existsNode("A"));
    }

    @Test
    public void testNewNode_null() {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        try {
            graph.newNode(null);
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Node label cannot be null", e.getMessage());
        }
    }

    @Test
    public void testNewEdge_many_weigthed() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        graph.newEdge("A", "B", 1.0f);
        graph.newEdge("A", "C", 1.0f);
        graph.newEdge("B", "C", 3.0f);
        assertTrue(graph.existsEdge("A", "B"));
        assertTrue(graph.existsEdge("A", "C"));
        assertTrue(graph.existsEdge("B", "C"));
    }

    @Test
    public void testNewEdge_many_notWeigthed() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        graph.newEdge("A", "B");
        graph.newEdge("A", "C");
        graph.newEdge("B", "C");
        assertTrue(graph.existsEdge("A", "B"));
        assertTrue(graph.existsEdge("A", "C"));
        assertTrue(graph.existsEdge("B", "C"));
    }

    @Test
    public void testNewEdge_notExistingNode() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        try {
            graph.newEdge("A", "D", 1f);
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Node does not exist", e.getMessage());
        }
    }

    @Test
    public void testNewEdge_weigthed_weigthNotSpecified() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        try {
            graph.newEdge("A", "B");
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Edge weight cannot be null", e.getMessage());
        }
    }

    @Test
    public void testnewEdge_notWeighted_weigthSpecified() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        try {
            graph.newEdge("A", "B", 1f);
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Edge weight cannot be specified", e.getMessage());
        }
    }

    @Test
    public void testNewEdge_alreadyPresent() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        try {
            graph.newEdge("A", "B", 1f);
            graph.newEdge("A", "C", 2f);
            graph.newEdge("B", "C", 3f);
        } catch (GraphException e) {
            fail("Unexpected GraphException");
        }
        assertTrue(true);
    }

    @Test
    public void testNewEdge_weighted_null() {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        try {
            graph.newEdge(null, "B", 1f);
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Node label cannot be null", e.getMessage());
        }
    }

    @Test
    public void testNewEdge_weighted_null2() {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        try {
            graph.newEdge("A", null, 1f);
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Node label cannot be null", e.getMessage());
        }
    }

    @Test
    public void testNewEdge_weigthed_null3() {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        try {
            graph.newEdge("A", "B");
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Edge weight cannot be null", e.getMessage());
        }
    }

    @Test
    public void testNewEdge_notWeighted_null() {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        try {
            graph.newEdge(null, "B");
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Node label cannot be null", e.getMessage());
        }
    }

    @Test
    public void testNewEdge_notWeighted_null2() {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        try {
            graph.newEdge("A", null);
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Node label cannot be null", e.getMessage());
        }
    }

    @Test
    public void testIsDirected_directed() {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        assertTrue(graph.isDirected());
    }

    @Test
    public void testIsDirected_Notdirected() {
        Graph<String, Float> graph = new Graph<>(false, true, new StringComparator());
        assertFalse(graph.isDirected());
    }

    @Test
    public void testIsWeighted_weigthed() {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        assertTrue(graph.isWeighted());
    }

    @Test
    public void testIsWeighted_notWeigthed() {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        assertFalse(graph.isWeighted());
    }

    @Test
    public void testExistsNode_true() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        graph.newNode("A");
        assertTrue(graph.existsNode("A"));
    }

    @Test
    public void testExistsNode_false() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        graph.newNode("A");
        assertFalse(graph.existsNode("B"));
    }

    @Test
    public void testExistsNode_null() {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        try {
            graph.existsNode(null);
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Node label cannot be null", e.getMessage());
        }
    }

    @Test
    public void testExistsEdge_true() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newEdge("A", "B");
        assertTrue(graph.existsEdge("A", "B"));
    }

    @Test
    public void testExistsEdge_false() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        assertFalse(graph.existsEdge("A", "B"));
    }

    @Test
    public void testExistsEdge_null() {
        Graph<String, Float> graph = new Graph<>(true, true, new StringComparator());
        try {
            graph.existsEdge(null, "B");
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Node label cannot be null", e.getMessage());
        }
    }

    @Test
    public void testRemoveNode_true_directed() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newEdge("A", "B");
        graph.removeNode("A");
        assertFalse(graph.existsNode("A"));
        assertFalse(graph.existsEdge("A", "B"));
    }

    @Test
    public void testRemoveNode_true_notDirected() throws GraphException {
        Graph<String, Float> graph = new Graph<>(false, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newEdge("A", "B");
        graph.removeNode("A");
        assertFalse(graph.existsNode("A"));
        assertFalse(graph.existsEdge("A", "B"));
        assertFalse(graph.existsEdge("B", "A"));
    }

    @Test
    public void testRemoveNode_false_directed() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        graph.newEdge("A", "B");
        graph.removeNode("C");
        assertTrue(graph.existsNode("A"));
        assertTrue(graph.existsNode("B"));
        assertTrue(graph.existsEdge("A", "B"));
    }

    @Test
    public void testRemoveNode_false_notDirected() throws GraphException {
        Graph<String, Float> graph = new Graph<>(false, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        graph.newEdge("A", "B");
        graph.removeNode("C");
        assertTrue(graph.existsNode("A"));
        assertTrue(graph.existsNode("B"));
        assertTrue(graph.existsEdge("A", "B"));
        assertTrue(graph.existsEdge("B", "A"));
    }

    @Test
    public void testRemoveNode_null() {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        try {
            graph.removeNode(null);
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Node label cannot be null", e.getMessage());
        }
    }

    @Test
    public void testRemoveEdge_true_directed() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newEdge("A", "B");
        graph.removeEdge("A", "B");
        assertFalse(graph.existsEdge("A", "B"));
    }

    @Test
    public void testRemoveEdge_true_notDirected() throws GraphException {
        Graph<String, Float> graph = new Graph<>(false, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newEdge("A", "B");
        graph.removeEdge("A", "B");
        assertFalse(graph.existsEdge("A", "B"));
        assertFalse(graph.existsEdge("B", "A"));
    }

    @Test
    public void testRemoveEdge_false_directed() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        graph.newEdge("A", "B");
        try {
            graph.removeEdge("C", "B");
        } catch (GraphException e) {
            assertEquals("Library Graph: Edge does not exist", e.getMessage());
        }
        assertTrue(graph.existsNode("A"));
        assertTrue(graph.existsNode("B"));
        assertTrue(graph.existsNode("C"));
        assertTrue(graph.existsEdge("A", "B"));
    }

    @Test
    public void testRemoveEdge_false_notDirected() throws GraphException {
        Graph<String, Float> graph = new Graph<>(false, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        graph.newEdge("A", "B");
        try {
            graph.removeEdge("C", "B");
        } catch (GraphException e) {
            assertEquals("Library Graph: Edge does not exist", e.getMessage());
        }
        assertTrue(graph.existsNode("A"));
        assertTrue(graph.existsNode("B"));
        assertTrue(graph.existsNode("C"));
        assertTrue(graph.existsEdge("A", "B"));
        assertTrue(graph.existsEdge("B", "A"));
        assertFalse(graph.existsEdge("C", "B"));
        assertFalse(graph.existsEdge("B", "C"));
    }

    @Test
    public void testRemoveEdge_null() {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        try {
            graph.removeEdge(null, "B");
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Node label cannot be null", e.getMessage());
        }
    }

    @Test
    public void testRemoveEdge_invalidNodes() {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        try {
            graph.removeEdge("A", "B");
            fail("Expected GraphException");
        } catch (GraphException e) {
            assertEquals("Library Graph: Nodes with such labels do not exist", e.getMessage());
        }
    }

    @Test
    public void testNNode_zero() {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        assertEquals(0, graph.nNode());
    }

    @Test
    public void testNNode_one() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        assertEquals(1, graph.nNode());
    }

    @Test
    public void testNNode_two() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        assertEquals(2, graph.nNode());
    }

    @Test
    public void testNEdge_zero() {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        assertEquals(0, graph.nEdge());
    }

    @Test
    public void testNEdge_one() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newEdge("A", "B");
        assertEquals(1, graph.nEdge());
    }

    @Test
    public void testNEdge_two() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newEdge("A", "B");
        graph.newEdge("B", "A");
        assertEquals(2, graph.nEdge());
    }

    @Test
    public void testNEdge_directed_two() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        graph.newEdge("A", "B");
        graph.newEdge("B", "C");
        assertEquals(2, graph.nEdge());
    }

    @Test
    public void testGetNodeList_empty() {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        assertEquals(0, graph.getNodeList().size());
    }

    @Test
    public void testGetNodeList_one() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        assertEquals(1, graph.getNodeList().size());
    }

    @Test
    public void testGetNodeList_two() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        assertEquals(2, graph.getNodeList().size());
    }

    @Test
    public void testGetEdgeList() {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        assertEquals(0, graph.getEdgeList().size());
    }

    @Test
    public void testGetEdgeList_directed_one() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newEdge("A", "B");
        assertEquals(1, graph.getEdgeList().size());
    }

    @Test
    public void testGetEdgeList_directed_two() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newNode("C");
        graph.newEdge("A", "B");
        graph.newEdge("B", "C");
        assertEquals(2, graph.getEdgeList().size());
    }

    @Test
    public void testGetAdiacent_empty() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        assertEquals(0, graph.getAdiacent("A").size());
    }

    @Test
    public void testGetAdiacent_one() throws GraphException {
        Graph<String, Float> graph = new Graph<>(true, false, new StringComparator());
        graph.newNode("A");
        graph.newNode("B");
        graph.newEdge("A", "B");
        assertEquals(1, graph.getAdiacent("A").size());
    }
}
