/**
 * @file skiplist.c
 * @author Francesco Mazzucco (francesco.mazzucc539@edu.unito.it)
 * @brief skiplist implementation
 *
 */

#include "skiplist.h"
#include <stdio.h>

SkipList *skiplist_create(int (*compare)(void *, void *))
{
  SkipList *l = malloc(sizeof(SkipList));
  l->max_level = 0;
  l->compare = *compare;
  l->head = malloc(sizeof(Node));
  l->head->size = MAX_HEIGHT;
  l->head->item = NULL;
  l->head->next = malloc(sizeof(Node *) * MAX_HEIGHT);
  for (int i = 0; i < MAX_HEIGHT; i++)
    l->head->next[i] = NULL;
  return l;
}

void skiplist_delete(SkipList *l)
{
  Node *curr = l->head;
  while (curr->next[0] != NULL)
  {
    Node *next = curr->next[0];
    free(curr->next);
    free(curr);
    curr = next;
  }
  free(l);
}

Node *skiplist_insert(SkipList *l, void *el)
{
  // Node creation
  Node *new = malloc(sizeof(Node));
  new->item = el;
  new->size = random_level();
  new->next = malloc(sizeof(Node *) * new->size);

  for (int i = 0; i < new->size; i++)
  {
    new->next[i] = NULL;
  }

  // Updating max level
  l->max_level = (new->size > l->max_level) ? new->size : l->max_level;

  Node *x = l->head;

  for (int k = l->max_level - 1; k >= 0; k--)
  {
    if (x->next[k] == NULL || l->compare(el, x->next[k]->item) < 0)
    {
      if (k < new->size)
      {
        new->next[k] = x->next[k];
        x->next[k] = new;
      }
    }
    else
    {
      x = x->next[k];
      k++;
    }
  }
  return new;
}

unsigned int random_level()
{
  struct timeval tm;
  gettimeofday(&tm, NULL);
  srand(tm.tv_usec);

  unsigned int lvl = 1;
  while (rand() % 2 && lvl < MAX_HEIGHT)
    lvl++;

  return lvl;
}

bool skiplist_search(SkipList *l, void *el)
{
  Node *x = l->head;

  for (int i = l->max_level - 1; i >= 0; i--)
  {
    while (i <= x->size && x->next[i] != NULL && l->compare(x->next[i]->item, el) < 0)
      x = x->next[i];
  }
  if (x->next[0] == NULL)
    return false;
  else
    x = x->next[0];

  if (l->compare(x->item, el) == 0)
    return true;
  else
    return false;
}