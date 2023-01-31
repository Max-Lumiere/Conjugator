#!/bin/bash

path="../iosApp/Strings.strings"

rm $path
touch $path

i=1
echo "Starting strings generation"
for var in "$@"
do
    echo "\"polished_red_string_$i\" = \"$var\";" >> $path
    echo "polished_red_string_$i has been generated"
    ((i+=1))
done

echo "" >> $path

echo "Finishing strings generation"
