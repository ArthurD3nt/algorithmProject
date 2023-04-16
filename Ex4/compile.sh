#!/bin/bash

compile() {
    # Check for dependencies
    if [[ ! -d ./lib ]]; then
        printf "\nDownloading junit and hamcrest core libraries...\n"
        mkdir lib
        cd lib || exit 1
        wget "https://search.maven.org/remotecontent?filepath=junit/junit/4.13.2/junit-4.13.2.jar"
        wget "https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar"
        mv ./*junit-4.13.2.jar junit.jar
        mv ./*hamcrest-core-1.3.jar hamcrest.jar
        cd .. || exit
        printf "\nDone!\n"
    fi

    # Compile the graph library
    printf "\nCompiling the graph library and it's dependencies ...\n"
    rm -rf ./bin || exit 1
    mkdir bin || exit 1
    cd src || exit 1
    javac heap/Heap.java -d ../bin || exit 1
    javac -classpath ../lib/junit.jar:../lib/hamcrest.jar:. graph/*.java -d ../bin || exit 1
    javac -classpath ../lib/junit.jar:../lib/hamcrest.jar:. graph/StringGraphTestsRunner.java -d ../bin|| exit 1
    cd .. || exit 1
    printf "Done!\n"
}

# User input
printf "What do you want to do?\n
        1. Just compile the graph library\n
        2. Compile and run the tests for the graph library\n
        3. Compile and run the Dijkstra application\n"

read -p "Enter your choice: " choice

case $choice in
1) # Compile the graph library
    compile
    exit 0
    ;;
2) # Compile and run the tests for the graph library
    compile
    printf "Running the tests...\n"
    cd bin || exit 1
    java -classpath ../lib/junit.jar:../lib/hamcrest.jar:. graph/StringGraphTestsRunner || exit 1
    cd ..
    exit 0
    ;;
3) # Compile and run the Dijkstra application
    printf "Compiling the graph library...\n"
    compile

    read -p "Insert dataset absolute path: " dataset
    read -p "Insert starting vertex name: " start
    read -p "Insert target vertex name: " end
    cd bin || exit 1
    java graph/Dijkstra "$dataset" "$start" "$end"
    cd .. || exit 1
    exit 0
    ;;
*)
    printf "Invalid choice. Exiting...\n"
    exit 1
    ;;
esac
