/**
 * @file main_ex2.c
 * @author Francesco Mazzucco (francesco.mazzucc539@edu.unito.it)
 * @brief exercise 2 implementation
 *
 */

#include "main_ex2.h"
#include <ctype.h>

int str_compare(void *a, void *b)
{
  return strcmp((char *)a, (char *)b);
}

SkipList *dictionary_create(FILE *dictionary)
{
  char buffer[BUFF_SIZE];
  // Creating and populating skiplist with dictionary content
  SkipList *dict = skiplist_create(str_compare);

  while (fgets(buffer, BUFF_SIZE, dictionary) != NULL)
  {
    char *line = malloc((strlen(strtok(buffer, "\n")) + 1) * sizeof(char));
    if (line == NULL)
    {
      fprintf(stderr, "unable to allocate memory for the line read\n");
      exit(EXIT_FAILURE);
    }

    strcpy(line, buffer);
    skiplist_insert(dict, line);
  }

  return dict;
}

void fixer(FILE *fixMe, FILE *dictionary, FILE *fixed)
{
  char buffer[BUFF_SIZE];
  char delim[] = {" ,.:;\n!`\"?(){}[]\0"};

  if (fixMe == NULL || dictionary == NULL || fixed == NULL)
  {
    fprintf(stderr, "One of the files is invalid\n");
    exit(EXIT_FAILURE);
  }

  SkipList *dict = dictionary_create(dictionary);

  while (fgets(buffer, BUFF_SIZE, fixMe) != NULL)
  {
    char *tmp = strtok(buffer, delim);
    while (buffer != NULL && tmp != NULL)
    {
      tmp[0] = tolower(tmp[0]);
      if (skiplist_search(dict, tmp) == false)
      {
        printf("%s\n", tmp);
        fprintf(fixed, "%s\n", tmp);
      }
      tmp = strtok(NULL, delim);
    }
  }
  skiplist_delete(dict);
}

/**
 * @brief Main function
 *
 * @param argc number of inputted args
 * @param argv  [1] = dictionary file
 *              [2] = file to be fixed
 *              [3] = output path
 * @return int
 */
int main(int argc, char const *argv[])
{
  FILE *dictionary = fopen(argv[1], "r");
  FILE *fixMe = fopen(argv[2], "r");
  FILE *fixed = fopen(argv[3], "w");

  fixer(fixMe, dictionary, fixed);

  fclose(fixMe);
  fclose(dictionary);
  fclose(fixed);

  return 0;
}
