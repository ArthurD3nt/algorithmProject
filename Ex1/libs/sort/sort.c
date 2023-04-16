#include <sys/time.h>
#include <string.h>
#include <stdint.h>
#include <stdio.h>

#include "sort.h"

void swap(void *p1, void *p2, size_t typeSize)
{
  uint8_t tmp[typeSize];
  memcpy(tmp, p1, typeSize);
  memcpy(p1, p2, typeSize);
  memcpy(p2, tmp, typeSize);
}

int random_index(int lower, int upper)
{
  struct timeval tm;
  gettimeofday(&tm, NULL);
  srand(tm.tv_usec);
  return (int)(rand() % (upper - lower)) + lower;
}

void quicksort(void *a, int l, int h, size_t typeSize, int pivotSelection, int (*compare)(void *, void *))
{
  if (l >= h)
    return;
  int pivot = partition(a, l, h, typeSize, pivotSelection, compare);
  quicksort(a, l, pivot - 1, typeSize, pivotSelection, compare);
  quicksort(a, pivot + 1, h, typeSize, pivotSelection, compare);
}

int partition(void *a, int l, int h, size_t typeSize, int pivotSelection, int (*compare)(void *, void *))
{
  int indPivot;
  int i = l;

  switch (pivotSelection)
  {
  case 1: // high value
    indPivot = h;
    break;
  case 2: // middle
    indPivot = l + ((h - l) / 2);
    break;
  case 3: // low value
    indPivot = l;
    break;
  default: // random ~= 0
    indPivot = random_index(l, h);
    break;
  }

  if (indPivot != h)
    swap(((uint8_t *)a) + (indPivot * typeSize), ((uint8_t *)a) + (h * typeSize), typeSize);

  void *pivot = ((uint8_t *)a) + (h * typeSize);

  for (int j = l; j < h; j++)
  {
    if ((*compare)(((uint8_t *)a) + (j * typeSize), pivot) <= 0)
    {
      swap(((uint8_t *)a) + (i * typeSize), ((uint8_t *)a) + (j * typeSize), typeSize);
      i++;
    }
  }
  swap(((uint8_t *)a) + (i * typeSize), ((uint8_t *)a) + (h * typeSize), typeSize);
  return i;
}

int binary_search(void *a, size_t typeSize, void *item, int low, int high, int (*compare)(void *, void *))
{
  if (high <= low)
    return ((*compare)(item, ((uint8_t *)a) + (low * typeSize)) > 0) ? (low + 1) : low;
  int mid = (low + high) / 2;
  if ((*compare)(item, ((uint8_t *)a) + (mid * typeSize)) == 0)
    return mid + 1;
  if ((*compare)(item, ((uint8_t *)a) + (mid * typeSize)) > 0)
    return binary_search(a, typeSize, item, mid + 1, high, compare);
  return binary_search(a, typeSize, item, low, mid - 1, compare);
}

void binary_insertion_sort(void *a, size_t arrSize, size_t typeSize, int (*compare)(void *, void *))
{
  int j;
  void *selected = malloc(typeSize);

  for (int i = 1; i < (int)arrSize; i++)
  {
    j = i - 1;
    memcpy(selected, ((uint8_t *)a) + (i * typeSize), typeSize);
    int placement = binary_search(a, typeSize, selected, 0, j, compare);

    while (j >= placement)
    {
      memcpy(((uint8_t *)a) + ((j + 1) * typeSize), ((uint8_t *)a) + (j * typeSize), typeSize);
      j--;
    }

    memcpy(((uint8_t *)a) + ((j + 1) * typeSize), selected, typeSize);
  }
}
