#!/bin/bash

# @Author: Francesco Mazzucco (francesco.mazzucc539@edu.unito.it)

# This script is used to setup the environment for the project.

setup() {
    if [[ ! -d ./external ]]; then
        mkdir ./external
        cd external || exit
        git clone https://github.com/ThrowTheSwitch/Unity.git
        cd ..
    fi

    if [[ ! -d ./input ]]; then
        mkdir ./input
        cd ./input || exit
        wget "https://datacloud.di.unito.it/index.php/s/taii8aA8rNnXgCN/download/es2_dataset.zip"
        unzip es2_dataset.zip
        mv ./es2_dataset/dictionary.txt .
        mv ./es2_dataset/correctme.txt .
        rm es2_dataset.zip && rm -r ./es2_dataset
        cd ..
    fi

    if [[ ! -d ./output ]]; then
        mkdir ./output
        touch ./output/fixed.txt
    fi
}

clean() {
    rm -rf ./build
}

build(){
    clean
    mkdir -p ./build
    cd ./build || exit
    cmake ..
    make
    cd ..
}

tests(){
    clean
    mkdir -p ./build
    cd ./build || exit
    cmake -DTEST_MODE=ON ..
    make
    cd ..
    ./build/tests
}

setup
printf "Select an option:\n1. Setup\n2. Build\n3. Run\n4. Test\n5. Clean\n6. Exit\n"
read -r option;

case $option in
    1)
        setup
        ;;
    2)
        build
        ;;
    3)
        build
        ./build/main_ex2 ./input/dictionary.txt ./input/correctme.txt ./output/fixed.txt
        ;;
    4)
        tests
        ;;
    5)
        clean
        ;;
    6)
        exit 0
        ;;
    *)
        printf "Invalid option\n"
        exit 1
        ;;
esac