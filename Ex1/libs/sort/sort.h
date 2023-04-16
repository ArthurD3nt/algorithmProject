#include <stdlib.h>

#ifndef SORT_H
#define SORT_H

// @Author: Francesco Mazzucco

/**
 * @brief quicksort algorithm
 * @param a array to be sorted
 * @param l lower boundary index (to order a array of size 10 you must pass a value of 0)
 * @param h higher boundary index (to order a array of size 10 you must pass a value of 9)
 * @param typeSize size of the given type
 * @param pivotSelection flag to selected the pivot mode
 *                       0 or invalid: random pivot
 *                       1: most left element
 *                       2: middle element
 *                       3: right element
 * @param compare function to compare given type
 */
void quicksort(void *a, int l, int h, size_t typeSize, int pivotSelection, int (*compare)(void *, void *));

/**
 * @brief partitions and sorts partition
 *
 * @param a array to be sorted
 * @param l starting index
 * @param h higher bound index
 * @param typeSize size of the given type
 * @param pivotSelection flag to choose the kind of pivot
 * @param compare function
 * @return int: index of the pivot
 */
int partition(void *a, int l, int h, size_t typeSize, int pivotSelection, int (*compare)(void *, void *));

/**
 * @brief binary insertion sort algorithm
 *
 * @param a array to be sorted
 * @param typeSize size of the given type
 * @param compare
 */
void binary_insertion_sort(void *a, size_t arrSize, size_t typeSize, int (*compare)(void *, void *));

/**
 * @brief binary search algorithm
 *
 * @param a array to search the item into
 * @param typeSize size of the given type
 * @param item to be searched
 * @param low lower bound index
 * @param high higher bound index
 * @param compare function
 * @return int: index of the item if found
 */
int binary_search(void *a, size_t typeSize, void *item, int low, int high, int (*compare)(void *, void *));

/**
 * @brief swaps two elements in memory
 *
 * @param p1 first element
 * @param p2 second element
 * @param typeSize size of the given type
 */
void swap(void *p1, void *p2, size_t typeSize);

/**
 * @brief Gets random index from range lower -> upper
 *
 * @param lower bound
 * @param upper bound
 * @return random number in range [lower, upper]
 */
int random_index(int lower, int upper);

#endif // SORT_H
