#!/bin/bash

# @Author: Francesco Mazzucco (francesco.mazzucc539@edu.unito.it)

# This script is used to analyse the performance of the project.

if [[ ! -d ./input ]]; then
    mkdir ./input
    cd ./input || exit
    wget "https://datacloud.di.unito.it/index.php/s/X7qC8JSLNRtLxPC/download/records.zip"
    unzip records.zip
    rm records.zip
    cd ..
fi

if [[ ! -d ./build ]]; then
    mkdir ./build
    cd build || exit
    cmake -DUNATTENDED_MODE=ON ..
    make
    cd ..
fi

if [[ ! -d ./output ]]; then
    mkdir ./output
    cd ./output || exit
    touch unattended.csv
    cd ..
fi

# -- quicksort --
# n=10000000

# while [ "$n" -gt "1000" ]; do
#     for m in {0..3}; do
#         for i in {1..3}; do
#             ./build/unattended ./input/records.csv 1 "$i" "$n" "$m" >>./output/unattended.csv
#         done
#     done
#     n=$((n - 100000))
# done

# -- binary insertion sort --
n=1000000

while [ "$n" -gt "1000" ]; do
    for i in {1..3}; do
        ./build/unattended ./input/records.csv 2 "$i" "$n" >>./output/unattended.csv
    done
    n=$((n - 100000))
done
