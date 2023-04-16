cmake_minimum_required(VERSION 3.20)

option(TEST_MODE "Builds the tests for sort library")
option(UNATTENDED_MODE "Makes the algorithms work without the need of user input")

# C standard => C99
set(CMAKE_C_STANDARD 99)

#add the required libraries
add_subdirectory(./libs/sort)

project(ex1)


if(TEST_MODE)   # tests target
    message("Test mode has been selected")

    add_subdirectory(./external/Unity)
    add_executable(tests src/tests.c)

    # linking libraries
    target_link_libraries(tests PUBLIC sort unity)

    # include directories for header files
    target_include_directories(tests PUBLIC ./libs/sort ./external/Unity/src)

elseif(UNATTENDED_MODE) # unattended production like target
    add_subdirectory(./libs/arrayCreator)
    add_executable(unattended src/unattended.c)

    # linking libraries
    target_link_libraries(unattended PUBLIC sort arrayCreator)

    # include directories for header files
    target_include_directories(unattended PUBLIC ./libs/sort ./libs/arrayCreator)

else()     # production target
    add_subdirectory(./libs/arrayCreator)
    add_executable(ex1 src/ex1.c)
    target_compile_options(ex1 PRIVATE -Wno-unused-result)
    # linking libraries
    target_link_libraries(ex1 PUBLIC sort arrayCreator m)

    # include directories for header files
    target_include_directories(ex1 PUBLIC ./libs/sort ./libs/arrayCreator)
endif()


