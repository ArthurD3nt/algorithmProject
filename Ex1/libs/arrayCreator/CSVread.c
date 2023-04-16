#include "CSVread.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

size_t get_size(const char *file_path)
{
  FILE *fp = fopen(file_path, "r");
  char buffer[BUFF_SIZE];
  size_t nrow = 0;

  while (fgets(buffer, BUFF_SIZE, fp))
    nrow++;

  fclose(fp);
  return nrow;
}

OurVector *our_matrix_create_from_csv(const char *file_path)
{
  char buffer[BUFF_SIZE];
  size_t nrow = get_size(file_path);
  OurVector *data = calloc(nrow, sizeof(OurVector));

  if (data == NULL)
  {
    fprintf(stderr, "unable to allocate memory for our matrix\n");
    exit(EXIT_FAILURE);
  }

  // opening the file
  FILE *fp = fopen(file_path, "r");
  if (fp == NULL)
  { // null pointer check
    fprintf(stderr, "Something whent wrong while reading %s\n", file_path);
    exit(EXIT_FAILURE);
  }

  // line reading loop (fgets returns one line and loop stops when EOF is encountered)
  int line = 0;
  while (fgets(buffer, BUFF_SIZE, fp) != NULL)
  {
    char *read_line_p = malloc((strlen(buffer) + 1) * sizeof(char));
    if (read_line_p == NULL)
    {
      fprintf(stderr, "unable to allocate memory for the line read\n");
      exit(EXIT_FAILURE);
    }
    strcpy(read_line_p, buffer);

    // uuid
    data[line].uid = atoi(strtok(read_line_p, ","));

    // field1
    char *tmp = strtok(NULL, ",");
    data[line].field1 = malloc((strlen(tmp) + 1) * sizeof(char));
    if (data[line].field1 == NULL)
    {
      fprintf(stderr, "unable to allocate memory for field1 in %d position\n", line);
      exit(EXIT_FAILURE);
    }
    strcpy(data[line].field1, tmp);

    // field2
    data[line].field2 = atoi(strtok(NULL, ","));

    // field3
    tmp = strtok(NULL, ",\n");
    sscanf(tmp, "%f", &data[line].field3);
    // matrix.data[line].field3 = strtod(tmp, NULL);

    line++;
    free(read_line_p);
  }

  fclose(fp);

  return data;
}
