/**
 * @file tests.c
 * @author Francesco Mazzucco (francesco.mazzucc539@edu.unito.it)
 * @brief tests for skiplist library
 *
 */

#include "unity.h"
#include "skiplist.h"
#include <stdio.h>

void setUp(void) {}
void tearDown(void) {}

int int_compare(void *n, void *m)
{
  if ((int *)n < (int *)m)
    return -1;
  else if ((int *)n > (int *)m)
    return 1;
  return 0;
}

void test_skiplist_create(void)
{
  SkipList *list = skiplist_create(int_compare);
  TEST_ASSERT(list->head->item == NULL);
  TEST_ASSERT(list->max_level == 0);
  skiplist_delete(list);
}

void test_skiplist_insert(void)
{
  SkipList *list = skiplist_create(int_compare);
  Node *node4 = skiplist_insert(list, (void *)4);
  Node *node1 = skiplist_insert(list, (void *)1);
  Node *node3 = skiplist_insert(list, (void *)3);
  Node *node5 = skiplist_insert(list, (void *)5);
  Node *node2 = skiplist_insert(list, (void *)2);

  int expected[] = {1, 2, 3, 4, 5};
  int actual[5];

  Node *curr = list->head->next[0];
  for (int i = 0; i < 5; i++)
  {
    actual[i] = curr->item;
    curr = curr->next[0];
  }
  TEST_ASSERT_EQUAL_INT_ARRAY(expected, actual, 5);
  skiplist_delete(list);
}

void test_skiplist_search_true()
{
  SkipList *list = skiplist_create(int_compare);
  Node *node4 = skiplist_insert(list, (void *)4);
  Node *node1 = skiplist_insert(list, (void *)1);
  Node *node3 = skiplist_insert(list, (void *)3);
  TEST_ASSERT_TRUE(skiplist_search(list, (void*)4));
  skiplist_delete(list);
}

void test_skiplist_search_false()
{
  SkipList *list = skiplist_create(int_compare);
  Node *node4 = skiplist_insert(list, (void *)5);
  Node *node1 = skiplist_insert(list, (void *)1);
  Node *node3 = skiplist_insert(list, (void *)3);
  Node *c = list->head->next[0];
  TEST_ASSERT_FALSE(skiplist_search(list, (void*)6));
  skiplist_delete(list);
}

int main(void)
{
  UNITY_BEGIN();
  RUN_TEST(test_skiplist_create);
  RUN_TEST(test_skiplist_insert);
  RUN_TEST(test_skiplist_search_true);
  RUN_TEST(test_skiplist_search_false);
  return UNITY_END();
}