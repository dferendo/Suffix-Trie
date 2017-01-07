#!/bin/bash

cd ..
mvn exec:java -Dexec.mainClass="SuffixTrieRunner" -Dexec.args="src/main/resources/StringInputFile.txt show"

