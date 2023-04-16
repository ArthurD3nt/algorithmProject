package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * It represents the Heap (mini-heap) data structure
 * Elements in the array are always ordered with the root
 * being the smallest element
 *
 * @author Francesco Mazzucco
 * @param <T>: type of the ordered array Elements
 */

public class Heap<T> {

	private ArrayList<T> array = null;
	Comparator<? super T> comparator = null;
	private HashMap<T, Integer> map = null;

	/**
	 * Creates new empty mini-heap
	 *
	 * @param comparator : a comparator implementing the precendence relation
	 *                   between array elements
	 * @throws OrderedArrayException : if parameter is null
	 */
	public Heap(Comparator<? super T> comparator) throws HeapException {
		if (comparator == null)
			throw new HeapException("Heap constructor: comparator must be specified");
		this.array = new ArrayList<T>();
		this.comparator = comparator;
		this.map = new HashMap<T, Integer>();
	}

	/**
	 * Checks if the heap is empty or not
	 *
	 * @return true if and the heap is empty
	 */
	public boolean isEmpty() {
		return this.array.isEmpty();
	}

	/**
	 * Gets the parent of the Element pointed by i
	 *
	 * @param i: index of the element to get the parent of
	 * @return: the parent of the element at index i or the element itself if i == 0
	 * @throws HeapException if index is out of bounds
	 */
	public T getParentOf(int i) throws HeapException {
		if (i >= this.size())
			throw new HeapException("The given index is out of bounds");
		if (i == 0)
			return this.array.get(i);
		return this.array.get((i - 1) / 2);
	}

	/**
	 * Gets the the right child of the Element pointed by i
	 *
	 * @param i: index of the element to get the right child of
	 * @return: the right child of the element at index i
	 * @throws HeapException if i is out of bounds
	 */
	public T getRightOf(int i) throws HeapException {
		if (i >= this.size())
			throw new HeapException("The given index is out of bounds");
		if (2 * i + 2 <= this.size())
			return this.array.get(2 * i + 2);
		else
			return this.array.get(i);
	}

	/**
	 * Gets the the right child of the Element pointed by i
	 *
	 * @param i: index of the element to get the left child of
	 * @return: the left child of the element at index i
	 * @throws HeapException if i is out of bounds
	 */
	public T getLeftOf(int i) throws HeapException {
		if (i >= this.size())
			throw new HeapException("The given index is out of bounds");
		if (2 * i + 1 < this.size())
			return this.array.get(2 * i + 1);
		else
			return this.array.get(i);
	}

	/**
	 * Gets the parent of element T
	 *
	 * @param element
	 * @return the parent of the element T or the element itself if i == 0
	 * @throws HeapException if the given element isn't contained inside the Heap
	 */
	public T getParentOf(T element) throws HeapException {
		if (map.containsKey(element) == false)
			throw new HeapException("There is no such element in the heap");
		int i = map.get(element);
		if (i == 0)
			return this.array.get(i);
		return this.array.get((i - 1) / 2);

	}

	/**
	 * Gets right child of element T
	 *
	 * @param element
	 * @return the left child of the element
	 * @throws HeapException if the given element isn't contained inside the Heap
	 */
	public T getRightOf(T element) throws HeapException {
		if (map.containsKey(element) == false)
			throw new HeapException("There is no such element in the heap");
		int i = map.get(element);
		if (2 * i + 2 < this.size())
			return this.array.get(2 * i + 2);
		else
			return this.array.get(i);
	}

	/**
	 * Gets left child of element T
	 *
	 * @param element element to get the left child of
	 * @return the left child of the element
	 * @throws HeapException if the given element isn't contained inside the Heap
	 */
	public T getLeftOf(T element) throws HeapException {
		if (map.containsKey(element) == false)
			throw new HeapException("There is no such element in the heap");
		int i = map.get(element);
		if (2 * i + 1 < this.size())
			return this.array.get(2 * i + 1);
		else
			return this.array.get(i);

	}

	/**
	 * Gets the size of the heap
	 *
	 * @return: size of heap
	 */
	public int size() {
		return this.array.size();
	}

	/**
	 * Inserts a element inside the heap
	 *
	 * @param element
	 * @param n
	 * @throws HeapException if getParentOf fails
	 */
	public void insert(T element) throws HeapException {
		int i = this.size();
		this.array.add(element);
		map.put(element, i);

		while (i != 0 && comparator.compare(this.array.get(i), this.getParentOf(i)) < 0) {
			swap(i, (i - 1) / 2);
			i = (i - 1) / 2;
		}
	}

	/**
	 * Extracts the minimum value inside the heap
	 *
	 * @throws HeapException if heapify fails
	 */
	public T extractMin() throws HeapException {
		if (this.isEmpty())
			throw new HeapException("Heap is empty");
		if (this.size() == 0) {
			throw new HeapException("Operation not permitted when the heap is empty");
		}
		T min = this.array.get(0);
		this.array.set(0, this.array.get(this.size() - 1));
		this.map.replace(this.array.get(0), 0);
		this.array.remove(this.size() - 1);
		this.heapify(0);
		return min;
	}

	/**
	 * Gets value pointed by i
	 *
	 * @param i index of value
	 * @return value pointed by i
	 */
	public T get(int i) {
		return this.array.get(i);
	}

	private void heapify(int i) throws HeapException {
		int smallest = i;
		if (2 * i + 1 < this.size() && comparator.compare(this.getLeftOf(i), this.array.get(smallest)) < 0)
			smallest = 2 * i + 1;
		if (2 * i + 2 < this.size() && comparator.compare(this.getRightOf(i), this.array.get(smallest)) < 0)
			smallest = 2 * i + 2;
		if (smallest != i) {
			swap(i, smallest);
			heapify(smallest);
		}
	}

	private void swap(int i1, int i2) throws HeapException {
		T t1 = this.array.get(i1);
		T t2 = this.array.get(i2);

		if (map.remove(t2) == null || map.remove(t1) == null)
			throw new HeapException("The mapping of the key is null");

		this.array.set(i2, t1);
		map.put(t1, i2);

		this.array.set(i1, t2);
		map.put(t2, i1);
	}

	/**
	 * Decrements the inputted value
	 *
	 * @param element:  the element to decrement
	 * @param newvalue: the value of the decremented element
	 * @throws HeapException if heapify fails
	 */
	public void decrement(T element, T newvalue) throws HeapException {
		if (!map.containsKey(element))
			throw new HeapException("The given element " + element + " isn't inside the heap");
		int i = map.get(element);
		if (comparator.compare(element, newvalue) > 0) {
			this.array.set(i, newvalue);
			map.remove(element);
			map.put(newvalue, i);

			while (i > 0 && comparator.compare(this.array.get(i), this.getParentOf(i)) < 0) {
				swap(i, (i - 1) / 2);
				i = (i - 1) / 2;
			}
		}
	}
}