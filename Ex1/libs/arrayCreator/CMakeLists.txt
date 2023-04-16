project(arrayCreator)

# C standard => C99
set(CMAKE_C_STANDARD 99)

# arrayCreator library
add_library(arrayCreator CSVread.c)