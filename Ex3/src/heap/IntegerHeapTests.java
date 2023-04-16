package heap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.Comparator;
import org.junit.Before;

public class IntegerHeapTests {

    class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
        }
    }

    private Integer i1, i2, i3, i4, i5, i6, i7;
    private Heap<Integer> heap;

    @Before
    public void createHeap() throws HeapException {
        i1 = 1;
        i2 = -12;
        i3 = 11;
        i4 = 200;
        i5 = 2;
        i6 = 2;
        i7 = -111111111;

        heap = new Heap<>(new IntegerComparator());
    }

    @Test
    public void testIsEmpty_zeroEl() {
        try {
            assertTrue(heap.isEmpty());
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testIsEmpty_oneEl() throws HeapException {
        try {
            heap.insert(i2);
            assertFalse(heap.isEmpty());
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testGetParentOf_indexVersion_zeroEl() throws HeapException {
        Boolean success = false;
        try {
            heap.getParentOf(0);
        } catch (Exception heapException) {
            success = true;
        }
        assertTrue(success);
    }

    @Test
    public void testGetParentOf_indexVersion_oneEl() throws HeapException {
        try {
            heap.insert(i5);
            assertTrue(heap.getParentOf(0) == i5);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testGetParentOf_indexVersion_twoEl() throws HeapException {
        try {
            heap.insert(i5);
            heap.insert(i7);
            assertTrue(heap.getParentOf(1) == i7);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testGetParentOf_elemVersion_zeroEl() throws HeapException {
        Boolean success = false;
        try {
            heap.getParentOf(i5);
        } catch (Exception heapException) {
            success = true;
        }
        assertTrue(success);
    }

    @Test
    public void testGetParentOf_elemVersion_oneEl() throws HeapException {

        try {
            heap.insert(i5);
            assertTrue(heap.getParentOf(i5) == i5);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testGetParentOf_elemVersion_twoEl() throws HeapException {
        try {
            heap.insert(i5);
            heap.insert(i7);
            assertTrue(heap.getParentOf(i5) == i7);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testgetRightOf_indexVersion_zeroEl() throws HeapException {
        Boolean success = false;
        try {
            heap.getRightOf(0);
        } catch (Exception heapException) {
            success = true;
        }
        assertTrue(success);
    }

    @Test
    public void testGetRightOf_indexVersion_oneEl() throws HeapException {
        try {
            heap.insert(i5);
            assertTrue(heap.getRightOf(0) == i5);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testGetRightOf_indexVersion_threeEl() throws HeapException {
        try {
            heap.insert(i5);
            heap.insert(i4);
            heap.insert(i7);
            assertTrue(heap.getRightOf(0) == i5 || heap.getRightOf(0) == i4);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testGetRightOf_elemVersion_zeroEl() throws HeapException {
        Boolean success = false;
        try {
            heap.getRightOf(i5);
        } catch (Exception heapException) {
            success = true;
        }
        assertTrue(success);
    }

    @Test
    public void testGetRightOf_elemVersion_oneEl() throws HeapException {
        try {
            heap.insert(i5);
            assertTrue(heap.getRightOf(i5) == i5);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testGetRightOf_elemVersion_threeEl() throws HeapException {
        try {
            heap.insert(i5);
            heap.insert(i7);
            heap.insert(i4);
            assertTrue(heap.getRightOf(i7) == i5 || heap.getRightOf(i7) == i4);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    // ---------------------------

    @Test
    public void testgetLeftOf_indexVersion_zeroEl() throws HeapException {
        Boolean success = false;
        try {
            heap.getLeftOf(0);
        } catch (Exception heapException) {
            success = true;
        }
        assertTrue(success);
    }

    @Test
    public void testGetLeftOf_indexVersion_oneEl() throws HeapException {
        try {
            heap.insert(i5);
            assertTrue(heap.getLeftOf(0) == i5);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testGetLeftOf_indexVersion_threeEl() throws HeapException {
        try {
            heap.insert(i5);
            heap.insert(i4);
            heap.insert(i7);
            assertTrue(heap.getLeftOf(0) == i5 || heap.getLeftOf(0) == i4);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testGetLeftOf_elemVersion_zeroEl() throws HeapException {
        Boolean success = false;
        try {
            heap.getLeftOf(i5);
        } catch (Exception heapException) {
            success = true;
        }
        assertTrue(success);
    }

    @Test
    public void testGetLeftOf_elemVersion_oneEl() throws HeapException {
        try {
            heap.insert(i5);
            assertTrue(heap.getRightOf(i5) == i5);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testGetLeftOf_elemVersion_threeEl() throws HeapException {
        try {
            heap.insert(i5);
            heap.insert(i7);
            heap.insert(i4);
            assertTrue(heap.getLeftOf(i7) == i5 || heap.getLeftOf(i7) == i4);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testSize_zeroEl() {
        try {
            assertTrue(heap.size() == 0);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testSize_manyEl() throws HeapException {

        try {
            heap.insert(i1);
            heap.insert(i2);
            heap.insert(i3);
            heap.insert(i4);
            heap.insert(i5);
            assertFalse(heap.size() == 0);
            assertTrue(heap.size() == 5);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testExtractMin_zeroEl() throws HeapException {
        Boolean success = false;
        try {
            heap.extractMin();
        } catch (Exception HeapException) {
            success = true;
        }
        assertTrue(success);
    }

    @Test
    public void testExtractMin_oneEl() throws HeapException {
        try {
            heap.insert(i2);
            heap.extractMin();
            assertTrue(heap.size() == 0);
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }

    }

    @Test
    public void testExtractMin_manyEl() throws HeapException {
        try {
            heap.insert(i1);
            heap.insert(i2);
            heap.insert(i7);
            heap.extractMin();
            assertEquals(i2, heap.get(0));
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }

    }

    @Test
    public void testDecrement_zeroEl() throws HeapException {
        Boolean success = false;
        try {
            heap.decrement(i1, i2);
        } catch (Exception e) {
            success = true;
        }
        assertTrue(success);
    }

    @Test
    public void testDecrement_oneEl() throws HeapException {
        try {
            heap.insert(i1);
            heap.decrement(i1, i7);
            assertEquals(i7, heap.get(0));

        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }

    @Test
    public void testDecrement_manyEl() throws HeapException {
        try {
            heap.insert(i1);
            heap.insert(i2);
            heap.decrement(i1, i7);
            assertEquals(i7, heap.get(0));
        } catch (Exception e) {
            fail("Caught Exception " + e);
        }
    }
}