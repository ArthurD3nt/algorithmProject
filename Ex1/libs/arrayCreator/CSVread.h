#ifndef CSVREAD_H
#define CSVREAD_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>

/**
 * @Author Francesco Mazzucco
 */
#define BUFF_SIZE 1024

typedef struct
{
  int uid;
  char *field1;
  int field2;
  float field3;
} OurVector;

/**
 * @param path to the read CSV
 * @return a matrix with the csv file content
 */
OurVector *our_matrix_create_from_csv(const char *file_path);

/**
 * Gets the size of the array
 * @param file_path
 * @return size of the array
 */
size_t get_size(const char *file_path);

#endif
