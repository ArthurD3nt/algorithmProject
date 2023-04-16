cmake_minimum_required(VERSION 3.20)

option(TEST_MODE "Builds the tests for sort library")

# C standard => C99
set(CMAKE_C_STANDARD 99)

#add the required libraries
add_subdirectory(./libs/skiplist)

project(ex2)


if(TEST_MODE)   # tests target
    message("Test mode has been selected")

    add_subdirectory(./external/Unity)
    add_executable(tests src/tests.c)
    target_compile_options(tests PRIVATE -g)

    # linking libraries
    target_link_libraries(tests PUBLIC skiplist unity)

    # include directories for header files
    target_include_directories(tests PUBLIC ./libs/skiplist ./external/Unity/src)

else()     # production target
    add_executable(main_ex2 src/main_ex2.c)
    target_compile_options(main_ex2 PRIVATE -Wno-unused-result)
    # linking libraries
    target_link_libraries(main_ex2 PUBLIC skiplist)

    # include directories for header files
    target_include_directories(main_ex2 PUBLIC ./libs/skiplist)
endif()


