/**
 * @file unattended.c
 * @author Francesco Mazzucco (francesco.mazzucc539@edu.unito.it)
 * @brief production like code for unattended code execution
 *
 */

#include "sort.h"
#include "CSVread.h"
#include <stdio.h>
#include <string.h>
#include <time.h>

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

int main(int argc, char const *argv[])
{
  clock_t start, end;
  OurVector *a = our_matrix_create_from_csv(argv[1]);

  switch (atoi(argv[2]))
  {
  case 1:
    printf("quicksort,");
    printf("%s,", argv[4]);
    switch (atoi(argv[3]))
    {
    case 1:
      printf("field1,");
      start = clock();
      quicksort(a, 0, atoi(argv[4]), sizeof(OurVector), atoi(argv[5]), compare_from_field1);
      end = clock();
      printf("%f,", (double)(end - start) / CLOCKS_PER_SEC);
      printf("%s\n", argv[5]);
      break;
    case 2:
      printf("field2,");
      start = clock();
      quicksort(a, 0, atoi(argv[4]), sizeof(OurVector), atoi(argv[5]), compare_from_field2);
      end = clock();
      printf("%f,", (double)(end - start) / CLOCKS_PER_SEC);
      printf("%s\n", argv[5]);
      break;
    case 3:
      printf("field3,");
      printf("%s", argv[5]);
      start = clock();
      quicksort(a, 0, atoi(argv[4]), sizeof(OurVector), atoi(argv[5]), compare_from_field3);
      end = clock();
      printf("%f,", (double)(end - start) / CLOCKS_PER_SEC);
      printf("%s\n", argv[5]);
      break;
    default:
      exit(EXIT_FAILURE);
    }
    break;
  case 2:
    printf("binary_insertion_sort,");
    printf("%s,", argv[4]);
    switch (atoi(argv[3]))
    {
    case 1:
      printf("field1,");
      start = clock();
      binary_insertion_sort((void *)a, atoi(argv[4]), sizeof(OurVector), compare_from_field1);
      end = clock();
      printf("%f\n", (double)(end - start) / CLOCKS_PER_SEC);
      break;
    case 2:
      printf("field2,");
      start = clock();
      binary_insertion_sort((void *)a, atoi(argv[4]), sizeof(OurVector), compare_from_field2);
      end = clock();
      printf("%f\n", (double)(end - start) / CLOCKS_PER_SEC);
      break;
    case 3:
      printf("field3,");
      start = clock();
      binary_insertion_sort((void *)a, atoi(argv[4]), sizeof(OurVector), compare_from_field3);
      end = clock();
      printf("%f\n", (double)(end - start) / CLOCKS_PER_SEC);
      break;
    default:
      exit(EXIT_FAILURE);
    }
    break;
  default:
    exit(EXIT_FAILURE);
  }
  return 0;
}
