/**
 * @file ex1.c
 * @author Francesco Mazzucco (francesco.mazzucc539@edu.unito.it)
 * @brief main file of exercice 1 project
 *
 */

#include "sort.h"
#include "CSVread.h"
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <math.h>

/**
 * @brief compare function to order from field 1
 *
 * @param n first element to compare
 * @param m second element to compare
 * @return int -1 if n < m || 1 if n > m || 0 if n == m
 */
int compare_from_field1(void *n, void *m)
{
  OurVector *nn = (OurVector *)n;
  OurVector *mm = (OurVector *)m;

  int res = strcmp(nn->field1, mm->field1);

  if (res == 0)
  {
    if (nn->field2 < mm->field2)
      return -1;
    else if (nn->field2 > mm->field2)
      return 1;
    else
    {
      if (nn->field3 < mm->field3)
        return -1;
      else if (nn->field3 > mm->field3)
        return 1;
      else
        return 0;
    }
  }
  return res;
}

/**
 * @brief compare function to order from field 2
 *
 * @param n first element to compare
 * @param m second element to compare
 * @return int -1 if n < m || 1 if n > m || 0 if n == m
 */
int compare_from_field2(void *n, void *m)
{
  OurVector *nn = (OurVector *)n;
  OurVector *mm = (OurVector *)m;

  if (nn->field2 < mm->field2)
    return -1;
  else if (nn->field2 > mm->field2)
    return 1;
  else
  {
    if (nn->field3 < mm->field3)
      return -1;
    else if (nn->field3 > mm->field3)
      return 1;
    else
      return 0;
  }
}

/**
 * @brief compare function to order from field 3
 *
 * @param n first element to compare
 * @param m second element to compare
 * @return int -1 if n < m || 1 if n > m || 0 if n == m
 */
int compare_from_field3(void *n, void *m)
{
  OurVector *nn = (OurVector *)n;
  OurVector *mm = (OurVector *)m;

  if (nn->field3 < mm->field3)
    return -1;
  else if (nn->field3 > mm->field3)
    return 1;
  else
  {
    if (nn->field2 < mm->field2)
      return -1;
    else if (nn->field2 > mm->field2)
      return 0;
  }
}

/**
 * @brief selection and setting of quicksort algorithm
 *
 * @param path of the file to be sorted
 * @param size number of elements of the resulting array
 * @return OurVector* array sorted
 */
OurVector *sel_quicksort(char *path, size_t size)
{
  OurVector *array;
  int pivotSel;
  int sortSel;
  clock_t start, end;

  array = our_matrix_create_from_csv(path);

  printf("What kind of pivot you want to choose?\n0)Random\n1)Left\n2)Middle\n3)Right\n(A invalid choice will result in random being chosen)\nChoice: ");
  scanf(" %d", &pivotSel);

  printf("What row you want to select to be ordered\n1)Second row (String)\n2)Third row (Integer)\n3)Forth row (float)\nSelection: ");
  scanf(" %d", &sortSel);

  switch (sortSel)
  {
  case 1:
    start = clock();
    quicksort((void *)array, 0, (int)size - 1, sizeof(OurVector), pivotSel, compare_from_field1);
    end = clock();
    break;
  case 2:
    start = clock();
    quicksort((void *)array, 0, (int)size - 1, sizeof(OurVector), pivotSel, compare_from_field2);
    end = clock();
    break;
  case 3:
    start = clock();
    quicksort((void *)array, 0, (int)size - 1, sizeof(OurVector), pivotSel, compare_from_field3);
    end = clock();
    break;
  default:
    printf("Invalid selection, exiting...\n");
    exit(EXIT_FAILURE);
  }

  printf("%s sorted in %f seconds\n", path, (double)(end - start) / CLOCKS_PER_SEC);

  return array;
}

/**
 * @brief Calculates projected sort time for binary insertion sort algorithm
 *
 * @param path of the file to be sorted
 * @param size of the file (number of lines)
 * @param sortSel flag for the selected row
 */
