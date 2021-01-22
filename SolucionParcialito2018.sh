#!/bin/bash
old_ifs=$IFS
IFS=$(echo -en "\n\b")

cantLineas=$(cat $3 | wc -l)
if (( $1 < 1 )); then
    echo error1
elif (( $1 >= $2 )); then
    echo error2
elif (( $2 > $cantLineas )); then
    echo error3
else
    let contador=0
    for line in $(cat $3); do
        if (( $1 <= $contador )); then
            if (( $contador < $2 )); then
                echo "$line"
            fi
        fi
        let contador+=1
    done
fi

IFS=$old_ifs