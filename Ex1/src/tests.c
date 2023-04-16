#include "unity.h"
#include "sort.h"

/**
 * @file tests.c
 * @author Francesco Mazzucco (francesco.mazzucc539@edu.unito.it)
 * @brief File used to run tests with Unity testing framework made
 *        by the throw the switch org
 */

void setUp(void) {}
void tearDown(void) {}

int int_compare(void *n, void *m)
{
  int nn = *(int*)n;
  int mm = *(int*)m;

  if (nn > mm)
    return 1;
  else if (nn < mm)
    return -1;
  else
    return 0;
}

void test_swap_should_swap_two_bytes(void)
{
  int a[] = {1, 2, 3, 4, 5};
  int a_expected[] = {4, 2, 3, 1, 5};
  swap(&a[0], &a[3], sizeof(int));
  TEST_ASSERT_EQUAL_INT_ARRAY(a_expected, a, 5);
}

void test_swap_NULL_arg(void)
{
  int a[] = {1, 2, NULL, 4, 5};
  int a_expected[] = {NULL, 2, 1, 4, 5};
  swap(&a[0], &a[2], sizeof(int));
  TEST_ASSERT_EQUAL_INT_ARRAY(a_expected, a, 5);
}

void test_partition_rand(void)
{
  int a[] = {2, 1, 3, 5, 4};
  int p = partition((void *)a, 0, 4, sizeof(int), 0, int_compare);
  TEST_ASSERT(p >= 0 && p <= 4);
  for (size_t i = 0; i < p; i++)
  {
    TEST_ASSERT(a[i] <= a[p]);
  }
  for (size_t i = p + 1; i < 5; i++)
  {
    TEST_ASSERT(a[i] >= a[p]);
  }
}

void test_partition_right(void)
{
  int a[] = {2, 1, 3, 5, 4};
  int p = partition((void *)a, 0, 4, sizeof(int), 1, int_compare);
  TEST_ASSERT(p >= 0 && p <= 4);
  for (size_t i = 0; i < p; i++)
  {
    TEST_ASSERT(a[i] <= a[p]);
  }
  for (size_t i = p + 1; i < 5; i++)
  {
    TEST_ASSERT(a[i] >= a[p]);
  }
}

void test_partition_middle(void)
{
  int a[] = {2, 1, 3, 5, 4};
  int p = partition((void *)a, 0, 4, sizeof(int), 2, int_compare);
  TEST_ASSERT(p >= 0 && p <= 4);
  for (size_t i = 0; i < p; i++)
  {
    TEST_ASSERT(a[i] <= a[p]);
  }
  for (size_t i = p + 1; i < 5; i++)
  {
    TEST_ASSERT(a[i] >= a[p]);
  }
}

void test_partition_left(void)
{
  int a[] = {2, 1, 3, 5, 4};
  int p = partition((void *)a, 0, 4, sizeof(int), 3, int_compare);
  TEST_ASSERT(p >= 0 && p <= 4);
  for (size_t i = 0; i < p; i++)
  {
    TEST_ASSERT(a[i] <= a[p]);
  }
  for (size_t i = p + 1; i < 5; i++)
  {
    TEST_ASSERT(a[i] >= a[p]);
  }
}

void test_quicksort_rand(void)
{
  int a[] = {2, 1, 3, 5, 4, 6, 11, 8, 7, 10, 9};
  int a_expected[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
  quicksort((void *)a, 0, 10, sizeof(int), 0, int_compare);
  TEST_ASSERT_EQUAL_INT_ARRAY(a_expected, a, 11);
}

void test_quicksort_right(void)
{
  int a[] = {2, 1, 3, 5, 4, 6, 11, 8, 7, 10, 9};
  int a_expected[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
  quicksort((void *)a, 0, 10, sizeof(int), 1, int_compare);
  TEST_ASSERT_EQUAL_INT_ARRAY(a_expected, a, 11);
}

void test_quicksort_middle(void)
{
  int a[] = {2, 1, 3, 5, 4, 6, 11, 8, 7, 10, 9};
  int a_expected[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
  quicksort((void *)a, 0, 10, sizeof(int), 2, int_compare);
  TEST_ASSERT_EQUAL_INT_ARRAY(a_expected, a, 11);
}

void test_quicksort_left(void)
{
  int a[] = {2, 1, 3, 5, 4, 6, 11, 8, 7, 10, 9};
  int a_expected[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
  quicksort((void *)a, 0, 10, sizeof(int), 3, int_compare);
  TEST_ASSERT_EQUAL_INT_ARRAY(a_expected, a, 11);
}

void test_binary_search(void){
  int a[] = {1, 3, 4, 5, 6, 7, 8, 9, 2, 10, 11};
  TEST_ASSERT_EQUAL_INT(1, binary_search((void*)a, sizeof(int), &a[8], 0, 10, int_compare));
}

void test_binary_insertion_sort(void)
{
  int a[] = {2, 1, 3, 5, 4, 6, 11, 8, 7, 10, 9};
  int a_expected[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
  binary_insertion_sort((void *)a, 11, sizeof(int), int_compare);
  TEST_ASSERT_EQUAL_INT_ARRAY(a_expected, a, 11);
}

int main(void)
{
  UNITY_BEGIN();
  RUN_TEST(test_swap_should_swap_two_bytes);
  RUN_TEST(test_swap_NULL_arg);
  RUN_TEST(test_partition_rand);
  RUN_TEST(test_partition_middle);
  RUN_TEST(test_partition_right);
  RUN_TEST(test_quicksort_rand);
  RUN_TEST(test_quicksort_left);
  RUN_TEST(test_quicksort_middle);
  RUN_TEST(test_quicksort_right);
  RUN_TEST(test_binary_search);
  RUN_TEST(test_binary_insertion_sort);
  return UNITY_END();
}