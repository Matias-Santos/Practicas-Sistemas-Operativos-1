#!/bin/bash
old_ifs=$IFS
IFS=$(echo -en "\n\b")

rm -f "$3"
for line in $(cat $1); do
    let palabras=$(echo $line | wc -w)
    if (( "$palabras" <= $2 )); then
        echo "$line" >> $3
    fi
    palabras=0
done

IFS=$old_ifs