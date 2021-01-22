#!/bin/bash
old_ifs=$IFS
#IFS=";"
cantLineas=$(cat $1 | wc -l)
total=0
for line in $(cat $1); do
    let tiempo=$(echo "$line" | cut -d ';' -f1)
    let porcentaje=$(echo "$line" | cut -d ';' -f2)
    echo tiempo: $tiempo
    echo porcentaje: $porcentaje
    let total+=$(($tiempo/$porcentaje))
    echo totalcalculado: $(($tiempo/$porcentaje))
done
total=$(($total/1000))
total=$(($total/$cantLineas))
echo cantLineas: $cantLineas
echo promedio: $total

IFS=$old_ifs