void project_time_bin_ins_sort(char *path, size_t size, int sortSel)
{
  //(sqrt(tempo di esecuzione con 1000 el)/10000*size)^2
  clock_t start, end;
  OurVector *array = our_matrix_create_from_csv(path);
  size_t testSize = (size < 1000) ? size : 1000;
  switch (sortSel)
  {
  case 1:
    start = clock();
    binary_insertion_sort((void *)array, testSize, sizeof(OurVector), compare_from_field1);
    end = clock();
    break;
  case 2:
    start = clock();
    binary_insertion_sort((void *)array, testSize, sizeof(OurVector), compare_from_field2);
    end = clock();
    break;
  case 3:
    start = clock();
    binary_insertion_sort((void *)array, testSize, sizeof(OurVector), compare_from_field3);
    end = clock();
    break;
  default:
    printf("Invalid selection, exiting...\n");
    exit(EXIT_FAILURE);
  }

  float exeTime = exp2f(sqrtf((double)(end - start) / CLOCKS_PER_SEC) / (float)testSize * (float)size);

  printf("Binary insertion sort will take approximately %f seconds\n", exeTime);
}

/**
 * @brief selection and setting of binary insertion sort algorithm
 *
 * @param path of the file to be sorted
 * @param size number of elements of the resulting array
 * @return OurVector* array sorted
 */
OurVector *sel_bin_ins_sort(char *path, size_t size)
{
  int sortSel;
  char res;
  clock_t start, end;
  OurVector *array = our_matrix_create_from_csv(path);

  printf("What row you want to select to be ordered\n1) Second row (String)\n2) Third row (Integer)\n3) Forth row (float)\nSelection: ");
  scanf(" %d", &sortSel);

  project_time_bin_ins_sort(path, size, sortSel);
  printf("Are you ok with that ? (y/n)\n");
  scanf(" %c", &res);
  if (res != 'y')
    exit(EXIT_FAILURE);

  switch (sortSel)
  {
  case 1:
    start = clock();
    binary_insertion_sort((void *)array, size, sizeof(OurVector), compare_from_field1);
    end = clock();
    break;
  case 2:
    start = clock();
    binary_insertion_sort((void *)array, size, sizeof(OurVector), compare_from_field2);
    end = clock();
    break;
  case 3:
    start = clock();
    binary_insertion_sort((void *)array, size, sizeof(OurVector), compare_from_field3);
    end = clock();
    break;
  default:
    printf("Invalid selection, exiting...\n");
    exit(EXIT_FAILURE);
  }

  printf("%s sorted in %f seconds\n", path, (double)(end - start) / CLOCKS_PER_SEC);
  return array;
}

/**
 * @brief main loop
 *
 * @return int
 */
int main(void)
{
  char path[BUFF_SIZE];
  int algoFlag;
  size_t tmpSize;
  OurVector *array;

  printf("Insert path of file to be sorted: ");
  scanf(" %s", path);

  size_t size = get_size(path);
  printf("A total of %ld elements were found, how many do you want to sort?\nAnswer (insert 0 to sort the whole file): ", size);
  scanf(" %ld", &tmpSize);
  if (size < tmpSize)
  {
    printf("Invalid option exiting...");
    exit(EXIT_FAILURE);
  }
  else if (tmpSize > 0)
    size = tmpSize;

  printf("Select algorithm:\n1) Quicksort\n2) Binary insertion sort\nChoice: ");
  scanf(" %d", &algoFlag);

  switch (algoFlag)
  {
  case 1:
    array = sel_quicksort(path, size);
    break;
  case 2:
    array = sel_bin_ins_sort(path, size);
    break;
  default:
    printf("Invalid selection... exiting\n");
    exit(EXIT_FAILURE);
  }
  char resPath[BUFF_SIZE];
  printf("Where do you want to save the sorted array?\nFull path: ");
  scanf(" %s", resPath);
  printf("\n");

  FILE *output = fopen(resPath, "w");

  for (size_t i = 0; i < size; i++)
  {
    char buffer[BUFF_SIZE];
    snprintf(buffer, BUFF_SIZE, "%d,%s,%d,%.6f\n", array[i].uid, array[i].field1, array[i].field2,
             array[i].field3);
    fputs(buffer, output);
  }
  fclose(output);

  return 0;
}