#!/bin/sh

./gradlew ':network:spotlessApply'
./gradlew ':client:spotlessApply'
./gradlew ':huffman:spotlessApply'

