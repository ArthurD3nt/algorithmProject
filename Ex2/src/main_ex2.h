/**
 * @file main_ex2.h
 * @author Francesco Mazzucco (francesco.mazzucc539@edu.unito.it)
 * @brief exercise 2 header
 *
 */
#include <stdbool.h>
#include <stdio.h>
#include "skiplist.h"

#ifndef MAIN_EX2_H
#define MAIN_EX2_H

#define BUFF_SIZE 1024 /* size of the char buffer used to read one line
                        from dictionary and fixMe */

/**
 * @brief Fixes the fixMe file removing the words that are not present
 *        in the dictionary
 *
 * @param fixMe file to be fixed
 * @param dictionary with legal words
 * @param fixed fixed version of fixMe
 */
void fixer(FILE *fixMe, FILE *dictionary, FILE *fixed);

/**
 * @brief creates a skiplist containing the dictionary
 *
 * @param dictionary file containing the dictionary
 * @return SkipList* containing the dictionary
 */
SkipList *dictionary_create(FILE *dictionary);

/**
 * @brief string compare
 *
 * @return int > 0 if a > b and vice versa
 */
int str_compare(void* a, void* b);

#endif // MAIN_EX2_H