/**
 * @file skiplist.h
 * @author Francesco Mazzucco (francesco.mazzucc539@edu.unito.it)
 * @brief skiplist interface
 *
 */

#ifndef SKIPLIST_H
#define SKIPLIST_H

#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include <sys/time.h>

#define MAX_HEIGHT 20

typedef struct _SkipList SkipList;
typedef struct _Node Node;

struct _SkipList
{
  Node *head;
  unsigned int max_level;
  int (*compare)(void *, void *);
};

struct _Node
{
  Node **next;
  unsigned int size;
  void *item;
};

/**
 * @brief creates a empty skiplist
 *
 * @return newly created empty SkipList
 */
SkipList *skiplist_create(int (*compare)(void *, void *));

/**
 * @brief deletes existing skiplist and all nodes
 *
 * @param l skiplist to delete
 */
void skiplist_delete(SkipList *l);

/**
 * @brief insert new element inside a skiplist
 *
 * @param l where the item will be inserted
 * @param el element to be inserted
 * @return Node containing the newly added element
 */
Node *skiplist_insert(SkipList *l, void *el);

/**
 * @brief determines the number of pointers to be included in a new node
 *
 * @return unsigned int
 */
unsigned int random_level();

/**
 * @brief Searches for given element inside given list
 *
 * @param l to seach into
 * @param el element to be searched
 * @return true if the element exists, false otherwise
 */
bool skiplist_search(SkipList *l, void *el);

#endif // SKIPLIST_H