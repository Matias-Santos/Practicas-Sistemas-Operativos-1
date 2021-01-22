#!/bin/bash

eq1=0
eq2=0
for(i=2;i<=6;i++); do
    ptj1=0
    ptj2=0
    for(j=2;j<=5;j++); do
        if(($j<4)); then
            let ptj1=$(head -n$i $1 | tail n-n1 | cut -d " " -f$j)
        else
            let ptj2=$(head -n$i $1 | tail n-n1 | cut -d " " -f$j)
        fi;
        if(($ptj1>$ptj2)); then
            let eq1++
        else
            if(($ptj2>$ptj1)) then
                let eq2++
            fi;
        fi;
    done;
done;
if((eq1>eq2)); then
    echo gano el equipo 1
else
    if((eq2>eq1)) then
        echo gano el equipo 2
    else
        echo empataron
    fi;
fi;
